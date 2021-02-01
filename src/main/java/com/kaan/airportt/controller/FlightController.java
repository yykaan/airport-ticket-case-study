package com.kaan.airportt.controller;

import com.kaan.airportt.dto.Response;
import com.kaan.airportt.dto.flight.FlightDto;
import com.kaan.airportt.dto.flight.FlightSearchByNameDto;
import com.kaan.airportt.dto.flightRoute.FlightRouteDto;
import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightTicket;
import com.kaan.airportt.mapper.FlightMapper;
import com.kaan.airportt.mapper.FlightRouteMapper;
import com.kaan.airportt.service.flight.FlightService;
import com.kaan.airportt.service.flightTicket.FlightTicketService;
import com.kaan.airportt.util.TicketNoSequenceCreator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flight")
@Validated
@Api(value = "Flight API documentation")
public class FlightController extends AbstractController{
    
    private final FlightService flightService;
    private final FlightMapper flightMapper;

    private final FlightRouteMapper flightRouteMapper;

    private final FlightTicketService flightTicketService;

    @PostMapping("/save")
    @ApiOperation(value = "New Flight adding operation. FlightRoute field must be provided since FlightRoute cannot be created on its own. " +
            "Flight Tickets are created after Flight saved and the number of tickets are provided by saved Flight's passengerCapacity field")
    public Response<FlightDto> save(@RequestBody @Valid FlightDto flightDto) {
        Flight savedFlight = flightService.saveAndUpdate(flightMapper.toEntity(flightDto));
        Integer passengerCapacity = flightDto.getPassengerCapacity();

        List<FlightTicket> flightTicketList = new ArrayList<>();
        for (int i=0;i<passengerCapacity;i++){
            FlightTicket flightTicket = new FlightTicket();
            flightTicket.setFlight(savedFlight);
            flightTicket.setPrice(flightDto.getBasePrice());
            flightTicket.setTicketNo(TicketNoSequenceCreator.getInstance().getTicketNumber(flightDto));
            flightTicketList.add(flightTicket);
            log.info(getMessage("flight.ticket.created") + flightTicket.toString());
        }

        flightTicketService.saveAll(flightTicketList);

        log.info(getMessage("flight.created") + savedFlight.toString());
        return new Response<>(flightMapper.toDto(savedFlight), HttpStatus.CREATED, getMessage("flight.created"));
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "Updating existing Flight with ID field")
    public Response<FlightDto> update(@RequestBody @Valid FlightDto flightDto, @PathVariable Long id) {
        if (flightService.existsById(id)){
            Optional<Flight> optionalFlight = flightService.findById(id);
            if (optionalFlight.isPresent()){
                Flight savedFlight = optionalFlight.get();
                if (flightMapper.toEntity(flightDto).equals(savedFlight)){
                    log.info(getMessage("flight.updated") + savedFlight.toString());
                    return new Response<>(flightMapper.toDto(savedFlight), HttpStatus.OK, getMessage("flight.updated"));
                }else {
                    return new Response<>(flightMapper.toDto(flightService.saveAndUpdate(flightMapper.toEntity(flightDto))), HttpStatus.OK);
                }
            }else {
                log.info(getMessage("flight.with.id.could.not.be.found",id));
                return new Response<>(getMessage("flight.with.id.could.not.be.found",id), HttpStatus.NO_CONTENT);
            }
        }else {
            log.info(getMessage("flight.with.id.could.not.be.found",id));
            return new Response<>(getMessage("flight.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    @ApiOperation(value = "Listing all persisted Flights")
    public Response<List<FlightDto>> findAll(){
        List<Flight> flightList = (List<Flight>) flightService.findAll();
        if (flightList.isEmpty()){
            log.info(getMessage("flight.list.empty"));
            new Response<>(getMessage("flight.list.empty"),HttpStatus.NO_CONTENT);
        }
        return new Response<>(
                flightList.stream()
                        .map(flightMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    @ApiOperation(value = "Search Flight by ID field")
    public Response<FlightDto> findById(@PathVariable Long id){
        if (flightService.existsById(id)) {
            Optional<Flight> optionalFlight = flightService.findById(id);
            if (optionalFlight.isPresent()){
                return new Response<>(flightMapper.toDto(optionalFlight.get()), HttpStatus.OK);
            }else {
                log.info(getMessage("flight.with.id.could.not.be.found",id));
                return new Response<>(getMessage("flight.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(getMessage("flight.with.id.could.not.be.found",id));
            return new Response<>(getMessage("flight.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list/byName")
    @ApiOperation(value = "Search Flight with NAME field with DTO")
    public Response<List<FlightDto>> findByName(@RequestBody FlightSearchByNameDto flightSearchByNameDto) {
        List<Flight> flightList = flightService.findByName(flightSearchByNameDto.getName());
        if (flightList.isEmpty()) {
            log.info(getMessage("flight.with.name.could.not.be.found",flightSearchByNameDto.getName()));
            return new Response<>(getMessage("flight.with.name.could.not.be.found",flightSearchByNameDto.getName()),HttpStatus.NO_CONTENT);
        } else {
            return new Response<>(flightList
                    .stream()
                    .map(flightMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @GetMapping("/list/byFlightRoute")
    @ApiOperation(value = "Search Flight with existing FlightRoute")
    public Response<List<FlightDto>> findByFlightRoute(@RequestBody FlightRouteDto flightRoute) {
        List<Flight> flightList = flightService.findByFlightRoute(flightRouteMapper.toEntity(flightRoute));
        if (flightList.isEmpty()) {
            log.info(getMessage("flight.with.flight.route.could.not.be.found",flightRoute.getId()));
            return new Response<>(getMessage("flight.with.flight.route.could.not.be.found", flightRoute.getId()),HttpStatus.NO_CONTENT);
        } else {
            return new Response<>(flightList
                    .stream()
                    .map(flightMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Airport with ID field. Check if any FlightTickets are sold. If any found then Flight cannot be deleted")
    public Response<?> deleteById(@PathVariable Long id){
        if (flightService.existsById(id)){
            Optional<Flight> optionalFlight = flightService.findById(id);
            if (optionalFlight.isPresent()){
                Flight flight = optionalFlight.get();
                if (flight.getFlightTickets().stream().anyMatch(FlightTicket::isPurchased)){
                    log.info(getMessage("flight.with.id.has.purchased.tickets.cannot.delete",id));
                    return new Response<>(getMessage("flight.with.id.has.purchased.tickets.cannot.delete",id),HttpStatus.OK);
                }else {
                    flightService.deleteById(id);
                    log.info(getMessage("flight.with.id.deleted",id));
                    return new Response<>(getMessage("flight.with.id.deleted",id),HttpStatus.OK);
                }
            }else {
                log.info(getMessage("flight.with.id.could.not.be.found",id));
                return new Response<>(getMessage("flight.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(getMessage("flight.with.id.could.not.be.found",id));
            return new Response<>(getMessage("flight.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
        }
    }
}
