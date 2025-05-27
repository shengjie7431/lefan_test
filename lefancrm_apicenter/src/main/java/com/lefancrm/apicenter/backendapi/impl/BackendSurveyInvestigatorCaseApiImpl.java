package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.SurveyInvestigatorCaseApi;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.model.CommonFile;
import com.lefancrm.apicenter.util.*;
import com.lefancrm.apicenter.util.baidu.AuthService;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/**
 * Created by lixianfeng on 2018/12/18.
 */
@Service
@ApiService(descript = "调查员案件业务表API")
public class BackendSurveyInvestigatorCaseApiImpl extends BaseServiceImpl implements SurveyInvestigatorCaseApi{
    @Value("${survey.update.common}")
    private String surveyUpdateCommon;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyTaskTypeMapper surveyTaskTypeMapper;
    @Autowired
    private SurveyTaskInfoMapper surveyTaskInfoMapper;
    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;
    @Autowired
    private SurveyInvestigatorCaseTypeMapper surveyInvestigatorCaseTypeMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private SurveyFranchiseePriceMapper surveyFranchiseePriceMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyCommonAreaPriceMapper surveyCommonAreaPriceMapper;
    @Autowired
    private SurveyInvestigatorAreaPriceMapper surveyInvestigatorAreaPriceMapper;
    @Autowired
    private SurveyConsignorPriceMapper surveyConsignorPriceMapper;
    @Autowired
    private SurveyFeeDetailsMapper surveyFeeDetailsMapper;
    @Autowired
    private SurveyPunishMapper surveyPunishMapper;

    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private SurveyCaseDirectionFileMapper surveyCaseDirectionFileMapper;
    @Autowired
    private SurveyTaskInfoContentMapper surveyTaskInfoContentMapper;

    @Autowired
    private BackendSurveyCaseWorkflowApiImpl surveyCaseWorkflowApi;
    @Autowired
    private BackendSurveyProgressApiImpl backendSurveyProgressApi;
    @Autowired
    private SurveyBackCaseMapper surveyBackCaseMapper;

    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private BackendSurveyInvestigatorApiImpl backendSurveyInvestigatorApi;
    @Autowired
    private SurveyConsignorModelMapper surveyConsignorModelMapper;
    @Autowired
    private BackendSurveyMessageApiImpl backendSurveyMessageApi;
    @Autowired
    private SurveyCaseFileMapper surveyCaseFileMapper;
    @Autowired
    private SurveyDirectionAreaHistoryMapper surveyDirectionAreaHistoryMapper;
    @Autowired
    private SurveyDirectionResultTypeMapper surveyDirectionResultTypeMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyTaskDirectionResultMapper surveyTaskDirectionResultMapper;
    @Autowired
    private BackendSurveyRiskCaseInfoApiImpl surveyRiskCaseInfoApi;
    @Autowired
    private SurveyAccountLogMapper surveyAccountLogMapper;
    @Autowired
    private SurveyReimbursementInfoMapper surveyReimbursementInfoMapper;
    @Autowired
    private SurveyReimbursementFileMapper surveyReimbursementFileMapper;
    @Autowired
    private InvestigatorReDetailsMapper investigatorReDetailsMapper;
    @Autowired
    private InvestigatorPreDetailsMapper investigatorPreDetailsMapper;
    @Autowired
    private SurveyUserPrescriptionFlowMapper surveyUserPrescriptionFlowMapper;
    @Autowired
    private SurveyOrgPrescriptionFlowMapper surveyOrgPrescriptionFlowMapper;
    @Autowired
    private SurveyPriceMapper surveyPriceMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;

    @Autowired
    private SurveyAttrUpdRecordMapper surveyAttrUpdRecordMapper;
    @Autowired
    private SurveyChannelModelInfoMapper surveyChannelModelInfoMapper;
    @Autowired
    private SurveyChannelModelOrgMapper surveyChannelModelOrgMapper;
    @Autowired
    private SurveyChannelCostNewMapper surveyChannelCostNewMapper;
    @Autowired
    private SurveyPriceModelMapper surveyPriceModelMapper;
    @Autowired
    private SurveyScoreModelOrgMapper surveyScoreModelOrgMapper;
    @Autowired
    private SurveyScoreModelInfoMapper surveyScoreModelInfoMapper;
    @Autowired
    private CommonAreaMapper commonAreaMapper;


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private SurveyClockCaseMapper surveyClockCaseMapper;
    @ApiMethod(needLogin = false,descript = "调查员案件列表",value = "list-survey-investigator-case")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        setBackendPageSize(apiRequest);
        String menuCode = apiRequest.getString("menuCode");
        String operateState = apiRequest.getString("operateState");
        apiRequest.put("searchStr",apiRequest.getString("searchStr") == null ? null : apiRequest.getString("searchStr").trim());
        if ("dcy-list".equals(menuCode)){//调查员案件列表
            apiRequest.put("surveyUserId",currentUserId);
//            if ("2189".equals(currentUserId.toString())){// 查看所有调查员  便于维护 检查问题
//                apiRequest.remove("surveyUserId");
//            }
            if (operateState == null || "".equals(operateState) || "1".equals(operateState)){
                apiRequest.put("conditionNew",1);//调查中
            }else if ("2".equals(operateState)){
                apiRequest.put("conditionNew",2);//已提交
            }
            apiRequest.put("order",1); //排序
        }else if ("all-list".equals(menuCode)){

        }else if ("report-list".equals(menuCode)){//只显示自己机构下的案件
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (surveyInvestigator != null){
                apiRequest.put("org",surveyInvestigator.getOrgId());
                if ("0".equals(operateState) || operateState == null || "".equals(operateState)){
                    apiRequest.put("surveyState",3);//为3的    属于待处理
                }else if ("1".equals(operateState)){
                    apiRequest.put("condition",12);//不为3的  属于已处理
                }
            }
        }else if ("report-review-list".equals(menuCode)){
            apiRequest.put("surveyState",6);//初审中的  初审审核通过的
        }else if ("refuse-list".equals(menuCode)){
            apiRequest.put("surveyState",5);//拒接审核中
        }else if ("over-time-list".equals(menuCode)){
            List<BusUserRole> roles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean orgManFrist = isRoleUser(roles,57L);//机构初审
            Boolean orgMan = isRoleUser(roles,58L);//机构复核
            if(orgManFrist || orgMan){
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (surveyInvestigator != null) {
                    apiRequest.put("org", surveyInvestigator.getOrgId());
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String specifiedDay = sdf.format(new Date());
            apiRequest.put("condition",4);//即将超期（3天），已超期的案件列表
            String overTimeType = apiRequest.getString("overTimeType");//1、已超期；2、即将超期（3天）
            if("1".equals(overTimeType)){
                apiRequest.put("endDate",new Date());
            }else if("2".equals(overTimeType)){
                apiRequest.put("endDate",getSpecifiedDayBefore(specifiedDay,-3));
                apiRequest.put("startDate",new Date());
            }else{  //全部
                apiRequest.put("endDate",getSpecifiedDayBefore(specifiedDay,-3));
            }
        }else if("sun-list".equals(menuCode)){//阳性案件(阳性案件审核状态：1退回的 机构审核中2  平台审核中3  平台审核通过4，通过之后调查员案件表的阳性为1 案件阳性也为1)
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            Boolean a = true,b = true;
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            b = isRoleUser(userRoles,58L);//狄大人机构复核
            a = isRoleUser(userRoles,53L);//平台终审
            //机构角色 只查询机构的审核中    平台角色 查询所有的审核中
            if (b){
                apiRequest.put("condition",50);//机构审核中
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (surveyInvestigator != null) {
                    apiRequest.put("currentOrg", surveyInvestigator.getOrgId());
                }
            }
            if (a){
                apiRequest.put("condition",51);//平台审核中
            }
            if (a && b){//初审中  和 平台审核中
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (surveyInvestigator != null) {
                    apiRequest.put("currentOrg", surveyInvestigator.getOrgId());
                }
                apiRequest.put("condition",52);// 机构审核中 和 平台
            }
        }else if ("task-user-review".equals(menuCode)){//任务预审（机构的任务）
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (surveyInvestigator == null){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
            apiRequest.put("org",surveyInvestigator.getOrgId());
            apiRequest.put("condition",60);//调查员已提交 机构还是调查中
            apiRequest.put("order",2);
        }else{
            return new ApiResponse(ApiMsgEnum.ERROR_PARAMETER);
        }

        int count = surveyInvestigatorCaseMapper.listSize(apiRequest);
        List<SurveyInvestigatorCaseDto> list = surveyInvestigatorCaseMapper.list(apiRequest);
        for (SurveyInvestigatorCaseDto caseDto : list) {
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(caseDto.getSurveyId());
            caseDto.setSurveyRiskCase(surveyRiskCase);
            caseDto.setSurveyRiskCaseInfo(surveyRiskCaseInfoMapper.selectByPrimaryKey(caseDto.getSurveyInfoId()));
            caseDto.setIsCurOrg(true);
            if ("report-list".equals(menuCode)){
                caseDto.setSurveyFranchisee(surveyFranchiseeMapper.selectByPrimaryKey(caseDto.getSurveyOrgId()));
            }
            if("dcy-list".equals(menuCode) || "task-user-review".equals(menuCode)){
                if ("dcy-list".equals(menuCode) && !"2".equals(operateState)){
                    if(caseDto.getSurveyRemark() !=null){
                        caseDto.setBackgroundColor("#f9e3e4");
                    }
                    if (caseDto.getIsDirectionSuccess() == 1){//方向完成
                        Map<String,Object> paramMap =  new HashMap<String,Object>();
                        paramMap.put("surveyInfoId",caseDto.getSurveyInfoId());
                        paramMap.put("surveyOrgId", caseDto.getSurveyOrgId());
                        paramMap.put("surveyUserId",caseDto.getSurveyUserId());
                        List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgIdNoUserId(paramMap);//非当前调查员
                        Boolean last = true;
                        for (SurveyInvestigatorCase aCase : cases) {
                            if (aCase.getSurveyState() != 4) {
                                last = false;
                                break;
                            }
                        }
                        if (last){
                            caseDto.setBackgroundColor("#D5EDFF");
                        }
                    }
                }
                //显示费用报销数值
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(caseDto.getSurveyOrgId());
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCase.getEntrustOrgId());
                caseDto.setShowExpenseReimbursementValue(surveyConsignor.getOrgAttr() == 2 && surveyFranchisee.getType() == 1);
                caseDto.setShowBaoSi(surveyConsignor.getOrgAttr() == 1 && surveyFranchisee.getInsuranceType() == 1);
//                String totalMoney = surveyReimbursementInfoMapper.selectSurveyReimbursementInfoTotalMoneyBySurveyInfoId(caseDto.getSurveyInfoId(),caseDto.getId(),currentUserId);
                Double totalMoney = surveyClockCaseMapper.surveyInvCaseAllMoney(caseDto.getSurveyInfoId(),caseDto.getSurveyUserId());
                caseDto.setExpenseReimbursementValue(totalMoney.toString());

                int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
                if(surveyConsignor!=null){
                    efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
                }

                //计算时效
                SimpleDateFormat orgFormatter = new SimpleDateFormat("yyyy-MM-dd");
                String assDate = orgFormatter.format(caseDto.getAssignDate());
                String endDate = orgFormatter.format(caseDto.getSurveyEndTime());
                String commitDate = "";
                if(caseDto.getCreportDate()!=null){
                    commitDate = orgFormatter.format(caseDto.getCreportDate());
                }
                Map<String,Object> resultInfo = surveyCaseUserDays(caseDto,caseDto.getSurveyState(), assDate, commitDate, endDate, efficiencyAttr);
                caseDto.setEfficiencyState(resultInfo.get("efficiencyState").toString());
                caseDto.setEfficiencyStateColor(resultInfo.get("efficiencyStateColor").toString());
            }

            //获取当前调查员是否是主调查员 是否紧急案件
            if ("dcy-list".equals(menuCode)){
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyAssorgCaseId(caseDto.getSurveyAssorgCaseId());
                if (cases.size() > 1) {
                    cases.sort(Comparator.comparing(SurveyInvestigatorCase :: getAssignDate));
                    if (caseDto.getId().intValue() == cases.get(0).getId().intValue()){
                        caseDto.setShowKey(true);
                    }
                }
                String caseEndTime = DateUtils.DateToStr(caseDto.getSurveyRiskCaseInfo().getEndTime(),"yyyyMMdd");
                String surveyEndTime = DateUtils.DateToStr(caseDto.getSurveyEndTime(),"yyyyMMdd");
                if (caseEndTime.compareTo(surveyEndTime) < 0) {
                    caseDto.setUrgent(true);
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(needLogin = false,descript = "调查员查看案件详情",value = "info-survey-investigator-case")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        String btnCode = apiRequest.getString("btnCode");
        String menuCode = apiRequest.getString("menuCode");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        if ("feeViewSurvey".equals(menuCode) && apiRequest.getLong("surveyInfoId") != null){//费用报销查看案件信息。
            Map<String,Object> map =  new HashMap<String,Object>();
            map.put("surveyInfoId",apiRequest.getLong("surveyInfoId"));
            map.put("surveyUserId",apiRequest.getLong("surveyUserId"));
            SurveyInvestigatorCase one = surveyInvestigatorCaseMapper.getSurveyInvestigatorCaseByOne(map);
            id = one.getId();
        }
        SurveyInvestigatorCaseDto dto = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
        if (dto.getSurveyUserId() != null){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(dto.getSurveyUserId());
            if (surveyInvestigator != null) {
                if (surveyInvestigator.getHaveNwAccount() == null){
                    dto.setHaveNwAccount(0);
                }else{
                    dto.setHaveNwAccount(surveyInvestigator.getHaveNwAccount());
                }
            }
        }
        try {
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(dto.getSurveyId());
            SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(dto.getSurveyInfoId());
            dto.setRiskHandleId(surveyRiskCaseInfo.getHandleId());
            //查询案件打卡次数
            Map findMap=new HashMap();
            findMap.put("surveyInfoId",surveyRiskCaseInfo.getId());
            Integer punchClockCount=surveyClockCaseMapper.selectPunchClockCount(findMap);
            surveyRiskCaseInfo.setPunchClockCount(punchClockCount);
            dto.setSurveyRiskCase(surveyRiskCase);
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCase.getEntrustOrgId());
            surveyRiskCaseInfo.setSurveyConsignor(surveyConsignor);
            dto.setSurveyRiskCaseInfo(surveyRiskCaseInfo);
            //匹配机构模板
            if ("dcy-list".equals(menuCode)){
                dto.setZhongan(surveyConsignor.getId().intValue() == 94 ? true : false);
                //文件数量
//                List<SurveyFileInfoDTO> files = surveyCaseFileMapper.getSurveyFileInfo(dto.getSurveyInfoId());
                Map<String,Long> map =  new HashMap<String,Long>();
                map.put("surveyInfoId",dto.getSurveyInfoId());
                List<SurveyCaseFileDto> files = surveyCaseFileMapper.list(map);
                dto.setFileSize(files.size());

                SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(surveyRiskCase.getEntrustOrgId());
                if(surveyConsignorModel!=null){
                    surveyRiskCase.setModelId(surveyConsignorModel.getModelId());
                }
                //如果是“中宏模板”，需判断 是否使用过“介绍信”
                if(surveyRiskCase.getModelId() != null && surveyRiskCase.getModelId() == 4){
                    if (surveyRiskCaseInfo.getUseLetterInfo() == null){
                        surveyRiskCaseInfo.setUseLetterInfo(0);
                    }
                    if(surveyRiskCaseInfo.getUseLetterInfo() == 0){
                        dto.setIsUserLetterInfo(false);
                    }else{
                        dto.setIsUserLetterInfo(true);
                    }
                }
            }

            //案件关联任务列表taskRemark
            dto.setSurveyTaskTypes(surveyTaskTypeMapper.getSurveyTaskTypeBySurveyInfoId(dto.getSurveyInfoId()));
            //如果是主调查员
            if (dto.getSurveyUserType() == 1 && !"report-list".equals(menuCode) && !"report-review-list".equals(menuCode)){
                dto.setCommonFile(commonFileMapper.selectByPrimaryKey(dto.getSurveyRiskCaseInfo().getReportId()));
            }else{
                dto.setCommonFile(commonFileMapper.selectByPrimaryKey(dto.getCreportId()));
            }

//            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(dto.getSurveyUserId());
            //案件管理 已分配任务类型列表
            List<SurveyInvestigatorCaseType> tasks = surveyInvestigatorCaseTypeMapper.getSurveyInvestigatorCaseTypesByCaseId(dto.getId());//根据调查员案件Id关联任务列表
            dto.setTasks(tasks);

            //任务详情
            List<SurveyInvestigatorCase> surveyInvestigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(dto.getSurveyInfoId());
            List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtos = new ArrayList<>();
            for (SurveyInvestigatorCase surveyInvestigatorCase : surveyInvestigatorCases) {
                SurveyInvestigatorCaseDto caseDto = new SurveyInvestigatorCaseDto();
                BeanUtils.copyProperties(caseDto,surveyInvestigatorCase);
                caseDto.setTasks(surveyInvestigatorCaseTypeMapper.getSurveyInvestigatorCaseTypesByCaseId(surveyInvestigatorCase.getId()));
                caseDto.setCommonFile(new CommonFile());
                caseDto.setSurveyFranchisee(surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyOrgId()));
                surveyInvestigatorCaseDtos.add(caseDto);
            }
            dto.setSurveyInvestigatorCases(surveyInvestigatorCaseDtos);
            //显示费用报销数值
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(dto.getSurveyOrgId());
            dto.setShowExpenseReimbursementValue(surveyConsignor.getOrgAttr() == 2 && surveyFranchisee.getType() == 1);
            dto.setShowBaoSi(surveyConsignor.getOrgAttr() == 1 && surveyFranchisee.getInsuranceType() == 1);
            Integer reState = investigatorReDetailsMapper.selectReStateStrByInvCaseId(dto.getId());
            InvestigatorPreDetails investigatorPreDetails = investigatorPreDetailsMapper.selectByInvCaseId(dto.getId());
            if (investigatorPreDetails != null){//如果有预报销 直接不可编辑
                dto.setReState(6);
            }else {
                dto.setReState(reState);//没有预报销 报销状态>1（待提交发票）情况下 不可编辑
            }
            //调查方向列表
            Map map = new HashMap();
            if ("report-list".equals(menuCode) || "cash-info".equals(menuCode) || "dcy-list".equals(menuCode) || "sign-list".equals(menuCode) || "feeViewSurvey".equals(menuCode))
            {
                map.put("surveyInvestigatorCaseId",dto.getId());// 机构初审  只查询调查员案件关联的方向
                map.put("orderByCondition",1);
            }else{
                map.put("surveyInfoId",dto.getSurveyInfoId());//  子案件关联的方向
                map.put("orderByCondition",1);
            }

            if(!"direction".equals(btnCode)){
                List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.list(map);
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("orgAttr",surveyConsignor.getOrgAttr());
                String taskIds = surveyCaseDirections.stream().filter(p -> p.getTaskId() != null).map(e -> e.getTaskId().toString()).collect(Collectors.joining(","));
                paramMap.put("taskIds", StringUtils.isEmpty(taskIds) ? null : taskIds);
                List<SurveyTaskDirectionResult> results = surveyTaskInfoMapper.getResults(paramMap);

                List<SurveyCaseDirectionDto> surveyCaseDirectionDtos = new ArrayList<>();
                for (SurveyCaseDirection surveyCaseDirection : surveyCaseDirections) {
                    SurveyCaseDirectionDto surveyCaseDirectionDto = new SurveyCaseDirectionDto();
                    BeanUtils.copyProperties(surveyCaseDirectionDto,surveyCaseDirection);

                    List<SurveyTaskInfoContent> taskInfoContents = new ArrayList<>();//任务子类
                    List<SurveyTaskDirectionResult> collect = results.stream().filter(p -> surveyCaseDirection.getTaskId() != null  && p.getTaskId().intValue() == surveyCaseDirection.getTaskId().intValue()).collect(Collectors.toList());
                    Map<Long, List<SurveyTaskDirectionResult>> tempContents = collect.stream().collect(Collectors.groupingBy(SurveyTaskDirectionResult::getTaskInfoContentId));//任务类型对应的子类
                    tempContents.forEach((v1,v2) ->{
                        SurveyTaskInfoContent content = new SurveyTaskInfoContent();
                        content.setId(v1);
                        content.setName(v2.get(0).getTaskInfoContentName());
                        List<SurveyTaskDirectionResult> tempResults = results.stream().filter(p -> p.getTaskInfoContentId() != null && p.getTaskInfoContentId().intValue() == v1.intValue()).collect(Collectors.toList());//子类对应的结果
                        for (SurveyTaskDirectionResult tempResult : tempResults) {
                            if (tempResult.getScore() == null) {
                                tempResult.setScore(0D);
                            }
                            if (tempResult.getPointScore() == null){
                                tempResult.setPointScore(0D);
                            }
                        }
                        content.setTaskDirectionResults(tempResults);
                        taskInfoContents.add(content);
                        if (surveyCaseDirection.getNewId() != null){
                            if (v1.intValue() == surveyCaseDirection.getNewId().intValue()){
                                surveyCaseDirectionDto.setResultTypes(tempResults);
                            }
                        }
                    });
                    surveyCaseDirectionDto.setTaskInfoContents(taskInfoContents);

                    SurveyInvestigatorCaseDto surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
                    surveyInvestigatorCase.setSurveyFranchisee(surveyFranchiseeMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyOrgId()));
                    surveyCaseDirectionDto.setSurveyInvestigatorCase(surveyInvestigatorCase);
                    if (surveyCaseDirection.getHaveReimbursement() != null && surveyCaseDirection.getHaveReimbursement() == 1){
                        String totalMoney = surveyReimbursementInfoMapper.selectSurveyReimbursementInfoOneMoneyByDirectionId(surveyCaseDirection.getId());
                        surveyCaseDirectionDto.setExpenseReimbursementValue(totalMoney);
                    }else {
                        surveyCaseDirectionDto.setExpenseReimbursementValue("0");
                    }

                    //获取图片信息
                    Map mapFile = new HashMap();
                    mapFile.put("directionId",surveyCaseDirection.getId());
                    List<SurveyCaseDirectionFileDto> surveyCaseDirectionFileDtos = surveyCaseDirectionFileMapper.list(mapFile);
                    surveyCaseDirectionDto.setSurveyCaseDirectionFiles(surveyCaseDirectionFileDtos);

                    //获取任务类型颜色
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyCaseDirectionDto.getTaskId());
                    if(surveyTaskInfo != null){
                        surveyCaseDirectionDto.setTaskColor(surveyTaskInfo.getColor());
                    }

                    //获取任务子类颜色
                    SurveyTaskInfoContent surveyTaskInfoContent = surveyTaskInfoContentMapper.selectByPrimaryKey(surveyCaseDirectionDto.getNewId());
                    if(surveyTaskInfoContent != null){
                        surveyCaseDirectionDto.setNewColor(surveyTaskInfoContent.getColor());
                    }

                    //如果是互助机构
                    StringBuffer huzhuColsRemark = new StringBuffer();
                    if (surveyConsignor.getOrgAttr() == 2) {
                        String taskName = surveyCaseDirection.getTaskName();
                        if (taskName == null){
                            taskName = "";
                        }
                        switch (taskName)
                        {
                            case "面访患病成员及申请人":
                            case "面访患病成员家属":
                                if (surveyCaseDirection.getHuzhuDate() !=  null){
                                    huzhuColsRemark.append("面访时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                                }
                                huzhuColsRemark.append("\n面访对象与患病查员关系：" + surveyCaseDirection.getAttr1Obj());
                                huzhuColsRemark.append("\n面访对象姓名：" + surveyCaseDirection.getAttr1ObjName());
                                huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirection.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirection.getSunRemark()) : "否"));
                                break;
                            case "走访就诊医疗机构":
                            case "走访出生医疗机构":
                            case "居住地医疗机构排查":
                            case "工作地医疗机构排查":
                            case "出险地医疗机构排查":
                            case "户籍所在地医疗机构排查":
                            case "走访街道办事处或村委会":
                                if (surveyCaseDirection.getHuzhuDate() !=  null){
                                    huzhuColsRemark.append("排查时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                                }
                                huzhuColsRemark.append("\n排查类型：" + surveyCaseDirection.getAttr2Type());
                                huzhuColsRemark.append("\n排查科室：" + surveyCaseDirection.getAttr2His());
                                huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirection.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirection.getSunRemark()) : "否"));
                                break;
                            case "走访居住地":
                            case "走访户籍所在地":
                            case "走访工作单位":
                            case "走访疾控防疫中心":
                            case "走访公检法等机关单位":
                            case "走访鉴定机构":
                            case "走访材料出具机构":
                                if (surveyCaseDirection.getHuzhuDate() !=  null){
                                    huzhuColsRemark.append("走访时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                                }
                                huzhuColsRemark.append("\n走访对象：" + surveyCaseDirection.getAttr3Obj());
                                huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirection.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirection.getSunRemark()) : "否"));
                                break;
                            case "商保排查":
                                huzhuColsRemark.append("\n商保排查：" + surveyCaseDirection.getAttr4Name1());
                                huzhuColsRemark.append("\n商保排查结论：" + surveyCaseDirection.getAttr4Remark1());
                                huzhuColsRemark.append("\n互助排查：" + surveyCaseDirection.getAttr4Name2());
                                huzhuColsRemark.append("\n互助排查结论：" + surveyCaseDirection.getAttr4Remark2());
                                break;
                            case "社保排查":
                            case "药店排查":
                            case "体检机构排查": // 和 社保排查药店排查 。所有字段一样 所以写一起
                                if (surveyCaseDirection.getHuzhuDate() !=  null){
                                    huzhuColsRemark.append("排查时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                                }
                                huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirection.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirection.getSunRemark()) : "否"));
                                break;
                            case "事故地点排查":
                            case "事故处理机构排查": // 和 事故地点排查 。所有字段一样 所以写一起
                                if (surveyCaseDirection.getHuzhuDate() !=  null){
                                    huzhuColsRemark.append("排查时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                                }
                                break;
                            default:
                                break;
                        }
                        huzhuColsRemark.append("\n是否有录音：" + (surveyCaseDirection.getHaveSound() !=null?(surveyCaseDirection.getHaveSound() == 1 ? "是":"否"):""));
                        if (taskName.equals("居住地医疗机构排查") || taskName.equals("走访就诊医疗机构") || taskName.equals("工作地医疗机构排查") || taskName.equals("社保排查")
                                ||taskName.equals("体检机构排查") ||taskName.equals("商保排查") || taskName.equals("药店排查") || taskName.equals("出险地医疗机构排查") || taskName.equals("户籍所在地医疗机构排查")){
                            huzhuColsRemark.append("\n是否获得屏拍或纸质材料：" + (surveyCaseDirection.getHaveFile()!=null?(surveyCaseDirection.getHaveFile() == 1 ? "是":"否"):""));
                        }
                        surveyCaseDirectionDto.setHuzhuColsRemark(huzhuColsRemark.toString());
                    }
                    surveyCaseDirectionDto.setHuzhuSunStr(surveyCaseDirection.getSun() == 1 ? "是" : "否");
                    surveyCaseDirectionDtos.add(surveyCaseDirectionDto);
                }
                dto.setSurveyCaseDirections(surveyCaseDirectionDtos);

                DecimalFormat df = new DecimalFormat("#.00");
                Double totalSurveyPoints=0D;//计算的调查总积分
                Double positiveTotalScore=0D;//计算阳性积分
                for (SurveyCaseDirectionDto surveyCase:surveyCaseDirectionDtos) {
                    totalSurveyPoints=totalSurveyPoints+surveyCase.getScore();
                }

                if(dto.getOverdueAgingRate()!=null){
                    dto.setTotalSurveyPoints(Double.parseDouble(df.format(totalSurveyPoints*dto.getOverdueAgingRate())));
                    if(dto.getScoreSun()==null){
                        dto.setPositiveTotalScore(0D);
                    }else{
                        positiveTotalScore=dto.getScoreSun()*dto.getOverdueAgingRate();
                        dto.setPositiveTotalScore(Double.parseDouble(df.format(positiveTotalScore)));
                    }
                }else{
                    dto.setTotalSurveyPoints(Double.parseDouble(df.format(totalSurveyPoints)));
                    if(dto.getScoreSun()==null){
                        dto.setPositiveTotalScore(0D);
                    }else{
                        positiveTotalScore=dto.getScoreSun()*dto.getOverdueAgingRate();
                        dto.setPositiveTotalScore(Double.parseDouble(df.format(positiveTotalScore)));
                    }
                }
            }

            //查看机构调查属性
            SurveyConsignor surveyConsignorTwo=surveyConsignorMapper.selectByPrimaryKey(dto.getEntrustOrgId());
            SurveyFranchisee surveyFranchiseeTwo=surveyFranchiseeMapper.selectByPrimaryKey(dto.getSurveyOrgId());
            if (surveyConsignorTwo.getOrgAttr()==1) {//保险公司
                dto.setMechanismType(surveyFranchiseeTwo.getInsuranceType());
            }else if (surveyConsignorTwo.getOrgAttr()==2) {
                dto.setMechanismType(surveyFranchiseeTwo.getType());
            }


            SurveyAssignOrg surveyAssignOrg = null;
            //调查调查方机构表
            dto.setSurveyFranchisee(surveyFranchiseeMapper.selectByPrimaryKey(dto.getSurveyOrgId()));
            //机构案件信息
//            map =  new HashMap<String,Long>();
//            map.put("surveyOrgId",dto.getSurveyOrgId());
//            map.put("surveyInfoId",dto.getSurveyInfoId());
//            surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
            surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(dto.getSurveyAssorgCaseId());
            dto.setSurveyAssignOrg(surveyAssignOrg);

            //调查处理 且是 主调查员
            if ("dcy-list".equals(menuCode) && dto.getSurveyUserType() ==1){
                if (surveyAssignOrg != null){
                    dto.setCommonFile(commonFileMapper.selectByPrimaryKey(surveyAssignOrg.getReportId()));
                }
            }

            //协助调查案件列表
            if ("dcy-list".equals(menuCode)){
                //2019年11月13日  11点03分  不是互助案件 验证基础 是否都已填写
                if (surveyRiskCaseInfo.getSourceSupportType() != 3){
                    dto.setNullCode(basicInfo(surveyRiskCase,surveyRiskCaseInfo,dto));//获取基础信息 有NULL的编码
                }else{
                    dto.setNullCode("");
                }
                map = new HashMap();
                map.put("surveyCaseId",dto.getId());
                List<SurveyBackCaseDto> surveyBackCases = surveyBackCaseMapper.list(map);
                dto.setSurveyBackCases(surveyBackCases);

                map = new HashMap();
                map.put("surveyInfoId",dto.getSurveyInfoId());
                List<SurveyAssignOrgDto> orgs = surveyAssignOrgMapper.list(map);
                int successNum = 0;
                for (SurveyAssignOrgDto org : orgs) {
                    //计算已完成的数量
                    if (org.getOrgSurveyState() == 4 || org.getOrgSurveyState() == 3){
                        successNum = successNum + 1;
                    }
                }
                dto.setSuccessNum(successNum);
                dto.setAllNum(orgs.size());
                if (orgs.size() == 1){
                    int count = 0;//没有完成的数量
                    for (SurveyInvestigatorCaseDto aCase : dto.getSurveyInvestigatorCases()) {
                        if (aCase.getSurveyState() != 4 && aCase.getSurveyState() != 2 && aCase.getSurveyState() != 6){
                            count ++;
                        }
                    }
                }
                dto.setShowAddDirectionBtn(false);
                dto.setShowCommitBtn(false);
                dto.setShowOrgSummaryBtn(false);
                dto.setShowReportCompletionBtn(false);
                dto.setShowOrgCommitBtn(false);
                //控制显示按钮
                if (dto.getIsDirectionSuccess() == null){//默认为0
                    dto.setIsDirectionSuccess(0);
                }
                if (dto.getSurveyState() == 1 || (dto.getSurveyState() == 4 && dto.getIsDirectionSuccess() == 0)){//调查中
                    if (dto.getIsDirectionSuccess() == 0){
                        dto.setShowAddDirectionBtn(true);
                    } else if (dto.getIsDirectionSuccess() == 1){
                        Boolean isLast = true;// 当前机构下是最后一个调查员提交
                        apiRequest.clear();
                        apiRequest.put("surveyInfoId",dto.getSurveyInfoId());
                        apiRequest.put("surveyAssorgCaseId",dto.getSurveyAssorgCaseId());
                        List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(apiRequest);
                        for (SurveyInvestigatorCaseDto aCase : cases) {
                            if (aCase.getSurveyState() != 4 && aCase.getId().intValue() != dto.getId().intValue()){
                                isLast = false;
                                break;
                            }
                        }
                        if (!isLast){
                            dto.setShowCommitBtn(true);//不是最后一个显示提交审核
                        }else{
                            //是最后一个显示 填写机构小结
                            dto.setShowOrgSummaryBtn(true);
                            //如果是所有机构的最后一个 显示填写报告结论
                            Boolean isAllLast = true;//调查员的最后一个
                            apiRequest.clear();
                            apiRequest.put("surveyInfoId",dto.getSurveyInfoId());
                            cases = surveyInvestigatorCaseMapper.list(apiRequest);
                            for (SurveyInvestigatorCaseDto aCase : cases) {
                                if (aCase.getSurveyState() != 4 && aCase.getId().intValue() != dto.getId().intValue()){
                                    isAllLast = false;
                                    break;
                                }
                            }

                            //如果不是最后一个机构的调查员提交。则显示填写报告结论
//                            if (successNum != orgs.size() - 1) {
//                                isAllLast = false;
//                            }

                            if (isAllLast){
                                dto.setShowOrgSummaryBtn(false);
                                dto.setShowReportCompletionBtn(true);
                            }
                        }
                    }
                }
            }

            //如果是新案件。则更新
            if ("dcy-list".equals(menuCode)){
                if (dto != null && dto.getSurveyUserId() != null){
                    if (userInfo.getUserId().intValue() == dto.getSurveyUserId().intValue()){
                        if (dto.getNewCase() == 1){
                            dto.setNewCase(0);
                            surveyInvestigatorCaseMapper.updateByPrimaryKey(dto);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }

    @ApiMethod(needLogin = false,descript = "调查员提交报告审核",value = "operate-survey-investigator-case")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");//当为终审 修改方向价格 或 删除方向的时候 id 为 null
        String btnCode = apiRequest.getString("btnCode");
        String oprRemark = apiRequest.getString("oprRemark");
        if ("updSurveyTaskMoney".equals(btnCode) || "aging-rate".equals(btnCode) || "survey-task-remark".equals(btnCode)){//如果是乐凡修改调查员案件价格 则ID取调查员案件ID
            id = apiRequest.getLong("surveyCaseId");
        }
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
        //2019年3月15日 09点41分   提交审核 更改为审核通过，跳过初审；   所以把report-commit的逻辑换成 pass1 的逻辑
        if ("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX".equals(btnCode))
        {
            surveyInvestigatorCase.setSurveyState(3);//审核中（调查员案件）
            surveyInvestigatorCase.setSurveyStateName("审核中");
            surveyInvestigatorCase.setSurveyRemark(null);
        }
        else if ("pass".equals(btnCode)){
            surveyInvestigatorCase.setSurveyState(4);
            surveyInvestigatorCase.setSurveyStateName("已提交");
            surveyInvestigatorCase.setCreportDate(new Date());
            SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());

            int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
            if(surveyConsignor!=null){
                efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
            }
            SurveyUserPrescriptionFlow userPreFollow = surveyUserPrescriptionFlowMapper.selectByInvCaseIdLimitOne(surveyInvestigatorCase.getId());
            if (userPreFollow != null){
                Date endTime = new Date();
                userPreFollow.setEndTime(endTime);
                userPreFollow.setDays(Math.abs((double)GetWorkDay.calLeaveDays(userPreFollow.getStartTime(),endTime,efficiencyAttr)));
                surveyUserPrescriptionFlowMapper.updateByPrimaryKeySelective(userPreFollow);
            }

            //调查员时效
            double s = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(surveyInvestigatorCase.getSurveyInfoId(), surveyInvestigatorCase.getSurveyAssorgCaseId(), surveyInvestigatorCase.getId());
            surveyInvestigatorCase.setAgingDay(AgingDayUtil.surveyAgingDay(surveyInvestigatorCase,surveyConsignor.getEfficiencyAttr(),s));
            surveyInvestigatorCase.setAgingReal(s);
            surveyInvestigatorCase.setAgingOver(surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck()>0?surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck():0);

        }
        else if ("pass1".equals(btnCode) || "report-commit".equals(btnCode)){
            surveyInvestigatorCase.setCreportState(2);//已审核通过
            surveyInvestigatorCase.setSurveyState(4);
            surveyInvestigatorCase.setCreportDate(new Date());
            surveyInvestigatorCase.setSurveyStateName("已提交");
            surveyInvestigatorCase.setSurveyRemark(null);
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());

            int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
            if(surveyConsignor!=null){
                efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
            }
            SurveyUserPrescriptionFlow userPreFollow = surveyUserPrescriptionFlowMapper.selectByInvCaseIdLimitOne(surveyInvestigatorCase.getId());
            if (userPreFollow != null){
                Date endTime = new Date();
                userPreFollow.setEndTime(endTime);
                userPreFollow.setDays(Math.abs((double)GetWorkDay.calLeaveDays(userPreFollow.getStartTime(),endTime,efficiencyAttr)));
                surveyUserPrescriptionFlowMapper.updateByPrimaryKeySelective(userPreFollow);
            }

            //调查员时效
            double s = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(surveyInvestigatorCase.getSurveyInfoId(), surveyInvestigatorCase.getSurveyAssorgCaseId(), surveyInvestigatorCase.getId());
            surveyInvestigatorCase.setAgingDay(AgingDayUtil.surveyAgingDay(surveyInvestigatorCase,surveyConsignor.getEfficiencyAttr(),s));
            surveyInvestigatorCase.setAgingReal(s);
            surveyInvestigatorCase.setAgingOver(surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck()>0?surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck():0);
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
            surveyCaseWorkflowApi.addSurveyCaseWorkflow(surveyInvestigatorCase.getSurveyUserName().concat("调查案件"), userInfo, surveyInvestigatorCase.getAcceptDate(), new Date(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());

            List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
            Boolean last = true;
            for (SurveyInvestigatorCase aCase : cases) {
                if (aCase.getSurveyState() != 4){
                    last = false;
                }
            }
            if (last){
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                surveyAssignOrg.setOrgSurveyState(2);
                surveyAssignOrg.setOrgSurveyStateName("初审中");
                surveyAssignOrg.setOrgOpinion(null);
                surveyAssignOrg.setReviewTime(null);
                surveyAssignOrg.setReportDate(null);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);


                //
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("roleId",58L);
                paramMap.put("orgId",surveyAssignOrg.getSurveyOrgId());
                List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","机构初审");
                msgMap.put("content","你有调查员提交任务，请尽快进行初审！");
                msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()) + "\n"
                        + "调查员：" + surveyInvestigatorCase.getSurveyUserName());
                backendWechatApi.send(toUsers,msgMap);
            }

        }
        else if("huzhuCommit".equals(btnCode)){//互助的调查员提交
            SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
            String surveySummary = apiRequest.getString("surveySummary");
            surveyInvestigatorCase.setSurveySummary(surveySummary);
            surveyInvestigatorCase.setCreportState(2);//已审核通过
            surveyInvestigatorCase.setSurveyState(4);
            surveyInvestigatorCase.setCreportDate(new Date());
            surveyInvestigatorCase.setSurveyStateName("已提交");
            surveyInvestigatorCase.setSurveyRemark(null);
            String specialRemark = apiRequest.getString("specialRemark");
            surveyInvestigatorCase.setSpecialRemark(specialRemark);
            SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());

            int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
            if(surveyConsignor!=null){
                efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
            }
            SurveyUserPrescriptionFlow userPreFollow = surveyUserPrescriptionFlowMapper.selectByInvCaseIdLimitOne(surveyInvestigatorCase.getId());
            if (userPreFollow != null){
                Date endTime = new Date();
                userPreFollow.setEndTime(endTime);
                userPreFollow.setDays(Math.abs((double)GetWorkDay.calLeaveDays(userPreFollow.getStartTime(),endTime,efficiencyAttr)));
                surveyUserPrescriptionFlowMapper.updateByPrimaryKeySelective(userPreFollow);
            }

            //调查员时效
            double s = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(surveyInvestigatorCase.getSurveyInfoId(), surveyInvestigatorCase.getSurveyAssorgCaseId(), surveyInvestigatorCase.getId());
            surveyInvestigatorCase.setAgingDay(AgingDayUtil.surveyAgingDay(surveyInvestigatorCase,surveyConsignor.getEfficiencyAttr(),s));
            surveyInvestigatorCase.setAgingReal(s);
            surveyInvestigatorCase.setAgingOver(surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck()>0?surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck():0);
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
            Double agingRate = AgingDayUtil.surveyAgingRate(surveyInvestigatorCase,surveyAssignOrg,surveyFranchisee,surveyConsignor);
            surveyInvestigatorCase = agingRate(agingRate,surveyInvestigatorCase);

            //判断  是否存在阳性方向 2020/02/27
            Map<String,Object> map =  new HashMap<String,Object>();
            map.put("surveyInvestigatorCaseId",surveyInvestigatorCase.getId());
            List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
            Boolean sun = false;
            for (SurveyCaseDirection direction : directions) {
                if (direction.getSun() == 1) {
                    sun  = true;
                    break;
                }
            }
            if (sun){//如果方向存在阳性。 则整个调查员案件代表阳性
                surveyInvestigatorCase.setIsSun(1);
                surveyInvestigatorCase.setSunType(1);
                surveyInvestigatorCase.setSunTime(new Date());
                surveyInvestigatorCase.setSunUserId(userInfo.getUserId());
                surveyInvestigatorCase.setSunUserName(userInfo.getUserName());
            }


            //判断是否是当前机构的最后一个调查员提交 。 如果是 则机构变为初审中
            map =  new HashMap<String,Object>();
            map.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
            map.put("id",surveyInvestigatorCase.getId());
            map.put("surveyAssorgCaseId",surveyInvestigatorCase.getSurveyAssorgCaseId());
            Boolean last = true;
            List<SurveyInvestigatorCase> surveyInvestigatorCases = surveyInvestigatorCaseMapper.selectSurveyInvestigatorCasesByNoId(map);//该机构的其他调查员
            for (SurveyInvestigatorCase investigatorCase : surveyInvestigatorCases) {
                //除当前调查员 该机构只要有一个为调查中 说明就不是最后一个人
                if (investigatorCase.getSurveyState() == 1) {
                    last = false;
                    break;
                }
            }
            if (last){
                surveyAssignOrg.setOrgSurveyState(2);
                surveyAssignOrg.setOrgSurveyStateName("初审中");
                surveyAssignOrg.setOrgOpinion(null);
                surveyAssignOrg.setReportDate(null);
                surveyAssignOrg.setReview(null);
                double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
                surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
                surveyAssignOrg.setAgingReal(Math.abs(v));
                surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);


                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("roleId",58L);
                paramMap.put("orgId",surveyAssignOrg.getSurveyOrgId());
                List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","机构初审");
                msgMap.put("content","你有调查员提交任务，请尽快进行初审！");
                msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()) + "\n"
                        + "调查员：" + surveyInvestigatorCase.getSurveyUserName());
                backendWechatApi.send(toUsers,msgMap);
            }
            surveyCaseWorkflowApi.addSurveyCaseWorkflow(surveyInvestigatorCase.getSurveyUserName().concat("调查案件"), userInfo, surveyInvestigatorCase.getAcceptDate(), new Date(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());
        }
        else if("backPrimary".equals(btnCode)){//主调查员的退回
            Boolean b = true;//全部退回
            if (b){
                //属于改机构的所有调查员任务 更改为初审审核中
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                for (SurveyInvestigatorCase aCase : cases) {
                    if (surveyInvestigatorCase.getSurveyOrgId().intValue() == aCase.getSurveyOrgId().intValue() && aCase.getSurveyState() == 4){
                        aCase.setSurveyState(3);
                        aCase.setSurveyStateName("主调查员退回");
                        aCase.setSurveyRemark(oprRemark);
                        surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
                    }
                }
                //当前主调查员的案件也需要到初审
                surveyInvestigatorCase.setSurveyState(3);
                surveyInvestigatorCase.setSurveyStateName("主调查员退回");
                surveyInvestigatorCase.setSurveyRemark(oprRemark);
            }else{
                Long surveyCaseId = apiRequest.getLong("surveyCaseId");//选择调查员案件退回
                if (surveyCaseId.intValue() == surveyInvestigatorCase.getId().intValue()){//选择的退回案件就是当前主调查员的案件
                    surveyInvestigatorCase.setSurveyState(3);
                    surveyInvestigatorCase.setSurveyStateName("主调查员退回");
                    surveyInvestigatorCase.setSurveyRemark(oprRemark);
                }else{
                    SurveyInvestigatorCase aCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseId);
                    aCase.setSurveyState(3);
                    aCase.setSurveyStateName("主调查员退回");
                    aCase.setSurveyRemark(oprRemark);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
                }
            }
        }
        else if ("back".equals(btnCode)){
            if (surveyInvestigatorCase.getCreportId() == null){
                surveyInvestigatorCase.setCreportState(0);
            }else {
                surveyInvestigatorCase.setCreportState(1);
            }
            surveyInvestigatorCase.setSurveyRemark(oprRemark);
            surveyInvestigatorCase.setSurveyState(1);//业务按照调查中的逻辑去处理
            surveyInvestigatorCase.setSurveyStateName("调查中");
            surveyInvestigatorCase.setCreportDate(null);
            SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            //调查员时效
            double s = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(surveyInvestigatorCase.getSurveyInfoId(), surveyInvestigatorCase.getSurveyAssorgCaseId(), surveyInvestigatorCase.getId());
            surveyInvestigatorCase.setAgingDay(AgingDayUtil.surveyAgingDay(surveyInvestigatorCase,surveyConsignor.getEfficiencyAttr(),s));
            surveyInvestigatorCase.setAgingReal(s);
            surveyInvestigatorCase.setAgingOver(surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck()>0?surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck():0);
        }
        else if ("back1".equals(btnCode)){
            surveyInvestigatorCase.setSurveyState(3);//审核中（调查员案件）
            surveyInvestigatorCase.setSurveyStateName("审核中");
            surveyInvestigatorCase.setSurveyRemark(oprRemark);
        }
        else if ("sign".equals(btnCode)){
            //阳性案件(阳性案件审核状态：默认0 机构审核中2  平台审核中3  平台审核通过4， 机构退回5  平台退回6)
            String signType = apiRequest.getString("signType");
            if("survey-app".equals(signType)){
                surveyInvestigatorCase.setSunState(2);
                //给机构发送阳性案件审核通知

            }else if ("org-ok".equals(signType)){
                surveyInvestigatorCase.setSunState(3);
                //给平台发送阳性案件审核通知

            }else if ("lefan-ok".equals(signType)){
                surveyInvestigatorCase.setSunState(4);
                surveyInvestigatorCase.setIsSun(1);
                surveyInvestigatorCase.setSunTime(new Date());
                surveyInvestigatorCase.setSunType(2);
                surveyInvestigatorCase.setSunUserId(userInfo.getUserId());
                surveyInvestigatorCase.setSunUserName(userInfo.getUserName());
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                surveyRiskCaseInfo.setIsSun(1);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

                surveyRiskCaseInfoApi.score(surveyInvestigatorCase.getSurveyInfoId());


                SurveyRiskCaseInfoDto surveyRiskCaseInfoDto=surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                Double money=0D;
                if (surveyRiskCaseInfoDto.getServicesId()==11 || surveyRiskCaseInfoDto.getServicesId()==12) {
                    money=100D;
                }else if(surveyRiskCaseInfoDto.getServicesId()==13){
                    money=400D;
                }
                Map findMap=new HashMap();
                findMap.put("surveyInfoId",surveyRiskCaseInfoDto.getId());
                findMap.put("type",1);
                findMap.put("isSun",1);
                List<SurveyInvestigatorCase> surveyInvestigatorCaseShree=surveyInvestigatorCaseMapper.selectInformation(findMap);
                Integer count=surveyInvestigatorCaseShree.size();
                DecimalFormat df = new DecimalFormat("#.00");
                for (SurveyInvestigatorCase surveyInvestigatorCaseList:surveyInvestigatorCaseShree) {
//                    surveyInvestigatorCaseList.setSunMoney(Double.parseDouble(df.format(money/count)));
                    surveyInvestigatorCaseList.setSunMoney(0D);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCaseList);
                }


                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyId());
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","阳性案件通知");
                msgMap.put("content","你有案件发现阳性，请中止调查并于24小时之内提交审核，超时将不计积分！");
                msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson());
                backendWechatApi.send(surveyInvestigatorCase.getSurveyUserId(),msgMap);

            }else if ("org-back".equals(signType)){
                surveyInvestigatorCase.setSunState(5);
            }else if ("lefan-back".equals(signType)){
                surveyInvestigatorCase.setSunState(6);
            }else if ("lefan-quxiao".equals(signType)){
                surveyInvestigatorCase.setIsSun(0);
                surveyInvestigatorCase.setSunTime(null);
                surveyInvestigatorCase.setSunType(null);
                surveyInvestigatorCase.setSunUserId(null);
                surveyInvestigatorCase.setSunUserName(null);
                surveyInvestigatorCase.setSunMoney(0D);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

                SurveyRiskCaseInfoDto surveyRiskCaseInfoDto=surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                Double money=0D;
                if (surveyRiskCaseInfoDto.getServicesId()==11 || surveyRiskCaseInfoDto.getServicesId()==12) {
                    money=100D;
                }else if(surveyRiskCaseInfoDto.getServicesId()==13){
                    money=400D;
                }
                Map findMap=new HashMap();
                findMap.put("surveyInfoId",surveyRiskCaseInfoDto.getId());
                findMap.put("type",1);
                findMap.put("isSun",1);
                List<SurveyInvestigatorCase> surveyInvestigatorCaseShree=surveyInvestigatorCaseMapper.selectInformation(findMap);
                Integer count=surveyInvestigatorCaseShree.size();
                DecimalFormat df = new DecimalFormat("#.00");
                for (SurveyInvestigatorCase surveyInvestigatorCaseList:surveyInvestigatorCaseShree) {
//                    surveyInvestigatorCaseList.setSunMoney(Double.parseDouble(df.format(money/count)));
                    surveyInvestigatorCaseList.setSunMoney(0D);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCaseList);
                }
                surveyRiskCaseInfoApi.score(surveyInvestigatorCase.getSurveyInfoId());
                //查询该案件是否还有阳性记录
                Boolean have = false;
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getIsSun() == null){
                        aCase.setIsSun(0);
                    }
                    if (aCase.getIsSun() == 1){
                        have = true;
                        break;
                    }
                }
                if (!have){// 如果没有阳性记录
                    SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                    surveyRiskCaseInfo.setIsSun(0);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }
            }else if ("lefan-help-ok".equals(signType)){
                surveyInvestigatorCase.setIsSun(1);
                surveyInvestigatorCase.setSunTime(new Date());
                surveyInvestigatorCase.setSunType(2);
                surveyInvestigatorCase.setSunUserId(userInfo.getUserId());
                surveyInvestigatorCase.setSunUserName(userInfo.getUserName());
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

                //同步分值 以及 奖励分值 和 不计算的方向分值
                surveyRiskCaseInfoApi.score(surveyInvestigatorCase.getSurveyInfoId());

                //同步案件：是否阳性
                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                surveyRiskCaseInfo.setIsSun(1);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

                //推送公众号
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyId());
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","阳性案件通知");
                msgMap.put("content","你有案件发现阳性，请中止调查并于24小时之内提交审核，超时将不计积分！");
                msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson());
                backendWechatApi.send(surveyInvestigatorCase.getSurveyUserId(),msgMap);

            }else if ("lefan-help-quxiao".equals(signType)){
                surveyInvestigatorCase.setIsSun(0);
                surveyInvestigatorCase.setSunTime(null);
                surveyInvestigatorCase.setSunType(null);
                surveyInvestigatorCase.setSunUserId(null);
                surveyInvestigatorCase.setSunUserName(null);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

                //同步分值 以及 奖励分值 和 不计算的方向分值
                surveyRiskCaseInfoApi.score(surveyInvestigatorCase.getSurveyInfoId());

                //同步案件：是否阳性 //查询该案件是否还有阳性记录
                Boolean have = false;
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getIsSun() == null){
                        aCase.setIsSun(0);
                    }
                    if (aCase.getIsSun() == 1){
                        have = true;
                        break;
                    }
                }
                if (!have){// 如果没有阳性记录
                    SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                    surveyRiskCaseInfo.setIsSun(0);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }


            }else if ("lefan-bs-quxiao".equals(signType)){
                surveyInvestigatorCase.setIsSun(0);
                surveyInvestigatorCase.setSunMoney(0d);
                surveyInvestigatorCase.setSunTime(null);
                surveyInvestigatorCase.setSunType(null);
                surveyInvestigatorCase.setSunUserId(null);
                surveyInvestigatorCase.setSunUserName(null);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

                Map map = new HashMap<>();
                map.put("surveyInvestigatorCaseId",surveyInvestigatorCase.getId());
                List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
                for (SurveyCaseDirection direction : directions) {
                    direction.setSun(0);
                    surveyCaseDirectionMapper.updateByPrimaryKey(direction);
                }

                //同步案件：是否阳性 //查询该案件是否还有阳性记录
                Boolean have = false;
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getIsSun() == null){
                        aCase.setIsSun(0);
                    }
                    if (aCase.getIsSun() == 1){
                        have = true;
                        break;
                    }
                }
                if (!have){// 如果没有阳性记录
                    SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                    surveyRiskCaseInfo.setIsSun(0);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }

                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                //同步阳性奖励
                double sunMoney = 0;
                if (surveyRiskCaseInfo.getServicesId() == 13) {//深度400
                    sunMoney = 400;
                }
                if (surveyRiskCaseInfo.getServicesId() == 11 || surveyRiskCaseInfo.getServicesId() == 12) {//单点100
                    sunMoney = 100;
                }
                List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtos = surveyInvestigatorCaseMapper.selectAllBySurveyId(surveyRiskCaseInfo.getSurveyId());
                surveyInvestigatorCaseDtos = surveyInvestigatorCaseDtos.stream().filter(e->e.getIsSun() == 1).collect(Collectors.toList());
                for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto : surveyInvestigatorCaseDtos) {
                    surveyInvestigatorCaseDto.setSunMoney(0D);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCaseDto);
                }

            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        else if ("resign".equals(btnCode)){
            surveyInvestigatorCase.setIsSun(0);
        }
        else if ("price".equals(btnCode)){
            Double surveyTaskMoney = apiRequest.getDouble("surveyTaskMoney");
            surveyInvestigatorCase.setSurveyTaskMoney(surveyTaskMoney);
        }
        else if ("losses".equals(btnCode)){
            Double surveryReLosses = apiRequest.getDouble("surveryReLosses");
            surveyInvestigatorCase.setSurveryReLosses(surveryReLosses);
        }
        else if ("deldirection".equals(btnCode)){//删除调查方向
            Long directionId = apiRequest.getLong("directionId");
            SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
            surveyCaseDirection.setDeleteFlag(1);
            surveyCaseDirection.setUpdateBy(userInfo.getUserName());
            surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInfoId());
            SurveyAccountLog surveyAccountLog=new SurveyAccountLog();
            if(surveyRiskCaseInfo.getEntrustMoney() != null && !surveyRiskCaseInfo.getEntrustMoney().equals("")){
                surveyAccountLog.setOldBasicPrice(surveyRiskCaseInfo.getEntrustMoney());
            }else{
                surveyAccountLog.setOldBasicPrice(0.00);
            }
            //如果还在调查中 删除的方向不插入记录表
            Boolean insert = true;
            if (surveyInvestigatorCase.getCreportDate() == null) {
                insert = false;
            }
            syncPrice(surveyRiskCaseInfo,userInfo,insert);
            surveyRiskCaseInfoApi.score(surveyRiskCaseInfo.getId());
            surveyAccountLog.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyAccountLog.setCodeType(Long.parseLong("1"));
            if(surveyRiskCaseInfo.getEntrustMoney() != null && !surveyRiskCaseInfo.getEntrustMoney().equals("")){
                surveyAccountLog.setNewBasicPrice(surveyRiskCaseInfo.getEntrustMoney());
            }else{
                surveyAccountLog.setNewBasicPrice(0.00);
            }
            if(surveyRiskCaseInfo.getEntrustReLosses() != null && !surveyRiskCaseInfo.getEntrustReLosses().equals("")){
                surveyAccountLog.setOldDePrice(surveyRiskCaseInfo.getEntrustReLosses());
                surveyAccountLog.setNewDePrice(surveyRiskCaseInfo.getEntrustReLosses());
            }else{
                surveyAccountLog.setOldDePrice(0.00);
                surveyAccountLog.setNewDePrice(0.00);
            }
            surveyAccountLog.setUpdateTime(new Date());
            surveyAccountLog.setUpdateBy(userInfo.getUserName());
            surveyAccountLogMapper.insert(surveyAccountLog);

            //查询该调查员案件，是否都不是阳性案件
            Map map = new HashMap<>();
            map.put("surveyInvestigatorCaseId",surveyCaseDirection.getSurveyInvestigatorCaseId());
            map.put("invalidState", 0);
            List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
            Boolean isSun = false;
            for (SurveyCaseDirection direction : directions) {
                if(direction.getSun() == 1){
                    isSun = true;break;
                }
            }
            surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
            //同时更新调查员案件阳性状态
            if(isSun){
                surveyInvestigatorCase.setIsSun(1);
            }else{
                surveyInvestigatorCase.setIsSun(0);
            }
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

            //同步案件：是否阳性 //查询该案件是否还有阳性记录
            Boolean have = false;
            List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
            for (SurveyInvestigatorCase aCase : cases) {
                if (aCase.getIsSun() == null){
                    aCase.setIsSun(0);
                }
                if (aCase.getIsSun() == 1){
                    have = true;
                    break;
                }
            }
            if (!have){// 如果没有阳性记录
                surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                surveyRiskCaseInfo.setIsSun(0);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
            }

            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        else if("updSunMoney".equals(btnCode)){
            Double updSunMoney = apiRequest.getDouble("updSunMoney");
            String updSunMoneyRemark = apiRequest.getString("updSunMoneyRemark");
            String beforeValue = surveyInvestigatorCase.getSunMoney() == null ? "" : surveyInvestigatorCase.getSunMoney().toString();
            surveyInvestigatorCase.setSunMoney(updSunMoney);
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

            SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
            record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
            record.setUpdAfterValue(surveyInvestigatorCase.getSunMoney() == null ? "" : surveyInvestigatorCase.getSunMoney().toString());
            if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                record.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                record.setUpdAttr("survey_investigator_case_sun_money" + surveyInvestigatorCase.getId());
                record.setUpdTime(new Date());
                record.setUpdRemark(updSunMoneyRemark);
                record.setUpdUserName(userInfo.getUserName());
                surveyAttrUpdRecordMapper.insert(record);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        else if ("updPricedirection".equals(btnCode)){//修改方向价格
            Long directionId = apiRequest.getLong("directionId");
            String updType = apiRequest.getString("updType");
            Double amount = 0D;
            Integer feeType = null;
            if ("1".equals(updType)){//调查方 价格更改
                Double newSurveyMoney = apiRequest.getDouble("surveyMoney".concat(directionId.toString()));
                String newSurveyMoneyRemark = apiRequest.getString("surveyMoneyRemark".concat(directionId.toString()));
                newSurveyMoney = newSurveyMoney == null ? 0D : newSurveyMoney;
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                String beforeValue = surveyCaseDirection.getSurveyMoney() == null ? "" : surveyCaseDirection.getSurveyMoney().toString();
                Double oldSurveyMoney = surveyCaseDirection.getSurveyMoney() == null ? 0D : surveyCaseDirection.getSurveyMoney();
                surveyCaseDirection.setSurveyMoney(newSurveyMoney);
                if (!newSurveyMoney.toString().equals(oldSurveyMoney.toString())){//新输入价格不等于原始价格 则改为修改价格
                    surveyCaseDirection.setSurveyPriceSource(3);
                    surveyCaseDirection.setOldSurveyMoney(oldSurveyMoney);
                }
                surveyCaseDirection.setSurveyMoneyRemark(newSurveyMoneyRemark);
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyCaseDirection.getSurveyMoney() == null ? "" : surveyCaseDirection.getSurveyMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_survey_money" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(newSurveyMoneyRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInfoId());
                syncPrice(surveyRiskCaseInfo,userInfo,true);
            }else if ("2".equals(updType)){//委托方 价格更改
                Double newEntrustMoney = apiRequest.getDouble("entrustMoney".concat(directionId.toString()));
                String newEntrustMoneyRemark = apiRequest.getString("entrustMoneyRemark".concat(directionId.toString()));
                newEntrustMoney = newEntrustMoney == null ? 0D : newEntrustMoney;
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                String beforeValue = surveyCaseDirection.getEntrustMoney() == null ? "" : surveyCaseDirection.getEntrustMoney().toString();
                Double oldEntrustMoney = surveyCaseDirection.getEntrustMoney() == null ? 0D : surveyCaseDirection.getEntrustMoney();
                surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
                surveyCaseDirection.setEntrustMoney(newEntrustMoney);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyCaseDirection.getEntrustMoney() == null ? "" : surveyCaseDirection.getEntrustMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_entrust_money" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(newEntrustMoneyRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }



                SurveyAccountLog surveyAccountLog=null;
                SurveyRiskCaseInfoDto surveyRiskCaseInfoDto=surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInfoId());
                if (!newEntrustMoney.toString().equals(oldEntrustMoney.toString())){//新输入价格不等于原始价格 则改为修改价格
                    if(surveyRiskCaseInfoDto != null){
                        surveyAccountLog=new SurveyAccountLog();
                        surveyAccountLog.setSurveyInfoId(surveyRiskCaseInfoDto.getId());
                        surveyAccountLog.setCodeType(Long.parseLong("1"));
                        if(surveyRiskCaseInfoDto.getEntrustMoney() != null && !surveyRiskCaseInfoDto.getEntrustMoney().equals("")){
                            surveyAccountLog.setOldBasicPrice(surveyRiskCaseInfoDto.getEntrustMoney());
                        }else{
                            surveyAccountLog.setOldBasicPrice(0.00);
                        }
                        if(surveyRiskCaseInfoDto.getEntrustReLosses() != null && !surveyRiskCaseInfoDto.getEntrustReLosses().equals("")){
                            surveyAccountLog.setOldDePrice(surveyRiskCaseInfoDto.getEntrustReLosses());
                            surveyAccountLog.setNewDePrice(surveyRiskCaseInfoDto.getEntrustReLosses());
                        }else{
                            surveyAccountLog.setOldDePrice(0.00);
                            surveyAccountLog.setNewDePrice(0.00);
                        }
                        surveyAccountLog.setUpdateTime(new Date());
                        surveyAccountLog.setUpdateBy(userInfo.getUserName());
                    }
                    surveyCaseDirection.setEntrustPriceSource(3);
                    surveyCaseDirection.setOldEntrustMoney(oldEntrustMoney);
                }
                surveyCaseDirection.setEntrustMoneyRemark(newEntrustMoneyRemark);
                //2019年6月25日 09点28分 增加需求 邓剑平 陈建华 李中祥的调查放价格  按照委托方价格的70%(深度案件) 或者 其他50% 计算
                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInfoId());
                Long surveyOrgId = surveyInvestigatorCase.getSurveyOrgId();
//                Map<String,Object> mappen = new HashMap();
//                mappen.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
//                mappen.put("surveyOrgId", surveyOrgId);
//                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(mappen);
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                Integer surveyPayType = surveyAssignOrg.getPayType();
                surveyCaseDirection.setSurveyMoney(userMoney(surveyInvestigatorCase,surveyRiskCaseInfo,surveyCaseDirection,surveyPayType,surveyAssignOrg.getServicesId()));
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                syncPrice(surveyRiskCaseInfo,userInfo,true,true);

                Map map=new HashMap();
                map.put("surveyInfoId",surveyRiskCaseInfoDto.getId());
                map.put("invalidState", 0);//无效方向
                List<SurveyCaseDirection> list = surveyCaseDirectionMapper.list(map);
                Double money=0.00;
                for (SurveyCaseDirection surveyCaseDirectionList:list) {
                    if(surveyCaseDirectionList.getEntrustMoney() != null && !surveyCaseDirectionList.getEntrustMoney().equals("")){
                        money=money+surveyCaseDirectionList.getEntrustMoney();
                    }
                }
                if(surveyAccountLog != null){
                    surveyAccountLog.setNewBasicPrice(money);
                    surveyAccountLogMapper.insert(surveyAccountLog);
                }
                surveyRiskCaseInfoDto.setEntrustMoney(money);
                surveyRiskCaseInfoDto.setEntrustOkPrice1(money);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfoDto);
            }else if ("3".equals(updType)){
                Double score = apiRequest.getDouble("score".concat(directionId.toString()));
                String scoreRemark = apiRequest.getString("scoreRemark".concat(directionId.toString()));
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                String beforeValue = surveyCaseDirection.getScore() == null ? "" : surveyCaseDirection.getScore().toString();
                surveyCaseDirection.setScore(score);
                surveyCaseDirection.setUpdateBy(userInfo.getUserName());
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                surveyRiskCaseInfoApi.score(surveyCaseDirection.getSurveyInfoId());


                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyCaseDirection.getScore() == null ? "" : surveyCaseDirection.getScore().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_score" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(scoreRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }



                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }else if ("4".equals(updType)){
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                surveyCaseDirection.setReviewOff(1);
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
            }else if ("5".equals(updType)){
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                surveyCaseDirection.setReviewOff(0);
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
            }else if ("6".equals(updType)){
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                String beforeValue = surveyCaseDirection.getChannelFeeCur() == null ? "" : surveyCaseDirection.getChannelFeeCur().toString();
                Double channelFee = apiRequest.getDouble("channelFee".concat(directionId.toString()));
                surveyCaseDirection.setChannelFeeCur(channelFee);
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                //如果已存在渠道费用是申请记录 则直接改金额。
                SurveyChannelCostNew surveyChannelCostNew = surveyChannelCostNewMapper.selectChannelCostNewByDirectionId(surveyCaseDirection.getId());
                if (surveyChannelCostNew != null){
                    if (surveyChannelCostNew.getIsProPay() == 0){
                        if (surveyCaseDirection.getChannelFeeCur() == null){
                            surveyCaseDirection.setChannelFeeCur(0D);
                        }
                        if (surveyCaseDirection.getChannelFeeSent() == null){
                            surveyCaseDirection.setChannelFeeSent(0D);
                        }
                        if (surveyCaseDirection.getChannelFeeCur() > surveyCaseDirection.getChannelFeeSent()){
                            surveyChannelCostNew.setState(5);//大于限额需要审核
                            surveyChannelCostNew.setReviewerTime(null);
                        }else{
                            surveyChannelCostNew.setState(3);//否则直接审核通过
                            surveyChannelCostNew.setReviewerTime(new Date());
                        }
                        surveyChannelCostNew.setChnannelMoney(channelFee);
                        surveyChannelCostNewMapper.updateByPrimaryKey(surveyChannelCostNew);
                    }
                }


                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyCaseDirection.getChannelFeeCur() == null ? "" : surveyCaseDirection.getChannelFeeCur().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_channel_fee_cur" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark("");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }


            }
            else if ("7".equals(updType)){ //标记为无效方向
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                surveyCaseDirection.setInvalidState(1);
                surveyCaseDirection.setUpdateBy(userInfo.getUserName());
                surveyCaseDirection.setHisScore(surveyCaseDirection.getScore());
                surveyCaseDirection.setScore(0D);
                surveyCaseDirection.setSurveyMoney(0D);
                surveyCaseDirection.setEntrustMoney(0D);
                surveyCaseDirection.setChannelFeeCur(0D);
                surveyCaseDirection.setChannelFeeSent(0D);
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInfoId());
                //标记为无效方向后，不计入分值，费用
                syncPrice(surveyRiskCaseInfo,userInfo,true);
                surveyRiskCaseInfoApi.score(surveyRiskCaseInfo.getId());


                //查询该调查员案件，是否都不是阳性案件
                Map map = new HashMap<>();
                map.put("surveyInvestigatorCaseId",surveyCaseDirection.getSurveyInvestigatorCaseId());
                map.put("invalidState", 0);
                List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
                Boolean isSun = false;
                for (SurveyCaseDirection direction : directions) {
                    if(direction.getSun() == 1){
                        isSun = true;break;
                    }
                }
                surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
                //同时更新调查员案件阳性状态
                if(isSun){
                    surveyInvestigatorCase.setIsSun(1);
                }else{
                    surveyInvestigatorCase.setIsSun(0);
                }
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

                //同步案件：是否阳性 //查询该案件是否还有阳性记录
                Boolean have = false;
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getIsSun() == null){
                        aCase.setIsSun(0);
                    }
                    if (aCase.getIsSun() == 1){
                        have = true;
                        break;
                    }
                }
                if (!have){// 如果没有阳性记录
                    surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                    surveyRiskCaseInfo.setIsSun(0);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }

            }else if ("8".equals(updType)){//取消 标记为无效方向
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                surveyCaseDirection.setInvalidState(0);
                surveyCaseDirection.setUpdateBy(userInfo.getUserName());
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInfoId());
                //取消标记为无效方向后，重新计入分值，费用
                syncPrice(surveyRiskCaseInfo,userInfo,true);
                surveyRiskCaseInfoApi.score(surveyRiskCaseInfo.getId());

                //查询该调查员案件，是否都不是阳性案件
                Map map = new HashMap<>();
                map.put("surveyInvestigatorCaseId",surveyCaseDirection.getSurveyInvestigatorCaseId());
                map.put("invalidState", 0);
                List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
                Boolean isSun = false;
                for (SurveyCaseDirection direction : directions) {
                    if(direction.getSun() == 1){
                        isSun = true;break;
                    }
                }
                surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
                //同时更新调查员案件阳性状态
                if(isSun){
                    surveyInvestigatorCase.setIsSun(1);
                }else{
                    surveyInvestigatorCase.setIsSun(0);
                }
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

                //同步案件：是否阳性 //查询该案件是否还有阳性记录
                Boolean have = false;
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getIsSun() == null){
                        aCase.setIsSun(0);
                    }
                    if (aCase.getIsSun() == 1){
                        have = true;
                        break;
                    }
                }
                if (!have){// 如果没有阳性记录
                    surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                    surveyRiskCaseInfo.setIsSun(0);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }

            }else if ("9".equals(updType)){ //标记阳性-- 针对保司案件
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                surveyCaseDirection.setSun(1);
                surveyCaseDirection.setUpdateBy(userInfo.getUserName());
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                //同时更新调查员案件阳性状态
                surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
                surveyInvestigatorCase.setIsSun(1);
                surveyInvestigatorCase.setSunTime(new Date());
//                surveyInvestigatorCase.setSunType(2);
                surveyInvestigatorCase.setSunUserId(userInfo.getUserId());
                surveyInvestigatorCase.setSunUserName(userInfo.getUserName());
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

                //同步案件：是否阳性
                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                surveyRiskCaseInfo.setIsSun(1);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

                //同步阳性奖励
                double sunMoney = 0;
                if (surveyRiskCaseInfo.getServicesId() == 13) {//深度400
                    sunMoney = 400;
                }
                if (surveyRiskCaseInfo.getServicesId() == 11 || surveyRiskCaseInfo.getServicesId() == 12) {//单点100
                    sunMoney = 100;
                }
                List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtos = surveyInvestigatorCaseMapper.selectAllBySurveyId(surveyRiskCaseInfo.getSurveyId());
                surveyInvestigatorCaseDtos = surveyInvestigatorCaseDtos.stream().filter(e->e.getIsSun() == 1).collect(Collectors.toList());
                for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto : surveyInvestigatorCaseDtos) {
                    surveyInvestigatorCaseDto.setSunMoney(0D);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCaseDto);
                }
            }else if("10".equals(updType)){ //取消阳性-- 针对保司案件
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                surveyCaseDirection.setSun(0);
                surveyCaseDirection.setUpdateBy(userInfo.getUserName());
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                //查询该调查员案件，是否都不是阳性案件
                Map map = new HashMap<>();
                map.put("surveyInvestigatorCaseId",surveyCaseDirection.getSurveyInvestigatorCaseId());
                map.put("invalidState", 0);
                List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
                Boolean isSun = false;
                for (SurveyCaseDirection direction : directions) {
                    if(direction.getSun() == 1){
                        isSun = true;break;
                    }
                }
                surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
                //同时更新调查员案件阳性状态
                if(isSun){
                    surveyInvestigatorCase.setIsSun(1);
                }else{
                    surveyInvestigatorCase.setIsSun(0);
                    surveyInvestigatorCase.setSunMoney(0d);//不是阳性 阳性奖励为0
                }
                surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

                //同步案件：是否阳性 //查询该案件是否还有阳性记录
                Boolean have = false;
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getIsSun() == null){
                        aCase.setIsSun(0);
                    }
                    if (aCase.getIsSun() == 1){
                        have = true;
                        break;
                    }
                }
                if (!have){// 如果没有阳性记录
                    SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                    surveyRiskCaseInfo.setIsSun(0);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }
                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
                //同步阳性奖励
                double sunMoney = 0;
                if (surveyRiskCaseInfo.getServicesId() == 13) {//深度400
                    sunMoney = 400;
                }
                if (surveyRiskCaseInfo.getServicesId() == 11 || surveyRiskCaseInfo.getServicesId() == 12) {//单点100
                    sunMoney = 100;
                }
                List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtos = surveyInvestigatorCaseMapper.selectAllBySurveyId(surveyRiskCaseInfo.getSurveyId());
                surveyInvestigatorCaseDtos = surveyInvestigatorCaseDtos.stream().filter(e->e.getIsSun() == 1).collect(Collectors.toList());
                for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto : surveyInvestigatorCaseDtos) {
                    surveyInvestigatorCaseDto.setSunMoney(0D);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCaseDto);
                }
            }else if ("15".equals(updType)){//标记为渠道费用
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                surveyCaseDirection.setHisScore(surveyCaseDirection.getScore());
                surveyCaseDirection.setScore(0D);
                surveyCaseDirection.setChannelType(1);
                SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyAssorgCaseId());
                SurveyChannelModelOrg surveyChannelModelOrg = surveyChannelModelOrgMapper.selectOne(surveyAssignOrg.getSurveyOrgId());
                if (surveyChannelModelOrg != null){
                    Map<String,Object> paramMap =  new HashMap<String,Object>();
                    paramMap.put("modelId",surveyChannelModelOrg.getModelId());
                    paramMap.put("taskId",surveyCaseDirection.getTaskId());
                    paramMap.put("taskContentId",surveyCaseDirection.getNewId());
                    paramMap.put("taskContentResutlId",surveyCaseDirection.getDirectionResultTypeId());
                    Integer areaId = null;
                    Integer areaType = surveyCaseDirection.getAreaType();
                    if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                        areaId = surveyCaseDirection.getCityId();
                    }else{
                        areaId = surveyCaseDirection.getDistrictId();
                    }
                    paramMap.put("areaId",areaId);
                    SurveyChannelModelInfo surveyChannelModelInfo = surveyChannelModelInfoMapper.selectByParam(paramMap);
                    if (surveyChannelModelInfo != null) {
                        surveyCaseDirection.setChannelFeeCur(surveyChannelModelInfo.getPrice());
                        surveyCaseDirection.setChannelFeeSent(surveyChannelModelInfo.getPrice());
                    }
                }
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                surveyRiskCaseInfoApi.score(surveyCaseDirection.getSurveyInfoId());
            }else if ("16".equals(updType)){//取消标记为渠道费用
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                surveyCaseDirection.setScore(surveyCaseDirection.getHisScore());
                surveyCaseDirection.setChannelFeeCur(0D);
                surveyCaseDirection.setChannelType(0);
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                surveyRiskCaseInfoApi.score(surveyCaseDirection.getSurveyInfoId());
            }
            else if ("20".equals(updType)){
                SurveyRiskCaseInfoDto surveyRiskCaseInfoDto = surveyRiskCaseInfoMapper.selectByPrimaryKey(directionId);//前端此处的directionId传的是案件的id
                String beforeValue = surveyRiskCaseInfoDto.getReportCompletion();
                surveyRiskCaseInfoDto.setReportCompletion(apiRequest.getString("reportCompletion"));
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfoDto);

                //同时修改对应机构的报告结论,最后一个调查员提交 对应的机构。
                SurveyInvestigatorCase temp = surveyInvestigatorCaseMapper.selectLastCommitInfo(surveyRiskCaseInfoDto.getId());
                SurveyAssignOrgDto surveyAssignOrgDto = surveyAssignOrgMapper.selectByPrimaryKey(temp.getSurveyAssorgCaseId());
                surveyAssignOrgDto.setOrgSummary(surveyRiskCaseInfoDto.getReportCompletion());
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrgDto);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfoDto.getReportCompletion() == null ? "" : surveyRiskCaseInfoDto.getReportCompletion());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyRiskCaseInfoDto.getId());
                    record.setUpdAttr("survey_risk_case_info_report_completion");
                    record.setUpdTime(new Date());
                    record.setUpdRemark("");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }else if ("hege".equals(updType)){
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                String beforeValue = getEvaluateName(surveyCaseDirection.getEvaluate());
                surveyCaseDirection.setEvaluate(1);
                surveyCaseDirection.setUpdateBy(userInfo.getUserName());
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(getEvaluateName(surveyCaseDirection.getEvaluate()));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_evaluate" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark("");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }else if ("you".equals(updType)){
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                String beforeValue = getEvaluateName(surveyCaseDirection.getEvaluate());
                surveyCaseDirection.setEvaluate(2);
                surveyCaseDirection.setUpdateBy(userInfo.getUserName());
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(getEvaluateName(surveyCaseDirection.getEvaluate()));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_evaluate" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark("");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }else if ("cha".equals(updType)){
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                String beforeValue = getEvaluateName(surveyCaseDirection.getEvaluate());
                surveyCaseDirection.setEvaluate(3);
                surveyCaseDirection.setUpdateBy(userInfo.getUserName());
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(getEvaluateName(surveyCaseDirection.getEvaluate()));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_evaluate" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark("");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }else if ("21".equals(updType)){
                SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
                String beforeValue = surveyCaseDirection.getDirectionText();
                surveyCaseDirection.setDirectionText(apiRequest.getString("directionText_" + surveyCaseDirection.getId()));
                surveyCaseDirection.setUpdateBy(userInfo.getUserName());
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyCaseDirection.getDirectionText());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_text" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark("");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        else if("updSurveyTaskMoney".equals(btnCode)){//修改任务价格
            Double oldSurveyTaskMoney = surveyInvestigatorCase.getSurveyTaskMoney() == null ? 0D : surveyInvestigatorCase.getSurveyTaskMoney();
            Double oldSurveryReLosses = surveyInvestigatorCase.getSurveryReLosses() == null ? 0D : surveyInvestigatorCase.getSurveryReLosses();
            Double surveyTaskMoney = apiRequest.getDouble("surveyTaskMoney".concat(id.toString()));
            Double surveryReLosses = apiRequest.getDouble("surveryReLosses".concat(id.toString()));
            surveyInvestigatorCase.setSurveyTaskMoney((surveyTaskMoney == null ? 0D : surveyTaskMoney));
            surveyInvestigatorCase.setSurveryReLosses((surveryReLosses == null ? 0D : surveryReLosses));
        }
        else if("punish".equals(btnCode)){//增加处罚记录
            SurveyPunish surveyPunish = new SurveyPunish();
            surveyPunish.setSurveyUserId(surveyInvestigatorCase.getSurveyUserId());
            surveyPunish.setSurveyUserName(surveyInvestigatorCase.getSurveyUserName());
            surveyPunish.setSurveyId(surveyInvestigatorCase.getSurveyId());
            surveyPunish.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
            surveyPunish.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyPunish.setRemark(apiRequest.getString("remark"));

            surveyPunish.setDealUserId(userInfo.getUserId());
            surveyPunish.setDealUserName(userInfo.getUserName());
            surveyPunish.setIsExec(0);
            surveyPunish.setCreateBy(userInfo.getUserName());
            surveyPunish.setCreateTime(new Date());
            surveyPunish.setDeleteFlag(0);
            surveyPunishMapper.insert(surveyPunish);
        }
        else if ("score".equals(btnCode)){//案件评分
            Long scoreLevel = apiRequest.getLong("scoreLevel");
            surveyInvestigatorCase.setScoreState(1);//已评分
            surveyInvestigatorCase.setScoreLevel(Integer.parseInt(scoreLevel.toString()));
            surveyInvestigatorCase.setScoreRemark(apiRequest.getString("scoreRemark"));
            Long num = 0L;
            if (scoreLevel == 1){
                num = 1000L;
            }else if (scoreLevel == 2){
                num = 300L;
            }else if (scoreLevel == 3){
                num = 0L;
                //默认加一条处罚记录
                SurveyPunish surveyPunish = new SurveyPunish();
                surveyPunish.setSurveyUserId(surveyInvestigatorCase.getSurveyUserId());
                surveyPunish.setSurveyUserName(surveyInvestigatorCase.getSurveyUserName());
                surveyPunish.setSurveyId(surveyInvestigatorCase.getSurveyId());
                surveyPunish.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                surveyPunish.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
                surveyPunish.setRemark("案件不合格（默认处罚）");

                surveyPunish.setDealUserId(userInfo.getUserId());
                surveyPunish.setDealUserName(userInfo.getUserName());
                surveyPunish.setIsExec(0);
                surveyPunish.setCreateBy(userInfo.getUserName());
                surveyPunish.setCreateTime(new Date());
                surveyPunish.setDeleteFlag(0);
                surveyPunishMapper.insert(surveyPunish);
            }
            if (num != 0){
                backendSurveyInvestigatorApi.addPrice(surveyInvestigatorCase.getSurveyUserId(),num,3,"案件评星",surveyInvestigatorCase.getId(),4);
            }
        }
        else if ("classic".equals(btnCode)){
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            surveyRiskCaseInfo.setIsClassic(1);
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
        }
        else if ("reclassic".equals(btnCode)){
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            surveyRiskCaseInfo.setIsClassic(0);
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
        }
        else if ("refuseYes".equals(btnCode)){//拒绝有效
            //2019年1月22日14点16分  拒绝有效，更改案件状态
            surveyInvestigatorCase.setSurveyState(2);
            surveyInvestigatorCase.setSurveyStateName("已拒绝");
            surveyInvestigatorCase.setAcceptDate(null);
            surveyInvestigatorCase.setDeleteFlag(0);//改
            surveyInvestigatorCase.setSurveyUserType(2);//把拒绝的案件改为辅助调查员
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

            //辅助机构调查员拒绝 。  如果其他人都已经审核通过 则调整最后一个人  填写机构小结;主机构调查员拒绝 。    如果其他人都已经审核通过  则调整最后一个人 填写报告结论
            //调查员拒绝 ： 如果除当前调查员案件  其他所有的调查员案件都审核通过 则  填写机构小结 或 报告结论
            ApiRequest old = new ApiRequest();
            old.clear();
            old.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
            old.put("org",surveyInvestigatorCase.getSurveyOrgId());
            List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(old);
            if (cases.size() == 0) {//如果删除之后 没有调查员任务 则将机构关联的案件ID清空 可删除。
//                Map map =  new HashMap<String,Long>();
//                map.put("surveyOrgId",surveyInvestigatorCase.getSurveyOrgId());
//                map.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
//                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                surveyAssignOrg.setSurveyInvestigatorCaseId(null);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
            }
            int count = 0;
            SurveyInvestigatorCase tempCase = null;
            for (SurveyInvestigatorCase aCase : cases) {
                if (aCase.getSurveyState() != 0 && aCase.getSurveyState() != 1){
                    count ++ ;
                    if (aCase.getId().intValue() != surveyInvestigatorCase.getId().intValue()){
                        tempCase = aCase;
                    }
                }
            }
            if (count == cases.size() && cases.size() > 1){
                if (tempCase != null){
                    tempCase.setSurveyState(1);
                    tempCase.setSurveyStateName("调查中");
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(tempCase);

//                    Map map =  new HashMap<String,Long>();
//                    map.put("surveyOrgId",surveyInvestigatorCase.getSurveyOrgId());
//                    map.put("surveyInfoId",tempCase.getSurveyInfoId());
//                    SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
                    SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                    SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(tempCase.getSurveyId());
                    //发送消息  填写报告结论 或 机构小结
                    //发送消息通知
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

                    String url = "/survey/case/sic/info?id=" + surveyInvestigatorCase.getId() + "&menuCode=dcy-list";
                    if (surveyAssignOrg.getOrgPrimaryType() == 1){
                        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),tempCase.getSurveyUserId(),tempCase.getSurveyUserName(),4,"填写报告结论通知",
                                "你有案件需要填写报告结论，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()),url);

                    }else {
                        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),tempCase.getSurveyUserId(),tempCase.getSurveyUserName(),4,"填写机构小结通知",
                                "你有案件需要填写机构小结，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()),url);
                    }
                }
            }


            //更新案件子表状态
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            //根据子表ID查询，所有调查员案件数量
            count = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId()).size() ;
            if (count == 1){
                surveyRiskCaseInfo.setSurveyStateName("调查中");
            }else if (count > 1){
                surveyRiskCaseInfo.setSurveyStateName("调查中");
            }
            surveyRiskCaseInfo.setAcceptState(3);
            surveyRiskCaseInfo.setSurveyState(12);
            if (surveyInvestigatorCase.getSurveyUserType() == 1){//主调查员拒接 ，则清空主表调查员信息。
                surveyRiskCaseInfo.setSurveyUserId(null);
                surveyRiskCaseInfo.setSurveyUserName(null);
            }
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            //同时生成处罚记录
            SurveyPunish surveyPunish = new SurveyPunish();
            surveyPunish.setSurveyUserId(surveyInvestigatorCase.getSurveyUserId());
            surveyPunish.setSurveyUserName(surveyInvestigatorCase.getSurveyUserName());
            surveyPunish.setSurveyId(surveyInvestigatorCase.getSurveyId());
            surveyPunish.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
            surveyPunish.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyPunish.setRemark("拒接案件处罚");

            surveyPunish.setDealUserId(userInfo.getUserId());
            surveyPunish.setDealUserName(userInfo.getUserName());
            surveyPunish.setIsExec(0);
            surveyPunish.setCreateBy(userInfo.getUserName());
            surveyPunish.setCreateTime(new Date());
            surveyPunish.setDeleteFlag(0);
            surveyPunishMapper.insert(surveyPunish);
        }
        else if ("refuseNo".equals(btnCode)){//拒绝无效
            surveyInvestigatorCase.setSurveyState(0);
            surveyInvestigatorCase.setSurveyStateName("拒绝无效");
            surveyInvestigatorCase.setSurveyRemark(oprRemark);
        }
        else if ("appHelpCase".equals(btnCode)){
            SurveyBackCase surveyBackCase = new SurveyBackCase();
            surveyBackCase.setSurveyId(surveyInvestigatorCase.getSurveyId());
            surveyBackCase.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
            surveyBackCase.setSurveyCaseId(surveyInvestigatorCase.getId());
            surveyBackCase.setAssOrgId(null);
            surveyBackCase.setAssOrgName(null);
            surveyBackCase.setAssSurveyUserId(null);
            surveyBackCase.setAssSurveyUserName(null);
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyOrgId());
            surveyBackCase.setOrgId(surveyFranchisee.getId());
            surveyBackCase.setOrgName(surveyFranchisee.getName());
            surveyBackCase.setSurveyUserId(surveyInvestigatorCase.getSurveyUserId());
            surveyBackCase.setSurveyUserName(surveyInvestigatorCase.getSurveyUserName());
            surveyBackCase.setBackState(1);
            surveyBackCase.setBackStateName("调查员发起协助调查审核中");
            surveyBackCase.setBackRemark(oprRemark);
            surveyBackCase.setOpinion(null);

            surveyBackCase.setCreateBy(userInfo.getUserName());
            surveyBackCase.setCreateTime(new Date());
            surveyBackCase.setUpdateBy(null);
            surveyBackCase.setUpdateTime(null);
            surveyBackCase.setDeleteFlag(0);
            surveyBackCaseMapper.insert(surveyBackCase);
        }
        else if ("orgReturn".equals(btnCode)){//机构退回 调查员案件
            if (surveyInvestigatorCase.getCreportId() == null){
                surveyInvestigatorCase.setCreportState(0);
            }else{
                surveyInvestigatorCase.setCreportState(1);
            }
            surveyInvestigatorCase.setIsDirectionSuccess(0);
            surveyInvestigatorCase.setSurveyState(1);
            surveyInvestigatorCase.setSurveyStateName("调查中");
            surveyInvestigatorCase.setSurveyRemark(oprRemark);
            surveyInvestigatorCase.setReturnState(1);//是退回案件
            surveyInvestigatorCase.setCreportDate(null);
            SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            //调查员时效
            double s = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(surveyInvestigatorCase.getSurveyInfoId(), surveyInvestigatorCase.getSurveyAssorgCaseId(), surveyInvestigatorCase.getId());
            surveyInvestigatorCase.setAgingDay(AgingDayUtil.surveyAgingDay(surveyInvestigatorCase,surveyConsignor.getEfficiencyAttr(),s));
            surveyInvestigatorCase.setAgingReal(s);
            surveyInvestigatorCase.setAgingOver(surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck()>0?surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck():0);
            //对应的机构案件状态变为 6 （部分调查员案件已退回）
//            Map map =  new HashMap<String,Long>();
//            map.put("surveyOrgId",surveyInvestigatorCase.getSurveyOrgId());
//            map.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
//            SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
            SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
            surveyAssignOrg.setOrgSurveyState(1);
            surveyAssignOrg.setOrgSurveyStateName("调查中");
            surveyAssignOrg.setReportDate(null);
            double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
            surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
            surveyAssignOrg.setAgingReal(Math.abs(v));
            surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);


            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyId());
            Map<String,Object> msgMap =  new HashMap<String,Object>();
            msgMap.put("title","初审退回");
            msgMap.put("content","你有任务初审退回，请尽快进行处理！");
            msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "初审人员：" + userInfo.getUserName()
                    + "\n" + "退回原因：" + oprRemark);
            backendWechatApi.send(surveyInvestigatorCase.getSurveyUserId(),msgMap);

            SurveyUserPrescriptionFlow prescriptionFlow = new SurveyUserPrescriptionFlow();
            prescriptionFlow.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
            prescriptionFlow.setSurveyAssignOrgId(surveyInvestigatorCase.getSurveyAssorgCaseId());
            prescriptionFlow.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            Date startTime = new Date();
            prescriptionFlow.setStartTime(startTime);
            prescriptionFlow.setOperateType(3);
            surveyUserPrescriptionFlowMapper.insert(prescriptionFlow);
            prescriptionFlow.setOperateType(2);
            surveyUserPrescriptionFlowMapper.insert(prescriptionFlow);

        }
        else if ("delCase".equals(btnCode)){//删除调查员任务
            surveyInvestigatorCase.setDeleteFlag(1);
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

            // 同时删除方向
            Map map = new HashMap<>();
            map.put("surveyInvestigatorCaseId",surveyInvestigatorCase.getId());
            List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
            for (SurveyCaseDirection direction : directions) {
                direction.setUpdateBy(userInfo.getUserName());
                direction.setDeleteFlag(1);
                surveyCaseDirectionMapper.updateByPrimaryKey(direction);
                //删除费用清单
                List<SurveyFeeDetails> surveyFeeDetails = surveyFeeDetailsMapper.getSurveyFeeDetailsByDirectionId(direction.getId());
                for (SurveyFeeDetails surveyFeeDetail : surveyFeeDetails) {
                    surveyFeeDetail.setDeleteFlag(1);
                    surveyFeeDetailsMapper.updateByPrimaryKey(surveyFeeDetail);
                }
            }

            //删除的调查员，是否归属“省级机构系列”，
            // 本人是子机构人员，并是案件中“省级机构系列”唯一机构：就会删除子机构，并新增为省级机构，不是唯一机构，仅仅删除子机构


            //辅助机构调查员拒绝 。  如果其他人都已经审核通过 则调整最后一个人  填写机构小结;主机构调查员拒绝 。    如果其他人都已经审核通过  则调整最后一个人 填写报告结论
            //调查员拒绝 ： 如果除当前调查员案件  其他所有的调查员案件都审核通过 则  填写机构小结 或 报告结论
            ApiRequest old = new ApiRequest();
            old.clear();
            old.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
            old.put("surveyAssorgCaseId",surveyInvestigatorCase.getSurveyAssorgCaseId());
            List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(old);
            int count = 0;
            SurveyInvestigatorCase tempCase = null;
            for (SurveyInvestigatorCase aCase : cases) {
                if (aCase.getSurveyState() != 0 && aCase.getSurveyState() != 1){
                    count ++ ;
                    if (aCase.getId().intValue() != surveyInvestigatorCase.getId().intValue()){
                        tempCase = aCase;
                    }
                }
            }
            if (count == cases.size()){
                if (tempCase != null){
                    SurveyInvestigatorCase investigatorCase = tempCase;
                    investigatorCase.setSurveyState(1);
                    investigatorCase.setSurveyStateName("调查中");
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(investigatorCase);
                    SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                    surveyAssignOrg.setOrgSurveyState(1);
                    surveyAssignOrg.setOrgSurveyStateName("调查中");
                    surveyAssignOrg.setReportDate(null);
                    surveyAssignOrg.setReviewTime(null);
                    surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                    SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(investigatorCase.getSurveyId());
                    //发送消息  填写报告结论 或 机构小结
                    //发送消息通知
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                    String url = "/survey/case/sic/info?id=" + surveyInvestigatorCase.getId() + "&menuCode=dcy-list";

                    //是否是最后一个机构
                    Map<String,Object> paramMap =  new HashMap<String,Object>();
                    paramMap.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
                    Boolean orgLast = true;
                    List<SurveyAssignOrgDto> assignOrgDtos = surveyAssignOrgMapper.selectByMap(paramMap);
                    for (SurveyAssignOrgDto assignOrgDto : assignOrgDtos) {
                        if (assignOrgDto.getOrgSurveyState() != 4 && assignOrgDto.getId().intValue() != surveyInvestigatorCase.getSurveyAssorgCaseId()) {
                            orgLast = false;
                        }
                    }
                    if (orgLast){
                        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),investigatorCase.getSurveyUserId(),investigatorCase.getSurveyUserName(),4,"填写报告结论通知",
                                "你有案件需要填写报告结论，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()),url);

                    }else {
                        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),investigatorCase.getSurveyUserId(),investigatorCase.getSurveyUserName(),4,"填写机构小结通知",
                                "你有案件需要填写机构小结，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()),url);
                    }
                }
            }
            //如果机构下  没有调查员任务则将 机构的分派状态变为 未分派
            Boolean isHaveCase = false;//没有调查员任务
            for (SurveyInvestigatorCase aCase : cases) {
                if (aCase.getDeleteFlag() == 0 && surveyInvestigatorCase.getId().intValue() != aCase.getId().intValue()){
                    isHaveCase = true;
                }
            }
            if (!isHaveCase){//如果机构
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                surveyAssignOrg.setSurveyInvestigatorCaseId(null);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
            }

            //删除调查员
            String progressDesc ="调查员：" +surveyInvestigatorCase.getSurveyUserName()+"\n"+
                    "删除原因：" + apiRequest.getString("orgOpinion");
            backendSurveyProgressApi.saveProgress(surveyInvestigatorCase.getSurveyId(),surveyInvestigatorCase.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"删除调查员",progressDesc);

            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyId());
            Map<String,Object> msgMap =  new HashMap<String,Object>();
            msgMap.put("title","案件取消");
            msgMap.put("content","你有任务被取消，请注意！");
            msgMap.put("keyWords","案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "操作人员：" + userInfo.getUserName());
            backendWechatApi.send(surveyInvestigatorCase.getSurveyUserId(),msgMap);

        }
        //1、调查员survey 操作退回（调查处理页面）2、机构org 操作退回（案件分派页面）
        else if("case-return".equals(btnCode)){
            String roleCode = apiRequest.getString("roleCode");

            if("survey".equals(roleCode)){
                SurveyInvestigatorCase dto = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);

                String reason = apiRequest.getString("reason");
                dto.setDeleteFlag(1);//改为已删除
                dto.setSurveyState(0);//改为0 表示 删除 退回。
                dto.setReturnState(1);
                dto.setSurveyRemark(reason);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(dto);
                //同时删除方向
                Map<String,Object> map = new HashMap<>();
                map.put("surveyInvestigatorCaseId",id);
                List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
                for (SurveyCaseDirection direction : directions) {
                    direction.setUpdateBy(userInfo.getUserName());
                    direction.setDeleteFlag(1);
                    surveyCaseDirectionMapper.updateByPrimaryKey(direction);
                }
                //如果该机构下是最后一个调查员点击 退回。  则将另外一个调查员改为调查中   （填写报告结论或机构小结）
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("surveyInfoId",dto.getSurveyInfoId());
                paramMap.put("surveyAssorgCaseId", dto.getSurveyAssorgCaseId());
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(paramMap);
                Boolean last = true;
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getSurveyState() != 4) {
                        last = false;
                        break;
                    }
                }
                if (cases.size() > 0 && last){//是当前机构的最后一个人 则将另外一个改为调查中 填写报告结论或机构小结
                    cases.get(0).setSurveyState(1);
                    cases.get(0).setSurveyStateName("调查中");
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(cases.get(0));
                }

                //修改机构案件的退回状态：调查员主动退回(0：否，1:是)
//                paramMap =  new HashMap<String,Object>();
//                paramMap.put("surveyInfoId",dto.getSurveyInfoId());
//                paramMap.put("surveyOrgId", dto.getSurveyOrgId());
//                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(paramMap);
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(dto.getSurveyAssorgCaseId());
                surveyAssignOrg.setSurveyReturn(1);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

                //增加进度 调查员退回
                String progressDesc ="调查员：" + dto.getSurveyUserName() +"\n"+
                        "退回原因：" + reason;
                backendSurveyProgressApi.saveProgress(dto.getSurveyId(),dto.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"调查员退回案件",progressDesc);

            }
            else if("org".equals(roleCode)){
                Long assignOrgId = apiRequest.getLong("assignOrgId");
                SurveyAssignOrg assignOrg = surveyAssignOrgMapper.selectByPrimaryKey(assignOrgId);

                String reason = apiRequest.getString("reason");
                assignOrg.setDeleteFlag(1);//改为已删除
                assignOrg.setOrgSurveyState(0);//改为0 表示 删除 退回。
                assignOrg.setOrgOpinion(reason);
                surveyAssignOrgMapper.updateByPrimaryKey(assignOrg);
                //同时删除调查员及方向
                Map<String,Object> paramMap =  new HashMap<String,Object>();
//                paramMap.put("surveyInfoId",assignOrg.getSurveyInfoId());
//                paramMap.put("surveyOrgId", assignOrg.getSurveyOrgId());
//                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(paramMap);
                ApiRequest apiRequestInfo = new ApiRequest();
                apiRequestInfo.put("surveyAssorgCaseId", assignOrgId);
                List<SurveyInvestigatorCaseDto> caseDtos = surveyInvestigatorCaseMapper.list(apiRequestInfo);
                for (SurveyInvestigatorCaseDto aCase : caseDtos) {
                    aCase.setDeleteFlag(1);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
                    Map<String,Object> map = new HashMap<>();
                    map.put("surveyInvestigatorCaseId",aCase.getId());
                    List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);
                    for (SurveyCaseDirection direction : directions) {
                        direction.setUpdateBy(userInfo.getUserName());
                        direction.setDeleteFlag(1);
                        surveyCaseDirectionMapper.updateByPrimaryKey(direction);
                    }
                }

                Boolean last = true;//是否是最后一个机构退回  ture 是   false 否
                Map<String,Object> map =  new HashMap<String,Object>();
                map.put("surveyInfoId",assignOrg.getSurveyInfoId());
                map.put("surveyOrgId", assignOrg.getSurveyOrgId());
                List<SurveyAssignOrg> orgs = surveyAssignOrgMapper.selectListBySurveyInfoIdAndNotOrgId(map);
                if (orgs.size() > 0) {//如果有其他机构
                    for (SurveyAssignOrg org : orgs) {
                        if (org.getOrgSurveyState() != 4) {
                            last = false;
                        }
                    }
                }

                //修改总案件的退回状态：机构主动退回(0：否，1:是)
                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(assignOrg.getSurveyInfoId());
                surveyRiskCaseInfo.setOrgReturn(1);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

                //增加进度 机构退回
                String progressDesc ="机构：" + assignOrg.getSurveyOrgName() +"\n"+
                        "退回原因：" + reason;
                backendSurveyProgressApi.saveProgress(assignOrg.getSurveyId(),assignOrg.getSurveyInfoId(),userInfo.getUserId(),userInfo.getUserName(),"机构退回案件",progressDesc);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        else if ("primary-veto".equals(btnCode)){
            List<SurveyPrimaryVetoDTO> vetos = JSONArray.parseArray(apiRequest.getString("vetos"),SurveyPrimaryVetoDTO.class);
            for (SurveyPrimaryVetoDTO veto : vetos) {
                if (veto.getType() == 1){//退回机构
                    SurveyAssignOrg item = surveyAssignOrgMapper.selectByPrimaryKey(veto.getId());
                    item.setOrgSurveyState(2);
                    item.setOrgSurveyStateName("初审中");
                    item.setReportDate(null);
                    SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(item.getSurveyInfoId());
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
                    double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(item.getSurveyInfoId(), item.getId());
                    item.setAgingDay(AgingDayUtil.orgAgingDay(item,surveyConsignor.getEfficiencyAttr(),v));
                    item.setAgingReal(v);
                    item.setAgingOver(item.getAgingReal()-item.getAgingCheck()>0?item.getAgingReal()-item.getAgingCheck():0);
                    surveyAssignOrgMapper.updateByPrimaryKey(item);
                }else if (veto.getType() == 2){//退回调查员  机构同时也变为调查中。
                    SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                    surveyAssignOrg.setOrgSurveyState(1);
                    surveyAssignOrg.setOrgSurveyStateName("调查中");
                    surveyAssignOrg.setReportDate(null);
                    SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
                    double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
                    surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
                    surveyAssignOrg.setAgingReal(Math.abs(v));
                    surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
                    surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

                    SurveyInvestigatorCase item = surveyInvestigatorCaseMapper.selectByPrimaryKey(veto.getId());
                    item.setSurveyState(1);
                    item.setSurveyStateName("调查中");
                    item.setSurveyRemark(veto.getOpinion());
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(item);
                }
            }
        }
        else if ("review-user-yes".equals(btnCode)){//预审通过
            surveyInvestigatorCase.setReviewOff(1);
        }
        else if ("review-user-cancel".equals(btnCode))
        {//取消预审
            surveyInvestigatorCase.setReviewOff(0);
        }
        else if ("revoke".equals(btnCode))
        {
            surveyInvestigatorCase.setIsDirectionSuccess(0);
        }
        else if ("aging-rate".equals(btnCode)){//超期考核绩效更改。
            Double agingRate = apiRequest.getDouble("agingRate" + surveyInvestigatorCase.getId()) == null ? 1D : apiRequest.getDouble("agingRate" + surveyInvestigatorCase.getId());
            String agingRateRemark = apiRequest.getString("agingRateRemark" + surveyInvestigatorCase.getId());
            String beforeValue = surveyInvestigatorCase.getOverdueAgingRate() == null ? "" : surveyInvestigatorCase.getOverdueAgingRate().toString();
            surveyInvestigatorCase = agingRate(agingRate,surveyInvestigatorCase);

            SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
            record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
            record.setUpdAfterValue(surveyInvestigatorCase.getOverdueAgingRate() == null ? "" : surveyInvestigatorCase.getOverdueAgingRate().toString());
            if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                record.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                record.setUpdAttr("survey_investigator_case_overdue_aging_rate" + surveyInvestigatorCase.getId());
                record.setUpdTime(new Date());
                record.setUpdRemark(agingRateRemark);
                record.setUpdUserName(userInfo.getUserName());
                surveyAttrUpdRecordMapper.insert(record);
            }
        }
        else if ("survey-task-remark".equals(btnCode)){
            String surveyTaskRemark = apiRequest.getString("surveyTaskRemark" + surveyInvestigatorCase.getId());
            String surveyTaskRemarkRemark = apiRequest.getString("surveyTaskRemarkRemark" + surveyInvestigatorCase.getId());
            String beforeValue = surveyInvestigatorCase.getSurveyTaskRemark() == null ? "" : surveyInvestigatorCase.getSurveyTaskRemark();
            surveyInvestigatorCase.setSurveyTaskRemark(surveyTaskRemark);

            SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
            record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
            record.setUpdAfterValue(surveyInvestigatorCase.getSurveyTaskRemark() == null ? "" : surveyInvestigatorCase.getSurveyTaskRemark());
            if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                record.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
                record.setUpdAttr("survey_investigator_case_survey_remark" + surveyInvestigatorCase.getId());
                record.setUpdTime(new Date());
                record.setUpdRemark(surveyTaskRemarkRemark);
                record.setUpdUserName(userInfo.getUserName());
                surveyAttrUpdRecordMapper.insert(record);
            }
        }
        else if ("selDirectionResult".equals(btnCode)){//选择 任务子类结果
            Long directionId = apiRequest.getLong("directionId");
            Long newId = apiRequest.getLong("newId");
            Long directionResultTypeId = apiRequest.getLong("directionResultTypeId");
            SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
            // 任务子类
            SurveyTaskInfoContent surveyTaskInfoContent = surveyTaskInfoContentMapper.selectByPrimaryKey(newId);
            if (surveyTaskInfoContent != null){
                surveyCaseDirection.setNewId(newId);
                surveyCaseDirection.setNewName(surveyTaskInfoContent.getName());
            }else{
                surveyCaseDirection.setNewId(null);
                surveyCaseDirection.setNewName(null);
            }
            //方向结果类型
            SurveyDirectionResultType type = surveyDirectionResultTypeMapper.selectByPrimaryKey(directionResultTypeId);
            if(type != null){
                surveyCaseDirection.setDirectionResultTypeId(directionResultTypeId);
                surveyCaseDirection.setDirectionResultTypeName(type.getName());
                surveyCaseDirection.setDirectionResultTypeCode(type.getCode());
            }else{
                surveyCaseDirection.setDirectionResultTypeId(null);
                surveyCaseDirection.setDirectionResultTypeName(null);
                surveyCaseDirection.setDirectionResultTypeCode(null);
            }
            Integer areaType = surveyCaseDirection.getAreaType();//城市类型（1、直辖市；2、省会；3、地级市；4、县级市）
            Integer areaId = null;
            if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                areaId = surveyCaseDirection.getCityId();
            }else{
                areaId = surveyCaseDirection.getDistrictId();
            }
            Integer entrustPayType = surveyRiskCaseInfo.getPayType();//委托方结算方式
            reloadDirection(surveyCaseDirection,surveyAssignOrg,surveyConsignor,surveyRiskCaseInfo,areaId,areaType,entrustPayType,surveyInvestigatorCase,userInfo,null,"","");
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        else if ("signOrgPoint".equals(btnCode) || "reOrgPoint".equals(btnCode)){//标记 指定方向 或  取消指定方向
            Long directionId = apiRequest.getLong("directionId");
            SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
            surveyCaseDirection.setOrgPoint("signOrgPoint".equals(btnCode) ? 1 : 0);
            //分值
            Map appendMap = new HashMap();
            appendMap.put("taskInfoContentId", surveyCaseDirection.getNewId());
            appendMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
            SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(appendMap);
            if(surveyTaskDirectionResult != null){
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                //获取分值系数 2021年3月30日
                Double scoreRate = 1D;
                SurveyScoreModelOrg surveyScoreModelOrg = surveyScoreModelOrgMapper.selectOne(surveyAssignOrg.getSurveyOrgId());
                if (surveyScoreModelOrg != null){
                    Map<String,Object> paramMap =  new HashMap<String,Object>();
                    paramMap.put("modelId",surveyScoreModelOrg.getModelId());
                    Integer areaType = surveyCaseDirection.getAreaType(),areaId;
                    if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                        areaId = surveyCaseDirection.getCityId();
                    }else{
                        areaId = surveyCaseDirection.getDistrictId();
                    }
                    paramMap.put("areaId",areaId);
                    SurveyScoreModelInfo surveyScoreModelInfo = surveyScoreModelInfoMapper.selectByParam(paramMap);
                    if (surveyScoreModelInfo != null) {
                        scoreRate = surveyScoreModelInfo.getScoreRate() == null ? 0D : surveyScoreModelInfo.getScoreRate();
                    }
                }
                Double score = surveyTaskDirectionResult.getScore();
                if (surveyCaseDirection.getOrgPoint() == null) {
                    surveyCaseDirection.setOrgPoint(0);
                }
                if (surveyCaseDirection.getOrgPoint() == 1){
                    score = surveyTaskDirectionResult.getPointScore();
                }
                score = (score == null) ? 0D : score;
                surveyCaseDirection.setScore(score * scoreRate);
                surveyCaseDirection.setAccScore(surveyTaskDirectionResult.getScore());
                surveyCaseDirection.setScoreRate(scoreRate);
            }else{
                surveyCaseDirection.setScore(null);
            }
            surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        else if ("valid".equals(btnCode)){
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("surveyInvestigatorCaseId",surveyInvestigatorCase.getId());
            List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.list(paramMap);
            for (SurveyCaseDirection surveyCaseDirection : surveyCaseDirections) {
                if (surveyCaseDirection.getNewId() == null || surveyCaseDirection.getDirectionResultTypeId() == null || surveyCaseDirection.getDirectionText() == null) {
                    return new ApiResponse(ApiMsgEnum.SUCCESS,1,false);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,true);
        }
        else {
            return new ApiResponse(ApiMsgEnum.ERROR_PARAMETER);
        }
        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    private String getEvaluateName(Integer evaluate){
        if (evaluate == null){
            evaluate = 1;
        }
        switch (evaluate){
            case 1 : return "合格";
            case 2 : return "优";
            case 3 : return "差";
        }
        return "合格";
    }

    /**
     * 根据比例计算调查员分值
     * @param agingRate
     * @param surveyInvestigatorCase
     * @return
     */
    private SurveyInvestigatorCase agingRate(Double agingRate,SurveyInvestigatorCase surveyInvestigatorCase){
        surveyInvestigatorCase.setOverdueAgingRate(agingRate);
        surveyInvestigatorCase.setScore((surveyInvestigatorCase.getAssessScore() == null ? 0D : surveyInvestigatorCase.getAssessScore()) * agingRate);
        surveyInvestigatorCase.setScoreSun((surveyInvestigatorCase.getAssessSunScore() == null ? 0D : surveyInvestigatorCase.getAssessSunScore()) * agingRate);

        surveyInvestigatorCase.setBsScore((surveyInvestigatorCase.getAssessBsScore() == null ? 0D : surveyInvestigatorCase.getAssessBsScore()) * agingRate);
        surveyInvestigatorCase.setOtherScore((surveyInvestigatorCase.getAssessOtherScore() == null ? 0D : surveyInvestigatorCase.getAssessOtherScore()) * agingRate);
        return surveyInvestigatorCase;
    }

    /**
     *
     * @param surveyRiskCase
     * @param surveyRiskCaseInfo
     * @param surveyInvestigatorCase
     * @return  "" 表示 基础信息完整  不为"" 表示不完整 不完整的CODE表示属性
     */
    private String basicInfo(SurveyRiskCase surveyRiskCase,SurveyRiskCaseInfoDto surveyRiskCaseInfo,SurveyInvestigatorCaseDto surveyInvestigatorCase){
        Long modelId = surveyRiskCase.getModelId();
        if (modelId != null) {
            if (modelId.intValue() == 1 || modelId.intValue() == 2){
                if (surveyRiskCase.getEntrustTime() == null){
                    return "NULL_ENTRUST_TIME";
                }
//                if (surveyRiskCase.getInsureTime() == null){
//                    return "NULL_INSURE_TIME";
//                }
//                if (surveyRiskCase.getDangerTime() == null){
//                    return "NULL_DANGER_TIME";
//                }
//                if (surveyRiskCase.getDangerAddress() == null){
//                    return "NULL_DANGER_ADDRESS";
//                }
                if (surveyRiskCaseInfo.getSurveyItem() == null){
                    return "NULL_SURVEY_ITEM";
                }
                if (surveyRiskCaseInfo.getSurveyInfo() == null){
                    return "NULL_SURVEY_INFO";
                }
                if (surveyRiskCase.getIdNumber() == null){
                    return "NULL_ID_NUMBER";
                }
//                if (surveyRiskCase.getInsureName() == null){
//                    return "NULL_INSURE_NAME";
//                }
            }else if (modelId.intValue() == 3){
                if (surveyRiskCase.getEntrustTime() == null){
                    return "NULL_ENTRUST_TIME";
                }
//                if (surveyRiskCaseInfo.getLefanReportDate() == null){
//                    return "NULL_LEFAN_REPORT_DATE";
//                }
                if (surveyRiskCase.getIdNumber() == null){
                    return "NULL_ID_NUMBER";
                }
                if(surveyRiskCase.getPolicyNo() == null){
                    return "NULL_POLICY_NO";
                }
                if (surveyRiskCase.getInsureTakeTime() == null){
                    return "NULL_INSURE_TAKE_TIME";
                }
//                if (surveyRiskCase.getDangerTime() == null){
//                    return "NULL_DANGER_TIME";
//                }
                if (surveyRiskCaseInfo.getSurveyItem() == null){
                    return "NULL_SURVEY_ITEM";
                }
            }else if (modelId.intValue() == 4){
                if(surveyRiskCase.getPolicyNo() == null){
                    return "NULL_POLICY_NO";
                }
                if (surveyRiskCase.getEntrustTime() == null){
                    return "NULL_ENTRUST_TIME";
                }
//                if (surveyRiskCase.getClaimsNo() == null){
//                    return "NULL_CLAIMS_NO";
//                }
                if (surveyRiskCaseInfo.getSurveyItem() == null){
                    return "NULL_SURVEY_ITEM";
                }
                if (surveyRiskCaseInfo.getSurveyInfo() == null){
                    return "NULL_SURVEY_INFO";
                }
            }else if (modelId.intValue() == 5){
                if (surveyRiskCaseInfo.getSurveyItem() == null){
                    return "NULL_SURVEY_ITEM";
                }
                if (surveyRiskCaseInfo.getSurveyInfo() == null){
                    return "NULL_SURVEY_INFO";
                }
            }
        }
        return "";
    }

    @ApiMethod(needLogin = false,descript = "调查员接收案件",value = "accept-survey-investigator-case")
    @Override
    public ApiResponse accept(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        //更新调查员案件表状态
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
        surveyInvestigatorCase.setSurveyState(1);
        surveyInvestigatorCase.setSurveyStateName("调查中");
        surveyInvestigatorCase.setAcceptDate(new Date());
        //计算调查方价格  分派的时候已经计算
//        if (surveyInvestigatorCase.getSurveryUserOrgType() == 1) {//自营调查员接收
//            surveyInvestigatorCase.setSurveyTaskMoney(0D);
//        }else if (surveyInvestigatorCase.getSurveryUserOrgType() == 2){//调查方调查员接收
//            if (surveyRiskCaseInfo.getPayType() == 1) {//一口价
//                surveyInvestigatorCase.setSurveyTaskMoney(surveyRiskCaseInfo.getSurveyMoney());
//            }else if (surveyRiskCaseInfo.getPayType() == 2){//基本费+减损奖励
//                surveyInvestigatorCase.setSurveyTaskMoney(surveyRiskCaseInfo.getSurveyMoney() + surveyRiskCaseInfo.getSurveryReLosses());
//            }else if (surveyRiskCaseInfo.getPayType() == 3){//任务点
//                surveyInvestigatorCase.setSurveyTaskMoney(0D);
//            }else if (surveyRiskCaseInfo.getPayType() == 4){//任务点+减损奖励
//                surveyInvestigatorCase.setSurveyTaskMoney(0D);
//            }
//        }
//        surveyInvestigatorCase.setEntrustTaskMoney(surveyRiskCaseInfo.getEntrustMoney());//委托方价格
        surveyInvestigatorCase.setSurveyState(1);
        surveyInvestigatorCase.setAcceptDate(new Date());
        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

        //查询所有子案件信息的调查员案件信息 判断是否还有调查员未接收
        List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
        Boolean a = false,b = false;//是否存在未接收案件,是否存在已拒绝案件
        for (SurveyInvestigatorCase aCase : cases) {
            if (aCase.getSurveyState() == 0){
                a = true;//说明有未接收
            }
            if (aCase.getSurveyState() == 2){
                b = true;//说明有已拒绝
            }
        }

        //是否全部接收   如果有未接收或者有已拒绝 那么就不是全部接收  否则就是全部接收
        Boolean isAllAccept = (a || b) == true ? false : true;//是true  否false
        if(isAllAccept){
            surveyRiskCaseInfo.setSurveyState(12);
            surveyRiskCaseInfo.setSurveyStateName("调查中");
            surveyRiskCaseInfo.setAcceptState(2);
        }else{
            //如果没有已拒绝案件  则验证是否有未接收案件 有则是部分接收
            if (!b){
                if (a){
                    surveyRiskCaseInfo.setSurveyState(14);
                    surveyRiskCaseInfo.setSurveyStateName("调查中");
                    surveyRiskCaseInfo.setAcceptState(1);
                }
            }
        }
//        surveyCaseWorkflowApi.addSurveyCaseWorkflow(userInfo.getUserName().concat("案件接收"),userInfo,surveyInvestigatorCase.getAssignDate(),new Date(),surveyRiskCaseInfo.getId(),surveyRiskCaseInfo.getSurveyId());
        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(needLogin = false,descript = "调查员拒绝案件",value = "refuse-survey-investigator-case")
    @Override
    public ApiResponse refuse(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        String oprRemark = apiRequest.getString("oprRemark");//拒绝原因
        //更新调查员案件表状态
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
        //2019年1月22日14点16分  拒绝案件需要 先审核 有效才是已拒绝状态。 否则还是待接收状态。
        if (true){
            surveyInvestigatorCase.setSurveyState(5);
            surveyInvestigatorCase.setSurveyStateName("拒接审核中");
            surveyInvestigatorCase.setSurveyRemark(oprRemark);
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(needLogin = false,descript = "上传报告",value = "upload-report-vaild-survey-investigator-case")
    @Override
    public ApiResponse uploadValidReport(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
        List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
        Boolean b = true;//验证所有调查员案件的报告是否已上传  未上传则不能上传主报告 true是  false否
        for (SurveyInvestigatorCase aCase : cases) {
            if(aCase.getCreportState() == 0){
                b = false;
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,b);
    }

    @ApiMethod(needLogin = false,descript = "上传报告",value = "upload-report-survey-investigator-case")
    @Override
    public ApiResponse uploadReport(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String btnCode = apiRequest.getString("btnCode");//primary 表示上传主报告
        SurveyInvestigatorCase surveyInvestigatorCase = null;
        SurveyRiskCaseInfo surveyRiskCaseInfo = null;
        String tsId = apiRequest.getString("tsId");
        if ("surveyInfoId".equals(tsId)){//子案件详情页面上传主报告  id 为 子案件的ID
            surveyInvestigatorCase = new SurveyInvestigatorCase();
            surveyInvestigatorCase.setSurveyUserType(1);// 模拟一个主调查员上传报告的逻辑
            surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(id);
        }else if ("orgAssignId".equals(tsId)){
            surveyInvestigatorCase = new SurveyInvestigatorCase();
            surveyInvestigatorCase.setSurveyUserType(1);// 模拟一个调查员上传报告的逻辑
        }else{
            surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
            surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
        }
        String path = apiRequest.getString("path");
        String fileRealName = apiRequest.getString("fileRealName");
        CommonFile commonFile = new CommonFile();
        commonFile.setFilePath(path);
        int first = path.lastIndexOf("/");//最后一个斜杠出现的位置
        int last = path.lastIndexOf(".");//最后一个点出现的位置
        commonFile.setFileName(path.substring(first + 1,last));
        commonFile.setCreateTime(new Date());
        if (surveyInvestigatorCase.getSurveyUserType() == 1 && "primary".equals(btnCode)){//如果是主调查员提交 且 是上传主报告 则更新字表报告上传
            //根据子案件ID，查询所有调查员案件列表
            List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
            Boolean b = true;//验证所有调查员案件的报告是否审核通过  未上传则不能上传主报告 true是  false否
            if (!"surveyInfoId".equals(tsId)){//如果是子页面上传主报告 则不验证
                for (SurveyInvestigatorCase aCase : cases) {
                    // 特殊机构可以不上床报告  2019年2月18日11点31分新增逻辑
                    SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyOrgId());
                    if (surveyFranchisee.getIsSpecial() == 2){//特殊机构
                        aCase.setCreportState(2);//按照报告已上传的逻辑处理
                    }
                    //如果当前循环的机构和主调查员所在机构相同 并且 报告未上传   则不可通过验证
                    Long curOrgId = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId()).getOrgId();
                    Long surveyCaseOrgId = surveyInvestigatorCase.getSurveyOrgId();
                    if(aCase.getCreportState() != 2 && curOrgId.intValue() == surveyCaseOrgId.intValue()){
                        b = false;
                    }
                }
            }
            if (!b){
                return new ApiResponse(ApiMsgEnum.HelpReportState);
            }
            commonFileMapper.insert(commonFile);

            //子案件详情上传主报告
            if ("surveyInfoId".equals(tsId)){
                //根据子案件ID，查询所有调查员案件列表
                surveyRiskCaseInfo.setReportId(commonFile.getId());
                surveyRiskCaseInfo.setReportName(fileRealName);
                surveyRiskCaseInfo.setReportState(1);
                surveyRiskCaseInfo.setReportDate(new Date());
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
            }else{//主调查员任务上传主报告
                //同步机构案件的主报告信息
                Map<String,Long> map =  new HashMap<String,Long>();
//                map.put("surveyOrgId",surveyInvestigatorCase.getSurveyOrgId());
//                map.put("surveyInfoId",surveyRiskCaseInfo.getId());
//                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                if (surveyAssignOrg != null){
                    surveyAssignOrg.setReportState(1);
                    surveyAssignOrg.setReportId(commonFile.getId());
                    surveyAssignOrg.setReportName(fileRealName);
//                    surveyAssignOrg.setReportDate(new Date());
                    surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                }

                map =  new HashMap<String,Long>();
                map.put("surveyInfoId",surveyRiskCaseInfo.getId());
                List<SurveyAssignOrgDto> surveyAssignOrgs = surveyAssignOrgMapper.list(map);
                //如果只有一个机构案件 则 子案件主报告为当前上传的主报告  否则清空子案件的主报告 由 乐凡终审合并上传主报告
                if (surveyAssignOrgs.size() == 1) {
                    surveyRiskCaseInfo.setReportId(commonFile.getId());
                    surveyRiskCaseInfo.setReportName(fileRealName);
                    surveyRiskCaseInfo.setReportState(1);
                    surveyRiskCaseInfo.setReportDate(new Date());
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }else{
                    surveyRiskCaseInfo.setReportId(null);
                    surveyRiskCaseInfo.setReportName(null);
                    surveyRiskCaseInfo.setReportState(0);
                    surveyRiskCaseInfo.setReportDate(null);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }
            }
        }else if ("orgPrimary".equals(btnCode)){
            commonFileMapper.insert(commonFile);
            SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(id);
            surveyAssignOrg.setReportId(commonFile.getId());
            surveyAssignOrg.setReportDate(new Date());
            surveyAssignOrg.setReportName(fileRealName);
            surveyAssignOrg.setReportState(1);
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
            surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
            surveyAssignOrg.setAgingReal(Math.abs(v));
            surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
        }else{
            commonFileMapper.insert(commonFile);
            surveyInvestigatorCase.setCreportId(commonFile.getId());
//            surveyInvestigatorCase.setCreportDate(new Date());
            surveyInvestigatorCase.setCreportName(fileRealName);
            surveyInvestigatorCase.setCreportState(1);
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(needLogin = false,descript = "增加方向/修改方向",value = "add-direction-survey-investigator-case")
    @Override
    public ApiResponse addDirection(ApiRequest apiRequest) {
        try {
            Map<String,Object> validateMap = new HashMap<>();
            validateMap.put("surveyInfoId",apiRequest.getString("surveyInfoId"));
            validateMap.put("directionName",apiRequest.getString("directionName"));
            Long directionId = apiRequest.getLong("directionId");//方向ID
            if (directionId != null && !"".equals(directionId)){
                validateMap.put("directionId",directionId);
            }
            List<SurveyCaseDirection> validateDirections = surveyCaseDirectionMapper.selectValidateDirectionName(validateMap);
            if (validateDirections.size() > 0){
                return new ApiResponse(ApiMsgEnum.SURVEY_VALIDATE_DIRECTIONNAME);
            }

            SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
            SurveyCaseDirection surveyCaseDirectionOld = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
            String feeOpr = apiRequest.getString("feeOpr");//费用清单 进入编辑方向
            if ("edit".equals(feeOpr)){//2020年4月20日  10点56分  新增逻辑
                Integer haveReimbursement = apiRequest.getString("haveReimbursement")!=null?Integer.valueOf( apiRequest.getString("haveReimbursement")):null;
                surveyCaseDirection.setHaveReimbursement(haveReimbursement);
                surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                HashMap<String,Object> verficationMap = this.verification(apiRequest);
                boolean flag = (boolean) verficationMap.get("flag");
                if (surveyCaseDirection.getHaveReimbursement() !=null){
                    if (surveyCaseDirection.getHaveReimbursement() == 1){
                        SurveyReimbursementInfo reimbursementInfo = surveyReimbursementInfoMapper.selectBySurveyDirectionId(surveyCaseDirection.getId());
                        //更新费用报销相关内容
                        SurveyReimbursementInfo surveyReimbursementInfo = (SurveyReimbursementInfo) verficationMap.get("surveyReimbursementInfo");
                        if (reimbursementInfo == null) {
                            if (flag) {
                                surveyReimbursementInfo.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                                surveyReimbursementInfo.setSurveyOrgId(surveyCaseDirection.getSurveyOrgId());
                                surveyReimbursementInfo.setSurveyOrgName(surveyCaseDirection.getSurveyOrgName());
                                surveyReimbursementInfo.setSurveyUserId(getCurrentUserId(apiRequest));
                                surveyReimbursementInfo.setSurveyUserName(surveyCaseDirection.getSurveyUserName());
                                surveyReimbursementInfo.setSurveyDirectionId(surveyCaseDirection.getId());
                                surveyReimbursementInfo.setInvestigatorCaseId(String.valueOf(surveyCaseDirection.getSurveyInvestigatorCaseId()));
                                surveyReimbursementInfoMapper.insert(surveyReimbursementInfo);
                                //添加费用报销附件
                                this.addReimbursementFiles(apiRequest, surveyReimbursementInfo);
                            }

                        } else {
                            surveyReimbursementInfo.setId(reimbursementInfo.getId());
                            surveyReimbursementInfoMapper.updateByPrimaryKeySelective(surveyReimbursementInfo);
                            //添加费用报销附件
                            this.addReimbursementFiles(apiRequest, surveyReimbursementInfo);
                        }
                    }else {
                        surveyReimbursementInfoMapper.deleteBySurveyDirectionId(surveyCaseDirection.getId());
                    }
                }
                //计算报销明细对应的案件金额
                InvestigatorReDetails investigatorReDetails = investigatorReDetailsMapper.selectDetailByInvestigatorCaseId(surveyCaseDirection.getSurveyInvestigatorCaseId());
                if (investigatorReDetails != null){
                    Double taotalMoney = surveyInvestigatorCaseMapper.selectAllDirectionReimTotalMoneyById(surveyCaseDirection.getSurveyInvestigatorCaseId());
                    investigatorReDetails.setInvestigatorReMoney(taotalMoney == null ? 0 : taotalMoney);
                    investigatorReDetailsMapper.updateByPrimaryKey(investigatorReDetails);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyCaseDirection);
            }
            Long id = apiRequest.getLong("id");
            Long taskId = apiRequest.getLong("taskId");
            Long newId = apiRequest.getLong("newId");
            Long currentUserId = getCurrentUserId(apiRequest);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
            String successDirection = apiRequest.getString("successDirection");

            if (apiRequest.containsKey("id")){
                apiRequest.remove("id");
            }
            if (surveyCaseDirection == null){
                surveyCaseDirection = ConvertToBeanUtil.toBean(apiRequest, SurveyCaseDirection.class);
            }else{
                surveyCaseDirection = ConvertToBeanUtil.toBean(apiRequest, surveyCaseDirection);
            }

            Integer areaType = surveyCaseDirection.getAreaType();//城市类型（1、直辖市；2、省会；3、地级市；4、县级市）
            Integer areaId = null;
            if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                areaId = surveyCaseDirection.getCityId();
            }else{
                areaId = surveyCaseDirection.getDistrictId();
            }
            //都保存省的ID
            surveyCaseDirection.setAreaId(surveyCaseDirection.getProvinceId());
            surveyCaseDirection.setAreaName((surveyCaseDirection.getProvince()==null?"":surveyCaseDirection.getProvince()) + (surveyCaseDirection.getCity()==null?"":surveyCaseDirection.getCity()) + (surveyCaseDirection.getDistrict()==null?"":surveyCaseDirection.getDistrict()));
            surveyCaseDirection.setSurveyInvestigatorCaseId(id);
            surveyCaseDirection.setTaskId(taskId);
            SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(taskId);
            surveyCaseDirection.setTaskName(surveyTaskInfo.getName());
            SurveyTaskInfoContent surveyTaskInfoContent = surveyTaskInfoContentMapper.selectByPrimaryKey(newId);
            if (surveyTaskInfoContent != null){
                surveyCaseDirection.setNewId(newId);
                surveyCaseDirection.setNewName(surveyTaskInfoContent.getName());
            }
            Map appendMap = new HashMap();
            appendMap.put("taskInfoContentId", surveyCaseDirection.getNewId());
            appendMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
            SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(appendMap);
            if(surveyTaskDirectionResult != null){
                //获取分值系数 2021年3月30日
                Double scoreRate = 1D;
                SurveyScoreModelOrg surveyScoreModelOrg = surveyScoreModelOrgMapper.selectOne(surveyAssignOrg.getSurveyOrgId());
                if (surveyScoreModelOrg != null){
                    Map<String,Object> paramMap =  new HashMap<String,Object>();
                    paramMap.put("modelId",surveyScoreModelOrg.getModelId());
                    areaType = surveyCaseDirection.getAreaType();
                    if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                        areaId = surveyCaseDirection.getCityId();
                    }else{
                        areaId = surveyCaseDirection.getDistrictId();
                    }
                    paramMap.put("areaId",areaId);
                    SurveyScoreModelInfo surveyScoreModelInfo = surveyScoreModelInfoMapper.selectByParam(paramMap);
                    if (surveyScoreModelInfo != null) {
                        scoreRate = surveyScoreModelInfo.getScoreRate() == null ? 0D : surveyScoreModelInfo.getScoreRate();
                    }
                }
                //如果是平台复审修改方向 方向分值不变  2021年4月23日。
                if (surveyAssignOrg.getOrgSurveyState() !=  4) {
                    Double score = surveyTaskDirectionResult.getScore();
                    if (surveyCaseDirection.getOrgPoint() == null) {
                        surveyCaseDirection.setOrgPoint(0);
                    }
                    if (surveyCaseDirection.getOrgPoint() == 1){
                        score = surveyTaskDirectionResult.getPointScore();
                    }
                    surveyCaseDirection.setScore(score * scoreRate);
                    surveyCaseDirection.setAccScore(score);
                }else{

                }
                surveyCaseDirection.setScoreRate(scoreRate);
            }else{
                surveyCaseDirection.setScore(null);
            }
            surveyCaseDirection.setSurveyId(surveyInvestigatorCase.getSurveyId());
            surveyCaseDirection.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
            //获取调查员的认证信息，得到调查员的机构ID  根据调查方机构ID和任务ID获取价格
            Long surveyOrgId = surveyInvestigatorCase.getSurveyOrgId();//调查方机构ID
            surveyCaseDirection.setSurveyOrgId(surveyInvestigatorCase.getSurveyOrgId());
            surveyCaseDirection.setSurveyOrgName(surveyInvestigatorCase.getSurveyOrgName());
            surveyCaseDirection.setSurveyPriceSource(0);
            surveyCaseDirection.setEntrustPriceSource(0);
            surveyCaseDirection.setDeleteFlag(0);
            //阳性 新增需求 2020年9月15日
            String isSun = apiRequest.getString("isSun");
            surveyCaseDirection.setSun(StringUtils.isEmpty(isSun) ? 0 : Integer.valueOf(isSun));
            Long regionType = apiRequest.getLong("regionType");//5市去 6郊区
            surveyCaseDirection.setRegionType(regionType == null ? null : regionType.intValue());
            Integer entrustPayType = surveyRiskCaseInfo.getPayType();//委托方结算方式
            //病例数量
            Integer medicalNumber = apiRequest.getInt("medicalNumber");
            surveyCaseDirection.setMedicalNumber(medicalNumber);
            String isUser = apiRequest.getString("isUser");
            Integer haveReimbursement = apiRequest.getString("haveReimbursement")!=null?Integer.valueOf( apiRequest.getString("haveReimbursement")):null;
            surveyCaseDirection.setHaveReimbursement(haveReimbursement);
            HashMap<String,Object> verficationMap = this.verification(apiRequest);
            boolean flag = (boolean) verficationMap.get("flag");

            //方向结果类型
            Long directionResultTypeId = surveyCaseDirection.getDirectionResultTypeId();
            SurveyDirectionResultType type = surveyDirectionResultTypeMapper.selectByPrimaryKey(directionResultTypeId);
            if(type != null){
                surveyCaseDirection.setDirectionResultTypeId(directionResultTypeId);
                surveyCaseDirection.setDirectionResultTypeName(type.getName());
                surveyCaseDirection.setDirectionResultTypeCode(type.getCode());
            }

            //新版互助方向转换数据(及委托方价格)
            if (surveyConsignor.getOrgAttr() == 2){//互助
                String attrCode = apiRequest.getString("attrCode");
                String huzhuDateStr = "",sunStr = "",sunRemark = "";
                switch (attrCode)
                {
                    case "attr1" :
                        huzhuDateStr = apiRequest.getString("attr1Date");
                        sunStr = apiRequest.getString("attr1Sun");
                        sunRemark = apiRequest.getString("attr1SunRemark");
                        surveyCaseDirection.setDirectionText(apiRequest.getString("attr1Remark"));
                        break;
                    case "attr2" :
                        huzhuDateStr = apiRequest.getString("attr2Date");
                        sunStr = apiRequest.getString("attr2Sun");
                        sunRemark = apiRequest.getString("attr2SunRemark");
                        surveyCaseDirection.setDirectionText(apiRequest.getString("attr2Remark"));
                        break;
                    case "attr3" :
                        huzhuDateStr = apiRequest.getString("attr3Date");
                        sunStr = apiRequest.getString("attr3Sun");
                        sunRemark = apiRequest.getString("attr3SunRemark");
                        surveyCaseDirection.setDirectionText(apiRequest.getString("attr3Remark"));
                        break;
                    case "attr4" :
                        break;
                    case "attr5" :
                        huzhuDateStr = apiRequest.getString("attr5Date");
                        sunStr = apiRequest.getString("attr5Sun");
                        sunRemark = apiRequest.getString("attr5SunRemark");
                        surveyCaseDirection.setDirectionText(apiRequest.getString("attr5Remark"));
                        break;
                    case "attr6" :
                        huzhuDateStr = apiRequest.getString("attr6Date");
                        sunStr = apiRequest.getString("attr6Sun");
                        sunRemark = apiRequest.getString("attr6SunRemark");
                        surveyCaseDirection.setDirectionText(apiRequest.getString("attr6Remark"));
                        break;
                    case "attr7":
                    case "attr8":
                        huzhuDateStr = apiRequest.getString("attr7Date");
                        surveyCaseDirection.setDirectionText(apiRequest.getString("attr7Remark"));
                        break;
                }
                if (!StringUtils.isEmpty(huzhuDateStr)){
                    Date huzhuDate = new SimpleDateFormat("yyyy-MM-dd").parse(huzhuDateStr);
                    surveyCaseDirection.setHuzhuDate(huzhuDate);
                }

                if (!StringUtils.isEmpty(sunStr)){
                    if ("是".equals(sunStr)) {
                        surveyCaseDirection.setSun(1);
                        surveyCaseDirection.setSunRemark(sunRemark);
                    }
                }
            }

            reloadDirection(surveyCaseDirection,surveyAssignOrg,surveyConsignor,surveyRiskCaseInfo,areaId,areaType,entrustPayType,surveyInvestigatorCase,userInfo,surveyCaseDirectionOld,successDirection,isUser);

            //保存该调查员的方向 “地址信息”历史记录
            saveDirectionAreaHistory(apiRequest,surveyCaseDirection);

            if (surveyConsignor.getOrgAttr() == 1 && "success".equals(successDirection)){
                //验证调查员除当前方向的所有打卡的方向内容是否都已经填写。
                //如果有未填写的 不可完成全部方向。 如果没有。则循环所有的打卡方向刷新价格。

                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("surveyUserCaseId",surveyInvestigatorCase.getId());
                paramMap.put("directionId",surveyCaseDirection.getId());
                List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.selectClockDirections(paramMap);
                List<SurveyCaseDirection> collect = surveyCaseDirections.stream().filter(p -> p.getDirectionText() == null).collect(Collectors.toList());
                if (collect.size() > 0){
                    surveyInvestigatorCase.setIsDirectionSuccess(0);
                }else{
                    for (SurveyCaseDirection itemDirection : surveyCaseDirections) {
                        areaType = itemDirection.getAreaType();//城市类型（1、直辖市；2、省会；3、地级市；4、县级市）
                        if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                            areaId = itemDirection.getCityId();
                        }else{
                            areaId = itemDirection.getDistrictId();
                        }
                        // 刷新打卡方向价格
                        reloadDirection(itemDirection,surveyAssignOrg,surveyConsignor,surveyRiskCaseInfo,areaId,areaType,entrustPayType,surveyInvestigatorCase,userInfo,itemDirection,successDirection,isUser);
                    }
                }
            }

            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
            surveyRiskCaseInfoApi.score(surveyCaseDirection.getSurveyInfoId());
            syncPrice(surveyRiskCaseInfo,userInfo,surveyInvestigatorCase.getCreportDate() == null ? false : true);

        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    public void reloadDirection(SurveyCaseDirection surveyCaseDirection,SurveyAssignOrg surveyAssignOrg,SurveyConsignor surveyConsignor,SurveyRiskCaseInfo surveyRiskCaseInfo,
                                Integer areaId,Integer areaType,Integer entrustPayType,SurveyInvestigatorCase surveyInvestigatorCase,UserInfo userInfo,SurveyCaseDirection surveyCaseDirectionOld,String successDirection,String isUser){
        //分值
        Map appendMap = new HashMap();
        appendMap.put("taskInfoContentId", surveyCaseDirection.getNewId());
        appendMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
        SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(appendMap);
        if(surveyTaskDirectionResult != null){
            //获取分值系数 2021年3月30日
            Double scoreRate = 1D;
            SurveyScoreModelOrg surveyScoreModelOrg = surveyScoreModelOrgMapper.selectOne(surveyAssignOrg.getSurveyOrgId());
            if (surveyScoreModelOrg != null){
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("modelId",surveyScoreModelOrg.getModelId());
                areaType = surveyCaseDirection.getAreaType();
                if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                    areaId = surveyCaseDirection.getCityId();
                }else{
                    areaId = surveyCaseDirection.getDistrictId();
                }
                paramMap.put("areaId",areaId);
                SurveyScoreModelInfo surveyScoreModelInfo = surveyScoreModelInfoMapper.selectByParam(paramMap);
                if (surveyScoreModelInfo != null) {
                    scoreRate = surveyScoreModelInfo.getScoreRate() == null ? 1D : surveyScoreModelInfo.getScoreRate();
                }
            }
            //如果是平台复审修改方向 方向分值不变  2021年4月23日。
            if (surveyAssignOrg.getOrgSurveyState() !=  4) {
                Double score = surveyTaskDirectionResult.getScore();
                if (surveyCaseDirection.getOrgPoint() == null) {
                    surveyCaseDirection.setOrgPoint(0);
                }
                if (surveyCaseDirection.getOrgPoint() == 1){
                    score = surveyTaskDirectionResult.getPointScore();
                }
                score = score == null ? 0D : score;
                surveyCaseDirection.setScore(score * scoreRate);
                surveyCaseDirection.setAccScore(surveyTaskDirectionResult.getScore());
            }else{

            }
            surveyCaseDirection.setScoreRate(scoreRate);
        }else{
            surveyCaseDirection.setScore(null);
        }

        //委托方价格
        surveyCaseDirection.setEntrustMoney(0D);
        surveyCaseDirection.setEntrustPriceSource(0);
        //调查方价格
        surveyCaseDirection.setSurveyMoney(0D);
        surveyCaseDirection.setSurveyPriceSource(0);

        SurveyFranchisee sf = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
        Integer surveyFranchiseeType = sf.getType();//互助的调查方类别
        if (surveyConsignor.getOrgAttr() == 1){
            surveyFranchiseeType = sf.getInsuranceType();//保司的调查方类别
        }
        Integer surveyPayType = surveyAssignOrg.getPayType();

        //根据具体的区域ID,及委托方   获取区域类别价格
        Map<String,Object> map = new HashMap();
        map.put("taskId",surveyCaseDirection.getTaskId());
        map.put("taskInfoContentId",surveyCaseDirection.getNewId());
        map.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
        map.put("entrustOrgId",surveyRiskCaseInfo.getEntrustOrgId());
        map.put("surveyOrgId",surveyAssignOrg.getSurveyOrgId());
        map.put("areaId",areaId);
        map.put("orgAttr",surveyConsignor.getOrgAttr());
        SurveyPrice surveyPrice = surveyPriceMapper.selectDirectionPriceNew(map);

        //获取核算价格
        if (surveyPrice != null){
            surveyCaseDirection.setAccMoney(surveyPrice.getTaskPrice());
        }else {
            surveyCaseDirection.setAccMoney(0D);
        }

        if (surveyPayType == 3 || surveyPayType == 4){
            if (surveyPrice != null) {
                surveyCaseDirection.setSurveyMoney(surveyPrice.getTaskPrice() == null ? 0D : surveyPrice.getTaskPrice());
                surveyCaseDirection.setSurveyPriceSource(1);
            }
            if(false){
                //获取调查员价格
                map = new HashMap();
                map.put("franchiseeId",surveyAssignOrg.getSurveyOrgId());
                map.put("taskId", surveyCaseDirection.getTaskId());
                map.put("areaId",surveyCaseDirection.getAreaId());
                if (areaType == 1){
                    map.put("cityType",surveyCaseDirection.getRegionType());
                }else{
                    map.put("cityType",areaType);
                    surveyCaseDirection.setRegionType(0);
                }
                map.put("taskInfoContentId", surveyCaseDirection.getNewId());
                map.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
                if(surveyConsignor != null){
                    map.put("priceType",surveyConsignor.getOrgAttr());
                }else{
                    map.put("priceType",1);//默认为“保险公司”
                }
                SurveyFranchiseePrice surveyFranchiseePrice = surveyFranchiseePriceMapper.selectInfo(map);
                if (surveyFranchiseePrice != null){
                    surveyCaseDirection.setSurveyMoney(surveyFranchiseePrice.getTaskPrice());
                    surveyCaseDirection.setSurveyPriceSource(1);
                }else{
                    map = new HashMap();
                    map.put("areaId",surveyCaseDirection.getAreaId());
                    map.put("taskId",surveyCaseDirection.getTaskId());
                    if (areaType == 1){
                        map.put("cityType",surveyCaseDirection.getRegionType());
                    }else{
                        map.put("cityType",areaType);
                    }
                    map.put("taskInfoContentId", surveyCaseDirection.getNewId());
                    map.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
                    if(surveyConsignor != null){
                        map.put("priceType",surveyConsignor.getOrgAttr());
                    }else{
                        map.put("priceType",1);//默认为“保险公司”
                    }
                    SurveyInvestigatorAreaPrice investigatorAreaPrice = surveyInvestigatorAreaPriceMapper.selectByInfo(map);
                    if (investigatorAreaPrice != null){
                        surveyCaseDirection.setSurveyPriceSource(2);
                        surveyCaseDirection.setSurveyMoney(investigatorAreaPrice.getPrice());
                    }
                }
            }
        }

        if (surveyConsignor.getOrgAttr() == 2){
            if (entrustPayType == 3 || entrustPayType == 4){//任务 或 任务+减；   基本费或一口价   转风控之前按照规则计算
                areaId = null;
                if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                    areaId = surveyCaseDirection.getCityId();
                }else{
                    areaId = surveyCaseDirection.getDistrictId();
                }
                //根据具体的区域ID,及委托方   获取区域类别价格
                map = new HashMap();
                map.put("taskId",surveyCaseDirection.getTaskId());
                map.put("taskInfoContentId",surveyCaseDirection.getNewId());
                map.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
                map.put("entrustOrgId",surveyRiskCaseInfo.getEntrustOrgId());
                map.put("areaId",areaId);
                map.put("orgAttr",surveyConsignor.getOrgAttr());
                surveyPrice = surveyPriceMapper.selectDirectionPrice(map);
                if (surveyPrice != null) {
                    surveyCaseDirection.setEntrustMoney(surveyPrice.getTaskPrice() == null ? 0D : surveyPrice.getTaskPrice());
                }
                surveyCaseDirection.setEntrustPriceSource(1);
            }
        }


        if (surveyConsignor.getOrgAttr() == 1)
        {// 保司
            if (entrustPayType == 3 || entrustPayType == 4){
                //获取新版设置价格 2020年8月7日 16点25分 新增逻辑
                //  1)根据委托方机构ID获取模板ID;2)根据模板ID+区域ID获取类别;3)根据类别+任务类型+任务子类+子类结果 获取价格
                //根据具体的区域ID,及委托方   获取区域类别价格
                Map<String,Object> priceMap = new HashMap<>();
                priceMap.put("taskId",surveyCaseDirection.getTaskId());
                priceMap.put("taskInfoContentId",surveyCaseDirection.getNewId());
                priceMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
                priceMap.put("entrustOrgId",surveyRiskCaseInfo.getEntrustOrgId());
                areaId = null;
                if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                    areaId = surveyCaseDirection.getCityId();
                }else{
                    areaId = surveyCaseDirection.getDistrictId();
                }
                priceMap.put("areaId",areaId);
                priceMap.put("orgAttr",surveyConsignor.getOrgAttr());
                surveyPrice = surveyPriceMapper.selectDirectionPrice(priceMap);
                if (surveyPrice != null){
                    surveyCaseDirection.setEntrustMoney(surveyPrice.getTaskPrice());
                    surveyCaseDirection.setEntrustPriceSource(1);
                }else{
                    // 根据委托方机构ID和任务ID获取价格
                    map = new HashMap();
                    map.put("enturyId",surveyConsignor.getId());
                    map.put("taskId", surveyCaseDirection.getTaskId());
                    map.put("areaId",surveyCaseDirection.getAreaId());
                    if (areaType == 1){
                        map.put("cityType",surveyCaseDirection.getRegionType());
                    }else{
                        //如果是省会的地级市，则取省会的价格
                        if (areaType == 3){
                            CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(surveyCaseDirection.getCityId().longValue());
                            if (commonArea.getCityType() == 2){
                                areaType = 2;
                            }
                        }
                        map.put("cityType",areaType);
                    }
                    map.put("taskInfoContentId", surveyCaseDirection.getNewId());
                    map.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
                    SurveyConsignorPrice surveyConsignorPrice = surveyConsignorPriceMapper.selectInfo(map);
                    if (surveyConsignorPrice !=  null){
                        surveyCaseDirection.setEntrustMoney(surveyConsignorPrice.getTaskPrice());
                        surveyCaseDirection.setEntrustPriceSource(1);
                    }else{
                        map = new HashMap();
                        map.put("areaId",surveyCaseDirection.getAreaId());
                        map.put("taskId",surveyCaseDirection.getTaskId());
                        if (areaType == 1){
                            map.put("cityType",surveyCaseDirection.getRegionType());
                        }else{
                            map.put("cityType",areaType);
                        }
                        map.put("taskInfoContentId", surveyCaseDirection.getNewId());
                        map.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());

                        if(surveyConsignor!=null){
                            map.put("priceType",surveyConsignor.getOrgAttr());
                        }else{
                            map.put("priceType",1);//默认为“保险公司”
                        }
                        SurveyCommonAreaPrice surveyCommonAreaPrice = surveyCommonAreaPriceMapper.selectByInfo(map);
                        if (surveyCommonAreaPrice != null){
                            surveyCaseDirection.setEntrustPriceSource(2);
                            surveyCaseDirection.setEntrustMoney(surveyCommonAreaPrice.getPrice());
                        }
                    }
                }
            }
            Integer medicalNumber = surveyCaseDirection.getMedicalNumber();
            if(medicalNumber!=null){
                surveyCaseDirection.setMedicalNumber(medicalNumber);
            }

            if(medicalNumber ==null){
                medicalNumber = 1;
            }
            if(medicalNumber > 1){
                if (!(surveyPayType == 1 || surveyPayType == 2)){
                    //住院病例调查，每多一分病史，增加的价格
                    SurveyDirectionResultType directionResultType = surveyDirectionResultTypeMapper.selectByPrimaryKey(surveyCaseDirection.getDirectionResultTypeId());
                    if(directionResultType != null){
                        if("medical".equals(directionResultType.getCode()) || "medical01".equals(directionResultType.getCode()) || "medical02".equals(directionResultType.getCode()) || "medical03".equals(directionResultType.getCode())){
                            //调查方
                            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
                            if(surveyFranchisee!=null){
                                Double medicalMoney = (surveyFranchisee.getMedicalMoney() ==null?0D:surveyFranchisee.getMedicalMoney()) * (medicalNumber-1);
                                if (surveyFranchisee.getMaxMedicalMoney() == null){
                                    surveyFranchisee.setMaxMedicalMoney(0D);
                                }
                                if (medicalMoney > surveyFranchisee.getMaxMedicalMoney()){
                                    medicalMoney = surveyFranchisee.getMaxMedicalMoney();// 2022年3月8日   如果多份病例价格大于上限价格 则为上限价格
                                }

                                surveyCaseDirection.setSurveyMoney(surveyCaseDirection.getSurveyMoney()+medicalMoney);
                            }

                            //委托方
                            if(surveyConsignor != null){
                                Double medicalMoney = (surveyConsignor.getMedicalMoney() ==null?0D:surveyConsignor.getMedicalMoney()) * (medicalNumber-1);
                                if (surveyCaseDirection.getEntrustMoney() == null){
                                    surveyCaseDirection.setEntrustMoney(0D);
                                }
                                surveyCaseDirection.setEntrustMoney(surveyCaseDirection.getEntrustMoney() + medicalMoney);
                            }
                        }
                    }
                }
            }

            if("1".equals(surveyCaseDirection.getSun() + "")){
                surveyInvestigatorCase.setSunState(4);
                surveyInvestigatorCase.setIsSun(1);
                surveyInvestigatorCase.setSunTime(new Date());
                surveyRiskCaseInfo.setIsSun(1);
            }
        }

        //如果调查方按比例计算。 2021年3月17日 需求
        if (surveyPayType == 3 || surveyPayType == 4){
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("orgAttr",surveyConsignor.getOrgAttr());
            paramMap.put("orgId",surveyConsignor.getId());
            SurveyPriceModel surveyPriceModel = surveyPriceModelMapper.selectModelByOrgId(paramMap);
            if (surveyPriceModel != null){
                if (surveyPriceModel.getSurveyEntrustIsRate() == null){
                    surveyPriceModel.setSurveyEntrustIsRate(0);
                }
                if (surveyPriceModel.getSurveyEntrustIsRate() == 1){//如果按比例计算
                    if (surveyPriceModel.getSurveyEntrustRate() == null){
                        surveyPriceModel.setSurveyEntrustRate(0D);
                    }
                    if (surveyFranchiseeType != 1){
                        surveyCaseDirection.setSurveyMoney(surveyCaseDirection.getEntrustMoney() == null ? 0 : surveyCaseDirection.getEntrustMoney() * surveyPriceModel.getSurveyEntrustRate() / 100);
                    }
                    surveyCaseDirection.setAccMoney(surveyCaseDirection.getEntrustMoney() == null ? 0 : surveyCaseDirection.getEntrustMoney() * surveyPriceModel.getSurveyEntrustRate() / 100);
                }
            }
            if (surveyConsignor.getOrgAttr() == 1){
                //2019年6月25日 09点28分 增加需求 邓剑平 陈建华 李中祥的调查方价格  按照委托方价格的70%(深度案件) 或者 其他50% 计算
                surveyCaseDirection.setSurveyMoney(userMoney(surveyInvestigatorCase,surveyRiskCaseInfo,surveyCaseDirection,surveyPayType,surveyAssignOrg.getServicesId()));
            }
        }


        if (surveyCaseDirection.getId() == null){
            surveyCaseDirection.setCreateTime(new Date());
            surveyCaseDirection.setCreateBy(userInfo.getUserName());
            surveyCaseDirection.setSurveyAssorgCaseId(surveyAssignOrg.getId());
            surveyCaseDirection.setDirectionName(LFStringUtil.replacePrint(surveyCaseDirection.getDirectionName()));
            surveyCaseDirection.setDirectionText(LFStringUtil.replacePrint(surveyCaseDirection.getDirectionText()));
            surveyCaseDirection.setInvalidState(0);//是否无效方向（0：有效；1、无效）
            surveyCaseDirection.setOrgPoint(0);
            surveyCaseDirection.setEvaluate(1);
            surveyCaseDirectionMapper.insert(surveyCaseDirection);
            Map findMap=new HashMap();
            findMap.put("id",surveyCaseDirection.getSurveyInfoId());
            SurveyRiskCaseInfoDto surveyRiskCaseInfoDto=surveyRiskCaseInfoMapper.selectByOne(findMap);
            if(surveyRiskCaseInfoDto!=null){
                Double money=0.00;
                if(surveyCaseDirection.getEntrustMoney() != null && !surveyCaseDirection.getEntrustMoney().equals("")){
                    money=money+surveyCaseDirection.getEntrustMoney();
                }
                SurveyAccountLog surveyAccountLog=new SurveyAccountLog();
                surveyAccountLog.setSurveyInfoId(surveyRiskCaseInfoDto.getId());
                surveyAccountLog.setCodeType(Long.parseLong("1"));
                if(surveyRiskCaseInfoDto.getEntrustMoney() != null && !surveyRiskCaseInfoDto.getEntrustMoney().equals("")){
                    surveyAccountLog.setOldBasicPrice(surveyRiskCaseInfoDto.getEntrustMoney());
                    surveyRiskCaseInfoDto.setEntrustMoney(surveyRiskCaseInfoDto.getEntrustMoney()+money);
                    surveyAccountLog.setNewBasicPrice(surveyRiskCaseInfoDto.getEntrustMoney());
                }else{
                    surveyAccountLog.setOldBasicPrice(0.00);
                    surveyRiskCaseInfoDto.setEntrustMoney(money);
                    surveyAccountLog.setNewBasicPrice(0.00);
                }
                if(surveyRiskCaseInfoDto.getEntrustReLosses() != null && !surveyRiskCaseInfoDto.getEntrustReLosses().equals("")){
                    surveyAccountLog.setOldDePrice(surveyRiskCaseInfoDto.getEntrustReLosses());
                    surveyAccountLog.setNewDePrice(surveyRiskCaseInfoDto.getEntrustReLosses());
                }else{
                    surveyAccountLog.setOldDePrice(0.00);
                    surveyAccountLog.setNewDePrice(0.00);
                }
                surveyAccountLog.setUpdateTime(new Date());
                surveyAccountLog.setUpdateBy(userInfo.getUserName());
                surveyAccountLogMapper.insert(surveyAccountLog);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfoDto);
            }
            //添加调查费用登记
            Integer haveReimbursement = surveyCaseDirection.getHaveReimbursement();
            if (haveReimbursement!=null&& haveReimbursement == 1){
//                if (flag){
//                    SurveyReimbursementInfo reimbursementInfo = (SurveyReimbursementInfo) verficationMap.get("surveyReimbursementInfo");
//                    reimbursementInfo.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
//                    reimbursementInfo.setSurveyOrgId(surveyCaseDirection.getSurveyOrgId());
//                    reimbursementInfo.setSurveyOrgName(surveyCaseDirection.getSurveyOrgName());
//                    reimbursementInfo.setSurveyUserId(userInfo.getUserId());
//                    reimbursementInfo.setSurveyUserName(userInfo.getUserName());
//                    reimbursementInfo.setSurveyDirectionId(surveyCaseDirection.getId());
//                    reimbursementInfo.setInvestigatorCaseId(String.valueOf(surveyCaseDirection.getSurveyInvestigatorCaseId()));
//                    surveyReimbursementInfoMapper.insert(reimbursementInfo);
//                    //添加费用报销附件
//                    this.addReimbursementFiles(apiRequest,reimbursementInfo);
//                }
            }
        }
        else{
            surveyCaseDirection.setDirectionName(LFStringUtil.replacePrint(surveyCaseDirection.getDirectionName()));
            surveyCaseDirection.setDirectionText(LFStringUtil.replacePrint(surveyCaseDirection.getDirectionText()));
            surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
            Map findMap=new HashMap();
            findMap.put("surveyInfoId",surveyCaseDirection.getSurveyInfoId());
            List<SurveyCaseDirection>  list=surveyCaseDirectionMapper.list(findMap);
            Double money=0.00;
            for (SurveyCaseDirection surveyCaseDirectionList:list) {
                if(surveyCaseDirectionList.getEntrustMoney() != null && !surveyCaseDirectionList.getEntrustMoney().equals("")){
                    money=money+surveyCaseDirectionList.getEntrustMoney();
                }
            }
            findMap=new HashMap();
            findMap.put("id",surveyCaseDirection.getSurveyInfoId());
            SurveyRiskCaseInfoDto surveyRiskCaseInfoDto=surveyRiskCaseInfoMapper.selectByOne(findMap);
            SurveyAccountLog surveyAccountLog=new SurveyAccountLog();
            surveyAccountLog.setSurveyInfoId(surveyRiskCaseInfoDto.getId());
            surveyAccountLog.setCodeType(Long.parseLong("1"));
            if(surveyRiskCaseInfoDto.getEntrustMoney() != null && !surveyRiskCaseInfoDto.getEntrustMoney().equals("")){
                surveyAccountLog.setOldBasicPrice(surveyRiskCaseInfoDto.getEntrustMoney());
            }else{
                surveyAccountLog.setOldBasicPrice(0.00);
            }
            surveyAccountLog.setNewBasicPrice(money);
            if(surveyRiskCaseInfoDto.getEntrustReLosses()!= null && !surveyRiskCaseInfoDto.getEntrustReLosses().equals("")){
                surveyAccountLog.setOldDePrice(surveyRiskCaseInfoDto.getEntrustReLosses());
                surveyAccountLog.setNewDePrice(surveyRiskCaseInfoDto.getEntrustReLosses());
            }else{
                surveyAccountLog.setOldDePrice(0.00);
                surveyAccountLog.setNewDePrice(0.00);
            }
            surveyAccountLog.setUpdateTime(new Date());
            surveyAccountLog.setUpdateBy(userInfo.getUserName());
            surveyAccountLogMapper.insert(surveyAccountLog);
            surveyRiskCaseInfoDto.setEntrustMoney(money);
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfoDto);
            if (surveyCaseDirection.getHaveReimbursement() !=null){
                if (surveyCaseDirection.getHaveReimbursement() == 1){
//                    SurveyReimbursementInfo reimbursementInfo = surveyReimbursementInfoMapper.selectBySurveyDirectionId(surveyCaseDirection.getId());
//                    //更新费用报销相关内容
//                    SurveyReimbursementInfo surveyReimbursementInfo = (SurveyReimbursementInfo) verficationMap.get("surveyReimbursementInfo");
//                    if (reimbursementInfo == null) {
//                        if (flag) {
//                            surveyReimbursementInfo.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
//                            surveyReimbursementInfo.setSurveyOrgId(surveyCaseDirection.getSurveyOrgId());
//                            surveyReimbursementInfo.setSurveyOrgName(surveyCaseDirection.getSurveyOrgName());
//                            surveyReimbursementInfo.setSurveyUserId(userInfo.getUserId());
//                            surveyReimbursementInfo.setSurveyUserName(userInfo.getUserName());
//                            surveyReimbursementInfo.setSurveyDirectionId(surveyCaseDirection.getId());
//                            surveyReimbursementInfo.setInvestigatorCaseId(String.valueOf(surveyCaseDirection.getSurveyInvestigatorCaseId()));
//                            surveyReimbursementInfoMapper.insert(surveyReimbursementInfo);
//                            //添加费用报销附件
//                            this.addReimbursementFiles(apiRequest, surveyReimbursementInfo);
//                        }
//
//                    } else {
//                        surveyReimbursementInfo.setId(reimbursementInfo.getId());
//                        surveyReimbursementInfo.setSurveyInfoId(reimbursementInfo.getSurveyInfoId());
//                        surveyReimbursementInfo.setInvestigatorCaseId(reimbursementInfo.getInvestigatorCaseId());
//                        surveyReimbursementInfoMapper.updateByPrimaryKeySelective(surveyReimbursementInfo);
//                        //添加费用报销附件
//                        this.addReimbursementFiles(apiRequest, surveyReimbursementInfo);
//                    }
                }else {
                    surveyReimbursementInfoMapper.deleteBySurveyDirectionId(surveyCaseDirection.getId());
                }
            }

        }

        surveyCaseDirection.setDirectionName(LFStringUtil.replacePrint(surveyCaseDirection.getDirectionName()));
        surveyCaseDirection.setDirectionText(LFStringUtil.replacePrint(surveyCaseDirection.getDirectionText()));
        surveyCaseDirection.setUpdateTime(new Date());
        surveyCaseDirection.setUpdateBy(userInfo.getUserName());

        //2020年11月9日 若为渠道调查员。则新增的方向为渠道方向。分值为0
        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyInvestigatorCase.getSurveyUserId());
        Integer channelType = surveyInvestigator.getChannelType() == null ? 0 : surveyInvestigator.getChannelType();
        if (surveyCaseDirection.getChannelType() == null) {
            surveyCaseDirection.setChannelType(0);
        }
        if (channelType == 1){
            surveyCaseDirection.setChannelType(1);
        }
        surveyCaseDirection.setChannelFeeCur(0D);
        surveyCaseDirection.setChannelFeeSent(0D);
        if (surveyCaseDirection.getChannelType() == 1){
            surveyCaseDirection.setHisScore(surveyCaseDirection.getScore());
            surveyCaseDirection.setScore(0D);
            SurveyChannelModelOrg surveyChannelModelOrg = surveyChannelModelOrgMapper.selectOne(surveyAssignOrg.getSurveyOrgId());
            if (surveyChannelModelOrg != null){
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("modelId",surveyChannelModelOrg.getModelId());
                paramMap.put("taskId",surveyCaseDirection.getTaskId());
                paramMap.put("taskContentId",surveyCaseDirection.getNewId());
                paramMap.put("taskContentResutlId",surveyCaseDirection.getDirectionResultTypeId());
                areaId = null;
                areaType = surveyCaseDirection.getAreaType();
                if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                    areaId = surveyCaseDirection.getCityId();
                }else{
                    areaId = surveyCaseDirection.getDistrictId();
                }
                paramMap.put("areaId",areaId);
                SurveyChannelModelInfo surveyChannelModelInfo = surveyChannelModelInfoMapper.selectByParam(paramMap);
                if (surveyChannelModelInfo != null) {
                    surveyCaseDirection.setChannelFeeCur(surveyChannelModelInfo.getPrice());
                    surveyCaseDirection.setChannelFeeSent(surveyChannelModelInfo.getPrice());
                }
            }

            //如果已存在渠道费用是申请记录 则直接改金额。
            SurveyChannelCostNew surveyChannelCostNew = surveyChannelCostNewMapper.selectChannelCostNewByDirectionId(surveyCaseDirection.getId());
            if (surveyChannelCostNew != null){
                if (surveyChannelCostNew.getIsProPay() == 0){
                    if (surveyCaseDirection.getChannelFeeCur() == null){
                        surveyCaseDirection.setChannelFeeCur(0D);
                    }
                    if (surveyCaseDirection.getChannelFeeSent() == null){
                        surveyCaseDirection.setChannelFeeSent(0D);
                    }
                    if (surveyCaseDirection.getChannelFeeCur() > surveyCaseDirection.getChannelFeeSent()){
                        surveyChannelCostNew.setState(5);//大于限额需要审核
                        surveyChannelCostNew.setReviewerTime(null);
                    }else{
                        surveyChannelCostNew.setState(3);//否则直接审核通过
                        surveyChannelCostNew.setReviewerTime(new Date());
                    }
                    surveyChannelCostNew.setChnannelMoney(surveyCaseDirection.getChannelFeeCur());
                    surveyChannelCostNewMapper.updateByPrimaryKey(surveyChannelCostNew);
                }
            }
        }
        surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
        //计算报销明细对应的案件金额
        InvestigatorReDetails investigatorReDetails = investigatorReDetailsMapper.selectDetailByInvestigatorCaseId(surveyCaseDirection.getSurveyInvestigatorCaseId());
        if (investigatorReDetails != null){
            Double taotalMoney = surveyInvestigatorCaseMapper.selectAllDirectionReimTotalMoneyById(surveyCaseDirection.getSurveyInvestigatorCaseId());
            investigatorReDetails.setInvestigatorReMoney(taotalMoney == null ? 0 : taotalMoney);
            investigatorReDetailsMapper.updateByPrimaryKey(investigatorReDetails);
        }

        //计算机构的调查方价格,案件的委托方价格
        Boolean insert = true;
        if (surveyInvestigatorCase.getCreportDate() == null){
            insert = false;
        }
        if (insert){
            if (surveyCaseDirectionOld != null){
                String beforeValue = surveyCaseDirectionOld.getScore() == null ? "" : surveyCaseDirectionOld.getScore().toString();
                //分值记录
                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyCaseDirection.getScore() == null ? "" : surveyCaseDirection.getScore().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_score" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark("方向变更");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }


                //调查方记录
                beforeValue = surveyCaseDirectionOld.getSurveyMoney() == null ? "" : surveyCaseDirectionOld.getSurveyMoney().toString();
                record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyCaseDirection.getSurveyMoney() == null ? "" : surveyCaseDirection.getSurveyMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_survey_money" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark("方向变更");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

                //委托方记录
                beforeValue = surveyCaseDirectionOld.getEntrustMoney() == null ? "" : surveyCaseDirectionOld.getEntrustMoney().toString();
                record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyCaseDirection.getEntrustMoney() == null ? "" : surveyCaseDirection.getEntrustMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                    record.setUpdAttr("survey_case_direction_entrust_money" + surveyCaseDirection.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark("方向变更");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }
        }

        if ("success".equals(successDirection)){//完成调查方向
            surveyInvestigatorCase.setIsDirectionSuccess(1);
            if("1".equals(isUser)){ //
                surveyRiskCaseInfo.setUseLetterInfo(1);
            }
                /*String isSun = apiRequest.getString("isSun");
                if("1".equals(isSun)){
                    surveyInvestigatorCase.setSunState(4);
                    surveyInvestigatorCase.setIsSun(1);
                    surveyInvestigatorCase.setSunTime(new Date());
                    surveyRiskCaseInfo.setIsSun(1);
                }*/
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
        }





    }


    @ApiMethod(needLogin = false,descript = "文本纠错-百度接口",value = "valid-direction-text-error-baidu")
    @Override
    public ApiResponse baiduContextError(ApiRequest apiRequest) {
        String text = apiRequest.getString("text");
        String json = null;
        try {
            json = AuthService.valid(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,json);
    }

    //添加费用报销文件
    public void addReimbursementFiles(ApiRequest apiRequest,SurveyReimbursementInfo surveyReimbursementInfo){
        SurveyReimbursementFile surveyReimbursementFile = new SurveyReimbursementFile();
        surveyReimbursementFile.setSurveyInfoId(surveyReimbursementInfo.getSurveyInfoId());
        surveyReimbursementFile.setReimbursementId(surveyReimbursementInfo.getId());
        if (!StringUtils.isEmpty(apiRequest.getString("medicalHistoryFiles"))){
            surveyReimbursementFile.setFileCode("medh");
            List<String> medicalHistoryMoneyFileList= Arrays.asList(apiRequest.getString("medicalHistoryFiles").split(","));
            for (String s : medicalHistoryMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }
        if (!StringUtils.isEmpty(apiRequest.getString("troubleshootingFiles"))){
            surveyReimbursementFile.setFileCode("trou");
            List<String> troubleshootingMoneyFileList= Arrays.asList(apiRequest.getString("troubleshootingFiles").split(","));
            for (String s : troubleshootingMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }
        if (!StringUtils.isEmpty(apiRequest.getString("innerCityFiles"))){
            surveyReimbursementFile.setFileCode("inner");
            List<String> troubleshootingMoneyFileList= Arrays.asList(apiRequest.getString("innerCityFiles").split(","));
            for (String s : troubleshootingMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }
        if (!StringUtils.isEmpty(apiRequest.getString("printingFiles"))){
            surveyReimbursementFile.setFileCode("print");
            List<String> printingMoneyFileList= Arrays.asList(apiRequest.getString("printingFiles").split(","));
            for (String s : printingMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }
        if (!StringUtils.isEmpty(apiRequest.getString("accommodatioFiles"))){
            surveyReimbursementFile.setFileCode("acco");
            List<String> accommodatioMoneyFileList= Arrays.asList(apiRequest.getString("accommodatioFiles").split(","));
            for (String s : accommodatioMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }
        if (!StringUtils.isEmpty(apiRequest.getString("trainFiles"))){
            surveyReimbursementFile.setFileCode("train");
            List<String> trainMoneyFileList= Arrays.asList(apiRequest.getString("trainFiles").split(","));
            for (String s : trainMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }
        if (!StringUtils.isEmpty(apiRequest.getString("carFiles"))){
            surveyReimbursementFile.setFileCode("car");
            List<String> carMoneyFileList= Arrays.asList(apiRequest.getString("carFiles").split(","));
            for (String s : carMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }
        if (!StringUtils.isEmpty(apiRequest.getString("airFiles"))){
            surveyReimbursementFile.setFileCode("aircraft");
            List<String> aircraftMoneyFileList= Arrays.asList(apiRequest.getString("airFiles").split(","));
            for (String s : aircraftMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }
        if (!StringUtils.isEmpty(apiRequest.getString("selfDrivingFiles"))){
            surveyReimbursementFile.setFileCode("self");
            List<String> selfDrivingMoneyFileList= Arrays.asList(apiRequest.getString("selfDrivingFiles").split(","));
            for (String s : selfDrivingMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }
        if (!StringUtils.isEmpty(apiRequest.getString("otherMoneyFiles"))){
            surveyReimbursementFile.setFileCode("other");
            List<String> otherMoneyFileList= Arrays.asList(apiRequest.getString("otherMoneyFiles").split(","));
            for (String s : otherMoneyFileList) {
                surveyReimbursementFile.setFileUrl(s);
                surveyReimbursementFileMapper.insert(surveyReimbursementFile);
            }
        }

    }

    public HashMap<String ,Object> verification(ApiRequest apiRequest){
        boolean flag = true;
        SurveyReimbursementInfo surveyReimbursementInfo = new SurveyReimbursementInfo();
        HashMap<String ,Object> resultMap = new HashMap();
        String medicalHistoryMoney = apiRequest.getString("medicalHistoryMoney");
        String troubleshootingMoney = apiRequest.getString("troubleshootingMoney");
        String printingMoney = apiRequest.getString("printingMoney");
        String accommodatioMoney = apiRequest.getString("accommodatioMoney");
        String trainMoney = apiRequest.getString("trainMoney");
        String carMoney = apiRequest.getString("carMoney");
        String aircraftMoney = apiRequest.getString("aircraftMoney");
        String selfDrivingMoney = apiRequest.getString("selfDrivingMoney");
        String otherMoney = apiRequest.getString("otherMoney") ;
        String medicalHistoryDesc = apiRequest.getString("medicalHistoryDesc");
        String troubleshootingDesc = apiRequest.getString("troubleshootingDesc");
        String printingDesc = apiRequest.getString("printingDesc");
        String accommodatioDesc = apiRequest.getString("accommodatioDesc");
        String trainDesc = apiRequest.getString("trainDesc");
        String carDesc = apiRequest.getString("carDesc");
        String aircraftDesc = apiRequest.getString("aircraftDesc");
        String selfDrivingDesc = apiRequest.getString("selfDrivingDesc");
        String otherDesc = apiRequest.getString("otherDesc");
        String kilometresNum = apiRequest.getString("kilometresNum");
        String accommodatioDays = apiRequest.getString("accommodatioDays");
        String innerCityMoney = apiRequest.getString("innerCityMoney");
        String innerCityDesc = apiRequest.getString("innerCityDesc");
        String tollMoney = apiRequest.getString("tollMoney");
        if (StringUtils.isEmpty(medicalHistoryMoney) &&
                StringUtils.isEmpty(troubleshootingMoney)&&
                StringUtils.isEmpty(printingMoney)&&
                StringUtils.isEmpty(accommodatioMoney)&&
                StringUtils.isEmpty(trainMoney)&&
                StringUtils.isEmpty(carMoney)&&
                StringUtils.isEmpty(aircraftMoney)&&
                StringUtils.isEmpty(selfDrivingMoney)&&
                StringUtils.isEmpty(otherMoney)&&
                StringUtils.isEmpty(medicalHistoryDesc)&&
                StringUtils.isEmpty(troubleshootingDesc)&&
                StringUtils.isEmpty(troubleshootingDesc)&&
                StringUtils.isEmpty(printingDesc)&&
                StringUtils.isEmpty(accommodatioDesc)&&
                StringUtils.isEmpty(trainDesc)&&
                StringUtils.isEmpty(carDesc)&&
                StringUtils.isEmpty(aircraftDesc)&&
                StringUtils.isEmpty(selfDrivingDesc)&&
                StringUtils.isEmpty(otherDesc)&&
                StringUtils.isEmpty(kilometresNum)&&
                StringUtils.isEmpty(accommodatioDays)&&
                StringUtils.isEmpty(innerCityMoney)&&
                StringUtils.isEmpty(innerCityDesc)&&
                StringUtils.isEmpty(tollMoney)
                ) flag = false;
        resultMap.put("flag",flag);
        if (flag){
            if (medicalHistoryMoney!=null)surveyReimbursementInfo.setMedicalHistoryMoney(Double.valueOf(medicalHistoryMoney));
            surveyReimbursementInfo.setMedicalHistoryDesc(medicalHistoryDesc);
            if (trainMoney!=null)surveyReimbursementInfo.setTrainMoney(Double.valueOf(trainMoney));
            surveyReimbursementInfo.setTrainDesc(trainDesc);
            if (printingMoney!=null)surveyReimbursementInfo.setPrintingMoney(Double.valueOf(printingMoney));
            surveyReimbursementInfo.setPrintingDesc(printingDesc);
            if (accommodatioMoney!=null)surveyReimbursementInfo.setAccommodatioMoney(Double.valueOf(accommodatioMoney));
            surveyReimbursementInfo.setAccommodatioDesc(accommodatioDesc);
            if (troubleshootingMoney!=null)surveyReimbursementInfo.setTroubleshootingMoney(Double.valueOf(troubleshootingMoney));
            surveyReimbursementInfo.setTroubleshootingDesc(troubleshootingDesc);
            if (carMoney!=null)surveyReimbursementInfo.setCarMoney(Double.valueOf(carMoney));
            surveyReimbursementInfo.setCarDesc(carDesc);
            if (aircraftMoney!=null) surveyReimbursementInfo.setAircraftMoney(Double.valueOf(aircraftMoney));
            surveyReimbursementInfo.setAircraftDesc(aircraftDesc);
            if (selfDrivingMoney!=null)surveyReimbursementInfo.setSelfDrivingMoney(Double.valueOf(selfDrivingMoney));
            surveyReimbursementInfo.setSelfDrivingDesc(selfDrivingDesc);
            if (otherMoney!=null)surveyReimbursementInfo.setOtherMoney(Double.valueOf(otherMoney));
            surveyReimbursementInfo.setOtherDesc(otherDesc);
            if (kilometresNum!=null)surveyReimbursementInfo.setKilometresNum(Double.valueOf(kilometresNum));
            if (accommodatioDays!=null) surveyReimbursementInfo.setAccommodatioDays(Integer.valueOf(accommodatioDays));
            if (innerCityMoney!=null)surveyReimbursementInfo.setInnerCityMoney(Double.valueOf(innerCityMoney));
            surveyReimbursementInfo.setInnerCityDesc(innerCityDesc);
            if (tollMoney!=null)surveyReimbursementInfo.setTollMoney(Double.valueOf(tollMoney));
            resultMap.put("surveyReimbursementInfo",surveyReimbursementInfo);
        }
        return resultMap;
    }

    public void syncPrice(SurveyRiskCaseInfo surveyRiskCaseInfo,UserInfo userInfo,Boolean insert,boolean updPrice){
        //众安案子同步委托方价格
        if (!updPrice){
            if (surveyRiskCaseInfo.getEntrustOrgId() == 94 && surveyRiskCaseInfo.getSubServiceId() != null){
                List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.selectAllDirectionByRiskInfoId(surveyRiskCaseInfo.getId());
                if (surveyRiskCaseInfo.getSubServiceId() == 2){//核对调阅类 按照机构分组 区域分组计算价格
                    Map<Long, List<SurveyCaseDirection>> orgDirectionList = surveyCaseDirections.stream().collect(Collectors.groupingBy(SurveyCaseDirection::getSurveyOrgId));//机构分组
                    orgDirectionList.forEach((k,v)->{
                        Map<Integer, List<SurveyCaseDirection>> areaDirectionList = v.stream().collect(Collectors.groupingBy(e->e.getCityId()+e.getDistrictId()));//机构下的区域分组 cityId+districtId才是唯一的
                        //每个机构下面区域相同的方向 设置委托方价格
                        areaDirectionList.forEach((k1,v1)->{
                            double firstMoney = 0;
                            double otherMoney = 0;
                            for (int i = 0; i < v1.size(); i++) {
                                List<SurveyPrice> surveyPriceList;
                                SurveyCaseDirection surveyCaseDirection = v1.get(i);
                                if (i == 0 ){
                                    surveyPriceList = surveyPriceMapper.selectZhongAnPrice(surveyCaseDirection.getDistrictId() == 0 ? surveyCaseDirection.getCityId() : surveyCaseDirection.getDistrictId());//数据库之前保存的是  districtid =0 就保存的cityId
                                    firstMoney = surveyPriceList.stream().filter(e->e.getTaskId() == 10001).limit(1).mapToDouble(SurveyPrice::getTaskPrice).sum();
                                    otherMoney = surveyPriceList.stream().filter(e->e.getTaskId() == 10002).limit(1).mapToDouble(SurveyPrice::getTaskPrice).sum();
                                    surveyCaseDirection.setEntrustMoney(firstMoney);
                                }else {
                                    surveyCaseDirection.setEntrustMoney(otherMoney);
                                }

                                Map<String,Object> paramMap =  new HashMap<String,Object>();
                                paramMap.put("orgAttr",1);
                                paramMap.put("orgId",surveyRiskCaseInfo.getEntrustOrgId());
                                SurveyPriceModel surveyPriceModel = surveyPriceModelMapper.selectModelByOrgId(paramMap);
                                if (surveyPriceModel != null){
                                    if (surveyPriceModel.getSurveyEntrustIsRate() == null){
                                        surveyPriceModel.setSurveyEntrustIsRate(0);
                                    }
                                    if (surveyPriceModel.getSurveyEntrustIsRate() == 1){//如果按比例计算
                                        if (surveyPriceModel.getSurveyEntrustRate() == null){
                                            surveyPriceModel.setSurveyEntrustRate(0D);
                                        }
                                        surveyCaseDirection.setSurveyMoney(surveyCaseDirection.getEntrustMoney() == null ? 0 : surveyCaseDirection.getEntrustMoney() * surveyPriceModel.getSurveyEntrustRate() / 100);
                                        surveyCaseDirection.setAccMoney(surveyCaseDirection.getEntrustMoney() == null ? 0 : surveyCaseDirection.getEntrustMoney() * surveyPriceModel.getSurveyEntrustRate() / 100);
                                    }
                                }
                                surveyCaseDirectionMapper.updateByPrimaryKeySelective(surveyCaseDirection);
                            }
                        });
                    });
                }else {//其他类别价格都是0
                    for (SurveyCaseDirection surveyCaseDirection : surveyCaseDirections) {
                        surveyCaseDirection.setEntrustMoney(0d);
                        surveyCaseDirection.setSurveyMoney(0d);
                        surveyCaseDirectionMapper.updateByPrimaryKeySelective(surveyCaseDirection);
                    }
                    if (surveyRiskCaseInfo.getSubServiceId()!=6){
                        Integer taskId = 0;
                        if (surveyRiskCaseInfo.getSubServiceId() == 3){
                            taskId = 10003;
                        }else if (surveyRiskCaseInfo.getSubServiceId() == 4){
                            taskId = 10004;
                        }else if (surveyRiskCaseInfo.getSubServiceId() == 5){
                            taskId = 10005;
                        }
                        String allDirectionDistIdStr = surveyCaseDirections.stream().map(e -> {
                            if (e.getDistrictId() != null){
                                return e.getDistrictId() == 0 ? e.getCityId().toString() : e.getDistrictId().toString();
                            }else {
                                return "";
                            }
                        }).distinct().collect(Collectors.joining(","));
                        Double maxMoney = surveyPriceMapper.maxMoney(allDirectionDistIdStr, taskId);
                        surveyRiskCaseInfo.setEntrustMoney(maxMoney);
                    }
                }

            }
        }
        //委托方案件价格
        Integer entrustPayType = surveyRiskCaseInfo.getPayType();
        if (entrustPayType == 3 || entrustPayType == 4){//按照任务点结算
            //所有方向价格总和
            Map<String,Object> map =  new HashMap<String,Object>();
            map.put("surveyInfoId",surveyRiskCaseInfo.getId());
            map.put("invalidState",0);//是否无效方向（0：有效；1、无效）
            String beforeValue = surveyRiskCaseInfo.getEntrustMoney() == null ? ""  : surveyRiskCaseInfo.getEntrustMoney().toString();
            SurveyDirectionMoneyDTO money = surveyCaseDirectionMapper.selectDirectionMoney(map);
            surveyRiskCaseInfo.setEntrustMoney(money.getEntrustMoney());
            surveyRiskCaseInfo.setEntrustOkPrice1(money.getEntrustMoney());
            if (insert){
                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfo.getEntrustMoney() == null ? "" : surveyRiskCaseInfo.getEntrustMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_entrust_money");
                    record.setUpdTime(new Date());
                    record.setUpdRemark("关联变更");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }
        }
        //2021年9月10日
        Boolean flag = false;//是否是发起二调之后的深度案件
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        if (surveyRiskCase.getTransferType() != null) {
            if (surveyRiskCase.getTransferType() > 1 && surveyRiskCaseInfo.getServicesId().intValue() == 13) {
                flag = true;
            }
        }


        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
        //调查方机构价格 及 案件价格
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("surveyInfoId",surveyRiskCaseInfo.getId());
        Double surveyMoney = 0D,surveyRelosses = 0D;
        List<SurveyAssignOrgDto> orgs = surveyAssignOrgMapper.list(paramMap);
        Map parmMap = new HashMap();
        parmMap.put("surveyInfoId", surveyRiskCaseInfo.getId());
        int totalDirection = surveyCaseDirectionMapper.selectTotalDirection(parmMap);//该案件方向的总数量
        if (totalDirection == 0) totalDirection = 1;
        //2021年8月20日 机构价格逻辑改掉
        for (SurveyAssignOrgDto org : orgs) {
            String beforeValue = org.getAssessOrgMoney() == null ? "" : org.getAssessOrgMoney().toString();
            String remark = "关联变更";
            if (flag) {
                remark = "二调之后的深度案件，价格为0";
                org.setAssessOrgMoney(0D);
                org.setSurveryReLosses(0D);
            } else {
                //获取改机构所有方向的最大深度价格
                paramMap = new HashMap();
                paramMap.put("orgAttr",surveyConsignor.getOrgAttr());//1保司 2互助
                paramMap.put("caseOrgId",org.getId());
                Double maxMoney = surveyCaseDirectionMapper.selectSurveyShenduMoneyByMap(paramMap);
                maxMoney = maxMoney == null ? 1200D : maxMoney;

                Boolean singPoint = false;//是否为单点
                if (org.getServicesId() == 11 || org.getServicesId() == 12){
                    singPoint = true;
                }
                paramMap = new HashMap();
                paramMap.put("invalidState",0);//是否无效方向（0：有效；1、无效）
                paramMap.put("surveyAssignCaseId",org.getId());
                paramMap.put("surveyInfoId",surveyRiskCaseInfo.getId());
                SurveyDirectionMoneyDTO moneyDTO = surveyCaseDirectionMapper.selectDirectionMoney(paramMap);

                if (orgs.size() == 1){//单机构
                    if (singPoint){//单点
                        if (moneyDTO.getSurveyMoney() > maxMoney){
                            beforeValue = moneyDTO.getSurveyMoney().toString();
                            org.setAssessOrgMoney(maxMoney);
                            //生成一条调整记录
                            remark = "系统自动调整:单点不可超越深度";
                        }else{
                            org.setAssessOrgMoney(moneyDTO.getSurveyMoney());
                        }
                    }else{//深度
                        org.setAssessOrgMoney(maxMoney);
                    }
                }else{//多机构
                    Double tempMoney = DecimalUtil.twoDecimalTOFourFromFive(((float)moneyDTO.getDirectionNum() / totalDirection) * maxMoney);
                    long point = orgs.stream().filter(p -> p.getServicesId() == 11 || p.getServicesId() == 12).count();
                    long depth = orgs.stream().filter(p -> p.getServicesId() == 13).count();
                    if (point > 0 && depth == 0){//全部单点
                        if (moneyDTO.getSurveyMoney() > tempMoney){
                            beforeValue = moneyDTO.getSurveyMoney().toString();
                            org.setAssessOrgMoney(tempMoney);
                            remark = "系统自动调整:按比例计算单点不可超越深度";
                        }else{
                            org.setAssessOrgMoney(moneyDTO.getSurveyMoney());
                        }
                    }else if (point > 0 && depth > 0){//单点+深度混合
                        if (singPoint){
                            if (moneyDTO.getSurveyMoney() > tempMoney){
                                beforeValue = moneyDTO.getSurveyMoney().toString();
                                org.setAssessOrgMoney(tempMoney);
                                remark = "系统自动调整:按比例计算单点不可超越深度";
                            }else{
                                org.setAssessOrgMoney(moneyDTO.getSurveyMoney());
                            }
                        }else{
                            org.setAssessOrgMoney(tempMoney);
                        }
                    }else if (point == 0 && depth > 0){//全部深度
                        org.setAssessOrgMoney(tempMoney);
                    }
                }
            }

            org.setAssessOrgLossesMoney(org.getSurveryReLosses());
            org.setSurveyMoney(org.getAssessOrgMoney() * (org.getOverdueAgingRate() == null ? 1D : org.getOverdueAgingRate()));
            org.setSurveyMoneySubmit(org.getSurveyMoney());
            org.setSurveryReLossesSubmit((org.getAssessOrgLossesMoney() == null ? 0D : org.getAssessOrgLossesMoney()) * (org.getOverdueAgingRate() == null ? 1D : org.getOverdueAgingRate()));
            org.setAccMoney(org.getSurveyMoney());
            surveyAssignOrgMapper.updateByPrimaryKey(org);
            surveyMoney += (org.getSurveyMoney() == null ? 0D : org.getSurveyMoney());
            surveyRelosses += (org.getSurveryReLosses() == null ? 0D : org.getSurveryReLosses());

            //增加机构记录
            if (insert){
                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(org.getAssessOrgMoney() == null ? "" : org.getAssessOrgMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                    record.setSurveyInfoId(org.getSurveyInfoId());
                    record.setUpdAttr("survey_assign_org_org_assess_org_money" + org.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(remark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }
        }

        String beforeValue = surveyRiskCaseInfo.getSurveyMoney() == null ? "" : surveyRiskCaseInfo.getSurveyMoney().toString();
        surveyRiskCaseInfo.setSurveyMoney(surveyMoney);
        surveyRiskCaseInfo.setSurveryReLosses(surveyRelosses);

        if (insert){
            SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
            record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
            record.setUpdAfterValue(surveyRiskCaseInfo.getSurveyMoney() == null ? "" : surveyRiskCaseInfo.getSurveyMoney().toString());
            if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())){
                record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                record.setUpdAttr("survey_risk_case_info_survey_money");
                record.setUpdTime(new Date());
                record.setUpdRemark("关联变更");
                record.setUpdUserName(userInfo.getUserName());
                surveyAttrUpdRecordMapper.insert(record);
            }
        }
        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
    }

    /**
     * 同步案件价格
     * @param surveyRiskCaseInfo
     */
    public void syncPrice(SurveyRiskCaseInfo surveyRiskCaseInfo,UserInfo userInfo,Boolean insert){
        syncPrice(surveyRiskCaseInfo,userInfo,insert,false);
    }

    /**
     * 获取特殊的方向价格
     * @param surveyInvestigatorCase
     * @param surveyRiskCaseInfo
     * @param direction
     * @return
     */
    private Double userMoney(SurveyInvestigatorCase surveyInvestigatorCase,SurveyRiskCaseInfo surveyRiskCaseInfo,SurveyCaseDirection direction,int surveyPayType,Long surveyServiceId){
        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
        SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
//        if (surveyFranchisee.getInsuranceType() != 1) {//非自营(保司非直营) 2020年6月11日 18点02分 修改逻辑
        if (surveyPayType == 3 || surveyPayType == 4){//任务 或者  任务+减损
            //陈建华6255（35）    邓剑平6258(39)
            if(surveyFranchisee.getId().toString().equals("35") || surveyFranchisee.getId().toString().equals("39")){//三个特殊人员
                Double rate = 0D;
                if (surveyServiceId.intValue() == 13){
                    rate = 0.5D;
                }else{
                    rate = 0.65D;
                }
                if (surveyFranchisee.getId().toString().equals("39")){//邓剑平
                    rate = 0.73;
                }
                direction.setSurveyMoney(direction.getEntrustMoney() * rate);
            }
        }
//        }
        return direction.getSurveyMoney();
    }

    @ApiMethod(needLogin = false,descript = "调查方向详情(修改)",value = "info-survey-direction")
    @Override
    public ApiResponse infoDirection(ApiRequest apiRequest) {
        Long directionId = apiRequest.getLong("directionId");
        SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
        SurveyCaseDirectionDto surveyCaseDirectionDto = new SurveyCaseDirectionDto();
        if (surveyCaseDirection != null){
            try {
                surveyCaseDirectionDto.setSurveyRiskCaseInfo(surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInfoId()));
                BeanUtils.copyProperties(surveyCaseDirectionDto,surveyCaseDirection);
                Map map = new HashMap();
                map.put("directionId",directionId);
                List<SurveyCaseDirectionFileDto> directionFileDtos = surveyCaseDirectionFileMapper.list(map);
                for (SurveyCaseDirectionFileDto directionFileDto : directionFileDtos) {
                    CommonFile commonFile = commonFileMapper.selectByPrimaryKey(directionFileDto.getFileId());
                    directionFileDto.setCommonFile(commonFile);
                    int lastNamePdf = commonFile.getFilePath().lastIndexOf(".") + 1;
                    String urlName =commonFile.getFilePath().substring(lastNamePdf);
                    if("doc".equals(urlName)){
                        directionFileDto.setFileType(1);
                    }else if("png".equals(urlName) || "jpg".equals(urlName)){
                        directionFileDto.setFileType(3);
                    }else if("xls".equals(urlName) || "xlsx".equals(urlName)){
                        directionFileDto.setFileType(4);
                    }else{
                        directionFileDto.setFileType(2);
                    }
                }
                SurveyReimbursementInfo surveyReimbursementInfo = surveyReimbursementInfoMapper.selectBySurveyDirectionId(surveyCaseDirection.getId());
                if (surveyReimbursementInfo!=null){
                    List<SurveyReimbursementFileDto> surveyReimbursementFileDtos = surveyReimbursementFileMapper.selectByReimbursementId(surveyReimbursementInfo.getId());
                    surveyCaseDirectionDto.setSurveyReimbursementFileDtoList(surveyReimbursementFileDtos);
                }
                surveyCaseDirectionDto.setSurveyCaseDirectionFiles(directionFileDtos);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyCaseDirectionDto);
    }

    @ApiMethod(needLogin = false,descript = "提交审核（案件提交审核）",value = "commit--direction-survey-investigator-case")
    @Override
    public ApiResponse commitReport(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        String typeCode = apiRequest.getString("typeCode");
        String generateReportPath = apiRequest.getString("generateReportPath");
        String orgSummary = apiRequest.getString("orgSummary");
        String reportCompletion = apiRequest.getString("reportCompletion");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
        //机构案件更改为初审中

        //验证机构下的所有调查员是否都已经审核通过
        apiRequest.clear();
        apiRequest.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
        apiRequest.put("surveyAssorgCaseId",surveyInvestigatorCase.getSurveyAssorgCaseId());

        int count = 0;
        List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(apiRequest);
        for (SurveyInvestigatorCaseDto aCase : cases) {
            if (aCase.getSurveyState() != 4 && aCase.getSurveyState() != 2 && aCase.getSurveyState() != 6){
                count ++;
                aCase.setSurveyUserType(2);//辅助调查员
                if (aCase.getId().intValue() == surveyInvestigatorCase.getId().intValue()){
                    aCase.setSurveyUserType(1);//辅助调查员
                }
                surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
            }
        }
        if (count > 1){
            return new ApiResponse(ApiMsgEnum.SURVEY_OPR_ALL_SUCCESS);
        }
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
//        Map<String,Long> map =  new HashMap<String,Long>();
//        map.put("surveyOrgId",surveyInvestigatorCase.getSurveyOrgId());
//        map.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
//        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
        SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
        if (surveyAssignOrg != null){

            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
            if(surveyConsignor!=null){
                efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
            }
            SurveyUserPrescriptionFlow userPreFollow = surveyUserPrescriptionFlowMapper.selectByInvCaseIdLimitOne(surveyInvestigatorCase.getId());
            if (userPreFollow != null){
                Date endTime = new Date();
                userPreFollow.setEndTime(endTime);
                userPreFollow.setDays(Math.abs((double)GetWorkDay.calLeaveDays(userPreFollow.getStartTime(),endTime,efficiencyAttr)));
                surveyUserPrescriptionFlowMapper.updateByPrimaryKeySelective(userPreFollow);
            }

            if (orgSummary != null){
                surveyAssignOrg.setOrgSummary(orgSummary);
            }
            surveyInvestigatorCase.setSurveyState(4);
            surveyInvestigatorCase.setSurveyStateName("已提交");
            surveyInvestigatorCase.setCreportDate(new Date());//提交审核时间
            surveyInvestigatorCase.setSurveyRemark(null);//提交后，复核退回原因置空
            //调查员时效
            double s = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(cases.get(0).getSurveyInfoId(), cases.get(0).getSurveyAssorgCaseId(), cases.get(0).getId());
            surveyInvestigatorCase.setAgingDay(AgingDayUtil.surveyAgingDay(surveyInvestigatorCase,surveyConsignor.getEfficiencyAttr(),s));
            surveyInvestigatorCase.setAgingReal(s);
            surveyInvestigatorCase.setAgingOver(surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck()>0?surveyInvestigatorCase.getAgingReal()-surveyInvestigatorCase.getAgingCheck():0);
            //计算调查员考核绩效 2020年5月26日 17点33分 增加逻辑
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
            Double agingRate = AgingDayUtil.surveyAgingRate(surveyInvestigatorCase,surveyAssignOrg,surveyFranchisee,surveyConsignor);
            surveyInvestigatorCase = agingRate(agingRate,surveyInvestigatorCase);
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

            surveyAssignOrg.setOrgSurveyState(2);
            surveyAssignOrg.setOrgSurveyStateName("初审中");
            surveyAssignOrg.setOrgOpinion(null);
            surveyAssignOrg.setReportDate(null);
            double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
            surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg,surveyConsignor.getEfficiencyAttr(),v)));
            surveyAssignOrg.setAgingReal(Math.abs(v));
            surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck()>0?surveyAssignOrg.getAgingReal()-surveyAssignOrg.getAgingCheck():0);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

            //发送消息通知
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=org-review-list&assignOrgId=" + surveyAssignOrg.getId();
            Map<String,Long> paramMap =  new HashMap<String,Long>();
            paramMap.put("roleId",58L);
            paramMap.put("orgId",surveyAssignOrg.getSurveyOrgId());
            List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
            backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),toUsers,4,"任务审核通知",
                    "你有新的任务待审核，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，案件截止日期：" + simpleDateFormat.format(surveyRiskCaseInfo.getEndTime()),url);

        }

        if (reportCompletion != null){
            if (generateReportPath != null && !"".equals(generateReportPath)) {
                CommonFile commonFile = new CommonFile();
                commonFile.setFilePath(generateReportPath);
                String fileName = generateReportPath.substring(generateReportPath.lastIndexOf("/") + 1);
                commonFile.setFileName(fileName);
                commonFile.setCreateTime(new Date());
                commonFileMapper.insert(commonFile);
                surveyAssignOrg.setReportId(commonFile.getId());
                surveyAssignOrg.setReportName(fileName);
                surveyRiskCaseInfo.setReportId(commonFile.getId());
                surveyRiskCaseInfo.setReportName(fileName);
            }

            if (reportCompletion != null){
                surveyAssignOrg.setOrgSummary(reportCompletion);
            }
            surveyAssignOrg.setReportState(1);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
            surveyRiskCaseInfo.setReportCompletion(reportCompletion);
            surveyRiskCaseInfo.setReportState(1);
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
        }

        //计算调查方总价格
//        Double price1 = 0D,price2 = 0D;
//        List<SurveyInvestigatorCase> investigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
//        for (SurveyInvestigatorCase aCase : investigatorCases) {
//            price1 += (aCase.getSurveyTaskMoney() == null ? 0D : aCase.getSurveyTaskMoney());
//            price2 += (aCase.getSurveryReLosses() == null ? 0D : aCase.getSurveryReLosses());
//        }
//        surveyRiskCaseInfo.setSurveyMoney(price1);
//        surveyRiskCaseInfo.setSurveryReLosses(price2);
//        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
        surveyCaseWorkflowApi.addSurveyCaseWorkflow(surveyInvestigatorCase.getSurveyUserName().concat("调查案件"), userInfo, surveyInvestigatorCase.getAcceptDate(), new Date(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());




        //流程更改  暂时不执行以下代码  初审通过之后执行。
        if (false){
//            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
            List<SurveyInvestigatorCase> surveyInvestigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
            Double entrustMoney = 0D,surveyMoney = 0D;
            for (SurveyInvestigatorCase investigatorCase : surveyInvestigatorCases) {
                if (surveyRiskCaseInfo.getPayType() == 3 || surveyRiskCaseInfo.getPayType() == 4){
                    entrustMoney += investigatorCase.getEntrustTaskMoney();
                    surveyMoney += investigatorCase.getSurveyTaskMoney();
                }
            }
            if (surveyRiskCaseInfo.getPayType() == 1){
                entrustMoney = surveyRiskCaseInfo.getEntrustMoney();
                surveyMoney = surveyRiskCaseInfo.getSurveyMoney();
            }else if (surveyRiskCaseInfo.getPayType() == 2){
                entrustMoney = surveyRiskCaseInfo.getEntrustMoney() + surveyRiskCaseInfo.getEntrustReLosses();
                surveyMoney = surveyRiskCaseInfo.getSurveyMoney() + surveyRiskCaseInfo.getSurveryReLosses();
            }
            surveyRiskCase.setEntrustTotalMoney(entrustMoney);
            surveyRiskCase.setSurveyTotalMoney(surveyMoney);
            surveyRiskCaseInfo.setLefanReportDate(new Date());
            //特殊机构报告未上传也可以审核通过。  所以在这里不改变报告状态  2019年2月18日11点10分 注释
//        surveyRiskCaseInfo.setReportState(2);
            surveyRiskCaseInfo.setSurveyState(22);
            surveyRiskCaseInfo.setOpinion(null);
            surveyRiskCaseInfo.setSurveyStateName("平台复审中");
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            //增加进度 调查完毕，分控审核
            backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(),surveyRiskCaseInfo.getId(),userInfo.getUserId(),userInfo.getUserName(),"调查完毕,平台复审","");
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(needLogin = false,descript = "统计调查费用",value = "report-survey-money")
    @Override
    public ApiResponse surveyMoney(ApiRequest apiRequest) {
        String export = apiRequest.getString("export");//导出标识
        if(!"1".equals(export)){
//            setBackendPageSize(apiRequest,15);//分页
        }
        String searchType = apiRequest.getString("searchType");
        List<SurveyMoneyDto> list = new ArrayList<SurveyMoneyDto>();
        int count = 0;
        Map map = new HashMap();
        try {
            String startDate = apiRequest.getString("startDate");
            String endDate = apiRequest.getString("endDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            if (startDate != null){
                apiRequest.put("startDate",sdf.parse(startDate));
            }
            if (endDate != null){
                apiRequest.put("endDate",sdf.parse(endDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Integer sourceSupportType = apiRequest.getInt("sourceSupportType");
//        if (sourceSupportType == null){
//            sourceSupportType = 3;
//        }

        if ("survey".equals(searchType)) {
            list = surveyInvestigatorCaseMapper.selectSurveyMoney(apiRequest);
            for (SurveyMoneyDto surveyMoneyDto : list) {
                surveyMoneyDto.setSurveyMoney(DecimalUtil.twoDecimalTOFourFromFive(surveyMoneyDto.getSurveyMoney()));
                surveyMoneyDto.setWageMoney(DecimalUtil.twoDecimalTOFourFromFive(surveyMoneyDto.getWageMoney()));
                surveyMoneyDto.setFitMoney(DecimalUtil.twoDecimalTOFourFromFive(surveyMoneyDto.getSurveyMoney() - surveyMoneyDto.getWageMoney()));
                surveyMoneyDto.setAppMoney(DecimalUtil.twoDecimalTOFourFromFive(surveyMoneyDto.getFitMoney() - surveyMoneyDto.getHisOweMoney()));
            }
        }else if ("entrust".equals(searchType)){

        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }
    @ApiMethod(needLogin = false,descript = "统计调查费用明细",value = "report-survey-money-detail")
    @Override
    public ApiResponse surveyMoneyDetail(ApiRequest apiRequest) {
        String export = apiRequest.getString("export");//导出标识
        if("1".equals(export)){
            List<SurveyMoneyDtoDetail> details = surveyInvestigatorCaseMapper.selectSurveyMoneyDetailExport(apiRequest);
            for (SurveyMoneyDtoDetail detail : details) {
                if (detail.getServicesId() == 13) {
                    detail.setServicesName("深度案件");
                }else{
                    detail.setServicesName("单点调查");
                }
//                String createTime = detail.getOrgCreateTimeStr(); //分派机构时间
//                String reportDate = detail.getOrgStartTimeStr();//提交时间
//
//                int orgDays = GetWorkDay.calLeaveDays(createTime, reportDate,detail.getEfficiencyAttr());
//                detail.setOrgDays(Math.abs(orgDays));//机构时效

                //考核时效
//                Long surveyAssignOrgId = detail.getSurveyAssignOrgId();
//                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
//                Long servicesId = surveyAssignOrg.getServicesId();
//                detail.setAgingDay(7);
//                if(detail.getServicesId() != null){
//                    if(detail.getServicesId() == 11 || detail.getServicesId() == 12){
//                        if(detail.getAreaType() !=null){
//                            switch (detail.getAreaType()){
//                                case 0:detail.setAgingDay(3);break;
//                                case 1:detail.setAgingDay(4);break;
//                                case 2:detail.setAgingDay(4);break;
//                                case 3:detail.setAgingDay(5);break;
//                                case 4:detail.setAgingDay(7);break;
//                                default: break;
//                            }
//                        }
//                    }
//                }
                //如果有延期申请,并且审核通过，考核时效：新的截止日期-分派机构日期
//                if(detail.getExtensionState() != null && detail.getExtensionState() == 2){
//                    int days = GetWorkDay.calLeaveDays(createTime, detail.getUtterEndTime() ==null?detail.getOrgEndTimeStr():detail.getUtterEndTime(),detail.getEfficiencyAttr());
//                    detail.setAgingDay(Math.abs(days));
//                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,details.size(),details);
        }
        setBackendPageSize(apiRequest);
        String searchType = apiRequest.getString("searchType");
        List<SurveyMoneyDtoDetail> list = new ArrayList<SurveyMoneyDtoDetail>();
        int count = 0;
        Map map = new HashMap();
        try {
            String startDate = apiRequest.getString("startDate");
            String endDate = apiRequest.getString("endDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            if (startDate != null && !"".equals(startDate)){
                apiRequest.put("startDate",sdf.parse(startDate));
            }
            if (endDate != null && !"".equals(endDate)){
                apiRequest.put("endDate",sdf.parse(endDate));
            }
            apiRequest.put("orgId",apiRequest.getLong("orgId"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if ("survey".equals(searchType)) {
            String detailType = apiRequest.getString("detailType");
            if("surveyMoneyDetail".equals(detailType)){ //调查金额明细
                count = surveyInvestigatorCaseMapper.selectSurveyMoneyDetailSize(apiRequest);
                list = surveyInvestigatorCaseMapper.selectSurveyMoneyDetail(apiRequest);
                for (SurveyMoneyDtoDetail detail : list) {
                    //机构时效
                    detail.setOrgDays(detail.getCaseDays());
                    int areaType = detail.getAreaType();
                    switch (areaType){
                        case 0:detail.setAreaName("直辖市市区");break;
                        case 1: detail.setAreaName("直辖市郊区");break;
                        case 2: detail.setAreaName("省会");break;
                        case 3: detail.setAreaName("地级市");break;
                        case 4: detail.setAreaName("县级市");break;
                        default:detail.setAreaName("县级市");
                    }
                }
            }else if("entrustMoneyDetail".equals(detailType)){ //委托金额明细
                count = surveyInvestigatorCaseMapper.selectEntruetMoneyDetailSize(apiRequest);
                list = surveyInvestigatorCaseMapper.selectEntruetMoneyDetail(apiRequest);
            }
//            for (SurveyMoneyDtoDetail surveyMoneyDtoDetail : list) {
//                SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyMoneyDtoDetail.getSurveyInvestigatorCaseId());
//                if (surveyInvestigatorCase != null){
//                    surveyMoneyDtoDetail.setSurveyInvestigatorCase(surveyInvestigatorCase);
//                    surveyMoneyDtoDetail.setSurveyRiskCaseInfo(surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId()));
//                    surveyMoneyDtoDetail.setSurveyRiskCase(surveyRiskCaseMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyId()));
//                }
//            }
        }else if ("entrust".equals(searchType)){

        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }


    @ApiMethod(needLogin = false,descript = "自营机构分值清单",value = "survey-self-score")
    @Override
    public ApiResponse surveyScore(ApiRequest apiRequest) {
        String export = apiRequest.getString("export");// value=export 表示是导出
        List<SurveySourceScoreDto> list = new ArrayList<>();
        Map<String,Object> map =  new HashMap<String,Object>();
        Double score = 0D;
        try {
            String startDate = apiRequest.getString("startDate");
            String endDate = apiRequest.getString("endDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (startDate != null && !"".equals(startDate)){
                map.put("startDate",sdf.parse(startDate));
            }
            if (endDate != null && !"".equals(endDate)){
                map.put("endDate",sdf.parse(endDate));
            }
            String searchMonth  = apiRequest.getString("searchMonth");
            if (searchMonth != null && !"".equals(searchMonth)){
                map.put("searchMonth",searchMonth);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        map.put("sourceyType", 1);
        score = surveyInvestigatorCaseMapper.selectScore(map);
        SurveySourceScoreDto dto = new SurveySourceScoreDto("市场一部（郑哲）",score,1);
        if ("export".equals(export)){
            dto.setSourceListMx(surveyInvestigatorCaseMapper.selectScoreDetail(map));
        }
        list.add(dto);

        map.put("sourceyType", 2);
        score = surveyInvestigatorCaseMapper.selectScore(map);
        dto = new SurveySourceScoreDto("市场二部（曹刘强）",score,2);
        if ("export".equals(export)){
            dto.setSourceListMx(surveyInvestigatorCaseMapper.selectScoreDetail(map));
        }
        list.add(dto);

        map.put("sourceyType", 5);
        score = surveyInvestigatorCaseMapper.selectScore(map);
        dto = new SurveySourceScoreDto("市场三部（韩正栋）",score,2);
        if ("export".equals(export)){
            dto.setSourceListMx(surveyInvestigatorCaseMapper.selectScoreDetail(map));
        }
        list.add(dto);
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }

    @ApiMethod(needLogin = false,descript = "自营机构分值清单明细",value = "survey-self-score-detail")
    @Override
    public ApiResponse surveyScoreDetail(ApiRequest apiRequest) {
        Map<String,Object> map =  new HashMap<String,Object>();
        try {
            String startDate = apiRequest.getString("searchStartDate");
            String endDate = apiRequest.getString("searchEndDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (startDate != null && !"".equals(startDate)){
                map.put("startDate",sdf.parse(startDate));
            }
            if (endDate != null && !"".equals(endDate)){
                map.put("endDate",sdf.parse(endDate));
            }
            String searchMonth  = apiRequest.getString("searchMonth");
            if (searchMonth != null && !"".equals(searchMonth)){
                map.put("searchMonth",searchMonth);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        map.put("sourceyType",apiRequest.getLong("searchSourceyType"));
        List<SurveySourceDto> dtos = surveyInvestigatorCaseMapper.selectScoreDetail(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,dtos.size(),dtos);
    }

    public String getSpecifiedDayBefore(String specifiedDay,int days) {//可以用new Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - days);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    //角色判断
    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }


    /**
     * 案源分值 内部构造类
     */
    private class SurveySourceScoreDto{
        private String name;
        private Double value;
        private Integer type;

        private List<SurveySourceDto> sourceListMx;//案源机构分值明细

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public SurveySourceScoreDto(){

        }
        public SurveySourceScoreDto(String name, Double value,Integer type) {
            this.name = name;
            this.value = value;
            this.type = type;
        }

        public List<SurveySourceDto> getSourceListMx() {
            return sourceListMx;
        }

        public void setSourceListMx(List<SurveySourceDto> sourceListMx) {
            this.sourceListMx = sourceListMx;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }

    //保存该调查员的方向 “地址信息”历史记录
    private void saveDirectionAreaHistory(ApiRequest apiRequest, SurveyCaseDirection surveyCaseDirection) {
        try {
            Long currentUserId = getCurrentUserId(apiRequest);

            String districtId = apiRequest.getString("districtId");
            String cityId = apiRequest.getString("cityId");
            String areaId = null;
            if("0".equals(districtId)){
                areaId = cityId;
            }else{
                areaId = districtId;
            }

            Map<String,Object> hisMap = new HashMap<>();
            hisMap.put("surveyUserId",currentUserId);
            hisMap.put("areaId",areaId);
            SurveyDirectionAreaHistory surveyDirectionAreaHistory = surveyDirectionAreaHistoryMapper.selectByInfo(hisMap);

            if(surveyDirectionAreaHistory == null){    //没有历史记录
                SurveyDirectionAreaHistory history = new SurveyDirectionAreaHistory();
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
                history.setSurveyUserId(currentUserId);
                history.setSurveyUserName(userInfo.getUserName());
                history.setProvince(surveyCaseDirection.getProvince());
                history.setProvinceId(surveyCaseDirection.getProvinceId());
                history.setCity(surveyCaseDirection.getCity());
                history.setCityId(surveyCaseDirection.getCityId());
                history.setDistrict(surveyCaseDirection.getDistrict());
                history.setDistrictId(surveyCaseDirection.getDistrictId());
                history.setAreaType(surveyCaseDirection.getAreaType());
                history.setAreaId(Integer.valueOf(areaId));
                history.setAreaName(surveyCaseDirection.getAreaName());
                history.setRegionType(surveyCaseDirection.getRegionType());
                history.setCreateBy(currentUserId);
                history.setCreateByName(userInfo.getUserName());
                history.setCreateTime(new Date());
                history.setDeleteFlag(0);
                history.setCityType(apiRequest.getInt("hisCityType"));
                history.setCityTypeName(apiRequest.getString("hisCityTypeName"));
                surveyDirectionAreaHistoryMapper.insertSelective(history);
            }else{
                SurveyDirectionAreaHistory history = new SurveyDirectionAreaHistory();
                BeanUtils.copyProperties(history,surveyDirectionAreaHistory);
                history.setId(null);
                surveyDirectionAreaHistoryMapper.insert(history);
                surveyDirectionAreaHistoryMapper.deleteByPrimaryKey(surveyDirectionAreaHistory.getId());
            }
            //所有历史记录
            hisMap = new HashMap<>();
            hisMap.put("surveyUserId",currentUserId);
            List<SurveyDirectionAreaHistory> surveyDirectionAreaHistories = surveyDirectionAreaHistoryMapper.list(hisMap);
            if(surveyDirectionAreaHistories.size()>5){ //保持5个历史记录
                surveyDirectionAreaHistoryMapper.deleteByPrimaryKey(surveyDirectionAreaHistories.get(5).getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiMethod(needLogin = false,descript = "调查员退回获取机构信息 调查员信息",value = "primary-veto-get-data-survey-investigator-case")
    @Override
    public ApiResponse primaryVetoGetData(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
        map.put("id",surveyInvestigatorCase.getId());
        map.put("surveyAssorgCaseId",surveyInvestigatorCase.getSurveyAssorgCaseId());
        List<SurveyInvestigatorCase> surveyInvestigatorCases = surveyInvestigatorCaseMapper.selectSurveyInvestigatorCasesByNoId(map);//该机构的其他调查员
        map = new HashMap<>();
        map.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
        map.put("surveyOrgId",surveyInvestigatorCase.getSurveyOrgId());
        List<SurveyAssignOrg> surveyAssignOrgs = surveyAssignOrgMapper.selectSurveyAssignOrgByNoOrgId(map);
        List<SurveyPrimaryVetoDTO> vetos = new LinkedList<>();
        for (SurveyAssignOrg surveyAssignOrg : surveyAssignOrgs) {
            SurveyPrimaryVetoDTO veto = new SurveyPrimaryVetoDTO();
            veto.setId(surveyAssignOrg.getId());
            veto.setName(surveyAssignOrg.getSurveyOrgName());
            veto.setType(1);
            vetos.add(veto);
        }
        for (SurveyInvestigatorCase investigatorCase : surveyInvestigatorCases) {
            SurveyPrimaryVetoDTO veto = new SurveyPrimaryVetoDTO();
            veto.setId(investigatorCase.getId());
            veto.setName(investigatorCase.getSurveyUserName());
            veto.setType(2);
            vetos.add(veto);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,vetos.size(),vetos);
    }

    //计算案件的案件时效
    private Map<String, Object> surveyCaseUserDays(SurveyInvestigatorCaseDto caseDto,int caseUserState, String assDate, String commitDate, String endDate, int efficiencyAttr) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (caseUserState == 4) { //说明是已提交 -- （提交时间-分派时间 计算时效）
//            if (commitDate == null || "".equals(commitDate)){
//                commitDate = endDate;
//            }
////            int days = GetWorkDay.calLeaveDays(assDate, commitDate, efficiencyAttr);
//            int days =agingDay;
//            days = Math.abs(days);
//            if(commitDate.compareTo(endDate) > 0){ //如果“提交时间” 超过“截止时间” 为红色
            if(caseDto.getAgingOver()>0){
                map.put("efficiencyState","时效"+caseDto.getAgingReal().intValue()+"天");
                map.put("efficiencyStateColor","#e51c23");
            }else{
                map.put("efficiencyState","时效"+caseDto.getAgingReal().intValue()+"天");
                map.put("efficiencyStateColor","#3ba9ff");
            }
        }else{//未提交
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
//            if(endDate.compareTo(dateString) > 0){ //未超时
            int days = GetWorkDay.calLeaveDays(dateString,endDate, efficiencyAttr);
            if (caseDto.getAgingOver().intValue() == 0){
//                Double days = caseDto.getAgingCheck()-caseDto.getAgingReal();
                map.put("efficiencyState","剩余"+Math.abs(days)+"天");
                map.put("efficiencyStateColor","#3ba9ff");//蓝色
                if(days <= 2 && days >=0 ){ //
                    map.put("efficiencyStateColor","#ff9800");//黄色
                }
            }else{
//                int days = GetWorkDay.calLeaveDays(endDate,dateString,efficiencyAttr);
                map.put("efficiencyState","超时"+caseDto.getAgingOver().intValue()+"天");
                map.put("efficiencyStateColor","#e51c23");
            }
        }
        return map;
    }

    @ApiMethod(needLogin = false,descript = "查询同事任务",value = "info-survey-investigator-case-colleague")
    public ApiResponse selectColleagueTasks(ApiRequest apiRequest){
        Long surveyInfoId=apiRequest.getLong("id");
        SurveyInvestigatorCaseDto surveyInvestigatorCase=surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyInfoId);
        SurveyConsignor surveyConsignor=surveyConsignorMapper.selectByPrimaryKey(surveyInvestigatorCase.getEntrustOrgId());
        SurveyColleagueTaskDto surveyColleagueTaskDto=new SurveyColleagueTaskDto();
        //当委托方机构是互助时进行查询
        if(surveyConsignor.getOrgAttr()==2 && surveyConsignor.getOrgAttr() != null){
            Map findMap=new HashMap();
            Long surveyUserId=apiRequest.getLong("currentUserId");
            findMap.put("surveyInfoId",surveyInfoId);
            findMap.put("surveyUserId",surveyUserId);
            SurveyFranchisee surveyFranchisee=surveyFranchiseeMapper.selectMechanism(surveyUserId);
            if(surveyFranchisee.getBusType()==1 || surveyFranchisee.getBusType()==3){
                if(surveyFranchisee != null){
                    findMap.put("surveyFranchiseeId",surveyFranchisee.getId());
                    findMap.put("parentId",surveyFranchisee.getParentId());
                }
                List<SurveyInvestigatorCaseDto> list=surveyInvestigatorCaseMapper.selectColleague(findMap);
                if(list != null){
                    for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto:list) {
                        findMap=new HashMap();
                        findMap.put("surveyUserCaseId",surveyInvestigatorCaseDto.getId());
                        findMap.put("surveyUserId",surveyInvestigatorCaseDto.getSurveyUserId());
                        List<SurveyInvestigatorCaseType> caseTypeList= surveyInvestigatorCaseTypeMapper.list(findMap);
                        for (SurveyInvestigatorCaseType surveyInvestigatorCaseType:caseTypeList) {
                            //获取任务类型颜色
                            SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyInvestigatorCaseType.getTaskId());
                            if(surveyTaskInfo != null){
                                surveyInvestigatorCaseType.setTaskColor(surveyTaskInfo.getColor());
                            }
                        }
                        surveyInvestigatorCaseDto.setTasks(caseTypeList);
                    }
                }
                //            map.put("list",list);
                surveyColleagueTaskDto.setSurveyInvestigatorCaseDto(list);

                //调查方向
                findMap=new HashMap();
                findMap.put("surveyInfoId",surveyInfoId);
                findMap.put("surveyUserId",surveyUserId);
                findMap.put("surveyFranchiseeId",surveyFranchisee.getId());
                findMap.put("parentId",surveyFranchisee.getParentId());
                List<SurveyCaseDirectionDto> caseDirectionList= surveyCaseDirectionMapper.selectDirectionColleagues(findMap);
                for (SurveyCaseDirectionDto surveyCaseDirectionDto:caseDirectionList) {
                    if(surveyCaseDirectionDto.getTaskId() != null){
                        //获取任务类型颜色
                        SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyCaseDirectionDto.getTaskId());
                        if(surveyTaskInfo != null){
                            surveyCaseDirectionDto.setTaskColor(surveyTaskInfo.getColor());
                        }
                    }
                    if(surveyCaseDirectionDto.getNewId() != null){
                        //获取任务子类颜色
                        SurveyTaskInfoContent surveyTaskInfoContent = surveyTaskInfoContentMapper.selectByPrimaryKey(surveyCaseDirectionDto.getNewId());
                        if(surveyTaskInfoContent != null){
                            surveyCaseDirectionDto.setNewColor(surveyTaskInfoContent.getColor());
                        }
                    }
                    //获取图片信息
                    Map mapFile = new HashMap();
                    mapFile.put("directionId",surveyCaseDirectionDto.getId());
                    List<SurveyCaseDirectionFileDto> surveyCaseDirectionFileDtos = surveyCaseDirectionFileMapper.list(mapFile);
                    surveyCaseDirectionDto.setSurveyCaseDirectionFiles(surveyCaseDirectionFileDtos);
                    surveyCaseDirectionDto.setSurveyCaseDirectionFilesSize(surveyCaseDirectionFileDtos.size());

                    StringBuffer huzhuColsRemark = new StringBuffer();
                    String taskName = surveyCaseDirectionDto.getTaskName();
                    switch (taskName)
                    {
                        case "面访患病成员及申请人":
                        case "面访患病成员家属":
                            if (surveyCaseDirectionDto.getHuzhuDate() !=  null){
                                huzhuColsRemark.append("面访时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirectionDto.getHuzhuDate()));
                            }
                            huzhuColsRemark.append("\n面访对象与患病查员关系：" + surveyCaseDirectionDto.getAttr1Obj());
                            huzhuColsRemark.append("\n面访对象姓名：" + surveyCaseDirectionDto.getAttr1ObjName());
                            huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirectionDto.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirectionDto.getSunRemark()) : "否"));
                            break;
                        case "走访就诊医疗机构":
                        case "走访出生医疗机构":
                        case "居住地医疗机构排查":
                        case "工作地医疗机构排查":
                        case "出险地医疗机构排查":
                        case "户籍所在地医疗机构排查":
                        case "走访街道办事处或村委会":
                            if (surveyCaseDirectionDto.getHuzhuDate() !=  null){
                                huzhuColsRemark.append("排查时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirectionDto.getHuzhuDate()));
                            }
                            huzhuColsRemark.append("\n排查类型：" + surveyCaseDirectionDto.getAttr2Type());
                            huzhuColsRemark.append("\n排查科室：" + surveyCaseDirectionDto.getAttr2His());
                            huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirectionDto.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirectionDto.getSunRemark()) : "否"));
                            break;
                        case "走访居住地":
                        case "走访户籍所在地":
                        case "走访工作单位":
                        case "走访疾控防疫中心":
                        case "走访公检法等机关单位":
                        case "走访鉴定机构":
                        case "走访材料出具机构":
                            if (surveyCaseDirectionDto.getHuzhuDate() !=  null){
                                huzhuColsRemark.append("走访时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirectionDto.getHuzhuDate()));
                            }
                            huzhuColsRemark.append("\n走访对象：" + surveyCaseDirectionDto.getAttr3Obj());
                            huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirectionDto.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirectionDto.getSunRemark()) : "否"));
                            break;
                        case "商保排查":
                            huzhuColsRemark.append("\n商保排查：" + surveyCaseDirectionDto.getAttr4Name1());
                            huzhuColsRemark.append("\n商保排查结论：" + surveyCaseDirectionDto.getAttr4Remark1());
                            huzhuColsRemark.append("\n互助排查：" + surveyCaseDirectionDto.getAttr4Name2());
                            huzhuColsRemark.append("\n互助排查结论：" + surveyCaseDirectionDto.getAttr4Remark2());
                            break;
                        case "社保排查":
                        case "药店排查":
                        case "体检机构排查": // 和 社保排查药店排查 。所有字段一样 所以写一起
                            if (surveyCaseDirectionDto.getHuzhuDate() !=  null){
                                huzhuColsRemark.append("排查时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirectionDto.getHuzhuDate()));
                            }
                            huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirectionDto.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirectionDto.getSunRemark()) : "否"));
                            break;
                        case "事故地点排查":
                        case "事故处理机构排查": // 和 事故地点排查 。所有字段一样 所以写一起
                            if (surveyCaseDirectionDto.getHuzhuDate() !=  null){
                                huzhuColsRemark.append("排查时间："  + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirectionDto.getHuzhuDate()));
                            }
                            break;
                        default:
                            break;
                    }
                    huzhuColsRemark.append("\n是否有录音：" + (surveyCaseDirectionDto.getHaveSound() !=null?(surveyCaseDirectionDto.getHaveSound() == 1 ? "是":"否"):""));
                    if (taskName.equals("居住地医疗机构排查") || taskName.equals("走访就诊医疗机构") || taskName.equals("工作地医疗机构排查") || taskName.equals("社保排查")
                            ||taskName.equals("体检机构排查") ||taskName.equals("商保排查") || taskName.equals("药店排查") || taskName.equals("出险地医疗机构排查") || taskName.equals("户籍所在地医疗机构排查")){
                        huzhuColsRemark.append("\n是否获得屏拍或纸质材料：" + (surveyCaseDirectionDto.getHaveFile()!=null?(surveyCaseDirectionDto.getHaveFile() == 1 ? "是":"否"):""));
                    }
                    surveyCaseDirectionDto.setHuzhuColsRemark(huzhuColsRemark.toString());
                    surveyCaseDirectionDto.setHuzhuSunStr(surveyCaseDirectionDto.getSun() == 1 ? "是" : "否");
                }
                surveyColleagueTaskDto.setSurveyCaseDirectionDto(caseDirectionList);
                //            map.put("caseDirectionList",caseDirectionList);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyColleagueTaskDto);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyColleagueTaskDto);
    }

    @ApiMethod(needLogin = false,descript = "根据条件查询单条数据",value = "info-survey-investigator-selectOne")
    public ApiResponse selectOne(ApiRequest apiRequest){
        Map map=new HashMap();
        Long id=apiRequest.getLong("surveyInvestigatorCaseId");
        map.put("id",id);
        SurveyInvestigatorCase surveyInvestigatorCase=surveyInvestigatorCaseMapper.selectOne(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyInvestigatorCase);
    }

    @ApiMethod(needLogin = false,descript = "初审修改调查员是否阳性",value = "info-survey-investigator-update-issun")
    public ApiResponse updateIsSun(ApiRequest apiRequest){
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String btnCode=apiRequest.getString("btnCode");
        if("updateIsSun".equals(btnCode)){
            Long id=apiRequest.getLong("id");
            Integer isSun=apiRequest.getInt("isSun");
            SurveyInvestigatorCase surveyInvestigatorCase=surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
            if(isSun == 0){
                surveyInvestigatorCase.setSunTime(null);
            }else{
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String dateTime=df.format(new Date());// new Date()为获取当前系统时间
                surveyInvestigatorCase.setSunTime(DateUtils.strToDate(dateTime));
                surveyInvestigatorCase.setSunUserId(userInfo.getUserId());
                surveyInvestigatorCase.setSunUserName(userInfo.getUserName());
            }
            surveyInvestigatorCase.setIsSun(isSun);
            Integer count=surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,count);
        }
        return null;
    }

    @ApiMethod(needLogin = false,descript = "根据案件id查询阳性奖励",value = "info-survey-investigator-case-reward")
    public ApiResponse selectReward(ApiRequest apiRequest){
        SurveyRiskCaseInfoDto surveyRiskCaseInfoDto=surveyRiskCaseInfoMapper.selectByPrimaryKey(apiRequest.getLong("id"));
        if(surveyRiskCaseInfoDto != null){
            SurveyConsignor surveyConsignor=surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfoDto.getEntrustOrgId());
            if(surveyConsignor.getOrgAttr() == 1){
                Map map=new HashMap();
                map.put("surveyInfoId",surveyRiskCaseInfoDto.getId());
                map.put("type",1);
                map.put("isSun",1);
                List<SurveyInvestigatorCase> list=surveyInvestigatorCaseMapper.selectInformation(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
    }

    @ApiMethod(needLogin = false,descript = "阳性奖励报表",value = "survey-investigator-case-reward-report")
    public ApiResponse selectRewardReport(ApiRequest apiRequest){
        String menuType=apiRequest.getString("menuType");
        Map findMap=new HashMap();
        if("investigator".equals(menuType)){//根据机构id查询机构下的调查员
            String orgIds=apiRequest.getString("orgIds");
            if(orgIds != null && !orgIds.equals("") ) {
                String[] orgIdsString = orgIds.split(",");
                List<Long> orgIdsList = new ArrayList<>();
                for (int i = 0; i < orgIdsString.length; i++) {
                    orgIdsList.add(Long.parseLong(orgIdsString[i]));
                }
                findMap.put("orgIdsList", orgIdsList);
            }
            findMap.put("type",1);
            findMap.put("busType",1);
            List<SurveyInvestigator> list=surveyInvestigatorMapper.selectByMap(findMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
        }else if("reportdata".equals(menuType)){//根据条件查询报表数据
            String orgIds=apiRequest.getString("orgIds");
            if(orgIds != null && !orgIds.equals("")){
                String [] orgIdsString=orgIds.split(",");
                List<Long> orgIdsList=new ArrayList<>();
                for (int i = 0; i < orgIdsString.length; i++) {
                    orgIdsList.add(Long.parseLong(orgIdsString[i]));
                }
                findMap.put("orgIdsList",orgIdsList);
            }
            String userIds=apiRequest.getString("userIds");
            if(userIds != null && !userIds.equals("")){
                String [] userIdsString=userIds.split(",");
                List<Long> userIdsList=new ArrayList<>();
                for (int i = 0; i < userIdsString.length; i++) {
                    userIdsList.add(Long.parseLong(userIdsString[i]));
                }
                findMap.put("userIdsList",userIdsList);
            }
            String startTime=apiRequest.getString("startTime");
            String endTime=apiRequest.getString("endTime");
            findMap.put("startTime",startTime);
            findMap.put("endTime",endTime);
            List<SurveyInvestigatorPositiveRewardDto> list=surveyInvestigatorCaseMapper.selectPositiveReward(findMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
        }else if("reportdataDetails".equals(menuType)) {//根据条件查询报表数据详情
            Long userId=apiRequest.getLong("userId");
            String startTime=apiRequest.getString("startTime");
            String endTime=apiRequest.getString("endTime");
            findMap.put("startTime",startTime);
            findMap.put("endTime",endTime);
            findMap.put("userId",userId);
            List<SurveyInvestigatorPositiveRewardDto> list=surveyInvestigatorCaseMapper.selectPositiveRewardDetails(findMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
    }
}
