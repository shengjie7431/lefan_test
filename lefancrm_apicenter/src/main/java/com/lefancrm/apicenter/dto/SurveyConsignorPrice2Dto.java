package com.lefancrm.apicenter.dto;

import java.util.ArrayList;
import java.util.List;

public class SurveyConsignorPrice2Dto {

    private Integer areaId;

    private String areaName;

    private Integer cityType; //市区、郊区、省会、地级市、县级市

    private Long enturyId;

    private String enturyName;

    private Integer areaType; //直辖市、非直辖市

    private Double taskPriceMZ;

    private Double taskPriceQY;

    private Double taskPriceSC;

    private Double taskPriceTJ;

    private Double taskPriceKC;

    private Double taskPriceYL;

    private Double taskPriceBL;

    private Double taskPriceSD;

    private Double taskPriceYB;

    private Double taskPriceZF;

    private String strPirce; //sql查出的（任务类型_任务子类_方向结果类型_价格）拼成的数据

    private List<Prices> priceses; //将strPirce数据解析为list

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getCityType() {
        return cityType;
    }

    public void setCityType(Integer cityType) {
        this.cityType = cityType;
    }

    public Long getEnturyId() {
        return enturyId;
    }

    public void setEnturyId(Long enturyId) {
        this.enturyId = enturyId;
    }

    public String getEnturyName() {
        return enturyName;
    }

    public void setEnturyName(String enturyName) {
        this.enturyName = enturyName;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Double getTaskPriceMZ() {
        return taskPriceMZ;
    }

    public void setTaskPriceMZ(Double taskPriceMZ) {
        this.taskPriceMZ = taskPriceMZ;
    }

    public Double getTaskPriceQY() {
        return taskPriceQY;
    }

    public void setTaskPriceQY(Double taskPriceQY) {
        this.taskPriceQY = taskPriceQY;
    }

    public Double getTaskPriceSC() {
        return taskPriceSC;
    }

    public void setTaskPriceSC(Double taskPriceSC) {
        this.taskPriceSC = taskPriceSC;
    }

    public Double getTaskPriceTJ() {
        return taskPriceTJ;
    }

    public void setTaskPriceTJ(Double taskPriceTJ) {
        this.taskPriceTJ = taskPriceTJ;
    }

    public Double getTaskPriceKC() {
        return taskPriceKC;
    }

    public void setTaskPriceKC(Double taskPriceKC) {
        this.taskPriceKC = taskPriceKC;
    }

    public Double getTaskPriceYL() {
        return taskPriceYL;
    }

    public void setTaskPriceYL(Double taskPriceYL) {
        this.taskPriceYL = taskPriceYL;
    }

    public Double getTaskPriceBL() {
        return taskPriceBL;
    }

    public void setTaskPriceBL(Double taskPriceBL) {
        this.taskPriceBL = taskPriceBL;
    }

    public Double getTaskPriceSD() {
        return taskPriceSD;
    }

    public void setTaskPriceSD(Double taskPriceSD) {
        this.taskPriceSD = taskPriceSD;
    }

    public Double getTaskPriceYB() {
        return taskPriceYB;
    }

    public void setTaskPriceYB(Double taskPriceYB) {
        this.taskPriceYB = taskPriceYB;
    }

    public Double getTaskPriceZF() {
        return taskPriceZF;
    }

    public void setTaskPriceZF(Double taskPriceZF) {
        this.taskPriceZF = taskPriceZF;
    }

    public String getStrPirce() {
        return strPirce;
    }

    public void setStrPirce(String strPirce) {
        this.strPirce = strPirce;

        setPriceses();
    }

    public List<Prices> getPriceses() {
        setPriceses();
        return priceses;
    }

    public void setPriceses() {
        List list = new ArrayList();
        String [] strs = this.getStrPirce().split(",");
        for (String str : strs) {
            String [] item = str.split("_");
            if (item[0] == null || item[1] == null || item[2] == null){
                continue;
            }
            Prices prices = new Prices();
            prices.setTaskId(Long.parseLong(item[0]));
            prices.setTaskInfoContentId(Long.parseLong(item[1]));
            prices.setDirectionResultTypeId(Long.parseLong(item[2]));
            prices.setPrice(new Double(item[3] == null ? "0" : item[3]));
            list.add(prices);
        }
        this.priceses = list;
    }

    public class Prices{
        private Long taskId;
        private Long taskInfoContentId;
        private Long directionResultTypeId;
        private Double price;

        public Long getTaskId() {
            return taskId;
        }

        public void setTaskId(Long taskId) {
            this.taskId = taskId;
        }

        public Long getTaskInfoContentId() {
            return taskInfoContentId;
        }

        public void setTaskInfoContentId(Long taskInfoContentId) {
            this.taskInfoContentId = taskInfoContentId;
        }

        public Long getDirectionResultTypeId() {
            return directionResultTypeId;
        }

        public void setDirectionResultTypeId(Long directionResultTypeId) {
            this.directionResultTypeId = directionResultTypeId;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
}
