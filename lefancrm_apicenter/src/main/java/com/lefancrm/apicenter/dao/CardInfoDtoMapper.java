package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CardInfoDto;

public interface CardInfoDtoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CardInfoDto record);

    int insertSelective(CardInfoDto record);

    CardInfoDto selectByPrimaryKey(Long id);

    CardInfoDto selectByCaseId1(Long caseId);

    CardInfoDto selectByInfo(CardInfoDto cardInfoDto);

    CardInfoDto selectByCustomerId(String customerId);

    int updateByPrimaryKeySelective(CardInfoDto record);

    int updateByPrimaryKey(CardInfoDto record);
}