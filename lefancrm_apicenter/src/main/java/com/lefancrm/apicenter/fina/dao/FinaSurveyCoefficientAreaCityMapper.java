package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyCoefficientAreaCity;
import com.lefancrm.apicenter.fina.model.FinaSurveyCoefficientModelArea;
import com.lefancrm.apicenter.fina.model.FinaSurveyConsignorEfficiencyAreaCity;
import com.lefancrm.apicenter.model.CommonArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FinaSurveyCoefficientAreaCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyCoefficientAreaCity record);

    int insertSelective(FinaSurveyCoefficientAreaCity record);

    FinaSurveyCoefficientAreaCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyCoefficientAreaCity record);

    int updateByPrimaryKey(FinaSurveyCoefficientAreaCity record);

    //数据
    List<FinaSurveyCoefficientAreaCity> list(Map map);

    int selectAllSelectedCount(@Param("parentIds") String parentIds, @Param("areaCategoriesId") Long areaCategoriesId);
    String selectAllChildrenEfficiency2(@Param("areaId") Long areaId,@Param("getAreaCateGoriesId")  Long getAreaCateGoriesId);

    //删除某“区域类别”下所有的对应数据
    int deleteByAreaCategoriesId(@Param("areaCategoriesId") Long areaCategoriesId, @Param("modelId")Long modelId);

    int deleteByAreaId(@Param("areaId") Long areaId,@Param("modelId") Long modelId);

    List<CommonArea> selectAllChildrenEfficiency(@Param("areaId") Long areaId, @Param("getAreaCateGoriesId")  Long getAreaCateGoriesId, @Param("modelId") Long modelId);

    FinaSurveyCoefficientAreaCity selectByOne(Map map);
}