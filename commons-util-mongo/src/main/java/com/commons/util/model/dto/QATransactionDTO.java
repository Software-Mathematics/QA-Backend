package com.commons.util.model.dto;

import com.commons.data.entity.DataSet;
import com.commons.data.entity.FeatureDataset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QATransactionDTO extends BaseDtoI {

    //    private Long id;
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

    private String classname;

}