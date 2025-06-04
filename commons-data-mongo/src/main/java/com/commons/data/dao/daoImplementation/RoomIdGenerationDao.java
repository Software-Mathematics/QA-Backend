package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.RoomIdGeneration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomIdGenerationDao extends MongoRepository<RoomIdGeneration, Long> {
}
