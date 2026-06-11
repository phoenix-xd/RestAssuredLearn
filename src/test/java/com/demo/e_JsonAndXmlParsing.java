package com.demo;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class e_JsonAndXmlParsing {

    // ==========================================================
    // JSON VALIDATION + JSON PATH + EXTRACT INDIVIDUAL ELEMENTS
    // ==========================================================

    @Test(priority = 1)
    public void jsonParsingAndValidation() {

        Response response =

                given()

                .when()
                        .get("https://api.restful-api.dev/objects/7")

                .then()
                        .statusCode(200)
                        .contentType("application/json")

                        // Response validations
                        .body("id", equalTo("7"))
                        .body("name", equalTo("Apple MacBook Pro 16"))
                        .body("data.year", equalTo(2019))
                        .body("data.price", equalTo(1849.99f))
                        .body("data.'CPU model'", notNullValue())
                        .body("data.'Hard disk size'", notNullValue())

                        .extract()
                        .response();

        System.out.println("\n=========== COMPLETE JSON RESPONSE ===========");
        System.out.println(response.asPrettyString());

        JsonPath jp = response.jsonPath();

        // Access individual elements

        String id = jp.getString("id");
        String name = jp.getString("name");
        int year = jp.getInt("data.year");
        double price = jp.getDouble("data.price");
        String cpu = jp.getString("data.'CPU model'");
        String disk = jp.getString("data.'Hard disk size'");

        System.out.println("\n=========== EXTRACTED JSON VALUES ===========");

        System.out.println("ID          : " + id);
        System.out.println("Name        : " + name);
        System.out.println("Year        : " + year);
        System.out.println("Price       : " + price);
        System.out.println("CPU Model   : " + cpu);
        System.out.println("Hard Disk   : " + disk);

        // TestNG validations

        Assert.assertEquals(id, "7");
        Assert.assertEquals(name, "Apple MacBook Pro 16");
        Assert.assertTrue(price > 1000);
        Assert.assertNotNull(cpu);
        Assert.assertNotNull(disk);
    }

    // ==========================================================
    // JSON ARRAY PARSING
    // ==========================================================

    @Test(priority = 2)
    public void jsonArrayParsing() {

        Response response =

                given()

                .when()
                        .get("https://api.restful-api.dev/objects")

                .then()
                        .statusCode(200)

                        .extract()
                        .response();

        JsonPath jp = response.jsonPath();

        int totalObjects = jp.getList("$").size();

        String firstId = jp.getString("[0].id");
        String firstName = jp.getString("[0].name");

        System.out.println("\n=========== JSON ARRAY EXAMPLE ===========");

        System.out.println("Total Objects : " + totalObjects);
        System.out.println("First ID      : " + firstId);
        System.out.println("First Name    : " + firstName);

        Assert.assertTrue(totalObjects > 0);
        Assert.assertNotNull(firstId);
    }

    // ==========================================================
    // XML PARSING + XML VALIDATION + XML PATH
    // ==========================================================

    @Test(priority = 3)
    public void xmlParsingAndValidation() {

        Response response =

                given()

                .when()
                        .get("https://www.w3schools.com/xml/simple.xml")

                .then()
                        .statusCode(200)
                        .contentType(containsString("xml"))

                        .extract()
                        .response();

        System.out.println("\n=========== COMPLETE XML RESPONSE ===========");
        System.out.println(response.asPrettyString());

        XmlPath xp = new XmlPath(response.asString());

        // Access individual XML elements

        String firstFood =
                xp.getString("breakfast_menu.food[0].name");

        String firstPrice =
                xp.getString("breakfast_menu.food[0].price");

        String firstDescription =
                xp.getString("breakfast_menu.food[0].description");

        String secondFood =
                xp.getString("breakfast_menu.food[1].name");

        int totalFoods =
                xp.getList("breakfast_menu.food").size();

        System.out.println("\n=========== EXTRACTED XML VALUES ===========");

        System.out.println("First Food        : " + firstFood);
        System.out.println("First Price       : " + firstPrice);
        System.out.println("First Description : " + firstDescription);
        System.out.println("Second Food       : " + secondFood);
        System.out.println("Total Food Items  : " + totalFoods);

        // Assertions

        Assert.assertEquals(firstFood, "Belgian Waffles");
        Assert.assertTrue(firstPrice.contains("$"));
        Assert.assertEquals(totalFoods, 5);
    }

    // ==========================================================
    // XML LIST EXTRACTION
    // ==========================================================

    @Test(priority = 4)
    public void xmlListExtraction() {

        Response response =

                given()

                .when()
                        .get("https://www.w3schools.com/xml/simple.xml")

                .then()
                        .statusCode(200)

                        .extract()
                        .response();

        XmlPath xp = new XmlPath(response.asString());

        List<String> foodNames =
                xp.getList("breakfast_menu.food.name");

        System.out.println("\n=========== ALL FOOD ITEMS ===========");

        for (String food : foodNames) {
            System.out.println(food);
        }

        Assert.assertTrue(foodNames.size() > 0);
    }

    // ==========================================================
    // ADVANCED JSON PATH EXAMPLES
    // ==========================================================

    @Test(priority = 5)
    public void advancedJsonPathExamples() {

        Response response =

                given()

                .when()
                        .get("https://api.restful-api.dev/objects")

                .then()
                        .statusCode(200)

                        .extract()
                        .response();

        JsonPath jp = response.jsonPath();

        List<String> names =
                jp.getList("name");

        System.out.println("\n=========== ALL OBJECT NAMES ===========");

        for (String name : names) {
            System.out.println(name);
        }

        Assert.assertTrue(names.size() > 0);
    }
}