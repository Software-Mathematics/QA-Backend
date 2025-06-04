package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.MedRequisitionGenTxn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedRequisitionGenTxnDao extends MongoRepository<MedRequisitionGenTxn, Long> {
}
