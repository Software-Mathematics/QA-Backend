package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ConsentMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentMasterDao extends MongoRepository<ConsentMaster, Long> {
}
