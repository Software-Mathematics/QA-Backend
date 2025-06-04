package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO extends BaseDtoI {

    //    private Long id;
    private String title;
    private String message;
    private  String classname;

}