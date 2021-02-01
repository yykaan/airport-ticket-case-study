package com.kaan.airportt.controller;

import com.kaan.airportt.dto.Response;
import com.kaan.airportt.dto.flightTicket.FlightTicketDto;
import com.kaan.airportt.dto.flightTicket.FlightTicketPostPurchaseDto;
import com.kaan.airportt.dto.flightTicket.FlightTicketPurchaseDto;
import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightTicket;
import com.kaan.airportt.mapper.FlightMapper;
import com.kaan.airportt.mapper.FlightTicketMapper;
import com.kaan.airportt.service.flight.FlightService;
import com.kaan.airportt.service.flightTicket.FlightTicketService;
import com.kaan.airportt.util.CreditCardMaskUtil;
import com.kaan.airportt.util.TicketPriceCalculator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flightTicket")
@Validated
@Api(value = "Flight Ticket API documentation")
public class FlightTicketController extends AbstractController {

    private final FlightTicketService flightTicketService;
    private final FlightTicketMapper flightTicketMapper;

    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @ApiOperation(value = "Lists all available Flight Tickets for given Flight ID")
    @GetMapping("/list/available/byFlightId/{flightId}")
    public Response<List<FlightTicketDto>> listAvailableTickets(
            @PathVariable Long flightId) {
        return getAllTickets(flightId, true);
    }

    @GetMapping("/list/all/byFlightId/{flightId}")
    @ApiOperation(value = "Lists all unsold and sold Flight Tickets combined for given Flight ID")
    public Response<List<FlightTicketDto>> listAllTickets(
            @PathVariable Long flightId) {
        return getAllTickets(flightId, false);
    }

    @Transactional
    @PostMapping("/purchase")
    @ApiOperation(value = "Purchase Flight Ticket with given Flight Ticket ID and credit card number. " +
            "Checks if any Flight associated with Flight Ticket ID." +
            "Checks if Flight Ticket is previously sold or not." +
            "Automatically calculates the Flight Ticket price." +
            "Automatically masks given credit card numbers." +
            "")
    public Response<FlightTicketPostPurchaseDto> purchaseTicket(@RequestBody FlightTicketPurchaseDto flightTicketPurchaseDto) {
        if (flightTicketService.existsById(flightTicketPurchaseDto.getTicketId())) {
            if (flightTicketService.isPurchased(flightTicketPurchaseDto.getTicketId())) {
                log.info(getMessage("flight.ticket.with.id.already.purchased") + flightTicketPurchaseDto.getTicketId());
                return new Response<>(getMessage("flight.ticket.with.id.already.purchased", flightTicketPurchaseDto.getTicketId()), HttpStatus.BAD_REQUEST);
            }
            Optional<FlightTicket> flightTicketOptional = flightTicketService.findById(flightTicketPurchaseDto.getTicketId());
            FlightTicket purchasedTicket = null;
            if (flightTicketOptional.isPresent()) {
                FlightTicket flightTicket = flightTicketOptional.get();
                flightTicket.setPurchased(true);

                Integer purchasedTicketCount = flightTicketService.getPurchasedTicketCount(flightTicket.getFlight());
                Optional<Flight> flightOptional = flightService.findById(flightTicket.getFlight().getId());
                if (flightOptional.isPresent()) {
                    BigDecimal ticketPrice = TicketPriceCalculator.getInstance().calculateTicketPrice(flightOptional.get(), flightTicket, purchasedTicketCount);
                    flightTicket.setPrice(ticketPrice);

                    purchasedTicket = flightTicketService.saveAndUpdate(flightTicket);
                }

            }
            if (purchasedTicket != null) {
                FlightTicketPostPurchaseDto flightTicketPostPurchaseDto = new FlightTicketPostPurchaseDto();

                purchasedTicket.getFlight().setFlightTickets(null);
                flightTicketPostPurchaseDto.setFlight(flightMapper.toDto(purchasedTicket.getFlight()));
                flightTicketPostPurchaseDto.setTicketNo(purchasedTicket.getTicketNo());
                flightTicketPostPurchaseDto.setPrice(purchasedTicket.getPrice());
                flightTicketPostPurchaseDto.setCreditCardNumber(CreditCardMaskUtil.maskCreditCardNumber(flightTicketPurchaseDto.getCreditCardNumber()));

                flightTicketPostPurchaseDto.getFlight().getFlightRoute().setFlight(null);
                flightTicketPostPurchaseDto.getFlight().getAirline().setFlights(null);
                log.info(getMessage("flight.ticket.purchased") + flightTicketPostPurchaseDto.toString());
                return new Response<>(flightTicketPostPurchaseDto, HttpStatus.OK, getMessage("flight.ticket.purchased"));
            }

        }
        log.info(getMessage("flight.ticket.with.id.could.not.be.found",flightTicketPurchaseDto.getTicketId()));
        return new Response<>(getMessage("flight.ticket.with.id.could.not.be.found", flightTicketPurchaseDto.getTicketId()),HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PutMapping("/cancelTicket/{flightTicketId}")
    @ApiOperation(value = "Cancels previously purchased Flight Ticket with given ID." +
            "Checks if given Flight Ticket is valid." +
            "If cancellation is successful, price will be recalculated automatically.")
    public Response<Void> cancelTicket(@PathVariable Long flightTicketId) {
        if (flightTicketService.existsById(flightTicketId)) {
            Optional<FlightTicket> flightTicketOptional = flightTicketService.findById(flightTicketId);
            if (flightTicketOptional.isPresent()) {
                FlightTicket flightTicket = flightTicketOptional.get();
                if (flightTicket.isPurchased()) {

                    flightTicket.setPurchased(false);
                    Optional<Flight> flightOptional = flightService.findById(flightTicket.getFlight().getId());
                    if (flightOptional.isPresent()){
                        Flight flight = flightOptional.get();
                        flightTicket.setPrice(flight.getBasePrice());
                        flightTicketService.saveAndUpdate(flightTicket);

                        if (flightTicketService.getPurchasedTicketCount(flightOptional.get()) == 0) {
                            TicketPriceCalculator.setLastSoldTicketPrice(flightOptional.get().getBasePrice());
                        } else {
                            TicketPriceCalculator.setLastSoldTicketPrice(flightTicketService.getLastPurchasedTicketPrice());
                        }
                        flightTicket.getFlight().getAirline().setFlights(null);
                        flightTicket.getFlight().getFlightRoute().setFlight(null);
                        flight.setFlightTickets(null);
                        log.info(getMessage("flight.ticket.with.id.cancelled", flightTicketId) + flightTicket.toString());
                        return new Response<>(getMessage("flight.ticket.with.id.cancelled", flightTicketId) ,HttpStatus.OK);
                    }
                }
            }
        }
        log.info(getMessage("flight.ticket.with.id.could.not.be.found",flightTicketId));
        return new Response<>(getMessage("flight.ticket.with.id.could.not.be.found", flightTicketId),HttpStatus.NOT_FOUND);
    }


    private Response<List<FlightTicketDto>> getAllTickets(Long flightId, Boolean isNotPurchased) {
        Optional<Flight> optionalFlight = flightService.findById(flightId);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            List<FlightTicket> flightTickets = flightTicketService.findByFlight(flight);

            if (!flightTickets.isEmpty()) {
                if (isNotPurchased) {
                    flightTickets = flightTickets.stream().filter(flightTicket -> !flightTicket.isPurchased()).collect(Collectors.toList());
                }

                if (!flightTickets.isEmpty()) {
                    return new Response<>(flightTickets.stream()
                            .map(flightTicketMapper::toDto)
                            .collect(Collectors.toList()), HttpStatus.OK);
                } else {
                    log.info(getMessage("flight.ticket.list.empty"));
                    return new Response<>(getMessage("flight.ticket.list.empty"),HttpStatus.NO_CONTENT);
                }
            }
        }
        log.info(getMessage("flight.ticket.with.flight.id.could.not.be.found",flightId));
        return new Response<>(getMessage("flight.ticket.with.flight.id.could.not.be.found", flightId) ,HttpStatus.NOT_FOUND);
    }
}
