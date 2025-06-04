package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.InvestmentItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentItemDao extends MongoRepository<InvestmentItem, Long> {
}
