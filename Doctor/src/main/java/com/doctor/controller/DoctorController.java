package com.doctor.controller;

import com.doctor.payload.DoctorDto;
import com.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @PostMapping("/add_doctor")
    public ResponseEntity<DoctorDto> addDostor(@RequestBody DoctorDto dto){

//         UUID uuid =UUID.randomUUID();
//         String id=uuid.toString();
//        UUID uuid = UUID.randomUUID();
//        String id = uuid.toString().substring(0, 6); // Example: Use the first 10 characters


        DoctorDto doctorDto = doctorService.addDoctor(dto);

        return new ResponseEntity<>(doctorDto, HttpStatus.CREATED);


    }

    @GetMapping("/view_doctors")
    public ResponseEntity<List<DoctorDto>> viewDoctor(){
        List<DoctorDto> doctorDtos = doctorService.viewAllDoctors();
        return new ResponseEntity<>(doctorDtos,HttpStatus.OK);
    }

    @GetMapping("/{doctorid}")
    public ResponseEntity<?> findDoctorById(@PathVariable long  doctorId){
        DoctorDto doctorById = doctorService.findDoctorById(doctorId);
        return new ResponseEntity<>(doctorById,HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDoctor(@RequestParam(name="keyword") String keyword)
    {
        List<DoctorDto> doctorDtos = doctorService.searchDoctor(keyword);
        return new ResponseEntity<>(doctorDtos,HttpStatus.OK);

    }





}
