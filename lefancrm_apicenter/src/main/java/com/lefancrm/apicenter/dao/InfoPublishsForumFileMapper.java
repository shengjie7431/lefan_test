package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.InfoPublishsForumFile;

import java.util.List;

public interface InfoPublishsForumFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InfoPublishsForumFile record);

    int insertSelective(InfoPublishsForumFile record);

    InfoPublishsForumFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InfoPublishsForumFile record);

    int updateByPrimaryKey(InfoPublishsForumFile record);

    List<InfoPublishsForumFile> findByPublishsForumId(Long publishsForumId);
}