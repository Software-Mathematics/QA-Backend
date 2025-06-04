package com.commons.util.model.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllCodeResponse {
    private String role;
    private String department;
    private String username;
    private String designation;
    private String group;
}
