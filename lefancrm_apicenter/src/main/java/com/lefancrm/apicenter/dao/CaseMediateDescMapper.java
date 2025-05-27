package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseMediateDesc;

import java.util.HashMap;

public interface CaseMediateDescMapper {
    int insert(CaseMediateDesc record);

    int insertSelective(CaseMediateDesc record);

    int insertCaseMediateDesc(HashMap<String, Object> map);

    /**
     * 查询是否存在备注信息
     * @param map
     * @return
     */
    int selectWhetherCaseMediateDesc(HashMap<String, Object> map);

    /**
     * 修改案件备注信息
     * @param map
     * @return
     */
    int updateCaseMediateDesc(HashMap<String, Object> map);

    CaseMediateDesc selectCaseMediateDesc(HashMap<String, Object> map);


}