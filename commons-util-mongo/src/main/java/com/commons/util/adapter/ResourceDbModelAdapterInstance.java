package com.commons.util.adapter;

import com.commons.data.entity.Address;
import com.commons.data.entity.Resource;
import com.commons.util.model.dto.AddressDTO;
import com.commons.util.model.dto.ResourceDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum ResourceDbModelAdapterInstance implements
AppDbModelAdapter<Object,Object>{
    RESOURCE_TO_DTO() {
        @Override
        public Object adapt(Object request){
            if(request instanceof Resource){
                ModelMapper modelMapper = new ModelMapper();
                Object dto = modelMapper.map((Resource)request, ResourceDTO.class);
                return dto;
            }else{
                ModelMapper modelMapper = new ModelMapper();
                List<Resource> resources = (List<Resource>) request;
                List<ResourceDTO> resourceDTOS = resources
                        .stream()
                        .map(resource -> modelMapper.map(resource, ResourceDTO.class))
                        .collect(Collectors.toList());
                return resourceDTOS;
//                List<AddressDTO> addressDTOS = modelMapper.map((List<Address>)request, new TypeToken<List<AddressDTO>>(){}.getType());
//                return addressDTOS;
            }


        }
    },

    RESOURCEDTO_TO_RESOURCE(){
        @Override
        public Object adapt(Object  request){
            ResourceDTO resourceDTO = (ResourceDTO) request;
            ModelMapper modelMapper = new ModelMapper();
            Object resource = modelMapper.map(resourceDTO, Resource.class);
            return resource;
        }
    };
}
