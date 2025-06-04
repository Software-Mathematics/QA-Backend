package com.commons.util.model.dto;

import lombok.Data;

@Data

public class Bankaccounts1DTO extends BaseDtoI{

    private String bankname;
    private String ifsc;
    private String accountno;
    private String holdername;
    private String profileid;
    private String classname;
}
