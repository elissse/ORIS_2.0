package com.oris.lab09.model;


public record ClientInfo(String phone, String address, String passport) {

    @Override
    public String toString() {
        return "%s %s %s".formatted(phone, address, passport);
    }
}
