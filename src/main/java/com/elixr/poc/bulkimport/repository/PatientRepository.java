package com.elixr.poc.bulkimport.repository;

import com.elixr.poc.bulkimport.dto.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to communicate with database.
 */
@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
  Patient getByPatientId(String patientId);
}
