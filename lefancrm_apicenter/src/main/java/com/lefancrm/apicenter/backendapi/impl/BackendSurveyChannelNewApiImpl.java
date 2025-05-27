package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendSurveyChannelApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyChannelNewApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyAssignOrgDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.task.ReTask;
import com.lefancrm.apicenter.util.DecimalUtil;
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
public class BackendSurveyChannelNewApiImpl extends BaseServiceImpl implements BackendSurveyChannelNewApi {
    @Autowired
    private SurveyChannelCostNewMapper surveyChannelCostNewMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private BankInfoMapper bankInfoMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;
    @Autowired
    private BackendSurveyRiskCaseInfoApiImpl backendSurveyRiskCaseInfoApi;


    @ApiMethod(needLogin = false,descript = "渠道费管理列表",value = "list-survey-channel-new")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        if ("report".equals(apiRequest.getString("report"))){

        }else{
            setBackendPageSize(apiRequest);
        }

        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        //如果只有机构负责人的角色。 则查询自己的数据。
        Boolean orgRole = isRoleUser(userRoles,58L);
        Boolean reviewRole = isRoleUser(userRoles,112L);
        Boolean ceshiRole = isRoleUser(userRoles,28L);//测试角色 查看所有
        apiRequest.put("surveyUserId",currentUserId);
        if (reviewRole || ceshiRole){
            apiRequest.remove("surveyUserId");
        }
        int count = surveyChannelCostNewMapper.listSize(apiRequest);
        List<SurveyChannelCostNew> list = surveyChannelCostNewMapper.list(apiRequest);
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

    @ApiMethod(needLogin = false,descript = "渠道费操作管理",value = "operate-survey-channel-new")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        String btnCode = apiRequest.getString("btnCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        Long id = apiRequest.getLong("id");
        SurveyChannelCostNew surveyChannelCostNew = surveyChannelCostNewMapper.selectByPrimaryKey(id);
        if ("edit".equals(btnCode)){//编辑
            SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(surveyChannelCostNew.getSurveyDirectionId());
            surveyChannelCostNew.setChnannelMoney(apiRequest.getDouble("chnannelMoney"));
            surveyChannelCostNew.setChannelDesc(apiRequest.getString("channelDesc"));
            surveyChannelCostNew.setPayeeUserName(apiRequest.getString("payeeUserName"));
            surveyChannelCostNew.setBankDeposit(apiRequest.getString("bankDeposit"));
            surveyChannelCostNew.setBankBranch(apiRequest.getString("bankBranch"));
            surveyChannelCostNew.setBankNo(apiRequest.getString("bankNo"));
            surveyChannelCostNew.setRejectDesc(null);
            if (surveyCaseDirection.getChannelFeeCur() == null) {
                surveyCaseDirection.setChannelFeeCur(0D);
            }
            if (surveyCaseDirection.getChannelFeeSent() == null){
                surveyCaseDirection.setChannelFeeSent(0D);
            }
            if (surveyChannelCostNew.getChnannelMoney() == null){
                surveyChannelCostNew.setChnannelMoney(0D);
            }
            surveyChannelCostNew.setState(3);
            surveyChannelCostNew.setReviewerTime(new Date());
            if (surveyChannelCostNew.getChnannelMoney() > surveyCaseDirection.getChannelFeeSent()){//当金额大于限额 则需要审核
                surveyChannelCostNew.setState(5);
                surveyChannelCostNew.setReviewerTime(null);
            }
            surveyChannelCostNewMapper.updateByPrimaryKey(surveyChannelCostNew);

            if (surveyCaseDirection != null) {
                surveyCaseDirection.setHisScore(surveyCaseDirection.getScore());
                surveyCaseDirection.setScore(0D);
                surveyCaseDirection.setChannelFeeCur(surveyChannelCostNew.getChnannelMoney());
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                backendSurveyRiskCaseInfoApi.score(surveyCaseDirection.getSurveyInfoId());
            }

            if (surveyChannelCostNew.getState() == 5){
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyId());
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("roleId", 112L);
                List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                Map<String, Object> msgMap = new HashMap<String, Object>();
                msgMap.put("title", "渠道费用审核");
                msgMap.put("content", "你有渠道费用审核，请尽快进行审核！");
                msgMap.put("keyWords", "关联案件号：" + surveyRiskCase.getSurveyCaseNo() + "\n申请人：" + surveyChannelCostNew.getSurveyUserName() + "\n渠道金额：" + surveyChannelCostNew.getChnannelMoney() + "元");
                backendWechatApi.send(toUsers, msgMap);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCostNew);
        }else if ("review".equals(btnCode)){
            String reviewType = apiRequest.getString("reviewType");
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            if ("yes".equals(reviewType)){
                surveyChannelCostNew.setState(33);
                surveyChannelCostNew.setReviewerTime(new Date());
                surveyChannelCostNew.setReviewerUserId(userInfo.getUserId());
                surveyChannelCostNew.setReviewerUserName(userInfo.getUserName());
            }else if ("veto".equals(reviewType)){
                surveyChannelCostNew.setState(6);//驳回
                surveyChannelCostNew.setRejectDesc(apiRequest.getString("rejectDesc"));
            }
            surveyChannelCostNewMapper.updateByPrimaryKey(surveyChannelCostNew);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCostNew);
        }else if ("del".equals(btnCode)){
            surveyChannelCostNew.setDeleteFlag(1);
            surveyChannelCostNewMapper.updateByPrimaryKey(surveyChannelCostNew);
            SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(surveyChannelCostNew.getSurveyDirectionId());
            surveyCaseDirection.setScore(surveyCaseDirection.getHisScore());
            surveyCaseDirection.setChannelType(0);
            surveyCaseDirection.setChannelFeeCur(0D);
            surveyCaseDirection.setChannelFeeSent(0D);
            surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
            backendSurveyRiskCaseInfoApi.score(surveyCaseDirection.getSurveyInfoId());
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCostNew);
        }else if ("first-commit".equals(btnCode)){//第一步提交审核
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (surveyInvestigator == null){
                return  new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
            }
            String channelCases = apiRequest.getString("channelCases");
            if (!StringUtils.isEmpty(channelCases)) {
                List<SurveyChannelCostNew> costNews = JSONArray.parseArray(channelCases, SurveyChannelCostNew.class);
                for (SurveyChannelCostNew item : costNews) {
                    SurveyChannelCostNew valid = surveyChannelCostNewMapper.selectChannelCostNewByDirectionId(item.getSurveyDirectionId());
                    if (valid != null){//验证重复
                        continue;
                    }
                    SurveyChannelCostNew record = new SurveyChannelCostNew();
                    record.setSurveyInfoId(item.getSurveyInfoId());
                    record.setSurveyDirectionId(item.getSurveyDirectionId());
                    record.setChnannelMoney(item.getChnannelMoney());
                    record.setMaxChannelMoney(item.getMaxChannelMoney());
                    record.setChannelDesc(item.getChannelDesc());
                    if (record.getChnannelMoney() == null){
                        record.setChnannelMoney(0D);
                    }
                    if (record.getMaxChannelMoney() == null){
                        record.setMaxChannelMoney(0D);
                    }
                    record.setState(3);
                    record.setReviewerTime(new Date());
                    if (record.getChnannelMoney() > record.getMaxChannelMoney()){//渠道费用 大于设置的渠道费用则需要审核
                        record.setState(5);
                        record.setReviewerTime(null);
                    }
                    record.setSurveyOrgId(surveyInvestigator.getOrgId());
                    record.setSurveyOrgName(surveyInvestigator.getOrgName());
                    record.setSurveyUserId(surveyInvestigator.getUserId());
                    record.setSurveyUserName(surveyInvestigator.getRealName());
                    record.setOperationTime(new Date());
                    record.setOperationUserId(surveyInvestigator.getUserId());
                    record.setOperationUserName(surveyInvestigator.getRealName());
                    record.setPayeeUserName(apiRequest.getString("payeeUserName"));
                    record.setBankDeposit(apiRequest.getString("bankDeposit"));
                    record.setBankBranch(apiRequest.getString("bankBranch"));
                    record.setBankNo(apiRequest.getString("bankNo"));
                    record.setRejectDesc(null);
                    record.setDeleteFlag(0);
                    record.setIsProPay(0);
                    surveyChannelCostNewMapper.insert(record);

                    SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(record.getSurveyDirectionId());
                    surveyCaseDirection.setHisScore(surveyCaseDirection.getScore());
                    surveyCaseDirection.setScore(0D);
                    surveyCaseDirection.setChannelType(1);
                    surveyCaseDirection.setChannelFeeCur(record.getChnannelMoney());
                    surveyCaseDirection.setChannelFeeSent(record.getMaxChannelMoney());
                    surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                    backendSurveyRiskCaseInfoApi.score(surveyCaseDirection.getSurveyInfoId());


                    if (record.getState() == 5){
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        paramMap.put("roleId", 112L);
                        List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                        Map<String, Object> msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "渠道费用审核");
                        msgMap.put("content", "你有渠道费用审核，请尽快进行审核！");
                        msgMap.put("keyWords", "关联案件号：" + item.getSurveyCaseNo() + "\n申请人：" + record.getSurveyUserName() + "\n渠道金额：" + item.getChnannelMoney() + "元");
                        backendWechatApi.send(toUsers, msgMap);
                    }
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        else if ("generate".equals(btnCode)){//测试生成付款记录
            reTask.channelNew();
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
    @Autowired
    private ReTask reTask;

    @ApiMethod(needLogin = false,descript = "渠道费处理详情",value = "info-survey-channel-new")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        SurveyChannelCostNew surveyChannelCostNew = surveyChannelCostNewMapper.selectByPrimaryKey(id);
        if (surveyChannelCostNew == null){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (surveyInvestigator == null){
                return  new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
            }
            //默认显示 上一条的渠道费银行信息
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyUserId",surveyInvestigator.getUserId());
            SurveyChannelCostNew upItem = surveyChannelCostNewMapper.selectChannelCostNewUp(paramMap);
            if (upItem != null) {
                surveyChannelCostNew = new SurveyChannelCostNew();
                surveyChannelCostNew.setPayeeUserName(upItem.getPayeeUserName());
                surveyChannelCostNew.setBankDeposit(upItem.getBankDeposit());
                surveyChannelCostNew.setBankBranch(upItem.getBankBranch());
                surveyChannelCostNew.setBankNo(upItem.getBankNo());
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCostNew);
        }
        SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(surveyChannelCostNew.getSurveyDirectionId());
        //案件名称 方向名称
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyId());
        surveyChannelCostNew.setSurveyCaseNo(surveyRiskCase.getSurveyCaseNo());
        surveyChannelCostNew.setSurveyDirectionName(surveyCaseDirection.getDirectionName());
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelCostNew);
    }


    @ApiMethod(needLogin = false,descript = "异步获取渠道费数据",value = "ajax-data-survey-channel-new")
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
            paramMap.put("searchData",4); //默认查询不到任何数据
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

            SurveyChannelModelOrg surveyChannelModelOrg = surveyChannelModelOrgMapper.selectOne(surveyInvestigator.getOrgId());
            List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.selectDirections(paramMap);
            if (surveyChannelModelOrg != null){
                Map<String,Object> parapMap =  new HashMap<String,Object>();
                parapMap.put("modelId",surveyChannelModelOrg.getModelId());
                List<SurveyChannelModelInfo> modelInfos = surveyChannelModelInfoMapper.list(parapMap);
                for (SurveyCaseDirection direction : directions) {
                    Integer areaId = null;
                    Integer areaType = direction.getAreaType();
                    if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                        areaId = direction.getCityId();
                    }else{
                        areaId = direction.getDistrictId();
                    }
                    paramMap.put("areaId",areaId);
                    //根据区域ID查询方向属于几类。
                    paramMap.put("modelId",surveyChannelModelOrg.getModelId());
                    SurveyChannelModelAreaCity surveyChannelModelAreaCity = surveyChannelModelAreaCityMapper.selectByAreaIdAndModelId(paramMap);
                    if (surveyChannelModelAreaCity != null){
                        for (SurveyChannelModelInfo modelInfo : modelInfos) {
                            if (surveyChannelModelAreaCity.getAreaCategoriesId().intValue() == modelInfo.getAreaCategoriesId().intValue()
                                && direction.getTaskId().intValue() == modelInfo.getTaskId().intValue() && direction.getNewId().intValue() == modelInfo.getTaskContentId().intValue()
                                && direction.getDirectionResultTypeId().intValue() == modelInfo.getTaskContentResultId().intValue())
                            {
                                direction.setChannelFeeCur(modelInfo.getPrice());
                                direction.setChannelFeeSent(modelInfo.getPrice());
                                break;
                            }
                        }
                    }
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,directions.size(),directions);
        }else if ("bank".equals(dataType)){
            List<BankInfo> list = bankInfoMapper.selectBankInfoList(new HashMap<>());
            return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @Autowired
    private SurveyChannelModelOrgMapper surveyChannelModelOrgMapper;
    @Autowired
    private SurveyChannelModelInfoMapper surveyChannelModelInfoMapper;
    @Autowired
    private SurveyChannelModelAreaCityMapper surveyChannelModelAreaCityMapper;
}
