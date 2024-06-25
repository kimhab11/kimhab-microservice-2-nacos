package org.example.dto;

import lombok.Data;
import org.example.entity.AuthorEntity;

@Data
public class AuthorResponse {
    private String name;

    AuthorResponse(AuthorEntity authorEntity){
        name = authorEntity.getName();
    }
}
