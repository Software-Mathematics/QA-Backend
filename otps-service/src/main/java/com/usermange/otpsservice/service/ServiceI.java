package com.usermange.otpsservice.service;

import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.dto.UpdateDTO;
import com.commons.util.model.error.AppException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Service
public interface ServiceI {

   BaseDtoI create(BaseDtoI baseDtoI, String msgType) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
   List<BaseDtoI> get(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
   BaseDtoI delete(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
   BaseDtoI update(BaseDtoI baseDtoI) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
   Long update(Map<String, Object> criteriaMap, BaseDtoI baseDtoI) throws AppException;
   Long updateList(List<UpdateDTO> updateDTOList) throws AppException;

   BaseDtoI forgetPassword(String userid, String resourceCode, String otpOn) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException ;

   BaseDtoI forgetPasswordV2(String userid, String resourceCode, String otpOn, String msgCode) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException ;

}
