package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.TypeMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMasterDao extends MongoRepository<TypeMaster, Long> {
}
