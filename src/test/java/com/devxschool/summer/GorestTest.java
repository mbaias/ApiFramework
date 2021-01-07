package com.devxschool.summer;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GorestTest {

    private static Response apiResponse;

    @Test
    public void getAllUser_1() {
        Response response = given()
                .header("Authorization", "Bearer token")
                .header("Content-type", "application/json")
                .when().request("GET", "https://gorest.co.in/public-api/users");

        JsonPath jsonPath = response.body().jsonPath();

        String name = jsonPath.getString("data[0].name");

        System.out.println(name);
    }


    @Test
    public void createUser_1() {
        /*
        * Send a request to create a user
        * Save a response in class variable
        * Validate response (status code, response body)
        * */

        String bearerToken = "Bearer 5355736deeba143d1344135912e38fd3ac26f9b855ed281e5d08922ca9b65a7c";
        Response createdUserResponse = given()
                .header("Authorization", bearerToken)
                .header("Content-type", "application/json")
                .body("{\n" +
                        "    \"name\": \"John Doe\",\n" +
                        "    \"email\": \"johnDoe18@gmail.com\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"status\": \"Active\"\n" +
                        "}")
                .request("POST", "https://gorest.co.in/public-api/users");
        createdUserResponse.prettyPrint();
        Assert.assertEquals(200, createdUserResponse.getStatusCode());
        String email = createdUserResponse.body().jsonPath().getString("data.email");
        Assert.assertEquals("johnDoe18@gmail.com", email);

        // Get User Details


    }

    public static void getUserDetails_1() {
        String id = apiResponse.body().jsonPath().getString("data.id");
        Response newlyCreatedUser = given()
                .pathParam("userId", id)
                .when()
                .request("GET", "https://gorest.co.in/public-api/users/{userId}");

        Assert.assertEquals(200, newlyCreatedUser.getStatusCode());

        Assert.assertEquals(apiResponse.body().jsonPath().getString("data.name"), newlyCreatedUser.body().jsonPath().getString("data.name"));
        Assert.assertEquals(apiResponse.body().jsonPath().getString("data.email"), newlyCreatedUser.body().jsonPath().getString("data.email"));
    }

    @Test
    public void updateUser_1() {
        createUser_1();

        String id = apiResponse.body().jsonPath().getString("data.id");

        String bearerToken = "Bearer 5355736deeba143d1344135912e38fd3ac26f9b855ed281e5d08922ca9b65a7c";
        Response updatedUser = given()
                .header("Authorization", bearerToken)
                .header("Content-type", "application/json")
                .body("{\n" +
                        "    \"name\": \"Baias Mamet\",\n" +
                        "    \"email\": \"johnDoe15@gmail.com\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"status\": \"Active\"\n" +
                        "}")
                .pathParam("userId", id)
                .request("PUT", "https://gorest.co.in/public-api/users/{userId}");

        updatedUser.prettyPrint();
        Assert.assertEquals(200, updatedUser.getStatusCode());
        String name = updatedUser.body().jsonPath().getString("data.name");
        Assert.assertEquals("Baias Mamet", name);

        apiResponse = updatedUser;

        getUserDetails_1();
    }

    @Test
    public void deleteUser_1() {
        createUser_1();
        String id = apiResponse.body().jsonPath().getString("data.id");
        String bearerToken = "Bearer 5355736deeba143d1344135912e38fd3ac26f9b855ed281e5d08922ca9b65a7c";
        Response response = given()
                .header("Authorization", bearerToken)
                .pathParam("userId", id)
                .when()
                .request("DELETE", "https://gorest.co.in/public-api/users/{userId}");

        Assert.assertEquals(200, response.getStatusCode());
    }
}
