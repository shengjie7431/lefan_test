package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.InfoPublishs;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface InfoPublishsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InfoPublishs record);

    int insertSelective(InfoPublishs record);

    InfoPublishs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InfoPublishs record);

    int updateByPrimaryKey(InfoPublishs record);

    List<InfoPublishs> findList(ApiRequest apiRequest);
    int findListSize(ApiRequest apiRequest);
}