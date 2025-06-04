package com.commons.util.model.dto;

import com.commons.data.entity.PinCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntityDTO extends BaseDtoI{

//    private long id;
    private String resourcecode;
    private String resourcename;
    private String groupcode;
    private String rolecode;
    private String rolename;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String resetflag;
    private String token;
    private String profileid;
    private String emailid;
    private String phoneno;
    private String classname;
    private PinCode pincode;
    private String designationcode;
    private String designationname;
    private String departmentcode;
    private String mmucode;
    private String departmentname;
    private String dateofbirth;

    private String referalcode;
    private String gender;

    List<CommunicationRecordDTO> communicationRecordDTOList;
    List<ProfessionalDetailsDTO> professionalDetailsDTOList;
    PersonalInformationDTO personalInformationDTO;
    List<QualificationDTO> qualificationDTOList;
    List<ExperienceDTO> experienceDTOList;

    private Config config;
}
