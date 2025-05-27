package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.WithdrawalsInfo;

import java.util.List;
import java.util.Map;

public interface WithdrawalsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WithdrawalsInfo record);

    int insertSelective(WithdrawalsInfo record);

    WithdrawalsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WithdrawalsInfo record);

    int updateByPrimaryKey(WithdrawalsInfo record);

    /**
     * 提现审核列表
     *
     */
    List<WithdrawalsInfo> selectWithdrawalsInfoList(Map<String, Object> paramMap);
    int selectWithdrawalsInfoListSize(Map<String, Object> paramMap);

    /**
     * 该用户提现总和
     *
     */
    Double selectWithdrawalsInfoMoneyByUserId(Map<String, Object> paramMap);

    /**
     * 根据userId和状态查询提现数据
     *
     */
    List<WithdrawalsInfo> selectWithdrawalsInfoByUserIdAndState(Map<String, Object> paramMap);
}