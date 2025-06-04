package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "QATransaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QATransaction extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "QATransaction";
    private  String classname;
    @Id
    private Long id;
    private String transaction_id;
    private String type;
    private String value;
    private String update_date;
    private List<DataSet> data_list;
    private String report;
    private List<FeatureDataset> feature_data_list;
    private String progress;
    private String processed_datasets;
    private String total_datasets;

    public void setClassname(String classname) {
        this.classname = "QATransaction";
    }

}
