package com.commons.util.adapter;


import com.commons.data.entity.OTPEntity;
import com.commons.data.entity.Registration;
import com.commons.data.entity.User;
import com.commons.util.model.dto.OTP_DTO;
import com.commons.util.model.dto.RegistrationDTO;
import com.commons.util.model.dto.UserRegistrationDTO;

public enum OTP_DbModelAdapter implements AppDbModelAdapter<Object, Object>{
    OTP_TO_OTP_DTO(){
        @Override
        public Object adapt(Object request) {
            OTPEntity otp = (OTPEntity) request;
            OTP_DTO dto = new OTP_DTO();
            dto.setUser((RegistrationDTO) RegistrationDbModelAdapterInstances.REGISTRATION_TO_DTO_ADAPTER.adapt(otp.getUser()));
            dto.setExpiresAt(otp.getExpiresAt());
            dto.setCreatedAt(otp.getCreatedAt());
            dto.setUserId(otp.getUserId());
            dto.setOtp(otp.getOtp());
            dto.setPhoneNumber(otp.getPhoneNumber());
            dto.setId(otp.getId());
            dto.setConfirmedAt(otp.getConfirmedAt());
            dto.setCount(otp.getCount());
//            ModelMapper modelMapper = new ModelMapper();
//            OTP_DTO dto = modelMapper.map(otp, OTP_DTO.class);
            return dto;
        }
    },
    OTP_DTO_TO_OTP(){
        @Override
        public Object adapt(Object request){
                OTP_DTO dto = (OTP_DTO) request;
                OTPEntity otp = new OTPEntity();
                otp.setId(dto.getId());
                otp.setCount(dto.getCount());
                otp.setConfirmedAt(dto.getConfirmedAt());
                otp.setCreatedAt(dto.getCreatedAt());
                otp.setExpiresAt(dto.getExpiresAt());
                otp.setOtp(dto.getOtp());
                otp.setPhoneNumber(dto.getPhoneNumber());
                otp.setUser((Registration) RegistrationDbModelAdapterInstances.DTO_TO_REGISTRATION_ADAPTER.adapt(dto.getUser()));
                otp.setUserId(dto.getUserId());
//                ModelMapper modelMapper = new ModelMapper();
//                OTPEntity otp = modelMapper.map(dto, OTPEntity.class);
                return otp;
//        }
    }
    };
}
