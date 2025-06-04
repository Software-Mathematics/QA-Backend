package com.commons.util.model.dto;

import com.commons.data.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class ProfileAggregationDTO {
    private ProfileEntity profile;
    private Login login;
    private List<CommunicationRecord> communicationrecordlist;
    private List<ProfessionalDetails> professionaldetailslist;
    private PersonalInformation personalinformation;
    private List<Qualification> qualificationlist;
    private List<Experience> experiencelist;
    private List<Address> addresslist;
    private List<UsercategoryDTO> usercategories;
    private List<DocumentsDTO> documents;
    private Commission commission;
}
