package com.demo;

import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class b_DifferentWaysToPostJSON {

    String BASE_URL = "https://api.restful-api.dev/objects";

    // =========================================================
    // 1. POST USING RAW STRING
    // =========================================================
    @Test(priority = 1)
    public void postUsingRawString() {

        String body = "{\n" +
                "\"name\":\"Apple MacBook Pro 16\",\n" +
                "\"data\":{\n" +
                "\"year\":2023,\n" +
                "\"price\":2500,\n" +
                "\"CPU model\":\"M2 Max\"\n" +
                "}\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(body)

        .when()
                .post(BASE_URL)

        .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("name", equalTo("Apple MacBook Pro 16"))
                .body("data.year", equalTo(2023))
                .body("data.price", equalTo(2500))
                .body("data.'CPU model'", equalTo("M2 Max"));
    }

    // =========================================================
    // 2. POST USING HASHMAP
    // =========================================================
    @Test(priority = 2)
    public void postUsingHashMap() {

        HashMap<String, Object> data = new HashMap<>();
        data.put("year", 2024);
        data.put("price", 3000);
        data.put("CPU model", "M3 Pro");

        HashMap<String, Object> body = new HashMap<>();
        body.put("name", "MacBook Air");
        body.put("data", data);

        given()
                .contentType(ContentType.JSON)
                .body(body)

        .when()
                .post(BASE_URL)

        .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("name", equalTo("MacBook Air"))
                .body("data.year", equalTo(2024))
                .body("data.price", equalTo(3000))
                .body("data.'CPU model'", equalTo("M3 Pro"));
    }

    // =========================================================
    // 3. POST USING JSONOBJECT
    // =========================================================
    @Test(priority = 3)
    public void postUsingJSONObject() {

        JSONObject data = new JSONObject();
        data.put("year", 2025);
        data.put("price", 4000);
        data.put("CPU model", "M4 Ultra");

        JSONObject body = new JSONObject();
        body.put("name", "Mac Studio");
        body.put("data", data);

        given()
                .contentType(ContentType.JSON)
                .body(body.toString())

        .when()
                .post(BASE_URL)

        .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("name", equalTo("Mac Studio"))
                .body("data.year", equalTo(2025))
                .body("data.price", equalTo(4000))
                .body("data.'CPU model'", equalTo("M4 Ultra"));
    }

    // =========================================================
    // 4. POST USING POJO
    // =========================================================
    @Test(priority = 4)
    public void postUsingPOJO() {

        DataPojo data = new DataPojo();
        data.setYear(2026);
        data.setPrice(5000);
        data.setCpuModel("M5 Extreme");

        MainPojo body = new MainPojo();
        body.setName("iMac Pro");
        body.setData(data);

        given()
                .contentType(ContentType.JSON)
                .body(body)

        .when()
                .post(BASE_URL)

        .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("name", equalTo("iMac Pro"))
                .body("data.year", equalTo(2026))
                .body("data.price", equalTo(5000))
                .body("data.'CPU model'", equalTo("M5 Extreme"));
    }

    // =========================================================
    // 5. POST USING EXTERNAL JSON FILE
    // =========================================================
    @Test(priority = 5)
    public void postUsingExternalJsonFile() throws IOException {

        String json = "{\n" +
                "\"name\":\"Dell XPS\",\n" +
                "\"data\":{\n" +
                "\"year\":2027,\n" +
                "\"price\":6000,\n" +
                "\"CPU model\":\"Intel i9\"\n" +
                "}\n" +
                "}";

        File file = new File("payload.json");

        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.close();

        given()
                .contentType(ContentType.JSON)
                .body(file)

        .when()
                .post(BASE_URL)

        .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("name", equalTo("Dell XPS"))
                .body("data.year", equalTo(2027))
                .body("data.price", equalTo(6000))
                .body("data.'CPU model'", equalTo("Intel i9"));
    }
}


// =========================================================
// POJO CLASS 1
// =========================================================
class MainPojo {

    private String name;
    private DataPojo data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataPojo getData() {
        return data;
    }

    public void setData(DataPojo data) {
        this.data = data;
    }
}


// =========================================================
// POJO CLASS 2
// =========================================================
class DataPojo {

    private int year;
    private int price;

    private String cpuModel;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    // JSON key mapping
    public String getCPUModel() {
        return cpuModel;
    }
}