package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendLawFeeDetailApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.LawFeeDetailDto;
import com.lefancrm.apicenter.model.LawFeeDetail;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lixianfeng on 2018/10/17.
 */
@ApiService(descript = "司法评估API")
@Service
public class BackendLawFeeDetailApiImpl extends BaseServiceImpl implements BackendLawFeeDetailApi {
    @Autowired
    private LawFeeDetailMapper lawFeeDetailMapper;

    /**
     * 司法评估-退费列表list
     *
     * */
    @ApiMethod(descript = "司法评估-退费列表list", value = "backend-law-fee-detail-info-list")
    @Override
    public ApiResponse<List<LawFeeDetailDto>> list(ApiRequest apiRequest) {
        this.setBackendPageSize(apiRequest);
        int count = lawFeeDetailMapper.findListSize(apiRequest);
        List<LawFeeDetailDto> list = lawFeeDetailMapper.findList(apiRequest);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    /**
     * 根据信息查询退费费用
     *
     * */
    @ApiMethod(descript = "司法评估-退案费用", value = "backend-law-fee-detail-by-info")
    @Override
    public ApiResponse<LawFeeDetailDto> selectByInfo(ApiRequest apiRequest) {
        this.setBackendPageSize(apiRequest);
        //退案退费
        String menuType = apiRequest.getString("menuType");
        if(menuType != null){
            apiRequest.put("type",3);
        }
        LawFeeDetailDto list = lawFeeDetailMapper.selectByInfo(apiRequest);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,null,list);
    }

    /**
     * 退费修改
     *
     * */
    @ApiMethod(descript = "司法评估-退费修改", value = "backend-law-fee-detail-upd")
    @Override
    public ApiResponse<LawFeeDetail> upd(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        LawFeeDetail lawFeeDetail = lawFeeDetailMapper.selectByPrimaryKey(id);
        lawFeeDetail.setState(2);
        int ret = lawFeeDetailMapper.updateByPrimaryKey(lawFeeDetail);
        if (ret < 0){
            return new ApiResponse<>(ApiMsgEnum.FAIL);
        }
        return new ApiResponse<>(ApiMsgEnum.SUCCESS);
    }
}
