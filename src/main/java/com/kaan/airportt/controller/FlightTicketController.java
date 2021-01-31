package com.kaan.airportt.controller;

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
import com.kaan.airportt.util.CreditCardMask;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<FlightTicketDto>> listAvailableTickets(
            @PathVariable Long flightId) {
        return getAllTickets(flightId, true);
    }

    @GetMapping("/list/all/byFlightId/{flightId}")
    public ResponseEntity<List<FlightTicketDto>> listAllTickets(
            @PathVariable Long flightId) {
        return getAllTickets(flightId, false);
    }

    @PostMapping("/purchase")
    public ResponseEntity<FlightTicketPostPurchaseDto> purchaseTicket(@RequestBody FlightTicketPurchaseDto flightTicketPurchaseDto) throws TicketAlreadyPurchasedException {
        if (flightTicketService.existsById(flightTicketPurchaseDto.getTicketId())) {
            if (flightTicketService.isPurchased(flightTicketPurchaseDto.getTicketId())) {
                throw new TicketAlreadyPurchasedException("Ticket is already purchased! Try another ticket");
            }
            Optional<FlightTicket> flightTicketOptional = flightTicketService.findById(flightTicketPurchaseDto.getTicketId());
            FlightTicket purchasedTicket = null;
            if (flightTicketOptional.isPresent()) {
                FlightTicket flightTicket = flightTicketOptional.get();
                flightTicket.setPurchased(true);
                purchasedTicket = flightTicketService.saveAndUpdate(flightTicket);
            }
            if (purchasedTicket != null) {
                FlightTicketPostPurchaseDto flightTicketPostPurchaseDto = new FlightTicketPostPurchaseDto();

                purchasedTicket.getFlight().setFlightTickets(null);
                flightTicketPostPurchaseDto.setFlight(flightMapper.toDto(purchasedTicket.getFlight()));
                flightTicketPostPurchaseDto.setTicketNo(purchasedTicket.getTicketNo());
                flightTicketPostPurchaseDto.setPrice(purchasedTicket.getPrice());//fiyat hesaplaması yapılacak
                flightTicketPostPurchaseDto.setCreditCardNumber(CreditCardMask.maskCreditCardNumber(flightTicketPurchaseDto.getCreditCardNumber()));

                return new ResponseEntity<>(flightTicketPostPurchaseDto, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    private ResponseEntity<List<FlightTicketDto>> getAllTickets(Long flightId, Boolean isNotPurchased) {
        Optional<Flight> optionalFlight = flightService.findById(flightId);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            List<FlightTicket> flightTickets = flightTicketService.findByFlight(flight);

            if (!flightTickets.isEmpty()) {
                if (isNotPurchased) {
                    flightTickets = flightTickets.stream().filter(flightTicket -> !flightTicket.isPurchased()).collect(Collectors.toList());
                }

                if (!flightTickets.isEmpty()) {
                    return new ResponseEntity<>(flightTickets.stream()
                            .map(flightTicketMapper::toDto)
                            .collect(Collectors.toList()), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
