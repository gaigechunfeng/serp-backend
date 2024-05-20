package com.fct.serp.services.impl;

import com.fct.serp.entities.User;
import com.fct.serp.mappers.UserRepository;
import com.fct.serp.model.PageInfo;
import com.fct.serp.services.UserService;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

//    @Override
//    public User save(User user) {
//
//        return userRepository.save(user);
//    }

    @Override
    public Page<User> queryPage(User user, PageInfo pageInfo) {

        PageRequest pageRequest = PageRequest.of(pageInfo.getCurrent() - 1, pageInfo.getPageSize(),
                Sort.by("id").descending());
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withTransformer("username", o -> o.isPresent() && StringUtils.isBlank(o.get().toString()) ? Optional.empty() : o)
                .withTransformer("realname", o -> o.isPresent() && StringUtils.isBlank(o.get().toString()) ? Optional.empty() : o);
        return userRepository.findAll(Example.of(user, exampleMatcher), pageRequest);
    }
}
