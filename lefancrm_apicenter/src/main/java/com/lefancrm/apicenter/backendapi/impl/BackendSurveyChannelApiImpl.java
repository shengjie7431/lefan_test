package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendSurveyChannelApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyAssignOrgDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.task.ReTask;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.pinganfu.StringUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@ApiService(descript = "渠道费相关API")
public class BackendSurveyChannelApiImpl extends BaseServiceImpl implements BackendSurveyChannelApi {
    @Autowired
    private SurveyChannelCostMapper surveyChannelCostMapper;
    @Autowired
    private SurveyChannelCostItemMapper surveyChannelCostItemMapper;
    @Autowired
    private SurveyChannelCaseMapper surveyChannelCaseMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;
    @Autowired
    private BackendSurveyRiskCaseInfoApiImpl surveyRiskCaseInfoApi;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private BankInfoMapper bankInfoMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;


    @ApiMethod(needLogin = false,descript = "渠道费管理列表",value = "list-survey-channel")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        setBackendPageSize(apiRequest);

        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        //如果只有机构负责人的角色。 则查询自己的数据。
        Boolean orgRole = isRoleUser(userRoles,58L);
        Boolean reviewRole = isRoleUser(userRoles,112L);
        apiRequest.put("surveyUserId",currentUserId);
        if (reviewRole){
            apiRequest.remove("surveyUserId");
        }
        int count = surveyChannelCostMapper.listSize(apiRequest);
        List<SurveyChannelCost> list = surveyChannelCostMapper.list(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }

    @ApiMethod(needLogin = false,descript = "渠道费操作管理",value = "operate-survey-channel")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        String btnCode = apiRequest.getString("btnCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        Long id = apiRequest.getLong("id");
        SurveyChannelCost surveyChannelCost = surveyChannelCostMapper.selectByPrimaryKey(id);
        if ("first-commit".equals(btnCode)){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (surveyInvestigator == null){
                return  new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
            }
            if (surveyChannelCost == null) {
                surveyChannelCost = new SurveyChannelCost();
                surveyChannelCostMapper.insert(surveyChannelCost);
            }
            surveyChannelCost.setSurveyOrgId(surveyInvestigator.getOrgId());
            surveyChannelCost.setSurveyOrgName(surveyInvestigator.getOrgName());
            surveyChannelCost.setSurveyUserId(surveyInvestigator.getUserId());
            surveyChannelCost.setSurveyUserName(surveyInvestigator.getRealName());
            surveyChannelCost.setOperationTime(new Date());
            surveyChannelCost.setOperationUserId(surveyInvestigator.getUserId());
            surveyChannelCost.setOperationUserName(surveyInvestigator.getRealName());
            surveyChannelCost.setState(2);
            surveyChannelCost.setChnannelMoney(apiRequest.getDouble("chnannelMoney"));
            surveyChannelCost.setChannelDesc(apiRequest.getString("channelDesc"));
            surveyChannelCost.setPayeeUserName(apiRequest.getString("payeeUserName"));
            surveyChannelCost.setBankDeposit(apiRequest.getString("bankDeposit"));
            surveyChannelCost.setBankBranch(apiRequest.getString("bankBranch"));
            surveyChannelCost.setBankNo(apiRequest.getString("bankNo"));
            surveyChannelCost.setRejectDesc(null);
            surveyChannelCost.setDeleteFlag(0);
            surveyChannelCost.setIsProPay(0);
            surveyChannelCostMapper.updateByPrimaryKey(surveyChannelCost);
            //保存关联案件表
            surveyChannelCostItemMapper.deleteBySurveyChannelId(surveyChannelCost.getId());
            String surveyInfoIds = apiRequest.getString("surveyInfoIds");
            String surveyCaseNos = apiRequest.getString("surveyCaseNos");
            if (!StringUtils.isEmpty(surveyInfoIds)){
                String [] ids = surveyInfoIds.split(",");
                for (String s : ids) {
                    SurveyChannelCostItem item = new SurveyChannelCostItem();
                    item.setSurveyChannelId(surveyChannelCost.getId());
                    item.setSurveyInfoId(Long.parseLong(s));
                    surveyChannelCostItemMapper.insert(item);
                }
            }

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("roleId", 112L);
            List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
            Map<String, Object> msgMap = new HashMap<String, Object>();
            msgMap.put("title", "渠道费用签报审核");
            msgMap.put("content", "你有渠道费用签报审核，请尽快进行审核！");
            msgMap.put("keyWords", "关联案件号："+(surveyCaseNos != null ? surveyCaseNos : "")+"\n申请人：" + surveyInvestigator.getRealName() + "\n渠道金额：" + surveyChannelCost.getChnannelMoney() + "元");
            backendWechatApi.send(toUsers, msgMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCost);
        }else if("two-yes".equals(btnCode)){
            surveyChannelCost.setState(4);
            surveyChannelCostMapper.updateByPrimaryKey(surveyChannelCost);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCost);
        }else if ("two-no".equals(btnCode)){
            surveyChannelCost.setState(1);
            surveyChannelCost.setRejectDesc(apiRequest.getString("rejectDesc"));
            surveyChannelCostMapper.updateByPrimaryKey(surveyChannelCost);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCost);
        }
        else if ("app".equals(btnCode)){//发起付费审核
            surveyChannelCost.setState(5);
            surveyChannelCost.setChnannelMoney(apiRequest.getDouble("chnannelMoney"));
            surveyChannelCost.setChannelDesc(apiRequest.getString("channelDesc"));
            surveyChannelCost.setPayeeUserName(apiRequest.getString("payeeUserName"));
            surveyChannelCost.setBankDeposit(apiRequest.getString("bankDeposit"));
            surveyChannelCost.setBankBranch(apiRequest.getString("bankBranch"));
            surveyChannelCost.setBankNo(apiRequest.getString("bankNo"));
            surveyChannelCost.setRejectDesc(null);
            surveyChannelCostMapper.updateByPrimaryKey(surveyChannelCost);
            //保存明细表
            surveyChannelCaseMapper.deleteBySurveyChannelId(surveyChannelCost.getId());
            String channelCases = apiRequest.getString("channelCases");
            List<SurveyChannelCase> surveyChannelCases = JSONArray.parseArray(channelCases, SurveyChannelCase.class);
            for (SurveyChannelCase surveyChannelCase : surveyChannelCases) {
                surveyChannelCase.setSurveyChannelId(surveyChannelCost.getId());
                surveyChannelCaseMapper.insert(surveyChannelCase);
            }
            //保存渠道单案件表
            surveyChannelCostItemMapper.deleteBySurveyChannelId(surveyChannelCost.getId());
            StringBuffer surveyCaseNos = new StringBuffer();
            List<SurveyChannelCase> surveyChannelCostItems = surveyChannelCaseMapper.selectBySurveyChannelIdGroupBySurveyInfoId(surveyChannelCost.getId());
            for (SurveyChannelCase surveyChannelCostItem : surveyChannelCostItems) {
                SurveyChannelCostItem item = new SurveyChannelCostItem();
                item.setSurveyInfoId(surveyChannelCostItem.getSurveyInfoId());
                item.setSurveyChannelId(surveyChannelCost.getId());
                surveyChannelCostItemMapper.insert(item);
                surveyCaseNos.append(surveyChannelCostItem.getSurveyNo() + ",");
            }

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("roleId", 112L);
            List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
            Map<String, Object> msgMap = new HashMap<String, Object>();
            msgMap.put("title", "渠道费用审核");
            msgMap.put("content", "你有渠道费用审核，请尽快进行审核！");
            msgMap.put("keyWords", "关联案件号：" + (surveyCaseNos.length() > 0 ? surveyCaseNos.substring(0,surveyCaseNos.length() - 1) : "") + "\n申请人：" + surveyChannelCost.getSurveyUserName() + "\n渠道金额：" + surveyChannelCost.getChnannelMoney() + "元");
            backendWechatApi.send(toUsers, msgMap);

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCost);
        }else if ("review".equals(btnCode)){
            String reviewType = apiRequest.getString("reviewType");
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            if ("yes".equals(reviewType)){
                surveyChannelCost.setState(3);
                surveyChannelCost.setReviewerTime(new Date());
                surveyChannelCost.setReviewerUserId(userInfo.getUserId());
                surveyChannelCost.setReviewerUserName(userInfo.getUserName());
                //审核通过之后方向的分值自动变为0
                Double channelFee = 0D;
                List<SurveyChannelCase> surveyChannelCases = surveyChannelCaseMapper.selectBySurveyChannelId(surveyChannelCost.getId());
                int size = surveyChannelCases.size();
                if (surveyChannelCases.size() > 0){
                    channelFee = DecimalUtil.twoDecimalTOFourFromFive(surveyChannelCost.getChnannelMoney() / size); //将渠道费平分给每一个方向
                }
                Double totalChannelFee = 0D;
                for (SurveyChannelCase surveyChannelCase : surveyChannelCases) {
                    SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(surveyChannelCase.getSurveyDirectionId());
                    if (surveyCaseDirection.getChannelFeeCur() == null) {
                        surveyCaseDirection.setChannelFeeCur(0D);
                    }
                    if (size > 1){
                        if (surveyChannelCase.getId().intValue() == surveyChannelCases.get(size - 1).getId().intValue()){//如果是最后一个。可能存在无法平分的情况 。 所以取余数
                            Double lastChannelFee = surveyChannelCost.getChnannelMoney() - totalChannelFee;
                            surveyCaseDirection.setChannelFeeCur(surveyCaseDirection.getChannelFeeCur() + lastChannelFee);
                        }else{
                            surveyCaseDirection.setChannelFeeCur(surveyCaseDirection.getChannelFeeCur() + channelFee);
                        }
                    }else{
                        surveyCaseDirection.setChannelFeeCur(surveyCaseDirection.getChannelFeeCur() + channelFee);
                    }

                    totalChannelFee = totalChannelFee + channelFee;
                    if (surveyCaseDirection.getChannelFeeCur() == 0){//若渠道费为0.  则将分值还原成改之前的分值
                        if (surveyCaseDirection.getHisScore() != null){
                            if (surveyCaseDirection.getHisScore() > 0){
                                surveyCaseDirection.setScore(surveyCaseDirection.getHisScore());
                            }
                        }
                    }else{
                        if (surveyCaseDirection.getScore() != null){
                            if (surveyCaseDirection.getScore() > 0) {
                                surveyCaseDirection.setHisScore(surveyCaseDirection.getScore());
                            }
                        }
                        surveyCaseDirection.setScore(0D);
                    }
                    surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                    surveyRiskCaseInfoApi.score(surveyCaseDirection.getSurveyInfoId());
                }
            }else if ("veto".equals(reviewType)){
                surveyChannelCost.setState(6);//驳回
                surveyChannelCost.setRejectDesc(apiRequest.getString("rejectDesc"));
            }
            surveyChannelCostMapper.updateByPrimaryKey(surveyChannelCost);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCost);
        }else if ("del".equals(btnCode)){
            surveyChannelCost.setDeleteFlag(1);
            surveyChannelCostMapper.updateByPrimaryKey(surveyChannelCost);

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCost);
        }else if ("generate".equals(btnCode)){//测试生成付款记录
            reTask.channel();
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
    @Autowired
    private ReTask reTask;

    @ApiMethod(needLogin = false,descript = "渠道费处理详情",value = "info-survey-channel")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        SurveyChannelCost surveyChannelCost = surveyChannelCostMapper.selectByPrimaryKey(id);
        if (surveyChannelCost == null){
            surveyChannelCost = new SurveyChannelCost();
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (surveyInvestigator == null){
                return  new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
            }
            //默认显示 上一条的渠道费银行信息
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyUserId",surveyInvestigator.getUserId());
            SurveyChannelCost upItem = surveyChannelCostMapper.selectUpItem(paramMap);
            if (upItem != null) {
                surveyChannelCost.setPayeeUserName(upItem.getPayeeUserName());
                surveyChannelCost.setBankDeposit(upItem.getBankDeposit());
                surveyChannelCost.setBankBranch(upItem.getBankBranch());
                surveyChannelCost.setBankNo(upItem.getBankNo());
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCost);
        }
        List<SurveyChannelCase> surveyChannelCases = surveyChannelCaseMapper.selectBySurveyChannelIdGroupBySurveyInfoId(surveyChannelCost.getId());
        if (surveyChannelCases.size() == 0){
            List<SurveyChannelCostItem> surveyChannelCostItems = surveyChannelCostItemMapper.selectBySurveyChannelId(surveyChannelCost.getId());
            for (SurveyChannelCostItem surveyChannelCostItem : surveyChannelCostItems) {
                SurveyChannelCase surveyChannelCase = new SurveyChannelCase();
                surveyChannelCase.setSurveyInfoId(surveyChannelCostItem.getSurveyInfoId());
                surveyChannelCase.setSurveyChannelId(surveyChannelCost.getId());
                surveyChannelCase.setSurveyNo(surveyChannelCostItem.getSurveyCaseNo());
                surveyChannelCase.setSelDirections(new ArrayList<>());
                surveyChannelCases.add(surveyChannelCase);
            }
        }
        for (SurveyChannelCase surveyChannelCase : surveyChannelCases) {
            //选中的方向集合
            if (!StringUtils.isEmpty(surveyChannelCase.getDirectionsStr())) {
                String[] directions = surveyChannelCase.getDirectionsStr().split(",");
                List<SurveyChannelCase> selDirections = new ArrayList<SurveyChannelCase>();
                for (String direction : directions) {
                    SurveyChannelCase selItem = new SurveyChannelCase();
                    selItem.setSurveyChannelId(surveyChannelCost.getId());
                    selItem.setSurveyInfoId(surveyChannelCase.getSurveyInfoId());
                    selItem.setSurveyDirectionId(Long.parseLong(direction));
                    selDirections.add(selItem);
                }
                surveyChannelCase.setSelDirections(selDirections);
            }

            //案件对应的方向集合
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(getCurrentUserId(apiRequest));
            if (surveyInvestigator == null){
                return  new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
            }
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyInfoId",surveyChannelCase.getSurveyInfoId());
            paramMap.put("orgId",surveyInvestigator.getOrgId());
            List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.selectDirections(paramMap);
            List<SurveyChannelCase> allDirections = new ArrayList<SurveyChannelCase>();
            for (SurveyCaseDirection direction : directions) {
                SurveyChannelCase allItem = new SurveyChannelCase();
                allItem.setSurveyChannelId(surveyChannelCost.getId());
                allItem.setSurveyInfoId(surveyChannelCase.getSurveyInfoId());
                allItem.setSurveyDirectionId(direction.getId());
                allItem.setSurveyDirectionName(direction.getDirectionName());
                allDirections.add(allItem);
            }
            surveyChannelCase.setAllDirections(allDirections);
        }
        surveyChannelCost.setSurveyChannelCases(surveyChannelCases);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCost);
    }

    @ApiMethod(needLogin = false,descript = "异步获取渠道费数据",value = "ajax-data-survey-channel")
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        String dataType = apiRequest.getString("dataType");
        if ("cases".equals(dataType)){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(getCurrentUserId(apiRequest));
            if (surveyInvestigator == null){
                return  new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
            }
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigator.getOrgId());
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("orgId",surveyInvestigator.getOrgId());
            Integer insuranceType = surveyFranchisee.getInsuranceType();
            if (insuranceType == 1){
                paramMap.put("searchData",1);//保司直营查询保司
            }
            Integer type = surveyFranchisee.getType();
            if (type == 1){
                paramMap.put("searchData",2);//互助直营查询互助
            }
            if (type == 1 && insuranceType == 1){
                paramMap.put("searchData",3);//保司+互助直营查询 保司+互助
            }
            List<SurveyAssignOrgDto> cases = surveyAssignOrgMapper.selectChannels(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,cases.size(),cases);
        }else if ("directions".equals(dataType)){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(getCurrentUserId(apiRequest));
            if (surveyInvestigator == null){
                return  new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
            }
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyInfoId",apiRequest.getString("surveyInfoId"));
            paramMap.put("orgId",surveyInvestigator.getOrgId());
            List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.selectDirections(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,directions.size(),directions);
        }else if ("bank".equals(dataType)){
            List<BankInfo> list = bankInfoMapper.selectBankInfoList(new HashMap<>());
            return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
