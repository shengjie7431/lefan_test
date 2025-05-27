package com.lefancrm.backend.dto.avg;

public class SurveyHuzhuDTO {
    private int lineNum;
    private int caseNum;
    private Double caseAvg;
    private int longTimeNum;
    private Double longTimeAvg;
    private Double longTimeRate;
    private int vetoCaseNum;
    private Double vetoCaseAvg;
    private int vetoNum;
    private Double vetoAvg;
    private Double vetoRate;
    private int commitEdNum;

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(int caseNum) {
        this.caseNum = caseNum;
    }

    public Double getCaseAvg() {
        return caseAvg;
    }

    public void setCaseAvg(Double caseAvg) {
        this.caseAvg = caseAvg;
    }

    public int getLongTimeNum() {
        return longTimeNum;
    }

    public void setLongTimeNum(int longTimeNum) {
        this.longTimeNum = longTimeNum;
    }

    public Double getLongTimeAvg() {
        return longTimeAvg;
    }

    public void setLongTimeAvg(Double longTimeAvg) {
        this.longTimeAvg = longTimeAvg;
    }

    public Double getLongTimeRate() {
        return longTimeRate;
    }

    public void setLongTimeRate(Double longTimeRate) {
        this.longTimeRate = longTimeRate;
    }

    public int getVetoCaseNum() {
        return vetoCaseNum;
    }

    public void setVetoCaseNum(int vetoCaseNum) {
        this.vetoCaseNum = vetoCaseNum;
    }

    public Double getVetoCaseAvg() {
        return vetoCaseAvg;
    }

    public void setVetoCaseAvg(Double vetoCaseAvg) {
        this.vetoCaseAvg = vetoCaseAvg;
    }

    public int getVetoNum() {
        return vetoNum;
    }

    public void setVetoNum(int vetoNum) {
        this.vetoNum = vetoNum;
    }

    public Double getVetoAvg() {
        return vetoAvg;
    }

    public void setVetoAvg(Double vetoAvg) {
        this.vetoAvg = vetoAvg;
    }

    public Double getVetoRate() {
        return vetoRate;
    }

    public void setVetoRate(Double vetoRate) {
        this.vetoRate = vetoRate;
    }

    public int getCommitEdNum() {
        return commitEdNum;
    }

    public void setCommitEdNum(int commitEdNum) {
        this.commitEdNum = commitEdNum;
    }
}
