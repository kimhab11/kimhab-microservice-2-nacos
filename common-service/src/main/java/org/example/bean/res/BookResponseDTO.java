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
public class BookResponseDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate publishedDate;
    private BigDecimal price;
    private List<AuthorResponseDTO> authorResponses = new ArrayList<>();
    private List<CategoryResponseDTO> categoryResponses = new ArrayList<>();
}
