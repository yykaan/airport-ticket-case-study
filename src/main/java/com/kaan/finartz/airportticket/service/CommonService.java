package com.kaan.finartz.airportticket.service;

import com.kaan.finartz.airportticket.entity.BaseEntity;
import com.kaan.finartz.airportticket.repository.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class CommonService<T extends BaseEntity, R extends CommonRepository<T>>{

    private final CommonRepository<T> commonRepository;

    public CommonService(R repository) {
        this.commonRepository = repository;
    }

    public T saveAndUpdate(T t){
        return commonRepository.save(t);
    }

    public Iterable<T> findAll(Sort sort){
        return commonRepository.findAll(sort);
    }

    public  Page<T> findAll(Pageable pageable){
        return commonRepository.findAll(pageable);
    }

    public Iterable<T> saveAll(Iterable<T> iterableList){
        return commonRepository.saveAll(iterableList);
    }

    public Optional<T> findById(Long id){
        return commonRepository.findById(id);
    }

    public boolean existsById(Long id){
        return commonRepository.existsById(id);
    }

    public Iterable<T> findAll(){
        return commonRepository.findAll();
    }

    public Iterable<T> findAllById(Iterable<Long> idList){
        return commonRepository.findAllById(idList);
    }

    public long count(){
        return commonRepository.count();
    }

    public void deleteById(Long id){
        commonRepository.deleteById(id);
    }

    public void delete(T t){
        commonRepository.delete(t);
    }

    public void deleteAll(Iterable<T> list){
        commonRepository.deleteAll(list);
    }

    public void deleteAll(){
        commonRepository.deleteAll();
    }

}
