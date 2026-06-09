package com.demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {

    // @Test
    public void testGetRequest() {

        Response response =
            RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("content-encoding"),"gzip");
        Assert.assertEquals(response.header("content-type"), "application/json; charset=utf-8");
    }
}