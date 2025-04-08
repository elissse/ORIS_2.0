package com.oris.lab2_04.orm;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DbConfig {
    private String jdbcdriver;
    private String jdbcurl;
    private String dbuser;
    private String dbpassword;
    private String poolsize;
    private String ddloption;

    @Override
    public String toString() {
        return "DbConfig{" +
                "jdbcdriver='" + jdbcdriver + '\'' +
                ", jdbcurl='" + jdbcurl + '\'' +
                ", dbuser='" + dbuser + '\'' +
                ", dbpassword='" + dbpassword + '\'' +
                ", poolsize='" + poolsize + '\'' +
                ", ddloption='" + ddloption + '\'' +
                '}';
    }
}