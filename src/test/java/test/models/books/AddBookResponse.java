package test.models.books;

import lombok.Data;

import java.util.List;

@Data
public class AddBookResponse {

    List<Books> books;

    @Data
    public static class Books {
        String isbn;
    }
}
