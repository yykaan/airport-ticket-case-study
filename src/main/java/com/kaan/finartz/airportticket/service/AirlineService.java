package com.kaan.finartz.airportticket.service;

import com.kaan.finartz.airportticket.entity.Airline;
import com.kaan.finartz.airportticket.repository.AirlineRepository;
import org.springframework.stereotype.Service;

@Service
public class AirlineService extends CommonService<Airline, AirlineRepository>{

    public AirlineService(AirlineRepository repository) {
        super(repository);
    }
}
