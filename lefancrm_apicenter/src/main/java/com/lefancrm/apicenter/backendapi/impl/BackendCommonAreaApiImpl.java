package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCommonAreaApi;
import com.lefancrm.apicenter.dao.CommonAreaMapper;
import com.lefancrm.apicenter.dto.CommonAreaDto;
import com.lefancrm.apicenter.model.CommonArea;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ApiService(descript = "区域API")
public class BackendCommonAreaApiImpl extends BaseServiceImpl implements BackendCommonAreaApi {
    @Autowired
    private CommonAreaMapper commonAreaMapper;

    @Override
    public ApiResponse matchAddress(ApiRequest apiReq) {
        String name1 = apiReq.getString("name1");
        String name2 = apiReq.getString("name2");
        CommonAreaDto commonArea = commonAreaMapper.matchAddress(name1, name2);
//        if (commonArea != null){
//            Integer cityType = commonArea.getCityType();
//            if (cityType == 3){
//                 commonArea = commonAreaMapper.matchAddress2(name1);
//            }
//        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonArea);
    }
}
