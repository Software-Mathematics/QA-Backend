package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends MongoRepository<Address, Long> {
    Address findByMmucodeAndRecstatus(String mmucode, String recstatus);
}

