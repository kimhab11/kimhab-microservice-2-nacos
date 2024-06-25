package org.example.dto;

import lombok.Data;
import org.example.entity.CategoryEntity;

@Data
public class CategoryResponse {
    private String name;

    CategoryResponse(CategoryEntity categoryEntity){
        name = categoryEntity.getName();
    }
}
