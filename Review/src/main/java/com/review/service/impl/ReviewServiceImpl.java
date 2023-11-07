package com.review.service.impl;

import com.review.config.RestTemplateConfig;
import com.review.entity.Review;
import com.review.payload.DoctorDto;
import com.review.payload.PatientDto;
import com.review.payload.ReviewDto;
import com.review.repository.ReviewRepository;
import com.review.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RestTemplateConfig restTemplate;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public ReviewDto createReview(ReviewDto reviewDto) {
//        System.out.print("inside create");
//        DoctorDto doctorDto = restTemplate.getRestTemplate().getForObject("http://localhost:8080/api/doctor/" + reviewDto.getDoctorId(), DoctorDto.class);
//        System.out.println(doctorDto.getName());
//        PatientDto patientDto = restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/patients/" + reviewDto.getPatientId(), PatientDto.class);
//        patientDto.getName();
//        Review review = new Review();
//        if(doctorDto!=null && patientDto!=null){
//            review.setRating(reviewDto.getRating());
//            review.setDoctorId(reviewDto.getDoctorId());
//            review.setPatientId(reviewDto.getPatientId());
//            Review savedReview = reviewRepository.save(review);
//            return mapToDto(savedReview);
//        }else{
//            return null;
//        }
//
//    }
public ReviewDto createReview(ReviewDto reviewDto) {
    System.out.print("inside create");

    // Make a GET request to retrieve doctor information
    DoctorDto doctorDto = restTemplate.getRestTemplate().getForObject("http://localhost:8080/api/doctor/" + reviewDto.getDoctorId(), DoctorDto.class);

    if (doctorDto == null) {
        // Handle the case where the doctor information could not be retrieved
        // You can log an error or return an appropriate response here.
        return null;
    }

    System.out.println(doctorDto.getName());

    // Make a GET request to retrieve patient information
    PatientDto patientDto = restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/patients/" + reviewDto.getPatientId(), PatientDto.class);

    if (patientDto == null) {
        // Handle the case where the patient information could not be retrieved
        // You can log an error or return an appropriate response here.
        return null;
    }

    Review review = new Review();
    review.setRating(reviewDto.getRating());
    review.setDoctorId(reviewDto.getDoctorId());
    review.setPatientId(reviewDto.getPatientId());

    Review savedReview = reviewRepository.save(review);

    return mapToDto(savedReview);
}



//    @Override
//    public ReviewDto createReview(ReviewDto reviewDto) {
//         try {
//            // Fetch doctor and patient information
//            ResponseEntity<DoctorDto> doctorResponse = restTemplate.getForEntity(
//                    "http://localhost:8080/api/doctor/" + reviewDto.getDoctorId(),
//                    DoctorDto.class
//            );
//
//            ResponseEntity<PatientDto> patientResponse = restTemplate.getForEntity(
//                    "http://localhost:8081/api/patients/" + reviewDto.getPatientId(),
//                    PatientDto.class
//            );
//
//            if (doctorResponse.getStatusCode() == HttpStatus.OK && patientResponse.getStatusCode() == HttpStatus.OK) {
//                DoctorDto doctorDto = doctorResponse.getBody();
//                PatientDto patientDto = patientResponse.getBody();
//
//                Review review = new Review();
//                review.setRating(reviewDto.getRating());
//                review.setDoctorId(reviewDto.getDoctorId());
//                review.setPatientId(reviewDto.getPatientId());
//
//                // Save the review to your database
//                Review savedReview = reviewRepository.save(review);
//
//                return mapToDto(savedReview);
//            } else {
//                // Handle the case where doctor or patient information could not be retrieved
//                return null;
//            }
//        } catch (RestClientException ex) {
//            // Handle any exceptions related to the REST calls
//            ex.printStackTrace(); // Replace with appropriate error handling
//            return null;
//        }
//    }



    @Override
    public ArrayList<ReviewDto> getAllReviews(long doctorId) {
        ArrayList<ReviewDto> reviewWithDoctorId = reviewRepository.findByDoctorId(doctorId);
        return reviewWithDoctorId;
    }

    private ReviewDto mapToDto(Review review){
        return modelMapper.map(review, ReviewDto.class);

    }

    private Review mapToEntity(ReviewDto dto){
        return modelMapper.map(dto,Review.class);
    }

}
