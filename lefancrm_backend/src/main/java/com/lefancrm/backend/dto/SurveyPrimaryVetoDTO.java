package com.lefancrm.backend.dto;

public class SurveyPrimaryVetoDTO {
    private Long id;//案件ID （机构ID）
    private String name;//名称
    private int type;//1机构 2调查员

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
