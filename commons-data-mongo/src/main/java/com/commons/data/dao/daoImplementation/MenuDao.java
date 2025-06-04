package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends MongoRepository<Menu, Long> {
}
