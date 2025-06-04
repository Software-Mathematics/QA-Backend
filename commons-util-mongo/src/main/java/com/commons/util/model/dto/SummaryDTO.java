package com.commons.util.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SummaryDTO extends BaseDtoI {

    private String name;
    private String experience;
    private String age;
    private String profileid;
    private String rescoursecode;
    private String classname;
    private List<String> categorycodes;
    private List<UsercategoryDTO> usercategories;
    private String type;
    List<QualificationDTO> qualificationDTOList;
    private List<AddressDTO> address;
    private List<PriceDTO> price;
    private List<EventDTO> slots;
    private String isactive;
}
