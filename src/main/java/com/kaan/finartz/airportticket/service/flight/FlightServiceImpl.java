package com.kaan.finartz.airportticket.service.flight;

import com.kaan.finartz.airportticket.entity.Flight;
import com.kaan.finartz.airportticket.repository.FlightRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository repository;

    public FlightServiceImpl(FlightRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flight saveAndUpdate(Flight t) {
        return repository.save(t);
    }

    @Override
    public Iterable<Flight> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Flight> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Iterable<Flight> saveAll(Iterable<Flight> iterableList) {
        return repository.saveAll(iterableList);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<Flight> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Flight> findAllById(Iterable<Long> idList) {
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
    public void delete(Flight t) {
        repository.delete(t);
    }

    @Override
    public void deleteAll(Iterable<Flight> list) {
        repository.deleteAll(list);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
