package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.PartStatus;
import com.commons.data.entity.PartStatusMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PartStatusMasterDao extends MongoRepository<PartStatusMaster, Long> {
    List<PartStatusMaster> findByStationcodeAndRecstatusAndCreateddateBetween(
            String stationCode,
            String status,
            Date startdate,
            Date enddate
    );
    List<PartStatusMaster> findByRecstatusAndCreateddateBetween(
            String status,
            Date startdate,
            Date enddate
    );

    List<PartStatusMaster> findByCreateddateBetween(
            Date startdate,
            Date enddate
    );

    List<PartStatusMaster> findByStationcodeIgnoreCase(String stationcode);
}
