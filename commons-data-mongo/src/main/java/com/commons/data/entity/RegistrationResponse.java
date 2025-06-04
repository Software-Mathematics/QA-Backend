package com.commons.data.entity;

import com.commons.data.entity.Registration;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationResponse {
    private List<Registration> registrations;
}
