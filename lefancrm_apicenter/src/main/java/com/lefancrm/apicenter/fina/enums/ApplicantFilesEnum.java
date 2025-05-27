package com.lefancrm.apicenter.fina.enums;

/**
 *垫付立案枚举
 */
public enum ApplicantFilesEnum {
    APPLICANT_FILES_BLZL(1L,"病历资料"),
    APPLICANT_FILES_SFZ(2L,"身份证"),
    APPLICANT_FILES_RYTZS(3L,"入院通知书"),
    APPLICANT_FILES_JFTZS(4L,"缴费通知书"),
    APPLICANT_FILES_YBK(5L,"医保卡");

    private Long enumId;
    private String enumName;

    public Long getEnumId() {
        return enumId;
    }

    public void setEnumId(Long enumId) {
        this.enumId = enumId;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    ApplicantFilesEnum(Long enumId, String enumName) {
        this.enumId = enumId;
        this.enumName = enumName;
    }

    public static String getEnumNameByEnumId(int state){
        ApplicantFilesEnum[] values = ApplicantFilesEnum.values();
        for (ApplicantFilesEnum value : values) {
            if (value.getEnumId() == state) {
                return value.enumName;
            }
        }
        return null;
    }
}
