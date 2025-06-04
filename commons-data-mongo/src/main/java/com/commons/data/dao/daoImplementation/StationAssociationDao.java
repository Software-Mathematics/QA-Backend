package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.StationAssociation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StationAssociationDao extends MongoRepository<StationAssociation, Long> {
}
