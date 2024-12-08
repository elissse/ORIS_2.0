package com.oris.lab09.model;


public record Client(Long id, String name, String email, ClientInfo clientInfo) {

    @Override
    public String toString() {
        return "%s %s %s".formatted(name, email, clientInfo.toString());
    }
}
