package com.commons.data.dao.daoImplementation;


import com.commons.data.entity.CommunityEngagement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityEngagementDao extends MongoRepository<CommunityEngagement, Long> {
}
