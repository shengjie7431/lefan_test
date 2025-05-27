package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.dto.SurveyReInfoDto;
import com.lefancrm.apicenter.model.SurveyClockReInfo;
import com.lefancrm.apicenter.model.SurveyReInfo;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyReInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyReInfo record);

    int insertSelective(SurveyReInfo record);

    SurveyReInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyReInfo record);

    int updateByPrimaryKey(SurveyReInfo record);

    List<SurveyReInfoDto> selectAllListByParam(ApiRequest apiRequest);

    int selectCountAllListByParam(ApiRequest apiRequest);

    SurveyReInfoDto selectReInfo(@Param("reId") Long reId, @Param("orgRole") Boolean orgRole,@Param("orgId") Long orgId);
}