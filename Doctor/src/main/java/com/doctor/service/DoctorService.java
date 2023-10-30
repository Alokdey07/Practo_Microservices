package com.doctor.service;


import com.doctor.payload.DoctorDto;

import java.util.List;

public interface DoctorService {
    public DoctorDto addDoctor(DoctorDto dto);
    public List<DoctorDto> viewAllDoctors();

    public DoctorDto findDoctorById(long doctorId);

    public List<DoctorDto> searchDoctor(String keyword);

}
