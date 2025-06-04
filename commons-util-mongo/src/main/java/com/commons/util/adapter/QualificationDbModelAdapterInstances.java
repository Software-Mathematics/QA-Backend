package com.commons.util.adapter;

import com.commons.data.entity.Qualification;
import com.commons.util.model.dto.QualificationDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum  QualificationDbModelAdapterInstances implements
                                            AppDbModelAdapter<Object,Object> {

    QUALIFICATION_TO_QUALIFICATIONDTO() {
        @Override
        public Object adapt(Object request) {
            if (request instanceof Qualification) {

                ModelMapper modelMapper = new ModelMapper();
                QualificationDTO dto = modelMapper.map((Qualification) request, QualificationDTO.class);
                return dto;
            } else {
                ModelMapper modelMapper = new ModelMapper();
                List<Qualification> qualifications = (List<Qualification>) request;
                List<QualificationDTO> qualificationDTOS = qualifications
                        .stream()
                        .map(qualification -> modelMapper.map(qualification, QualificationDTO.class))
                        .collect(Collectors.toList());
                return qualificationDTOS;
            }
        }
    },
    QUALIFICATION_DTO_TO_QUALIFICATION() {
        @Override
        public Object adapt(Object request) {
            QualificationDTO dto = (QualificationDTO) request;
            ModelMapper modelMapper = new ModelMapper();
            Object qualification = modelMapper.map(dto, Qualification.class);
            return qualification;
        }


    };
}


