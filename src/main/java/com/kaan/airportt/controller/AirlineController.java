package com.kaan.airportt.controller;

import com.kaan.airportt.dto.airline.AirlineDto;
import com.kaan.airportt.dto.airline.AirlineSaveDto;
import com.kaan.airportt.dto.flight.FlightDto;
import com.kaan.airportt.entity.Airline;
import com.kaan.airportt.exception.ObjectNotFoundException;
import com.kaan.airportt.mapper.AirlineMapper;
import com.kaan.airportt.mapper.FlightMapper;
import com.kaan.airportt.service.airline.AirlineService;
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
@RequestMapping("/api/v1/airline")
@Validated
public class AirlineController extends AbstractController {

    private final AirlineService airlineService;
    private final AirlineMapper airlineMapper;

    private final FlightMapper flightMapper;

    @PostMapping("/save")
    public ResponseEntity<AirlineDto> save(@RequestBody @Valid AirlineSaveDto airport) {
        Airline persistedAirport = airlineService.saveAndUpdate(airlineMapper.saveDtoToEntity(airport));
        return new ResponseEntity<>(airlineMapper.toDto(persistedAirport), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AirlineDto> update(@RequestBody @Valid AirlineDto airlineDto, @PathVariable Long id) throws ObjectNotFoundException {
        if (airlineService.existsById(id)) {
            Airline persistedAirport = airlineService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Airline could not be found!"));
            if (airlineMapper.toEntity(airlineDto).equals(persistedAirport)) {
                return new ResponseEntity<>(airlineMapper.toDto(persistedAirport), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(airlineMapper.toDto(airlineService.saveAndUpdate(airlineMapper.toEntity(airlineDto))), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<AirlineDto>> findAll() {
        List<Airline> airlineList = (List<Airline>) airlineService.findAll();
        if (airlineList.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                airlineList.stream()
                        .map(airlineMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<AirlineDto> findById(@PathVariable Long id) {
        if (airlineService.existsById(id)) {
            return new ResponseEntity<>(airlineMapper.toDto(airlineService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Airline could not be found!"))), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list/byName")
    public ResponseEntity<List<AirlineDto>> findByName(@RequestBody AirlineSaveDto name) {
        List<Airline> airlineList = airlineService.findByName(name.getName());
        if (airlineList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(airlineList
                    .stream()
                    .map(airlineMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @GetMapping("/list/byFlightList")
    public ResponseEntity<List<AirlineDto>> findByName(@RequestBody List<FlightDto> flightList) {
        List<Airline> airlineList = airlineService.findByFlightList(
                flightList.stream()
                .map(flightMapper::toEntity)
                .collect(Collectors.toList()));
        if (airlineList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(airlineList
                    .stream()
                    .map(airlineMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (airlineService.existsById(id)) {
            airlineService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
