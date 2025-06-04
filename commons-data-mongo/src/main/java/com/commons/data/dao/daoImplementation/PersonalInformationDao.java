package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.PersonalInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalInformationDao extends MongoRepository<PersonalInformation, Long> {
//    @Query(
//            value = "SELECT * FROM dbo.personal_information a WHERE a.id = ?1 ",
//            nativeQuery = true
//    )
//    PersonalInformation getById(String id);
//
//    @Query(
//            value = "SELECT * FROM dbo.personal_information a WHERE a.id = ?1 and a.status ='true' ",
//            nativeQuery = true
//    )
//    PersonalInformation getByIdWithEnable(String id);
//
//    @Query(value = "SELECT  * FROM dbo.personal_information a where a.status='true'", nativeQuery = true)
//    List<PersonalInformation> getAll();
//
////    @Query(value = "SELECT * FROM personal_information a where a.profile_id=?1 and a.status =true"
////            , nativeQuery = true)
////    List<PersonalInformation> getByProfileId(String profileId);
//
//    @Query(value = "SELECT * FROM dbo.personal_information a where a.profile_id=?1 and a.status ='true'"
//            , nativeQuery = true)
//    List<PersonalInformation> findByProfileId(String profileId);
//
//    PersonalInformation findByProfileIdAndStatus(String profileId, Boolean status);
//
//    @Query(value = "select id, date_of_birth, father_name, first_name, last_name, mother_name, nick_name, profile_id, status from dbo.personal_information  where profile_id=:profileId and status='true'"
//            , nativeQuery = true)
//    PersonalInformation getByProfileId(@Param("profileId") String profileId);

    List<PersonalInformation> findByAgeBetweenAndSexInAndStatus(int ageFrom, int ageTo, List<String> sexList, String status);

}
