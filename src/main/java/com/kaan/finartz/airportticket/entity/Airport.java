package com.kaan.finartz.airportticket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity(name = "Airport")
@Table(name = "AIRPORT")
public class Airport extends BaseEntity {
    public static final long serialVersionUID = -345835141719651775L;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @JoinTable(name = "AIRLINE_AIRPORT_RELATION",
            joinColumns = @JoinColumn(name = "AIRPORT_ID"),
            inverseJoinColumns = @JoinColumn(name = "AIRLINE_ID"))
    @ManyToMany
    private List<Airline> airlines;
}
