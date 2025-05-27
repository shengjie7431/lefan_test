package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyPriceModelAreaCategories;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface FinaSurveyPriceModelAreaCategoriesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyPriceModelAreaCategories record);

    int insertSelective(FinaSurveyPriceModelAreaCategories record);

    FinaSurveyPriceModelAreaCategories selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyPriceModelAreaCategories record);

    int updateByPrimaryKey(FinaSurveyPriceModelAreaCategories record);

    //数据
    List<FinaSurveyPriceModelAreaCategories> list(Map map);
    int listSize(Map map);

    int copyOneByPriceModelId(@Param("oldPriceModelId") Long oldPriceModelId, @Param("newPriceModelId") Long newPriceModelId);

    List<FinaSurveyPriceModelAreaCategories> selectByPriceModelId(@Param("newPriceModelId") Long id,@Param("oldPriceModelId") Long oldPriceModelId);

    void deleteByPriceModelId(Long id);
}