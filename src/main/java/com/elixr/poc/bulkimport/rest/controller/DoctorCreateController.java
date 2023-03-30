package com.elixr.poc.bulkimport.rest.controller;

import com.elixr.poc.bulkimport.rest.data.DoctorData;
import com.elixr.poc.bulkimport.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorCreateController {
    private final DoctorService doctorService;


    public DoctorCreateController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("doctor")
    public ResponseEntity<DoctorData> createDoctor(@RequestBody @Valid DoctorData doctorData) {
        doctorService.createNewDoctor(doctorData);
        return new ResponseEntity<>(doctorData, HttpStatus.OK);
    }
}
