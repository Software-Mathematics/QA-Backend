package com.commons.data.dao.daoImplementation;
import com.commons.data.entity.Event;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface EventDao extends MongoRepository<Event, Long> {


        List<Event> findByMmucodeAndRecstatusAndStartdateBetween(String mmuCode, String recStatus,Date startDate, Date endDate);
        List<Event> findByProfileidAndStartdateBetween(String profileId,Date startDate, Date endDate);
        List<Event> findByProfileidAndStartdateBetween(String profileId, String startDate, String endDate);

        List<Event> findByMmucodeAndRecstatus(String mmuCode, String recStatus);
//        List<Event> findAllByMmucodeAndRecstatusAndPublicationTimeBetween(String mmuCode, String recStatus,Date startDate, Date endDate);

        List<Event> findByMmucodeAndAddressobjectRecstatusAndStartdateBetween(String mmucode, String recstatus, String startDate, String endDate);
        List<Event> getByMmucodeAndStartdateBetween(String mmucode, String startDate, String endDate);

}

