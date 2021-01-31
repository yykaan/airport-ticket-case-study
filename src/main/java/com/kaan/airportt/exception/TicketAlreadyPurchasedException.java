package com.kaan.airportt.exception;

import javax.persistence.PersistenceException;

public class TicketAlreadyPurchasedException extends PersistenceException {
    public TicketAlreadyPurchasedException(String errorMessage) {
        super(errorMessage);
    }
}
