package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.CategoryEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String name;

    CategoryResponse(CategoryEntity categoryEntity){
        name = categoryEntity.getName();
    }
}
