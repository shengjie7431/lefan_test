package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.QualifiedManpower;

import java.util.List;
import java.util.Map;

public interface QualifiedManpowerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QualifiedManpower record);

    int insertSelective(QualifiedManpower record);

    QualifiedManpower selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QualifiedManpower record);

    int updateByPrimaryKey(QualifiedManpower record);

    //获取数据总值，包含条件查询后的结果
    int selectCountQualifiedManpower(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<QualifiedManpower> selectQualifiedManpowerList(Map<String, Object> paramMap);

    //查询LevelPromotion.LevelId=id的list
    List<QualifiedManpower> searchQualifiedManpowerByLevelId(Long id);
}