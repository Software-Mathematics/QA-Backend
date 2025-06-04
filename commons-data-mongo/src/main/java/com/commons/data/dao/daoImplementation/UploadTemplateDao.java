package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.UploadTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadTemplateDao extends MongoRepository<UploadTemplate, Long> {
}
