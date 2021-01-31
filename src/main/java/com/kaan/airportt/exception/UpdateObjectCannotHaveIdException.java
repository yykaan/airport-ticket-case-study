package com.kaan.airportt.exception;

public class UpdateObjectCannotHaveIdException extends Exception{
    public UpdateObjectCannotHaveIdException(String errorMessage) {
        super(errorMessage);
    }
}
