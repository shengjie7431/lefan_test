package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendSurveyLevelApi;
import com.lefancrm.apicenter.dao.SurveyLevelMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyLevel;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wangwei on 2018/12/17.
 * “调查员级别”数据管理
 */
@Service
@ApiService(descript = "调查员级别API")
public class BackendSurveyLevelApiImpl extends BaseServiceImpl implements BackendSurveyLevelApi {

    @Autowired
    private SurveyLevelMapper surveyLevelMapper;

    /**
     * 调查员级别(非分页数据)list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "调查员级别(非分页数据)list", value = "backend-survey-level-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        //(非分页数据)
        List<SurveyLevel> list = surveyLevelMapper.list(apiReq);
        return new ApiResponse<List<SurveyLevel>>(ApiMsgEnum.SUCCESS, null, list);

    }


    /**
     * 获取调查员级别姓名
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "获取调查员级别姓名", value = "backend-survey-level-names", apiParams = { })
    @Override
    public ApiResponse selectNamesByIds(ApiRequest apiReq) {
        String levels = apiReq.getString("levels");
        StringBuffer sb = new StringBuffer();
        if(levels !=null){
            JSONArray list= JSONArray.parseArray(levels);
            for(int i=0 ; i<list.size();i++){
                Long id = Long.valueOf(list.get(i).toString());
                SurveyLevel surveyLevel = surveyLevelMapper.selectByPrimaryKey(id);
                sb.append(surveyLevel.getName());
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, null, sb);

    }
}
