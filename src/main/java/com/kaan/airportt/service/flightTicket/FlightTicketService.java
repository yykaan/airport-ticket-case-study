package com.kaan.airportt.service.flightTicket;

import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightTicket;
import com.kaan.airportt.service.CommonService;

import java.math.BigDecimal;
import java.util.List;

public interface FlightTicketService extends CommonService<FlightTicket> {
    List<FlightTicket> findByFlight(Flight flight);

    boolean isPurchased(Long ticketId);

    Integer getPurchasedTicketCount(Flight flight);

    BigDecimal getLastPurchasedTicketPrice();
}
