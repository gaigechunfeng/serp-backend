package com.fct.serp.services;

import com.fct.serp.entities.User;
import com.fct.serp.model.PageInfo;
import org.springframework.data.domain.Page;

public interface UserService {

    User findByUsername(String username);

//    User save(User user);

    Page<User> queryPage(User userVO, PageInfo pageInfo);

}
