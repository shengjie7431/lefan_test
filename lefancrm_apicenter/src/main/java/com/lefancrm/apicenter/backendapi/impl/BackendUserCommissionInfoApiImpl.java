package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendUserCommissionInfoApi;
import com.lefancrm.apicenter.backendapi.BackendUserPoLevelApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwei on 2018/3/22.
 * “CC佣金”数据管理
 */
@Service
@ApiService(descript = "CC佣金数据管理API")
public class BackendUserCommissionInfoApiImpl extends BaseServiceImpl implements BackendUserCommissionInfoApi {

    @Autowired
    private UserCommissionInfoMapper userCommissionInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CommissionLogMapper commissionLogMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    /**
     * CC佣金数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "CC佣金数据管理列表查询", value = "backend-user-commission-info-list", apiParams = { })
    @Override
    public ApiResponse<List<UserCommissionInfo>> getUserCommissionInfoList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = userCommissionInfoMapper.selectCountUserCommissionInfo(apiReq);

        //列表查询，包含条件查询
        List<UserCommissionInfo> list = userCommissionInfoMapper.selectUserCommissionInfoList(apiReq);

        return new ApiResponse<List<UserCommissionInfo>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * CC佣金数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "CC佣金的佣金记录", value = "backend-commission-log-list-by-user", apiParams = { })
    @Override
    public ApiResponse<List<CommissionLog>> searchCommissionLogList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //列表查询，包含条件查询
        List<CommissionLog> list = commissionLogMapper.searchCommissionLogList(apiReq);

        return new ApiResponse<List<CommissionLog>>(ApiMsgEnum.SUCCESS, null, list);

    }

    /**
     * CC佣金的月新签单详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "CC佣金的月新签单详情", value = "backend-case-center-info-list-for-month-new-sign", apiParams = { })
    @Override
    public ApiResponse<List<CaseCenterInfo>> searchMonthNewSignList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //列表查询
        List<CaseCenterInfo> caseCenterInfoList = caseCenterInfoMapper.searchMonthNewSignList(apiReq);;
        return new ApiResponse<List<CaseCenterInfo>>(ApiMsgEnum.SUCCESS, null, caseCenterInfoList);

    }
}
