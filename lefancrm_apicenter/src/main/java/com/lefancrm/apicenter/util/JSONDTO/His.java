package com.lefancrm.apicenter.util.JSONDTO;

import java.util.List;

public class His {
    private String hospitalId;
    private String hospitalName;//医院名称
    private String hospitalProvince;//省
    private String hospitalCity;//市
    private String hospitalDetailAddress;//详细地址
    private String hospitalLevel;//等级
    private String hospitalBusinessNature;//性质
    private String investStatus;//调查状态(Y/N)
    private String [] investMethods;//调查方式(NULL（全部）,["LIVE"],["REMOTE", "LIVE"])
    private String investMethodsStr;
    private String hospitalInvestAbility;//调查能力(OUTPATIENT_AND_INPATIENT(住院及门诊),OUTPATIENT门诊,INPATIENT住院,全部NULL)
    private String [] evidenceFormats;//证据格式  ["MEDICAL_RECORD"盖章病例, "BILL_OF_CHARGES" 病例清单, "SCREEN_BEAT"屏拍, "SOUND_RECORDING"录音]
    private String evidenceFormatsStr;
    private String investRemark;//备注说明


    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalProvince() {
        return hospitalProvince;
    }

    public void setHospitalProvince(String hospitalProvince) {
        this.hospitalProvince = hospitalProvince;
    }

    public String getHospitalCity() {
        return hospitalCity;
    }

    public void setHospitalCity(String hospitalCity) {
        this.hospitalCity = hospitalCity;
    }

    public String getHospitalDetailAddress() {
        return hospitalDetailAddress;
    }

    public void setHospitalDetailAddress(String hospitalDetailAddress) {
        this.hospitalDetailAddress = hospitalDetailAddress;
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getHospitalBusinessNature() {
        return hospitalBusinessNature;
    }

    public void setHospitalBusinessNature(String hospitalBusinessNature) {
        this.hospitalBusinessNature = hospitalBusinessNature;
    }

    public String getInvestStatus() {
        return investStatus;
    }

    public void setInvestStatus(String investStatus) {
        this.investStatus = investStatus;
    }

    public String[] getInvestMethods() {
        return investMethods;
    }

    public void setInvestMethods(String[] investMethods) {
        this.investMethods = investMethods;
    }

    public String getHospitalInvestAbility() {
        return hospitalInvestAbility;
    }

    public void setHospitalInvestAbility(String hospitalInvestAbility) {
        this.hospitalInvestAbility = hospitalInvestAbility;
    }

    public String[] getEvidenceFormats() {
        return evidenceFormats;
    }

    public void setEvidenceFormats(String[] evidenceFormats) {
        this.evidenceFormats = evidenceFormats;
    }

    public String getInvestRemark() {
        return investRemark;
    }

    public void setInvestRemark(String investRemark) {
        this.investRemark = investRemark;
    }

    public String getInvestMethodsStr() {
        return investMethodsStr;
    }

    public void setInvestMethodsStr(String investMethodsStr) {
        this.investMethodsStr = investMethodsStr;
    }

    public String getEvidenceFormatsStr() {
        return evidenceFormatsStr;
    }

    public void setEvidenceFormatsStr(String evidenceFormatsStr) {
        this.evidenceFormatsStr = evidenceFormatsStr;
    }
}
