package com.lefancrm.apicenter.dto.report;

import com.lefancrm.apicenter.model.SurveyAssignOrg;

import java.util.Date;
import java.util.List;

public class SurveyReportCaseDTO {
    private Long surveyInfoId;
    private Long surveyAssorgCaseId;//机构报表调查员报表点开的时候存在
    private Long surveyInvestigatorCaseId;//调查员报表点开的时候存在

    private String surveyCaseNo;//案件编号
    private Long entrustOrgId;
    private String entrustOrgName;//保险公司
    private String entrustDepartmentName;//部门
    private String surveyPerson;//被调查人
    private String sex;//性别
    private int idType;//证件类型  1身份证 2驾驶证
    private String idNumber;//证件号码
    private Integer age;//年龄
    private int insureType;//保险种类  1、医疗；2、重疾；3、身故；4、其他
    private Date insureTime;//投保日期
    private Date dangerTime;//出险日期
    private String dangerAddress;//出险地点
    private Date insureTakeTime;//保单生效日期
    private String claimsNo;//理赔编号
    private String busName;//领域
    private String serviceName;//业务类型
    private String surveyInfo;//案件基本信息
    private String surveyItem;//调查事项
    private int payType;//结算方式  1:一口价,2:基本费+减损奖励,3:任务,4:任务+减损奖励
    private int investigationArea;//调查区域 1：直辖市（市区），2：直辖市（郊区），3：省会，4：地级市，5：县级市
    private String surveyStateName;//状态
    private Date entrustDate;//委托时间
    private Date lefanReportDate;//复审开始时间
    private Date entrustReportStartDate;//复审通过时间
    private Date entrustReportEndDate;//保司通过时间
    private Date endTime;//调查截止日期
    private int agingDay;//时效天数
    private String reportCompletion;//调查结论
    private Double entrustOkPrice1;//委托方确认结算价格基本费
    private Double entrustOkPrice2;//委托方确认结算价格减损奖励
    private int price1IsCalc;//基本费是否结算
    private int price2IsCalc;//减损奖励是否结算
    private int isSun;//是否阳性

    private int markError;

    private String markErrorRemark;

    private String surveyOrgName;

    private String returnRemark;

    private Double entrustMoney;

    private List<SurveyAssignOrg> surveyOrgs;//分派机构集合

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getSurveyAssorgCaseId() {
        return surveyAssorgCaseId;
    }

    public void setSurveyAssorgCaseId(Long surveyAssorgCaseId) {
        this.surveyAssorgCaseId = surveyAssorgCaseId;
    }

    public Long getSurveyInvestigatorCaseId() {
        return surveyInvestigatorCaseId;
    }

    public void setSurveyInvestigatorCaseId(Long surveyInvestigatorCaseId) {
        this.surveyInvestigatorCaseId = surveyInvestigatorCaseId;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getEntrustDepartmentName() {
        return entrustDepartmentName;
    }

    public void setEntrustDepartmentName(String entrustDepartmentName) {
        this.entrustDepartmentName = entrustDepartmentName;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getInsureType() {
        return insureType;
    }

    public void setInsureType(int insureType) {
        this.insureType = insureType;
    }

    public Date getInsureTime() {
        return insureTime;
    }

    public void setInsureTime(Date insureTime) {
        this.insureTime = insureTime;
    }

    public Date getDangerTime() {
        return dangerTime;
    }

    public void setDangerTime(Date dangerTime) {
        this.dangerTime = dangerTime;
    }

    public String getDangerAddress() {
        return dangerAddress;
    }

    public void setDangerAddress(String dangerAddress) {
        this.dangerAddress = dangerAddress;
    }

    public Date getInsureTakeTime() {
        return insureTakeTime;
    }

    public void setInsureTakeTime(Date insureTakeTime) {
        this.insureTakeTime = insureTakeTime;
    }

    public String getClaimsNo() {
        return claimsNo;
    }

    public void setClaimsNo(String claimsNo) {
        this.claimsNo = claimsNo;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSurveyInfo() {
        return surveyInfo;
    }

    public void setSurveyInfo(String surveyInfo) {
        this.surveyInfo = surveyInfo;
    }

    public String getSurveyItem() {
        return surveyItem;
    }

    public void setSurveyItem(String surveyItem) {
        this.surveyItem = surveyItem;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getInvestigationArea() {
        return investigationArea;
    }

    public void setInvestigationArea(int investigationArea) {
        this.investigationArea = investigationArea;
    }

    public String getSurveyStateName() {
        return surveyStateName;
    }

    public void setSurveyStateName(String surveyStateName) {
        this.surveyStateName = surveyStateName;
    }

    public Date getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }

    public Date getLefanReportDate() {
        return lefanReportDate;
    }

    public void setLefanReportDate(Date lefanReportDate) {
        this.lefanReportDate = lefanReportDate;
    }

    public Date getEntrustReportStartDate() {
        return entrustReportStartDate;
    }

    public void setEntrustReportStartDate(Date entrustReportStartDate) {
        this.entrustReportStartDate = entrustReportStartDate;
    }

    public Date getEntrustReportEndDate() {
        return entrustReportEndDate;
    }

    public void setEntrustReportEndDate(Date entrustReportEndDate) {
        this.entrustReportEndDate = entrustReportEndDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getAgingDay() {
        return agingDay;
    }

    public void setAgingDay(int agingDay) {
        this.agingDay = agingDay;
    }

    public String getReportCompletion() {
        return reportCompletion;
    }

    public void setReportCompletion(String reportCompletion) {
        this.reportCompletion = reportCompletion;
    }

    public Double getEntrustOkPrice1() {
        return entrustOkPrice1;
    }

    public void setEntrustOkPrice1(Double entrustOkPrice1) {
        this.entrustOkPrice1 = entrustOkPrice1;
    }

    public Double getEntrustOkPrice2() {
        return entrustOkPrice2;
    }

    public void setEntrustOkPrice2(Double entrustOkPrice2) {
        this.entrustOkPrice2 = entrustOkPrice2;
    }

    public int getPrice1IsCalc() {
        return price1IsCalc;
    }

    public void setPrice1IsCalc(int price1IsCalc) {
        this.price1IsCalc = price1IsCalc;
    }

    public int getPrice2IsCalc() {
        return price2IsCalc;
    }

    public void setPrice2IsCalc(int price2IsCalc) {
        this.price2IsCalc = price2IsCalc;
    }

    public int getIsSun() {
        return isSun;
    }

    public void setIsSun(int isSun) {
        this.isSun = isSun;
    }

    public List<SurveyAssignOrg> getSurveyOrgs() {
        return surveyOrgs;
    }

    public void setSurveyOrgs(List<SurveyAssignOrg> surveyOrgs) {
        this.surveyOrgs = surveyOrgs;
    }

    public Long getEntrustOrgId() {
        return entrustOrgId;
    }

    public void setEntrustOrgId(Long entrustOrgId) {
        this.entrustOrgId = entrustOrgId;
    }

    public int getMarkError() {
        return markError;
    }

    public void setMarkError(int markError) {
        this.markError = markError;
    }

    public String getMarkErrorRemark() {
        return markErrorRemark;
    }

    public void setMarkErrorRemark(String markErrorRemark) {
        this.markErrorRemark = markErrorRemark;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public String getReturnRemark() {
        return returnRemark;
    }

    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark;
    }

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }
}
