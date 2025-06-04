package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ItemMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMasterDao extends MongoRepository<ItemMaster, Long> {
}
