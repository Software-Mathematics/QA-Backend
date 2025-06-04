package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.CheckList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckListDao extends MongoRepository<CheckList, Long> {
}
