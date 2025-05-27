package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelModelAreaCity;
import com.lefancrm.apicenter.model.SurveyScoreModelAreaCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyScoreModelAreaCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyScoreModelAreaCity record);

    int insertSelective(SurveyScoreModelAreaCity record);

    SurveyScoreModelAreaCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyScoreModelAreaCity record);

    int updateByPrimaryKey(SurveyScoreModelAreaCity record);



    //数据
    List<SurveyScoreModelAreaCity> list(Map map);

    int listSize(Map map);

    int selectAllSelectedCount(@Param("parentIds") String parentIds, @Param("areaCategoriesId") Long areaCategoriesId);

    //删除某“区域类别”下所有的对应数据
    int deleteByAreaCategoriesId(@Param("areaCategoriesId") Long areaCategoriesId, @Param("modelId")Long modelId);

    int deleteByAreaId(@Param("areaId") Long areaId,@Param("modelId") Long modelId);

    SurveyScoreModelAreaCity selectByAreaIdAndModelId(Map<String,Object> paramMap);

}