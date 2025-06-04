package com.userm.login.service;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.dao.commonDao.BaseDaoImplV2;
import com.commons.data.dao.daoImplementation.ForgetPasswordDao;
import com.commons.data.dao.daoImplementation.LoginDao;
import com.commons.data.dao.daoImplementation.SummaryDao;
import com.commons.data.dao.dto.FilterByPage;
import com.commons.data.entity.*;

import com.commons.data.multitenancy.context.TenantContext;
import com.commons.data.multitenancy.filter.TenantFilter;
import com.commons.util.PasswordEncoder;
import com.commons.util.adapter.BaseAdapter;
import com.commons.util.adapter.util.LoginConstant;
import com.commons.util.model.dto.*;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;

import com.commons.util.tokenizer.Tokenizer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userm.login.publisher.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.json.XMLTokener.entity;

@Service
public class LoginServiceImpl implements ServiceI {

    public static Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private BaseAdapter baseAdapter;

    @Autowired
    private BaseDaoImplV2 baseDaoV2;

    @Autowired
    private BaseDaoImpl baseDao;

    @Autowired
    private LoginDao dao;

    @Autowired
    private ForgetPasswordDao fdao;

    @Autowired
    private SummaryDao summaryDao;

    @Autowired
    LoginServiceImpl service;

    @Value("${status.synced}")
    private String syncedStatus;

    @Value("${status.not-synced}")
    private String notSyncedStatus;
    @Value("${fields.search.index}")
    private List<String> fields;

    @Autowired
    private Publisher publisher;


    @Override
    public BaseDtoI create(BaseDtoI baseDtoI) throws Exception {
        if (baseDtoI.getCreateddate() == null) {
            baseDtoI.setCreateddate(new Date());
        }
        baseDtoI.setSyncstatus(notSyncedStatus);
        baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
        Login entity = (Login) baseAdapter.dtoToEntity(baseDtoI, new Login());
        if ((entity.getPassword() != null) && entity.getId() == null) {
            entity.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(entity.getPassword()));
        }
        if (entity.getEmailid() != null) {
            entity.setEmailid(entity.getEmailid().trim());
        }
        if (entity.getMobileno() != null) {
            entity.setMobileno(entity.getMobileno().trim());
        } else {
            entity.setMobileno("");
        }
        if (entity.getResourcecode() != null) {
            entity.setResourcecode(entity.getResourcecode().trim());
        }

        if (entity.getId() == null) {
//            if (entity.getRolecode().equalsIgnoreCase("RE001")){
//                entity.setIsactive("ACTIVE");
//            }else {
//                entity.setIsactive("INACTIVE");
//            }
            entity.setCode(baseDao.generateUUID());
            entity.setProfileid(entity.getCode());
            entity.setId(baseDao.generateSequence(Login.SEQUENCE_NAME));
            entity.setIsfamilymember(entity.getIsfamilymember() == null ? "false" : entity.getIsfamilymember());
            if ((entity.getMobileno() != null || entity.getEmailid() != null) && entity.getResourcecode() != null) {
                if (!entity.getIsfamilymember().equalsIgnoreCase("true") && isUsernameExist(entity.getEmailid(), entity.getMobileno(), entity.getResourcecode(), entity.getRolecode())) {
                    throw new AppException(errorRepository.getError("K008"));
                }
            } else {
                throw new AppException(errorRepository.getError("K009"));
            }
        }

        Map<String, String> roleTypeMap = new HashMap<>();
        roleTypeMap.put("PH257", "pharmacy");
        roleTypeMap.put("LB001", "lab");
        roleTypeMap.put("CL139", "Clinic");
        roleTypeMap.put("DO001", "doctor");

        boolean isHealthcareRole = entity.getRolecode() != null &&
                roleTypeMap.containsKey(entity.getRolecode().toUpperCase());

        if ("HEALTHCARE".equalsIgnoreCase(entity.getResourcecode())) {
            if (isHealthcareRole) {
                Summary summary = summaryDao.findByProfileid(entity.getProfileid());

                switch (entity.getIsactive().toUpperCase()) {
                    case "ACTIVE":
                        if (summary != null) {
                            summary.setStatus("ACTIVE");
                            summaryDao.save(summary);
                        }
                        break;

                    case "OPEN":
                        SummaryDTO summaryDTO = new SummaryDTO();
                        summaryDTO.setName(entity.getName());
                        summaryDTO.setAge(entity.getAge());
                        summaryDTO.setExperience(entity.getExperience());
                        summaryDTO.setProfileid(entity.getCode());
                        summaryDTO.setStatus("INACTIVE");
                        summaryDTO.setType(roleTypeMap.getOrDefault(entity.getRolecode().toUpperCase(), "doctor"));
                        createSummary(summaryDTO);
                        entity.setIsactive("INACTIVE");
                        break;
                    default:
                        if (summary != null) {
                            summary.setStatus("INACTIVE");
                            summaryDao.save(summary);
                        }
                        break;
                }
            } else if (!"AD001".equalsIgnoreCase(entity.getCreatedby()) &&
                    !"RE001".equalsIgnoreCase(entity.getRolecode()) &&
                    "PA001".equalsIgnoreCase(entity.getRolecode())) {
                pushKafkaToCreateOTP(entity);
            }
        }
        if (!"HEALTHCARE".equalsIgnoreCase(entity.getResourcecode())) {
            pushKafkaToCreateOTP(entity);
        }
        BaseEntity data = baseDao.save(entity);
        return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
    }

    public BaseDtoI createSummary(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            if (baseDtoI.getCreateddate() == null) {
                baseDtoI.setCreateddate(new Date());
            }
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Summary entity = (Summary) baseAdapter.dtoToEntity(baseDtoI, new Summary());
            if (entity.getId() == null) {
                entity.setId(baseDao.generateSequence(Summary.SEQUENCE_NAME));
            }
            BaseEntity data = baseDao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public BaseDtoI createKafka(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            if (baseDtoI.getCreateddate() == null) {
                baseDtoI.setCreateddate(new Date());
            }
            if (baseDtoI.getId() == null) {
                baseDtoI.setId(baseDao.generateSequence(Login.SEQUENCE_NAME));
            }
            baseDtoI.setSyncstatus(syncedStatus);
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Login entity = (Login) baseAdapter.dtoToEntity(baseDtoI, new Login());
            BaseEntity data = baseDao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public List<BaseDtoI> get(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            return (List) baseAdapter.entityToDTO(baseDao.get(map, new Login()), new LoginDTO());
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    public org.apache.commons.lang3.tuple.Pair<List<BaseDtoI>, Page> getByPage(Map<String, Object> map, Pageable pageable) throws AppException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        try {
            map.remove("ispageable");
            map.remove("page");
            map.remove("size");
            String searchText = map.get("st") != null ? (String) map.get("st") : null;
            map.remove("st");
            List<Login> response = new ArrayList<>();
            Page page = baseDao.getAllV2(
                    map
                    , searchText
                    , fields
                    , new Login()
                    , pageable
            );
            response.addAll(page.getContent());
            return org.apache.commons.lang3.tuple.Pair.of((List<BaseDtoI>) baseAdapter.entityToDTO(response, new LoginDTO()), page);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }


    public org.apache.commons.lang3.tuple.Pair<List<BaseDtoI>, Page> getPageByPinCode(Map<String, Object> map, Pageable pageable) throws AppException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        try {
            map.remove("ispageable");
            map.remove("page");
            map.remove("size");
            String searchText = map.get("st") != null ? (String) map.get("st") : null;
            map.remove("st");
            List<Login> response = new ArrayList<>();
            String pinCodes = map.get("pincode.village.shortname") != null ? (String) map.get("pincode.village.shortname") : "";
            StringTokenizer st = new StringTokenizer(pinCodes, "~");
            List<String> pinCodeList = new ArrayList<>();
            while (st.hasMoreTokens()) {
                pinCodeList.add(st.nextToken());
            }
            if (pinCodeList.size() > 0) {
                map.put("pincode.village.shortname", pinCodeList);
            }
            List<Address> addressList = baseDao.getV2(map, new Address());
            List<String> profileIdList = new ArrayList<>();
            for (Address address : addressList) {
                profileIdList.add(address.getProfileid());
            }
            map = new HashMap<>();
            map.put("profileid", profileIdList);
            Page page = baseDao.getAll(
                    map
                    , searchText
                    , fields
                    , new Login()
                    , pageable
            );
            response.addAll(page.getContent());
            return org.apache.commons.lang3.tuple.Pair.of((List<BaseDtoI>) baseAdapter.entityToDTO(response, new LoginDTO()), page);
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
            return create(baseDtoI);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    public BaseDtoI updateV2(BaseDtoI baseDtoI) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            if (baseDtoI.getId() == null) {
                throw new AppException(errorRepository.getError("K005"));
            } else {
                baseDtoI.setModifieddate(new Date());
            }
            if (baseDtoI.getRecstatus().equalsIgnoreCase("PRIMARY")) {
                Login entity = (Login) baseAdapter.dtoToEntity(baseDtoI, new Login());
                Map<String, Object> map = new HashMap<>();
                map.put("profileid", entity.getProfileid());
                List<BaseDtoI> baseDtoIList = get(map);
                for (BaseDtoI dto : baseDtoIList) {
                    dto.setStatus("ACTIVE");
                    create(dto);
                }

            }
            return create(baseDtoI);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public BaseDtoI updateKafka(BaseDtoI baseDtoI) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            if (baseDtoI.getId() == null) {
                throw new AppException(errorRepository.getError("K005"));
            } else {
                baseDtoI.setModifieddate(new Date());
            }
            return createKafka(baseDtoI);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    public BaseDtoI login(LoginDTO dto, String deviceToken) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (dto.getUsername() != null && dto.getResourcecode() != null && dto.getPassword() != null) {
            String username = dto.getUsername().trim();
            String resourceCode = dto.getResourcecode().trim();
            String password = dto.getPassword().trim();
            List<Login> entityList = dao.findByEmailidIgnoreCaseOrMobilenoIgnoreCaseOrUsernameIgnoreCase(
                    username,
                    username,
                    username
            );

            Login entity = null;
            for (Login obj : entityList) {
                if (obj.getIsactive().equalsIgnoreCase("ACTIVE")) {
                    entity = obj;
                } else {
                    throw new AppException(errorRepository.getError("E001"));
                }
            }
            if (entity == null) {
                throw new AppException(errorRepository.getError("K011"));
            }

            if (!entity.getResourcecode().equalsIgnoreCase(resourceCode)) {
                throw new AppException(errorRepository.getError("K012"));
            }

            if (passwordEncoder.bCryptPasswordEncoder().matches(password, entity.getPassword())) {
                // if (entity.getLogincount() == null || entity.getLogincount().equals("")) {
                //     entity.setLogincount("1");
                // } else {
                //     if (entity.getName() != null) {
                //         entity.setLogincount(Integer.toString(Integer.parseInt(entity.getLogincount()) + 1));
                //     }
                // }
                // baseDao.save(entity);

                // Store device-token if available
                if (deviceToken != null && !deviceToken.trim().isEmpty()) {
                    System.out.println(deviceToken);
                    entity.setDevicetoken(deviceToken);
                    baseDao.save(entity);
                }
                System.out.println(deviceToken);

                return (BaseDtoI) baseAdapter.entityToDTO(entity, new LoginDTO());
            } else {
                throw new AppException(errorRepository.getError("K007"));
            }

        } else {
            throw new AppException(errorRepository.getError("K010"));
        }


    }

    public BaseDtoI setPassword(ChangePasswordDTO dto) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (
                dto.getUsername() != null &&
                        dto.getResourcecode() != null &&
                        dto.getNewpassword() != null &&
                        dto.getOldpassword() != null
        ) {
            String username = dto.getUsername().trim();
            String resourceCode = dto.getResourcecode().trim();
            String oldPassword = dto.getOldpassword().trim();
            String newPassword = dto.getNewpassword().trim();
            List<Login> entityList = dao.findByEmailidIgnoreCaseOrMobilenoIgnoreCase(
                    username,
                    username
            );

            Login entity = null;
            for (Login obj : entityList) {
                if (obj.getIsactive().equalsIgnoreCase("ACTIVE")) {
                    entity = obj;
                }
            }
            if (entity == null) {
                throw new AppException(errorRepository.getError("K011"));
            }

            if (!entity.getResourcecode().equalsIgnoreCase(resourceCode)) {
                throw new AppException(errorRepository.getError("K012"));
            }
            if (!passwordEncoder.bCryptPasswordEncoder().matches(oldPassword, entity.getPassword())) {
                throw new AppException(errorRepository.getError("K007"));
            }
            entity.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(newPassword));
            entity = dao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(entity, new LoginDTO());

        } else {
            throw new AppException(errorRepository.getError("K014"));
        }

    }

    public boolean isUsernameExist(String email, String mobile, String resourceCode, String roleCode) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        boolean res = false;
        List<Login> entityList = (roleCode != null && roleCode.equalsIgnoreCase("PA001")) ? dao.findByEmailid(email) : dao.findByEmailidIgnoreCaseOrMobilenoIgnoreCaseAndResourcecodeIgnoreCase(email, mobile, resourceCode);
        for (Login login : entityList) {
            if (login.getIsactive() != null && login.getIsactive().equalsIgnoreCase("ACTIVE")) {
                res = true;
            }
        }
        return res;
    }

    public BaseDtoI ForgetPassword(ForgetPasswordDTO fdto) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (
                fdto.getUsername() != null &&
                        fdto.getResourcecode() != null &&
                        fdto.getNewpassword() != null
        ) {
            String username = fdto.getUsername().trim();
            String resourceCode = fdto.getResourcecode().trim();
            String newPassword = fdto.getNewpassword().trim();
            List<Login> entityList = dao.findByEmailidIgnoreCaseOrMobilenoIgnoreCase(
                    username,
                    username
            );

            Login entity = null;
            for (Login obj : entityList) {
                if (obj.getIsactive().equalsIgnoreCase("ACTIVE")) {
                    entity = obj;
                }
            }
            if (entity == null) {
                throw new AppException(errorRepository.getError("K011"));
            }

            if (!entity.getResourcecode().equalsIgnoreCase(resourceCode)) {
                throw new AppException(errorRepository.getError("K012"));
            }
            entity.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(newPassword));
            entity = dao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(entity, new LoginDTO());

        } else {
            throw new AppException(errorRepository.getError("K014"));
        }
    }

    public BaseDtoI ForgetPasswordV2(ForgetPasswordDTO fdto) throws AppException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        if (
                fdto.getUsername() != null &&
                        fdto.getResourcecode() != null &&
                        fdto.getNewpassword() != null &&
                        fdto.getType() != null
        ) {
            String username = fdto.getUsername().trim();
            String resourceCode = fdto.getResourcecode().trim();
            String newPassword = fdto.getNewpassword().trim();
            String type = fdto.getType().trim();

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
            if (entity == null) {
                throw new AppException(errorRepository.getError("K011"));
            }
            if (!entity.getResourcecode().equalsIgnoreCase(resourceCode)) {
                throw new AppException(errorRepository.getError("K012"));
            }
            entity.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(newPassword));
            entity = dao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(entity, new LoginDTO());

        } else {
            throw new AppException(errorRepository.getError("K014"));
        }
    }




    public void pushKafkaToCreateOTP(Login login) throws Exception {
        if (login.getResourcecode() == null) {
            throw new AppException(errorRepository.getError("K016"));
        }
        OtpsDTO otp = new OtpsDTO();
        otp.setCreatedby("Backend");
        otp.setStatus("ACTIVE");
        otp.setRecstatus("OPEN");
        otp.setUserid(login.getProfileid());
        otp.setOtptype("SMS");
        otp.setTypevalue("CREATE");
        otp.setResourcecode(login.getResourcecode());
        otp.setResourcename(login.getResourcename());
        Map<String, Object> map = new HashMap<>();
        map.put("resourcecode", login.getResourcecode());
        map.put("type", "TOPIC");
        map.put("subtype", "OTP");
        List<Resource> resources = baseDao.get(map, new Resource());
        if (resources.size() > 0) {
            try {
                Resource resource = resources.get(0);
                publisher.createProducer(resource.getResourcecode() + "_" + resource.getDescription(), otp);
//                publisher.createProducer("OTP_CREATE", otp);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            System.out.println("Resources not available !!");
        }
    }

    @Override
    public Page getByPage(FilterByPage filter) throws AppException {
        try {
            if (filter != null && filter.getPolygonCoordinates() != null && !baseDaoV2.isValidPolygon(filter.getPolygonCoordinates())){
                throw new AppException("Invalid Polygon !!");
            }
            return baseDaoV2.getByPage(filter, new Login());
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    public List<BaseDtoI> getByMappingcode(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String code = (String) map.get("projectcode");
        map.remove("projectcode");
        if (code == null || code.isEmpty()) {
            throw new AppException("Code is null or empty.");
        }
        String field = null;
        String searchText = null;
        if (!map.isEmpty()) {
            field = map.keySet().stream().findFirst().orElse(null);
            searchText = (String) map.get(field);
        }
        try {
            List<Login> loginList;
            if (field != null && searchText != null) {
                loginList = dao.findByMappingcodeStartsWithField(code, field, searchText);
            } else {
                loginList = dao.findByMappingcodeStartsWith(code);
            }
            if (loginList == null || loginList.isEmpty()) {
                return (List) baseAdapter.entityToDTO(new ArrayList<>(), new LoginDTO());
            }
            return (List) baseAdapter.entityToDTO(loginList, new LoginDTO());
        } catch (Exception e) {
            throw new AppException("An error occurred while retrieving projects: " + e.getMessage(), e);
        }
    }

    public org.apache.commons.lang3.tuple.Pair<List<BaseDtoI>, Page> getByMappingcodeByPage(Map<String, Object> map, Pageable pageable) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String code = (String) map.get("projectcode");
        map.remove("projectcode");
        if (code == null || code.isEmpty()) {
            throw new AppException("Code is null or empty.");
        }
        try {
            map.remove("ispageable");
            map.remove("page");
            map.remove("size");
            String searchText = map.get("st") != null ? (String) map.get("st") : "";
            map.remove("st");
            String field = fields.size() > 0 ? fields.get(0) : null;
            Page<Login> loginPage;
            if (field != null && searchText != null) {
                loginPage = dao.findByMappingcodeStartsWithField(code, field, searchText, pageable);
            } else {
                loginPage = dao.findByMappingcodeStartsWith(code, pageable);
            }
            List<Login> loginList = loginPage.getContent();
            if (loginList == null || loginList.isEmpty()) {
                return org.apache.commons.lang3.tuple.Pair.of(new ArrayList<>(), Page.empty(pageable));
            }
            List<BaseDtoI> dtoList = (List<BaseDtoI>) baseAdapter.entityToDTO(loginList, new LoginDTO());
            return org.apache.commons.lang3.tuple.Pair.of(dtoList, loginPage);
        } catch (Exception e) {
            throw new AppException("An error occurred while retrieving projects: " + e.getMessage(), e);
        }
    }

    public org.apache.commons.lang3.tuple.Pair<List<BaseDtoI>, Page> getByMappingcodeByPage2(Map<String, Object> map, Pageable pageable) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String code = (String) map.get("mappingcode");
        if (code == null || code.isEmpty()) {
            throw new AppException("Code is null or empty.");
        }
        try {
            map.remove("ispageable");
            map.remove("page");
            map.remove("size");
            String searchText = map.get("st") != null ? (String) map.get("st") : null;
            map.remove("st");
            List<Login> response = new ArrayList<>();
            Page page = baseDao.getAllV2(
                    map
                    , searchText
                    , fields
                    , new Login()
                    , pageable
            );
            response.addAll(page.getContent());
            return org.apache.commons.lang3.tuple.Pair.of((List<BaseDtoI>) baseAdapter.entityToDTO(response, new LoginDTO()), page);
        } catch (Exception e) {
            throw new AppException("An error occurred while retrieving projects: " + e.getMessage(), e);
        }
    }

    public BaseDtoI updateV3(Map<String, Object> kafkaMessage) throws AppException {
        try {

            String tenantId = (String) kafkaMessage.get("tanentid");
            TenantContext.setTenantId(tenantId);

            String username = (String) kafkaMessage.get("username");
            String status = (String) kafkaMessage.get("status");
            String isActive = (String) kafkaMessage.get("isactive");


            Map<String, Object> map = new HashMap<>();
            map.put("username", username);

            List<Login> login = (List<Login>) baseDao.get(map, new Login());

            if (login.isEmpty()) {

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Login loginDTO = objectMapper.convertValue(kafkaMessage, Login.class);

                loginDTO.setIsactive(isActive);
                loginDTO.setStatus(status);
                loginDTO.setCreatedby("SYSTEM");
                loginDTO.setRecstatus("APPROVED");
                loginDTO.setResourcecode("JAINA");
                loginDTO.setResourcename("Jaina India");
                loginDTO.setRolecode("TE001");
                loginDTO.setRolename("Technician");
                loginDTO.setType("USERNAME");
                loginDTO.setCreateddate(new Date());
                loginDTO.setCode(baseDao.generateUUID());
                loginDTO.setProfileid(loginDTO.getCode());
                loginDTO.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(loginDTO.getPassword()));
                loginDTO.setId(baseDao.generateSequence(Login.SEQUENCE_NAME));
                return (BaseDtoI) baseAdapter.entityToDTO(baseDao.save(loginDTO), new LoginDTO());
            }

            Login existingRecord = login.get(0);

            String emailId = (String) kafkaMessage.get("emailid");
            String mobileNo = (String) kafkaMessage.get("mobileno");
            String password = (String) kafkaMessage.get("password");
            String name = (String) kafkaMessage.get("name");
            String title = (String) kafkaMessage.get("title");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//            LoginDTO loginDTO = objectMapper.convertValue(existingRecord, LoginDTO.class);

//            existingRecord.setId(existingRecord.getId());
            existingRecord.setEmailid(emailId);
            existingRecord.setMobileno(mobileNo);
            existingRecord.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(password));
            existingRecord.setName(name);
            existingRecord.setTitle(title);
            existingRecord.setIsactive(isActive);
            existingRecord.setStatus(status);
//            existingRecord.setRecstatus("APPROVED");
//            existingRecord.setResourcecode("JAINA");
//            existingRecord.setResourcename("Jaina India");
//            existingRecord.setRolecode("TE001");
//            existingRecord.setRolename("Technician");
            existingRecord.setModifiedby("SYSTEM");
            existingRecord.setModifieddate(new Date());

            return (BaseDtoI) baseAdapter.entityToDTO(baseDao.save(existingRecord), new LoginDTO());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}