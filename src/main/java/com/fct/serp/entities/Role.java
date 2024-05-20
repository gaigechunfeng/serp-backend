package com.fct.serp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString(exclude = {"users", "resources"})
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<Resource> resources;
}
