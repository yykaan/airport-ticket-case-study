package com.kaan.airportt.util;

import com.kaan.airportt.dto.flight.FlightDto;

import java.util.HashMap;

public class TicketNoSequenceCreator {
    private static TicketNoSequenceCreator ticketNoSequenceCreator = null;
    private final HashMap<FlightDto, Integer> ticketNoMap = new HashMap<>();


    protected TicketNoSequenceCreator() {
    }

    private synchronized static void createInstance(){
        if (ticketNoSequenceCreator == null){
            ticketNoSequenceCreator = new TicketNoSequenceCreator();
        }
    }

    public static TicketNoSequenceCreator getInstance(){
        if (ticketNoSequenceCreator == null){
            createInstance();
        }
        return ticketNoSequenceCreator;
    }

    public Integer getTicketNumber(FlightDto flightDto){
        if (ticketNoMap.containsKey(flightDto)){
            ticketNoMap.put(flightDto,ticketNoMap.get(flightDto) + 1);
        }else {
            ticketNoMap.put(flightDto, 1);
        }

        return ticketNoMap.get(flightDto);
    }
}
