package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.CheckListMaster;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckListMasterDao extends MongoRepository<CheckListMaster, Long> {
}
