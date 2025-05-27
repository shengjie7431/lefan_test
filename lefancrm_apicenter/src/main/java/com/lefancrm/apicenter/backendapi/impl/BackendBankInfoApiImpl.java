package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendBankInfoApi;
import com.lefancrm.apicenter.dao.BankInfoMapper;
import com.lefancrm.apicenter.model.BankInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangwei on 2018/4/24.
 * 银行卡数据
 */
@Service
@ApiService(descript = "银行卡数据API")
public class BackendBankInfoApiImpl extends BaseServiceImpl implements BackendBankInfoApi {

    @Autowired
    private BankInfoMapper bankInfoMapper;
    /**
     * 银行卡数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "银行卡数据", value = "backend-bank-info_dto", apiParams = { })
    @Override
    public ApiResponse<List<BankInfo>> getBankInfoList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        //列表查询，包含条件查询
        List<BankInfo> list = bankInfoMapper.selectBankInfoList(apiReq);

        return new ApiResponse<List<BankInfo>>(ApiMsgEnum.SUCCESS, null, list);

    }

}
