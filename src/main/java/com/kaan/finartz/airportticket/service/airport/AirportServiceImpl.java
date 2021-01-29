package com.kaan.finartz.airportticket.service.airport;

import com.kaan.finartz.airportticket.entity.Airport;
import com.kaan.finartz.airportticket.repository.AirportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService{
    
    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Airport saveAndUpdate(Airport t) {
        return airportRepository.save(t);
    }

    @Override
    public Iterable<Airport> findAll(Sort sort) {
        return airportRepository.findAll(sort);
    }

    @Override
    public Page<Airport> findAll(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    @Override
    public Iterable<Airport> saveAll(Iterable<Airport> iterableList) {
        return airportRepository.saveAll(iterableList);
    }

    @Override
    public Optional<Airport> findById(Long id) {
        return airportRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return airportRepository.existsById(id);
    }

    @Override
    public Iterable<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public Iterable<Airport> findAllById(Iterable<Long> idList) {
        return airportRepository.findAllById(idList);
    }

    @Override
    public long count() {
        return airportRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }

    @Override
    public void delete(Airport t) {
        airportRepository.delete(t);
    }

    @Override
    public void deleteAll(Iterable<Airport> list) {
        airportRepository.deleteAll(list);
    }

    @Override
    public void deleteAll() {
        airportRepository.deleteAll();
    }
}
