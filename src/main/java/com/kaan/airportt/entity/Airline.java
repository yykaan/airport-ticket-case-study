package com.kaan.airportt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "AIRLINE")
public class Airline extends BaseEntity {

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="airline")
    private Set<Flight> flights;
}
