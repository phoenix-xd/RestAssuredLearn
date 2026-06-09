package com.demo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class a_HTTPSReq {

    // Store created object ID
    static String objectId;

    @BeforeClass
    public void setup() {

        // Base URI
        RestAssured.baseURI = "https://api.restful-api.dev";
    }

    // =====================================================
    // GET ALL OBJECTS
    // =====================================================

    @Test(priority = 1)
    public void getObjects() {

        given()

        .when()
            .get("/objects")

        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    // =====================================================
    // GET SINGLE OBJECT
    // =====================================================

    @Test(priority = 2)
    public void getSingleObject() {

        given()

        .when()
            .get("/objects/1")

        .then()
            .statusCode(200)
            .body("id", equalTo("1"))
            .body("name", notNullValue())
            .body("data", notNullValue());
    }

    // =====================================================
    // CREATE OBJECT
    // =====================================================

    @Test(priority = 3)
    public void createObject() {

        String requestBody = "{\n" +
                "  \"name\": \"Apple MacBook Pro 16\",\n" +
                "  \"data\": {\n" +
                "    \"year\": 2025,\n" +
                "    \"price\": 2500,\n" +
                "    \"CPU model\": \"M4\",\n" +
                "    \"Hard disk size\": \"1 TB\"\n" +
                "  }\n" +
                "}";

        Response response =

            given()
                .header("Content-Type", "application/json")
                .body(requestBody)

            .when()
                .post("/objects");

        // Extract created object ID
        objectId = response.jsonPath().getString("id");

        // Validations
        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo("Apple MacBook Pro 16"))
                .body("data.year", equalTo(2025))
                .body("data.price", equalTo(2500))
                .body("data.'CPU model'", equalTo("M4"))
                .body("data.'Hard disk size'", equalTo("1 TB"));
    }

    // =====================================================
    // UPDATE CREATED OBJECT
    // =====================================================

    @Test(priority = 4, dependsOnMethods = "createObject")
    public void updateObject() {

        String updateBody = "{\n" +
                "  \"name\": \"Apple MacBook Pro 16 Updated\",\n" +
                "  \"data\": {\n" +
                "    \"year\": 2026,\n" +
                "    \"price\": 3000,\n" +
                "    \"CPU model\": \"M5\",\n" +
                "    \"Hard disk size\": \"2 TB\"\n" +
                "  }\n" +
                "}";

        given()
            .header("Content-Type", "application/json")
            .body(updateBody)

        .when()
            .put("/objects/" + objectId)

        .then()
            .statusCode(200)
            .body("id", equalTo(objectId))
            .body("name", equalTo("Apple MacBook Pro 16 Updated"))
            .body("data.year", equalTo(2026))
            .body("data.price", equalTo(3000))
            .body("data.'CPU model'", equalTo("M5"))
            .body("data.'Hard disk size'", equalTo("2 TB"));
    }

    // =====================================================
    // DELETE CREATED OBJECT
    // =====================================================

    @Test(priority = 5, dependsOnMethods = "createObject")
    public void deleteObject() {

        given()

        .when()
            .delete("/objects/" + objectId)

        .then()
            .statusCode(200)
            .body("message",
                    equalTo("Object with id = " + objectId + " has been deleted."));
    }
}