package com.kaan.finartz.airportticket.controller;

import com.kaan.finartz.airportticket.dto.plane.PlaneDto;
import com.kaan.finartz.airportticket.entity.Plane;
import com.kaan.finartz.airportticket.exception.ObjectNotFoundException;
import com.kaan.finartz.airportticket.mapper.PlaneMapper;
import com.kaan.finartz.airportticket.service.plane.PlaneService;
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
@RequestMapping("/api/v1/plane")
@Validated
public class PlaneController extends AbstractController{
    
    private final PlaneService planeService;
    private final PlaneMapper planeMapper;

    @PostMapping("/save")
    public ResponseEntity<PlaneDto> save(@RequestBody @Valid PlaneDto flightDto) {
        Plane savedPlane = planeService.saveAndUpdate(planeMapper.toEntity(flightDto));
        return new ResponseEntity<>(planeMapper.toDto(savedPlane), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PlaneDto> update(@RequestBody @Valid PlaneDto flightDto, @PathVariable Long id) throws ObjectNotFoundException {
        if (planeService.existsById(id)){
            Plane savedPlane = planeService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Plane could not be found!"));
            if (planeMapper.toEntity(flightDto).equals(savedPlane)){
                return new ResponseEntity<>(planeMapper.toDto(savedPlane), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(planeMapper.toDto(planeService.saveAndUpdate(planeMapper.toEntity(flightDto))), HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<PlaneDto>> findAll(){
        List<Plane> flightList = (List<Plane>) planeService.findAll();
        if (flightList.isEmpty()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                flightList.stream()
                        .map(planeMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<PlaneDto> findById(@PathVariable Long id){
        if (planeService.existsById(id)){
            return new ResponseEntity<>(planeMapper.toDto(planeService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Plane could not be found!"))), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (planeService.existsById(id)){
            planeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
