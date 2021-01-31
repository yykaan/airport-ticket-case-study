package com.kaan.airportt.controller;

import com.kaan.airportt.dto.airport.AirportDto;
import com.kaan.airportt.entity.Airport;
import com.kaan.airportt.exception.ObjectNotFoundException;
import com.kaan.airportt.mapper.AirportMapper;
import com.kaan.airportt.service.airport.AirportService;
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
@RequestMapping("/api/v1/airport")
@Validated
public class AirportController extends AbstractController {

    private final AirportService airportService;
    private final AirportMapper airportMapper;

    @PostMapping("/save")
    public ResponseEntity<AirportDto> save(@RequestBody @Valid AirportDto airport) {
        Airport savedAirport = airportService.saveAndUpdate(airportMapper.toEntity(airport));
        return new ResponseEntity<>(airportMapper.toDto(savedAirport), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<AirportDto> update(@RequestBody @Valid AirportDto airport, @PathVariable Long id) throws ObjectNotFoundException {
        if (airportService.existsById(id)) {
            Airport persistedAirport = airportService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Airport could not be found!"));
            if (airportMapper.toEntity(airport).equals(persistedAirport)) {
                return new ResponseEntity<>(airportMapper.toDto(persistedAirport), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(airportMapper.toDto(airportService.saveAndUpdate(airportMapper.toEntity(airport))), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<AirportDto>> findAll() {
        List<Airport> airportList = (List<Airport>) airportService.findAll();
        if (airportList.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                airportList.stream()
                        .map(airportMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<AirportDto> findById(@PathVariable Long id) {
        if (airportService.existsById(id)) {
            return new ResponseEntity<>(airportMapper.toDto(airportService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Airport could not be found!"))), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list/byName")
    public ResponseEntity<List<AirportDto>> findByName(@RequestBody AirportDto name) {
        List<Airport> airportList = airportService.findByName(name.getName());
        if (airportList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(airportList
                    .stream()
                    .map(airportMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (airportService.existsById(id)) {
            airportService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
