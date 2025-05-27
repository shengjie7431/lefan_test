package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.CaseFileMid;

/**
 * Created by wanjun on 2017-05-19.
 */
public class CaseFileMidDto extends CaseFileMid{




    private String fileName;
    private String filePath;

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
}
