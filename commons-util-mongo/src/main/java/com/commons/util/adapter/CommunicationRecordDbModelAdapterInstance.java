package com.commons.util.adapter;

import com.commons.data.entity.CommunicationRecord;
import com.commons.util.model.dto.CommunicationRecordDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum CommunicationRecordDbModelAdapterInstance implements
        AppDbModelAdapter<Object,Object> {


    COMMUNICATION_RECORD_TO_COMMUNICATION_RECORD_DTO() {
        @Override
        public Object adapt (Object request){
            if (request instanceof CommunicationRecord) {

                ModelMapper modelMapper = new ModelMapper();
                CommunicationRecordDTO dto = modelMapper.map((CommunicationRecord) request, CommunicationRecordDTO.class);
                return dto;
            }else {
                ModelMapper modelMapper = new ModelMapper();
                List<CommunicationRecord> communicationRecords = (List<CommunicationRecord>) request;
                List<CommunicationRecordDTO> communicationRecordDTOS = communicationRecords
                        .stream()
                        .map(communicationRecord -> modelMapper.map(communicationRecord, CommunicationRecordDTO.class))
                        .collect(Collectors.toList());
                return communicationRecordDTOS;
            }

        }
    },
    COMMUNICATION_RECORD_DTO_TO_COMMUNICATION_RECORD(){
        @Override
        public Object adapt(Object request) {
            if (request instanceof CommunicationRecordDTO) {
                CommunicationRecordDTO dto = (CommunicationRecordDTO) request;
                ModelMapper modelMapper = new ModelMapper();
                Object communicatiorecord = modelMapper.map(dto, CommunicationRecord.class);
                return communicatiorecord;
            }else{
                ModelMapper modelMapper = new ModelMapper();
                List<CommunicationRecordDTO> communicationRecordDTOS = (List<CommunicationRecordDTO>) request;
                List<CommunicationRecord> communicationRecords = communicationRecordDTOS
                .stream()
                .map(communicationRecordDTO -> modelMapper.map(communicationRecordDTO, CommunicationRecord.class))
                .collect(Collectors.toList());
                return communicationRecords;
            }
        }
    }
    };