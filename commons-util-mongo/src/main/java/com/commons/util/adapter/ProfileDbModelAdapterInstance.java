package com.commons.util.adapter;

import com.commons.data.entity.Profile;
import com.commons.util.model.dto.ProfileDTO;
import org.modelmapper.ModelMapper;

public enum ProfileDbModelAdapterInstance implements AppDbModelAdapter<Object, Object>{
    PROFILE_TO_PROFILE_DTO(){
        @Override
        public Object adapt(Object request) {
            Profile profile = (Profile) request;
            ModelMapper modelMapper = new ModelMapper();
            Object dto = modelMapper.map(profile, ProfileDTO.class);
            return dto;
        }
    },
        PROFILE_DTO_TO_PROFILE(){
            @Override
            public Object adapt(Object request) {
                ProfileDTO profileDTO = (ProfileDTO) request;
                ModelMapper modelMapper = new ModelMapper();
                Object profile = modelMapper.map(profileDTO, Profile.class);
                return profile;
            }
        };


}
