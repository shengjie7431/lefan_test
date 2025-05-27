package com.lefancrm.apicenter.dto.staff;

public class StaffPersonnelCostAnalysisDTO {
    private Long id;

    private String workTime;

    private Long socialSecurityCompanyId;

    private String socialSecurityCompany;

    private Long companyId;

    private String company;

    private Long organId;

    private String organ;

    private Long departmentId;

    private String department;

    private String team;

    private Long teamId;

    private Integer personnelNum;

    private Double wagesPaySub;

    private Double fixedPerfPay;

    private Double assesPerfPay;

    private Double travelAllowancePay;

    private Double integralPay;

    private Double welfarePay;

    private Double quitCost;

    private Double companyMoneySub;

    private Double personnelCostSub;

    private Integer monthBeginPersonnelNum;

    private Integer monthEndPersonnelNum;

    private Integer quitPersonnelNum;

    private Double quitRate;

    private String contidion;//筛选条件拼接(公司-部门-科室-组别)

    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public Long getSocialSecurityCompanyId() {
        return socialSecurityCompanyId;
    }

    public void setSocialSecurityCompanyId(Long socialSecurityCompanyId) {
        this.socialSecurityCompanyId = socialSecurityCompanyId;
    }

    public String getSocialSecurityCompany() {
        return socialSecurityCompany;
    }

    public void setSocialSecurityCompany(String socialSecurityCompany) {
        this.socialSecurityCompany = socialSecurityCompany;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getPersonnelNum() {
        return personnelNum;
    }

    public void setPersonnelNum(Integer personnelNum) {
        this.personnelNum = personnelNum;
    }

    public Double getWagesPaySub() {
        return wagesPaySub;
    }

    public void setWagesPaySub(Double wagesPaySub) {
        this.wagesPaySub = wagesPaySub;
    }

    public Double getFixedPerfPay() {
        return fixedPerfPay;
    }

    public void setFixedPerfPay(Double fixedPerfPay) {
        this.fixedPerfPay = fixedPerfPay;
    }

    public Double getAssesPerfPay() {
        return assesPerfPay;
    }

    public void setAssesPerfPay(Double assesPerfPay) {
        this.assesPerfPay = assesPerfPay;
    }

    public Double getTravelAllowancePay() {
        return travelAllowancePay;
    }

    public void setTravelAllowancePay(Double travelAllowancePay) {
        this.travelAllowancePay = travelAllowancePay;
    }

    public Double getIntegralPay() {
        return integralPay;
    }

    public void setIntegralPay(Double integralPay) {
        this.integralPay = integralPay;
    }

    public Double getWelfarePay() {
        return welfarePay;
    }

    public void setWelfarePay(Double welfarePay) {
        this.welfarePay = welfarePay;
    }

    public Double getQuitCost() {
        return quitCost;
    }

    public void setQuitCost(Double quitCost) {
        this.quitCost = quitCost;
    }

    public Double getCompanyMoneySub() {
        return companyMoneySub;
    }

    public void setCompanyMoneySub(Double companyMoneySub) {
        this.companyMoneySub = companyMoneySub;
    }

    public Double getPersonnelCostSub() {
        return personnelCostSub;
    }

    public void setPersonnelCostSub(Double personnelCostSub) {
        this.personnelCostSub = personnelCostSub;
    }

    public Integer getMonthBeginPersonnelNum() {
        return monthBeginPersonnelNum;
    }

    public void setMonthBeginPersonnelNum(Integer monthBeginPersonnelNum) {
        this.monthBeginPersonnelNum = monthBeginPersonnelNum;
    }

    public Integer getMonthEndPersonnelNum() {
        return monthEndPersonnelNum;
    }

    public void setMonthEndPersonnelNum(Integer monthEndPersonnelNum) {
        this.monthEndPersonnelNum = monthEndPersonnelNum;
    }

    public Integer getQuitPersonnelNum() {
        return quitPersonnelNum;
    }

    public void setQuitPersonnelNum(Integer quitPersonnelNum) {
        this.quitPersonnelNum = quitPersonnelNum;
    }

    public Double getQuitRate() {
        return quitRate;
    }

    public void setQuitRate(Double quitRate) {
        this.quitRate = quitRate;
    }

    public String getContidion() {
        return contidion;
    }

    public void setContidion(String contidion) {
        this.contidion = contidion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}