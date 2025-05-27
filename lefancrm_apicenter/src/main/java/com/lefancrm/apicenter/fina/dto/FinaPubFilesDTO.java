package com.lefancrm.apicenter.fina.dto;

import com.lefancrm.apicenter.fina.model.FinaFile;

import java.util.List;

public class FinaPubFilesDTO {
    private Long enumId;
    private String enumName;
    private List<FinaFile> files;

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

    public List<FinaFile> getFiles() {
        return files;
    }

    public void setFiles(List<FinaFile> files) {
        this.files = files;
    }


    public FinaPubFilesDTO(Long enumId, String enumName, List<FinaFile> files) {
        this.enumId = enumId;
        this.enumName = enumName;
        this.files = files;
    }
}
