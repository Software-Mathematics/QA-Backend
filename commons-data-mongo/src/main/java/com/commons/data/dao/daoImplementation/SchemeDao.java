package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Scheme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemeDao extends MongoRepository<Scheme, Long> {
}
