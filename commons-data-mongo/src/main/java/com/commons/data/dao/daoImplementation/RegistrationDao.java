package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationDao extends MongoRepository<Registration, Long> {

//    @Query(value = "SELECT * FROM registration a WHERE a.id = ?1", nativeQuery = true)
//    Registration getById(String id);
//
//    @Query(value = "SELECT * FROM registration a WHERE a.username = ?1", nativeQuery = true)
//    Registration getByEmail(String userName);
//
//    @Query(value = "SELECT * FROM registration a WHERE a.username = ?1 and a.enabled='true'", nativeQuery = true)
//    Registration getByEmailWithEnable(String userName);
//
//    @Query(value = "SELECT * FROM registration a", nativeQuery = true)
//    List<Registration> getAll();
//
//    Registration findByResourceCodeAndUsername(String resourceCode, String username);
//
//    Registration findByResourceCodeAndUsernameAndLocked(String resourceCode, String username, boolean locked);
//
//    Registration findByProfileIdAndEnabled(String profileId, Boolean status);
//
//    Registration findByProfileId(String profileId);
//
//    Registration findByProfileIdAndLocked(String profileId, boolean locked);
//
//    Registration findByUsernameAndResourceCode(String username, String resourceCode);
//
//    List<Registration> findAllByLocked(boolean locked);
//
//    @Query(value = "SELECT * FROM registration a WHERE a.username = ?1 and a.enabled='true' and a.resource_code=?2", nativeQuery = true)
//    Registration getByUserNameAndResourceCodeAndStatus(String userName, String resourceCode);
//
//    List<Registration> findByRoleCodeAndEnabled(String roleCode, boolean enable);
//
//    List<Registration> findByGroupCodeAndEnabled(String groupCode, boolean enable);
//
//    Registration findByUsernameAndEnabled(String username, boolean enable);
//
//    @Query(value = "SELECT * FROM registration a where concat(a.first_name, ' ', trim(a.last_name)) = ?1 and a.enabled='true'", nativeQuery = true)
//    Registration findByFirstNameAndLastNameAndEnabled(String name);


}
