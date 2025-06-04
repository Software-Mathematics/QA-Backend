package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Attribute;
import com.commons.data.entity.PartStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttributeDao extends MongoRepository<Attribute, Long> {

    List<Attribute> findByProcessidAndStationcodeAndMachinecodeAndNameAndStatus(
            String processId,
            String stationCode,
            String machineCode,
            String name,
            String status
    );

    List<Attribute> findByNameAndStationcodeAndCreateddateBetween(
            String name,
            String stationCode,
            Date startdate,
            Date enddate
    );
    List<Attribute> findByNameAndCode(
            String name,
            List<String> stationCode

    );
}
