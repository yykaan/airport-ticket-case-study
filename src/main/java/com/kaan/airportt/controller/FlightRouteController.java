package com.kaan.airportt.controller;

import com.kaan.airportt.dto.Response;
import com.kaan.airportt.dto.flightRoute.FlightRouteDto;
import com.kaan.airportt.entity.FlightRoute;
import com.kaan.airportt.mapper.FlightRouteMapper;
import com.kaan.airportt.service.flightRoute.FlightRouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flightRoute")
@Validated
public class FlightRouteController extends AbstractController{

    private final FlightRouteService flightRouteService;
    private final FlightRouteMapper flightRouteMapper;

    /**
     * listeme yaparken FLightRoute.Flight.FlightRoute geliyor.
     * Engelleyemedim bu durumu..
     * */
    @GetMapping("/listAll")
    public Response<List<FlightRouteDto>> findAll(){
        List<FlightRoute> flightRouteList = (List<FlightRoute>) flightRouteService.findAll();
        if (flightRouteList.isEmpty()){
            log.info(getMessage("flight.route.list.empty"));
            new Response<>(getMessage("flight.route.list.empty"),HttpStatus.NO_CONTENT);
        }
        return new Response<>(
                flightRouteList.stream()
                        .map(flightRouteMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public Response<FlightRouteDto> findById(@PathVariable Long id){
        if (flightRouteService.existsById(id)) {
            Optional<FlightRoute> optionalFlightRoute = flightRouteService.findById(id);
            if (optionalFlightRoute.isPresent()){
                return new Response<>(flightRouteMapper.toDto(optionalFlightRoute.get()), HttpStatus.OK);
            }else {
                log.info(getMessage("flight.route.with.id.could.not.be.found",id));
                return new Response<>(getMessage("flight.route.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
            }
        } else {
            log.info(getMessage("flight.route.with.id.could.not.be.found",id));
            return new Response<>(getMessage("flight.route.with.id.could.not.be.found",id),HttpStatus.NO_CONTENT);
        }
    }
}
