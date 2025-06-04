package com.commons.util.adapter;

import com.commons.data.entity.BaseEntity;
import com.commons.data.entity.Kyc;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.dto.KycDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BaseAdapter {
    public Object dtoToEntity(Object request, BaseEntity baseEntity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (request instanceof List) {
            List entityList = new ArrayList();
            for (BaseDtoI dto: (List<BaseDtoI>) request){
                Object entity = baseEntity.getClass().getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(dto, entity);
                entityList.add(entity);
            }
            return entityList;
        }else {
            BeanUtils.copyProperties(request, baseEntity);
            return baseEntity;
        }
    }

    public Object entityToDTO(Object request, BaseDtoI baseDtoI) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (request instanceof List) {
            List dtoList = new ArrayList();
            for (BaseEntity baseEntity: (List<BaseEntity>) request){
                Object dto = baseDtoI.getClass().getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(baseEntity, dto);
                dtoList.add(dto);
            }
            return dtoList;
        }else {
            BeanUtils.copyProperties(request, baseDtoI);
            return baseDtoI;
        }
    }

}
