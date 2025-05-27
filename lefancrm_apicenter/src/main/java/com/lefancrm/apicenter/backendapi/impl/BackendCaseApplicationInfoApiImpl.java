package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseApplicationInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.CaseApplicationInfoDto;
import com.lefancrm.apicenter.dto.CaseCenterInfoDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.UserAccountService;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.SendMessageUntil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangwei on 2018/5/14.
 */
@ApiService(descript = "案件审核列表API")
@Service
public class BackendCaseApplicationInfoApiImpl extends BaseServiceImpl implements BackendCaseApplicationInfoApi {

    SimpleDateFormat sdf1 =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private CaseApplicationInfoMapper caseApplicationInfoMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private CaseFollowInfoMapper caseFollowInfoMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private MessageInfoMapper messageInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private PromotedInfoMapper promotedInfoMapper;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private BackendCaseInfoApiImpl backendCaseInfoApi;
    @Autowired
    private CaseCenterExtendMapper caseCenterExtendMapper;
    @Autowired
    private UserPromotedMapper userPromotedMapper;
    /**
     * 案件审核列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "案件审核列表" ,value = "backend-case-application-info-list")
    @Override
    public ApiResponse<List<CaseApplicationInfoDto>> caseApplicationInfoList(ApiRequest apiReq){

        //根据当前登录人是否是测试人员
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(apiReq.getLong("operatorId"));
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                apiReq.put("isTest",0);
            }else{
                //测试人员:默认查询测试案件
                apiReq.put("isTest",1);
            }
        }

        this.setBackendPageSize(apiReq);
        int count = caseApplicationInfoMapper.selectCountCaseApplicationInfo(apiReq);
        List<CaseApplicationInfoDto> list = caseApplicationInfoMapper.selectCaseApplicationInfoList(apiReq);
//        if(!list.isEmpty()){
//            for(CaseApplicationInfoDto caseApplicationInfoDto : list){
//                Long userId = caseApplicationInfoDto.getUserId();
//                UserPromoted userPromoted =getUserPromoted(userId);
//                if(userPromoted == null){
//                    caseApplicationInfoDto.setPromoterName("无");
//                }else{
//                    caseApplicationInfoDto.setPromoterName(userPromoted.getRealName());
//                    caseApplicationInfoDto.setPromoterPhone(userPromoted.getPhone());
//                }
//                //仅返回推广人姓名，用以下方法
//                //String promotedName = getPromotedName(userId);
//                //caseApplicationInfoDto.setPromoterName(promotedName);
//            }
//        }
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    private UserPromoted getUserPromoted(Long userId){
        if(userId == null || userId == 0){
            return null;
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId",userId);
        UserPromoted userPromoted = userPromotedMapper.selectUserPromotedByUserId(paramMap);
        if(userPromoted == null){
            paramMap.clear();
            paramMap.put("customerId",userId);
            //去找推广人员
            PromotedInfo promotedInfo = promotedInfoMapper.selectPromotedInfoByParam(paramMap);
            if(promotedInfo == null){
                return null;
            }
            userPromoted = userPromotedMapper.selectByPrimaryKey(promotedInfo.getPromoterId());
            if(userPromoted == null){
                return null;
            }
            paramMap.clear();
            paramMap.put("userId",userPromoted.getUserId());
            paramMap.put("roleId",2);
            BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(paramMap);
            if(busUserRole != null){
                return userPromoted;
            }
            UserPromoted userPromoted1 = userPromotedMapper.selectByPrimaryKey(userPromoted.getParentId());
            if(userPromoted1 == null){
                return null;
            }
            return getUserPromoted(userPromoted1.getUserId());
        }else {
            paramMap.clear();
            paramMap.put("userId",userId);
            paramMap.put("roleId",2);
            BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(paramMap);
            if(busUserRole != null){
                return userPromoted;
            }
            UserPromoted userPromoted1 = userPromotedMapper.selectByPrimaryKey(userPromoted.getParentId());
            if(userPromoted1 == null){
                return null;
            }
            return getUserPromoted(userPromoted1.getUserId());
        }
    }

//    private String getPromotedName(Long userId){
//        if(userId == null || userId == 0){
//            return "无";
//        }
//        Map<String,Object> paramMap = new HashMap<>();
//        paramMap.put("userId",userId);
//        UserPromoted userPromoted = userPromotedMapper.selectUserPromotedByUserId(paramMap);
//        if(userPromoted == null){
//            paramMap.clear();
//            paramMap.put("customerId",userId);
//            //去找推广人员
//            PromotedInfo promotedInfo = promotedInfoMapper.selectPromotedInfoByParam(paramMap);
//            if(promotedInfo == null){
//                return "无";
//            }
//            userPromoted = userPromotedMapper.selectByPrimaryKey(promotedInfo.getPromoterId());
//            if(userPromoted == null){
//                return "无";
//            }
//            paramMap.clear();
//            paramMap.put("userId",userPromoted.getUserId());
//            paramMap.put("roleId",2);
//            BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(paramMap);
//            if(busUserRole != null){
//                return userPromoted.getRealName();
//            }
//            UserPromoted userPromoted1 = userPromotedMapper.selectByPrimaryKey(userPromoted.getParentId());
//            if(userPromoted1 == null){
//                return "无";
//            }
//            return getPromotedName(userPromoted1.getUserId());
//        }else {
//            paramMap.clear();
//            paramMap.put("userId",userId);
//            paramMap.put("roleId",2);
//            BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(paramMap);
//            if(busUserRole != null){
//                return userPromoted.getRealName();
//            }
//            UserPromoted userPromoted1 = userPromotedMapper.selectByPrimaryKey(userPromoted.getParentId());
//            if(userPromoted1 == null){
//                return "无";
//            }
//            return getPromotedName(userPromoted1.getUserId());
//        }
//    }
    /**
     * 通过‘案件编号’查询代理申请数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "通过‘案件编号’查询代理申请数据" ,value = "backend-agent-apply-info-by-agentno")
    @Override
    public ApiResponse<AgentApply> searchAgentApplyByAgentNo(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        AgentApply agentApplyInfo = agentApplyMapper.searchAgentApplyByAgentNo(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,agentApplyInfo);
    }


    /**
     * 通过‘贷款申请编号’查询贷款申请数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "通过‘贷款申请编号’查询贷款申请数据" ,value = "backend-loan-application-by-loanno")
    @Override
    public ApiResponse<LoanApplication> searchLoanApplicationByLoanNo(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        LoanApplication loanApplicationInfo = loanApplicationMapper.searchLoanApplicationByLoanNo(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, loanApplicationInfo);
    }


    /**
     * 代理申请变更状态
     * @param apiReq
     * @return
     */
    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "代理申请变更状态", value = "backend-agent-apply-state")
    public ApiResponse editAgentApplyInfoState(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        Integer state = apiReq.getInt("state");
        String reson = apiReq.getString("reson");
        Long operatorId = apiReq.getLong("operatorId");//当前登录人id
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(state)) {
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        AgentApply agentApplyInfo = agentApplyMapper.selectByPrimaryKey(id);
        if(agentApplyInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        //预防多次提交
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("caseNo",agentApplyInfo.getAgentNo());
        List<CaseCenterInfoDto> info =caseCenterInfoMapper.findListByInfo(paramMap);
        if(info.size() > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }

        if(state ==3){
            agentApplyInfo.setReson(reson);
        }
        agentApplyInfo.setId(id);
        agentApplyInfo.setState(state);
        agentApplyInfo.setUpdateTime(new Date());
        agentApplyInfo.setUpdateBy(String.valueOf(operatorId));
        this.agentApplyMapper.updateByPrimaryKeySelective(agentApplyInfo);
        //state ==3 代表是被驳回的案件申请，没有后续操作
        if(state ==3){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        CaseFollowInfo caseFollowInfo = new CaseFollowInfo();
        caseFollowInfo.setType(2);
        caseFollowInfo.setCaseId(id);
        caseFollowInfo.setCaseState(2);
        caseFollowInfo.setCaseStateStr("审核通过");
        caseFollowInfo.setFollowTime(new Date());
        caseFollowInfo.setFollowBy(apiReq.getCurrentUserDisplayName());
        caseFollowInfo.setFollowById(operatorId);
        caseFollowInfo.setCaseNo(agentApplyInfo.getAgentNo());
        caseFollowInfoMapper.insertSelective(caseFollowInfo);

        String typeName = "";
        switch (agentApplyInfo.getAgentType()){
            case 1:
                typeName = "交通事故索赔";
                break;
            case 2:
                typeName = "工伤事故索赔";
                break;
            case 3:
                typeName = "寿险索赔";
                break;
            case 4:
                typeName = "车辆损失索赔";
                break;
            case 5:
                typeName = "保险拒赔";
                break;
            case 6:
                typeName = "意外保险";
                break;
            case 7:
                typeName = "其他侵权";
                break;
            case 8:
                typeName = "援助服务";
                break;
        }
//2018-05-29 新需求：佣金的计算，改变为“确认支付”的动作
//        //验证提供案源客户是否是第一次
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("caseName", agentApplyInfo.getUserName());
//        paramMap.put("caseTel", agentApplyInfo.getUserPhone());
//        int count = caseCenterInfoMapper.selectUserNameCaseCount(paramMap);
//        if(count <= 0){
//            userAccountService.caseMoney(agentApplyInfo.getUserId(),agentApplyInfo.getAgentNo(),agentApplyInfo.getAccidentCity() + agentApplyInfo.getUserName() + typeName);
//        }
        //1.准备封装案件中心数据。
        CaseCenterInfo caseCenterInfo = new CaseCenterInfo();


        /*
        注释原因：客服审核案件之后，全部都在 同一个机构之下
        HashMap<String,Object> map = caseCenterInfoMapper.selectByOrgIdAndOrgName(apiReq.getInt("currentUserId"));
        //2.根据用户是否为加盟机构用户分别进行数据封装
        if(Integer.parseInt(map.get("orgId").toString())>0){//判断是否为机构用户推广的案件
            //封装机构用户推广的案件数据
            caseCenterInfo.setOrgId(Long.parseLong(map.get("orgId").toString())); //添加机构id
            caseCenterInfo.setOrgName(map.get("orgName").toString());              //添加机构名称
        }else{
            HashMap<String,Object> map2 = caseCenterInfoMapper.selectByApplyInfoOrgIdAndOrgNameByCaseId(apiReq.getLong("caseId"));
            if(map2!=null){
                caseCenterInfo.setOrgId(Long.parseLong(map2.get("orgId").toString())); //添加机构id
                caseCenterInfo.setOrgName(map2.get("orgName").toString());              //添加机构名称
            }
        }
        */

        HashMap<String,Object> map2 = caseCenterInfoMapper.selectByApplyInfoOrgIdAndOrgNameByCaseId(apiReq.getLong("id"));
        if(map2!=null){
            //2018年9月20日14:10:13  若匹配的是"乐凡赔偿总部"则默认,将组织更改为:上海机构
            if ("1".equals(map2.get("orgId").toString())){
                caseCenterInfo.setOrgId(26L);
                caseCenterInfo.setOrgName("上海机构");
            }else{
                caseCenterInfo.setOrgId(Long.parseLong(map2.get("orgId").toString())); //添加机构id
                caseCenterInfo.setOrgName(map2.get("orgName").toString());              //添加机构名称
            }
        }
        //获取当前用户对应的业务员
        UserInfo userInfo = returnBusUser(agentApplyInfo.getUserId());
        if (userInfo == null){
            caseCenterInfo.setSalesmanId(null);
            caseCenterInfo.setSalesmanName(null);
        }else{
            caseCenterInfo.setSalesmanId(userInfo.getUserId());
            caseCenterInfo.setSalesmanName(userInfo.getUserName());
            caseCenterInfo.setOrgUserId(userInfo.getUserId());
            caseCenterInfo.setOrgUserName(userInfo.getUserName());
            caseCenterInfo.setOperatorId(userInfo.getUserId());
            caseCenterInfo.setOperatorName(userInfo.getUserName());
        }

        caseCenterInfo.setType(2);
        caseCenterInfo.setCaseId(agentApplyInfo.getId());

        caseCenterInfo.setCaseTitle(agentApplyInfo.getAccidentCity() + agentApplyInfo.getUserName() + typeName);
        caseCenterInfo.setCaseState(1);
        caseCenterInfo.setCaseStateStr("待接收");
        caseCenterInfo.setCreateTime(new Date());
        caseCenterInfo.setCreateBy(operatorId);
        caseCenterInfo.setCaseName(agentApplyInfo.getUserName());
        caseCenterInfo.setCaseTel(agentApplyInfo.getUserPhone());
        caseCenterInfo.setCaseNo(agentApplyInfo.getAgentNo());
        caseCenterInfo.setDangerTime(agentApplyInfo.getAccidentTime());
        caseCenterInfo.setGradationState(1);
        caseCenterInfo.setUpdateTime(new Date());
        caseCenterInfo.setListState(999);
        caseCenterInfo.setListStateName("待签约");

        //根据是否是测试人员，设置案件是否是测试案件
        caseCenterInfo.setIsTestcase(agentApplyInfo.getIsTestcase());
        //如果是测试案件，机构默认为“总部”
        if(agentApplyInfo.getIsTestcase() == 1){
            caseCenterInfo.setOrgId(33L);
            caseCenterInfo.setOrgName("总部");
        }

        caseCenterInfo.setDeleteFlag(0);
        caseCenterInfoMapper.insertSelective(caseCenterInfo);

        //落地数据至“案件中心”附属表
        CaseCenterExtend caseCenterExtend = new CaseCenterExtend();
        caseCenterExtend.setId(caseCenterInfo.getId());
        caseCenterExtend.setAssessFlowState(0l);
        caseCenterExtend.setClaimFlowState(0l);
        caseCenterExtend.setCustomerFlowState(0l);
        caseCenterExtend.setLegalFlowState(0l);
        caseCenterExtend.setOperatorFlowState(0l);

        //推广人联系方式
        caseCenterExtend.setUserPromotedPhone(agentApplyInfo.getUserPromotedPhone());

        caseCenterExtendMapper.insertSelective(caseCenterExtend);

        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setTitle("代办理赔审核通过通知");
        messageInfo.setConent("您好，您提交的代办理赔申请我们审核通过，服务专员将在24小时内与您联系，请您保持电话畅通，如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边。");
        messageInfo.setSenderId(0);
        messageInfo.setSenderName("系统消息");
        messageInfo.setReceiverId(agentApplyInfo.getUserId());
        messageInfo.setReceiverName(agentApplyInfo.getUserName());
        messageInfo.setMessageType(1);
        messageInfo.setSendTime(new Date());
        messageInfo.setDeleteFlag(0);
        messageInfo.setIsRead(0);
        messageInfoMapper.insertSelective(messageInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 递归调用   获取用户对应的业务员
     * @param userId  用户ID
     * @return  用户对应的业务员  没有返回NULL
     */
    private UserInfo returnBusUser(Long userId){
        List<BusUserRole> busUserRoles = busUserRoleMapper.orgUserRoleList(userId);
        //验证是否有业务员角色  true 表示用户是业务员，直接返回业务员对象
        if (isBusUser(busUserRoles)){
            return userInfoMapper.selectByPrimaryKey(userId);
        }else {
            //获取上级UserId  验证  上级是否是业务员
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("customerId",userId);
            PromotedInfo promotedInfo = promotedInfoMapper.selectPromotedInfoByParam(paramMap);
            //表示没有上级
            if (promotedInfo == null || promotedInfo.getPromoterId() == null){
                return null;
            }
            //表示有上级  就递归迪调用
            return returnBusUser(promotedInfo.getPromoterId());
        }
    }

    /**
     * 验证角色列表是否有业务员角色
     * @param busUserRoles 角色列表
     * @return
     */
    private Boolean isBusUser(List<BusUserRole> busUserRoles){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == 2){
                return true;
            }
        }
        return false;
    }


    /**
     * 贷款申请状态修改
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "贷款申请状态修改", value = "backend-loan-application-edit-state")
    @Override
    public ApiResponse<LoanApplication> editLoanApplicationInfoState(ApiRequest apiReq){
        Long id = apiReq.getLong("id");
        Integer state = apiReq.getInt("state");
        String accidentCity = apiReq.getString("accidentCity");
        String userName = apiReq.getString("userName");
        String reson = apiReq.getString("reson");
        LoanApplication loanApplication=this.loanApplicationMapper.selectByPrimaryKey(id);
        loanApplication.setState(state);

        //预防多次提交
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("caseNo",loanApplication.getLoanNo());
        List<CaseCenterInfoDto> info =caseCenterInfoMapper.findListByInfo(paramMap);
        if(info.size() > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }

        if(state ==3){
            loanApplication.setReson(reson);
        }
        int result = this.loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
        //添加系统消息状态(1:报案，2：受理.3:驳回，4：申请中，5,：完成)
       /* Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId",loanApplication.getUserId())
        UserInfo userInfo = this.userInfoMapper.selectUserInfoByUserId(paramMap);*/
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy年MM月dd天");
        String dateStr = sdf.format(loanApplication.getCreateTime());
        String loanTypeStr="医疗费垫付";
        if(loanApplication.getLoanPurpose()==1){
            loanTypeStr="医疗费垫付";
        }else if(loanApplication.getLoanPurpose()==2){
            loanTypeStr="赔偿款垫付";
        }
        String loanStateStr="";
        MessageInfo  messageInfo = new MessageInfo();
        if(state==2){
            loanStateStr="审核通过";
            messageInfo.setTitle(loanTypeStr+"申请审核通知");
            messageInfo.setIsRead(0);
            messageInfo.setConent("您好，您提交的"+loanTypeStr+"申请我们"+loanStateStr+"，服务专员将在24小时内与您联系，请您保持电话畅通，如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边");

//2018-05-29 新需求：佣金的计算，改变为“确认支付”的动作
            //验证提供案源客户是否是第一次
//            Map<String, Object> paramMap = new HashMap<>();
//            paramMap.put("caseName", loanApplication.getUserName());
//            paramMap.put("caseTel", loanApplication.getUserPhone());
//            int count = caseCenterInfoMapper.selectUserNameCaseCount(paramMap);
//
//            if(count <= 0){
//                userAccountService.caseMoney(loanApplication.getUserId(),loanApplication.getLoanNo(),accidentCity+userName+loanTypeStr);
//            }

            //审核通过开始添加案件中心信息
            apiReq.put("caseId",id);
            apiReq.put("type",1);
            apiReq.put("caseTitle",accidentCity+userName+loanTypeStr);
            apiReq.put("caseNo",loanApplication.getLoanNo());
            apiReq.put("caseName",userName);
            apiReq.put("caseTel",loanApplication.getUserPhone());
            apiReq.put("dangerTime",sdf1.format(loanApplication.getAccidentTime()));
            backendCaseInfoApi.addCaseCenterInfo(apiReq);
        }
        if(state==3){
            loanStateStr="已经驳回";
            messageInfo.setTitle(loanTypeStr+"申请驳回通知");
            messageInfo.setIsRead(0);
            messageInfo.setConent("您好，您提交的"+loanTypeStr+"申请我们"+loanStateStr+"，服务专员将在24小时内与您联系，请您保持电话畅通，如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边");

        }
        try {
            String  resultStr= SendMessageUntil.sendSmsCaseCenterInfo(loanApplication.getUserPhone(), loanTypeStr, loanStateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageInfo.setSenderId(0);
        messageInfo.setSenderName("系统消息");
        messageInfo.setReceiverId(loanApplication.getUserId());
        messageInfo.setReceiverName(loanApplication.getUserName());
        messageInfo.setMessageType(1);
        messageInfo.setSendTime(new Date());
        messageInfo.setDeleteFlag(0);
        this.messageInfoMapper.insertSelective(messageInfo);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }
}
