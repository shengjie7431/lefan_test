package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.OrgFileMid;

import java.util.List;

public interface OrgFileMidMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrgFileMid record);

    int insertSelective(OrgFileMid record);

    OrgFileMid selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrgFileMid record);

    int updateByPrimaryKey(OrgFileMid record);

    List<OrgFileMid> queryOrgFiles(Long orgId);

    int deleteByFileId(Long fileId);
}