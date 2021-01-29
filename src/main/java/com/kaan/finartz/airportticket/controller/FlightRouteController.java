package com.kaan.finartz.airportticket.controller;

import com.kaan.finartz.airportticket.dto.flightRoute.FlightRouteDto;
import com.kaan.finartz.airportticket.entity.FlightRoute;
import com.kaan.finartz.airportticket.exception.ObjectNotFoundException;
import com.kaan.finartz.airportticket.mapper.FlightRouteMapper;
import com.kaan.finartz.airportticket.service.flightRoute.FlightRouteService;
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
@RequestMapping("/api/v1/flightRoute")
@Validated
public class FlightRouteController extends AbstractController{
    
    private final FlightRouteService flightRouteService;
    private final FlightRouteMapper flightRouteMapper;

    @PostMapping("/save")
    public ResponseEntity<FlightRouteDto> save(@RequestBody @Valid FlightRouteDto flightRouteDto) {
        FlightRoute savedFlightRoute = flightRouteService.saveAndUpdate(flightRouteMapper.toEntity(flightRouteDto));
        return new ResponseEntity<>(flightRouteMapper.toDto(savedFlightRoute), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<FlightRouteDto> update(@RequestBody @Valid FlightRouteDto flightRouteDto, @PathVariable Long id) throws ObjectNotFoundException {
        if (flightRouteService.existsById(id)){
            FlightRoute savedFlightRoute = flightRouteService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Flight Route could not be found!"));
            if (flightRouteMapper.toEntity(flightRouteDto).equals(savedFlightRoute)){
                return new ResponseEntity<>(flightRouteMapper.toDto(savedFlightRoute), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(flightRouteMapper.toDto(flightRouteService.saveAndUpdate(flightRouteMapper.toEntity(flightRouteDto))), HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (flightRouteService.existsById(id)){
            flightRouteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
