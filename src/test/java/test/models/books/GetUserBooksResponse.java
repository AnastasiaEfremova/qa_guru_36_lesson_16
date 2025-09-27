package test.models.books;

import lombok.Data;

import java.util.List;

@Data
public class GetUserBooksResponse {

    private String userId;
    private String username;
    private List<Book> books;
}
