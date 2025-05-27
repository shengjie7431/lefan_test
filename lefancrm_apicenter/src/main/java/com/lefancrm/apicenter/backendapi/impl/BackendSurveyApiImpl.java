package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.backendapi.BackendSurveyApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.dto.finacial.FinancialFileTableEnumDto;
import com.lefancrm.apicenter.model.CommonFile;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.*;
import com.lefancrm.apicenter.util.pinganfu.StringUtil;
import com.lefancrm.apicenter.util.wechatPay.util.MD5Util;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wangwei on 2018/12/24.
 * “狄大人”数据管理
 */
@Service
@ApiService(descript = "狄大人API")
public class BackendSurveyApiImpl extends BaseServiceImpl implements BackendSurveyApi {
    @Autowired
    private SurveyAchieveRuleMapper surveyAchieveRuleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyBusinessTypeMapper surveyBusinessTypeMapper;
    @Autowired
    private SurveyTaskInfoMapper surveyTaskInfoMapper;
    @Autowired
    private SurveyFileCatalogMapper surveyFileCatalogMapper;
    @Autowired
    private SurveyLevelMapper surveyLevelMapper;
    @Autowired
    private SurveyIntroductionMapper surveyIntroductionMapper;
    @Autowired
    private SurveyServiceAdvantageMapper surveyServiceAdvantageMapper;
    @Autowired
    private SurveyConsignerMapper surveyConsignerMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyLfcoinExplainMapper surveyLfcoinExplainMapper;
    @Autowired
    private SurveyLfcoinRuleMapper surveyLfcoinRuleMapper;
    @Autowired
    private SurveyKnowledgeTypeMapper surveyKnowledgeTypeMapper;
    @Autowired
    private SurveyKnowledgeBaseMapper surveyKnowledgeBaseMapper;
    @Autowired
    private SurveyConsignorPriceMapper surveyConsignorPriceMapper;
    @Autowired
    private SurveyFranchiseePriceMapper surveyFranchiseePriceMapper;
    @Autowired
    private SurveyQaMapper surveyQaMapper;
    @Autowired
    private SurveyKnowledgeCommentMapper surveyKnowledgeCommentMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyServiceTypeMapper surveyServiceTypeMapper;
    @Autowired
    private SurveyProductMapper surveyProductMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private SurveyOrderMapper surveyOrderMapper;
    @Autowired
    private SurveyCommonAreaPriceMapper surveyCommonAreaPriceMapper;
    @Autowired
    private SurveyLevelExplainMapper surveyLevelExplainMapper;
    @Autowired
    private SurveyProductLevelMapper surveyProductLevelMapper;
    @Autowired
    private SurveyProductRoleMapper surveyProductRoleMapper;
    @Autowired
    private SurveyConsignerFileMapper surveyConsignerFileMapper;
    @Autowired
    private SurveyServiceAreaMapper surveyServiceAreaMapper;
    @Autowired
    private BusinessRoleMapper businessRoleMapper;
    @Autowired
    private SurveyInvestigatorAreaPriceMapper surveyInvestigatorAreaPriceMapper;
    @Autowired
    private SurveyMessageMapper surveyMessageMapper;
    @Autowired
    private SurveyLfcoinDetailMapper surveyLfcoinDetailMapper;
    @Autowired
    private SurveyAchieveDetailMapper surveyAchieveDetailMapper;
    @Autowired
    private SurveyPunishMapper surveyPunishMapper;
    @Autowired
    private CommonAreaMapper commonAreaMapper;
    @Autowired
    private SurveyCashInfoMapper surveyCashInfoMapper;
    @Autowired
    private SurveyConsignorDepartmentMapper surveyConsignorDepartmentMapper;
    @Autowired
    private SurveyTaskInfoContentMapper surveyTaskInfoContentMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPromotedMapper userPromotedMapper;
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyBankCardMapper surveyBankCardMapper;
    @Autowired
    private SurveyCashInfoDetailMapper surveyCashInfoDetailMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private SurveyCashInfoRecordMapper surveyCashInfoRecordMapper;
    @Autowired
    private BillingApplyCorporationMapper billingApplyCorporationMapper;
    @Autowired
    private BillingApplyCorporationEnumMapper  billingApplyCorporationEnumMapper;
    @Autowired
    private CommonEnumMapper  commonEnumMapper;
    @Autowired
    private BillingApplyEnumItemMapper  billingApplyEnumItemMapper;
    @Autowired
    private BusApplyProductRoleMapper busApplyProductRoleMapper;
    @Autowired
    private BusApplyEnumRoleMapper busApplyEnumRoleMapper;
    @Autowired
    private BusApplyItemRoleMapper busApplyItemRoleMapper;
    @Autowired
    private BusApplyOrgRoleMapper busApplyOrgRoleMapper;
    @Autowired
    private BusApplyCorporationRoleMapper busApplyCorporationRoleMapper;
    @Autowired
    private BillingApplyProductTypeMapper billingApplyProductTypeMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;
    @Autowired
    private BillingApplyProductOrgMapper billingApplyProductOrgMapper;
    @Autowired
    private SurveyUserConsignorMapper surveyUserConsignorMapper;
    @Autowired
    private SurveyUserFranchiseeMapper surveyUserFranchiseeMapper;
    @Autowired
    private SurveyModelInfoMapper surveyModelInfoMapper;
    @Autowired
    private SurveyConsignorModelMapper surveyConsignorModelMapper;
    @Autowired
    private SurveyConsignorReportRuleMapper surveyConsignorReportRuleMapper;
    @Autowired
    private SurveyDirectionAreaHistoryMapper surveyDirectionAreaHistoryMapper;
    @Autowired
    private SurveyConsignorBillSubjectMapper surveyConsignorBillSubjectMapper;
    @Autowired
    private BillingApplyCompanyMapper billingApplyCompanyMapper;
    @Autowired
    private SurveyBusinessTaskTypeMapper surveyBusinessTaskTypeMapper;
    @Autowired
    private SurveyDirectionResultTypeMapper surveyDirectionResultTypeMapper;
    @Autowired
    private SurveyTaskDirectionResultMapper surveyTaskDirectionResultMapper;
    @Autowired
    private BackendSurveyBusinessTypeApiImpl backendSurveyBusinessTypeApiImpl;
    @Autowired
    private SurveyPriceModelMapper surveyPriceModelMapper;
    @Autowired
    private SurveyPriceModelOrgMapper surveyPriceModelOrgMapper;
    @Autowired
    private SurveyPriceModelAreaCategoriesMapper surveyPriceModelAreaCategoriesMapper;
    @Autowired
    private SurveyAreaCategoriesAreaCityMapper surveyAreaCategoriesAreaCityMapper;
    @Autowired
    private SurveyPriceMapper surveyPriceMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;
    @Autowired
    private SurveyConsignorEfficiencyModelMapper surveyConsignorEfficiencyModelMapper;
    @Autowired
    private SurveyConsignorEfficiencyModelOrgMapper surveyConsignorEfficiencyModelOrgMapper;
    @Autowired
    private SurveyConsignorEfficiencyModelInfoMapper surveyConsignorEfficiencyModelInfoMapper;
    @Autowired
    private SurveyUserSignMapper surveyUserSignMapper;
    @Autowired
    private SurveyInvestigatorRewordMidMapper surveyInvestigatorRewordMidMapper;
    @Autowired
    private SurveyTeachRewardListMapper surveyTeachRewardListMapper;
    @Autowired
    private StaffOrganMapper staffOrganMapper;
    @Autowired
    private StaffBudgetCompanyMapper staffBudgetCompanyMapper;
    @Autowired
    private SurveyEmailInfoMapper surveyEmailInfoMapper;
    @Autowired
    private SurveyEmailInfoOrgMapper surveyEmailInfoOrgMapper;
    @Autowired
    private SurveyConsignorEfficiencyModelAreaMapper surveyConsignorEfficiencyModelAreaMapper;
    @Autowired
    private SurveyConsignorEfficiencyAreaCityMapper surveyConsignorEfficiencyAreaCityMapper;
    @Autowired
    private SurveyChannelModelMapper surveyChannelModelMapper;
    @Autowired
    private SurveyChannelModelOrgMapper surveyChannelModelOrgMapper;
    @Autowired
    private SurveyChannelModelAreaMapper surveyChannelModelAreaMapper;
    @Autowired
    private SurveyChannelModelAreaCityMapper surveyChannelModelAreaCityMapper;
    @Autowired
    private SurveyChannelModelInfoMapper surveyChannelModelInfoMapper;
    @Autowired
    private SurveyFranchiseeAreaCityMapper surveyFranchiseeAreaCityMapper;
    @Autowired
    private SurveyConsignorAreaCityMapper surveyConsignorAreaCityMapper;
    @Autowired
    private SurveyScoreModelMapper surveyScoreModelMapper;
    @Autowired
    private SurveyScoreModelAreaMapper surveyScoreModelAreaMapper;
    @Autowired
    private SurveyScoreModelInfoMapper surveyScoreModelInfoMapper;
    @Autowired
    private SurveyScoreModelOrgMapper surveyScoreModelOrgMapper;
    @Autowired
    private SurveyScoreModelAreaCityMapper surveyScoreModelAreaCityMapper;
    @Autowired
    private BackendFinancialFileApiImpl backendFinancialFileApiImpl;
    @Autowired
    private SurveyConsignerDepartmentMapper surveyConsignerDepartmentMapper;

    @Value("${survey.role}")
    private String surveyRole; //调查角色（场景：移除调查员时，清除role）
    /**
     * 狄大人 list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "狄大人 list", value = "backend-survey-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        //不分页
        if(apiReq.getString("menuType") == null){
            this.setBackendPageSize(apiReq);
        }
        String surveyCode = apiReq.getString("surveyCode");
        Long currentUserId = getCurrentUserId(apiReq);
        int count = 1;
        List list = new ArrayList();

        //领域类型
        if("businessType".equals(surveyCode)){
            count = surveyBusinessTypeMapper.listSize(apiReq);
            list = surveyBusinessTypeMapper.list(apiReq);
        }
        //业务类型
        else if("serviceType".equals(surveyCode)){
            count = surveyServiceTypeMapper.listSize(apiReq);
            list = surveyServiceTypeMapper.list(apiReq);
        }
        //任务类型
        else if("taskInfo".equals(surveyCode)){
            count = surveyTaskInfoMapper.listSize(apiReq);
            list = surveyTaskInfoMapper.list(apiReq);
        }
        //材料目录
        else if("fileCatalog".equals(surveyCode)){
            count = surveyFileCatalogMapper.listSize(apiReq);
            list = surveyFileCatalogMapper.list(apiReq);
        }
        //调查员登记
        else if("level".equals(surveyCode)){
            count = surveyLevelMapper.listSize(apiReq);
            list = surveyLevelMapper.list(apiReq);
        }
        //称号特权详情
        else if("levelExplain".equals(surveyCode)){
            count = surveyLevelExplainMapper.listSize(apiReq);
            list = surveyLevelExplainMapper.list(apiReq);
        }
        //平台介绍
        else if("introduction".equals(surveyCode)){
            count = surveyIntroductionMapper.listSize(apiReq);
            list = surveyIntroductionMapper.list(apiReq);
        }
        //服务优势
        else if("serviceAdvantage".equals(surveyCode)){
            count = surveyServiceAdvantageMapper.listSize(apiReq);
            list = surveyServiceAdvantageMapper.list(apiReq);
        }
        //委托人认证
        else if("consigner".equals(surveyCode)){
            Long departmentId = apiReq.getLong("departmentId");
            if(departmentId != null){
                list = surveyConsignerMapper.selectSurveyConsignerByDepartmentId(departmentId);
                for (Object o : list) {
                    SurveyConsigner surveyConsigner = (SurveyConsigner)o;
                    Map<String,Object> paramMap = new HashMap<>();
                    paramMap.put("consignorOrgId",surveyConsigner.getEntrustOrgId());
                    paramMap.put("consignerUserId",surveyConsigner.getId());
                    List<SurveyConsignerDepartment> consignerDepartments = surveyConsignerDepartmentMapper.list(paramMap);
                    if (consignerDepartments.size() > 0) {
                        String names = consignerDepartments.stream().map(SurveyConsignerDepartment::getConsignorDepartmentName).collect(Collectors.joining(" "));
                        surveyConsigner.setDepartmentNames(names);
                    }
                }
                count = list.size();
            }else{
                count = surveyConsignerMapper.listSize(apiReq);
                list = surveyConsignerMapper.list(apiReq);
                for (Object o : list) {
                    SurveyConsigner surveyConsigner = (SurveyConsigner)o;
                    Map<String,Object> paramMap = new HashMap<>();
                    paramMap.put("consignorOrgId",surveyConsigner.getEntrustOrgId());
                    paramMap.put("consignerUserId",surveyConsigner.getId());
                    List<SurveyConsignerDepartment> consignerDepartments = surveyConsignerDepartmentMapper.list(paramMap);
                    if (consignerDepartments.size() > 0) {
                        String names = consignerDepartments.stream().map(SurveyConsignerDepartment::getConsignorDepartmentName).collect(Collectors.joining(" "));
                        surveyConsigner.setDepartmentNames(names);
                    }
                }
            }
        }
        else if("clientConsignor".equals(surveyCode)){
//            count = surveyConsignorMapper.listPageSize(apiReq);
//            list = surveyConsignorMapper.listPage(apiReq);
            List<SurveyConsignorModel> temp = surveyConsignorModelMapper.list(new HashMap());
            count = surveyConsignorMapper.listSize(apiReq);
            List<SurveyConsignor> tempList = surveyConsignorMapper.list(apiReq);
            for (SurveyConsignor surveyConsignor : tempList) {
                List<SurveyConsignorModel> collect = temp.stream().filter(p -> p.getConsignorId().intValue() == surveyConsignor.getId().intValue()).collect(Collectors.toList());
                if (collect.size() > 0){
                    surveyConsignor.setModelName(collect.get(0).getModelName());
                }
                StringBuffer ruleName = new StringBuffer(1000);
                SurveyConsignorReportRule surveyConsignorReportRule = surveyConsignorReportRuleMapper.selectByConsignorId(surveyConsignor.getId());
                if (surveyConsignorReportRule != null){
                    switch (surveyConsignorReportRule.getOneRule()){
                        case 1 : ruleName.append("保单号+"); break;
                        case 2 : ruleName.append("被保险人姓名+"); break;
                        case 3 : ruleName.append("理赔编号+"); break;
                        case 4 : ruleName.append("保险公司名称+"); break;
                        case 5 : ruleName.append(""); break;
                        case 6 : ruleName.append(surveyConsignorReportRule.getOneRuleStr()); break;
                        case 7 : ruleName.append("部门名称+"); break;
                    }
                    switch (surveyConsignorReportRule.getTwoRule()){
                        case 1 : ruleName.append("保单号+"); break;
                        case 2 : ruleName.append("被保险人姓名+"); break;
                        case 3 : ruleName.append("理赔编号+"); break;
                        case 4 : ruleName.append("保险公司名称+"); break;
                        case 5 : ruleName.append(""); break;
                        case 6 : ruleName.append(surveyConsignorReportRule.getTwoRuleStr()); break;
                        case 7 : ruleName.append("部门名称+"); break;
                    }
                    switch (surveyConsignorReportRule.getThreeRule()){
                        case 1 : ruleName.append("保单号+"); break;
                        case 2 : ruleName.append("被保险人姓名+"); break;
                        case 3 : ruleName.append("理赔编号+"); break;
                        case 4 : ruleName.append("保险公司名称+"); break;
                        case 5 : ruleName.append(""); break;
                        case 6 : ruleName.append(surveyConsignorReportRule.getThreeRuleStr()); break;
                        case 7 : ruleName.append("部门名称+"); break;
                    }
                    switch (surveyConsignorReportRule.getFourRule()){
                        case 1 : ruleName.append("保单号+"); break;
                        case 2 : ruleName.append("被保险人姓名+"); break;
                        case 3 : ruleName.append("理赔编号+"); break;
                        case 4 : ruleName.append("保险公司名称+"); break;
                        case 5 : ruleName.append(""); break;
                        case 6 : ruleName.append(surveyConsignorReportRule.getFourRuleStr()); break;
                        case 7 : ruleName.append("部门名称+"); break;
                    }
                }
                surveyConsignor.setRuleName(ruleName.toString());
                if (surveyConsignor.getRuleName().length() > 0){
                    String subStr = surveyConsignor.getRuleName().substring(0, surveyConsignor.getRuleName().length() - 1);
                    surveyConsignor.setRuleName(subStr);
                }
            }
            list.clear();
            list.addAll(tempList);
        }
        //委托方机构
        else if("consignor".equals(surveyCode)){
            String btnCode = apiReq.getString("btnCode");
            if("myInfo".equals(btnCode)){//首页时，获取自身的委托机构
                SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByUserId(currentUserId);
                if (surveyConsigner != null) {
                    apiReq.put("condition", 1);
                    apiReq.put("entrustOrgId", surveyConsigner.getEntrustOrgId());
                }
            }
            if ("project".equals(apiReq.getString("project"))){//项目组报表的委托方机构
                Long curUserId = getCurrentUserId(apiReq);
                Map<String,Object> searchMap = new HashMap<>();
                searchMap.put("userId",curUserId);
                List<SurveyUserConsignor> tempList = surveyUserConsignorMapper.list(searchMap);
                String entrustOrgIds = tempList.stream().map(p -> p.getConsignorId().toString()).collect(Collectors.joining(","));
                apiReq.put("entrustOrgIds",entrustOrgIds);
            }

            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean roleUser = isRoleUser(userRoles, 144L);
            if (roleUser){
                apiReq.put("orgAttr",1);
                apiReq.put("busAttr144",144);
            }
            count = surveyConsignorMapper.listSize(apiReq);
            list = surveyConsignorMapper.list(apiReq);
        }
        //调查方认证
        else if("investigator".equals(surveyCode)){
            String btnCode = apiReq.getString("btnCode");
            Long orgId = 58L; //分派机构的id，默认“测试机构”
            if ("113".equals(btnCode) || "116".equals(btnCode) || "report".equals(btnCode)){ //report--调查员报表时使用到
                if ("116".equals(btnCode)){
                    orgId = apiReq.getLong("currentOrgId");
                    apiReq.put("orgId",orgId);
                }else{
                    SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                    if (surveyInvestigator == null) {
                        return new ApiResponse(ApiMsgEnum.SURVEY_INVESTIGATOR);
                    }
                    orgId = surveyInvestigator.getOrgId();
                    apiReq.put("orgId",orgId);
                }
            }
            if("myInfo".equals(btnCode)){//首页时，获取自身的调查员数据
//                apiReq.put("condition",1);
                apiReq.put("userId",currentUserId);
            }
            else if("otherInfo".equals(btnCode)){//首页时，获取点击的调查员数据
//                apiReq.put("condition",1);
                apiReq.put("userId",apiReq.getString("surveyUserId"));
            }
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean whole = isRoleUser(userRoles,91L);
            Boolean wholeTwo = isRoleUser(userRoles,96L);
            if ((whole || wholeTwo) && "survey".equals(apiReq.getString("menuCode"))){
                apiReq.remove("userId");
                // 只筛选调查员     我是醉了！！！  为了暂时解决问题。才这么写。   麻烦以后改掉！！！！！谢谢！
                apiReq.put("busType",1);//只查询保司或者保司+互助的调查员
                btnCode = "realInvest";
            }
            count = surveyInvestigatorMapper.listSize(apiReq);
            list = surveyInvestigatorMapper.list(apiReq);
            if ("111".equals(btnCode) || "113".equals(btnCode) || "115".equals(btnCode) || "116".equals(btnCode)){
//                List<SurveyInvestigator> surveyInvestigator = surveyInvestigatorMapper.list(apiReq);
//                list = new ArrayList();
//                for (int i = 0; i < surveyInvestigator.size(); i++) {
//                    String roles = businessRoleMapper.selectInvestigatorRoles(surveyInvestigator.get(i).getUserId());
//                    if (roles != null) {
//                        String[] role = roles.split(",");
//                        for (String ro : role) {
//                            if ("50".equals(ro)) {
//                                list.add(surveyInvestigator.get(i));
//                            }
//                        }
//                    }
//                }
                //获取“调查员角色-50”的surveyInvestigator
                apiReq.put("roleId",50);
                list = surveyInvestigatorMapper.selectInfoAndCaseNum(orgId);
            }
            if("realInvest".equals(btnCode)){
                Map<String,Object> map = new HashMap<>();
                map.put("roleId",50);
                List<BusUserRole> busUserRoles = busUserRoleMapper.selectBusInfo(map);
                List<SurveyInvestigator> surveyInvestigators =  new ArrayList<>();
                for (SurveyInvestigator surveyInvestigator : (List<SurveyInvestigator>)list) {
                    for (BusUserRole busUserRole : busUserRoles) {
                        if (surveyInvestigator.getUserId().intValue() == busUserRole.getUserId().intValue()) {
                            surveyInvestigators.add(surveyInvestigator);
                        }
                    }
                }
                list.clear();
                list.addAll(surveyInvestigators);
            }
            if ("whole".equals(btnCode)) {
                Map<String,Object> map = new HashMap<>();
                map.put("busType",1);
                list = surveyInvestigatorMapper.selectByMapJurisdiction(map);
            }
        }
        //乐凡币介绍
        else if("lfcoinExplain".equals(surveyCode)){
            count = surveyLfcoinExplainMapper.listSize(apiReq);
            list = surveyLfcoinExplainMapper.list(apiReq);
        }
        //乐凡币规则
        else if("lfcoinRule".equals(surveyCode)){
            count = surveyLfcoinRuleMapper.listSize(apiReq);
            list = surveyLfcoinRuleMapper.list(apiReq);
        }
        //成就点规则
        else if("achieveRule".equals(surveyCode)){
            count = surveyAchieveRuleMapper.listSize(apiReq);
            list = surveyAchieveRuleMapper.list(apiReq);
        }
        //帖子类别
        else if("knowledgeType".equals(surveyCode)){
            count = surveyKnowledgeTypeMapper.listSize(apiReq);
            list = surveyKnowledgeTypeMapper.list(apiReq);
        }
        //论坛帖子
        else if("knowledgeBase".equals(surveyCode)){
            count = surveyKnowledgeBaseMapper.listSize(apiReq);
            list = surveyKnowledgeBaseMapper.list(apiReq);
        }
        //帖子评论
        else if("knowledgeComment".equals(surveyCode)){

        }
        //调查委托方价格
        else if("consignorPrice".equals(surveyCode)){
            count = surveyConsignorPriceMapper.listSize(apiReq);
            list = surveyConsignorPriceMapper.list(apiReq);
        }
        //调查调查方价格
        else if("franchiseePrice".equals(surveyCode)){
            count = surveyFranchiseePriceMapper.listSize(apiReq);
            list = surveyFranchiseePriceMapper.list(apiReq);
        }
        //QA问答
        else if("qa".equals(surveyCode)){
            count = surveyQaMapper.listSize(apiReq);
            list = surveyQaMapper.list(apiReq);
        }
        //加盟运营调查方
        else if ("InvestigatorFranchisee".equals(surveyCode)){
            count = surveyFranchiseeMapper.listPageSize(apiReq);
            list = surveyFranchiseeMapper.listPage(apiReq);
        }
        //调查调查方
        else if("franchisee".equals(surveyCode)){
            String btnCode = apiReq.getString("btnCode");
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if("myInfo".equals(btnCode)){//首页时，获取自身的调查机构
                if (surveyInvestigator != null) {
                    apiReq.put("condition", 1);
                    apiReq.put("franchiseeId", surveyInvestigator.getOrgId());
                }
            }else if("myHzInfo".equals(btnCode)){ //互助积分报表
                String scoreRole = apiReq.getString("scoreRole");
                apiReq.put("condition", 1);
                apiReq.put("franchiseeId", surveyInvestigator.getOrgId());
                if("provincialManger".equals(scoreRole)){ // 省级机构
                    apiReq.put("level", 1);
                }else if("areaManger".equals(scoreRole)){ //片区机构
                    apiReq.put("level", 2);
                }
            }else if("assessmentIndexBtn".equals(btnCode)){ //考核指标报表
                String scoreRole = apiReq.getString("scoreRole");
                apiReq.put("condition", 1);
                apiReq.put("franchiseeId", surveyInvestigator.getOrgId());
                if("provincialManger".equals(scoreRole)){ // 省级机构
                    apiReq.put("level", 1);
                }else if("areaManger".equals(scoreRole)){ //片区机构
                    apiReq.put("level", 2);
                }
            }else if("1000".equals(btnCode)){ //分值清单
                apiReq.put("surveyOrgIds", "");
            }
            count = surveyFranchiseeMapper.listSize(apiReq);
            list = surveyFranchiseeMapper.list(apiReq);
        }
        //商品
        else if("product".equals(surveyCode)){
            count = surveyProductMapper.listSize(apiReq);
            list = surveyProductMapper.list(apiReq);
        }
        //委托方默认价格
        else if("commonAreaPrice".equals(surveyCode)){
            count = surveyCommonAreaPriceMapper.listSize(apiReq);
            list = surveyCommonAreaPriceMapper.list(apiReq);
        }
        //调查方默认价格
        else if("investigatorAreaPrice".equals(surveyCode)){
            count = surveyInvestigatorAreaPriceMapper.listSize(apiReq);
            list = surveyInvestigatorAreaPriceMapper.list(apiReq);
        }
        //订单
        else if("order".equals(surveyCode)){
            count = surveyOrderMapper.listSize(apiReq);
            list = surveyOrderMapper.list(apiReq);
        }
        //服务区域表
        else if("serviceArea".equals(surveyCode)){
            count = surveyServiceAreaMapper.listSize(apiReq);
            list = surveyServiceAreaMapper.list(apiReq);
        }
        //处罚记录表
        else if("punish".equals(surveyCode)){
            count = surveyPunishMapper.listSize(apiReq);
            list = surveyPunishMapper.list(apiReq);
        }
        //提现记录
        else if("cashInfo".equals(surveyCode)){
            List<BusUserRole> roles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean orgMan = isRoleUser(roles,58L);//机构复核
            if(orgMan){
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                apiReq.put("franchiseeId",surveyInvestigator.getOrgId());
            }
            count = surveyCashInfoMapper.listSize(apiReq);
            list = surveyCashInfoMapper.list(apiReq);
        }
        //委托方机构-部门信息
        else if("consignorDepartment".equals(surveyCode)){
            count = surveyConsignorDepartmentMapper.listSize(apiReq);
            list = surveyConsignorDepartmentMapper.list(apiReq);
        }
        //任务类型-方向名称
        else if("taskInfoContent".equals(surveyCode)){
            count = surveyTaskInfoContentMapper.listSize(apiReq);
            list = surveyTaskInfoContentMapper.list(apiReq);
        }
        //提现记录
        else if("cashInfoRecord".equals(surveyCode)){
            Long userId = apiReq.getLong("operatorId");
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userId);
            apiReq.put("franchiseeId",surveyInvestigator.getOrgId());
            String menuCode = apiReq.getString("menuCode");
            //待提现的数据（因为有全选，所以不分页）
            if(menuCode != null && "toBe-cash-list".equals(menuCode)){
                apiReq.put("condition",1);
            }
            count = surveyCashInfoRecordMapper.listSize(apiReq);
            list = surveyCashInfoRecordMapper.list(apiReq);
        }
        //开票-开票公司
        else if("applyCorporation".equals(surveyCode)){
            count = billingApplyCorporationMapper.listSize(apiReq);
            list = billingApplyCorporationMapper.list(apiReq);
        }
        //开票-开票公司
        else if("applyCorporationEnum".equals(surveyCode)){
            count = billingApplyCorporationEnumMapper.listSize(apiReq);
            list = billingApplyCorporationEnumMapper.list(apiReq);
        }
        //开票-开票公司 -- 类目及项目
        else if("applyEnumItem".equals(surveyCode)){
            count = billingApplyEnumItemMapper.listSize(apiReq);
            list = billingApplyEnumItemMapper.list(apiReq);
        }
        //开票-开票产品类型
        else if("applyProductType".equals(surveyCode)){
            count = billingApplyProductTypeMapper.listSize(apiReq);
            list = billingApplyProductTypeMapper.list(apiReq);
        }
        //开票-开票产品类型对应业务来源
        else if("applyProductOrg".equals(surveyCode)){
            count = billingApplyProductOrgMapper.listSize(apiReq);
            list = billingApplyProductOrgMapper.list(apiReq);
        }
        //开票-开票产品
        else if("billingEnum".equals(surveyCode)){
            count = commonEnumMapper.selectListByParentEnumCodePageCount(apiReq);
            list = commonEnumMapper.selectListByParentEnumCodePage(apiReq);
        }
        //开票-开票项目
        else if("billingItem".equals(surveyCode)){
            count = commonEnumMapper.selectListByParentEnumCodePageCount(apiReq);
            list = commonEnumMapper.selectListByParentEnumCodePage(apiReq);
        }
        //狄大人平台终审人员
        else if("finalJudgmentUser".equals(surveyCode)){
            apiReq.put("roleId",53);//狄大人平台终审角色
            count = userInfoMapper.selectSizeByRoleId(apiReq);
            list = userInfoMapper.selectListByRoleId(apiReq);
        }
        //终审人员对应的委托方
        else if("userConsignor".equals(surveyCode)){
            count = surveyUserConsignorMapper.listSize(apiReq);
            list = surveyUserConsignorMapper.list(apiReq);
        }
        //终审人员对应的调查方
        else if("userFranchisee".equals(surveyCode)){
            count = surveyUserFranchiseeMapper.listSize(apiReq);
            list = surveyUserFranchiseeMapper.list(apiReq);
        }
        //报告模板
        else if("modelInfo".equals(surveyCode)){
            count = surveyModelInfoMapper.listSize(apiReq);
            list = surveyModelInfoMapper.list(apiReq);
        }
        //报告模板对应的委托方
        else if("consignorModel".equals(surveyCode)){
            count = surveyConsignorModelMapper.listSize(apiReq);
            list = surveyConsignorModelMapper.list(apiReq);
        }
        //通知中心
        else if("message".equals(surveyCode)){
            Integer messageType = apiReq.getInt("messageType");
            if(messageType != null && messageType == 4){
                //狄大人通知消息
                if(apiReq.getString("enter")!=null){
                    if("login".equals(apiReq.getString("enter"))){ //代表入口是“登录”时
                        apiReq.put("toUserId",apiReq.getString("userId"));
                    }
                }else{
                    apiReq.put("toUserId",currentUserId);//当前登录人
                }
                apiReq.put("msgType",4);//消息类型(1:系统消息，2：客服消息，3：其他消息)
            }
            count = surveyMessageMapper.listSize(apiReq);
            list = surveyMessageMapper.list(apiReq);
        }
        //查询该调查员的方向 “地址信息”历史记录
        else if("directionAreaHistory".equals(surveyCode)){
            apiReq.put("surveyUserId",currentUserId);//当前登录人
            count = surveyDirectionAreaHistoryMapper.listSize(apiReq);
            list = surveyDirectionAreaHistoryMapper.list(apiReq);
        }
        //委托方机构-开票主体
        else if("consignorBillSubject".equals(surveyCode)){
//            count = surveyConsignorBillSubjectMapper.listSize(apiReq);
//            list = surveyConsignorBillSubjectMapper.list(apiReq);

            if (apiReq.containsKey("consignorId")){
                apiReq.put("entrustOrgId",apiReq.getLong("consignorId"));
            }
            count = billingApplyCompanyMapper.selectListSize(apiReq);
            list = billingApplyCompanyMapper.selectList(apiReq);

        }
        //领域类型 -- 关联任务类型
        else if("businessTaskType".equals(surveyCode)){
            count = surveyBusinessTaskTypeMapper.listSize(apiReq);
            list = surveyBusinessTaskTypeMapper.list(apiReq);
        }
        //方向结果类型
        else if("directionResultType".equals(surveyCode)){
            count = surveyDirectionResultTypeMapper.listSize(apiReq);
            list = surveyDirectionResultTypeMapper.list(apiReq);
        }
        //任务子类对应方向结果类型
        else if("taskDirectionResult".equals(surveyCode)){
            count = surveyTaskDirectionResultMapper.listSize(apiReq);
            list = surveyTaskDirectionResultMapper.list(apiReq);
        }
        //价格模板
        else if("priceModel".equals(surveyCode)){
            count = surveyPriceModelMapper.listSize(apiReq);
            list = surveyPriceModelMapper.list(apiReq);
        }
        //价格模板对应机构
        else if("priceModelOrg".equals(surveyCode)){
            count = surveyPriceModelOrgMapper.listSize(apiReq);
            list = surveyPriceModelOrgMapper.list(apiReq);
        }
        //委托时效模板
        else if("consignorEfficiencyModel".equals(surveyCode)){
            count = surveyConsignorEfficiencyModelMapper.listSize(apiReq);
            list = surveyConsignorEfficiencyModelMapper.list(apiReq);
        }
        //委托时效模板对应的机构信息
        else if("consignorEfficiencyModelOrg".equals(surveyCode)){
            count = surveyConsignorEfficiencyModelOrgMapper.listSize(apiReq);
            list = surveyConsignorEfficiencyModelOrgMapper.list(apiReq);
        }

        else if("channelModel".equals(surveyCode)){
            count = surveyChannelModelMapper.listSize(apiReq);
            list = surveyChannelModelMapper.list(apiReq);
        }
        else if("scoreModel".equals(surveyCode)){
            count = surveyScoreModelMapper.listSize(apiReq);
            list = surveyScoreModelMapper.list(apiReq);
        }
        //委托时效模板对应的机构信息
        else if("channelModelOrg".equals(surveyCode)){
            count = surveyChannelModelOrgMapper.listSize(apiReq);
            list = surveyChannelModelOrgMapper.list(apiReq);
        }
        //委托时效模板对应的机构信息
        else if("scoreModelOrg".equals(surveyCode)){
            count = surveyScoreModelOrgMapper.listSize(apiReq);
            list = surveyScoreModelOrgMapper.list(apiReq);
        }

        //邮箱模板
        else if("emailInfo".equals(surveyCode)){
            count = surveyEmailInfoMapper.listSize(apiReq);
            list = surveyEmailInfoMapper.list(apiReq);
        }
        //邮箱模板对应的委托方
        else if("emailInfoOrg".equals(surveyCode)){
            count = surveyEmailInfoOrgMapper.listSize(apiReq);
            list = surveyEmailInfoOrgMapper.list(apiReq);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, list);

    }


    /**
     * 数据 info
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "数据 info", value = "backend-survey-info", apiParams = { })
    @Override
    public ApiResponse edit(ApiRequest apiReq) {

        String surveyCode = apiReq.getString("surveyCode");
        Long currentUserId = getCurrentUserId(apiReq);
        //领域类型
        if("businessType".equals(surveyCode)){
            SurveyBusinessType surveyBusinessType = surveyBusinessTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyBusinessType);
        }
        //业务类型
        else if("serviceType".equals(surveyCode)){
            SurveyServiceType surveyServiceType = surveyServiceTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyServiceType);
        }
        //任务类型
        else if("taskInfo".equals(surveyCode)){
            SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyTaskInfo);
        }
        //材料目录
        else if("fileCatalog".equals(surveyCode)){
            SurveyFileCatalog surveyFileCatalog = surveyFileCatalogMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyFileCatalog);
        }
        //调查员登记
        else if("level".equals(surveyCode)){
            SurveyLevel surveyLevel = surveyLevelMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyLevel);
        }
        //称号特权详情
        else if("levelExplain".equals(surveyCode)){
            SurveyLevelExplain surveyLevelExplain = surveyLevelExplainMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyLevelExplain);
        }
        //平台介绍
        else if("introduction".equals(surveyCode)){
            SurveyIntroduction surveyIntroduction = surveyIntroductionMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyIntroduction);
        }
        //服务优势
        else if("serviceAdvantage".equals(surveyCode)){
            SurveyServiceAdvantage surveyServiceAdvantage = surveyServiceAdvantageMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyServiceAdvantage);
        }
        //委托人认证
        else if("consigner".equals(surveyCode)){
            SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsigner);
        }
        //委托方机构
        else if("consignor".equals(surveyCode)){
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(apiReq.getLong("id"));
            //获取部门信息
            if(surveyConsignor!=null){
                Map<String,Object> map = new HashMap<>();
                map.put("consignorId",surveyConsignor.getId());
                map.put("pageIndex", null); //不分页
                map.put("pageSize", null); //不分页
                List<SurveyConsignorDepartment> surveyConsignorDepartment = surveyConsignorDepartmentMapper.list(map);
                surveyConsignor.setSurveyConsignorDepartment(surveyConsignorDepartment);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignor);
        }
        //调查方认证
        else if("investigator".equals(surveyCode)){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByPrimaryKey(apiReq.getLong("id"));
            //手写签名
            SurveyUserSign surveyUserSign = surveyUserSignMapper.selectByUserId(surveyInvestigator.getUserId());
            if(surveyUserSign!=null){
                surveyInvestigator.setSignUrl(surveyUserSign.getSignImg());
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyInvestigator);
        }
        //乐凡币介绍
        else if("lfcoinExplain".equals(surveyCode)){
            SurveyLfcoinExplain surveyLfcoinExplain = surveyLfcoinExplainMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyLfcoinExplain);
        }
        //乐凡币规则
        else if("lfcoinRule".equals(surveyCode)){
            SurveyLfcoinRule surveyLfcoinRule = surveyLfcoinRuleMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyLfcoinRule);
        }
        //成就点规则
        else if("achieveRule".equals(surveyCode)){
            SurveyAchieveRule surveyAchieveRule = surveyAchieveRuleMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyAchieveRule);
        }
        //帖子类别
        else if("knowledgeType".equals(surveyCode)){
            SurveyKnowledgeType surveyKnowledgeType = surveyKnowledgeTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyKnowledgeType);
        }
        //论坛帖子
        else if("knowledgeBase".equals(surveyCode)){
            SurveyKnowledgeBase surveyKnowledgeBase = surveyKnowledgeBaseMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyKnowledgeBase);
        }
        //帖子评论
        else if("knowledgeComment".equals(surveyCode)){

        }
        //调查委托方价格
        else if("consignorPrice".equals(surveyCode)){
            SurveyConsignorPrice surveyConsignorPrice = surveyConsignorPriceMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignorPrice);
        }
        //调查调查方价格
        else if("franchiseePrice".equals(surveyCode)){
            SurveyFranchiseePrice surveyFranchiseePrice = surveyFranchiseePriceMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyFranchiseePrice);
        }
        //qa问答
        else if("qa".equals(surveyCode)){
            SurveyQa surveyQa = surveyQaMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyQa);
        }
        //调查调查方
        else if("franchisee".equals(surveyCode)){
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if(surveyFranchisee!=null){
                //银行卡照片
                String bankUrls= surveyFranchisee.getBankUrl();
                List bankUrlList = new ArrayList();
                if(bankUrls != null){
                    String[] bankUrl = bankUrls.split(",");
                    for (String url : bankUrl) {
                        bankUrlList.add(url);
                    }
                }
                surveyFranchisee.setBankUrlList(bankUrlList);

                //收款人身份证照片
                String acceptUserIdcardUrls= surveyFranchisee.getAcceptUserIdcardUrl();
                List acceptUserIdcardUrlList = new ArrayList();
                if(acceptUserIdcardUrls != null){
                    String[] acceptUserIdcardUrl = acceptUserIdcardUrls.split(",");
                    for (String url : acceptUserIdcardUrl) {
                        acceptUserIdcardUrlList.add(url);
                    }
                }
                surveyFranchisee.setAcceptUserIdcardUrlList(acceptUserIdcardUrlList);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyFranchisee);
        }
        //商品
        else if("product".equals(surveyCode)){
            SurveyProductDto surveyProduct = surveyProductMapper.selectByPrimaryKey(apiReq.getLong("id"));
            Map<String,Object> map = new HashMap<>();
            map.put("productId",apiReq.getLong("id"));
            surveyProduct.setLevels(surveyProductLevelMapper.selectInfo(map));
            surveyProduct.setRoles(surveyProductRoleMapper.selectInfo(map));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyProduct);
        }
        //委托方默认价格
        else if("commonAreaPrice".equals(surveyCode)){
            Map<String,Object> map  = new HashMap<>();
            map.put("areaId",apiReq.getLong("areaId"));
            map.put("taskId",apiReq.getLong("taskId"));
            map.put("cityType",apiReq.getLong("cityType"));
            SurveyCommonAreaPrice surveyCommonAreaPrice = surveyCommonAreaPriceMapper.selectByInfo(map);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyCommonAreaPrice);
        }
        //调查方默认价格
        else if("investigatorAreaPrice".equals(surveyCode)){
            Map<String,Object> map  = new HashMap<>();
            map.put("areaId",apiReq.getLong("areaId"));
            map.put("taskId",apiReq.getLong("taskId"));
            map.put("cityType",apiReq.getLong("cityType"));
            SurveyInvestigatorAreaPrice surveyInvestigatorAreaPrice = surveyInvestigatorAreaPriceMapper.selectByInfo(map);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyInvestigatorAreaPrice);
        }
        //订单
        else if("order".equals(surveyCode)){
            SurveyOrder surveyOrder = surveyOrderMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyOrder);
        }
        //服务区域
        else if("serviceArea".equals(surveyCode)){
            SurveyServiceArea surveyServiceArea = surveyServiceAreaMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyServiceArea);
        }
        //处罚记录表
        else if("punish".equals(surveyCode)){
            SurveyPunish surveyPunish = surveyPunishMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyPunish);
        }
        //处罚记录表
        else if("cashInfo".equals(surveyCode)){
            SurveyCashInfo surveyCashInfo = surveyCashInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));

            List<BusUserRole> roles = busUserRoleMapper.orgUserRoleList(currentUserId);
            surveyCashInfo.setOrgMan(isRoleUser(roles,58L));//机构复核
            surveyCashInfo.setLfYuying(isRoleUser(roles,59L));//平台运营

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyCashInfo);
        }
        //委托方机构-部门信息
        else if("consignorDepartment".equals(surveyCode)){
            SurveyConsignorDepartment surveyConsignorDepartment = surveyConsignorDepartmentMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignorDepartment);
        }
        //任务类型-方向名称
        else if("taskInfoContent".equals(surveyCode)){
            SurveyTaskInfoContent surveyTaskInfoContent = surveyTaskInfoContentMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyTaskInfoContent);
        }
        //人员信息
        else if("userInfo".equals(surveyCode)){
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(apiReq.getLong("userId"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,userInfo);
        }
        //人员信息
        else if("bankCard".equals(surveyCode)){
            SurveyBankCard surveyBankCard = surveyBankCardMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyBankCard);
        }
        //开票-开票公司
        else if("applyCorporation".equals(surveyCode)){
            BillingApplyCorporation applyCorporation = billingApplyCorporationMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,applyCorporation);
        }
        //开票-开票产品类型
        else if("applyProductType".equals(surveyCode)){
            BillingApplyProductType applyProductType = billingApplyProductTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,applyProductType);
        }
        //开票-开票产品
        else if("billingEnum".equals(surveyCode)){
            CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonEnum);
        }
        //开票-开票项目
        else if("billingItem".equals(surveyCode)){
            CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonEnum);
        }
        //报告模板
        else if("modelInfo".equals(surveyCode)){
            SurveyModelInfo surveyModelInfo = surveyModelInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyModelInfo);
        }
        //委托机构 -- 报告命名规则
        else if("consignorReportRule".equals(surveyCode)){
            SurveyConsignorReportRule surveyConsignorReportRule = surveyConsignorReportRuleMapper.selectByConsignorId(apiReq.getLong("surveyConsignorId"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignorReportRule);
        }
        //委托方机构-开票主体
        else if("consignorBillSubject".equals(surveyCode)){
            BillingApplyCompany billingApplyCompany = billingApplyCompanyMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,billingApplyCompany);
//            SurveyConsignorBillSubject surveyConsignorBillSubject = surveyConsignorBillSubjectMapper.selectByPrimaryKey(apiReq.getLong("id"));
//            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignorBillSubject);
        }
        //方向结果类型
        else if("directionResultType".equals(surveyCode)){
            SurveyDirectionResultType surveyDirectionResultType = surveyDirectionResultTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyDirectionResultType);
        }
        //价格模板
        else if("priceModel".equals(surveyCode)){
            SurveyPriceModel surveyPriceModel = surveyPriceModelMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyPriceModel);
        }
        //区域类别，以及区域对应的具体城市
        else if("priceModelAreaCategories".equals(surveyCode)){
            SurveyPriceModelAreaCategories surveyPriceModelAreaCategories = surveyPriceModelAreaCategoriesMapper.selectByPrimaryKey(apiReq.getLong("id"));
            Map<String,Object> map = new HashMap<>();
            map.put("areaCategoriesId",apiReq.getLong("id"));
            List<SurveyAreaCategoriesAreaCity> areaCitys = surveyAreaCategoriesAreaCityMapper.list(map);
            surveyPriceModelAreaCategories.setAreaCitys(areaCitys);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyPriceModelAreaCategories);
        }
        else if("consignorEfficiencyModelArea".equals(surveyCode)){
            SurveyConsignorEfficiencyModelArea surveyConsignorEfficiencyModelArea = surveyConsignorEfficiencyModelAreaMapper.selectByPrimaryKey(apiReq.getLong("id"));
            Map<String,Object> map = new HashMap<>();
            map.put("areaCategoriesId",apiReq.getLong("id"));
            List<SurveyConsignorEfficiencyAreaCity> surveyAreaCategoriesAreaCities = surveyConsignorEfficiencyAreaCityMapper.list(map);
            surveyConsignorEfficiencyModelArea.setAreaCities(surveyAreaCategoriesAreaCities);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignorEfficiencyModelArea);
        }
        else if("channelModelArea".equals(surveyCode)){
            SurveyChannelModelArea surveyChannelModelArea = surveyChannelModelAreaMapper.selectByPrimaryKey(apiReq.getLong("id"));
            Map<String,Object> map = new HashMap<>();
            map.put("areaCategoriesId",apiReq.getLong("id"));
            List<SurveyChannelModelAreaCity> surveyAreaCategoriesAreaCities = surveyChannelModelAreaCityMapper.list(map);
            surveyChannelModelArea.setAreaCities(surveyAreaCategoriesAreaCities);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelModelArea);
        }
        else if("scoreModelArea".equals(surveyCode)){
            SurveyScoreModelArea surveyScoreModelArea = surveyScoreModelAreaMapper.selectByPrimaryKey(apiReq.getLong("id"));
            Map<String,Object> map = new HashMap<>();
            map.put("areaCategoriesId",apiReq.getLong("id"));
            List<SurveyScoreModelAreaCity> surveyAreaCategoriesAreaCities = surveyScoreModelAreaCityMapper.list(map);
            surveyScoreModelArea.setAreaCities(surveyAreaCategoriesAreaCities);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyScoreModelArea);
        }
        //根据“userId”获取调查员信息（场景：1、二次认证）
        else if("investigatorUser".equals(surveyCode)){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(apiReq.getLong("userId"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyInvestigator);
        }
        //根据“userId”获取委托人信息（场景：1、二次认证）
        else if("consignerUser".equals(surveyCode)){
            SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByUserId(apiReq.getLong("userId"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsigner);
        }
        //委托时效设置
        else if("consignorEfficiencyModel".equals(surveyCode)){
            SurveyConsignorEfficiencyModel surveyConsignorEfficiencyModel = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(apiReq.getLong("id"));
            //时效体系
//            if(surveyConsignorEfficiencyModel != null){
//                Map<String,Object> map =  new HashMap<>();
//                map.put("efficiencyModelId",surveyConsignorEfficiencyModel.getId());
//                List<SurveyConsignorEfficiencyModelInfo> infos = surveyConsignorEfficiencyModelInfoMapper.list(map);
//                surveyConsignorEfficiencyModel.setEfficiencyModelInfos(infos);
//            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignorEfficiencyModel);
        }
        else if("channelModel".equals(surveyCode)){
            SurveyChannelModel surveyChannelModel = surveyChannelModelMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyChannelModel);
        }
        else if("scoreModel".equals(surveyCode)){
            SurveyScoreModel surveyScoreModel = surveyScoreModelMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyScoreModel);
        }
        //平台终审人员 -- 手写签名
        else if("userSign".equals(surveyCode)){
            SurveyUserSign surveyUserSign = surveyUserSignMapper.selectByUserId(apiReq.getLong("userId"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyUserSign);
        }
        //邮箱模板
        else if("emailInfo".equals(surveyCode)){
            SurveyEmailInfo surveyEmailInfo = surveyEmailInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyEmailInfo);
        }
        return null;
    }

    /**
     * 根据电话号码查询是否存在
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "数据 info", value = "backend-survey-selectByOne", apiParams = { })
    @Override
    public  ApiResponse selectByOne(ApiRequest apiReq){
        String userTel=apiReq.getString("userTel");
        Map map=new HashMap();
        map.put("userTelphone",userTel);
        UserInfo userInfo=userInfoMapper.selectUserInfoByPhone(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,userInfo);
    }

    @ApiMethod(descript = "模板区域删除", value = "backend-survey-model-area-delete", apiParams = { })
    @Override
    public ApiResponse modelAreaDel(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        Long id = apiRequest.getLong("id");
        if ("channelModelAreaDel".equals(surveyCode)){
            SurveyChannelModelArea surveyChannelModelArea = surveyChannelModelAreaMapper.selectByPrimaryKey(id);
            if (surveyChannelModelArea != null){
                surveyChannelModelAreaMapper.deleteByPrimaryKey(id);
                surveyChannelModelAreaCityMapper.deleteByAreaCategoriesId(surveyChannelModelArea.getId(),surveyChannelModelArea.getModelId());
            }
        }
        if ("scoreModelAreaDel".equals(surveyCode)){
            SurveyScoreModelArea surveyScoreModelArea = surveyScoreModelAreaMapper.selectByPrimaryKey(id);
            if (surveyScoreModelArea != null){
                surveyScoreModelAreaMapper.deleteByPrimaryKey(id);
                surveyScoreModelAreaCityMapper.deleteByAreaCategoriesId(surveyScoreModelArea.getId(),surveyScoreModelArea.getModelId());
            }
        }
        else if ("priceModelAreaCategoriesDel".equals(surveyCode)){
            SurveyPriceModelAreaCategories surveyPriceModelAreaCategories = surveyPriceModelAreaCategoriesMapper.selectByPrimaryKey(id);
            if (surveyPriceModelAreaCategories != null) {
                surveyPriceModelAreaCategoriesMapper.deleteByPrimaryKey(id);
                surveyAreaCategoriesAreaCityMapper.deleteByAreaCategoriesId(surveyPriceModelAreaCategories.getId(),surveyPriceModelAreaCategories.getPriceModelId());
            }
        }else if ("consignorEfficiencyModelAreaDel".equals(surveyCode)){
            SurveyConsignorEfficiencyModelArea surveyConsignorEfficiencyModelArea = surveyConsignorEfficiencyModelAreaMapper.selectByPrimaryKey(id);
            if (surveyConsignorEfficiencyModelArea != null){
                surveyConsignorEfficiencyModelAreaMapper.deleteByPrimaryKey(id);
                surveyConsignorEfficiencyAreaCityMapper.deleteByAreaCategoriesId(surveyConsignorEfficiencyModelArea.getId(),surveyConsignorEfficiencyModelArea.getModelId());
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(descript = "添加区域类别数据", value = "backend-survey-area-type-list", apiParams = { })
    @Override
    public ApiResponse selectArea(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        String fromType = apiRequest.getString("fromType");
        Long areaCategoriesId = apiRequest.getLong("id");
        if ("priceModelAreaCategories".equals(surveyCode)){
            List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(0L);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> childrenList = commonAreaMapper.selectAreaCountByParentId(commonArea.getAreaId());
                commonArea.setAllChildrenNum((int)childrenList.stream().filter(e->e.getAreaType() == 2).count());
                commonArea.setSelectedChildrenNum(0);
                if ("edit".equals(fromType)) {
                    String collect = childrenList.stream().filter(e->e.getAreaType() == 2).map(e -> e.getAreaId().toString()).collect(Collectors.joining(","));
                    commonArea.setSelectedChildrenNum(surveyAreaCategoriesAreaCityMapper.selectAllSelectedCount(collect,areaCategoriesId));
                    commonArea.setSelectAreaIds(commonAreaMapper.selectAllChildren2(commonArea.getAreaId(),areaCategoriesId));
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
        }else if ("consignorEfficiencyModelArea".equals(surveyCode)){
            List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(0L);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> childrenList = commonAreaMapper.selectAreaCountByParentId(commonArea.getAreaId());
                commonArea.setAllChildrenNum((int)childrenList.stream().filter(e->e.getAreaType() == 2).count());
                commonArea.setSelectedChildrenNum(0);
                if ("edit".equals(fromType)) {
                    String collect = childrenList.stream().filter(e->e.getAreaType() == 2).map(e -> e.getAreaId().toString()).collect(Collectors.joining(","));
                    commonArea.setSelectedChildrenNum(surveyConsignorEfficiencyAreaCityMapper.selectAllSelectedCount(collect,areaCategoriesId));
                    commonArea.setSelectAreaIds(commonAreaMapper.selectAllChildrenEfficiency2(commonArea.getAreaId(),areaCategoriesId));
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
        }
        else if ("channelModelArea".equals(surveyCode)){
            List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(0L);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> childrenList = commonAreaMapper.selectAreaCountByParentId(commonArea.getAreaId());
                commonArea.setAllChildrenNum((int)childrenList.stream().filter(e->e.getAreaType() == 2).count());
                commonArea.setSelectedChildrenNum(0);
                if ("edit".equals(fromType)) {
                    String collect = childrenList.stream().filter(e->e.getAreaType() == 2).map(e -> e.getAreaId().toString()).collect(Collectors.joining(","));
                    commonArea.setSelectedChildrenNum(surveyChannelModelAreaCityMapper.selectAllSelectedCount(collect,areaCategoriesId));
                    commonArea.setSelectAreaIds(commonAreaMapper.selectAllChildrenChannel2(commonArea.getAreaId(),areaCategoriesId));
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
        }
        else if ("scoreModelArea".equals(surveyCode)){
            List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(0L);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> childrenList = commonAreaMapper.selectAreaCountByParentId(commonArea.getAreaId());
                commonArea.setAllChildrenNum((int)childrenList.stream().filter(e->e.getAreaType() == 2).count());
                commonArea.setSelectedChildrenNum(0);
                if ("edit".equals(fromType)) {
                    String collect = childrenList.stream().filter(e->e.getAreaType() == 2).map(e -> e.getAreaId().toString()).collect(Collectors.joining(","));
                    commonArea.setSelectedChildrenNum(surveyScoreModelAreaCityMapper.selectAllSelectedCount(collect,areaCategoriesId));
                    commonArea.setSelectAreaIds(commonAreaMapper.selectAllChildrenChannel2(commonArea.getAreaId(),areaCategoriesId));
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    /**
     * 更新
     */
    @ApiMethod(descript = "更新", value = "backend-survey-update")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse update(ApiRequest apiReq) {

        String surveyCode = apiReq.getString("surveyCode");
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        String userName = null;
        if(userInfo != null){
            userName = userInfo.getUserName();
            userId = userInfo.getUserId();
        }
        //领域类型
        try {
            if("businessType".equals(surveyCode)){
                SurveyBusinessType surveyBusinessType = surveyBusinessTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyBusinessType == null){
                    //保存
                    surveyBusinessType = ConvertToBeanUtil.toBean(apiReq, SurveyBusinessType.class);
                    surveyBusinessType.setCreateByName(userName);// 发起人
                    surveyBusinessType.setCreateBy(userId);// 发起人id
                    surveyBusinessType.setCreateTime(new Date());//创建时间
                    surveyBusinessType.setDeleteFlag(0);
                    surveyBusinessTypeMapper.insert(surveyBusinessType);
                }else{
                    //修改
                    surveyBusinessType = ConvertToBeanUtil.toBean(apiReq,surveyBusinessType);
                    surveyBusinessType.setUpdateBy(userId);// 更新人id
                    surveyBusinessType.setUpdateTime(new Date());//更新时间
                    surveyBusinessTypeMapper.updateByPrimaryKey(surveyBusinessType);
                }
            }
            //业务类型
            else if("serviceType".equals(surveyCode)){
                SurveyServiceType surveyServiceType = surveyServiceTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyServiceType == null){
                    //保存
                    surveyServiceType = ConvertToBeanUtil.toBean(apiReq, SurveyServiceType.class);
                    surveyServiceType.setCreateByName(userName);// 发起人
                    surveyServiceType.setCreateBy(userId);// 发起人id
                    surveyServiceType.setCreateTime(new Date());//创建时间
                    surveyServiceType.setDeleteFlag(0);
                    surveyServiceTypeMapper.insert(surveyServiceType);
                }else{
                    //修改
                    surveyServiceType = ConvertToBeanUtil.toBean(apiReq,surveyServiceType);
                    surveyServiceType.setUpdateBy(userId);// 更新人id
                    surveyServiceType.setUpdateTime(new Date());//更新时间
                    surveyServiceTypeMapper.updateByPrimaryKey(surveyServiceType);
                }
            }
            //任务类型
            else if("taskInfo".equals(surveyCode)){
                SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyTaskInfo == null){
                    //保存
                    surveyTaskInfo = ConvertToBeanUtil.toBean(apiReq, SurveyTaskInfo.class);
                    surveyTaskInfo.setCreateByName(userName);// 发起人
                    surveyTaskInfo.setCreateBy(userId);// 发起人id
                    surveyTaskInfo.setCreateTime(new Date());//创建时间
                    surveyTaskInfo.setDeleteFlag(0);
                    surveyTaskInfoMapper.insert(surveyTaskInfo);
                }else{
                    //修改
                    surveyTaskInfo = ConvertToBeanUtil.toBean(apiReq,surveyTaskInfo);
                    surveyTaskInfo.setUpdateBy(userId);// 更新人id
                    surveyTaskInfo.setUpdateTime(new Date());//更新时间
                    surveyTaskInfoMapper.updateByPrimaryKey(surveyTaskInfo);
                }
            }
            //材料目录
            else if("fileCatalog".equals(surveyCode)){
                SurveyFileCatalog surveyFileCatalog = surveyFileCatalogMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyFileCatalog == null){
                    //保存
                    surveyFileCatalog = ConvertToBeanUtil.toBean(apiReq, SurveyFileCatalog.class);
                    surveyFileCatalog.setCreateByName(userName);// 发起人
                    surveyFileCatalog.setCreateBy(userId);// 发起人id
                    surveyFileCatalog.setCreateTime(new Date());//创建时间
                    surveyFileCatalog.setDeleteFlag(0);
                    surveyFileCatalogMapper.insertSelective(surveyFileCatalog);
                }else{
                    //修改
                    surveyFileCatalog = ConvertToBeanUtil.toBean(apiReq,surveyFileCatalog);
                    surveyFileCatalog.setUpdateBy(userId);// 更新人id
                    surveyFileCatalog.setUpdateTime(new Date());//更新时间
                    surveyFileCatalogMapper.updateByPrimaryKey(surveyFileCatalog);
                }
            }
            //调查员登记
            else if("level".equals(surveyCode)){
                SurveyLevel surveyLevel = surveyLevelMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyLevel == null){
                    //保存
                    surveyLevel = ConvertToBeanUtil.toBean(apiReq, SurveyLevel.class);
                    surveyLevel.setCreateByName(userName);// 发起人
                    surveyLevel.setCreateBy(userId);// 发起人id
                    surveyLevel.setCreateTime(new Date());//创建时间
                    surveyLevel.setDeleteFlag(0);
                    surveyLevelMapper.insert(surveyLevel);
                }else{
                    //修改
                    surveyLevel = ConvertToBeanUtil.toBean(apiReq,surveyLevel);
                    surveyLevel.setUpdateBy(userId);// 更新人id
                    surveyLevel.setUpdateTime(new Date());//更新时间
                    surveyLevelMapper.updateByPrimaryKey(surveyLevel);
                }
            }
            //称号特权详情
            else if("levelExplain".equals(surveyCode)){
                SurveyLevelExplain surveyLevelExplain = surveyLevelExplainMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyLevelExplain == null){
                    //保存
                    surveyLevelExplain = ConvertToBeanUtil.toBean(apiReq, SurveyLevelExplain.class);
                    surveyLevelExplain.setCreateByName(userName);// 发起人
                    surveyLevelExplain.setCreateBy(userId);// 发起人id
                    surveyLevelExplain.setCreateTime(new Date());//创建时间
                    surveyLevelExplain.setDeleteFlag(0);
                    surveyLevelExplainMapper.insert(surveyLevelExplain);
                }else{
                    //修改
                    surveyLevelExplain = ConvertToBeanUtil.toBean(apiReq,surveyLevelExplain);
                    surveyLevelExplain.setUpdateBy(userId);// 更新人id
                    surveyLevelExplain.setUpdateTime(new Date());//更新时间
                    surveyLevelExplainMapper.updateByPrimaryKey(surveyLevelExplain);
                }
            }
            //平台介绍
            else if("introduction".equals(surveyCode)){
                SurveyIntroduction surveyIntroduction = surveyIntroductionMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyIntroduction == null){
                    //保存
                    surveyIntroduction = ConvertToBeanUtil.toBean(apiReq, SurveyIntroduction.class);
                    surveyIntroduction.setCreateByName(userName);// 发起人
                    surveyIntroduction.setCreateBy(userId);// 发起人id
                    surveyIntroduction.setCreateTime(new Date());//创建时间
                    surveyIntroduction.setDeleteFlag(0);
                    surveyIntroductionMapper.insert(surveyIntroduction);
                }else{
                    //修改
                    surveyIntroduction = ConvertToBeanUtil.toBean(apiReq,surveyIntroduction);
                    surveyIntroduction.setUpdateBy(userId);// 更新人id
                    surveyIntroduction.setUpdateTime(new Date());//更新时间
                    surveyIntroductionMapper.updateByPrimaryKey(surveyIntroduction);
                }
            }
            //服务优势
            else if("serviceAdvantage".equals(surveyCode)){
                SurveyServiceAdvantage surveyServiceAdvantage = surveyServiceAdvantageMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyServiceAdvantage == null){
                    //保存
                    surveyServiceAdvantage = ConvertToBeanUtil.toBean(apiReq, SurveyServiceAdvantage.class);

                    String serviceAdContent = apiReq.getString("content");
                    surveyServiceAdvantage.setServiceAdContent(serviceAdContent);
                    surveyServiceAdvantage.setCreateByName(userName);// 发起人
                    surveyServiceAdvantage.setCreateBy(userId);// 发起人id
                    surveyServiceAdvantage.setCreateTime(new Date());//创建时间
                    surveyServiceAdvantage.setDeleteFlag(0);
                    surveyServiceAdvantageMapper.insert(surveyServiceAdvantage);
                }else{
                    //修改
                    surveyServiceAdvantage = ConvertToBeanUtil.toBean(apiReq,surveyServiceAdvantage);
                    String serviceAdContent = apiReq.getString("content");
                    surveyServiceAdvantage.setServiceAdContent(serviceAdContent);
                    surveyServiceAdvantage.setUpdateBy(userId);// 更新人id
                    surveyServiceAdvantage.setUpdateTime(new Date());//更新时间
                    surveyServiceAdvantageMapper.updateByPrimaryKey(surveyServiceAdvantage);
                }
            }
            //委托人认证
            else if("consigner".equals(surveyCode)){
                SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyConsigner == null){
                    //保存
                    surveyConsigner = ConvertToBeanUtil.toBean(apiReq, SurveyConsigner.class);
                    surveyConsigner.setCreateByName(userName);// 发起人
                    surveyConsigner.setCreateBy(userId);// 发起人id
                    surveyConsigner.setCreateTime(new Date());//创建时间
                    surveyConsigner.setDeleteFlag(0);
                    surveyConsignerMapper.insert(surveyConsigner);
                    SurveyConsignerDepartment surveyConsignerDepartment = new SurveyConsignerDepartment();
                    surveyConsignerDepartment.setConsignorOrgId(surveyConsigner.getEntrustOrgId());
                    surveyConsignerDepartment.setConsignorDepartmentId(surveyConsigner.getDepartmentId());
                    surveyConsignerDepartment.setConsignerUserId(surveyConsigner.getId());
                    surveyConsignerDepartmentMapper.insert(surveyConsignerDepartment);
                }else{
                    //修改
                    surveyConsigner = ConvertToBeanUtil.toBean(apiReq,surveyConsigner);
                    Long departmentId = apiReq.getLong("departmentId");
                    SurveyConsignorDepartment surveyConsignorDepartment = surveyConsignorDepartmentMapper.selectByPrimaryKey(departmentId);
                    surveyConsigner.setDepartmentId(surveyConsignorDepartment.getId());
                    surveyConsigner.setDepartmentName(surveyConsignorDepartment.getName());
                    surveyConsigner.setUpdateBy(userId);// 更新人id
                    surveyConsigner.setUpdateTime(new Date());//更新时间
                    surveyConsignerMapper.updateByPrimaryKey(surveyConsigner);

                    SurveyConsignerDepartment surveyConsignerDepartment = new SurveyConsignerDepartment();
                    surveyConsignerDepartment.setConsignorOrgId(surveyConsigner.getEntrustOrgId());
                    surveyConsignerDepartment.setConsignorDepartmentId(surveyConsigner.getDepartmentId());
                    surveyConsignerDepartment.setConsignerUserId(surveyConsigner.getId());
                    surveyConsignerDepartmentMapper.insert(surveyConsignerDepartment);
                }
            }
            //委托方机构
            else if("consignor".equals(surveyCode)){
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyConsignor == null){
                    SurveyConsignor consignorName = surveyConsignorMapper.selectByName(apiReq.getString("company"));
                    if(consignorName!=null){
                        return new ApiResponse(ApiMsgEnum.SURVEY_NAME_ERROR);
                    }
                    //保存
                    surveyConsignor = ConvertToBeanUtil.toBean(apiReq, SurveyConsignor.class);
                    surveyConsignor.setCreateByName(userName);// 发起人
                    surveyConsignor.setCreateBy(userId);// 发起人id
                    surveyConsignor.setCreateTime(new Date());//创建时间
                    surveyConsignor.setDeleteFlag(0);
                    surveyConsignor.setMedicalMoney(100D);//住院病例调查，每多一份病例，价格增加金额
                    surveyConsignor.setMainProvinceMoney(2500D);//主省价格上限
                    surveyConsignor.setViceProvinceMoney(1500D);//副省价格上限
                    Long marketUserId=surveyConsignor.getMarketUserId();
                    UserInfo marketUserInfo = userInfoMapper.selectByPrimaryKey(marketUserId);
                    if (marketUserInfo != null)
                        surveyConsignor.setMarketUserName(marketUserInfo.getUserName());
                    Long recommendUserId=surveyConsignor.getRecommendUserId();
                    UserInfo recommendUserInfo = userInfoMapper.selectByPrimaryKey(recommendUserId);
                    if (recommendUserInfo != null)
                        surveyConsignor.setRecommendUserName(recommendUserInfo.getUserName());
                    surveyConsignorMapper.insert(surveyConsignor);
                    surveyConsignor.setCode("WT"+surveyConsignor.getId());
                    if (surveyConsignor.getOrgAttr() == 3){// 反欺诈 也属于 保司、取价格逻辑不变。 只需要把反欺诈字段标记 为是。
                        surveyConsignor.setOrgAttr(1);
                        surveyConsignor.setSharp(1);
                    }else{
                        surveyConsignor.setSharp(0);
                    }
                    StaffBudgetCompany staffBudgetCompany = staffBudgetCompanyMapper.selectByPrimaryKey(surveyConsignor.getBillCompanyId());
                    if (staffBudgetCompany != null){
                        surveyConsignor.setBillCompanyName(staffBudgetCompany.getName());
                    }
                    surveyConsignorMapper.updateByPrimaryKeySelective(surveyConsignor);

                    //保存 报告命名规则
                    SurveyConsignorReportRule surveyConsignorReportRule =  new SurveyConsignorReportRule();
                    surveyConsignorReportRule.setSurveyConsignorId(surveyConsignor.getId());
                    surveyConsignorReportRule.setOneRule(3);
                    surveyConsignorReportRule.setTwoRule(2);
                    surveyConsignorReportRule.setThreeRule(5);
                    surveyConsignorReportRule.setFourRule(5);
                    surveyConsignorReportRuleMapper.insertSelective(surveyConsignorReportRule);
                }else{
                    //修改
                    surveyConsignor = ConvertToBeanUtil.toBean(apiReq,surveyConsignor);
                    surveyConsignor.setUpdateBy(userId);// 更新人id
                    surveyConsignor.setUpdateTime(new Date());//更新时间
                    Long marketUserId=surveyConsignor.getMarketUserId();
                    UserInfo marketUserInfo = userInfoMapper.selectByPrimaryKey(marketUserId);
                    if (marketUserInfo != null)
                        surveyConsignor.setMarketUserName(marketUserInfo.getUserName());
                    Long recommendUserId=surveyConsignor.getRecommendUserId();
                    UserInfo recommendUserInfo = userInfoMapper.selectByPrimaryKey(recommendUserId);
                    if (recommendUserInfo != null)
                        surveyConsignor.setRecommendUserName(recommendUserInfo.getUserName());
                    if (surveyConsignor.getOrgAttr() == 3){
                        surveyConsignor.setOrgAttr(1);
                        surveyConsignor.setSharp(1);
                    }else{
                        surveyConsignor.setSharp(0);
                    }
                    StaffBudgetCompany staffBudgetCompany = staffBudgetCompanyMapper.selectByPrimaryKey(surveyConsignor.getBillCompanyId());
                    if (staffBudgetCompany != null){
                        surveyConsignor.setBillCompanyName(staffBudgetCompany.getName());
                    }
                    surveyConsignorMapper.updateByPrimaryKey(surveyConsignor);

                    Map<String,Object> map = new HashMap<>();
                    map.put("entrustOrgId",surveyConsignor.getId());
                    List<SurveyConsigner> surveyConsigners = surveyConsignerMapper.list(map);
                    for (SurveyConsigner surveyConsigner : surveyConsigners) {
                        surveyConsigner.setCompany(surveyConsignor.getCompany());
                        surveyConsigner.setEntrustOrgId(surveyConsignor.getId());
                        surveyConsigner.setEntrustOrgName(surveyConsignor.getName());
                        surveyConsigner.setCode(surveyConsignor.getCode());
                        surveyConsigner.setIsCredit(surveyConsignor.getIsCredit());
                        surveyConsignerMapper.updateByPrimaryKey(surveyConsigner);
                    }

                }

                //添加、更新该委托机构，对应的模板
                SurveyModelInfo surveyModelInfo = surveyModelInfoMapper.selectByPrimaryKey(apiReq.getLong("modelId"));
                SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(apiReq.getLong("id"));
                if(surveyConsignorModel!=null){
                    surveyConsignorModel.setModelId(surveyModelInfo.getId());
                    surveyConsignorModel.setModelName(surveyModelInfo.getName());
                    surveyConsignorModelMapper.updateByPrimaryKeySelective(surveyConsignorModel);
                }else{
                    surveyConsignorModel = new SurveyConsignorModel();
                    surveyConsignorModel.setModelId(surveyModelInfo.getId());
                    surveyConsignorModel.setModelName(surveyModelInfo.getName());
                    surveyConsignorModel.setConsignorId(surveyConsignor.getId());
                    surveyConsignorModel.setConsignorName(surveyConsignor.getCompany());
                    surveyConsignorModelMapper.insertSelective(surveyConsignorModel);
                }
            }
            //调查方认证
            else if("investigator".equals(surveyCode)){
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyInvestigator == null){

                }else{
                    //修改
                    surveyInvestigator = ConvertToBeanUtil.toBean(apiReq,surveyInvestigator);
                    String teacherUserId=apiReq.get("teacherUserId").toString();
                    if(teacherUserId != null && !teacherUserId.equals("")){
                        SurveyInvestigator surveyInvestigatorTwo=surveyInvestigatorMapper.selectByUserId(Long.parseLong(teacherUserId));
                        if(surveyInvestigatorTwo != null){
                            surveyInvestigator.setTeacherUserId(Integer.parseInt(surveyInvestigatorTwo.getUserId().toString()));
                            surveyInvestigator.setTeacherUserName(surveyInvestigatorTwo.getRealName());
                            surveyInvestigator.setTeacherOrgId(Integer.parseInt(surveyInvestigatorTwo.getOrgId().toString()));
                            surveyInvestigator.setTeacherOrgName(surveyInvestigatorTwo.getOrgName());
                            surveyInvestigator.setTeacherUserTel(surveyInvestigatorTwo.getTel());
                        }
                    }
                    surveyInvestigator.setUpdateBy(userId);// 更新人id
                    surveyInvestigator.setUpdateTime(new Date());//更新时间

                    //原因：认证通过时，可直接选择称号
                    Long levelId = apiReq.getLong("titleId");
                    SurveyLevel surveyLevel = surveyLevelMapper.selectByPrimaryKey(levelId);
                    if(surveyLevel != null){
                        surveyInvestigator.setTitleId(surveyLevel.getId());
                        surveyInvestigator.setTitleName(surveyLevel.getName());
                    }


                    //成就点数规则 -- 认证成就点数
                    SurveyAchieveRule surveyAchieveRule = surveyAchieveRuleMapper.selectByCode("rz");
                    if(surveyAchieveRule != null){
                        //生成成就点数明细
                        SurveyAchieveDetail detail =new SurveyAchieveDetail();
                        //因认证通过时，可直接选择称号，两种情况：1、选择最低等级，根据“成就点数规则”；2、选择更高等级，需根据等级对应的成就点

                        surveyInvestigator.setAchPoint(surveyAchieveRule.getNum() + surveyLevel.getSuccessPoint() );
                        detail.setAchNum(surveyAchieveRule.getNum() + surveyLevel.getSuccessPoint() );//1、规则对应点数

                        detail.setAchRemark("认证获取的成就点数");//获得成就点数备注
                        detail.setSurveyUserId(surveyInvestigator.getUserId());//调查员ID
                        detail.setSurveyUserName(surveyInvestigator.getRealName());//调查员姓名
                        detail.setCreateTime(new Date());//时间
                        detail.setOprType(1);//业务类型（1.认证，2.业务案件，3.论坛，4.业务案件评星）
                        surveyAchieveDetailMapper.insertSelective(detail);
                    }

                    //乐凡币规则 -- 调查员认证
                    SurveyLfcoinRule surveyLfcoinRule = surveyLfcoinRuleMapper.selectByCode("rz");
                    if(surveyLfcoinRule != null){

                        surveyInvestigator.setLefanCurrency(surveyLfcoinRule.getNum());

                        //生成乐凡币明细
                        SurveyLfcoinDetail detail = new SurveyLfcoinDetail();
                        detail.setLefanCoin(surveyLfcoinRule.getNum());//乐凡币数量
                        detail.setConsumeType(1);//消费类型（1，获得，2：消费）
                        detail.setSurveyUserId(surveyInvestigator.getUserId());//调查员ID
                        detail.setSurveyUserName(surveyInvestigator.getRealName());//调查员姓名
                        detail.setCreateTime(new Date());//时间
                        detail.setOprType(1);//类型（1.认证，2.业务案件，3.论坛.4.案件评星，5.兑换）
                        detail.setRemark("认证获取的乐凡币");
                        surveyLfcoinDetailMapper.insertSelective(detail);
                    }
                    //手写签名
                    String img = apiReq.getString("proFile");
                    SurveyUserSign surveyUserSign = surveyUserSignMapper.selectByUserId(surveyInvestigator.getUserId());
                    if (surveyUserSign != null) {
                        surveyUserSign.setSignImg(img);
                        surveyUserSign.setUpdateBy(userInfo.getUserName());
                        surveyUserSign.setUpdateTime(new Date());
                        surveyUserSignMapper.updateByPrimaryKey(surveyUserSign);
                    }else{
                        surveyUserSign = new SurveyUserSign();
                        surveyUserSign.setUserId(surveyInvestigator.getUserId());
                        surveyUserSign.setUserName(surveyInvestigator.getRealName());
                        surveyUserSign.setSignImg(img);
                        surveyUserSign.setCreateBy(userInfo.getUserName());
                        surveyUserSign.setCreateTime(new Date());
                        surveyUserSign.setUpdateBy(userInfo.getUserName());
                        surveyUserSign.setUpdateTime(new Date());
                        surveyUserSign.setDeleteFlag(0);
                        surveyUserSignMapper.insert(surveyUserSign);
                    }
                    surveyInvestigator.setAuthType(2);
                    surveyInvestigatorMapper.updateByPrimaryKey(surveyInvestigator);
                }
            }
            //乐凡币介绍
            else if("lfcoinExplain".equals(surveyCode)){
                SurveyLfcoinExplain surveyLfcoinExplain = surveyLfcoinExplainMapper.selectByPrimaryKey(apiReq.getLong("id"));
                String remark = apiReq.getString("content");
                if(surveyLfcoinExplain == null){
                    //保存
                    surveyLfcoinExplain = ConvertToBeanUtil.toBean(apiReq, SurveyLfcoinExplain.class);
                    surveyLfcoinExplain.setCreateByName(userName);// 发起人
                    surveyLfcoinExplain.setCreateBy(userId);// 发起人id
                    surveyLfcoinExplain.setCreateTime(new Date());//创建时间
                    surveyLfcoinExplain.setDeleteFlag(0);
                    surveyLfcoinExplain.setRemark(remark);
                    surveyLfcoinExplainMapper.insertSelective(surveyLfcoinExplain);
                }else{
                    //修改
                    surveyLfcoinExplain = ConvertToBeanUtil.toBean(apiReq,surveyLfcoinExplain);
                    surveyLfcoinExplain.setUpdateBy(userId);// 更新人id
                    surveyLfcoinExplain.setUpdateTime(new Date());//更新时间
                    surveyLfcoinExplain.setRemark(remark);
                    surveyLfcoinExplainMapper.updateByPrimaryKey(surveyLfcoinExplain);
                }
            }
            //乐凡币规则
            else if("lfcoinRule".equals(surveyCode)){
                SurveyLfcoinRule surveyLfcoinRule = surveyLfcoinRuleMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyLfcoinRule == null){
                    //保存
                    surveyLfcoinRule = ConvertToBeanUtil.toBean(apiReq, SurveyLfcoinRule.class);
                    surveyLfcoinRule.setCreateByName(userName);// 发起人
                    surveyLfcoinRule.setCreateBy(userId);// 发起人id
                    surveyLfcoinRule.setCreateTime(new Date());//创建时间
                    surveyLfcoinRule.setDeleteFlag(0);
                    surveyLfcoinRuleMapper.insertSelective(surveyLfcoinRule);
                }else{
                    //修改
                    surveyLfcoinRule = ConvertToBeanUtil.toBean(apiReq,surveyLfcoinRule);
                    surveyLfcoinRule.setUpdateBy(userId);// 更新人id
                    surveyLfcoinRule.setUpdateTime(new Date());//更新时间
                    surveyLfcoinRuleMapper.updateByPrimaryKey(surveyLfcoinRule);
                }
            }
            //成就点规则
            else if("achieveRule".equals(surveyCode)){
                SurveyAchieveRule surveyAchieveRule = surveyAchieveRuleMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyAchieveRule == null){
                    //保存
                    surveyAchieveRule = ConvertToBeanUtil.toBean(apiReq, SurveyAchieveRule.class);
                    surveyAchieveRule.setCreateByName(userName);// 发起人
                    surveyAchieveRule.setCreateBy(userId);// 发起人id
                    surveyAchieveRule.setCreateTime(new Date());//创建时间
                    surveyAchieveRule.setDeleteFlag(0);
                    surveyAchieveRuleMapper.insertSelective(surveyAchieveRule);
                }else{
                    //修改
                    surveyAchieveRule = ConvertToBeanUtil.toBean(apiReq,surveyAchieveRule);
                    surveyAchieveRule.setUpdateBy(userId);// 更新人id
                    surveyAchieveRule.setUpdateTime(new Date());//更新时间
                    surveyAchieveRuleMapper.updateByPrimaryKey(surveyAchieveRule);
                }
            }
            //帖子类别
            else if("knowledgeType".equals(surveyCode)){
                SurveyKnowledgeType surveyKnowledgeType = surveyKnowledgeTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyKnowledgeType == null){
                    //保存
                    surveyKnowledgeType = ConvertToBeanUtil.toBean(apiReq, SurveyKnowledgeType.class);
                    surveyKnowledgeType.setCreateByName(userName);// 发起人
                    surveyKnowledgeType.setCreateBy(userId);// 发起人id
                    surveyKnowledgeType.setCreateTime(new Date());//创建时间
                    surveyKnowledgeType.setDeleteFlag(0);
                    surveyKnowledgeTypeMapper.insert(surveyKnowledgeType);
                }else{
                    //修改
                    surveyKnowledgeType = ConvertToBeanUtil.toBean(apiReq,surveyKnowledgeType);
                    surveyKnowledgeType.setUpdateBy(userId);// 更新人id
                    surveyKnowledgeType.setUpdateTime(new Date());//更新时间
                    surveyKnowledgeTypeMapper.updateByPrimaryKey(surveyKnowledgeType);
                }
            }
            //论坛帖子
            else if("knowledgeBase".equals(surveyCode)){
                SurveyKnowledgeBase surveyKnowledgeBase = surveyKnowledgeBaseMapper.selectByPrimaryKey(apiReq.getLong("id"));

                String content = apiReq.getString("content");
                String img = apiReq.getString("fileImg");
                CommonFile commonFile = new CommonFile();
                String name = null;
                //图片保存
                if(img!=null){
                    commonFile.setFilePath(img);
                    int firstName = img.lastIndexOf("\\") + 1 ;
                    int lastName = img.lastIndexOf(".");
                    name = img.substring(firstName,lastName);
                    commonFile.setFileName(name);
                    commonFile.setCreateTime(new Date());
                    commonFileMapper.insertSelective(commonFile);
                }

                if(surveyKnowledgeBase == null){
                    //保存
                    surveyKnowledgeBase = ConvertToBeanUtil.toBean(apiReq, SurveyKnowledgeBase.class);
                    surveyKnowledgeBase.setCreateByName(userName);// 发起人
                    surveyKnowledgeBase.setCreateBy(userId);// 发起人id
                    surveyKnowledgeBase.setCreateTime(new Date());//创建时间
                    surveyKnowledgeBase.setIsDelete(0);
                    surveyKnowledgeBase.setContent(content);

                    surveyKnowledgeBase.setAuthUserId(userId);//发布人
                    surveyKnowledgeBase.setAuthUserName(userName); //发布人
                    if(img!=null){
                        surveyKnowledgeBase.setFileId(commonFile.getId());
                        surveyKnowledgeBase.setFileName(img);
                    }
                    surveyKnowledgeBaseMapper.insert(surveyKnowledgeBase);
                }else{
                    //修改
                    surveyKnowledgeBase = ConvertToBeanUtil.toBean(apiReq,surveyKnowledgeBase);
                    surveyKnowledgeBase.setUpdateBy(userId);// 更新人id
                    surveyKnowledgeBase.setUpdateTime(new Date());//更新时间
                    surveyKnowledgeBase.setContent(content);
                    if(img!=null){
                        surveyKnowledgeBase.setFileId(commonFile.getId());
                        surveyKnowledgeBase.setFileName(img);
                    }
                    surveyKnowledgeBaseMapper.updateByPrimaryKey(surveyKnowledgeBase);
                }
            }
            //帖子评论
            else if("knowledgeComment".equals(surveyCode)){

            }
            //调查委托方价格
            else if("consignorPrice".equals(surveyCode)){
                Double price = apiReq.getDouble("price");
                Long enturyId = apiReq.getLong("enturyId");
                String enturyName = apiReq.getString("enturyName");
                Map<String,Object> map = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("taskId",apiReq.getLong("taskId"));
                map.put("enturyId",enturyId);
                SurveyConsignorPrice surveyConsignorPrice = surveyConsignorPriceMapper.selectInfo(map);
                if(surveyConsignorPrice!=null){
                    surveyConsignorPrice.setTaskPrice(price);
                }else{
                    surveyConsignorPrice = new SurveyConsignorPrice();
                    surveyConsignorPrice.setEnturyId(enturyId);
                    surveyConsignorPrice.setEnturyName(enturyName);
                    surveyConsignorPrice.setTaskId(apiReq.getLong("taskId"));
                    surveyConsignorPrice.setTaskName(apiReq.getString("taskName"));
                    surveyConsignorPrice.setDeleteFlag(0);
                    surveyConsignorPrice.setTaskPrice(price);

                    CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                    surveyConsignorPrice.setAreaId(area.getAreaId().intValue());
                    surveyConsignorPrice.setAreaType(area.getAreaType());
                    surveyConsignorPrice.setAreaName(area.getAreaLongname());

                    surveyConsignorPrice.setProvince(apiReq.getString("province"));
                    surveyConsignorPrice.setProvinceId(apiReq.getInt("provinceId"));
                    surveyConsignorPrice.setCity(apiReq.getString("city"));
                    surveyConsignorPrice.setCityId(apiReq.getInt("cityId"));
                    surveyConsignorPrice.setDistrict(apiReq.getString("district"));
                    surveyConsignorPrice.setDistrictId(apiReq.getInt("districtId"));

                    surveyConsignorPriceMapper.insertSelective(surveyConsignorPrice);
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
                surveyConsignorPrice.setUpdateTime(new Date());//修改时间
                surveyConsignorPriceMapper.updateByPrimaryKeySelective(surveyConsignorPrice);
            }
            //调查调查方价格
            else if("franchiseePrice".equals(surveyCode)){
                Double price = apiReq.getDouble("price");

                Map<String,Object> map = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("taskId",apiReq.getLong("taskId"));
                map.put("franchiseeId",apiReq.getLong("franchiseeId"));
                SurveyFranchiseePrice surveyFranchiseePrice = surveyFranchiseePriceMapper.selectInfo(map);

                if(surveyFranchiseePrice!=null){
                    surveyFranchiseePrice.setTaskPrice(price);
                }else{
                    surveyFranchiseePrice = new SurveyFranchiseePrice();
                    surveyFranchiseePrice.setFranchiseeId(apiReq.getLong("franchiseeId"));
                    surveyFranchiseePrice.setFranchiseeName(apiReq.getString("franchiseeName"));
                    surveyFranchiseePrice.setTaskId(apiReq.getLong("taskId"));
                    surveyFranchiseePrice.setTaskName(apiReq.getString("taskName"));
                    surveyFranchiseePrice.setDeleteFlag(0);
                    surveyFranchiseePrice.setTaskPrice(price);

                    CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                    surveyFranchiseePrice.setAreaId(area.getAreaId().intValue());
                    surveyFranchiseePrice.setAreaType(area.getAreaType());
                    surveyFranchiseePrice.setAreaName(area.getAreaLongname());

                    surveyFranchiseePrice.setProvince(apiReq.getString("province"));
                    surveyFranchiseePrice.setProvinceId(apiReq.getInt("provinceId"));
                    surveyFranchiseePrice.setCity(apiReq.getString("city"));
                    surveyFranchiseePrice.setCityId(apiReq.getInt("cityId"));
                    surveyFranchiseePrice.setDistrict(apiReq.getString("district"));
                    surveyFranchiseePrice.setDistrictId(apiReq.getInt("districtId"));

                    surveyFranchiseePriceMapper.insertSelective(surveyFranchiseePrice);
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
                surveyFranchiseePrice.setUpdateTime(new Date());
                surveyFranchiseePriceMapper.updateByPrimaryKeySelective(surveyFranchiseePrice);

            }
            //qa问答
            else if("qa".equals(surveyCode)){
                SurveyQa surveyQa = surveyQaMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyQa == null){
                    //保存
                    surveyQa = ConvertToBeanUtil.toBean(apiReq, SurveyQa.class);
                    surveyQa.setQuestionUserName(userName);// 问题人
                    surveyQa.setQuestionUserId(userId);// 问题人id
                    surveyQa.setQuestionTime(new Date());//问题时间
                    surveyQa.setQuestionType(1);//系统
                    surveyQa.setQuestionState(2); //已回复
                    surveyQa.setDeleteFlag(0);  //删除标识
                    surveyQaMapper.insert(surveyQa);
                }else{
                    //修改
                    surveyQa = ConvertToBeanUtil.toBean(apiReq,surveyQa);
                    surveyQaMapper.updateByPrimaryKey(surveyQa);
                }
            }
            //调查调查方
            else if("franchisee".equals(surveyCode)){
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyFranchisee == null){
                    SurveyFranchisee franName = surveyFranchiseeMapper.selectByName(apiReq.getString("name"));
                    if(franName!=null){
                        return new ApiResponse(ApiMsgEnum.SURVEY_NAME_ERROR);
                    }

                    //保存
                    surveyFranchisee = ConvertToBeanUtil.toBean(apiReq, SurveyFranchisee.class);
                    surveyFranchisee.setCreateByName(userName);// 发起人
                    surveyFranchisee.setCreateBy(userId);// 发起人id
                    surveyFranchisee.setCreateTime(new Date());//创建时间
                    surveyFranchisee.setDeleteFlag(0);
                    if(surveyFranchisee.getDepartmentId()!=null && !surveyFranchisee.getDepartmentId().equals("")){
                        StaffOrgan staffOrgan=staffOrganMapper.selectByPrimaryKey(Long.parseLong(surveyFranchisee.getDepartmentId().toString()));
                        if(staffOrgan != null){
                            surveyFranchisee.setDepartmentName(staffOrgan.getName());
                        }else{
                            surveyFranchisee.setDepartmentId(null);
                            surveyFranchisee.setDepartmentName(null);
                        }

                    }
                    //保存父级，调查方级别
                    Long parentId = apiReq.getLong("parentId");
                    if(parentId ==null){
                        surveyFranchisee.setParentId(0L);
                        surveyFranchisee.setLevel(1);//一级--顶级
                    }else{
                        SurveyFranchisee franchisee =  surveyFranchiseeMapper.selectByParentId(parentId);
                        if(franchisee!=null){
                            surveyFranchisee.setParentId(parentId);
                            surveyFranchisee.setLevel(franchisee.getLevel() + 1);//父级的调查方级别+1
                        }
                    }
                    surveyFranchisee.setMedicalMoney(100D);//住院病例调查，每多一份病例，价格增加金额

                    Map<String,Object> paramMap =  new HashMap<String,Object>();
                    paramMap.put("roleId",58L);
                    paramMap.put("orgId",surveyFranchisee.getId());
                    List<UserInfo> users = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);//机构经理
                    for (UserInfo user : users) {
                        if (surveyFranchisee.getAloneBill() != null && surveyFranchisee.getAloneBill() == 1){
                            //给机构负责人加上 机构经理的角色
                            paramMap.clear();
                            paramMap.put("userId",user.getUserId());
                            paramMap.put("roleId",31L);
                            List<BusUserRole> userRoles = busUserRoleMapper.selectBusInfo(paramMap);
                            if (userRoles.size() == 0){
                                BusUserRole busUserRole = new BusUserRole();
                                busUserRole.setUserId(user.getUserId());
                                busUserRole.setRoleId(31L);
                                busUserRoleMapper.insert(busUserRole);
                            }
                        }else{
                            paramMap.clear();
                            paramMap.put("userId",user.getUserId());
                            paramMap.put("roleId",31);
                            busUserRoleMapper.deleteByParam(paramMap);
                        }
                    }
                    Integer busType=apiReq.getInt("busType");
                    surveyFranchisee.setBusType(busType);
                    surveyFranchiseeMapper.insert(surveyFranchisee);
                    surveyFranchisee.setCode("JM"+surveyFranchisee.getId());
                    surveyFranchiseeMapper.updateByPrimaryKeySelective(surveyFranchisee);
                }else{
                    //修改
                    surveyFranchisee = ConvertToBeanUtil.toBean(apiReq,surveyFranchisee);
                    surveyFranchisee.setUpdateBy(userId);// 更新人id
                    surveyFranchisee.setUpdateTime(new Date());//更新时间
                    if(surveyFranchisee.getDepartmentId()!=null && !surveyFranchisee.getDepartmentId().equals("")){
                        StaffOrgan staffOrgan=staffOrganMapper.selectByPrimaryKey(Long.parseLong(surveyFranchisee.getDepartmentId().toString()));
                        if(staffOrgan != null){
                            surveyFranchisee.setDepartmentName(staffOrgan.getName());
                        }else{
                            surveyFranchisee.setDepartmentId(null);
                            surveyFranchisee.setDepartmentName(null);
                        }
                    }
                    Map<String,Object> paramMap =  new HashMap<String,Object>();
                    paramMap.put("roleId",58L);
                    paramMap.put("orgId",surveyFranchisee.getId());
                    List<UserInfo> users = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);//机构经理
                    for (UserInfo user : users) {
                        if (surveyFranchisee.getAloneBill() != null && surveyFranchisee.getAloneBill() == 1){
                            //给机构负责人加上 机构经理的角色
                            paramMap.clear();
                            paramMap.put("userId",user.getUserId());
                            paramMap.put("roleId",31L);
                            List<BusUserRole> userRoles = busUserRoleMapper.selectBusInfo(paramMap);
                            if (userRoles.size() == 0){
                                BusUserRole busUserRole = new BusUserRole();
                                busUserRole.setUserId(user.getUserId());
                                busUserRole.setRoleId(31L);
                                busUserRoleMapper.insert(busUserRole);
                            }
                        }else{
                            paramMap.clear();
                            paramMap.put("userId",user.getUserId());
                            paramMap.put("roleId",31);
                            busUserRoleMapper.deleteByParam(paramMap);
                        }
                    }

                    //保存父级，调查方级别
                    Long parentId = apiReq.getLong("parentId");
                    if(parentId ==null){
                        surveyFranchisee.setParentId(0L);
                        surveyFranchisee.setLevel(1);//一级--顶级
                    }else{
                        SurveyFranchisee franchisee =  surveyFranchiseeMapper.selectByParentId(parentId);
                        if(franchisee!=null){
                            surveyFranchisee.setParentId(parentId);
                            surveyFranchisee.setLevel(franchisee.getLevel() + 1);//父级的调查方级别+1
                        }
                    }

                    Integer busType=apiReq.getInt("busType");
                    surveyFranchisee.setBusType(busType);
                    surveyFranchiseeMapper.updateByPrimaryKey(surveyFranchisee);

                    paramMap =  new HashMap<String,Object>();
                    paramMap.put("orgId",surveyFranchisee.getId());
                    List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.list(paramMap);
                    for (SurveyInvestigator surveyInvestigator : surveyInvestigators) {
                        surveyInvestigator.setOrgId(surveyFranchisee.getId());      //机构id
                        surveyInvestigator.setOrgName(surveyFranchisee.getName());
                        surveyInvestigator.setType(surveyFranchisee.getType());
                        surveyInvestigator.setBusType(busType);
                        surveyInvestigatorMapper.updateByPrimaryKey(surveyInvestigator);
                    }
                }
            }
            //商品
            else if("product".equals(surveyCode)){
                SurveyProductDto surveyProduct = surveyProductMapper.selectByPrimaryKey(apiReq.getLong("id"));

                String remark = apiReq.getString("content");
                String img = apiReq.getString("proFile");
                CommonFile commonFile = new CommonFile();
                //图片保存
                if(img!=null){
                    commonFile.setFilePath(img);
                    int firstName = img.lastIndexOf("\\") + 1 ;
                    int lastName = img.lastIndexOf(".");
                    String name = img.substring(firstName,lastName);
                    commonFile.setFileName(name);
                    commonFile.setCreateTime(new Date());
                    commonFileMapper.insertSelective(commonFile);
                }

                if(surveyProduct == null){
                    //保存
                    surveyProduct = ConvertToBeanUtil.toBean(apiReq, SurveyProductDto.class);
                    surveyProduct.setCreateBy(userInfo.getUserName());// 发起人
                    surveyProduct.setCreateTime(new Date());//创建时间
                    surveyProduct.setIsDelete(0);  //删除标识
                    surveyProduct.setIsUp(0); //上架标识
                    surveyProduct.setRemark(remark);
                    if(img!=null){
                        surveyProduct.setFileId(commonFile.getId());
                        surveyProduct.setProFile(img);
                    }
                    surveyProductMapper.insert(surveyProduct);
                    surveyProduct.setCode("SP"+surveyProduct.getId());
                    surveyProductMapper.updateByPrimaryKeySelective(surveyProduct);
                    //处理角色、等级
                    operateLevelAndRole(apiReq,surveyProduct,false);
                }else{
                    //修改
                    surveyProduct = ConvertToBeanUtil.toBean(apiReq,surveyProduct);
                    surveyProduct.setRemark(remark);
                    if(img!=null){
                        surveyProduct.setFileId(commonFile.getId());
                        surveyProduct.setProFile(img);
                    }
                    //处理角色、等级
                    operateLevelAndRole(apiReq,surveyProduct,true);
                    surveyProductMapper.updateByPrimaryKeySelective(surveyProduct);
                }
            }
            //委托方默认价格
            else if("commonAreaPrice".equals(surveyCode)){
                Map<String,Object> map  = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("taskId",apiReq.getLong("taskId"));
                SurveyCommonAreaPrice surveyCommonAreaPrice = surveyCommonAreaPriceMapper.selectByInfo(map);
                if(surveyCommonAreaPrice == null){

                }else{
                    //修改
                    surveyCommonAreaPrice.setPrice(apiReq.getDouble("price"));
                    surveyCommonAreaPriceMapper.updateByInfo(surveyCommonAreaPrice);
                }
            }
            //调查方默认价格
            else if("investigatorAreaPrice".equals(surveyCode)){
                Map<String,Object> map  = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("taskId",apiReq.getLong("taskId"));
                SurveyInvestigatorAreaPrice surveyInvestigatorAreaPrice = surveyInvestigatorAreaPriceMapper.selectByInfo(map);
                if(surveyInvestigatorAreaPrice == null){

                }else{
                    //修改
                    surveyInvestigatorAreaPrice.setPrice(apiReq.getDouble("price"));
                    surveyInvestigatorAreaPriceMapper.updateByInfo(surveyInvestigatorAreaPrice);
                }
            }
            //服务区域
            else if("serviceArea".equals(surveyCode)){
                SurveyServiceArea surveyServiceArea = surveyServiceAreaMapper.selectByPrimaryKey(apiReq.getLong("id"));
                String remark = apiReq.getString("content");

                if(surveyServiceArea == null){
                    //保存
                    surveyServiceArea = ConvertToBeanUtil.toBean(apiReq, SurveyServiceArea.class);
                    surveyServiceArea.setCreateBy(userInfo.getUserId());// 发起人
                    surveyServiceArea.setCreateTime(new Date());//创建时间
                    surveyServiceArea.setDeleteFlag(0);  //删除标识
                    surveyServiceArea.setReamrk(remark);
                    surveyServiceAreaMapper.insert(surveyServiceArea);
                }else{
                    //修改
                    surveyServiceArea = ConvertToBeanUtil.toBean(apiReq,surveyServiceArea);
                    surveyServiceArea.setReamrk(remark);
                    surveyServiceArea.setUpdateTime(new Date());
                    surveyServiceArea.setUpdateBy(userInfo.getUserId());
                    surveyServiceAreaMapper.updateByPrimaryKeySelective(surveyServiceArea);
                }
            }
            //委托方机构-部门信息
            else if("consignorDepartment".equals(surveyCode)){
                SurveyConsignorDepartment surveyConsignorDepartment = surveyConsignorDepartmentMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyConsignorDepartment == null){
                    //保存
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(apiReq.getLong("consignorId"));

                    surveyConsignorDepartment = ConvertToBeanUtil.toBean(apiReq, SurveyConsignorDepartment.class);
                    surveyConsignorDepartment.setConsignorId(surveyConsignor.getId());
                    surveyConsignorDepartment.setConsignorName(surveyConsignor.getName());
                    surveyConsignorDepartment.setCreateBy(userName);// 发起人
                    surveyConsignorDepartment.setCreateById(userId);// 发起人id
                    surveyConsignorDepartment.setCreateTime(new Date());//创建时间
                    surveyConsignorDepartment.setDeleteFlag(0);
                    surveyConsignorDepartmentMapper.insert(surveyConsignorDepartment);
                }else{
                    //修改
                    surveyConsignorDepartment = ConvertToBeanUtil.toBean(apiReq,surveyConsignorDepartment);
                    surveyConsignorDepartment.setUpdateById(userId);// 更新人id
                    surveyConsignorDepartment.setUpdateBy(userName);
                    surveyConsignorDepartment.setUpdateTime(new Date());//更新时间
                    surveyConsignorDepartmentMapper.updateByPrimaryKey(surveyConsignorDepartment);
                }
            }
            //任务类型-方向名称
            else if("taskInfoContent".equals(surveyCode)){
                SurveyTaskInfoContent surveyTaskInfoContent = surveyTaskInfoContentMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyTaskInfoContent == null){
                    //保存
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(apiReq.getLong("taskInfoId"));

                    surveyTaskInfoContent = ConvertToBeanUtil.toBean(apiReq, SurveyTaskInfoContent.class);
                    surveyTaskInfoContent.setTaskInfoId(surveyTaskInfo.getId());
                    surveyTaskInfoContent.setTaskInfoName(surveyTaskInfo.getName());
                    surveyTaskInfoContent.setCreateBy(userName);// 发起人
                    surveyTaskInfoContent.setCreateById(userId);// 发起人id
                    surveyTaskInfoContent.setCreateTime(new Date());//创建时间
                    surveyTaskInfoContent.setDeleteFlag(0);
                    surveyTaskInfoContentMapper.insert(surveyTaskInfoContent);
                }else{
                    //修改
                    surveyTaskInfoContent = ConvertToBeanUtil.toBean(apiReq,surveyTaskInfoContent);
                    surveyTaskInfoContent.setUpdateById(userId);// 更新人id
                    surveyTaskInfoContent.setUpdateBy(userName);
                    surveyTaskInfoContent.setUpdateTime(new Date());//更新时间
                    surveyTaskInfoContentMapper.updateByPrimaryKey(surveyTaskInfoContent);
                }
            }
            //添加用户
            else if("userInfo".equals(surveyCode)){
                UserInfo newUserInfo = userInfoMapper.selectByPrimaryKey(apiReq.getLong("userId"));
                userName = apiReq.getString("userName");
                String nickName = apiReq.getString("nickName");
                String userTel = apiReq.getString("userTel");
                if(newUserInfo == null){

                    //先添加login信息
                    UserLogin userLogin = userLoginMapper.selectByPhone(userTel);
                    if(userLogin != null){
                        return  new ApiResponse(ApiMsgEnum.UsernameBeenRegistered);
                    }
                    userLogin = new UserLogin();
                    userLogin.setUserTelphone(userTel);
                    userLogin.setPassword(MD5Util.MD5Encode("123456@Qaz", null));
                    userLogin.setCreateTime(new Date());
                    int ret = userLoginMapper.insertSelective(userLogin);
                    if(ret < 1){
                        return new ApiResponse(ApiMsgEnum.FAIL);
                    }

                    UserInfo record = new UserInfo();
                    record.setUserId(userLogin.getUserId());
                    record.setUserName(userName);
                    record.setNickName(nickName);
                    record.setUserTel(userTel);
                    record.setIsPromoter(0);//是否是推广人(1:否，0：是)
                    record.setUserState(0);//用户状态(1:禁用，0：正常)
                    record.setCreateTime(new Date());
                    record.setCreateBy(apiReq.getString("operatorName"));
                    record.setIsTester(0);//是否为测试人员：0、不是；1、是
                    record.setDeleteFlag(0);//是否删除(0:否，1：是)
                    record.setUserType(1);//用户类型：1普通用户，2机构用户，3测试用户，4其他
                    userInfoMapper.insertSelective(record);
                    //添加用户账号信息
                    UserAccount userAccount = new UserAccount();
                    userAccount.setUserId(record.getUserId());
                    userAccount.setUserRecharge(0D);
                    userAccount.setWithdrawDeposit(0D);
                    userAccountMapper.insertSelective(userAccount);

                    BusUserRole busUserRole = new BusUserRole();
                    busUserRole.setUserId(record.getUserId());
                    busUserRole.setRoleId(6L);
                    busUserRoleMapper.insertSelective(busUserRole);
                }else{
                    //修改
                    newUserInfo.setUserName(userName);
                    newUserInfo.setNickName(nickName);
                    userInfoMapper.updateByPrimaryKey(newUserInfo);
                }
            }
            //任务类型-方向名称
            else if("bankCard".equals(surveyCode)){
                SurveyBankCard surveyBankCard = surveyBankCardMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyBankCard == null){
                    String img = apiReq.getString("proFile");
                    //保存
                    surveyBankCard = ConvertToBeanUtil.toBean(apiReq, SurveyBankCard.class);
                    surveyBankCard.setCardImg(img);
                    surveyBankCard.setUserName(userName);// 发起人
                    surveyBankCard.setUserId(userId);// 发起人id
                    surveyBankCard.setInvestigatorName(userName);
                    surveyBankCard.setInvestigatorId(userId);
                    surveyBankCard.setCreateId(userId);
                    surveyBankCard.setCreateName(userName);
                    surveyBankCard.setCreateTime(new Date());//创建时间
                    surveyBankCard.setDeleteFlag(0);
                    surveyBankCardMapper.insert(surveyBankCard);
                }else{
                    //修改
                    String img = apiReq.getString("proFile");
                    surveyBankCard = ConvertToBeanUtil.toBean(apiReq,surveyBankCard);
                    surveyBankCard.setCardImg(img);
                    surveyBankCardMapper.updateByPrimaryKey(surveyBankCard);
                }
            }
            //开票-开票公司
            else if("applyCorporation".equals(surveyCode)){
                BillingApplyCorporation applyCorporation = billingApplyCorporationMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(applyCorporation == null){
                    //保存
                    applyCorporation = ConvertToBeanUtil.toBean(apiReq, BillingApplyCorporation.class);
                    applyCorporation.setCreateBy(userName);
                    applyCorporation.setCreateTime(new Date());//创建时间
                    applyCorporation.setDeleteFlag(0);
                    billingApplyCorporationMapper.insert(applyCorporation);
                }else{
                    //修改
                    applyCorporation = ConvertToBeanUtil.toBean(apiReq,applyCorporation);
                    applyCorporation.setUpdateBy(userName);
                    applyCorporation.setUpdateTime(new Date());
                    billingApplyCorporationMapper.updateByPrimaryKey(applyCorporation);
                }
            }
            //开票-开票公司
            else if("applyCorporationEnum".equals(surveyCode)){
                Long corporationId = apiReq.getLong("corporationId");
                String corporationName = null;
                BillingApplyCorporation applyCorporation = billingApplyCorporationMapper.selectByPrimaryKey(corporationId);
                if(applyCorporation!=null){
                    corporationName = applyCorporation.getName();
                }
                //先删除记录，再保存全新数据
                Map<String,Object> map = new HashMap<>();
                map.put("corporationId",corporationId);
                billingApplyCorporationEnumMapper.deleteByInfo(map);

                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        map = new HashMap<>();
                        map.put("corporationId",corporationId);
                        map.put("billingEnumId",ro);
                        BillingApplyCorporationEnum applyCorporationEnum = billingApplyCorporationEnumMapper.selectByInfo(map);
                        if(applyCorporationEnum == null){
                            applyCorporationEnum = new BillingApplyCorporationEnum();
                            applyCorporationEnum.setCorporationId(corporationId);
                            applyCorporationEnum.setCorporationName(corporationName);

                            map = new HashMap<>();
                            map.put("parentEnumCode","billingEnum");
                            map.put("enumCode",ro);
                            CommonEnum commonEnum = commonEnumMapper.selectBill(map);
                            if(commonEnum != null){
                                String billingEnumName = commonEnum.getEnumName();
                                applyCorporationEnum.setBillingEnumId(Long.valueOf(ro));
                                applyCorporationEnum.setBillingEnumName(billingEnumName);
                            }
                            billingApplyCorporationEnumMapper.insertSelective(applyCorporationEnum);
                        }
                    }
                }

            }
            //开票-开票公司
            else if("applyEnumItem".equals(surveyCode)){
                //
//                Long corporationId = apiReq.getLong("corporationId");
//                String corporationName = null;
//                BillingApplyCorporation applyCorporation = billingApplyCorporationMapper.selectByPrimaryKey(corporationId);
//                if(applyCorporation!=null){
//                    corporationName = applyCorporation.getName();
//                }

                Long billingEnumId = apiReq.getLong("billingEnumId");
                String billingEnumName = null;
                Map<String,Object> map  = new HashMap<>();
                map.put("parentEnumCode","billingEnum");
                map.put("enumCode",billingEnumId);
                CommonEnum commonEnum = commonEnumMapper.selectBill(map);
                if(commonEnum != null){
                    billingEnumName = commonEnum.getEnumName();
                }

                //先删除记录，再保存全新数据
                map = new HashMap<>();
//                map.put("corporationId",corporationId);
                map.put("billingEnumId",billingEnumId);
                billingApplyEnumItemMapper.deleteByInfo(map);

                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    //公司对应产品
//                    map = new HashMap<>();
//                    map.put("corporationId",corporationId);
//                    map.put("billingEnumId",billingEnumId);
//                    BillingApplyCorporationEnum applyCorporationEnum = billingApplyCorporationEnumMapper.selectByInfo(map);

                    String[] role = buss.split(",");
                    for (String ro : role) {
                        map = new HashMap<>();
//                        map.put("corporationId",corporationId);
                        map.put("billingEnumId",billingEnumId);
                        map.put("billingItemId",ro);
                        BillingApplyEnumItem applyEnumItem = billingApplyEnumItemMapper.selectByInfo(map);
                        if(applyEnumItem == null){
                            BillingApplyEnumItem billingApplyEnumItem = new BillingApplyEnumItem();
                            billingApplyEnumItem.setBillingEnumId(billingEnumId);
                            billingApplyEnumItem.setBillingEnumName(billingEnumName);
//                            billingApplyEnumItem.setCorporationId(corporationId);
//                            billingApplyEnumItem.setCorporationName(corporationName);
//                            billingApplyEnumItem.setBillingCorporationEnumId(applyCorporationEnum.getId());
                            map  = new HashMap<>();
                            map.put("parentEnumCode","billingItem");
                            map.put("enumCode",ro);
                            CommonEnum item = commonEnumMapper.selectBill(map);
                            if(item != null){
                                String billingItemName = item.getEnumName();
                                billingApplyEnumItem.setBillingItemId(Long.valueOf(ro));
                                billingApplyEnumItem.setBillingItemName(billingItemName);
                            }
                            billingApplyEnumItemMapper.insertSelective(billingApplyEnumItem);
                        }
                    }
                }
            }
            //开票-开票产品类型
            else if("applyProductType".equals(surveyCode)){
                BillingApplyProductType applyProductType = billingApplyProductTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(applyProductType == null){
                    //保存
                    applyProductType = ConvertToBeanUtil.toBean(apiReq, BillingApplyProductType.class);
                    applyProductType.setCreateBy(userName);
                    applyProductType.setCreateTime(new Date());//创建时间
                    applyProductType.setDeleteFlag(0);
                    billingApplyProductTypeMapper.insert(applyProductType);
                }else{
                    //修改
                    applyProductType = ConvertToBeanUtil.toBean(apiReq,applyProductType);
                    applyProductType.setUpdateBy(userName);
                    applyProductType.setUpdateTime(new Date());
                    billingApplyProductTypeMapper.updateByPrimaryKey(applyProductType);
                }
            }
            //开票-开票产品
            else if("billingEnum".equals(surveyCode)){
                CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(commonEnum == null){
                    //保存
                    commonEnum = ConvertToBeanUtil.toBean(apiReq, CommonEnum.class);
                    commonEnum.setParentId(apiReq.getLong("parentId"));
                    commonEnum.setCreateBy(userName);
                    commonEnum.setCreateTime(new Date());//创建时间
                    commonEnumMapper.insert(commonEnum);
                    commonEnum.setEnumCode(String.valueOf(commonEnum.getId()+100));
                    commonEnumMapper.updateByPrimaryKey(commonEnum);
                }else{
                    //修改
                    commonEnum = ConvertToBeanUtil.toBean(apiReq,commonEnum);
                    commonEnumMapper.updateByPrimaryKey(commonEnum);
                }
            }
            //开票-开票产品
            else if("billingItem".equals(surveyCode)){
                CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(commonEnum == null){
                    //保存
                    commonEnum = ConvertToBeanUtil.toBean(apiReq, CommonEnum.class);
                    commonEnum.setParentId(apiReq.getLong("parentId"));
                    commonEnum.setCreateBy(userName);
                    commonEnum.setCreateTime(new Date());//创建时间
                    commonEnumMapper.insert(commonEnum);
                    commonEnum.setEnumCode(String.valueOf(commonEnum.getId()+100));
                    commonEnumMapper.updateByPrimaryKey(commonEnum);
                }else{
                    //修改
                    commonEnum = ConvertToBeanUtil.toBean(apiReq,commonEnum);
                    commonEnumMapper.updateByPrimaryKey(commonEnum);
                }
            }
            //开票-开票产品类型对应业务来源
            else if("applyProductOrg".equals(surveyCode)){
                Long productId = apiReq.getLong("productId");
                String productName = null;
                BillingApplyProductType applyProductType = billingApplyProductTypeMapper.selectByPrimaryKey(productId);
                if(applyProductType!=null){
                    productName = applyProductType.getName();
                }
                //先删除记录，再保存全新数据
                Map<String,Object> map = new HashMap<>();
                map.put("productId",productId);
                billingApplyProductOrgMapper.deleteByInfo(map);

                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        BillingApplyProductOrg applyProductOrg = new BillingApplyProductOrg();
                        applyProductOrg.setProductId(productId);
                        applyProductOrg.setProductName(productName);
                        OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(Long.valueOf(ro));
                        applyProductOrg.setOrgId(Long.valueOf(ro));
                        if(orgInfo!=null){
                            applyProductOrg.setOrgName(orgInfo.getOrgName());
                        }
                        billingApplyProductOrgMapper.insertSelective(applyProductOrg);
                    }
                }

            }
            //狄大人平台终审人员对应“委托机构”、“调查方”
            else if("finalJudgmentUser".equals(surveyCode)){
                String btnCode = apiReq.getString("btnCode");
                Long userInfoId = apiReq.getLong("userId");
                //人员信息
                UserInfo userInfo1 = userInfoMapper.selectByPrimaryKey(userInfoId);
                String userInfoName = userInfo1.getUserName();
                //委托方机构
                if("1000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("userId",userInfoId);
                    surveyUserConsignorMapper.deleteByUserId(map);

                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {

                            SurveyUserConsignor surveyUserConsignor =new SurveyUserConsignor();
                            //机构信息
                            SurveyConsignor consignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(consignor!=null){
                                surveyUserConsignor.setConsignorId(consignor.getId());
                                surveyUserConsignor.setConsignorName(consignor.getCompany());
                            }
                            surveyUserConsignor.setUserId(userInfoId);
                            surveyUserConsignor.setUserName(userInfoName);
                            surveyUserConsignor.setDeleteFlag(0);
                            surveyUserConsignorMapper.insertSelective(surveyUserConsignor);
                        }
                    }
                }
                //调查方机构
                else if("2000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("userId",userInfoId);
                    surveyUserFranchiseeMapper.deleteByUserId(map);

                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {

                            SurveyUserFranchisee surveyUserFranchisee =new SurveyUserFranchisee();
                            //机构信息
                            SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(franchisee!=null){
                                surveyUserFranchisee.setFranchiseeId(franchisee.getId());
                                surveyUserFranchisee.setFranchiseeName(franchisee.getName());
                            }
                            surveyUserFranchisee.setUserId(userInfoId);
                            surveyUserFranchisee.setUserName(userInfoName);
                            surveyUserFranchisee.setDeleteFlag(0);
                            surveyUserFranchiseeMapper.insertSelective(surveyUserFranchisee);
                        }
                    }
                }

            }
            //报告模板
            else if("modelInfo".equals(surveyCode)){
                SurveyModelInfo surveyModelInfo = surveyModelInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyModelInfo == null){
                    //保存
                    surveyModelInfo = ConvertToBeanUtil.toBean(apiReq, SurveyModelInfo.class);
                    surveyModelInfo.setCreateBy(userName);// 创建人
                    surveyModelInfo.setCreateTime(new Date());//创建时间
                    surveyModelInfo.setDeleteFlag(0);  //删除标识
                    surveyModelInfoMapper.insert(surveyModelInfo);
                }else{
                    //修改
                    surveyModelInfo = ConvertToBeanUtil.toBean(apiReq,surveyModelInfo);
                    surveyModelInfo.setUpdateBy(userId);// 更新人
                    surveyModelInfo.setUpdateTime(new Date());//更新时间
                    surveyModelInfoMapper.updateByPrimaryKey(surveyModelInfo);
                }
            }

            //狄大人--报告模板对应“委托机构”
            else if("consignorModel".equals(surveyCode)){
                String btnCode = apiReq.getString("btnCode");
                Long modelId = apiReq.getLong("modelId");
                //报告信息
                SurveyModelInfo surveyModelInfo = surveyModelInfoMapper.selectByPrimaryKey(modelId);
                String modelName = surveyModelInfo.getName();
                //委托方机构
                if("1000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("modelId",modelId);
                    surveyConsignorModelMapper.deleteByInfo(map);

                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {
                            //机构信息
                            SurveyConsignor consignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(ro));

                            if (consignor != null) {
                                //查询该机构，是否已经设置了模板，如果没有insert，如果设置了update
                                SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(consignor.getId());

                                if (surveyConsignorModel != null) {
                                    surveyConsignorModel.setModelId(modelId);
                                    surveyConsignorModel.setModelName(modelName);
                                    surveyConsignorModelMapper.updateByPrimaryKeySelective(surveyConsignorModel);
                                } else {
                                    surveyConsignorModel = new SurveyConsignorModel();

                                    surveyConsignorModel.setConsignorId(consignor.getId());
                                    surveyConsignorModel.setConsignorName(consignor.getCompany());
                                    surveyConsignorModel.setModelId(modelId);
                                    surveyConsignorModel.setModelName(modelName);
                                    surveyConsignorModelMapper.insertSelective(surveyConsignorModel);
                                }
                            }
                        }
                    }
                }
            }
            //委托机构--报告命名规则
            else if("consignorReportRule".equals(surveyCode)){
                SurveyConsignorReportRule surveyConsignorReportRule = surveyConsignorReportRuleMapper.selectByConsignorId(apiReq.getLong("surveyConsignorId"));
                if(surveyConsignorReportRule == null){
                    //保存
                    surveyConsignorReportRule = ConvertToBeanUtil.toBean(apiReq, SurveyConsignorReportRule.class);
                    surveyConsignorReportRuleMapper.insert(surveyConsignorReportRule);
                }else{
                    //修改
                    surveyConsignorReportRule = ConvertToBeanUtil.toBean(apiReq,surveyConsignorReportRule);
                    surveyConsignorReportRuleMapper.updateByPrimaryKey(surveyConsignorReportRule);
                }
            }
            //委托方机构-开票主体
            else if("consignorBillSubject".equals(surveyCode)){
                BillingApplyCompany billingApplyCompany = billingApplyCompanyMapper.selectByPrimaryKey(apiReq.getLong("id"));

//                SurveyConsignorBillSubject surveyConsignorBillSubject = surveyConsignorBillSubjectMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(billingApplyCompany == null){
                    //保存
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(apiReq.getLong("consignorId"));
                    billingApplyCompany = ConvertToBeanUtil.toBean(apiReq, BillingApplyCompany.class);
                    billingApplyCompany.setEntrustOrgId(surveyConsignor.getId());
                    billingApplyCompany.setEntrustOrgName(surveyConsignor.getName());
                    billingApplyCompany.setCreateBy(userInfo.getUserId());// 发起人
                    billingApplyCompany.setCreateByName(userInfo.getUserName());// 发起人id
                    billingApplyCompany.setCreateTime(new Date());//创建时间
                    billingApplyCompany.setDeleteFlag(0);
                    billingApplyCompany.setState(0);
                    billingApplyCompanyMapper.insert(billingApplyCompany);
                }else{
                    //修改
                    billingApplyCompany = ConvertToBeanUtil.toBean(apiReq,billingApplyCompany);
                    billingApplyCompanyMapper.updateByPrimaryKey(billingApplyCompany);
                }
            }
            //方向结果类型
            else if("directionResultType".equals(surveyCode)){
                SurveyDirectionResultType surveyDirectionResultType = surveyDirectionResultTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyDirectionResultType == null){
                    //保存
                    surveyDirectionResultType = ConvertToBeanUtil.toBean(apiReq, SurveyDirectionResultType.class);
                    surveyDirectionResultType.setCreateByName(userName);// 发起人
                    surveyDirectionResultType.setCreateBy(userId);// 发起人id
                    surveyDirectionResultType.setCreateTime(new Date());//创建时间
                    surveyDirectionResultType.setDeleteFlag(0);
                    surveyDirectionResultTypeMapper.insert(surveyDirectionResultType);
                }else{
                    //修改
                    surveyDirectionResultType = ConvertToBeanUtil.toBean(apiReq,surveyDirectionResultType);
                    surveyDirectionResultType.setUpdateBy(userId);// 更新人id
                    surveyDirectionResultType.setUpdateTime(new Date());//更新时间
                    surveyDirectionResultTypeMapper.updateByPrimaryKey(surveyDirectionResultType);
                }
            }
            //价格模板
            else if("priceModel".equals(surveyCode)){
                SurveyPriceModel surveyPriceModel = surveyPriceModelMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyPriceModel == null){
                    //保存
                    surveyPriceModel = ConvertToBeanUtil.toBean(apiReq, SurveyPriceModel.class);
                    surveyPriceModel.setCreateByName(userName);// 发起人
                    surveyPriceModel.setCreateBy(userId);// 发起人id
                    surveyPriceModel.setCreateTime(new Date());//创建时间
                    surveyPriceModel.setDeleteFlag(0);
                    surveyPriceModel.setSurveyEntrustIsRate(0);
                    surveyPriceModelMapper.insert(surveyPriceModel);
                }else{
                    //修改
                    surveyPriceModel = ConvertToBeanUtil.toBean(apiReq,surveyPriceModel);
                    surveyPriceModel.setUpdateBy(userId);// 更新人id
                    surveyPriceModel.setUpdateTime(new Date());//更新时间
                    surveyPriceModelMapper.updateByPrimaryKey(surveyPriceModel);
                }
            }else if("priceModelDelete".equals(surveyCode)){
                surveyPriceModelMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            }else if ("priceModelCopy".equals(surveyCode)){
                Long oldPriceModelId = apiReq.getLong("id");
                SurveyPriceModel surveyPriceModel = new SurveyPriceModel();
                surveyPriceModel.setCreateBy(userInfo.getUserId());
                surveyPriceModel.setCreateByName(userInfo.getUserName());
                surveyPriceModel.setId(oldPriceModelId);
                surveyPriceModelMapper.copyOneByPriceModelId(surveyPriceModel);
                surveyPriceModelAreaCategoriesMapper.copyOneByPriceModelId(oldPriceModelId,surveyPriceModel.getId());
                List<SurveyPriceModelAreaCategories> surveyPriceModelAreaCategories = surveyPriceModelAreaCategoriesMapper.selectByPriceModelId(surveyPriceModel.getId(),oldPriceModelId);
                for (SurveyPriceModelAreaCategories surveyPriceModelAreaCategory : surveyPriceModelAreaCategories) {
                    surveyAreaCategoriesAreaCityMapper.copyOneByPriceModelId(surveyPriceModelAreaCategory,oldPriceModelId);
                    surveyPriceMapper.copyOneByPriceModelId(surveyPriceModelAreaCategory.getId(),surveyPriceModelAreaCategory.getOldAreaCategoriesId());
                }
            }
            //区域类别对应具体区域
            else if("priceModelAreaCategories".equals(surveyCode)){
                SurveyPriceModelAreaCategories surveyPriceModelAreaCategories = surveyPriceModelAreaCategoriesMapper.selectByPrimaryKey(apiReq.getLong("id"));
                SurveyPriceModel surveyPriceModel = surveyPriceModelMapper.selectByPrimaryKey(apiReq.getLong("priceModelId"));

                if(surveyPriceModelAreaCategories == null){
                    //保存
                    surveyPriceModelAreaCategories = ConvertToBeanUtil.toBean(apiReq, SurveyPriceModelAreaCategories.class);
                    surveyPriceModelAreaCategories.setPriceModelId(surveyPriceModel.getId());
                    surveyPriceModelAreaCategories.setPriceModelName(surveyPriceModel.getName());
                    surveyPriceModelAreaCategoriesMapper.insert(surveyPriceModelAreaCategories);

                    //区域类别 对应 具体区域（城市）
                    String cityIds = apiReq.getString("cityIds");
                    if(cityIds!=null) {
                        ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                        HashSet citySet = new HashSet(cityId);
                        Iterator iterator = citySet.iterator();
                        while (iterator.hasNext()){
                            String ro = iterator.next().toString().trim();
                            surveyAreaCategoriesAreaCityMapper.deleteByAreaId(Long.valueOf(ro),surveyPriceModelAreaCategories.getPriceModelId());
                            SurveyAreaCategoriesAreaCity surveyAreaCategoriesAreaCity = new SurveyAreaCategoriesAreaCity();
                            surveyAreaCategoriesAreaCity.setPriceModelId(surveyPriceModelAreaCategories.getPriceModelId());
                            surveyAreaCategoriesAreaCity.setPriceModelName(surveyPriceModelAreaCategories.getPriceModelName());
                            surveyAreaCategoriesAreaCity.setAreaCategoriesId(surveyPriceModelAreaCategories.getId());
                            surveyAreaCategoriesAreaCity.setAreaCategoriesName(surveyPriceModelAreaCategories.getName());
                            CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(commonArea != null){
                                surveyAreaCategoriesAreaCity.setAreaId(commonArea.getAreaId());
                                surveyAreaCategoriesAreaCity.setAreaName(commonArea.getAreaName());
                                surveyAreaCategoriesAreaCity.setAreaLongName(commonArea.getAreaLongname());
                            }
                            surveyAreaCategoriesAreaCityMapper.insertSelective(surveyAreaCategoriesAreaCity);
                        }
                    }
                }else{
                    //修改
                    surveyPriceModelAreaCategories = ConvertToBeanUtil.toBean(apiReq,surveyPriceModelAreaCategories);
                    surveyPriceModelAreaCategoriesMapper.updateByPrimaryKey(surveyPriceModelAreaCategories);

                    if(StringUtils.isNotBlank(apiReq.getString("first")) && apiReq.getString("first").equals("true")){
                        //先删除已匹配的数据
                        surveyAreaCategoriesAreaCityMapper.deleteByAreaCategoriesId(surveyPriceModelAreaCategories.getId(),surveyPriceModelAreaCategories.getPriceModelId());
                    }


                    //区域类别 对应 具体区域（城市）
                    String cityIds = apiReq.getString("cityIds");
                    if(cityIds!=null) {
                        ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                        HashSet citySet = new HashSet(cityId);
                        Iterator iterator = citySet.iterator();
                        while (iterator.hasNext()) {
                            String ro = iterator.next().toString().trim();
                            surveyAreaCategoriesAreaCityMapper.deleteByAreaId(Long.valueOf(ro),surveyPriceModelAreaCategories.getPriceModelId());
                            SurveyAreaCategoriesAreaCity surveyAreaCategoriesAreaCity = new SurveyAreaCategoriesAreaCity();
                            surveyAreaCategoriesAreaCity.setPriceModelId(surveyPriceModelAreaCategories.getPriceModelId());
                            surveyAreaCategoriesAreaCity.setPriceModelName(surveyPriceModelAreaCategories.getPriceModelName());
                            surveyAreaCategoriesAreaCity.setAreaCategoriesId(surveyPriceModelAreaCategories.getId());
                            surveyAreaCategoriesAreaCity.setAreaCategoriesName(surveyPriceModelAreaCategories.getName());
                            CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(commonArea != null){
                                surveyAreaCategoriesAreaCity.setAreaId(commonArea.getAreaId());
                                surveyAreaCategoriesAreaCity.setAreaName(commonArea.getAreaName());
                            }
                            surveyAreaCategoriesAreaCityMapper.insertSelective(surveyAreaCategoriesAreaCity);
                        }
                    }
                }
            }else if ("consignorEfficiencyModelArea".equals(surveyCode)){
                SurveyConsignorEfficiencyModelArea surveyConsignorEfficiencyModelArea = surveyConsignorEfficiencyModelAreaMapper.selectByPrimaryKey(apiReq.getLong("id"));
                SurveyConsignorEfficiencyModel surveyConsignorEfficiencyModel = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(apiReq.getLong("modelId"));

                if(surveyConsignorEfficiencyModelArea == null){
                    //保存
                    surveyConsignorEfficiencyModelArea = ConvertToBeanUtil.toBean(apiReq, SurveyConsignorEfficiencyModelArea.class);
                    surveyConsignorEfficiencyModelArea.setModelId(surveyConsignorEfficiencyModel.getId());
                    surveyConsignorEfficiencyModelArea.setModelName(surveyConsignorEfficiencyModel.getName());
                    surveyConsignorEfficiencyModelAreaMapper.insert(surveyConsignorEfficiencyModelArea);

                    //区域类别 对应 具体区域（城市）
                    String cityIds = apiReq.getString("cityIds");
                    if(cityIds!=null) {
                        ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                        HashSet citySet = new HashSet(cityId);
                        Iterator iterator = citySet.iterator();
                        while (iterator.hasNext()){
                            String ro = iterator.next().toString().trim();
//                            surveyAreaCategoriesAreaCityMapper.deleteByAreaId(Long.valueOf(ro));
                            SurveyConsignorEfficiencyAreaCity surveyConsignorEfficiencyAreaCity = new SurveyConsignorEfficiencyAreaCity();
                            surveyConsignorEfficiencyAreaCity.setModelId(surveyConsignorEfficiencyModelArea.getModelId());
                            surveyConsignorEfficiencyAreaCity.setModelName(surveyConsignorEfficiencyModelArea.getModelName());
                            surveyConsignorEfficiencyAreaCity.setAreaCategoriesId(surveyConsignorEfficiencyModelArea.getId());
                            surveyConsignorEfficiencyAreaCity.setAreaCategoriesName(surveyConsignorEfficiencyModelArea.getName());
                            CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(commonArea != null){
                                surveyConsignorEfficiencyAreaCity.setAreaId(commonArea.getAreaId());
                                surveyConsignorEfficiencyAreaCity.setAreaName(commonArea.getAreaName());
                                surveyConsignorEfficiencyAreaCity.setAreaLongname(commonArea.getAreaLongname());
                            }
                            surveyConsignorEfficiencyAreaCityMapper.insertSelective(surveyConsignorEfficiencyAreaCity);
                        }
                    }
                }else{
                    //修改
                    surveyConsignorEfficiencyModelArea = ConvertToBeanUtil.toBean(apiReq,surveyConsignorEfficiencyModelArea);
                    surveyConsignorEfficiencyModelAreaMapper.updateByPrimaryKey(surveyConsignorEfficiencyModelArea);

                    if(StringUtils.isNotBlank(apiReq.getString("first")) && apiReq.getString("first").equals("true")){
                        //先删除已匹配的数据
                        surveyConsignorEfficiencyAreaCityMapper.deleteByAreaCategoriesId(surveyConsignorEfficiencyModelArea.getId(),surveyConsignorEfficiencyModelArea.getModelId());
                    }


                    //区域类别 对应 具体区域（城市）
                    String cityIds = apiReq.getString("cityIds");
                    if(cityIds!=null) {
                        ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                        HashSet citySet = new HashSet(cityId);
                        Iterator iterator = citySet.iterator();
                        while (iterator.hasNext()) {
                            String ro = iterator.next().toString().trim();
                            surveyConsignorEfficiencyAreaCityMapper.deleteByAreaId(Long.valueOf(ro),surveyConsignorEfficiencyModelArea.getModelId());
                            SurveyConsignorEfficiencyAreaCity surveyConsignorEfficiencyAreaCity = new SurveyConsignorEfficiencyAreaCity();
                            surveyConsignorEfficiencyAreaCity.setModelId(surveyConsignorEfficiencyModelArea.getModelId());
                            surveyConsignorEfficiencyAreaCity.setModelName(surveyConsignorEfficiencyModelArea.getModelName());
                            surveyConsignorEfficiencyAreaCity.setAreaCategoriesId(surveyConsignorEfficiencyModelArea.getId());
                            surveyConsignorEfficiencyAreaCity.setAreaCategoriesName(surveyConsignorEfficiencyModelArea.getName());
                            CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(commonArea != null){
                                surveyConsignorEfficiencyAreaCity.setAreaId(commonArea.getAreaId());
                                surveyConsignorEfficiencyAreaCity.setAreaName(commonArea.getAreaName());
                            }
                            surveyConsignorEfficiencyAreaCityMapper.insertSelective(surveyConsignorEfficiencyAreaCity);
                        }
                    }
                }
            }
            else if ("channelModelArea".equals(surveyCode)){
                SurveyChannelModelArea surveyChannelModelArea = surveyChannelModelAreaMapper.selectByPrimaryKey(apiReq.getLong("id"));
                SurveyChannelModel surveyChannelModel = surveyChannelModelMapper.selectByPrimaryKey(apiReq.getLong("modelId"));

                if(surveyChannelModelArea == null){
                    //保存
                    surveyChannelModelArea = ConvertToBeanUtil.toBean(apiReq, SurveyChannelModelArea.class);
                    surveyChannelModelArea.setModelId(surveyChannelModel.getId());
                    surveyChannelModelArea.setModelName(surveyChannelModel.getName());
                    surveyChannelModelAreaMapper.insert(surveyChannelModelArea);

                    //区域类别 对应 具体区域（城市）
                    String cityIds = apiReq.getString("cityIds");
                    if(cityIds!=null) {
                        ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                        HashSet citySet = new HashSet(cityId);
                        Iterator iterator = citySet.iterator();
                        while (iterator.hasNext()){
                            String ro = iterator.next().toString().trim();
//                            surveyAreaCategoriesAreaCityMapper.deleteByAreaId(Long.valueOf(ro));
                            SurveyChannelModelAreaCity surveyChannelModelAreaCity = new SurveyChannelModelAreaCity();
                            surveyChannelModelAreaCity.setModelId(surveyChannelModelArea.getModelId());
                            surveyChannelModelAreaCity.setModelName(surveyChannelModelArea.getModelName());
                            surveyChannelModelAreaCity.setAreaCategoriesId(surveyChannelModelArea.getId());
                            surveyChannelModelAreaCity.setAreaCategoriesName(surveyChannelModelArea.getName());
                            CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(commonArea != null){
                                surveyChannelModelAreaCity.setAreaId(commonArea.getAreaId());
                                surveyChannelModelAreaCity.setAreaName(commonArea.getAreaName());
                                surveyChannelModelAreaCity.setAreaLongname(commonArea.getAreaLongname());
                            }
                            surveyChannelModelAreaCityMapper.insertSelective(surveyChannelModelAreaCity);
                        }
                    }
                }else{
                    //修改
                    surveyChannelModelArea = ConvertToBeanUtil.toBean(apiReq,surveyChannelModelArea);
                    surveyChannelModelAreaMapper.updateByPrimaryKey(surveyChannelModelArea);

                    if(StringUtils.isNotBlank(apiReq.getString("first")) && apiReq.getString("first").equals("true")){
                        //先删除已匹配的数据
                        surveyChannelModelAreaCityMapper.deleteByAreaCategoriesId(surveyChannelModelArea.getId(),surveyChannelModelArea.getModelId());
                    }


                    //区域类别 对应 具体区域（城市）
                    String cityIds = apiReq.getString("cityIds");
                    if(cityIds!=null) {
                        ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                        HashSet citySet = new HashSet(cityId);
                        Iterator iterator = citySet.iterator();
                        while (iterator.hasNext()) {
                            String ro = iterator.next().toString().trim();
                            surveyChannelModelAreaCityMapper.deleteByAreaId(Long.valueOf(ro),surveyChannelModelArea.getModelId());
                            SurveyChannelModelAreaCity surveyChannelModelAreaCity = new SurveyChannelModelAreaCity();
                            surveyChannelModelAreaCity.setModelId(surveyChannelModelArea.getModelId());
                            surveyChannelModelAreaCity.setModelName(surveyChannelModelArea.getModelName());
                            surveyChannelModelAreaCity.setAreaCategoriesId(surveyChannelModelArea.getId());
                            surveyChannelModelAreaCity.setAreaCategoriesName(surveyChannelModelArea.getName());
                            CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(commonArea != null){
                                surveyChannelModelAreaCity.setAreaId(commonArea.getAreaId());
                                surveyChannelModelAreaCity.setAreaName(commonArea.getAreaName());
                            }
                            surveyChannelModelAreaCityMapper.insertSelective(surveyChannelModelAreaCity);
                        }
                    }
                }
            }
            else if ("scoreModelArea".equals(surveyCode)){
                SurveyScoreModelArea surveyScoreModelArea = surveyScoreModelAreaMapper.selectByPrimaryKey(apiReq.getLong("id"));
                SurveyScoreModel surveyScoreModel = surveyScoreModelMapper.selectByPrimaryKey(apiReq.getLong("modelId"));

                if(surveyScoreModelArea == null){
                    //保存
                    surveyScoreModelArea = ConvertToBeanUtil.toBean(apiReq, SurveyScoreModelArea.class);
                    surveyScoreModelArea.setModelId(surveyScoreModel.getId());
                    surveyScoreModelArea.setModelName(surveyScoreModel.getName());
                    surveyScoreModelAreaMapper.insert(surveyScoreModelArea);

                    //区域类别 对应 具体区域（城市）
                    String cityIds = apiReq.getString("cityIds");
                    if(cityIds!=null) {
                        ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                        HashSet citySet = new HashSet(cityId);
                        Iterator iterator = citySet.iterator();
                        while (iterator.hasNext()){
                            String ro = iterator.next().toString().trim();
//                            surveyAreaCategoriesAreaCityMapper.deleteByAreaId(Long.valueOf(ro));
                            SurveyScoreModelAreaCity surveyScoreModelAreaCity = new SurveyScoreModelAreaCity();
                            surveyScoreModelAreaCity.setModelId(surveyScoreModelArea.getModelId());
                            surveyScoreModelAreaCity.setModelName(surveyScoreModelArea.getModelName());
                            surveyScoreModelAreaCity.setAreaCategoriesId(surveyScoreModelArea.getId());
                            surveyScoreModelAreaCity.setAreaCategoriesName(surveyScoreModelArea.getName());
                            CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(commonArea != null){
                                surveyScoreModelAreaCity.setAreaId(commonArea.getAreaId());
                                surveyScoreModelAreaCity.setAreaName(commonArea.getAreaName());
                                surveyScoreModelAreaCity.setAreaLongname(commonArea.getAreaLongname());
                            }
                            surveyScoreModelAreaCityMapper.insertSelective(surveyScoreModelAreaCity);
                        }
                    }
                }else{
                    //修改
                    surveyScoreModelArea = ConvertToBeanUtil.toBean(apiReq,surveyScoreModelArea);
                    surveyScoreModelAreaMapper.updateByPrimaryKey(surveyScoreModelArea);

                    if(StringUtils.isNotBlank(apiReq.getString("first")) && apiReq.getString("first").equals("true")){
                        //先删除已匹配的数据
                        surveyScoreModelAreaCityMapper.deleteByAreaCategoriesId(surveyScoreModelArea.getId(),surveyScoreModelArea.getModelId());
                    }


                    //区域类别 对应 具体区域（城市）
                    String cityIds = apiReq.getString("cityIds");
                    if(cityIds!=null) {
                        ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                        HashSet citySet = new HashSet(cityId);
                        Iterator iterator = citySet.iterator();
                        while (iterator.hasNext()) {
                            String ro = iterator.next().toString().trim();
                            surveyScoreModelAreaCityMapper.deleteByAreaId(Long.valueOf(ro),surveyScoreModelArea.getModelId());
                            SurveyScoreModelAreaCity surveyScoreModelAreaCity = new SurveyScoreModelAreaCity();
                            surveyScoreModelAreaCity.setModelId(surveyScoreModelArea.getModelId());
                            surveyScoreModelAreaCity.setModelName(surveyScoreModelArea.getModelName());
                            surveyScoreModelAreaCity.setAreaCategoriesId(surveyScoreModelArea.getId());
                            surveyScoreModelAreaCity.setAreaCategoriesName(surveyScoreModelArea.getName());
                            CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(commonArea != null){
                                surveyScoreModelAreaCity.setAreaId(commonArea.getAreaId());
                                surveyScoreModelAreaCity.setAreaName(commonArea.getAreaName());
                            }
                            surveyScoreModelAreaCityMapper.insertSelective(surveyScoreModelAreaCity);
                        }
                    }
                }
            }
            else if ("franchiseeAreaCity".equals(surveyCode)){
                Long orgId = apiReq.getLong("orgId");
                String cityIds = apiReq.getString("cityIds");
                surveyFranchiseeAreaCityMapper.deleteByOrgId(orgId);
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(orgId);
                if(cityIds!=null) {
                    ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                    HashSet citySet = new HashSet(cityId);
                    Iterator iterator = citySet.iterator();
                    while (iterator.hasNext()) {
                        String ro = iterator.next().toString().trim();
                        surveyFranchiseeAreaCityMapper.deleteByAreaId(Long.valueOf(ro),orgId);
                        SurveyFranchiseeAreaCity surveyFranchiseeAreaCity = new SurveyFranchiseeAreaCity();
                        surveyFranchiseeAreaCity.setOrgId(orgId);
                        surveyFranchiseeAreaCity.setOrgName(surveyFranchisee.getName());
                        CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if(commonArea != null){
                            surveyFranchiseeAreaCity.setAreaId(commonArea.getAreaId());
                            surveyFranchiseeAreaCity.setAreaName(commonArea.getAreaName());
                        }
                        surveyFranchiseeAreaCityMapper.insertSelective(surveyFranchiseeAreaCity);
                    }
                }
            }
            else if ("consignorAreaCity".equals(surveyCode)){
                Long orgId = apiReq.getLong("orgId");
                String cityIds = apiReq.getString("cityIds");
                surveyConsignorAreaCityMapper.deleteByOrgId(orgId);
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(orgId);
                if(cityIds!=null) {
                    ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                    HashSet citySet = new HashSet(cityId);
                    Iterator iterator = citySet.iterator();
                    while (iterator.hasNext()) {
                        String ro = iterator.next().toString().trim();
                        surveyConsignorAreaCityMapper.deleteByAreaId(Long.valueOf(ro),orgId);
                        SurveyConsignorAreaCity surveyConsignorAreaCity = new SurveyConsignorAreaCity();
                        surveyConsignorAreaCity.setOrgId(orgId);
                        surveyConsignorAreaCity.setOrgName(surveyConsignor.getName());
                        CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if(commonArea != null){
                            surveyConsignorAreaCity.setAreaId(commonArea.getAreaId());
                            surveyConsignorAreaCity.setAreaName(commonArea.getAreaName());
                        }
                        surveyConsignorAreaCityMapper.insertSelective(surveyConsignorAreaCity);
                    }
                }
            }
            //委托时效模板
            else if("consignorEfficiencyModel".equals(surveyCode)){
                SurveyConsignorEfficiencyModel surveyConsignorEfficiencyModel = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyConsignorEfficiencyModel == null){
                    surveyConsignorEfficiencyModel = ConvertToBeanUtil.toBean(apiReq, SurveyConsignorEfficiencyModel.class);
//                    surveyConsignorEfficiencyModel.setType(apiReq.getInt("type"));
                    surveyConsignorEfficiencyModel.setCreateByName(userName);// 发起人
                    surveyConsignorEfficiencyModel.setCreateBy(userId);// 发起人id
                    surveyConsignorEfficiencyModel.setCreateTime(new Date());//创建时间
                    surveyConsignorEfficiencyModel.setDeleteFlag(0);
                    surveyConsignorEfficiencyModelMapper.insert(surveyConsignorEfficiencyModel);

//                    List<SurveyConsignorEfficiencyModelInfo> infos = JSONArray.parseArray(apiReq.getString("infos"),SurveyConsignorEfficiencyModelInfo.class);
//                    for (SurveyConsignorEfficiencyModelInfo info : infos) {
//                        SurveyConsignorEfficiencyModelInfo newInfo = new SurveyConsignorEfficiencyModelInfo();
//                        newInfo.setEfficiencyModelId(surveyConsignorEfficiencyModel.getId());
//                        newInfo.setEfficiencyModelName(surveyConsignorEfficiencyModel.getName());
//                        newInfo.setCityType(info.getCityType());
//                        newInfo.setDays(info.getDays());
//                        if (info.getSubServiceId() != null){
//                            newInfo.setSubServiceId(info.getSubServiceId());
//                            newInfo.setSubServiceName(info.getSubServiceName());
//                        }else {
//                            SurveyServiceType serviceType = surveyServiceTypeMapper.selectByPrimaryKey(info.getServiceId());
//                            newInfo.setServiceId(serviceType.getId());
//                            newInfo.setServiceName(serviceType.getName());
//                        }
//
//                        surveyConsignorEfficiencyModelInfoMapper.insert(newInfo);
//                    }
                }else{
                    surveyConsignorEfficiencyModel = ConvertToBeanUtil.toBean(apiReq,surveyConsignorEfficiencyModel);
                    surveyConsignorEfficiencyModel.setUpdateBy(userId);// 更新人id
                    surveyConsignorEfficiencyModel.setUpdateTime(new Date());//更新时间
                    surveyConsignorEfficiencyModelMapper.updateByPrimaryKey(surveyConsignorEfficiencyModel);


                }
            }
            //委托时效模板
            else if("channelModel".equals(surveyCode)){
                SurveyChannelModel surveyChannelModel = surveyChannelModelMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyChannelModel == null){
                    surveyChannelModel = ConvertToBeanUtil.toBean(apiReq, SurveyChannelModel.class);
                    surveyChannelModel.setCreateByName(userName);// 发起人
                    surveyChannelModel.setCreateBy(userId);// 发起人id
                    surveyChannelModel.setCreateTime(new Date());//创建时间
                    surveyChannelModel.setDeleteFlag(0);
                    surveyChannelModelMapper.insert(surveyChannelModel);
                }else{
                    surveyChannelModel = ConvertToBeanUtil.toBean(apiReq,surveyChannelModel);
                    surveyChannelModel.setUpdateBy(userId);// 更新人id
                    surveyChannelModel.setUpdateTime(new Date());//更新时间
                    surveyChannelModelMapper.updateByPrimaryKey(surveyChannelModel);
                }
            }
            else if("scoreModel".equals(surveyCode)){
                SurveyScoreModel surveyScoreModel = surveyScoreModelMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyScoreModel == null){
                    surveyScoreModel = ConvertToBeanUtil.toBean(apiReq, SurveyScoreModel.class);
                    surveyScoreModel.setCreateByName(userName);// 发起人
                    surveyScoreModel.setCreateBy(userId);// 发起人id
                    surveyScoreModel.setCreateTime(new Date());//创建时间
                    surveyScoreModel.setDeleteFlag(0);
                    surveyScoreModelMapper.insert(surveyScoreModel);
                }else{
                    surveyScoreModel = ConvertToBeanUtil.toBean(apiReq,surveyScoreModel);
                    surveyScoreModel.setUpdateBy(userId);// 更新人id
                    surveyScoreModel.setUpdateTime(new Date());//更新时间
                    surveyScoreModelMapper.updateByPrimaryKey(surveyScoreModel);
                }
            }
            //终审人员--手写签名
            else if("userSign".equals(surveyCode)){

                String img = apiReq.getString("proFile");
                UserInfo info = userInfoMapper.selectByPrimaryKey(apiReq.getLong("userId"));
                SurveyUserSign surveyUserSign = surveyUserSignMapper.selectByUserId(info.getUserId());
                if (surveyUserSign != null) {
                    surveyUserSign.setSignImg(img);
                    surveyUserSign.setUpdateBy(userInfo.getUserName());
                    surveyUserSign.setUpdateTime(new Date());
                    surveyUserSignMapper.updateByPrimaryKey(surveyUserSign);
                }else{
                    surveyUserSign = new SurveyUserSign();
                    surveyUserSign.setUserId(info.getUserId());
                    surveyUserSign.setUserName(info.getUserName());
                    surveyUserSign.setSignImg(img);
                    surveyUserSign.setCreateBy(userInfo.getUserName());
                    surveyUserSign.setCreateTime(new Date());
                    surveyUserSign.setUpdateBy(userInfo.getUserName());
                    surveyUserSign.setUpdateTime(new Date());
                    surveyUserSign.setDeleteFlag(0);
                    surveyUserSignMapper.insert(surveyUserSign);
                }
            }
            //邮箱模板
            else if("emailInfo".equals(surveyCode)){
                SurveyEmailInfo surveyEmailInfo = surveyEmailInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if(surveyEmailInfo == null){
                    //保存
                    surveyEmailInfo = ConvertToBeanUtil.toBean(apiReq, SurveyEmailInfo.class);
                    surveyEmailInfo.setCreateBy(userName);// 创建人
                    surveyEmailInfo.setCreateTime(new Date());//创建时间
                    surveyEmailInfo.setDeleteFlag(0);  //删除标识
                    surveyEmailInfoMapper.insert(surveyEmailInfo);
                }else{
                    //修改
                    surveyEmailInfo = ConvertToBeanUtil.toBean(apiReq,surveyEmailInfo);
                    surveyEmailInfo.setUodateTime(new Date());//更新时间
                    surveyEmailInfoMapper.updateByPrimaryKey(surveyEmailInfo);
                }
            }

            //狄大人--邮件模板对应“委托机构”
            else if("emailInfoOrg".equals(surveyCode)){
                String btnCode = apiReq.getString("btnCode");
                Long emailInfoId = apiReq.getLong("modelId");
                //报告信息
                SurveyEmailInfo surveyEmailInfo = surveyEmailInfoMapper.selectByPrimaryKey(emailInfoId);
                String emailUserName = surveyEmailInfo.getEmailUserName();
                //委托方机构
                if("1000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("emailInfoId",emailInfoId);
                    surveyEmailInfoOrgMapper.deleteByInfo(map);

                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {
                            //机构信息
                            SurveyConsignor consignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(ro));

                            if (consignor != null) {
                                //查询该机构，是否已经设置了模板，如果没有insert，如果设置了update
                                SurveyEmailInfoOrg surveyEmailInfoOrg = surveyEmailInfoOrgMapper.selectByEntrustOrgId(consignor.getId());

                                if (surveyEmailInfoOrg != null) {
                                    surveyEmailInfoOrg.setEmailInfoId(emailInfoId);
                                    surveyEmailInfoOrg.setEmailInfoName(emailUserName);
                                    surveyEmailInfoOrgMapper.updateByPrimaryKeySelective(surveyEmailInfoOrg);
                                } else {
                                    surveyEmailInfoOrg = new SurveyEmailInfoOrg();

                                    surveyEmailInfoOrg.setEntrustOrgId(consignor.getId());
                                    surveyEmailInfoOrg.setEntrustOrgName(consignor.getCompany());
                                    surveyEmailInfoOrg.setEmailInfoId(emailInfoId);
                                    surveyEmailInfoOrg.setEmailInfoName(emailUserName);
                                    surveyEmailInfoOrgMapper.insertSelective(surveyEmailInfoOrg);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 数据处理
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "数据处理", value = "backend-survey-operate", apiParams = { })
    @Override
    public ApiResponse operate(ApiRequest apiReq) {
        String surveyCode = apiReq.getString("surveyCode"); // 菜单标识
        String btnCode = apiReq.getString("btnCode"); //具体操作标识

        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);

        //领域类型
        if("businessType".equals(surveyCode)){
            SurveyBusinessType surveyBusinessType = surveyBusinessTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyBusinessType.setDeleteFlag(1);
            }
            surveyBusinessTypeMapper.updateByPrimaryKeySelective(surveyBusinessType);
        }
        //业务类型
        if("serviceType".equals(surveyCode)){
            SurveyServiceType surveyServiceType = surveyServiceTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyServiceType.setDeleteFlag(1);
            }
            surveyServiceTypeMapper.updateByPrimaryKeySelective(surveyServiceType);
        }
        //任务类型
        else if("taskInfo".equals(surveyCode)){
            SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyTaskInfo.setDeleteFlag(1);
            }
            surveyTaskInfoMapper.updateByPrimaryKeySelective(surveyTaskInfo);
        }
        //材料目录
        else if("fileCatalog".equals(surveyCode)){
            SurveyFileCatalog surveyFileCatalog = surveyFileCatalogMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyFileCatalog.setDeleteFlag(1);
            }
            surveyFileCatalogMapper.updateByPrimaryKeySelective(surveyFileCatalog);
        }
        //调查员登记
        else if("level".equals(surveyCode)){
            SurveyLevel surveyLevel = surveyLevelMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyLevel.setDeleteFlag(1);
            }
            surveyLevelMapper.updateByPrimaryKeySelective(surveyLevel);
        }
        //称号特权详情
        else if("levelExplain".equals(surveyCode)){
            SurveyLevelExplain surveyLevelExplain = surveyLevelExplainMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyLevelExplain.setDeleteFlag(1);
            }
            surveyLevelExplainMapper.updateByPrimaryKeySelective(surveyLevelExplain);
        }
        //平台介绍
        else if("introduction".equals(surveyCode)){
            SurveyIntroduction surveyIntroduction = surveyIntroductionMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyIntroduction.setDeleteFlag(1);
            }
            surveyIntroductionMapper.updateByPrimaryKeySelective(surveyIntroduction);
        }
        //服务优势
        else if("serviceAdvantage".equals(surveyCode)){
            SurveyServiceAdvantage surveyServiceAdvantage = surveyServiceAdvantageMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyServiceAdvantage.setDeleteFlag(1);
            }
            surveyServiceAdvantageMapper.updateByPrimaryKeySelective(surveyServiceAdvantage);
        }
        //委托人认证
        else if("consigner".equals(surveyCode)){
            SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyConsigner.setDeleteFlag(1);
            }
            //认证通过
            else if("1100".equals(btnCode)){
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyConsigner.getEntrustOrgId());
                if(surveyConsignor!=null){
                    surveyConsigner.setIsCredit(surveyConsignor.getIsCredit());
                }else{
                    surveyConsigner.setIsCredit(1);
                }
                surveyConsigner.setAuthState(2);
                //给用户赋权限
                Map<String,Object> map = new HashMap<>();
                map.put("userId",surveyConsigner.getUserId());
                map.put("roleId",51);
                BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(map);
                if(busUserRole==null){
                    busUserRole = new BusUserRole();
                    busUserRole.setUserId(surveyConsigner.getUserId());
                    busUserRole.setRoleId(51l); //调查委托人权限
                    busUserRoleMapper.insertSelective(busUserRole);
                }

                SurveyMessage surveyMessage= new SurveyMessage();
                surveyMessage.setMsgType(1); //消息类型(1:系统消息，2：客服消息，3：其他消息)
                surveyMessage.setMsgTitle("认证通过");//消息标题
                surveyMessage.setMsgContent("你申请的委托方认证已通过审核。");//消息内容
                surveyMessage.setIsRead(0);//是否阅读
                surveyMessage.setFromUserId(userInfo.getUserId()); //发送方ID
                surveyMessage.setFromUserName(userInfo.getUserName()); //发送方姓名
                surveyMessage.setFromTime(new Date());//发送时间
                surveyMessage.setToUserId(surveyConsigner.getUserId());//接收方ID
                surveyMessage.setToUserName(surveyConsigner.getUserName());//接收方姓名
                surveyMessageMapper.insertSelective(surveyMessage);
            }
            //驳回认证
            else if("1200".equals(btnCode)){
                surveyConsigner.setAuthState(3);

                SurveyMessage surveyMessage= new SurveyMessage();
                surveyMessage.setMsgType(1); //消息类型(1:系统消息，2：客服消息，3：其他消息)
                surveyMessage.setMsgTitle("认证不通过");//消息标题
                surveyMessage.setMsgContent("你申请的委托方认证未通过审核，请核实原因。");//消息内容
                surveyMessage.setIsRead(0);//是否阅读
                surveyMessage.setFromUserId(userInfo.getUserId()); //发送方ID
                surveyMessage.setFromUserName(userInfo.getUserName()); //发送方姓名
                surveyMessage.setFromTime(new Date());//发送时间
                surveyMessage.setToUserId(surveyConsigner.getUserId());//接收方ID
                surveyMessage.setToUserName(surveyConsigner.getUserName());//接收方姓名
                surveyMessageMapper.insertSelective(surveyMessage);
            }
            //移除出 机构（使用场景：1、委托方机构模块，移除名下委托人）
            else if("1300".equals(btnCode)){
                surveyConsigner.setDeleteFlag(1);
                surveyConsignerMapper.updateByPrimaryKey(surveyConsigner);
                //同时移除委托人权限
                Map<String,Object> map = new HashMap<>();
                map.put("userId",surveyConsigner.getUserId());
                map.put("roleId",51);
                busUserRoleMapper.deleteByParam(map);
            }
            //加入机构（使用场景：1、委托方机构模块，添加委托人）
            else if("1400".equals(btnCode)){
                UserInfo user = userInfoMapper.selectByPrimaryKey(apiReq.getLong("userId"));

                //会存在二次认证的情况
                surveyConsigner = surveyConsignerMapper.selectByUserId(apiReq.getLong("userId"));
                if(surveyConsigner !=null){
                    surveyConsignerMapper.deleteByPrimaryKey(surveyConsigner.getId());
                }
                //单条数据加入
                surveyConsigner = new SurveyConsigner();
                //机构信息
                Long consignorId = apiReq.getLong("consignorId");
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(consignorId);
                surveyConsigner.setCompany(surveyConsignor.getCompany());
                surveyConsigner.setEntrustOrgId(surveyConsignor.getId());
                surveyConsigner.setEntrustOrgName(surveyConsignor.getName());
                surveyConsigner.setCode(surveyConsignor.getCode());
                surveyConsigner.setIsCredit(surveyConsignor.getIsCredit());//是否授信（0、否；1、是）
                //页面选择的人员信息
                surveyConsigner.setUserId(user.getUserId());
                surveyConsigner.setUserName(user.getUserName());
                surveyConsigner.setTel(user.getUserTel());
                surveyConsigner.setAccState(0);//账户状态：0、正常
                surveyConsigner.setAuthState(2);//认证状态：2、认证通过
                surveyConsigner.setDeleteFlag(0);
                surveyConsigner.setEmail(user.getEmail());
                //当前登录人信息
                surveyConsigner.setCreateBy(userInfo.getUserId());
                surveyConsigner.setCreateByName(userInfo.getUserName());
                surveyConsigner.setCreateTime(new Date());
                surveyConsigner.setEmail(apiReq.getString("email"));
                surveyConsigner.setMakeEmails(apiReq.getString("makeEmails"));
                //部门信息
                Long departmentId = apiReq.getLong("departmentId");
                SurveyConsignorDepartment surveyConsignorDepartment = surveyConsignorDepartmentMapper.selectByPrimaryKey(departmentId);
                if(surveyConsignorDepartment !=null){
                    surveyConsigner.setDepartmentId(surveyConsignorDepartment.getId());
                    surveyConsigner.setDepartmentName(surveyConsignorDepartment.getName());
                }else{
                    //再根据机构的名称查询
                    surveyConsignorDepartment = surveyConsignorDepartmentMapper.selectByName(surveyConsignor.getCompany());
                    if(surveyConsignorDepartment!=null) {
                        surveyConsigner.setDepartmentId(surveyConsignorDepartment.getId());
                        surveyConsigner.setDepartmentName(surveyConsignorDepartment.getName());
                    }else{
                        surveyConsignorDepartment = new SurveyConsignorDepartment();
                        surveyConsignorDepartment.setConsignorId(surveyConsignor.getId());
                        surveyConsignorDepartment.setConsignorName(surveyConsignor.getName());
                        surveyConsignorDepartment.setName(surveyConsignor.getName());
                        surveyConsignorDepartment.setCreateById(userId);
                        surveyConsignorDepartment.setCreateBy(userInfo.getUserName());
                        surveyConsignorDepartment.setCreateTime(new Date());
                        surveyConsignorDepartment.setDeleteFlag(0);
                        surveyConsignorDepartmentMapper.insertSelective(surveyConsignorDepartment);

                        surveyConsigner.setDepartmentId(surveyConsignorDepartment.getId());
                        surveyConsigner.setDepartmentName(surveyConsignorDepartment.getName());
                    }
                }

                surveyConsignerMapper.insertSelective(surveyConsigner);

                //认证材料
                String materialImgs = apiReq.getString("materialImgs");
                if (materialImgs != null) {
                    String[] urls = materialImgs.split(",");
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

                        //材料中间表
                        SurveyConsignerFile surveyConsignerFile = new SurveyConsignerFile();
                        surveyConsignerFile.setFileId(commonFile.getId());
                        surveyConsignerFile.setFileName(commonFile.getFileName());
                        surveyConsignerFile.setConsignId(surveyConsigner.getId());
                        surveyConsignerFile.setConsignType(1); // 委托方
                        surveyConsignerFile.setCreateBy(userInfo.getUserId());
                        surveyConsignerFile.setCreateByName(userInfo.getUserName());
                        surveyConsignerFile.setCreateTime(new Date());
                        surveyConsignerFileMapper.insert(surveyConsignerFile);
                    }
                }

                //给用户赋权限
                Map<String,Object> map = new HashMap<>();
                map.put("userId",surveyConsigner.getUserId());
                map.put("roleId",51);
                BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(map);
                if(busUserRole==null){
                    busUserRole = new BusUserRole();
                    busUserRole.setUserId(surveyConsigner.getUserId());
                    busUserRole.setRoleId(51l); //调查委托人权限
                    busUserRoleMapper.insertSelective(busUserRole);
                }

                //默认登录密码
                UserLogin userLogin = userLoginMapper.selectByPhone(user.getUserTel());
                if(userLogin !=null && userLogin.getPassword()==null){
                    userLogin.setPassword(MD5Util.MD5Encode("123456@Qaz",null));
                    userLoginMapper.updateByPrimaryKeySelective(userLogin);
                }
            }
            //修改为授信--是否授信（0、否；1、是）
            else if("1500".equals(btnCode)){
                surveyConsigner.setIsCredit(1);
            }
            //修改为非授信--是否授信（0、否；1、是）
            else if("1600".equals(btnCode)){
                surveyConsigner.setIsCredit(0);
            }
            //修改为正常-- 账户状态（0、正常；1、冻结；2、删除）
            else if("1700".equals(btnCode)){
                surveyConsigner.setAccState(0);
            }
            //修改为禁用-- 账户状态（0、正常；1、冻结；2、删除）
            else if("1800".equals(btnCode)){
                surveyConsigner.setAccState(1);
            }
            //修改部门
            else if("1900".equals(btnCode)){
                //部门信息
                Long departmentId = apiReq.getLong("departmentId");
                SurveyConsignorDepartment surveyConsignorDepartment = surveyConsignorDepartmentMapper.selectByPrimaryKey(departmentId);
                surveyConsigner.setDepartmentId(surveyConsignorDepartment.getId());
                surveyConsigner.setDepartmentName(surveyConsignorDepartment.getName());
            }
            //移除出部门
            else if("2000".equals(btnCode)){
                Long departmentId = apiReq.getLong("departmentId");
                surveyConsigner.setDepartmentId(null);
                surveyConsigner.setDepartmentName(null);
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("consignerUserId",surveyConsigner.getId());
                paramMap.put("consignorDepartmentId",departmentId);
                surveyConsignerDepartmentMapper.deleteByUserAndDepartment(paramMap);

            }
            surveyConsignerMapper.updateByPrimaryKey(surveyConsigner);
        }
        //委托方机构
        else if("consignor".equals(surveyCode)){
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyConsignor.setDeleteFlag(1);
                //同时清空部门信息
                Map<String,Object> map = new HashMap<>();
                map.put("consignorId",apiReq.getLong("id"));
                map.put("pageIndex",null);
                List<SurveyConsignorDepartment> surveyConsignorDepartments = surveyConsignorDepartmentMapper.list(map);
                for (int i = 0; i < surveyConsignorDepartments.size(); i++) {
                    surveyConsignorDepartments.get(i).setDeleteFlag(1);
                    surveyConsignorDepartmentMapper.updateByPrimaryKey(surveyConsignorDepartments.get(i));
                }
                //同时清空人员信息
                map =  new HashMap<>();
                map.put("entrustOrgId",apiReq.getLong("id"));
                map.put("pageIndex",null);//不分页
                List<SurveyConsigner> surveyConsigners = surveyConsignerMapper.list(map);
                for (int i = 0; i < surveyConsigners.size(); i++) {
                    surveyConsignerMapper.deleteByPrimaryKey(surveyConsigners.get(i).getId());
                }
            }
            //修改为散户
            else if("1700".equals(btnCode)){
                surveyConsignor.setType(2);
            }
            //修改为合作伙伴
            else if("1800".equals(btnCode)){
                surveyConsignor.setType(1);
            }
            //修改为授信--是否授信（0、否；1、是）
            else if("2100".equals(btnCode)){
                surveyConsignor.setIsCredit(1);
                //同时修改名下人员
                Map<String,Object> map = new HashMap<>();
                map.put("entrustOrgId",surveyConsignor.getId());
                map.put("pageIndex",null);//不分页
                List<SurveyConsigner> consigners = surveyConsignerMapper.list(map);
                for (int i = 0; i < consigners.size(); i++) {
                    consigners.get(i).setIsCredit(1);
                    surveyConsignerMapper.updateByPrimaryKey(consigners.get(i));
                }
            }
            //修改为非授信--是否授信（0、否；1、是）
            else if("2200".equals(btnCode)){
                surveyConsignor.setIsCredit(0);
                //同时修改名下人员
                Map<String,Object> map = new HashMap<>();
                map.put("entrustOrgId",surveyConsignor.getId());
                map.put("pageIndex",null);//不分页
                List<SurveyConsigner> consigners = surveyConsignerMapper.list(map);
                for (int i = 0; i < consigners.size(); i++) {
                    consigners.get(i).setIsCredit(0);
                    surveyConsignerMapper.updateByPrimaryKey(consigners.get(i));
                }
            }
            //修改价格备注
            else if("2300".equals(btnCode)){
                String priceRemark = apiReq.getString("priceRemark");
                surveyConsignor.setPriceRemark(priceRemark);
            }
            //保存邮箱
            else if("3100".equals(btnCode)){
                String sendName = apiReq.getString("sendName");
                String sendAddress = apiReq.getString("sendAddress");

                String[] receiveName = apiReq.getStringArray("receiveName");
                String[] receiveAddress = apiReq.getStringArray("receiveAddress");

//                for (int i = 0; i < receiveName.length; i++ ) {
//                    User user = new User();
//                    user.setId(receiveName[i]);
//                    user.setName(receiveAddress[i]);
//                    user.setPwd(pwd[i]);
//                    userList.add(user);
//                }
            }
            //报告命名规则
            else if("3200".equals(btnCode)){


            }
            //修改--多份病史价格
            else if("4000".equals(btnCode)){
                String medicalMoney = apiReq.getString("money");
                surveyConsignor.setMedicalMoney(Double.valueOf(medicalMoney));
            }
            //修改--主省价格
            else if("4100".equals(btnCode)){
                String mainProvinceMoney = apiReq.getString("money");
                surveyConsignor.setMainProvinceMoney(Double.valueOf(mainProvinceMoney));
            }
            //修改--副省价格
            else if("4200".equals(btnCode)){
                String viceProvinceMoney = apiReq.getString("money");
                surveyConsignor.setViceProvinceMoney(Double.valueOf(viceProvinceMoney));
            }
            //设置终审人员
            else if("5000".equals(btnCode)){
                //先删除记录，再保存全新数据
                Map<String,Object> map = new HashMap<>();
                map.put("consignorId",apiReq.getLong("id"));
                surveyUserConsignorMapper.deleteByConsignorId(map);

                //保存数据
                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        SurveyUserConsignor surveyUserConsignor =new SurveyUserConsignor();
                        //机构信息
                        UserInfo info = userInfoMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if(info!=null){
                            surveyUserConsignor.setUserId(info.getUserId());
                            surveyUserConsignor.setUserName(info.getUserName());
                        }
                        surveyUserConsignor.setConsignorId(surveyConsignor.getId());
                        surveyUserConsignor.setConsignorName(surveyConsignor.getCompany());
                        surveyUserConsignor.setDeleteFlag(0);
                        surveyUserConsignorMapper.insertSelective(surveyUserConsignor);
                    }
                }
            }
            //理赔原件回寄地址设置
            else if ("6000".equals(btnCode)){
                String receiver = apiReq.getString("receiver");
                String receiverTel = apiReq.getString("receiverTel");
                String receiverAddress = apiReq.getString("receiverAddress");
                surveyConsignor.setReceiver(Optional.ofNullable(receiver).orElse(""));
                surveyConsignor.setReceiverTel(Optional.ofNullable(receiverTel).orElse(""));
                surveyConsignor.setReceiverAddress(Optional.ofNullable(receiverAddress).orElse(""));
            }else if ("pact".equals(btnCode)){
                String json = apiReq.getString("files");//附件
                FinancialFileTableEnumDto fileTableEnum = FinancialFileTableEnumDto.SURVEY_ENTRUST_PACT;
                backendFinancialFileApiImpl.saveFile(surveyConsignor.getId(),fileTableEnum,userInfo,json);
            }else if ("del-pact".equals(btnCode)){
                backendFinancialFileApiImpl.deleteFileById(apiReq.getLong("fileId"));
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,null);
            }
            surveyConsignorMapper.updateByPrimaryKeySelective(surveyConsignor);
        }
        //调查方认证
        else if("investigator".equals(surveyCode)){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyInvestigator.setDeleteFlag(1);
                surveyInvestigator.setQuitTime(new Date());
            }
            //认证通过
            else if("1100".equals(btnCode)){
                surveyInvestigator.setAuthType(2);
                //给用户赋权限
                Map<String,Object> map = new HashMap<>();
                map.put("userId",surveyInvestigator.getUserId());
                map.put("roleId",50);
                BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(map);
                if(busUserRole==null){
                    busUserRole = new BusUserRole();
                    busUserRole.setUserId(surveyInvestigator.getUserId());
                    busUserRole.setRoleId(50l);  //调查员权限
                    busUserRoleMapper.insertSelective(busUserRole);
                }

                //乐凡币规则 -- 调查员认证
                SurveyLfcoinRule surveyLfcoinRule = surveyLfcoinRuleMapper.selectByCode("rz");
                if(surveyLfcoinRule != null){
                    surveyInvestigator.setLefanCurrency(surveyLfcoinRule.getNum());

                    //生成乐凡币明细
                    SurveyLfcoinDetail detail = new SurveyLfcoinDetail();
                    detail.setLefanCoin(surveyLfcoinRule.getNum());//乐凡币数量
                    detail.setConsumeType(1);//消费类型（1，获得，2：消费）
                    detail.setSurveyUserId(surveyInvestigator.getUserId());//调查员ID
                    detail.setSurveyUserName(surveyInvestigator.getRealName());//调查员姓名
                    detail.setCreateTime(new Date());//时间
                    detail.setOprType(1);//类型（1.认证，2.业务案件，3.论坛.4.案件评星，5.兑换）
                    detail.setRemark("认证获取的乐凡币");
                    surveyLfcoinDetailMapper.insertSelective(detail);
                }

                //称号规则
                //原因：认证通过时，可直接选择称号
                //称号规则 -- 最低等级
                Map<String,Object> parmap = new HashMap<>();
                parmap.put("code","V1");
                SurveyLevel surveyLevel = surveyLevelMapper.selectOne(parmap);
                if(surveyLevel != null){
                    surveyInvestigator.setTitleId(surveyLevel.getId());
                    surveyInvestigator.setTitleName(surveyLevel.getName());
                }
                //成就点数规则 -- 认证成就点数
                SurveyAchieveRule surveyAchieveRule = surveyAchieveRuleMapper.selectByCode("rz");
                if(surveyAchieveRule != null){
                    //生成成就点数明细
                    SurveyAchieveDetail detail =new SurveyAchieveDetail();
                    //因认证通过时，可直接选择称号，两种情况：1、选择最低等级，根据“成就点数规则”；2、选择更高等级，需根据等级对应的成就点

                    surveyInvestigator.setAchPoint(surveyAchieveRule.getNum());
                    detail.setAchNum(surveyAchieveRule.getNum());//1、规则对应点数


                    detail.setAchRemark("认证获取的成就点数");//获得成就点数备注
                    detail.setSurveyUserId(surveyInvestigator.getUserId());//调查员ID
                    detail.setSurveyUserName(surveyInvestigator.getRealName());//调查员姓名
                    detail.setCreateTime(new Date());//时间
                    detail.setOprType(1);//业务类型（1.认证，2.业务案件，3.论坛，4.业务案件评星）
                    surveyAchieveDetailMapper.insertSelective(detail);
                }

                //认证通过--发送消息
                SurveyMessage surveyMessage= new SurveyMessage();
                surveyMessage.setMsgType(1); //消息类型(1:系统消息，2：客服消息，3：其他消息)
                surveyMessage.setMsgTitle("认证通过");//消息标题
                surveyMessage.setMsgContent("你申请的调查方认证已通过审核。");//消息内容
                surveyMessage.setIsRead(0);//是否阅读
                surveyMessage.setFromUserId(userInfo.getUserId()); //发送方ID
                surveyMessage.setFromUserName(userInfo.getUserName()); //发送方姓名
                surveyMessage.setFromTime(new Date());//发送时间
                surveyMessage.setToUserId(surveyInvestigator.getUserId());//接收方ID
                surveyMessage.setToUserName(surveyInvestigator.getRealName());//接收方姓名
                surveyMessageMapper.insertSelective(surveyMessage);

            }
            //驳回认证
            else if("1200".equals(btnCode)){
                surveyInvestigator.setAuthType(3);

                //认证通过--发送消息
                SurveyMessage surveyMessage= new SurveyMessage();
                surveyMessage.setMsgType(1); //消息类型(1:系统消息，2：客服消息，3：其他消息)
                surveyMessage.setMsgTitle("认证不通过");//消息标题
                surveyMessage.setMsgContent("你申请的调查方认证未通过审核，请核实情况。");//消息内容
                surveyMessage.setIsRead(0);//是否阅读
                surveyMessage.setFromUserId(userInfo.getUserId()); //发送方ID
                surveyMessage.setFromUserName(userInfo.getUserName()); //发送方姓名
                surveyMessage.setFromTime(new Date());//发送时间
                surveyMessage.setToUserId(surveyInvestigator.getUserId());//接收方ID
                surveyMessage.setToUserName(surveyInvestigator.getRealName());//接收方姓名
                surveyMessageMapper.insertSelective(surveyMessage);
            }
            //修改为加盟
            else if("1300".equals(btnCode)){
                surveyInvestigator.setType(2);
            }
            //修改为自营
            else if("1400".equals(btnCode)){
                surveyInvestigator.setType(1);
            }
            //移除出 机构（使用场景：1、调查方机构模块，移除名下调查员）
            else if("1500".equals(btnCode)){
                surveyInvestigator.setDeleteFlag(1);
                surveyInvestigator.setQuitTime(new Date());
                surveyInvestigatorMapper.updateByPrimaryKey(surveyInvestigator);
                //同时移除调查人权限
                /*Map<String,Object> mapRole = new HashMap<>();
                mapRole.put("roleCode","lordDi");
                mapRole.put("notRole",51);//特殊处理，不删除此id
                List<BusinessRole> businessRoles = businessRoleMapper.selectInvestigatorRoleList(mapRole);
                for (BusinessRole businessRole : businessRoles) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("userId",surveyInvestigator.getUserId());
                    map.put("roleId",businessRole.getId());
                    busUserRoleMapper.deleteByParam(map);
                }*/
                //仅仅移除调查的权限
                if (!StringUtils.isEmpty(surveyRole)) {
                    String [] roleIds = surveyRole.split(",");
                    for (String roleId : roleIds) {
                        if (!StringUtils.isEmpty(roleId)){
                            Map<String,Object> map = new HashMap<>();
                            map.put("userId",surveyInvestigator.getUserId());
                            map.put("roleId",roleId);
                            busUserRoleMapper.deleteByParam(map);
                        }
                    }
                }
                //当调查员被移除时对带教清单列表进行修改
                if(surveyInvestigator.getIsSettlement()==0){
                    Map findMap=new HashMap();
                    findMap.put("surveyInvestigatorId",surveyInvestigator.getId());
                    SurveyInvestigatorRewordMid surveyInvestigatorRewordMid=surveyInvestigatorRewordMidMapper.selectByOne(findMap);
                    if(surveyInvestigatorRewordMid != null){
                        SurveyTeachRewardList surveyTeachRewardList=surveyTeachRewardListMapper.selectByPrimaryKey(surveyInvestigatorRewordMid.getRewardListId());
                        if(surveyTeachRewardList != null && surveyInvestigator.getTeacherRealReward() != null && surveyTeachRewardList.getState() != 4){
                            surveyInvestigatorRewordMid.setDeleteFlag(1);
                            surveyInvestigatorRewordMidMapper.updateByPrimaryKey(surveyInvestigatorRewordMid);
                            surveyTeachRewardList.setTeacherReward(surveyTeachRewardList.getTeacherReward()-surveyInvestigator.getTeacherRealReward());
                            surveyTeachRewardList.setStudentNum(surveyTeachRewardList.getStudentNum()-1);
                            Integer teacherCount=surveyInvestigatorMapper.selectTeacherCount(surveyTeachRewardList.getId());
                            surveyTeachRewardList.setTeacherNum(teacherCount);
                            surveyTeachRewardListMapper.updateByPrimaryKey(surveyTeachRewardList);
                        }
                    }
                }

            }
            //加入机构（使用场景：1、调查方机构模块，添加调查员）
            else if("1600".equals(btnCode)){
                UserInfo user = userInfoMapper.selectByPrimaryKey(apiReq.getLong("userId"));

                //会存在二次认证
                surveyInvestigator = surveyInvestigatorMapper.selectByUserId(apiReq.getLong("userId"));
//                if(surveyInvestigator != null){
//                    surveyInvestigatorMapper.deleteByPrimaryKey(surveyInvestigator.getId());
//                }
                if (surveyInvestigator == null){
                    surveyInvestigator = new SurveyInvestigator();
                }
//                surveyInvestigator = new SurveyInvestigator();

                surveyInvestigator.setNickName(user.getNickName());
                surveyInvestigator.setUserId(user.getUserId());
                surveyInvestigator.setRealName(user.getUserName());
                surveyInvestigator.setIdcard(apiReq.getString("idcard"));
                surveyInvestigator.setTel(user.getUserTel());

                surveyInvestigator.setProvince(apiReq.getString("province"));
                surveyInvestigator.setProvinceId(apiReq.getInt("provinceId"));
                surveyInvestigator.setCity(apiReq.getString("city"));
                surveyInvestigator.setCityId(apiReq.getInt("cityId"));
                surveyInvestigator.setDistrict(apiReq.getString("district"));
                surveyInvestigator.setDistrictId(apiReq.getInt("districtId"));
                surveyInvestigator.setAddress(apiReq.getString("address"));
                surveyInvestigator.setBasicIntegral(apiReq.getDouble("basicIntegral"));
                //覆盖区域
               /* String includeArea = apiReq.getString("includeArea");
                surveyInvestigator.setIncludeArea(includeArea);
                String includeAreaName = apiReq.getString("includeAreaName");
                surveyInvestigator.setIncludeAreaName(includeAreaName);*/

                //第一责任区
                surveyInvestigator.setResArea(apiReq.getString("resArea"));
                surveyInvestigator.setResAreaId(apiReq.getString("resAreaId"));

                //其他覆盖区域
                surveyInvestigator.setOtherOverlayArea(apiReq.getString("otherOverlayArea"));
                surveyInvestigator.setOtherOverlayAreaId(apiReq.getString("otherOverlayAreaId"));

                //银行卡
                surveyInvestigator.setBankNo(apiReq.getString("bankNo"));
                surveyInvestigator.setBankName(apiReq.getString("bankName"));


                //擅长领域
                String includeBus = apiReq.getString("includeBus");
                surveyInvestigator.setIncludeBus(includeBus);
                String includeBusName = apiReq.getString("includeBusName");
                surveyInvestigator.setIncludeBusName(includeBusName);

                //擅长任务类型
                String includeTask = apiReq.getString("includeTask");
                surveyInvestigator.setIncludeTask(includeTask);
                String includeTaskName = apiReq.getString("includeTaskName");
                surveyInvestigator.setIncludeTaskName(includeTaskName);
                surveyInvestigator.setRegisterNo(apiReq.getString("registerNo"));

                surveyInvestigator.setAccState(0); //账户状态：0、正常
                surveyInvestigator.setAuthType(2); //认证状态：1、认证通过
                surveyInvestigator.setRemark(apiReq.getString("remark"));
                String isNecessary=apiReq.getString("isNecessary");
                if (isNecessary.equals("0")) {
                    //查询带教老师信息
                    String teacherUserId=apiReq.get("teacherUserId").toString();
                    if(teacherUserId != null && !teacherUserId.equals("")){
                        SurveyInvestigator surveyInvestigatorTwo=surveyInvestigatorMapper.selectByUserId(Long.parseLong(teacherUserId));
                        if(surveyInvestigatorTwo != null){
                            surveyInvestigator.setTeacherUserId(Integer.parseInt(surveyInvestigatorTwo.getUserId().toString()));
                            surveyInvestigator.setTeacherUserName(surveyInvestigatorTwo.getRealName());
                            surveyInvestigator.setTeacherOrgId(Integer.parseInt(surveyInvestigatorTwo.getOrgId().toString()));
                            surveyInvestigator.setTeacherOrgName(surveyInvestigatorTwo.getOrgName());
                            surveyInvestigator.setTeacherUserTel(surveyInvestigatorTwo.getTel());
                            surveyInvestigator.setIsNewPeople(0);
                            surveyInvestigator.setIsSettlement(0);
                            String currentTime=DateUtils.getCurrentTime();
                            String laterTime=DateUtils.getFirstDays(currentTime,+28);
                            Date qualifiedEnfdTime=DateUtils.getFormatDate(laterTime);
                            surveyInvestigator.setQualifiedEnfdTime(qualifiedEnfdTime);
                        }
                    }
                }else if (isNecessary.equals("1")) {
                    surveyInvestigator.setIsNewPeople(1);
                    surveyInvestigator.setIsSettlement(1);
                }
                //乐凡币规则 -- 调查员认证
                SurveyLfcoinRule surveyLfcoinRule = surveyLfcoinRuleMapper.selectByCode("rz");
                if(surveyLfcoinRule!=null){
                    surveyInvestigator.setLefanCurrency(surveyLfcoinRule.getNum());

                    //生成乐凡币明细
                    SurveyLfcoinDetail detail = new SurveyLfcoinDetail();
                    detail.setLefanCoin(surveyLfcoinRule.getNum());//乐凡币数量
                    detail.setConsumeType(1);//消费类型（1，获得，2：消费）
                    detail.setSurveyUserId(surveyInvestigator.getUserId());//调查员ID
                    detail.setSurveyUserName(surveyInvestigator.getRealName());//调查员姓名
                    detail.setCreateTime(new Date());//时间
                    detail.setOprType(1);//类型（1.认证，2.业务案件，3.论坛.4.案件评星，5.兑换）
                    detail.setRemark("认证获取的乐凡币");
                    surveyLfcoinDetailMapper.insertSelective(detail);
                }

                //称号规则 -- 最低等级
                //原因：提交认证，可直接选择称号
                Long levelId = apiReq.getLong("levelId");
                SurveyLevel surveyLevel = surveyLevelMapper.selectByPrimaryKey(levelId);
                if(surveyLevel != null){
                    surveyInvestigator.setTitleId(surveyLevel.getId());
                    surveyInvestigator.setTitleName(surveyLevel.getName());
                }

                //成就点数规则 -- 认证成就点数
                SurveyAchieveRule surveyAchieveRule = surveyAchieveRuleMapper.selectByCode("rz");
                if(surveyAchieveRule != null){
                    //生成成就点数明细
                    SurveyAchieveDetail detail =new SurveyAchieveDetail();

                    //因认证通过时，可直接选择称号，两种情况：1、选择最低等级，根据“成就点数规则”；2、选择更高等级，需根据等级对应的成就点
                    if("V1".equals(surveyLevel.getCode())){
                        surveyInvestigator.setAchPoint(surveyAchieveRule.getNum());
                        detail.setAchNum(surveyAchieveRule.getNum());//1、规则对应点数
                    }else{
                        surveyInvestigator.setAchPoint(surveyLevel.getSuccessPoint());
                        detail.setAchNum(surveyLevel.getSuccessPoint());//2、称号对应点数
                    }

                    detail.setAchRemark("认证获取的成就点数");//获得成就点数备注
                    detail.setSurveyUserId(surveyInvestigator.getUserId());//调查员ID
                    detail.setSurveyUserName(surveyInvestigator.getRealName());//调查员姓名
                    detail.setCreateTime(new Date());//时间
                    detail.setOprType(1);//业务类型（1.认证，2.业务案件，3.论坛，4.业务案件评星）
                    surveyAchieveDetailMapper.insertSelective(detail);
                }

                //金额
                surveyInvestigator.setAmount(0D);

                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(apiReq.getLong("franchiseeId"));
                if(surveyFranchisee!=null){
                    surveyInvestigator.setOrgId(surveyFranchisee.getId());      //机构id
                    surveyInvestigator.setOrgName(surveyFranchisee.getName());  //机构名称
                    surveyInvestigator.setBusType(surveyFranchisee.getBusType());
                }

                surveyInvestigator.setCreateBy(userId);
                surveyInvestigator.setCreateByName(userInfo.getUserName());
                surveyInvestigator.setCreateTime(new Date());
                surveyInvestigator.setDeleteFlag(0);//删除标志：0、否
                surveyInvestigator.setChannelType(0);

                if(surveyFranchisee.getType()==1){
                    surveyInvestigator.setType(1);//调查员类型（1：自营，2：加盟）
                }else if(surveyFranchisee.getType()==2){
                    surveyInvestigator.setType(2);//调查员类型（1：自营，2：加盟）
                }else if(surveyFranchisee.getType()==3){
                    surveyInvestigator.setType(3);//调查员类型（1：自营，2：加盟，3：兼职）
                }

                //手写签名
                String img = apiReq.getString("proFile");
                SurveyUserSign surveyUserSign = surveyUserSignMapper.selectByUserId(user.getUserId());
                if (surveyUserSign != null) {
                    surveyUserSign.setSignImg(img);
                    surveyUserSign.setUpdateBy(userInfo.getUserName());
                    surveyUserSign.setUpdateTime(new Date());
                    surveyUserSignMapper.updateByPrimaryKey(surveyUserSign);
                }else{
                    surveyUserSign = new SurveyUserSign();
                    surveyUserSign.setUserId(user.getUserId());
                    surveyUserSign.setUserName(user.getUserName());
                    surveyUserSign.setSignImg(img);
                    surveyUserSign.setCreateBy(userInfo.getUserName());
                    surveyUserSign.setCreateTime(new Date());
                    surveyUserSign.setUpdateBy(userInfo.getUserName());
                    surveyUserSign.setUpdateTime(new Date());
                    surveyUserSign.setDeleteFlag(0);
                    surveyUserSignMapper.insert(surveyUserSign);
                }

                if (surveyInvestigator.getId() == null){
                    surveyInvestigatorMapper.insertSelective(surveyInvestigator);
                }else{
                    surveyInvestigatorMapper.updateByPrimaryKey(surveyInvestigator);
                }

                //认证材料
                String materialImgs = apiReq.getString("materialImgs");
                if (materialImgs != null) {
                    String[] urls = materialImgs.split(",");
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

                        //材料中间表
                        SurveyConsignerFile surveyConsignerFile = new SurveyConsignerFile();
                        surveyConsignerFile.setFileId(commonFile.getId());
                        surveyConsignerFile.setFileName(commonFile.getFileName());
                        surveyConsignerFile.setConsignId(surveyInvestigator.getId());
                        surveyConsignerFile.setConsignType(2); // 调查方
                        surveyConsignerFile.setCreateBy(userInfo.getUserId());
                        surveyConsignerFile.setCreateByName(userInfo.getUserName());
                        surveyConsignerFile.setCreateTime(new Date());
                        surveyConsignerFileMapper.insert(surveyConsignerFile);
                    }
                }

                //给用户赋权限
                Map<String,Object> map = new HashMap<>();
                map.put("userId",surveyInvestigator.getUserId());
                map.put("roleId",50);
                BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(map);
                if(busUserRole==null){
                    busUserRole = new BusUserRole();
                    busUserRole.setUserId(surveyInvestigator.getUserId());
                    busUserRole.setRoleId(50l); //调查员权限
                    busUserRoleMapper.insertSelective(busUserRole);
                }

                //默认登录密码
                UserLogin userLogin = userLoginMapper.selectByPhone(user.getUserTel());
                if(userLogin !=null && userLogin.getPassword()==null){
                    userLogin.setPassword(MD5Util.MD5Encode("123456@Qaz",null));
                    userLoginMapper.updateByPrimaryKeySelective(userLogin);
                }
            }
            //修改为正常-- 账户状态（0、正常；1、冻结；2、删除）
            else if("1700".equals(btnCode)){
                surveyInvestigator.setAccState(0);
            }
            //修改为禁用-- 账户状态（0、正常；1、冻结；2、删除）
            else if("1800".equals(btnCode)){
                surveyInvestigator.setAccState(1);
            }
            surveyInvestigatorMapper.updateByPrimaryKey(surveyInvestigator);
        }
        //乐凡币介绍
        else if("lfcoinExplain".equals(surveyCode)){
            SurveyLfcoinExplain surveyLfcoinExplain = surveyLfcoinExplainMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyLfcoinExplain.setDeleteFlag(1);
            }
            surveyLfcoinExplainMapper.updateByPrimaryKeySelective(surveyLfcoinExplain);
        }
        //乐凡币规则
        else if("lfcoinRule".equals(surveyCode)){
            SurveyLfcoinRule surveyLfcoinRule = surveyLfcoinRuleMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyLfcoinRule.setDeleteFlag(1);
            }
            surveyLfcoinRuleMapper.updateByPrimaryKeySelective(surveyLfcoinRule);
        }
        //成就点规则
        else if("achieveRule".equals(surveyCode)){
            SurveyAchieveRule surveyAchieveRule = surveyAchieveRuleMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyAchieveRule.setDeleteFlag(1);
            }
            surveyAchieveRuleMapper.updateByPrimaryKeySelective(surveyAchieveRule);
        }
        //帖子类别
        else if("knowledgeType".equals(surveyCode)){
            SurveyKnowledgeType surveyKnowledgeType = surveyKnowledgeTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyKnowledgeType.setDeleteFlag(1);
            }
            //唯一置顶
            else if("1100".equals(btnCode)){
                surveyKnowledgeType.setIsTop(1);
                //修改其余所有数据为非置顶
                surveyKnowledgeTypeMapper.updateOther(surveyKnowledgeType);
            }

            surveyKnowledgeTypeMapper.updateByPrimaryKeySelective(surveyKnowledgeType);
        }
        //论坛帖子
        else if("knowledgeBase".equals(surveyCode)){
            SurveyKnowledgeBase surveyKnowledgeBase = surveyKnowledgeBaseMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyKnowledgeBase.setIsDelete(1);
            }
            //置顶
            else if("1100".equals(btnCode)){
                surveyKnowledgeBase.setIsTop(1);
            }
            //取消置顶
            else if("1200".equals(btnCode)){
                surveyKnowledgeBase.setIsTop(0);
            }
            surveyKnowledgeBaseMapper.updateByPrimaryKeySelective(surveyKnowledgeBase);
        }
        //帖子评论
        else if("knowledgeComment".equals(surveyCode)){
            SurveyKnowledgeComment surveyKnowledgeComment = surveyKnowledgeCommentMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyKnowledgeComment.setIsDelete(1);
            }
            surveyKnowledgeCommentMapper.updateByPrimaryKeySelective(surveyKnowledgeComment);
        }
        //调查委托方价格
        else if("consignorPrice".equals(surveyCode)){
            SurveyConsignorPrice surveyConsignorPrice = surveyConsignorPriceMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                Map<String,Object> map = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("enturyId",apiReq.getLong("enturyId"));
                surveyConsignorPriceMapper.deleteByInfo(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            //添加价格
            if("1100".equals(btnCode)){
                Double price = apiReq.getDouble("price");
                Map<String,Object> map = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("taskId",apiReq.getLong("taskId"));
                map.put("enturyId",apiReq.getLong("enturyId"));
                surveyConsignorPrice = surveyConsignorPriceMapper.selectInfo(map);
                if(surveyConsignorPrice!=null){
                    surveyConsignorPrice.setTaskPrice(price);
                }else{
                    surveyConsignorPrice = new SurveyConsignorPrice();
                    surveyConsignorPrice.setEnturyId(apiReq.getLong("enturyId"));
                    surveyConsignorPrice.setEnturyName(apiReq.getString("enturyName"));
                    surveyConsignorPrice.setTaskId(apiReq.getLong("taskId"));
                    surveyConsignorPrice.setTaskName(apiReq.getString("taskName"));
                    surveyConsignorPrice.setDeleteFlag(0);
                    surveyConsignorPrice.setTaskPrice(price);

                    CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                    surveyConsignorPrice.setAreaId(area.getAreaId().intValue());
                    surveyConsignorPrice.setAreaType(area.getAreaType());
                    surveyConsignorPrice.setAreaName(area.getAreaLongname());

                    if(area.getAreaType()==1){ //省
                        surveyConsignorPrice.setProvinceId(area.getAreaId().intValue());//省id
                        surveyConsignorPrice.setProvince(area.getAreaName()); //省名称
                    }else if(area.getAreaType()==2){ //市
                        surveyConsignorPrice.setCity(area.getAreaName());//市id
                        surveyConsignorPrice.setCityId(area.getAreaId().intValue());//市id

                        //查询省级父级
                        CommonArea areaPr = commonAreaMapper.selectByPrimaryKey(area.getParentId());
                        if(areaPr!=null){
                            surveyConsignorPrice.setProvinceId(areaPr.getAreaId().intValue());//省id
                            surveyConsignorPrice.setProvince(areaPr.getAreaName()); //省名称
                        }

                    }else if(area.getAreaType()==3){ //区
                        surveyConsignorPrice.setDistrict(area.getAreaName());//区名称
                        surveyConsignorPrice.setDistrictId(area.getAreaId().intValue());//区id

                        //查询市级父级
                        CommonArea areaCity = commonAreaMapper.selectByPrimaryKey(area.getParentId());
                        if(areaCity!=null){
                            surveyConsignorPrice.setCity(areaCity.getAreaName());//市名称
                            surveyConsignorPrice.setCityId(areaCity.getAreaId().intValue());//市id
                        }

                        //查询省级父级
                        CommonArea areaPr = commonAreaMapper.selectByPrimaryKey(areaCity.getParentId());
                        if(areaPr!=null){
                            surveyConsignorPrice.setProvinceId(areaPr.getAreaId().intValue());//省id
                            surveyConsignorPrice.setProvince(areaPr.getAreaName()); //省名称
                        }
                    }


                    surveyConsignorPriceMapper.insertSelective(surveyConsignorPrice);
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
            //修改价格
            if("1200".equals(btnCode)){
                surveyConsignorPrice.setTaskPrice(apiReq.getDouble("taskPrice"));
            }
            //新增价格，省会、地级市、县级市
            if("1300".equals(btnCode)){
                Double price = apiReq.getDouble("price");
                Long taskId = apiReq.getLong("taskId");//任务类型id
                Long directionResultTypeId = apiReq.getLong("directionResultTypeId");//方向结果id
                String directionResultTypeName = apiReq.getString("directionResultTypeName");//方向结果
                //全部
                if(apiReq.getLong("areaId") == 0){
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(taskId);
                    Map<String,Object> map = new HashMap<>();
                    map.put("cityType",0);
                    map.put("parentId",0);
                    List<CommonArea> commonAreas = commonAreaMapper.selectListByCityType(map);
                    for (int i = 0; i < commonAreas.size(); i++) {
                        map = new HashMap<>();
                        map.put("areaId",commonAreas.get(i).getAreaId());
                        map.put("taskId",taskId);
                        map.put("enturyId",apiReq.getLong("enturyId"));
                        map.put("cityType",apiReq.getLong("cityType"));
                        map.put("directionResultTypeId",directionResultTypeId);
                        if(taskId==13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28){
                            map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
                        }
                        List<SurveyConsignorPrice> priceList = surveyConsignorPriceMapper.list(map);
                        if(priceList.size() > 0){
                            for (SurveyConsignorPrice consignorPrice : priceList) {
                                consignorPrice.setTaskPrice(price);
                                surveyConsignorPriceMapper.updateByPrimaryKeySelective(consignorPrice);
                            }
                        }else {
                            if (surveyTaskInfo != null) {
                                if (taskId == 13  || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28) { // 医疗调查
                                    Long taskInfoContentId = apiReq.getLong("taskInfoContentId");//任务子类id
                                    String taskInfoContentName = apiReq.getString("taskInfoContentName");//任务子类
                                    SurveyConsignorPrice consignorPrice = new SurveyConsignorPrice();
                                    consignorPrice.setTaskId(taskId);
                                    consignorPrice.setTaskName(surveyTaskInfo.getName());
                                    consignorPrice.setTaskInfoContentId(taskInfoContentId);
                                    consignorPrice.setTaskInfoContentName(taskInfoContentName);
                                    consignorPrice.setDirectionResultTypeId(directionResultTypeId);
                                    consignorPrice.setDirectionResultTypeName(directionResultTypeName);

                                    //具体数据
                                    consignorPrice.setEnturyId(apiReq.getLong("enturyId"));
                                    consignorPrice.setEnturyName(apiReq.getString("enturyName"));
                                    consignorPrice.setDeleteFlag(0);
                                    consignorPrice.setTaskPrice(price);
                                    consignorPrice.setCityType(apiReq.getInt("cityType"));

//                                    CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                                    consignorPrice.setAreaId(commonAreas.get(i).getAreaId().intValue());
                                    consignorPrice.setAreaType(commonAreas.get(i).getCityType());
                                    consignorPrice.setAreaName(commonAreas.get(i).getAreaLongname());

                                    surveyConsignorPriceMapper.insertSelective(consignorPrice);

                                } else {
                                    map = new HashMap<>();
                                    map.put("taskInfoId", taskId);
                                    List<SurveyTaskInfoContent> contentList = surveyTaskInfoContentMapper.list(map);
                                    for (SurveyTaskInfoContent surveyTaskInfoContent : contentList) {
                                        SurveyConsignorPrice consignorPrice = new SurveyConsignorPrice();
                                        consignorPrice.setTaskId(taskId);
                                        consignorPrice.setTaskName(surveyTaskInfo.getName());
                                        consignorPrice.setTaskInfoContentId(surveyTaskInfoContent.getId());
                                        consignorPrice.setTaskInfoContentName(surveyTaskInfoContent.getName());
                                        consignorPrice.setDirectionResultTypeId(directionResultTypeId);
                                        consignorPrice.setDirectionResultTypeName(directionResultTypeName);

                                        //具体数据
                                        consignorPrice.setEnturyId(apiReq.getLong("enturyId"));
                                        consignorPrice.setEnturyName(apiReq.getString("enturyName"));
                                        consignorPrice.setDeleteFlag(0);
                                        consignorPrice.setTaskPrice(price);
                                        consignorPrice.setCityType(apiReq.getInt("cityType"));

                                        consignorPrice.setAreaId(commonAreas.get(i).getAreaId().intValue());
                                        consignorPrice.setAreaType(commonAreas.get(i).getCityType());
                                        consignorPrice.setAreaName(commonAreas.get(i).getAreaLongname());

                                        surveyConsignorPriceMapper.insertSelective(consignorPrice);
                                    }
                                }
                            }
                        }
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);

                }


                Map<String,Object> map = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("taskId",taskId);
                map.put("enturyId",apiReq.getLong("enturyId"));
                map.put("cityType",apiReq.getLong("cityType"));
                map.put("directionResultTypeId",directionResultTypeId);

                if(taskId==13  || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28){
                    map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
                }
                List<SurveyConsignorPrice> priceList = surveyConsignorPriceMapper.list(map);
                if(priceList.size() > 0){
                    if(price!=null){
                        for (SurveyConsignorPrice consignorPrice : priceList) {
                            consignorPrice.setTaskPrice(price);
                            surveyConsignorPriceMapper.updateByPrimaryKey(consignorPrice);
                        }
                        return new ApiResponse(ApiMsgEnum.SUCCESS);
                    }else{
                        for (SurveyConsignorPrice consignorPrice : priceList) {
                            surveyConsignorPriceMapper.deleteByPrimaryKey(consignorPrice.getId());
                        }
                        return new ApiResponse(ApiMsgEnum.SUCCESS);
                    }
                }else {

                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(taskId);
                    if (surveyTaskInfo != null) {
                        if (taskId == 13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28) { // 医疗调查
                            Long taskInfoContentId = apiReq.getLong("taskInfoContentId");//任务子类id
                            String taskInfoContentName = apiReq.getString("taskInfoContentName");//任务子类
                            SurveyConsignorPrice consignorPrice = new SurveyConsignorPrice();
                            consignorPrice.setTaskId(taskId);
                            consignorPrice.setTaskName(surveyTaskInfo.getName());
                            consignorPrice.setTaskInfoContentId(taskInfoContentId);
                            consignorPrice.setTaskInfoContentName(taskInfoContentName);
                            consignorPrice.setDirectionResultTypeId(directionResultTypeId);
                            consignorPrice.setDirectionResultTypeName(directionResultTypeName);

                            //具体数据
                            consignorPrice.setEnturyId(apiReq.getLong("enturyId"));
                            consignorPrice.setEnturyName(apiReq.getString("enturyName"));
                            consignorPrice.setDeleteFlag(0);
                            consignorPrice.setTaskPrice(price);
                            consignorPrice.setCityType(apiReq.getInt("cityType"));

                            CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                            consignorPrice.setAreaId(area.getAreaId().intValue());
                            consignorPrice.setAreaType(area.getCityType());
                            consignorPrice.setAreaName(area.getAreaLongname());

                            surveyConsignorPriceMapper.insertSelective(consignorPrice);

                        } else {
                            map = new HashMap<>();
                            map.put("taskInfoId", taskId);
                            List<SurveyTaskInfoContent> contentList = surveyTaskInfoContentMapper.list(map);
                            for (SurveyTaskInfoContent surveyTaskInfoContent : contentList) {
                                SurveyConsignorPrice consignorPrice = new SurveyConsignorPrice();
                                consignorPrice.setTaskId(taskId);
                                consignorPrice.setTaskName(surveyTaskInfo.getName());
                                consignorPrice.setTaskInfoContentId(surveyTaskInfoContent.getId());
                                consignorPrice.setTaskInfoContentName(surveyTaskInfoContent.getName());
                                consignorPrice.setDirectionResultTypeId(directionResultTypeId);
                                consignorPrice.setDirectionResultTypeName(directionResultTypeName);

                                //具体数据
                                consignorPrice.setEnturyId(apiReq.getLong("enturyId"));
                                consignorPrice.setEnturyName(apiReq.getString("enturyName"));
                                consignorPrice.setDeleteFlag(0);
                                consignorPrice.setTaskPrice(price);
                                consignorPrice.setCityType(apiReq.getInt("cityType"));

                                CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                                consignorPrice.setAreaId(area.getAreaId().intValue());
                                consignorPrice.setAreaType(area.getCityType());
                                consignorPrice.setAreaName(area.getAreaLongname());

                                surveyConsignorPriceMapper.insertSelective(consignorPrice);
                            }
                        }
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
            surveyConsignorPriceMapper.updateByPrimaryKeySelective(surveyConsignorPrice);
        }
        //调查调查方价格
        else if("franchiseePrice".equals(surveyCode)){
            SurveyFranchiseePrice surveyFranchiseePrice = surveyFranchiseePriceMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                Map<String,Object> map = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("franchiseeId",apiReq.getLong("franchiseeId"));
                map.put("priceType",apiReq.getLong("priceType"));
                surveyFranchiseePriceMapper.deleteByInfo(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            //添加价格
            if("1100".equals(btnCode)){
                Double price = apiReq.getDouble("price");
                Map<String,Object> map = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("taskId",apiReq.getLong("taskId"));
                map.put("franchiseeId",apiReq.getLong("franchiseeId"));
                surveyFranchiseePrice = surveyFranchiseePriceMapper.selectInfo(map);
                if(surveyFranchiseePrice!=null){
                    surveyFranchiseePrice.setTaskPrice(price);
                }else{
                    surveyFranchiseePrice = new SurveyFranchiseePrice();
                    surveyFranchiseePrice.setFranchiseeId(apiReq.getLong("franchiseeId"));
                    surveyFranchiseePrice.setFranchiseeName(apiReq.getString("franchiseeName"));
                    surveyFranchiseePrice.setTaskId(apiReq.getLong("taskId"));
                    surveyFranchiseePrice.setTaskName(apiReq.getString("taskName"));
                    surveyFranchiseePrice.setDeleteFlag(0);
                    surveyFranchiseePrice.setTaskPrice(price);

                    CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                    surveyFranchiseePrice.setAreaId(area.getAreaId().intValue());
                    surveyFranchiseePrice.setAreaType(area.getAreaType());
                    surveyFranchiseePrice.setAreaName(area.getAreaLongname());

                    if(area.getAreaType()==1){ //省
                        surveyFranchiseePrice.setProvinceId(area.getAreaId().intValue());//省id
                        surveyFranchiseePrice.setProvince(area.getAreaName()); //省名称
                    }else if(area.getAreaType()==2){ //市
                        surveyFranchiseePrice.setCity(area.getAreaName());//市id
                        surveyFranchiseePrice.setCityId(area.getAreaId().intValue());//市id

                        //查询省级父级
                        CommonArea areaPr = commonAreaMapper.selectByPrimaryKey(area.getParentId());
                        if(areaPr!=null){
                            surveyFranchiseePrice.setProvinceId(areaPr.getAreaId().intValue());//省id
                            surveyFranchiseePrice.setProvince(areaPr.getAreaName()); //省名称
                        }

                    }else if(area.getAreaType()==3){ //区
                        surveyFranchiseePrice.setDistrict(area.getAreaName());//区名称
                        surveyFranchiseePrice.setDistrictId(area.getAreaId().intValue());//区id

                        //查询市级父级
                        CommonArea areaCity = commonAreaMapper.selectByPrimaryKey(area.getParentId());
                        if(areaCity!=null){
                            surveyFranchiseePrice.setCity(areaCity.getAreaName());//市名称
                            surveyFranchiseePrice.setCityId(areaCity.getAreaId().intValue());//市id
                        }

                        //查询省级父级
                        CommonArea areaPr = commonAreaMapper.selectByPrimaryKey(areaCity.getParentId());
                        if(areaPr!=null){
                            surveyFranchiseePrice.setProvinceId(areaPr.getAreaId().intValue());//省id
                            surveyFranchiseePrice.setProvince(areaPr.getAreaName()); //省名称
                        }
                    }

                    surveyFranchiseePriceMapper.insertSelective(surveyFranchiseePrice);
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }

            //修改价格
            if("1200".equals(btnCode)){
                surveyFranchiseePrice.setTaskPrice(apiReq.getDouble("taskPrice"));
            }
            //新增价格，省会、地级市、县级市
            if("1300".equals(btnCode)){
                Double price = apiReq.getDouble("price");
                Long taskId = apiReq.getLong("taskId");//任务类型id
                Long directionResultTypeId = apiReq.getLong("directionResultTypeId");//方向结果id
                String directionResultTypeName = apiReq.getString("directionResultTypeName");//方向结果
                Integer priceType= apiReq.getInt("priceType");
                //全部
                if(apiReq.getLong("areaId") == 0) {
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(taskId);
                    Map<String,Object> map = new HashMap<>();
                    map.clear();
                    map.put("cityType", 0);
                    map.put("parentId", 0);
                    List<CommonArea> commonAreas = commonAreaMapper.selectListByCityType(map);
                    for (int i = 0; i < commonAreas.size(); i++) {
                        map = new HashMap<>();
                        map.put("priceType",priceType);
                        map.put("areaId",commonAreas.get(i).getAreaId());
                        map.put("taskId",taskId);
                        map.put("franchiseeId",apiReq.getLong("franchiseeId"));
                        map.put("cityType",apiReq.getLong("cityType"));
                        map.put("directionResultTypeId",directionResultTypeId);
                        if(taskId==13|| taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28){
                            map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
                        }
                        List<SurveyFranchiseePrice> priceList = surveyFranchiseePriceMapper.list(map);
                        if(priceList.size() > 0){
                            for (SurveyFranchiseePrice franchiseePrice : priceList) {
                                franchiseePrice.setTaskPrice(price);
                                surveyFranchiseePriceMapper.updateByPrimaryKeySelective(franchiseePrice);
                            }
                        }else {

                            if(surveyTaskInfo!=null) {
                                if (taskId == 13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28) { // 医疗调查
                                    Long taskInfoContentId = apiReq.getLong("taskInfoContentId");//任务子类id
                                    String taskInfoContentName = apiReq.getString("taskInfoContentName");//任务子类
                                    SurveyFranchiseePrice franchiseePrice = new SurveyFranchiseePrice();
                                    franchiseePrice.setTaskId(taskId);
                                    franchiseePrice.setTaskName(surveyTaskInfo.getName());
                                    franchiseePrice.setTaskInfoContentId(taskInfoContentId);
                                    franchiseePrice.setTaskInfoContentName(taskInfoContentName);
                                    franchiseePrice.setDirectionResultTypeId(directionResultTypeId);
                                    franchiseePrice.setDirectionResultTypeName(directionResultTypeName);

                                    //具体数据
                                    franchiseePrice.setFranchiseeId(apiReq.getLong("franchiseeId"));
                                    franchiseePrice.setFranchiseeName(apiReq.getString("franchiseeName"));
                                    franchiseePrice.setDeleteFlag(0);
                                    franchiseePrice.setTaskPrice(price);
                                    franchiseePrice.setCityType(apiReq.getInt("cityType"));
                                    franchiseePrice.setPriceType(priceType);

                                    franchiseePrice.setAreaId(commonAreas.get(i).getAreaId().intValue());
                                    franchiseePrice.setAreaType(commonAreas.get(i).getCityType());
                                    franchiseePrice.setAreaName(commonAreas.get(i).getAreaLongname());

                                    surveyFranchiseePriceMapper.insertSelective(franchiseePrice);

                                } else {
                                    map = new HashMap<>();
                                    map.put("taskInfoId", taskId);
                                    List<SurveyTaskInfoContent> contentList = surveyTaskInfoContentMapper.list(map);
                                    for (SurveyTaskInfoContent surveyTaskInfoContent : contentList) {
                                        SurveyFranchiseePrice franchiseePrice = new SurveyFranchiseePrice();
                                        franchiseePrice.setTaskId(taskId);
                                        franchiseePrice.setTaskName(surveyTaskInfo.getName());
                                        franchiseePrice.setTaskInfoContentId(surveyTaskInfoContent.getId());
                                        franchiseePrice.setTaskInfoContentName(surveyTaskInfoContent.getName());
                                        franchiseePrice.setDirectionResultTypeId(directionResultTypeId);
                                        franchiseePrice.setDirectionResultTypeName(directionResultTypeName);

                                        //具体数据
                                        franchiseePrice.setFranchiseeId(apiReq.getLong("franchiseeId"));
                                        franchiseePrice.setFranchiseeName(apiReq.getString("franchiseeName"));
                                        franchiseePrice.setDeleteFlag(0);
                                        franchiseePrice.setTaskPrice(price);
                                        franchiseePrice.setCityType(apiReq.getInt("cityType"));
                                        franchiseePrice.setPriceType(priceType);

                                        franchiseePrice.setAreaId(commonAreas.get(i).getAreaId().intValue());
                                        franchiseePrice.setAreaType(commonAreas.get(i).getCityType());
                                        franchiseePrice.setAreaName(commonAreas.get(i).getAreaLongname());

                                        surveyFranchiseePriceMapper.insertSelective(franchiseePrice);
                                    }
                                }
                            }
                        }
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }

                Map<String,Object> map = new HashMap<>();
                map.put("priceType",priceType);
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("taskId",taskId);
                map.put("franchiseeId",apiReq.getLong("franchiseeId"));
                map.put("cityType",apiReq.getLong("cityType"));
                map.put("directionResultTypeId",directionResultTypeId);

                if(taskId==13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28){
                    map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
                }
                List<SurveyFranchiseePrice> priceList = surveyFranchiseePriceMapper.list(map);
                if(priceList.size() > 0){
                    if(price!=null){
                        for (SurveyFranchiseePrice franchiseePrice : priceList) {
                            franchiseePrice.setTaskPrice(price);
                            surveyFranchiseePriceMapper.updateByPrimaryKey(franchiseePrice);
                        }
                        return new ApiResponse(ApiMsgEnum.SUCCESS);
                    }else{
                        for (SurveyFranchiseePrice franchiseePrice : priceList) {
                            surveyFranchiseePriceMapper.deleteByPrimaryKey(franchiseePrice.getId());
                        }
                        return new ApiResponse(ApiMsgEnum.SUCCESS);
                    }
                }else {

                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(taskId);
                    if(surveyTaskInfo!=null) {
                        if (taskId == 13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28) { // 医疗调查
                            Long taskInfoContentId = apiReq.getLong("taskInfoContentId");//任务子类id
                            String taskInfoContentName = apiReq.getString("taskInfoContentName");//任务子类
                            SurveyFranchiseePrice franchiseePrice = new SurveyFranchiseePrice();
                            franchiseePrice.setTaskId(taskId);
                            franchiseePrice.setTaskName(surveyTaskInfo.getName());
                            franchiseePrice.setTaskInfoContentId(taskInfoContentId);
                            franchiseePrice.setTaskInfoContentName(taskInfoContentName);
                            franchiseePrice.setDirectionResultTypeId(directionResultTypeId);
                            franchiseePrice.setDirectionResultTypeName(directionResultTypeName);

                            //具体数据
                            franchiseePrice.setFranchiseeId(apiReq.getLong("franchiseeId"));
                            franchiseePrice.setFranchiseeName(apiReq.getString("franchiseeName"));
                            franchiseePrice.setDeleteFlag(0);
                            franchiseePrice.setTaskPrice(price);
                            franchiseePrice.setCityType(apiReq.getInt("cityType"));
                            franchiseePrice.setPriceType(priceType);

                            CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                            franchiseePrice.setAreaId(area.getAreaId().intValue());
                            franchiseePrice.setAreaType(area.getCityType());
                            franchiseePrice.setAreaName(area.getAreaLongname());

                            surveyFranchiseePriceMapper.insertSelective(franchiseePrice);

                        } else {
                            map = new HashMap<>();
                            map.put("taskInfoId", taskId);
                            List<SurveyTaskInfoContent> contentList = surveyTaskInfoContentMapper.list(map);
                            for (SurveyTaskInfoContent surveyTaskInfoContent : contentList) {
                                SurveyFranchiseePrice franchiseePrice = new SurveyFranchiseePrice();
                                franchiseePrice.setTaskId(taskId);
                                franchiseePrice.setTaskName(surveyTaskInfo.getName());
                                franchiseePrice.setTaskInfoContentId(surveyTaskInfoContent.getId());
                                franchiseePrice.setTaskInfoContentName(surveyTaskInfoContent.getName());
                                franchiseePrice.setDirectionResultTypeId(directionResultTypeId);
                                franchiseePrice.setDirectionResultTypeName(directionResultTypeName);

                                //具体数据
                                franchiseePrice.setFranchiseeId(apiReq.getLong("franchiseeId"));
                                franchiseePrice.setFranchiseeName(apiReq.getString("franchiseeName"));
                                franchiseePrice.setDeleteFlag(0);
                                franchiseePrice.setTaskPrice(price);
                                franchiseePrice.setCityType(apiReq.getInt("cityType"));
                                franchiseePrice.setPriceType(priceType);

                                CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                                franchiseePrice.setAreaId(area.getAreaId().intValue());
                                franchiseePrice.setAreaType(area.getCityType());
                                franchiseePrice.setAreaName(area.getAreaLongname());

                                surveyFranchiseePriceMapper.insertSelective(franchiseePrice);
                            }
                        }
                    }

                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
            surveyFranchiseePriceMapper.updateByPrimaryKeySelective(surveyFranchiseePrice);
        }
        //qa问答
        else if("qa".equals(surveyCode)){
            SurveyQa surveyQa = surveyQaMapper.selectByPrimaryKey(apiReq.getLong("id"));
            //删除
            if("9999".equals(btnCode)){
                surveyQa.setDeleteFlag(1);
            }
            //置顶
            else if("1200".equals(btnCode)){
                surveyQa.setIsTop(1);
            }
            //取消置顶
            else if("1201".equals(btnCode)){
                surveyQa.setIsTop(0);
            }
            //回复问题
            else if("1100".equals(btnCode)){
                String answer = apiReq.getString("answer");
                surveyQa.setAnswer(answer);
                surveyQa.setAnswerTime(new Date());
                surveyQa.setAnswerUserId(userInfo.getUserId());
                surveyQa.setAnswerUserName(userInfo.getUserName());
                surveyQa.setQuestionState(2);//问题状态（1：未回复，2：已回复）
            }
            surveyQaMapper.updateByPrimaryKey(surveyQa);
        }
        //调查调查方
        else if("franchisee".equals(surveyCode)){
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyFranchisee.setDeleteFlag(1);
                //同时删除所有名下人员
                Map<String,Object> map = new HashMap<>();
                map.put("orgId",apiReq.getLong("id"));
                map.put("pageIndex",null);
                List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.list(map);
                for (int i = 0; i < surveyInvestigators.size(); i++) {
                    surveyInvestigatorMapper.deleteByPrimaryKey(surveyInvestigators.get(i).getId());
                }
            }
            //修改调查方的角色
            else if("1100".equals(btnCode)){
                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    Long user = apiReq.getLong("userId");
                    //删除狄大人角色
                    Map map=new HashMap();
                    map.put("userId",user);
                    map.put("roleCode","lordDi");
                    businessRoleMapper.deleteByUserIdAndInvest(map);
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        BusUserRole busUserRole = new BusUserRole();
                        busUserRole.setUserId(user);
                        busUserRole.setRoleId(Long.valueOf(ro));
                        busUserRoleMapper.insertSelective(busUserRole);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            //修改为加盟
            else if("1700".equals(btnCode)){
                surveyFranchisee.setType(2);
                //并将名下所有调查员 修改为 加盟
                Map<String,Object> map = new HashMap<>();
                map.put("orgId",surveyFranchisee.getId());
                map.put("type",2);//调查员类型（1：自营，2：加盟）
                surveyInvestigatorMapper.updateTypeByFranchiseeId(map);
            }
            //修改为自营
            else if("1800".equals(btnCode)){
                surveyFranchisee.setType(1);
                //并将名下所有调查员 修改为 加盟
                Map<String,Object> map = new HashMap<>();
                map.put("orgId",surveyFranchisee.getId());
                map.put("type",1);//调查员类型（1：自营，2：加盟）
                surveyInvestigatorMapper.updateTypeByFranchiseeId(map);
            }
            //修改调查方类别（1.自营，2.加盟，3.兼职）
            else if("1900".equals(btnCode)){
                surveyFranchisee.setType(apiReq.getInt("franchiseeType"));
                surveyFranchisee.setInsuranceType(apiReq.getInt("insuranceType"));
                //并将名下所有调查员 修改为 加盟
                Map<String,Object> map = new HashMap<>();
                map.put("orgId",surveyFranchisee.getId());
                map.put("type",apiReq.getInt("franchiseeType"));//调查员类型（1：自营，2：加盟）
                surveyInvestigatorMapper.updateTypeByFranchiseeId(map);
            }
            //修改--多份病史价格
            else if("4000".equals(btnCode)){
                String medicalMoney = apiReq.getString("medicalMoney");
                surveyFranchisee.setMedicalMoney(Double.valueOf(medicalMoney));
            }
            else if("4400".equals(btnCode)){
                String maxMedicalMoney = apiReq.getString("maxMedicalMoney");
                surveyFranchisee.setMaxMedicalMoney(Double.valueOf(maxMedicalMoney));
            }
            //设置终审人员
            else if("5000".equals(btnCode)){
                //先删除记录，再保存全新数据
                Map<String,Object> map = new HashMap<>();
                map.put("franchiseeId",apiReq.getLong("id"));
                surveyUserFranchiseeMapper.deleteByFranchiseeId(map);
                //保存数据
                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        SurveyUserFranchisee surveyUserFranchisee =new SurveyUserFranchisee();
                        //机构信息
                        UserInfo info = userInfoMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if(info!=null){
                            surveyUserFranchisee.setUserId(info.getUserId());
                            surveyUserFranchisee.setUserName(info.getUserName());
                        }
                        surveyUserFranchisee.setFranchiseeId(surveyFranchisee.getId());
                        surveyUserFranchisee.setFranchiseeName(surveyFranchisee.getName());
                        surveyUserFranchisee.setDeleteFlag(0);
                        surveyUserFranchiseeMapper.insertSelective(surveyUserFranchisee);
                    }
                }
            }
            //修改机构
            else if("changeFranchisee".equals(btnCode)){
                //先修改 调查员信息
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(apiReq.getLong("userId"));
                Long oldOrgId = surveyInvestigator.getOrgId();
                surveyInvestigator.setOrgId(surveyFranchisee.getId());
                surveyInvestigator.setOrgName(surveyFranchisee.getName());
                surveyInvestigator.setType(surveyFranchisee.getType());
                surveyInvestigatorMapper.updateByPrimaryKey(surveyInvestigator);


                //原机构下没有人， 则将所有调查中的案件 跟着人走。
                Map<String,Object> map =  new HashMap<String,Object>();
                map.put("orgId",oldOrgId);
                List<SurveyInvestigator> list = surveyInvestigatorMapper.list(map);
                if (list.size() == 0){
                    //保司未审核的案件 更换机构
                    map = new HashMap<>();
                    map.put("oldSurveyOrgId",oldOrgId);
                    map.put("surveyOrgId",surveyFranchisee.getId());
                    map.put("surveyOrgName",surveyFranchisee.getName());
                    surveyAssignOrgMapper.updateListOrgId(map);
                }
            }
            surveyFranchiseeMapper.updateByPrimaryKeySelective(surveyFranchisee);
        }
        //商品
        else if("product".equals(surveyCode)){
            SurveyProductDto surveyProduct = surveyProductMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyProduct.setIsDelete(1);//删除
            }else if("1100".equals(btnCode)){
                surveyProduct.setIsUp(0); //下架
            }else if("1200".equals(btnCode)){
                surveyProduct.setIsUp(1); //上架
            }
            surveyProductMapper.updateByPrimaryKeySelective(surveyProduct);
        }
        //委托方默认价格
        else if("commonAreaPrice".equals(surveyCode)){
            //新增价格
            if("1100".equals(btnCode)){

                Double price = apiReq.getDouble("price");
                Integer taskId = apiReq.getInt("taskId");//任务类型id
                Long directionResultTypeId = apiReq.getLong("directionResultTypeId");//方向结果id
                String directionResultTypeName = apiReq.getString("directionResultTypeName");//方向结果
                Integer priceType= apiReq.getInt("priceType");
                //全部
                if(apiReq.getLong("areaId") == 0){
                    Map<String,Object> map = new HashMap<>();
                    map.put("cityType",0);
                    map.put("parentId",0);
                    List<CommonArea> commonAreas = commonAreaMapper.selectListByCityType(map);
                    for (int i = 0; i < commonAreas.size(); i++) {
                        SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(Long.valueOf(taskId));
                        map = new HashMap<>();
                        map.put("priceType",priceType);
                        map.put("areaId",commonAreas.get(i).getAreaId());
                        map.put("taskId",taskId);
                        map.put("cityType",apiReq.getLong("cityType"));
                        map.put("directionResultTypeId",directionResultTypeId);
                        if(taskId==13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28){
                            map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
                        }
                        List<SurveyCommonAreaPrice> priceList = surveyCommonAreaPriceMapper.list(map);
                        if(priceList.size() > 0){
                            if(price!=null){
                                for (SurveyCommonAreaPrice surveyCommonAreaPrice : priceList) {
                                    surveyCommonAreaPrice.setPrice(price);
                                    surveyCommonAreaPriceMapper.updateByInfo(surveyCommonAreaPrice);
                                }
                                return new ApiResponse(ApiMsgEnum.SUCCESS);
                            }else{
                                map = new HashMap<>();
                                map.put("areaId",apiReq.getLong("areaId"));
                                map.put("priceType",priceType);
                                surveyCommonAreaPriceMapper.deleteByInfo(map);
                                return new ApiResponse(ApiMsgEnum.SUCCESS);
                            }
                        }else {
                            if (surveyTaskInfo != null) {
                                if (taskId == 13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28) { // 医疗调查
                                    Long taskInfoContentId = apiReq.getLong("taskInfoContentId");//任务子类id
                                    String taskInfoContentName = apiReq.getString("taskInfoContentName");//任务子类
                                    SurveyCommonAreaPrice commonAreaPrice = new SurveyCommonAreaPrice();
                                    commonAreaPrice.setTaskId(taskId);
                                    commonAreaPrice.setTaskName(surveyTaskInfo.getName());
                                    commonAreaPrice.setTaskInfoContentId(taskInfoContentId);
                                    commonAreaPrice.setTaskInfoContentName(taskInfoContentName);
                                    commonAreaPrice.setDirectionResultTypeId(directionResultTypeId);
                                    commonAreaPrice.setDirectionResultTypeName(directionResultTypeName);

                                    //具体数据
                                    commonAreaPrice.setDeleteFlag(0);
                                    commonAreaPrice.setPrice(price);
                                    commonAreaPrice.setCityType(apiReq.getInt("cityType"));
                                    commonAreaPrice.setPriceType(priceType);

                                    commonAreaPrice.setAreaId(commonAreas.get(i).getAreaId());
                                    commonAreaPrice.setAreaType(commonAreas.get(i).getCityType().toString());
                                    commonAreaPrice.setAreaName(commonAreas.get(i).getAreaLongname());

                                    surveyCommonAreaPriceMapper.insertSelective(commonAreaPrice);

                                } else {
                                    map = new HashMap<>();
                                    map.put("taskInfoId", taskId);
                                    List<SurveyTaskInfoContent> contentList = surveyTaskInfoContentMapper.list(map);
                                    for (SurveyTaskInfoContent surveyTaskInfoContent : contentList) {
                                        SurveyCommonAreaPrice commonAreaPrice = new SurveyCommonAreaPrice();
                                        commonAreaPrice.setTaskId(taskId);
                                        commonAreaPrice.setTaskName(surveyTaskInfo.getName());
                                        commonAreaPrice.setTaskInfoContentId(surveyTaskInfoContent.getId());
                                        commonAreaPrice.setTaskInfoContentName(surveyTaskInfoContent.getName());
                                        commonAreaPrice.setDirectionResultTypeId(directionResultTypeId);
                                        commonAreaPrice.setDirectionResultTypeName(directionResultTypeName);

                                        //具体数据
                                        commonAreaPrice.setDeleteFlag(0);
                                        commonAreaPrice.setPrice(price);
                                        commonAreaPrice.setCityType(apiReq.getInt("cityType"));
                                        commonAreaPrice.setPriceType(priceType);
                                        commonAreaPrice.setAreaId(commonAreas.get(i).getAreaId());
                                        commonAreaPrice.setAreaType(commonAreas.get(i).getCityType().toString());
                                        commonAreaPrice.setAreaName(commonAreas.get(i).getAreaLongname());

                                        surveyCommonAreaPriceMapper.insertSelective(commonAreaPrice);
                                    }
                                }
                            }
                        }
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }

                Map<String,Object> map = new HashMap<>();
                map.put("priceType",priceType);
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("cityType",apiReq.getLong("cityType"));
                map.put("taskId",taskId);
                map.put("directionResultTypeId",directionResultTypeId);

//                if(taskId==13){
                map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
//                }
                List<SurveyCommonAreaPrice> priceList = surveyCommonAreaPriceMapper.list(map);

                if(priceList.size() > 0){
                    if(price!=null){
                        for (SurveyCommonAreaPrice surveyCommonAreaPrice : priceList) {
                            surveyCommonAreaPrice.setPrice(price);
                            surveyCommonAreaPriceMapper.updateByInfo(surveyCommonAreaPrice);
                        }
                        return new ApiResponse(ApiMsgEnum.SUCCESS);
                    }else{
                        map = new HashMap<>();
                        map.put("priceType",priceType);
                        map.put("areaId",apiReq.getLong("areaId"));
                        map.put("taskId",taskId);
                        map.put("cityType",apiReq.getLong("cityType"));
                        map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
                        map.put("directionResultTypeId",directionResultTypeId);
                        surveyCommonAreaPriceMapper.deleteByInfo(map);
                        return new ApiResponse(ApiMsgEnum.SUCCESS);
                    }
                }else {
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(Long.valueOf(taskId));
                    if(surveyTaskInfo!=null) {
                        if (taskId == 13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28) { // 医疗调查
                            Long taskInfoContentId = apiReq.getLong("taskInfoContentId");//任务子类id
                            String taskInfoContentName = apiReq.getString("taskInfoContentName");//任务子类
                            SurveyCommonAreaPrice commonAreaPrice = new SurveyCommonAreaPrice();
                            commonAreaPrice.setTaskId(taskId);
                            commonAreaPrice.setTaskName(surveyTaskInfo.getName());
                            commonAreaPrice.setTaskInfoContentId(taskInfoContentId);
                            commonAreaPrice.setTaskInfoContentName(taskInfoContentName);
                            commonAreaPrice.setDirectionResultTypeId(directionResultTypeId);
                            commonAreaPrice.setDirectionResultTypeName(directionResultTypeName);

                            //具体数据
                            commonAreaPrice.setDeleteFlag(0);
                            commonAreaPrice.setPrice(price);
                            commonAreaPrice.setCityType(apiReq.getInt("cityType"));
                            commonAreaPrice.setPriceType(priceType);
                            CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                            commonAreaPrice.setAreaId(area.getAreaId());
                            commonAreaPrice.setAreaType(area.getCityType().toString());
                            commonAreaPrice.setAreaName(area.getAreaLongname());
                            surveyCommonAreaPriceMapper.insertSelective(commonAreaPrice);

                        } else {
                            map = new HashMap<>();
                            map.put("taskInfoId", taskId);
                            List<SurveyTaskInfoContent> contentList = surveyTaskInfoContentMapper.list(map);
                            for (SurveyTaskInfoContent surveyTaskInfoContent : contentList) {
                                SurveyCommonAreaPrice commonAreaPrice = new SurveyCommonAreaPrice();
                                commonAreaPrice.setTaskId(taskId);
                                commonAreaPrice.setTaskName(surveyTaskInfo.getName());
                                commonAreaPrice.setTaskInfoContentId(surveyTaskInfoContent.getId());
                                commonAreaPrice.setTaskInfoContentName(surveyTaskInfoContent.getName());
                                commonAreaPrice.setDirectionResultTypeId(directionResultTypeId);
                                commonAreaPrice.setDirectionResultTypeName(directionResultTypeName);

                                //具体数据
                                commonAreaPrice.setDeleteFlag(0);
                                commonAreaPrice.setPrice(price);
                                commonAreaPrice.setCityType(apiReq.getInt("cityType"));
                                commonAreaPrice.setPriceType(priceType);
                                CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                                commonAreaPrice.setAreaId(area.getAreaId());
                                commonAreaPrice.setAreaType(area.getCityType().toString());
                                commonAreaPrice.setAreaName(area.getAreaLongname());
                                surveyCommonAreaPriceMapper.insertSelective(commonAreaPrice);
                            }
                        }
                    }

                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
            else if("9999".equals(btnCode)){ //删除
                Map<String,Object> map = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                surveyCommonAreaPriceMapper.deleteByInfo(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
        }
        //调查方默认价格
        else if("investigatorAreaPrice".equals(surveyCode)){
            //新增价格
            if("1100".equals(btnCode)){
                Double price = apiReq.getDouble("price");
                Integer taskId = apiReq.getInt("taskId");//任务类型id
                Long directionResultTypeId = apiReq.getLong("directionResultTypeId");//方向结果id
                String directionResultTypeName = apiReq.getString("directionResultTypeName");//方向结果
                Integer priceType= apiReq.getInt("priceType");
                //全部
                if(apiReq.getLong("areaId") == 0){
                    Map<String,Object> map = new HashMap<>();
                    map.put("cityType",0);
                    map.put("parentId",0);
                    List<CommonArea> commonAreas = commonAreaMapper.selectListByCityType(map);
                    for (int i = 0; i < commonAreas.size(); i++) {
                        SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(Long.valueOf(taskId));
                        map = new HashMap<>();
                        map.put("priceType",priceType);
                        map.put("areaId",commonAreas.get(i).getAreaId());
                        map.put("taskId",taskId);
                        map.put("cityType",apiReq.getLong("cityType"));
                        map.put("directionResultTypeId",directionResultTypeId);
                        if(taskId==13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28){
                            map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
                        }
                        List<SurveyInvestigatorAreaPrice> priceList = surveyInvestigatorAreaPriceMapper.list(map);
                        if(priceList.size() > 0){
                            if(price!=null){
                                for (SurveyInvestigatorAreaPrice surveyInvestigatorAreaPrice : priceList) {
                                    surveyInvestigatorAreaPrice.setPrice(price);
                                    surveyInvestigatorAreaPriceMapper.updateByInfo(surveyInvestigatorAreaPrice);
                                }
                                return new ApiResponse(ApiMsgEnum.SUCCESS);
                            }else{
                                map = new HashMap<>();
                                map.put("areaId",apiReq.getLong("areaId"));
                                map.put("priceType",priceType);
                                surveyInvestigatorAreaPriceMapper.deleteByInfo(map);
                                return new ApiResponse(ApiMsgEnum.SUCCESS);
                            }
                        }else {
                            if (surveyTaskInfo != null) {
                                if (taskId == 13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28) { // 医疗调查
                                    Long taskInfoContentId = apiReq.getLong("taskInfoContentId");//任务子类id
                                    String taskInfoContentName = apiReq.getString("taskInfoContentName");//任务子类
                                    SurveyInvestigatorAreaPrice commonAreaPrice = new SurveyInvestigatorAreaPrice();
                                    commonAreaPrice.setTaskId(taskId);
                                    commonAreaPrice.setTaskName(surveyTaskInfo.getName());
                                    commonAreaPrice.setTaskInfoContentId(taskInfoContentId);
                                    commonAreaPrice.setTaskInfoContentName(taskInfoContentName);
                                    commonAreaPrice.setDirectionResultTypeId(directionResultTypeId);
                                    commonAreaPrice.setDirectionResultTypeName(directionResultTypeName);

                                    //具体数据
                                    commonAreaPrice.setDeleteFlag(0);
                                    commonAreaPrice.setPrice(price);
                                    commonAreaPrice.setCityType(apiReq.getInt("cityType"));
                                    commonAreaPrice.setPriceType(priceType);

                                    commonAreaPrice.setAreaId(commonAreas.get(i).getAreaId());
                                    commonAreaPrice.setAreaType(commonAreas.get(i).getCityType().toString());
                                    commonAreaPrice.setAreaName(commonAreas.get(i).getAreaLongname());

                                    surveyInvestigatorAreaPriceMapper.insertSelective(commonAreaPrice);

                                } else {
                                    map = new HashMap<>();
                                    map.put("taskInfoId", taskId);
                                    List<SurveyTaskInfoContent> contentList = surveyTaskInfoContentMapper.list(map);
                                    for (SurveyTaskInfoContent surveyTaskInfoContent : contentList) {
                                        SurveyInvestigatorAreaPrice commonAreaPrice = new SurveyInvestigatorAreaPrice();
                                        commonAreaPrice.setTaskId(taskId);
                                        commonAreaPrice.setTaskName(surveyTaskInfo.getName());
                                        commonAreaPrice.setTaskInfoContentId(surveyTaskInfoContent.getId());
                                        commonAreaPrice.setTaskInfoContentName(surveyTaskInfoContent.getName());
                                        commonAreaPrice.setDirectionResultTypeId(directionResultTypeId);
                                        commonAreaPrice.setDirectionResultTypeName(directionResultTypeName);

                                        //具体数据
                                        commonAreaPrice.setDeleteFlag(0);
                                        commonAreaPrice.setPrice(price);
                                        commonAreaPrice.setCityType(apiReq.getInt("cityType"));
                                        commonAreaPrice.setPriceType(priceType);

                                        commonAreaPrice.setAreaId(commonAreas.get(i).getAreaId());
                                        commonAreaPrice.setAreaType(commonAreas.get(i).getCityType().toString());
                                        commonAreaPrice.setAreaName(commonAreas.get(i).getAreaLongname());

                                        surveyInvestigatorAreaPriceMapper.insertSelective(commonAreaPrice);
                                    }
                                }
                            }
                        }
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }

                Map<String,Object> map = new HashMap<>();
                map.put("priceType",priceType);
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("cityType",apiReq.getLong("cityType"));
                map.put("taskId",taskId);
                map.put("directionResultTypeId",directionResultTypeId);

//                if(taskId==13){
                map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
//                }
                List<SurveyInvestigatorAreaPrice> priceList = surveyInvestigatorAreaPriceMapper.list(map);

                if(priceList.size() > 0){
                    if(price!=null){
                        for (SurveyInvestigatorAreaPrice surveyInvestigatorAreaPrice : priceList) {
                            surveyInvestigatorAreaPrice.setPrice(price);
                            surveyInvestigatorAreaPriceMapper.updateByInfo(surveyInvestigatorAreaPrice);
                        }
                        return new ApiResponse(ApiMsgEnum.SUCCESS);
                    }else{
                        map = new HashMap<>();
                        map.put("priceType",priceType);
                        map.put("areaId",apiReq.getLong("areaId"));
                        map.put("taskId",taskId);
                        map.put("cityType",apiReq.getLong("cityType"));
                        map.put("taskInfoContentId",apiReq.getLong("taskInfoContentId"));
                        map.put("directionResultTypeId",directionResultTypeId);
                        surveyInvestigatorAreaPriceMapper.deleteByInfo(map);
                        return new ApiResponse(ApiMsgEnum.SUCCESS);
                    }
                }else {
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(Long.valueOf(taskId));
                    if(surveyTaskInfo!=null) {
                        if (taskId == 13 || taskId == 25 || taskId == 26 || taskId == 27|| taskId == 28) { // 医疗调查
                            Long taskInfoContentId = apiReq.getLong("taskInfoContentId");//任务子类id
                            String taskInfoContentName = apiReq.getString("taskInfoContentName");//任务子类
                            SurveyInvestigatorAreaPrice commonAreaPrice = new SurveyInvestigatorAreaPrice();
                            commonAreaPrice.setTaskId(taskId);
                            commonAreaPrice.setTaskName(surveyTaskInfo.getName());
                            commonAreaPrice.setTaskInfoContentId(taskInfoContentId);
                            commonAreaPrice.setTaskInfoContentName(taskInfoContentName);
                            commonAreaPrice.setDirectionResultTypeId(directionResultTypeId);
                            commonAreaPrice.setDirectionResultTypeName(directionResultTypeName);

                            //具体数据
                            commonAreaPrice.setDeleteFlag(0);
                            commonAreaPrice.setPrice(price);
                            commonAreaPrice.setCityType(apiReq.getInt("cityType"));
                            commonAreaPrice.setPriceType(priceType);

                            CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                            commonAreaPrice.setAreaId(area.getAreaId());
                            commonAreaPrice.setAreaType(area.getCityType().toString());
                            commonAreaPrice.setAreaName(area.getAreaLongname());
                            surveyInvestigatorAreaPriceMapper.insertSelective(commonAreaPrice);

                        } else {
                            map = new HashMap<>();
                            map.put("taskInfoId", taskId);
                            List<SurveyTaskInfoContent> contentList = surveyTaskInfoContentMapper.list(map);
                            for (SurveyTaskInfoContent surveyTaskInfoContent : contentList) {
                                SurveyInvestigatorAreaPrice commonAreaPrice = new SurveyInvestigatorAreaPrice();
                                commonAreaPrice.setTaskId(taskId);
                                commonAreaPrice.setTaskName(surveyTaskInfo.getName());
                                commonAreaPrice.setTaskInfoContentId(surveyTaskInfoContent.getId());
                                commonAreaPrice.setTaskInfoContentName(surveyTaskInfoContent.getName());
                                commonAreaPrice.setDirectionResultTypeId(directionResultTypeId);
                                commonAreaPrice.setDirectionResultTypeName(directionResultTypeName);

                                //具体数据
                                commonAreaPrice.setDeleteFlag(0);
                                commonAreaPrice.setPrice(price);
                                commonAreaPrice.setCityType(apiReq.getInt("cityType"));
                                commonAreaPrice.setPriceType(priceType);

                                CommonArea area = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
                                commonAreaPrice.setAreaId(area.getAreaId());
                                commonAreaPrice.setAreaType(area.getCityType().toString());
                                commonAreaPrice.setAreaName(area.getAreaLongname());
                                surveyInvestigatorAreaPriceMapper.insertSelective(commonAreaPrice);
                            }
                        }
                    }

                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
            else if("9999".equals(btnCode)){ //删除
                Map<String,Object> map = new HashMap<>();
                map.put("areaId",apiReq.getLong("areaId"));
                map.put("priceType",apiReq.getLong("priceType"));
                surveyInvestigatorAreaPriceMapper.deleteByInfo(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
        }
        //订单
        else if("order".equals(surveyCode)){
            SurveyOrder surveyOrder = surveyOrderMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){

            }else if("1100".equals(btnCode)){
                String logisticsCode = apiReq.getString("logisticsCode");
                surveyOrder.setLogisticsCode(logisticsCode);
                surveyOrder.setOrderState(3); //发货
            }
            surveyOrderMapper.updateByPrimaryKeySelective(surveyOrder);
        }
        //服务区域
        else if("serviceArea".equals(surveyCode)){
            SurveyServiceArea surveyServiceArea = surveyServiceAreaMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyServiceArea.setDeleteFlag(1);//删除
            }
            surveyServiceAreaMapper.updateByPrimaryKeySelective(surveyServiceArea);
        }
        //处罚记录
        else if("punish".equals(surveyCode)){
            SurveyPunish surveyPunish = surveyPunishMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyPunish.setDeleteFlag(1);//删除
            }
            //操作执行
            else if("1200".equals(btnCode)){
                surveyPunish.setIsExec(1);//是否执行
            }
            surveyPunishMapper.updateByPrimaryKeySelective(surveyPunish);
        }

        //提现列表
        else if("cashInfo".equals(surveyCode)){
            SurveyCashInfo surveyCashInfo = surveyCashInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){

            }
            //线上提现
            else if("1200".equals(btnCode)){
                String paySource = apiReq.getString("paySource");
                surveyCashInfo.setPaySource(paySource);
                surveyCashInfo.setCashState(3);//已提现
                surveyCashInfo.setTradeType(1);//交易类型(1:线上交易，2：线下交易)
                surveyCashInfo.setOperateUserId(userInfo.getUserId());
                surveyCashInfo.setOperateUserName(userInfo.getUserName());
                surveyCashInfo.setOperateTime(new Date());

                //同时修改被提现案件的数据
                Map<String,Object> map = new HashMap<>();
                map = new HashMap<>();
                map.put("cashInfoId",apiReq.getLong("id"));
                List<SurveyCashInfoDetail> detailList = surveyCashInfoDetailMapper.list(map);
                for (SurveyCashInfoDetail details : detailList){
                    //释放被提现案件的数据
                    SurveyCashInfoRecord surveyCashInfoRecord = surveyCashInfoRecordMapper.selectByPrimaryKey(details.getSurveyInfoRecordId());
                    surveyCashInfoRecord.setCashState(3);//'提现状态：(1、未提现；2、提现中；3、提现成功; 4、提现失败）'
                    surveyCashInfoRecordMapper.updateByPrimaryKeySelective(surveyCashInfoRecord);
                }
                //同时添加机构的累计金额
//                SurveyInvestigator investigator = surveyInvestigatorMapper.selectByPrimaryKey(surveyCashInfo.getInvestigatorId());
//                SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(investigator.getOrgId());
//                if(franchisee!=null){
//                    franchisee.setAccumulatedAmount(franchisee.getAccumulatedAmount()==null?0D:franchisee.getAccumulatedAmount() + surveyCashInfo.getCaseAmount());
//                }
//                surveyFranchiseeMapper.updateByPrimaryKeySelective(franchisee);
            }
            //线下提现
            else if("1300".equals(btnCode)){
                String img = apiReq.getString("unlineImg");
                if(img!=null){
                    surveyCashInfo.setUnlineImg(img);
                }
                String paySource = apiReq.getString("paySource");
                surveyCashInfo.setPaySource(paySource);
                surveyCashInfo.setCashState(3);//已提现
                surveyCashInfo.setTradeType(2);//交易类型(1:线上交易，2：线下交易)
                surveyCashInfo.setOperateUserId(userInfo.getUserId());
                surveyCashInfo.setOperateUserName(userInfo.getUserName());
                surveyCashInfo.setOperateTime(new Date());

                //同时修改被提现案件的数据
                Map<String,Object> map = new HashMap<>();
                map = new HashMap<>();
                map.put("cashInfoId",apiReq.getLong("id"));
                List<SurveyCashInfoDetail> detailList = surveyCashInfoDetailMapper.list(map);
                for (SurveyCashInfoDetail details : detailList){
                    //释放被提现案件的数据
                    SurveyCashInfoRecord surveyCashInfoRecord = surveyCashInfoRecordMapper.selectByPrimaryKey(details.getSurveyInfoRecordId());
                    surveyCashInfoRecord.setCashState(3);//'提现状态：(1、未提现；2、提现中；3、提现成功; 4、提现失败）'
                    surveyCashInfoRecordMapper.updateByPrimaryKeySelective(surveyCashInfoRecord);
                }
                //同时添加机构的累计金额
//                SurveyInvestigator investigator = surveyInvestigatorMapper.selectByPrimaryKey(surveyCashInfo.getInvestigatorId());
//                SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(investigator.getOrgId());
//                if(franchisee!=null){
//                    franchisee.setAccumulatedAmount(franchisee.getAccumulatedAmount()==null?0D:franchisee.getAccumulatedAmount() + surveyCashInfo.getCaseAmount());
//                }
//                surveyFranchiseeMapper.updateByPrimaryKeySelective(franchisee);
            }
            //驳回原因
            else if("1400".equals(btnCode)) {
                String reason = apiReq.getString("reason");
                surveyCashInfo.setReason(reason);
                surveyCashInfo.setCashState(4);//4、支付失败
                surveyCashInfo.setConfirmAccountState(3);//

                surveyCashInfo.setOperateUserId(userInfo.getUserId());
                surveyCashInfo.setOperateUserName(userInfo.getUserName());
                surveyCashInfo.setOperateTime(new Date());

                //同时释放被提现案件的数据
                Map<String, Object> map = new HashMap<>();
                map = new HashMap<>();
                map.put("cashInfoId", apiReq.getLong("id"));
                List<SurveyCashInfoDetail> detailList = surveyCashInfoDetailMapper.list(map);
                for (SurveyCashInfoDetail details : detailList) {
                    //释放被提现案件的数据
                    SurveyCashInfoRecord surveyCashInfoRecord = surveyCashInfoRecordMapper.selectByPrimaryKey(details.getSurveyInfoRecordId());
                    surveyCashInfoRecord.setCashState(4);//'提现状态：(1、未提现；2、提现中；3、提现成功; 4、提现失败）'
                    surveyCashInfoRecordMapper.updateByPrimaryKeySelective(surveyCashInfoRecord);
                }
            }
            //确认到账
            else if("1500".equals(btnCode)){
                if(surveyCashInfo.getCashState() == 3){
                    surveyCashInfo.setConfirmAccountState(2);//'确认到账状态：1、未到账 2、已到账',
                    surveyCashInfoMapper.updateByPrimaryKeySelective(surveyCashInfo);
                    //修改机构的“累计金额”
                    SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyCashInfo.getFranchiseeId());
                    franchisee.setAccumulatedAmount(franchisee.getAccumulatedAmount()==null?0D:franchisee.getAccumulatedAmount()+surveyCashInfo.getCaseAmount());
                    surveyFranchiseeMapper.updateByPrimaryKeySelective(franchisee);
                }
            }
            surveyCashInfoMapper.updateByPrimaryKeySelective(surveyCashInfo);
        }
        //委托方机构-部门信息
        else if("consignorDepartment".equals(surveyCode)){
            SurveyConsignorDepartment surveyConsignorDepartment = surveyConsignorDepartmentMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyConsignorDepartment.setDeleteFlag(1);
                //同时将部门内的委托人清掉
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("consignorOrgId",surveyConsignorDepartment.getConsignorId());
                paramMap.put("consignorDepartmentId",surveyConsignorDepartment.getId());
                surveyConsignerDepartmentMapper.delete(paramMap);
                //同时将部门内的委托人清掉
//                Map<String,Object> map =  new HashMap<>();
//                map.put("departmentId",apiReq.getLong("id"));
//                map.put("pageIndex",null);//不分页
//                List<SurveyConsigner> surveyConsigners = surveyConsignerMapper.list(map);
//                for (int i = 0; i < surveyConsigners.size(); i++) {
//                    surveyConsigners.get(i).setDepartmentName(null);
//                    surveyConsigners.get(i).setDepartmentId(null);
//                    surveyConsignerMapper.updateByPrimaryKey(surveyConsigners.get(i));
//                }
            }
            //添加人员进部门
            else if("1100".equals(btnCode)){
                String consignerIds = apiReq.getString("consignerIds");
                Long departmentId = apiReq.getLong("id");
                if(consignerIds!=null) {
                    String[] consignerId = consignerIds.split(",");
                    for (String id : consignerId) {
                        SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByPrimaryKey(Long.valueOf(id));
                        Map<String,Object> paramMap = new HashMap<>();
                        paramMap.put("consignorOrgId",surveyConsigner.getEntrustOrgId());
                        paramMap.put("consignorDepartmentId",departmentId);
                        paramMap.put("consignerUserId",surveyConsigner.getId());
                        SurveyConsignerDepartment consignerDepartment = surveyConsignerDepartmentMapper.selectOne(paramMap);
                        if (consignerDepartment == null) {
                            SurveyConsignerDepartment department = new SurveyConsignerDepartment();
                            department.setConsignorOrgId(surveyConsigner.getEntrustOrgId());
                            department.setConsignorDepartmentId(departmentId);
                            department.setConsignerUserId(surveyConsigner.getId());
                            surveyConsignerDepartmentMapper.insert(department);
                        }
//                        if(surveyConsigner!=null){
//                            surveyConsigner.setDepartmentId(surveyConsignorDepartment.getId());
//                            surveyConsigner.setDepartmentName(surveyConsignorDepartment.getName());
//                        }
//                        surveyConsignerMapper.updateByPrimaryKeySelective(surveyConsigner);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            surveyConsignorDepartmentMapper.updateByPrimaryKeySelective(surveyConsignorDepartment);
        }
        //任务类型-方向名称
        else if("taskInfoContent".equals(surveyCode)){
            SurveyTaskInfoContent surveyTaskInfoContent = surveyTaskInfoContentMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyTaskInfoContent.setDeleteFlag(1);
            }
            surveyTaskInfoContentMapper.updateByPrimaryKeySelective(surveyTaskInfoContent);
        }
        //用户信息
        else if("userInfo".equals(surveyCode)){
            if("9998".equals(btnCode)){
                UserLogin userLogin = new UserLogin();
                userLogin.setUserId(apiReq.getLong("id"));
                userLogin.setPassword(MD5Util.MD5Encode("123456@Qaz",null));

                int ret = userLoginMapper.updateByPrimaryKeySelective(userLogin);
                if(ret > 0){
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }
        //提现数据
        else if("cashInfoRecord".equals(surveyCode)) {
            SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(userId);
            if ("1100".equals(btnCode)) {

                //当前登录人的银行卡信息
                SurveyBankCard surveyBankCard = surveyBankCardMapper.selectByUserId(userId);
                if (surveyBankCard == null) {
                    //提示未绑定银行卡
                    return new ApiResponse(ApiMsgEnum.SurveyBankCardError);
                }

                String idList = apiReq.getString("idList");
                //先落地提现记录表
                SurveyCashInfo cashInfo = new SurveyCashInfo();
                List<SurveyCashInfoDetail> detailList = new ArrayList<>();
                if (idList != null) {
                    String[] ids = idList.split(",");
                    for (String id : ids) {
                        SurveyCashInfoRecord surveyCashInfoRecord = surveyCashInfoRecordMapper.selectByPrimaryKey(Long.parseLong(id));
                        if (surveyCashInfoRecord.getCashState() == 2 || surveyCashInfoRecord.getCashState() == 3) {
                            return new ApiResponse(ApiMsgEnum.SurveyCashInfoErrorByRepeat);
                        }
                    }
                    cashInfo.setCashInfoCode(SerialNumberUtil.nextCaseCode("WD"));
                    cashInfo.setFranchiseeId(investigator.getOrgId());
                    cashInfo.setFranchiseeName(investigator.getOrgName());
                    cashInfo.setApplyUserId(userInfo.getUserId());
                    cashInfo.setApplyUserName(userInfo.getUserName());
                    cashInfo.setApplyUserTel(userInfo.getUserTel());
                    cashInfo.setApplyTime(new Date());
                    cashInfo.setInvestigatorId(investigator.getId());
                    cashInfo.setInvestigatorName(investigator.getRealName());

                    cashInfo.setCashState(2);//提现状态：(0、未结算；1、待提现；2、提现中；3、已提现)
                    cashInfo.setSurveyBankCardId(surveyBankCard.getId());
                    cashInfo.setCreateTime(new Date());
                    cashInfo.setDeleteFlag(0);
                    surveyCashInfoMapper.insertSelective(cashInfo);

                    String cashInfoName = "";
                    Double caseAmount = 0D;//提现金额
                    int i = 0;
                    for (String id : ids) {
                        SurveyCashInfoRecord surveyCashInfoRecord = surveyCashInfoRecordMapper.selectByPrimaryKey(Long.parseLong(id));
                        if (surveyCashInfoRecord != null) {
                            //同时生成提现记录明细
                            if (surveyCashInfoRecord.getSurveyTaskMoney() != null && surveyCashInfoRecord.getSurveyTaskMoney() > 0) {
                                SurveyCashInfoDetail cashInfoDetail = addCaseInfoDetail(cashInfo, surveyCashInfoRecord);
                                detailList.add(cashInfoDetail);
                            }
                            caseAmount += surveyCashInfoRecord.getSurveyTaskMoney(); //提现金额汇总
                            surveyCashInfoRecord.setCashState(2);//提现状态：(1、未提现；2、提现中；3、提现成功; 4、提现失败）
                            surveyCashInfoRecordMapper.updateByPrimaryKeySelective(surveyCashInfoRecord);

                            //提现名称
                            cashInfoName = surveyCashInfoRecord.getSurveyCaseName();
                            if (i == ids.length - 1) {
                                cashInfoName += "等";
                            } else {
                                cashInfoName += ",";
                            }
                            i++;
                        }
                    }
                    cashInfo.setCaseAmount(DecimalUtil.twoDecimalTOFourFromFive(caseAmount)); //提现金额
                    cashInfo.setFeeRatio(5D);   //手续费比例
                    cashInfo.setFeeAmount(DecimalUtil.twoDecimalTOFourFromFive(caseAmount * 5 / 100));    //手续费金额
                    cashInfo.setRealAmount(DecimalUtil.twoDecimalTOFourFromFive(caseAmount - caseAmount * 5 / 100)); //实际提现金额
                    cashInfo.setCashInfoName(cashInfoName + ids.length + "笔案件"); //提现名称
                    surveyCashInfoMapper.updateByPrimaryKeySelective(cashInfo);
                }

                SurveyCashInfoDto cashInfoDto = new SurveyCashInfoDto();
                cashInfoDto.setSurveyCashInfo(cashInfo);
                cashInfoDto.setSurveyCashInfoDetails(detailList);
                cashInfoDto.setSurveyBankCard(surveyBankCard);
                return new ApiResponse(ApiMsgEnum.SUCCESS,detailList==null?0:detailList.size(),cashInfoDto);

            }
            //提现记录，确认到账
            else if ("1200".equals(btnCode)){

                Map<String,Object> map = new HashMap<>();
                map.put("franchiseeId",investigator.getOrgId());
                map.put("confirmAccountState",1);
                List<SurveyCashInfo> surveyCashInfo = surveyCashInfoMapper.selectByDate(map);
                if(surveyCashInfo!=null && surveyCashInfo.size() > 0){
                    if(surveyCashInfo.get(0).getCashState()==3) {
                        surveyCashInfo.get(0).setConfirmAccountState(2);//'确认到账状态：1、未到账 2、已到账',
                        surveyCashInfoMapper.updateByPrimaryKeySelective(surveyCashInfo.get(0));

                        //修改机构的“累计金额”
                        SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyCashInfo.get(0).getFranchiseeId());
                        franchisee.setAccumulatedAmount(franchisee.getAccumulatedAmount() == null ? 0D : franchisee.getAccumulatedAmount() + surveyCashInfo.get(0).getCaseAmount());
                        surveyFranchiseeMapper.updateByPrimaryKeySelective(franchisee);
                    }
                }
            }
        }
        //开票-开票公司
        else if("applyCorporation".equals(surveyCode)){
            BillingApplyCorporation applyCorporation = billingApplyCorporationMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                applyCorporation.setDeleteFlag(1);
            }
            billingApplyCorporationMapper.updateByPrimaryKeySelective(applyCorporation);
        }
        //开票-开票公司对应产品
        else if("applyCorporationEnum".equals(surveyCode)){

            BillingApplyCorporationEnum corporationEnum = billingApplyCorporationEnumMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9998".equals(btnCode)){
                //先删除该产品下对应的开票项目
                Map<String,Object> map = new HashMap<>();
                map.put("billingCorporationEnumId",corporationEnum.getId());
                List<BillingApplyEnumItem> list = billingApplyEnumItemMapper.list(map);
                for (BillingApplyEnumItem billingApplyEnumItem : list) {
                    billingApplyEnumItemMapper.deleteByPrimaryKey(billingApplyEnumItem.getId());
                }
                //再删除该产品
                billingApplyCorporationEnumMapper.deleteByPrimaryKey(apiReq.getLong("id"));
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            billingApplyCorporationEnumMapper.updateByPrimaryKeySelective(corporationEnum);
        }
        //任务类型-方向名称
        else if("applyProductType".equals(surveyCode)){
            BillingApplyProductType applyProductType = billingApplyProductTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                applyProductType.setDeleteFlag(1);
            }
            billingApplyProductTypeMapper.updateByPrimaryKeySelective(applyProductType);
        }
        //开票-开票产品
        else if("billingEnum".equals(surveyCode)){
            CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                commonEnumMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            }
        }
        //开票-开票公司
        else if("billingItem".equals(surveyCode)){
            CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                commonEnumMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            }
        }
        //报告模板对应的委托方机构
        else if("consignorModel".equals(surveyCode)){
            SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyConsignorModelMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            }
        }
        //狄大人 -- 通知中心
        else if("message".equals(surveyCode)){
            SurveyMessage surveyMessage = surveyMessageMapper.selectByPrimaryKey(apiReq.getLong("id"));
            //单条更新已读
            if("1000".equals(btnCode)){
                surveyMessage.setIsRead(1);
                surveyMessage.setToTime(new Date());
            }
            //批量更新已读
            else if("2000".equals(btnCode)){
                Map<String,Object> map = new HashMap<>();
                map.put("toUserId",apiReq.getLong("toUserId"));
                map.put("msgType",4);
                surveyMessageMapper.updateByToUserIdAndType(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            surveyMessageMapper.updateByPrimaryKeySelective(surveyMessage);
        }
        //委托机构 -- 开票主体
        else if("consignorBillSubject".equals(surveyCode)){
            BillingApplyCompany billingApplyCompany = billingApplyCompanyMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                billingApplyCompany.setDeleteFlag(1);
            }
            billingApplyCompanyMapper.updateByPrimaryKey(billingApplyCompany);

//            SurveyConsignorBillSubject surveyConsignorBillSubject = surveyConsignorBillSubjectMapper.selectByPrimaryKey(apiReq.getLong("id"));
//            if("9999".equals(btnCode)){
//                surveyConsignorBillSubject.setDeleteFlag(1);
//            }
//            surveyConsignorBillSubjectMapper.updateByPrimaryKeySelective(surveyConsignorBillSubject);
        }
        //领域类型- 关联 任务类型
        else if("businessTaskType".equals(surveyCode)){
            String businessTypeId = apiReq.getString("businessTypeId");
            if("9999".equals(btnCode)){//删除关联关系
                String taskInfoId = apiReq.getString("taskInfoId");
                HashMap<String,Object> map  = new HashMap<>();
                map.put("businessTypeId",businessTypeId);
                map.put("taskInfoId",taskInfoId);
                List<SurveyBusinessTaskType> surveyBusinessTaskTypes = surveyBusinessTaskTypeMapper.list(map);
                if(surveyBusinessTaskTypes!=null && surveyBusinessTaskTypes.size()>0) {
                    for (SurveyBusinessTaskType surveyBusinessTaskType : surveyBusinessTaskTypes) {
                        surveyBusinessTaskTypeMapper.deleteByPrimaryKey(surveyBusinessTaskType.getId());
                    }
                }

            }
            else if("2100".equals(btnCode)){//保存关联关系
                HashMap<String,Object> map  = new HashMap<>();
                map.put("businessTypeId",businessTypeId);
                List<SurveyBusinessTaskType> surveyBusinessTaskTypes = surveyBusinessTaskTypeMapper.list(map);
                if(surveyBusinessTaskTypes!=null && surveyBusinessTaskTypes.size()>0){
                    for (SurveyBusinessTaskType businessTaskType : surveyBusinessTaskTypes) {
                        surveyBusinessTaskTypeMapper.deleteByPrimaryKey(businessTaskType.getId());
                    }
                }
                String taskInfoIds = apiReq.getString("taskInfoIds");
                String[] ids = taskInfoIds.split(",");
                for (String id : ids) {
                    SurveyBusinessTaskType businessTaskType =  new SurveyBusinessTaskType();

                    SurveyBusinessType businessType =  surveyBusinessTypeMapper.selectByPrimaryKey(Long.valueOf(businessTypeId));
                    if(businessType != null){
                        businessTaskType.setBusinessTypeId(Long.valueOf(businessTypeId));
                        businessTaskType.setBusinessTypeName(businessType.getName());
                    }
                    SurveyTaskInfo taskInfo = surveyTaskInfoMapper.selectByPrimaryKey(Long.valueOf(id));
                    if(taskInfo!=null){
                        businessTaskType.setTaskInfoId(Long.valueOf(id));
                        businessTaskType.setTaskInfoName(taskInfo.getName());
                    }
                    surveyBusinessTaskTypeMapper.insert(businessTaskType);
                }
            }
        }
        //方向结果类型
        else if("directionResultType".equals(surveyCode)){
            SurveyDirectionResultType surveyDirectionResultType = surveyDirectionResultTypeMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyDirectionResultType.setDeleteFlag(1);
            }
            surveyDirectionResultTypeMapper.updateByPrimaryKeySelective(surveyDirectionResultType);
        }
        //任务子列- 关联 方向结果类型
        else if("taskDirectionResult".equals(surveyCode)){
            String taskInfoContentId = apiReq.getString("taskInfoContentId");
            if("9999".equals(btnCode)){//删除关联关系
                String directionResultTypeId = apiReq.getString("directionResultTypeId");
                HashMap<String,Object> map  = new HashMap<>();
                map.put("taskInfoContentId",taskInfoContentId);
                map.put("directionResultTypeId",directionResultTypeId);
                List<SurveyTaskDirectionResult> surveyTaskDirectionResults = surveyTaskDirectionResultMapper.list(map);
                if(surveyTaskDirectionResults!=null && surveyTaskDirectionResults.size()>0) {
                    for (SurveyTaskDirectionResult surveyTaskDirectionResult : surveyTaskDirectionResults) {
                        surveyTaskDirectionResultMapper.deleteByPrimaryKey(surveyTaskDirectionResult.getId());
                    }
                }

            }
            else if("2000".equals(btnCode)){//保存关联关系
                HashMap<String,Object> map  = new HashMap<>();
                map.put("taskInfoContentId",taskInfoContentId);
                List<SurveyTaskDirectionResult> surveyTaskDirectionResults = surveyTaskDirectionResultMapper.list(map);
                if(surveyTaskDirectionResults!=null && surveyTaskDirectionResults.size()>0) {
                    for (SurveyTaskDirectionResult surveyTaskDirectionResult : surveyTaskDirectionResults) {
                        surveyTaskDirectionResultMapper.deleteByPrimaryKey(surveyTaskDirectionResult.getId());
                    }
                }

                List<SurveyTaskDirectionResult> surveyTaskDirectionResultList = JSONArray.parseArray(apiReq.getString("scoreList"), SurveyTaskDirectionResult.class);
                if(surveyTaskDirectionResultList!=null){
                    for (SurveyTaskDirectionResult surveyTaskDirectionResult : surveyTaskDirectionResultList) {
                        Long directionResultTypeId = surveyTaskDirectionResult.getDirectionResultTypeId();
                        Double score = surveyTaskDirectionResult.getScore();
                        SurveyTaskInfoContent surveyTaskInfoContent = surveyTaskInfoContentMapper.selectByPrimaryKey(Long.valueOf(taskInfoContentId));
                        surveyTaskDirectionResult.setTaskInfoContentId(Long.valueOf(taskInfoContentId));
                        if(surveyTaskInfoContent!=null){
                            surveyTaskDirectionResult.setTaskInfoContentName(surveyTaskInfoContent.getName());
                        }

                        SurveyDirectionResultType surveyDirectionResultType = surveyDirectionResultTypeMapper.selectByPrimaryKey(directionResultTypeId);
                        surveyTaskDirectionResult.setDirectionResultTypeId(directionResultTypeId);
                        if(surveyDirectionResultType != null){
                            surveyTaskDirectionResult.setDirectionResultTypeName(surveyDirectionResultType.getName());
                        }
                        surveyTaskDirectionResult.setScore(score);
                        surveyTaskDirectionResultMapper.insert(surveyTaskDirectionResult);
                    }
                }
            }
        }
        //价格模板
        else if("priceModel".equals(surveyCode)){

            Long id = apiReq.getLong("id");
            int type = apiReq.getInt("type");

            SurveyPriceModel surveyPriceModel = surveyPriceModelMapper.selectByPrimaryKey(id);
            if("9999".equals(btnCode)){
                surveyPriceModel.setDeleteFlag(1);
                surveyPriceModelMapper.updateByPrimaryKeySelective(surveyPriceModel);
            }else if("2000".equals(btnCode)){//委托机构
                //先删除记录，再保存全新数据
                Map<String,Object> map = new HashMap<>();
                map.put("priceModelId",id);
                map.put("type",type);
                surveyPriceModelOrgMapper.deleteByInfo(map);

                //保存数据
                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        //如果该机构在其他模板中，需将其删除，加入此类数据中
                        map = new HashMap<>();
                        map.put("orgId",ro);
                        map.put("type",type);
                        SurveyPriceModelOrg oldInfo = surveyPriceModelOrgMapper.selectOneByInfo(map);
                        if(oldInfo != null){
                            surveyPriceModelOrgMapper.deleteByPrimaryKey(oldInfo.getId());
                        }

                        SurveyPriceModelOrg surveyPriceModelOrg =new SurveyPriceModelOrg();
                        //机构信息
                        SurveyConsignor consignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if(consignor!=null){
                            surveyPriceModelOrg.setOrgId(consignor.getId());
                            surveyPriceModelOrg.setOrgName(consignor.getCompany());
                        }
                        surveyPriceModelOrg.setPriceModelId(surveyPriceModel.getId());
                        surveyPriceModelOrg.setPriceModelName(surveyPriceModel.getName());
                        surveyPriceModelOrg.setType(type);
                        surveyPriceModelOrgMapper.insertSelective(surveyPriceModelOrg);
                    }
                }
            }else if("2100".equals(btnCode)){//调查机构
                //先删除记录，再保存全新数据
                Map<String,Object> map = new HashMap<>();
                map.put("priceModelId",id);
                map.put("type",type);
                surveyPriceModelOrgMapper.deleteByInfo(map);

                //保存数据
                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        //如果该机构在其他模板中，需将其删除，加入此类数据中
                        map = new HashMap<>();
                        map.put("orgId",ro);
                        map.put("type",type);
                        SurveyPriceModelOrg oldInfo = surveyPriceModelOrgMapper.selectOneByInfo(map);
                        if(oldInfo != null){
                            surveyPriceModelOrgMapper.deleteByPrimaryKey(oldInfo.getId());
                        }

                        SurveyPriceModelOrg surveyPriceModelOrg =new SurveyPriceModelOrg();
                        //机构信息
                        SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if(franchisee!=null){
                            surveyPriceModelOrg.setOrgId(franchisee.getId());
                            surveyPriceModelOrg.setOrgName(franchisee.getName());
                        }
                        surveyPriceModelOrg.setPriceModelId(surveyPriceModel.getId());
                        surveyPriceModelOrg.setPriceModelName(surveyPriceModel.getName());
                        surveyPriceModelOrg.setType(type);
                        surveyPriceModelOrgMapper.insertSelective(surveyPriceModelOrg);
                    }
                }
            } else if ("save-survey-entrust".equals(btnCode)){
                Integer surveyEntrustIsRate = apiReq.getInt("surveyEntrustIsRate");
                Double surveyEntrustRate = apiReq.getDouble("surveyEntrustRate");
                if (surveyEntrustIsRate == 1){
                    surveyPriceModel.setSurveyEntrustIsRate(surveyEntrustIsRate);
                    surveyPriceModel.setSurveyEntrustRate(surveyEntrustRate);
                }else{
                    surveyPriceModel.setSurveyEntrustIsRate(0);
                    surveyPriceModel.setSurveyEntrustRate(0D);
                }
                surveyPriceModelMapper.updateByPrimaryKeySelective(surveyPriceModel);
            }
        }
        else if("surveyPrice".equals(surveyCode)){
            Double price = apiReq.getDouble("price");
            Long taskId = apiReq.getLong("taskId");//任务类型id
            String taskName = apiReq.getString("taskName");
            Long taskInfoContentId =  apiReq.getLong("taskInfoContentId");
            String taskInfoContentName = apiReq.getString("taskInfoContentName");
            Long directionResultTypeId = apiReq.getLong("directionResultTypeId");//方向结果id
            String directionResultTypeName = apiReq.getString("directionResultTypeName");
            Long areaCategoriesId= apiReq.getLong("areaCategoriesId");
            String areaCategoriesName = apiReq.getString("areaCategoriesName");

            int type = apiReq.getInt("type");//保司、互助

            if(type == 2 || (type == 1 && (taskId == 13 || taskId >= 9999))){ // 互助类  2、保险类-医疗调查,深度案件 特殊处理
                Map<String,Object> map = new HashMap<>();
                map.put("areaCategoriesId",areaCategoriesId);
                map.put("taskId",taskId);
                map.put("taskInfoContentId",taskInfoContentId);
                map.put("directionResultTypeId",directionResultTypeId);

                SurveyPrice surveyPrice = surveyPriceMapper.selectOneByInfo(map);
                if(surveyPrice !=null){
                    surveyPrice.setTaskPrice(price);
                    surveyPrice.setUpdateBy(userId);
                    surveyPrice.setUpdateTime(new Date());
                    surveyPriceMapper.updateByPrimaryKey(surveyPrice);
                }else{
                    surveyPrice = new SurveyPrice();
                    surveyPrice.setAreaCategoriesId(areaCategoriesId);
                    surveyPrice.setAreaCategoriesName(areaCategoriesName);
                    surveyPrice.setTaskId(taskId);
                    surveyPrice.setTaskName(taskName);
                    surveyPrice.setTaskInfoContentId(taskInfoContentId);
                    surveyPrice.setTaskInfoContentName(taskInfoContentName);
                    surveyPrice.setDirectionResultTypeId(directionResultTypeId);
                    surveyPrice.setDirectionResultTypeName(directionResultTypeName);
                    surveyPrice.setCreateTime(new Date());
                    surveyPrice.setCreateBy(userId);
                    surveyPrice.setCreateByName(userInfo.getUserName());
                    surveyPrice.setDeleteFlag(0);
                    surveyPrice.setTaskPrice(price);
                    surveyPriceMapper.insert(surveyPrice);
                }
            }
            if(type == 1){//保险类-医疗调查,深度案件 特殊处理
                if(taskId != 13 && taskId < 9999){
                    Map<String,Object> map = new HashMap<>();
                    map.put("taskInfoId",taskId);
                    List<SurveyTaskInfoContent> contentList = surveyTaskInfoContentMapper.list(map);
                    for (SurveyTaskInfoContent surveyTaskInfoContent : contentList) {
                        map = new HashMap<>();
                        map.put("areaCategoriesId",areaCategoriesId);
                        map.put("taskId",taskId);
                        map.put("taskInfoContentId",surveyTaskInfoContent.getId());
                        map.put("directionResultTypeId",directionResultTypeId);
                        SurveyPrice surveyPrice = surveyPriceMapper.selectOneByInfo(map);
                        if(surveyPrice !=null){
                            surveyPrice.setTaskPrice(price);
                            surveyPrice.setUpdateBy(userId);
                            surveyPrice.setUpdateTime(new Date());
                            surveyPriceMapper.updateByPrimaryKey(surveyPrice);
                        }else{
                            surveyPrice = new SurveyPrice();
                            surveyPrice.setAreaCategoriesId(areaCategoriesId);
                            surveyPrice.setAreaCategoriesName(areaCategoriesName);
                            surveyPrice.setTaskId(taskId);
                            surveyPrice.setTaskName(taskName);
                            surveyPrice.setTaskInfoContentId(surveyTaskInfoContent.getId());
                            surveyPrice.setTaskInfoContentName(surveyTaskInfoContent.getName());
                            surveyPrice.setDirectionResultTypeId(directionResultTypeId);
                            surveyPrice.setDirectionResultTypeName(directionResultTypeName);
                            surveyPrice.setCreateTime(new Date());
                            surveyPrice.setCreateBy(userId);
                            surveyPrice.setCreateByName(userInfo.getUserName());
                            surveyPrice.setDeleteFlag(0);
                            surveyPrice.setTaskPrice(price);
                            surveyPriceMapper.insert(surveyPrice);
                        }
                    }
                }
            }
        }
        else if("surveyEfficiency".equals(surveyCode)){
            String days = apiReq.getString("days");
            Long modelId = apiReq.getLong("modelId");//
            Long serviceId = apiReq.getLong("serviceId");
            String subServiceId = apiReq.getString("subServiceId");
            String subServiceName = apiReq.getString("subServiceName");
            Long areaCategoriesId= apiReq.getLong("areaCategoriesId");
            Map<String,Object> map = new HashMap<>();
            map.put("efficiencyModelId",modelId);
            map.put("areaCategoriesId",areaCategoriesId);
            map.put("serviceId",serviceId);
            map.put("subServiceId",StringUtils.isEmpty(subServiceId) ? null : subServiceId);
            SurveyConsignorEfficiencyModelInfo info = surveyConsignorEfficiencyModelInfoMapper.selectOne(map);
            if(info!=null){
                if(StringUtils.isEmpty(days)){
                    surveyConsignorEfficiencyModelInfoMapper.deleteByPrimaryKey(info.getId());
                }else{
                    info.setDays(Integer.parseInt(days));
                    surveyConsignorEfficiencyModelInfoMapper.updateByPrimaryKey(info);
                }
            }else{
                if(!StringUtils.isEmpty(days)) {
                    info = new SurveyConsignorEfficiencyModelInfo();
                    //模板信息
                    SurveyConsignorEfficiencyModel model = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(modelId);
                    info.setEfficiencyModelId(model.getId());
                    info.setEfficiencyModelName(model.getName());
                    if (StringUtils.isEmpty(subServiceId)){
                        //业务类型数据
                        SurveyServiceType serviceType = surveyServiceTypeMapper.selectByPrimaryKey(serviceId);
                        info.setServiceId(serviceType.getId());
                        info.setServiceName(serviceType.getName());
                    }else {
                        info.setSubServiceId(Long.parseLong(subServiceId));
                        info.setSubServiceName(subServiceName);
                    }

                    info.setCityType(null);
                    info.setAreaCategoriesId(areaCategoriesId);
                    info.setDays(Integer.parseInt(days));
                    surveyConsignorEfficiencyModelInfoMapper.insert(info);
                }
            }
        }
        else if("consignorEfficiencyModel".equals(surveyCode)){
            if("1000".equals(btnCode)){
                Long efficiencyModelId = apiReq.getLong("efficiencyModelId");
                Long serviceId = apiReq.getLong("serviceId");
                Long subServiceId = apiReq.getLong("subServiceId");
                String subServiceName = apiReq.getString("subServiceName");
                Long areaCategoriesId = apiReq.getLong("areaCategoriesId");
                String days= apiReq.getString("days");
                Map<String,Object> map = new HashMap<>();
                map.put("efficiencyModelId",efficiencyModelId);
                map.put("areaCategoriesId",areaCategoriesId);
                map.put("serviceId",serviceId);
                map.put("subServiceId",subServiceId);
                SurveyConsignorEfficiencyModelInfo info = surveyConsignorEfficiencyModelInfoMapper.selectOne(map);
                if(info!=null){
                    if(StringUtils.isEmpty(days)){
                        surveyConsignorEfficiencyModelInfoMapper.deleteByPrimaryKey(info.getId());
                    }else{
                        info.setDays(Integer.parseInt(days));
                        surveyConsignorEfficiencyModelInfoMapper.updateByPrimaryKey(info);
                    }

                }else{
                    if(!StringUtils.isEmpty(days)) {
                        info = new SurveyConsignorEfficiencyModelInfo();
                        //模板信息
                        SurveyConsignorEfficiencyModel model = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(efficiencyModelId);
                        info.setEfficiencyModelId(model.getId());
                        info.setEfficiencyModelName(model.getName());
                        if (subServiceId == null){
                            //业务类型数据
                            SurveyServiceType serviceType = surveyServiceTypeMapper.selectByPrimaryKey(serviceId);
                            info.setServiceId(serviceType.getId());
                            info.setServiceName(serviceType.getName());
                        }else {
                            info.setSubServiceId(subServiceId);
                            info.setSubServiceName(subServiceName);
                        }

                        info.setCityType(null);
                        info.setAreaCategoriesId(areaCategoriesId);
                        info.setDays(Integer.parseInt(days));
                        surveyConsignorEfficiencyModelInfoMapper.insert(info);
                    }
                }
            }
            //删除
            else if("9999".equals(btnCode)){
                Long id = apiReq.getLong("id");
                //删除名下价格
                surveyConsignorEfficiencyModelInfoMapper.deleteByModelId(id);
                //删除名下委托机构
                surveyConsignorEfficiencyModelOrgMapper.deleteByModelId(id);
                //删除模板信息
                surveyConsignorEfficiencyModelMapper.deleteByPrimaryKey(id);
            }
            //设置委托方
            else if("2000".equals(btnCode)){
                Long efficiencyModelId = apiReq.getLong("efficiencyModelId");
                SurveyConsignorEfficiencyModel model = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(efficiencyModelId);
                //先删除记录，再保存全新数据
                surveyConsignorEfficiencyModelOrgMapper.deleteByModelId(efficiencyModelId);

                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        //如果该机构在其他模板中，需将其删除，加入此类数据中
                        Map<String,Object> map = new HashMap<>();
                        map.put("orgId",ro);
                        SurveyConsignorEfficiencyModelOrg oldInfo = surveyConsignorEfficiencyModelOrgMapper.selectOne(Long.parseLong(ro));
                        if(oldInfo != null){
                            surveyConsignorEfficiencyModelOrgMapper.deleteByPrimaryKey(oldInfo.getId());
                        }

                        SurveyConsignorEfficiencyModelOrg orgInfo = new SurveyConsignorEfficiencyModelOrg();
                        orgInfo.setEfficiencyModelId(model.getId());
                        orgInfo.setEfficiencyModelName(model.getName());

                        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(Long.parseLong(ro));
                        if(surveyConsignor != null){
                            orgInfo.setOrgId(surveyConsignor.getId());
                            orgInfo.setOrgName(surveyConsignor.getName());
                        }
                        surveyConsignorEfficiencyModelOrgMapper.insert(orgInfo);
                    }
                }

            }
        }
        else if("channelModel".equals(surveyCode)){
            if("1000".equals(btnCode)){
                Long modelId = apiReq.getLong("modelId");
                Long areaCategoriesId = apiReq.getLong("areaCategoriesId");
                Long taskId = apiReq.getLong("taskId");
                Double price = apiReq.getDouble("price");
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("modelId",modelId);
                paramMap.put("areaCategoriesId",areaCategoriesId);
                paramMap.put("taskId",taskId);
                //先删后插（除三种特殊合并插入9行，其他都插入一行）
                if (taskId == 9 || taskId == 14 || taskId == 17){
                    surveyChannelModelInfoMapper.deleteByParam(paramMap);
                    paramMap.put("price",price);
                    surveyChannelModelInfoMapper.insertRecords(paramMap);
                }else{
                    Long taskContentId = apiReq.getLong("taskContentId");
                    Long taskContentResultId = apiReq.getLong("taskContentResultId");
                    paramMap.put("taskContentId",taskContentId);
                    paramMap.put("taskContentResultId",taskContentResultId);
                    surveyChannelModelInfoMapper.deleteByParam(paramMap);

                    SurveyChannelModelInfo surveyChannelModelInfo = new SurveyChannelModelInfo();
                    surveyChannelModelInfo.setModelId(modelId);
                    surveyChannelModelInfo.setAreaCategoriesId(areaCategoriesId);
                    surveyChannelModelInfo.setTaskId(taskId);
                    surveyChannelModelInfo.setTaskContentId(taskContentId);
                    surveyChannelModelInfo.setTaskContentResultId(taskContentResultId);
                    surveyChannelModelInfo.setPrice(price);
                    surveyChannelModelInfoMapper.insert(surveyChannelModelInfo);
                }
            }
            //删除
            else if("9999".equals(btnCode)){
                Long id = apiReq.getLong("id");
                SurveyChannelModel surveyChannelModel = surveyChannelModelMapper.selectByPrimaryKey(id);
                surveyChannelModel.setDeleteFlag(1);
                surveyChannelModelMapper.updateByPrimaryKey(surveyChannelModel);
            }
            //设置调查方
            else if("2000".equals(btnCode)){
                Long modelId = apiReq.getLong("modelId");
                SurveyChannelModel surveyChannelModel = surveyChannelModelMapper.selectByPrimaryKey(modelId);
                //先删除记录，再保存全新数据
                surveyChannelModelOrgMapper.deleteByModelId(modelId);

                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        //如果该机构在其他模板中，需将其删除，加入此类数据中
                        Map<String,Object> map = new HashMap<>();
                        map.put("orgId",ro);
                        SurveyChannelModelOrg surveyChannelModelOrg = surveyChannelModelOrgMapper.selectOne(Long.parseLong(ro));
                        if(surveyChannelModelOrg != null){
                            surveyChannelModelOrgMapper.deleteByPrimaryKey(surveyChannelModelOrg.getId());
                        }

                        surveyChannelModelOrg = new SurveyChannelModelOrg();
                        surveyChannelModelOrg.setModelId(surveyChannelModel.getId());
                        surveyChannelModelOrg.setModelName(surveyChannelModel.getName());

                        SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(Long.parseLong(ro));
                        if(surveyFranchisee != null){
                            surveyChannelModelOrg.setOrgId(surveyFranchisee.getId());
                            surveyChannelModelOrg.setOrgName(surveyFranchisee.getName());
                        }
                        surveyChannelModelOrgMapper.insert(surveyChannelModelOrg);
                    }
                }

            }else if ("copy".equals(btnCode)){
                Long modelId = apiReq.getLong("id");
                SurveyChannelModel surveyChannelModel = surveyChannelModelMapper.selectByPrimaryKey(modelId);
                try {
                    SurveyChannelModel record = SurveyChannelModel.class.newInstance();
                    record.setName(surveyChannelModel.getName() + "-副本");
                    record.setCreateBy(userInfo.getUserId());
                    record.setCreateByName(userInfo.getUserName());
                    record.setCreateTime(new Date());
                    record.setDeleteFlag(0);
                    record.setType(1);
                    surveyChannelModelMapper.insert(record);
                    //区域表
                    Map<String,Object> paramMap =  new HashMap<String,Object>();
                    paramMap.put("copyModelId",surveyChannelModel.getId());
                    paramMap.put("modelId",record.getId());
                    paramMap.put("modelName",record.getName());
                    surveyChannelModelAreaMapper.copy(paramMap);
                    //区域城市表
                    surveyChannelModelAreaCityMapper.copy(paramMap);
                    //区域价格表
                    surveyChannelModelInfoMapper.copy(paramMap);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        else if("scoreModel".equals(surveyCode)){
            if("1000".equals(btnCode)){
                Long modelId = apiReq.getLong("modelId");
                Long areaCategoriesId = apiReq.getLong("areaCategoriesId");
                Double price = apiReq.getDouble("price");
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("modelId",modelId);
                paramMap.put("areaCategoriesId",areaCategoriesId);
                //先删后插
                surveyScoreModelInfoMapper.deleteByParam(paramMap);

                SurveyScoreModelInfo surveyScoreModelInfo = new SurveyScoreModelInfo();
                surveyScoreModelInfo.setModelId(modelId);
                surveyScoreModelInfo.setAreaCategoriesId(areaCategoriesId);
                surveyScoreModelInfo.setScoreRate(price);
                surveyScoreModelInfoMapper.insert(surveyScoreModelInfo);
            }
            //删除
            else if("9999".equals(btnCode)){
                Long id = apiReq.getLong("id");
                SurveyScoreModel surveyScoreModel = surveyScoreModelMapper.selectByPrimaryKey(id);
                surveyScoreModel.setDeleteFlag(1);
                surveyScoreModelMapper.updateByPrimaryKey(surveyScoreModel);
            }
            //设置调查方
            else if("2000".equals(btnCode)){
                Long modelId = apiReq.getLong("modelId");
                SurveyScoreModel surveyScoreModel = surveyScoreModelMapper.selectByPrimaryKey(modelId);
                //先删除记录，再保存全新数据
                surveyScoreModelOrgMapper.deleteByModelId(modelId);

                String buss = apiReq.getString("roleIds");
                if(buss!=null) {
                    String[] role = buss.split(",");
                    for (String ro : role) {
                        //如果该机构在其他模板中，需将其删除，加入此类数据中
                        Map<String,Object> map = new HashMap<>();
                        map.put("orgId",ro);
                        SurveyScoreModelOrg surveyScoreModelOrg = surveyScoreModelOrgMapper.selectOne(Long.parseLong(ro));
                        if(surveyScoreModelOrg != null){
                            surveyScoreModelOrgMapper.deleteByPrimaryKey(surveyScoreModelOrg.getId());
                        }

                        surveyScoreModelOrg = new SurveyScoreModelOrg();
                        surveyScoreModelOrg.setModelId(surveyScoreModel.getId());
                        surveyScoreModelOrg.setModelName(surveyScoreModel.getName());

                        SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(Long.parseLong(ro));
                        if(surveyFranchisee != null){
                            surveyScoreModelOrg.setOrgId(surveyFranchisee.getId());
                            surveyScoreModelOrg.setOrgName(surveyFranchisee.getName());
                        }
                        surveyScoreModelOrgMapper.insert(surveyScoreModelOrg);
                    }
                }

            }
        }
        //邮件模板对应的委托方机构
        else if("emailInfoOrg".equals(surveyCode)){
            SurveyEmailInfoOrg surveyEmailInfoOrg = surveyEmailInfoOrgMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if("9999".equals(btnCode)){
                surveyEmailInfoOrgMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            }
        }else if ("copyPrice".equals(surveyCode)){
            Long modelId = apiReq.getLong("modelId");
            SurveyConsignorEfficiencyModel surveyConsignorEfficiencyModel = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(modelId);
            surveyConsignorEfficiencyModelAreaMapper.deleteByModelId(modelId);
            Long selPriceModelId = apiReq.getLong("selPriceModelId");
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("modelId",modelId);
            paramMap.put("modelName",surveyConsignorEfficiencyModel.getName());
            paramMap.put("selPriceModelId",selPriceModelId);
            surveyConsignorEfficiencyModelAreaMapper.copyPirce(paramMap);
            surveyConsignorEfficiencyAreaCityMapper.copyPirce(paramMap);

        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 商品新增或修改时
     */
    private void operateLevelAndRole(ApiRequest apiReq, SurveyProductDto surveyProduct, boolean b) {
        //调查员等级
        String levels = apiReq.getString("levelId");
        //先删除该产品的所有等级信息
        if(b){
            surveyProductLevelMapper.deleteByProductId(surveyProduct.getId());
        }
        if (levels != null) {
            String[] ids = levels.split(",");
            for (String id : ids) {
                SurveyLevel surveyLevel = surveyLevelMapper.selectByPrimaryKey(Long.valueOf(id));
                if(surveyLevel != null){
                    //商品与级别的中间表
                    SurveyProductLevel productLevel = new SurveyProductLevel();
                    productLevel.setLevelId(surveyLevel.getId());
                    productLevel.setLevelName(surveyLevel.getName());
                    productLevel.setProductId(surveyProduct.getId());
                    surveyProductLevelMapper.insertSelective(productLevel);
                }
            }
        }

        //角色
        String roles = apiReq.getString("roleId");
        //先删除该产品的所有角色
        if(b){
            surveyProductRoleMapper.deleteByProductId(surveyProduct.getId());
        }
        if (roles != null) {
            String[] ids = roles.split(",");
            for (String id : ids) {
                BusinessRole businessRole = businessRoleMapper.selectByPrimaryKey(Long.valueOf(id));
                if(businessRole != null){
                    //商品与级别的中间表
                    SurveyProductRole productRole = new SurveyProductRole();
                    productRole.setRoleId(businessRole.getId());
                    productRole.setRoleName(businessRole.getRoleName());
                    productRole.setProductId(surveyProduct.getId());
                    surveyProductRoleMapper.insertSelective(productRole);
                }
            }
        }
    }


    /**
     * 数据 info
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "根据id，查询关联的子表数据", value = "backend-survey-info-by-relation-id", apiParams = { })
    @Override
    public ApiResponse selectInfoByRelationId(ApiRequest apiReq) {

        String surveyCode = apiReq.getString("surveyCode");
        String btnCode = apiReq.getString("btnCode");
        Map<String ,Object> map  =  new HashMap<>();

        Long loginByUserId = getCurrentUserId(apiReq);
        SurveyInvestigator loginBySurveyInvestigator = surveyInvestigatorMapper.selectByUserId(loginByUserId);
        //根据“任务类型id”，查询“任务类型-方向名称”
        if("taskInfo".equals(surveyCode)){
            if("1000".equals(btnCode)){
                //不分页
                if(apiReq.getString("menuType")!=null){
                    map.put("pageIndex", null);
                    map.put("pageSize", null);
                }
                map.put("taskInfoId",apiReq.getString("taskInfoId"));
                List<SurveyTaskInfoContent> surveyTaskInfoContent = surveyTaskInfoContentMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyTaskInfoContent);
            }
        }
        ////根据“委托方机构id”，查询“机构部门”
        else if("consignor".equals(surveyCode)){
            if("1000".equals(btnCode)){
                //不分页
                if(apiReq.getString("menuType")!=null){
                    map.put("pageIndex", null);
                    map.put("pageSize", null);
                }
                Long consignorId = apiReq.getLong("consignorId");
                map.put("consignorId",consignorId);
                //部门信息
                List<SurveyConsignorDepartment> list = surveyConsignorDepartmentMapper.list(map);
                //模板信息
                SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(apiReq.getLong("consignorId"));
                //报告命名规则信息
                SurveyConsignorReportRule surveyConsignorReportRule = surveyConsignorReportRuleMapper.selectByConsignorId(apiReq.getLong("consignorId"));
                Map<String,Object> map1 = new HashMap<>();
                map1.put("departmentList",list);
                map1.put("surveyConsignorModel",surveyConsignorModel);
                map1.put("surveyConsignorReportRule",surveyConsignorReportRule);
                //获取保险公司对应德保险种类信息
                List<String> top3insures = surveyRiskCaseMapper.top3insures(consignorId);
                map1.put("top3insures",top3insures);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,map1);
            }else if ("pact".equals(btnCode)){
                Long id = apiReq.getLong("id");
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(id);
                List<Long> ids = new ArrayList<Long>();
                ids.add(surveyConsignor.getId());
                List<FinancialFile> files = backendFinancialFileApiImpl.getFilesByIds(ids, FinancialFileTableEnumDto.SURVEY_ENTRUST_PACT);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,files);
            }else if ("2000".equals(btnCode)){
                Long orgId = apiReq.getLong("orgId");
                List<CommonArea> commonAreas = commonAreaMapper.selectAllChildrenConsignorAreas(Long.valueOf(apiReq.getString("areaId")),orgId);
                for (CommonArea commonArea : commonAreas) {
                    List<CommonArea> allChildren =  commonAreaMapper.selectAllChildrenConsignorAreas(commonArea.getAreaId(),orgId);
                    int count = allChildren.size();
                    if (count == 0 && !commonArea.getSelected()){
                        commonArea.setShowType(0);
                    }
                    if ((count > 0 && count < commonAreas.size()) || commonArea.getSelected()){
                        commonArea.setShowType(1);
                    }
                    if (count == commonAreas.size()){
                        commonArea.setShowType(2);
                    }
                    commonArea.setChildrens(allChildren);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
            }
        }
        //根据“委托方机构-部门id”，查询“委托人员”
        else if("consignorDepartment".equals(surveyCode)){
            if("1000".equals(btnCode)){
                //不分页
                if(apiReq.getString("menuType")!=null){
                    map.put("pageIndex", null);
                    map.put("pageSize", null);
                }
                map.put("departmentId",apiReq.getString("departmentId"));
//                List<SurveyConsigner> list = surveyConsignerMapper.list(map);


                // 根据委托方部门Id，查询委托人列表
                List<SurveyConsigner> list = new ArrayList<>();
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("consignorDepartmentId",apiReq.getLong("departmentId"));
                List<SurveyConsignerDepartment> consignerDepartments = surveyConsignerDepartmentMapper.list(paramMap);
                for (SurveyConsignerDepartment consignerDepartment : consignerDepartments) {
                    list.add(surveyConsignerMapper.selectByPrimaryKey(consignerDepartment.getConsignerUserId()));
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }
        }
        //根据“委托方机构-部门id”，查询“委托人员”
        else if("riskCase".equals(surveyCode)){
            if("1000".equals(btnCode)){
                //不分页
                if(apiReq.getString("menuType")!=null){
                    apiReq.put("pageIndex", null);
                    apiReq.put("pageSize", null);
                }
                apiReq.put("idNumber",apiReq.getString("idNumber"));
                List<SurveyRiskCaseInfoDto> list = surveyRiskCaseInfoMapper.list(apiReq);
                for (SurveyRiskCaseInfoDto dto : list) {
                    dto.setSurveyRiskCase(surveyRiskCaseMapper.selectByPrimaryKey(dto.getSurveyId()));
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }
        }
        //根据“提现记录id”，查询
        else if("cashInfo".equals(surveyCode)){
            //根据“提现记录id”，查询银行卡信息
            if("1000".equals(btnCode)){
                SurveyCashInfo surveyCashInfo = surveyCashInfoMapper.selectByPrimaryKey(apiReq.getLong("cashInfoId"));
                SurveyBankCard bankCard = surveyBankCardMapper.selectByPrimaryKey(surveyCashInfo.getSurveyBankCardId());
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,bankCard);
            }
            //根据“提现记录id”，查询提现明细
            else if("1100".equals(btnCode)){
                //不分页
                if(apiReq.getString("menuType")!=null){
                    apiReq.put("pageIndex", null);
                    apiReq.put("pageSize", null);
                }
                map = new HashMap<>();
                map.put("cashInfoId",apiReq.getLong("cashInfoId"));
                List<SurveyCashInfoDetail> detailList = surveyCashInfoDetailMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,detailList);
            }
        }
        //提现数据
        else if("cashInfoRecord".equals(surveyCode)){
            //当前登录人的银行卡信息
            if("1000".equals(btnCode)){
                Long userId = apiReq.getLong("operatorId");
                SurveyBankCard bankCard = surveyBankCardMapper.selectByUserId(userId);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,bankCard);
            }
        }
        //根据userId的查询
        else if("investigator".equals(surveyCode)){
            //根据userId的查询
            if("1000".equals(btnCode)){
                Long userId = apiReq.getLong("userId");
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userId);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyInvestigator);
            }
            //分派调查员  -- 获取机构下调查员
            else if("2000".equals(btnCode)){
                map = new HashMap<>();
                Long currentUserId = getCurrentUserId(apiReq);
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if(surveyInvestigator!=null){
                    SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigator.getOrgId());
                    if(surveyFranchisee!=null && surveyFranchisee.getLevel() ==1){ //(如果是省级机构，同时获取名下子机构的)
                        map.put("orgId",surveyFranchisee.getId());
                    }else{
                        map.put("orgId",surveyInvestigator.getOrgId());
                    }
                }
                map.put("roleId",50);
                List<SurveyInvestigator> list = surveyInvestigatorMapper.selectInfoByRole(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }
            //获取本机构，及附属机构下调查员
            else if("3000".equals(btnCode)){
                List<SurveyInvestigator> listAll = new ArrayList<>();
                String surveyOrgIds = apiReq.getString("surveyOrgIds");
                if(surveyOrgIds !=null){
                    String[] ids = surveyOrgIds.split(",");
                    for (String id : ids) {
                        map = new HashMap<>();
                        map.put("orgId",id);
                        map.put("roleId",50);
                        List<SurveyInvestigator> list = surveyInvestigatorMapper.selectInfoByRole(map);
                        listAll.addAll(list);
                    }
                }else{
                    map = new HashMap<>();
                    map.put("roleId",50);
                    listAll = surveyInvestigatorMapper.selectInfoByRole(map);
                }

                return new ApiResponse(ApiMsgEnum.SUCCESS,1,listAll);
            }//分派调查员  -- 获取机构下调查员
            else if("4000".equals(btnCode)){
                map = new HashMap<>();
                Long currentUserId = getCurrentUserId(apiReq);
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if(surveyInvestigator!=null){
                    SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigator.getOrgId());
                    if(surveyFranchisee!=null && surveyFranchisee.getLevel() ==1){ //(如果是省级机构，同时获取名下子机构的)
                        map.put("orgId",surveyFranchisee.getId());
                    }else{
                        map.put("orgId",surveyInvestigator.getOrgId());
                    }
                }
                map.put("roleId",50);
                if("assign-org-list".equals(apiReq.getString("menuCode"))){
                    Map<String,Object> dataRoleMap = getDataRole(currentUserId,"");
                    String dataRoleCode=dataRoleMap.get("dataRoleCode").toString();
                    if("districtManger".equals(dataRoleCode)){
                        if(surveyInvestigator.getSurveyAreaId() != null){
                            map.put("surveyAreaId",surveyInvestigator.getSurveyAreaId());
                        }else{
                            map.put("userId",currentUserId);
                        }
                    }
                }
                List<SurveyInvestigatorDto> list = surveyInvestigatorMapper.selectNumberIndividual(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }else if("5000".equals(btnCode)){
                List<SurveyInvestigator> list = surveyInvestigatorMapper.list(apiReq);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }
        }
        //狄大人-调查审核（改派归属人 改派时，对人员的筛选）
        else if("finalJudgmentUser".equals(surveyCode)){
            //List<UserInfo> list = userInfoMapper.selectListForBelongUser(apiReq);//根据案件的“委托机构”“加盟机构”查询对应的“平台终审人员”
            apiReq.put("roleId",53);//狄大人平台终审角色
            List<UserInfo> list = userInfoMapper.selectListByRoleId(apiReq);//查询所有的“平台终审人员”

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }
        //查询“调查方机构”的价格体系
        else if("franchiseePrice".equals(surveyCode)){
//            List<SurveyFranchiseePrice2Dto> list = surveyFranchiseePriceMapper.selectListByFranchiseeId(apiReq);
            map = new HashMap<>();
            map.put("franchiseeId",apiReq.getLong("franchiseeId"));
            map.put("priceType",apiReq.getInt("priceType"));
            List<SurveyFranchiseePrice2Dto> list = surveyFranchiseePriceMapper.selectFranchiseePrice(map);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }
        //查询“委托方机构”的价格体系
        else if("consignorPrice".equals(surveyCode)){
            Long enturyId = apiReq.getLong("enturyId");
            List<SurveyConsignorPrice2Dto> list = surveyConsignorPriceMapper.selectConsignorPrice(enturyId);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }
        //根据“地区名字”查询地区信息
        else if("commonArea".equals(surveyCode)){
            if("1000".equals(btnCode)) {
                List<CommonAreaDto> list = commonAreaMapper.selectListByName(apiReq.getString("areaName"));
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }
            if("2000".equals(btnCode)) {
                Long areaCategoriesId = apiReq.getLong("priceModelCaId");
                Long modelId = apiReq.getLong("modelId");
                List<CommonArea> commonAreas = commonAreaMapper.selectAllChildren(Long.valueOf(apiReq.getString("areaId")),areaCategoriesId,modelId);
                for (CommonArea commonArea : commonAreas) {
                    List<CommonArea> allChildren =  commonAreaMapper.selectAllChildren(commonArea.getAreaId(),areaCategoriesId,modelId);
                    if (null != areaCategoriesId){//说明是修改
                        List<CommonArea> collect = allChildren.stream().filter(e -> e.getAreaCateGoriesId() != null && e.getAreaCateGoriesId().equals(areaCategoriesId)).collect(Collectors.toList());//属于当前区域选中的数量
                        int count = collect.size();
                        if (count == 0 && !commonArea.getSelected()){
                            commonArea.setShowType(0);
                        }
                        if ((count > 0 && count < commonAreas.size()) || commonArea.getSelected()){
                            commonArea.setShowType(1);
                        }
                        if (count == commonAreas.size()){
                            commonArea.setShowType(2);
                        }
                    }
                    commonArea.setChildrens(allChildren);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
            }
        }
        else if("commonAreaEfficiency".equals(surveyCode)){
            if("1000".equals(btnCode)) {
                List<CommonAreaDto> list = commonAreaMapper.selectListByName(apiReq.getString("areaName"));
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }
            if("2000".equals(btnCode)) {
                Long areaCategoriesId = apiReq.getLong("priceModelCaId");
                Long modelId = apiReq.getLong("modelId");
                List<CommonArea> commonAreas = commonAreaMapper.selectAllChildrenEfficiency(Long.valueOf(apiReq.getString("areaId")),areaCategoriesId,modelId);
                for (CommonArea commonArea : commonAreas) {
                    List<CommonArea> allChildren =  commonAreaMapper.selectAllChildrenEfficiency(commonArea.getAreaId(),areaCategoriesId,modelId);
                    if (null != areaCategoriesId){//说明是修改
                        List<CommonArea> collect = allChildren.stream().filter(e -> e.getAreaCateGoriesId() != null && e.getAreaCateGoriesId().equals(areaCategoriesId)).collect(Collectors.toList());//属于当前区域选中的数量
                        int count = collect.size();
                        if (count == 0 && !commonArea.getSelected()){
                            commonArea.setShowType(0);
                        }
                        if ((count > 0 && count < commonAreas.size()) || commonArea.getSelected()){
                            commonArea.setShowType(1);
                        }
                        if (count == commonAreas.size()){
                            commonArea.setShowType(2);
                        }
                    }
                    commonArea.setChildrens(allChildren);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
            }
        }
        else if("commonAreaChannel".equals(surveyCode)){
            if("1000".equals(btnCode)) {
                List<CommonAreaDto> list = commonAreaMapper.selectListByName(apiReq.getString("areaName"));
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }
            if("2000".equals(btnCode)) {
                Long areaCategoriesId = apiReq.getLong("channelModelCaId");
                Long modelId = apiReq.getLong("modelId");
                List<CommonArea> commonAreas = commonAreaMapper.selectAllChildrenChannel(Long.valueOf(apiReq.getString("areaId")),areaCategoriesId,modelId);
                for (CommonArea commonArea : commonAreas) {
                    List<CommonArea> allChildren =  commonAreaMapper.selectAllChildrenChannel(commonArea.getAreaId(),areaCategoriesId,modelId);
                    if (null != areaCategoriesId){//说明是修改
                        List<CommonArea> collect = allChildren.stream().filter(e -> e.getAreaCateGoriesId() != null && e.getAreaCateGoriesId().equals(areaCategoriesId)).collect(Collectors.toList());//属于当前区域选中的数量
                        int count = collect.size();
                        if (count == 0 && !commonArea.getSelected()){
                            commonArea.setShowType(0);
                        }
                        if ((count > 0 && count < commonAreas.size()) || commonArea.getSelected()){
                            commonArea.setShowType(1);
                        }
                        if (count == commonAreas.size()){
                            commonArea.setShowType(2);
                        }
                    }
                    commonArea.setChildrens(allChildren);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
            }
        }
        else if("commonAreaScore".equals(surveyCode)){
            if("1000".equals(btnCode)) {
                List<CommonAreaDto> list = commonAreaMapper.selectListByName(apiReq.getString("areaName"));
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
            }
            if("2000".equals(btnCode)) {
                Long areaCategoriesId = apiReq.getLong("scoreModelCaId");
                Long modelId = apiReq.getLong("modelId");
                List<CommonArea> commonAreas = commonAreaMapper.selectAllChildrenScore(Long.valueOf(apiReq.getString("areaId")),areaCategoriesId,modelId);
                for (CommonArea commonArea : commonAreas) {
                    List<CommonArea> allChildren =  commonAreaMapper.selectAllChildrenScore(commonArea.getAreaId(),areaCategoriesId,modelId);
                    if (null != areaCategoriesId){//说明是修改
                        List<CommonArea> collect = allChildren.stream().filter(e -> e.getAreaCateGoriesId() != null && e.getAreaCateGoriesId().equals(areaCategoriesId)).collect(Collectors.toList());//属于当前区域选中的数量
                        int count = collect.size();
                        if (count == 0 && !commonArea.getSelected()){
                            commonArea.setShowType(0);
                        }
                        if ((count > 0 && count < commonAreas.size()) || commonArea.getSelected()){
                            commonArea.setShowType(1);
                        }
                        if (count == commonAreas.size()){
                            commonArea.setShowType(2);
                        }
                    }
                    commonArea.setChildrens(allChildren);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
            }
        }
        else if("franchisee".equals(surveyCode)){
            if ("2000".equals(btnCode)){
                Long orgId = apiReq.getLong("orgId");
                List<CommonArea> commonAreas = commonAreaMapper.selectAllChildrenFranchiseeAreas(Long.valueOf(apiReq.getString("areaId")),orgId);
                for (CommonArea commonArea : commonAreas) {
                    List<CommonArea> allChildren =  commonAreaMapper.selectAllChildrenFranchiseeAreas(commonArea.getAreaId(),orgId);
                    int count = allChildren.size();
                    if (count == 0 && !commonArea.getSelected()){
                        commonArea.setShowType(0);
                    }
                    if ((count > 0 && count < commonAreas.size()) || commonArea.getSelected()){
                        commonArea.setShowType(1);
                    }
                    if (count == commonAreas.size()){
                        commonArea.setShowType(2);
                    }
                    commonArea.setChildrens(allChildren);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
            }
        }
        //委托机构 默认价格
        else if("commonAreaPrice".equals(surveyCode)){
//            List<SurveyCommonAreaPrice2Dto> list = surveyCommonAreaPriceMapper.selectList(apiReq);
            List<SurveyCommonAreaPrice2Dto> list = surveyCommonAreaPriceMapper.selectConsignorPrice(apiReq);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }
        //调查机构默认价格
        else if("investigatorAreaPrice".equals(surveyCode)){
//            List<SurveyInvestigatorAreaPrice2Dto> list = surveyInvestigatorAreaPriceMapper.selectList(apiReq);
            List<SurveyInvestigatorAreaPrice2Dto> list = surveyInvestigatorAreaPriceMapper.selectFranchiseePrice(apiReq);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }
        //根据“任务子类id”，查询“方向结果类型”
        else if("taskInfoContent".equals(surveyCode)){
            if("1000".equals(btnCode)){
                //不分页
                if(apiReq.getString("menuType")!=null){
                    map.put("pageIndex", null);
                    map.put("pageSize", null);
                }
                map.put("taskInfoContentId",apiReq.getString("taskInfoContentId"));
                List<SurveyTaskDirectionResult> surveyTaskDirectionResults = surveyTaskDirectionResultMapper.list(map);
                for (SurveyTaskDirectionResult surveyTaskDirectionResult : surveyTaskDirectionResults) {
                    SurveyDirectionResultType directionResultType = surveyDirectionResultTypeMapper.selectByPrimaryKey(surveyTaskDirectionResult.getDirectionResultTypeId());
                    if(directionResultType!=null){
                        surveyTaskDirectionResult.setDirectionResultTypeCode(directionResultType.getCode());
                    }
                }
                Long id=apiReq.getLong("id");
                SurveyInvestigatorCase surveyInvestigatorCase=surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
                SurveyConsignor surveyConsignor=surveyConsignorMapper.selectByPrimaryKey(surveyInvestigatorCase.getEntrustOrgId());
                SurveyFranchisee surveyFranchisee=surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyOrgId());
                if (surveyConsignor.getOrgAttr()==1) {//保险公司
                    return new ApiResponse(ApiMsgEnum.SUCCESS,surveyFranchisee.getInsuranceType(),surveyTaskDirectionResults);
                }else if (surveyConsignor.getOrgAttr()==2) {
                    return new ApiResponse(ApiMsgEnum.SUCCESS,loginBySurveyInvestigator.getType(),surveyTaskDirectionResults);
                }
            }
        }
        //查询“方向结果类型”
        else if("directionResultType".equals(surveyCode)){
            if("1000".equals(btnCode)){//通过code查询
                String info = apiReq.getString("info");
                map.put("code",info);
                SurveyDirectionResultType surveyDirectionResultType = surveyDirectionResultTypeMapper.selectByInfo(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyDirectionResultType);
            }
            else if("1100".equals(btnCode)){//通过name查询
                String info = apiReq.getString("info");
                map.put("name",info);
                SurveyDirectionResultType surveyDirectionResultType = surveyDirectionResultTypeMapper.selectByInfo(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyDirectionResultType);
            }
        }
        //根据子类id与结果id查询分值
        else if("taskInfoScore".equals(surveyCode)){
            Map findMap=new HashMap();
            findMap.put("taskInfoContentId",apiReq.getInt("taskInfoContentId"));
            findMap.put("directionResultTypeId",apiReq.getInt("directionResultTypeId"));
            List<SurveyTaskDirectionResult> list=surveyTaskDirectionResultMapper.list(findMap);
            Long surveyInfoId=apiReq.getLong("surveyInfoId");
            map=new HashMap<>();
            map.put("surveyId",surveyInfoId);
            Long id=apiReq.getLong("id");
            SurveyInvestigatorCase surveyInvestigatorCase=surveyInvestigatorCaseMapper.selectByPrimaryKey(id);
            SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
            SurveyConsignor surveyConsignor=surveyConsignorMapper.selectByPrimaryKey(surveyInvestigatorCase.getEntrustOrgId());
            SurveyFranchisee surveyFranchisee=surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyOrgId());
            if (list.size()> 0){
                for (SurveyTaskDirectionResult surveyTaskDirectionResult : list) {
                    Double scoreRate = 1D;
                    Long areaType = apiReq.getLong("areaType");
                    Long cityId = apiReq.getLong("cityId");
                    Long districtId = apiReq.getLong("districtId");
                    if (areaType !=  null){
                        Long areaId = areaType == 2 || areaType == 3 ? cityId : districtId;
                        //获取分值系数 2021年3月30日
                        SurveyScoreModelOrg surveyScoreModelOrg = surveyScoreModelOrgMapper.selectOne(surveyAssignOrg.getSurveyOrgId());
                        if (surveyScoreModelOrg != null){
                            Map<String,Object> paramMap =  new HashMap<String,Object>();
                            paramMap.put("modelId",surveyScoreModelOrg.getModelId());
                            paramMap.put("areaId",areaId);
                            SurveyScoreModelInfo surveyScoreModelInfo = surveyScoreModelInfoMapper.selectByParam(paramMap);
                            if (surveyScoreModelInfo != null) {
                                scoreRate = surveyScoreModelInfo.getScoreRate() == null ? 0D : surveyScoreModelInfo.getScoreRate();
                            }
                        }
                    }
                    surveyTaskDirectionResult.setScoreRate(scoreRate);
                }
            }
            if (surveyConsignor.getOrgAttr()==1) {//保险公司
                return new ApiResponse(ApiMsgEnum.SUCCESS,surveyFranchisee.getInsuranceType(),list);
            }else if (surveyConsignor.getOrgAttr()==2) {
                return new ApiResponse(ApiMsgEnum.SUCCESS,loginBySurveyInvestigator.getType(),list);
            }
        }
        //领域类型对应“任务类型”
        else if("businessType".equals(surveyCode)){
            if("1000".equals(btnCode)){
                //不分页
                if(apiReq.getString("menuType")!=null){
                    map.put("pageIndex", null);
                    map.put("pageSize", null);
                }
                map.put("businessTypeId",apiReq.getString("businessTypeId"));
                List<SurveyBusinessTaskType> surveyBusinessTaskTypes = surveyBusinessTaskTypeMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyBusinessTaskTypes);
            }
        }
        //价格模板下的区域类别，以及具体区域
        else if("areaCategories".equals(surveyCode)){
            List<SurveyPriceModelAreaCategories> list = surveyPriceModelAreaCategoriesMapper.list(apiReq);
            for (SurveyPriceModelAreaCategories surveyPriceModelAreaCategories : list) {
                map = new HashMap<>();
                map.put("areaCategoriesId",surveyPriceModelAreaCategories.getId());
                List<SurveyAreaCategoriesAreaCity> areaCityList = surveyAreaCategoriesAreaCityMapper.list(map);
                surveyPriceModelAreaCategories.setAreaCitys(areaCityList);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }
        //委托时效模板下的区域类别，以及具体区域
        else if("efficiencyArea".equals(surveyCode)){
            List<SurveyConsignorEfficiencyModelArea> list = surveyConsignorEfficiencyModelAreaMapper.list(apiReq);
            for (SurveyConsignorEfficiencyModelArea areas : list) {
                map = new HashMap<>();
                map.put("areaCategoriesId",areas.getId());
                List<SurveyConsignorEfficiencyAreaCity> areaCities = surveyConsignorEfficiencyAreaCityMapper.list(map);
                areas.setAreaCities(areaCities);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }
        else if("channelModelArea".equals(surveyCode)){
            List<SurveyChannelModelArea> list = surveyChannelModelAreaMapper.list(apiReq);
            for (SurveyChannelModelArea areas : list) {
                map = new HashMap<>();
                map.put("areaCategoriesId",areas.getId());
                List<SurveyChannelModelAreaCity> areaCities = surveyChannelModelAreaCityMapper.list(map);
                areas.setAreaCities(areaCities);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }
        else if("scoreModelArea".equals(surveyCode)){
            List<SurveyScoreModelArea> list = surveyScoreModelAreaMapper.list(apiReq);
            for (SurveyScoreModelArea areas : list) {
                map = new HashMap<>();
                map.put("areaCategoriesId",areas.getId());
                List<SurveyScoreModelAreaCity> areaCities = surveyScoreModelAreaCityMapper.list(map);
                areas.setAreaCities(areaCities);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }

        else if ("getSurveyArea".equals(surveyCode)){//新增案件。根据保司ID获取具体区域类别集合
            List<SurveyConsignorEfficiencyModelArea> surveyConsignorEfficiencyModelAreas = surveyConsignorEfficiencyModelAreaMapper.selectModelAreasByEntrustOrgId(apiReq.getLong("entrustOrgId"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,surveyConsignorEfficiencyModelAreas.size(),surveyConsignorEfficiencyModelAreas);
        }
        else if("surveyPrice".equals(surveyCode)){
            Long priceModelId = apiReq.getLong("priceModelId");
            //价格模板
            map = new HashMap<>();
            map.put("priceModelId",priceModelId);
            //区域类别
            List<SurveyPriceModelAreaCategories>  areaCategories = surveyPriceModelAreaCategoriesMapper.list(map);
            for (SurveyPriceModelAreaCategories areaCategory : areaCategories) {
                map = new HashMap<>();
                map.put("areaCategoriesId",areaCategory.getId());
                //具体价格
                List<SurveyPrice> surveyPrices = surveyPriceMapper.list(map);
                areaCategory.setPrices(surveyPrices);
            }

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,areaCategories);
        }else if("surveyEfficiency".equals(surveyCode)){
            Long modelId = apiReq.getLong("modelId");
            //价格模板
            map = new HashMap<>();
            map.put("modelId",modelId);
            //区域类别
            List<SurveyConsignorEfficiencyModelArea>  surveyConsignorEfficiencyModelAreas = surveyConsignorEfficiencyModelAreaMapper.list(map);
            for (SurveyConsignorEfficiencyModelArea areaCategory : surveyConsignorEfficiencyModelAreas) {
                map = new HashMap<>();
                map.put("areaCategoriesId",areaCategory.getId());
                //具体价格
                List<SurveyConsignorEfficiencyModelInfo> surveyConsignorEfficiencyModelInfos = surveyConsignorEfficiencyModelInfoMapper.list(map);
                areaCategory.setModelInfos(surveyConsignorEfficiencyModelInfos);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignorEfficiencyModelAreas);
        }else if("channelAreaPrice".equals(surveyCode)){
            Long modelId = apiReq.getLong("modelId");
            //价格模板
            map = new HashMap<>();
            map.put("modelId",modelId);
            //区域类别
            List<SurveyChannelModelArea>  surveyChannelModelAreas = surveyChannelModelAreaMapper.list(map);
            for (SurveyChannelModelArea areaCategory : surveyChannelModelAreas) {
                map = new HashMap<>();
                map.put("areaCategoriesId",areaCategory.getId());
                //具体价格
                List<SurveyChannelModelInfo> surveyChannelModelInfos = surveyChannelModelInfoMapper.list(map);
                areaCategory.setModelInfos(surveyChannelModelInfos);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,surveyChannelModelAreas.size(),surveyChannelModelAreas);
        }
        else if("scoreAreaPrice".equals(surveyCode)){
            Long modelId = apiReq.getLong("modelId");
            //价格模板
            map = new HashMap<>();
            map.put("modelId",modelId);
            //区域类别
            List<SurveyScoreModelArea>  surveyScoreModelAreas = surveyScoreModelAreaMapper.list(map);
            for (SurveyScoreModelArea areaCategory : surveyScoreModelAreas) {
                map = new HashMap<>();
                map.put("areaCategoriesId",areaCategory.getId());
                //具体价格
                List<SurveyScoreModelInfo> surveyScoreModelInfos = surveyScoreModelInfoMapper.list(map);
                areaCategory.setModelInfos(surveyScoreModelInfos);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,surveyScoreModelAreas.size(),surveyScoreModelAreas);
        }
        else if ("channelTasks".equals(surveyCode)){
            List<SurveyTaskInfo2Dto> list = new LinkedList<>();
            map = new HashMap<>();
            map.put("searchType",1);
            List<SurveyTaskInfo2Dto> safeTasks1 = surveyTaskInfoMapper.selectChannelTasks(map);//保险合并子类（任务类型对结果）
            for (SurveyTaskInfo2Dto surveyTaskInfo2Dto : safeTasks1) {
                surveyTaskInfo2Dto.setInfoName(surveyTaskInfo2Dto.getTaskName() + "("+surveyTaskInfo2Dto.getDirectionResultTypeName()+")");
            }
            list.addAll(safeTasks1);
            map.put("searchType",2);
            List<SurveyTaskInfo2Dto> safeTasks2 = surveyTaskInfoMapper.selectChannelTasks(map);//保险子类对结果
            for (SurveyTaskInfo2Dto surveyTaskInfo2Dto : safeTasks2) {
                surveyTaskInfo2Dto.setInfoName("【" + surveyTaskInfo2Dto.getTaskName() +  "】" + surveyTaskInfo2Dto.getTaskInfoContentName() + "("+surveyTaskInfo2Dto.getDirectionResultTypeName()+")");
            }
            list.addAll(safeTasks2);
            map.put("searchType",3);
            List<SurveyTaskInfo2Dto> helpTasks = surveyTaskInfoMapper.selectChannelTasks(map);//互助任务类型
            for (SurveyTaskInfo2Dto surveyTaskInfo2Dto : helpTasks) {
                surveyTaskInfo2Dto.setInfoName("【" + surveyTaskInfo2Dto.getTaskName() +  "】" + surveyTaskInfo2Dto.getTaskInfoContentName() + "("+surveyTaskInfo2Dto.getDirectionResultTypeName()+")");
            }
            list.addAll(helpTasks);
            return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
        }else if("copyPrice".equals(surveyCode)){
            List<SurveyPriceModel> surveyPriceModels = surveyPriceModelMapper.list(new HashMap());
            return new ApiResponse(ApiMsgEnum.SUCCESS,surveyPriceModels.size(),surveyPriceModels);
        }else if ("entrustEndTime".equals(surveyCode)){
            if("1000".equals(btnCode)){
                Long entrustOrgId = apiReq.getLong("entrustOrgId"); //委托机构
                Long serviceId = apiReq.getLong("serviceId"); //业务类型
                String entrustTime = apiReq.getString("entrustTime");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                int efficiencyAttr = 1; //1、工作日；2、自然日
                int days = 0;//默认0天
                SurveyConsignorEfficiencyModelOrg modelOrg = surveyConsignorEfficiencyModelOrgMapper.selectOne(entrustOrgId);
                if(modelOrg!=null){
                    SurveyConsignorEfficiencyModel model = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(modelOrg.getEfficiencyModelId());
                    efficiencyAttr = model.getEfficiencyAttr();
                    map = new HashMap<>();
                    map.put("serviceId",serviceId);
                    map.put("efficiencyModelId",modelOrg.getEfficiencyModelId());
                    days = surveyConsignorEfficiencyModelInfoMapper.selectMaxDay(map);
                }
                Date endTime = null;
                try {
                    endTime = GetWorkDay.calLeaveEndDate(StringUtils.isEmpty(entrustTime)?new Date():simpleDateFormat.parse(entrustTime),StringUtils.isEmpty(entrustTime)?new Date():simpleDateFormat.parse(entrustTime),days,efficiencyAttr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,DateUtils.DateToStr(endTime,"yyyy-MM-dd 23:59:59"));
            }
        }else if ("getAreaIdList".equals(surveyCode)){
            try {
                Map<String,Map<String, Object>> resultMap = new HashMap<>();
                String cityIds = apiReq.getString("cityIds");
                if (StringUtils.isNotBlank(cityIds)){
                    String[] areaIds = cityIds.split(",");
                    for (String areaId : areaIds) {
                        Map<String, Object> map1 = new HashMap();
                        List<Long> commonAreas = commonAreaMapper.selectAllByType(Long.parseLong(areaId), Integer.parseInt(btnCode));
                        Integer count = commonAreaMapper.selectAllByTypeCount(Long.parseLong(areaId), Integer.parseInt(btnCode));
                        map1.put("k1",commonAreas);
                        map1.put("k2",count);
                        resultMap.put(areaId,map1);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultMap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        /*
        //需求：2020年9月16日 0923版本：新建“调查方时效模板”，然后取该模板其中的具体天数。但是，同一版本修改需求，maxDate为“案件截止日期-1天”
        else if("consignorEfficiencyModel".equals(surveyCode)){
            if("1000".equals(btnCode)){
                map = new HashMap<>();
                map.put("serviceId",apiReq.getString("serviceId"));
                map.put("cityType",apiReq.getString("cityType"));
                map.put("orgId",apiReq.getString("orgId"));
                map.put("orgType",apiReq.getString("orgType"));
                SurveyConsignorEfficiencyModelInfo info = surveyConsignorEfficiencyModelInfoMapper.selectOne(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,info);
            }
        }*/

        return null;
    }




    //生成提现记录明细
    private SurveyCashInfoDetail addCaseInfoDetail(SurveyCashInfo cashInfo, SurveyCashInfoRecord surveyCashInfoRecord) {

        SurveyCashInfoDetail cashInfoDetail =  new SurveyCashInfoDetail();
        cashInfoDetail.setCashInfoId(cashInfo.getId());
        cashInfoDetail.setSurveyId(surveyCashInfoRecord.getSurveyId());
        cashInfoDetail.setSurveyInfoId(surveyCashInfoRecord.getSurveyInfoId());
        cashInfoDetail.setSurveyInvestigatorCaseId(surveyCashInfoRecord.getSurveyInvestigatorCaseId());
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyCashInfoRecord.getSurveyId());
        if(surveyRiskCase!=null){
            cashInfoDetail.setSurveyNo(surveyRiskCase.getSurveyNo());
            cashInfoDetail.setSurveyPerson(surveyRiskCase.getSurveyPerson());
            cashInfoDetail.setSurveyPersonTel(surveyRiskCase.getSurveryPersonTel());
        }
        cashInfoDetail.setSurveyUserId(surveyCashInfoRecord.getSurveyUserId());
        cashInfoDetail.setSurveyUserName(surveyCashInfoRecord.getSurveyUserName());
        cashInfoDetail.setSurveyTaskMoney(surveyCashInfoRecord.getSurveyTaskMoney());
        cashInfoDetail.setDeleteFlag(0);
        cashInfoDetail.setSurveyInfoRecordId(surveyCashInfoRecord.getId());

        surveyCashInfoDetailMapper.insertSelective(cashInfoDetail);
        return cashInfoDetail;
    }

    //角色判断
    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().intValue() == roleId.intValue()){
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
}
