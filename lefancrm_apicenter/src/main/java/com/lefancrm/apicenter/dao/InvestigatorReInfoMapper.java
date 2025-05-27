package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.InvestigatorReInfoDTO;
import com.lefancrm.apicenter.model.InvestigatorReInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface InvestigatorReInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestigatorReInfo record);

    int insertSelective(InvestigatorReInfo record);

    InvestigatorReInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvestigatorReInfo record);

    int updateByPrimaryKey(InvestigatorReInfo record);

    int suedInsert(Map map);

    List<InvestigatorReInfoDTO> selectInvestigatorReInfos(ApiRequest apiRequest);

    Map selectGroupMoney(InvestigatorReInfoDTO item);

    int selectInvestigatorReInfosSize(ApiRequest apiRequest);

    List<InvestigatorReInfo> selectReStateBySurveyUserId(Long surveyUserId);

    InvestigatorReInfo selectByInvestigatorCaseId(Long investigatorCaseId);



    /**
     * 查询改报销单下得所有报销清单是否都已完成。
     * @param reId
     * @return
     */
    int selectAllSuccessByReId(Long reId);

    /**
     * 根据报销清单ID 更新案件得报销状态
     * @param reId
     * @return
     */
    int updateSurveyRiskInfoReimSate(Long reId);
}