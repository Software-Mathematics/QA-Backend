package com.usermanagement.department.service.controller;


import com.commons.util.Operation;
import com.commons.util.Status;
import com.commons.util.model.AppResponseDTO;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.dto.CommunicationRecordDTO;
import com.commons.util.model.dto.DepartmentDTO;
import com.commons.util.model.error.AppError;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.model.responses.BaseResponse;
import com.usermanagement.department.service.service.DepartmentServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = {"*","http://localhost:4200","http://portal.amwl.in"},allowedHeaders = "*")
@RequestMapping("/api/department/v2")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl service;

    @Autowired
    private ErrorRepository errorRepository;
    
    @PostMapping("/create")
    @ApiOperation(value = "create", response = DepartmentDTO.class, tags = {"topic_name_CREATE_DEPARTMENT"})
    public ResponseEntity<AppResponseDTO<BaseResponse>> create(@RequestBody DepartmentDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.create(dto));
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

    @PostMapping("/createList")
    @ApiOperation(value = "createList", response = DepartmentDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> createList(@RequestBody List<DepartmentDTO> dtoList) throws AppException {

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
    @ApiOperation(value = "get", response = DepartmentDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> get(@RequestParam(required = false) Map<String, Object> map) throws AppException {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();

        try {
            BaseResponse baseResponse = new BaseResponse();
            List<BaseDtoI> dataList = new ArrayList<>();
            if(map.get("ispageable") != null ? Boolean.valueOf((String) map.get("ispageable")) : false){
                Pageable pageable = PageRequest.of(map.get("page") != null ? Integer.valueOf((String) map.get("page")) : 0, map.get("size") != null ? Integer.valueOf((String) map.get("size")) : 10);
                Pair<List<BaseDtoI>, Page> dataPair = service.getByPage(map, pageable);
                baseResponse.setPage(dataPair.getRight());
                dataList = dataPair.getLeft();
            }else {
                dataList = service.get(map);
            }
            baseResponse.setDtoList(dataList);
            baseResponse.setListSize(baseResponse.getDtoList().size());
            retVal.setData(baseResponse).setOperation(Operation.READ).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        }
         catch (AppException e) {
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
    @ApiOperation(value = "getSingle", response = DepartmentDTO.class)
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
    @ApiOperation(value = "update", response = DepartmentDTO.class, tags = {"topic_name_CREATE_DEPARTMENT"})
    public ResponseEntity<AppResponseDTO<BaseResponse>> update(@RequestBody DepartmentDTO dto) throws AppException {

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
    @ApiOperation(value = "delete", response = DepartmentDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> delete(@RequestBody DepartmentDTO dto) throws AppException {

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
