package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyPriceModelAreaCategories;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyPriceModelAreaCategoriesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyPriceModelAreaCategories record);

    int insertSelective(SurveyPriceModelAreaCategories record);

    SurveyPriceModelAreaCategories selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyPriceModelAreaCategories record);

    int updateByPrimaryKey(SurveyPriceModelAreaCategories record);

    //数据
    List<SurveyPriceModelAreaCategories> list(Map map);
    int listSize(Map map);

    int copyOneByPriceModelId(@Param("oldPriceModelId") Long oldPriceModelId, @Param("newPriceModelId") Long newPriceModelId);

    List<SurveyPriceModelAreaCategories> selectByPriceModelId(@Param("newPriceModelId") Long id,@Param("oldPriceModelId") Long oldPriceModelId);
}