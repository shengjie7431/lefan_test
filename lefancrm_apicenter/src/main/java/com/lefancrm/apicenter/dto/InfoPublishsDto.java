package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.InfoPublishs;
import com.lefancrm.apicenter.model.InfoPublishsForum;

import java.util.List;

/**
 * Created by lixianfeng on 2018/11/22.
 */
public class InfoPublishsDto extends InfoPublishs{
    private String safeCompanys;
    private String safeCompanyNames;
    private Boolean isAssignRole = false;//是否有分配协助保司的权限

    private List<InfoPublishsForumDto> infoPublishsForumDtos;

    public List<InfoPublishsForumDto> getInfoPublishsForumDtos() {
        return infoPublishsForumDtos;
    }

    public void setInfoPublishsForumDtos(List<InfoPublishsForumDto> infoPublishsForumDtos) {
        this.infoPublishsForumDtos = infoPublishsForumDtos;
    }

    public String getSafeCompanys() {
        return safeCompanys;
    }

    public void setSafeCompanys(String safeCompanys) {
        this.safeCompanys = safeCompanys;
    }

    public String getSafeCompanyNames() {
        return safeCompanyNames;
    }

    public void setSafeCompanyNames(String safeCompanyNames) {
        this.safeCompanyNames = safeCompanyNames;
    }

    public Boolean getIsAssignRole() {
        return isAssignRole;
    }

    public void setIsAssignRole(Boolean isAssignRole) {
        this.isAssignRole = isAssignRole;
    }
}
