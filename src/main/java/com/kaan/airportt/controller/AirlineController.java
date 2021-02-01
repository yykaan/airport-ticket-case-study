package com.kaan.airportt.controller;

import com.kaan.airportt.dto.Response;
import com.kaan.airportt.dto.airline.AirlineDto;
import com.kaan.airportt.dto.airline.AirlineSaveDto;
import com.kaan.airportt.dto.flight.FlightDto;
import com.kaan.airportt.entity.Airline;
import com.kaan.airportt.mapper.AirlineMapper;
import com.kaan.airportt.mapper.FlightMapper;
import com.kaan.airportt.service.airline.AirlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/v1/airline")
@Validated
@Api(value = "Airline API documentation")
public class AirlineController extends AbstractController {

    private final AirlineService airlineService;
    private final AirlineMapper airlineMapper;

    private final FlightMapper flightMapper;

    @PostMapping("/save")
    @ApiOperation(value = "New Airline adding operation")
    public Response<AirlineDto> save(@RequestBody @Valid AirlineSaveDto airport) {
        Airline persistedAirport = airlineService.saveAndUpdate(airlineMapper.saveDtoToEntity(airport));
        log.info(getMessage("airline.created") + persistedAirport.toString());
        return new Response<>(airlineMapper.toDto(persistedAirport), HttpStatus.CREATED, getMessage("airline.created"));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Updating existing Airline with ID field")
    public Response<AirlineDto> update(@RequestBody @Valid AirlineDto airlineDto, @PathVariable Long id) {
        if (airlineService.existsById(id)) {
            Optional<Airline> optionalAirline = airlineService.findById(id);
            if (optionalAirline.isPresent()){
                Airline persistedAirline = optionalAirline.get();
                if (airlineMapper.toEntity(airlineDto).equals(persistedAirline)) {
                    log.info(getMessage("airline.updated" + persistedAirline.toString()));
                    return new Response<>(airlineMapper.toDto(persistedAirline), HttpStatus.OK, getMessage("airline.updated"));
                } else {
                    return new Response<>(airlineMapper.toDto(airlineService.saveAndUpdate(airlineMapper.toEntity(airlineDto))), HttpStatus.OK);
                }
            }else {
                log.info(getMessage("airline.with.id.could.not.be.found",id));
                return new Response<>(getMessage("airline.with.id.could.not.be.found",id), HttpStatus.NOT_FOUND);
            }
        } else {
            log.info(getMessage("airline.with.id.could.not.be.found",id));
            return new Response<>(getMessage("airline.with.id.could.not.be.found",id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listAll")
    @ApiOperation(value = "Listing all persisted Airlines")
    public Response<List<AirlineDto>> findAll() {
        List<Airline> airlineList = (List<Airline>) airlineService.findAll();
        if (airlineList.isEmpty()) {
            log.info(getMessage("airline.list.empty"));
            new Response<>(getMessage("airline.list.empty"),HttpStatus.NO_CONTENT);
        }
        List<AirlineDto> airlineDtoList = airlineList.stream()
                .map(airlineMapper::toDto)
                .collect(Collectors.toList());
        return new Response<>(airlineDtoList, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    @ApiOperation(value = "Search Airline by ID field")
    public Response<AirlineDto> findById(@PathVariable Long id) {
        if (airlineService.existsById(id)) {
            Optional<Airline> airlineOptional = airlineService.findById(id);
            if (airlineOptional.isPresent()){
                return new Response<>(airlineMapper.toDto(airlineOptional.get()), HttpStatus.OK);
            }else {
                log.info(getMessage("airline.with.id.could.not.be.found",id));
                return new Response<>(getMessage("airline.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(getMessage("airline.with.id.could.not.be.found",id));
            return new Response<>(getMessage("airline.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list/byName")
    @ApiOperation(value = "Search airline with NAME field with DTO")
    public Response<List<AirlineDto>> findByName(@RequestBody AirlineSaveDto name) {
        List<Airline> airlineList = airlineService.findByName(name.getName());
        if (airlineList.isEmpty()) {
            log.info(getMessage("airline.with.name.could.not.be.found",name.getName()));
            return new Response<>(getMessage("airline.with.name.could.not.be.found",name.getName()),HttpStatus.NO_CONTENT);
        } else {
            return new Response<>(airlineList
                    .stream()
                    .map(airlineMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @GetMapping("/list/byFlightList")
    @ApiOperation(value = "Search airlines with Flight list")
    public Response<List<AirlineDto>> findAirlineByFlightList(@RequestBody List<FlightDto> flightList) {
        List<Airline> airlineList = airlineService.findByFlightList(
                flightList.stream()
                .map(flightMapper::toEntity)
                .collect(Collectors.toList()));
        if (airlineList.isEmpty()) {
            log.info(getMessage("airline.with.flight.list.not.found"));
            return new Response<>(getMessage("airline.with.flight.list.not.found") , HttpStatus.NO_CONTENT);
        } else {
            return new Response<>(airlineList
                    .stream()
                    .map(airlineMapper::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete airline with ID field")
    public Response<?> deleteById(@PathVariable Long id) {
        if (airlineService.existsById(id)) {
            airlineService.deleteById(id);
            log.info(getMessage("airline.with.id.deleted",id));
            return new Response<>(getMessage("airline.with.id.deleted",id),HttpStatus.OK);
        } else {
            return new Response<>(getMessage("airline.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
        }
    }

}
