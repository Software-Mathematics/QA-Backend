package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Designation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationDao extends MongoRepository<Designation, Long> {

}
