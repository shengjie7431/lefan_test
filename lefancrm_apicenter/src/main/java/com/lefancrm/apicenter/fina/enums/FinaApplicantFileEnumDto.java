package com.lefancrm.apicenter.fina.enums;

import com.lefancrm.apicenter.fina.model.FinaApplicantFile;

import java.util.List;

public class FinaApplicantFileEnumDto {

    private Long enumId;//枚举类名
    private String enumName;//枚举类名
    private String enumNameCode;//枚举类名
    private Integer fileNum;

    private List<FinaApplicantFile> files;//包含文件

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

    public List<FinaApplicantFile> getFiles() {
        return files;
    }

    public void setFiles(List<FinaApplicantFile> files) {
        this.files = files;
    }

    public FinaApplicantFileEnumDto(Long enumId, String enumName, List<FinaApplicantFile> files) {
        this.enumId = enumId;
        this.enumName = enumName;
        this.files = files;
    }
}