package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Summary;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SummaryDao extends MongoRepository<Summary, Long> {
    Summary findByProfileid(String profileId);
}
