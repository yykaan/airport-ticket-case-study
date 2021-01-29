package com.kaan.finartz.airportticket.dto.customer;

import com.kaan.finartz.airportticket.dto.BaseDto;
import com.kaan.finartz.airportticket.dto.flightTicket.FlightTicketDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto extends BaseDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private Set<FlightTicketDto> purchasedTickets;

}
