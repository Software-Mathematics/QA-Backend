package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ApprovalLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalLogDao extends MongoRepository<ApprovalLog, Long> {
}
