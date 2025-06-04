package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.GSTMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GSTMasterDao extends MongoRepository<GSTMaster, Long> {

}
