package com.lcwd.user.service.UserService;

import com.lcwd.user.service.UserService.entities.Rating.Rating;
import com.lcwd.user.service.UserService.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@Service
@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating(){
//		Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("This is created using feign client").build();
//		Rating savedRating= ratingService.createRating(rating);
//		System.out.println("New rating created");
//	}

}
