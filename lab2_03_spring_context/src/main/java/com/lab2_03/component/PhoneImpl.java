package com.lab2_03.component;

public class PhoneImpl implements Phone {

    private String number;

    private String countryCode;

    public PhoneImpl(String number, String countryCode) {
        this.number = number;
        this.countryCode = countryCode;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String toString() {
        return "PhoneImpl{" +
                "number='" + number + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
