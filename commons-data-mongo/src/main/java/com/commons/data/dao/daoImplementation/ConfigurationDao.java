package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationDao extends MongoRepository<Configuration, Long> {
}
