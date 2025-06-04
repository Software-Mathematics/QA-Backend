package com.QA.scenarioservice.controller;

import com.commons.util.Operation;
import com.commons.util.Status;
import com.commons.util.model.AppResponseDTO;
import com.commons.util.model.dto.ProjectScenarioAssociationDTO;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.error.AppError;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.model.responses.BaseResponse;
import com.QA.scenarioservice.service.ProjectScenarioAssociationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ProjectScenarioAssociation/v1")
public class ProjectScenarioAssociationController {

    @Autowired
    private ProjectScenarioAssociationServiceImpl service;

    @Autowired
    private ErrorRepository errorRepository;

    @PostMapping("/create")
    @ApiOperation(value = "create", response = ProjectScenarioAssociationDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> create(@RequestBody ProjectScenarioAssociationDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.create(dto));
            retVal.setData(baseResponse).setOperation(Operation.CREATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/createList")
    @ApiOperation(value = "createList", response = ProjectScenarioAssociationDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> createList(@RequestBody List<ProjectScenarioAssociationDTO> dtoList) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            List<BaseDtoI> baseDtoIList = new ArrayList<>();
            for (BaseDtoI dto : dtoList) {
                baseDtoIList.add(service.create(dto));
            }
            baseResponse.setDtoList(baseDtoIList);
            baseResponse.setListSize(baseResponse.getDtoList().size());
            retVal.setData(baseResponse).setOperation(Operation.CREATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(new AppError("HG02", e.getMessage()));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get")
    @ApiOperation(value = "get", response = ProjectScenarioAssociationDTO.class)
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

    @GetMapping("/getSingle")
    @ApiOperation(value = "getSingle", response = ProjectScenarioAssociationDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> getSingle(@RequestParam(required = false) Map<String, Object> map) throws AppException {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.get(map).get(0));
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

    @PutMapping("/update")
    @ApiOperation(value = "update", response = ProjectScenarioAssociationDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> update(@RequestBody ProjectScenarioAssociationDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.update(dto));
            retVal.setData(baseResponse).setOperation(Operation.UPDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(new AppError("HG02", e.getMessage()));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/delete")
    @ApiOperation(value = "delete", response = ProjectScenarioAssociationDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> delete(@RequestBody ProjectScenarioAssociationDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.delete(dto));
            retVal.setData(baseResponse).setOperation(Operation.DELETE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(new AppError("HG02", e.getMessage()));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

