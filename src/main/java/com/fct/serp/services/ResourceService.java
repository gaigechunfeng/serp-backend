package com.fct.serp.services;

import com.fct.serp.entities.Resource;
import com.fct.serp.entities.User;

import java.util.List;

public interface ResourceService {

    List<Resource> getResourcesByUser(User user);
}
