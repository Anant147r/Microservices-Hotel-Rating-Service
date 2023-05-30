package com.lcwd.hotel.HotelService.services;

import com.lcwd.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelServices {
//        create
    Hotel create(Hotel hotel);

//    getAll
    List<Hotel> getAll();
//    get single
    Hotel get(String id);
}
