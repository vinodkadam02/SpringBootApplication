package com.elixr.poc.repository;

import com.elixr.poc.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GlobalRepository extends MongoRepository<User, UUID> {

}
