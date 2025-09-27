package test.models.books;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteOneBookRequest {

    String isbn;
    String userId;
}
