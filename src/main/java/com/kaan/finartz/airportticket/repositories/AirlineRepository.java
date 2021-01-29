package com.kaan.finartz.airportticket.repositories;

import com.kaan.finartz.airportticket.entity.Airline;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends PagingAndSortingRepository<Airline, Long> {
}
