package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.NoSignUserDto;
import com.lefancrm.apicenter.model.SignInfo;

import java.util.List;
import java.util.Map;

public interface SignInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SignInfo record);

    int insertSelective(SignInfo record);

    SignInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SignInfo record);

    int updateByPrimaryKey(SignInfo record);

    List<SignInfo> selectSignInfoByOrgId(Map<String, Object> paramMap);

    List<SignInfo>   selectSignInfoByUserId(Map<String, Object> paramMap);

    List<NoSignUserDto>  selectNoSignByOrgId(Map<String, Object> paramMap);

}