package com.lcwd.hotel.HotelService.controllers;

import com.lcwd.hotel.HotelService.Impl.HotelServicesImpl;
import com.lcwd.hotel.HotelService.entities.Hotel;
import com.lcwd.hotel.HotelService.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelServices hotelServices;

// create
    @PostMapping
    public ResponseEntity<Hotel>createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelServices.create(hotel));
    }

//get single
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel>createHotel(@PathVariable String  hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelServices.get(hotelId));
    }

    //get single
    @GetMapping
    public ResponseEntity<List<Hotel>>createHotel(){
        return ResponseEntity.ok(hotelServices.getAll());
    }
}
