package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends MongoRepository<Employee, Long> {
}
