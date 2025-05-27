package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CommonFineEnum;

import java.util.List;
import java.util.Map;

public interface CommonFineEnumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommonFineEnum record);

    int insertSelective(CommonFineEnum record);

    CommonFineEnum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommonFineEnum record);

    int updateByPrimaryKey(CommonFineEnum record);

    /**
     * 扣罚案件清单列表
     *
     */
    List<CommonFineEnum> selectCommonFineEnumList(Map<String, Object> paramMap);
    int selectCommonFineEnumListSize(Map<String, Object> paramMap);
}