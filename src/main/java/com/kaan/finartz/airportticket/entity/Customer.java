package com.kaan.finartz.airportticket.entity;

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
@Entity(name = "Customer")
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity{
    public static final long serialVersionUID = -3925522215522640791L;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @NotBlank
    @Column(name = "SURNAME")
    private String surname;

    @OneToMany(mappedBy = "customer")
    private Set<FlightTicket> purchasedTickets;

}
