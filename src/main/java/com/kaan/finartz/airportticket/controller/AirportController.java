package com.kaan.finartz.airportticket.controller;

import com.kaan.finartz.airportticket.dto.AirportDto;
import com.kaan.finartz.airportticket.entity.Airport;
import com.kaan.finartz.airportticket.mapper.AirportMapper;
import com.kaan.finartz.airportticket.service.airport.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<AirportDto> saveAirport(@RequestBody @Valid Airport airport){
        Airport savedAirport = airportService.saveAndUpdate(airport);
        return new ResponseEntity<>(airportMapper.toDto(savedAirport), HttpStatus.CREATED);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Airport>> listAirports(){
        return new ResponseEntity<>((List<Airport>) airportService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAirportById(@PathVariable Long id){
        airportService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
