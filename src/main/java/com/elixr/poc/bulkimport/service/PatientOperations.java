package com.elixr.poc.bulkimport.service;

import com.elixr.poc.bulkimport.dto.Doctor;
import com.elixr.poc.bulkimport.dto.Patient;
import com.elixr.poc.bulkimport.repository.DoctorRepository;
import com.elixr.poc.bulkimport.repository.PatientRepository;
import com.elixr.poc.bulkimport.response.RowResponse;
import com.elixr.poc.common.enums.FileHeadersEnum;
import com.elixr.poc.common.enums.FileMessageEnum;
import com.elixr.poc.common.enums.MessagesKeyEnum;
import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.common.util.MessagesUtil;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class PatientOperations {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public PatientOperations(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * Performing the Action Operation based on the Excel actions.
     *
     * @param patient
     * @param columnHeader
     * @return
     */
    public RowResponse performAction(Patient patient, HashMap<String, String> columnHeader) {
        if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_ADD_OPERATION.getKey()).equalsIgnoreCase(columnHeader
                .get(FileHeadersEnum.ACTION.getKey()))) {
            return performAddPatient(patient);
        }
        if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_UPDATE_OPERATION.getKey()).equalsIgnoreCase(columnHeader
                .get(FileHeadersEnum.ACTION.getKey()))) {
            return performUpdatePatient(patient);
        }
        if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_DELETE_OPERATION.getKey()).equalsIgnoreCase(columnHeader
                .get(FileHeadersEnum.ACTION.getKey()))) {
            return performDeletePatient(patient);
        }
        if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_ASSIGN_NEW_DOCTOR.getKey()).equalsIgnoreCase(columnHeader
                .get(FileHeadersEnum.ACTION.getKey()))) {
            return performAssignNewDoctor(patient);
        }
        if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_REMOVE_DOCTOR.getKey()).equalsIgnoreCase(columnHeader
                .get(FileHeadersEnum.ACTION.getKey()))) {
            return performRemoveDoctor(patient);
        } else {
            return errorResponseBuilder(FileMessageEnum.FAILURE.getFileKey(), FileMessageEnum.NO_MATCHING_ACTION_FOUND.getFileKey());
        }
    }

    /**
     * Creating a Patient.
     *
     * @param patient
     */
    private RowResponse performAddPatient(Patient patient) {
        Doctor doctor = checkDoctorIsPresentInDatabase(patient.getDoctorList());
        if (doctor != null) {
            if (patient.getPatientId() == null || patient.getPatientId().isEmpty()) {
                patient.setPatientId(String.valueOf(UUID.randomUUID()));
            }
            patientRepository.save(patient);
            return successResponseBuilder(FileMessageEnum.SUCCESS.getFileKey(), FileMessageEnum.RECORD_CREATED.getFileKey());
        }
        return errorResponseBuilder(FileMessageEnum.FAILURE.getFileKey(), FileMessageEnum.RECORD_CREATION_FAILED.getFileKey());
    }

    /**
     * Updating the patient if existing.
     *
     * @param patientDetails
     * @return
     */
    private RowResponse performUpdatePatient(Patient patientDetails) {
        Patient patient = getPatientFromRepository(patientDetails.getPatientId());
        if (patient != null) {
            Patient patientObject = validatePatientForBlank(patientDetails, patient);
            patientRepository.save(patientObject);
            return successResponseBuilder(FileMessageEnum.SUCCESS.getFileKey(), FileMessageEnum.RECORD_UPDATED.getFileKey());
        }
        return errorResponseBuilder(FileMessageEnum.FAILURE.getFileKey(), FileMessageEnum.RECORD_UPDATE_FAILED.getFileKey());
    }

    /**
     * Deleting the Patient.
     *
     * @param
     * @return
     */
    private RowResponse performDeletePatient(Patient patient) {
        Patient patientObject = getPatientFromRepository(patient.getPatientId());
        if (patientObject != null) {
            patientRepository.deleteById(patientObject.getPatientId());
            return successResponseBuilder(FileMessageEnum.SUCCESS.getFileKey(), FileMessageEnum.RECORD_DELETED.getFileKey());
        }
        return errorResponseBuilder(FileMessageEnum.FAILURE.getFileKey(), FileMessageEnum.RECORD_DELETE_FAILED.getFileKey());
    }

    /**
     * Assigning new doctor to existing patient.
     *
     * @param patient
     * @return
     */
    private RowResponse performAssignNewDoctor(Patient patient) {
        Patient patientObject = getPatientFromRepository(patient.getPatientId());
        if (patientObject != null) {
            Doctor doctor = checkDoctorIsPresentInDatabase(patient.getDoctorList());
            if (doctor != null) {
                List<String> doctorIds = patientObject.getDoctorList();
                if (doctorIds.contains(doctor.getId())) {
                    return errorResponseBuilder(FileMessageEnum.FAILURE.getFileKey(), FileMessageEnum.DOCTOR_ALREADY_EXIST.getFileKey());
                } else {
                    patientObject.setDoctorList(Collections.singletonList(doctor.getId()));
                    patientRepository.save(patientObject);
                    return successResponseBuilder(FileMessageEnum.SUCCESS.getFileKey(), FileMessageEnum.ASSIGNED_NEW_DOCTOR.getFileKey());
                }
            }
            return errorResponseBuilder(FileMessageEnum.FAILURE.getFileKey(), FileMessageEnum.DOCTOR_NOT_EXIST.getFileKey());
        }
        return errorResponseBuilder(FileMessageEnum.FAILURE.getFileKey(), FileMessageEnum.ASSIGN_NEW_DOCTOR_FAILED.getFileKey());
    }

    /**
     * Removing a doctor for an Existing patient.
     * But minimum one doctor is required for a patient.
     *
     * @param patient
     * @return
     */
    private RowResponse performRemoveDoctor(Patient patient) {
        Patient patientObject = getPatientFromRepository(patient.getPatientId());
        if (patientObject != null) {
            List<String> doctorList = patientObject.getDoctorList();
            if (doctorList.size() > 1) {
                Doctor doctor = checkDoctorIsPresentInDatabase(patient.getDoctorList());
                if (doctorList.contains(doctor.getId())) {
                    doctorList.remove(String.valueOf(doctor.getId()));
                }
            }
            patientObject.setDoctorList(doctorList);
            patientRepository.save(patientObject);
            return successResponseBuilder(FileMessageEnum.SUCCESS.getFileKey(), FileMessageEnum.REMOVE_DOCTOR.getFileKey());
        }
        return errorResponseBuilder(FileMessageEnum.FAILURE.getFileKey(), FileMessageEnum.REMOVE_DOCTOR_FAILED.getFileKey());
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
    private Doctor checkDoctorIsPresentInDatabase(List<String> doctorIdList) {
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
        patientObject.setPatientAge(StringUtils.isNotBlank(patient.getPatientAge()) ?
                patient.getPatientAge() : patientObject.getPatientAge());
        patientObject.setPatientAddress(StringUtils.isNotBlank(patient.getPatientAddress()) ?
                patient.getPatientAddress() : patientObject.getPatientAddress());
        patientObject.setDoctorList(patientObject.getDoctorList());
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
