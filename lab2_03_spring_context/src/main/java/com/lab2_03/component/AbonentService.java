package com.lab2_03.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbonentService {

    @Autowired
    private Phone phone;

    public void print() {
        System.out.println(phone);
    }

}
