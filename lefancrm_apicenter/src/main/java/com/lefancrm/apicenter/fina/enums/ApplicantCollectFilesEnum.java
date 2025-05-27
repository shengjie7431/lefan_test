package com.lefancrm.apicenter.fina.enums;

/**
*垫付材料收集枚举
*/
public enum ApplicantCollectFilesEnum {
    APPLICANT_COLLECT_FILES_SFZZFM(1L,"身份证正反面"),
    APPLICANT_COLLECT_FILES_YBKZFM(2L,"医保卡正反面"),
    APPLICANT_COLLECT_FILES_MZBL(3L,"门诊病历"),
    APPLICANT_COLLECT_FILES_JCBG(4L,"检查报告"),
    APPLICANT_COLLECT_FILES_ZYZ(5L,"住院证"),
    APPLICANT_COLLECT_FILES_JFTZD(6L,"缴费通知单"),
    APPLICANT_COLLECT_FILES_YYZH(7L,"医院账号"),
    APPLICANT_COLLECT_FILES_RYJJ(8L,"入院记录"),
    APPLICANT_COLLECT_FILES_BBXRZP(9L,"被保险人照片"),
    APPLICANT_COLLECT_FILES_QTZL(10L,"其他资料");

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

    ApplicantCollectFilesEnum(Long enumId, String enumName) {
        this.enumId = enumId;
        this.enumName = enumName;
    }

    public static String getEnumNameByEnumId(int state){
        ApplicantCollectFilesEnum[] values = ApplicantCollectFilesEnum.values();
        for (ApplicantCollectFilesEnum value : values) {
            if (value.getEnumId() == state) {
                return value.enumName;
            }
        }
        return null;
    }
}
