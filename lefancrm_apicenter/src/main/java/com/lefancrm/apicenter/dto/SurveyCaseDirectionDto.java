package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.*;

import java.util.List;

/**
 * Created by lixianfeng on 2019/2/21.
 */
public class SurveyCaseDirectionDto extends SurveyCaseDirection {
    private String realDirectionName;//用于删除服务器端的方向附件（此数据与服务器端方向名称比较）
    private String huzhuColsRemark;
    private String huzhuSunStr;

    private String directionInfo;
    private String itemDateStr;
    private String itemCityStr;
    private String itemContext;
    private String cityStr;
    private String addressStr;
    private String money1;
    private String money2;
    private String margeStr;
    private List<SurveyCaseDirectionFileDto> surveyCaseDirectionFiles;//方向附件清单
    private Integer surveyCaseDirectionFilesSize;
    private SurveyRiskCaseInfo surveyRiskCaseInfo;
    private SurveyInvestigatorCaseDto surveyInvestigatorCase;
    private String taskColor;//任务类型的颜色
    private String newColor;//任务子类的颜色
    private Boolean review;
    private Boolean showExpenseReimbursementValue;//是否显示费用报销数值
    private String expenseReimbursementValue;//费用报销数值
    private List<SurveyReimbursementFileDto> surveyReimbursementFileDtoList;


    /**
     * 总数
     */
    private Integer count;

    /**
     * 地区名称Id
     */
    private String regionNameId;

    /**
     * 地区名称
     */
    private String regionName;

    /**
     * 占比
     */
    private Double proportion;

    /**
     * 是否存在下一级
     */
    private String subordinate;

    private List<SurveyTaskInfoContent> taskInfoContents;

    private List<SurveyTaskDirectionResult> resultTypes;

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public SurveyInvestigatorCaseDto getSurveyInvestigatorCase() {
        return surveyInvestigatorCase;
    }

    public void setSurveyInvestigatorCase(SurveyInvestigatorCaseDto surveyInvestigatorCase) {
        this.surveyInvestigatorCase = surveyInvestigatorCase;
    }

    public List<SurveyCaseDirectionFileDto> getSurveyCaseDirectionFiles() {
        return surveyCaseDirectionFiles;
    }

    public void setSurveyCaseDirectionFiles(List<SurveyCaseDirectionFileDto> surveyCaseDirectionFiles) {
        this.surveyCaseDirectionFiles = surveyCaseDirectionFiles;
    }

    public String getDirectionInfo() {
        return directionInfo;
    }

    public void setDirectionInfo(String directionInfo) {
        this.directionInfo = directionInfo;
    }

    public String getItemDateStr() {
        return itemDateStr;
    }

    public void setItemDateStr(String itemDateStr) {
        this.itemDateStr = itemDateStr;
    }

    public String getItemCityStr() {
        return itemCityStr;
    }

    public void setItemCityStr(String itemCityStr) {
        this.itemCityStr = itemCityStr;
    }

    public String getItemContext() {
        return itemContext;
    }

    public void setItemContext(String itemContext) {
        this.itemContext = itemContext;
    }

    public String getCityStr() {
        return cityStr;
    }

    public void setCityStr(String cityStr) {
        this.cityStr = cityStr;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    public String getMoney1() {
        return money1;
    }

    public void setMoney1(String money1) {
        this.money1 = money1;
    }

    public String getMoney2() {
        return money2;
    }

    public void setMoney2(String money2) {
        this.money2 = money2;
    }

    public String getMargeStr() {
        return margeStr;
    }

    public void setMargeStr(String margeStr) {
        this.margeStr = margeStr;
    }

    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    public String getNewColor() {
        return newColor;
    }

    public void setNewColor(String newColor) {
        this.newColor = newColor;
    }

    public String getHuzhuColsRemark() {
        return huzhuColsRemark;
    }

    public void setHuzhuColsRemark(String huzhuColsRemark) {
        this.huzhuColsRemark = huzhuColsRemark;
    }

    public String getHuzhuSunStr() {
        return huzhuSunStr;
    }

    public void setHuzhuSunStr(String huzhuSunStr) {
        this.huzhuSunStr = huzhuSunStr;
    }

    public SurveyRiskCaseInfo getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfo surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public Boolean getReview() {
        return review;
    }

    public void setReview(Boolean review) {
        this.review = review;
    }
    public Boolean getShowExpenseReimbursementValue() {
        return showExpenseReimbursementValue;
    }

    public void setShowExpenseReimbursementValue(Boolean showExpenseReimbursementValue) {
        this.showExpenseReimbursementValue = showExpenseReimbursementValue;
    }

    public String getExpenseReimbursementValue() {
        return expenseReimbursementValue;
    }

    public void setExpenseReimbursementValue(String expenseReimbursementValue) {
        this.expenseReimbursementValue = expenseReimbursementValue;
    }

    public List<SurveyReimbursementFileDto> getSurveyReimbursementFileDtoList() {
        return surveyReimbursementFileDtoList;
    }

    public void setSurveyReimbursementFileDtoList(List<SurveyReimbursementFileDto> surveyReimbursementFileDtoList) {
        this.surveyReimbursementFileDtoList = surveyReimbursementFileDtoList;
    }

    public Integer getSurveyCaseDirectionFilesSize() {
        return surveyCaseDirectionFilesSize;
    }

    public void setSurveyCaseDirectionFilesSize(Integer surveyCaseDirectionFilesSize) {
        this.surveyCaseDirectionFilesSize = surveyCaseDirectionFilesSize;
    }

    public String getRegionNameId() {
        return regionNameId;
    }

    public void setRegionNameId(String regionNameId) {
        this.regionNameId = regionNameId;
    }

    public String getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(String subordinate) {
        this.subordinate = subordinate;
    }

    public String getRealDirectionName() {
        return realDirectionName;
    }

    public void setRealDirectionName(String realDirectionName) {
        this.realDirectionName = realDirectionName;
    }

    public List<SurveyTaskInfoContent> getTaskInfoContents() {
        return taskInfoContents;
    }

    public void setTaskInfoContents(List<SurveyTaskInfoContent> taskInfoContents) {
        this.taskInfoContents = taskInfoContents;
    }

    public List<SurveyTaskDirectionResult> getResultTypes() {
        return resultTypes;
    }

    public void setResultTypes(List<SurveyTaskDirectionResult> resultTypes) {
        this.resultTypes = resultTypes;
    }
}
