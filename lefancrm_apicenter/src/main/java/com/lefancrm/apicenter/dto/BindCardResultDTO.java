package com.lefancrm.apicenter.dto;

/**
 * Created by lixianfeng on 2018/7/10.
 */
public class BindCardResultDTO {
    private Long caseId;
    private String customerId;
    private String userIndexCardNo;
    private Boolean success;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserIndexCardNo() {
        return userIndexCardNo;
    }

    public void setUserIndexCardNo(String userIndexCardNo) {
        this.userIndexCardNo = userIndexCardNo;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
