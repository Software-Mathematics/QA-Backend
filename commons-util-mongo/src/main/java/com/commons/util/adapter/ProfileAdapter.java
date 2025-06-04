package com.commons.util.adapter;

import com.commons.data.entity.Kyc;
import com.commons.data.entity.ProfileEntity;
import com.commons.util.model.dto.KycDTO;
import com.commons.util.model.dto.ProfileEntityDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileAdapter {

    public Object dtoToEntity(Object request){
        if (request instanceof ProfileEntityDTO) {
            ProfileEntity kyc = new ProfileEntity();
            BeanUtils.copyProperties(request, kyc);
            return kyc;
        }else {
            List<ProfileEntityDTO> kycDTOList = (List<ProfileEntityDTO>) request;
            List<ProfileEntity> kycList = new ArrayList<>();
            for (ProfileEntityDTO kycDTO: kycDTOList){
                ProfileEntity kyc = new ProfileEntity();
                BeanUtils.copyProperties(kycDTO, kyc);
                kycList.add(kyc);
            }
            return kycList;
        }
    }

    public Object entityToDTO(Object request){
        if (request instanceof ProfileEntity) {
            ProfileEntityDTO kycDTO = new ProfileEntityDTO();
            BeanUtils.copyProperties(request, kycDTO);
            return kycDTO;
        }else {
            List<ProfileEntity> kycList = (List<ProfileEntity>) request;
            List<ProfileEntityDTO> kycDTOList = new ArrayList<>();
            for (ProfileEntity kyc: kycList){
                ProfileEntityDTO kycDTO = new ProfileEntityDTO();
                BeanUtils.copyProperties(kyc, kycDTO);
                kycDTOList.add(kycDTO);
            }
            return kycDTOList;
        }
    }
}
