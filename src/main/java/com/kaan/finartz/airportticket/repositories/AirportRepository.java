package com.kaan.finartz.airportticket.repositories;

import com.kaan.finartz.airportticket.entity.Airport;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends PagingAndSortingRepository<Airport, Long> {
}
