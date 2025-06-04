package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Dealer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DealerDao extends MongoRepository<Dealer, Long> {
}
