package com.elixr.poc.repository;
import com.elixr.poc.data.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface PurchaseRepository extends MongoRepository<Purchase, UUID> {
}