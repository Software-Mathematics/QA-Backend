package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.VaccineMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineMasterDao extends MongoRepository<VaccineMaster, Long> {
    List<VaccineMaster> findByStatus(String status);

}
