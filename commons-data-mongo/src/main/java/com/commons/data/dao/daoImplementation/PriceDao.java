package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PriceDao extends MongoRepository<Price, Long > {
    List<Price> findByMmucode(String mmucode);
    List<Price> findByMmucodeAndTypeIgnoreCase(String mmucode, String Type);
}
