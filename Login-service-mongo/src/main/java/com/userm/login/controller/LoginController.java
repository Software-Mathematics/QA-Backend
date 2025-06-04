package com.userm.login.controller;

import com.commons.data.dao.dto.FilterByPage;
import com.commons.util.Operation;
import com.commons.util.Status;
import com.commons.util.model.AppResponseDTO;
import com.commons.util.model.dto.*;
import com.commons.util.model.error.AppError;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.model.responses.BaseResponse;
import com.userm.login.service.LoginServiceImpl;
import com.userm.login.service.LoginServiceImplV2;
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
@RequestMapping("/api/loginservice/v2")
public class LoginController {


    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private LoginServiceImpl service;

    @Autowired
    private LoginServiceImplV2 serviceV2;

    @PostMapping("/create")
    @ApiOperation(value = "create", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> create(@RequestBody LoginDTO dto) throws AppException {

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
            e.printStackTrace();
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/createV2")
    @ApiOperation(value = "createV2", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> createV2(@RequestBody LoginDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(serviceV2.create(dto));
            retVal.setData(baseResponse).setOperation(Operation.CREATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.CREATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createList")
    @ApiOperation(value = "createList", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> createList(@RequestBody List<LoginDTO> dtoList) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            List<BaseDtoI> baseDtoIList = new ArrayList<>();
            for (BaseDtoI dto : dtoList) {
                baseDtoIList.add(service.create(dto));
            }

            baseResponse.setDtoList(baseDtoIList);
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



    @GetMapping("/get")
    @ApiOperation(value = "get", response = LoginDTO.class)
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
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByPinCode")
    @ApiOperation(value = "getByPinCode", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> getByPinCode(@RequestParam(required = false) Map<String, Object> map) throws AppException {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            List<BaseDtoI> dataList = new ArrayList<>();
            if(map.get("ispageable") != null ? Boolean.valueOf((String) map.get("ispageable")) : false){
                Pageable pageable = PageRequest.of(map.get("page") != null ? Integer.valueOf((String) map.get("page")) : 0, map.get("size") != null ? Integer.valueOf((String) map.get("size")) : 10);
                Pair<List<BaseDtoI>, Page> dataPair = service.getPageByPinCode(map, pageable);
                baseResponse.setPage(dataPair.getRight());
                dataList = dataPair.getLeft();
            }else {
                dataList = service.get(map);
            }
            baseResponse.setDtoList(dataList);
            baseResponse.setListSize(baseResponse.getDtoList().size());
            retVal.setData(baseResponse).setOperation(Operation.READ).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            retVal.setAceErrors(e.getErrors());
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
    @ApiOperation(value = "getSingle", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> getSingle(@RequestParam(required = false) Map<String, Object> map) throws AppException {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.get(map).get(0));
            retVal.setData(baseResponse).setOperation(Operation.READ).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.READ);
            retVal.setAceErrors(e.getErrors());
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
    @ApiOperation(value = "update", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> update(@RequestBody LoginDTO dto) throws AppException {

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

    @PutMapping("/updateV2")
    @ApiOperation(value = "updateV2", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> updateV2(@RequestBody LoginDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.updateV2(dto));
            retVal.setData(baseResponse).setOperation(Operation.UPDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.UPDATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(new AppError("HG02", e.getMessage()));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.UPDATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateV3")
    @ApiOperation(value = "updateV3", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> updateV3(@RequestBody LoginDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(serviceV2.update(dto));
            retVal.setData(baseResponse).setOperation(Operation.UPDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.UPDATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(new AppError("HG02", e.getMessage()));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.UPDATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/delete")
    @ApiOperation(value = "delete", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> delete(@RequestBody LoginDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.delete(dto));
            retVal.setData(baseResponse).setOperation(Operation.DELETE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.DELETE);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.DELETE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K002"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AppResponseDTO<BaseResponse>> login(@RequestBody LoginDTO dto,
                                                              @RequestHeader(value = "device-token", required = false) String deviceToken){
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.login(dto,deviceToken));
            retVal.setData(baseResponse).setOperation(Operation.VALIDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        }catch (AppException e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            List<AppError> appErrors = new ArrayList<>();
            appErrors.add(errorRepository.getError("K015"));
            retVal.setAceErrors(appErrors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/loginV2")
    public ResponseEntity<AppResponseDTO<BaseResponse>> loginV2(@RequestBody LoginDTO dto){
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(serviceV2.login(dto));
            retVal.setData(baseResponse).setOperation(Operation.VALIDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        }catch (AppException e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            List<AppError> appErrors = new ArrayList<>();
            appErrors.add(errorRepository.getError("K015"));
            retVal.setAceErrors(appErrors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/setPassword")
    public ResponseEntity<AppResponseDTO<BaseResponse>> setNewPassword(@RequestBody ChangePasswordDTO dto){
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.setPassword(dto));
            retVal.setData(baseResponse).setOperation(Operation.VALIDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        }catch (AppException e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            List<AppError> appErrors = new ArrayList<>();
            appErrors.add(errorRepository.getError("L001"));
            retVal.setAceErrors(appErrors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/ForgetPassword")
    public ResponseEntity<AppResponseDTO<BaseResponse>> ForgetPassword(@RequestBody ForgetPasswordDTO fdto){
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.ForgetPassword(fdto));
            retVal.setData(baseResponse).setOperation(Operation.VALIDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        }catch (AppException e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            List<AppError> appErrors = new ArrayList<>();
            appErrors.add(errorRepository.getError("K015"));
            retVal.setAceErrors(appErrors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ForgetPasswordV2")
    public ResponseEntity<AppResponseDTO<BaseResponse>> ForgetPasswordV2(@RequestBody ForgetPasswordDTO fdto){
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.ForgetPasswordV2(fdto));
            retVal.setData(baseResponse).setOperation(Operation.VALIDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
        }catch (AppException e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
            List<AppError> appErrors = new ArrayList<>();
            appErrors.add(errorRepository.getError("K015"));
            retVal.setAceErrors(appErrors);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getByPage")
    @ApiOperation(value = "getByPage", response = LoginDTO.class)
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

    @GetMapping("/getByPageV2")
    @ApiOperation(value = "get", response = LoginDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> getByMappingcode(@RequestParam(required = false) Map<String, Object> map) throws AppException {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            List<BaseDtoI> dataList = new ArrayList<>();
            if (map.get("ispageable") != null ? Boolean.valueOf((String) map.get("ispageable")) : false) {
                Pageable pageable = PageRequest.of(map.get("page") != null ? Integer.valueOf((String) map.get("page")) : 0, map.get("size") != null ? Integer.valueOf((String) map.get("size")) : 10);
                Pair<List<BaseDtoI>, Page> dataPair = service.getByMappingcodeByPage2(map, pageable);
                baseResponse.setPage(dataPair.getRight());
                dataList = dataPair.getLeft();
            } else {
                dataList = service.getByMappingcode(map);
            }
            baseResponse.setDtoList(dataList);
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
