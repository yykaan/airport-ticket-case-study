package com.kaan.finartz.airportticket.controller;

import com.kaan.finartz.airportticket.dto.AirportDto;
import com.kaan.finartz.airportticket.entity.Airport;
import com.kaan.finartz.airportticket.exception.ObjectNotFoundException;
import com.kaan.finartz.airportticket.exception.UpdateObjectCannotHaveIdException;
import com.kaan.finartz.airportticket.mapper.AirportMapper;
import com.kaan.finartz.airportticket.service.airport.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController extends AbstractController{

    private final AirportService airportService;
    private final AirportMapper airportMapper;

    public AirportController(AirportService airportService, AirportMapper airportMapper) {
        this.airportService = airportService;
        this.airportMapper = airportMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<AirportDto> save(@RequestBody @Valid Airport airport) throws UpdateObjectCannotHaveIdException {
        if (airport.getId() == null){
            throw new UpdateObjectCannotHaveIdException("Airport cannot have ID before save operation");
        }
        Airport savedAirport = airportService.saveAndUpdate(airport);
        return new ResponseEntity<>(airportMapper.toDto(savedAirport), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<AirportDto> update(@RequestBody @Valid Airport airport, @PathVariable Long id) throws UpdateObjectCannotHaveIdException {
        if (airportService.existsById(id)){
            Airport persistedAirport = airportService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Airport could not be found!"));
            if (airport.equals(persistedAirport)){
                return new ResponseEntity<>(airportMapper.toDto(persistedAirport), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(airportMapper.toDto(airportService.saveAndUpdate(airport)), HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<AirportDto>> findAll(){
        List<Airport> airportList = (List<Airport>) airportService.findAll();
        if (airportList.isEmpty()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                airportList.stream()
                        .map(airportMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<AirportDto> findById(@PathVariable Long id){
        if (airportService.existsById(id)){
            return new ResponseEntity<>(airportMapper.toDto(airportService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Airport could not be found!"))), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (airportService.existsById(id)){
            airportService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
