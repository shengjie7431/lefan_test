package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.LevelPromotion;

import java.util.List;
import java.util.Map;

public interface LevelPromotionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LevelPromotion record);

    int insertSelective(LevelPromotion record);

    LevelPromotion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LevelPromotion record);

    int updateByPrimaryKey(LevelPromotion record);

    //获取数据总值，包含条件查询后的结果
    int selectCountLevelPromotion(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<LevelPromotion> selectLevelPromotionList(Map<String, Object> paramMap);

    //查询LevelPromotion.LevelId=id的list
    List<LevelPromotion> searchLevelPromotionByLevelId(Long id);

}