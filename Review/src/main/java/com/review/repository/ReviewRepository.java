package com.review.repository;

import com.review.entity.Review;
import com.review.payload.ReviewDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    ArrayList<ReviewDto> findByDoctorId(long doctorId);

}
