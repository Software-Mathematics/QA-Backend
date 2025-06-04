package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestDao extends MongoRepository<Request, Long> {
}
