package com.commons.data.dao.commonDao;

import com.commons.data.entity.BaseEntity;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.Map;

public interface BaseDao {

    BaseEntity save(BaseEntity baseEntity);
    Map<String, Object> save(Map<String, Object> dataMap, String collectionName);
    List get(Map<String, Object> map, BaseEntity baseEntity) throws ClassNotFoundException;
    List getV2(Map<String, Object> map, BaseEntity baseEntity) throws ClassNotFoundException;
    List<Document> getV3(Map<String, Object> map, String collectionName) throws ClassNotFoundException;
    public List getByGeoLoc(Map<String, Object> searchItems, Point point, Integer radius, BaseEntity baseEntity) throws ClassNotFoundException ;
    List getWithoutRegX(Map<String, Object> map, BaseEntity baseEntity) throws ClassNotFoundException;
    List getBySort(Map<String, Object> map,  BaseEntity baseEntity) throws ClassNotFoundException;
    Page<BaseEntity> findWithFilters(
            Map<String, Object> searchItems
            , com.commons.data.model.Page page
            , BaseEntity baseEntity
    );
    Page getAll(
            Map<String, Object> searchItems
            , String searchText
            , List<String> searchOnFields
            , BaseEntity baseEntity
            , Pageable pageable
    );
    Page getAllV2(
            Map<String, Object> searchItems
            , String searchText
            , List<String> searchOnFields
            , BaseEntity baseEntity
            , Pageable pageable
    );
    long generateSequence(String seqName);
    BaseEntity saveAuditLog(BaseEntity baseEntity, Long classId);
    List getAggregatedEntities(Map<String, Object> map, BaseEntity baseEntity) throws ClassNotFoundException;
    Long update(Map<String, Object> criteriaMap, Map<String, Object> dataMap, BaseEntity baseEntity);


}
