package com.lefancrm.apicenter.dto.think;

import java.util.List;

public class ProThinkDTO {
    private Long proId;
    private String proName;

    //复审
    private Double fsMoney;
    private Double fsMoneyTb;//同比金额
    private Double fsMoneyHb;//环比金额
    private Double fsRateTb;
    private Double fsRateHb;

    //终审
    private Double zsMoney;
    private Double zsMoneyTb;//同比金额
    private Double zsMoneyHb;//环比金额
    private Double zsRateTb;
    private Double zsRateHb;

    //开票
    private Double kpMoney;
    private Double kpMoneyTb;//同比金额
    private Double kpMoneyHb;//环比金额
    private Double kpRateTb;
    private Double kpRateHb;

    //到账
    private Double dzMoney;
    private Double dzMoneyTb;//同比金额
    private Double dzMoneyHb;//环比金额
    private Double dzRateTb;
    private Double dzRateHb;

    private Double inMoney;//主营业务成本
    private ThinkProDataDTO detail;

    private Double outMoney;//销售费用
    private ThinkOutDataDTO outDetail;

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

    public Double getFsMoneyTb() {
        return fsMoneyTb;
    }

    public void setFsMoneyTb(Double fsMoneyTb) {
        this.fsMoneyTb = fsMoneyTb;
    }

    public Double getFsMoneyHb() {
        return fsMoneyHb;
    }

    public void setFsMoneyHb(Double fsMoneyHb) {
        this.fsMoneyHb = fsMoneyHb;
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

    public Double getZsMoneyTb() {
        return zsMoneyTb;
    }

    public void setZsMoneyTb(Double zsMoneyTb) {
        this.zsMoneyTb = zsMoneyTb;
    }

    public Double getZsMoneyHb() {
        return zsMoneyHb;
    }

    public void setZsMoneyHb(Double zsMoneyHb) {
        this.zsMoneyHb = zsMoneyHb;
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

    public Double getKpMoneyTb() {
        return kpMoneyTb;
    }

    public void setKpMoneyTb(Double kpMoneyTb) {
        this.kpMoneyTb = kpMoneyTb;
    }

    public Double getKpMoneyHb() {
        return kpMoneyHb;
    }

    public void setKpMoneyHb(Double kpMoneyHb) {
        this.kpMoneyHb = kpMoneyHb;
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

    public Double getDzMoneyTb() {
        return dzMoneyTb;
    }

    public void setDzMoneyTb(Double dzMoneyTb) {
        this.dzMoneyTb = dzMoneyTb;
    }

    public Double getDzMoneyHb() {
        return dzMoneyHb;
    }

    public void setDzMoneyHb(Double dzMoneyHb) {
        this.dzMoneyHb = dzMoneyHb;
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

    public ThinkProDataDTO getDetail() {
        return detail;
    }

    public void setDetail(ThinkProDataDTO detail) {
        this.detail = detail;
    }

    public ThinkOutDataDTO getOutDetail() {
        return outDetail;
    }

    public void setOutDetail(ThinkOutDataDTO outDetail) {
        this.outDetail = outDetail;
    }
}
