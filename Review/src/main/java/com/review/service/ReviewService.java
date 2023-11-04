package com.review.service;


import com.review.payload.ReviewDto;

import java.util.ArrayList;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);

    ArrayList<ReviewDto> getAllReviews(long doctorId);
}
