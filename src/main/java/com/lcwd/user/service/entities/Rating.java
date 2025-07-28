//package com.lcwd.user.service.entities;
//
//public class Rating {
//    private String ratingId;
//    private String userId;
//
//    private String hotelId;
//    private int ratings;
//
//    private String feedback;
//
//
//    private Hotel hotel;
//
//
//    public Rating(String ratingId, String userId, String hotelId, int ratings, String feedback) {
//        this.ratingId = ratingId;
//        this.userId = userId;
//        this.hotelId = hotelId;
//        this.ratings = ratings;
//        this.feedback = feedback;
//
//    }
//
//    public String getRatingId() {
//        return ratingId;
//    }
//
//    public void setRatingId(String ratingId) {
//        this.ratingId = ratingId;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getHotelId() {
//        return hotelId;
//    }
//
//    public void setHotelId(String hotelId) {
//        this.hotelId = hotelId;
//    }
//
//    public int getRatings() {
//        return ratings;
//    }
//
//    public void setRatings(int ratings) {
//        this.ratings = ratings;
//    }
//
//    public String getFeedback() {
//        return feedback;
//    }
//
//    public void setFeedback(String feedback) {
//        this.feedback = feedback;
//    }
//
//    public void setHotel(Hotel hotel) {
//    }
//}









package com.lcwd.user.service.entities;

public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int ratings;
    private String feedback;
    private Hotel hotel;

    // No-args constructor
    public Rating() {
    }

    // All-args constructor
    public Rating(String ratingId, String userId, String hotelId, int ratings, String feedback, Hotel hotel) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.ratings = ratings;
        this.feedback = feedback;
        this.hotel = hotel;
    }

    // Getters and Setters
    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
