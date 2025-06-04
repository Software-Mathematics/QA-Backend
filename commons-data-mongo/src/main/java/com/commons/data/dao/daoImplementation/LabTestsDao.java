package com.commons.data.dao.daoImplementation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;   
import com.commons.data.entity.LabTests;

@Repository
public interface LabTestsDao extends MongoRepository<LabTests, Long>
{

}
