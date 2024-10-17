package com.example.VehicleLab03.demo.Vservice;

import com.example.VehicleLab03.demo.Vrepository.Vrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Vservice {

    @Autowired
    private Vrepository vrepository;
}
