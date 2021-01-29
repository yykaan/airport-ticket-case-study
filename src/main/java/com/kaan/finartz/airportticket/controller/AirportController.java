package com.kaan.finartz.airportticket.controller;

import com.kaan.finartz.airportticket.dto.airport.AirportDto;
import com.kaan.finartz.airportticket.dto.airport.AirportSaveDto;
import com.kaan.finartz.airportticket.entity.Airline;
import com.kaan.finartz.airportticket.entity.Airport;
import com.kaan.finartz.airportticket.exception.ObjectNotFoundException;
import com.kaan.finartz.airportticket.mapper.AirlineMapper;
import com.kaan.finartz.airportticket.mapper.AirportMapper;
import com.kaan.finartz.airportticket.service.airline.AirlineService;
import com.kaan.finartz.airportticket.service.airport.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/airport")
@Validated
public class AirportController extends AbstractController{

    private final AirportService airportService;
    private final AirportMapper airportMapper;

    private final AirlineService airlineService;
    private final AirlineMapper airlineMapper;

    @PostMapping("/save")
    public ResponseEntity<AirportDto> save(@RequestBody @Valid AirportSaveDto airport) {
        Airport savedAirport = airportService.saveAndUpdate(airportMapper.saveDtoToEntity(airport));
        return new ResponseEntity<>(airportMapper.toDto(savedAirport), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<AirportDto> update(@RequestBody @Valid AirportDto airport, @PathVariable Long id) throws ObjectNotFoundException {
        if (airportService.existsById(id)){
            Airport persistedAirport = airportService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Airport could not be found!"));
            if (airportMapper.toEntity(airport).equals(persistedAirport)){
                return new ResponseEntity<>(airportMapper.toDto(persistedAirport), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(airportMapper.toDto(airportService.saveAndUpdate(airportMapper.toEntity(airport))), HttpStatus.OK);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (airportService.existsById(id)){
            airportService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/addAirlineToAirport/{airportId}/{airlineId}")
    public ResponseEntity<AirportDto> addAirline(@PathVariable Long airportId, @PathVariable Long airlineId){
        if (!airportService.existsById(airportId)){
            throw new ObjectNotFoundException("Airport could not be found");
        }

        if (!airlineService.existsById(airlineId)){
            throw new ObjectNotFoundException("Airline could not be found");
        }

        Optional<Airport> optionalAirport = airportService.findById(airportId);
        Optional<Airline> optionalAirline = airlineService.findById(airlineId);

        if (optionalAirport.isPresent() && optionalAirline.isPresent()){
            Airport airport = optionalAirport.get();


            Airline airline = optionalAirline.get();
            if (!airline.getAirports().contains(airport)){
                airline.getAirports().add(airport);
                airline = airlineService.saveAndUpdate(airline);
            }

            if(!airport.getAirlines().contains(airline)){
                airport.getAirlines().add(airline);
            }

            return new ResponseEntity<>(airportMapper.toDto(airportService.saveAndUpdate(airport)), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
