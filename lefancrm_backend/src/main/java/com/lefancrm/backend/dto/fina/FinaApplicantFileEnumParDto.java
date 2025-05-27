package com.lefancrm.backend.dto.fina;

import java.util.List;

public class FinaApplicantFileEnumParDto {

    private Long enumId;//枚举类名
    private String enumName;//枚举类名
    private String enumNameCode;//枚举类名
    private Integer fileNum;

    private List<FinaApplicantFileEnumDto> sons;

    public Long getEnumId() {
        return enumId;
    }

    public void setEnumId(Long enumId) {
        this.enumId = enumId;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getEnumNameCode() {
        return enumNameCode;
    }

    public void setEnumNameCode(String enumNameCode) {
        this.enumNameCode = enumNameCode;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public List<FinaApplicantFileEnumDto> getSons() {
        return sons;
    }

    public void setSons(List<FinaApplicantFileEnumDto> sons) {
        this.sons = sons;
    }
}