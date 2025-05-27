package com.lefancrm.backend.dto;

import java.util.Date;

public class CommonEnumDto {
    private Long id;

    private String enumCode;

    private String enumName;

    private String enumText;

    private Long parentId;

    private Date createTime;

    private String createBy;

    private Boolean selected;

    private Integer num;

    private Double accMoney;//到账金额
    private Double imgMoney;//开票金额
    private Double accSum;//到账总金额
    private Double imgSum;//开票总金额
    private Double receMoney;//应收账款金额
    private String dateTime;//时间

    private Double unmatchMoney;//待分配回款

    private Double monthImgMoneyHBRate;//本月开票金额环比
    private Double monthImgMoneyTBRate;//本月开票金额同比

    private Double monthAccMoneyHBRate;//本月到账金额环比
    private Double monthAccMoneyTBRate;//本月到账金额同比

    private Double yearImgMoneyYear;//本年开票金额
    private Double yearImgMoneyTBRate;//本年开票金额同比

    private Double yearAccMoneyYear;//本年到账金额
    private Double yearAccMoneyTBRate;//本年到账金额同比

    private Double hisReceMoneyYear;//历史应收金额 ：上年的应收账款余额 + 开票总额（本年一月至本年被选中的月份） - 到账总额 （本年一月至本年被选中的月份）
    private Double hisReceMoneyTBRate;//历史应收金额同比

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getEnumText() {
        return enumText;
    }

    public void setEnumText(String enumText) {
        this.enumText = enumText;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }

    public Double getImgMoney() {
        return imgMoney;
    }

    public void setImgMoney(Double imgMoney) {
        this.imgMoney = imgMoney;
    }

    public Double getAccSum() {
        return accSum;
    }

    public void setAccSum(Double accSum) {
        this.accSum = accSum;
    }

    public Double getImgSum() {
        return imgSum;
    }

    public void setImgSum(Double imgSum) {
        this.imgSum = imgSum;
    }

    public Double getReceMoney() {
        return receMoney;
    }

    public void setReceMoney(Double receMoney) {
        this.receMoney = receMoney;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getUnmatchMoney() {
        return unmatchMoney;
    }

    public void setUnmatchMoney(Double unmatchMoney) {
        this.unmatchMoney = unmatchMoney;
    }

    public Double getMonthImgMoneyHBRate() {
        return monthImgMoneyHBRate;
    }

    public void setMonthImgMoneyHBRate(Double monthImgMoneyHBRate) {
        this.monthImgMoneyHBRate = monthImgMoneyHBRate;
    }

    public Double getMonthImgMoneyTBRate() {
        return monthImgMoneyTBRate;
    }

    public void setMonthImgMoneyTBRate(Double monthImgMoneyTBRate) {
        this.monthImgMoneyTBRate = monthImgMoneyTBRate;
    }

    public Double getMonthAccMoneyHBRate() {
        return monthAccMoneyHBRate;
    }

    public void setMonthAccMoneyHBRate(Double monthAccMoneyHBRate) {
        this.monthAccMoneyHBRate = monthAccMoneyHBRate;
    }

    public Double getMonthAccMoneyTBRate() {
        return monthAccMoneyTBRate;
    }

    public void setMonthAccMoneyTBRate(Double monthAccMoneyTBRate) {
        this.monthAccMoneyTBRate = monthAccMoneyTBRate;
    }

    public Double getYearImgMoneyYear() {
        return yearImgMoneyYear;
    }

    public void setYearImgMoneyYear(Double yearImgMoneyYear) {
        this.yearImgMoneyYear = yearImgMoneyYear;
    }

    public Double getYearImgMoneyTBRate() {
        return yearImgMoneyTBRate;
    }

    public void setYearImgMoneyTBRate(Double yearImgMoneyTBRate) {
        this.yearImgMoneyTBRate = yearImgMoneyTBRate;
    }

    public Double getYearAccMoneyYear() {
        return yearAccMoneyYear;
    }

    public void setYearAccMoneyYear(Double yearAccMoneyYear) {
        this.yearAccMoneyYear = yearAccMoneyYear;
    }

    public Double getYearAccMoneyTBRate() {
        return yearAccMoneyTBRate;
    }

    public void setYearAccMoneyTBRate(Double yearAccMoneyTBRate) {
        this.yearAccMoneyTBRate = yearAccMoneyTBRate;
    }

    public Double getHisReceMoneyYear() {
        return hisReceMoneyYear;
    }

    public void setHisReceMoneyYear(Double hisReceMoneyYear) {
        this.hisReceMoneyYear = hisReceMoneyYear;
    }

    public Double getHisReceMoneyTBRate() {
        return hisReceMoneyTBRate;
    }

    public void setHisReceMoneyTBRate(Double hisReceMoneyTBRate) {
        this.hisReceMoneyTBRate = hisReceMoneyTBRate;
    }
}