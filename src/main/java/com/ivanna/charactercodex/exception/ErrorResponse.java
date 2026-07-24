package com.ivanna.charactercodex.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private LocalDateTime timestamp;
    private List<String> message;

    public ErrorResponse(List<String> message){
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}
