package com.commons.data.dao.daoImplementation;



import com.commons.data.entity.Version;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionDao extends MongoRepository<Version, Long> {
}
