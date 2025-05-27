package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.InfoPublishsForumFile;

public class InfoPublishsForumFileDto extends InfoPublishsForumFile{
    private String fileType;

    private String filePath;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}