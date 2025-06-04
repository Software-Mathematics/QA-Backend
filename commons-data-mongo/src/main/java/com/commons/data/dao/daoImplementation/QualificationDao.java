package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Qualification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationDao extends MongoRepository<Qualification, Long> {
////    @Query(
////            value = "SELECT * FROM dbo.qualification a WHERE a.id = ?1 and a.status ='true' ",
////            nativeQuery = true
////    )
////    Qualification getById(String id);
//    Qualification findByIdAndStatus(String id, boolean statusTrue);
//
////    @Query(value = "SELECT  * FROM dbo.qualification a where a.status='true'", nativeQuery = true)
////    List<Qualification> getAll();
//    List<Qualification> findAllByStatus(boolean statusTrue);
//
////    @Query(value = "SELECT * FROM dbo.qualification a where a.profile_id = ?1 and a.status ='true'"
////            , nativeQuery = true)
////    List<Qualification> getByProfileId(String profileId);
//    List<Qualification> findAllByProfileIdAndStatus(String profileId, boolean statusTrue);
}
