package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.MetalMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetalMasterDao extends MongoRepository<MetalMaster, Long> {
}
