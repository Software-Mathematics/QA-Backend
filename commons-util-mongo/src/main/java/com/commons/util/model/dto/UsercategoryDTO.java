package com.commons.util.model.dto;

import com.commons.data.entity.Category;
import lombok.Data;

@Data

public class UsercategoryDTO extends BaseDtoI{

    private String userid;
    private String profileid;
    private String resourcecode;
    private Category category;
    private String mmucode;
}
