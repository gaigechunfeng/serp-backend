package com.fct.serp.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@ToString(exclude = {"roles"})
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String username;
    private String password;
    private String realname;
    private String mobile;
    private String company;
    @Column(length = 20)
    private String status;
    private String userType;

    @ManyToMany
    private List<Role> roles;

    @Transient
    private List<Resource> resources;

    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;


}
