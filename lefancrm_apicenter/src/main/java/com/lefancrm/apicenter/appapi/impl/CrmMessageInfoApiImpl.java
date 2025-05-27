package com.lefancrm.apicenter.appapi.impl;

import com.lefancrm.apicenter.appapi.CrmMessageInfoApi;
import com.lefancrm.apicenter.dao.CrmMessageInfoMapper;
import com.lefancrm.apicenter.dao.CrmUserMesMapper;
import com.lefancrm.apicenter.dao.MessageInfoMapper;
import com.lefancrm.apicenter.dto.CrmMsgDto;
import com.lefancrm.apicenter.model.CrmMessageInfo;
import com.lefancrm.apicenter.model.CrmUserMes;
import com.lefancrm.apicenter.model.MessageInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ting on 2017/12/18.
 */
@Service
@ApiService(descript = "消息相关API")
public class CrmMessageInfoApiImpl extends BaseServiceImpl implements CrmMessageInfoApi {

    @Autowired
    private CrmUserMesMapper crmUserMesMapper;
    @Autowired
    private CrmMessageInfoMapper crmMessageInfoMapper;

    /**
     * 我的消息列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "CRM消息列表", value = "crm-messageInfo-list", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse<List<CrmMsgDto>> getMessageInfoList(ApiRequest apiReq) {
        Long userId = apiReq.getCurrentUserId();
        this.setPageIndex(apiReq);
        apiReq.put("receiverId",userId);
        List<CrmMsgDto> list = crmUserMesMapper.selectMessageInfoList(apiReq);
        return new ApiResponse<List<CrmMsgDto>>(ApiMsgEnum.SUCCESS, (list == null ? 0 : list.size()), list);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "CRM消息详情", value = "crm-messageInfo-indetails", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)"),@ApiParam(descript = "主键ID", name = "id")})
    @Override
    public ApiResponse<CrmMessageInfo> selectMessageInfoById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CrmMessageInfo crmMessageInfo = crmMessageInfoMapper.selectByPrimaryKey(id);
//        CrmMessageInfo crmMessageInfo1 = crmMessageInfoMapper.selectByUserMsgId(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,crmMessageInfo);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "CRM未读消息提示统计", value = "select-noRead-message", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse<Map<String ,Object>> selectMessagePrompt(ApiRequest apiReq) {
        Long userId = apiReq.getCurrentUserId();
        //查询我的消息
        int messageCount = crmUserMesMapper.selectMessageNotReadCount(userId);

        return new ApiResponse(ApiMsgEnum.SUCCESS,messageCount,null);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "消息已读", value = "crm-message-read", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)")
            ,@ApiParam(name = "id",descript = "阅读消息唯一ID")})
    @Override
    public ApiResponse updateMessageRead(ApiRequest apiReq) {
        String type = apiReq.getString("type");
        String ids = apiReq.getString("id");

        CrmUserMes crmUserMes = new CrmUserMes();
        //crmUserMes.setMsgId(Long.parseLong(ids)); // 根据msgId 更新msg
        crmUserMes.setId(Long.parseLong(ids));  // 根据id 更新msg
        crmUserMes.setIsRead(1);
        crmUserMes.setReadTime(new Date());
//        int ret = crmUserMesMapper.updateByPrimaryKeySelective(crmUserMes);
        int ret = crmUserMesMapper.updateByMsgIdSelective(crmUserMes);

        if(ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
