package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Login;
import com.commons.data.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LoginDao extends MongoRepository<Login, Long> {

    List<Login> findByMobilenoIgnoreCaseOrCodeIgnoreCase(String mobileNo, String code);

    List<Login> findByMobilenoOrCode(String mobileNo, String code);

    List<Login> findByEmailidIgnoreCaseOrMobilenoIgnoreCaseAndResourcecodeIgnoreCase(String email, String mobile, String resourceCode);

    List<Login> findByEmailidIgnoreCaseOrMobilenoIgnoreCaseAndResourcecodeIgnoreCaseAndIsactiveIgnoreCase(String email, String mobile, String resourceCode, String isActive);

    List<Login> findByIsactiveIgnoreCaseAndResourcecodeIgnoreCaseAndEmailidIgnoreCaseOrMobilenoIgnoreCase(String isActive, String resourceCode, String email, String mobile);

    Login findByIsactiveIgnoreCaseAndResourcecodeIgnoreCase(String isActive, String resourceCode);

    //    Login findByEmailidIgnoreCaseOrMobilenoIgnoreCase(String email, String mobile);
    List<Login> findByEmailidIgnoreCaseOrMobilenoIgnoreCase(String email, String mobile);

    List<Login> findByEmailidIgnoreCaseOrMobilenoIgnoreCaseOrUsernameIgnoreCase(String email, String mobile, String username);

    Login findByIsactiveIgnoreCaseAndEmailidIgnoreCaseOrMobilenoIgnoreCase(String isActive, String email, String mobile);

    List<Login> findByEmailid(String email);

    Login findByProfileid(String profileId);

    @Query("{'mappingcode': {$regex: '^?0(-.*)?$', $options: ''}, 'status': 'ACTIVE'}")
    List<Login> findByMappingcodeStartsWith(String mappingCode);

    @Query("{'mappingcode': {$regex: '^?0(-.*)?$', $options: ''}, 'status': 'ACTIVE', '?1': {$regex: ?2, $options: 'i'}}")
    List<Login> findByMappingcodeStartsWithField(String mappingCode, String field, String searchText);

    @Query("{'mappingcode': {$regex: '^?0(-.*)?$', $options: ''}, 'status': 'ACTIVE'")
    Page<Login> findByMappingcodeStartsWith(String mappingCode, Pageable pageable);

    @Query("{'mappingcode': {$regex: '^?0(-.*)?$', $options: ''}, 'status': 'ACTIVE', " + "'$or': [ { ?1: { $regex: ?2, $options: 'i' } } ] }")
    Page<Login> findByMappingcodeStartsWithField(String mappingCode, String field, String searchText, Pageable pageable);

}
