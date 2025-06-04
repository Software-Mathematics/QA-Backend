package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "UploadTemplate")
@Data
public class UploadTemplate extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "UploadTemplate";
    @Id
    private Long id;
    private String schemaname;
    private String templatename;
    private List<UploadMapping> mappinglist;

    @Override
    public String toString() {
        return "UploadTemplate{" +
                "id=" + id +
                ", schemaname='" + schemaname + '\'' +
                ", templatename='" + templatename + '\'' +
                ", mappinglist=" + mappinglist +
                '}';
    }
    //    {
//        "schemaname":"Stock",
//            "templatename":"TEMP001",
//            "mappinglist": [
//        {
//            "type":"DIRECT/REFERENCED/AUTOGENERATOR",
//                "refcollectionname":"ItemMaster",
//                "reffieldoperation":"CONCAT/GROUP",
//                "srcfields":["name", "brand"],
//            "delimeter": "-",
//                "srckeytype": "String",
//                "tgtkey": ""
//        }
//    ]
//    }

}
