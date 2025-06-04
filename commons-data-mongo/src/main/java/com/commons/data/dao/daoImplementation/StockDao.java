package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDao extends MongoRepository<Stock, Long> {

    List<Stock> findByNameAndMmucode(String name, String mmucode);
}
