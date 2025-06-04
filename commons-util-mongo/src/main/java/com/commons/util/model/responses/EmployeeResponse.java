package com.commons.util.model.responses;

import com.commons.data.entity.Registration;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse {
    private List<Registration> employees;
}
