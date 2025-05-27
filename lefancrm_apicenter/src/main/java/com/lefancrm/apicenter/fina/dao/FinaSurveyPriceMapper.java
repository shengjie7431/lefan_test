package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyPrice;

import java.util.List;
import java.util.Map;

public interface FinaSurveyPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyPrice record);

    int insertSelective(FinaSurveyPrice record);

    FinaSurveyPrice selectByPrimaryKey(Long id);

    Double getPrice(Long surveyOrgId, Long taskId, Long id);

    int updateByPrimaryKeySelective(FinaSurveyPrice record);

    int updateByPrimaryKey(FinaSurveyPrice record);

    //数据
    List<FinaSurveyPrice> list(Map map);
    int listSize(Map map);

    FinaSurveyPrice selectOneByInfo(Map map);

    /**
     *  获取方向价格
     * @param map
     * @return
     */
    FinaSurveyPrice selectDirectionPrice(Map map);

    FinaSurveyPrice selectDirectionPriceNew(Map map);

    int copyOneByPriceModelId(Long id, Long oldAreaCategoriesId);

    List<FinaSurveyPrice> selectZhongAnPrice(Integer areaId);

    Double maxMoney(String allDirectionDistIdStr,Integer taskId);
}