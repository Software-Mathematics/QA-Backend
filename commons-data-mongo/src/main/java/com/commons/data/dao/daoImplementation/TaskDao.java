package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Taskk;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends MongoRepository<Taskk, Long> {

}
