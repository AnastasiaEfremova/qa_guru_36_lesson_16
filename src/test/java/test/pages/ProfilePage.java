package test.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import test.models.login.LoginResponse;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {

    SelenideElement gitPocketGuideBook = $("[id='see-book-Git Pocket Guide']"),
            deleteBookButton = $("#delete-record-undefined"),
            confirmDeletionButton = $("#closeSmallModal-ok");

    @Step("Remove ad banners")
    public ProfilePage removeAds() {
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    @Step("Add authorization cookies")
    public ProfilePage addAuthCookies(LoginResponse loginResponse) {
        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        return this;
    }

    @Step("Open profile page")
    public ProfilePage openProfile() {
        open("/profile");
        return waitUntilLoaded();
    }

    @Step("Verify profile page is loaded")
    public ProfilePage waitUntilLoaded() {
        gitPocketGuideBook.shouldBe(visible);
        return this;
    }

    @Step("Delete book from user basket")
    public ProfilePage deleteBook() {
        deleteBookButton.click();
        confirmDeletion();
        verifyBookDeleted();
        return this;
    }

    @Step("Confirm book deletion")
    public ProfilePage confirmDeletion() {
        confirmDeletionButton.click();
        return this;
    }

    @Step("Verify book is deleted")
    public ProfilePage verifyBookDeleted() {
        gitPocketGuideBook.shouldNotBe(visible);
        return this;
    }
}
