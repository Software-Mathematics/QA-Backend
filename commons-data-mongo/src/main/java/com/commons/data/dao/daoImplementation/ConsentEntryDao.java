package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ConsentEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentEntryDao extends MongoRepository<ConsentEntry, Long> {
}
