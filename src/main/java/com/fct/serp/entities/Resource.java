package com.fct.serp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Resource {
    @Id
    @GeneratedValue
    private Long id;
    private Long parentId;
    private String name;
    private String description;
    private int level;
    private String url;
    @Column(length = 2)
    private String status;

    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;
}
