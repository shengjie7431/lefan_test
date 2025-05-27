package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.PositionPromotion;

import java.util.List;
import java.util.Map;

public interface PositionPromotionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PositionPromotion record);

    int insertSelective(PositionPromotion record);

    PositionPromotion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PositionPromotion record);

    int updateByPrimaryKey(PositionPromotion record);

    //获取数据总值，包含条件查询后的结果
    int selectCountPositionPromotion(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<PositionPromotion> selectPositionPromotionList(Map<String, Object> paramMap);

    //查询PositionPromotion.positionId=id的list
    List<PositionPromotion> searchPositionPromotionByPositionId(Long id);
}