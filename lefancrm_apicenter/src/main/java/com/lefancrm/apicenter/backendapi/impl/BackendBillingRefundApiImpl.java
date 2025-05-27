package com.lefancrm.apicenter.backendapi.impl;


import com.lefancrm.apicenter.backendapi.BackendBillingRefundApi;
import com.lefancrm.apicenter.dao.BillingApplyRefundMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.BillingApplyRefund;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiService(descript = "发票列表API")
@Service
public class BackendBillingRefundApiImpl extends BaseServiceImpl implements BackendBillingRefundApi {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BillingApplyRefundMapper billingApplyRefundMapper;


    @ApiMethod(descript = "退费操作" ,value = "backend-billing-apply-refund-operate")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        Long billId = apiRequest.getLong("billId");
        String btnCode = apiRequest.getString("btnCode");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        if ("save-refund".equals(btnCode))
        {
            BillingApplyRefund refund = new BillingApplyRefund();
            refund.setBillId(billId);
            refund.setRefundMoney(apiRequest.getDouble("refundMoney"));
            try {
                refund.setRefundTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(apiRequest.getString("refundTime")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            refund.setRefundUser(userInfo.getUserId());
            refund.setRefundUserBy(userInfo.getUserName());
            refund.setRemark(apiRequest.getString("remark"));
            refund.setCreateTime(new Date());
            refund.setDeleteFlag(0);
            billingApplyRefundMapper.insert(refund);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,refund);
        }
        return null;
    }
}
