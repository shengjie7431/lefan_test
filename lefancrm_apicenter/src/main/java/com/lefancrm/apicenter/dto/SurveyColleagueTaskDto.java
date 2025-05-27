package com.lefancrm.apicenter.dto;

import java.util.List;

/**
 * 同事的任务
 * @author EDZ
 */
public class SurveyColleagueTaskDto {

    /**
     * 同事任务
     */
    private List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDto;

    /**
     * 同事方向
     */
    private List<SurveyCaseDirectionDto> surveyCaseDirectionDto;

    public List<SurveyInvestigatorCaseDto> getSurveyInvestigatorCaseDto() {
        return surveyInvestigatorCaseDto;
    }

    public void setSurveyInvestigatorCaseDto(List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDto) {
        this.surveyInvestigatorCaseDto = surveyInvestigatorCaseDto;
    }

    public List<SurveyCaseDirectionDto> getSurveyCaseDirectionDto() {
        return surveyCaseDirectionDto;
    }

    public void setSurveyCaseDirectionDto(List<SurveyCaseDirectionDto> surveyCaseDirectionDto) {
        this.surveyCaseDirectionDto = surveyCaseDirectionDto;
    }
}
