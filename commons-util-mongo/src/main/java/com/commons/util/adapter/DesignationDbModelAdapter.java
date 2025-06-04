package com.commons.util.adapter;

import com.commons.data.entity.Address;
import com.commons.data.entity.Designation;
import com.commons.util.model.dto.AddressDTO;
import com.commons.util.model.dto.DesignationDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum DesignationDbModelAdapter implements AppDbModelAdapter<Object, Object>{
    DESIGNATION_TO_DTO(){
        @Override
        public Object adapt(Object o) {
            if (o instanceof Designation) {
                Designation designation = (Designation) o;
                ModelMapper modelMapper = new ModelMapper();
                Object dto = modelMapper.map(designation, DesignationDTO.class);
                return dto;
            }else {
                ModelMapper modelMapper = new ModelMapper();
                List<Designation> designations = (List<Designation>) o;
                List<DesignationDTO> designationDTOS = designations
                        .stream()
                        .map(address -> modelMapper.map(address, DesignationDTO.class))
                        .collect(Collectors.toList());
                return designationDTOS;
            }
        }
    },

    DTO_TO_DESIGNATION(){
        @Override
        public Object adapt(Object o) {
            DesignationDTO dto = (DesignationDTO) o;
            ModelMapper modelMapper = new ModelMapper();
            Object designation = modelMapper.map(dto, Designation.class);
            return designation;
        }
    }
}
