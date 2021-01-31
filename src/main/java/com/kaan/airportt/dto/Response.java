package com.kaan.airportt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Response<T> {
    private T responseObject;
    private HttpStatus httpStatus;
    private boolean error = false;
    private String message = "";

    public Response(T responseObject) {
        this.responseObject = responseObject;
    }

    public Response(T responseObject, HttpStatus httpStatus) {
        this.responseObject = responseObject;
        this.httpStatus = httpStatus;
    }

    public Response(T responseObject, HttpStatus httpStatus, String message) {
        this.responseObject = responseObject;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public Response(String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
