package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendMessageApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.CrmMessageInfoDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@ApiService(descript = "通知消息")
public class BackendMessageApiImpl extends BaseServiceImpl implements BackendMessageApi {
    @Autowired
    private CrmMessageInfoMapper crmMessageInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CrmUserMesMapper crmUserMesMapper;

    @Autowired
    private MessageInfoMapper messageInfoMapper;
    @Autowired
    private CaseFollowInfoMapper caseFollowInfoMapper;
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;
    @Autowired
    private CaseEntrustInputMapper caseEntrustInputMapper;
    /**
     * 分页查询通知消息列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "CRM后台通知消息列表", value = "backend-crm-message-list", apiParams = { @ApiParam(descript = "页码", name = "page"), @ApiParam(descript = "每页多少条", name = "page_size")})
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        Integer sendTime = apiReq.getInt("sendTime");
        Integer isRead = apiReq.getInt("isRead");
        Integer deleteFlag = apiReq.getInt("deleteFlag");
        Long userId = apiReq.getLong("operatorId");
       // System.out.println("消息通知列表方法参数查看: sendTime = " + sendTime + ", isRead = " + isRead + ",deleteFale = " +deleteFlag);

        Date startDate = null;
        Date endDate = null;
        if(!StringUtils.isEmpty(sendTime)){
            Calendar c = Calendar.getInstance();
            Date nowDate = c.getTime();
            // 发送时间处理
            switch(sendTime){
                case 1:// 当天
                    startDate = nowDate;
                    endDate = startDate;
                    break;
                case 2:// 昨天
                    startDate = DateUtils.getDateFromSourceDate(nowDate,-1);
                    endDate = startDate;
                    break;
                case 3:// 本周
                    startDate = DateUtils.getFirstDayOfWeek(nowDate);
                    endDate = DateUtils.getLastDayOfWeek(nowDate);
                    break;
                case 4:// 本月
                    startDate = DateUtils.getFirstDayOfYearMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
                    endDate = DateUtils.getLastDayOfYearMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
                    break;
            }
        }

        apiReq.put("startDate", startDate);
        apiReq.put("endDate", endDate);
        apiReq.put("userId", userId);

        // 更新发送状态
        crmMessageInfoMapper.updateIsSend(new Date());
        this.setBackendPageSize(apiReq);
        int count = crmMessageInfoMapper.listCount(apiReq);
        List<CrmMessageInfoDto> list = crmMessageInfoMapper.list(apiReq);
        return new ApiResponse<List<CrmMessageInfoDto>>(ApiMsgEnum.SUCCESS, count, list);
    }

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "接受者", value = "backend-crm-receive")
	public ApiResponse toAddMessage(ApiRequest apiReq) {
		/*this.setBackendPageSize(apiReq);*/
		/*int count = this.sysUserMapper.countListForBackend(apiReq);*/
		List<UserInfo> list = this.userInfoMapper.selectListForBackend(apiReq);
		return new ApiResponse<List<UserInfo>>(ApiMsgEnum.SUCCESS,list==null?0:list.size(), list);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "添加消息", value = "backend-message-insert")
	public ApiResponse addMessage(ApiRequest apiReq) {
        Integer sendState = apiReq.getInt("sendState");//定时 0 立即 1
        String content = apiReq.getString("content");
        String sendTime = apiReq.getString("sendTime");
        Long userId = apiReq.getLong("userId");
        String userIdsJ = apiReq.getString("userIds");
        List<Long> userIds = JSONArray.parseArray(userIdsJ, Long.class);

        if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(userIds)||StringUtils.isEmpty(content)||StringUtils.isEmpty(sendState)){
          return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }

        // 先添加消息内容
        CrmMessageInfo crmMessageInfo = new CrmMessageInfo();
        crmMessageInfo.setContent(content);
        crmMessageInfo.setMsgType(1);
        crmMessageInfo.setSendTime(sendState == 0 ? DateUtils.parseDate(sendTime,"yyyy-MM-dd"):new Date());
        int ret = crmMessageInfoMapper.insertSelective(crmMessageInfo);
        if(ret < 1){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }

        List<CrmUserMes> list = new ArrayList();
        // 添加用户消息
        for (Long receiveId : userIds){
            CrmUserMes crmUserMes = new CrmUserMes();
            crmUserMes.setMsgId(crmMessageInfo.getId());
            crmUserMes.setIsRead(0);
            crmUserMes.setDeleteFlag(0);
            crmUserMes.setReceiveId(receiveId);
            crmUserMes.setSendId(userId);
            crmUserMes.setIsSend(sendState);
//            list.add(crmUserMes);
            ret = crmUserMesMapper.insertSelective(crmUserMes);
        }
//        crmUserMesMapper.insertList(list);

		return new ApiResponse<List<UserInfo>>(ApiMsgEnum.SUCCESS,0, null);
	}


    /**
     * 添加系统消息 以及  案件经办进度流程     针对于关于案件状态变更
     * @param currentUserId         当前登录人ID
     * @param currentUserName       当前登录人NAME
     * @param caseCenterInfo        案件中心信息
     * @param caseStateName         案件状态名称
     * @param  caseStateDesc        状态变更描述
     * @return
     */
    public Boolean addMessageOrFollowByUpdCaseState(Long currentUserId,String currentUserName,CaseCenterInfo caseCenterInfo,String caseStateName,String caseStateDesc) {
        try {
            CaseEntrustInput caseEntrustInput = caseEntrustInputMapper.selectByPrimaryKey(caseCenterInfo.getId());
            Integer type = caseCenterInfo.getType();
            String caseTypeName = "";
            Long caseUserId = null;
            if (caseEntrustInput != null){
                if (caseEntrustInput.getAgentType() == 1){
                    caseTypeName = "交通事故索赔";
                }else if (caseEntrustInput.getAgentType() == 2){
                    caseTypeName = "工伤事故索赔";
                }
            }
            String title = caseTypeName + "申请状态变更通知";
            String content = "您好，您提交的" + caseTypeName + "申请，当前状态:" + caseStateName + "。如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边";
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setTitle(title);
            messageInfo.setConent(content);
            messageInfo.setSenderId(Integer.parseInt(currentUserId.toString()));
            messageInfo.setSenderName(currentUserName);
            messageInfo.setReceiverId(caseUserId);
            messageInfo.setMessageType(1);
            messageInfo.setSendTime(new Date());
            messageInfo.setDeleteFlag(0);
            messageInfo.setIsRead(0);
            messageInfoMapper.insertSelective(messageInfo);
            CaseFollowInfo caseFollowInfo = new CaseFollowInfo();
            caseFollowInfo.setType(type);
            caseFollowInfo.setCaseId(caseCenterInfo.getId());
            caseFollowInfo.setCaseState(null);
            caseFollowInfo.setCaseStateStr(caseStateName);
            caseFollowInfo.setFollowDesc("".equals(caseStateDesc) || caseStateDesc == null ? "同意" : caseStateDesc);
            caseFollowInfo.setFollowBy(currentUserName);
            caseFollowInfo.setFollowById(currentUserId);
            caseFollowInfo.setFollowTime(new Date());
            caseFollowInfo.setCaseNo(caseCenterInfo.getCaseNo());
            caseFollowInfoMapper.insertSelective(caseFollowInfo);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
