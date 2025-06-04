package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Itembatch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ItembatchDao extends MongoRepository<Itembatch,Long> {
}
