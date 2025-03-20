package com.oris.lab2_01.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Abonent {
    private String name;
    private List<PhoneNumber> phoneNumbers;

    public Abonent(String name, String countryCode, String number) {
        this.name = name;
        PhoneNumber phoneNumber = new PhoneNumber(number, countryCode);
        phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber);
    }

    public void addPhoneNumber(String countryCode, String number) {
        PhoneNumber phoneNumber = new PhoneNumber(number, countryCode);
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<>();
        }
        phoneNumbers.add(phoneNumber);
    }
}