package com.lefancrm.apicenter.backendapi.impl;

import cn.jpush.api.utils.StringUtils;
import com.lefancrm.apicenter.backendapi.SurveyCaseWorkflowApi;
import com.lefancrm.apicenter.dao.SurveyCaseWorkflowMapper;
import com.lefancrm.apicenter.model.SurveyCaseWorkflow;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateCaleUtil;
import com.lefancrm.apicenter.util.GetWorkDay;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianfeng on 2018/12/20.
 */
@Service
@ApiService(descript = "调查工作流")
public class BackendSurveyCaseWorkflowApiImpl extends BaseServiceImpl implements SurveyCaseWorkflowApi{
    @Autowired
    private SurveyCaseWorkflowMapper surveyCaseWorkflowMapper;

    @Override
    public int addSurveyCaseWorkflow(String stepName,UserInfo userInfo,Date startTime,Date endTime,Long surveyInfoId,Long surveyId){
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        SurveyCaseWorkflow workflow = new SurveyCaseWorkflow();
        workflow.setStepName(stepName);
        workflow.setDealUserId(userInfo.getUserId());
        workflow.setDealUserName(userInfo.getUserName());
        workflow.setStartTime(startTime);
        workflow.setEndTime(endTime);

//        int days = GetWorkDay.calLeaveDays(startTime,endTime,1);
//        Long startTimeL = workflow.getStartTime().getTime();
//        Long endTimeL = workflow.getEndTime().getTime();
        Long hours = DateCaleUtil.getDiffHours(startTime,endTime);
//        Long hours = (endTimeL - startTimeL) % nd / nh;
//        if (hours == 0){//不满一小时按一小时算
//            hours += 1;
//        }
        workflow.setHours(hours);
        workflow.setSurveyId(surveyId);
        workflow.setSurveyInfoId(surveyInfoId);
        return surveyCaseWorkflowMapper.insert(workflow);
    }


    @ApiMethod(needLogin = false,descript = "时效",value = "list-survey-workflow")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("id");
        Map<String,Long> map =  new HashMap<String,Long>();
        map.put("surveyInfoId",surveyInfoId);
        List<SurveyCaseWorkflow> list = surveyCaseWorkflowMapper.list(map);
        for (SurveyCaseWorkflow surveyCaseWorkflow : list) {
            Long hours = surveyCaseWorkflow.getHours();
            if ( hours != null){
                Long day = hours / 24;
                Long min = hours - hours / 24 * 24;
//                if (day == 0){
//                    surveyCaseWorkflow.setShowTimeStr(String.format("%s小时",min));
//                }
//                if (min == 0){
//                    surveyCaseWorkflow.setShowTimeStr(String.format("%s天",day));
//                }
                surveyCaseWorkflow.setShowTimeStr(String.format("%s天%s小时", day , min));
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }
}
