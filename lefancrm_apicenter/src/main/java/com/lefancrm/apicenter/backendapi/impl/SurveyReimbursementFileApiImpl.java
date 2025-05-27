package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.SurveyReimbursementFileApi;
import com.lefancrm.apicenter.dao.SurveyReimbursementFileMapper;
import com.lefancrm.apicenter.model.SurveyReimbursementFile;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.pinganfu.StringUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 费用报销登记文件表(SurveyReimbursementFile)表服务实现类
 *
 * @author makejava
 * @since 2020-04-09 17:06:37
 */
@Service
@ApiService(descript = "调查费用登记报销文件表API")
public class SurveyReimbursementFileApiImpl extends BaseServiceImpl implements SurveyReimbursementFileApi {
    @Resource
    private SurveyReimbursementFileMapper surveyReimbursementFileMapper;


    @Override
    public SurveyReimbursementFile queryById(Long id) {
        return null;
    }

    @Override
    public List<SurveyReimbursementFile> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    @ApiMethod(needLogin = false,descript = "调查方向-费用报销-删除图片",value = "backend-survey-case-direction-remiburse-file-delete")
    public ApiResponse deleteById(ApiRequest apiRequest) {
        String fileId = apiRequest.getString("fileId");
        if (StringUtils.isNotBlank(fileId)){
            int  result =  surveyReimbursementFileMapper.deleteByPrimaryKey(Long.valueOf(fileId));
           if (result>0){
               return new ApiResponse(ApiMsgEnum.SUCCESS);
           }
        }

        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}