package com.elixr.poc.bulkimport.service;

import com.elixr.poc.bulkimport.dto.Patient;
import com.elixr.poc.bulkimport.repository.PatientRepository;
import com.elixr.poc.common.enums.MessagesKeyEnum;
import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.common.util.MessagesUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FileOperationService {
    private final PatientRepository patientRepository;

    public FileOperationService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Creating a Patient.
     * @param patient
     */
    public void performAddPatient(Patient patient) {
        if (patient.getPatientId() == null || patient.getPatientId().isEmpty()) {
            patient.setPatientId(String.valueOf(UUID.randomUUID()));
        }
        patientRepository.save(patient);
    }

    /**
     * Updating the patient if existing.
     * @param patientId
     * @param patientDetails
     */
    public void performUpdatePatient(String patientId, Patient patientDetails) {
        patientRepository.findById(patientId).orElseThrow(() -> new NotFoundException(MessagesUtil.getMessage(
                MessagesKeyEnum.ENTITY_PATIENT_DOES_NOT_EXIST.getKey())));
        patientRepository.save(patientDetails);
    }

    /**
     * Deleting the Patient.
     * @param patientId
     */
    public void performDeletePatient(String patientId) {
        patientRepository.findById(patientId).orElseThrow(() -> new NotFoundException(MessagesUtil.getMessage(
                MessagesKeyEnum.ENTITY_PATIENT_DOES_NOT_EXIST.getKey(), "PatientId")));
        patientRepository.deleteById(patientId);
    }
}
