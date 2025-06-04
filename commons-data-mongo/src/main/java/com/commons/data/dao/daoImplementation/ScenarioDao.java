package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Scenario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioDao extends MongoRepository<Scenario, Long> {
}
