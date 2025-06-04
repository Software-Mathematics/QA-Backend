package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrescriptionDao extends MongoRepository<Prescription, Long> {
    List<Prescription> findAllByStatus(String status);
    List<Prescription> findAllByPresidAndStatusNotLike(String presId, String status);
    List<Prescription> findAllByPrestempidAndStatusNotLike(String PresTempId, String status);
    List<Prescription> findByMmucodeAndCreateddateBetween(String mmucode, Date startdate, Date enddate);

    List<Prescription> findByMmucodeInAndCreateddateBetween(List<String> mmucodeList, Date startdate, Date enddate);


}
