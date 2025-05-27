package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaApplicantTrack;

import java.util.List;
import java.util.Map;

public interface FinaApplicantTrackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaApplicantTrack record);

    int insertSelective(FinaApplicantTrack record);

    FinaApplicantTrack selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaApplicantTrack record);

    int updateByPrimaryKey(FinaApplicantTrack record);

    List<FinaApplicantTrack> list(Map map);
    int listSize(Map map);
}