package com.fct.serp.services.test;

import com.fct.serp.entities.User;
import com.fct.serp.model.PageInfo;
import com.fct.serp.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {


//        User userVO = new User();
//        userVO.setUsername("gaige");
//        userVO.setPassword(DigestUtils.sha256Hex("gaige"));
//        userVO.setCompany("平安付科技有限公司上海分公司");
//        userVO.setMobile("17621622895");
//        userVO.setStatus(UserStatus.IN_USE.getValue());
//        userVO.setUserType(UserType.ADMIN.getValue());
//        userVO.setCreateTime(new Date());
//        userVO.setCreateUser("WK");
//
//        userService.save(userVO);


//        UserVO userVO  = userService.findByUsername("gaige");
//        userVO.setStatus(UserStatus.IN_USE);
//        userService.save(userVO);

        User userVO = new User();
        PageInfo pageInfo = new PageInfo(0, 20);
        Page<User> userPage = userService.queryPage(userVO, pageInfo);
        System.out.println(userPage);

    }
}
