package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    // get


    // post

    @PostMapping("/ratings")
    public Rating createRating(Rating values);


    // put

    @PostMapping("ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);


    // delte

    @DeleteMapping("ratings/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId);
}
