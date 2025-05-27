package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.InfoPublishsSafeCompanyDto;
import com.lefancrm.apicenter.model.InfoPublishsSafeCompany;
import com.lefancrm.apicenter.model.InfoSafeCompany;

import java.util.List;
import java.util.Map;

public interface InfoPublishsSafeCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InfoPublishsSafeCompany record);

    int insertSelective(InfoPublishsSafeCompany record);

    InfoPublishsSafeCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InfoPublishsSafeCompany record);

    int updateByPrimaryKey(InfoPublishsSafeCompany record);

    InfoPublishsSafeCompany selectInfoByPubIdandCompanyId(Map map);

    List<InfoPublishsSafeCompany> selectListByPublishsId(Long publishsId);

    List<InfoSafeCompany> selectListDtoByPublishsIdYremove(Map map);

    List<InfoSafeCompany> selectListDtoByPublishsIdYselect(Map map);
}