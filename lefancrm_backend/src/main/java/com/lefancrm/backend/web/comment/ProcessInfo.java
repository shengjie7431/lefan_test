package com.lefancrm.backend.web.comment;

import com.lefancrm.backend.dto.SurveyUploadFileDto;

import java.io.Serializable;
import java.util.List;

public class ProcessInfo implements Serializable {
    public long totalSize = 1;
    public long readSize = 0;
    public String show = "";
    public int itemNum = 0;
    public int rate = 0;
    private List<SurveyUploadFileDto> surveyFiles;

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getReadSize() {
        return readSize;
    }

    public void setReadSize(long readSize) {
        this.readSize = readSize;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<SurveyUploadFileDto> getSurveyFiles() {
        return surveyFiles;
    }

    public void setSurveyFiles(List<SurveyUploadFileDto> surveyFiles) {
        this.surveyFiles = surveyFiles;
    }

    @Override
    public String toString() {
        return "ProcessInfo{" +
                "totalSize=" + totalSize +
                ", readSize=" + readSize +
                ", show='" + show + '\'' +
                ", itemNum=" + itemNum +
                ", rate=" + rate +
                '}';
    }
}
