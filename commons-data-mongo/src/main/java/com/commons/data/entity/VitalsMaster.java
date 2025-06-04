package com.commons.data.entity;

        import lombok.Data;
        import org.springframework.data.annotation.Id;
        import org.springframework.data.annotation.Transient;
        import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VitalsMaster")
@Data
public class VitalsMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "VitalsMaster";
    private  String classname ;
    @Id
    private Long id;
    private String code;
    private String name;
    private String value;
    private String uom;
    private String type;
    private String priority;
    private String range;

    public void setClassname(String classname) {
        this.classname = "VitalsMaster";
    }

}
