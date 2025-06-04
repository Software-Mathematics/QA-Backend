package com.QA.featureservice.service;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.dao.daoImplementation.FeatureScenarioDao;
import com.commons.data.entity.FeatureScenario;
import com.commons.data.entity.BaseEntity;
import com.commons.util.adapter.BaseAdapter;
import com.commons.util.model.dto.FeatureScenarioDTO;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.sequenceGenerator.Db_sequenceDao;
import com.commons.util.tokenizer.Tokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeatureServiceImpl implements ServiceI {
    @Autowired
    private FeatureScenarioDao dao;

    @Autowired
    private BaseDaoImpl baseDao;

    @Autowired
    private BaseAdapter baseAdapter;

    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private Db_sequenceDao dbseq;

    @Value("${status.synced}")
    private String syncedStatus;

    @Value("${status.not-synced}")
    private String notSyncedStatus;

    @Override
    public BaseDtoI create(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            if (baseDtoI.getCreateddate() == null) {
                baseDtoI.setCreateddate(new Date());
            }

            baseDtoI.setSyncstatus(notSyncedStatus);
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            FeatureScenario entity = (FeatureScenario) baseAdapter.dtoToEntity(baseDtoI, new FeatureScenario());
            if (entity.getId() == null) {
                entity.setId(baseDao.generateSequence(FeatureScenario.SEQUENCE_NAME));
                entity.setCode(dbseq.sequenceGeneratorForCode(entity.getName()));

            }

            BaseEntity data = baseDao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public List<BaseDtoI> get(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String value = entry.getValue().toString();
                if (value.contains("~")) {
                    map.put(entry.getKey(), Tokenizer.tokenize(value, "~"));
                }
            }

            return (List) baseAdapter.entityToDTO(baseDao.getV2(map, new FeatureScenario()), new FeatureScenarioDTO());
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
            return create(baseDtoI);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    public List<BaseDtoI> getFeaturesByCategory(String category) throws AppException {
        try {
            List<String> tagList = Arrays.stream(category.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());

            Map<String, Object> getMap = new HashMap<>();
            getMap.put("tags", tagList);
            List<FeatureScenario> features = baseDao.getV2(getMap, new FeatureScenario());

            if (features.isEmpty()) {
                throw new AppException("No features available for given category");
            }

            return (List<BaseDtoI>) baseAdapter.entityToDTO(features, new FeatureScenarioDTO());

        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

}