package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyReimbursementFile;

import java.util.List;


public class SurveyReimbursementFileDto extends SurveyReimbursementFile {
    private int medhCount;
    private int trouCount;
    private int printCount;
    private int accoCount;
    private int carCount;
    private int trainCount;
    private int aircraftCount;
    private int selfCount;
    private int otherCount;
    private int innerCount;

    public int getInnerCount() {
        return innerCount;
    }

    public void setInnerCount(int innerCount) {
        this.innerCount = innerCount;
    }

    public int getMedhCount() {
        return medhCount;
    }

    public void setMedhCount(int medhCount) {
        this.medhCount = medhCount;
    }

    public int getTrouCount() {
        return trouCount;
    }

    public void setTrouCount(int trouCount) {
        this.trouCount = trouCount;
    }

    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(int printCount) {
        this.printCount = printCount;
    }

    public int getAccoCount() {
        return accoCount;
    }

    public void setAccoCount(int accoCount) {
        this.accoCount = accoCount;
    }

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public int getTrainCount() {
        return trainCount;
    }

    public void setTrainCount(int trainCount) {
        this.trainCount = trainCount;
    }

    public int getAircraftCount() {
        return aircraftCount;
    }

    public void setAircraftCount(int aircraftCount) {
        this.aircraftCount = aircraftCount;
    }

    public int getSelfCount() {
        return selfCount;
    }

    public void setSelfCount(int selfCount) {
        this.selfCount = selfCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(int otherCount) {
        this.otherCount = otherCount;
    }
}