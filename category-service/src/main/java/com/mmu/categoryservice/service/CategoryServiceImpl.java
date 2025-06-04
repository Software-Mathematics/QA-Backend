package com.mmu.categoryservice.service;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.dao.daoImplementation.CategoryDao;
import com.commons.data.entity.BaseEntity;
import com.commons.data.entity.Category;
import com.commons.data.entity.Role;
import com.commons.util.adapter.BaseAdapter;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.dto.CategoryDTO;
import com.commons.util.model.dto.RoleDTO;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.sequenceGenerator.Db_sequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements ServiceI {
    @Autowired
    private CategoryDao dao;

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

    @Value("${fields.search.index}")
    private List<String> fields;

    @Override
    public BaseDtoI create(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            if (baseDtoI.getCreateddate() == null) {
                baseDtoI.setCreateddate(new Date());
            }

            baseDtoI.setSyncstatus("notSyncedStatus");
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Category entity = (Category) baseAdapter.dtoToEntity(baseDtoI, new Category());
            if (entity.getId() == null) {
                entity.setId(baseDao.generateSequence(Category.SEQUENCE_NAME));
                entity.setCode(entity.getName() == null ? baseDao.generateUUID() : dbseq.sequenceGeneratorForCode(entity.getName()));
                if (entity.getHierarchicalcode() != null && !entity.getHierarchicalcode().equals("")) {
                    entity.setHierarchicalcode(entity.getHierarchicalcode() + "-" + entity.getCode());
                } else {
                    entity.setHierarchicalcode(entity.getCode());
                }
            }

            BaseEntity data = baseDao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        }catch (Exception e){
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
                baseDtoI.setId(baseDao.generateSequence(Category.SEQUENCE_NAME));
            }
            baseDtoI.setSyncstatus("syncedStatus");
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Category entity = (Category) baseAdapter.dtoToEntity(baseDtoI, new Category());
            entity.setCode(dbseq.sequenceGeneratorForCode(entity.getName()));
            BaseEntity data = baseDao.save(entity);
            return (BaseDtoI) baseAdapter.entityToDTO(data, baseDtoI);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public List<BaseDtoI> get(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            return (List) baseAdapter.entityToDTO(baseDao.get(map, new Category()), new CategoryDTO());
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    public org.apache.commons.lang3.tuple.Pair<List<BaseDtoI>, Page> getByPage(Map<String, Object> map, Pageable pageable) throws AppException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        try {
            map.remove("ispageable");
            map.remove("page");
            map.remove("size");
            String searchText = map.get("st") != null ?(String) map.get("st") : null;
            map.remove("st");
            List<Category> response = new ArrayList<>();
            Page page = baseDao.getAll(
                    map
                    , searchText
                    , fields
                    , new Category()
                    , pageable
            );
            response.addAll(page.getContent());
            return org.apache.commons.lang3.tuple.Pair.of((List<BaseDtoI>) baseAdapter.entityToDTO(response, new CategoryDTO()), page);
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

    public List<BaseDtoI> getWithHierarchy(Map<String, Object> map) throws AppException {
        try {
            List<Category> entityList = new ArrayList<>();
            if(map.get("depth") != null && map.get("hierarchicalcode") != null) {
                entityList = dao.findByDepthAndHierarchicalcodeContaining(Integer.valueOf((String) map.get("depth")), (String) map.get("hierarchicalcode"));
            } else {
                entityList = baseDao.get(map, new Category());
            }
            return (List) baseAdapter.entityToDTO(entityList, new CategoryDTO());
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }
}

