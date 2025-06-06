package com.dailycodebuffer.filemngt.repository;

import com.dailycodebuffer.filemngt.entity.VideoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<VideoEntity, String> {

}
