package com.fct.serp.mappers;

import com.fct.serp.entities.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {

    List<Resource> findAllByStatus(String status);
}
