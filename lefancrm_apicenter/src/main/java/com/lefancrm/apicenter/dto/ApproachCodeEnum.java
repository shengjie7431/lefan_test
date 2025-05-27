package com.lefancrm.apicenter.dto;

/**
 * 众安任务类型与乐凡的映射
 */
public enum ApproachCodeEnum {

    Type1("1","出险医疗机构核查",13L,319L,"医疗排查","病历调查+门诊排查"),
    Type2("2","非出险医疗机构排查（习惯就诊医院）",13L,319L,"医疗排查","病历调查+门诊排查"),
    Type3("3","非出险医疗机构排查（专科专病医院）",13L,319L,"医疗排查","病历调查+门诊排查"),
    Type4("4","非出险医疗机构排查（生活工作地医院）",13L,319L,"医疗排查","病历调查+门诊排查"),
    Type5("5","非出险医疗机构排查（户籍地附近医院）",13L,319L,"医疗排查","病历调查+门诊排查"),
    Type6("6","农合或医保排查",10L,306L,"医保调查","城镇医保排查"),
    Type7("7","医疗机构加查重点门诊就诊或（和）特定检查、诊疗项目",13L,318L,"医疗排查","门诊排查"),
    Type8("8","访谈事故者或（及）家属",9L,309L,"走访调查","面见被保险人"),
    Type9("9","访谈相关单位",9L,308L,"走访调查","单位走访"),
    Type10("10","访谈代理人",9L,315L,"走访调查","其他方向走访"),
    Type11("11","访谈代理人",9L,315L,"走访调查","其他方向走访"),
    Type12("12","访谈相关人员",9L,310L,"走访调查","面见目击证人"),
    Type13("13","现场查勘",9L,313L,"走访调查","事发地点走访"),
    Type14("14","要求尸检",9L,317L,"走访调查","鉴定机构走访"),
    Type15("15","委托司法鉴定",9L,317L,"走访调查","鉴定机构走访"),
    Type16("16","模拟实验",9L,315L,"走访调查","其他方向走访"),
    Type17("17","网络核查",9L,315L,"走访调查","其他方向走访"),
    Type18("18","请求警方、安监、消防等部门介入调查",9L,312L,"走访调查","公检法走访"),
    Type19("19","联合调查",9L,315L,"走访调查","其他方向走访"),
    Type20("20","其他",9L,315L,"走访调查","其他方向走访");

    private String approachCode;
    private String approachCodeName;
    private Long taskId;
    private Long newId;
    private String taskName;
    private String newName;


    ApproachCodeEnum(String approachCode, String approachCodeName, Long taskId, Long newId, String taskName, String newName) {
        this.approachCode = approachCode;
        this.approachCodeName = approachCodeName;
        this.taskId = taskId;
        this.newId = newId;
        this.taskName = taskName;
        this.newName = newName;
    }

    public String getApproachCode() {
        return approachCode;
    }

    public void setApproachCode(String approachCode) {
        this.approachCode = approachCode;
    }

    public String getApproachCodeName() {
        return approachCodeName;
    }

    public void setApproachCodeName(String approachCodeName) {
        this.approachCodeName = approachCodeName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getNewId() {
        return newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public static ApproachCodeEnum getEnumNameByEnumId(String approachCode){
        ApproachCodeEnum[] values = ApproachCodeEnum.values();
        for (ApproachCodeEnum value : values) {
            if (approachCode.equals(value.getApproachCode())) {
                return value;
            }
        }
        return null;
    }
}
