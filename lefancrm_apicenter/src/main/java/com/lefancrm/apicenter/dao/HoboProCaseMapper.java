package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.HoboReportData;
import com.lefancrm.apicenter.model.HoboProCase;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HoboProCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HoboProCase record);

    int insertSelective(HoboProCase record);

    HoboProCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HoboProCase record);

    int updateByPrimaryKey(HoboProCase record);

    List<HoboProCase> list(Map<String,Object> map);

    int listSize(Map map);

    int saveByData(@Param("data") List<HoboProCase> hoboProCase);

    public List<HoboReportData> selectReportData(Map<String,Object> map);
}