package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.InvestigatorReDetails;

import java.util.List;
import java.util.Map;

public interface InvestigatorReDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestigatorReDetails record);

    int insertSelective(InvestigatorReDetails record);

    InvestigatorReDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvestigatorReDetails record);

    int updateByPrimaryKey(InvestigatorReDetails record);

    int suedInsert(Map map);

    int selectCountByInvestigatorReId(Long investigatorReId);

    Double selectTotalMoneyByInvestigatorReId(Long investigatorReId);

    int selectInvestigatorCountByPayInfoId(Long payInfoId);

    InvestigatorReDetails selectDetailByInvestigatorCaseId(Long investigatorCaseId);

    Integer selectReStateStrByInvCaseId(Long id);
}