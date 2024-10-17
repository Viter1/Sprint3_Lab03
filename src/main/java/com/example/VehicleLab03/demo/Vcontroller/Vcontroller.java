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
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<?> deleteVehicle (@PathVariable Integer id){
        System.out.println("The id to delete is : " + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping ("/vehicle/{id}")
    public ResponseEntity<?> alterVehicle(@PathVariable Integer id , @RequestBody VehicleRequest vehicleRequest){
        //System.out.println("Greetings from: " + greetingsRequest.getPersonName() + " with text " + greetingsRequest.getGreeting());
        System.out.println("Vehicle " + vehicleRequest.toString());
        System.out.println("The id to add is : " + id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("hello/{id}")
//    public ResponseEntity<?> getGreeting(@PathVariable Integer id){
//        GreetingsResponse greetingsResponse = new GreetingsResponse();
//        greetingsResponse.setGreeting("Hello from API");
//        greetingsResponse.setPersonName("SpringBoot");
//        return ResponseEntity.ok(greetingsResponse);
//    }

    @GetMapping("vehicle/{id}")
    public ResponseEntity<?> getGreeting(@PathVariable Integer id){
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setId(1);
        vehicleResponse.setBrand("Toyota");
        vehicleResponse.setModel("Auris");
        vehicleResponse.setMilleage(100000);
        vehicleResponse.setPrice(16000.99);
        vehicleResponse.setYear(2016);

        
        vehicleResponse.setDescription("mu bonito");
        vehicleResponse.setColour("White");
        vehicleResponse.setFuelType("Gasolina");
        vehicleResponse.setNumDoors(4);


        vehicleResponseList.add(vehicleResponse);



        try {
            VehicleResponse response = (VehicleResponse) vehicleResponseList.stream().filter(g -> g.getId() == id).findFirst().get();
            return ResponseEntity.ok(response);
        }catch (NumberFormatException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){

            log.error("Failed to request greeting with id {}" , id);
            return ResponseEntity.internalServerError().build();
        }

    }

}
