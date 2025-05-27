package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.LoanApplication;

import java.util.Date;

public class LoanApplicationDto extends LoanApplication {

    private String accidentCityName;

    public String getAccidentCityName() {
        return accidentCityName;
    }

    public void setAccidentCityName(String accidentCityName) {
        this.accidentCityName = accidentCityName;
    }
}