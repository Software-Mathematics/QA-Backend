package com.usermange.otpsservice.controller;

import com.commons.util.Operation;
import com.commons.util.Status;
import com.commons.util.model.AppResponseDTO;
import com.commons.util.model.dto.Count;
import com.commons.util.model.dto.OtpsDTO;
import com.commons.util.model.dto.UpdateDTO;
import com.commons.util.model.error.AppError;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.model.responses.BaseResponse;
//import com.usermange.otpsservice.service.OtpsServiceImpl;
import com.usermange.otpsservice.service.OtpsServiceImplV2;
import com.usermange.otpsservice.service.OtpsServiceImplV3;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Otps/v2")
public class OtpsControllerV2 {

    @Autowired
    private OtpsServiceImplV2 service;

    @Autowired
    private OtpsServiceImplV3 serviceV3;
    @Autowired
    private ErrorRepository errorRepository;

    @PutMapping("/update")
    @ApiOperation(value = "update", response = Count.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> update(@RequestParam(required = false) Map<String, Object> criteriaMap, @RequestBody OtpsDTO dto) throws AppException, IOException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setCount(new Count(service.update(criteriaMap, dto)));
            retVal.setData(baseResponse).setOperation(Operation.UPDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
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

    @PutMapping("/updateList")
    @ApiOperation(value = "updateList", response = Count.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> updateList(@RequestBody List<UpdateDTO> updateDTOList) throws AppException, IOException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setCount(new Count(service.updateList(updateDTOList)));
            retVal.setData(baseResponse).setOperation(Operation.UPDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.OK);
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

    @GetMapping("/forget/password")
    @ApiOperation(value = "forgetPassword", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> forgetPassword(@RequestParam("userid") String userid, @RequestParam("resourceCode") String resourceCode, @RequestParam("otpOn") String otpOn, @RequestParam("msgCode") String msgCode ) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.forgetPasswordV2(userid, resourceCode, otpOn, msgCode));
            retVal.setData(baseResponse).setOperation(Operation.CREATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
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

    @GetMapping("/forget/password/V2")
    @ApiOperation(value = "forgetPassword", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> forgetPasswordV2(
            @RequestParam("userid") String userid,
            @RequestParam("resourceCode") String resourceCode,
            @RequestParam("otpOn") String otpOn,
            @RequestParam("msgCode") String msgCode,
            @RequestParam("type") String type
    ) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.forgetPasswordV3(userid, resourceCode, otpOn, msgCode, type));
            retVal.setData(baseResponse).setOperation(Operation.CREATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
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

    @PostMapping("/confirm")
    @ApiOperation(value = "confirm", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> confirm(@RequestParam("otp") String otp,@RequestParam("userid") String userid , @RequestParam(required = false) String msgType) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.confirmV2(otp, userid, msgType));
            retVal.setData(baseResponse).setOperation(Operation.CREATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
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

    @PostMapping("/create")
    @ApiOperation(value = "create", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> create(@RequestBody OtpsDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.create(dto));
            retVal.setData(baseResponse).setOperation(Operation.CREATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
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


    @PostMapping("/confirmV2")
    @ApiOperation(value = "confirmV2", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> confirmV2(@RequestParam("otp") String otp,@RequestParam("userid") String userid , @RequestParam(required = false) String msgType) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.confirmV3(otp, userid, msgType));
            retVal.setData(baseResponse).setOperation(Operation.CREATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
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

    @PostMapping("/confirmV3")
    @ApiOperation(value = "confirmV3", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> confirmV3(@RequestParam("otp") String otp,@RequestParam("userid") String userid , @RequestParam(required = true) String msgType,
                                                                  @RequestHeader(value = "device-token", required = false) String deviceToken) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(serviceV3.confirmV3(otp, userid, msgType, deviceToken));
            retVal.setData(baseResponse).setOperation(Operation.CREATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<AppResponseDTO<BaseResponse>>(retVal, HttpStatus.CREATED);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE). setOperation(Operation.VALIDATE);
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

    @GetMapping("/resend")
    @ApiOperation(value = "resend", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> resendOtp(@RequestParam(required = true) String complaintNo, @RequestParam(required = true) String userId) {
        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.resendOtp(complaintNo, userId));
            retVal.setData(baseResponse).setOperation(Operation.UPDATE).setRequestStatus(Status.SUCCESS);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        } catch (AppException e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.UPDATE);
            retVal.setAceErrors(e.getErrors());
            return new ResponseEntity<>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            retVal.setRequestStatus(Status.FAILURE).setOperation(Operation.UPDATE);
            List<AppError> errors = new ArrayList<>();
            errors.add(errorRepository.getError("K003"));
            retVal.setAceErrors(errors);
            return new ResponseEntity<>(retVal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
