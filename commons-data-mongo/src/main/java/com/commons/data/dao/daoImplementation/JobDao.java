package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobDao extends MongoRepository<Job, Long> {
    List<Job> findByRecstatus(String recstatus);
}
