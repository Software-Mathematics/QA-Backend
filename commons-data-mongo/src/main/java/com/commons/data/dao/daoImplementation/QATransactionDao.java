package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.QATransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QATransactionDao extends MongoRepository<QATransaction, Long> {
}
