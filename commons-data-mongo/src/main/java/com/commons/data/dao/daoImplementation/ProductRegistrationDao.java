package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ProductRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRegistrationDao extends MongoRepository<ProductRegistration, Long> {
}
