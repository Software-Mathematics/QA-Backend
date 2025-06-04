package com.messagecreation.entity;

import com.messagecreation.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TemplateTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateTableMongo extends BaseDTO {

    @Id
    private Long id;
    private String className;
    private String modelMapRef;
    private String bodyTemplateFormat;
    private String bodyTemplatePath;
    private String bodyTemplateName;
    private String subTemplateFormat;
    private String subTemplatePath;
    private String subTemplateName;
    private String msgCode;
    private String msgType;

}
