package com.commons.data.dao.commonDao;

import com.commons.data.entity.AuditLog;
import com.commons.data.entity.BaseEntity;
import com.commons.data.entity.DatabaseSequence;
import com.commons.data.model.DataTypeForConversion;
import com.commons.data.multitenancy.config.MultiTenantMongoDbFactory;
import com.commons.data.multitenancy.context.TenantContext;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Component
public class BaseDaoImpl implements BaseDao{

    @Autowired
    private MongoTemplate mongoOperations;
    @Autowired
    private DataTypeForConversion dataTypeForConversion;

    @Override
    public BaseEntity save(BaseEntity baseEntity) {
        if (baseEntity.getTanentid() != null) {
            TenantContext.setTenantId(baseEntity.getTanentid());
        }
        final String tenantId = TenantContext.getTenantId();
        baseEntity.setTanentid(tenantId);
        return mongoOperations.save(baseEntity);
    }
    @Override
    public Map<String, Object> save(Map<String, Object> dataMap, String collectionName) {
        final String tenantId = TenantContext.getTenantId();
        dataMap.put("tanentid", tenantId);
        return mongoOperations.save(dataMap, collectionName);
    }

    public Map<String, Object> setTenantContext(Map<String, Object> searchItems) {
//        final String tenantId = TenantContext.getTenantId();
//
//
//        if (searchItems != null && searchItems.size() > 0 && searchItems.get("tanentid") != null) {
//            TenantContext.setTenantId((String) searchItems.get("tanentid"));
//            searchItems.remove("tanentid");
//        }else {
//            TenantContext.setTenantId(MultiTenantMongoDbFactory.DEFAULT_DB_INSTACE);
//        }
//        System.out.println("tenantId = " + tenantId);
        return searchItems;
    }
    @Override
    public List get(Map<String, Object> searchItems, BaseEntity baseEntity) {
        searchItems = setTenantContext(searchItems);
        searchItems = findDataType(searchItems, baseEntity);
        Query dynamicQuery = new Query();
        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria nameCriteria ;
            if(entry.getValue() instanceof String) {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
            }else {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            }
            dynamicQuery.addCriteria(nameCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        dynamicQuery.addCriteria(nameCriteria);

        return mongoOperations.find(dynamicQuery, baseEntity.getClass());

    }



    @Override
    public List getV2(Map<String, Object> searchItems, BaseEntity baseEntity) throws ClassNotFoundException {
        searchItems = setTenantContext(searchItems);
        searchItems = findDataTypeV2(searchItems, baseEntity);
        Query dynamicQuery = new Query();
        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria nameCriteria ;
            if(entry.getValue() instanceof String) {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
            }
            else if(entry.getValue() instanceof List)  {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).in((Collection<? extends String>) entry.getValue());
            }else {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            }
            dynamicQuery.addCriteria(nameCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        dynamicQuery.addCriteria(nameCriteria);

        return mongoOperations.find(dynamicQuery, baseEntity.getClass());
    }

//    @Override
//    public List<Document> getV3(Map<String, Object> searchItems, String collectionName) throws ClassNotFoundException {
//        searchItems = setTenantContext(searchItems);
//        searchItems = findDataTypeV2(searchItems, new BaseEntity());
//        Query dynamicQuery = new Query();
//        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
//            Criteria nameCriteria ;
//            if(entry.getValue() instanceof String) {
//                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
//            }
//            else if(entry.getValue() instanceof List)  {
//                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).in((Collection<? extends String>) entry.getValue());
//            }else {
//                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
//            }
//            dynamicQuery.addCriteria(nameCriteria);
//        }
//        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
//        dynamicQuery.addCriteria(nameCriteria);
//        return mongoOperations.find(dynamicQuery, Document.class, collectionName);
//
//    }

    @Override
    public List<Document> getV3(Map<String, Object> searchItems, String collectionName) throws ClassNotFoundException {
        searchItems = this.setTenantContext(searchItems);
        searchItems = this.findDataTypeV2(searchItems, new BaseEntity());
        Query dynamicQuery = new Query();

        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria criteria;
            if (entry.getValue() instanceof String) {
                criteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            } else if (entry.getValue() instanceof List) {
                criteria = Criteria.where(entry.getKey().toLowerCase()).in((Collection<?>) entry.getValue());
            } else {
                criteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            }
            dynamicQuery.addCriteria(criteria);
        }

        // Add status criteria to exclude "INACTIVE"
        dynamicQuery.addCriteria(Criteria.where("status").ne("INACTIVE"));

        // Set collation for case-insensitive matching
        Collation collation = Collation.of("en").strength(Collation.ComparisonLevel.secondary());

        // Execute query with collation
        return this.mongoOperations.find(dynamicQuery.collation(collation), Document.class, collectionName);
    }

    public List<Document> getV4(Map<String, Object> searchItems, String collectionName) throws ClassNotFoundException, ParseException {
        searchItems = setTenantContext(searchItems);
        searchItems = findDataTypeV2(searchItems, new BaseEntity());
        Query dynamicQuery = new Query();

        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Criteria nameCriteria;

            if (value instanceof Date) {
                nameCriteria = Criteria.where(key).gte(getStartDateTime((Date) value)).lte(getEndDateTime((Date) value));
            }
            else if (value instanceof String) {
                nameCriteria = Criteria.where(key).is(value);
            }else if (value instanceof List) {
                nameCriteria = Criteria.where(key).in((Collection<? extends String>) value);
            } else {
                nameCriteria = Criteria.where(key).is(value);
            }
            dynamicQuery.addCriteria(nameCriteria);
        }

        Criteria statusCriteria = Criteria.where("status").ne("INACTIVE");
        dynamicQuery.addCriteria(statusCriteria);

        return mongoOperations.find(dynamicQuery, Document.class, collectionName);
    }


    public List getV4(Map<String, Object> searchItems, BaseEntity baseEntity) throws ClassNotFoundException, ParseException {
        searchItems = setTenantContext(searchItems);
        searchItems = findDataTypeV2(searchItems, new BaseEntity());
        Query dynamicQuery = new Query();

        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Criteria nameCriteria;

            if (value instanceof String) {
                nameCriteria = Criteria.where(key).is(value);
            }else if (value instanceof Date) {
                nameCriteria = Criteria.where(key).gte(getStartDateTime((Date) value)).lte(getEndDateTime((Date) value));
            }else if (value instanceof List) {
                nameCriteria = Criteria.where(key).in((Collection<? extends String>) value);
            } else {
                nameCriteria = Criteria.where(key).is(value);
            }
            dynamicQuery.addCriteria(nameCriteria);
        }

        Criteria statusCriteria = Criteria.where("status").ne("INACTIVE");
        dynamicQuery.addCriteria(statusCriteria);
        System.out.println("dynamicQuery = " + dynamicQuery);
        return mongoOperations.find(dynamicQuery, baseEntity.getClass());
    }

    public Date getStartDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getEndDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    @Override
    public List getByGeoLoc(Map<String, Object> searchItems, Point point, Integer radius, BaseEntity baseEntity) throws ClassNotFoundException {
        searchItems = setTenantContext(searchItems);
        searchItems = findDataTypeV2(searchItems, baseEntity);
        Distance distance = new Distance(radius, Metrics.KILOMETERS);
        Circle circle = new Circle(point, distance);
        Criteria geoCriteria = Criteria.where("geolocation").withinSphere(circle);
//        Criteria geoCriteria = Criteria.where("location").near(point);
        Query dynamicQuery = new Query();
        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria nameCriteria ;
            if(entry.getValue() instanceof String) {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
            }
            else if(entry.getValue() instanceof List)  {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).in((Collection<? extends String>) entry.getValue());
            }else {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            }
            dynamicQuery.addCriteria(nameCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        dynamicQuery.addCriteria(nameCriteria);
        dynamicQuery.addCriteria(geoCriteria);

        return mongoOperations.find(dynamicQuery, baseEntity.getClass());
    }


    @Override
    public List getWithoutRegX(Map<String, Object> searchItems, BaseEntity baseEntity) {
        searchItems = setTenantContext(searchItems);
        searchItems = findDataType(searchItems, baseEntity);
        Query dynamicQuery = new Query();
        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria nameCriteria ;
//            if(entry.getValue() instanceof String) {
//                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
//            }else {
//                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
//            }
            nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            dynamicQuery.addCriteria(nameCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        dynamicQuery.addCriteria(nameCriteria);

        return mongoOperations.find(dynamicQuery, baseEntity.getClass());

    }

    @Override
    public List getBySort(Map<String, Object> searchItems, BaseEntity baseEntity) throws ClassNotFoundException {
        searchItems = setTenantContext(searchItems);
        String orderedGet = (String) searchItems.get("sortby");
        searchItems.remove("sortby");
        searchItems = findDataTypeV2(searchItems, baseEntity);
        Query dynamicQuery = new Query();
        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria nameCriteria ;
            if(entry.getValue() instanceof String) {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
            }else if(entry.getValue() instanceof List)  {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).in((Collection<? extends String>) entry.getValue());
            } else {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            }
            dynamicQuery.addCriteria(nameCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");

        if(orderedGet != null && orderedGet.equalsIgnoreCase("ASC")){
            dynamicQuery.addCriteria(nameCriteria).with(Sort.by(Sort.Direction.ASC, "id"));
        }else {
            dynamicQuery.addCriteria(nameCriteria).with(Sort.by(Sort.Direction.DESC, "id"));
        }

        return mongoOperations.find(dynamicQuery, baseEntity.getClass());
    }

    public Map<String, Object> findDataType(Map<String, Object> parameterMap, BaseEntity baseEntity)  {
        Map<String, Object> responseMap = new HashMap<>();
            Class<?> kls = baseEntity.getClass();
            List<Field> fields = new ArrayList<>();
            Field[] currentFields = kls.getDeclaredFields();
            Field[] supperFields = kls.getSuperclass().getDeclaredFields();
            for (int i = 0; i < supperFields.length; i++) {
                fields.add(supperFields[i]);
            }
            for (int i = 0; i < currentFields.length; i++) {
                fields.add(currentFields[i]);
            }
            for (Field field : fields) {
                if (parameterMap.containsKey(field.getName())) {
                    parameterMap.put(
                            field.getName(),
                            dataTypeForConversion.dataTypeForConversion(
                                    field.getType().toString(),
                                    parameterMap.get(field.getName()).toString()
                            )
                    );
                }
            }

        return parameterMap;
    }
    public Map<String, Object> findDataTypeV2(Map<String, Object> parameterMap, BaseEntity baseEntity)  {
        Map<String, Object> responseMap = new HashMap<>();
        Class<?> kls = baseEntity.getClass();
        List<Field> fields = new ArrayList<>();
        Field[] currentFields = kls.getDeclaredFields();
        Field[] supperFields = kls.getSuperclass().getDeclaredFields();
        for (int i = 0; i < supperFields.length; i++) {
            fields.add(supperFields[i]);
        }
        for (int i = 0; i < currentFields.length; i++) {
            fields.add(currentFields[i]);
        }
        for (Field field : fields) {
            if (parameterMap.containsKey(field.getName())) {
                if(parameterMap.get(field.getName()) instanceof List || parameterMap.get(field.getName()) instanceof Date){
                    parameterMap.put(
                            field.getName(),
                            parameterMap.get(field.getName())
                    );
                }else {
                    parameterMap.put(
                            field.getName(),
                            dataTypeForConversion.dataTypeForConversion(
                                    field.getType().toString(),
                                    parameterMap.get(field.getName()).toString()
                            )
                    );
                }
            }
        }

        return parameterMap;
    }

    public Map<String, Object> findDataTypeV3(Map<String, Object> parameterMap, Class<?> baseEntity)  {
        Map<String, Object> responseMap = new HashMap<>();
        Class<?> kls = baseEntity.getClass();
        List<Field> fields = new ArrayList<>();
        Field[] currentFields = kls.getDeclaredFields();
        Field[] supperFields = kls.getSuperclass().getDeclaredFields();
        for (int i = 0; i < supperFields.length; i++) {
            fields.add(supperFields[i]);
        }
        for (int i = 0; i < currentFields.length; i++) {
            fields.add(currentFields[i]);
        }
        for (Field field : fields) {
            if (parameterMap.containsKey(field.getName())) {
                if(parameterMap.get(field.getName()) instanceof List){
                    parameterMap.put(
                            field.getName(),
                            parameterMap.get(field.getName())
                    );
                }else {
                    parameterMap.put(
                            field.getName(),
                            dataTypeForConversion.dataTypeForConversion(
                                    field.getType().toString(),
                                    parameterMap.get(field.getName()).toString()
                            )
                    );
                }
            }
        }

        return parameterMap;
    }


    @Override
    public Page findWithFilters(
            Map<String, Object> searchItems
            , com.commons.data.model.Page page
            , BaseEntity baseEntity
    ){
        searchItems = setTenantContext(searchItems);
        searchItems = findDataType(searchItems, baseEntity);
        Query dynamicQuery = new Query();
        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria nameCriteria ;
            if(entry.getValue() instanceof String) {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
            }else {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            }
            dynamicQuery.addCriteria(nameCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        dynamicQuery.addCriteria(nameCriteria);

        List content =  mongoOperations.find(dynamicQuery, baseEntity.getClass());
       return null;
    }

    @Override
    public Page getAll(
            Map<String, Object> searchItems
            , String searchText
            , List<String> searchOnFields
            , BaseEntity baseEntity
            , Pageable pageable)
    {
        searchItems = setTenantContext(searchItems);
        String orderedGet = (String) searchItems.get("sortby");
        searchItems.remove("sortby");
        searchItems = findDataTypeV2(searchItems, baseEntity);
        Query query = new Query().with(pageable);
        final List<Criteria> criteria = new ArrayList<>();
        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria nameCriteria ;
            if(entry.getValue() instanceof String) {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
            }else if(entry.getValue() instanceof List)  {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).in((Collection<? extends String>) entry.getValue());
            }else  {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            }
            query.addCriteria(nameCriteria);
        }
        final List<Criteria> searchCriteriaList = new ArrayList<>();
        if(searchText != null && !searchText.equals("")) {
            for (String searchField : searchOnFields) {
                Criteria nameCriteria = Criteria.where(searchField.toLowerCase()).regex( searchText , "i");
                searchCriteriaList.add(nameCriteria);
            }
        }
        if (!searchCriteriaList.isEmpty()) {
            Criteria searchCriteria = new Criteria().orOperator(searchCriteriaList.toArray(new Criteria[searchCriteriaList.size()]));
            query.addCriteria(searchCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        if(orderedGet != null && orderedGet.equalsIgnoreCase("ASC")){
            query.addCriteria(nameCriteria).with(Sort.by(Sort.Direction.ASC, "id"));
        }else {
            query.addCriteria(nameCriteria).with(Sort.by(Sort.Direction.DESC, "id"));
        }
        return PageableExecutionUtils.getPage(
                mongoOperations.find(query, baseEntity.getClass()),
                pageable,
                () -> mongoOperations.count(query.skip(0).limit(0), baseEntity.getClass())
        );
    }

    @Override
    public Page getAllV2(
            Map<String, Object> searchItems
            , String searchText
            , List<String> searchOnFields
            , BaseEntity baseEntity
            , Pageable pageable)
    {
        searchItems = setTenantContext(searchItems);
        String orderedGet = (String) searchItems.get("sortby");
        searchItems.remove("sortby");
        searchItems = findDataTypeV2(searchItems, baseEntity);
        Query query = new Query().with(pageable);
        final List<Criteria> criteria = new ArrayList<>();
        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria nameCriteria ;
            if(entry.getKey().equalsIgnoreCase("mappingcode")) {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + entry.getValue() + "(-.*)?$", "");
            }
            else if(entry.getValue() instanceof String) {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
            }else if (entry.getValue() instanceof Date) {
                nameCriteria = Criteria.where(entry.getKey()).gte(getStartDateTime((Date) entry.getValue())).lte(getEndDateTime((Date) entry.getValue()));
            }else if(entry.getValue() instanceof List)  {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).in((Collection<? extends String>) entry.getValue());
            }else  {
                nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
            }
            query.addCriteria(nameCriteria);
        }
        final List<Criteria> searchCriteriaList = new ArrayList<>();
        if(searchText != null && !searchText.equals("")) {
            for (String searchField : searchOnFields) {
                Criteria nameCriteria = Criteria.where(searchField.toLowerCase()).regex( searchText , "i");
                searchCriteriaList.add(nameCriteria);
            }
        }
        if (!searchCriteriaList.isEmpty()) {
            Criteria searchCriteria = new Criteria().orOperator(searchCriteriaList.toArray(new Criteria[searchCriteriaList.size()]));
            query.addCriteria(searchCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        if(orderedGet != null && orderedGet.equalsIgnoreCase("ASC")){
            query.addCriteria(nameCriteria).with(Sort.by(Sort.Direction.ASC, "id"));
        }else {
            query.addCriteria(nameCriteria).with(Sort.by(Sort.Direction.DESC, "id"));
        }
        return PageableExecutionUtils.getPage(
                mongoOperations.find(query, baseEntity.getClass()),
                pageable,
                () -> mongoOperations.count(query.skip(0).limit(0), baseEntity.getClass())
        );
    }

    @Override
    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public BaseEntity saveAuditLog(BaseEntity baseEntity, Long classId){
        AuditLog entity = new AuditLog();
        BeanUtils.copyProperties(baseEntity, entity);
        entity.setId(generateSequence(AuditLog.SEQUENCE_NAME));
        entity.setClassid(classId);
        entity.setClassname(baseEntity.getClass().toString());
        return  mongoOperations.save(entity);
    }

    @Override
    public List getAggregatedEntities(Map<String, Object> map, BaseEntity baseEntity) throws ClassNotFoundException {
        map = setTenantContext(map);
        List<String> currentFields = getEntityProperties(baseEntity);
        Map<String, Object> searchItems = new HashMap<>();
        for (String field: currentFields){
            if (map.containsKey(field)){
                searchItems.put(field, map.get(field));
            }
        }
        searchItems = findDataType(searchItems, baseEntity);
        Query dynamicQuery = new Query();
        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            Criteria nameCriteria = Criteria.where(entry.getKey()).is(entry.getValue());
            dynamicQuery.addCriteria(nameCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        dynamicQuery.addCriteria(nameCriteria);

        return mongoOperations.find(dynamicQuery, baseEntity.getClass());
    }

    @Override
    public Long update(Map<String, Object> criteriaMap, Map<String, Object> dataMap, BaseEntity baseEntity) {
        criteriaMap = setTenantContext(criteriaMap);
        Query dynamicQuery = new Query();
        if(criteriaMap != null) {
            criteriaMap = findDataType(criteriaMap, baseEntity);

            for (Map.Entry<String, Object> entry : criteriaMap.entrySet()) {
                Criteria nameCriteria = Criteria.where(entry.getKey()).is(entry.getValue());
                dynamicQuery.addCriteria(nameCriteria);
            }
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        dynamicQuery.addCriteria(nameCriteria);

        Update update = new Update();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            if(entry.getValue() != null) {
                update.set(entry.getKey(), entry.getValue());
            }
        }
        UpdateResult updateResult = mongoOperations.updateMulti(dynamicQuery, update, baseEntity.getClass());
        return updateResult.getModifiedCount();
    }



    public List<String> getEntityProperties(BaseEntity obj){
        List<String> fields = new ArrayList<>() ;
        Class<?> kls = obj.getClass();
        Field[] currentFields = kls.getDeclaredFields();
        Field[] supperFields = kls.getSuperclass().getDeclaredFields();
        for (int i =0; i<supperFields.length; i++){
            fields.add(supperFields[i].getName());
        }
        for (int i =0; i<currentFields.length; i++){
            fields.add(currentFields[i].getName());
        }
        return fields;
    }

    public String generateUUID(){
        return UUID.randomUUID().toString();
    }

}
