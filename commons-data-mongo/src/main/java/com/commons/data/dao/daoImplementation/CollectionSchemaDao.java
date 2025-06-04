package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.CollectionSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionSchemaDao extends MongoRepository<CollectionSchema, Long> {
}
