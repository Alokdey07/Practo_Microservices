package com.review.service;


import com.review.payload.ReviewDto;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
}
