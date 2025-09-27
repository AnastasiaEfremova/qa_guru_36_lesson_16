package test;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
    }
}