package com.lefancrm.backend.dto;

import com.lefancrm.backend.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class HoboProCase {
    private Long id;

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

    private Long proId;

    @Excel(name = "产品名称",excelIndex = 6)
    private String proName;

    @Excel(name = "产品单价",excelIndex = 7)
    private BigDecimal proPrice;

    @Excel(name = "产品内容",excelIndex = 8)
    private String proText;

    @Excel(name = "委托时间",excelIndex = 9, dateFormat = "yyyy-MM-dd")
    private Date entrustTime;

    private Long deptId;

    @Excel(name = "主体",excelIndex = 10)
    private String deptName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getProPrice() {
        return proPrice;
    }

    public void setProPrice(BigDecimal proPrice) {
        this.proPrice = proPrice;
    }

    public String getProText() {
        return proText;
    }

    public void setProText(String proText) {
        this.proText = proText;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}