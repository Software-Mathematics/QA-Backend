package com.commons.util.adapter;

import com.commons.data.entity.User;
import com.commons.util.model.dto.UserRegistrationDTO;
import org.modelmapper.ModelMapper;


public enum UserDbModelAdapterInstances implements AppDbModelAdapter<Object, Object>{

	USER_TO_USERDTO_ADAPTER() {
		@Override
		public Object adapt(Object request) { // throws
			// AppDBModelAdapterException {
			User user = (User) request;
			ModelMapper modelMapper = new ModelMapper();
			Object dto = modelMapper.map(user, UserRegistrationDTO.class);
			return dto;
		}


	},
	USERDTO_TO_USER_ADAPTER() {
		@Override
		public Object adapt(Object request) {
			// throws
			// AppDBModelAdapterException {
			UserRegistrationDTO dto = (UserRegistrationDTO) request;
			ModelMapper modelMapper = new ModelMapper();
			Object user = modelMapper.map(dto, User.class);
			return user;
		}

	};
}
