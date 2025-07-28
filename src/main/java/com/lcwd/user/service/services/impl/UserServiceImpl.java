package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {


   @Autowired
   private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {

        // genertate random unique id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
//        List<User> users = userRepository.findAll();
//
//        for (User user : users) {
//            // Call the Rating Service for each user
//            ArrayList<Rating> ratingsOfUser = restTemplate.getForObject(
//                    "http://localhost:8083/ratings/usersId/" + user.getUserId(),
//                    ArrayList.class
//            );
//
//            logger.info("Ratings for user {}: {}", user.getUserId(), ratingsOfUser);
//
//            // Set ratings into the user
//            user.setRatings(ratingsOfUser);
//        }
//
//        return users;

    }

    // get singal user
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !!"+userId));

        // fetch rating of user
        //http://localhost:8083/ratings/usersId/3c9fb1f6-2aac-4140-a33a-fd1fa3a51174

       Rating[]  ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/usersId/"+user.getUserId() ,Rating[].class);

       logger.info("{}" , ratingsOfUser);

      List<Rating> ratings =   Arrays.stream(ratingsOfUser).toList();

     List<Rating>  ratingList =  ratings.stream().map(rating -> {

         // api cal to hotel service
         //http://localhost:8082/hotels/35adc930-9d33-4651-b1ea-012182ec9b1e

        ResponseEntity<Hotel>  forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
        Hotel hotel = forEntity.getBody();
//         Hotel hotel = hotelService.getHotel(rating.getHotelId());
//        logger.info("response entity code {}" ,forEntity.getStatusCode());

        // set  the hotel to rating

         rating.setHotel(hotel);
         return rating;

       }).collect(Collectors.toList());

       user.setRatings(ratingList);
        return  user;
    }
}
