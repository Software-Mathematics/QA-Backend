package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ETLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ETLServiceDao extends MongoRepository<ETLEntity,Long> {
    List<ETLEntity> findByGroup(String group);
}
