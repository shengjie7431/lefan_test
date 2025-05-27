package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.InfoPublishsForum;

import java.util.List;

public interface InfoPublishsForumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InfoPublishsForum record);

    int insertSelective(InfoPublishsForum record);

    InfoPublishsForum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InfoPublishsForum record);

    int updateByPrimaryKey(InfoPublishsForum record);

    List<InfoPublishsForum> findByPublishsId(Long publishsId);
}