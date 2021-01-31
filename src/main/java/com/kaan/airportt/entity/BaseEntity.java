package com.kaan.airportt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "CREATED_DATE", updatable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @JsonIgnore
    @Column(name = "UPDATED_DATE")
    @UpdateTimestamp
    private Timestamp updatedDate;
}
