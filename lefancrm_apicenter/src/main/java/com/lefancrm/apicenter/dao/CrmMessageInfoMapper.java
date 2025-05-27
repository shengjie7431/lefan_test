package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CrmMessageInfoDto;
import com.lefancrm.apicenter.model.CrmMessageInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface CrmMessageInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrmMessageInfo record);

    int insertSelective(CrmMessageInfo record);

    CrmMessageInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrmMessageInfo record);

    int updateByPrimaryKey(CrmMessageInfo record);
/** 根据userMsgId获取msg信息*/
    CrmMessageInfo selectByUserMsgId(Long id);

    int listCount(HashMap<String,Object> map);
    List<CrmMessageInfoDto> list(HashMap<String,Object> map);

    // 根据发送时间更新已发送状态方法
    int updateIsSend(Date sendTime);
}