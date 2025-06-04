package com.commons.util.adapter;

import com.commons.data.entity.Identities;
import com.commons.util.model.dto.IdentitiesDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum  IdentitiesDbModelAdapterInstance implements AppDbModelAdapter<Object,Object> {


    IDENTITIES_TO_IDENTITIES_DTO(){
        @Override
        public Object adapt (Object request){
            if (request instanceof Identities) {

                ModelMapper modelMapper = new ModelMapper();
                IdentitiesDTO dto = modelMapper.map((Identities) request, IdentitiesDTO.class);
                return dto;
            }else {
                ModelMapper modelMapper = new ModelMapper();
                List<Identities> identities = (List<Identities>) request;
                List<IdentitiesDTO> identitiesDTOS = identities
                        .stream()
                        .map(identities1 -> modelMapper.map(identities1, IdentitiesDTO.class))
                        .collect(Collectors.toList());
                return identitiesDTOS;
            }

        }
    },
    IDENTITIES_DTO_TO_IDENTITIES(){
    @Override
    public Object adapt(Object request) {
        IdentitiesDTO dto = (IdentitiesDTO) request;
        ModelMapper modelMapper = new ModelMapper();
        Object identities = modelMapper.map(dto, Identities.class);
        return identities;
    }
    }
    };