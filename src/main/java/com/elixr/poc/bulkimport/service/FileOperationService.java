package com.elixr.poc.bulkimport.service;

import com.elixr.poc.bulkimport.dto.Doctor;
import com.elixr.poc.bulkimport.dto.Patient;
import com.elixr.poc.bulkimport.repository.DoctorRepository;
import com.elixr.poc.bulkimport.repository.PatientRepository;
import com.elixr.poc.bulkimport.response.RowResponse;
import com.elixr.poc.common.enums.FileOperationEnum;
import com.elixr.poc.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Service Class for performing actions.
 */
@Slf4j
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
    public RowResponse performAddPatient(Patient patient, int row) {
        Doctor doctor = checkDoctorIsPresentInDatabase(patient.getDoctorId(), row);
        if (doctor != null) {
            if (patient.getPatientId() == null || patient.getPatientId().isEmpty()) {
                patient.setPatientId(String.valueOf(UUID.randomUUID()));
            }
            patientRepository.save(patient);
            return successResponseBuilder(FileOperationEnum.SUCCESS.getFileKey(), FileOperationEnum.RECORD_CREATED.getFileKey());
        }
        return errorResponseBuilder(FileOperationEnum.FAILURE.getFileKey(), FileOperationEnum.RECORD_CREATION_FAILED.getFileKey());
    }

    /**
     * Updating the patient if existing.
     *
     * @param patientDetails
     * @return
     */
    public RowResponse performUpdatePatient(Patient patientDetails, int row) {
        Patient patient = getPatientFromRepository(patientDetails.getPatientId());
        if (patient != null) {
            Patient patientObject = validatePatientForBlank(patientDetails, patient);
            patientRepository.save(patientObject);
            return successResponseBuilder(FileOperationEnum.SUCCESS.getFileKey(), FileOperationEnum.RECORD_UPDATED.getFileKey());
        }
        return errorResponseBuilder(FileOperationEnum.FAILURE.getFileKey(), FileOperationEnum.RECORD_UPDATE_FAILED.getFileKey());
    }

    /**
     * Deleting the Patient.
     *
     * @param
     * @return
     */
    public RowResponse performDeletePatient(Patient patient, int row) {
        Patient patientObject = getPatientFromRepository(patient.getPatientId());
        if (patientObject != null) {
            patientRepository.deleteById(patientObject.getPatientId());
            return successResponseBuilder(FileOperationEnum.SUCCESS.getFileKey(), FileOperationEnum.RECORD_DELETED.getFileKey());
        }
        return errorResponseBuilder(FileOperationEnum.FAILURE.getFileKey(), FileOperationEnum.RECORD_DELETE_FAILED.getFileKey());
    }

    /**
     * Assigning new doctor to existing patient.
     *
     * @param patient
     * @return
     */
    public RowResponse performAssignNewDoctor(Patient patient, int row) {
        Patient patientObject = getPatientFromRepository(patient.getPatientId());
        if (patientObject != null) {
            Doctor doctor = checkDoctorIsPresentInDatabase(patient.getDoctorId(), row);
            if (doctor != null) {
                List<String> doctorIds = patientObject.getDoctorId();
                if (doctorIds.contains(doctor.getId())) {
                    return errorResponseBuilder(FileOperationEnum.FAILURE.getFileKey(), FileOperationEnum.DOCTOR_ALREADY_EXIST.getFileKey());
                } else {
                    patientObject.setDoctorId(Collections.singletonList(doctor.getId()));
                }
            }
            patientRepository.save(patientObject);
            return successResponseBuilder(FileOperationEnum.SUCCESS.getFileKey(), FileOperationEnum.ASSIGNED_NEW_DOCTOR.getFileKey());
        }
        return errorResponseBuilder(FileOperationEnum.FAILURE.getFileKey(), FileOperationEnum.ASSIGN_NEW_DOCTOR_FAILED.getFileKey());
    }

    /**
     * Removing a doctor for an Existing patient.
     * But minimum one doctor is required for a patient.
     *
     * @param patient
     * @return
     */
    public RowResponse performRemoveDoctor(Patient patient, int row) {
        Patient patientObject = getPatientFromRepository(patient.getPatientId());
        if (patientObject != null) {
            List<String> doctorList = patientObject.getDoctorId();
            if (doctorList.size() > 1) {
                Doctor doctor = checkDoctorIsPresentInDatabase(patient.getDoctorId(), row);
                if (doctorList.contains(doctor.getId())) {
                    doctorList.remove(String.valueOf(doctor.getId()));
                }
            }
            patientObject.setDoctorId(doctorList);
            patientRepository.save(patientObject);
            return successResponseBuilder(FileOperationEnum.SUCCESS.getFileKey(), FileOperationEnum.REMOVE_DOCTOR.getFileKey());
        }
        return errorResponseBuilder(FileOperationEnum.FAILURE.getFileKey(), FileOperationEnum.REMOVE_DOCTOR_FAILED.getFileKey());
    }

    /**
     * Getting the patient from the DB.
     * And checking if the fields are non-blank for updating the patient.
     *
     * @return
     */
    private Patient getPatientFromRepository(String patientId) throws NotFoundException {
        return patientRepository.getByPatientId(patientId);
    }

    /**
     * Checking if the doctor is present in DB.
     *
     * @param
     * @return
     */
    private Doctor checkDoctorIsPresentInDatabase(List<String> doctorIdList, int row) {
        String doctorId2 = doctorIdList.get(0);
        return doctorRepository.getById(doctorId2);
    }

    /**
     * Checking if the patient fields are non-blank.
     * If fields are blank, we are assigning the previous values to those fields.
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

    /**
     * Success Response with Status
     *
     * @param status
     * @param message
     * @return
     */
    private RowResponse successResponseBuilder(String status, String message) {
        return RowResponse.builder().status(status).successMessage(message).build();
    }

    /**
     * Error Response with Status
     *
     * @param status
     * @param message
     * @return
     */
    private RowResponse errorResponseBuilder(String status, String message) {
        return RowResponse.builder().status(status).errorMessage(message).build();
    }
}
