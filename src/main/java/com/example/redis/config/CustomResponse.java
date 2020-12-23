package com.example.redis.config;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> {
    private T data;
    private String message;
    private int code;
}
