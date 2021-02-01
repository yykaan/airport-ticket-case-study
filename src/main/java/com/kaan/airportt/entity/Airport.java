package com.kaan.airportt.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Airport Entity Documentation", description = "Entity")
public class Airport extends BaseEntity {

    @NotBlank
    @Column(name = "NAME")
    @ApiModelProperty(value = "Airport's name field")
    private String name;
}
