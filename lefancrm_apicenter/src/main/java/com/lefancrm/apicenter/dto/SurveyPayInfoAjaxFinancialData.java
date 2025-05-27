package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.FinancialFile;
import com.lefancrm.apicenter.model.FinancialReProgres;

import java.util.List;

public class SurveyPayInfoAjaxFinancialData {
    private String title;
    private String littleTitle;
    private String applyTime;
    private String sno;
    private String applyUserName;
    private String departments;
    private String reason;
    private Double money;
    private String payTime;
    private String receiveUserName;
    private String receiveUserAcc;
    private String remark;
    private List<FinancialFile> files;
    private List<FinancialReProgres> progress;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLittleTitle() {
        return littleTitle;
    }

    public void setLittleTitle(String littleTitle) {
        this.littleTitle = littleTitle;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getReceiveUserAcc() {
        return receiveUserAcc;
    }

    public void setReceiveUserAcc(String receiveUserAcc) {
        this.receiveUserAcc = receiveUserAcc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<FinancialFile> getFiles() {
        return files;
    }

    public void setFiles(List<FinancialFile> files) {
        this.files = files;
    }

    public List<FinancialReProgres> getProgress() {
        return progress;
    }

    public void setProgress(List<FinancialReProgres> progress) {
        this.progress = progress;
    }
}
