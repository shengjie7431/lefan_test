package com.lefancrm.backend.dto;

import com.lefancrm.backend.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class HoboProCaseTemplate {

    @Excel(name = "客户名称",excelIndex = 0)
    private String insureName;

    @Excel(name = "部门",excelIndex = 1)
    private String insureDeptName;

    @Excel(name = "姓名",excelIndex = 2)
    private String name;

    @Excel(name = "身份证号",excelIndex = 3)
    private String idCard;

    @Excel(name = "手机号",excelIndex = 4)
    private String tel;

    @Excel(name = "车牌号",excelIndex = 5)
    private String carNo;

    @Excel(name = "产品名称",excelIndex = 6)
    private String proName;

    public String getInsureName() {
        return insureName;
    }

    public void setInsureName(String insureName) {
        this.insureName = insureName;
    }

    public String getInsureDeptName() {
        return insureDeptName;
    }

    public void setInsureDeptName(String insureDeptName) {
        this.insureDeptName = insureDeptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}