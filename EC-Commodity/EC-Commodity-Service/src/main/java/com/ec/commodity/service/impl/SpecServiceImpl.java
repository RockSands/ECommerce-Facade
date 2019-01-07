package com.ec.commodity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ec.commodity.mapper.SpecGroupMapper;
import com.ec.commodity.mapper.SpecParamMapper;
import com.ec.commodity.pojo.SpecGroup;
import com.ec.commodity.pojo.SpecParam;
import com.ec.commodity.service.SpecService;
import com.ec.common.enums.ExceptionEnum;
import com.ec.common.exception.ECException;

/**
 * @author bystander
 * @date 2018/9/18
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;


    @Override
    public List<SpecGroup> querySpecGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> specGroupList = specGroupMapper.select(specGroup);
        if (CollectionUtils.isEmpty(specGroupList)) {
            throw new ECException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return specGroupList;
    }

    @Override
    public void saveSpecGroup(SpecGroup specGroup) {
        int count = specGroupMapper.insert(specGroup);
        if (count != 1) {
            throw new ECException(ExceptionEnum.SPEC_GROUP_CREATE_FAILED);
        }
    }

    @Override
    public void deleteSpecGroup(Long id) {
        if (id == null) {
            throw new ECException(ExceptionEnum.INVALID_PARAM);
        }
        SpecGroup specGroup = new SpecGroup();
        specGroup.setId(id);
        int count = specGroupMapper.deleteByPrimaryKey(specGroup);
        if (count != 1) {
            throw new ECException(ExceptionEnum.DELETE_SPEC_GROUP_FAILED);
        }
    }

    @Override
    public void updateSpecGroup(SpecGroup specGroup) {
        int count = specGroupMapper.updateByPrimaryKey(specGroup);
        if (count != 1) {
            throw new ECException(ExceptionEnum.UPDATE_SPEC_GROUP_FAILED);
        }
    }


    @Override
    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        specParam.setGeneric(generic);
        List<SpecParam> specParamList = specParamMapper.select(specParam);
        if (CollectionUtils.isEmpty(specParamList)) {
            throw new ECException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return specParamList;
    }

    @Override
    public void saveSpecParam(SpecParam specParam) {
        int count = specParamMapper.insert(specParam);
        if (count != 1) {
            throw new ECException(ExceptionEnum.SPEC_PARAM_CREATE_FAILED);
        }
    }

    @Override
    public void updateSpecParam(SpecParam specParam) {
        int count = specParamMapper.updateByPrimaryKeySelective(specParam);
        if (count != 1) {
            throw new ECException(ExceptionEnum.UPDATE_SPEC_PARAM_FAILED);
        }
    }

    @Override
    public void deleteSpecParam(Long id) {
        if (id == null) {
            throw new ECException(ExceptionEnum.INVALID_PARAM);
        }
        int count = specParamMapper.deleteByPrimaryKey(id);
        if (count != 1) {
            throw new ECException(ExceptionEnum.DELETE_SPEC_PARAM_FAILED);
        }
    }

    @Override
    public List<SpecGroup> querySpecsByCid(Long cid) {
        List<SpecGroup> specGroups = querySpecGroupByCid(cid);

        List<SpecParam> specParams = querySpecParams(null, cid, null, null);

        Map<Long, List<SpecParam>> map = new HashMap<>();
        //遍历specParams
        for (SpecParam param : specParams) {
            Long groupId = param.getGroupId();
            if (!map.keySet().contains(groupId)) {
                //map中key不包含这个组ID
                map.put(groupId, new ArrayList<>());
            }
            //添加进map中
            map.get(param.getGroupId()).add(param);
        }

        for (SpecGroup specGroup : specGroups) {
            specGroup.setParams(map.get(specGroup.getId()));
        }

        return specGroups;
    }
}


