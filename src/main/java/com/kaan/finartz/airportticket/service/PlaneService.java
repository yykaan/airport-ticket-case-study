package com.kaan.finartz.airportticket.service;

import com.kaan.finartz.airportticket.entity.Plane;
import com.kaan.finartz.airportticket.repository.PlaneRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaneService extends CommonService<Plane, PlaneRepository>{

    public PlaneService(PlaneRepository repository) {
        super(repository);
    }
}
