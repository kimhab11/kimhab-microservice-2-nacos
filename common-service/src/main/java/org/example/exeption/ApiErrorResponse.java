package org.example.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    private int status;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String msg;

    public ApiErrorResponse(int status, String message) {
        this.status = status;
        this.msg = message;
    }
}
