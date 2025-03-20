package com.oris.lab2_01.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumber {
    private String number;
    private String countryCode;

    public PhoneNumber() {
    }

    public PhoneNumber(String number, String countryCode) {
        this.number = number;
        this.countryCode = countryCode;
    }
}
