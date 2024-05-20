package com.fct.serp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class OperateLog {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @Column(length = 512)
    private String requestUri;

    @Column(length = 4000)
    private String params;

    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;
}
