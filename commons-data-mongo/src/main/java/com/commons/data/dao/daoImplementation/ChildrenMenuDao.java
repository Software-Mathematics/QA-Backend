package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.ChildrenMenu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildrenMenuDao extends MongoRepository<ChildrenMenu, Long> {


    List<ChildrenMenu> findAllByMastermenuid(Long menuId);
}
