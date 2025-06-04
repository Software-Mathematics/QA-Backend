package com.commons.util.adapter;

import com.commons.data.entity.Address;
import com.commons.data.entity.PersonalInformation;
import com.commons.util.model.dto.AddressDTO;
import com.commons.util.model.dto.PersonalInformationDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum  PersonalInformationDbModelAdapterInstances
        implements AppDbModelAdapter<Object,Object >{
    PERSONAL_INFORMATION_TO_PERSONALINFORMATIONDTO() {
        @Override
        public Object adapt(Object request) {
            if (request instanceof PersonalInformation) {
                ModelMapper modelMapper = new ModelMapper();
                Object dto = modelMapper.map((PersonalInformation) request, PersonalInformationDTO.class);
                return dto;
            } else {
                ModelMapper modelMapper = new ModelMapper();
                List<PersonalInformation> personalInformations = (List) request;
                List<PersonalInformationDTO> personalInformationDTOS = (List) personalInformations.stream().map((personalInformation) -> {
                    return (PersonalInformationDTO) modelMapper.map(personalInformation, PersonalInformationDTO.class);
                }).collect(Collectors.toList());
                return personalInformationDTOS;
            }
        }
            },
    PERSONALINFORMATIONDTO_TO_PERSONAL_INFORMATION(){
@Override
public Object adapt(Object request)  {
        PersonalInformationDTO dto= (PersonalInformationDTO) request;
        ModelMapper modelMapper=new ModelMapper();
        PersonalInformation personalInformation=modelMapper.map(dto,PersonalInformation.class);
        return personalInformation;
        }
        };

}
