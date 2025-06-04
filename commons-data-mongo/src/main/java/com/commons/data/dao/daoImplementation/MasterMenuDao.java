package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.MasterMenu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterMenuDao extends MongoRepository<MasterMenu, Long> {


    MasterMenu findAllByMenuid(Long menuId);

//    @Query(value = "select * from dbo.mastermenu m where m.role=?1", nativeQuery = true)
    List<MasterMenu> findAllByRole(String role);

//    @Query(value = "select * from dbo.mastermenu m where m.role=?1 and m.resource_code=?2", nativeQuery = true)
    List<MasterMenu> findAllByRoleAndResourcecode(String role, String resourceCode);
}
