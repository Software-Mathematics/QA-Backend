package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Identities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentitiesDao extends MongoRepository<Identities, Long> {
//    @Query(
//            value = "SELECT * FROM dbo.identities a WHERE a.id = ?1 and a.status ='true' ",
//            nativeQuery = true
//    )
//    Identities getById(String id);
//
//    @Query(value = "SELECT  * FROM dbo.identities a where a.status='true'", nativeQuery = true)
//    List<Identities> getAll();
//
//    @Query(value = "SELECT * FROM dbo.identities a where a.profile_id=?1 and a.status ='true'"
//            , nativeQuery = true)
//    List<Identities> getByProfileId(String profileId);
}
