package com.kaan.airportt.exception;

import javax.persistence.PersistenceException;

public class ObjectNotFoundException extends PersistenceException {
    public ObjectNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
