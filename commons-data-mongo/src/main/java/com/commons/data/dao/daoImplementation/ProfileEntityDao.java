package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileEntityDao extends MongoRepository<ProfileEntity, Long> {
//    List<ProfileEntity> findAllByUsername(String username);
//    List<ProfileEntity> findAllByIsEnable(boolean status);
//
//    List<ProfileEntity> findAllByResourceCodeAndIsEnable(String resourceCode, boolean status);
//
//    List<ProfileEntity> findAllByResourceCodeAndIdAndIsEnable(String resourceCode, String id, boolean status);
//
//    ProfileEntity findByIdAndIsEnable(Long id, boolean status);
}
