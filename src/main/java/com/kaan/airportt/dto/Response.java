package com.kaan.airportt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "Base REST Response documentation", description = "Used for REST responses. Contains response model, HttpStatus, error status and message")
public class Response<T> {
    private T responseObject;
    private HttpStatus httpStatus;
    private boolean error = false;
    private String message = "";

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
