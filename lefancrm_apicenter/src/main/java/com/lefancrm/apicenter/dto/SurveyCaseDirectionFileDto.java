package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyCaseDirectionFile;
import com.lefancrm.apicenter.model.CommonFile;


/**
 * Created by lixianfeng on 2019/2/23.
 */
public class SurveyCaseDirectionFileDto extends SurveyCaseDirectionFile{
    private CommonFile commonFile;

    private Integer fileType;//路径后缀类型

    public CommonFile getCommonFile() {
        return commonFile;
    }

    public void setCommonFile(CommonFile commonFile) {
        this.commonFile = commonFile;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}
