package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationDao extends MongoRepository<User, String> {

////    @Query(value = "SELECT * dbo.FROM users a WHERE a.id = ?1", nativeQuery = true)
//    User getById(String id);
//
////    @Query(value = "SELECT * FROM dbo.users a WHERE a.email = ?1", nativeQuery = true)
//    User getByEmail(String email);
//
////    @Query(value = "SELECT * FROM dbo.users a WHERE a.email = ?1 and a.enabled='true'", nativeQuery = true)
//    User getByEmailWithEnable(String email);
}
