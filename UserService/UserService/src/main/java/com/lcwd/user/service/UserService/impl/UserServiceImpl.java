package com.lcwd.user.service.UserService.impl;

import com.lcwd.user.service.UserService.entities.Rating.Rating;
import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.entities.hotel.Hotel;
import com.lcwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.external.services.HotelService;
import com.lcwd.user.service.UserService.repositories.UserRepository;
import com.lcwd.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
//        generate unique userId
        String randomUUID= UUID.randomUUID().toString();
        user.setUserId(randomUUID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        //        get user from database with the help of user repository
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server!! "+userId ));

        //      fetch ratings given by the above user
        //      http://localhost:8083/ratings/users/fecabb70-bf66-497a-84c1-0bcf819cd91d
        Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
        List<Rating>ratings= Arrays.stream(ratingsOfUser).toList();
        List<Rating>ratingList= ratings.stream().map(rating -> {

            //            api call to hotel service to get the hotel
            //            http://localhost:8082/hotels/fecabb70-bf66-497a-84c1-0bcf819cd91d

//             Using RESTTEMPLATE
//               ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//               Hotel hotel= forEntity.getBody();

//            USING FEIGN CLIENT
            Hotel hotel=hotelService.getHotel(rating.getHotelId());

        //      set the hotel to rating
                rating.setHotel(hotel);

        //      return the rating
                return rating;
        }).collect(Collectors.toList());

        //        logger.info("{} ",ratingsOfUser);
        user.setRatings(ratingList);
        return user;
    }
}
