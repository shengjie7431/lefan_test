package com.lefancrm.backend.dto.think;

public class ProThinkDTO {
    private Long proId;
    private String proName;

    //复审
    private Double fsMoney;
    private Double fsMoney1;//同比金额
    private Double fsMoney2;//环比金额
    private Double fsRateTb;
    private Double fsRateHb;

    //终审
    private Double zsMoney;
    private Double zsMoney1;//同比金额
    private Double zsMoney2;//环比金额
    private Double zsRateTb;
    private Double zsRateHb;

    //开票
    private Double kpMoney;
    private Double kpMoney1;//同比金额
    private Double kpMoney2;//环比金额
    private Double kpRateTb;
    private Double kpRateHb;

    //到账
    private Double dzMoney;
    private Double dzMoney1;//同比金额
    private Double dzMoney2;//环比金额
    private Double dzRateTb;
    private Double dzRateHb;

    private Double inMoney;//主营业务成本
    private Double outMoney;//销售费用
    private Double cwMoney;//财务费用

    public ProThinkDTO() {
    }

    public ProThinkDTO(Long proId, String proName) {
        this.proId = proId;
        this.proName = proName;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Double getFsMoney() {
        return fsMoney;
    }

    public void setFsMoney(Double fsMoney) {
        this.fsMoney = fsMoney;
    }

    public Double getFsMoney1() {
        return fsMoney1;
    }

    public void setFsMoney1(Double fsMoney1) {
        this.fsMoney1 = fsMoney1;
    }

    public Double getFsMoney2() {
        return fsMoney2;
    }

    public void setFsMoney2(Double fsMoney2) {
        this.fsMoney2 = fsMoney2;
    }

    public Double getFsRateTb() {
        return fsRateTb;
    }

    public void setFsRateTb(Double fsRateTb) {
        this.fsRateTb = fsRateTb;
    }

    public Double getFsRateHb() {
        return fsRateHb;
    }

    public void setFsRateHb(Double fsRateHb) {
        this.fsRateHb = fsRateHb;
    }

    public Double getZsMoney() {
        return zsMoney;
    }

    public void setZsMoney(Double zsMoney) {
        this.zsMoney = zsMoney;
    }

    public Double getZsMoney1() {
        return zsMoney1;
    }

    public void setZsMoney1(Double zsMoney1) {
        this.zsMoney1 = zsMoney1;
    }

    public Double getZsMoney2() {
        return zsMoney2;
    }

    public void setZsMoney2(Double zsMoney2) {
        this.zsMoney2 = zsMoney2;
    }

    public Double getZsRateTb() {
        return zsRateTb;
    }

    public void setZsRateTb(Double zsRateTb) {
        this.zsRateTb = zsRateTb;
    }

    public Double getZsRateHb() {
        return zsRateHb;
    }

    public void setZsRateHb(Double zsRateHb) {
        this.zsRateHb = zsRateHb;
    }

    public Double getKpMoney() {
        return kpMoney;
    }

    public void setKpMoney(Double kpMoney) {
        this.kpMoney = kpMoney;
    }

    public Double getKpMoney1() {
        return kpMoney1;
    }

    public void setKpMoney1(Double kpMoney1) {
        this.kpMoney1 = kpMoney1;
    }

    public Double getKpMoney2() {
        return kpMoney2;
    }

    public void setKpMoney2(Double kpMoney2) {
        this.kpMoney2 = kpMoney2;
    }

    public Double getKpRateTb() {
        return kpRateTb;
    }

    public void setKpRateTb(Double kpRateTb) {
        this.kpRateTb = kpRateTb;
    }

    public Double getKpRateHb() {
        return kpRateHb;
    }

    public void setKpRateHb(Double kpRateHb) {
        this.kpRateHb = kpRateHb;
    }

    public Double getDzMoney() {
        return dzMoney;
    }

    public void setDzMoney(Double dzMoney) {
        this.dzMoney = dzMoney;
    }

    public Double getDzMoney1() {
        return dzMoney1;
    }

    public void setDzMoney1(Double dzMoney1) {
        this.dzMoney1 = dzMoney1;
    }

    public Double getDzMoney2() {
        return dzMoney2;
    }

    public void setDzMoney2(Double dzMoney2) {
        this.dzMoney2 = dzMoney2;
    }

    public Double getDzRateTb() {
        return dzRateTb;
    }

    public void setDzRateTb(Double dzRateTb) {
        this.dzRateTb = dzRateTb;
    }

    public Double getDzRateHb() {
        return dzRateHb;
    }

    public void setDzRateHb(Double dzRateHb) {
        this.dzRateHb = dzRateHb;
    }

    public Double getInMoney() {
        return inMoney;
    }

    public void setInMoney(Double inMoney) {
        this.inMoney = inMoney;
    }

    public Double getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(Double outMoney) {
        this.outMoney = outMoney;
    }

    public Double getCwMoney() {
        return cwMoney;
    }

    public void setCwMoney(Double cwMoney) {
        this.cwMoney = cwMoney;
    }
}
