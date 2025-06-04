package com.commons.util.adapter;

import com.commons.data.entity.Registration;
import com.commons.data.entity.Role;
import com.commons.data.entity.User;
import com.commons.util.model.dto.RegistrationDTO;
import com.commons.util.model.dto.RoleDTO;
import com.commons.util.model.dto.UserRegistrationDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


public enum RegistrationDbModelAdapterInstances implements AppDbModelAdapter<Object, Object>{

	REGISTRATION_TO_DTO_ADAPTER() {
		@Override
		public Object adapt(Object request) { // throws
			if (request instanceof Registration) {
				Registration registration = (Registration) request;
				ModelMapper modelMapper = new ModelMapper();
				Object dto = modelMapper.map(registration, RegistrationDTO.class);
				return dto;
			} else {
				ModelMapper modelMapper = new ModelMapper();
				List<Registration> registrations = (List<Registration>) request;
				List<RegistrationDTO> registrationDTOS = registrations
						.stream()
						.map(role -> modelMapper.map(role, RegistrationDTO.class))
						.collect(Collectors.toList());
				return registrationDTOS;
			}

		}
	},
	DTO_TO_REGISTRATION_ADAPTER() {
		@Override
		public Object adapt(Object request) {
			// throws
			// AppDBModelAdapterException {
			RegistrationDTO dto = (RegistrationDTO) request;
			ModelMapper modelMapper = new ModelMapper();
			Object user = modelMapper.map(dto, Registration.class);
			return user;
		}

	};
}
