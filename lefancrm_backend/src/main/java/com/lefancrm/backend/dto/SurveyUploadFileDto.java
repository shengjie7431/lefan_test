package com.lefancrm.backend.dto;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by lixianfeng on 2019/1/24.
 */
public class SurveyUploadFileDto {
    private Boolean folder;// 是否是文件夹 true 是  false 否
    private int fileSize; // 如果是文件夹  则获取 子文件数量
    private String fileName;//文件名称  报告.doc 123.png
    private String filePath;//文件路径
    private String realFilePath;//实际文件路径
    private String fileExt; //文件后缀名

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

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public Boolean getFolder() {
        return folder;
    }

    public void setFolder(Boolean folder) {
        this.folder = folder;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getRealFilePath() {
        return realFilePath;
    }

    public void setRealFilePath(String realFilePath) {
        this.realFilePath = realFilePath;
    }
}
