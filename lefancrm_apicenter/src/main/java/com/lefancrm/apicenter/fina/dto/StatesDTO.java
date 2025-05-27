package com.lefancrm.apicenter.fina.dto;

public class StatesDTO {
    private int value;
    private String valueName;
    private Boolean checked;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public StatesDTO(){}
    public StatesDTO(int value, String valueName,Boolean checked) {
        this.value = value;
        this.valueName = valueName;
        this.checked = checked;
    }
}
