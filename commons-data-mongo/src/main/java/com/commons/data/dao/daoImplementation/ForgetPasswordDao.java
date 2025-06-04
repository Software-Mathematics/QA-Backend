package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ForgetPassword;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ForgetPasswordDao extends MongoRepository<ForgetPassword, Long> {
}
