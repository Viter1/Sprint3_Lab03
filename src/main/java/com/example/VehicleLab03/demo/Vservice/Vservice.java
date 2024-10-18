package com.example.VehicleLab03.demo.Vservice;

import com.example.VehicleLab03.demo.Vcontroller.dtos.VehicleRequest;
import com.example.VehicleLab03.demo.Vcontroller.dtos.VehicleResponse;

import java.util.List;

public interface Vservice {

     VehicleResponse getVehicleById(Integer id) throws  Exception;

     void deleteById(Integer id) throws Exception;


     VehicleResponse updateById(Integer id , VehicleRequest vehicleRequest) throws Exception;

     VehicleResponse saveVehicle(VehicleRequest vehicleRequest) throws Exception;

      List<VehicleResponse> completeVehicleList();

}
