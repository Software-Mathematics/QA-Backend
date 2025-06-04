package com.usermange.otpsservice.service;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.dao.daoImplementation.LoginDao;
import com.commons.data.dao.daoImplementation.OtpsDao;
import com.commons.data.dao.daoImplementation.SummaryDao;
import com.commons.data.entity.*;
import com.commons.util.adapter.BaseAdapter;
import com.commons.util.model.dto.*;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.sequenceGenerator.Db_sequenceDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermange.otpsservice.publisher.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OtpsServiceImpl implements ServiceI {


    @Autowired
    private OtpsDao dao;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private BaseDaoImpl baseDao;

    @Autowired
    private BaseAdapter baseAdapter;

    @Autowired
    private ErrorRepository errorRepository;


    @Autowired
    private Publisher publisher;
    @Autowired
    private Db_sequenceDao dbseq;

    @Autowired
    private EmailService emailService;

    @Override
    public BaseDtoI create(BaseDtoI baseDtoI, String msgType) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            if (baseDtoI.getCreateddate() == null) {
                baseDtoI.setCreateddate(new Date());
            }
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Otps entity = (Otps) baseAdapter.dtoToEntity(baseDtoI, new Otps());
            if(entity.getId() == null) {
//                if (msgType.equalsIgnoreCase("FORGET")){
//                    entity.setOtp(UUID.randomUUID().toString());
//                }else {
//                    entity.setOtp(Db_sequenceDao.generateOTP(6));
//                }
                entity.setOtp(Db_sequenceDao.generateOTP(6));
                entity.setId(baseDao.generateSequence(Otps.SEQUENCE_NAME));
                entity.setCreatedat(LocalDateTime.now());
                entity.setExpiresat(entity.getCreatedat().plusMinutes(5));
                entity.setConfirmedat(null);
            }
            BaseEntity data = baseDao.save(entity);
            if (entity.getTypevalue() != null){
                msgType = entity.getTypevalue();
            }
            sendOTP(entity.getUserid().trim(), entity, msgType);
//            if (entity.getTypevalue() != null && entity.getTypevalue().equalsIgnoreCase("DIRECT")){
//                sendOTP(entity);
//            }else {
//                sendOTP(entity.getUserid().trim(), entity, msgType);
//            }
            publisher.createProducerV2("Otps_UPDATE",entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        }catch (AppException e){
            throw e;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendOTP(String userId, Otps otp, String msgType) throws AppException, JsonProcessingException {
        Map<String, Object> objectMap = new HashMap<>();
        boolean sendMail = true;

//        Map<String, Object> map = new HashMap<>();
//        map.put("code", userId);
//        List<Login> entityList = baseDao.get(map, new Login());
        if (msgType.equalsIgnoreCase("DIRECT")){
            objectMap.put("resourceCode", otp.getResourcecode());
            String mobile = "91" + otp.getUserid().trim();
            Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("otp", otp.getOtp());
            modelMap.put("resourceName", otp.getResourcename());
            modelMap.put("EXPIRY_TIME", "5");

            objectMap.put("modelMap", modelMap);
            objectMap.put("msgType", "SMS");
            objectMap.put("to", mobile);
            objectMap.put("msgCode", "DSMSOTP01");
        }
        else {
            List<Login> entityList = otp.getTypevalue() != null && otp.getTypevalue().equalsIgnoreCase("FORGET") ?
                    loginDao.findByEmailidIgnoreCaseOrMobilenoIgnoreCase(userId, userId) :
                    loginDao.findByMobilenoOrCode(userId, userId);
            if (entityList.size() == 0) {
                throw new AppException(errorRepository.getError("K010"));
            }
            String username = entityList.get(0).getName();
            String email = entityList.get(0).getEmailid();
            String mobile = "91" + entityList.get(0).getMobileno().trim();
            objectMap.put("resourceCode", otp.getResourcecode());
            if (otp.getOtptype().equalsIgnoreCase("SMS")) {
                Map<String, Object> modelMap = new HashMap<>();
                modelMap.put("username", username);
                modelMap.put("otp", otp.getOtp());
                modelMap.put("resourceName", otp.getResourcename());

                objectMap.put("msgType", "SMS");
                objectMap.put("to", mobile);
                if (msgType.equalsIgnoreCase("CREATE")) {
                    objectMap.put("msgCode", "FSMSOTP01");
                } else if (msgType.equalsIgnoreCase("FORGET")) {
                    objectMap.put("msgCode", "FSMSOTP03");
                    modelMap.put("time", "5");
                } else if (msgType.equalsIgnoreCase("CONFIRM")) {
                    objectMap.put("msgCode", "FSMSOTP02");
                } else if (msgType.equalsIgnoreCase("DIRECT")) {
                    objectMap.put("msgCode", "DSMSOTP01");
                    modelMap.put("EXPIRY_TIME", "5");
                } else {
                    sendMail = false;
                }
                objectMap.put("modelMap", modelMap);
            } else {
                Map<String, Object> modelMap = new HashMap<>();
                modelMap.put("username", username);
                modelMap.put("otp", otp.getOtp());

                objectMap.put("modelMap", modelMap);
                objectMap.put("msgType", "EMAIL");
                objectMap.put("to", email);
                if (msgType.equalsIgnoreCase("FORGET")) {
                    objectMap.put("msgCode", "OF01");
                } else if (msgType.equalsIgnoreCase("CREATE")) {
                    objectMap.put("msgCode", "ROTP01");
                } else if (msgType.equalsIgnoreCase("CONFIRM")) {
                    objectMap.put("msgCode", "CO01");
                } else {
                    sendMail = false;
                }
            }
        }

        try {
            if (sendMail) {
                Map<String, Object> map = new HashMap<>();
                map.put("resourcecode", otp.getResourcecode());
                map.put("type", "TOPIC");
                map.put("subtype", "MSG_CREATION");
                List<Resource> resources = baseDao.get(map, new Resource());
                if (resources.size() > 0) {
                    Resource resource = resources.get(0);
                    publisher.createProducer(resource.getResourcecode() + "_" + resource.getDescription(), objectMap);
//                    publisher.createProducer("MSG_CREATION_MC1", objectMap);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        String subject= "";
//        String text = "";
//        String email = "";
//        if (msgType.equalsIgnoreCase("FORGET")){
//            subject = "Reset Your Password";
//            text = "Please enter the mentioned OTP " + otp.getOtp() + " for Reset your password in to your account on Medharva.";
//            email = otp.getTypevalue();
//        }else {
//            Map<String, Object> map = new HashMap<>();
//            map.put("code", userId);
//            List<Login> entityList = baseDao.get(map, new Login());
//            if (entityList.size() == 0) {
//                throw new AppException(errorRepository.getError("K010"));
//            }
//            subject = "Your OTP to login to Medharva.";
//            text = "Please enter the mentioned OTP " + otp.getOtp() + " for logging in to your account on Medharva.";
//            email = entityList.get(0).getEmailid();
//        }
//        emailService.sendSimpleMessage(email, subject, text);

    }

    public void sendConfirmationMsg(String userId) throws AppException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", userId);
        List<Login> entityList =  baseDao.get(map, new Login());
        if (entityList.size() == 0) {
            throw new AppException(errorRepository.getError("K010"));
        }
        String subject = "Registration Confirmation - Welcome to Medharva";
        String text = "Dear " + entityList.get(0).getName() +
                ", Congratulations! We are delighted to confirm your successful registration for Medharva. We're excited to have you as part of our community and look forward to providing you with an enriching experience.";
//        String text = "<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "    <title></title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "    <div>Dear " + entityList.get(0).getName() + ",</div><br>\n" +
//                "    <div>Congratulations! We are delighted to confirm your successful registration for Medharva. We're excited to have you as part of our community and look forward to providing you with an enriching experience.</div><br>\n" +
//                "    <div>Thank you for choosing.</div>\n" +
//                "    <div>Best regards,</div><br>\n" +
//                "    <div>Medharva</div>\n" +
//                "</body>\n" +
//                "</html>";
        emailService.sendSimpleMessage(entityList.get(0).getEmailid(), subject, text);
    }

    @Override
    public List<BaseDtoI> get(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            return (List) baseAdapter.entityToDTO(baseDao.get(map, new Otps()), new OtpsDTO());
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public BaseDtoI delete(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            baseDtoI.setStatus("INACTIVE");
            return update(baseDtoI);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public BaseDtoI update(BaseDtoI baseDtoI) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            if (baseDtoI.getId() == null) {
                throw new AppException(errorRepository.getError("K005"));
            } else {
                baseDtoI.setModifieddate(new Date());
            }
            return create(baseDtoI, "");
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public Long update(Map<String, Object> criteriaMap, BaseDtoI baseDtoI) throws AppException {
        try {
            Map<String, Object> dataMap = new ObjectMapper().convertValue( baseDtoI, HashMap.class);
            return baseDao.update(criteriaMap, dataMap, new Otps());
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public Long updateList(List<UpdateDTO> updateDTOList) throws AppException {
        try {
            long count = 0;
            for(UpdateDTO updateDTO : updateDTOList){
                if(updateDTO.getDto() == null){
                    throw new AppException("dto cannot be null !!");
                }
                count += baseDao.update(updateDTO.getCriteria(), updateDTO.getDto(), new Otps());
            }

            return count;
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public BaseDtoI forgetPassword(String userid, String resourceCode, String otpOn) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException  {
        Map<String, Object> map = new HashMap<>();
        Login login = getLoginUser(userid, userid, resourceCode);
        if (login == null) {
            throw new AppException(errorRepository.getError("K010"));
        }
        OtpsDTO otp = new OtpsDTO();
        otp.setCreatedby("Backend");
        otp.setStatus("ACTIVE");
        otp.setRecstatus("OPEN");
        otp.setTypevalue("FORGET");
        otp.setResourcecode(login.getResourcecode());
        otp.setResourcename(login.getResourcename());
        if (otpOn.equalsIgnoreCase("SMS")){
            otp.setOtptype("SMS");
            otp.setUserid(login.getMobileno());
        }else {
            otp.setOtptype("EMAIL");
            otp.setUserid(login.getEmailid());
        }
        create(otp, "FORGET");
        return (BaseDtoI) baseAdapter.entityToDTO(login, new LoginDTO());
    }

    @Override
    public BaseDtoI forgetPasswordV2(String userid, String resourceCode, String otpOn, String msgCode) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        return null;
    }

    public Login getLoginUser(String email, String mobile, String resourceCode) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Login response = null;
        List<Login> entityList = loginDao.findByEmailidIgnoreCaseOrMobilenoIgnoreCaseAndResourcecodeIgnoreCase(email, mobile, resourceCode);
        for (Login login : entityList) {
            if (login.getIsactive().equalsIgnoreCase("ACTIVE")) {
                response = login;
            }
        }
        return response;
    }
    public BaseDtoI confirm(String otp, String userid, String msgType) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, JsonProcessingException {
        Otps otps = new Otps();
        try {
            otps = dao.findByUseridAndOtp(userid.trim(), otp.trim());
            if (msgType == null ) { msgType = otps.getTypevalue(); }
        }catch (Exception e){
            throw new AppException(errorRepository.getError("K009"));
        }
        if(otps == null){
            throw new AppException(errorRepository.getError("K006"));
        }
        if(otps.getConfirmedat()!=null){
            throw new AppException(errorRepository.getError("K008"));
        }
        LocalDateTime nowDate = LocalDateTime.now();
        LocalDateTime expireAt = otps.getExpiresat();
        if (expireAt.isBefore(nowDate)) {
            throw new AppException(errorRepository.getError("K007"));
        }
        otps.setConfirmedat(nowDate);
        if (
                msgType != null
                        && !msgType.equalsIgnoreCase("DIRECT")
                        && !msgType.equalsIgnoreCase("FORGET")
        ) {
            if (activateActivate(userid)) {
                otps = dao.save(otps);
                if (msgType != null) {
                    sendOTP(userid, otps, msgType);
                } else if (msgType != null && msgType.equalsIgnoreCase("CONFIRM")) {
                    sendOTP(userid, otps, "CONFIRM");
                } else {

                }

            } else {
                throw new AppException(errorRepository.getError("K009"));
            }
        }else {
            otps = dao.save(otps);
        }
        return (BaseDtoI) baseAdapter.entityToDTO(otps, new OtpsDTO());
    }


    public boolean activateActivate(String userid) throws AppException {
        boolean response = false;
        try {
//            Map<String, Object> map = new HashMap<>();
//            map.put("code", userid);
//            List<Login> entityList =  baseDao.get(map, new Login());
            List<Login> entityList = loginDao.findByMobilenoIgnoreCaseOrCodeIgnoreCase(userid, userid);
            if (entityList.size() == 0) {
                throw new AppException(errorRepository.getError("K010"));
            }
            entityList.get(0).setIsactive("ACTIVE");
            Login login = loginDao.save(entityList.get(0));
            if (login.getRolecode() != null && login.getRolecode().equalsIgnoreCase("DO001")) {
                SummaryDTO summaryDTO = new SummaryDTO();
                summaryDTO.setName(login.getName());
                summaryDTO.setAge(login.getAge());
                summaryDTO.setExperience(login.getExperience());
                summaryDTO.setProfileid(login.getCode());
                summaryDTO.setStatus("ACTIVE");
                createSummary(summaryDTO);
            }else {
                createPatient(login);
            }
            response = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public BaseDtoI createSummary(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            if (baseDtoI.getCreateddate() == null) {
                baseDtoI.setCreateddate(new Date());
            }
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Summary entity = (Summary) baseAdapter.dtoToEntity(baseDtoI, new Summary());
            if(entity.getId() == null) {
                entity.setId(baseDao.generateSequence(Summary.SEQUENCE_NAME));
            }
            BaseEntity data = baseDao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    public BaseDtoI createPatient(Login login) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            Patient entity = new Patient();
            entity.setName(login.getName());
            entity.setAge(login.getAge());
            entity.setGender(login.getSex());
            entity.setMobileno(login.getMobileno());
            entity.setCreatedby(login.getCreatedby());
            entity.setCreateddate(new Date());
            entity.setProfileid(login.getProfileid());
            entity.setStatus("ACTIVE");
            entity.setId(baseDao.generateSequence(Patient.SEQUENCE_NAME));
            entity.setPatientid(dbseq.idGenerator("PAMED", null));
            BaseEntity baseEntity = baseDao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(baseEntity, new PatientDTO());
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

}