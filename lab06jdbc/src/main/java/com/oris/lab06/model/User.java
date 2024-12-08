package com.oris.lab06.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    public User(Long id, String name) {
        this.id=id;
        this.name=name;
    }
    User(){}
}
