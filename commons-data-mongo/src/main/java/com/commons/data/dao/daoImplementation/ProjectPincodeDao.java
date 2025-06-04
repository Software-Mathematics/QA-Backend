package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Project;
import com.commons.data.entity.ProjectPincode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPincodeDao extends MongoRepository<ProjectPincode, Long> {
}
