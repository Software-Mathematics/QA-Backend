package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.PageStructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageStructureDao extends MongoRepository<PageStructure, Long> {
}
