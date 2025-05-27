package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCommonFineEnumApi;
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

import java.util.List;


/**
 * Created by wangwei on 2018/5/30.
 */
@ApiService(descript = "扣罚类目列表API")
@Service
public class BackendCommonFineEnumApiImpl extends BaseServiceImpl implements BackendCommonFineEnumApi {
    @Autowired
    private CommonFineEnumMapper commonFineEnumMapper;

    /**
     * 扣罚案件清单列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "扣罚案件清单列表" ,value = "backend-common-fine-enum-list")
    @Override
    public ApiResponse<List<CommonFineEnum>> commonFineEnumList(ApiRequest apiReq){
        List<CommonFineEnum> list = commonFineEnumMapper.selectCommonFineEnumList(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }

    /**
     * 根据id查询扣罚类目
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据id查询扣罚类目" ,value = "backend-common-fine-enum-info-by-id")
    @Override
    public ApiResponse<CommonFineEnum> seachInfoByFinedType(ApiRequest apiReq){

        Long id = apiReq.getLong("finedType");
        CommonFineEnum commonFineEnum = commonFineEnumMapper.selectByPrimaryKey(id);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,commonFineEnum);
    }

}
