package com.fct.serp.enums;

import lombok.Getter;

@Getter
public enum UserType {
    ADMIN("ADMIN", "超级管理员"),
    NORMAL("NORMAL", "普通用户"),
    ;

    private final String value;
    private final String desc;

    UserType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
