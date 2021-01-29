package com.kaan.finartz.airportticket.service.airline;

import com.kaan.finartz.airportticket.entity.Airline;
import com.kaan.finartz.airportticket.repository.AirlineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {
    
    private final AirlineRepository repository;

    public AirlineServiceImpl(AirlineRepository repository) {
        this.repository = repository;
    }

    @Override
    public Airline saveAndUpdate(Airline t) {
        return repository.save(t);
    }

    @Override
    public Iterable<Airline> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Airline> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Iterable<Airline> saveAll(Iterable<Airline> iterableList) {
        return repository.saveAll(iterableList);
    }

    @Override
    public Optional<Airline> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<Airline> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Airline> findAllById(Iterable<Long> idList) {
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
    public void delete(Airline t) {
        repository.delete(t);
    }

    @Override
    public void deleteAll(Iterable<Airline> list) {
        repository.deleteAll(list);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
