package com.messagecreation.repo;

import com.messagecreation.entity.TemplateTableMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateTableRepoMongo extends MongoRepository<TemplateTableMongo, Long> {
    TemplateTableMongo findByMsgTypeAndMsgCode(String msgType, String msgCode);
}
