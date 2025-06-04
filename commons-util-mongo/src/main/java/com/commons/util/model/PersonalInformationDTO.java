package com.commons.util.model;

import com.commons.util.model.dto.BaseDTO;
import lombok.Data;

@Data
public class PersonalInformationDTO {

    private String id;
    private String profileId;
    private String firstName;
    private String lastName;
    private String nickName;
    private String fatherName;
    private String motherName;
    private String dateOfBirth;
    private BaseDTO auditTrails;
    private boolean status;

    public boolean isStatus() {
        return status;
    }
}
