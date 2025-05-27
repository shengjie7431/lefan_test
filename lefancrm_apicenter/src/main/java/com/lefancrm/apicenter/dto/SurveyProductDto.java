package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.*;

import java.util.List;

/**
 * Created by zhuxia on 2019/1/11.
 */
public class SurveyProductDto extends SurveyProduct {

    private List<SurveyProductLevel> levels;

    private List<SurveyProductRole> roles;

    public List<SurveyProductLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<SurveyProductLevel> levels) {
        this.levels = levels;
    }

    public List<SurveyProductRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SurveyProductRole> roles) {
        this.roles = roles;
    }
}
