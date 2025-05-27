package com.lefancrm.apicenter.model;

public class StudioCommissionInfo {
    private Long id;

    private Long levelId;

    private String levelCode;

    private Double comrate;

    private Double studioComrate;

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

    public Double getComrate() {
        return comrate;
    }

    public void setComrate(Double comrate) {
        this.comrate = comrate;
    }

    public Double getStudioComrate() {
        return studioComrate;
    }

    public void setStudioComrate(Double studioComrate) {
        this.studioComrate = studioComrate;
    }
}