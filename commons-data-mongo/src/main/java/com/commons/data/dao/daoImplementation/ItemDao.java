package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemDao extends MongoRepository<Item, Long> {
}
