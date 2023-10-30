package com.doctor.service.serviceImpl;

import com.doctor.entity.Doctor;
import com.doctor.exception.ResourceNotFoundException;
import com.doctor.payload.DoctorDto;
import com.doctor.repository.DoctorRepository;
import com.doctor.service.DoctorService;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public DoctorDto addDoctor(DoctorDto dto) {
        Doctor doctor=mapToEntity(dto);

        Doctor save = doctorRepository.save(doctor);

        DoctorDto doctorDto=mapToDto(save);
        return doctorDto;

    }

    @Override
    public List<DoctorDto> viewAllDoctors() {
        List<Doctor> all = doctorRepository.findAll();
        List<DoctorDto> listDto=all.stream().map(x->mapToDto(x)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public DoctorDto findDoctorById(long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Not Found With The Id: " + doctorId));
        return mapToDto(doctor);
    }

    @Override
    public List<DoctorDto> searchDoctor(String keyword) {

        List<Doctor> doctors = doctorRepository.searchDoctor(keyword);
        List<DoctorDto> doctordto = doctors.stream().map(x -> mapToDto(x)).collect(Collectors.toList());

        return doctordto;
    }


    private Doctor mapToEntity(DoctorDto dto){
        Doctor doctor = mapper.map(dto,Doctor.class);
        return doctor;
    }

    private  DoctorDto mapToDto(Doctor doctor){
        DoctorDto doctorDto = mapper.map(doctor,DoctorDto.class);
        return doctorDto;
    }
}
