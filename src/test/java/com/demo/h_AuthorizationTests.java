package com.demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Base64;

public class h_AuthorizationTests {

    /*
     * =====================================
     * 1. BASIC AUTH
     * =====================================
     * Username: postman
     * Password: password
     */
    @Test
    public void basicAuthTest() {

        Response response = RestAssured
                .given()
                .auth()
                .basic("postman", "password")
                .get("https://postman-echo.com/basic-auth");

        Assert.assertEquals(response.getStatusCode(), 200);

        boolean authenticated =
                response.jsonPath().getBoolean("authenticated");

        Assert.assertTrue(authenticated);

        System.out.println(response.asPrettyString());
    }

    /*
     * =====================================
     * 2. PREEMPTIVE BASIC AUTH
     * =====================================
     */
    @Test
    public void preemptiveBasicAuthTest() {

        Response response = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("postman", "password")
                .get("https://postman-echo.com/basic-auth");

        Assert.assertEquals(response.getStatusCode(), 200);

        Assert.assertTrue(
                response.jsonPath().getBoolean("authenticated"));

        System.out.println(response.asPrettyString());
    }

    /*
     * =====================================
     * 3. MANUAL AUTH HEADER
     * =====================================
     */
    @Test
    public void manualBasicAuthHeaderTest() {

        String credentials = "postman:password";

        String encoded =
                Base64.getEncoder()
                        .encodeToString(credentials.getBytes());

        Response response = RestAssured
                .given()
                .header("Authorization", "Basic " + encoded)
                .get("https://postman-echo.com/basic-auth");

        Assert.assertEquals(response.getStatusCode(), 200);

        Assert.assertTrue(
                response.jsonPath().getBoolean("authenticated"));

        System.out.println(response.asPrettyString());
    }

    /*
     * =====================================
     * 4. API KEY AUTHORIZATION
     * =====================================
     * Sample only
     * Replace YOUR_API_KEY with actual key
     */
    @Test(enabled = false)
    public void apiKeyAuthorizationTest() {

        String apiKey = "YOUR_API_KEY";

        Response response = RestAssured
                .given()
                .header("x-api-key", apiKey)
                .get("https://api.example.com/users");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /*
     * =====================================
     * 5. BEARER TOKEN AUTHORIZATION
     * =====================================
     * Sample only
     */
    @Test(enabled = false)
    public void bearerTokenAuthorizationTest() {

        String token = "YOUR_JWT_TOKEN";

        Response response = RestAssured
                .given()
                .header("Authorization",
                        "Bearer " + token)
                .get("https://api.example.com/profile");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /*
     * =====================================
     * 6. OAUTH 2.0 TOKEN
     * =====================================
     * Sample only
     */
    @Test(enabled = false)
    public void oauth2AuthorizationTest() {

        String accessToken = "ACCESS_TOKEN";

        Response response = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .get("https://api.example.com/users");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /*
     * =====================================
     * 7. DIGEST AUTH
     * =====================================
     * Sample only
     */
    @Test(enabled = false)
    public void digestAuthTest() {

        Response response = RestAssured
                .given()
                .auth()
                .digest("username", "password")
                .get("https://api.example.com/protected");

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}