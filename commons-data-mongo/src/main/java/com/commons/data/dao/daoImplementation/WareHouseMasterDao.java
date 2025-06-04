package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.WareHouseMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseMasterDao extends MongoRepository<WareHouseMaster, Long> {
}
