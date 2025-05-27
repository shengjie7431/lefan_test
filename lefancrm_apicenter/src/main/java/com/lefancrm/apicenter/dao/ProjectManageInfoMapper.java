package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ProjectManageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author EDZ
 */
public interface ProjectManageInfoMapper {

    /**
     * 根据条件查询所有
     * @param map
     * @return
     */
    List<ProjectManageInfo> selectByMap(Map map);

    /**
     * 根据条件查询一条信息
     * @param map
     * @return
     */
    ProjectManageInfo selectByOne(Map map);

    /**
     * 根据实体类修改数据
     * @param map
     * @return
     */
    Integer updateOne(Map map);

    /**
     * 新增数据
     * @param projectManageInfo
     * @return
     */
    Integer insertOne(ProjectManageInfo projectManageInfo);
    Integer insertSelective(ProjectManageInfo projectManageInfo);

    /**
     * 根据条件删除数据
     * @param map
     * @return
     */
    Integer deleteOne(Map map);

    Map queryDataByEntrustOrgId(Map paramMap);
}
