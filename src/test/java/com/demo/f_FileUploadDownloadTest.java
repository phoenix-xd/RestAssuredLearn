package com.demo;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class f_FileUploadDownloadTest {

    // =====================================================
    // SINGLE FILE UPLOAD
    // =====================================================

    //@Test(priority = 1)
    public void uploadSingleFile() {

        File file =
                new File("src/test/resources/sample1.txt");

        given()
                .multiPart("file", file)

        .when()
                .post("http://localhost:8080/upload")

        .then()
                .statusCode(200)
                .body(containsString("sample1.txt"));

        System.out.println("Single file uploaded successfully");
    }

    // =====================================================
    // MULTIPLE FILE UPLOAD
    // =====================================================

    //@Test(priority = 2)
    public void uploadMultipleFiles() {

        given()
                .multiPart("files",
                        new File("src/test/resources/sample1.txt"))

                .multiPart("files",
                        new File("src/test/resources/sample2.txt"))

                .multiPart("files",
                        new File("src/test/resources/sample3.txt"))

        .when()
                .post("http://localhost:8080/uploadMultiple")

        .then()
                .statusCode(200);

        System.out.println("Multiple files uploaded successfully");
    }

    // =====================================================
    // DOWNLOAD PDF FILE
    // =====================================================

    @Test(priority = 3)
    public void downloadPdfFile() throws Exception {

        byte[] pdfData =

                given()

                .when()
                        .get("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf")

                .then()
                        .statusCode(200)
                        .extract()
                        .asByteArray();

        String filePath =
                "downloads/dummy.pdf";

        Files.createDirectories(
                Paths.get("downloads"));

        Files.write(
                Paths.get(filePath),
                pdfData);

        File downloadedFile =
                new File(filePath);

        Assert.assertTrue(downloadedFile.exists());
        Assert.assertTrue(downloadedFile.length() > 0);

        System.out.println("PDF downloaded successfully");
        System.out.println("Size = " +
                downloadedFile.length());
    }

    // =====================================================
    // DOWNLOAD IMAGE FILE
    // =====================================================

    @Test(priority = 4)
    public void downloadImageFile() throws Exception {

        byte[] imageData =

                given()

                .when()
                        .get("https://picsum.photos/300")

                .then()
                        .statusCode(200)
                        .extract()
                        .asByteArray();

        String filePath =
                "downloads/image.jpg";

        Files.write(
                Paths.get(filePath),
                imageData);

        File image =
                new File(filePath);

        Assert.assertTrue(image.exists());
        Assert.assertTrue(image.length() > 0);

        System.out.println("Image downloaded successfully");
        System.out.println("Size = " +
                image.length());
    }

    // =====================================================
    // DOWNLOAD MULTIPLE FILES
    // =====================================================

    @Test(priority = 5)
    public void downloadMultipleFiles() throws Exception {

        String[] urls = {

                "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",

                "https://picsum.photos/200",

                "https://picsum.photos/300"
        };

        String[] fileNames = {

                "downloads/file1.pdf",

                "downloads/file2.jpg",

                "downloads/file3.jpg"
        };

        for (int i = 0; i < urls.length; i++) {

            byte[] data =

                    given()

                    .when()
                            .get(urls[i])

                    .then()
                            .statusCode(200)
                            .extract()
                            .asByteArray();

            Files.write(
                    Paths.get(fileNames[i]),
                    data);

            File file =
                    new File(fileNames[i]);

            Assert.assertTrue(file.exists());
            Assert.assertTrue(file.length() > 0);

            System.out.println(
                    file.getName()
                            + " downloaded successfully. Size = "
                            + file.length());
        }
    }
}