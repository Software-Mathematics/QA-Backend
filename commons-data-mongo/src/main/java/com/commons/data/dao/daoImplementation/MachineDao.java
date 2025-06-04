package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Machine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MachineDao extends MongoRepository<Machine, Long> {
}
