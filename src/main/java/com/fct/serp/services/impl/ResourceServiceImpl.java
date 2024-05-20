package com.fct.serp.services.impl;

import com.fct.serp.entities.Resource;
import com.fct.serp.entities.Role;
import com.fct.serp.entities.User;
import com.fct.serp.enums.UserType;
import com.fct.serp.mappers.ResourceRepository;
import com.fct.serp.services.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceRepository resourceRepository;


    @Autowired
    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public List<Resource> getResourcesByUser(User user) {

        if (user == null) return new ArrayList<>();

        if (StringUtils.equals(user.getUserType(), UserType.ADMIN.getValue()))
            return resourceRepository.findAllByStatus("Y");

        Set<Resource> resourceSet = user.getRoles().stream().map(Role::getResources).flatMap(List::stream)
                .collect(Collectors.toSet());

        return List.copyOf(resourceSet);
    }
}
