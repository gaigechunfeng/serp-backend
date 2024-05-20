package com.fct.serp.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    IN_USE("IN_USE", "使用中"),
    LOCK("LOCK", "已锁定"),
    DEL("DEL", "已删除");

    private final String value;
    private final String desc;

    UserStatus(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
