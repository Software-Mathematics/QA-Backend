package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenDao extends MongoRepository<ConfirmationToken, String> {

//    @Query(value = "SELECT * FROM dbo.token a WHERE a.token = ?1", nativeQuery = true)
    ConfirmationToken getByToken(String token);
}
