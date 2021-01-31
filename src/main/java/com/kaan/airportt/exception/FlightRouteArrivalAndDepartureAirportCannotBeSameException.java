package com.kaan.airportt.exception;

public class FlightRouteArrivalAndDepartureAirportCannotBeSameException extends Exception{
    public FlightRouteArrivalAndDepartureAirportCannotBeSameException(String errorMessage) {
        super(errorMessage);
    }
}
