package com.commons.util.adapter;

import com.commons.data.entity.Kyc;
import com.commons.util.model.dto.KycDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KycAdapter {

    public Object dtoToEntity(Object request){
        if (request instanceof KycDTO) {
            Kyc kyc = new Kyc();
            BeanUtils.copyProperties(request, kyc);
            return kyc;
        }else {
            List<KycDTO> kycDTOList = (List<KycDTO>) request;
            List<Kyc> kycList = new ArrayList<>();
            for (KycDTO kycDTO: kycDTOList){
                Kyc kyc = new Kyc();
                BeanUtils.copyProperties(kycDTO, kyc);
                kycList.add(kyc);
            }
            return kycList;
        }
    }

    public Object entityToDTO(Object request){
        if (request instanceof Kyc) {
            KycDTO kycDTO = new KycDTO();
            BeanUtils.copyProperties(request, kycDTO);
            return kycDTO;
        }else {
            List<Kyc> kycList = (List<Kyc>) request;
            List<KycDTO> kycDTOList = new ArrayList<>();
            for (Kyc kyc: kycList){
                KycDTO kycDTO = new KycDTO();
                BeanUtils.copyProperties(kyc, kycDTO);
                kycDTOList.add(kycDTO);
            }
            return kycDTOList;
        }
    }


}
