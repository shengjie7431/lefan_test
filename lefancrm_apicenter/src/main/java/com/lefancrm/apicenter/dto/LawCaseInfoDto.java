package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.LawCaseInfo;
import org.springframework.util.StringUtils;

public class LawCaseInfoDto extends LawCaseInfo{
    private Boolean isAssess = false;//是否评估师
    private Boolean isComplex  = false;//是否综合内勤
    private Boolean isAssessSuper  = false;//是否评估主管
    private Boolean isAssessManager  = false;//是否评估经理

    public Boolean getIsAssess() {
        return isAssess;
    }

    public void setIsAssess(Boolean isAssess) {
        this.isAssess = isAssess;
    }

    public Boolean getIsComplex() {
        return isComplex;
    }

    public void setIsComplex(Boolean isComplex) {
        this.isComplex = isComplex;
    }

    public Boolean getIsAssessSuper() {
        return isAssessSuper;
    }

    public void setIsAssessSuper(Boolean isAssessSuper) {
        this.isAssessSuper = isAssessSuper;
    }

    public Boolean getIsAssessManager() {
        return isAssessManager;
    }

    public void setIsAssessManager(Boolean isAssessManager) {
        this.isAssessManager = isAssessManager;
    }
}