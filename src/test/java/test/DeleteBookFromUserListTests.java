package test;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.models.books.AddBookResponse;
import test.models.books.DeleteOneBookRequest;
import test.models.login.LoginResponse;
import test.pages.ProfilePage;
import test.services.AuthService;

import static io.qameta.allure.Allure.step;
import static test.api.ApiBooksClient.deleteOneBookFromCollection;
import static test.services.AuthService.*;
import static test.services.BookService.*;

@Owner("Ефремова Анастасия")
@Story("Удаление книги из пользовательской корзины")
public class DeleteBookFromUserListTests extends BaseTest {

    @Test
    @DisplayName("Удаление товара из корзины через UI")
    public void successDeleteBookWithSelenide() {

        LoginResponse loginResponse = step("Авторизация пользователя",
                AuthService::loginWithDefaultUser);

        step("Проверка ответа авторизации", () ->
                verifyLoginResponse(loginResponse));


        step("Очистка корзины пользователя", () ->
                deleteAllUserBooks(loginResponse.getUserId(), loginResponse.getToken()));

        String firstBookIsbn = step("Получение списка книг", () ->
                getAllBooks().getBooks().get(0).getIsbn());

        step("Добавление книги в корзину", () -> {
            AddBookResponse addResponse = addBookToUser(
                    loginResponse.getUserId(),
                    loginResponse.getToken(),
                    firstBookIsbn
            );
            verifyBookAdded(addResponse, firstBookIsbn);
        });

        // UI тест
        step("Удаление книги через UI и проверка", () -> {
            ProfilePage profilePage = new ProfilePage();
            profilePage.addAuthCookies(loginResponse)
                    .openProfile()
                    .removeAds()
                    .deleteBook()
                    .verifyBookDeleted();
        });
    }

    @Test
    @DisplayName("Удаление товара из корзины через API")
    public void successDeleteBookWithApi() {

        LoginResponse loginResponse = step("Авторизация пользователя",
                AuthService::loginWithDefaultUser);

        step("Проверка ответа авторизации", () ->
                verifyLoginResponse(loginResponse));


        step("Очистка корзины пользователя", () ->
                deleteAllUserBooks(loginResponse.getUserId(), loginResponse.getToken()));

        String firstBookIsbn = step("Получение списка книг", () ->
                getAllBooks().getBooks().get(0).getIsbn());

        step("Добавление книги в корзину", () -> {
            AddBookResponse addResponse = addBookToUser(
                    loginResponse.getUserId(),
                    loginResponse.getToken(),
                    firstBookIsbn
            );
            verifyBookAdded(addResponse, firstBookIsbn);
        });

        // API тест
        step("Удаление одной книги через API", () -> {
            DeleteOneBookRequest deleteRequest = new DeleteOneBookRequest(
                    firstBookIsbn,
                    loginResponse.getUserId()
            );
            deleteOneBookFromCollection(deleteRequest, loginResponse.getToken());
        });

        step("Проверка что корзина пуста", () ->
                verifyUserBooksEmpty(loginResponse.getUserId(), loginResponse.getToken()));
    }
}