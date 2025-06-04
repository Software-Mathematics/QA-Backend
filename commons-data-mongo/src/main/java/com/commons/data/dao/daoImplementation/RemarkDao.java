package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Remark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarkDao extends MongoRepository<Remark, Long> {

}
