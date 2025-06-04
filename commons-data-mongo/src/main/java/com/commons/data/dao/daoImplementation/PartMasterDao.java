package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.PartMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartMasterDao extends MongoRepository<PartMaster, Long> {
}
