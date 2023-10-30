package com.doctor.payload;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {


    private long id;

    private String name;

    private String qualification;

    private int experience;

    private boolean availability;

    private Appointment appointment;

    private Review review;
}
