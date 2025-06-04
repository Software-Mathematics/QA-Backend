package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.AssociationMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationMasterDao extends MongoRepository<AssociationMaster, Long> {
}
