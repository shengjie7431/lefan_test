package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorEfficiencyAreaCity;
import com.lefancrm.apicenter.model.SurveyConsignorEfficiencyModelArea;
import com.lefancrm.apicenter.model.SurveyPriceModelAreaCategories;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorEfficiencyAreaCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorEfficiencyAreaCity record);

    int insertSelective(SurveyConsignorEfficiencyAreaCity record);

    SurveyConsignorEfficiencyAreaCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorEfficiencyAreaCity record);

    int updateByPrimaryKey(SurveyConsignorEfficiencyAreaCity record);


    //数据
    List<SurveyConsignorEfficiencyAreaCity> list(Map map);
    int listSize(Map map);

    //删除某“区域类别”下所有的对应数据
    int deleteByAreaCategoriesId(@Param("areaCategoriesId") Long areaCategoriesId, @Param("modelId")Long modelId);

    int deleteByAreaId(@Param("areaId") Long areaId,@Param("modelId") Long modelId);

    int selectAllSelectedCount(@Param("parentIds") String parentIds,@Param("areaCategoriesId") Long areaCategoriesId);

    int copyPirce(Map<String,Object> paramMap);
}