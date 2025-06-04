package com.commons.util.model.dto;

import com.commons.util.model.BaseDTO;
import lombok.Data;

import java.util.List;
@Data
public class IdentitiesDTO extends BaseDtoI{
//    private Long id;
    private String profileid;
    private String identityname;
    private String identityvalue;
    private String identityprovider;
    private String identitystartdate;
    private String identityexpiredate;
    private String classname;
  
  



}
