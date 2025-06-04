package com.commons.data.dao.commonDao;

import com.commons.data.dao.dto.Filter;
import com.commons.data.dao.dto.FilterByPage;
import com.commons.data.dao.dto.NearLocation;
import com.commons.data.entity.BaseEntity;
import com.commons.data.entity.Project;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;

public interface BaseDaoV2 {
    List<Document> get(Map<String, Object> searchItems, String collectionName, ZoneId zoneId);
    List get(Map<String, Object> searchItems, BaseEntity baseEntity, ZoneId zoneId);
    boolean isValidPolygon(List<double[]> polygonCoordinates);
    List get(Filter filter, BaseEntity baseEntity);
    Page getByPage(
            FilterByPage filter, BaseEntity baseEntity
    );
    List<Project> findByStatusAndProjecthirarichalcodeInOrLike(String status, List<String> codes);
}
