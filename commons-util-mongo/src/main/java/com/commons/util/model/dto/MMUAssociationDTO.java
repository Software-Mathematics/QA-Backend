package com.commons.util.model.dto;

import com.commons.data.entity.ProfileEntity;
import lombok.Data;

@Data
public class MMUAssociationDTO extends BaseDtoI{
    private String code;
    private String profileid;
    private String mmucode;
    private String rolecode;
    private String fullname;
    private String firstname;
    private String lastname;
    private String rolename;
    private String mmuname;
    private String username;
    private String parentcode;
    private String type;
    private String parentHierarchicalcode;
    private  String classname ;

}
