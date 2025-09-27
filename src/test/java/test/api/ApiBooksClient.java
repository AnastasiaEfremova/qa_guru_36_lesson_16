package test.api;

import test.models.books.*;

import static io.restassured.RestAssured.given;
import static test.specs.BaseSpecification.*;

public class ApiBooksClient {

    public static GetBooksResponse getBooks() {
        return given(requestSpecification)
                .basePath("/BookStore/v1/Books")
                .when()
                .get()
                .then()
                .spec(status200ResponseSpec)
                .extract().as(GetBooksResponse.class);
    }

    public static GetUserBooksResponse getUserBooks(String userId, String token) {
        return given(requestSpecification)
                .basePath("/Account/v1/User/" + userId)
                .headers("Authorization", "Bearer " + token)
                .when()
                .get()
                .then()
                .spec(status200ResponseSpec)
                .extract().as(GetUserBooksResponse.class);
    }

    public static AddBookResponse addBookToCollection(AddBooksRequest addBooksRequest, String token) {
        return given(requestSpecification)
                .basePath("/BookStore/v1/Books")
                .headers("Authorization", "Bearer " + token)
                .body(addBooksRequest)
                .when()
                .post()
                .then()
                .spec(status201ResponseSpec)
                .extract().as(AddBookResponse.class);
    }

    public static void deleteAllBooksFromCollection(String userId, String token) {
        given(requestSpecification)
                .basePath("/BookStore/v1/Books")
                .queryParam("UserId", userId)
                .headers("authorization", "Bearer " + token)
                .when()
                .delete()
                .then()
                .spec(status204ResponseSpec);
    }

    public static void deleteOneBookFromCollection(DeleteOneBookRequest deleteOneBookRequest, String token) {
        given(requestSpecification)
                .basePath("/BookStore/v1/Book")
                .headers("authorization", "Bearer " + token)
                .body(deleteOneBookRequest)
                .when()
                .delete()
                .then()
                .spec(status204ResponseSpec);
    }
}
