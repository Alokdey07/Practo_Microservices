package com.patient.service;

import com.patient.payload.PatientDto;

import java.util.List;

public interface PatientService {
    public PatientDto createPatient(PatientDto dto);

    public List<PatientDto> getAllPatient();

    PatientDto getPatient(long patientId);
}

