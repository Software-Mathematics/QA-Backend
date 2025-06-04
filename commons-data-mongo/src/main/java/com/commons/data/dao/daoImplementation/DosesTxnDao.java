package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.DosesTxn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosesTxnDao extends MongoRepository<DosesTxn, Long> {
}
