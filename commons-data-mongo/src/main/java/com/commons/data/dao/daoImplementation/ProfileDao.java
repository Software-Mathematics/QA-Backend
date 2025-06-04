package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends MongoRepository<Profile, String> {

//    @Query(value = "SELECT * FROM dbo.profile a WHERE a.id = ?1", nativeQuery = true)
    Profile getById(String id);

}
