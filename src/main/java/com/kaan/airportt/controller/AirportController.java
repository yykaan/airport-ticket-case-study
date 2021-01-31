package com.kaan.airportt.controller;

import com.kaan.airportt.dto.Response;
import com.kaan.airportt.dto.airport.AirportDto;
import com.kaan.airportt.entity.Airport;
import com.kaan.airportt.mapper.AirportMapper;
import com.kaan.airportt.service.airport.AirportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/airport")
@Validated
public class AirportController extends AbstractController {

    private final AirportService airportService;
    private final AirportMapper airportMapper;

    @PostMapping("/save")
    public Response<AirportDto> save(@RequestBody @Valid AirportDto airport) {
        Airport savedAirport = airportService.saveAndUpdate(airportMapper.toEntity(airport));
        log.info(getMessage("airport.created") + airport.toString());
        return new Response<>(airportMapper.toDto(savedAirport), HttpStatus.CREATED, getMessage("airport.created"));
    }

    @PostMapping("/update/{id}")
    public Response<AirportDto> update(@RequestBody @Valid AirportDto airport, @PathVariable Long id) {
        if (airportService.existsById(id)) {
            Optional<Airport> optionalAirport = airportService.findById(id);
            if (optionalAirport.isPresent()){
                Airport persistedAirport = optionalAirport.get();
                if (airportMapper.toEntity(airport).equals(persistedAirport)) {
                    log.info(getMessage("airport.updated") + airport.toString());
                    return new Response<>(airportMapper.toDto(persistedAirport), HttpStatus.OK, getMessage("airport.updated"));
                } else {
                    return new Response<>(airportMapper.toDto(airportService.saveAndUpdate(airportMapper.toEntity(airport))), HttpStatus.OK);
                }
            }else {
                log.info(getMessage("airport.with.id.could.not.be.found",id));
                return new Response<>(getMessage("airport.with.id.could.not.be.found", id), HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(getMessage("airport.with.id.could.not.be.found",id));
            return new Response<>(getMessage("airport.with.id.could.not.be.found", id), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public Response<List<AirportDto>> findAll() {
        List<Airport> airportList = (List<Airport>) airportService.findAll();
        if (airportList.isEmpty()) {
            log.info(getMessage("airport.list.empty"));
            new Response<>(getMessage("airport.list.empty"), HttpStatus.NO_CONTENT);
        }
        return new Response<>(
                airportList.stream()
                        .map(airportMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public Response<AirportDto> findById(@PathVariable Long id) {
        if (airportService.existsById(id)) {
            Optional<Airport> optionalAirport = airportService.findById(id);
            if (optionalAirport.isPresent()){
                return new Response<>(airportMapper.toDto(optionalAirport.get()), HttpStatus.OK);
            }else {
                log.info(getMessage("airline.with.id.could.not.be.found",id));
                return new Response<>(getMessage("airport.with.id.could.not.be.found", id),HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(getMessage("airline.with.id.could.not.be.found",id));
            return new Response<>(getMessage("airport.with.id.could.not.be.found", id),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list/byName")
    public Response<List<AirportDto>> findByName(@RequestBody AirportDto name) {
        List<Airport> airportList = airportService.findByName(name.getName());
        if (airportList.isEmpty()) {
            log.info(getMessage("airline.with.name.could.not.be.found",name.toString()));
            return new Response<>(getMessage("airport.with.name.could.not.be.found", name.getName()),HttpStatus.NO_CONTENT);
        } else {
            return new Response<>(airportList
                    .stream()
                    .map(airportMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Response<Void> deleteById(@PathVariable Long id) {
        if (airportService.existsById(id)) {
            airportService.deleteById(id);
            log.info(getMessage("airline.wit.id.deleted",id));
            return new Response<>(getMessage("airport.with.id.deleted", id),HttpStatus.OK);
        } else {
            log.info(getMessage("airline.with.id.could.not.be.found",id));
            return new Response<>(getMessage("airport.with.id.could.not.be.found", id),HttpStatus.NO_CONTENT);
        }
    }
}
