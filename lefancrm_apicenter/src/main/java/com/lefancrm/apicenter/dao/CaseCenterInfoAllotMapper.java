package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CaseCenterInfoAllotDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CaseCenterInfoAllotMapper {

    //列表list查询，包含条件查询
    List<CaseCenterInfoAllotDto> selectCaseCenterInfoAllotList(Map<String, Object> paramMap);

    /**
     * 根据调度员名下所有机构查询案件
     * @param map
     * @return
     */
    List<CaseCenterInfoAllotDto> selectAgencyCaseCenterInfoByPatch(HashMap<String,Object> map);
    int selectCountAgencyCaseCenterInfoByPatch(Map<String, Object> paramMap);

    /**
     * 案件分配-查看案件详情
     * @param map
     * @return
     */
    CaseCenterInfoAllotDto searchCaseCenterInfoForAllotById(HashMap<String,Object> map);
}