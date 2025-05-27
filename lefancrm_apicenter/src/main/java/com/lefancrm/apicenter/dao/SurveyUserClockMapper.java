package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.dto.SurveyUserClockDto;
import com.lefancrm.apicenter.dto.report.SurveyClockDTO;
import com.lefancrm.apicenter.model.SurveyClockCase;
import com.lefancrm.apicenter.model.SurveyClockReInfo;
import com.lefancrm.apicenter.model.SurveyReInfo;
import com.lefancrm.apicenter.model.SurveyUserClock;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyUserClockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyUserClock record);

    int insertSelective(SurveyUserClock record);

    SurveyUserClockDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyUserClock record);

    int updateByPrimaryKey(SurveyUserClock record);

    List<SurveyUserClockDto> selectByParam(ApiRequest apiRequest);

    List<SurveyUserClockDto> selectByParamFromPre(ApiRequest apiRequest);

    int updateReIdForBatch(SurveyReInfo surveyReInfo);

    int selectClockCaseCount(Long reInfoId);

    /**
     * 根据条件查询调查员打卡记录
     * @param map
     * @return
     */
    List<SurveyUserClockDto> selectByMap(Map map);

    List<SurveyUserClockDto> selectPunchTheClock(Map map);

    int selectByUserIdAndDate(Long userId);

    /**
     * 根据案件id查询打卡记录
     * @param surveyInfoId
     * @return
     */
    List<SurveyUserClockDto> selectCaseClockDetails(Integer surveyInfoId);



    List<SurveyClockDTO> clocks(Map<String,Object> paramMap);
}