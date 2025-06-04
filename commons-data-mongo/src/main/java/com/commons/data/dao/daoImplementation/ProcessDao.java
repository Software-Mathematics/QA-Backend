package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Process;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessDao extends MongoRepository<Process, Long> {
}
