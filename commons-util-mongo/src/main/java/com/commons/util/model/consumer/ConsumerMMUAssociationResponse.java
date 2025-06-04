package com.commons.util.model.consumer;

import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

@Data
public class ConsumerMMUAssociationResponse extends ConsumerBaseResponse {
    private String code;
    private String profileid;
    private String mmucode;
    private String rolecode;
    private String fullname;
    private String firstname;
    private String lastname;
    private String rolename;
    private String mmuname;
    private String classname;
}
