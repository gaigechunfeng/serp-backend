package com.fct.serp.mappers;


import com.fct.serp.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

    Page<User> findAll(Example<User> userExample, Pageable pageable);
}
