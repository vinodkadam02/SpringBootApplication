package com.elixr.poc.bulkimport.service;

import com.elixr.poc.bulkimport.dto.Doctor;
import com.elixr.poc.bulkimport.dto.Patient;
import com.elixr.poc.bulkimport.repository.DoctorRepository;
import com.elixr.poc.bulkimport.repository.PatientRepository;
import com.elixr.poc.common.enums.MessagesKeyEnum;
import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.common.util.MessagesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service Class for performing actions.
 */
@Service
public class FileOperationService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public FileOperationService(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * Creating a Patient.
     *
     * @param patient
     */
    public void performAddPatient(Patient patient) {
        checkDoctorIsPresentInDatabase(patient);
        if (patient.getPatientId() == null || patient.getPatientId().isEmpty()) {
            patient.setPatientId(String.valueOf(UUID.randomUUID()));
        }
        patientRepository.save(patient);
    }

    /**
     * Updating the patient if existing.
     *
     * @param patientDetails
     */
    public void performUpdatePatient(Patient patientDetails) {
        patientRepository.save(getPatientFromRepository(patientDetails));
    }

    /**
     * Deleting the Patient.
     *
     * @param
     */
    public void performDeletePatient(Patient patient) {
        Patient patientObject = getPatientFromRepository(patient);
        patientRepository.deleteById(patientObject.getPatientId());
    }

    /**
     * Assigning new doctor to existing patient.
     *
     * @param patient
     */
    public void performAssignNewDoctor(Patient patient) {
        Patient patientObject = getPatientFromRepository(patient);
        checkDoctorIsPresentInDatabase(patient);
        patientObject.setDoctorId(patient.getDoctorId());
        patientRepository.save(patientObject);
    }

    /**
     * Removing a doctor for an Existing patient.
     * But minimum one doctor is required for a patient.
     *
     * @param patient
     */
    public void performRemoveDoctor(Patient patient) {
        Patient patientObject = getPatientFromRepository(patient);
        List<String> doctorList = patientObject.getDoctorId();
        if (doctorList.size() > 1) {
            Doctor doctor = checkDoctorIsPresentInDatabase(patient);
            if (doctorList.contains(doctor.getId())) {
                doctorList.remove(String.valueOf(doctor.getId()));
            }
        }
        patientObject.setDoctorId(doctorList);
        patientRepository.save(patientObject);
    }

    /**
     * Getting the patient from the DB.
     * And checking if the fields are non-blank for updating thepatient.
     *
     * @param patient
     * @return
     */
    private Patient getPatientFromRepository(Patient patient) {
        Patient patientObject = patientRepository.findById(patient.getPatientId()).orElseThrow(() -> new NotFoundException(
                MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXIST.getKey())));
        return validatePatientForBlank(patient, patientObject);
    }

    /**
     * Checking if the doctor is present in DB.
     *
     * @param patient
     * @return
     */
    private Doctor checkDoctorIsPresentInDatabase(Patient patient) {
        List<String> doctorIdList = patient.getDoctorId();
        String doctorId = doctorIdList.get(0);
        return doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException(MessagesUtil.getMessage(
                MessagesKeyEnum.ENTITY_DOES_NOT_EXIST.getKey(), "DoctorId")));
    }

    /**
     *Checking if the patient fields are non-blank.
     *If fields are blank, we are assigning the previous values to those fields.
     *
     * @param patient
     * @param patientObject
     * @return
     */
    private Patient validatePatientForBlank(Patient patient, Patient patientObject) {
        patientObject.setPatientFirstName(StringUtils.isNotBlank(patient.getPatientFirstName()) ?
                patient.getPatientFirstName() : patientObject.getPatientFirstName());
        patientObject.setPatientLastName(StringUtils.isNotBlank(patient.getPatientLastName()) ?
                patient.getPatientLastName() : patientObject.getPatientLastName());
        patientObject.setPatientAge(patient.getPatientAge() != 0 ?
                patient.getPatientAge() : patientObject.getPatientAge());
        patientObject.setPatientAddress(StringUtils.isNotBlank(patient.getPatientAddress()) ?
                patient.getPatientAddress() : patientObject.getPatientAddress());
        patientObject.setDoctorId(patientObject.getDoctorId());
        return patientObject;
    }
}
