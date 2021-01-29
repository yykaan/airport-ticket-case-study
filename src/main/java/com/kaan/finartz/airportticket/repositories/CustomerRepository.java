package com.kaan.finartz.airportticket.repositories;

import com.kaan.finartz.airportticket.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
