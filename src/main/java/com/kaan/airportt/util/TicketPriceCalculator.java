package com.kaan.airportt.util;

import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightTicket;

import java.math.BigDecimal;

public class TicketPriceCalculator {
    private static TicketPriceCalculator ticketPriceCalculator = null;

    protected TicketPriceCalculator() {
    }

    private synchronized static void createInstance(){
        if (ticketPriceCalculator == null){
            ticketPriceCalculator = new TicketPriceCalculator();
        }
    }

    public static TicketPriceCalculator getInstance(){
        if (ticketPriceCalculator == null){
            createInstance();
        }
        return ticketPriceCalculator;
    }

    public BigDecimal calculateTicketPrice(Flight flight, FlightTicket flightTicket){
        if (flight.getFlightTickets() != null && !flight.getFlightTickets().isEmpty()){
            BigDecimal baseTicketPrice = flightTicket.getPrice();

            Integer capacity = flight.getPassengerCapacity();

            Integer priceUpCapacity = capacity * (1 / 10);


        }

        return BigDecimal.ZERO;
    }

}
