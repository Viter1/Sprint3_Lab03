package com.example.VehicleLab03.demo.Vcontroller;


import com.example.VehicleLab03.demo.Vcontroller.dtos.VehicleRequest;
import com.example.VehicleLab03.demo.Vcontroller.dtos.VehicleResponse;
import com.example.VehicleLab03.demo.Vservice.Vservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class Vcontroller {

    @Autowired
    private Vservice vservice;


    @GetMapping("vehicle/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Integer id){

        try {
            //VehicleResponse response = (VehicleResponse) vehicleResponseList.stream().filter(v -> v.getId() == id).findFirst().get();
            return ResponseEntity.ok(vservice.getVehicleById(id));
        }catch (NumberFormatException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){

            log.error("Failed to request vehicle with id {}" , id);
            return ResponseEntity.internalServerError().build();
        }

    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<?> deleteVehicleById(@PathVariable Integer id){
        System.out.println("The id to delete is : " + id);
        try {
            //VehicleResponse response = (VehicleResponse) vehicleResponseList.stream().filter(v -> v.getId() == id).findFirst().get();
            vservice.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (NumberFormatException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){

            log.error("Failed to request vehicle with id {}" , id);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicle(@RequestBody VehicleRequest vehicleRequest){
        //System.out.println("Grettings from: " + greetingsRequest.getPersonName() + " with text " + greetingsRequest.getGreeting());
        System.out.println(vehicleRequest.toString());
        try {
            //VehicleResponse response = (VehicleResponse) vehicleResponseList.stream().filter(v -> v.getId() == id).findFirst().get();
            vservice.saveVehicle(vehicleRequest);
            return ResponseEntity.ok().build();
        }catch (NumberFormatException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @DeleteMapping("/vehicle/{id}")
//    public ResponseEntity<?> deleteVehicle (@PathVariable Integer id){
//        System.out.println("The id to delete is : " + id);
//        return ResponseEntity.ok().build();
//    }



    @PutMapping ("/vehicle/{id}")
    public ResponseEntity<?> alterVehicleById(@PathVariable Integer id , @RequestBody VehicleRequest vehicleRequest){
        //System.out.println("Greetings from: " + greetingsRequest.getPersonName() + " with text " + greetingsRequest.getGreeting());
        System.out.println("Vehicle " + vehicleRequest.toString());

        try {
            //VehicleResponse response = (VehicleResponse) vehicleResponseList.stream().filter(v -> v.getId() == id).findFirst().get();
            vservice.updateById(id,vehicleRequest);
            return ResponseEntity.ok().build();
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
            return vservice.completeVehicleList();
    }



}
