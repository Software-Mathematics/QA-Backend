package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.FamilyDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyDetailsDao extends MongoRepository<FamilyDetails, Long> {
}
