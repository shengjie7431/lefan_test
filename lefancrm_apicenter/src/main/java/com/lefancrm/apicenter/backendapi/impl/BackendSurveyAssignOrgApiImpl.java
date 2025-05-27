package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendSurveyAssignOrgApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.dto.help.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.model.CommonFile;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.*;
import com.lefancrm.apicenter.util.pinganfu.StringUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
// import org.codehaus.jackson.map.util.Comparators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lixianfeng on 2019/2/15.
 */
@Service
@ApiService(descript = "案件分配机构API")
public class BackendSurveyAssignOrgApiImpl extends BaseServiceImpl implements BackendSurveyAssignOrgApi{
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private BackendSurveyProgressApiImpl backendSurveyProgressApi;
    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;
    @Autowired
    private SurveyCaseDirectionFileMapper surveyCaseDirectionFileMapper;
    @Autowired
    private BackendSurveyMessageApiImpl backendSurveyMessageApi;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private SurveyTaskInfoMapper surveyTaskInfoMapper;
    @Autowired
    private SurveyTaskInfoContentMapper surveyTaskInfoContentMapper;
    @Autowired
    private SurveyFeeDetailsMapper surveyFeeDetailsMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyHelpInfoMapper surveyHelpInfoMapper;
    @Autowired
    private SurveyConsignorModelMapper surveyConsignorModelMapper;
    @Autowired
    private SurveyModelInfoMapper surveyModelInfoMapper;
    @Autowired
    BackendSurveyInvestigatorCaseApiImpl surveyInvestigatorCaseApi;
    @Autowired
    private BackendSurveyCaseWorkflowApiImpl surveyCaseWorkflowApi;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private BackendSurveyRiskCaseInfoApiImpl surveyRiskCaseInfoApi;
    @Autowired
    private SurveyAssignOrgExtensionMapper surveyAssignOrgExtensionMapper;
    @Autowired
    private SurveyAssignOrgTypeMapper surveyAssignOrgTypeMapper;
    @Autowired
    private SurveyOrgPrescriptionFlowMapper surveyOrgPrescriptionFlowMapper;
    @Autowired
    private SurveyUserPrescriptionFlowMapper surveyUserPrescriptionFlowMapper;
    @Autowired
    private SurveyCheckPreFlowMapper surveyCheckPreFlowMapper;
    @Autowired
    private SurveyAssignOrgExtendMapper surveyAssignOrgExtendMapper;
    @Autowired
    private SurveyChannelCostMapper surveyChannelCostMapper;
    @Autowired
    private SurveyChannelCostNewMapper surveyChannelCostNewMapper;

    @Autowired
    private BackendWechatApiImpl backendWechatApi;

    @Autowired
    private BusUserRoleMapper busUserRoleMapper;

    @Autowired
    private SurveyAssignOrgReplyMapper surveyAssignOrgReplyMapper;

    @Autowired
    private SurveyProgressMapper surveyProgressMapper;
    @Autowired
    private SurveyAttrUpdRecordMapper surveyAttrUpdRecordMapper;
    @Autowired
    private SurveyEmailInfoOrgMapper surveyEmailInfoOrgMapper;
    @Autowired
    private SurveyEmailInfoMapper surveyEmailInfoMapper;
    @Autowired
    private SurveyConsignerMapper surveyConsignerMapper;
    @Autowired
    private SurveyConsignorEfficiencyModelInfoMapper surveyConsignorEfficiencyModelInfoMapper;
    @Autowired
    private SurveyBillingApplyMapper surveyBillingApplyMapper;

    @Autowired
    private SurveyBackReplyMapper surveyBackReplyMapper;

    @Autowired
    private SurveyAssignOrgBackMapper surveyAssignOrgBackMapper;

    @ApiMethod(needLogin = false,descript = "机构案件列表（分派调查员）",value = "list-survey-assign-org")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        try{
            Long currentUserId = getCurrentUserId(apiRequest);
            String menuCode = apiRequest.getString("menuCode");

            apiRequest.put("searchStr",apiRequest.getString("searchStr") == null ? null : apiRequest.getString("searchStr").trim());
            //模糊查询
            String operateState = apiRequest.getString("operateState");

            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            //导出时，会传值pageIndex，否则会第一页数据一直重复
            String pageIndex = apiRequest.getString("pageIndex");
            if(pageIndex == null){
                setBackendPageSize(apiRequest);
            }
            if ("assign-org-list".equals(menuCode)){
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
                if (surveyInvestigator == null){
                    return new ApiResponse(ApiMsgEnum.SURVEY_INVESTIGATOR);
                }
                //新需求，省级机构查询自身机构及名下子机构的案件, 子机构没有查询权限
                /*SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigator.getOrgId());
                if(surveyFranchisee !=null && surveyFranchisee.getLevel() ==1){
                    //apiRequest.put("surveyOrgId",surveyInvestigator.getOrgId());//分派调查员列表 和 机构复核列表。  机构经理只查询该机构的所有案件
                    //2020年1月16日  14点57分。 只要案件还是调查中 或 平台复审中。 机构都可再次分派。
                    apiRequest.put("search",1); // 案件状态为（4,8,10,12,14,16,22,30）
                    apiRequest.put("surveyParentOrgId",surveyInvestigator.getOrgId());
                    apiRequest.put("order",2);  //主动退回 survey_return 优先排序
                }else{
                    return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
                }*/

                apiRequest.put("search",1); // 案件状态为（4,8,10,12,14,16,22,30）
                apiRequest.put("surveyParentOrgId",surveyInvestigator.getOrgId());
                apiRequest.put("order",2);
            }else if ("org-review-list".equals(menuCode)){
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
                if (surveyInvestigator == null){
                    return new ApiResponse(ApiMsgEnum.SURVEY_INVESTIGATOR);
                }
                //apiRequest.put("surveyOrgId",surveyInvestigator.getOrgId());//分派调查员列表 和 机构复核列表。  机构经理只查询该机构的所有案件
                //2020年4月15日16:59:55 新需求：省级机构可以查询名下子机构的案件
                apiRequest.put("surveyParentOrgId",surveyInvestigator.getOrgId());
                //查询初审中
                if ("0".equals(operateState) || operateState == null || "".equals(operateState)){
                    apiRequest.put("search",2);//org_survey_state = 2
                }else if ("1".equals(operateState)){
                    apiRequest.put("search",3);
                }
                apiRequest.put("order",98);  //退回原因org_opinion 优先排序
            }else if("time-track-list".equals(menuCode)){ //时效跟踪
                String orgSurveyStates = apiRequest.getString("orgSurveyStates");
                if (orgSurveyStates != null && !"".equals(orgSurveyStates)){
                    if ("1".equals(orgSurveyStates)){
                        orgSurveyStates = "1,5";//调查中 包含多种状态
                    }
                    apiRequest.put("orgSurveyStates",orgSurveyStates);
                }
                apiRequest.put("search",10);
                apiRequest.put("userId",currentUserId);
            }else if("extension-time".equals(menuCode)){//延期审核
                String extensionState = apiRequest.getString("extensionState");
                if (extensionState == null || "".equals(extensionState) || "1".equals(extensionState)){
                    apiRequest.put("extensionState",1);//待审核
                }
                apiRequest.put("search",11);
                apiRequest.put("userId",currentUserId);
            }else if ("task-org-review".equals(menuCode)){//平台预审
                apiRequest.put("search",20);// 机构已提交 。但还未到平台复审
                apiRequest.put("order",2);
                apiRequest.put("belongUserId",currentUserId);//案件归属人
            }else if("help-review".equals(menuCode)){// 互助平台复审
                //平台复审（保司） 修改逻辑   只查询非互助案件
                //历史已经在平台复审的互助案件。 如何处理？？？？？？？？？？？？？？？？？？？
                //平台复审（互助） 查询： 机构初审通过 and 复审人员是当前登陆人 and (调查中的案件 or 平台复核的案件）
                        // 复审通过逻辑：  最后一个机构的复审人员复审通过 将案件状态变为保司审核中 及 平台复审时间改为当前时间
                        // 保司审核退回：  退回给哪个复审人员？？？？？？？？？

                //增加脚本
                    //机构案件表增加 复审人员ID，名称，复审提交时间。
                    //survey_risk_case_info增加 指导状态  0未指导 1已指导
                        //机构案件分派增加 逻辑  查询已指导的案件
                        //增加 指导信息表（确诊疾病，可能的阳性点，重点调查方向，注意事项等）
                        //历史数据处理： 所有未分派的案件改为未指导  ， 已分派的案子改为已指导。


                // ！！！！！！！！！！！！！！！以下逻辑不通。  复审人员加载  和保司 以及 调查机构两者都有关。 重新考虑如何加载复审人员？？？？？
                //调查机构表 增加当前复审人员ID名称 及  下一次复审人员ID名称
                        //分派机构的时候 保存当前复审人员以及下一次复审人员
                            //下一次复审人员

                apiRequest.put("search",24);
                apiRequest.put("reviewUserId",currentUserId);//案件归属人
                if (userInfo.getUserId().intValue() == 477  || userInfo.getUserId().intValue() == 2189
                        || userInfo.getUserId().intValue() == 669
                        || userInfo.getUserId().intValue() == 26){
                    apiRequest.put("reviewUserId",null);
                }

            }

            String surveyStates = apiRequest.getString("surveyStates");
            if (surveyStates != null && !"".equals(surveyStates)){
                if ("12".equals(surveyStates)){
                    surveyStates = "12,8,14,16,26";//调查中 包含多种状态
                }
                if ("22".equals(surveyStates)){
                    surveyStates = "22,30";//平台复审中 包含多种状态
                }
                apiRequest.put("surveyStates",surveyStates);
            }
            int count = 0;
            List<SurveyAssignOrgDto> list = null;
            if ("assign-org-list".equals(menuCode)){
                if(apiRequest.getString("investigators") == null){
                    Map<String,Object> dataRoleMap = getDataRole(currentUserId,"");
                    if(dataRoleMap.get("dataRoleCode") !=null){
                        String dataRoleCode=dataRoleMap.get("dataRoleCode").toString();
                        if("districtManger".equals(dataRoleCode)){
                            SurveyInvestigator surveyInvestigator=surveyInvestigatorMapper.selectByUserId(currentUserId);
                            if(surveyInvestigator.getSurveyAreaId() == null){
                                apiRequest.put("investigators", currentUserId);
                            }else {
                                Map map = new HashMap();
                                map.put("surveyAreaId", surveyInvestigator.getSurveyAreaId());
                                List<SurveyInvestigator> SurveyInvestigatorList = surveyInvestigatorMapper.selectByMap(map);
                                String investigators = SurveyInvestigatorList.stream().map(r -> "" + r.getUserId()).collect(Collectors.joining(","));
                                apiRequest.put("investigators", investigators);
                            }
                        }
                    }
                }
                count = surveyAssignOrgMapper.selectAssignOrgListSize(apiRequest);
                list = surveyAssignOrgMapper.selectAssignOrgList(apiRequest);
            }else if("help-review".equals(menuCode)){
                count = surveyAssignOrgMapper.selectHelpReviewListSize(apiRequest);
                list = surveyAssignOrgMapper.selectHelpReviewList(apiRequest);
            }else if("extension-time".equals(menuCode)){
                count = surveyAssignOrgMapper.selectExtensionTimeListSize(apiRequest);
                list = surveyAssignOrgMapper.selectExtensionTimeList(apiRequest);
            }
            else{
                //互助1  保司
                String oprTypeValue = apiRequest.getString("oprTypeValue");
                if ("1".equals(oprTypeValue)){
                    String orgSurveyStates = apiRequest.getString("orgSurveyStates");
                    if (!StringUtils.isEmpty(orgSurveyStates)){
                        String[] strs = orgSurveyStates.split(",");
                        for (String str : strs) {
                            if ("7".equals(str)){
                                apiRequest.put("orgStatusItem1",1);
                            }
                            if ("8".equals(str)){
                                apiRequest.put("orgStatusItem2",1);
                            }
                            if ("9".equals(str)){
                                apiRequest.put("orgStatusItem3",1);
                            }
                        }
                    }
                }else{

                }

                if ("time-track-list".equals(menuCode)){
                    String oprType = apiRequest.getString("oprType");
                    if ("safe".equals(oprType)) {//时效跟踪保司
                        String handquery =  apiRequest.getString("handquery");
                        if (StringUtils.isEmpty(handquery)){//初始化页面设置默认值
//                            apiRequest.put("dateItem", 3);
                            if (StringUtils.isEmpty( apiRequest.getString("orgSurveyStates"))) {
                                apiRequest.put("orgSurveyStates", "1,2");
                            }
//                            if (StringUtils.isEmpty( apiRequest.getString("startDate"))) {
//                                apiRequest.put("startDate", LocalDate.now().plusDays(1).toString());
//                                apiRequest.put("endDate", LocalDate.now().plusDays(1).toString());
//                            }
                        }
                    }
                }
                count = surveyAssignOrgMapper.listSize(apiRequest);
                list = surveyAssignOrgMapper.list(apiRequest);
            }


            List<Long> collect = list.stream().map(SurveyAssignOrg::getSurveyId).collect(Collectors.toList());
            List<SurveyRiskCase> surveyRiskCases = new ArrayList<>();
            if (collect.size() > 0){
                surveyRiskCases = surveyRiskCaseMapper.list(collect);
            }

            List<SurveyRiskCaseInfoDto> surveyRiskCaseInfos = new ArrayList<>();
            List<Long> collect1 = list.stream().map(SurveyAssignOrg::getSurveyInfoId).collect(Collectors.toList());
            if (collect1.size() > 0) {
                surveyRiskCaseInfos = surveyRiskCaseInfoMapper.listByIds(collect1);
            }

            List<Long> collect2 = list.stream().map(SurveyAssignOrg::getId).collect(Collectors.toList());

            List<SurveyAssignOrgExtension> surveyAssignOrgExtensions = new ArrayList<>();//延期记录
            List<SurveyBackReply> surveyBackReplies = new ArrayList<>();//案件沟通记录
            List<SurveyAssignOrgBack> surveyAssignOrgBacks = new ArrayList<>();//退回记录
            if ("time-track-list".equals(menuCode) && collect1.size() > 0){
                surveyAssignOrgExtensions = surveyAssignOrgExtensionMapper.listByIds(collect1);
                surveyBackReplies = surveyBackReplyMapper.listByIds(collect1);
                surveyAssignOrgBacks = surveyAssignOrgBackMapper.listByIds(collect1);
            }


            List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtos = new ArrayList<>();
            if (collect2.size() > 0){
                surveyInvestigatorCaseDtos = surveyInvestigatorCaseMapper.listByIds(collect2);
            }


            for (SurveyAssignOrgDto dto : list) {
                dto.setSurveyRiskCase(surveyRiskCases.stream().filter(e -> e.getId().toString().equals(dto.getSurveyId().toString())).findFirst().get());
                SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfos.stream().filter(e -> e.getId().toString().equals(dto.getSurveyInfoId().toString())).findFirst().get();

                Map selectByMap=new HashMap();
                selectByMap.put("surveyInfoId",dto.getSurveyInfoId());
                selectByMap.put("surveyOrgId",dto.getSurveyOrgId());
                StringBuffer stringBuffer=new StringBuffer();
                List<SurveyInvestigatorCaseDto> SurveyInvestigatorCaseDtoList= surveyInvestigatorCaseDtos.stream().filter(e ->
                        e.getDeleteFlag() == 0 &&
                        e.getSurveyAssorgCaseId().toString().equals(dto.getId().toString())).collect(Collectors.toList());
                int i=0;
                for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto:SurveyInvestigatorCaseDtoList) {
                    if(i>0){
                        stringBuffer.append(",");
                    }
                    stringBuffer.append(surveyInvestigatorCaseDto.getSurveyUserName());
                    i++;
                }
                if (SurveyInvestigatorCaseDtoList.size() > 1){
                    SurveyInvestigatorCaseDtoList.sort(Comparator.comparing(SurveyInvestigatorCaseDto :: getAssignDate));
                    SurveyInvestigatorCaseDtoList.get(0).setShowKey(true);
                }
                surveyRiskCaseInfo.setSurveyUserName(stringBuffer.toString());
                dto.setSurveyRiskCaseInfo(surveyRiskCaseInfo);

                //是否是紧急案件
                String caseEndTime = DateUtils.DateToStr(surveyRiskCaseInfo.getEndTime(),"yyyyMMdd");
                String orgEndTime = DateUtils.DateToStr(dto.getOrgEndTime(),"yyyyMMdd");
                if (caseEndTime.compareTo(orgEndTime) < 0) {
                    dto.setUrgent(true);
                }


                int efficiencyAttr =  dto.getEfficiencyAttr(); //时效设置（1：工作日；2、自然日）
                if ("assign-org-list".equals(menuCode)) {
                    //是否超时
                    if (dto.getOrgEndTime() != null && dto.getOrgEndTime().compareTo(new Date()) == -1) {
                        dto.setIsOverTime(true);
                    } else {
                        dto.setIsOverTime(false);
                    }
                }
                if("org-review-list".equals(menuCode)){//机构初审
                    Date endTime=dto.getOrgEndTime();
                    if(dto.getReportDate()!=null){
                        if(dto.getReportDate().before(endTime)){
                            int days = GetWorkDay.calLeaveDays(dto.getCreateTime(), dto.getReportDate(),efficiencyAttr);
                            days = Math.abs(days);
                            //剩余n天
                            dto.setEfficiencyState("时效："+days+"天");
                            dto.setEfficiencyStateColor("green");
                        }else{
                            int days = GetWorkDay.calLeaveDays(endTime, dto.getReportDate(),efficiencyAttr);
                            days = Math.abs(days);
                            //超时n天
                            dto.setEfficiencyState("时效："+days+"天");
                            dto.setEfficiencyStateColor("red");
                        }
                    }else{
                        if(new Date().before(endTime)){
                            int days = GetWorkDay.calLeaveDays(new Date(), endTime,efficiencyAttr);
                            days = Math.abs(days);
                            //剩余n天
                            dto.setEfficiencyState("剩余："+days+"天");
                            dto.setEfficiencyStateColor("#eda42e");
                        }else{
                            int days = GetWorkDay.calLeaveDays(endTime,new Date(),efficiencyAttr);
                            days = Math.abs(days);
                            //超时n天
                            dto.setEfficiencyState("超时："+days+"天");
                            dto.setEfficiencyStateColor("red");
                        }
                    }
                }
                if("time-track-list".equals(menuCode)){//时效跟踪
                    dto.setSurveyRiskCaseInfoDto(surveyRiskCaseInfo);
                    //案件时效
                    if(dto.getSurveyRiskCaseInfoDto().getEntrustReportStartDate() == null){ //未提交保司审核
                        //“当前时间”与“案件截止时间”相比：
                        Date endTime = dto.getSurveyRiskCaseInfoDto().getEndTime()==null ? new Date() : dto.getSurveyRiskCaseInfoDto().getEndTime();
                        if(new Date().before(endTime)){
                            int days = GetWorkDay.calLeaveDays(new Date(), endTime,efficiencyAttr);
                            days = Math.abs(days);
                            //剩余n天
                            dto.getSurveyRiskCaseInfoDto().setEfficiencyState("剩余："+days+"天");
                            dto.getSurveyRiskCaseInfoDto().setEfficiencyStateColor("#eda42e");
                        }else{
                            int days = GetWorkDay.calLeaveDays(endTime, new Date(),efficiencyAttr);
                            days = Math.abs(days);
                            //超时n天
                            dto.getSurveyRiskCaseInfoDto().setEfficiencyState("超时："+days+"天");
                            dto.getSurveyRiskCaseInfoDto().setEfficiencyStateColor("red");
                        }
                    }
                    //提交保司审核
                    else{
                        //“委托时间”
                        Date entrustTime = dto.getSurveyRiskCase().getEntrustTime() == null ? new Date() : dto.getSurveyRiskCase().getEntrustTime();
                        //“案件截止时间”
                        Date endTime = dto.getSurveyRiskCaseInfoDto().getEndTime()==null ? new Date() : dto.getSurveyRiskCaseInfoDto().getEndTime();
                        //“提交保司审核时间”
                        Date entrustReportStartTime = dto.getSurveyRiskCaseInfoDto().getEntrustReportStartDate() == null ? new Date() : dto.getSurveyRiskCaseInfoDto().getEntrustReportStartDate();

                        int days = GetWorkDay.calLeaveDays(entrustTime, entrustReportStartTime,efficiencyAttr);
                        days = Math.abs(days);
                        dto.getSurveyRiskCaseInfoDto().setEfficiencyState("时效："+days+"天");

                        if(entrustReportStartTime.before(endTime)){
                            dto.getSurveyRiskCaseInfoDto().setEfficiencyStateColor("green");
                        }else {
                            dto.getSurveyRiskCaseInfoDto().setEfficiencyStateColor("red");
                        }
                    }
//                    //添加最后一次跟踪信息
                    dto.setLastFollowContent((dto.getFollowTime() != null ? DateFormatUtils.format(dto.getFollowTime(), "yyyy-MM-dd") : "") + " " + Optional.ofNullable(dto.getFollowInformation()).orElse(""));
                }

                if("time-track-list".equals(menuCode) || "assign-org-list".equals(menuCode) || "task-org-review".equals(menuCode)){
                    //机构时效
                    //分配给机构时间
                    Date createTime = dto.getCreateTime()==null ? new Date() : dto.getCreateTime();
                    //机构截止时间
                    Date endTime = dto.getOrgEndTime()==null ? new Date() : dto.getOrgEndTime();
                    //提交初审通过时间
                    Date reportDate = dto.getReportDate()==null ? new Date() : dto.getReportDate();
                    if(dto.getOrgSurveyState() == 4){ //初审通过
//                        int days = GetWorkDay.calLeaveDays(createTime, reportDate,efficiencyAttr);
//                        int days = dto.getAgingDay();
//                        days = Math.abs(days);
//                        if (new SimpleDateFormat("yyyyMMdd").format(createTime).equals(new SimpleDateFormat("yyyyMMdd").format(reportDate))){
//                            days = 0;
//                        }
//                        if(reportDate.before(endTime) || days == 0){ //未超时
                        if(dto.getAgingOver().intValue() == 0){
                            dto.setEfficiencyState("时效："+dto.getAgingReal().intValue()+"天");
                            dto.setEfficiencyStateColor("green");
                        }else{
                            dto.setEfficiencyState("时效："+dto.getAgingReal().intValue()+"天");
                            dto.setEfficiencyStateColor("red");
                        }
                    }else{
//                        if(new Date().before(endTime)){ //未超时
//                            int days = GetWorkDay.calLeaveDays(new Date(), endTime,efficiencyAttr);
////                            int days = dto.getAgingDay();
//                            days = Math.abs(days);
                        if(dto.getAgingOver().intValue() == 0){
                            int days = GetWorkDay.calLeaveDays(endTime,new Date(),efficiencyAttr);
                            dto.setEfficiencyState("剩余："+Math.abs(days)+"天");
                            dto.setEfficiencyStateColor("#eda42e");
                        }else{
//                            int days = GetWorkDay.calLeaveDays(endTime, new Date(),efficiencyAttr);
//                            int days = dto.getAgingDay();
//                            days = Math.abs(days);
                            dto.setEfficiencyState("超时："+dto.getAgingOver().intValue()+"天");
                            dto.setEfficiencyStateColor("red");
                        }
                    }
                }

                if ("time-track-list".equals(menuCode)){
                    // todo 延期申请记录、退回记录 、沟通记录 组装到list
                    if (Objects.isNull(dto.getReportDate())){
                        dto.setHxsj(null);
                        dto.setAjzt("调查中");
                    }else{
                        dto.setHxsj(new SimpleDateFormat("yyyy年MM月dd日").format(dto.getReportDate()));
                        if (Objects.isNull(surveyRiskCaseInfo.getEntrustReportStartDate())){
                            dto.setAjzt("平台复审中");
                        }else{
                            dto.setAjzt("保司终审中");
                        }
                        if (Objects.nonNull(surveyRiskCaseInfo.getEntrustReportEndDate())){
                            dto.setAjzt("保司终审通过");
                        }
                    }
                    //延期记录
                    StringBuffer yqjlStr = new StringBuffer();
                    List<SurveyAssignOrgExtension> yqjls = surveyAssignOrgExtensions.stream().filter(k -> k.getSurveyAssignOrgId().toString().equals(dto.getId().toString())
                            && k.getExtensionState() == 2 && k.getDeleteFlag() == 0).collect(Collectors.toList());
                    yqjls = yqjls.stream().sorted(Comparator.comparing(SurveyAssignOrgExtension :: getCreateTime).reversed()).collect(Collectors.toList());
                    yqjls.forEach(e -> {
                        yqjlStr.append(new SimpleDateFormat("yyyy年MM月dd日").format(e.getCreateTime()) + ":" + (e.getExtensionReasonResult() == null ?  "" : e.getExtensionReasonResult()) + "\n");
                    });
                    dto.setYqjl(yqjlStr.toString());

                    //退回记录
                    StringBuffer thjlStr = new StringBuffer();
                    List<SurveyAssignOrgBack> thjls = surveyAssignOrgBacks.stream().filter(k -> k.getSurveyCaseOrgId().toString().equals(dto.getId().toString())
                            && k.getType().equals("BACK") && k.getDeleteFlag() == 0).collect(Collectors.toList());
                    thjls = thjls.stream().sorted(Comparator.comparing(SurveyAssignOrgBack :: getCreateTime).reversed()).collect(Collectors.toList());
                    thjls.forEach(e -> {
                        thjlStr.append(new SimpleDateFormat("yyyy年MM月dd日").format(e.getCreateTime()) + ":" + e.getContent() + "\n");
                    });
                    dto.setThjl(thjlStr.toString());


                    //案件沟通
                    StringBuffer ajgtStr = new StringBuffer();
                    List<SurveyBackReply> ajgts = surveyBackReplies.stream().filter(k -> k.getSurveyInfoId().toString().equals(dto.getSurveyInfoId().toString())).collect(Collectors.toList());
                    ajgts = ajgts.stream().sorted(Comparator.comparing(SurveyBackReply :: getCreateTime).reversed()).collect(Collectors.toList());
                    ajgts.forEach(e -> {
                        ajgtStr.append(new SimpleDateFormat("yyyy年MM月dd日").format(e.getCreateTime()) + ":" + e.getContents() + "\n");
                    });
                    dto.setAjgt(ajgtStr.toString());


                }


                if("assign-org-list".equals(menuCode)){
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    List<SurveyAssignOrgDto.InvestigatorCaseInfo> lists = new ArrayList();
                    String [] strs = dto.getInvestigatorCaseStr().split(",");
                    for (String str : strs) {

                        String [] item = str.split("_");
                        if(StringUtils.isEmpty(item[0])){
                            continue;
                        }
                        SurveyAssignOrgDto.InvestigatorCaseInfo info = dto.new InvestigatorCaseInfo();
                        info.setSurveyUserName(item[0]);

                        //分配给调查员时间
                        Date assDate = new Date();
                        //截止时间
                        Date endDate = new Date();
                        //提交时间
                        Date commitDate = new Date();

                        if(!StringUtils.isEmpty(item[1])){
                            info.setAssignDate(formatter.parse(item[1]));
                            assDate = formatter.parse(item[1]);
                        }
                        if(!StringUtils.isEmpty(item[2])){
                            info.setCreportDate(formatter.parse(item[2]));
                            commitDate = formatter.parse(item[2]);
                        }
                        if(!StringUtils.isEmpty(item[3])){
                            info.setSurveyEndTime(formatter.parse(item[3]));
                            endDate = formatter.parse(item[3]);
                        }
                        info.setSurveyStateName(item[4]);
                        if(!StringUtils.isEmpty(item[4])){
                            info.setSurveyStateName(item[4]);
                        }

                        int surveyState = 1;//案件状态 默认调查中
                        if(!StringUtils.isEmpty(item[5])){
                            surveyState = Integer.parseInt(item[5]);
                        }
                        int surAgingDay = 0;
                        if(!StringUtils.isEmpty(item[7])){
                            surAgingDay = Integer.parseInt(item[7]);
                        }

                        if(surveyState == 4){ //初审通过
//                            int days = GetWorkDay.calLeaveDays(assDate, commitDate, efficiencyAttr);
                            int days = surAgingDay;
                            days = Math.abs(days);
                            if(commitDate.compareTo(endDate) > 0){ //如果“提交时间” 超过“截止时间” 为红色
                                info.setEfficiencyState("时效"+days+"天");
                                info.setEfficiencyStateColor("#e51c23");
                            }else{
                                info.setEfficiencyState("时效"+days+"天");
                                info.setEfficiencyStateColor("#3ba9ff");
                            }
                        }else{
                            if(endDate.compareTo(new Date()) > 0){ //未超时
                                int days = GetWorkDay.calLeaveDays(new Date(),endDate,efficiencyAttr);
                                days = Math.abs(days);
                                info.setEfficiencyState("剩余"+days+"天");
                                info.setEfficiencyStateColor("#3ba9ff");//蓝色
                                if(days <= 2 && days >0 ){ //
                                    info.setEfficiencyStateColor("#ff9800");//黄色
                                }
                            }else{
                                int days = GetWorkDay.calLeaveDays(endDate,new Date(),efficiencyAttr);
                                days = Math.abs(days);
                                info.setEfficiencyState("超时"+days+"天");
                                info.setEfficiencyStateColor("#e51c23");
                            }
                        }
                        lists.add(info);
                        lists.sort(Comparator.comparing(SurveyAssignOrgDto.InvestigatorCaseInfo :: getAssignDate));
                        if (lists.size() > 1){
                            lists.get(0).setShowKey(true);
                        }
                    }
                    dto.setInvestigatorCaseInfos(lists);
                }

                if ("help-review".equals(menuCode)){
                    Boolean oprOver = false;
                    Date startDate =  dto.getReportDate() == null ? new Date() : dto.getReportDate(),endDate = null;
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startDate);
                    calendar.add(Calendar.DATE, 1);
                    Date oneDayAfter = calendar.getTime();//一天之后的时间
                    if (dto.getReviewTime() == null){//未提交
                        if (oneDayAfter.before(new Date())){//加上一天之后 在当前时间之后。 则超时
                            startDate = oneDayAfter;
                            endDate = new Date();
                            oprOver = true;
                        }else{//未超时
                            startDate = oneDayAfter;
                            endDate = new Date();
                        }
                    }else{//已提交
                        endDate = dto.getReviewTime();
                        if (oneDayAfter.before(endDate)){
                            oprOver = true;
                        }
                    }

                    Long startTime = startDate.getTime(), endTime = endDate.getTime();
                    long millons = startTime-endTime;
                    if(dto.getReviewTime() != null){//复审通过的显示实际用时时间
                        if (dto.getAgingReal() != null){
                            millons = dto.getAgingReal().longValue()*60*1000;
                        }
                    }

                    int day = Math.abs((int) ((millons)/86400000));
                    //计算小时
                    int h = Math.abs((int) (((millons)%86400000)/3600000));
                    //计算分钟
                    int m = Math.abs((int)(((millons)%86400000)%3600000)/60000);
                    //计算秒
                    int s = Math.abs((int)((((millons)%86400000)%3600000)%60000)/1000);
                    dto.setOprOver(oprOver);
                    StringBuffer oprOverStr = new StringBuffer();
                    if (dto.getReviewTime() == null){
                        if (oprOver){
                            oprOverStr.append("超时");
                        }else{
                            oprOverStr.append("剩余");
                        }
                    }else{
                        oprOverStr.append("用时");
                    }
                    oprOverStr.append(day + "天" + h + "小时" + m + "分钟");
                    dto.setOprOverTimeStr(oprOverStr.toString());
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "机构案件详情",value = "info-survey-assign-org")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        SurveyAssignOrgDto dto = surveyAssignOrgMapper.selectByPrimaryKey(id);
        dto.setSurveyRiskCaseInfoDto(surveyRiskCaseInfoMapper.selectByPrimaryKey(dto.getSurveyInfoId()));
        dto.setSurveyRiskCase(surveyRiskCaseMapper.selectByPrimaryKey(dto.getSurveyId()));
        String btnCode = apiRequest.getString("btnCode");
        String operateType = apiRequest.getString("operateType");
        String roleCode = apiRequest.getString("roleCode");
        boolean b = !StringUtils.isEmpty(operateType) && "caseRemind".equals(operateType);

        List<SurveyAssignOrgExtension> extensionList = new ArrayList<>();
        if("extension-time".equals(btnCode) || b) {
            //所有的延期申请记录
            Map map = new HashMap();
            map.put("surveyAssignOrgId", id);
            extensionList = surveyAssignOrgExtensionMapper.list(map);
            for (SurveyAssignOrgExtension surveyAssignOrgExtension : extensionList) {
                String files = surveyAssignOrgExtension.getExtensionFiles();
                if (!StringUtils.isEmpty(files)) {
                    String[] filesIds = files.split(",");
                    List<CommonFile> commonFiles = new ArrayList<>();
                    for (String filesId : filesIds) {
                        if (!StringUtils.isEmpty(filesId)){
                            CommonFile commonFile = commonFileMapper.selectByPrimaryKey(Long.parseLong(filesId));
                            commonFiles.add(commonFile);
                        }
                    }
                    surveyAssignOrgExtension.setCommonFiles(commonFiles);
                }
            }
        }

        if("extension-time".equals(btnCode) && !b){ //申请延期
            Long assignOrgExtensionId = apiRequest.getLong("assignOrgExtensionId");
            if(assignOrgExtensionId ==null){
                String type = apiRequest.getString("type");//区分“申请延期-first”，“延期申请中-second”
                SurveyAssignOrgExtension extension = new SurveyAssignOrgExtension();
                extension.setOldOrgEndTime(dto.getOrgEndTime());
                dto.setExtension(extension);
                if("second".equals(type)){
                    if(extensionList !=null && extensionList.size() >0){
                        dto.setExtension(extensionList.get(0));
                    }
                }
            }else{
                SurveyAssignOrgExtension assignOrgExtension = surveyAssignOrgExtensionMapper.selectByPrimaryKey(assignOrgExtensionId);
                //如果是最新的延期记录，会有newestInfo=true,会有“撤回”
                if(extensionList.get(0).getId().equals(assignOrgExtensionId)){
                    assignOrgExtension.setNewestInfo(true);
                }
                if(assignOrgExtension !=null){
                    String files = assignOrgExtension.getExtensionFiles();
                    if (!StringUtils.isEmpty(files)) {
                        String[] filesIds = files.split(",");
                        List<CommonFile> commonFiles = new ArrayList<>();
                        for (String filesId : filesIds) {
                            if (!StringUtils.isEmpty(filesId)){
                                CommonFile commonFile = commonFileMapper.selectByPrimaryKey(Long.parseLong(filesId));
                                commonFiles.add(commonFile);
                            }
                        }
                        assignOrgExtension.setCommonFiles(commonFiles);
                    }
                }

                if("lfManager".equals(roleCode)){
                    //判断该申请，是否需要发邮件
                    //需求：若机构申请延期的时间超过案件截止时间 且保险公司配置了发件箱地址 则弹出发送邮件的提示框 若没超过，则直接审核通过 不发邮件
                    SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(dto.getSurveyInfoId());
                    SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
                    SurveyEmailInfo surveyEmailInfo = new SurveyEmailInfo();
                    SurveyEmailInfoOrg surveyEmailInfoOrg = surveyEmailInfoOrgMapper.selectByEntrustOrgId(surveyRiskCaseInfo.getEntrustOrgId());

                    if(assignOrgExtension.getEndTime().before(assignOrgExtension.getExtensionTime()) &&  surveyEmailInfoOrg != null){
                        Boolean sendEmail = true;
                        surveyEmailInfo = surveyEmailInfoMapper.selectByPrimaryKey(surveyEmailInfoOrg.getEmailInfoId());//发件箱
                        SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByUserId(surveyRiskCase.getEntrustUserId());
                        surveyEmailInfo.setSend(true);
                        if (surveyConsigner != null) {
                            surveyEmailInfo.setToEmailAddress(surveyConsigner.getEmail());//收件箱
                            surveyEmailInfo.setMakeEmail(surveyConsigner.getMakeEmails());//抄送邮箱
                        }
                        if (surveyConsignor != null){
                            surveyEmailInfo.setMaxSize(surveyConsignor.getAttrMaxSize());
                        }

                        DateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
                        String dateString = format2.format(assignOrgExtension.getExtensionTime());
                        String emailContent =surveyRiskCase.getSurveyCaseNo() + "\n" + assignOrgExtension.getExtensionReason() +"\n"
                                +"预计"+ dateString + "回销";
                        surveyEmailInfo.setEmailContent(emailContent);

                        assignOrgExtension.setSendEmail(sendEmail);
                        assignOrgExtension.setSurveyEmailInfo(surveyEmailInfo);


                    }
                }
                dto.setExtension(assignOrgExtension);
            }
            dto.setExtensionList(extensionList);
        }

        if (b){//每日案件提醒申请延期
            Map map = null;
            SurveyAssignOrgExtension surveyAssignOrgExtension = new SurveyAssignOrgExtension();
            dto.setExtension(surveyAssignOrgExtension);
            String replyId = apiRequest.getString("replyId");
            Integer type = apiRequest.getInt("type");
            if (StringUtils.isEmpty(replyId) && type == 3){
                map = surveyAssignOrgReplyMapper.selectInvPreByOrgCaseId(id,type);
            }

            if(!StringUtils.isEmpty(replyId)){
                map = surveyAssignOrgReplyMapper.selectInvReplyInfo(Integer.valueOf(replyId),type);
                surveyAssignOrgExtension.setExtensionState(1);
            }
            if (map!=null){
                try {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (map.get("maxTime")!=null){
                        surveyAssignOrgExtension.setExtensionTime(df.parse(map.get("maxTime").toString()));
                    }
                    if (map.get("files")!=null){
                        String files = map.get("files").toString();
                        if(!StringUtils.isEmpty(files)){
                            String[] filesIds = files.split(",");
                            List<CommonFile> commonFiles = new ArrayList<>();
                            for (String filesId : filesIds) {
                                if (!StringUtils.isEmpty(filesId)){
                                    CommonFile commonFile = commonFileMapper.selectByPrimaryKey(Long.parseLong(filesId));
                                    commonFiles.add(commonFile);
                                }
                            }
                            surveyAssignOrgExtension.setCommonFiles(commonFiles);
                            surveyAssignOrgExtension.setExtensionFiles(files);
                        }
                    }
                    if (map.get("replyContent")!=null){
                        surveyAssignOrgExtension.setExtensionReason(map.get("replyContent").toString());
                    }
                    dto.setExtension(surveyAssignOrgExtension);
                    dto.setExtensionList(extensionList);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }


    @ApiMethod(needLogin = false,descript = "机构案件处理",value = "operate-survey-assign-org")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        String btnCode = apiRequest.getString("btnCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        if ("aging-rate".equals(btnCode)){
            id = apiRequest.getLong("surveyAssignOrgId");
        }
        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(id);
        String opinion = apiRequest.getString("opinion");
        String orgOprOpinion = apiRequest.getString("orgOprOpinion");
        if ("org1".equals(btnCode)){//接收
            surveyAssignOrg.setOrgSurveyState(1);
            surveyAssignOrg.setOrgSurveyStateName("调查中");
            surveyAssignOrg.setOrgOpinion(null);
        }else if ("org2".equals(btnCode)){//拒绝
            surveyAssignOrg.setOrgSurveyState(3);
            surveyAssignOrg.setOrgSurveyStateName("已拒绝");
            surveyAssignOrg.setOrgOpinion(opinion);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);// 先执行修改操作，不然出现 执行更改另一个机构为主机构出现BUG
            if (surveyAssignOrg.getOrgPrimaryType() == 1) {//如果是主机构拒绝 更改另一个机构为主机构
                surveyAssignOrg.setOrgPrimaryType(2);//当前机构设置为辅助机构
                surveyAssignOrgMapper.updateOrgPrimayTypeBySurveyInfoId(surveyAssignOrg.getSurveyInfoId());
            }

            //主机构拒绝。  更改另一个机构为主机构 同时  验证 主机构的所有人是否都已经提交审核。  如果全部提交审核。 则需要更改一个人填写报告结论。

            //辅助机构拒绝 验证  主机构所有人是否都已经提交审核。
            SurveyAssignOrg primaryOrg = surveyAssignOrgMapper.selectPrimaryOrg(surveyAssignOrg.getSurveyInfoId());
            if (primaryOrg != null) {
                ApiRequest old = new ApiRequest();
                old.clear();
                old.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
                old.put("org",primaryOrg.getSurveyOrgId());
                List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(old);
                int count = 0;
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getSurveyState() == 4 || aCase.getSurveyState() == 6 || aCase.getSurveyState() == 2){
                        count ++ ;
                    }
                }
                if (count == cases.size() && cases.size() > 0){
                    //
                    primaryOrg.setOrgSurveyState(1);
                    surveyAssignOrgMapper.updateByPrimaryKey(primaryOrg);

                    SurveyInvestigatorCaseDto caseDto = cases.get(0);
                    caseDto.setSurveyState(1);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(caseDto);

                    //发送消息通知
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                    SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(caseDto.getSurveyId());
                    SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(caseDto.getSurveyInfoId());
                    String url = "/survey/case/sic/info?id=" + caseDto.getId() + "&menuCode=dcy-list";
                    String content = "案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime());
                    backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),caseDto.getSurveyUserId(),caseDto.getSurveyUserName(),4,"填写报告结论通知",
                            content,url);
                }
            }

        }else if("XXXXXXX".equals(btnCode)){//删除机构案件
            surveyAssignOrg.setDeleteFlag(1);
            surveyAssignOrg.setUpdateBy(userInfo.getUserName());
            surveyAssignOrg.setUpdateTime(new Date());

            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("roleId",58L);
            paramMap.put("orgId",surveyAssignOrg.getSurveyOrgId());
            List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
            Map<String,Object> msgMap =  new HashMap<String,Object>();
            msgMap.put("title","案件取消");
            msgMap.put("content","机构有案件被取消，请注意！");
            msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "操作人员：" + userInfo.getUserName());
            backendWechatApi.send(toUsers,msgMap);
        }else if ("118".equals(btnCode)){
            String orgOpinion = apiRequest.getString("orgOpinion");
//            SurveyAssignOrg assignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrg.getId());
            surveyAssignOrg.setDeleteFlag(1);//改为已删除
            surveyAssignOrg.setUpdateTime(new Date());
            surveyAssignOrg.setUpdateBy(userInfo.getUserName());
            surveyAssignOrg.setOrgOpinion(orgOpinion);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
            //同时删除调查员及方向
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
            paramMap.put("surveyAssorgCaseId", surveyAssignOrg.getId());
            List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(paramMap);
            for (SurveyInvestigatorCase aCase : cases) {
                aCase.setDeleteFlag(1);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
                Map<String,Object> map = new HashMap<>();
                map.put("surveyInvestigatorCaseId",aCase.getId());
                List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
                for (SurveyCaseDirection direction : directions) {
                    direction.setDeleteFlag(1);
                    direction.setUpdateTime(new Date());
                    direction.setUpdateBy(userInfo.getUserName());
                    surveyCaseDirectionMapper.updateByPrimaryKey(direction);
                }

                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(aCase.getSurveyId());
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","案件取消");
                msgMap.put("content","你有任务被取消，请注意！");
                msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "操作人员：" + userInfo.getUserName());
                backendWechatApi.send(aCase.getSurveyUserId(),msgMap);
            }

            Boolean last = true;//是否是最后一个机构删除  ture 是   false 否
            Map<String,Object> map =  new HashMap<String,Object>();
            map.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
            map.put("surveyOrgId", surveyAssignOrg.getSurveyOrgId());
            List<SurveyAssignOrg> orgs = surveyAssignOrgMapper.selectListBySurveyInfoIdAndNotOrgId(map);
            if (orgs.size() > 0) {
                for (SurveyAssignOrg org : orgs) {
                    if (org.getOrgSurveyState() != 4) {
                        last = false;
                    }
                }
            }
            if (last){//最后一个提交的机构填写报告结论
                paramMap =  new HashMap<String,Object>();
                paramMap.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
                paramMap.put("surveyAssorgCaseId", surveyAssignOrg.getId());
                cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(paramMap);
                if (cases.size() == 0){
                    paramMap =  new HashMap<String,Object>();
                    paramMap.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
                    cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(paramMap);
                }
                if (cases.size() > 0) {
                    SurveyAssignOrg tempCurOrg = surveyAssignOrgMapper.selectByPrimaryKey(cases.get(0).getSurveyAssorgCaseId());
                    cases.get(0).setSurveyState(1);
                    cases.get(0).setSurveyStateName("调查中");//填写报告结论
                    cases.get(0).setCreportDate(null);
                    SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(cases.get(0).getSurveyInfoId());
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
                    //调查员时效
                    double s = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(cases.get(0).getSurveyInfoId(), cases.get(0).getSurveyAssorgCaseId(), cases.get(0).getId());
                    cases.get(0).setAgingDay(AgingDayUtil.surveyAgingDay(cases.get(0),surveyConsignor.getEfficiencyAttr(),s));
                    cases.get(0).setAgingReal(s);
                    cases.get(0).setAgingOver( cases.get(0).getAgingReal() - cases.get(0).getAgingCheck()>0?cases.get(0).getAgingReal() - cases.get(0).getAgingCheck():0);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(cases.get(0));

                    SurveyUserPrescriptionFlow surveyUserPrescriptionFlow = new SurveyUserPrescriptionFlow();
                    surveyUserPrescriptionFlow.setSurveyInfoId(cases.get(0).getSurveyInfoId());
                    surveyUserPrescriptionFlow.setSurveyAssignOrgId(cases.get(0).getSurveyAssorgCaseId());
                    surveyUserPrescriptionFlow.setSurveyInvestigatorCaseId(cases.get(0).getId());
                    surveyUserPrescriptionFlow.setOperateType(2);
                    surveyUserPrescriptionFlow.setStartTime(new Date());
                    surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);

                    tempCurOrg.setOrgSurveyState(1);
                    tempCurOrg.setOrgSurveyStateName("调查中");
                    tempCurOrg.setReportDate(null);
                    //机构时效
                    double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(tempCurOrg.getSurveyInfoId(), tempCurOrg.getId());
                    tempCurOrg.setAgingReal(v);
                    tempCurOrg.setAgingOver(tempCurOrg.getAgingReal()-tempCurOrg.getAgingCheck()>0?tempCurOrg.getAgingReal()-tempCurOrg.getAgingCheck():0);
                    tempCurOrg.setAgingDay(AgingDayUtil.orgAgingDay(tempCurOrg,surveyConsignor.getEfficiencyAttr(),v));
                    surveyAssignOrgMapper.updateByPrimaryKey(tempCurOrg);

                    SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(cases.get(0).getSurveyId());
                    //发送消息  填写报告结论 或 机构小结
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                    //
                    Map<String,Object> msgMap =  new HashMap<String,Object>();
                    msgMap.put("title","填写报告结论");
                    msgMap.put("content","你有案件填写报告结论，请尽快填写并提交审核！");
                    msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "操作人员：" + userInfo.getUserName() + "\n" + "调查截止日期：" + simpleDateFormat.format(cases.get(0).getSurveyEndTime()));
                    backendWechatApi.send(cases.get(0).getSurveyUserId(),msgMap);

                    SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = new SurveyOrgPrescriptionFlow();
                    surveyOrgPrescriptionFlow.setSurveyInfoId(tempCurOrg.getSurveyInfoId());
                    surveyOrgPrescriptionFlow.setSurveyAssignOrgId(tempCurOrg.getId());
                    surveyOrgPrescriptionFlow.setOperateType(3);
                    surveyOrgPrescriptionFlow.setStartTime(new Date());
                    surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
                    surveyOrgPrescriptionFlow.setOperateType(2);
                    surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
                }
            }

            //删除任务类型
            map =  new HashMap<String,Object>();
            map.put("surveyAssignOrgId",surveyAssignOrg.getId());
            List<SurveyAssignOrgType> taskTypes = surveyAssignOrgTypeMapper.list(map);
            for (SurveyAssignOrgType taskType : taskTypes) {
                surveyAssignOrgTypeMapper.deleteByPrimaryKey(taskType.getId());
            }

            //查询案件类型：1、单点；2、单点+单点；3、全案；4、全案+单点；5、全案+全案
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
            surveyRiskCaseInfo.setCaseState(surveyRiskCaseInfoApi.findCaseState(surveyRiskCaseInfo));
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            //增加进度 删除机构
            String progressDesc ="机构：" +surveyAssignOrg.getSurveyOrgName()+"\n"+
                    "删除原因：" + orgOpinion;
            backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(),surveyAssignOrg.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"删除分派机构",progressDesc);

            paramMap =  new HashMap<String,Object>();
            paramMap.put("roleId",58L);
            paramMap.put("orgId",surveyAssignOrg.getSurveyOrgId());
            List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
            Map<String,Object> msgMap =  new HashMap<String,Object>();
            msgMap.put("title","案件取消");
            msgMap.put("content","机构有案件被取消，请注意！");
            msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "操作人员：" + userInfo.getUserName());
            backendWechatApi.send(toUsers,msgMap);

        }
//        else if ("org3".equals(btnCode) || "118".equals(btnCode) || "org5".equals(btnCode)){// 初审通过
        else if ("org3".equals(btnCode) ||  "org5".equals(btnCode)){
            Map<String,Long> map =  new HashMap<String,Long>();
            map.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
            List<SurveyAssignOrgDto> surveyAssignOrgs = surveyAssignOrgMapper.list(map);

            int count = 0;
            if ("org3".equals(btnCode) || "org5".equals(btnCode)){
                for (SurveyAssignOrgDto assignOrg : surveyAssignOrgs) {
                    if (assignOrg.getOrgSurveyState() != 3 && assignOrg.getOrgSurveyState() != 4) {//不是已拒绝  并且 不是初审通过的  count + 1 ,count 等于一说明是最后一个初审通过的 转风控
                        count ++ ;
                    }
                }
            }

            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());

            //2019年11月13日  10点53分   增加逻辑。 如果案源机构是互助案件    辅助机构的最后一个人初审通过，则不需要填写报告结论 直接提交。
            Boolean oprZhuan = false;
            if (count == 1 && surveyRiskCaseInfo.getSourceSupportType() == 3){
                oprZhuan = true;
            }
            String beforeValue = surveyAssignOrg.getOrgEndTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(surveyAssignOrg.getOrgEndTime());

            int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            if(surveyConsignor != null){
                efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
            }
            //根据方向区域调整机构截止日期 2020年5月12日 增加需求
            Date areaEndTime = surveyAssignOrg.getOrgEndTime();
            int areaType = surveyCaseDirectionMapper.selectAreaTypeBySurveyAssOrgId(surveyAssignOrg.getId());
            //2021/1/5 汪鑫确认需求 （取时效最大 减 一天）
            //根据 调查方方向区域+机构的业务类型 查找委托方时效天数 -1 天。 如果是0.则不更新机构的截止日期
            Long days = 0L;
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyAssorgCaseId",surveyAssignOrg.getId());
            if (surveyConsignor.getId().intValue() == 94){
                paramMap.put("subServiceId",surveyRiskCaseInfo.getSubServiceId());//众安的时候存在subServiceId
            }else{
                paramMap.put("serviceId",surveyAssignOrg.getServicesId());
            }
            days = surveyConsignorEfficiencyModelInfoMapper.selectSurveyDaysByMap(paramMap);
            if (days == null){
                days = 0L;
            }
            days = days - 1;
            if (days >= 0){
                areaEndTime = GetWorkDay.calLeaveEndDate(surveyAssignOrg.getCreateTime(),null,days.intValue(),efficiencyAttr);
            }
//            int days = GetWorkDay.days(areaType, surveyAssignOrg.getServicesId().intValue());
//            Date areaEndTime = GetWorkDay.calLeaveEndDate(surveyAssignOrg.getCreateTime(),null,days,1);
            surveyAssignOrg.setUtterEndTime(areaEndTime);
            surveyAssignOrg.setOrgEndTime(areaEndTime);
            //2020年9月27日 此时判断延期的有效性。如果有效。则机构的截止日期等于延期截止日期。如果无效则机构的截止日期等于省市区匹配的截止日期。  同时获取机构的最大截止日期+1天 若大于案件截止日期。则修改案件截止日期。
            paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyAssignOrgId",surveyAssignOrg.getId());
            paramMap.put("extensionState",2);
            paramMap.put("order",1);
            Boolean extensionFlag = false;
            List<SurveyAssignOrgExtension> extensions = surveyAssignOrgExtensionMapper.list(paramMap);
            if (extensions.size() > 0){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date compareTime = simpleDateFormat.parse(simpleDateFormat.format(areaEndTime));
                    for (SurveyAssignOrgExtension extension : extensions) {
                        Date appTime = simpleDateFormat.parse(simpleDateFormat.format(extension.getCreateTime()));
                        if (compareTime.compareTo(appTime) >= 0){//如果匹配的区域日期 大于申请日期 则有效。
                            surveyAssignOrg.setOrgEndTime(extension.getExtensionTime());
                            extensionFlag = true;
                        }
                        else{//延期无效 则直接跳出
                            break;
                        }
                        compareTime = extension.getExtensionTime();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                surveyAssignOrg.setOrgEndTime(areaEndTime);
            }
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

            //增加机构截止日期修改记录
            SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
            record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
            record.setUpdAfterValue(surveyAssignOrg.getOrgEndTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(surveyAssignOrg.getOrgEndTime()));
            if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                record.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                record.setUpdAttr("survey_assign_org_org_end_time" + surveyAssignOrg.getId());
                record.setUpdTime(new Date());
                record.setUpdRemark(extensionFlag ? "关联变更-延期有效日期" : "关联变更-区域匹配日期");
                record.setUpdUserName(userInfo.getUserName());
                surveyAttrUpdRecordMapper.insert(record);
            }

            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
            // 获取机构的最大截止日期。
            //仅当最后一个机构提交的时候 ，走一下逻辑  2021年4月22日
            if (oprZhuan){
                SurveyAssignOrgDto maxOrgEndTimeOrg = surveyAssignOrgMapper.selectMaxOrgEndTimeBySurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                if (maxOrgEndTimeOrg.getOrgEndTime().compareTo(surveyRiskCaseInfo.getEndTime()) >= 0) {
                    Date caseEndTime = GetWorkDay.calLeaveEndDate(maxOrgEndTimeOrg.getOrgEndTime(), null, 1, 1);//机构截止日期+1天等于案件截止日期。
                    beforeValue = surveyRiskCaseInfo.getEndTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(surveyRiskCaseInfo.getEndTime());
                    surveyRiskCaseInfo.setEndTime(caseEndTime);
                    //
                    record = new SurveyAttrUpdRecord();
                    record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                    record.setUpdAfterValue(surveyRiskCaseInfo.getEndTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(surveyRiskCaseInfo.getEndTime()));
                    if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                        record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                        record.setUpdAttr("survey_risk_case_info_end_time");
                        record.setUpdTime(new Date());
                        record.setUpdRemark("机构初审关联变更");
                        record.setUpdUserName(userInfo.getUserName());
                        surveyAttrUpdRecordMapper.insert(record);
                    }
                }
            }


            List<SurveyInvestigatorCase> surveyInvestigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyAssorgCaseId(surveyAssignOrg.getId());
            for (SurveyInvestigatorCase surveyInvestigatorCase : surveyInvestigatorCases) {
                if (extensionFlag){// 如果申请了延期。则 联动改机构的调查员截止日期。
                    surveyInvestigatorCase.setSurveyEndTime(surveyAssignOrg.getOrgEndTime());
                }

                //重新计算调查员时效。以及调查员超期考核绩效
                surveyInvestigatorCase.setAgingCheck((double)GetWorkDay.calLeaveDays(surveyInvestigatorCase.getAssignDate(),surveyInvestigatorCase.getSurveyEndTime(),efficiencyAttr));
                double v = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(surveyInvestigatorCase.getSurveyInfoId(), surveyAssignOrg.getId(), surveyInvestigatorCase.getId());
                surveyInvestigatorCase.setAgingReal(Math.abs(v));
                surveyInvestigatorCase.setAgingOver(surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck()>0?surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck():0);
                Double rate = AgingDayUtil.surveyAgingRate(surveyInvestigatorCase,surveyAssignOrg,surveyFranchisee,surveyConsignor);
                surveyInvestigatorCase.setOverdueAgingRate(rate);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
            }


            if("org3".equals(btnCode) || "org5".equals(btnCode)){

                SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = surveyOrgPrescriptionFlowMapper.selectByAssOrgIdLimitOne(surveyAssignOrg.getId());
                if (surveyOrgPrescriptionFlow != null){
                    Date endTime2 = new Date();
                    surveyOrgPrescriptionFlow.setOperateType(2);
                    surveyOrgPrescriptionFlow.setEndTime(endTime2);
                    surveyOrgPrescriptionFlow.setDays(Math.abs((double)GetWorkDay.calLeaveDays(surveyOrgPrescriptionFlow.getStartTime(),endTime2,efficiencyAttr)));
                    surveyOrgPrescriptionFlowMapper.updateByPrimaryKeySelective(surveyOrgPrescriptionFlow);
                }
                surveyAssignOrg.setOrgSurveyState(4);//初审通过
                surveyAssignOrg.setOrgSurveyStateName("初审通过");
                surveyAssignOrg.setOrgOpinion(null);
                surveyAssignOrg.setReportDate(new Date());//提交初审通过时间
                double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
                surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
                surveyAssignOrg.setAgingCheck((double)GetWorkDay.calLeaveDays(surveyAssignOrg.getCreateTime(),surveyAssignOrg.getOrgEndTime(),efficiencyAttr));
                surveyAssignOrg.setAgingReal(Math.abs(v));
                surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
                //计算超期考核绩效

                Double rate = AgingDayUtil.orgAgingRate(surveyAssignOrg,surveyFranchisee,surveyConsignor);
                surveyAssignOrg.setOverdueAgingRate(rate);
                //调查员发送 审核通过通知
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyId());
                apiRequest.clear();
                apiRequest.put("surveyAssorgCaseId",surveyAssignOrg.getId());
                List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(apiRequest);
                for (SurveyInvestigatorCaseDto aCase : cases) {
                    String url = "/survey/case/sic/info?id=" + aCase.getId() + "&menuCode=dcy-list";
                    backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),aCase.getSurveyUserId(),aCase.getSurveyUserName(),4,"审核通过通知",
                            "你有任务审核通过，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()),url);
                }
            }

            //获取上一次机构的开户行信息
            //默认显示 上一条的渠道费银行信息
            paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyUserId",userInfo.getUserId());
            SurveyChannelCostNew upRecord = surveyChannelCostNewMapper.selectChannelCostNewUp(paramMap);
            if (upRecord == null) {
                upRecord = new SurveyChannelCostNew();
                SurveyChannelCost upItem = surveyChannelCostMapper.selectUpItem(paramMap);
                if (upItem != null){
                    upRecord.setPayeeUserName(upItem.getPayeeUserName());
                    upRecord.setBankNo(upItem.getBankNo());
                    upRecord.setBankBranch(upItem.getBankBranch());
                    upRecord.setBankDeposit(upItem.getBankDeposit());
                }
            }
            upRecord.setSurveyOrgId(surveyAssignOrg.getSurveyOrgId());
            upRecord.setSurveyOrgName(surveyAssignOrg.getSurveyOrgName());
            upRecord.setSurveyUserId(userInfo.getUserId());
            upRecord.setOperationUserId(userInfo.getUserId());
            upRecord.setOperationUserName(userInfo.getUserName());
            upRecord.setSurveyUserName(userInfo.getUserName());
//            upRecord.setPayeeUserName(userInfo.getUserName());
            //同步渠道费用(未申请付款的渠道费用。小于等于限额的。直接到付款审核通过状态。  大于限额的到付费待审核状态)
            upRecord.setSurveyAssorgCaseId(surveyAssignOrg.getId());
            surveyChannelCostNewMapper.generate(upRecord);//只生成未生成过渠道费的方向。


            //同步分值
            surveyRiskCaseInfoApi.score(surveyAssignOrg.getSurveyInfoId());

            //
            backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(),surveyAssignOrg.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"机构已提交（"+surveyAssignOrg.getSurveyOrgName()+"）","");

            //如果是最后一个审核通过的  则更改 子案件状态 至  乐凡终审(平台复审中)
            if ((count == 1) || oprZhuan){
                zhuanLefan(surveyRiskCaseInfo,apiRequest,userInfo,orgOprOpinion);
                if(false){//2021年7月29日 所有保司的案子都没有阳性奖励
                    //计算阳性奖励-  2021年7月1日 且不为众安的时候
  //                if(surveyConsignor.getOrgAttr() == 1 && surveyRiskCaseInfo.getEntrustOrgId().intValue() != 94){
                    Double money=0D;
                    if (surveyRiskCaseInfo.getServicesId()==11 || surveyRiskCaseInfo.getServicesId()==12) {
                        money=100D;
                    }else if(surveyRiskCaseInfo.getServicesId()==13){
                        money=400D;
                    }
                    Map findMapTwo=new HashMap();
                    findMapTwo.put("surveyInfoId",surveyRiskCaseInfo.getId());
                    findMapTwo.put("type",1);
                    findMapTwo.put("isSun",1);
                    List<SurveyInvestigatorCase> surveyInvestigatorCaseShree=surveyInvestigatorCaseMapper.selectInformation(findMapTwo);
                    Integer counts=surveyInvestigatorCaseShree.size();
                    DecimalFormat df = new DecimalFormat("#.00");
                    for (SurveyInvestigatorCase surveyInvestigatorCaseList:surveyInvestigatorCaseShree) {
                        surveyInvestigatorCaseList.setSunMoney(Double.parseDouble(df.format(money/counts)));
                        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCaseList);
                    }
                }
            }

        }else if ("org4".equals(btnCode) || "org6".equals(btnCode))
        {//复核  审核退回
            surveyAssignOrg.setOrgSurveyState(1);
            surveyAssignOrg.setOrgSurveyStateName("调查中");
            surveyAssignOrg.setReportDate(null);
            SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
            surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
            surveyAssignOrg.setAgingReal(Math.abs(v));
            surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
            //退回改机构的所有调查员案件
            ApiRequest old = new ApiRequest();
            old.clear();
//            old.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
//            old.put("org",surveyAssignOrg.getSurveyOrgId());
            old.put("surveyAssorgCaseId",surveyAssignOrg.getId());
            List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(old);
            for (SurveyInvestigatorCaseDto aCase : cases) {
                if (aCase.getIsDirectionSuccess() == 1 && aCase.getSurveyState() != 2){
                    aCase.setIsDirectionSuccess(0);
                    aCase.setSurveyState(1);
                    aCase.setSurveyStateName("调查中");
                    aCase.setReturnState(1);
                    aCase.setSurveyRemark("审核人员："+userInfo.getUserName()+"  退回原因："+opinion);
                    aCase.setCreportDate(null);
                    //调查员时效
                    double s = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(cases.get(0).getSurveyInfoId(), cases.get(0).getSurveyAssorgCaseId(), cases.get(0).getId());
                    aCase.setAgingDay(AgingDayUtil.surveyAgingDay(aCase,surveyConsignor.getEfficiencyAttr(),s));
                    aCase.setAgingReal(s);
                    aCase.setAgingOver(aCase.getAgingReal()-aCase.getAgingCheck()>0?aCase.getAgingReal()-aCase.getAgingCheck():0);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);

                    SurveyUserPrescriptionFlow surveyUserPrescriptionFlow = new SurveyUserPrescriptionFlow();
                    surveyUserPrescriptionFlow.setSurveyInfoId(aCase.getSurveyInfoId());
                    surveyUserPrescriptionFlow.setSurveyAssignOrgId(aCase.getSurveyAssorgCaseId());
                    surveyUserPrescriptionFlow.setSurveyInvestigatorCaseId(aCase.getId());
                    Date startTime = new Date();
                    surveyUserPrescriptionFlow.setStartTime(startTime);
                    surveyUserPrescriptionFlow.setOperateType(3);
                    surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);
                    surveyUserPrescriptionFlow.setOperateType(2);
                    surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);
                }
            }
            surveyRiskCaseInfo.setSurveyState(12);
            surveyRiskCaseInfo.setSurveyStateName("调查中");
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            //调查员发送审核退回通知
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyId());
            for (SurveyInvestigatorCaseDto aCase : cases) {
                if (aCase.getSurveyEndTime() == null){
                    aCase.setSurveyEndTime(new Date());
                }
                String content = "案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(aCase.getSurveyEndTime());
                String url = "/survey/case/sic/info?id=" + aCase.getId() + "&menuCode=dcy-list";
                backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),aCase.getSurveyUserId(),aCase.getSurveyUserName(),4,"审核退回通知",
                        content,url);

                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","初审退回");
                msgMap.put("content","你有任务初审退回，请尽快进行处理！");
                msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "初审人员：" + userInfo.getUserName() + "\n"
                        + "退回原因：" + opinion);
                backendWechatApi.send(aCase.getSurveyUserId(),msgMap);
            }


            //添加进度
            backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(),surveyAssignOrg.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"机构初审退回",opinion);


            //主调查员 可以重新提交审核
//            SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInvestigatorCaseId());
//            if (surveyInvestigatorCase != null) {
//                surveyInvestigatorCase.setSurveyState(4);
//                surveyInvestigatorCase.setSurveyStateName("复核退回");
//                surveyInvestigatorCase.setSurveyRemark(opinion);
//                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
//
//                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
//                surveyRiskCaseInfo.setSurveyStateName("调查中");
//                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
//            }
        }else if ("123".equals(btnCode))
        {//撤回 到初审中
            surveyAssignOrg.setOrgSurveyState(2);//初审中
            surveyAssignOrg.setOrgSurveyStateName("初审中");
            surveyAssignOrg.setReportDate(null);
            SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
            surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
            surveyAssignOrg.setAgingReal(Math.abs(v));
            surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
            surveyRiskCaseInfo.setSurveyState(12);
            surveyRiskCaseInfo.setSurveyStateName("调查中");
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(),surveyAssignOrg.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"平台撤回","案件从初审中撤回到调查中");

            SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = new SurveyOrgPrescriptionFlow();
            surveyOrgPrescriptionFlow.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyOrgPrescriptionFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
            surveyOrgPrescriptionFlow.setStartTime(new Date());
            surveyOrgPrescriptionFlow.setOperateType(3);
            surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
            surveyOrgPrescriptionFlow.setOperateType(2);
            surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);


            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("roleId",58L);
            paramMap.put("orgId",surveyAssignOrg.getSurveyOrgId());
            List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            Map<String,Object> msgMap =  new HashMap<String,Object>();
            msgMap.put("title","机构初审");
            msgMap.put("content","你有平台撤回任务，请尽快进行初审！");
            msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()) + "\n"
                    + "撤回人：" + userInfo.getUserName());
            backendWechatApi.send(toUsers,msgMap);
        }else if("extension-time".equals(btnCode)){//机构提交延期审核
            String roleCode = apiRequest.getString("roleCode");
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            if("orgManager".equals(roleCode)){
                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());

                SurveyAssignOrgExtension surveyAssignOrgExtension =  new SurveyAssignOrgExtension();
                surveyAssignOrgExtension.setSurveyId(surveyAssignOrg.getSurveyId());
                surveyAssignOrgExtension.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                surveyAssignOrgExtension.setSurveyAssignOrgId(surveyAssignOrg.getId());
                surveyAssignOrgExtension.setExtensionState(1);
                surveyAssignOrgExtension.setExtensionReason(apiRequest.getString("extensionReason"));
                Date extensionTime = DateUtils.parseDate(apiRequest.getString("extensionTime"), "yyyy-MM-dd HH:mm:ss");
                surveyAssignOrgExtension.setExtensionTime(extensionTime);

                //认证材料
                StringBuffer fileIds  =  new StringBuffer();
                String imgs = apiRequest.getString("pathBackReason");
                if (imgs != null) {
                    String[] urls = imgs.split(",");
                    for (String url : urls) {
                        CommonFile commonFile = new CommonFile();
                        //保存认证材料
                        commonFile.setFilePath(url);
                        int firstName = url.lastIndexOf("/") + 1 ;
                        int lastName = url.lastIndexOf(".");
                        String name = url.substring(firstName,lastName);
                        commonFile.setFileName(name);
                        commonFile.setCreateTime(new Date());
                        commonFileMapper.insert(commonFile);
                        fileIds.append(commonFile.getId() +",");
                    }
                    surveyAssignOrgExtension.setExtensionFiles(fileIds.toString());
                }
                surveyAssignOrgExtension.setCreateBy(userInfo.getUserName());
                surveyAssignOrgExtension.setCreateTime(new Date());
                surveyAssignOrgExtension.setDeleteFlag(0);
                surveyAssignOrgExtension.setOldOrgEndTime(surveyAssignOrg.getOrgEndTime());
                surveyAssignOrgExtension.setEndTime(surveyRiskCaseInfo.getEndTime());
                surveyAssignOrgExtension.setExtensionReasonResult(surveyAssignOrgExtension.getExtensionReason());
                surveyAssignOrgExtensionMapper.insert(surveyAssignOrgExtension);

                //更新路径
                String fileIdStr = surveyAssignOrgExtension.getExtensionFiles();
                if (fileIdStr != null) {
                    String[] fileIdS = fileIdStr.split(",");
                    for (String s : fileIdS) {
                        CommonFile commonFile = commonFileMapper.selectByPrimaryKey(Long.parseLong(s));
                        String filePath = commonFile.getFilePath();
                        int firstName = filePath.lastIndexOf("/") - 13 ;
                        int lastName = filePath.lastIndexOf("/");
                        String path = filePath.substring(firstName,lastName);
                        filePath = filePath.replaceAll(path,surveyAssignOrgExtension.getId().toString());
                        commonFile.setFilePath(filePath);
                        //名称
                        int firstName1 = filePath.lastIndexOf("/") + 1 ;
                        int lastName1 = filePath.length();
                        String name = filePath.substring(firstName1,lastName1);
                        filePath = filePath.replaceAll(name,"rename_"+name);
                        commonFile.setFilePath(filePath);

                        commonFileMapper.updateByPrimaryKey(commonFile);
                    }
                }

                surveyAssignOrg.setExtensionState(1);
                surveyAssignOrg.setExtensionReason(apiRequest.getString("extensionReason"));
                surveyAssignOrg.setExtensionTime(extensionTime);

                //增加进度 提交延期申请
                String progressDesc ="申请机构：" +surveyAssignOrg.getSurveyOrgName()+"\n"+
                        "原机构截止时间：" + sdf.format(surveyAssignOrg.getOrgEndTime()) + "\n"+
                        "延期时间至：" + sdf.format(extensionTime) + "\n"+
                        "延期原因：" + apiRequest.getString("extensionReason");
                backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(),surveyAssignOrg.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"提交延期申请",progressDesc);


                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyId());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","延期审核");
                msgMap.put("content","你有机构申请延期，请尽快处理！");
                msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "申请机构：" + surveyAssignOrg.getSurveyOrgName() + "\n"
                        + "原机构截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()) + "\n" + "申请截止日期：" + simpleDateFormat.format(extensionTime));

                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCase.getEntrustOrgId());
                Long toUserId = surveyConsignor.getOrgAttr() == 2 ? surveyAssignOrg.getReviewUserId() : surveyRiskCaseInfo.getBelongUserId();
                if (toUserId != null){
                    backendWechatApi.send(toUserId,msgMap);
                }
                String operateType = apiRequest.getString("operateType");
                if (!StringUtils.isEmpty(operateType) && "caseRemind".equals(operateType)){
                    Map map = surveyAssignOrgReplyMapper.selectInvPreByOrgCaseId(surveyAssignOrg.getId(),3);
                    if (map !=null){
                        if (map.get("files")!=null) {
                            String files = map.get("files").toString();
                            fileIds.append(files);
                        }
                    }
                    SurveyAssignOrgReply surveyAssignOrgReply = new SurveyAssignOrgReply();
                    surveyAssignOrgReply.setSurveyAssorgCaseId(id);
                    surveyAssignOrgReply.setReplyFiles(fileIds.toString());
                    surveyAssignOrgReply.setReplyContent(apiRequest.getString("extensionReason"));
                    surveyAssignOrgReply.setReplyTime(new Date());
                    surveyAssignOrgReply.setReplyType(3);
                    surveyAssignOrgReplyMapper.insert(surveyAssignOrgReply);
                    if (StringUtils.isEmpty(fileIds.toString())){
                        surveyAssignOrgExtension.setExtensionFiles(null);
                    }else{
                        surveyAssignOrgExtension.setExtensionFiles(fileIds.toString());
                    }
                    surveyAssignOrgExtensionMapper.updateByPrimaryKeySelective(surveyAssignOrgExtension);
                    surveyAssignOrg.setFollowTime(new Date());
                    if (StringUtils.isEmpty(apiRequest.getString("extensionReason"))){
                        surveyAssignOrg.setFollowInformation("申请延期：");
                    }else {
                        surveyAssignOrg.setFollowInformation("申请延期："+apiRequest.getString("extensionReason"));
                    }
                    surveyAssignOrg.setFollowUserId(userInfo.getUserId());
                    surveyAssignOrg.setFollowUserName(userInfo.getUserName());
                }

                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyAssignOrgExtension);
            }else if("lfManager".equals(roleCode)){
                SurveyAssignOrgExtension surveyAssignOrgExtension = surveyAssignOrgExtensionMapper.selectByPrimaryKey(apiRequest.getLong("extensionId"));

                surveyAssignOrgExtension.setExtensionState(2);//通过
                Date extensionTime = DateUtils.parseDate(apiRequest.getString("extensionTime"), "yyyy-MM-dd HH:mm:ss");
                surveyAssignOrgExtension.setExtensionReason(apiRequest.getString("extensionReason"));
                surveyAssignOrgExtension.setExtensionTime(extensionTime);
                surveyAssignOrgExtension.setUpdateBy(userInfo.getUserName());
                surveyAssignOrgExtension.setUpdateTime(new Date());
                surveyAssignOrgExtension.setExtensionReasonResult(apiRequest.getString("extensionReasonResult"));
                surveyAssignOrgExtensionMapper.updateByPrimaryKey(surveyAssignOrgExtension);

                Date oldOrgEndTime = surveyAssignOrg.getOrgEndTime();
                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());

                //2020年9月25日  增加需求  只要延期审核通过。延期都有效
                Boolean extension = true;
                if (true) {//  则 延期有效
                    surveyAssignOrg.setOrgEndTime(extensionTime);

                    //因延期申请，需更新机构时效
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
                    int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
                    if(surveyConsignor!=null){
                        efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
                    }

                    int days = GetWorkDay.calLeaveDays(surveyAssignOrg.getCreateTime(), surveyAssignOrg.getOrgEndTime(),efficiencyAttr);
                    surveyAssignOrg.setAgingCheck(new Double(days));

                    double v2 = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
                    surveyAssignOrg.setAgingCheck((double)GetWorkDay.calLeaveDays(surveyAssignOrg.getCreateTime(),surveyAssignOrg.getOrgEndTime(),efficiencyAttr));
                    surveyAssignOrg.setAgingReal(Math.abs(v2));
                    surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);

                    //联动改机构的调查员截止日期。
                    List<SurveyInvestigatorCase> surveyInvestigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyAssorgCaseId(surveyAssignOrg.getId());
                    for (SurveyInvestigatorCase surveyInvestigatorCase : surveyInvestigatorCases) {
                        surveyInvestigatorCase.setSurveyEndTime(surveyAssignOrg.getOrgEndTime());
                        surveyInvestigatorCase.setAgingCheck((double)GetWorkDay.calLeaveDays(surveyInvestigatorCase.getAssignDate(),surveyInvestigatorCase.getSurveyEndTime(),efficiencyAttr));
                        double v = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(surveyInvestigatorCase.getSurveyInfoId(), surveyAssignOrg.getId(), surveyInvestigatorCase.getId());
                        surveyInvestigatorCase.setAgingReal(Math.abs(v));
                        surveyInvestigatorCase.setAgingOver(surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck()>0?surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck():0);
                        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
                    }

                    //获取机构的最大
                    if (surveyRiskCaseInfo.getEndTime().before(extensionTime)) {
                        surveyRiskCaseInfo.setEndTime(extensionTime);
                        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                    }
                }else{
                    extension = false;//延期无效
                }
                surveyAssignOrg.setExtensionState(2);
                surveyAssignOrg.setExtensionTime(extensionTime);

                //增加进度 通过延期申请
//                String progressDesc ="审核人：" +userInfo.getUserName()+"\n"+
//                        "延期时间至：" + sdf.format(extensionTime);
                String progressDesc = "审核人：" + userInfo.getUserName()  + "\n" +
                        "申请机构：" +surveyAssignOrg.getSurveyOrgName()+"\n"+
                        "原机构截止时间：" + sdf.format(oldOrgEndTime) + "\n"+
                        "延期时间至：" + sdf.format(extensionTime) + "\n"+
                        "延期原因：" + apiRequest.getString("extensionReason");

                backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(),surveyAssignOrg.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"延期申请审核通过",progressDesc);

                //
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("roleId",58L);
                paramMap.put("orgId",surveyAssignOrg.getSurveyOrgId());
                List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","延期审核通过");
                msgMap.put("content",extension ? "机构延期有效，请知晓！" : "机构延期无效，请知晓！");
                msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "原截止日期：" + simpleDateFormat.format(oldOrgEndTime) + "\n"
                        + "现机构截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()));
                backendWechatApi.send(toUsers,msgMap);


            }else if("lfManager-back".equals(roleCode)){//驳回
                SurveyAssignOrgExtension surveyAssignOrgExtension = surveyAssignOrgExtensionMapper.selectByPrimaryKey(apiRequest.getLong("extensionId"));

                String extensionBackReason = apiRequest.getString("extensionBackReason");
                surveyAssignOrgExtension.setExtensionBackReason(extensionBackReason);
                surveyAssignOrgExtension.setExtensionState(3);//驳回
                surveyAssignOrgExtension.setUpdateBy(userInfo.getUserName());
                surveyAssignOrgExtension.setUpdateTime(new Date());
                surveyAssignOrgExtensionMapper.updateByPrimaryKey(surveyAssignOrgExtension);

                surveyAssignOrg.setExtensionState(3);
                surveyAssignOrg.setExtensionBackReason(extensionBackReason);
                //增加进度 驳回延期申请
                String progressDesc ="审核人：" +userInfo.getUserName()+"\n"+
                        "驳回原因：" + extensionBackReason;
                backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(),surveyAssignOrg.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"延期申请驳回",progressDesc);

            }else if("lfManager-recall".equals(roleCode)) { //撤回
                //已审核通过的申请，可操作撤回
                SurveyAssignOrgExtension surveyAssignOrgExtension = surveyAssignOrgExtensionMapper.selectByPrimaryKey(apiRequest.getLong("extensionId"));

                String extensionBackReason = apiRequest.getString("extensionBackReason");
                surveyAssignOrgExtension.setExtensionBackReason(extensionBackReason);
                surveyAssignOrgExtension.setExtensionState(3);//驳回
                surveyAssignOrgExtension.setUpdateBy(userInfo.getUserName());
                surveyAssignOrgExtension.setUpdateTime(new Date());
                surveyAssignOrgExtensionMapper.updateByPrimaryKey(surveyAssignOrgExtension);

                //机构的原截止日期
                Date oldOrgEndTime = surveyAssignOrgExtension.getOldOrgEndTime();

                surveyAssignOrg.setExtensionState(3);
                surveyAssignOrg.setExtensionBackReason(extensionBackReason);
                surveyAssignOrg.setOrgEndTime(oldOrgEndTime);

                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
                int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
                if(surveyConsignor!=null){
                    efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
                }

                double v2 = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
                surveyAssignOrg.setAgingCheck((double)GetWorkDay.calLeaveDays(surveyAssignOrg.getCreateTime(),surveyAssignOrg.getOrgEndTime(),efficiencyAttr));
                surveyAssignOrg.setAgingReal(Math.abs(v2));
                if (surveyAssignOrg.getAgingCheck() == null){
                    surveyAssignOrg.setAgingCheck(0D);
                }
                surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

                //联动改机构的调查员截止日期。
                List<SurveyInvestigatorCase> surveyInvestigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyAssorgCaseId(surveyAssignOrg.getId());
                for (SurveyInvestigatorCase surveyInvestigatorCase : surveyInvestigatorCases) {
                    surveyInvestigatorCase.setSurveyEndTime(oldOrgEndTime);
                    surveyInvestigatorCase.setAgingCheck((double)GetWorkDay.calLeaveDays(surveyInvestigatorCase.getAssignDate(),surveyInvestigatorCase.getSurveyEndTime(),efficiencyAttr));
                    double v = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(surveyInvestigatorCase.getSurveyInfoId(), surveyAssignOrg.getId(), surveyInvestigatorCase.getId());
                    surveyInvestigatorCase.setAgingReal(Math.abs(v));
                    surveyInvestigatorCase.setAgingOver(surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck()>0?surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck():0);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
                }

                if (surveyRiskCaseInfo.getEndTime().before(oldOrgEndTime)) {
                    surveyRiskCaseInfo.setEndTime(oldOrgEndTime);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }

                //增加进度 驳回延期申请
                String progressDesc ="撤回人：" +userInfo.getUserName()+"\n"+
                        "撤回原因：" + extensionBackReason;
                backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(),surveyAssignOrg.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"延期申请撤回",progressDesc);


            }
        }else if ("review-org-yes".equals(btnCode))
        {//预审通过
            surveyAssignOrg.setReviewOff(1);
            Map<String,Long> map =  new HashMap<String,Long>();
            map.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
            map.put("surveyAssorgCaseId", surveyAssignOrg.getId());
            List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(map);
            for (SurveyInvestigatorCase aCase : cases) {
                aCase.setReviewOff(1);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
            }
        }else if ("review-org-cancel".equals(btnCode))
        {//取消预审
            surveyAssignOrg.setReviewOff(0);
            Map<String,Long> map =  new HashMap<String,Long>();
            map.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
            map.put("surveyAssorgCaseId", surveyAssignOrg.getId());
            List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(map);
            for (SurveyInvestigatorCase aCase : cases) {
                aCase.setReviewOff(0);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
            }
        }else if ("review-help-yes".equals(btnCode))
        {
            Boolean isLast = true;
//            surveyAssignOrg.setReviewOff(1);
            SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
            surveyAssignOrg.setReviewTime(new Date());
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
            Map<String, Object> map = new HashMap<>();
            map = new HashMap();
            map.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
            List<SurveyAssignOrgDto> orgs = surveyAssignOrgMapper.list(map);
            for (SurveyAssignOrgDto org : orgs) {
                if (org.getReviewTime() == null) {// 只要有一个未提交  就不是最后一个。  最后一个机构审核通过的时候到保司审核
                    isLast = false;
                }
            }

            String processName = "复审通过("  + surveyAssignOrg.getSurveyOrgName() + ")";
            if (isLast){
                processName = "复审通过("  + surveyAssignOrg.getSurveyOrgName() + ")" + "，转保司终审中";
            }
            backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(),surveyRiskCaseInfo.getId(),userInfo.getUserId(),userInfo.getUserName(),  processName,null);
            if (isLast){
                surveyRiskCaseInfo.setOpinion(null);
                surveyRiskCaseInfo.setEntrustReportStartDate(new Date());
                surveyRiskCaseInfo.setSurveyState(24);
                surveyRiskCaseInfo.setSurveyStateName("保司终审中");
                //判断是否已经开票
                map = new HashMap();
                map.put("riskCaseInfoId",surveyRiskCaseInfo.getId());
                List<SurveyBillingApply> surveyBillingApplies = surveyBillingApplyMapper.selectByInfo(map);
                if (surveyBillingApplies != null && surveyBillingApplies.size() > 0){//如果已开票就不管

                }else {
                    surveyRiskCaseInfo.setEntrustOkPrice1(surveyRiskCaseInfo.getEntrustMoney());//默认 委托方确认的金额为 乐凡申请的金额
                    surveyRiskCaseInfo.setEntrustOkPrice2(surveyRiskCaseInfo.getEntrustReLosses());
                }


                Double entrustOkPrice1 = surveyRiskCaseInfo.getEntrustOkPrice1()==null?0D:surveyRiskCaseInfo.getEntrustOkPrice1();
                Double entrustOkPrice2 = surveyRiskCaseInfo.getEntrustOkPrice2()==null?0D:surveyRiskCaseInfo.getEntrustOkPrice2();
                surveyRiskCaseInfo.setBillingMoney(entrustOkPrice1 + entrustOkPrice2);
                surveyRiskCaseInfo.setConfirmAccountMoney(entrustOkPrice1 + entrustOkPrice2);

                //获取“案件类型”
                surveyRiskCaseInfo.setCaseState(surveyRiskCaseInfoApi.findCaseState(surveyRiskCaseInfo));

                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

                //发送机构消息通知
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                map =  new HashMap<String,Object>();
                map.put("surveyInfoId",surveyRiskCaseInfo.getId());
                List<SurveyAssignOrgDto> assignOrgs = surveyAssignOrgMapper.list(map);
                for (SurveyAssignOrgDto assignOrg : assignOrgs) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                    String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=org-review-list&assignOrgId=" + assignOrg.getId();
                    Map<String,Long> paramMap =  new HashMap<String,Long>();
                    paramMap.put("roleId",58L);
                    paramMap.put("orgId",assignOrg.getSurveyOrgId());
                    List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                    backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),toUsers,4,"平台复审通过通知",
                            "你有案件平台复审通过，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，案件截止日期：" + simpleDateFormat.format(assignOrg.getOrgEndTime()),url);
                }

                surveyCaseWorkflowApi.addSurveyCaseWorkflow("平台复审",userInfo,surveyRiskCaseInfo.getLefanReportDate(),surveyRiskCaseInfo.getEntrustReportStartDate(),surveyRiskCaseInfo.getId(),surveyRiskCaseInfo.getSurveyId());

            }

            //添加复审时效记录
            SurveyCheckPreFlow surveyCheckPreFlow = surveyCheckPreFlowMapper.selectBySurveyAssignOrgId(surveyAssignOrg.getId());
            if (surveyCheckPreFlow != null) {
                surveyCheckPreFlow.setEndTime(new Date());
                surveyCheckPreFlow.setOperateType(2);
                double startTime = surveyCheckPreFlow.getStartTime().getTime();
                double endTime = surveyCheckPreFlow.getEndTime().getTime();
                surveyCheckPreFlow.setDays((double)Math.round((endTime - startTime)/1000/60));
                surveyCheckPreFlowMapper.updateByPrimaryKeySelective(surveyCheckPreFlow);
                SurveyAssignOrgExtend surveyAssignOrgExtend = surveyAssignOrgExtendMapper.selectByPrimaryKey(surveyAssignOrg.getId());
                if (surveyAssignOrgExtend != null) {
                    surveyAssignOrgExtend.setAgingReal(surveyAssignOrgExtend.getAgingReal() + surveyCheckPreFlow.getDays());
                    if (surveyAssignOrgExtend.getAgingCheck() < surveyAssignOrgExtend.getAgingReal()){
                        surveyAssignOrgExtend.setAgingOver(surveyAssignOrgExtend.getAgingReal() - surveyAssignOrgExtend.getAgingCheck());
                    }
                    surveyAssignOrgExtendMapper.updateByPrimaryKeySelective(surveyAssignOrgExtend);
                }
            }
        }else if ("review-help-no".equals(btnCode))
        {
            String reason = apiRequest.getString("opinion");
            Integer rejectionType=apiRequest.getInt("rejectionType");
            String returnType="";
            if(rejectionType != null && rejectionType==1  ){
                returnType="驳回";
            }else if(rejectionType != null && rejectionType==2  ){
                returnType="新增方向";
            }
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
            surveyAssignOrg.setOrgSurveyState(2);
            surveyAssignOrg.setOrgSurveyStateName("初审中");
            surveyAssignOrg.setOrgOpinion("退回人员："+userInfo.getUserName()+"  退回原因：["+returnType+"]" + reason);
            if (surveyAssignOrg.getReturnState() == null){
                surveyAssignOrg.setReturnState(0);
            }
            if(rejectionType != null && rejectionType == 1 ){
                surveyAssignOrg.setReturnState(surveyAssignOrg.getReturnState() + 1);//累计退回次数
            }

            surveyAssignOrg.setReportDate(null);
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
            surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
            surveyAssignOrg.setAgingReal(Math.abs(v));
            if (surveyAssignOrg.getAgingCheck() == null){
                surveyAssignOrg.setAgingCheck(0D);
            }
            surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
            surveyAssignOrg.setReviewTime(null);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
            //机构案件的状态 改变成审核通过
            apiRequest.clear();
//            apiRequest.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
//            apiRequest.put("org",surveyAssignOrg.getSurveyOrgId());
            apiRequest.put("surveyAssorgCaseId",surveyAssignOrg.getId());
            List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(apiRequest);
            for (SurveyInvestigatorCaseDto aCase : cases) {
                aCase.setSurveyState(4);
                aCase.setSurveyStateName("已提交");
                //此处风控审核退回 正常情况下 案件状态都为 4。 不需要赋 提交时间
                surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
            }

            //发送机构消息通知
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=org-review-list&assignOrgId=" + surveyAssignOrg.getId();
            Map<String,Long> paramMap =  new HashMap<String,Long>();
            paramMap.put("roleId",58L);
            paramMap.put("orgId",surveyAssignOrg.getSurveyOrgId());
            List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
            String content = "你有案件平台复审退回，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime());
            backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),toUsers,4,"平台复审退回通知",
                    content,url);

            Map<String,Object> msgMap =  new HashMap<String,Object>();
            msgMap.put("title","复审退回");
            msgMap.put("content","你有案件复审退回，请尽快进行处理！");
            msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()) + "\n"
                    + "复审人员：" + userInfo.getUserName() + "\n" + "退回原因：["+returnType+"]" + reason);
            backendWechatApi.send(toUsers,msgMap);

            //进度
            backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(),surveyRiskCaseInfo.getId(),userInfo.getUserId(),userInfo.getUserName(),  "平台复审退回("  + surveyAssignOrg.getSurveyOrgName() + ")",surveyAssignOrg.getOrgOpinion());

            surveyRiskCaseInfo.setLefanReportDate(null);
            surveyRiskCaseInfo.setSurveyState(12);//调查中  可以重新分派   机构重新复核
            surveyRiskCaseInfo.setSurveyStateName("调查中");
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = new SurveyOrgPrescriptionFlow();
            surveyOrgPrescriptionFlow.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyOrgPrescriptionFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
            surveyOrgPrescriptionFlow.setStartTime(new Date());
            surveyOrgPrescriptionFlow.setOperateType(3);
            surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
            surveyOrgPrescriptionFlow.setOperateType(2);
            surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);

            //添加复审时效记录
            SurveyCheckPreFlow surveyCheckPreFlow = surveyCheckPreFlowMapper.selectBySurveyAssignOrgId(surveyAssignOrg.getId());
            if (surveyCheckPreFlow != null) {
                surveyCheckPreFlow.setEndTime(new Date());
                surveyCheckPreFlow.setOperateType(4);
                double startTime = surveyCheckPreFlow.getStartTime().getTime();
                double endTime = surveyCheckPreFlow.getEndTime().getTime();
                surveyCheckPreFlow.setDays((double)Math.round((endTime - startTime)/1000/60));
                surveyCheckPreFlowMapper.updateByPrimaryKeySelective(surveyCheckPreFlow);
                SurveyAssignOrgExtend surveyAssignOrgExtend = surveyAssignOrgExtendMapper.selectByPrimaryKey(surveyAssignOrg.getId());
                if (surveyAssignOrgExtend != null) {
                    surveyAssignOrgExtend.setAgingReal(surveyAssignOrgExtend.getAgingReal() + surveyCheckPreFlow.getDays());
                    if (surveyAssignOrgExtend.getAgingCheck() < surveyAssignOrgExtend.getAgingReal()){
                        surveyAssignOrgExtend.setAgingOver(surveyAssignOrgExtend.getAgingReal() - surveyAssignOrgExtend.getAgingReal());
                    }
                    surveyAssignOrgExtendMapper.updateByPrimaryKeySelective(surveyAssignOrgExtend);
                }
            }
        }else if ("review-help-chehui".equals(btnCode))
        {
            surveyAssignOrg.setReviewTime(null);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
            surveyRiskCaseInfo.setEntrustReportStartDate(null);//保司审核不通过 到平台复审中  清空终审时间
            surveyRiskCaseInfo.setOpinion(opinion);
            surveyRiskCaseInfo.setSurveyState(22);
            surveyRiskCaseInfo.setSurveyStateName("平台复审中");
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            //添加复审时效记录
            SurveyCheckPreFlow surveyCheckPreFlow = new SurveyCheckPreFlow();
            surveyCheckPreFlow.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
            surveyCheckPreFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
            surveyCheckPreFlow.setStartTime(new Date());
            surveyCheckPreFlow.setOperateType(3);
            surveyCheckPreFlowMapper.insert(surveyCheckPreFlow);
            surveyCheckPreFlow.setOperateType(null);
            surveyCheckPreFlowMapper.insert(surveyCheckPreFlow);

        }else if ("org-help-commit".equals(btnCode))
        {//互助的初审通过
            /*String orgSummary = apiRequest.getString("orgSummary");
            surveyAssignOrg.setOrgSummary(orgSummary);*/
            surveyAssignOrg.setOrgSurveyState(4);//初审通过
            surveyAssignOrg.setOrgSurveyStateName("初审通过");
            surveyAssignOrg.setOrgOpinion(null);
            surveyAssignOrg.setReportDate(new Date());//提交初审通过时间
            surveyAssignOrg.setReviewTime(null);
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());

            int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
            if(surveyConsignor!=null){
                efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
            }
            SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = surveyOrgPrescriptionFlowMapper.selectByAssOrgIdLimitOne(surveyAssignOrg.getId());
            if (surveyOrgPrescriptionFlow != null){
                Date endTime = new Date();
                surveyOrgPrescriptionFlow.setOperateType(2);
                surveyOrgPrescriptionFlow.setEndTime(endTime);
                surveyOrgPrescriptionFlow.setDays(Math.abs((double)GetWorkDay.calLeaveDays(surveyOrgPrescriptionFlow.getStartTime(),endTime,efficiencyAttr)));
                surveyOrgPrescriptionFlowMapper.updateByPrimaryKeySelective(surveyOrgPrescriptionFlow);
            }

            double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
            surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
            surveyAssignOrg.setAgingReal(Math.abs(v));
            if (surveyAssignOrg.getAgingCheck() == null){
                surveyAssignOrg.setAgingCheck(0D);
            }
            surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
            //计算机构考核绩效 2020年5月26日 17点39分 增加逻辑
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
            Double agingRate = AgingDayUtil.orgAgingRate(surveyAssignOrg,surveyFranchisee,surveyConsignor);
            surveyAssignOrg = agingRate(agingRate,surveyAssignOrg);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

            //同步调查员超期考核绩效
            List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyAssorgCaseId(surveyAssignOrg.getId());
            for (SurveyInvestigatorCase aCase : cases) {
                agingRate = AgingDayUtil.surveyAgingRate(aCase,surveyAssignOrg,surveyFranchisee,surveyConsignor);
                aCase.setOverdueAgingRate(agingRate);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
            }


            //获取上一次机构的开户行信息
            //默认显示 上一条的渠道费银行信息
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyUserId",userInfo.getUserId());
            SurveyChannelCostNew upRecord = surveyChannelCostNewMapper.selectChannelCostNewUp(paramMap);
            if (upRecord == null) {
                upRecord = new SurveyChannelCostNew();
                SurveyChannelCost upItem = surveyChannelCostMapper.selectUpItem(paramMap);
                if (upItem != null){
                    upRecord.setPayeeUserName(upItem.getPayeeUserName());
                    upRecord.setBankNo(upItem.getBankNo());
                    upRecord.setBankBranch(upItem.getBankBranch());
                    upRecord.setBankDeposit(upItem.getBankDeposit());
                }
            }
            upRecord.setSurveyOrgId(surveyAssignOrg.getSurveyOrgId());
            upRecord.setSurveyOrgName(surveyAssignOrg.getSurveyOrgName());
            upRecord.setSurveyUserId(userInfo.getUserId());
            upRecord.setOperationUserId(userInfo.getUserId());
            upRecord.setOperationUserName(userInfo.getUserName());
            upRecord.setSurveyUserName(userInfo.getUserName());
//            upRecord.setPayeeUserName(userInfo.getUserName());
            //同步渠道费用(未申请付款的渠道费用。小于等于限额的。直接到付款审核通过状态。  大于限额的到付费待审核状态)
            upRecord.setSurveyAssorgCaseId(surveyAssignOrg.getId());
            surveyChannelCostNewMapper.generate(upRecord);

            //同步分值 以及 奖励分值 和 不计算的方向分值
            surveyRiskCaseInfoApi.score(surveyAssignOrg.getSurveyInfoId());

            SurveyHelpInfo surveyHelpInfo = null;
            try {
                surveyHelpInfo = ConvertToBeanUtil.toBean(apiRequest, SurveyHelpInfo.class);
                surveyHelpInfo.setId(null);
                surveyHelpInfo.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                surveyHelpInfo.setSurveyOrgId(surveyAssignOrg.getSurveyOrgId());
                if ("否".equals(surveyHelpInfo.getSun())) {
                    surveyHelpInfo.setSunType(null);
                }
                paramMap =  new HashMap<String,Object>();
                paramMap.put("surveyInfoId",surveyHelpInfo.getSurveyInfoId());
                paramMap.put("surveyOrgId",surveyHelpInfo.getSurveyOrgId());
                SurveyHelpInfo helpInfo = surveyHelpInfoMapper.seletSurveyHelpInfoBySurveyInfoIdAndOrgId(paramMap);
                if (helpInfo == null){
                    surveyHelpInfoMapper.insert(surveyHelpInfo);
                }else{
                    surveyHelpInfo.setId(helpInfo.getId());
                    surveyHelpInfoMapper.updateByPrimaryKey(surveyHelpInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            //判断当前机构是否是最后一个审核通过的。 如果是则转风控
            Boolean last = true;
            Map<String,Long> map =  new HashMap<String,Long>();
            map.put("surveyInfoId",surveyAssignOrg.getSurveyInfoId());
            List<SurveyAssignOrgDto> surveyAssignOrgs = surveyAssignOrgMapper.list(map);
            for (SurveyAssignOrgDto assignOrg : surveyAssignOrgs) {
                if (assignOrg.getOrgSurveyState() != 4) {
                    last = false;
                }
            }
            if (last){
                zhuanLefan(surveyRiskCaseInfo,apiRequest,userInfo,null);

            }else{
                String generateReportPath = apiRequest.getString("generateReportPath");
                if (generateReportPath != null && !"".equals(generateReportPath)){
                    CommonFile commonFile = new CommonFile();
                    commonFile.setFilePath(generateReportPath);
                    String fileName = generateReportPath.substring(generateReportPath.lastIndexOf("/") + 1);
                    commonFile.setFileName(fileName);
                    commonFile.setCreateTime(new Date());
                    commonFileMapper.insert(commonFile);
                    surveyRiskCaseInfo.setReportId(commonFile.getId());
                    surveyRiskCaseInfo.setReportName(fileName);
                    surveyRiskCaseInfo.setReportState(1);
                    surveyRiskCaseInfo.setReportDate(new Date());
                    surveyRiskCaseInfo.setGuideState(1);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }
            }

            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
            //发送平台复审人员审核通知
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            Map<String,Object> msgMap =  new HashMap<String,Object>();
            msgMap.put("title","平台复审");
            msgMap.put("content","你有机构提交任务，请尽快进行复审！");
            msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()) + "\n"
                    + "调查机构：" + surveyAssignOrg.getSurveyOrgName());
            backendWechatApi.send(surveyAssignOrg.getReviewUserId(),msgMap);

            //添加复审时效记录
            SurveyAssignOrgExtend surveyAssignOrgExtend = surveyAssignOrgExtendMapper.selectByPrimaryKey(surveyAssignOrg.getId());
            if (surveyAssignOrgExtend == null) {
                surveyAssignOrgExtend = new SurveyAssignOrgExtend();
                surveyAssignOrgExtend.setSurveyAssignOrgId(surveyAssignOrg.getId());
                surveyAssignOrgExtend.setAgingCheck(24d * 60);
                surveyAssignOrgExtend.setAgingReal(0d);
                surveyAssignOrgExtend.setAgingOver(0d);
                surveyAssignOrgExtendMapper.insert(surveyAssignOrgExtend);
            }
            SurveyCheckPreFlow surveyCheckPreFlow = new SurveyCheckPreFlow();
            surveyCheckPreFlow.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
            surveyCheckPreFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
            surveyCheckPreFlow.setStartTime(new Date());
            surveyCheckPreFlow.setOperateType(1);
            surveyCheckPreFlowMapper.insert(surveyCheckPreFlow);
            surveyCheckPreFlow.setOperateType(null);
            surveyCheckPreFlowMapper.insert(surveyCheckPreFlow);
        }else if ("generateTest".equals(btnCode) || "downFileReport".equals(btnCode))
        {
            String files = apiRequest.getString("files");
            List<String> filePaths = new ArrayList<>();
            if (files != null){
                filePaths = JSONArray.parseArray(files,String.class);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,filePaths);
        }
        else if ("mark-error".equals(btnCode))
        {
            surveyAssignOrg.setMarkError(1);
        }
        else if ("qx-mark-error".equals(btnCode))
        {
            surveyAssignOrg.setMarkError(0);
        }
        surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    public SurveyAssignOrg agingRate(Double agingRate,SurveyAssignOrg surveyAssignOrg){
        surveyAssignOrg.setOverdueAgingRate(agingRate);
        surveyAssignOrg.setSurveyMoneySubmit((surveyAssignOrg.getAssessOrgMoney() == null ? 0D : surveyAssignOrg.getAssessOrgMoney()) * agingRate);
        surveyAssignOrg.setSurveryReLossesSubmit((surveyAssignOrg.getAssessOrgLossesMoney() == null ? 0D : surveyAssignOrg.getAssessOrgLossesMoney()) * agingRate);
        return surveyAssignOrg;
    }

    protected void zhuanLefan(SurveyRiskCaseInfo surveyRiskCaseInfo,ApiRequest apiRequest,UserInfo userInfo,String orgOprOpinion){
        surveyInvestigatorCaseApi.syncPrice(surveyRiskCaseInfo,userInfo,true);

        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        surveyRiskCaseInfo.setLefanReportDate(new Date());
        surveyRiskCaseInfo.setSurveyState(22);
        surveyRiskCaseInfo.setOpinion(null);
        surveyRiskCaseInfo.setSurveyStateName("平台复审中");
        surveyRiskCaseInfo.setGuideState(1);

        //判断是否已经开票
        Map<String,Object> map = new HashMap();
        map.put("riskCaseInfoId",surveyRiskCaseInfo.getId());
        List<SurveyBillingApply> surveyBillingApplies = surveyBillingApplyMapper.selectByInfo(map);
        if (surveyBillingApplies != null && surveyBillingApplies.size() > 0){//如果已开票就不管

        }else{
            //获取该案件所有方向中，深度案件，最高的金额（条件：1、深度案件 2、基本费+减损  ）
            if (surveyRiskCaseInfo.getServicesId() == 13 && surveyRiskCaseInfo.getPayType() == 2 && (surveyRiskCaseInfo.getEntrustMoney() == null || surveyRiskCaseInfo.getEntrustMoney() <= 0)) {
                map = new HashMap();
                map.put("surveyInfoId", surveyRiskCaseInfo.getId());
                map.put("entrustOrgId", surveyRiskCaseInfo.getEntrustOrgId());
                Double money = surveyCaseDirectionMapper.selectMaxPriceForShenDu(map);
                if (money != null) {
                    surveyRiskCaseInfo.setEntrustMoney(money);
                } else {
                    surveyRiskCaseInfo.setEntrustMoney(0D);
                }
            }
        }





        String generateReportPath = apiRequest.getString("generateReportPath");
        if (generateReportPath != null && !"".equals(generateReportPath)){
            CommonFile commonFile = new CommonFile();
            commonFile.setFilePath(generateReportPath);
            String fileName = generateReportPath.substring(generateReportPath.lastIndexOf("/") + 1);
            commonFile.setFileName(fileName);
            commonFile.setCreateTime(new Date());
            commonFileMapper.insert(commonFile);
            surveyRiskCaseInfo.setReportId(commonFile.getId());
            surveyRiskCaseInfo.setReportName(fileName);
            surveyRiskCaseInfo.setReportState(1);
            surveyRiskCaseInfo.setReportDate(new Date());
        }
        surveyRiskCaseInfo.setOrgOprOpinion(orgOprOpinion);
        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

        //发送风控审核人通知
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
        if (surveyConsignor.getOrgAttr() == 1){
            UserInfo toUser = userInfoMapper.selectByPrimaryKey(surveyRiskCaseInfo.getBelongUserId());
            if (toUser != null){
                String content = "案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyRiskCaseInfo.getEndTime());
                String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=survey-list";
                backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),toUser.getUserId(),toUser.getUserName(),4,"案件审核通知", content ,url);

                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","平台复审");
                msgMap.put("content","你有机构提交任务，请尽快进行复审！");
                msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "案件截止日期：" + simpleDateFormat.format(surveyRiskCaseInfo.getEndTime()));
                backendWechatApi.send(toUser,msgMap);
            }
        }
        //增加进度 调查完毕，分控审核
        backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(),surveyRiskCaseInfo.getId(),userInfo.getUserId(),userInfo.getUserName(),"调查完毕,平台复审","");
    }

    @ApiMethod(needLogin = false,descript = "任务详情列表",value = "tasks-survey-assign-org")
    @Override
    public ApiResponse tasks(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        Long surveyInvestigatorCaseId = apiRequest.getLong("surveyInvestigatorCaseId");
        String btnCode = apiRequest.getString("btnCode");
        //机构列表
        Map<String, Object> map = new HashMap<>();
        map.put("surveyInfoId",surveyInfoId);
        SurveyShowInfoDTO showInfoDTO = new SurveyShowInfoDTO();
        List<SurveyAssignOrgDto> tasks = surveyAssignOrgMapper.list(map);
        showInfoDTO.setTasks(tasks);
        //方向列表
        map.put("surveyInfoId",surveyInfoId);
        map.put("orderByCondition",1);
        List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.list(map);
        List<SurveyCaseDirectionDto> surveyCaseDirectionDtos = new ArrayList<>();
        try {
            for (SurveyCaseDirection surveyCaseDirection : surveyCaseDirections) {
                SurveyCaseDirectionDto surveyCaseDirectionDto = new SurveyCaseDirectionDto();
                BeanUtils.copyProperties(surveyCaseDirectionDto,surveyCaseDirection);
                surveyCaseDirectionDto.setSurveyInvestigatorCase(surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirectionDto.getSurveyInvestigatorCaseId()));
                map = new HashMap();
                map.put("directionId",surveyCaseDirection.getId());
                List<SurveyCaseDirectionFileDto> directionFileDtos = surveyCaseDirectionFileMapper.list(map);
                surveyCaseDirectionDto.setSurveyCaseDirectionFiles(directionFileDtos);

                //获取任务类型颜色
                SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyCaseDirectionDto.getTaskId());
                if(surveyTaskInfo!=null){
                    surveyCaseDirectionDto.setTaskColor(surveyTaskInfo.getColor());
                }

                //获取任务子类颜色
                SurveyTaskInfoContent surveyTaskInfoContent = surveyTaskInfoContentMapper.selectByPrimaryKey(surveyCaseDirectionDto.getNewId());
                if(surveyTaskInfoContent != null){
                    surveyCaseDirectionDto.setNewColor(surveyTaskInfoContent.getColor());
                }

                surveyCaseDirectionDtos.add(surveyCaseDirectionDto);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        showInfoDTO.setDirectionDtos(surveyCaseDirectionDtos);

        //当前机构信息
        SurveyInvestigatorCaseDto investigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyInvestigatorCaseId);
//        SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(investigatorCase.getSurveyUserId());
//        map =  new HashMap<>();
//        map.put("surveyOrgId",investigator.getOrgId());
//        map.put("surveyInfoId",surveyInfoId);
//        SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
        SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(investigatorCase.getSurveyAssorgCaseId());
        showInfoDTO.setSurveyAssignOrg(surveyAssignOrg);

        //任务信息
        showInfoDTO.setSurveyInvestigatorCase(investigatorCase);

        //案件信息
        SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(investigatorCase.getSurveyInfoId());
        showInfoDTO.setSurveyRiskCaseInfo(surveyRiskCaseInfo);

        if (tasks.size() == 1 && !"tasks".equals(btnCode)){//单机构
            apiRequest.clear();
            apiRequest.put("surveyInfoId",surveyInfoId);
            apiRequest.put("surveyAssorgCaseId",tasks.get(0).getId());
            List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(apiRequest);
            if (cases.size() == 1){
                showInfoDTO.setType(1);
            }else if (cases.size() > 1){
                showInfoDTO.setType(2);
            }
        }else if (tasks.size() > 1 || "tasks".equals(btnCode)){//多机构
            showInfoDTO.setType(4);
            int successNum = 0;
            for (SurveyAssignOrgDto task : tasks) {
                //任务
                apiRequest.clear();
                apiRequest.put("surveyInfoId",surveyInfoId);
                apiRequest.put("org",task.getSurveyOrgId());
                List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(apiRequest);
                task.setCases(cases);
                //方向
                //过滤掉 不属于当前机构的方向
                List<SurveyCaseDirectionDto> directions = new ArrayList<>();
                for (SurveyCaseDirectionDto surveyCaseDirectionDto : surveyCaseDirectionDtos) {
                    if (task.getSurveyOrgId().intValue() == surveyCaseDirectionDto.getSurveyOrgId().intValue()){
                        directions.add(surveyCaseDirectionDto);
                    }
                }
                task.setDirections(directions);
                if (task.getOrgSurveyState() == 4 || task.getOrgSurveyState() == 3){
                    successNum = successNum + 1;
                }
            }
            showInfoDTO.setSuccessNum(successNum);
            if (showInfoDTO.getSuccessNum() != tasks.size() - 1){
                showInfoDTO.setAllSuccess(false);
            }
            showInfoDTO.setTasks(tasks);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,showInfoDTO);
    }



    @ApiMethod(needLogin = false,descript = "获取互助报告数据",value = "get-help-report-data")
    @Override
    public ApiResponse getHelpReportData(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        Long surveyOrgId = apiRequest.getLong("surveyOrgId");
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInfoId);
        TemplateHelpData helpData = getHelpReportData(surveyRiskCaseInfo,surveyOrgId);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,helpData);
    }

    private TemplateHelpData getHelpReportData(SurveyRiskCaseInfo surveyRiskCaseInfo,Long surveyOrgId){
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TemplateHelpData helpData = new TemplateHelpData();
        helpData.setReportName(surveyRiskCase.getSurveyCaseNo() == null ? surveyRiskCase.getSurveyPerson() + "案" : surveyRiskCase.getSurveyCaseNo());
        helpData.setSurveyRiskCaseInfo(surveyRiskCaseInfo);
        SurveyConsignorModel consignorModel = surveyConsignorModelMapper.selectByConsignorId(surveyRiskCase.getEntrustOrgId());
        SurveyModelInfo model = null;
        if (consignorModel == null){
            model = surveyModelInfoMapper.selectByPrimaryKey(5L);//互助模板
        }else{
            model = surveyModelInfoMapper.selectByPrimaryKey(consignorModel.getModelId());
        }
        if (model == null){
            return null;
        }
        helpData.setSurveyModelInfo(model);

        //sheet1
        HelpData1 helpData1 = new HelpData1();
        helpData1.setSurveyCompletion(surveyRiskCaseInfo.getReportCompletion());

        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("surveyOrgId",surveyOrgId);
        paramMap.put("surveyInfoId",surveyRiskCaseInfo.getId());
        SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByOne(paramMap);

        //sheet2
        HelpData2 helpData2 = new HelpData2();
        paramMap =  new HashMap<String,Object>();
        paramMap.put("surveyInfoId",surveyRiskCaseInfo.getId());
        paramMap.put("surveyAssorgCaseId", surveyAssignOrg.getId());
        List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(paramMap);
        String surveyNames = "";
        for (SurveyInvestigatorCase aCase : cases) {
            surveyNames += aCase.getSurveyUserName() + ",";
        }
        helpData2.setCellValue1(surveyNames);
        helpData2.setCellValue2(surveyRiskCaseInfo.getBelongUserName());


        helpData2.setCellValue3(convertDate(simpleDateFormat,surveyAssignOrg.getCreateTime()));
        helpData2.setCellValue4(convertDate(simpleDateFormat,surveyAssignOrg.getReportDate() == null ? surveyAssignOrg.getOrgEndTime() : surveyAssignOrg.getReportDate()));

        paramMap =  new HashMap<String,Object>();
        paramMap.put("surveyInfoId",surveyRiskCaseInfo.getId());
        paramMap.put("surveyOrgId",surveyOrgId);
        SurveyHelpInfo surveyHelpInfo = surveyHelpInfoMapper.seletSurveyHelpInfoBySurveyInfoIdAndOrgId(paramMap);
        if (surveyHelpInfo != null){
            helpData2.setCellValue5(surveyHelpInfo.getSun());
            helpData2.setCellValue6(surveyHelpInfo.getSunType());
            helpData2.setCellValue7(surveyHelpInfo.getSunRemark());
            helpData2.setCellValue8(surveyHelpInfo.getCustGive());
            helpData2.setCellValue9(surveyHelpInfo.getFeedProblem());
            helpData2.setCellValue10(surveyHelpInfo.getProblemRemark());
            helpData2.setCellValue11(surveyHelpInfo.getSurveyAllRemark());
        }

        //sheet....
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("surveyInfoId",surveyRiskCaseInfo.getId());
        map.put("surveyOrgId",surveyOrgId);
        map.put("invalidState", 0);//无效方向
        List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
        HelpDataLists helpDataLists = new HelpDataLists();
        String [] keys = {"面访患病成员及申请人","走访就诊医疗机构","居住地医疗机构排查","社保排查","商保排查","体检机构排查","事故地点排查",
                "事故处理机构排查","面访患病成员家属","走访出生医疗机构","工作地医疗机构排查","出险地医疗机构排查","户籍所在地医疗机构排查",
                "走访街道办事处或村委会","走访居住地","走访户籍所在地","走访工作单位","走访疾控防疫中心","走访公检法等机关单位","走访鉴定机构","走访材料出具机构"};
        for (String key : keys) {
            List<HelpDirectionLine> lines = new ArrayList<>();
            int index = 1;
            for (SurveyCaseDirection direction : directions) {
                if (key.equals(direction.getTaskName())) {
                    HelpDirectionLine line = new HelpDirectionLine();
                    line.setIndex(String.valueOf(index));
                    index ++ ;
                    line.setSurveyCaseDirection(direction);
                    //方向内容转换至sheet cellValue
                    {
                        if ("面访患病成员及申请人".equals(key) || "面访患病成员家属".equals(key)) {
                            SurveyCaseDirection item = line.getSurveyCaseDirection();
                            line.setCellValue1(convertDate(simpleDateFormat, item.getHuzhuDate()));
                            line.setCellValue2(item.getProvince());
                            if (item.getRegionType() == 5) {
                                line.setCellValue3(item.getCity().replace("市","") + "城区");
                            } else if (item.getRegionType() == 6) {
                                line.setCellValue3(item.getCity().replace("市","") + "郊县");
                            } else {
                                line.setCellValue3(item.getCity());
                            }
                            line.setCellValue4(item.getDistrict() == null ? "XXX" : item.getDistrict());
                            line.setCellValue5(item.getDirectionName());
                            line.setCellValue6(item.getCreateBy());
                            line.setCellValue7(item.getAttr1Obj());
                            line.setCellValue8(item.getAttr1ObjName());
                            line.setCellValue9(item.getSun() == 1 ? "是" : "否");
                            line.setCellValue10(item.getSunRemark());
                            line.setCellValue11(item.getDirectionText());
                        }else if ("走访就诊医疗机构".equals(key) || "居住地医疗机构排查".equals(key) || "走访出生医疗机构".equals(key) || "工作地医疗机构排查".equals(key)
                                || "出险地医疗机构排查".equals(key) || "户籍所在地医疗机构排查".equals(key)) {
                            SurveyCaseDirection item = line.getSurveyCaseDirection();
                            line.setCellValue1(item.getDirectionName());
                            line.setCellValue2(convertDate(simpleDateFormat, item.getHuzhuDate()));
                            line.setCellValue3(item.getAttr2Type());
                            line.setCellValue4(item.getAttr2His());
                            line.setCellValue5(item.getSun() == 1 ? "是" : "否");
                            line.setCellValue6(item.getSunRemark());
                            line.setCellValue7(item.getDirectionText());
                        }else if ("社保排查".equals(key) || "体检机构排查".equals(key)) {
                            SurveyCaseDirection item = line.getSurveyCaseDirection();
                            line.setCellValue1(item.getDirectionName());
                            line.setCellValue2(convertDate(simpleDateFormat, item.getHuzhuDate()));
                            line.setCellValue3(item.getSun() == 1 ? "是" : "否");
                            line.setCellValue4(item.getSunRemark());
                            line.setCellValue5(item.getDirectionText());
                        }else if ("商保排查".equals(key)) {
                            SurveyCaseDirection item = line.getSurveyCaseDirection();
                            line.setCellValue1(item.getAttr4Name1());
                            line.setCellValue2(item.getAttr4Remark1());
                            line.setCellValue3(item.getAttr4Name2());
                            line.setCellValue4(item.getAttr4Remark2());
                        }else if ("事故地点排查".equals(key)) {
                            SurveyCaseDirection item = line.getSurveyCaseDirection();
                            line.setCellValue1(convertDate(simpleDateFormat, item.getHuzhuDate()));
                            line.setCellValue2(item.getProvince());
                            if (item.getRegionType() == 5) {
                                line.setCellValue3(item.getCity().replace("市","") + "城区");
                            } else if (item.getRegionType() == 6) {
                                line.setCellValue3(item.getCity().replace("市","") + "郊县");
                            } else {
                                line.setCellValue3(item.getCity());
                            }
                            line.setCellValue4(item.getDistrict() == null ? "XXX" : item.getDistrict());
                            line.setCellValue5(item.getDirectionName());
                            line.setCellValue6(item.getDirectionText());
                        }else if ("事故处理机构排查".equals(key)){
                            SurveyCaseDirection item = line.getSurveyCaseDirection();
                            line.setCellValue1(convertDate(simpleDateFormat, item.getHuzhuDate()));
                            line.setCellValue2(item.getDirectionName());
                            line.setCellValue3(item.getDirectionText());
                        }else if ("走访街道办事处或村委会".equals(key) || "走访居住地".equals(key) || "走访户籍所在地".equals(key) || "走访工作单位".equals(key)
                                || "走访疾控防疫中心".equals(key) || "走访公检法等机关单位".equals(key) || "走访鉴定机构".equals(key) || "走访材料出具机构".equals(key)) {
                            SurveyCaseDirection item = line.getSurveyCaseDirection();
                            line.setCellValue1(item.getAttr3Obj());
                            line.setCellValue2(convertDate(simpleDateFormat, item.getHuzhuDate()));
                            line.setCellValue3(item.getProvince());
                            if (item.getRegionType() == 5) {
                                line.setCellValue4(item.getCity().replace("市","") + "城区");
                            } else if (item.getRegionType() == 6) {
                                line.setCellValue4(item.getCity().replace("市","") + "郊县");
                            } else {
                                line.setCellValue4(item.getCity());
                            }
                            line.setCellValue5(item.getDistrict() == null ? "XXX" : item.getDistrict());
                            line.setCellValue6(item.getDirectionName());
                            line.setCellValue7(item.getSun() == 1 ? "是" : "否");
                            line.setCellValue8(item.getSunRemark());
                            line.setCellValue9(item.getDirectionText());
                        }
                    }
                    lines.add(line);
                }
            }
            helpDataLists.setSheetLinesData(key, lines);
        }
        helpData.setHelpData1(helpData1);
        helpData.setHelpData2(helpData2);
        helpData.setHelpDataLists(helpDataLists);
        return helpData;
    }

    private String convertDate(SimpleDateFormat simpleDateFormat,Date date){
        if (date != null){
            return simpleDateFormat.format(date);
        }
        return "";
    }

    @ApiMethod(needLogin = false,descript = "获取已审核通过的调查机构列表",value = "get-survey-orgs")
    @Override
    public ApiResponse getSurveyOrgs(ApiRequest apiRequest) {
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("surveyInfoId",apiRequest.getLong("surveyInfoId"));
        map.put("orgSurveyState",4);
        List<SurveyAssignOrgDto> list = surveyAssignOrgMapper.list(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }

    @ApiMethod(needLogin = false,descript = "调查回访列表",value = "list-survey-assign-visit")
    @Override
    public ApiResponse listVisit(ApiRequest apiRequest) {
        try{
            Long currentUserId = getCurrentUserId(apiRequest);
            String menuCode = apiRequest.getString("menuCode");
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            setBackendPageSize(apiRequest);
            if("visit-list".equals(menuCode)){// 调查回访

            }
            apiRequest.put("searchStr",apiRequest.getString("searchStr") == null ? null : apiRequest.getString("searchStr").trim());
            int count = 0;
            List<SurveyAssignOrgDto> list = null;
            if ("visit-list".equals(menuCode)){
                count = surveyAssignOrgMapper.selectAssignVisitListSize(apiRequest);
                list = surveyAssignOrgMapper.selectAssignVisitList(apiRequest);
            }
            for (SurveyAssignOrgDto dto : list) {
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(dto.getSurveyId());
                dto.setSurveyRiskCase(surveyRiskCase);
                dto.setSurveyRiskCaseInfo(surveyRiskCaseInfoMapper.selectByPrimaryKey(dto.getSurveyInfoId()));

            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


    /**
     * 初始化比例
     * @param apiRequest
     * @return
     */
    @ApiMethod(needLogin = false,descript = "初始化比例",value = "init-rate")
    public ApiResponse initRate(ApiRequest apiRequest){
        String initType = apiRequest.getString("initType");
        if ("org".equals(initType)){
            List<SurveyAssignOrg> orgs = surveyAssignOrgMapper.initRate(new HashMap());
            for (SurveyAssignOrg org : orgs) {
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(org.getSurveyOrgId());
                SurveyConsignor surveyConsignor = new SurveyConsignor();
                surveyConsignor.setOrgAttr(2);surveyConsignor.setEfficiencyAttr(2);
                Double rate = AgingDayUtil.orgAgingRate(org,surveyFranchisee,surveyConsignor);
                org.setOverdueAgingRate(rate);
                if (rate < 1){
                    org.setSurveyMoneySubmit((org.getAssessOrgMoney() == null ? 0D : org.getAssessOrgMoney()) * rate);
                    org.setSurveryReLossesSubmit((org.getAssessOrgLossesMoney() == null ? 0D : org.getAssessOrgLossesMoney()) * rate);
                }
                surveyAssignOrgMapper.updateByPrimaryKey(org);
            }
        }else if ("survey".equals(initType)){
            List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.initRate(new HashMap());
            for (SurveyInvestigatorCase aCase : cases) {
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(aCase.getSurveyAssorgCaseId());
                if (surveyAssignOrg == null)
                    continue;
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
                SurveyConsignor surveyConsignor = new SurveyConsignor();
                surveyConsignor.setOrgAttr(2);surveyConsignor.setEfficiencyAttr(2);
                Double rate = AgingDayUtil.surveyAgingRate(aCase,surveyAssignOrg,surveyFranchisee,surveyConsignor);
                aCase.setOverdueAgingRate(rate);
                if (rate < 1){
                    aCase.setScore((aCase.getAssessScore() == null ? 0D : aCase.getAssessScore()) * rate);
                    aCase.setScoreSun((aCase.getAssessSunScore() == null ? 0D : aCase.getAssessSunScore()) * rate);
                    aCase.setBsScore((aCase.getAssessBsScore() == null ? 0D : aCase.getAssessBsScore()) * rate);
                    aCase.setOtherScore((aCase.getAssessOtherScore() == null ? 0D : aCase.getAssessOtherScore()) * rate);
                }
                surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
            }
        }else if ("risk".equals(initType)){
            apiRequest.put("condition",5);//保司审核通过的计算时效
            List<SurveyRiskCaseInfoDto> list =  surveyRiskCaseInfoMapper.list(apiRequest);
            List<Long> ids = Arrays.asList(new Long[]{105L,87L,82L});
            int efficiencyAttr = 2;
            for (SurveyRiskCaseInfoDto item : list) {
                if (ids.contains(item.getEntrustOrgId())) {
                    efficiencyAttr = 1;
                }
                item.setAgingDay(AgingDayUtil.riskCaseInfoAgingDay(item,efficiencyAttr));
                surveyRiskCaseInfoMapper.updateByPrimaryKey(item);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    //角色判断
    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().equals(roleId)){
                return true;
            }
        }
        return false;
    }

    private Map<String,Object> getDataRole(Long currentUserId,String menuCode){
        Map<String,Object> map =  new HashMap<String,Object>();
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgManger = isRoleUser(userRoles,104L), lfManger = isRoleUser(userRoles,95L),
                investigators = isRoleUser(userRoles,50L), areaManger = isRoleUser(userRoles,116L);
        SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
        if (lfManger){
            map.put("dataRoleCode","manger");
            return map;
        }
        if (orgManger){
            if (investigator != null){
                SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(investigator.getOrgId());
                if (franchisee != null){
                    if (franchisee.getLevel() == 1){
                        map.put("dataRoleCode","provincialManger");// 省级数据
                    }else if (franchisee.getLevel() == 2){
                        map.put("dataRoleCode","areaManger");//片区数据
                    }
                    map.put("dataRoleOrgId",investigator.getOrgId());
                    return map;
                }
            }
        }
        if(areaManger){
            map.put("dataRoleCode","districtManger");//片区负责人
            return map;
        }
        if(investigators){
            if (investigator != null && "investigatorReport".equals(menuCode)) {
                map.put("dataRoleUserId", investigator.getUserId());
                map.put("dataRoleOrgId", investigator.getOrgId());
            }
            map.put("dataRoleCode","investigators");
            return map;
        }
        return map;
    }

    @ApiMethod(needLogin = false, descript = "机构每日案件提醒", value = "backend-survey-org-case-remind-list")
    public ApiResponse orgCaseRemindList(ApiRequest apiRequest) {
        try {
            Long currentUserId = getCurrentUserId(apiRequest);
            List<BusUserRole> busUserRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            if (busUserRoles.removeIf(e -> 119 == e.getRoleId())) {//119每日案件不提醒角色
                return new ApiResponse(ApiMsgEnum.SUCCESS, 0, null);
            }
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (surveyInvestigator != null) {
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("orgId", surveyInvestigator.getOrgId());
                paramMap.put("yesterday", LocalDate.now().plusDays(-1).toString());
                paramMap.put("today", LocalDate.now().toString());
                paramMap.put("tomorrow", LocalDate.now().plusDays(1).toString());
                List<SurveyAssignOrgDto> surveyAssignOrgDtos = surveyAssignOrgMapper.selectOrgCaseRemindList(paramMap);
                for (SurveyAssignOrgDto surveyAssignOrgDto : surveyAssignOrgDtos) {
                    Long id = surveyAssignOrgDto.getId();
                    List<SurveyInvestigatorCaseDto> surveyInvestigatorCases = surveyInvestigatorCaseMapper.selectInvInfoByOrgCaseId(id);
                    for (SurveyInvestigatorCaseDto surveyInvestigatorCase : surveyInvestigatorCases) {
                        String replyFileIds = surveyInvestigatorCase.getReplyFiles();
                        if (!StringUtils.isEmpty(replyFileIds)) {
                            List<CommonFile> commonFiles = commonFileMapper.selectByIds(replyFileIds);
                            surveyInvestigatorCase.setReplyCommonFiles(commonFiles);
                        }
                    }
                    surveyAssignOrgDto.setCases(surveyInvestigatorCases);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS, surveyAssignOrgDtos.size(), surveyAssignOrgDtos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false, descript = "机构每日案件提醒操作", value = "backend-survey-case-remind-operate")
    public ApiResponse orgCaseRemindOperate(ApiRequest apiRequest) {
        Long orgCaseId = apiRequest.getLong("orgCaseId");
        SurveyAssignOrgDto surveyAssignOrgDto = surveyAssignOrgMapper.selectByPrimaryKey(orgCaseId);
        SurveyAssignOrgReply surveyAssignOrgReply = new SurveyAssignOrgReply();
        int type = apiRequest.getInt("type");
        String progressName = "";
        String progressDesc = "";
        if (type == 1 || type == 2){
            progressName = type == 1 ? "今天可回销" : "明天可回销";
            surveyAssignOrgReply.setSurveyAssorgCaseId(orgCaseId);
            surveyAssignOrgReply.setReplyContent(type == 1 ? "今天可回销" : "明天可回销");
            surveyAssignOrgReply.setReplyTime(new Date());
            surveyAssignOrgReply.setReplyType(type);
            surveyAssignOrgReplyMapper.insert(surveyAssignOrgReply);
            surveyAssignOrgDto.setFollowInformation(progressName);
        }
        if (type == 4){
            surveyAssignOrgReply.setSurveyAssorgCaseId(orgCaseId);
            surveyAssignOrgReply.setReplyContent(apiRequest.getString("replyContent"));
            surveyAssignOrgReply.setReplyTime(new Date());
            surveyAssignOrgReply.setReplyType(type);
            surveyAssignOrgReplyMapper.insert(surveyAssignOrgReply);

            progressName = "其他回复";
            progressDesc = apiRequest.getString("replyContent");
            if (StringUtils.isEmpty(progressDesc)){
                progressDesc = "";
            }
            surveyAssignOrgDto.setFollowInformation("其他回复："+progressDesc);
            //认证材料
            StringBuffer fileIds  =  new StringBuffer();
            String imgs = apiRequest.getString("pathBackReason");
            if (imgs != null) {
                String[] urls = imgs.split(",");
                for (String url : urls) {
                    CommonFile commonFile = new CommonFile();
                    //保存认证材料
                    commonFile.setFilePath(url);
                    int firstName = url.lastIndexOf("/") + 1 ;
                    int lastName = url.lastIndexOf(".");
                    String name = url.substring(firstName,lastName);
                    commonFile.setFileName(name);
                    commonFile.setCreateTime(new Date());
                    commonFileMapper.insert(commonFile);
                    fileIds.append(commonFile.getId() +",");
                }
                surveyAssignOrgReply.setReplyFiles(fileIds.toString());
                surveyAssignOrgReplyMapper.updateByPrimaryKey(surveyAssignOrgReply);
            }
        }

        if (surveyAssignOrgDto!=null){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(getCurrentUserId(apiRequest));
            SurveyProgress surveyProgress = new SurveyProgress();
            surveyProgress.setSurveyId(surveyAssignOrgDto.getSurveyId());
            surveyProgress.setSurveyInfoId(surveyAssignOrgDto.getSurveyInfoId());
            if (surveyInvestigator != null){
                surveyProgress.setProgressUserId(surveyInvestigator.getUserId());
                surveyProgress.setProgressUserName(surveyInvestigator.getRealName());
                surveyProgress.setCreateBy(surveyInvestigator.getRealName());
            }
            surveyProgress.setProgressName(progressName);
            surveyProgress.setProgressDesc(progressDesc);
            surveyProgress.setProgressTime(new Date());
            surveyProgress.setCreateTime(new Date());
            surveyProgress.setUpdateBy(null);
            surveyProgress.setUpdateTime(null);
            surveyProgress.setDeleteFlag(0);
            surveyProgressMapper.insert(surveyProgress);

            surveyAssignOrgDto.setFollowTime(new Date());
            surveyAssignOrgDto.setFollowUserId(surveyInvestigator.getUserId());
            surveyAssignOrgDto.setFollowUserName(surveyInvestigator.getRealName());
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrgDto);
        }


        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }
}
