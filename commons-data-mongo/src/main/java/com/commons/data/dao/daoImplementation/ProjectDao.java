package com.commons.data.dao.daoImplementation;


import com.commons.data.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends MongoRepository<Project, Long> {
    List<Project> findByProjecthirarichalcodeStartsWith(String hirarchalCode);

    List<Project> findByProjecthirarichalcodeContaining(String code);



    Project findByCode(String code);

    @Query("{'projecthirarichalcode': {$regex: '^?0-(?!.*-$)', $options: ''}, 'status': 'ACTIVE'}")
    List<Project> findByProjecthirarichalcode(String hirarchalCode);

    @Query("{'projecthirarichalcode': {$regex: '^?0-(?!.*-$)', $options: ''}, 'status': 'ACTIVE', '?1': {$regex: ?2, $options: 'i'}}")
    List<Project> findByProjecthirarichalcodeWithField(String hirarchalCode, String field, String searchText);

    @Query("{'projecthirarichalcode': {$regex: '^?0-(?!.*-$)', $options: ''}, 'status': 'ACTIVE'")
    Page<Project> findByProjecthirarichalcodeWithHyphenAndStartsWith(String hirarchalCode, Pageable pageable);

    @Query("{'projecthirarichalcode': {$regex: '^?0-(?!.*-$)', $options: ''}, 'status': 'ACTIVE', " + "'$or': [ { ?1: { $regex: ?2, $options: 'i' } } ] }")
    Page<Project> findByProjecthirarichalcodeWithHyphenAndStartsWithField(String hirarchalCode, String field, String searchText, Pageable pageable);
}
