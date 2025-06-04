package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends MongoRepository<Student, Long> {


}
