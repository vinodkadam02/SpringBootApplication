package com.elixr.poc.repository;

import com.elixr.poc.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to communicate with database
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
