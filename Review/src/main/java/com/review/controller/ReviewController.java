package com.review.controller;

import com.review.payload.DoctorDto;
import com.review.payload.ReviewDto;
import com.review.service.ReviewService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto){
        ReviewDto dto = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/average/{doctorId}")
    public ResponseEntity<String> averageReviewsWithDoctorId(@PathVariable("doctorId") long doctorId){
        ArrayList<ReviewDto> reviews=reviewService.getAllReviews(doctorId);

        double averageRating = reviews.stream()
                .mapToDouble(ReviewDto::getRating) // Map to the rating values
                .average()                        // Calculate the average
                .orElse(0.0);

        String string = Double.toString(averageRating);

        return ResponseEntity.ok(string);
    }



}
