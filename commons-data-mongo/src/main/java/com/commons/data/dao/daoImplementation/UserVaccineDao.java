package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.UserVaccine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVaccineDao extends MongoRepository<UserVaccine, Long> {
}
