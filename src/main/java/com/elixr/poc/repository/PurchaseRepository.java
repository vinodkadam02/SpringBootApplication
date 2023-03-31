package com.elixr.poc.repository;

import com.elixr.poc.data.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface to communicate with database
 */
@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, UUID> {
    List<Purchase> findPurchasesByUserName(String userName);
}
