package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.MessageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MessageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    MessageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);

    List<MessageInfo> selectMessageInfoList(Map<String, Object> paramMap);

    int selectMessageNotReadCount(@Param("receiverId") Long userId);
}