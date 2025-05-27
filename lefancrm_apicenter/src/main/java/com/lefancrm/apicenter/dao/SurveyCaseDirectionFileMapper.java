package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyCaseDirectionFileDto;
import com.lefancrm.apicenter.model.SurveyCaseDirectionFile;

import java.util.List;
import java.util.Map;

public interface SurveyCaseDirectionFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCaseDirectionFile record);

    int insertSelective(SurveyCaseDirectionFile record);

    SurveyCaseDirectionFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCaseDirectionFile record);

    int updateByPrimaryKey(SurveyCaseDirectionFile record);

    List<SurveyCaseDirectionFileDto> list(Map map);

    int listSize(Map map);

    SurveyCaseDirectionFileDto selectByInfo(Map map);
}