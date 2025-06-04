package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.AttributeMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeMasterDao extends MongoRepository<AttributeMaster, Long> {
    List<AttributeMaster> findByProcessidAndStationcodeAndMachinecodeAndNameAndStatus(
            String processId,
            String stationCode,
            String machineCode,
            String name,
            String status
    );

    List<AttributeMaster> findByModelnoAndStationcodeAndNameAndStatus(
            String modelNo,
            String stationCode,
            String name,
            String status
    );
}
