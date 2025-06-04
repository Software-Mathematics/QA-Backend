package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.FrequencyMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrequencyMasterDao extends MongoRepository<FrequencyMaster, Long> {
    List<FrequencyMaster> findAllByStatus(String status);
}
