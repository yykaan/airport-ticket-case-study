package com.kaan.airportt.controller;

import com.kaan.airportt.dto.flight.FlightDto;
import com.kaan.airportt.dto.flight.FlightSearchByNameDto;
import com.kaan.airportt.dto.flightRoute.FlightRouteDto;
import com.kaan.airportt.entity.Flight;
import com.kaan.airportt.entity.FlightRoute;
import com.kaan.airportt.entity.FlightTicket;
import com.kaan.airportt.mapper.FlightMapper;
import com.kaan.airportt.mapper.FlightRouteMapper;
import com.kaan.airportt.service.flight.FlightService;
import com.kaan.airportt.service.flightRoute.FlightRouteService;
import com.kaan.airportt.exception.ObjectNotFoundException;
import com.kaan.airportt.service.flightTicket.FlightTicketService;
import com.kaan.airportt.util.TicketNoSequenceCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final FlightRouteService flightRouteService;
    private final FlightRouteMapper flightRouteMapper;

    private final FlightTicketService flightTicketService;

    @PostMapping("/save")
    public ResponseEntity<FlightDto> save(@RequestBody @Valid FlightDto flightDto) {
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

        return new ResponseEntity<>(flightMapper.toDto(savedFlight), HttpStatus.CREATED);
    }

    @PostMapping("/addFlightRouteToFlight/{flightRouteId}")
    public ResponseEntity<FlightDto> addFlightRouteToFlight(@PathVariable Long flightRouteId, @RequestBody @Valid FlightDto flightDto){
        if (flightRouteService.existsById(flightRouteId)){
            Optional<FlightRoute> flightDtoOptional = flightRouteService.findById(flightRouteId);
            if (flightDtoOptional.isPresent()){
                FlightRoute flightRouteDto = flightDtoOptional.get();

                flightDto.setFlightRoute(flightRouteDto);
                return new ResponseEntity<>(flightMapper.toDto(flightService.saveAndUpdate(flightMapper.toEntity(flightDto))), HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<FlightDto> update(@RequestBody @Valid FlightDto flightDto, @PathVariable Long id) throws ObjectNotFoundException {
        if (flightService.existsById(id)){
            Flight savedFlight = flightService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Flight could not be found!"));
            if (flightMapper.toEntity(flightDto).equals(savedFlight)){
                return new ResponseEntity<>(flightMapper.toDto(savedFlight), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(flightMapper.toDto(flightService.saveAndUpdate(flightMapper.toEntity(flightDto))), HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<FlightDto>> findAll(){
        List<Flight> flightList = (List<Flight>) flightService.findAll();
        if (flightList.isEmpty()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                flightList.stream()
                        .map(flightMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<FlightDto> findById(@PathVariable Long id){
        if (flightService.existsById(id)){
            return new ResponseEntity<>(flightMapper.toDto(flightService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Flight could not be found!"))), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list/byName")
    public ResponseEntity<List<FlightDto>> findByName(@RequestBody FlightSearchByNameDto flightSearchByNameDto) {
        List<Flight> flightList = flightService.findByName(flightSearchByNameDto.getName());
        if (flightList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(flightList
                    .stream()
                    .map(flightMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @GetMapping("/list/byFlightRoute")
    public ResponseEntity<List<FlightDto>> findByName(@RequestBody FlightRouteDto flightRoute) {
        List<Flight> flightList = flightService.findByFlightRoute(flightRouteMapper.toEntity(flightRoute));
        if (flightList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(flightList
                    .stream()
                    .map(flightMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (flightService.existsById(id)){
            flightService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
