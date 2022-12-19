package ch.zhaw.application_crafty.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.application_crafty.model.Craftsman;


public interface CraftsmanRepository extends MongoRepository<Craftsman, String> {
    
}
