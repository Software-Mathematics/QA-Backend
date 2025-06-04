package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.GroupEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends MongoRepository<GroupEntity, Long> {

}
