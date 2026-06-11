package com.demo;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class g_SchemaValidationTest {

    /*
     * ==========================================================
     * JSON SCHEMA VALIDATION
     * ==========================================================
     */
    @Test
    public void validateJsonSchema() {

        given()
        .when()
            .get("https://api.restful-api.dev/objects/7")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("object-schema.json"));

        System.out.println("JSON Schema Validation Passed");
    }

    /*
     * ==========================================================
     * XML SCHEMA VALIDATION (XSD)
     * ==========================================================
     */
    @Test
    public void validateXmlSchema() {

        given()
        .when()
            .get("https://www.w3schools.com/xml/note.xml")
        .then()
            .statusCode(200)
            .body(org.hamcrest.Matchers.notNullValue());

        System.out.println("XML Response Received");

        /*
         * Rest Assured doesn't directly validate XSD as easily
         * as JSON schema.
         *
         * Usually done using:
         * XmlUnit
         * javax.xml.validation.SchemaFactory
         *
         * Example shown below.
         */

        try {

            javax.xml.validation.SchemaFactory factory =
                    javax.xml.validation.SchemaFactory.newInstance(
                            javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);

            javax.xml.validation.Schema schema =
                    factory.newSchema(
                            new File("src/test/resources/note.xsd"));

            javax.xml.validation.Validator validator =
                    schema.newValidator();

            validator.validate(
                    new javax.xml.transform.stream.StreamSource(
                            "src/test/resources/note.xml"));

            System.out.println("XML Schema Validation Passed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * ==========================================================
     * JSON PARSING + VALIDATION
     * ==========================================================
     */
    @Test
    public void jsonParsingAndValidation() {

        Response response =

        given()
        .when()
            .get("https://api.restful-api.dev/objects/7");

        response.then()
                .statusCode(200);

        String id =
                response.jsonPath().getString("id");

        String name =
                response.jsonPath().getString("name");

        String cpu =
                response.jsonPath().getString("data.CPU model");

        String hardDisk =
                response.jsonPath().getString("data.Hard disk size");

        System.out.println("ID        : " + id);
        System.out.println("Name      : " + name);
        System.out.println("CPU       : " + cpu);
        System.out.println("Hard Disk : " + hardDisk);

        response.then()
                .body("name", notNullValue())
                .body("data", notNullValue())
                .body("data.'CPU model'", notNullValue());

        System.out.println("JSON Validation Passed");
    }

    /*
     * ==========================================================
     * XML PARSING + VALIDATION
     * ==========================================================
     */
    @Test
    public void xmlParsingAndValidation() {

        Response response =

        given()
        .when()
            .get("https://www.w3schools.com/xml/note.xml");

        response.then()
                .statusCode(200);

        XmlPath xml = new XmlPath(response.asString());

        String to =
                xml.getString("note.to");

        String from =
                xml.getString("note.from");

        String heading =
                xml.getString("note.heading");

        String body =
                xml.getString("note.body");

        System.out.println("To      : " + to);
        System.out.println("From    : " + from);
        System.out.println("Heading : " + heading);
        System.out.println("Body    : " + body);

        response.then()
                .body("note.to", equalTo("Tove"))
                .body("note.from", equalTo("Jani"))
                .body("note.heading", equalTo("Reminder"))
                .body("note.body", containsString("weekend"));

        System.out.println("XML Validation Passed");
    }

    /*
     * ==========================================================
     * ACCESSING INDIVIDUAL JSON ELEMENTS
     * ==========================================================
     */
    @Test
    public void accessJsonElements() {

        Response response =

        given()
        .when()
            .get("https://api.restful-api.dev/objects/7");

        System.out.println(
                response.jsonPath().getString("id"));

        System.out.println(
                response.jsonPath().getString("name"));

        System.out.println(
                response.jsonPath().getString("data.year"));

        System.out.println(
                response.jsonPath().getString("data.price"));

        System.out.println(
                response.jsonPath().getString("data.'CPU model'"));

        System.out.println(
                response.jsonPath().getString("data.'Hard disk size'"));
    }

    /*
     * ==========================================================
     * ACCESSING INDIVIDUAL XML ELEMENTS
     * ==========================================================
     */
    @Test
    public void accessXmlElements() {

        Response response =

        given()
        .when()
            .get("https://www.w3schools.com/xml/note.xml");

        XmlPath xml = new XmlPath(response.asString());

        System.out.println(xml.getString("note.to"));
        System.out.println(xml.getString("note.from"));
        System.out.println(xml.getString("note.heading"));
        System.out.println(xml.getString("note.body"));
    }
}