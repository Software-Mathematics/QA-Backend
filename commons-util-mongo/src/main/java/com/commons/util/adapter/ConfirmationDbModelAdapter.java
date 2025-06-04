package com.commons.util.adapter;

import com.commons.data.entity.ConfirmationToken;
import com.commons.util.exception.AppDBModelAdapterException;
import com.commons.util.model.dto.ConfirmationTokenDTO;
import com.commons.util.model.error.AppException;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;

public enum ConfirmationDbModelAdapter implements AppDbModelAdapter<Object, Object>{

    TOKEN_TO_TOKEN_DTO(){
        @Override
        public Object adapt(Object request){

                ConfirmationToken confirmationToken = (ConfirmationToken) request;
                ModelMapper modelMapper = new ModelMapper();
                Object dto = modelMapper.map(confirmationToken, ConfirmationTokenDTO.class);
                return dto;
        }
    },

    TOKEN_DTO_TO_TOKEN(){
        @Override
        public Object adapt(Object request) {
                ConfirmationTokenDTO confirmationTokenDTO = (ConfirmationTokenDTO) request;
                ModelMapper modelMapper = new ModelMapper();
                Object conToken = modelMapper.map(confirmationTokenDTO, ConfirmationToken.class);
                return conToken;

        };
    }

}
