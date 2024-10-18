package com.example.VehicleLab03.demo.Vservice.impl;

import com.example.VehicleLab03.demo.Vcontroller.dtos.VehicleRequest;
import com.example.VehicleLab03.demo.Vcontroller.dtos.VehicleResponse;
import com.example.VehicleLab03.demo.Vrepository.VRepository;
import com.example.VehicleLab03.demo.Vservice.Vservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VserviceImpl implements Vservice {


    @Autowired
    private VRepository vRepository;

    @Value("${property.name}")
    private String name;

    List<VehicleResponse> vehicleResponseList = poblateVehicleList();




    @Override
    public VehicleResponse getVehicleById(Integer id) throws Exception {
        log.info("Properity value of property.name -> {}" , name);
        vehicleResponseList.forEach(car-> {
            log.info("Car with brand {} and model {} " , car.getBrand() , car.getModel());
        });
        return vehicleResponseList.stream().filter(car -> car.getId().equals(id)).findFirst().get();

    }



    @Override
    public void deleteById(Integer id) throws Exception{
         vehicleResponseList.remove(vehicleResponseList.stream().filter(car -> car.getId().equals(id)).findFirst().get());
            vehicleResponseList.forEach(car-> {
                log.info("Car with brand {} and model {} " , car.getBrand() , car.getModel());
            });
    }

    @Override
    public VehicleResponse updateById(Integer id, VehicleRequest vehicleRequest) throws Exception {
        Optional<VehicleResponse> vehicleResponse = (vehicleResponseList.stream().filter(car -> car.getId().equals(id)).findFirst());
        VehicleResponse requestMapped = this.mapper(vehicleRequest);
        if (vehicleResponse.isPresent()){
            requestMapped.setId(id);
            vehicleResponseList.remove(vehicleResponse.get());
            vehicleResponseList.add(requestMapped);
            vehicleResponseList.forEach(car-> {
                log.info("Car with brand {} and model {} " , car.getBrand() , car.getModel());
            });
        }else{
            throw new Exception();
        }

        return  requestMapped;
    }

    @Override
    public VehicleResponse saveVehicle(VehicleRequest vehicleRequest) {
        VehicleResponse vehicleResponse = this.mapper(vehicleRequest);
        vehicleResponseList.add(vehicleResponse);
        vehicleResponseList.forEach(car-> {
            log.info("Car with brand {} and model {} " , car.getBrand() , car.getModel());
        });
        return vehicleResponse;
    }

    private List<VehicleResponse> poblateVehicleList(){
        List<VehicleResponse> vehicleResponseList = new ArrayList<>();
        VehicleResponse vehicle = new VehicleResponse();
        vehicle.setId(1);
        vehicle.setBrand("Renault");
        vehicle.setModel("Clio");
        vehicle.setMilleage(10000);
        vehicle.setPrice(20000.99);

        vehicleResponseList.add(vehicle);

        vehicle.setId(2);
        vehicle.setBrand("Renault");
        vehicle.setModel("Megan");
        vehicle.setMilleage(10000);
        vehicle.setPrice(20001.99);

        vehicleResponseList.forEach(car-> {
            log.info("Car with brand {} and model {} " , car.getBrand() , car.getModel());
        });

        return vehicleResponseList;

    }

    public List<VehicleResponse> completeVehicleList(){
        List<VehicleResponse> vehicleResponseListComplete = vehicleResponseList;
        vehicleResponseListComplete.forEach(car-> {
            log.info("Car with brand {} and model {} " , car.getBrand() , car.getModel());
        });
        return vehicleResponseListComplete;

    }

    private VehicleResponse mapper(VehicleRequest request){
        VehicleResponse vehicle = new VehicleResponse();
        vehicle.setId(request.getId());
        vehicle.setBrand(request.getBrand());
        vehicle.setMilleage(request.getMilleage());
        vehicle.setPrice(request.getPrice());
        vehicle.setYear(request.getYear());
        vehicle.setDescription(request.getDescription());
        vehicle.setColour(request.getColour());
        vehicle.setFuelType(request.getFuelType());
        vehicle.setNumDoors(request.getNumDoors());
        vehicleResponseList.forEach(car-> {
            log.info("Car with brand {} and model {} " , car.getBrand() , car.getModel());
        });
        return vehicle;
    }
}
