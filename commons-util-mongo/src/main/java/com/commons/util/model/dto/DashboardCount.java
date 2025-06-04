package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardCount {
    private String excpectedfootfall;
    private String noofpatientinqueue;
    private String noofpatientprescribed;
}
