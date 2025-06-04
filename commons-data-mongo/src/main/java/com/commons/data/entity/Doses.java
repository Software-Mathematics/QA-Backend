package com.commons.data.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


    @Document(collection = "Doses")
    @Data
    public class Doses extends BaseEntity{

        @Transient
        public static final String SEQUENCE_NAME = "Doses";
        private  String classname ;

        @Id
        private Long id;
        @JsonProperty("pdnumber")
        private String pdnumber;
        private String code;
        @JsonProperty("medicineid")
        private String medicineid;
        @JsonProperty("medicinename")
        private String medicinename;
        @JsonProperty("dosage")
        private String dosage;
        @JsonProperty("frequency")
        private String frequency;
        @JsonProperty("duration")
        private String duration;
        private String presid;
        private String mmucode;
        private String quantity;
        private String countperday;
        private String dose;
        private Doses previousdose;
        private String visitid;
        private Itembatch itembatch;
        @JsonProperty("uom")
        private String uom;

        private String form;




        public void setClassname(String className) {
            this.classname = "Doses";
        }


    }
