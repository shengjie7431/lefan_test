package com.lefancrm.backend.dto;

public class QualifiedManpowerDto {
    private Long id;

    private Long levelId;

    private String levelCode;

    private Integer qualifiedMpNum;

    private Double qualifiedMpMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public Integer getQualifiedMpNum() {
        return qualifiedMpNum;
    }

    public void setQualifiedMpNum(Integer qualifiedMpNum) {
        this.qualifiedMpNum = qualifiedMpNum;
    }

    public Double getQualifiedMpMoney() {
        return qualifiedMpMoney;
    }

    public void setQualifiedMpMoney(Double qualifiedMpMoney) {
        this.qualifiedMpMoney = qualifiedMpMoney;
    }
}