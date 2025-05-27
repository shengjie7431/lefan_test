package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyAreaCategoriesAreaCity;
import com.lefancrm.apicenter.model.SurveyPriceModelAreaCategories;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyAreaCategoriesAreaCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAreaCategoriesAreaCity record);

    int insertSelective(SurveyAreaCategoriesAreaCity record);

    SurveyAreaCategoriesAreaCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAreaCategoriesAreaCity record);

    int updateByPrimaryKey(SurveyAreaCategoriesAreaCity record);

    //数据
    List<SurveyAreaCategoriesAreaCity> list(Map map);
    int listSize(Map map);

    //删除某“区域类别”下所有的对应数据
    int deleteByAreaCategoriesId(@Param("areaCategoriesId") Long areaCategoriesId,@Param("modelId")Long modelId);

    int deleteByAreaId(@Param("areaId") Long areaId,@Param("modelId") Long modelId);

    int selectAllSelectedCount(@Param("parentIds") String parentIds,@Param("areaCategoriesId") Long areaCategoriesId);

    int copyOneByPriceModelId(@Param("surveyPriceModelAreaCategory") SurveyPriceModelAreaCategories surveyPriceModelAreaCategory,@Param("oldPriceModelId") Long oldPriceModelId);
}