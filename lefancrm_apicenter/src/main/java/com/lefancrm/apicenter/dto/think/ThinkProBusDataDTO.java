package com.lefancrm.apicenter.dto.think;

public class ThinkProBusDataDTO {
    private Long orgId;
    private int dataType;//1平台复审  2保司终审
    private int searchType;//1 当前  、2 同比 、  3环比
    private Double money;


    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
