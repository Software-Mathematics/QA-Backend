package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.BOMCreation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BOMCreationDao extends MongoRepository<BOMCreation, Long> {
}
