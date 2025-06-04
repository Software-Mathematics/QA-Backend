package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Otps;
import com.commons.data.entity.Usercategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UsercategoryDao extends MongoRepository<Usercategory, Long> {
    List<Usercategory> findByMmucode(String mmucode);
}
