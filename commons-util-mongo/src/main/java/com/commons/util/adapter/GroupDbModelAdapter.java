package com.commons.util.adapter;

import com.commons.data.entity.GroupEntity;
import com.commons.util.model.dto.GroupDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum GroupDbModelAdapter implements AppDbModelAdapter<Object, Object>{
    GROUP_TO_DTO(){
        @Override
        public Object adapt(Object o) {
            if (o instanceof GroupEntity) {
                GroupEntity group = (GroupEntity) o;
                ModelMapper modelMapper = new ModelMapper();
                GroupDTO dto = modelMapper.map(group, GroupDTO.class);
                return dto;
            }else{
                ModelMapper modelMapper = new ModelMapper();
                List<GroupEntity> groups = (List<GroupEntity>) o;
                List<GroupDTO> groupDTOS = groups
                        .stream()
                        .map(group -> modelMapper.map(group, GroupDTO.class))
                        .collect(Collectors.toList());
                return groupDTOS;
            }
        }
    },

    DTO_TO_GROUP(){
        @Override
        public Object adapt(Object o) {
            GroupDTO groupDTO = (GroupDTO) o;
            ModelMapper modelMapper = new ModelMapper();
            Object group = modelMapper.map(groupDTO, GroupEntity.class);
            return group;
        }
    };
}
