package com.lefancrm.apicenter.model;

import com.lefancrm.apicenter.dto.staff.StaffPerformanceInfoDTO;

import java.util.Date;
import java.util.List;

public class StaffPerformance {
    private Long id;

    private String workTime;

    private Integer performanceState;

    private Long hrId;

    private String hrName;

    private Long financeId;

    private String financeName;

    private Long generalManagerId;

    private String generalManagerName;

    private Date hrTime;

    private Date financeTime;

    private Date generalManagerTime;

    private String queryPassword;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private String reason;

    private String divsionOne;
    private String divsionTwo;
    private String divsionThree;

    private List<StaffPerformanceInfoDTO> data;

    private StaffPerformancePersonnel staffPerformancePersonnel;

    private String roleCode;

    private String performanceStateName;

    private Boolean hrRole;
    private Boolean financeRole;
    private Boolean division;
    private Boolean ceoRole;
    private Boolean hrManageRole;
    private Boolean organManagerRole;//机构经理
    private Boolean superiorManagerRole;//分管总
    private Boolean surveyUserRole;//调查员

    private Long hrManageId;//人事主管
    private String hrManageName;
    private Date hrManageTime;

    private String organManagerInfo;    //机构经理拼接数据
    private String superiorManagerInfo; //分管总二审拼接数据
    private String firstSuperiorManagerInfo; //分管总一审拼接数据

    private List<OrganManagerList> organManagerList;        //所有的机构经理数据
    private List<SuperiorManagerList> superiorManagerList;  //所有的分管总二审数据
    private List<SuperiorManagerList> firstSuperiorManagerList;  //所有的分管总一审数据

    private Integer backState;

    private StaffPerformanceManager staffPerformanceManager ;//绩效--管理人员审核状态
    private String timeRemaining;//剩余时间（机构经理看到的数据）

    private Date superiorManagerFirstTime;//分管总一审 完成时间

    public StaffPerformance() {
    }

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

    public Integer getPerformanceState() {
        return performanceState;
    }

    public void setPerformanceState(Integer performanceState) {
        this.performanceState = performanceState;
    }

    public Long getHrId() {
        return hrId;
    }

    public void setHrId(Long hrId) {
        this.hrId = hrId;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public Long getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Long financeId) {
        this.financeId = financeId;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public Long getGeneralManagerId() {
        return generalManagerId;
    }

    public void setGeneralManagerId(Long generalManagerId) {
        this.generalManagerId = generalManagerId;
    }

    public String getGeneralManagerName() {
        return generalManagerName;
    }

    public void setGeneralManagerName(String generalManagerName) {
        this.generalManagerName = generalManagerName;
    }

    public Date getHrTime() {
        return hrTime;
    }

    public void setHrTime(Date hrTime) {
        this.hrTime = hrTime;
    }

    public Date getFinanceTime() {
        return financeTime;
    }

    public void setFinanceTime(Date financeTime) {
        this.financeTime = financeTime;
    }

    public Date getGeneralManagerTime() {
        return generalManagerTime;
    }

    public void setGeneralManagerTime(Date generalManagerTime) {
        this.generalManagerTime = generalManagerTime;
    }

    public String getQueryPassword() {
        return queryPassword;
    }

    public void setQueryPassword(String queryPassword) {
        this.queryPassword = queryPassword;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<StaffPerformanceInfoDTO> getData() {
        return data;
    }

    public void setData(List<StaffPerformanceInfoDTO> data) {
        this.data = data;
    }

    public String getDivsionOne() {
        return divsionOne;
    }

    public void setDivsionOne(String divsionOne) {
        this.divsionOne = divsionOne;
    }

    public String getDivsionTwo() {
        return divsionTwo;
    }

    public void setDivsionTwo(String divsionTwo) {
        this.divsionTwo = divsionTwo;
    }

    public String getDivsionThree() {
        return divsionThree;
    }

    public void setDivsionThree(String divsionThree) {
        this.divsionThree = divsionThree;
    }

    public StaffPerformancePersonnel getStaffPerformancePersonnel() {
        return staffPerformancePersonnel;
    }

    public void setStaffPerformancePersonnel(StaffPerformancePersonnel staffPerformancePersonnel) {
        this.staffPerformancePersonnel = staffPerformancePersonnel;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getPerformanceStateName() {
        return performanceStateName;
    }

    public void setPerformanceStateName(String performanceStateName) {
        this.performanceStateName = performanceStateName;
    }

    public Boolean getHrRole() {
        return hrRole;
    }

    public void setHrRole(Boolean hrRole) {
        this.hrRole = hrRole;
    }

    public Boolean getFinanceRole() {
        return financeRole;
    }

    public void setFinanceRole(Boolean financeRole) {
        this.financeRole = financeRole;
    }

    public Boolean getDivision() {
        return division;
    }

    public void setDivision(Boolean division) {
        this.division = division;
    }

    public Boolean getCeoRole() {
        return ceoRole;
    }

    public void setCeoRole(Boolean ceoRole) {
        this.ceoRole = ceoRole;
    }

    public Boolean getHrManageRole() {
        return hrManageRole;
    }

    public void setHrManageRole(Boolean hrManageRole) {
        this.hrManageRole = hrManageRole;
    }

    public Long getHrManageId() {
        return hrManageId;
    }

    public void setHrManageId(Long hrManageId) {
        this.hrManageId = hrManageId;
    }

    public Date getHrManageTime() {
        return hrManageTime;
    }

    public void setHrManageTime(Date hrManageTime) {
        this.hrManageTime = hrManageTime;
    }

    public String getHrManageName() {
        return hrManageName;
    }

    public void setHrManageName(String hrManageName) {
        this.hrManageName = hrManageName;
    }

    public class OrganManagerList {
        public String userName;
        public String state;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public class SuperiorManagerList {
        public String userName;
        public String state;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public String getOrganManagerInfo() {
        return organManagerInfo;
    }

    public void setOrganManagerInfo(String organManagerInfo) {
        this.organManagerInfo = organManagerInfo;
    }

    public String getSuperiorManagerInfo() {
        return superiorManagerInfo;
    }

    public void setSuperiorManagerInfo(String superiorManagerInfo) {
        this.superiorManagerInfo = superiorManagerInfo;
    }

    public List<OrganManagerList> getOrganManagerList() {
        return organManagerList;
    }

    public void setOrganManagerList(List<OrganManagerList> organManagerList) {
        this.organManagerList = organManagerList;
    }

    public List<SuperiorManagerList> getSuperiorManagerList() {
        return superiorManagerList;
    }

    public void setSuperiorManagerList(List<SuperiorManagerList> superiorManagerList) {
        this.superiorManagerList = superiorManagerList;
    }

    public Boolean getOrganManagerRole() {
        return organManagerRole;
    }

    public void setOrganManagerRole(Boolean organManagerRole) {
        this.organManagerRole = organManagerRole;
    }

    public Boolean getSuperiorManagerRole() {
        return superiorManagerRole;
    }

    public void setSuperiorManagerRole(Boolean superiorManagerRole) {
        this.superiorManagerRole = superiorManagerRole;
    }

    public Integer getBackState() {
        return backState;
    }

    public void setBackState(Integer backState) {
        this.backState = backState;
    }

    public StaffPerformanceManager getStaffPerformanceManager() {
        return staffPerformanceManager;
    }

    public void setStaffPerformanceManager(StaffPerformanceManager staffPerformanceManager) {
        this.staffPerformanceManager = staffPerformanceManager;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public Boolean getSurveyUserRole() {
        return surveyUserRole;
    }

    public void setSurveyUserRole(Boolean surveyUserRole) {
        this.surveyUserRole = surveyUserRole;
    }

    public String getFirstSuperiorManagerInfo() {
        return firstSuperiorManagerInfo;
    }

    public void setFirstSuperiorManagerInfo(String firstSuperiorManagerInfo) {
        this.firstSuperiorManagerInfo = firstSuperiorManagerInfo;
    }

    public List<SuperiorManagerList> getFirstSuperiorManagerList() {
        return firstSuperiorManagerList;
    }

    public void setFirstSuperiorManagerList(List<SuperiorManagerList> firstSuperiorManagerList) {
        this.firstSuperiorManagerList = firstSuperiorManagerList;
    }

    public Date getSuperiorManagerFirstTime() {
        return superiorManagerFirstTime;
    }

    public void setSuperiorManagerFirstTime(Date superiorManagerFirstTime) {
        this.superiorManagerFirstTime = superiorManagerFirstTime;
    }
}