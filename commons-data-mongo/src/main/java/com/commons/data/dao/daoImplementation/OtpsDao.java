package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.Otps;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OtpsDao extends MongoRepository<Otps, Long> {
    Otps findByUseridAndOtp(String userid, String otp);
}
