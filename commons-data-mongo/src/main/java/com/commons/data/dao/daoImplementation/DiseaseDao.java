package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Disease;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseDao extends MongoRepository<Disease, Long> {

}
