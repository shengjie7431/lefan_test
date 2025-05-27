package com.lefancrm.apicenter.model;

public class CaseMediateDesc {
    private Long id;

    private Long caseId;

    private Integer caseType;

    private String mainDispute;

    private String mediateDesc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public String getMainDispute() {
        return mainDispute;
    }

    public void setMainDispute(String mainDispute) {
        this.mainDispute = mainDispute;
    }

    public String getMediateDesc() {
        return mediateDesc;
    }

    public void setMediateDesc(String mediateDesc) {
        this.mediateDesc = mediateDesc;
    }

}