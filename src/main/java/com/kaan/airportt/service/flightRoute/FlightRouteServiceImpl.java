package com.kaan.airportt.service.flightRoute;

import com.kaan.airportt.entity.FlightRoute;
import com.kaan.airportt.repository.FlightRouteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightRouteServiceImpl implements FlightRouteService {

    private final FlightRouteRepository repository;

    public FlightRouteServiceImpl(FlightRouteRepository repository) {
        this.repository = repository;
    }

    @Override
    public FlightRoute saveAndUpdate(FlightRoute t) {
        return repository.save(t);
    }

    @Override
    public Iterable<FlightRoute> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<FlightRoute> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Iterable<FlightRoute> saveAll(Iterable<FlightRoute> iterableList) {
        return repository.saveAll(iterableList);
    }

    @Override
    public Optional<FlightRoute> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<FlightRoute> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<FlightRoute> findAllById(Iterable<Long> idList) {
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
    public void delete(FlightRoute t) {
        repository.delete(t);
    }

    @Override
    public void deleteAll(Iterable<FlightRoute> list) {
        repository.deleteAll(list);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
