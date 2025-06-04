package com.commons.data.dao.commonDao;

import com.commons.data.dao.dto.CustomPageable;
import com.commons.data.dao.dto.Filter;
import com.commons.data.dao.dto.FilterByPage;
import com.commons.data.dao.dto.NearLocation;
import com.commons.data.entity.BaseEntity;
import com.commons.data.entity.Project;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BaseDaoImplV2 implements BaseDaoV2{
    @Autowired
    private MongoTemplate mongoOperations;

    @Override
    public List<Document> get(Map<String, Object> searchItems, String collectionName, ZoneId zoneId) {
        searchItems = SMDataType.findDataTypeV2(searchItems, new BaseEntity());
        Query dynamicQuery = new Query();

        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Criteria nameCriteria;

            if (value instanceof Date) {
                Date startDate = SMDate.convertToStartOfDayUTC((Date) value, zoneId);
                Date endDate = SMDate.convertToEndOfDayUTC((Date) value, zoneId);
                nameCriteria = Criteria.where(key).gte(startDate).lte(endDate);
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


    @Override
    public List get(Map<String, Object> searchItems, BaseEntity baseEntity, ZoneId zoneId) {
        searchItems = SMDataType.findDataTypeV2(searchItems, new BaseEntity());
        Query dynamicQuery = new Query();

        for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Criteria nameCriteria;

            if (value instanceof String) {
                nameCriteria = Criteria.where(key).is(value);
            } else if (value instanceof Date) {
                Date startDate = SMDate.convertToStartOfDayUTC((Date) value, zoneId);
                Date endDate = SMDate.convertToEndOfDayUTC((Date) value, zoneId);
                nameCriteria = Criteria.where(key).gte(startDate).lte(endDate);
            } else if (value instanceof List) {
                nameCriteria = Criteria.where(key).in((Collection<?>) value);
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


    public boolean isValidPolygon(List<double[]> polygonCoordinates) {
        // Check if the polygon coordinates are non-null and contain at least 4 points (3 distinct + 1 closing point)
        if (polygonCoordinates == null || polygonCoordinates.size() < 4) {
            return false;
        }

        // Check if the first and last points are the same to ensure the polygon is closed
        double[] firstPoint = polygonCoordinates.get(0);
        double[] lastPoint = polygonCoordinates.get(polygonCoordinates.size() - 1);
        if (!Arrays.equals(firstPoint, lastPoint)) {
            return false;
        }

        for (double[] point : polygonCoordinates) {
            // Each point must have exactly 2 coordinates (longitude and latitude)
            if (point == null || point.length != 2) {
                return false;
            }

            // Validate latitude and longitude ranges
            double latitude = point[1];
            double longitude = point[0];

            if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List get(
            Filter filter, BaseEntity baseEntity
    ) {
        Map<String, Object> searchItems = filter.getSearchItems();
        List<double[]> polygonCoordinates = filter.getPolygonCoordinates();
        NearLocation nearLocation = filter.getNearLocation();

        searchItems = SMDataType.findDataTypeV2(searchItems, baseEntity);
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

        // Add geospatial criteria if polygon coordinates are provided
        if (polygonCoordinates != null && !polygonCoordinates.isEmpty()) {
            // Convert the list of coordinates to a list of GeoJsonPoint
            List<Point> points = polygonCoordinates.stream()
                    .map(coord -> new GeoJsonPoint(coord[0], coord[1]))
                    .collect(Collectors.toList());

            // Create the GeoJsonPolygon directly
            GeoJsonPolygon polygon = new GeoJsonPolygon(points);

            // Add the geospatial criteria to the query
            Criteria geoCriteria = Criteria.where("location").within(polygon);
            dynamicQuery.addCriteria(geoCriteria);
        }

        // Add geospatial criteria if nearLocation is provided
        if (
                nearLocation != null &&
                        nearLocation.getNearLocation() != null &&
                        nearLocation.getNearLocation().length == 2
        ) {
            GeoJsonPoint point = new GeoJsonPoint(nearLocation.getNearLocation()[0], nearLocation.getNearLocation()[1]);

            // Add the $near criteria with $maxDistance to the query
            Criteria geoCriteria = Criteria.where("location").near(point).maxDistance(nearLocation.getMaxDistance());
            dynamicQuery.addCriteria(geoCriteria);
        }

        // Set collation for case-insensitive matching
        Collation collation = Collation.of("en").strength(Collation.ComparisonLevel.secondary());

        // Execute query with collation
        return this.mongoOperations.find(dynamicQuery.collation(collation), baseEntity.getClass());
    }

    @Override
    public Page getByPage(
            FilterByPage filter, BaseEntity baseEntity
    )
    {
        if (filter == null) {
            filter = new FilterByPage();
        }
        Map<String, Object> searchItems = filter.getSearchItems();
        String searchText = filter.getSearchText();
        List<String> searchOnFields = filter.getSearchOnFields();
        if (filter.getPageable() == null) {
            filter.setPageable(new CustomPageable());
        }
        Pageable pageable = PageRequest.of(filter.getPageable().getPage(), filter.getPageable().getSize(), filter.getPageable().getSort());
        List<double[]> polygonCoordinates = filter.getPolygonCoordinates();
        NearLocation nearLocation = filter.getNearLocation();

        Query query = new Query().with(pageable);
        if (searchItems != null) {
            searchItems = SMDataType.findDataTypeV2(searchItems, baseEntity);
            query = new Query().with(pageable);
            for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
                Criteria nameCriteria;
                if (entry.getValue() instanceof String) {
                    nameCriteria = Criteria.where(entry.getKey().toLowerCase()).regex("^" + String.valueOf(entry.getValue()) + "$", "i");
                } else if (entry.getValue() instanceof Date) {
                    nameCriteria = Criteria.where(entry.getKey()).gte(SMDate.getStartDateTime((Date) entry.getValue())).lte(SMDate.getEndDateTime((Date) entry.getValue()));
                } else if (entry.getValue() instanceof List) {
                    nameCriteria = Criteria.where(entry.getKey().toLowerCase()).in((Collection<? extends String>) entry.getValue());
                } else {
                    nameCriteria = Criteria.where(entry.getKey().toLowerCase()).is(entry.getValue());
                }
                query.addCriteria(nameCriteria);
            }
        }
        final List<Criteria> searchCriteriaList = new ArrayList<>();
        if (searchText != null && !searchText.equals("")) {
            for (String searchField : searchOnFields) {
                Criteria nameCriteria = Criteria.where(searchField.toLowerCase()).regex(searchText, "i");
                searchCriteriaList.add(nameCriteria);
            }
        }
        if (!searchCriteriaList.isEmpty()) {
            Criteria searchCriteria = new Criteria().orOperator(searchCriteriaList.toArray(new Criteria[searchCriteriaList.size()]));
            query.addCriteria(searchCriteria);
        }



        // Add geospatial criteria if polygon coordinates are provided
        if (polygonCoordinates != null && !polygonCoordinates.isEmpty()) {
            // Convert the list of coordinates to a list of GeoJsonPoint
            List<Point> points = polygonCoordinates.stream()
                    .map(coord -> new GeoJsonPoint(coord[0], coord[1]))
                    .collect(Collectors.toList());

            // Create the GeoJsonPolygon directly
            GeoJsonPolygon polygon = new GeoJsonPolygon(points);

            // Add the geospatial criteria to the query
            Criteria geoCriteria = Criteria.where("location").within(polygon);
            query.addCriteria(geoCriteria);
        }

        // Add geospatial criteria if nearLocation is provided
        if (
                nearLocation != null &&
                        nearLocation.getNearLocation() != null &&
                        nearLocation.getNearLocation().length == 2
        ) {
            GeoJsonPoint point = new GeoJsonPoint(nearLocation.getNearLocation()[0], nearLocation.getNearLocation()[1]);

            // Add the $near criteria with $maxDistance to the query
            Criteria geoCriteria = Criteria.where("location").nearSphere(point).maxDistance(nearLocation.getMaxDistance());
            query.addCriteria(geoCriteria);
        }
        Criteria nameCriteria = Criteria.where("status").ne("INACTIVE");
        query.addCriteria(nameCriteria);

        Query finalQuery = query;
        return PageableExecutionUtils.getPage(
                mongoOperations.find(query, baseEntity.getClass()),
                pageable,
                () -> mongoOperations.count(finalQuery.skip(0).limit(0), baseEntity.getClass())
        );
    }


    @Override
    public List<Project> findByStatusAndProjecthirarichalcodeInOrLike(String status, List<String> codes) {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(status));

        Criteria inCriteria = Criteria.where("projecthirarichalcode").in(codes);
        Criteria likeCriteria = Criteria.where("projecthirarichalcode").regex(String.join("|", codes), "i"); // Case-insensitive regex match

        query.addCriteria(new Criteria().orOperator(inCriteria, likeCriteria));

        return mongoOperations.find(query, Project.class);
    }

}


