package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendBillingApplyRecipientApi;
import com.lefancrm.apicenter.dao.BillingApplyRecipientMapper;
import com.lefancrm.apicenter.dao.CommonAreaMapper;
import com.lefancrm.apicenter.model.BillingApplyRecipient;
import com.lefancrm.apicenter.model.CommonArea;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangwei on 2018/10/9.
 */
@ApiService(descript = "开票对象")
@Service
public class BackendBillingApplyRecipientApiImpl extends BaseServiceImpl implements BackendBillingApplyRecipientApi {


    @Autowired
    private BillingApplyRecipientMapper billingApplyRecipientMapper;
    @Autowired
    private CommonAreaMapper commonAreaMapper;
    /**
     * 开票收件地址list
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "开票收件地址list" ,value = "backend-billing-apply-recipient-list")
    @Override
    public ApiResponse<List<BillingApplyRecipient>> billingApplyRecipientList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        List<BillingApplyRecipient> billingApplyRecipient = billingApplyRecipientMapper.selectList(apiReq);
        //单个选择时
        String id = apiReq.getString("id");
        if(id != null) {
            BillingApplyRecipient recipient = billingApplyRecipient.get(0);
            //如果数据的省市区id为空
            if (recipient != null && recipient.getProvinceId() == null) {
                Map<String, Object> map = new HashMap<>();
                //先确定“省”
                map.put("areaName", recipient.getProvince());
                map.put("areaType", 1);
                map.put("parentId", 0);
                CommonArea area = commonAreaMapper.selectByInfo(map);
                if (area != null) {
                    recipient.setProvinceId(area.getAreaId().intValue());

                    //确定市
                    map.put("areaName", recipient.getCity());
                    map.put("areaType", 2);
                    map.put("parentId", recipient.getProvinceId());
                    area = commonAreaMapper.selectByInfo(map);
                    if (area != null) {
                        recipient.setCityId(area.getAreaId().intValue());

                        //确定区
                        map.put("areaName", recipient.getDistrict());
                        map.put("areaType", 3);
                        map.put("parentId", recipient.getCityId());
                        area = commonAreaMapper.selectByInfo(map);
                        if (area != null) {
                            recipient.setDistrictId(area.getAreaId().intValue());
                            billingApplyRecipientMapper.updateByPrimaryKeySelective(recipient);
                        }
                    }
                }
            }
        }

        int count = billingApplyRecipientMapper.selectListSize(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, billingApplyRecipient);
    }


    @ApiMethod(descript = "获取开票收件人信息" ,value = "backend-billing-apply-recipient-info")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long curUserId = getCurrentUserId(apiRequest);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("curUserId",curUserId);
        BillingApplyRecipient billingApplyRecipient = billingApplyRecipientMapper.selectByInfo(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,billingApplyRecipient);
    }
}
