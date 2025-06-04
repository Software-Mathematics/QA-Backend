package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Event;
import com.commons.data.entity.PartStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PartStatusDao extends MongoRepository<PartStatus, Long> {
    List<PartStatus> findByStationcodeAndRecstatusAndCreateddateBetween(
            String stationCode,
            String status,
            Date startdate,
            Date enddate
    );
    List<PartStatus> findByRecstatusAndCreateddateBetween(
            String status,
            Date startdate,
            Date enddate
    );
    List<PartStatus> findByTypeAndCreateddateBetween(
            String type,
            Date startdate,
            Date enddate
    );

    List<PartStatus> findByStationcodeIgnoreCase(String stationcode);

    List<PartStatus> findByStationcodeAndModelnoAndUpdateddateBetween(
            String stationCode,
            String modelNo,
            String startDate,
            String endDate
            );
    List<PartStatus> findByStationcodeInAndModelnoAndUpdateddateBetween(
            List<String> stationCodeList,
            String modelNo,
            String startDate,
            String endDate
    );
}
