package com.lefancrm.apicenter.model;

public class CaseFileMid {
    private Long id;

    private Long caseId;

    private Long fileId;

    private Long catalogId;

    private String catalogName;

    private CommonFile commonFile;

    private String caseNo;

    private Integer type;//材料的类型  1、pdf文件

    private Boolean isDownLoad = true; //是否可下载（原因：部分图片是在32服务器上，现在项目更改部署，放在了47上，通过流的方式无法下载。2019年6月13日11:36:05）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public CommonFile getCommonFile() {
        return commonFile;
    }

    public void setCommonFile(CommonFile commonFile) {
        this.commonFile = commonFile;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsDownLoad() {
        return isDownLoad;
    }

    public void setIsDownLoad(Boolean isDownLoad) {
        this.isDownLoad = isDownLoad;
    }
}