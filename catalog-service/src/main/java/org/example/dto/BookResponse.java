package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.BookEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate publishedDate;
    private BigDecimal price;

    private List<AuthorResponse> authorResponses = new ArrayList<>();
    private List<CategoryResponse> categoryResponses = new ArrayList<>();


    public BookResponse(BookEntity bookEntity){
        id = bookEntity.getId();
        title = bookEntity.getTitle();
        description = bookEntity.getDescription();
        publishedDate = bookEntity.getPublishedDate();
        price = bookEntity.getPrice();

        if (Objects.nonNull(bookEntity.getAuthors())) {
            bookEntity.getAuthors().forEach(authorEntity -> authorResponses.add(new AuthorResponse(authorEntity)));
        }

        if (Objects.nonNull(bookEntity.getCategories())){
            bookEntity.getCategories().forEach(categoryEntity -> categoryResponses.add(new CategoryResponse(categoryEntity)));
        }
    }
}
