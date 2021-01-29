package com.kaan.finartz.airportticket.repository;

import com.kaan.finartz.airportticket.entity.Plane;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends CommonRepository<Plane> {
}
