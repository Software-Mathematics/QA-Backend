package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Persistence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistenceDao extends MongoRepository<Persistence, Long> {
}

