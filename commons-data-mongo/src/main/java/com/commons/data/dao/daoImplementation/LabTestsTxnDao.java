package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.LabTestsTxn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestsTxnDao extends MongoRepository<LabTestsTxn, Long> {
}
