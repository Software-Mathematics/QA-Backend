package com.commons.util.adapter;

import com.commons.data.entity.Role;
import com.commons.util.model.dto.RoleDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum RoleDbModelAdapter implements AppDbModelAdapter<Object, Object> {
    ROLE_TO_DTO(){
        @Override
        public Object adapt(Object o) {
            if (o instanceof Role) {
                Role role = (Role) o;
                ModelMapper modelMapper = new ModelMapper();
                RoleDTO dto = modelMapper.map(role, RoleDTO.class);
                return dto;
            }else{
                ModelMapper modelMapper = new ModelMapper();
                List<Role> roles = (List<Role>) o;
                List<RoleDTO> roleDTOS = roles
                        .stream()
                        .map(role -> modelMapper.map(role, RoleDTO.class))
                        .collect(Collectors.toList());
                return roleDTOS;
            }
        }
    },

    DTO_TO_ROLE(){
        @Override
        public Object adapt(Object o) {
            RoleDTO roleDTO = (RoleDTO) o;
            ModelMapper modelMapper = new ModelMapper();
            Object role = modelMapper.map(roleDTO, Role.class);
            return role;
        }
    };

}
