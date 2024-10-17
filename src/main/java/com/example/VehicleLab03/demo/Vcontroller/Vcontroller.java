package com.example.VehicleLab03.demo.Vcontroller;


import com.example.VehicleLab03.demo.Vcontroller.dtos.VehicleRequest;
import com.example.VehicleLab03.demo.Vcontroller.dtos.VehicleResponse;
import com.example.VehicleLab03.demo.Vservice.Vservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class Vcontroller {

    @Autowired
    private Vservice vservice;

    List<VehicleResponse> vehicleResponseList = new ArrayList<>();

    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicle(@RequestBody VehicleResponse vehicleResponse){
        //System.out.println("Grettings from: " + greetingsRequest.getPersonName() + " with text " + greetingsRequest.getGreeting());
        System.out.println(vehicleResponse.toString());
        vehicleResponseList.add(vehicleResponse);
        return ResponseEntity.ok().build();
    }

//    @DeleteMapping("/vehicle/{id}")
//    public ResponseEntity<?> deleteVehicle (@PathVariable Integer id){
//        System.out.println("The id to delete is : " + id);
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<?>  deleteVehicle(@PathVariable Integer id){
        vehicleResponseList.removeIf(v -> Objects.equals(v.getId(), id));
        System.out.println("The id to delete is : " + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping ("/vehicle/{id}")
    public ResponseEntity<?> alterVehicle(@PathVariable Integer id , @RequestBody VehicleResponse vehicleResponse){
        //System.out.println("Greetings from: " + greetingsRequest.getPersonName() + " with text " + greetingsRequest.getGreeting());
        System.out.println("Vehicle " + vehicleResponse.toString());
        try {
            vehicleResponseList.removeIf(v -> Objects.equals(v.getId(), id));
            vehicleResponseList.add(vehicleResponse);
            VehicleResponse response = (VehicleResponse) vehicleResponseList.stream().filter(v -> v.getId() == id).findFirst().get();

            return ResponseEntity.ok(response);
        }catch (NumberFormatException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){

            log.error("Failed to request vehicle with id {}" , id);
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("vehicle/")
    public List<VehicleResponse> getAllVehicle(){


            return vehicleResponseList;
    }

    @GetMapping("vehicle/{id}")
    public ResponseEntity<?> getVehicle(@PathVariable Integer id){

        try {
            VehicleResponse response = (VehicleResponse) vehicleResponseList.stream().filter(v -> v.getId() == id).findFirst().get();
            return ResponseEntity.ok(response);
        }catch (NumberFormatException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){

            log.error("Failed to request vehicle with id {}" , id);
            return ResponseEntity.internalServerError().build();
        }

    }

}
