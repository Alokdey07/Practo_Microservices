package com.patient.controller;

import com.patient.payload.PatientDto;
import com.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto dto){
        PatientDto createPatient = patientService.createPatient(dto);
        return new ResponseEntity<>(createPatient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        List<PatientDto> allPatient=patientService.getAllPatient();
        return new ResponseEntity<>(allPatient,HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatientByID(@PathVariable("patientId") long patientId){
       PatientDto patientDto = patientService.getPatient(patientId);
       return new ResponseEntity<>(patientDto,HttpStatus.OK);
    }


}
