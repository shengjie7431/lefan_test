package com.lefancrm.apicenter.fina.dto;

import com.lefancrm.apicenter.fina.model.FinaFileSettlement;

import java.util.List;

public class SettlementFilesDTO {
    private Long enumId;
    private String enumName;
    private List<FinaFileSettlement> fileSettlements;

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

    public List<FinaFileSettlement> getFileSettlements() {
        return fileSettlements;
    }

    public void setFileSettlements(List<FinaFileSettlement> fileSettlements) {
        this.fileSettlements = fileSettlements;
    }

    public SettlementFilesDTO(Long enumId, String enumName) {
        this.enumId = enumId;
        this.enumName = enumName;
    }

    public SettlementFilesDTO(Long enumId, String enumName, List<FinaFileSettlement> fileSettlements) {
        this.enumId = enumId;
        this.enumName = enumName;
        this.fileSettlements = fileSettlements;
    }
}
