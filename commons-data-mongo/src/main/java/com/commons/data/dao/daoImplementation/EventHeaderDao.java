package com.commons.data.dao.daoImplementation;


import com.commons.data.entity.EventHeader;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventHeaderDao extends MongoRepository<EventHeader, Long> {
}



