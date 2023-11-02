package com.review.service;

import com.review.config.RestTemplateConfig;
import com.review.entity.Review;
import com.review.payload.DoctorDto;
import com.review.payload.ReviewDto;
import com.review.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RestTemplateConfig restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {

        DoctorDto doctorDto = restTemplate.getRestTemplate().getForObject("http://localhost:8080/api/doctor/" + reviewDto.getDoctorId(), DoctorDto.class);

        DoctorDto patientDto = restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/patients/" + reviewDto.getPatientId(), DoctorDto.class);
        Review review = new Review();
        if(doctorDto.isAvailability() && patientDto.isAvailability()){
            review.setRating(reviewDto.getRating());
            review.setDoctorId(reviewDto.getDoctorId());
            review.setPatientId(reviewDto.getPatientId());
            Review savedReview = reviewRepository.save(review);
            return mapToDto(savedReview);
        }else{
            return null;
        }

    }

    private ReviewDto mapToDto(Review review){
        return modelMapper.map(review, ReviewDto.class);

    }

    private Review mapToEntity(ReviewDto dto){
        return modelMapper.map(dto,Review.class);
    }

}
