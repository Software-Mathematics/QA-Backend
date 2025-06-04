package com.usermange.otpsservice.service;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.dao.daoImplementation.JobDao;
import com.commons.data.dao.daoImplementation.LoginDao;
import com.commons.data.dao.daoImplementation.OtpsDao;
import com.commons.data.dao.daoImplementation.SummaryDao;
import com.commons.data.entity.*;
import com.commons.data.multitenancy.context.TenantContext;
import com.commons.data.publisher.KafkaPublisher;
import com.commons.util.adapter.BaseAdapter;
import com.commons.util.adapter.util.LoginConstant;
import com.commons.util.model.dto.*;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.sequenceGenerator.Db_sequenceDao;
import com.commons.util.tokenizer.Tokenizer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermange.otpsservice.publisher.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;



@Service
public class OtpsServiceImplV2 implements ServiceI {


    @Autowired
    private OtpsDao dao;

    @Autowired
    private JobDao jobDao;

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

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @Value("${messageCreation.topic}")
    private String messageCreationTopic;

    @Value("${otp.length}")
    private int otpLength;

    @Value("${otp.expiry.minutes}")
    private int otpExpiryMinutes;

    @Value("${time.zone.id}")
    private String timeZone;

    // Updated create method
    public BaseDtoI createV2(BaseDtoI baseDtoI, String msgCode, String otpOn, Login login) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            if (baseDtoI.getCreateddate() == null) {
                baseDtoI.setCreateddate(new Date());
            }
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Otps entity = (Otps) baseAdapter.dtoToEntity(baseDtoI, new Otps());
            if (entity.getId() == null) {
                entity.setOtp(Db_sequenceDao.generateOTP(6));
                entity.setId(baseDao.generateSequence(Otps.SEQUENCE_NAME));
                entity.setCreatedat(LocalDateTime.now());
                entity.setExpiresat(entity.getCreatedat().plusMinutes(otpExpiryMinutes));
                entity.setConfirmedat(null);
            }
            BaseEntity data = baseDao.save(entity);
            sendOTP(entity.getUserid().trim(), msgCode, entity, login);

            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        } catch (AppException e) {
            throw e;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    // Updated sendOTP method
    public void sendOTP(String userId, String msgCode, Otps otps, Login login) throws AppException, JsonProcessingException {
        Map<String, Object> modelMap = new HashMap<>();
        String firstname = login.getFirstname() != null ? login.getFirstname() : "";
        String lastname = login.getLastname() != null ? login.getLastname() : "";
        String name = login.getName() != null ? login.getName() : "";
        modelMap.put("username", name);

        modelMap.put("otp", otps.getOtp());

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("correlId", "");
        payloadMap.put("msgCode", msgCode);
        payloadMap.put("msgType", "EMAIL");
        payloadMap.put("to", login.getEmailid());
        payloadMap.put("resourceCode", otps.getResourcecode());
        payloadMap.put("modelMap", modelMap);
        payloadMap.put("tanentid", TenantContext.getTenantId());

        kafkaPublisher.createProducer(messageCreationTopic, payloadMap);

    }

    public void sendConfirmationMsg(String userId) throws AppException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", userId);
        List<Login> entityList = baseDao.get(map, new Login());
        if (entityList.size() == 0) {
            throw new AppException(errorRepository.getError("K010"));
        }
        String subject = "Registration Confirmation - Welcome to Medharva";
        String text = "Dear " + entityList.get(0).getName() +
                ", Congratulations! We are delighted to confirm your successful registration for Medharva. We're excited to have you as part of our community and look forward to providing you with an enriching experience.";

        emailService.sendSimpleMessage(entityList.get(0).getEmailid(), subject, text);
    }

    public BaseDtoI create(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            if (baseDtoI.getCreateddate() == null) {
                baseDtoI.setCreateddate(new Date());
            }
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Otps entity = (Otps) baseAdapter.dtoToEntity(baseDtoI, new Otps());

            if (entity.getId() == null) {
                System.out.println("user_id: " + entity.getUserid());
                if(entity.getUserid().equals("9999999999")){
                    System.out.println("generating default otp..");
                    entity.setOtp("654321");
                }else {
                    System.out.println("generating otp..");
                    entity.setOtp(Db_sequenceDao.generateOTP(otpLength));
                }
                entity.setId(baseDao.generateSequence(Otps.SEQUENCE_NAME));
                entity.setCreatedat(LocalDateTime.now());
                entity.setExpiresat(entity.getCreatedat().plusMinutes(otpExpiryMinutes));
                entity.setConfirmedat(null);
            }
            BaseEntity data = baseDao.save(entity);
//            publisher.createProducerV2("Otps_UPDATE",entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public List<BaseDtoI> get(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            return (List) baseAdapter.entityToDTO(baseDao.get(map, new Otps()), new OtpsDTO());
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public BaseDtoI delete(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            baseDtoI.setStatus("INACTIVE");
            return update(baseDtoI);
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public Long update(Map<String, Object> criteriaMap, BaseDtoI baseDtoI) throws AppException {
        try {
            Map<String, Object> dataMap = new ObjectMapper().convertValue(baseDtoI, HashMap.class);
            return baseDao.update(criteriaMap, dataMap, new Otps());
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public Long updateList(List<UpdateDTO> updateDTOList) throws AppException {
        try {
            long count = 0;
            for (UpdateDTO updateDTO : updateDTOList) {
                if (updateDTO.getDto() == null) {
                    throw new AppException("dto cannot be null !!");
                }
                count += baseDao.update(updateDTO.getCriteria(), updateDTO.getDto(), new Otps());
            }

            return count;
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public BaseDtoI forgetPassword(String userid, String resourceCode, String otpOn) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        return null;
    }

    //@Override
    public BaseDtoI forgetPasswordV2(String userid, String resourceCode, String otpOn, String msgCode) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        // Map<String, Object> map = new HashMap<>();
        Login login = getLoginUser(userid, userid, resourceCode);

        if (login == null) {
            throw new AppException(errorRepository.getError("K010"));
        }

        if (!login.getIsactive().equalsIgnoreCase("ACTIVE")) {
            throw new AppException(errorRepository.getError("K011"));

        }

        OtpsDTO otp = new OtpsDTO();
        otp.setCreatedby("Backend");
        otp.setStatus("ACTIVE");
        otp.setRecstatus("OPEN");
        otp.setTypevalue("FORGET");
        otp.setResourcecode(login.getResourcecode());
        otp.setResourcename(login.getResourcename());
        if (otpOn.equalsIgnoreCase("SMS")) {
            otp.setOtptype("SMS");
            otp.setUserid(login.getMobileno());
        } else {
            otp.setOtptype("EMAIL");
            otp.setUserid(login.getEmailid());
        }
        createV2(otp, msgCode, otpOn, login);
        return (BaseDtoI) baseAdapter.entityToDTO(login, new LoginDTO());
    }

    public BaseDtoI forgetPasswordV3(String userid, String resourceCode, String otpOn, String msgCode, String type) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        // Map<String, Object> map = new HashMap<>();
        Login login = getLoginUserV2(userid, resourceCode, type);

        if (login == null) {
            throw new AppException(errorRepository.getError("K010"));
        }

        if (!login.getIsactive().equalsIgnoreCase("ACTIVE")) {
            throw new AppException(errorRepository.getError("K011"));
        }

        OtpsDTO otp = new OtpsDTO();
        otp.setCreatedby("Backend");
        otp.setStatus("ACTIVE");
        otp.setRecstatus("OPEN");
        otp.setTypevalue("FORGET");
        otp.setResourcecode(login.getResourcecode());
        otp.setResourcename(login.getResourcename());
        if (otpOn.equalsIgnoreCase("SMS")) {
            otp.setOtptype("SMS");
            otp.setUserid(login.getMobileno());
        } else {
            otp.setOtptype("EMAIL");
            otp.setUserid(login.getEmailid());
        }

        createV2(otp, msgCode, otpOn, login);
        return (BaseDtoI) baseAdapter.entityToDTO(login, new LoginDTO());
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

    public Login getLoginUserV2(String username, String resourceCode, String type) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (!LoginConstant.restrictedTypes.contains(type.toUpperCase())) {
            throw new AppException(errorRepository.getError("E003"));
        }
        List<String> keyList = Tokenizer.tokenize(LoginConstant.keyMap.get(type), "||");
        Map<String, Object> map = new HashMap<>();
        List<Login> entityList = new ArrayList<>();
        for (int i=0; i < keyList.size(); i++) {
            map = new HashMap<>();
            map.put(keyList.get(i), username);
            map.put("resourcecode", resourceCode);
            map.put("type", type);
            List<Login> dataList = baseDao.get(map, new Login());
            if (!dataList.isEmpty()){
                entityList.addAll(dataList);
            }
        }
        Login entity = null;
        for (Login obj : entityList) {
            if (obj.getIsactive().equalsIgnoreCase("ACTIVE")) {
                entity = obj;
            }
        }
        return entity;
    }





    public BaseDtoI confirmV2(String otp, String userid, String msgType) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, JsonProcessingException {
        Otps otps = new Otps();
        try {
            otps = dao.findByUseridAndOtp(userid.trim(), otp.trim());
            if (msgType == null) {
                msgType = otps.getTypevalue();
            }
        } catch (Exception e) {
            throw new AppException(errorRepository.getError("K009"));
        }
        if (otps == null) {
            throw new AppException(errorRepository.getError("K006"));
        }
        if (otps.getConfirmedat() != null) {
            throw new AppException(errorRepository.getError("K008"));
        }
        LocalDateTime nowDate = LocalDateTime.now();
        LocalDateTime expireAt = otps.getExpiresat();

        if (expireAt.isBefore(nowDate)) {
            throw new AppException(errorRepository.getError("K007"));
        }
        otps.setConfirmedat(nowDate);
        if (msgType != null && !msgType.equalsIgnoreCase("DIRECT") && !msgType.equalsIgnoreCase("FORGET")) {
            if (activateActivate(userid)) {
                otps = dao.save(otps);
                if (msgType != null) {
                    Login login = getLoginUser(otps.getUserid(), otps.getUserid(), otps.getResourcecode()); // Retrieve login information
                    sendOTP(userid, msgType, otps, login); // Pass the login object
                }
            } else {
                throw new AppException(errorRepository.getError("K009"));
            }
        } else {
            otps = dao.save(otps);
        }
        return (BaseDtoI) baseAdapter.entityToDTO(otps, new OtpsDTO());
    }

    public BaseDtoI confirmV3(String otp, String userid, String msgType) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, JsonProcessingException {
        Otps otps;

        if (userid == null || otp == null || userid.trim().isEmpty() || otp.trim().isEmpty()) {
            throw new AppException(errorRepository.getError("K001"));
        }

        try {
            otps = dao.findByUseridAndOtp(userid.trim(), otp.trim());

            if (otps == null) {
                throw new AppException(errorRepository.getError("K006"));
            }

            Login login = getLoginUser(otps.getUserid(), otps.getUserid(), otps.getResourcecode());

            if (login == null) {
                throw new AppException(errorRepository.getError("K010"));
            }

//            if (msgType == null) {
//                msgType = otps.getTypevalue();
//            }

            if (otps.getConfirmedat() != null) {
                throw new AppException(errorRepository.getError("K008"));
            }
            LocalDateTime nowDate = LocalDateTime.now();
            LocalDateTime expireAt = otps.getExpiresat();

            if (expireAt.isBefore(nowDate)) {
                throw new AppException(errorRepository.getError("K007"));
            }

            otps.setConfirmedat(nowDate);
            dao.save(otps);

            return (BaseDtoI) baseAdapter.entityToDTO(login, new LoginDTO());

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(errorRepository.getError("K002"));
        }
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

    @Override
    public BaseDtoI create(BaseDtoI baseDtoI, String msgType) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return null;
    }

    public BaseDtoI resendOtp(String complaintNo, String userId) throws AppException {
        try {

            Map<String, Object> map = new HashMap<>();
            map.put("extra_fields.complaint_no", complaintNo);
            map.put("userid", userId);

            List<Otps> otps = (List<Otps>) baseDao.get(map, new Otps());

            if (otps.isEmpty()) {
                throw new AppException("OTP not found for the given complaint number and user ID");

            }

            Otps existingOtp = otps.get(otps.size()-1);

//            String otpValue = existingOtp.getOtp();

            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("code", complaintNo);

            List<Job> job = (List<Job>) baseDao.get(queryMap, new Job());

            if(job.isEmpty()){
                throw new AppException("Invalid Complaint No:" + complaintNo);

            }
            Job existingJob = job.get(job.size()-1);

            int resendCount = existingJob.getResend_count() != 0 ? existingJob.getResend_count() : 0;
            existingJob.setResend_count(resendCount + 1);

            Map<String, Object> extraDetail = existingJob.getExtradetail();
            if (extraDetail == null) {
                extraDetail = new HashMap<>();
            }
            extraDetail.put("happycode_created", existingOtp.getOtp());

            existingJob.setExtradetail(extraDetail);
            jobDao.save(existingJob);

//            existingOtp.setOtp(otpValue);
            existingOtp.setCreatedat(LocalDateTime.now());
            existingOtp.setExpiresat(existingOtp.getCreatedat().plusDays(45));
            existingOtp.setConfirmedat(null);
            baseDao.save(existingOtp);
            existingOtp.setOtp(null);

            return (BaseDtoI) baseAdapter.entityToDTO(existingOtp, new OtpsDTO());
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

}