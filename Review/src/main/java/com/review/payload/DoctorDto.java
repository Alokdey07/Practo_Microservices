package com.review.payload;

import com.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private long id;

    private String name;

    private String qualification;

    private int experience;

    private boolean availability;



    private Review review;
}
