package com.commons.util.adapter;


import com.commons.data.entity.Address;
import com.commons.util.model.dto.AddressDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum AddressDbModelAdapterInstances implements AppDbModelAdapter<Object, Object> {
    ADDRESS_TO_ADDRESS_DTO() {
        @Override
        public Object adapt(Object request){
            if(request instanceof Address){
                ModelMapper modelMapper = new ModelMapper();
                Object dto = modelMapper.map((Address)request, AddressDTO.class);
                return dto;
            }else{
                ModelMapper modelMapper = new ModelMapper();
                List<Address> addresses = (List<Address>) request;
                List<AddressDTO> addressDTOS = addresses
                        .stream()
                        .map(address -> modelMapper.map(address, AddressDTO.class))
                        .collect(Collectors.toList());
                return addressDTOS;
//                List<AddressDTO> addressDTOS = modelMapper.map((List<Address>)request, new TypeToken<List<AddressDTO>>(){}.getType());
//                return addressDTOS;
            }


        }
    },

    ADDRESS_DTO_TO_ADDRESS(){
        @Override
        public Object adapt(Object  request){
                AddressDTO addressDTO = (AddressDTO) request;
                ModelMapper modelMapper = new ModelMapper();
                Object address = modelMapper.map(addressDTO, Address.class);
                return address;
        }
    };
}
