package com.commons.util.adapter;

import com.commons.data.entity.Experience;
import com.commons.data.entity.Kyc;
import com.commons.util.model.dto.ExperienceDTO;
import com.commons.util.model.dto.KycDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExperienceAdapter {
    public Object dtoToEntity(Object request){
        if (request instanceof ExperienceDTO) {
            Experience kyc = new Experience();
            BeanUtils.copyProperties(request, kyc);
            return kyc;
        }else {
            List<ExperienceDTO> kycDTOList = (List<ExperienceDTO>) request;
            List<Experience> kycList = new ArrayList<>();
            for (ExperienceDTO kycDTO: kycDTOList){
                Experience kyc = new Experience();
                BeanUtils.copyProperties(kycDTO, kyc);
                kycList.add(kyc);
            }
            return kycList;
        }
    }

    public Object entityToDTO(Object request){
        if (request instanceof Experience) {
            ExperienceDTO kycDTO = new ExperienceDTO();
            BeanUtils.copyProperties(request, kycDTO);
            return kycDTO;
        }else {
            List<Experience> kycList = (List<Experience>) request;
            List<ExperienceDTO> kycDTOList = new ArrayList<>();
            for (Experience kyc: kycList){
                ExperienceDTO kycDTO = new ExperienceDTO();
                BeanUtils.copyProperties(kyc, kycDTO);
                kycDTOList.add(kycDTO);
            }
            return kycDTOList;
        }
    }
}
