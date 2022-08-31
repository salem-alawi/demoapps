package com.deraah.demo_api.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    private List<String> message;
    private long timestamp;

    public ApiError(HttpStatus status, String message, long timestamp) {
        super();
        this.status = status;
        this.message = Arrays.asList(message);
        this.timestamp = timestamp;
    }

    public ApiError(HttpStatus status, List<String> messages, long timestamp) {
        super();
        this.status = status;
        this.message = messages;
        this.timestamp = timestamp;
    }
}
