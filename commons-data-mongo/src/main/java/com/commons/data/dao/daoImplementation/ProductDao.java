package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDao extends MongoRepository<Product, Long> {
}
