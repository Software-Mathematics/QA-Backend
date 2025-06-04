//package com.commons.util.adapter;
//
//import com.commons.data.entity.Department;
//import com.commons.data.entity.ProfessionalDetails;
//import com.commons.util.model.dto.DepartmentDTO;
//import com.commons.util.model.dto.PersonalInformationDTO;
//import com.commons.util.model.dto.ProfessionalDetailsDTO;
//import org.modelmapper.ModelMapper;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public enum ProfessionalDetailDbModelAdapter implements AppDbModelAdapter<Object, Object>{
//    PROFESSIONAL_TO_DTO(){
//        @Override
//        public Object adapt(Object o) {
//            if (o instanceof ProfessionalDetails) {
//                ProfessionalDetails professionalDetails = (ProfessionalDetails) o;
//                ModelMapper modelMapper = new ModelMapper();
//                ProfessionalDetailsDTO dto = modelMapper.map(professionalDetails, ProfessionalDetailsDTO.class);
//                return dto;
//            }else {
//                ModelMapper modelMapper = new ModelMapper();
//                List<ProfessionalDetails> professionalDetails = (List<ProfessionalDetails>) o;
//                List<ProfessionalDetailsDTO> professionalDetailsDTOS = professionalDetails
//                        .stream()
//                        .map(address -> modelMapper.map(address, ProfessionalDetailsDTO.class))
//                        .collect(Collectors.toList());
//                return professionalDetailsDTOS;
//            }
//        }
//    },
//
//    DTO_TO_PROFESSIONAL(){
//        @Override
//        public Object adapt(Object o) {
//            ProfessionalDetailsDTO dto = (ProfessionalDetailsDTO) o;
//            ModelMapper modelMapper = new ModelMapper();
//            ProfessionalDetails professionalDetails = modelMapper.map(dto, ProfessionalDetails.class);
//            return professionalDetails;
//        }
//    }
//}
