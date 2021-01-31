package com.kaan.airportt.repository;

import com.kaan.airportt.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CommonRepository<T extends BaseEntity> extends PagingAndSortingRepository<T, Long> {
}
