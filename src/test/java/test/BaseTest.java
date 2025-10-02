package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import test.config.ProjectConfig;
import test.helpers.Attach;

import java.util.Map;

import static org.aeonbits.owner.ConfigFactory.create;

public class BaseTest {

    public static ProjectConfig config = create(ProjectConfig.class);

    @BeforeAll
    static void setup() {

        validateConfig();

        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";

        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "127.0";
        Configuration.browserSize = "1920x1080";

        Configuration.remote = config.remoteUrl();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

    }

    private static void validateConfig() {
        if (config.username() == null || config.username().isEmpty()) {
            throw new RuntimeException("Username is not specified. Set it via -Dusername=your_username");
        }
        if (config.password() == null || config.password().isEmpty()) {
            throw new RuntimeException("Password is not specified. Set it via -Dpassword=your_password");
        }
    }

    @BeforeEach
    void beforeEachTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}