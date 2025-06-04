package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.StatutoryRecords;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutoryRecordsDao extends MongoRepository<StatutoryRecords, Long> {

}
