package com.lcwd.user.service.controllers;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Builder
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // create
    @PostMapping
    public ResponseEntity<User> createUser( @RequestBody User user){

        User user1 = userService.saveUser(user);

        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    private int retryCount=1;

    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser( @PathVariable String userId){

            System.out.println("Retry Count ="+ retryCount );
            retryCount++;

       User user = userService.getUser(userId);
       return ResponseEntity.ok(user);
    }

    // creating fallback circuit metod for fallback

    public ResponseEntity<User> ratingHotelFallback(String userId ,Exception ex){
        User user = new User(
                "dummy@gmail.com",
                "dummy",
                "this is dummy user",
                "122444"
        );
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
         List<User> allUser =  userService.getAllUser();

         return ResponseEntity.ok(allUser);
    }

}
