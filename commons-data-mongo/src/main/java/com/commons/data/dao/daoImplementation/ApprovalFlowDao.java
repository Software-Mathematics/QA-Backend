package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ApprovalFlow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalFlowDao extends MongoRepository<ApprovalFlow, Long> {
}
