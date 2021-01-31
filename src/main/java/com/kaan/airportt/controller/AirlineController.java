package com.kaan.airportt.controller;

import com.kaan.airportt.dto.Response;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
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
    public Response<AirlineDto> save(@RequestBody @Valid AirlineSaveDto airport) {
        Airline persistedAirport = airlineService.saveAndUpdate(airlineMapper.saveDtoToEntity(airport));
        return new Response<>(airlineMapper.toDto(persistedAirport), HttpStatus.CREATED, "Airline created");
    }

    @PutMapping("/update/{id}")
    public Response<AirlineDto> update(@RequestBody @Valid AirlineDto airlineDto, @PathVariable Long id) throws ObjectNotFoundException {
        if (airlineService.existsById(id)) {
            Optional<Airline> optionalAirline = airlineService.findById(id);
            if (optionalAirline.isPresent()){
                Airline persistedAirline = optionalAirline.get();
                if (airlineMapper.toEntity(airlineDto).equals(persistedAirline)) {
                    return new Response<>(airlineMapper.toDto(persistedAirline), HttpStatus.OK, "Airline updated");
                } else {
                    return new Response<>(airlineMapper.toDto(airlineService.saveAndUpdate(airlineMapper.toEntity(airlineDto))), HttpStatus.OK);
                }
            }else {
                return new Response<>("Airline with ID " + id + " could not be found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new Response<>("Airline with ID " + id + " could not be found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listAll")
    public Response<List<AirlineDto>> findAll() {
        List<Airline> airlineList = (List<Airline>) airlineService.findAll();
        if (airlineList.isEmpty()) {
            new Response<>("Airline list is empty!",HttpStatus.NO_CONTENT);
        }
        List<AirlineDto> airlineDtoList = airlineList.stream()
                .map(airlineMapper::toDto)
                .collect(Collectors.toList());
        return new Response<List<AirlineDto>>(airlineDtoList, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public Response<AirlineDto> findById(@PathVariable Long id) {
        if (airlineService.existsById(id)) {
            Optional<Airline> airlineOptional = airlineService.findById(id);
            if (airlineOptional.isPresent()){
                return new Response<>(airlineMapper.toDto(airlineOptional.get()), HttpStatus.OK);
            }else {
                return new Response<>("Airline with ID " + id + " could not be found!",HttpStatus.NO_CONTENT);
            }
        } else {
            return new Response<>("Airline with ID " + id + " could not be found!",HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list/byName")
    public Response<List<AirlineDto>> findByName(@RequestBody AirlineSaveDto name) {
        List<Airline> airlineList = airlineService.findByName(name.getName());
        if (airlineList.isEmpty()) {
            return new Response<>("Airline with name " + name + " could not be found!",HttpStatus.NO_CONTENT);
        } else {
            return new Response<List<AirlineDto>>(airlineList
                    .stream()
                    .map(airlineMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @GetMapping("/list/byFlightList")
    public Response<List<AirlineDto>> findByName(@RequestBody List<FlightDto> flightList) {
        List<Airline> airlineList = airlineService.findByFlightList(
                flightList.stream()
                .map(flightMapper::toEntity)
                .collect(Collectors.toList()));
        if (airlineList.isEmpty()) {
            return new Response<>("Airline with provided flight list could not be found!" , HttpStatus.NO_CONTENT);
        } else {
            return new Response<List<AirlineDto>>(airlineList
                    .stream()
                    .map(airlineMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Response<?> deleteById(@PathVariable Long id) {
        if (airlineService.existsById(id)) {
            airlineService.deleteById(id);
            return new Response<>("Airline with ID " + id + " deleted",HttpStatus.OK);
        } else {
            return new Response<>("Airline with ID " + id + " could not be found!",HttpStatus.NO_CONTENT);
        }
    }

}
