package com.example.VehicleLab03.demo.Vcontroller.dtos;

import lombok.Data;

@Data
public class VehicleRequest {
    private Integer id ;
    private String brand;
    private String model;
    private Integer milleage;
    private Double price;
    private Integer year;
    private String description;
    private String colour;
    private String fuelType;
    private Integer numDoors;



}
