package com.devxschool.summer.pojo;

import lombok.*;

import java.util.List;

@Getter // Generate getters
@Setter // Generate setters
@ToString // Generate toString() method
@AllArgsConstructor // Generate a constructor that will initialize all class variables
@NoArgsConstructor // Generate a default constructor
    public class Employee {
    private String firstName;
    private String lastName;
    private String position;
    private int salary;

    @Getter(AccessLevel.NONE) // Ignore this property (Will not create a getter; Used when user want to create custom getter/setter)
    private boolean status;
    private List<String> skills;
    private Address address;

    public String getStatus() {
        if (status) {
            return "Active";
        } else {
            return "Inactive";
        }
    }
}
