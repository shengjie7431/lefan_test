package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaApplicantFile;

import java.util.List;
import java.util.Map;

public interface FinaApplicantFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaApplicantFile record);

    int insertSelective(FinaApplicantFile record);

    FinaApplicantFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaApplicantFile record);

    int updateByPrimaryKey(FinaApplicantFile record);

    List<FinaApplicantFile> list(Map map);
    int listSize(Map map);
}