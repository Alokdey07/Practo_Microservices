package com.patient.service.patientServiceimpl;

import com.patient.entity.Patient;
import com.patient.payload.PatientDto;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PatientDto createPatient(PatientDto dto) {
        return mapper.map(patientRepository.save(mapToEntity(dto)), PatientDto.class);
    }

    @Override
    public List<PatientDto> getAllPatient() {
        List<Patient> all = patientRepository.findAll();
        List<PatientDto> collectdto = all.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
        return collectdto;
    }

    @Override
    public PatientDto getPatient(long patientId) {
        Patient patient = patientRepository.findById(patientId).get();
        return mapToDto(patient);
    }


    private Patient mapToEntity(PatientDto dto){

        return mapper.map(dto, Patient.class);

    }

    private PatientDto mapToDto(Patient patient){
        return mapper.map(patient,PatientDto.class);
    }

}
