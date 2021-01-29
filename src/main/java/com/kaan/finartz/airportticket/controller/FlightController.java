package com.kaan.finartz.airportticket.controller;

import com.kaan.finartz.airportticket.dto.flight.FlightDto;
import com.kaan.finartz.airportticket.entity.Flight;
import com.kaan.finartz.airportticket.exception.ObjectNotFoundException;
import com.kaan.finartz.airportticket.mapper.FlightMapper;
import com.kaan.finartz.airportticket.service.flight.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flight")
@Validated
public class FlightController extends AbstractController{
    
    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @PostMapping("/save")
    public ResponseEntity<FlightDto> save(@RequestBody @Valid FlightDto flightDto) {
        Flight savedFlight = flightService.saveAndUpdate(flightMapper.toEntity(flightDto));
        return new ResponseEntity<>(flightMapper.toDto(savedFlight), HttpStatus.CREATED);
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
