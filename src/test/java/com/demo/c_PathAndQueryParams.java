package com.demo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class c_PathAndQueryParams {

    // =========================================================
    // PATH PARAM EXAMPLE
    // URL:
    // https://api.restful-api.dev/objects/7
    // =========================================================
    @Test(priority = 1)
    public void pathParamExample() {

        given()
                .pathParam("id", 7)

        .when()
                .get("https://api.restful-api.dev/objects/{id}")

        .then()
                .statusCode(200)
                .body("id", equalTo("7"))
                .body("name", notNullValue());
    }

    // =========================================================
    // MULTIPLE PATH PARAMS EXAMPLE
    // =========================================================
    @Test(priority = 2)
    public void multiplePathParamsExample() {

        given()
                .pathParam("category", "objects")
                .pathParam("id", 3)

        .when()
                .get("https://api.restful-api.dev/{category}/{id}")

        .then()
                .statusCode(200)
                .body("id", equalTo("3"));
    }

    // =========================================================
    // QUERY PARAM EXAMPLE
    // URL: https://reqres.in/api/users?page=2
    // just for example no query param in actual API
    // =========================================================
    // @Test(priority = 3)
    public void queryParamExample() {

        given()
                .queryParam("page", 2)

        .when()
                .get("https://reqres.in/api/users")

        .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data", not(empty()));
    }

    // =========================================================
    // MULTIPLE QUERY PARAMS EXAMPLE
    // =========================================================
    // @Test(priority = 4)
    public void multipleQueryParamsExample() {

        given()
                .queryParam("page", 2)
                .queryParam("per_page", 3)

        .when()
                .get("https://reqres.in/api/users")

        .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("per_page", equalTo(3))
                .body("data.size()", lessThanOrEqualTo(3));
    }

    // =========================================================
    // PATH + QUERY PARAM TOGETHER
    // URL:
    // https://reqres.in/api/users/2?delay=3
    // =========================================================
    // @Test(priority = 5)
    public void pathAndQueryTogether() {

        given()
                .pathParam("userid", 2)
                .queryParam("delay", 3)

        .when()
                .get("https://reqres.in/api/users/{userid}")

        .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@reqres.in"));
    }
}