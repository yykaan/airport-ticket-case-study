package com.kaan.finartz.airportticket.service.customer;

import com.kaan.finartz.airportticket.entity.Customer;
import com.kaan.finartz.airportticket.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer saveAndUpdate(Customer t) {
        return repository.save(t);
    }

    @Override
    public Iterable<Customer> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Iterable<Customer> saveAll(Iterable<Customer> iterableList) {
        return repository.saveAll(iterableList);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Customer> findAllById(Iterable<Long> idList) {
        return repository.findAllById(idList);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Customer t) {
        repository.delete(t);
    }

    @Override
    public void deleteAll(Iterable<Customer> list) {
        repository.deleteAll(list);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
