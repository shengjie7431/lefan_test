package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.CaseSteps;

import java.util.List;

public interface CaseStepsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseSteps record);

    int insertSelective(CaseSteps record);

    CaseSteps selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseSteps record);

    int updateByPrimaryKey(CaseSteps record);
    /**
     * 根据案件中心ID获取最新的一条 用于修改 更新时间更新人 ID降序 取第一条
     * @param caseCenterId
     * @return
     */
    CaseSteps selectByCaseCenterIdToUpdate(Long caseCenterId);

    /**
     * 根据案件中心id获取 案件进度的 蓝色进度条  id升序 取列表
     * @param caseCenterId
     * @return
     */
    List<CaseSteps> selectByCaseCenterIdToList(Long caseCenterId);
}