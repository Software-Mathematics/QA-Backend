package com.QA.datasetservice.service;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.dao.daoImplementation.PageStructureDao;
import com.commons.data.entity.*;
import com.commons.util.adapter.BaseAdapter;
import com.commons.util.model.dto.PageStructureDTO;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.sequenceGenerator.Db_sequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PageDatasetServiceImpl implements ServiceI {
    @Autowired
    private PageStructureDao dao;

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
                baseDtoI.setId(baseDao.generateSequence(PageStructure.SEQUENCE_NAME));
            }
            baseDtoI.setSyncstatus(notSyncedStatus);
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            PageStructure entity = (PageStructure) baseAdapter.dtoToEntity(baseDtoI, new PageStructure());
            BaseEntity data = baseDao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public List<BaseDtoI> get(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {

            return (List) baseAdapter.entityToDTO(baseDao.getV2(map, new PageStructure()), new PageStructureDTO());
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

}