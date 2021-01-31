package com.kaan.airportt.controller;

import com.kaan.airportt.dto.flightRoute.FlightRouteDto;
import com.kaan.airportt.entity.FlightRoute;
import com.kaan.airportt.mapper.FlightRouteMapper;
import com.kaan.airportt.service.flightRoute.FlightRouteService;
import com.kaan.airportt.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flightRoute")
@Validated
/**
 * FlightRoute tanımlama sadece Flight oluştururken olmalı. O yüzden save ve update endpointleri kaldırıldı
 * */
public class FlightRouteController extends AbstractController{

    private final FlightRouteService flightRouteService;
    private final FlightRouteMapper flightRouteMapper;

    /**
     * listeme yaparken FLightRoute.Flight.FlightRoute geliyor.
     * Engelleyemedim bu durumu..
     * */
    @GetMapping("/listAll")
    public ResponseEntity<List<FlightRouteDto>> findAll(){
        List<FlightRoute> flightRouteList = (List<FlightRoute>) flightRouteService.findAll();
        if (flightRouteList.isEmpty()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                flightRouteList.stream()
                        .map(flightRouteMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<FlightRouteDto> findById(@PathVariable Long id){
        if (flightRouteService.existsById(id)){
            return new ResponseEntity<>(flightRouteMapper.toDto(flightRouteService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Flight Route could not be found!"))), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
