package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.StationMaster;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StationMasterDao extends MongoRepository<StationMaster, Long> {
}
