package com.lefancrm.apicenter.fina.api.impl;

import com.lefancrm.apicenter.fina.api.BackendFinaProgressApi;
import com.lefancrm.apicenter.fina.dao.FinaProgressMapper;
import com.lefancrm.apicenter.fina.model.FinaProgress;
import com.lefancrm.apicenter.model.FinancialReProgres;
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
 * Created by wangwei on 2021/2/4.
 */
@Service
@ApiService(descript = "垫付案件进度API")
public class BackendFinaProgressApiImpl extends BaseServiceImpl implements BackendFinaProgressApi {
    @Autowired
    private FinaProgressMapper finaProgresMapper;

    @ApiMethod(needLogin = false,descript = "进度列表",value = "list-fina-progress")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        Map<String,Long> map =  new HashMap<String,Long>();
        map.put("financialReApplyId",surveyInfoId);
        List<FinaProgress> list = finaProgresMapper.list(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }

    /**
     * 保存进度
     * @param
     * @return
     */
    public int saveProgress(Long finaInfoId,Long userId,String userName,String progressName,String progressDesc,String keyCode, String keyName){
        FinaProgress progress = new FinaProgress();
        progress.setFinaInfoId(finaInfoId);
        progress.setKeyCode(keyCode);
        progress.setKeyName(keyName);
        progress.setProgressUserId(userId);
        progress.setProgressUserName(userName);
        progress.setProgressName(progressName);
        progress.setProgressDesc(progressDesc);
        progress.setProgressTime(new Date());
        progress.setCreateBy(userName);
        progress.setCreateTime(new Date());
        progress.setDeleteFlag(0);
        return finaProgresMapper.insert(progress);
    }
}
