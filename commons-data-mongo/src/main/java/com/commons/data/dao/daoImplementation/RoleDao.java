package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends MongoRepository<Role, Long> {

//    @Query(value = "Select * from dbo.role r where r.status='true' and r.role_id=?1", nativeQuery = true)
//    Role getById(String id);
//
//    @Query(value = "Select * from dbo.role r where r.status='true'", nativeQuery = true)
//    List<Role> getAll();
//
//    @Query(value = "Select * from role r where r.status='true' and r.role_code=?1", nativeQuery = true)
//    Role getByCode(String code);
//
//    Role findByNameAndStatus(String name, Boolean status);
}
