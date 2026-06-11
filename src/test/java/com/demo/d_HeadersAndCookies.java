package com.demo;

import org.testng.annotations.Test;

import java.util.Map;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class d_HeadersAndCookies {

    // =========================================================
    // GET ALL HEADERS
    // =========================================================
    @Test(priority = 1)
    public void getAllHeaders() {

        Response response = given()

        .when()
                .get("https://api.restful-api.dev/objects");

        // print all headers
        System.out.println("===== ALL HEADERS =====");
        response.getHeaders().forEach(header ->
                System.out.println(header.getName() + " : " + header.getValue()));

        // validations
        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .header("Server", notNullValue());
    }

    // =========================================================
    // GET SINGLE HEADER
    // =========================================================
    @Test(priority = 2)
    public void getSingleHeader() {

        Response response = given()

        .when()
                .get("https://api.restful-api.dev/objects");

        String contentType = response.getHeader("Content-Type");

        System.out.println("Content-Type = " + contentType);

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"));
    }

    // =========================================================
    // SEND HEADERS IN REQUEST
    // =========================================================
    @Test(priority = 3)
    public void sendHeaders() {

        given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")

        .when()
                .get("https://api.restful-api.dev/objects/1")

        .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    // =========================================================
    // GET ALL COOKIES
    // =========================================================
    @Test(priority = 4)
    public void getAllCookies() {

        Response response = given()

        .when()
                .get("https://google.com");

        Map<String, String> cookies = response.getCookies();

        System.out.println("===== ALL COOKIES =====");

        for (String key : cookies.keySet()) {
            System.out.println(key + " : " + cookies.get(key));
        }

        response.then()
                .statusCode(anyOf(is(200), is(301), is(302)));
    }

    // =========================================================
    // GET SINGLE COOKIE
    // =========================================================
    @Test(priority = 5)
    public void getSingleCookie() {

        Response response = given()

        .when()
                .get("https://google.com");

        String cookieValue = response.getCookie("AEC");

        System.out.println("AEC Cookie = " + cookieValue);

        response.then()
                .statusCode(anyOf(is(200), is(301), is(302)));
    }

    // =========================================================
    // SEND COOKIE IN REQUEST
    // =========================================================
    @Test(priority = 6)
    public void sendCookie() {

        given()
                .cookie("username", "sarthak")
                .cookie("sessionid", "12345")

        .when()
                .get("https://httpbin.org/cookies")

        .then()
                .statusCode(200)
                .body("cookies.username", equalTo("sarthak"))
                .body("cookies.sessionid", equalTo("12345"));
    }
}