package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelModelAreaCity;
import com.lefancrm.apicenter.model.SurveyConsignorEfficiencyAreaCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyChannelModelAreaCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyChannelModelAreaCity record);

    int insertSelective(SurveyChannelModelAreaCity record);

    SurveyChannelModelAreaCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyChannelModelAreaCity record);

    int updateByPrimaryKey(SurveyChannelModelAreaCity record);

    //数据
    List<SurveyChannelModelAreaCity> list(Map map);

    int listSize(Map map);

    int selectAllSelectedCount(@Param("parentIds") String parentIds, @Param("areaCategoriesId") Long areaCategoriesId);

    //删除某“区域类别”下所有的对应数据
    int deleteByAreaCategoriesId(@Param("areaCategoriesId") Long areaCategoriesId, @Param("modelId")Long modelId);

    int deleteByAreaId(@Param("areaId") Long areaId,@Param("modelId") Long modelId);

    SurveyChannelModelAreaCity selectByAreaIdAndModelId(Map<String,Object> paramMap);

    int copy(Map<String,Object> paramMap);
}