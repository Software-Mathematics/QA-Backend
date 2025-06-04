package com.dailycodebuffer.filemngt.repository;

import com.dailycodebuffer.filemngt.entity.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends MongoRepository<Attachment, String> {
    List<Attachment> findByCodeIgnoreCase(String code);

    List<Attachment> findByCodeIgnoreCaseAndModelIgnoreCase(String code, String model);
}
