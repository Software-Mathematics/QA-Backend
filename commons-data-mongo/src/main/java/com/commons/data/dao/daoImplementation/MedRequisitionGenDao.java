package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.MedRequisitionGen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedRequisitionGenDao extends MongoRepository<MedRequisitionGen, Long> {
}
