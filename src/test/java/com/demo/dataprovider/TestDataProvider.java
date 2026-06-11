
package com.demo.dataprovider;

import com.demo.utils.ExcelUtils;

import net.datafaker.Faker;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "userData")
    public static Object[][] getUserData()
            throws Exception {

        Object[][] excelData =
                ExcelUtils.getExcelData(
                        "src/test/resources/TestData.xlsx",
                        "Sheet1");

        Faker faker = new Faker();

        Object[][] finalData =
                new Object[excelData.length][2];

        for (int i = 0; i < excelData.length; i++) {

            finalData[i][0] =
                    faker.name().firstName();

            finalData[i][1] =
                    excelData[i][0];
        }

        return finalData;
    }
}