package com.kaan.finartz.airportticket.service.plane;

import com.kaan.finartz.airportticket.entity.Plane;
import com.kaan.finartz.airportticket.repository.PlaneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository repository;

    public PlaneServiceImpl(PlaneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Plane saveAndUpdate(Plane t) {
        return repository.save(t);
    }

    @Override
    public Iterable<Plane> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Plane> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Iterable<Plane> saveAll(Iterable<Plane> iterableList) {
        return repository.saveAll(iterableList);
    }

    @Override
    public Optional<Plane> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<Plane> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Plane> findAllById(Iterable<Long> idList) {
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
    public void delete(Plane t) {
        repository.delete(t);
    }

    @Override
    public void deleteAll(Iterable<Plane> list) {
        repository.deleteAll(list);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
