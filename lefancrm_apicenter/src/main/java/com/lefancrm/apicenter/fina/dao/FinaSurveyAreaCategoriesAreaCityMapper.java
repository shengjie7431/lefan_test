package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyAreaCategoriesAreaCity;
import com.lefancrm.apicenter.fina.model.FinaSurveyPriceModelAreaCategories;
import com.lefancrm.apicenter.model.CommonArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FinaSurveyAreaCategoriesAreaCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyAreaCategoriesAreaCity record);

    int insertSelective(FinaSurveyAreaCategoriesAreaCity record);

    FinaSurveyAreaCategoriesAreaCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyAreaCategoriesAreaCity record);

    int updateByPrimaryKey(FinaSurveyAreaCategoriesAreaCity record);

    //数据
    List<FinaSurveyAreaCategoriesAreaCity> list(Map map);
    int listSize(Map map);

    //删除某“区域类别”下所有的对应数据
    int deleteByAreaCategoriesId(@Param("areaCategoriesId") Long areaCategoriesId,@Param("modelId")Long modelId);

    int deleteByAreaId(@Param("areaId") Long areaId,@Param("modelId") Long modelId);

    int selectAllSelectedCount(@Param("parentIds") String parentIds,@Param("areaCategoriesId") Long areaCategoriesId);

    String selectAllChildren2(@Param("areaId") Long areaId,@Param("getAreaCateGoriesId")  Long getAreaCateGoriesId);

    List<CommonArea> selectAllChildren(@Param("areaId") Long areaId, @Param("getAreaCateGoriesId")  Long getAreaCateGoriesId, @Param("modelId") Long modelId);


    int copyOneByPriceModelId(@Param("surveyPriceModelAreaCategory") FinaSurveyPriceModelAreaCategories surveyPriceModelAreaCategories, @Param("oldPriceModelId") Long oldPriceModelId);
}