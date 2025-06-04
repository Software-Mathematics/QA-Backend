package com.commons.util.adapter;

import com.commons.data.entity.Department;
import com.commons.data.entity.Designation;
import com.commons.util.model.dto.DepartmentDTO;
import com.commons.util.model.dto.DesignationDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum DepartmentDbModelAdapter implements AppDbModelAdapter<Object, Object> {
    DEPARTMENT_TO_DTO(){
        @Override
        public Object adapt(Object o) {
            if (o instanceof Department) {
                Department department = (Department) o;
                ModelMapper modelMapper = new ModelMapper();
                Object dto = modelMapper.map(department, DepartmentDTO.class);
                return dto;
            }else {
                ModelMapper modelMapper = new ModelMapper();
                List<Department> departments = (List<Department>) o;
                List<DepartmentDTO> departmentDTOS = departments
                        .stream()
                        .map(address -> modelMapper.map(address, DepartmentDTO.class))
                        .collect(Collectors.toList());
                return departmentDTOS;
            }
        }
    },

    DTO_DEPARTMENT(){
        @Override
        public Object adapt(Object o) {
            DepartmentDTO departmentDTO = (DepartmentDTO) o;
            ModelMapper modelMapper = new ModelMapper();
            Department department = modelMapper.map(departmentDTO, Department.class);
            return department;
        }
    }
}
