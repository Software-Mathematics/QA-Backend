package com.commons.util.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeRegineDTO {
    private LocalDateTime dateofleave;
    private String reasonofleaving;
}
