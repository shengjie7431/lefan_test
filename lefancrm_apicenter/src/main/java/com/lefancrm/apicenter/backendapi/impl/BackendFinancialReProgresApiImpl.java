package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendFinancialReProgresApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyProgressApi;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2021/1/21.
 */
@Service
@ApiService(descript = "每刻报销进度API")
public class BackendFinancialReProgresApiImpl extends BaseServiceImpl implements BackendFinancialReProgresApi {
    @Autowired
    private FinancialReProgresMapper financialReProgresMapper;

    @ApiMethod(needLogin = false,descript = "进度列表",value = "list-financial-re-progress")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        Map<String,Long> map =  new HashMap<String,Long>();
        map.put("financialReApplyId",surveyInfoId);
        List<FinancialReProgres> list = financialReProgresMapper.list(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }

    /**
     * 保存进度
     * @param
     * @return
     */
    public int saveProgress(Long financialReApplyId,Long userId,String userName,String progressName,String progressDesc,String stateStr,String details , Integer progressType){
        FinancialReProgres progress = new FinancialReProgres();
        progress.setFinancialReApplyId(financialReApplyId);
        progress.setProgressUserId(userId);
        progress.setProgressUserName(userName);
        progress.setProgressName(progressName);
        progress.setProgressDesc(progressDesc);
        progress.setProgressTime(new Date());
        progress.setCreateBy(userName);
        progress.setCreateTime(new Date());
        progress.setDeleteFlag(0);
        progress.setStateStr(stateStr);
        progress.setDetails(details);
        progress.setProgressType(progressType);
        return financialReProgresMapper.insert(progress);
    }
}
