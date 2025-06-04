package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogDao extends MongoRepository<AuditLog, Long> {
}
