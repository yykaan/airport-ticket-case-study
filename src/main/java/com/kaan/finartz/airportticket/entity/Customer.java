package com.kaan.finartz.airportticket.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Where(clause = "DELETED = 0")
@Entity(name = "Customer")
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity{

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @NotBlank
    @Column(name = "SURNAME")
    private String surname;

    @OneToMany(mappedBy = "customer")
    private Set<FlightTicket> purchasedTickets;

}
