package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDao extends MongoRepository<Resource, Long> {
//    @Query(value = "Select * from dbo.resource d where d.status='true' and d.id=?1", nativeQuery = true)
//    Resource getById(String id);
//
//    @Query(value = "Select * from dbo.resource d where d.status='true'", nativeQuery = true)
//    List<Resource> getAll();
//
//    @Query(value = "Select * from dbo.resource d where d.status='true' and d.resource_code=?1", nativeQuery = true)
//    Resource getByCode(String code);
}
