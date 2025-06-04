package com.commons.data.dao.daoImplementation;


import com.commons.data.entity.Event;
import com.commons.data.entity.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportDao extends MongoRepository<Report, Long> {
    List<Report> findByModelnoAndDateBetween(String modelno, Date startDate, Date endDate);
}

