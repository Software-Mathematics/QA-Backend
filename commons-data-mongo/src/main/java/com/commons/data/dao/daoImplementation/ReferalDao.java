package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Referal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferalDao extends MongoRepository<Referal, Long> {
}
