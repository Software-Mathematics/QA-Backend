package com.QA.datasetservice.service;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.dao.daoImplementation.DataSetDao;
import com.commons.data.entity.*;
import com.commons.util.adapter.BaseAdapter;
import com.commons.util.model.dto.DataSetDTO;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.sequenceGenerator.Db_sequenceDao;
import com.commons.util.tokenizer.Tokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataSetServiceImpl implements ServiceI {
    @Autowired
    private DataSetDao dao;

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
            if (baseDtoI.getId() == null) {
                boolean isCreateAllowed = validateScenarioCode(baseDtoI);
                if (!isCreateAllowed) {
                    throw new AppException(errorRepository.getError("K016"));
                }
                baseDtoI.setId(baseDao.generateSequence(DataSet.SEQUENCE_NAME));
            }
            baseDtoI.setSyncstatus(notSyncedStatus);
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            DataSet entity = (DataSet) baseAdapter.dtoToEntity(baseDtoI, new DataSet());
            BaseEntity data = baseDao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        } catch (AppException e) {
            throw e;
        } catch (Exception e){
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
            return (List) baseAdapter.entityToDTO(baseDao.getV2(map, new DataSet()), new DataSetDTO());
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

    public void update(Map<String, Object> kafkaMessage) {
        try {
            String code = (String) kafkaMessage.get("code");
            if (code == null ) {
                System.out.println("code is missing.");
                return;
            }

            String baseUrl = (String) kafkaMessage.get("base_url");
            String endpoint = (String) kafkaMessage.get("endpoint");
            String mappingType = (String) kafkaMessage.get("mapping_type");
            String scenarioName = (String) kafkaMessage.get("name");
            String scenarioDescription = (String) kafkaMessage.get("description");
            List<Map<String, Object>> dataModel = (List<Map<String, Object>>) kafkaMessage.get("data_model");
            Map<String, Object> headers = (Map<String, Object>) kafkaMessage.get("headers");

            Map<String, Object> criteriaMap = new HashMap<>();
            criteriaMap.put("scenario.code", code);

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("baseUrl", baseUrl);
            dataMap.put("endpoint", endpoint);
            dataMap.put("mappingType", mappingType);
            dataMap.put("scenario.name", scenarioName);
            dataMap.put("scenario.description", scenarioDescription);
            dataMap.put("data_model", dataModel);
            dataMap.put("header", headers);

            baseDao.update(criteriaMap, dataMap, new DataSet());
            System.out.println("DataSet updated successfully.");

        } catch (Exception e) {
             e.printStackTrace();
             System.out.println("Error processing Kafka message: " + e.getMessage());
        }
    }

    public boolean validateScenarioCode(BaseDtoI baseDtoI) throws AppException {
        try {
            DataSet dataSet = (DataSet) baseAdapter.dtoToEntity(baseDtoI, new DataSet());
            DatasetScenario scenario = dataSet.getScenario();

            if (scenario != null && scenario.getCode() != null) {
                Map<String, Object> queryMap = new HashMap<>();
                queryMap.put("scenario.code", scenario.getCode());
                List<DataSet> existingList = (List<DataSet>) baseDao.get(queryMap, new DataSet());

                if (!existingList.isEmpty()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

}