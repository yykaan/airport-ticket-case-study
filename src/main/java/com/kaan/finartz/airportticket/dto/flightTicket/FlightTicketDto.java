package com.kaan.finartz.airportticket.dto.flightTicket;

import com.kaan.finartz.airportticket.dto.BaseDto;
import com.kaan.finartz.airportticket.dto.customer.CustomerDto;
import com.kaan.finartz.airportticket.dto.flight.FlightDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightTicketDto extends BaseDto {

    @NotBlank
    private String ticketNo;

    @NotNull
    private FlightDto flight;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    private CustomerDto customer;
}
