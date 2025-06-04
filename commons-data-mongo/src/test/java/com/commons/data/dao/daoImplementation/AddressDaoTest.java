//package com.commons.data.dao.daoImplementation;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringEscapeUtils;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//class AddressDaoTest {
//    @Autowired
//    private Db_sequenceDao db_sequenceDao;
//    @Autowired
//    private AddressDao dao;
//    @Autowired
//    private CommunicationRecordDao communicationRecordDao;
//
////    @Test
////    public void saveAddress(){
////        AuditTrails auditTrails = AuditTrails.builder()
////                .audit_id(db_sequenceDao.getSequenceNumber())
////                .build();
////        Address address = Address.builder()
////                .id(db_sequenceDao.getSequenceNumber())
////                .auditTrails(auditTrails)
////                .build();
////        dao.save(address);
////    }
////    @Test
////    public void getAll(){
////        List<Address> addressList=dao.getAll();
////        System.out.println(addressList);
////    }
////    @Test
////    public void getByProfileId(){
////
////        System.out.println(" email : - " + communicationRecordDao.getByProfileIdAndType("12", "email"));
////    }
////
////    @Test
////    public  void getById(){
////        Address address =dao.getById("4");
////        System.out.println(address);
////    }
////    @Test
////    public void getByProfileId(){
////        List<Address> addressList=dao.getByProfileId("4");
////        System.out.println(addressList);
////    }
////    @Test
////    public void getByDefaultAddress(){
////        List<Address>addressList=dao.getByDefaultAddress(true);
////        System.out.println(addressList);
////    }
////    @Test
////    public void getByPriority() {
////        List<Address> addressList = dao.getByPriority();
////        System.out.println(addressList);
////    }
//
//    @Test
//    public void jsonToMap() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String json = "{\"phonetype\":\"N95\",\"cat\":\"WP\"}";
//
//        Map<String, Object> map = new HashMap<String, Object>();
//// convert JSON string to Map
//        map = mapper.readValue(json, Map.class);
//        map.get("phonetype");
//        System.out.println(map);
//
//    }
//
//}