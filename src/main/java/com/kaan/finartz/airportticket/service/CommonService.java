package com.kaan.finartz.airportticket.service;

import com.kaan.finartz.airportticket.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface CommonService<T extends BaseEntity>{

    T saveAndUpdate(T t);

    Iterable<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    Iterable<T> saveAll(Iterable<T> iterableList);

    Optional<T> findById(Long id);

    boolean existsById(Long id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<Long> idList);

    long count();

    void deleteById(Long id);

    void delete(T t);

    void deleteAll(Iterable<T> list);

    void deleteAll();
}
