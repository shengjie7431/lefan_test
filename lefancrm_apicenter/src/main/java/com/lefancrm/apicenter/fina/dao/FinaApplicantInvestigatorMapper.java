package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaApplicantInvestigator;

import java.util.List;
import java.util.Map;

public interface FinaApplicantInvestigatorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaApplicantInvestigator record);

    int insertSelective(FinaApplicantInvestigator record);

    FinaApplicantInvestigator selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaApplicantInvestigator record);

    int updateByPrimaryKey(FinaApplicantInvestigator record);


    List<FinaApplicantInvestigator> list(Map map);
    int listSize(Map map);

    FinaApplicantInvestigator selectByOne(Map map);
}