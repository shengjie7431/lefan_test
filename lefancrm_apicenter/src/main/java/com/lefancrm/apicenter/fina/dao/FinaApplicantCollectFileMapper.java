package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaApplicantCollectFile;

import java.util.List;
import java.util.Map;

public interface FinaApplicantCollectFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaApplicantCollectFile record);

    int insertSelective(FinaApplicantCollectFile record);

    FinaApplicantCollectFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaApplicantCollectFile record);

    int updateByPrimaryKey(FinaApplicantCollectFile record);

    List<FinaApplicantCollectFile> list(Map map);
    int listSize(Map map);
}