package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ProfessionalDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalDetailsDao extends MongoRepository<ProfessionalDetails, String> {

//
////    @Query(value = "Select * from dbo.professional_details d where d.status='true' and d.id=?1", nativeQuery = true)
////    ProfessionalDetails getById(String id);
//    ProfessionalDetails findByIdAndStatus(String id, boolean statusTrue);
//
////    @Query(value = "Select * from dbo.professional_details d where d.status='true'", nativeQuery = true)
////    List<ProfessionalDetails> getAll();
//    List<ProfessionalDetails> findAllByStatus(boolean statusTrue);
//
////    @Query(value = "Select * from dbo.professional_details d where d.status='true' and d.dept_code=?1", nativeQuery = true)
////    List<ProfessionalDetails> getByDeptCode(String deptCode);
//    List<ProfessionalDetails> findAllByDeptCodeAndStatus(String deptCode, boolean statusTrue);
//
////    @Query(value = "Select * from dbo.professional_details d where d.status='true' and d.profile_id=?1", nativeQuery = true)
////    ProfessionalDetails getByProfileId(String profileId);
//    ProfessionalDetails findByProfileIdAndStatus(String profileId, boolean statusTrue);
//
//    List<ProfessionalDetails> findAllByProfileIdAndStatus(String profileId, boolean status);
//
//    ProfessionalDetails findByEmpIdAndStatus(String emp_id, boolean status);
}
