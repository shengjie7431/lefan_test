package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyPayInfoDTO;
import com.lefancrm.apicenter.model.SurveyCaseDirection;
import com.lefancrm.apicenter.model.SurveyPayInfo;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyPayInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyPayInfo record);

    int insertSelective(SurveyPayInfo record);

    SurveyPayInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyPayInfo record);

    int updateByPrimaryKey(SurveyPayInfo record);

    int updateByKeyId(@Param("payState") Long payState,@Param("keyId") Long keyId);

    List<SurveyPayInfoDTO> list(ApiRequest map);
    int listSize(ApiRequest map);

    SurveyPayInfoDTO selectByInfo(Map map);//根据机构 查询 未到账记录

    SurveyPayInfoDTO selectByPaySurveyUserId(Long surveyUserId);//根据调查员查询 待确认到账记录

    SurveyPayInfo selectByPayKeyId(Long payKeyId);

    SurveyPayInfo selectByShortUrl(String shortUrl);

    /**
     * 某一个机构的支出合计成本
     * @param paramMap
     * @return
     */
    Double zcMoney(Map<String,Object> paramMap);
}