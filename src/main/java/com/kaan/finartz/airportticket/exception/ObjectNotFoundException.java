package com.kaan.finartz.airportticket.exception;

import javax.persistence.PersistenceException;

public class ObjectNotFoundException extends PersistenceException {
    public ObjectNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
