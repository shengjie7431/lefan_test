package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.LawFeeDetailDto;
import com.lefancrm.apicenter.model.LawFeeDetail;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface LawFeeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LawFeeDetail record);

    int insertSelective(LawFeeDetail record);

    LawFeeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LawFeeDetail record);

    int updateByPrimaryKey(LawFeeDetail record);

    //获取list和count
    int findListSize(ApiRequest apiRequest);
    List<LawFeeDetailDto> findList(ApiRequest apiRequest);

    LawFeeDetailDto selectByInfo(ApiRequest apiRequest);
}