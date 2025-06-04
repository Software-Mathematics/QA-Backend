package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Patient;
import com.commons.data.entity.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDao extends MongoRepository<Patient, Long> {
    Patient  findByPatientid(String patientid);
    Patient findByProfileid(String profileid);
    List<Patient>  findAllByStatus(String status);
    List<Patient> findAllByPatientidAndStatusNotLike(String patientid, String status);
    List<Patient> findAllByPatienttempidAndStatusNotLike(String patienttempid, String status);
    List<Patient> findAllByDocumentnoAndStatusNotLike(String documentno, String status);

    List<Patient> findByVillageEndsWith(String villageShortName);


    List<Patient> findByVillageIn(List<String> villageShortName);
    List<Patient> findByVillageshortnameIn(List<String> villageShortName);
    List<Patient> findBySyncstatusAndVillageshortnameIn(String syncStatus, List<String> villageShortName);

    List<Patient>  findByPatientidIn(List<String> patientIdList);
}
