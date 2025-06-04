package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends MongoRepository<Category, Long> {

    List<Category> findByDepthAndHierarchicalcodeContaining(Integer depth, String  heirarchalcode);
}
