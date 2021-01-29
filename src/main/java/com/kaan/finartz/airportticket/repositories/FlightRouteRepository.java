package com.kaan.finartz.airportticket.repositories;

import com.kaan.finartz.airportticket.entity.FlightRoute;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRouteRepository extends PagingAndSortingRepository<FlightRoute, Long> {
}
