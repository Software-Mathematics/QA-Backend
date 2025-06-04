package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ProjectScenarioAssociation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectScenarioAssociationDao extends MongoRepository<ProjectScenarioAssociation, Long> {
}
