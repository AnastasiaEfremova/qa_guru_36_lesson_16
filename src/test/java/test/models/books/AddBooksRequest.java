package test.models.books;

import lombok.Data;

import java.util.List;

@Data
public class AddBooksRequest {

    private String userId;
    private List<IsbnItem> collectionOfIsbns;

    @Data
    public static class IsbnItem {
        private String isbn;
    }
}