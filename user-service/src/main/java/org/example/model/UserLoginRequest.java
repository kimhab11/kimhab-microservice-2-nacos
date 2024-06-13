package org.example.model;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
