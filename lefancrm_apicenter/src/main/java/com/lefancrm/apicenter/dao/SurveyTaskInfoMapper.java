package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyTaskInfo2Dto;
import com.lefancrm.apicenter.dto.TaskDistribution;
import com.lefancrm.apicenter.model.SurveyTaskDirectionResult;
import com.lefancrm.apicenter.model.SurveyTaskInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;
import java.util.Map;

public interface SurveyTaskInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyTaskInfo record);

    int insertSelective(SurveyTaskInfo record);

    SurveyTaskInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyTaskInfo record);

    int updateByPrimaryKey(SurveyTaskInfo record);

    //数据
    List<SurveyTaskInfo> list(Map map);

    int listSize(Map map);

    /**
     * 根据案件ID 查询  可调度的任务类型列表
     * @param surveyInfoId
     * @return
     */
    List<SurveyTaskInfo> getSurveyTaskInfosBySurveyInfoId(Long surveyInfoId);

    /**
     * 根据案件ID +  方向名称 查询已做过的任务类型集合
     * @param map
     * @return
     */
    List<SurveyTaskInfo> getSurveyTaskInfosBySurveyInfoIdAndDirectionName(Map map);

    /**
     * 任务分布报表
     * @param map
     * @return
     */
    List<TaskDistribution> seletTaskDistribution(Map map);

    List<SurveyTaskInfo2Dto> selectChannelTasks(Map map);

    List<SurveyTaskDirectionResult> getResults(Map<String,Object> paramMap);
}
