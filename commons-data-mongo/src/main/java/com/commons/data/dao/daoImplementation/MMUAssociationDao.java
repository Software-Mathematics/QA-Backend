package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.MMUAssociation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MMUAssociationDao extends MongoRepository<MMUAssociation, Long> {
    @Query("{'mappingcode': {$regex: '^?0(-.*)?$', $options: ''}, 'status': 'ACTIVE'}")
    List<MMUAssociation> findByMappingcodeStartsWith(String mappingCode);

    @Query("{'mappingcode': {$regex: '^?0(-.*)?$', $options: ''}, 'status': 'ACTIVE', '?1': {$regex: ?2, $options: 'i'}}")
    List<MMUAssociation> findByMappingcodeStartsWithField(String mappingCode, String field, String searchText);

    @Query("{'mappingcode': {$regex: '^?0(-.*)?$', $options: ''}, 'status': 'ACTIVE'")
    Page<MMUAssociation> findByMappingcodeStartsWith(String mappingCode, Pageable pageable);

    @Query("{'mappingcode': {$regex: '^?0(-.*)?$', $options: ''}, 'status': 'ACTIVE', " + "'$or': [ { ?1: { $regex: ?2, $options: 'i' } } ] }")
    Page<MMUAssociation> findByMappingcodeStartsWithField(String mappingCode, String field, String searchText, Pageable pageable);

    MMUAssociation findByProfileidAndStatus(String profileId, String status);

}
