package com.lefancrm.backend.dto;

import java.util.List;

/**
 * Created by lixianfeng on 2019/6/19.
 */
public class SurveySourceScoreDto {
    private String name;
    private Double value;
    private Integer type;

    private List<SurveySourceScoreDetailDto> sourceListMx;//案源机构分值明细  导出的时候存在数据

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<SurveySourceScoreDetailDto> getSourceListMx() {
        return sourceListMx;
    }

    public void setSourceListMx(List<SurveySourceScoreDetailDto> sourceListMx) {
        this.sourceListMx = sourceListMx;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
