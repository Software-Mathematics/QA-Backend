package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends MongoRepository<Department, Long> {

}
