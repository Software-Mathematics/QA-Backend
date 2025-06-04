package com.QA.qaintegrationservice.controller;

import com.QA.qaintegrationservice.config.CSPNonceFilter;
import com.commons.data.dao.dto.FilterByPage;
import com.commons.data.multitenancy.context.TenantContext;
import com.commons.util.Operation;
import com.commons.util.Status;
import com.commons.util.model.AppResponseDTO;
import com.commons.util.model.dto.QATransactionDTO;
import com.commons.util.model.error.AppError;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.model.responses.BaseResponse;
import com.QA.qaintegrationservice.service.QATransactionServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/QATransaction/v1")
public class QATransactionController {

    @Autowired
    private QATransactionServiceImpl service;

    @Autowired
    private ErrorRepository errorRepository;


    @GetMapping("/run/old")
    @ApiOperation(value = "run", response = QATransactionDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> run(@RequestParam(required = false) Map<String, Object> map) throws AppException {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            if (!map.containsKey("project_code") && !map.containsKey("scenario_code")) {
                retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
                List<AppError> errors = new ArrayList<>();
                errors.add(errorRepository.getError("K016"));
                retVal.setAceErrors(errors);
                return new ResponseEntity<>(retVal, HttpStatus.BAD_REQUEST);
            }

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDtoList(Collections.singletonList(service.createPayload(map)));
//            baseResponse.setDtoList(service.createPayload(map));
            baseResponse.setListSize(baseResponse.getDtoList().size());
            retVal.setData(baseResponse).setOperation(Operation.READ).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            List<AppError> errors = new ArrayList<>();
            errors.add(new AppError("HG02", e.getMessage()));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get")
    @ApiOperation(value = "get", response = QATransactionDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> get(@RequestParam(required = false) Map<String, Object> map) throws AppException {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDtoList(service.get(map));
            baseResponse.setListSize(baseResponse.getDtoList().size());
            retVal.setData(baseResponse).setOperation(Operation.READ).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            List<AppError> errors = new ArrayList<>();
            errors.add(new AppError("HG02", e.getMessage()));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getByPage")
    @ApiOperation(value = "getByPage", response = QATransactionDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> getByPolygon(@RequestBody(required = false) FilterByPage filter) throws AppException {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setPage(service.getByPage(filter));
            retVal.setData(baseResponse).setOperation(Operation.READ).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            List<AppError> errors = new ArrayList<>();
            errors.add(new AppError("HG02", e.getMessage()));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/run")
    @ApiOperation(value = "run", response = QATransactionDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> runV2(@RequestParam(required = false) Map<String, Object> map) throws AppException {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            if (!map.containsKey("project_code") && !map.containsKey("scenario_code")) {
                retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
                List<AppError> errors = new ArrayList<>();
                errors.add(errorRepository.getError("K016"));
                retVal.setAceErrors(errors);
                return new ResponseEntity<>(retVal, HttpStatus.BAD_REQUEST);
            }

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDtoList(Collections.singletonList(service.createPayloadV2(map)));
            baseResponse.setListSize(baseResponse.getDtoList().size());
            retVal.setData(baseResponse).setOperation(Operation.READ).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            List<AppError> errors = new ArrayList<>();
            errors.add(new AppError("HG02", e.getMessage()));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}