package com.kaan.airportt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "AIRLINE")
@ApiModel(value = "Airline Entity Documentation", description = "Entity")
public class Airline extends BaseEntity {

    @NotBlank
    @Column(name = "NAME")
    @ApiModelProperty(value = "Airline's name field")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="airline")
    @ApiModelProperty(value = "Airline's FLight list")
    private Set<Flight> flights;
}
