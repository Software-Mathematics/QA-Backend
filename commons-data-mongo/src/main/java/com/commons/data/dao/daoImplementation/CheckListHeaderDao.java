package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.CheckListHeader;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckListHeaderDao extends MongoRepository<CheckListHeader, Long> {
}
