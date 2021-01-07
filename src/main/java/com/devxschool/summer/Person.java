package com.devxschool.summer;

public class Person {
    private String name;
    private String gender;
    private int age;
    private Address address;

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Baias");
        person.setAge(19);
        person.setGender("male");


        Address address = person.setGender("male")
                                .setAge(10)
                                .setName("baias")
                                .setCity("Chicago")
                                .setState("IL");
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public Address setCity(String city) {
        this.address.setCity(city);
        return this.address;
    }

    public Person setState(String state) {
        this.address.setState(state);
        return this;
    }
}
