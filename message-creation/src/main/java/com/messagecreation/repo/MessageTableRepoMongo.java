package com.messagecreation.repo;

import com.messagecreation.entity.MessageTableMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTableRepoMongo extends MongoRepository<MessageTableMongo, Long> {
}
