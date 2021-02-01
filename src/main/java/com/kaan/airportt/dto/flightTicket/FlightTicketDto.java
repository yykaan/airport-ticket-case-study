package com.kaan.airportt.dto.flightTicket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaan.airportt.dto.BaseDto;
import com.kaan.airportt.dto.flight.FlightDto;
import com.kaan.airportt.entity.Flight;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FlightTicketDTO documentation", description = "DTO")
public class FlightTicketDto extends BaseDto {

    @ApiModelProperty(value = "Flight Tickets' number, automatically created by the system as sequence")
    private Integer ticketNo;

    @JsonIgnore
    @ApiModelProperty(value = "Flight Tickets' Flight")
    private FlightDto flight;

    @ApiModelProperty(value = "Price for each ticket, price is automatically calculated based on Flight's sold ticket number")
    private BigDecimal price;

    @ApiModelProperty(value = "Information about whether the ticket purchased or not")
    private Boolean purchased;
}
