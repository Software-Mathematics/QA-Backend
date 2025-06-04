package com.commons.util.model.dto;

import com.commons.data.entity.ConsentSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentEntryDTO extends BaseDtoI {

    //    private Long id;
    private String profileid;
    private String consent_master_id;
    private String is_consented;
    private String consent_date;
    private ConsentSource consent_source;
    private String remarks;
    private String resourcecode;

    private String classname;

}
