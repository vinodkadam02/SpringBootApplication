package com.elixr.poc.bulkimport.service;

import com.elixr.poc.bulkimport.dto.Doctor;
import com.elixr.poc.bulkimport.repository.DoctorRepository;
import com.elixr.poc.bulkimport.rest.data.DoctorData;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void createNewDoctor(DoctorData doctorData) {
        Doctor doctor = Doctor.builder().id(doctorData.getId()).doctorName(doctorData.getDoctorName()).patients(doctorData.getPatients()).build();
        doctorRepository.save(doctor);
    }
}
