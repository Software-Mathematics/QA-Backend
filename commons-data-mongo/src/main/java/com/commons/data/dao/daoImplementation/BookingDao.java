package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface BookingDao extends MongoRepository<Booking, Long> {


}
