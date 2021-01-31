package com.kaan.airportt.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@ToString
@Table(name = "AIRPORT")
public class Airport extends BaseEntity {
    @NotBlank
    @Column(name = "NAME")
    private String name;
}
