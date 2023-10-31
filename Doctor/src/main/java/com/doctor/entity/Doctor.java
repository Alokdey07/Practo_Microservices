package com.doctor.entity;

import com.doctor.payload.Appointment;
import com.doctor.payload.Review;

import lombok.*;

import javax.persistence.*;

//import javax.persistence.*;

@Entity
@Table(name = "doctors")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String name;

    private String qualification;

    private int experience;
    @Column(name = "specializations")
    private String specializations;

    private boolean availability;

    //private Appointment appointment;




}
