package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.FeatureDataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureDatasetDao extends MongoRepository<FeatureDataset, Long> {
}
