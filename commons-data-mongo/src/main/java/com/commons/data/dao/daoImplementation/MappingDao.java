package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Mapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingDao extends MongoRepository<Mapping, Long> {
}
