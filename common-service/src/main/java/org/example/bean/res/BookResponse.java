package org.example.bean.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate publishedDate;
    private BigDecimal price;
    private List<AuthorResponse> authorResponses = new ArrayList<>();
    private List<CategoryResponse> categoryResponses = new ArrayList<>();
}
