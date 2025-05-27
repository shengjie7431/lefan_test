package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseMediationReport;

import java.util.HashMap;
import java.util.List;

public interface CaseMediationReportMapper {
    int insert(CaseMediationReport record);

    int insertSelective(CaseMediationReport record);

    /**
     * 查询
     * @param map
     * @return
     */
    List<CaseMediationReport> selectMediationByCaseIdAndCaseType(HashMap<String, Object> map);

    /**
     * 添加案件调解报告
     * @param map
     * @return
     */
    int addCaseMediationReport(HashMap<String, Object> map);

    /**
     * 查询是否存在
     * @param map
     * @return
     */
    int selectWhetherCaseMediation(HashMap<String, Object> map);

    /**
     * 修改案件调解报告
     * @param map
     * @return
     */
    int updateCaseMediationReport(HashMap<String, Object> map);
}