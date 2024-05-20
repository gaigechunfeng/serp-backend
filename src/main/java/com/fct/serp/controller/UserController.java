package com.fct.serp.controller;

import com.fct.serp.entities.User;
import com.fct.serp.enums.UserStatus;
import com.fct.serp.enums.UserType;
import com.fct.serp.model.PageInfo;
import com.fct.serp.services.ResourceService;
import com.fct.serp.services.UserService;
import com.fct.serp.util.Constants;
import com.fct.serp.web.WebResponse;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private static final Cache<String, AtomicInteger> LOGIN_ERR_CACHE = CacheBuilder.newBuilder()
            .maximumSize(200).expireAfterWrite(Duration.ofMinutes(30))
            .build();

    @Value("${login.max.pass.errortimes:3}")
    private int maxPassErrorTimes;

    private UserService userService;

    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public WebResponse<User> login(String username, String password, HttpServletRequest request) throws ExecutionException {

        if (StringUtils.isAnyBlank(username, password)) {
            throw new RuntimeException("用户和密码不能为空！");
        }

        if (LOGIN_ERR_CACHE.get(username, AtomicInteger::new).get() > maxPassErrorTimes) {

            throw new RuntimeException("登录失败次数超过最大次数 " + maxPassErrorTimes);
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在 " + username);
        }
        if (!StringUtils.equals(user.getStatus(), UserStatus.IN_USE.getValue())) {
            throw new RuntimeException("用户不允许登录 " + user.getStatus());
        }

        if (!StringUtils.equals(DigestUtils.sha256Hex(password), user.getPassword())) {

            LOGIN_ERR_CACHE.get(username, AtomicInteger::new).incrementAndGet();
            throw new RuntimeException("密码错误");
        } else {
            LOGIN_ERR_CACHE.invalidate(username);
        }
        request.getSession().setAttribute(Constants.SESSION_KEY_CURRENT_USER, user);

        user.setPassword("****");
        if (StringUtils.equals(user.getUserType(), UserType.ADMIN.getValue())) {
            user.setResources(resourceService.getResourcesByUser(user));
        }
        return WebResponse.success("登录成功", user);
    }

    @GetMapping("/current")
    public WebResponse<User> getCurrentUser(HttpServletRequest request) {

        HttpSession session = request.getSession();
        return WebResponse.success(null, (User) session.getAttribute(Constants.SESSION_KEY_CURRENT_USER));
    }

    @GetMapping("/query")
    public WebResponse<List<User>> query(User userVO, PageInfo pageInfo) {

        Page<User> userPage = userService.queryPage(userVO, pageInfo);

        pageInfo.setTotalRecords(userPage.getTotalElements());
        pageInfo.setTotalPages(userPage.getTotalPages());

        return new WebResponse<>(true, null, userPage.getContent(), pageInfo);
    }


}
