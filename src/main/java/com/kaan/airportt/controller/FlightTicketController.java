package com.kaan.airportt.controller;

import com.kaan.airportt.dto.Response;
import com.kaan.airportt.dto.flightTicket.FlightTicketDto;
import com.kaan.airportt.dto.flightTicket.FlightTicketPostPurchaseDto;
import com.kaan.airportt.dto.flightTicket.FlightTicketPurchaseDto;
import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightTicket;
import com.kaan.airportt.exception.TicketAlreadyPurchasedException;
import com.kaan.airportt.mapper.FlightMapper;
import com.kaan.airportt.mapper.FlightTicketMapper;
import com.kaan.airportt.service.flight.FlightService;
import com.kaan.airportt.service.flightTicket.FlightTicketService;
import com.kaan.airportt.util.CreditCardMaskUtil;
import com.kaan.airportt.util.TicketPriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flightTicket")
@Validated
public class FlightTicketController extends AbstractController {

    private final FlightTicketService flightTicketService;
    private final FlightTicketMapper flightTicketMapper;

    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @GetMapping("/list/available/byFlightId/{flightId}")
    public Response<List<FlightTicketDto>> listAvailableTickets(
            @PathVariable Long flightId) {
        return getAllTickets(flightId, true);
    }

    @GetMapping("/list/all/byFlightId/{flightId}")
    public Response<List<FlightTicketDto>> listAllTickets(
            @PathVariable Long flightId) {
        return getAllTickets(flightId, false);
    }

    @PostMapping("/purchase")
    public Response<FlightTicketPostPurchaseDto> purchaseTicket(@RequestBody FlightTicketPurchaseDto flightTicketPurchaseDto) {
        if (flightTicketService.existsById(flightTicketPurchaseDto.getTicketId())) {
            if (flightTicketService.isPurchased(flightTicketPurchaseDto.getTicketId())) {
                return new Response<>("Flight Ticket with ID " + flightTicketPurchaseDto.getTicketId() + " is already purchased!", HttpStatus.BAD_REQUEST);
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

                return new Response<>(flightTicketPostPurchaseDto, HttpStatus.OK, "Flight Ticket purchased!");
            }

        }
        return new Response<>("Flight Ticket with ID " + flightTicketPurchaseDto.getTicketId() + " could not be found",HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PutMapping("/cancelTicket/{flightTicketId}")
    public Response<Void> cancelTicket(@PathVariable Long flightTicketId) {
        if (flightTicketService.existsById(flightTicketId)) {
            Optional<FlightTicket> flightTicketOptional = flightTicketService.findById(flightTicketId);
            if (flightTicketOptional.isPresent()) {
                FlightTicket flightTicket = flightTicketOptional.get();
                if (flightTicket.isPurchased()) {

                    flightTicket.setPurchased(false);
                    Optional<Flight> flightOptional = flightService.findById(flightTicket.getFlight().getId());
                    flightOptional.ifPresent(flight -> flightTicket.setPrice(flight.getBasePrice()));
                    flightTicketService.saveAndUpdate(flightTicket);

                    if (flightTicketService.getPurchasedTicketCount(flightOptional.get()) == 0) {
                        TicketPriceCalculator.setLastSoldTicketPrice(flightOptional.get().getBasePrice());
                    } else {
                        TicketPriceCalculator.setLastSoldTicketPrice(flightTicketService.getLastPurchasedTicketPrice());
                    }
                    return new Response<>("Flight ticket with ID " + flightTicketId + " cancelled!" ,HttpStatus.OK);
                }
            }
        }
        return new Response<>("Flight ticket with ID " + flightTicketId + " could not be found",HttpStatus.NOT_FOUND);
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
                    return new Response<>("Flight Ticket list is empty",HttpStatus.NO_CONTENT);
                }
            }
        }
        return new Response<>("Flight Ticket with Flight ID " + flightId + " could not be found!" ,HttpStatus.NOT_FOUND);
    }
}
