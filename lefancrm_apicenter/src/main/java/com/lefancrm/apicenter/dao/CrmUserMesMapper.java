package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CrmMsgDto;
import com.lefancrm.apicenter.model.CrmUserMes;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface CrmUserMesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrmUserMes record);

    int insertSelective(CrmUserMes record);

    CrmUserMes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrmUserMes record);

    int updateByPrimaryKey(CrmUserMes record);

    /** 获取用户所有消息列表方法*/
    List<CrmMsgDto> selectMessageInfoList(ApiRequest apiReq);

    int selectMessageNotReadCount(Long receiverId);
/**根据msgId更新消息表*/
    int updateByMsgIdSelective(CrmUserMes crmUserMes);

    // 批量插入方法
    void insertList(List<CrmUserMes> list);
}