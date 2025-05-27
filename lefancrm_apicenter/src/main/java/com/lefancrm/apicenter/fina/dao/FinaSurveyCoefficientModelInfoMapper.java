package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyCoefficientModelInfo;
import com.lefancrm.apicenter.fina.model.FinaSurveyConsignorEfficiencyModelInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FinaSurveyCoefficientModelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyCoefficientModelInfo record);

    int insertSelective(FinaSurveyCoefficientModelInfo record);

    FinaSurveyCoefficientModelInfo selectByPrimaryKey(Long id);

    FinaSurveyCoefficientModelInfo selectByModelIdAndAreaId(@Param("modelId") Long modelId,@Param("areaId") Long areaId);
    //数据
    List<FinaSurveyCoefficientModelInfo> list(Map map);


    int updateByPrimaryKeySelective(FinaSurveyCoefficientModelInfo record);

    int updateByPrimaryKey(FinaSurveyCoefficientModelInfo record);
}