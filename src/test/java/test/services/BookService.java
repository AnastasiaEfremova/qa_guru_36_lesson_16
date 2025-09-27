package test.services;

import test.models.books.*;
import test.models.books.AddBooksRequest.IsbnItem;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static test.api.ApiBooksClient.*;

public class BookService {

    public static GetBooksResponse getAllBooks() {
        GetBooksResponse response = getBooks();
        assertTrue(response.getBooks().size() > 0, "Books list should not be empty");
        return response;
    }

    public static AddBookResponse addBookToUser(String userId, String token, String isbn) {
        AddBooksRequest request = new AddBooksRequest();
        request.setUserId(userId);

        IsbnItem isbnItem = new IsbnItem();
        isbnItem.setIsbn(isbn);
        request.setCollectionOfIsbns(Collections.singletonList(isbnItem));

        return addBookToCollection(request, token);
    }

    public static void verifyBookAdded(AddBookResponse addResponse, String expectedIsbn) {
        assertEquals(expectedIsbn, addResponse.getBooks().get(0).getIsbn(),
                "Added book ISBN should match");
    }

    public static void deleteAllUserBooks(String userId, String token) {
        deleteAllBooksFromCollection(userId, token);
    }

    public static void verifyUserBooksEmpty(String userId, String token) {
        GetUserBooksResponse userBooks = getUserBooks(userId, token);
        assertEquals(0, userBooks.getBooks().size(),
                "User books list should be empty after deletion");
    }
}