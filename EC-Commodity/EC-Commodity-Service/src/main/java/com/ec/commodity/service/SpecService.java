package com.ec.commodity.service;

import java.util.List;

import com.ec.commodity.pojo.SpecGroup;
import com.ec.commodity.pojo.SpecParam;

/**
 * @author bystander
 * @date 2018/9/18
 */
public interface SpecService {

    List<SpecGroup> querySpecGroupByCid(Long cid);

    void saveSpecGroup(SpecGroup specGroup);

    void deleteSpecGroup(Long id);

    void updateSpecGroup(SpecGroup specGroup);

    List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic);

    void saveSpecParam(SpecParam specParam);

    void updateSpecParam(SpecParam specParam);

    void deleteSpecParam(Long id);

    List<SpecGroup> querySpecsByCid(Long cid);
}
