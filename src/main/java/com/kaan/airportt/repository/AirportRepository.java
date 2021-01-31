package com.kaan.airportt.repository;

import com.kaan.airportt.entity.Airport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends CommonRepository<Airport> {

    List<Airport> findByName(String name);
}
