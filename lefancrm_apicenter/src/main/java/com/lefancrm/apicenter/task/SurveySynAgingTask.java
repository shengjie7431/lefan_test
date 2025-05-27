package com.lefancrm.apicenter.task;

import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyAssignOrgDto;
import com.lefancrm.apicenter.dto.SurveyInvestigatorCaseDto;
import com.lefancrm.apicenter.dto.SurveyRiskCaseInfoDto;
import com.lefancrm.apicenter.model.SurveyAssignOrg;
import com.lefancrm.apicenter.model.SurveyOrgPrescriptionFlow;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;
import com.lefancrm.apicenter.util.AgingDayUtil;
import com.lefancrm.apicenter.util.GetWorkDay;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SurveySynAgingTask implements Serializable {

    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyOrgPrescriptionFlowMapper surveyOrgPrescriptionFlowMapper;
    @Autowired
    private SurveyUserPrescriptionFlowMapper surveyUserPrescriptionFlowMapper;

    /**
     * 同步机构案件时效
     * @return
     */
    public ApiResponse synOrg(){
        //获取所有未提交的数据
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("statusData",0);//未提交  map.put("statusData",1);//所有
        List<SurveyAssignOrgDto> list = surveyAssignOrgMapper.list(map);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        for (SurveyAssignOrgDto item : list) {
            if (simpleDateFormat.format(item.getOrgEndTime()).compareTo(simpleDateFormat.format(now))<0){
                int agingOver = GetWorkDay.calLeaveDays(item.getOrgEndTime(), now, item.getEfficiencyAttr());
                item.setAgingOver((double)Math.abs(agingOver));
                surveyAssignOrgMapper.updateByPrimaryKey(item);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 同步调查员案件时效
     * @return
     */
    public ApiResponse synSurvey(){
        ApiRequest map =  new ApiRequest();
        map.put("statusData",0);//未提交  map.put("statusData",1);//所有
        List<SurveyInvestigatorCaseDto> list = surveyInvestigatorCaseMapper.list(map);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        for (SurveyInvestigatorCaseDto item : list) {
            if (simpleDateFormat.format(item.getSurveyEndTime()).compareTo(simpleDateFormat.format(now))<0){
                int agingOver = GetWorkDay.calLeaveDays(item.getSurveyEndTime(), now, item.getEfficiencyAttr());
                item.setAgingOver((double)Math.abs(agingOver));
                surveyInvestigatorCaseMapper.updateByPrimaryKey(item);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }
}
