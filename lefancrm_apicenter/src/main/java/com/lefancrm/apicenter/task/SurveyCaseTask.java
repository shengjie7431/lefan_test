package com.lefancrm.apicenter.task;

import com.lefancrm.apicenter.backendapi.impl.BackendWechatApiImpl;
import com.lefancrm.apicenter.dao.SurveyInvestigatorCaseMapper;
import com.lefancrm.apicenter.dto.SurveyInvestigatorCaseDto;
import com.lefancrm.apicenter.model.SurveyInvestigatorCase;
import com.lefancrm.apicenter.model.SurveyInvestigatorReInfo;
import com.lefancrm.apicenter.util.SendMessageUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2019/1/29.
 */
@Service
public class SurveyCaseTask {

    @Autowired
    private SurveyCaseTaskService surveyCaseTaskService;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;


    /**
     *每隔24小时就执行
     */
    public void surveyCaseTaskTimer(){
        surveyCaseTaskService.surveyUser();
        surveyCaseTaskService.surveyOrg();
    }

    /**
     *每隔10分钟就执行（重新加载通知中心--铃铛的显示）
     */
    public void surveyMessageTimer(){

    }

    public void http(){
        Boolean success = surveyCaseTaskService.http();
        if (!success){
            try {
                SendMessageUntil.assignmentSurveyCase("13651981861","李贤丰",new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"系统挂了","系统挂了", "系统挂了");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 调查员每日案件提醒
     */
    public void everyDayCaseRemind(){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("tomorrow", LocalDate.now().plusDays(1).toString());
        List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtos = surveyInvestigatorCaseMapper.selectOrgCaseRemindList(paramMap);
        for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto : surveyInvestigatorCaseDtos) {
            Map<String, Object> msgMap = new HashMap<String, Object>();
            msgMap.put("title", "工作提醒");
            msgMap.put("content", "超时提醒");
            msgMap.put("keyWords", "提醒内容：你有" + surveyInvestigatorCaseDto.getCaseNum() + "个任务还有一天超时，请点击此消息对调查情况进行快速回复！" +"\n"+"被调查人："+surveyInvestigatorCaseDto.getSurveyPerson());
            msgMap.put("path", "pages/claimsman/case/caseListAgency");
            backendWechatApi.send(surveyInvestigatorCaseDto.getSurveyUserId(), msgMap);
        }
    }


}
