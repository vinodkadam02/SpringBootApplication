package com.elixr.poc.repository;

import com.elixr.poc.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.UUID;
/**
 * Repository interface to communicate with database
 */

@Repository
@Component
public interface UserRepository extends MongoRepository<User, UUID> {
    User findByUserName(String userName);
    boolean existsByUserName(String userName);
}
