package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.AuthorEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private String name;

    AuthorResponse(AuthorEntity authorEntity){
        name = authorEntity.getName();
    }
}
