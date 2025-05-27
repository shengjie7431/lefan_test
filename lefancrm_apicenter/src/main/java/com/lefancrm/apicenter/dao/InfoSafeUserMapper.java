package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.UserInfoDto;
import com.lefancrm.apicenter.model.InfoSafeUser;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface InfoSafeUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InfoSafeUser record);

    int insertSelective(InfoSafeUser record);

    InfoSafeUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InfoSafeUser record);

    int updateByPrimaryKey(InfoSafeUser record);

    List<InfoSafeUser> selectList(Map<String, Object> map);

    //保险公司名下的用户
    int selectUserInfoCompanyCount(ApiRequest apiRequest);
    List<UserInfoDto> selectUserInfoCompany(ApiRequest apiRequest);


}