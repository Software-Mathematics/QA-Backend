package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.CommunicationRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRecordDao extends MongoRepository<CommunicationRecord, Long> {

}
