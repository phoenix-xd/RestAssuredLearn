package com.demo;

import com.demo.dataprovider.TestDataProvider;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class i_CreateUserTest {

    @Test(dataProvider = "userData",
          dataProviderClass = TestDataProvider.class)
    public void createUser(String name,
                           String job) {

        String payload =
                "{\n" +
                "  \"name\":\"" + name + "\",\n" +
                "  \"job\":\"" + job + "\"\n" +
                "}";

        Response response =
                RestAssured
                        .given()
                        .contentType("application/json")
                        .body(payload)
                        .post("https://reqres.in/api/users");

        Assert.assertEquals(
                response.getStatusCode(),
                201);

        JsonPath jp =
                response.jsonPath();

        String responseName =
                jp.getString("name");

        String responseJob =
                jp.getString("job");

        String id =
                jp.getString("id");

        String createdAt =
                jp.getString("createdAt");

        Assert.assertEquals(
                responseName,
                name);

        Assert.assertEquals(
                responseJob,
                job);

        Assert.assertNotNull(id);

        Assert.assertNotNull(createdAt);

        Assert.assertFalse(id.isEmpty());

        Assert.assertTrue(
                response.getTime() < 5000);

        System.out.println(
                "Created User => "
                        + response.asPrettyString());
    }
}