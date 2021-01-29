package com.kaan.finartz.airportticket.service;

import com.kaan.finartz.airportticket.entity.Customer;
import com.kaan.finartz.airportticket.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends CommonService<Customer, CustomerRepository>{

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }
}
