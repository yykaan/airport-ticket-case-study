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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flight")
@Validated
public class FlightController extends AbstractController{
    
    private final FlightService flightService;
    private final FlightMapper flightMapper;

    private final FlightRouteMapper flightRouteMapper;

    private final FlightTicketService flightTicketService;

    @PostMapping("/save")
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
        }

        flightTicketService.saveAll(flightTicketList);

        return new Response<>(flightMapper.toDto(savedFlight), HttpStatus.CREATED, "Flight created");
    }

    @PostMapping("/update/{id}")
    public Response<FlightDto> update(@RequestBody @Valid FlightDto flightDto, @PathVariable Long id) {
        if (flightService.existsById(id)){
            Optional<Flight> optionalFlight = flightService.findById(id);
            if (optionalFlight.isPresent()){
                Flight savedFlight = optionalFlight.get();
                if (flightMapper.toEntity(flightDto).equals(savedFlight)){
                    return new Response<>(flightMapper.toDto(savedFlight), HttpStatus.OK, "Flight updated");
                }else {
                    return new Response<>(flightMapper.toDto(flightService.saveAndUpdate(flightMapper.toEntity(flightDto))), HttpStatus.OK);
                }
            }else {
                return new Response<>("Flight with ID + " + id + " could not be found", HttpStatus.NO_CONTENT);
            }
        }else {
            return new Response<>("Flight with ID " + id + " could not be found",HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public Response<List<FlightDto>> findAll(){
        List<Flight> flightList = (List<Flight>) flightService.findAll();
        if (flightList.isEmpty()){
            new Response<>(HttpStatus.NO_CONTENT);
        }
        return new Response<>(
                flightList.stream()
                        .map(flightMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public Response<FlightDto> findById(@PathVariable Long id){
        if (flightService.existsById(id)) {
            Optional<Flight> optionalFlight = flightService.findById(id);
            if (optionalFlight.isPresent()){
                return new Response<>(flightMapper.toDto(optionalFlight.get()), HttpStatus.OK);
            }else {
                return new Response<>("Flight with ID " + id + " could not be found!",HttpStatus.NO_CONTENT);
            }
        } else {
            return new Response<>("Flight with ID " + id + " could not be found!",HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list/byName")
    public Response<List<FlightDto>> findByName(@RequestBody FlightSearchByNameDto flightSearchByNameDto) {
        List<Flight> flightList = flightService.findByName(flightSearchByNameDto.getName());
        if (flightList.isEmpty()) {
            return new Response<>("Flight with name " + flightSearchByNameDto.getName() + " could not be found!",HttpStatus.NO_CONTENT);
        } else {
            return new Response<>(flightList
                    .stream()
                    .map(flightMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @GetMapping("/list/byFlightRoute")
    public Response<List<FlightDto>> findByFlightRoute(@RequestBody FlightRouteDto flightRoute) {
        List<Flight> flightList = flightService.findByFlightRoute(flightRouteMapper.toEntity(flightRoute));
        if (flightList.isEmpty()) {
            return new Response<>("Flight with provided flight route ( ID "+flightRoute.getId()+" ) could not be found!",HttpStatus.NO_CONTENT);
        } else {
            return new Response<>(flightList
                    .stream()
                    .map(flightMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Response<Void> deleteById(@PathVariable Long id){
        if (flightService.existsById(id)){
            flightService.deleteById(id);
            return new Response<>("Flight with ID " + id + " deleted",HttpStatus.OK);
        } else {
            return new Response<>("Flight with ID " + id + " could not be found!",HttpStatus.NO_CONTENT);
        }
    }
}
