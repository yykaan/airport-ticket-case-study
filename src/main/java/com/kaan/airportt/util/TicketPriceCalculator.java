package com.kaan.airportt.util;

import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightTicket;

import java.math.BigDecimal;

public class TicketPriceCalculator {
    private static TicketPriceCalculator ticketPriceCalculator = null;
    private static volatile BigDecimal lastSoldTicketPrice;

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

    public BigDecimal calculateTicketPrice(Flight flight, FlightTicket flightTicket, Integer purchasedTicketCount){
        if (flight.getFlightTickets() != null && !flight.getFlightTickets().isEmpty()){
            BigDecimal baseTicketPrice = flightTicket.getPrice();

            if (lastSoldTicketPrice == null){
                lastSoldTicketPrice = baseTicketPrice;
            }

            Integer capacity = flight.getPassengerCapacity();

            int priceUpCapacity = capacity / 10;

            if ((purchasedTicketCount) % priceUpCapacity == 0 && purchasedTicketCount > 0){
                lastSoldTicketPrice = lastSoldTicketPrice.divide(BigDecimal.valueOf(10.0)).add(lastSoldTicketPrice);
            }
        }

        return lastSoldTicketPrice;
    }

    public static void setLastSoldTicketPrice(BigDecimal lastSoldTicketPrice) {
        TicketPriceCalculator.lastSoldTicketPrice = lastSoldTicketPrice;
    }
}
