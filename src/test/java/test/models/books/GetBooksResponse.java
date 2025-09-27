package test.models.books;

import lombok.Data;

import java.util.List;

@Data
public class GetBooksResponse {
    private List<Book> books;
}