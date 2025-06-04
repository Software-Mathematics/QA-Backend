package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.StatusMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusMasterDao extends MongoRepository<StatusMaster, Long> {
}
