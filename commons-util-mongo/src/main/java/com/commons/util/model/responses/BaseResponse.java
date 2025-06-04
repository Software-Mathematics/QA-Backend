package com.commons.util.model.responses;

import com.commons.util.model.dto.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.bson.Document;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

@Data
public class BaseResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BaseDtoI dto;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BaseDtoI> dtoList;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, Object> response;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Map<String, Object>> responseList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Document> documentList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<VisitAggregationDTO> visitAggregationDTOList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<PrescriptionAggregationDTO> prescriptionAggregationDTOList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<LabTestAggregation> labTestAggregationList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<DosesAggregation> dosesAggregationList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<MedRequisitionAggregation> medRequisitionAggregationList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<DosesTxnAggregation> dosesTxnAggregationList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ProjectAggregation projectAggregation;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private DashboardCount dashboardcount;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<InvestmentProfileAggregation> investmentProfileAggregation;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<InvestmentProfileAggregationV2> investmentProfileAggregationV2;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<InvestmentRedeemAggregation> investmentRedeemAggregation;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private TreeDTO tree;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ProfileAggregationDTO> profileaggregationlist;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ProfileAggregationDTO profileaggregation;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Page page;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Count count;
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer listSize;

    
}
