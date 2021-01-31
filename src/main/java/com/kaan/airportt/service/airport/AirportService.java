package com.kaan.airportt.service.airport;

import com.kaan.airportt.entity.Airport;
import com.kaan.airportt.service.CommonService;

import java.util.List;

public interface AirportService extends CommonService<Airport> {

    List<Airport> findByName(String name);
}
