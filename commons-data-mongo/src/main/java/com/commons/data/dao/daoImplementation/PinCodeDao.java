package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.PinCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinCodeDao extends MongoRepository<PinCode, Long> {
}
