package ch.zhaw.application_crafty.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.application_crafty.model.Customer;


public interface CustomerRepository extends MongoRepository<Customer, String> {
    
}
