package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyPrice;

import java.util.List;
import java.util.Map;

public interface SurveyPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyPrice record);

    int insertSelective(SurveyPrice record);

    SurveyPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyPrice record);

    int updateByPrimaryKey(SurveyPrice record);

    //数据
    List<SurveyPrice> list(Map map);
    int listSize(Map map);

    SurveyPrice selectOneByInfo(Map map);

    /**
     *  获取方向价格
     * @param map
     * @return
     */
    SurveyPrice selectDirectionPrice(Map map);

    SurveyPrice selectDirectionPriceNew(Map map);

    int copyOneByPriceModelId(Long id, Long oldAreaCategoriesId);

    List<SurveyPrice> selectZhongAnPrice(Integer areaId);

    Double maxMoney(String allDirectionDistIdStr,Integer taskId);
}