package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.LawFile;
import com.lefancrm.apicenter.model.CommonFile;

public class LawFileDto extends LawFile {

    private CommonFile commonFile;

    private Integer documentType;

    public CommonFile getCommonFile() {
        return commonFile;
    }

    public void setCommonFile(CommonFile commonFile) {
        this.commonFile = commonFile;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }
}