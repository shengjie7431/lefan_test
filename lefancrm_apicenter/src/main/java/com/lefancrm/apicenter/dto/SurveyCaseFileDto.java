package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyCaseFile;
import com.lefancrm.apicenter.model.CommonFile;

public class SurveyCaseFileDto extends SurveyCaseFile{
    private String fileName;
    private String filePath;
    private CommonFile commonFile;

    private Integer fileType;//路径后缀类型

    private String upLoadTime;//上传时间（使用场景：查看资料页面，上传时间展示）

    public CommonFile getCommonFile() {
        return commonFile;
    }

    public void setCommonFile(CommonFile commonFile) {
        this.commonFile = commonFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getUpLoadTime() {
        return upLoadTime;
    }

    public void setUpLoadTime(String upLoadTime) {
        this.upLoadTime = upLoadTime;
    }
}