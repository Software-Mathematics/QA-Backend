package com.role_service.service;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.dao.daoImplementation.PermissionDao;
import com.commons.data.dao.daoImplementation.RoleDao;
import com.commons.data.entity.*;
import com.commons.util.adapter.BaseAdapter;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.dto.ProjectDTO;
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
import java.util.*;

@Service
public class RoleServiceImpl implements ServiceI {
    @Autowired
    private RoleDao dao;

    @Autowired
    private PermissionDao permissionDao;

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
            if (baseDtoI.getId() == null) {
                baseDtoI.setId(baseDao.generateSequence(Role.SEQUENCE_NAME));
            }
            baseDtoI.setSyncstatus(notSyncedStatus);
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Role entity = (Role) baseAdapter.dtoToEntity(baseDtoI, new Role());
//            entity.setRolecode(dbseq.sequenceGeneratorForCode(entity.getName()));
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
                baseDtoI.setId(baseDao.generateSequence(Role.SEQUENCE_NAME));
            }
            baseDtoI.setSyncstatus(syncedStatus);
            baseDtoI.setStatus(baseDtoI.getStatus().toUpperCase());
            Role Role = (Role) baseAdapter.dtoToEntity(baseDtoI, new Role());
//            Role.setRolecode(dbseq.sequenceGeneratorForCode(Role.getName()));
            dao.save(Role);
            return (BaseDtoI) baseAdapter.entityToDTO(Role, baseDtoI);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }


    @Override
    public List<BaseDtoI> get(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            return (List) baseAdapter.entityToDTO(baseDao.get(map, new Role()), new RoleDTO());
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
            List<Role> response = new ArrayList<>();
            Page page = baseDao.getAll(
                    map
                    , searchText
                    , fields
                    , new Role()
                    , pageable
            );
            response.addAll(page.getContent());
            return org.apache.commons.lang3.tuple.Pair.of((List<BaseDtoI>) baseAdapter.entityToDTO(response, new RoleDTO()), page);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public BaseDtoI delete(BaseDtoI baseDtoI) throws AppException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            baseDtoI.setStatus("INACTIVE");

            removeRolePermission(baseDtoI);
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

    @Override
    public BaseDtoI updateKafka(BaseDtoI baseDtoI) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            if (baseDtoI.getId() == null) {
                throw new AppException(errorRepository.getError("K005"));
            } else {
                baseDtoI.setModifieddate(new Date());
            }
            return createKafka(baseDtoI);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    public void removeRolePermission(BaseDtoI baseDtoI) throws AppException {
        try {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("_id", baseDtoI.getId());

            List<Role> roleList = (List<Role>) baseDao.get(queryMap, new Role());

            if (roleList.isEmpty()) {
                throw new RuntimeException("No Role record found for Id: " + baseDtoI.getId());
            }
            Role role = roleList.get(roleList.size() - 1);
            String rolecode = role.getRolecode();

            List<Permission> allPermissions = (List<Permission>) baseDao.get(new HashMap<>(), new Permission());

            Permission matchedPermission = allPermissions.stream()
                    .filter(p -> p.getPermission() != null && p.getPermission().containsKey(rolecode))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No Permission record found for rolecode: " + rolecode));

            Map<String, Object> permissionMap = matchedPermission.getPermission();
            permissionMap.remove(rolecode);

            matchedPermission.setPermission(permissionMap);
            matchedPermission.setModifieddate(new Date());

            permissionDao.save(matchedPermission);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }
}
