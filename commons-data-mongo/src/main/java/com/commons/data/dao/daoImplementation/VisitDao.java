package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Visit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitDao extends MongoRepository<Visit, Long> {
    List<Visit> findAllByStatus(String status);
    List<Visit> findAllByMmucodeAndRecstatusAndCreateddate(String mmucode, String recstatus, Date createddate);
    List<Visit> findByCreateddate(Date createddate);
}
