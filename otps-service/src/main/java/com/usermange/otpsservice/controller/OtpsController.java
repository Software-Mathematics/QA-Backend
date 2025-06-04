package com.usermange.otpsservice.controller;


import com.commons.util.Operation;
import com.commons.util.Status;
import com.commons.util.model.AppResponseDTO;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.dto.OtpsDTO;
import com.commons.util.model.error.AppError;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.model.responses.BaseResponse;
import com.usermange.otpsservice.service.OtpsServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Otps/v1")
public class OtpsController {

    @Autowired
    private OtpsServiceImpl service;

    @Autowired
    private ErrorRepository errorRepository;


    @PostMapping("/create")
    @ApiOperation(value = "create", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> create(@RequestBody OtpsDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.create(dto, "CREATE"));
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
            baseResponse.setDto(service.confirm(otp, userid, msgType));
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

    @GetMapping("/forget/password")
    @ApiOperation(value = "forgetPassword", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> forgetPassword(@RequestParam("userid") String userid, @RequestParam("resourceCode") String resourceCode, @RequestParam("otpOn") String otpOn) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.forgetPassword(userid, resourceCode, otpOn));
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

    @PostMapping("/createList")
    @ApiOperation(value = "createList", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> createList(@RequestBody List<OtpsDTO> dtoList) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            List<BaseDtoI> baseDtoIList = new ArrayList<>();
            for (BaseDtoI dto : dtoList) {
                baseDtoIList.add(service.create(dto, ""));
            }
            baseResponse.setDtoList(baseDtoIList);
            baseResponse.setListSize(baseResponse.getDtoList().size());
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



    @GetMapping("/get")
    @ApiOperation(value = "get", response = OtpsDTO.class)
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
    @ApiOperation(value = "getSingle", response = OtpsDTO.class)
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
    @ApiOperation(value = "update", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> update(@RequestBody OtpsDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.update(dto));
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



    @PutMapping("/delete")
    @ApiOperation(value = "delete", response = OtpsDTO.class)
    public ResponseEntity<AppResponseDTO<BaseResponse>> delete(@RequestBody OtpsDTO dto) throws AppException {

        AppResponseDTO<BaseResponse> retVal = new AppResponseDTO<BaseResponse>();
        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setDto(service.delete(dto));
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

}
