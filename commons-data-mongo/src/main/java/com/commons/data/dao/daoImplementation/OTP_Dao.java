package com.commons.data.dao.daoImplementation;

import com.commons.data.entity.OTPEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTP_Dao extends MongoRepository<OTPEntity, String> {

//    @Query(value = "SELECT * FROM dbo.otp a WHERE a.otp = ?1", nativeQuery = true)
    OTPEntity getByOtp(int otp);

//    @Query(value = "SELECT * FROM dbo.otp a WHERE a.user_id = ?1", nativeQuery = true)
    OTPEntity getByUserid(String userId);
}
