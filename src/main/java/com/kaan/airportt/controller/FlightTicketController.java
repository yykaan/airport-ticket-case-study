package com.kaan.airportt.controller;

import com.kaan.airportt.dto.flightTicket.FlightTicketDto;
import com.kaan.airportt.entity.FlightTicket;
import com.kaan.airportt.mapper.FlightTicketMapper;
import com.kaan.airportt.service.flightTicket.FlightTicketService;
import com.kaan.airportt.exception.ObjectNotFoundException;
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
@RequestMapping("/api/v1/flightTicket")
@Validated
public class FlightTicketController extends AbstractController{
    
    private final FlightTicketService flightTicketService;
    private final FlightTicketMapper flightTicketMapper;

    @PostMapping("/save")
    public ResponseEntity<FlightTicketDto> save(@RequestBody @Valid FlightTicketDto flightTicketDto) {
        FlightTicket savedFlightTicket = flightTicketService.saveAndUpdate(flightTicketMapper.toEntity(flightTicketDto));
        return new ResponseEntity<>(flightTicketMapper.toDto(savedFlightTicket), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<FlightTicketDto> update(@RequestBody @Valid FlightTicketDto flightTicketDto, @PathVariable Long id) throws ObjectNotFoundException {
        if (flightTicketService.existsById(id)){
            FlightTicket savedFlightTicket = flightTicketService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Flight Ticket could not be found!"));
            if (flightTicketMapper.toEntity(flightTicketDto).equals(savedFlightTicket)){
                return new ResponseEntity<>(flightTicketMapper.toDto(savedFlightTicket), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(flightTicketMapper.toDto(flightTicketService.saveAndUpdate(flightTicketMapper.toEntity(flightTicketDto))), HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<FlightTicketDto>> findAll(){
        List<FlightTicket> flightTicketList = (List<FlightTicket>) flightTicketService.findAll();
        if (flightTicketList.isEmpty()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                flightTicketList.stream()
                        .map(flightTicketMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<FlightTicketDto> findById(@PathVariable Long id){
        if (flightTicketService.existsById(id)){
            return new ResponseEntity<>(flightTicketMapper.toDto(flightTicketService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Flight Ticket could not be found!"))), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (flightTicketService.existsById(id)){
            flightTicketService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
