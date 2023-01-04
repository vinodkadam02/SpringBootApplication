package com.elixr.poc.Repository;

import com.elixr.poc.data.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, UUID> {
}
