package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.TestMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMasterDao extends MongoRepository<TestMaster, Long> {
    List<TestMaster> findAllByStatus(String status);
}
