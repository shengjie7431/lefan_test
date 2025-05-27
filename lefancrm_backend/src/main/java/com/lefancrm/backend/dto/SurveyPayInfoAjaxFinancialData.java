package com.lefancrm.backend.dto;

import com.lefancrm.backend.dto.financial.FinancialFileDto;
import com.lefancrm.backend.dto.financial.FinancialReProgresDto;

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
    private List<FinancialFileDto> files;
    private List<FinancialReProgresDto> progress;

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

    public List<FinancialFileDto> getFiles() {
        return files;
    }

    public void setFiles(List<FinancialFileDto> files) {
        this.files = files;
    }

    public List<FinancialReProgresDto> getProgress() {
        return progress;
    }

    public void setProgress(List<FinancialReProgresDto> progress) {
        this.progress = progress;
    }
}
