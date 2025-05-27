package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyConsignerApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.CommonFile;
import com.lefancrm.apicenter.model.SurveyConsigner;
import com.lefancrm.apicenter.model.SurveyConsignerFile;
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
 * Created by wangwei on 2018/12/17.
 * 委托人认证
 */
@Service
@ApiService(descript = "委托人认证API")
public class BackendSurveyConsignerApiImpl extends BaseServiceImpl implements BackendSurveyConsignerApi {

    @Autowired
    private SurveyConsignerMapper surveyConsignerMapper;
    @Autowired
    private SurveyConsignerFileMapper surveyConsignerFileMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;

    /**
     * 委托人认证(非分页数据)list
     * @param apiReq
     * @return
     */
//    @SuppressWarnings("rawtypes")
//    @ApiMethod(descript = "委托人认证(非分页数据)list", value = "backend-survey-consigner-list", apiParams = { })
//    @Override
//    public ApiResponse list(ApiRequest apiReq) {
//        this.setBackendPageSize(apiReq);
//        //非分页数据
//        List<SurveyConsigner> list = surveyConsignerMapper.list(apiReq);
//        return new ApiResponse<List<SurveyConsigner>>(ApiMsgEnum.SUCCESS, null, list);
//
//    }

    /**
     * 认证材料
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "认证材料", value = "backend-survey-common-file", apiParams = { })
    @Override
    public ApiResponse commonFileList(ApiRequest apiReq) {

        Map<String,Object> map  = new HashMap<>();
        map.put("consignId",apiReq.getLong("id"));
        map.put("consignType",apiReq.getLong("consignType"));
        List<SurveyConsignerFile> list = surveyConsignerFileMapper.selectByInfo(map);
        List<CommonFile> fileList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CommonFile commonFile = commonFileMapper.selectByPrimaryKey(list.get(i).getFileId());
            fileList.add(commonFile);
        }
        return new ApiResponse<List<CommonFile>>(ApiMsgEnum.SUCCESS, null, fileList);

    }

    /**
     * 机构下的所有委托人
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "机构下的所有委托人list", value = "backend-survey-consigner-by-org", apiParams = { })
    @Override
    public ApiResponse listByOrg(ApiRequest apiReq) {

        Map<String,Object> map = new HashMap<>();
        map.put("entrustOrgId",apiReq.getString("consignorId"));
        List<SurveyConsigner> list = surveyConsignerMapper.list(map);
        return new ApiResponse<List<SurveyConsigner>>(ApiMsgEnum.SUCCESS, list.size(), list);

    }
}
