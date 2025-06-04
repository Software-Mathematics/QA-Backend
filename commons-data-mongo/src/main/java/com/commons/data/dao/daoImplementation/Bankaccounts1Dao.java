package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Bankaccounts1;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Bankaccounts1Dao extends MongoRepository<Bankaccounts1, Long> {
}
