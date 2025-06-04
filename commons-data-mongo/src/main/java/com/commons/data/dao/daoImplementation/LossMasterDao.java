package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.LossMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LossMasterDao extends MongoRepository<LossMaster, Long> {
}
