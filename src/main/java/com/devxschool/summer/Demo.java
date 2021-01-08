package com.devxschool.summer;

import com.devxschool.summer.pojo.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Demo {
    @Test
    public void deserializationProcess() {
        /*
        * Deserialization is a process of converting byte stream to Objects.
        *
        * */
        String employeeJson = "{\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"surname\": \"Doe\",\n" +
                "    \"position\": \"Accountant\",\n" +
                "    \"salary\": 1234,\n" +
                "    \"isActive\": true\n" +
                "}";

        Gson gson = new Gson();

        Employee employee = gson.fromJson(employeeJson, Employee.class);

        System.out.println(employee.getFirstName());
        System.out.println(employee.getLastName());
        System.out.println(employee.getPosition());
        System.out.println(employee.getSalary());
        System.out.println(employee.getStatus());


        String employeeJson2 = "{\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Doe\",\n" +
                "    \"position\": \"Accountant\",\n" +
                "    \"salary\": 1234,\n" +
                "    \"status\": true,\n" +
                "    \"skills\": [\"communications\", \"finance\"],\n" +
                "    \"address\": {\n" +
                "        \"city\": \"Chicago\",\n" +
                "        \"state\": \"IL\"\n" +
                "    }\n" +
                "}";

        Employee employee2 = gson.fromJson(employeeJson2, Employee.class);

        System.out.println("====== NESTED JSON OBJECT DESERIALIZATION=====");
        System.out.println(employee2.getFirstName());
        System.out.println(employee2.getLastName());
        System.out.println(employee2.getAddress().getCity());
        System.out.println(employee2.getAddress().getState());
    }

    @Test
    public void serializationProcess() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setPosition("CEO");
        employee.setSalary(23456);
        employee.setStatus(false);

        List<String> skills = Arrays.asList("communication", "finance");
        employee.setSkills(skills);

        /*
        * When you need to include null values into json string then use GsonBuilder
        * and set serializeNulls() configuration
        * */

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();

        Gson gson = gsonBuilder.create();

        String employeeJson = gson.toJson(employee);

        System.out.println(employeeJson);
    }
}
