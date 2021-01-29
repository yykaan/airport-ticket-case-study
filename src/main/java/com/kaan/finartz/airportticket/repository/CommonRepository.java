package com.kaan.finartz.airportticket.repository;

import com.kaan.finartz.airportticket.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CommonRepository<T extends BaseEntity> extends PagingAndSortingRepository<T, Long> {
}
