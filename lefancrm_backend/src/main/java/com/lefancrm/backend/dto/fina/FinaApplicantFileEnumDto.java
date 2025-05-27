package com.lefancrm.backend.dto.fina;

import java.util.List;

public class FinaApplicantFileEnumDto {

    private String enumId;//枚举类名
    private String enumName;//枚举类名
    private String enumNameCode;//枚举类名
    private Integer fileNum;

    private List<FinaApplicantFileEnumDto> childEnums;// 子类集合

    public String getEnumId() {
        return enumId;
    }

    public void setEnumId(String enumId) {
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

    public List<FinaApplicantFileEnumDto> getChildEnums() {
        return childEnums;
    }

    public void setChildEnums(List<FinaApplicantFileEnumDto> childEnums) {
        this.childEnums = childEnums;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }
}