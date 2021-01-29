package com.kaan.finartz.airportticket.service;

import com.kaan.finartz.airportticket.entity.Airport;
import com.kaan.finartz.airportticket.repository.AirportRepository;
import org.springframework.stereotype.Service;

@Service
public class AirportService extends CommonService<Airport, AirportRepository>{

    public AirportService(AirportRepository repository) {
        super(repository);
    }
}
