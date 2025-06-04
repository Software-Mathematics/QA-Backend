package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Vitals;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalsDao extends MongoRepository<Vitals, Long> {
    List<Vitals> findAllByStatus(String status);
}
