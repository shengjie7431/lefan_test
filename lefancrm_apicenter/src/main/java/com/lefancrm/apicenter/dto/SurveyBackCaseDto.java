package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyBackCase;
import com.lefancrm.apicenter.model.SurveyRiskCase;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;

/**
 * Created by lixianfeng on 2019/2/25.
 */
public class SurveyBackCaseDto extends SurveyBackCase{
    private SurveyRiskCase surveyRiskCase;
    private SurveyRiskCaseInfo surveyRiskCaseInfo;
    private Boolean isShow = true; //数据是否显示（模糊查询）

    public SurveyRiskCaseInfo getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfo surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public SurveyRiskCase getSurveyRiskCase() {
        return surveyRiskCase;
    }

    public void setSurveyRiskCase(SurveyRiskCase surveyRiskCase) {
        this.surveyRiskCase = surveyRiskCase;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }
}
