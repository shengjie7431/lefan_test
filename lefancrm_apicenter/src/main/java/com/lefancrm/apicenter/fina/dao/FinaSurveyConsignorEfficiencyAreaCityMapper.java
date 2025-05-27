package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyConsignorEfficiencyAreaCity;
import com.lefancrm.apicenter.model.CommonArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FinaSurveyConsignorEfficiencyAreaCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyConsignorEfficiencyAreaCity record);

    int insertSelective(FinaSurveyConsignorEfficiencyAreaCity record);

    FinaSurveyConsignorEfficiencyAreaCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyConsignorEfficiencyAreaCity record);

    int updateByPrimaryKey(FinaSurveyConsignorEfficiencyAreaCity record);


    //数据
    List<FinaSurveyConsignorEfficiencyAreaCity> list(Map map);
    int listSize(Map map);

    //删除某“区域类别”下所有的对应数据
    int deleteByAreaCategoriesId(@Param("areaCategoriesId") Long areaCategoriesId, @Param("modelId")Long modelId);

    int deleteByAreaId(@Param("areaId") Long areaId,@Param("modelId") Long modelId);

    int selectAllSelectedCount(@Param("parentIds") String parentIds,@Param("areaCategoriesId") Long areaCategoriesId);

    String selectAllChildrenEfficiency2(@Param("areaId") Long areaId,@Param("getAreaCateGoriesId")  Long getAreaCateGoriesId);

    List<CommonArea> selectAllChildrenEfficiency(@Param("areaId") Long areaId, @Param("getAreaCateGoriesId")  Long getAreaCateGoriesId, @Param("modelId") Long modelId);


    int copyPirce(Map<String,Object> paramMap);
}