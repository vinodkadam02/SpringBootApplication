package com.elixr.poc.bulkimport.repository;

import com.elixr.poc.bulkimport.dto.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to communicate with database.
 */
@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
}
