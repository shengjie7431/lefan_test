package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaApplicantFileEnum;

import java.util.List;
import java.util.Map;

public interface FinaApplicantFileEnumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaApplicantFileEnum record);

    int insertSelective(FinaApplicantFileEnum record);

    FinaApplicantFileEnum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaApplicantFileEnum record);

    int updateByPrimaryKey(FinaApplicantFileEnum record);

    List<FinaApplicantFileEnum> list(Map map);

    int selectListByParentEnumCodePageCount(Map map);
    List<FinaApplicantFileEnum> selectListByParentEnumCodePage(Map map);

    //不包含“某字段”的其他数据
    List<FinaApplicantFileEnum> selectListNoSomeCode(Map map);
}