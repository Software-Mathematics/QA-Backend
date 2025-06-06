package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends MongoRepository<Notification, Long> {
}
