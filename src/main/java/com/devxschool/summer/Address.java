package com.devxschool.summer;

public class Address {
    private String city;
    private String state;

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }
}
