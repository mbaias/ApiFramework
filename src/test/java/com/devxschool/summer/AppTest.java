package com.devxschool.summer;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getUserDetailsById_1() {

        /**
         * given() section is optional and it is used to build a request (setting headers, body, path/query parameters etc.)
         *
         */

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .get("/users/4")
        .then()
            .statusCode(200)
            .body("id", Matchers.equalTo(4));

//        Assert.assertEquals(4, id);

//        Assert.assertEquals("Hoeger Mall", streetAddress);
    }



    @Test
    public void getUserDetailsById_2() {
        Response response = when().get("/users/4");

        int id = response.body().jsonPath().getInt("id");
        String latitude = response.body().jsonPath().getString("address.geo.lat");

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(4, id);
        Assert.assertEquals("29.4572", latitude);
    }

    @Test
    public void getUserDetailsById_3() {
        Response response = given()
            .pathParam("userId", "4")
            .when().request("GET", "/users/{userId}");

        int id = response.body().jsonPath().getInt("id");
        String latitude = response.body().jsonPath().getString("address.geo.lat");

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(4, id);
        Assert.assertEquals("29.4572", latitude);
    }

    @Test
    public void getAllUsers_1() {
        Response response = given()
            .queryParam("name", "Leanne Graham")
            .when().request("GET", "/users");

        response.then().body("$", Matchers.hasSize(1));

        int id = response.body().jsonPath().getInt("[0].id");
        System.out.println(id);
    }

    @Test
    public void createUser_1() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"baiastan\",\n" +
                        "    \"username\": \"mbaias\",\n" +
                        "    \"email\": \"afnvld@gmail.com\"\n" +
                        "}")
                .when().request("POST", "/users");

        Assert.assertEquals(201, response.getStatusCode());

        String name = response.body().jsonPath().getString("name");

        Assert.assertEquals("baiastan", name);
    }

    @Test
    public void deleteUser() {
        Response response = when().request("DELETE", "/users/4");

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("{}", response.asString());
    }

}
