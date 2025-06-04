package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Doses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DosesDao extends MongoRepository<Doses, Long> {
    List<Doses> findAllByStatus(String status);
}
