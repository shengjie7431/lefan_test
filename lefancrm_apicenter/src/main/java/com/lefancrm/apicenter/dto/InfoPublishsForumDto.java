package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.InfoPublishsForum;
import com.lefancrm.apicenter.model.InfoPublishsForumFile;

import java.util.List;

/**
 * Created by lixianfeng on 2018/11/22.
 */
public class InfoPublishsForumDto extends InfoPublishsForum{
    private List<InfoPublishsForumFileDto> infoPublishsForumFiles;

    public List<InfoPublishsForumFileDto> getInfoPublishsForumFiles() {
        return infoPublishsForumFiles;
    }

    public void setInfoPublishsForumFiles(List<InfoPublishsForumFileDto> infoPublishsForumFiles) {
        this.infoPublishsForumFiles = infoPublishsForumFiles;
    }
}
