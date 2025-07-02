package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.SurveyRiskCaseInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.fina.dao.FinaApplicantInfoMapper;
import com.lefancrm.apicenter.fina.model.FinaApplicantInfo;
import com.lefancrm.apicenter.model.CommonFile;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.*;
import com.lefancrm.apicenter.util.bdaiPUtils.CharacterRecognitionUtils;
import com.lefancrm.apicenter.util.nwUtils.TestGateway;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.base.utils.RandomIDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lixianfeng on 2018/12/18.
 */
@Service
@ApiService(descript = "狄大人案件子表API")
public class BackendSurveyRiskCaseInfoApiImpl extends BaseServiceImpl implements SurveyRiskCaseInfoApi {
    @Value("${survey.setting.source}")
    private String surveySettingSource;
    @Value("${survey.report.path}")
    private String generateFilePath;
    @Value("${survey.file.path.sftp}")
    private String surveyFilePathSftp;
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private SurveyInvestigatorCaseTypeMapper surveyInvestigatorCaseTypeMapper;
    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;
    @Autowired
    private SurveyTaskInfoMapper surveyTaskInfoMapper;
    @Autowired
    private SurveyTaskTypeMapper surveyTaskTypeMapper;
    @Autowired
    private SurveyFollowMapper surveyFollowMapper;
    @Autowired
    private SurveyFollowFileMapper surveyFollowFileMapper;
    @Autowired
    private SurveyBackReplyMapper surveyBackReplyMapper;
    @Autowired
    private BackendSurveyCaseWorkflowApiImpl surveyCaseWorkflowApi;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyFeeDetailsMapper surveyFeeDetailsMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private SurveyBackCaseMapper surveyBackCaseMapper;
    @Autowired
    private BackendSurveyProgressApiImpl backendSurveyProgressApi;
    @Autowired
    private BillingApplyMapper billingApplyMapper;
    @Autowired
    private BillingApplyImgsMapper billingApplyImgsMapper;
    @Autowired
    private BillingApplyAccountsMapper billingApplyAccountsMapper;
    @Autowired
    private SurveyBillingApplyMapper surveyBillingApplyMapper;
    @Autowired
    private SurveyCashInfoRecordMapper surveyCashInfoRecordMapper;
    @Autowired
    private BackendSurveyInvestigatorApiImpl backendSurveyInvestigatorApi;
    @Autowired
    private SurveyConsignorModelMapper surveyConsignorModelMapper;
    @Autowired
    private SurveyModelInfoMapper surveyModelInfoMapper;
    @Autowired
    private SurveyCaseFileMapper surveyCaseFileMapper;
    @Autowired
    private SurveyCaseDirectionFileMapper surveyCaseDirectionFileMapper;
    @Autowired
    private BackendSurveyMessageApiImpl backendSurveyMessageApi;
    @Autowired
    private SurveyConsignorReportRuleMapper surveyConsignorReportRuleMapper;
    @Autowired
    private SurveyConsignerMapper surveyConsignerMapper;
    @Autowired
    private SurveyRiskCaseTransferMapper surveyRiskCaseTransferMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private SurveyHelpInfoMapper surveyHelpInfoMapper;
    @Autowired
    private SurveyBusinessTaskTypeMapper surveyBusinessTaskTypeMapper;
    @Autowired
    private SurveyUserSignMapper surveyUserSignMapper;
    @Autowired
    private SurveyRiskCaseGuideMapper surveyRiskCaseGuideMapper;
    @Autowired
    private SurveyRiskInfoFinalUserMapper surveyRiskInfoFinalUserMapper;
    @Autowired
    private SurveyRiskCaseVisitMapper surveyRiskCaseVisitMapper;
    @Autowired
    private SurveyAccountLogMapper surveyAccountLogMapper;
    @Autowired
    private SurveyReimbursementInfoMapper surveyReimbursementInfoMapper;
    @Autowired
    private SurveyAssignOrgTypeMapper surveyAssignOrgTypeMapper;
    @Autowired
    private SurveyAssignOrgExtensionMapper surveyAssignOrgExtensionMapper;
    @Autowired
    private BackendSurveyAssignOrgApiImpl surveyAssignOrgApi;
    @Autowired
    private SurveyOrgPrescriptionFlowMapper surveyOrgPrescriptionFlowMapper;
    @Autowired
    private SurveyUserPrescriptionFlowMapper surveyUserPrescriptionFlowMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;
    @Autowired
    private SurveyClockCaseMapper surveyClockCaseMapper;
    @Autowired
    private SurveyChannelCaseMapper surveyChannelCaseMapper;
    @Autowired
    private SurveyAttrUpdRecordMapper surveyAttrUpdRecordMapper;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private SurveyServiceTypeMapper surveyServiceTypeMapper;
    @Autowired
    private SurveyCheckPreFlowMapper surveyCheckPreFlowMapper;
    @Autowired
    private SurveyAssignOrgExtendMapper surveyAssignOrgExtendMapper;
    @Autowired
    private SurveyInvestigatorCaseSubMapper surveyInvestigatorCaseSubMapper;
    @Autowired
    private SurveyConsignorEfficiencyModelInfoMapper surveyConsignorEfficiencyModelInfoMapper;

    @Autowired
    private SurveyEmailInfoMapper surveyEmailInfoMapper;
    @Autowired
    private SurveyEmailInfoOrgMapper surveyEmailInfoOrgMapper;

    @Autowired
    private SurveyChannelCostNewMapper surveyChannelCostNewMapper;
    @Autowired
    private SurveyCaseArchivesMapper surveyCaseArchivesMapper;
    @Autowired
    private SurveyUserConsignorMapper surveyUserConsignorMapper;
    @Autowired
    private SurveyCaseWorkflowMapper surveyCaseWorkflowMapper;
    @Autowired
    private FinaApplicantInfoMapper finaApplicantInfoMapper;
    @Autowired
    private SurveyBusinessTypeMapper surveyBusinessTypeMapper;
    @Autowired
    private BackendSurveyInvestigatorCaseApiImpl backendSurveyInvestigatorCaseApi;
    @Autowired
    private CommonAreaMapper commonAreaMapper;
    @Autowired
    private SurveyTaskDirectionResultMapper surveyTaskDirectionResultMapper;
    @Autowired
    private SurveyPriceMapper surveyPriceMapper;
    @Autowired
    private SurveyFranchiseePriceMapper surveyFranchiseePriceMapper;
    @Autowired
    private SurveyInvestigatorAreaPriceMapper surveyInvestigatorAreaPriceMapper;
    @Autowired
    private SurveyConsignorPriceMapper surveyConsignorPriceMapper;
    @Autowired
    private SurveyCommonAreaPriceMapper surveyCommonAreaPriceMapper;
    @Autowired
    BackendSurveyInvestigatorCaseApiImpl surveyInvestigatorCaseApi;
    @Autowired
    private SurveyScoreModelOrgMapper surveyScoreModelOrgMapper;
    @Autowired
    private SurveyScoreModelInfoMapper surveyScoreModelInfoMapper;

    @Autowired
    private SurveyChannelModelOrgMapper surveyChannelModelOrgMapper;
    @Autowired
    private SurveyChannelModelInfoMapper surveyChannelModelInfoMapper;
    @Autowired
    private SurveyPriceModelMapper surveyPriceModelMapper;
    @Autowired
    private BillingApplyCompanyMapper billingApplyCompanyMapper;
    @Autowired
    private SurveyConsignorDepartmentMapper surveyConsignorDepartmentMapper;
    @Autowired
    private BackendCommonAreaApiImpl backendCommonAreaApi;

    @Autowired
    private SurveyAssignOrgBackMapper surveyAssignOrgBackMapper;

    @Value("${nw.appkey}")
    private String appkey;
    @Value("${nw.secretkey}")
    private String secretkey;
    @Value("${lf.cprivatekey}")
    private String cprivatekey;
    @Value("${nw.spublickey}")
    private String spublickey;
    @Value("${nw.url}")
    private String url;

    @ApiMethod(needLogin = false, descript = "子案件列表", value = "list-survey-risk-case-info")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);


        String menuCode = apiRequest.getString("menuCode");
        String menuType = apiRequest.getString("menuType");
        apiRequest.put("searchStr", apiRequest.getString("searchStr") == null ? null : apiRequest.getString("searchStr").trim());
        //如果点的是保司复审导出。则不分页
        String surveyReport = apiRequest.getString("surveyReport");

        String btnCode = apiRequest.getString("btnCode");
        if ("bill-list".equals(menuCode) || ("account-list".equals(menuCode) && "export".equals(menuType)) || "mark-list".equals(menuCode)
                || "condition-assign-list".equals(menuType) || "surveyReport".equals(surveyReport) || ("survey-list".equals(menuCode) && "reass".equals(btnCode))
                || ("agent-entrust-list".equals(menuCode) && "bsCase".equals(btnCode))) {
            //不分页
        } else {
            setBackendPageSize(apiRequest);
        }

        if ("my-list".equals(menuCode)) {//委托清单--自己创建的所有案件  和 代理委托创建的
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean b = isRoleUser(userRoles, 56L);//如果有代理委托的权限  则 查询创建人(代理委托人)  or 委托人是当前登录人 的列表
            if (b) {
                apiRequest.put("userId", userInfo.getUserId());
                apiRequest.put("condition", 3); //  agent_user_id = userInfo.getUserId  or create_user_id = userInfo.getUserId
            } else {
                apiRequest.put("createUserId", userInfo.getUserId());//委托人是当前登录人
            }
        } else if ("check-list".equals(menuCode)) {//委托审核
            apiRequest.put("surveyState", 2);//待审核
        } else if ("credit-list".equals(menuCode)) {//授信待支付
            apiRequest.put("entrustCredit", 0);
            apiRequest.put("entrustCreditIsPay", 0);
//            apiRequest.put("createUserId",userInfo.getUserId());
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean b = isRoleUser(userRoles, 56L);//如果有代理委托的权限  则 查询创建人(代理委托人)  or 委托人是当前登录人 的列表
            if (b) {
                apiRequest.put("userId", userInfo.getUserId());
                apiRequest.put("condition", 3); //  agent_user_id = userInfo.getUserId  or create_user_id = userInfo.getUserId
            } else {
                apiRequest.put("createUserId", userInfo.getUserId());//委托人是当前登录人
            }
        } else if ("assign-list".equals(menuCode)) {
            apiRequest.put("condition", 1);//待分派 部分分派  已拒绝 调查中
            String assignState = apiRequest.getString("assignState");
            String orgAssign = apiRequest.getString("orgAssign");
            if ("2".equals(assignState)) {
                apiRequest.put("condition", null);
            }
            if ("1".equals(orgAssign)) {
                apiRequest.put("condition", null);
            }
            String order = apiRequest.getString("order");
            if (StringUtils.isEmpty(order)) {
                apiRequest.put("order", 2); //特殊排序：优先根据“退回”排序
            }
        } else if ("dispatch-list".equals(menuCode)) {
            apiRequest.put("condition", 1);//所有待分派的案件。  都可调度
        } else if ("dispatch-opr-list".equals(menuCode)) {
            apiRequest.put("taskDispatchState", 2);
        } else if ("survey-list".equals(menuCode)) {//调查审核
            String operateState = apiRequest.getString("operateState");
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean b = isRoleUser(userRoles, 68L);//如果有案件归属改派的权限  则查询所有已经被归属的案件
            if ("0".equals(operateState) || operateState == null || "".equals(operateState)) { //未审核
                apiRequest.put("condition", 2);
                apiRequest.put("userId", currentUserId);
                apiRequest.put("belongUserId", currentUserId);//案件归属人
                if (b) {
                    apiRequest.put("belongUserId", null);//所有已归属案件 -- 改派
                }
            } else if ("1".equals(operateState)) {//已审核
                apiRequest.put("condition", 8);
                apiRequest.put("userId", currentUserId);
                apiRequest.put("belongUserId", currentUserId);//案件归属人
                if (b) {
                    apiRequest.put("belongUserId", null);//所有已归属案件 -- 改派
                }
            }
            if (!apiRequest.containsKey("order")) {
                apiRequest.put("order", 1);//退回原因优先排序
            }
            if ("reass".equals(btnCode)) {
                apiRequest.put("condition", 18);
            }

        } else if ("entrust-list".equals(menuCode) || "agent-entrust-list".equals(menuCode)) {//保司审核  和  代理保司审核
            apiRequest.put("surveyState", 24);//风控审核通过
//            apiRequest.put("createUserId",userInfo.getUserId());
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean b = isRoleUser(userRoles, 56L);//如果有代理委托的权限  则查询风控审核通过的所有数据
            if (b) {
                //apiRequest.put("userId",userInfo.getUserId());
                //apiRequest.put("condition",3); //  agent_user_id = userInfo.getUserId  or create_user_id = userInfo.getUserId
            } else {
                apiRequest.put("createUserId", userInfo.getUserId());//委托人是当前登录人
            }

            if ("agent-entrust-list".equals(menuCode) && "bsCase".equals(btnCode)) { //保司案件批量处理
                apiRequest.put("orgAttr", 1);
                apiRequest.put("btnCode", 9998);//因为别处的btnCode，也是传值数值，故替换
            }
        } else if ("all-list".equals(menuCode)) {
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean roleUser = isRoleUser(userRoles, 144L);
            if (roleUser) {
                apiRequest.put("orgAttr", 1);
                apiRequest.put("busAttr144", 144);
            }
        } else if ("bill-list".equals(menuCode)) {//批量开票的数据
            apiRequest.put("condition", 4);//基本费是否结算,减损奖励是否结算
        } else if ("account-list".equals(menuCode)) {//对账清单
            apiRequest.put("condition", 5);
            //平台复审人员：查询自己名下机构信息
            String isFinalUserManage = apiRequest.getString("isFinalUserManage");
            if (!StringUtils.isEmpty(isFinalUserManage)) {
                apiRequest.put("isFinalUserManage", isFinalUserManage);
                apiRequest.put("userId", currentUserId);

                String consignorIds = surveyUserConsignorMapper.selectConsignorIds(currentUserId);
                apiRequest.put("consignorIds", consignorIds);
            }
            apiRequest.put("acc-order", "1");
        } else if ("mark-list".equals(menuCode)) {
            String priceIsCalcType = apiRequest.getString("priceIsCalcType");
            if ("1".equals(priceIsCalcType) || priceIsCalcType == null || "".equals(priceIsCalcType)) {
                apiRequest.put("price1IsCalc", 0);
            } else if ("2".equals(priceIsCalcType)) {
                apiRequest.put("price2IsCalc", 0);
            }
            apiRequest.put("surveyState", 28);
        } else if ("make-report-list".equals(menuCode)) {
            apiRequest.put("sendReportUserId", userInfo.getUserId());
            apiRequest.put("sendReportState", 1);
        } else if ("belong-list".equals(menuCode)) { //案件未归属清单
            apiRequest.put("condition", 9);
            apiRequest.put("userId", currentUserId);
        } else if ("time-track-list".equals(menuCode)) { //时效跟踪
            apiRequest.put("condition", 10);
            apiRequest.put("userId", currentUserId);
        } else if ("guide-list".equals(menuCode)) {
            apiRequest.put("condition", 11);//机构已分派
        } else {
            return new ApiResponse(ApiMsgEnum.ERROR_PARAMETER);
        }

        String surveyStates = apiRequest.getString("surveyStates");
        if (surveyStates != null && !"".equals(surveyStates)) {
            if (surveyStates.indexOf("12") > -1) {
                surveyStates = surveyStates.replace("12", "12,8,14,16,26");//调查中 包含多种状态
            }
//            if ("12".equals(surveyStates)){
//                surveyStates = "12,8,14,16,26";//调查中 包含多种状态
//            }
            if (surveyStates.indexOf("22") > -1) {
                surveyStates = surveyStates.replace("22", "22,30");//调查中 包含多种状态
            }
//           if ("22".equals(surveyStates)){
//               surveyStates = "22,30";//平台复审中 包含多种状态
//           }
            apiRequest.put("surveyStates", surveyStates);
        }
        int count = 0;
        List<SurveyRiskCaseInfoDto> list = null;
        //如果是平台复审保司导出   2020年9月16日。         list查询相关人员需优化。 不可在循环内查询,案件主表 以及相关调查机构列表
        if ("surveyReport".equals(surveyReport)) {
            apiRequest.put("order", 50);
            apiRequest.put("colSortType", 1);
            list = surveyRiskCaseInfoMapper.list(apiRequest);
            for (SurveyRiskCaseInfoDto dto : list) {
                SurveyRiskCase surveyRiskCase = new SurveyRiskCase();
                surveyRiskCase.setId(dto.getRiskCaseId());
                surveyRiskCase.setSurveyNo(dto.getRiskCaseSurveyNo());
                surveyRiskCase.setSurveyPerson(dto.getRiskCaseSurveyPerson());
                surveyRiskCase.setSurveryPersonTel(dto.getRiskCaseSurveryPersonTel());
                surveyRiskCase.setSex(dto.getRiskCaseSex());
                surveyRiskCase.setPolicyNo(dto.getRiskCasePolicyNo());
                surveyRiskCase.setClaimsNo(dto.getRiskCaseClaimsNo());
                surveyRiskCase.setClaimsMoney(dto.getRiskCaseClaimsMoney());
                surveyRiskCase.setEntrustUserId(dto.getRiskCaseEntrustUserId());
                surveyRiskCase.setEntrustUserName(dto.getRiskCaseEntrustUserName());
                surveyRiskCase.setEntrustOrgId(dto.getRiskCaseEntrustOrgId());
                surveyRiskCase.setEntrustOrgName(dto.getRiskCaseEntrustOrgName());
                surveyRiskCase.setEntrustTime(dto.getRiskCaseEntrustTime());
                surveyRiskCase.setSurveyCaseNo(dto.getSurveyCaseNo());
                dto.setSurveyRiskCase(surveyRiskCase);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, list.size(), list);
        }


        if ("mark-list".equals(menuCode) || "bill-list".equals(menuCode)) {
            list = surveyRiskCaseInfoMapper.listMark(apiRequest);
            count = list.size();
        } else {
            count = surveyRiskCaseInfoMapper.listSize(apiRequest);
            list = surveyRiskCaseInfoMapper.list(apiRequest);
        }
        List<Long> collect = list.stream().map(SurveyRiskCaseInfoDto::getSurveyId).collect(Collectors.toList());
        List<SurveyRiskCase> surveyRiskCases = new ArrayList<>();
        if (collect.size() > 0) {
            surveyRiskCases = surveyRiskCaseMapper.list(collect);
        }

        Map<String, Object> map = new HashMap();
        List<SurveyAssignOrgDto> tempAssorgs = new ArrayList<>();
        List<Long> ids = list.stream().map(SurveyRiskCaseInfoDto::getId).collect(Collectors.toList());
        if (ids.size() > 0) {
            map.put("surveyInfoIds", ids);
            tempAssorgs = surveyAssignOrgMapper.list(map);
        }

        for (SurveyRiskCaseInfoDto dto : list) {
            if ("mark-list".equals(menuCode) || "bill-list".equals(menuCode)) {
                SurveyRiskCase surveyRiskCase = new SurveyRiskCase();
                surveyRiskCase.setId(dto.getRiskCaseId());
                surveyRiskCase.setSurveyNo(dto.getRiskCaseSurveyNo());
                surveyRiskCase.setSurveyPerson(dto.getRiskCaseSurveyPerson());
                surveyRiskCase.setSurveryPersonTel(dto.getRiskCaseSurveryPersonTel());
                surveyRiskCase.setSex(dto.getRiskCaseSex());
                surveyRiskCase.setPolicyNo(dto.getRiskCasePolicyNo());
                surveyRiskCase.setClaimsNo(dto.getRiskCaseClaimsNo());
                surveyRiskCase.setClaimsMoney(dto.getRiskCaseClaimsMoney());
                surveyRiskCase.setEntrustUserId(dto.getRiskCaseEntrustUserId());
                surveyRiskCase.setEntrustUserName(dto.getRiskCaseEntrustUserName());
                surveyRiskCase.setEntrustOrgId(dto.getRiskCaseEntrustOrgId());
                surveyRiskCase.setEntrustOrgName(dto.getRiskCaseEntrustOrgName());
                surveyRiskCase.setEntrustTime(dto.getRiskCaseEntrustTime());
                surveyRiskCase.setSurveyCaseNo(dto.getSurveyCaseNo());
                surveyRiskCase.setDepartmentName(dto.getDepartmentName());
                dto.setSurveyRiskCase(surveyRiskCase);
            } else {
                List<SurveyRiskCase> temp = surveyRiskCases.stream().filter(p -> p.getId().intValue() == dto.getSurveyId().intValue()).collect(Collectors.toList());
                if (temp.size() > 0) {
                    dto.setSurveyRiskCase(temp.get(0));
                }
            }
            if ("assign-list".equals(menuCode)) {
                //是否超时
//                Map<String, Object> map = new HashMap<>();
//                map.put("surveyInfoId", dto.getId());
//                List<SurveyAssignOrgDto> assignOrgDto = surveyAssignOrgMapper.list(map);
//                for (int i = 0; i < assignOrgDto.size(); i++) {
//                    if (assignOrgDto.get(i).getOrgEndTime() != null && assignOrgDto.get(i).getOrgEndTime().compareTo(new Date()) == -1) {
//                        dto.setIsOverTime(true);
//                    }
//                }
                if (dto.getEndTime() != null && dto.getEndTime().compareTo(new Date()) == -1) {
                    dto.setIsOverTime(true);
                } else {
                    dto.setIsOverTime(false);
                }
            }

            //调查审核，案件归属
            if (("survey-list".equals(menuCode) && !"reass".equals(btnCode)) || "belong-list".equals(menuCode) || "guide-list".equals(menuCode)) {
//                Map<String, Object> map = new HashMap<>();
//                map = new HashMap();
//                map.put("surveyInfoId",dto.getId());
//                List<SurveyAssignOrgDto> surveyAssignOrgs = surveyAssignOrgMapper.list(map);
                List<SurveyAssignOrgDto> surveyAssignOrgs = tempAssorgs.stream().filter(p -> p.getSurveyInfoId().intValue() == dto.getId().intValue()).collect(Collectors.toList());
                String orgName = "";
                for (int i = 0; i < surveyAssignOrgs.size(); i++) {
                    orgName += surveyAssignOrgs.get(i).getSurveyOrgName();
                    if (surveyAssignOrgs.size() > 1 && i < surveyAssignOrgs.size() - 1) {
                        orgName += ",";
                    }
                }
                dto.setSurveyOrgName(orgName);
            }
            //调查审核 ：根据案件截止时间与当前时间的比较，页面显示不同颜色
            if ("survey-list".equals(menuCode) && !"reass".equals(btnCode)) {
                long nowTime = new Date().getTime();
                if (dto.getEndTime() == null) {
                    dto.setColorTimeType(3);
                } else {
                    long endTime = dto.getEndTime().getTime();
                    long diff = endTime - nowTime;
                    if (diff < 0) {
                        dto.setColorTimeType(1); //超期：红色
                    } else {
                        Long days = (diff / (1000 * 3600 * 24));
                        if (days == 0 || (0 < days && days <= 2)) {
                            dto.setColorTimeType(2); //两天内：黄色
                        } else if (days > 2) {
                            dto.setColorTimeType(3); //两天外：黑色
                        }
                    }
                }
            }
            if ("agent-entrust-list".equals(menuCode)) {//代理保司终审页面 案件时效设置
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(dto.getEntrustOrgId());
                int efficiencyAttr = 1;//时效设置（1：工作日；2、自然日）
                if (surveyConsignor != null) {
                    efficiencyAttr = surveyConsignor.getEfficiencyAttr();
                }
                dto.setAgingDay(Math.abs(GetWorkDay.calLeaveDays(dto.getEntrustStartDate(), dto.getEntrustReportStartDate(), efficiencyAttr)));
            }
            //时效跟踪 -- 是否超过截止日期
            if ("time-track-list".equals(menuCode)) {
//                for (SurveyRiskCaseInfoDto surveyRiskCaseInfoDto : list) {
//                    if(surveyRiskCaseInfoDto.getEndTime()!=null) {
//                        if (surveyRiskCaseInfoDto.getEndTime().getTime() - new Date().getTime() < 0) {
//                            surveyRiskCaseInfoDto.setIsOverTime(true);
//                        } else {
//                            surveyRiskCaseInfoDto.setIsOverTime(false);
//                        }
//                    }
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(dto.getEntrustOrgId());
                int efficiencyAttr = 1;//时效设置（1：工作日；2、自然日）
                if (surveyConsignor != null) {
                    efficiencyAttr = surveyConsignor.getEfficiencyAttr();
                }
                //案件状态（未提交保司审核，提交保司审核）
                if (dto.getEntrustReportStartDate() == null) { //未提交保司审核
                    //“当前时间”与“案件截止时间”相比：
                    Date endTime = dto.getEndTime() == null ? new Date() : dto.getEndTime();
                    if (new Date().before(endTime)) {
                        int days = GetWorkDay.calLeaveDays(new Date(), endTime, efficiencyAttr);
                        days = Math.abs(days);
                        //剩余n天
                        dto.setEfficiencyState("剩余：" + days + "天");
                        dto.setEfficiencyStateColor("#eda42e");
                    } else {
                        int days = GetWorkDay.calLeaveDays(endTime, new Date(), efficiencyAttr);
                        days = Math.abs(days);
                        //超时n天
                        dto.setEfficiencyState("超时：" + days + "天");
                        dto.setEfficiencyStateColor("red");
                    }
                }
                //提交保司审核
                else {
                    //“委托时间”
                    Date entrustTime = dto.getSurveyRiskCase().getEntrustTime() == null ? new Date() : dto.getSurveyRiskCase().getEntrustTime();
                    //“案件截止时间”
                    Date endTime = dto.getEndTime() == null ? new Date() : dto.getEndTime();
                    //“提交保司审核时间”
                    Date entrustReportStartTime = dto.getEntrustReportStartDate() == null ? new Date() : dto.getEntrustReportStartDate();

                    int days = GetWorkDay.calLeaveDays(entrustTime, entrustReportStartTime, efficiencyAttr);
                    days = Math.abs(days);
                    dto.setEfficiencyState("时效：" + days + "天");

                    if (entrustReportStartTime.before(endTime)) {
                        dto.setEfficiencyStateColor("green");
                    } else {
                        dto.setEfficiencyStateColor("red");
                    }
                }

                //获取已分配机构信息
//                Map<String,Object> map = new HashMap();
//                map.put("surveyInfoId",dto.getId());
//                map.put("order",1);  //排序
//                List<SurveyAssignOrgDto> surveyAssignOrgs = surveyAssignOrgMapper.list(map);

                List<SurveyAssignOrgDto> surveyAssignOrgs = tempAssorgs.stream().filter(p -> p.getSurveyInfoId().intValue() == dto.getId().intValue()).collect(Collectors.toList());
                for (SurveyAssignOrgDto surveyAssignOrg : surveyAssignOrgs) {
                    //分配给机构时间
                    Date createTime = surveyAssignOrg.getCreateTime() == null ? new Date() : surveyAssignOrg.getCreateTime();
                    //机构截止时间
                    Date endTime = surveyAssignOrg.getOrgEndTime() == null ? new Date() : surveyAssignOrg.getOrgEndTime();
                    //提交初审通过时间
                    Date reportDate = surveyAssignOrg.getReportDate() == null ? new Date() : surveyAssignOrg.getReportDate();
                    if (surveyAssignOrg.getOrgSurveyState() == 4) { //初审通过
//                        int days = GetWorkDay.calLeaveDays(createTime, reportDate,efficiencyAttr);
//                        int days = surveyAssignOrg.getAgingDay();
//                        days = Math.abs(days);
                        if (surveyAssignOrg.getAgingOver().intValue() == 0) { //未超时
                            surveyAssignOrg.setEfficiencyState("时效：" + surveyAssignOrg.getAgingReal().intValue() + "天");
                            surveyAssignOrg.setEfficiencyStateColor("green");
                        } else {
                            surveyAssignOrg.setEfficiencyState("时效：" + surveyAssignOrg.getAgingReal().intValue() + "天");
                            surveyAssignOrg.setEfficiencyStateColor("red");
                        }
                    } else {
                        if (surveyAssignOrg.getAgingOver().intValue() == 0) { //未超时
                            int days = GetWorkDay.calLeaveDays(endTime, new Date(), efficiencyAttr);
//                            int days = surveyAssignOrg.getAgingDay();
                            days = Math.abs(days);
                            surveyAssignOrg.setEfficiencyState("剩余：" + days + "天");
                            surveyAssignOrg.setEfficiencyStateColor("#eda42e");
                        } else {
//                            int days = GetWorkDay.calLeaveDays(endTime, new Date(),efficiencyAttr);
//                            days = Math.abs(days);
                            surveyAssignOrg.setEfficiencyState("超时：" + surveyAssignOrg.getAgingOver().intValue() + "天");
                            surveyAssignOrg.setEfficiencyStateColor("red");
                        }
                    }
                }
                dto.setSurveyAssignOrgs(surveyAssignOrgs);
                //案件时效 --去除工作日的时间
//                    int days = calLeaveDays(surveyRiskCaseInfoDto.getSurveyRiskCase().getEntrustTime() == null ? new Date() : surveyRiskCaseInfoDto.getSurveyRiskCase().getEntrustTime(), surveyRiskCaseInfoDto.getEntrustReportStartDate() == null ? new Date() : surveyRiskCaseInfoDto.getEntrustReportStartDate());
//                    surveyRiskCaseInfoDto.setEfficiency(days);
            }

//            }
        }

        if ("account-list".equals(menuCode) && "export".equals(menuType)) {
            //导出时，excel头部因要展示“调查方向”的数量，所以用count字段来替代使用
            count = 0;
            for (SurveyRiskCaseInfoDto surveyRiskCaseInfoDto : list) {
                map = new HashMap();
                map.put("surveyInfoId", surveyRiskCaseInfoDto.getId());
                map.put("orderByCondition", 1);//排序
                List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.list(map);
                if (count < surveyCaseDirections.size()) {
                    count = surveyCaseDirections.size();
                }
                List<SurveyCaseDirectionDto> surveyCaseDirectionDtos = new ArrayList<>();
                for (SurveyCaseDirection surveyCaseDirection : surveyCaseDirections) {
                    SurveyCaseDirectionDto surveyCaseDirectionDto = new SurveyCaseDirectionDto();
                    try {
                        BeanUtils.copyProperties(surveyCaseDirectionDto, surveyCaseDirection);
                        SurveyInvestigatorCaseDto surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
                        surveyInvestigatorCase.setSurveyFranchisee(surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyOrgId()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    surveyCaseDirectionDtos.add(surveyCaseDirectionDto);
                }
                surveyRiskCaseInfoDto.setSurveyCaseDirections(surveyCaseDirectionDtos);
            }
        }
        if ("account-list".equals(menuCode)) {
            //去除工作日的时间
            for (SurveyRiskCaseInfoDto surveyRiskCaseInfoDto : list) {
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfoDto.getEntrustOrgId());
                int efficiencyAttr = 1;//时效设置（1：工作日；2、自然日）
                if (surveyConsignor != null) {
                    efficiencyAttr = surveyConsignor.getEfficiencyAttr();
                }
                int days = GetWorkDay.calLeaveDays(surveyRiskCaseInfoDto.getSurveyRiskCase().getEntrustTime() == null ? new Date() : surveyRiskCaseInfoDto.getSurveyRiskCase().getEntrustTime(), surveyRiskCaseInfoDto.getEntrustReportStartDate() == null ? new Date() : surveyRiskCaseInfoDto.getEntrustReportStartDate(), efficiencyAttr);
                days = Math.abs(days);
                surveyRiskCaseInfoDto.setEfficiency(days);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, list);
    }

    @ApiMethod(needLogin = false, descript = "批量开票金额修改", value = "list-survey-risk-case-upd-batch-kp")
    @Override
    public ApiResponse updBatchKp(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Double updMoney = apiRequest.getDouble("updMoney");
        SurveyRiskCaseInfoDto surveyRiskCaseInfoDto = surveyRiskCaseInfoMapper.selectByPrimaryKey(id);
        surveyRiskCaseInfoDto.setBillingMoney(updMoney);
        int result = surveyRiskCaseInfoMapper.updateByPrimaryKeySelective(surveyRiskCaseInfoDto);
        return new ApiResponse(result > 0 ? ApiMsgEnum.SUCCESS : ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false, descript = "委托方调查费金额修改", value = "list-survey-risk-case-upd-marklist-entrustmoney")
    @Override
    public ApiResponse updEntrustMoney(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Double updMoney = apiRequest.getDouble("updMoney");
        SurveyRiskCaseInfoDto surveyRiskCaseInfoDto = surveyRiskCaseInfoMapper.selectByPrimaryKey(id);
        surveyRiskCaseInfoDto.setEntrustOkPrice1(updMoney);
        int result = surveyRiskCaseInfoMapper.updateByPrimaryKeySelective(surveyRiskCaseInfoDto);
        return new ApiResponse(result > 0 ? ApiMsgEnum.SUCCESS : ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false, descript = "子案件详情", value = "info-survey-risk-case-info")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        try {
            Long id = apiRequest.getLong("id");
            String menuCode = apiRequest.getString("menuCode");
            String btnCode = apiRequest.getString("btnCode");
            Long currentUserId = getCurrentUserId(apiRequest);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            SurveyRiskCaseInfoDto dto = surveyRiskCaseInfoMapper.selectByPrimaryKey(id);
            if (dto == null) {
                dto = new SurveyRiskCaseInfoDto();
                //默认“委托时间”为当前时间
                SurveyRiskCase surveyRiskCase = new SurveyRiskCase();
                surveyRiskCase.setEntrustTime(new Date());
                if (userInfo.getUserId().intValue() == 2189) {
                    dto.setEndTime(new Date());
                    dto.setSurveyInfo("新增案件，默认测试数据");
                    dto.setSurveyItem("1.是否曾经有过伤残;2.是否具有诈骗行为;3.默认事项");
                    dto.setPayType(1);
                    dto.setEntrustMoney(500D);
                    String num = String.format("%02d", new Random().nextInt(99));
                    surveyRiskCase.setSurveyPerson("默认人".concat(num));
                    surveyRiskCase.setSurveryPersonTel("13697376856");
                    surveyRiskCase.setSex(2);
                    surveyRiskCase.setPolicyNo("HT9088882034203".concat(num));
                    surveyRiskCase.setClaimsNo("LP68676768678".concat(num));
                    surveyRiskCase.setClaimsMoney(8888D);
                    surveyRiskCase.setIdType(1);
                    surveyRiskCase.setIdNumber("42032119945725684");
                }

                //获取委托机构的“模板信息”
                SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByUserId(currentUserId);
                if (surveyConsigner != null) {
                    SurveyConsignorReportRule surveyConsignorReportRule = surveyConsignorReportRuleMapper.selectByConsignorId(surveyConsigner.getEntrustOrgId());
                    dto.setSurveyConsignorReportRule(surveyConsignorReportRule);

                    SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(surveyConsigner.getEntrustOrgId());
                    surveyRiskCase.setModelId(surveyConsignorModel.getModelId());

                    String obj = apiRequest.getString("obj"); //新增时：默认当前人所在机构名称
                    if ("own".equals(obj)) {
                        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyConsigner.getEntrustOrgId());
                        surveyRiskCase.setEntrustOrgName(surveyConsignor.getCompany());
                        dto.setSurveyConsignor(surveyConsignor);
                    }
                    List<String> top3insures = surveyRiskCaseMapper.top3insures(surveyConsigner.getEntrustOrgId());
                    dto.setTop3insures(top3insures);
                }

                dto.setSurveyRiskCase(surveyRiskCase);

                //垫付案件 转 发起乐凡调查时
                if ("to-lefan-survey".equals(btnCode)) {
                    Long finaInfoId = apiRequest.getLong("finaInfoId");
                    FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);

                    surveyRiskCase = new SurveyRiskCase();
                    surveyRiskCase.setEntrustOrgId(finaApplicantInfo.getEntrustOrgId());
                    surveyRiskCase.setEntrustOrgName(finaApplicantInfo.getEntrustOrgName());
                    surveyRiskCase.setSurveyPerson(finaApplicantInfo.getInsuredName());
                    surveyRiskCase.setSurveryPersonTel(finaApplicantInfo.getInsuredTel());
                    surveyRiskCase.setIdType(1);
                    surveyRiskCase.setIdNumber(finaApplicantInfo.getInsuredIdcard());

                    SurveyConsignorReportRule surveyConsignorReportRule = surveyConsignorReportRuleMapper.selectByConsignorId(finaApplicantInfo.getEntrustOrgId());
                    dto.setSurveyConsignorReportRule(surveyConsignorReportRule);

                    SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(finaApplicantInfo.getEntrustOrgId());
                    surveyRiskCase.setModelId(surveyConsignorModel.getModelId());

                    dto.setSurveyRiskCase(surveyRiskCase);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, dto);
            }

            Map findMap = new HashMap();
            findMap.put("surveyInfoId", dto.getId());
            Integer punchClockCount = surveyClockCaseMapper.selectPunchClockCount(findMap);
            dto.setPunchClockCount(punchClockCount);
            SurveyRiskCase surveyRiskCase = new SurveyRiskCase();
            if (dto.getSurveyId() != null) {
                surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(dto.getSurveyId());
                //获取委托机构的“模板信息”
                if (surveyRiskCase != null) {
                    SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(surveyRiskCase.getEntrustOrgId());
                    if (surveyConsignorModel != null) {
                        surveyRiskCase.setModelId(surveyConsignorModel.getModelId());
                    }
                    SurveyConsignorReportRule surveyConsignorReportRule = surveyConsignorReportRuleMapper.selectByConsignorId(surveyRiskCase.getEntrustOrgId());
                    dto.setSurveyConsignorReportRule(surveyConsignorReportRule);
                }
                dto.setSurveyRiskCase(surveyRiskCase);
            }
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(dto.getEntrustOrgId());
            int efficiencyAttr = 1;//时效设置（1：工作日；2、自然日）
            if (surveyConsignor != null) {
                efficiencyAttr = surveyConsignor.getEfficiencyAttr();
                SurveyConsignorModel consignorModel = surveyConsignorModelMapper.selectByConsignorId(surveyConsignor.getId());
                if (consignorModel != null) {
                    surveyConsignor.setModelType(consignorModel.getModelId().intValue());
                }
                ApiRequest paramMap = new ApiRequest();
                paramMap.put("entrustOrgId", surveyConsignor.getId());
                paramMap.put("state", 0);
                List<BillingApplyCompany> companies = billingApplyCompanyMapper.selectList(paramMap);
                surveyConsignor.setCompanys(companies);
            }
            dto.setSurveyConsignor(surveyConsignor);

            if (dto.getReportId() != null) {
                dto.setCommonFile(commonFileMapper.selectByPrimaryKey(dto.getReportId()));
            }

            List<SurveyTaskType> surveyTaskTypeList = surveyTaskTypeMapper.getSurveyTaskTypeBySurveyInfoId(dto.getId());
            dto.setSurveyTaskTypes(surveyTaskTypeList);
            String ids = "";
            for (SurveyTaskType surveyTaskType : surveyTaskTypeList) {
                ids += surveyTaskType.getTaskId() + ",";
            }
            dto.setSurveyTaskTypeIds(ids);

            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
            //任务详情
            List<SurveyInvestigatorCase> surveyInvestigatorCases = null;
            if ("assign-org-list".equals(menuCode) || "assign-list".equals(menuCode) || "all-list".equals(menuCode)) {
                surveyInvestigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdTasks(dto.getId());//包含已拒绝
            } else {
                surveyInvestigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(dto.getId());//不包含已拒绝
            }
            /*for (SurveyInvestigatorCase surveyInvestigatorCase:surveyInvestigatorCases) {
                SurveyInvestigator surveyInvestigatorTwo=surveyInvestigatorMapper.selectByUserId(surveyInvestigatorCase.getSurveyUserId());
                if(surveyInvestigatorTwo != null){
                    surveyInvestigatorCase.setInvestigatorType(surveyInvestigatorTwo.getType() != null ?surveyInvestigatorTwo.getType():0);
                }
            }*/
            if (surveyInvestigatorCases.size() > 0) {
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCases.get(0).getSurveyAssorgCaseId());
                if (surveyAssignOrg != null) {
                    dto.setReportCompletionOrgName(surveyAssignOrg.getSurveyOrgName());
                }
                //根据机构ID分组，调查员数量大于1个的，则显示主调查员
                Map<Long, List<SurveyInvestigatorCase>> collect = surveyInvestigatorCases.stream().collect(Collectors.groupingBy(SurveyInvestigatorCase::getSurveyAssorgCaseId));
                collect.forEach((k1, k2) -> {
                    if (k2.size() > 1) {
                        k2.sort(Comparator.comparing(SurveyInvestigatorCase::getAcceptDate));
                        k2.get(0).setShowKey(true);
                    }
                });
            }

            List<Long> allOrgList = new ArrayList();
            if (surveyInvestigator != null) {
                Long curOrgId = surveyInvestigator.getOrgId();
                //父级机构，或者名下子级机构 (分派调查员info，调查员列表的改派，删除按钮控制显示隐藏) 2020年2月28日23:08:41
                allOrgList.add(curOrgId);
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(curOrgId);
                if (surveyFranchisee.getLevel() == 1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("parentId", curOrgId);
                    List<SurveyFranchisee> childList = surveyFranchiseeMapper.list(map);
                    for (int i = 0; i < childList.size(); i++) {
                        allOrgList.add(childList.get(i).getId());
                    }
                } else if (surveyFranchisee.getLevel() == 2) {
                    SurveyFranchisee parentFranchise = surveyFranchiseeMapper.selectByParentId(curOrgId);
                    allOrgList.add(parentFranchise.getId());
                }

            }

            //设置是否显示费用报销
            dto.setShowExpenseReimbursementValue(true);

            List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtos = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("#.00");
            for (SurveyInvestigatorCase surveyInvestigatorCase : surveyInvestigatorCases) {
                SurveyInvestigator surveyInvestigatorTwo = surveyInvestigatorMapper.selectByUserId(surveyInvestigatorCase.getSurveyUserId());
                SurveyInvestigatorCaseSub sub = surveyInvestigatorCaseSubMapper.selectByInvestigatorCaseId(surveyInvestigatorCase.getId());
                surveyInvestigatorCase.setSurveyInvestigatorCaseSub(sub);
                if (surveyInvestigatorTwo != null) {
                    surveyInvestigatorCase.setInvestigatorType(surveyInvestigatorTwo.getType() != null ? surveyInvestigatorTwo.getType() : 0);
                }
                SurveyInvestigatorCaseDto caseDto = new SurveyInvestigatorCaseDto();
                BeanUtils.copyProperties(caseDto, surveyInvestigatorCase);
                Double assessScore = surveyInvestigatorCase.getAssessScore() == null ? 0D : surveyInvestigatorCase.getAssessScore();//总分值
                Double assessSunScore = surveyInvestigatorCase.getAssessSunScore() == null ? 0D : surveyInvestigatorCase.getAssessSunScore();
                caseDto.setAssessBaseScore(DecimalUtil.twoDecimalTOFourFromFive(assessScore - assessSunScore));//基础分

                Double score = surveyInvestigatorCase.getScore() == null ? 0D : surveyInvestigatorCase.getScore();//总分值
                Double sunScore = surveyInvestigatorCase.getScoreSun() == null ? 0D : surveyInvestigatorCase.getScoreSun();
                caseDto.setBaseScore(DecimalUtil.twoDecimalTOFourFromFive(score - sunScore));//基础分
                caseDto.setSunScore(DecimalUtil.twoDecimalTOFourFromFive(sunScore));//阳性分
                caseDto.setCommonFile(commonFileMapper.selectByPrimaryKey(surveyInvestigatorCase.getCreportId()));
                caseDto.setTasks(surveyInvestigatorCaseTypeMapper.getSurveyInvestigatorCaseTypesByCaseId(surveyInvestigatorCase.getId()));
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                Long surveyCaseOrgId = surveyAssignOrg.getSurveyOrgId();
                if (surveyInvestigator == null) {
                    caseDto.setIsCurOrg(false);
                } else {

                    //父级机构，或者名下子级机构 (分派调查员info，调查员列表的改派，删除按钮控制显示隐藏) 2020年2月28日23:08:41
                    for (int i = 0; i < allOrgList.size(); i++) {
                        if (allOrgList.get(i).intValue() == surveyCaseOrgId.intValue()) {
                            caseDto.setIsCurOrg(true);
                            dto.setIsPrimaryUser(false);//说明调查员案件已存在改机构的案件
                            break;
                        } else {
                            caseDto.setIsCurOrg(false);
                        }
                    }

                    /* 2020年2月28日23:09:20
                    if (curOrgId.intValue() == surveyCaseOrgId.intValue()){
                        caseDto.setIsCurOrg(true);
                        dto.setIsPrimaryUser(false);//说明调查员案件已存在改机构的案件
                    }else{
                        caseDto.setIsCurOrg(false);
                    }*/
                }
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyCaseOrgId);
                caseDto.setSurveyFranchisee(surveyFranchisee);
                //调查员费用报销总额
                if (dto.getShowExpenseReimbursementValue()) {
//                    Double totalMoney = surveyInvestigatorCaseMapper.selectAllDirectionReimTotalMoneyById(caseDto.getId());
                    Double totalMoney = surveyClockCaseMapper.surveyInvCaseAllMoney(caseDto.getSurveyInfoId(), caseDto.getSurveyUserId());
                    caseDto.setInvestigatorReMoney(totalMoney);
                    if (totalMoney != null) {
                        if (dto.getTotalMoney() == null) {
                            dto.setTotalMoney(0d);
                        }
                        dto.setTotalMoney(Double.valueOf(df.format(dto.getTotalMoney() + totalMoney)));
                    }
                }
                //计算时效
//                SimpleDateFormat orgFormatter = new SimpleDateFormat("yyyy-MM-dd");
//                String assDate = orgFormatter.format(caseDto.getAssignDate());
//                String endDate = orgFormatter.format(caseDto.getSurveyEndTime());
//                String commitDate = "";
//                if(caseDto.getCreportDate()!=null){
//                    commitDate = orgFormatter.format(caseDto.getCreportDate());
//                }
//                Map<String,Object> resultInfo = surveyCaseUserDays(caseDto.getSurveyState(), assDate, commitDate, endDate);
//                caseDto.setEfficiencyState(resultInfo.get("efficiencyState").toString());
//                caseDto.setEfficiencyStateColor(resultInfo.get("efficiencyStateColor").toString());
                List<SurveyUserPrescriptionFlow> surveyUserPrescriptionFlowList = surveyUserPrescriptionFlowMapper.selectByInfoIdAndSurOrgIdInvId(dto.getId(), surveyAssignOrg.getId(), surveyInvestigatorCase.getId());
                //分配给调查员时间
                Date assDate = caseDto.getAssignDate() == null ? new Date() : caseDto.getAssignDate();
                //截止时间
                Date endDate = caseDto.getSurveyEndTime() == null ? new Date() : caseDto.getSurveyEndTime();
                //提交时间
                Date commitDate = caseDto.getCreportDate() == null ? new Date() : caseDto.getCreportDate();
                caseDto.setAssDay(Math.abs(GetWorkDay.calLeaveDays(assDate, endDate, efficiencyAttr)));//考核时效
                if (caseDto.getSurveyState() == 4) { //初审通过
//                    int days = GetWorkDay.calLeaveDays(assDate, commitDate,efficiencyAttr);
                    if (surveyInvestigatorCase.getAgingDay() == null) {
                        surveyInvestigatorCase.setAgingDay(0);
                    }
                    int days = surveyInvestigatorCase.getAgingDay();
                    days = Math.abs(days);
                    caseDto.setAgingDay(days);
//                    if(commitDate.compareTo(endDate) > 0){ //如果“提交时间” 超过“截止时间” 为红色
//                        caseDto.setEfficiencyState("时效"+days+"天");
                    if (caseDto.getAgingOver() > 0) {
                        caseDto.setEfficiencyState("时效" + surveyInvestigatorCase.getAgingReal().intValue() + "天");
                        caseDto.setEfficiencyStateColor("#e51c23");
//                        caseDto.setOveDay(Math.max(caseDto.getAgingDay() - caseDto.getAssDay(), 0));
                    } else {
//                        caseDto.setEfficiencyState("时效"+days+"天");
                        caseDto.setEfficiencyState("时效" + surveyInvestigatorCase.getAgingReal().intValue() + "天");
                        caseDto.setEfficiencyStateColor("#3ba9ff");
//                        caseDto.setOveDay(0);
                    }

                } else {
                    Date currentTime = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = formatter.format(currentTime);
                    int agingDay = GetWorkDay.calLeaveDays(assDate, new Date(), efficiencyAttr);
                    caseDto.setAgingDay(Math.abs(agingDay));
//                    if(endDate.compareTo(new Date()) > 0){ //未超时
                    if (caseDto.getAgingOver().intValue() == 0) {
                        int days = GetWorkDay.calLeaveDays(new Date(), endDate, efficiencyAttr);
                        days = Math.abs(days);
//                        Double days = caseDto.getAgingCheck()-caseDto.getAgingReal();
                        caseDto.setEfficiencyState("剩余" + days + "天");
                        caseDto.setEfficiencyStateColor("#3ba9ff");//蓝色
                        if (days <= 2 && days > 0) { //
                            caseDto.setEfficiencyStateColor("#ff9800");//黄色
                        }
//                        caseDto.setOveDay(0);
                    } else {
//                        int days = GetWorkDay.calLeaveDays(endDate,new Date(),efficiencyAttr);
//                        days = Math.abs(days);
                        caseDto.setEfficiencyState("超时" + caseDto.getAgingOver().intValue() + "天");
                        caseDto.setEfficiencyStateColor("#e51c23");
//                        caseDto.setOveDay(Math.abs(caseDto.getAgingDay()- caseDto.getAssDay()));
                    }
                }

                if (surveyUserPrescriptionFlowList != null && surveyUserPrescriptionFlowList.size() > 0) {
                    List<Map<String, Object>> userPreList = new ArrayList<>();
                    Map<String, Object> orgPreMap = new HashMap<>();
                    for (int i = 0; i < surveyUserPrescriptionFlowList.size(); i++) {
                        SurveyUserPrescriptionFlow surveyOrgPrescriptionFlow = surveyUserPrescriptionFlowList.get(i);
                        if ((i & 1) == 1) {//奇数
                            if (surveyOrgPrescriptionFlow.getEndTime() != null) {
                                orgPreMap.put("k2", "提交审核日期(第" + ((i >> 1) + 1) + "次):" + simpleDateFormat.format(surveyOrgPrescriptionFlow.getEndTime()));
                                orgPreMap.put("k3", "用时" + surveyOrgPrescriptionFlow.getDays() + "天");
                            }
                            userPreList.add(orgPreMap);
                        } else {//偶数
                            if (i == 0) {
                                orgPreMap.put("k1", "分派调查员日期:" + simpleDateFormat.format(surveyOrgPrescriptionFlow.getStartTime()));
                            } else {
                                orgPreMap = new HashMap<>();
                                orgPreMap.put("k1", "初审驳回日期(第" + (i >> 1) + "次):" + simpleDateFormat.format(surveyOrgPrescriptionFlow.getStartTime()));
                            }
                        }
                    }
                    caseDto.setUrgPreList(userPreList);
                }

                Boolean rateEdit = false;
                //调查员任务案件 判断是否显示超期考核绩效铅笔按钮 (互助案件 + 直营机构)
                if (surveyConsignor != null) {
//                    if (surveyConsignor.getOrgAttr() == 2) {
                    if (surveyFranchisee != null) {
                        if (surveyFranchisee.getType() == 1) {//直营
                            rateEdit = true;
                        }
                    }
//                    }
                }
                caseDto.setRateEdit(rateEdit);
                surveyInvestigatorCaseDtos.add(caseDto);
            }
            /*for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto:surveyInvestigatorCaseDtos) {
                SurveyFranchisee surveyFranchisee=surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigatorCaseDto.getSurveyOrgId());
                if(surveyFranchisee != null){
                    surveyInvestigatorCaseDto.setEntrustOrgType(surveyFranchisee.getInsuranceType());
                }
            }*/
            dto.setSurveyInvestigatorCases(surveyInvestigatorCaseDtos);
            if (surveyInvestigatorCases == null || surveyInvestigatorCases.size() == 0) {//分派 如果没有调查员案件信息 则默认是主调查员
                dto.setIsPrimaryUser(true);
            }


            //反馈清单
            Map map = new HashMap();
            map.put("surveyInfoId", dto.getId());
            List<SurveyBackReply> surveyBackReplies = surveyBackReplyMapper.getSurveyBackReplyBySurveyInfoId(dto.getId());
            dto.setSurveyBackReplies(surveyBackReplies);


            Map reviewOrgs = new HashMap();
            //已分配机构列表。
            Double surveyOKPrice1 = 0D, surveyOKPrice2 = 0D;
            Double orgPrice1 = 0D, orgPirce2 = 0D;
            map = new HashMap();
            map.put("surveyInfoId", dto.getId());
            List<SurveyAssignOrgDto> surveyAssignOrgs = surveyAssignOrgMapper.list(map);
            for (SurveyAssignOrgDto surveyAssignOrg : surveyAssignOrgs) {
                if (surveyInvestigator != null) {
                    if (surveyAssignOrg.getSurveyOrgId().intValue() == surveyInvestigator.getOrgId().intValue()) {
                        orgPrice1 = surveyAssignOrg.getSurveyMoneySubmit();
                        orgPirce2 = surveyAssignOrg.getSurveryReLossesSubmit();
                    }
                }

                //调查方确认结算价格 基本费 or 减损奖励
                surveyOKPrice1 += (surveyAssignOrg.getSurveyMoneySubmit() == null ? 0D : surveyAssignOrg.getSurveyMoneySubmit());
                surveyOKPrice2 += (surveyAssignOrg.getSurveryReLossesSubmit() == null ? 0D : surveyAssignOrg.getSurveryReLossesSubmit());

                surveyAssignOrg.setCommonFile(commonFileMapper.selectByPrimaryKey(surveyAssignOrg.getReportId()));
                //分配给机构时间
                Date createTime = surveyAssignOrg.getCreateTime() == null ? new Date() : surveyAssignOrg.getCreateTime();
                //机构截止时间
                Date endTime = surveyAssignOrg.getOrgEndTime() == null ? new Date() : surveyAssignOrg.getOrgEndTime();
                //提交初审通过时间
                Date reportDate = surveyAssignOrg.getReportDate() == null ? new Date() : surveyAssignOrg.getReportDate();
                surveyAssignOrg.setAssDay(Math.abs(GetWorkDay.calLeaveDays(createTime, endTime, efficiencyAttr)));
                if (surveyAssignOrg.getReportDate() != null) { //初审通过
//                    int days = GetWorkDay.calLeaveDays(createTime, reportDate,efficiencyAttr);
                    int days = surveyAssignOrg.getAgingDay();
                    days = Math.abs(days);
                    surveyAssignOrg.setAgingDay(days);
//                    if(reportDate.before(endTime)){ //未超时
                    if (surveyAssignOrg.getAgingOver().intValue() == 0) {
                        surveyAssignOrg.setEfficiencyState("时效：" + surveyAssignOrg.getAgingReal().intValue() + "天");
                        surveyAssignOrg.setEfficiencyStateColor("green");
//                        surveyAssignOrg.setOveDay(0);
                    } else {
                        surveyAssignOrg.setEfficiencyState("时效：" + surveyAssignOrg.getAgingReal().intValue() + "天");
                        surveyAssignOrg.setEfficiencyStateColor("red");
//                        surveyAssignOrg.setOveDay(Math.max(surveyAssignOrg.getAgingDay() - surveyAssignOrg.getAssDay(), 0));
                    }

                } else {
                    int agingDay = GetWorkDay.calLeaveDays(createTime, new Date(), efficiencyAttr);
                    surveyAssignOrg.setAgingDay(Math.abs(agingDay));
//                    if(new Date().before(endTime)){ //未超时
                    if (surveyAssignOrg.getAgingOver().intValue() == 0) {
                        int days = GetWorkDay.calLeaveDays(endTime, new Date(), efficiencyAttr);
                        days = Math.abs(days);
                        surveyAssignOrg.setEfficiencyState("剩余：" + days + "天");
                        surveyAssignOrg.setEfficiencyStateColor("#eda42e");
//                        surveyAssignOrg.setOveDay(0);
                    } else {
//                        int days = GetWorkDay.calLeaveDays(endTime, new Date(),efficiencyAttr);
//                        days = Math.abs(days);
                        surveyAssignOrg.setEfficiencyState("超时：" + surveyAssignOrg.getAgingOver().intValue() + "天");
                        surveyAssignOrg.setEfficiencyStateColor("red");
//                        surveyAssignOrg.setOveDay(Math.abs(surveyAssignOrg.getAgingDay()-surveyAssignOrg.getAssDay()));
                    }
                }

                List<SurveyOrgPrescriptionFlow> surveyOrgPrescriptionFlows = surveyOrgPrescriptionFlowMapper.selectByInfoIdAndSurOrgId(dto.getId(), surveyAssignOrg.getId());
                if (surveyOrgPrescriptionFlows != null && surveyOrgPrescriptionFlows.size() > 0) {
                    List<Map<String, Object>> orgPreList = new ArrayList<>();
                    Map<String, Object> orgPreMap = new HashMap<>();
                    for (int i = 0; i < surveyOrgPrescriptionFlows.size(); i++) {
                        SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = surveyOrgPrescriptionFlows.get(i);
                        if ((i & 1) == 1) {//奇数
                            if (surveyOrgPrescriptionFlow.getEndTime() != null) {
                                orgPreMap.put("k2", "机构提交日期(第" + ((i >> 1) + 1) + "次):" + simpleDateFormat.format(surveyOrgPrescriptionFlow.getEndTime()));
                                orgPreMap.put("k3", "用时" + surveyOrgPrescriptionFlow.getDays() + "天");
                            }
                            orgPreList.add(orgPreMap);
                        } else {//偶数
                            if (i == 0) {
                                orgPreMap.put("k1", "分派机构日期:" + simpleDateFormat.format(surveyOrgPrescriptionFlow.getStartTime()));
                            } else {
                                orgPreMap = new HashMap<>();
                                orgPreMap.put("k1", "复审驳回日期(第" + (i >> 1) + "次):" + simpleDateFormat.format(surveyOrgPrescriptionFlow.getStartTime()));
                            }
                        }
                    }
                    surveyAssignOrg.setOrgPreList(orgPreList);
                }

                if (surveyInvestigator == null) {
                    surveyAssignOrg.setIsCurOrg(false);
                } else {
                    if (surveyAssignOrg.getSurveyOrgId().intValue() == surveyInvestigator.getOrgId().intValue()) {
                        surveyAssignOrg.setIsCurOrg(true);
                    } else {
                        surveyAssignOrg.setIsCurOrg(false);
                    }
                }


                //逻辑判断 前台页面是否显示 审核操作 以及 铅笔操作
                Boolean review = false;
                if (surveyAssignOrg.getOrgSurveyState() == 4 && surveyAssignOrg.getReviewUserId() != null) {//如果是互助复核的详情
                    if (surveyAssignOrg.getReviewUserId().intValue() == userInfo.getUserId().intValue()) {
                        if (surveyAssignOrg.getReviewTime() == null) {
                            review = true;
                            reviewOrgs.put(surveyAssignOrg.getId(), surveyAssignOrg.getId());
                        }
                    } else if (userInfo.getUserId().intValue() == 477 || userInfo.getUserId().intValue() == 2189
                            || userInfo.getUserId().intValue() == 669
                            || userInfo.getUserId().intValue() == 26) {
                        if (surveyAssignOrg.getReviewTime() == null) {
                            review = true;
                            reviewOrgs.put(surveyAssignOrg.getId(), surveyAssignOrg.getId());
                        }
                    }

                }
                surveyAssignOrg.setReview(review);

                Boolean rateEdit = false;
                //机构案件  判断是否显示超期考核绩效铅笔按钮 (互助案件 + 非直营机构)
                if (surveyConsignor != null) {
//                    if (surveyConsignor.getOrgAttr() == 2) {
                    SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
                    if (surveyFranchisee != null) {
                        if (surveyFranchisee.getType() != 1) {//非直营
                            rateEdit = true;
                        }
                    }

//                    }
                }
                surveyAssignOrg.setRateEdit(rateEdit);

                map = new HashMap();
                map.put("surveyAssignOrgId", surveyAssignOrg.getId());
                surveyAssignOrg.setAssignOrgTypes(surveyAssignOrgTypeMapper.list(map));
            }
            if (surveyConsignor.getOrgAttr() == 1) {
                if (dto.getSurveyState() == 22) {
                    if (dto.getServicesId() == 12 || dto.getServicesId() == 11) {
                        for (SurveyAssignOrgDto surveyAssignOrg : surveyAssignOrgs) {
                            Map parmMap = new HashMap();
                            parmMap.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
                            parmMap.put("surveyOrgId", surveyAssignOrg.getSurveyOrgId());
                            Double clientPrice = 0D;
                            List<SurveyCaseDirection> surveyCaseDirectionDtoList = surveyCaseDirectionMapper.selectDirection(parmMap);
                            for (SurveyCaseDirection surveyCaseDirectionDto : surveyCaseDirectionDtoList) {
                                if (surveyCaseDirectionDto.getEntrustMoney() != null) {
                                    clientPrice = clientPrice + surveyCaseDirectionDto.getEntrustMoney();
                                }
                            }
                            surveyAssignOrg.setInscompanyMoney(clientPrice);
                            surveyAssignOrg.setInscompanySubmitMoney(clientPrice);
                        }
                    } else if (dto.getServicesId() == 13) {
                        Integer total = 0;
                        for (SurveyAssignOrgDto surveyAssignOrg : surveyAssignOrgs) {
                            Map parmMap = new HashMap();
                            parmMap.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
                            parmMap.put("surveyOrgId", surveyAssignOrg.getSurveyOrgId());
                            Integer count = surveyCaseDirectionMapper.selectTotalDirection(parmMap);
                            surveyAssignOrg.setTotalDirection(count);
                            total = total + count;
                        }
                        Double theSum = 0D;
                        Double clientPrice = 0D;
                        DecimalFormat dfs = new DecimalFormat("#.00");
                        for (int i = 0; i < surveyAssignOrgs.size(); i++) {
                            if (total == 0) {
                                surveyAssignOrgs.get(i).setInscompanyMoney(0D);
                                surveyAssignOrgs.get(i).setInscompanySubmitMoney(clientPrice);
                            } else {
                                if (i == surveyAssignOrgs.size() - 1) {
                                    if (dto.getEntrustMoney() == null) {
                                        dto.setEntrustMoney(0D);
                                    }
                                    clientPrice = dto.getEntrustMoney() - theSum;
                                    surveyAssignOrgs.get(i).setInscompanyMoney(Double.parseDouble(dfs.format(clientPrice)));
                                    surveyAssignOrgs.get(i).setInscompanySubmitMoney(Double.parseDouble(dfs.format(clientPrice)));
                                    break;
                                }
                                clientPrice = dto.getEntrustMoney() != null ? (dto.getEntrustMoney() * surveyAssignOrgs.get(i).getTotalDirection() / total) : 0;
                                clientPrice = Double.parseDouble(dfs.format(clientPrice));
                                theSum = theSum + clientPrice;
                                surveyAssignOrgs.get(i).setInscompanyMoney(clientPrice);
                                surveyAssignOrgs.get(i).setInscompanySubmitMoney(clientPrice);
                            }
                        }
                    }
                }
            }
            dto.setSurveyAssignOrgs(surveyAssignOrgs);
            //如果是报告复核的界面，则获取机构价格  机构价格则是所有调查员的价格
            dto.setOrgPrice1(orgPrice1);
            dto.setOrgPrice2(orgPirce2);

            //调查方确认结算价格 基本费 or 减损奖励
            dto.setSurveyOKMoney(surveyOKPrice1);
            dto.setSurveryOKReLosses(surveyOKPrice2);

            ApiRequest paramMap = new ApiRequest();
            paramMap.put("surveyInfoId", dto.getId());
            List<SurveyChannelCostNew> channelCostNews = surveyChannelCostNewMapper.list(paramMap);


            //调查方向清单
            map = new HashMap();
            map.put("surveyInfoId", dto.getId());
            map.put("orderByCondition", 1);//排序
            List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.list(map);
            List<SurveyCaseDirectionDto> surveyCaseDirectionDtos = new ArrayList<>();
            for (SurveyCaseDirection surveyCaseDirection : surveyCaseDirections) {
                SurveyCaseDirectionDto surveyCaseDirectionDto = new SurveyCaseDirectionDto();
                if (surveyCaseDirection.getHaveReimbursement() != null && surveyCaseDirection.getHaveReimbursement() == 1) {
                    String money = surveyReimbursementInfoMapper.selectSurveyReimbursementInfoOneMoneyByDirectionId(surveyCaseDirection.getId());
                    surveyCaseDirectionDto.setExpenseReimbursementValue(money);
                } else {
                    surveyCaseDirectionDto.setExpenseReimbursementValue("0");
                }

                BeanUtils.copyProperties(surveyCaseDirectionDto, surveyCaseDirection);
                SurveyInvestigatorCaseDto surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyAssorgCaseId());
                surveyInvestigatorCase.setIsCurOrg(false);
                if (surveyInvestigator != null && surveyAssignOrg != null) {
                    if (allOrgList.contains(surveyAssignOrg.getSurveyOrgId())) {
                        surveyInvestigatorCase.setIsCurOrg(true);
                    }
                }
                surveyInvestigatorCase.setSurveyFranchisee(surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId()));
                surveyCaseDirectionDto.setSurveyInvestigatorCase(surveyInvestigatorCase);

                //如果是互助机构
                StringBuffer huzhuColsRemark = new StringBuffer();
                if (surveyConsignor.getOrgAttr() == 2) {
                    String taskName = surveyCaseDirection.getTaskName();
                    if (taskName == null) {
                        taskName = "";
                    }
                    switch (taskName) {
                        case "面访患病成员及申请人":
                        case "面访患病成员家属":
                            if (surveyCaseDirection.getHuzhuDate() != null) {
                                huzhuColsRemark.append("面访时间：" + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                            }
                            huzhuColsRemark.append("\n面访对象与患病查员关系：" + (surveyCaseDirection.getAttr1Obj() == null ? "" : surveyCaseDirection.getAttr1Obj()));
                            huzhuColsRemark.append("\n面访对象姓名：" + (surveyCaseDirection.getAttr1ObjName() == null ? "" : surveyCaseDirection.getAttr1ObjName()));
                            huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirection.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirection.getSunRemark()) : "否"));
                            break;
                        case "走访就诊医疗机构":
                        case "走访出生医疗机构":
                        case "居住地医疗机构排查":
                        case "工作地医疗机构排查":
                        case "出险地医疗机构排查":
                        case "户籍所在地医疗机构排查":
                        case "走访街道办事处或村委会":
                            if (surveyCaseDirection.getHuzhuDate() != null) {
                                huzhuColsRemark.append("排查时间：" + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                            }
                            huzhuColsRemark.append("\n排查类型：" + (surveyCaseDirection.getAttr2Type() == null ? "" : surveyCaseDirection.getAttr2Type()));
                            huzhuColsRemark.append("\n排查科室：" + (surveyCaseDirection.getAttr2His() == null ? "" : surveyCaseDirection.getAttr2His()));
                            huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirection.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirection.getSunRemark()) : "否"));
                            break;
                        case "走访居住地":
                        case "走访户籍所在地":
                        case "走访工作单位":
                        case "走访疾控防疫中心":
                        case "走访公检法等机关单位":
                        case "走访鉴定机构":
                        case "走访材料出具机构":
                            if (surveyCaseDirection.getHuzhuDate() != null) {
                                huzhuColsRemark.append("走访时间：" + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                            }
                            huzhuColsRemark.append("\n走访对象：" + (surveyCaseDirection.getAttr3Obj() == null ? "" : surveyCaseDirection.getAttr3Obj()));
                            huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirection.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirection.getSunRemark()) : "否"));
                            break;
                        case "商保排查":
                            huzhuColsRemark.append("\n商保排查：" + (surveyCaseDirection.getAttr4Name1() == null ? "" : surveyCaseDirection.getAttr4Name1()));
//                            huzhuColsRemark.append("\n商保排查结论：" + (surveyCaseDirection.getAttr4Remark1() == null ? "" : surveyCaseDirection.getAttr4Remark1()));
                            huzhuColsRemark.append("\n互助排查：" + (surveyCaseDirection.getAttr4Name2() == null ? "" : surveyCaseDirection.getAttr4Name2()));
//                            huzhuColsRemark.append("\n互助排查结论：" + (surveyCaseDirection.getAttr4Remark2() == null ? "" : surveyCaseDirection.getAttr4Remark2()));
                            surveyCaseDirectionDto.setDirectionText("商保排查结论：" + (surveyCaseDirection.getAttr4Remark1() == null ? "" : surveyCaseDirection.getAttr4Remark1()) + "\n" +
                                    "互助排查结论：" + (surveyCaseDirection.getAttr4Remark2() == null ? "" : surveyCaseDirection.getAttr4Remark2()));
                            break;
                        case "社保排查":
                        case "药店排查":
                        case "体检机构排查": // 和 社保排查药店排查 。所有字段一样 所以写一起
                            if (surveyCaseDirection.getHuzhuDate() != null) {
                                huzhuColsRemark.append("排查时间：" + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                            }
                            huzhuColsRemark.append("\n是否阳性：" + (surveyCaseDirection.getSun() == 1 ? ("是" + "。阳性说明：" + surveyCaseDirection.getSunRemark()) : "否"));
                            break;
                        case "事故地点排查":
                        case "事故处理机构排查": // 和 事故地点排查 。所有字段一样 所以写一起
                            if (surveyCaseDirection.getHuzhuDate() != null) {
                                huzhuColsRemark.append("排查时间：" + new SimpleDateFormat("yyyy-MM-dd").format(surveyCaseDirection.getHuzhuDate()));
                            }
                            break;
                        default:
                            break;
                    }
                    huzhuColsRemark.append("\n是否有录音：" + (surveyCaseDirection.getHaveSound() != null ? (surveyCaseDirection.getHaveSound() == 1 ? "是" : "否") : ""));
                    if (taskName.equals("居住地医疗机构排查") || taskName.equals("走访就诊医疗机构") || taskName.equals("工作地医疗机构排查") || taskName.equals("社保排查")
                            || taskName.equals("体检机构排查") || taskName.equals("商保排查") || taskName.equals("药店排查") || taskName.equals("出险地医疗机构排查") || taskName.equals("户籍所在地医疗机构排查")) {
                        huzhuColsRemark.append("\n是否获得屏拍或纸质材料：" + (surveyCaseDirection.getHaveFile() != null ? (surveyCaseDirection.getHaveFile() == 1 ? "是" : "否") : ""));
                    }
                    surveyCaseDirectionDto.setHuzhuColsRemark(huzhuColsRemark.toString());
                }
                surveyCaseDirectionDto.setHuzhuSunStr(surveyCaseDirection.getSun() == 1 ? "是" : "否");


//                if (reviewOrgs.containsKey(surveyCaseDirection.getSurveyAssorgCaseId()) || (dto.getEntrustOrgId().intValue() != 105 && dto.getEntrustOrgId().intValue() != 82)){//只能审核 自己所负责机构的方向
                if (reviewOrgs.containsKey(surveyCaseDirection.getSurveyAssorgCaseId()) || surveyConsignor.getOrgAttr() != 2) {//只能审核 自己所负责机构的方向
                    surveyCaseDirectionDto.setReview(true);
                }

                //如果该方向是渠道费用。则查询渠道费用审核状态。
                if (surveyCaseDirection.getChannelType() == null) {
                    surveyCaseDirection.setChannelType(0);
                }
                if (surveyCaseDirection.getChannelType() == 1) {
                    for (SurveyChannelCostNew channelCostNew : channelCostNews) {
                        if (surveyCaseDirection.getId().intValue() == channelCostNew.getSurveyDirectionId().intValue()) {
                            surveyCaseDirectionDto.setSurveyChannelCostNew(channelCostNew);
                            break;
                        }
                    }
                }


                Map appendMap = new HashMap();
                appendMap.put("taskInfoContentId", surveyCaseDirection.getNewId());
                appendMap.put("directionResultTypeId", surveyCaseDirection.getDirectionResultTypeId());
                SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(appendMap);
                if (surveyTaskDirectionResult != null) {
                    //获取分值系数 2021年3月30日
                    Double scoreRate = 1D;
                    SurveyScoreModelOrg surveyScoreModelOrg = surveyScoreModelOrgMapper.selectOne(surveyAssignOrg.getSurveyOrgId());
                    if (surveyScoreModelOrg != null) {
                        Map<String, Object> paramMapTemp = new HashMap<String, Object>();
                        paramMapTemp.put("modelId", surveyScoreModelOrg.getModelId());
                        int areaType = surveyCaseDirection.getAreaType() == null ? 1 : surveyCaseDirection.getAreaType();
                        Integer areaId = -1;
                        if (areaType == 2 || areaType == 3) {// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                            areaId = surveyCaseDirection.getCityId();
                        } else {
                            areaId = surveyCaseDirection.getDistrictId();
                        }
                        paramMapTemp.put("areaId", areaId);
                        SurveyScoreModelInfo surveyScoreModelInfo = surveyScoreModelInfoMapper.selectByParam(paramMapTemp);
                        if (surveyScoreModelInfo != null) {
                            scoreRate = surveyScoreModelInfo.getScoreRate() == null ? 0D : surveyScoreModelInfo.getScoreRate();
                        }
                    }
                    surveyCaseDirectionDto.setScoreRate(scoreRate);
                }
                surveyCaseDirectionDtos.add(surveyCaseDirectionDto);
            }
            dto.setSurveyCaseDirections(surveyCaseDirectionDtos);

            List<SurveyFeeDetails> feeDetails = new ArrayList<>();
            dto.setSurveyFeeDetails(feeDetails);


            Long assignOrgId = apiRequest.getLong("assignOrgId");
            if (assignOrgId != null) {
                SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(assignOrgId);
                dto.setCurrentSurveyAssignOrg(surveyAssignOrg);
                //如果是新案件。则更新
                if ("assign-org-list".equals(menuCode)) {
                    if (surveyInvestigator != null) {
                        if (surveyInvestigator.getOrgId().intValue() == surveyAssignOrg.getSurveyOrgId().intValue()) {
                            if (surveyAssignOrg.getNewCase() == 1) {
                                surveyAssignOrg.setNewCase(0);
                                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                            }
                        }
                    }
                }
                dto.setCommonFile(commonFileMapper.selectByPrimaryKey(surveyAssignOrg.getReportId()));
            }

            Long backCaseId = apiRequest.getLong("backCaseId");
            if (backCaseId != null) {
                SurveyBackCaseDto surveyBackCase = surveyBackCaseMapper.selectByPrimaryKey(backCaseId);
                dto.setCurrentSurveyBackCase(surveyBackCase);
                map = new HashMap();
                map.put("surveyOrgId", surveyBackCase.getOrgId());
                map.put("surveyInfoId", dto.getId());
                SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
                dto.setCurrentSurveyAssignOrg(surveyAssignOrg);
            }
            if ("113".equals(btnCode) || "116".equals(btnCode)) {
                Long surveyCaseId = apiRequest.getLong("surveyCaseId");
                if (surveyCaseId != null) {
                    SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseId);
                    if (surveyInvestigator != null) {
//                        map = new HashMap();
//                        map.put("surveyOrgId",surveyInvestigatorCase.getSurveyOrgId());
//                        map.put("surveyInfoId",dto.getId());
//                        SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
                        SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
                        dto.setCurrentSurveyAssignOrg(surveyAssignOrg);
                    } else {
                        dto.setCurrentSurveyAssignOrg(new SurveyAssignOrgDto());
                    }
                }
            }
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            dto.setLfSuper(isRoleUser(userRoles, 52L));
            dto.setLfManager(isRoleUser(userRoles, 53L));
            dto.setLfUpload(isRoleUser(userRoles, 60L));//终审上传报告
            dto.setLfCommit(isRoleUser(userRoles, 53L));//终审提交审核
            dto.setSurveyAgentEntrust(isRoleUser(userRoles, 56L));//调查代理委托人权限
            if ("2189".equals(currentUserId.toString())) {
                dto.setLxfTest(true);
            }
            //开票信息
            if ("account-list".equals(menuCode) || "all-list".equals(menuCode) || "help-review".equals(menuCode) || "agent-entrust-list".equals(menuCode) || "entrust-list".equals(menuCode)) {
                map = new HashMap<>();
                map.put("caseNo", dto.getSurveyCno());
                BillingApply billingApply = billingApplyMapper.selectByInfo(map);
                Boolean isBatch = false;// true 批量开票  false 单个开票
                if (billingApply == null) {//批量开票 取中间表
                    Long billId = null;
                    map = new HashMap<>();
                    map.put("riskCaseInfoId", dto.getId());
                    List<SurveyBillingApply> applyList = surveyBillingApplyMapper.selectByInfo(map);
                    if (applyList != null) {
                        if (applyList.size() > 0) {
                            billId = applyList.get(0).getBillId();
                        }
                    }
                    if (billId != null) {
                        billingApply = billingApplyMapper.selectByPrimaryKey(billId);
                    }
                    isBatch = true;
                }
                if (billingApply != null) {
                    List<BillingApplyImgs> imgs = billingApplyImgsMapper.selectListByBillId(billingApply.getId());
                    Double imgsMoney = 0D;
                    Double accountsMoney = 0D;
                    for (int i = 0; i < imgs.size(); i++) {
                        imgsMoney += imgs.get(i).getBillingMoney();
                    }
                    List<BillingApplyAccounts> accounts = billingApplyAccountsMapper.selectListByBillId(billingApply.getId());
                    for (int i = 0; i < accounts.size(); i++) {
                        accountsMoney += accounts.get(i).getMoney();
                    }
                    billingApply.setBillingMoney(imgsMoney);
                    billingApply.setConfirmAccountMoney(accountsMoney);

                    if (isBatch) {//如果是批量开票  则默认取 委托方确认结算价格
                        if (dto.getEntrustOkPrice1() == null) {
                            dto.setEntrustOkPrice1(0D);
                        }
                        if (dto.getEntrustOkPrice2() == null) {
                            dto.setEntrustOkPrice2(0D);
                        }
                        Double money = dto.getEntrustOkPrice1() + dto.getEntrustOkPrice2();
                        billingApply.setBillingMoney(money);
                        billingApply.setConfirmAccountMoney(money);
                    }
                    dto.setBillingApply(billingApply);
                }
            }

            //判断是否已经开票
            map = new HashMap();
            map.put("riskCaseInfoId", dto.getId());
            List<SurveyBillingApply> surveyBillingApplies = surveyBillingApplyMapper.selectByInfo(map);
            if (surveyBillingApplies != null && surveyBillingApplies.size() > 0) {
                dto.setOpenBill(true);
            } else {
                dto.setOpenBill(false);
            }


            //获取二调信息（该发起几调）
            if ("my-list".equals(menuCode) || "all-list".equals(menuCode)) {
                if (surveyRiskCase.getTransferType() != null) { //不是顶级关联id
                    //查询二调记录
                    SurveyRiskCaseTransfer transfer = surveyRiskCaseTransferMapper.selectBySurveyId(dto.getId());
                    if (transfer != null) {
                        map = new HashMap<>();
                        //通过顶级id，获取所有关联数据
                        map.put("surveyParentId", transfer.getSurveyParentId());
                        List<SurveyRiskCaseTransfer> transferList = surveyRiskCaseTransferMapper.list(map);
                        if (transferList.size() > 0) {
                            //判断所有关联数据，是否所有的“当前状态”都为“保司通过审核之后”
                            for (SurveyRiskCaseTransfer surveyRiskCaseTransfer : transferList) {
                                SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyRiskCaseTransfer.getSurveyId());
                                if (info != null) {
                                    if (info.getEntrustReportEndDate() == null) {
                                        surveyRiskCase.setIsShowTransfer(false);
                                        break;
                                    }
                                }
                            }
                            surveyRiskCase.setTopSurveyId(transferList.get(0).getSurveyParentId());
                            surveyRiskCase.setNextTransferType(transferList.get(0).getTransferType() + 1);
                            surveyRiskCase.setNextTransferName(ConvertToBeanUtil.toChinese(String.valueOf(transferList.get(0).getTransferType() + 1)));
                        }
                    }
                } else { //是顶级关联id
                    map = new HashMap<>();
                    map.put("surveyParentId", dto.getId());
                    //查询名下是否有关联的二调
                    List<SurveyRiskCaseTransfer> transferList = surveyRiskCaseTransferMapper.list(map);
                    if (transferList.size() > 0) {
                        //判断所有关联数据，是否所有的“当前状态”都为“保司通过审核之后”
                        for (SurveyRiskCaseTransfer surveyRiskCaseTransfer : transferList) {
                            SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyRiskCaseTransfer.getSurveyId());
                            if (info != null) {
                                if (info.getEntrustReportEndDate() == null) {
                                    surveyRiskCase.setIsShowTransfer(false);
                                    break;
                                }
                            }
                        }
                        surveyRiskCase.setTopSurveyId(transferList.get(0).getSurveyParentId());
                        surveyRiskCase.setNextTransferType(transferList.get(0).getTransferType() + 1);
                        surveyRiskCase.setNextTransferName(ConvertToBeanUtil.toChinese(String.valueOf(transferList.get(0).getTransferType() + 1)));
                    } else {
                        //没有记录，说明第一次是“二调”
                        surveyRiskCase.setTopSurveyId(dto.getId());
                        surveyRiskCase.setNextTransferType(2);
                        surveyRiskCase.setNextTransferName(ConvertToBeanUtil.toChinese(String.valueOf(2)));
                    }
                }
                if ("transfer".equals(btnCode)) {//发起二调时，返回数据：委托时间为当前，案件截止日期为空
                    surveyRiskCase.setEntrustTime(new Date());
                    dto.setEndTime(null);
                }
                dto.setSurveyRiskCase(surveyRiskCase);
            }


            map = new HashMap<String, Long>();
            map.put("surveyInfoId", dto.getId());
            List<SurveyCaseFileDto> files = surveyCaseFileMapper.list(map);
            dto.setFileSize(files.size());

            //机构主动退回的原因
            if ("assign-list".equals(menuCode)) {
                String reason = "";
                map = new HashMap<String, Object>();
                map.put("surveyInfoId", id);
                List<SurveyAssignOrg> returnAssigns = surveyAssignOrgMapper.selectSurveyAssignOrgByOrgOpinion(map);
//                for (SurveyAssignOrg surveyAssignOrg : returnAssigns) {
//                    reason += "[" + surveyAssignOrg.getSurveyOrgName()+"]："+surveyAssignOrg.getOrgOpinion() + "\n";
//                }
//                dto.setOrgReturnReason(reason);
                dto.setReturnAssignOrgs(returnAssigns);
            }
            //调查员主动退回的原因
            if ("assign-org-list".equals(menuCode)) {
                String reason = "";
                map = new HashMap<String, Object>();
                map.put("surveyInfoId", id);
                map.put("surveyOrgId", dto.getSurveyOrgId());
                List<SurveyInvestigatorCase> returnCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyRemark(map);
//                for (SurveyInvestigatorCase surveyInvestigatorCase : returnCases) {
//                    reason += "[" + surveyInvestigatorCase.getSurveyUserName()+"]："+surveyInvestigatorCase.getSurveyRemark() + "\n";
//                }
//                dto.setSurveyReturnReason(reason);
                dto.setReturnInvestigatorCases(returnCases);
            }

            dto.setHelp(false);
            if (dto.getEntrustOrgId() != null) {
                if (surveyConsignor.getOrgAttr() == 2) {
//                    if (dto.getEntrustOrgId().intValue() == 105 || dto.getEntrustOrgId().intValue() == 82){
                    dto.setHelp(true);
                }
            }

            //归档管理
            map = new HashMap<String, Object>();
            map.put("surveyInfoId", id);
            SurveyCaseArchives surveyCaseArchives = surveyCaseArchivesMapper.selectOne(map);
            if (surveyCaseArchives != null) {
                dto.setArchivesState(surveyCaseArchives.getArchivesState());
            }

            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


    private Boolean isRoleUser(List<BusUserRole> busUserRoles, Long roleId) {
        for (BusUserRole busUserRole : busUserRoles) {
            if (busUserRole.getRoleId().intValue() == roleId.intValue()) {
                return true;
            }
        }
        return false;
    }

    @ApiMethod(needLogin = false, descript = "子案件处理", value = "operate-survey-risk-case-info")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        try {
            Long id = apiRequest.getLong("id");
            String btnCode = apiRequest.getString("btnCode");
            if ("updOrgSurveyMoney".equals(btnCode) || "updOrgSurveyReview".equals(btnCode)
                    || "updClientPrice".equals(btnCode) || "org-aging-rate".equals(btnCode) || "updReportCompletion".equals(btnCode) || "mark-error".equals(btnCode) || "qx-mark-error".equals(btnCode)) {// 乐凡修改机构案件价格 则ID取调查员案件ID
                id = apiRequest.getLong("surveyInfoId");
            } else if ("match-address".equals(btnCode)) {
                return backendCommonAreaApi.matchAddress(apiRequest);
            } else if ("getTaskResults".equals(btnCode)) {
                Map<String, Object> searchMap = new HashMap<>();
                searchMap.put("taskInfoContentId", apiRequest.getLong("newId"));
                List<SurveyTaskDirectionResult> results = surveyTaskDirectionResultMapper.list(searchMap);
                return new ApiResponse(ApiMsgEnum.SUCCESS, results.size(), results);
            }
            //sssss
            Long currentUserId = getCurrentUserId(apiRequest);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(id);
            String opinion = apiRequest.getString("opinion");
            if ("mark".equals(btnCode)) {
                Integer type = apiRequest.getInt("type");//1 批量标记基本费   2批量标记减损奖励
                return markBas(apiRequest, userInfo, type);
            } else if ("del-file".equals(btnCode)) {
                SurveyCaseFile surveyCaseFile = surveyCaseFileMapper.selectByPrimaryKey(apiRequest.getLong("fileId"));
                surveyCaseFile.setDeleteFlag(1);
                surveyCaseFileMapper.updateByPrimaryKey(surveyCaseFile);
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, surveyCaseFile);
            }
            if ("500".equals(btnCode)) {

            } else if ("ajaxRecordData".equals(btnCode)) {
                Map<String, Object> recordMap = new HashMap<String, Object>();
                recordMap.put("surveyInfoId", surveyRiskCaseInfo.getId());
                List<SurveyAttrUpdRecord> records = surveyAttrUpdRecordMapper.list(recordMap);
                return new ApiResponse(ApiMsgEnum.SUCCESS, records.size(), records);
            } else if ("1000".equals(btnCode)) {//发送客服补充信息
                surveyRiskCaseInfo.setSupplementState(1);
            } else if ("1001".equals(btnCode)) {
                Date endTime = DateUtils.parseDate(apiRequest.getString("endTime"), "yyyy-MM-dd HH:mm:ss");
                String endTimeRemark = apiRequest.getString("endTimeRemark");
                String beforeValue = surveyRiskCaseInfo.getEndTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(surveyRiskCaseInfo.getEndTime());
                surveyRiskCaseInfo.setEndTime(endTime);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfo.getEndTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(surveyRiskCaseInfo.getEndTime()));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_end_time");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(endTimeRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("updInsureTime".equals(btnCode)) {
                Date insureTime = DateUtils.parseDate(apiRequest.getString("insureTime"), "yyyy-MM-dd");
                String insureTimeRemark = apiRequest.getString("insureTimeRemark");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());

                String beforeValue = surveyRiskCase.getInsureTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(surveyRiskCase.getInsureTime());
                surveyRiskCase.setInsureTime(insureTime);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getInsureTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(surveyRiskCase.getInsureTime()));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_insure_time");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(insureTimeRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("cooperativeCompany".equals(btnCode)) {
                String cooperativeCompany = apiRequest.getString("cooperativeCompany");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getCooperativeCompany();
                surveyRiskCase.setCooperativeCompany(cooperativeCompany);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(StringUtils.defaultString(beforeValue));
                record.setUpdAfterValue(StringUtils.defaultString(cooperativeCompany));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_cooperative_company");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("subsidiaryCompany".equals(btnCode)) {
                String subsidiaryCompany = apiRequest.getString("subsidiaryCompany");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getSubsidiaryCompany();

                surveyRiskCase.setSubsidiaryCompany(subsidiaryCompany);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(StringUtils.defaultString(beforeValue));
                record.setUpdAfterValue(StringUtils.defaultString(subsidiaryCompany));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_subsidiary_company");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("healthInsuranceCompany".equals(btnCode)) {
                String healthInsuranceCompany = apiRequest.getString("healthInsuranceCompany");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getHealthInsuranceCompany();

                surveyRiskCase.setHealthInsuranceCompany(healthInsuranceCompany);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(StringUtils.defaultString(beforeValue));
                record.setUpdAfterValue(StringUtils.defaultString(healthInsuranceCompany));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_health_insurance_company");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("taskNumber".equals(btnCode)) {
                String taskNumber = apiRequest.getString("taskNumber");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getTaskNumber();

                surveyRiskCase.setTaskNumber(taskNumber);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(StringUtils.defaultString(beforeValue));
                record.setUpdAfterValue(StringUtils.defaultString(taskNumber));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_task_number");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("updDangerTime".equals(btnCode)) {
                Date dangerTime = DateUtils.parseDate(apiRequest.getString("dangerTime"), "yyyy-MM-dd");
                String dangerTimeRemark = apiRequest.getString("dangerTimeRemark");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());

                String beforeValue = surveyRiskCase.getDangerTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(surveyRiskCase.getDangerTime());
                surveyRiskCase.setDangerTime(dangerTime);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getDangerTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(surveyRiskCase.getDangerTime()));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_danger_time");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(dangerTimeRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("1100".equals(btnCode)) {//客服补充信息完成
                Date endTime = DateUtils.parseDate(apiRequest.getString("endTime"), "yyyy-MM-dd HH:mm:ss");
                apiRequest.put("endTime", endTime);
                surveyRiskCaseInfo = ConvertToBeanUtil.toBean(apiRequest, surveyRiskCaseInfo);
                surveyRiskCaseInfo.setSupplementState(2);
            } else if ("1008".equals(btnCode)) {
                String surveyPerson = apiRequest.getString("surveyPerson");
                String surveyPersonRemark = apiRequest.getString("surveyPersonRemark");
                String surveryPersonTel = apiRequest.getString("surveryPersonTel");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getSurveyPerson();
                surveyRiskCase.setSurveyPerson(surveyPerson);
                surveyRiskCase.setSurveryPersonTel(surveryPersonTel);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getSurveyPerson() == null ? "" : surveyRiskCase.getSurveyPerson());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_survey_person");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(surveyPersonRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

            } else if ("1119".equals(btnCode)) {
                String surveryPersonTel = apiRequest.getString("surveryPersonTel2");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getSurveryPersonTel();
                surveyRiskCase.setSurveryPersonTel(surveryPersonTel);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);
                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getSurveryPersonTel() == null ? "" : surveyRiskCase.getSurveryPersonTel());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_survey_person_tel");
                    record.setUpdTime(new Date());
                    record.setUpdRemark("");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("1009".equals(btnCode)) {
                String idNumber = apiRequest.getString("idNumber");
                Pattern pattern = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
                Boolean isNumber = pattern.matcher(idNumber).matches();
                if (!isNumber) {
                    return new ApiResponse(ApiMsgEnum.SURVEY_ID_NUMBER_ERROR);
                }

                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getIdNumber();
                surveyRiskCase.setIdNumber(idNumber);
                //同时修改性别、年龄
                surveyRiskCase.setSex("男".equals(IDCardUtil.getSex(idNumber)) ? 1 : 2);
                surveyRiskCase.setAge(IDCardUtil.getAge(idNumber));
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getIdNumber() == null ? "" : surveyRiskCase.getIdNumber());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_id_number");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("updDangerAddress".equals(btnCode)) {
                String dangerAddress = apiRequest.getString("dangerAddress");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getDangerAddress();
                surveyRiskCase.setDangerAddress(dangerAddress);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getDangerAddress() == null ? "" : surveyRiskCase.getDangerAddress());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_danger_address");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("updHzContactName".equals(btnCode)) {
                String hzContactName = apiRequest.getString("hzContactName");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getHzContactName();
                surveyRiskCase.setHzContactName(hzContactName);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getHzContactName() == null ? "" : surveyRiskCase.getHzContactName());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_hz_contact_name");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("updHzContactTel".equals(btnCode)) {
                String hzContactTel = apiRequest.getString("hzContactTel");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getHzContactTel();
                surveyRiskCase.setHzContactTel(hzContactTel);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getHzContactTel() == null ? "" : surveyRiskCase.getHzContactTel());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_hz_contact_tel");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            } else if ("updInsureName".equals(btnCode)) {
                String insureName = apiRequest.getString("insureName");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getInsureName();
                surveyRiskCase.setInsureName(insureName);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getInsureName() == null ? "" : surveyRiskCase.getInsureName());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_insure_name");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }  else if ("updMainInsurance".equals(btnCode)) {
                String mainInsurance = apiRequest.getString("mainInsurance");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getMainInsurance();
                surveyRiskCase.setMainInsurance(mainInsurance);
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getMainInsurance() == null ? "" : surveyRiskCase.getMainInsurance());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_main_insurance");
                    record.setUpdTime(new Date());
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
            }else if ("1200".equals(btnCode)) {//案件审核通过
                //非授信的 验证预付金额不能为0
                if (surveyRiskCaseInfo.getEntrustCredit() == null) {
                    surveyRiskCaseInfo.setEntrustCredit(1);
                }
                if (surveyRiskCaseInfo.getEntrustCredit() == 0) {
                    if (surveyRiskCaseInfo.getEntrustCreditMoney() == 0D) {
                        return new ApiResponse(ApiMsgEnum.SURVEY_CREDIT_MONEY);
                    }
                    surveyRiskCaseInfo.setEntrustCreditIsPay(0);//预付费为 待支付
                }
                surveyRiskCaseInfo.setOpinion(null);
                surveyRiskCaseInfo.setSurveyPhase(2);
                surveyRiskCaseInfo.setSurveyState(4);
                surveyRiskCaseInfo.setSurveyStateName("待分派");
                surveyRiskCaseInfo.setEntrustEndDate(new Date());
                SurveyCaseWorkflow surveyCaseWorkflow = new SurveyCaseWorkflow();
                //增加统计工作流
                surveyCaseWorkflowApi.addSurveyCaseWorkflow("案件受理", userInfo, surveyRiskCaseInfo.getCreateTime(), new Date(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());
            } else if ("1201".equals(btnCode)) {//案件审核不通过
                surveyRiskCaseInfo.setOpinion(opinion);
                surveyRiskCaseInfo.setSurveyState(6);
                surveyRiskCaseInfo.setSurveyStateName("受理不通过");

                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                //受理不通过  给创建人发消息
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=my-list";
                backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(), userInfo.getUserName(), surveyRiskCase.getEntrustUserId(), surveyRiskCase.getEntrustUserName(), 4, "案件退回同通知",
                        "你有新的任务待接收，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyRiskCaseInfo.getEndTime()), url);

            } else if ("1270".equals(btnCode)) {
                surveyRiskCaseInfo.setEntrustCreditIsPay(1);
            } else if ("1300".equals(btnCode) || "survey-report-opr".equals(btnCode) || "13001".equals(btnCode)) {
                try {
                    Date oprTime = DateUtils.parseDate(apiRequest.getString("oprTime"), "yyyy-MM-dd HH:mm:ss");
                    surveyRiskCaseInfo.setEntrustReportStartDate(oprTime);
                } catch (Exception e) {
                    e.printStackTrace();
                    surveyRiskCaseInfo.setEntrustReportStartDate(new Date());
                }
                String remark = apiRequest.getString("remark");
                surveyRiskCaseInfo.setLefanReportRemark(remark);
                opinion = remark;
                surveyRiskCaseInfo.setOpinion(null);
                surveyRiskCaseInfo.setSurveyState(24);
                surveyRiskCaseInfo.setSurveyStateName("保司终审中");
                //判断是否已经开票
                Map<String, Object> map = new HashMap();
                map.put("riskCaseInfoId", surveyRiskCaseInfo.getId());
                List<SurveyBillingApply> surveyBillingApplies = surveyBillingApplyMapper.selectByInfo(map);
                if (surveyBillingApplies != null && surveyBillingApplies.size() > 0) {//如果已开票就不管

                } else {
                    surveyRiskCaseInfo.setEntrustOkPrice1(surveyRiskCaseInfo.getEntrustMoney());//默认 委托方确认的金额为 乐凡申请的金额
                    surveyRiskCaseInfo.setEntrustOkPrice2(surveyRiskCaseInfo.getEntrustReLosses());
                }

                Double entrustOkPrice1 = surveyRiskCaseInfo.getEntrustOkPrice1() == null ? 0D : surveyRiskCaseInfo.getEntrustOkPrice1();
                Double entrustOkPrice2 = surveyRiskCaseInfo.getEntrustOkPrice2() == null ? 0D : surveyRiskCaseInfo.getEntrustOkPrice2();
                surveyRiskCaseInfo.setBillingMoney(entrustOkPrice1 + entrustOkPrice2);
                surveyRiskCaseInfo.setConfirmAccountMoney(entrustOkPrice1 + entrustOkPrice2);

                String generateReportPath = apiRequest.getString("generateReportPath");
                if (generateReportPath != null) {
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

                if ("1300".equals(btnCode)) {//保司复审通过
                    map = new HashMap();
                    map.put("surveyInfoId", surveyRiskCaseInfo.getId());
                    List<SurveyAssignOrgDto> surveyAssignOrgs = surveyAssignOrgMapper.list(map);
                    if (surveyRiskCaseInfo.getServicesId() == 12 || surveyRiskCaseInfo.getServicesId() == 11) {
                        for (SurveyAssignOrgDto surveyAssignOrg : surveyAssignOrgs) {
                            Map parmMap = new HashMap();
                            parmMap.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
                            parmMap.put("surveyOrgId", surveyAssignOrg.getSurveyOrgId());
                            Double clientPrice = 0D;
                            List<SurveyCaseDirection> surveyCaseDirectionDtoList = surveyCaseDirectionMapper.selectDirection(parmMap);
                            for (SurveyCaseDirection surveyCaseDirectionDto : surveyCaseDirectionDtoList) {
                                if (surveyCaseDirectionDto.getEntrustMoney() != null) {
                                    clientPrice = clientPrice + surveyCaseDirectionDto.getEntrustMoney();
                                }
                            }
                            Map findMap = new HashMap();
                            findMap.put("id", surveyAssignOrg.getId());
                            findMap.put("inscompanyMoney", clientPrice);
                            surveyAssignOrgMapper.updateInscompanyMoney(findMap);
                        }
                    } else if (surveyRiskCaseInfo.getServicesId() == 13) {
                        Integer total = 0;
                        for (SurveyAssignOrgDto surveyAssignOrg : surveyAssignOrgs) {
                            Map parmMap = new HashMap();
                            parmMap.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
                            parmMap.put("surveyOrgId", surveyAssignOrg.getSurveyOrgId());
                            Integer count = surveyCaseDirectionMapper.selectTotalDirection(parmMap);
                            surveyAssignOrg.setTotalDirection(count);
                            total = total + count;
                        }
                        Double theSum = 0D;
                        Double clientPrice = 0D;
                        DecimalFormat dfs = new DecimalFormat("#.00");
                        for (int i = 0; i < surveyAssignOrgs.size(); i++) {
                            if (total == 0) {

                            } else {
                                if (i == surveyAssignOrgs.size() - 1) {
                                    if (surveyRiskCaseInfo.getEntrustMoney() == null) {
                                        surveyRiskCaseInfo.setEntrustMoney(0D);
                                    }
                                    clientPrice = surveyRiskCaseInfo.getEntrustMoney() - theSum;
                                    Map findMap = new HashMap();
                                    findMap.put("id", surveyAssignOrgs.get(i).getId());
                                    findMap.put("inscompanyMoney", Double.parseDouble(dfs.format(clientPrice)));
                                    surveyAssignOrgMapper.updateInscompanyMoney(findMap);
                                    break;
                                }
                                clientPrice = surveyRiskCaseInfo.getEntrustMoney() != null ? (surveyRiskCaseInfo.getEntrustMoney() * surveyAssignOrgs.get(i).getTotalDirection() / total) : 0;
                                clientPrice = Double.parseDouble(dfs.format(clientPrice));
                                theSum = theSum + clientPrice;
                                Map findMap = new HashMap();
                                findMap.put("id", surveyAssignOrgs.get(i).getId());
                                findMap.put("inscompanyMoney", clientPrice);
                                surveyAssignOrgMapper.updateInscompanyMoney(findMap);
                            }
                        }
                    }
                }

                if ("13001".equals(btnCode)) {//互助
                    SurveyHelpInfo surveyHelpInfo = ConvertToBeanUtil.toBean(apiRequest, SurveyHelpInfo.class);
                    surveyHelpInfo.setId(null);
                    surveyHelpInfo.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    if ("否".equals(surveyHelpInfo.getSun())) {
                        surveyHelpInfo.setSunType(null);
                    }
                    surveyRiskCaseInfo.setReportCompletion(surveyHelpInfo.getSurveyAllRemark());
                    surveyHelpInfoMapper.insert(surveyHelpInfo);

                    //获取“案件类型”
                    surveyRiskCaseInfo.setCaseState(findCaseState(surveyRiskCaseInfo));
                }


                //发送机构消息通知
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                Map<String, Long> map1 = new HashMap<String, Long>();
                map1.put("surveyInfoId", surveyRiskCaseInfo.getId());
                List<SurveyAssignOrgDto> assignOrgs = surveyAssignOrgMapper.list(map1);
                for (SurveyAssignOrgDto assignOrg : assignOrgs) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                    String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=org-review-list&assignOrgId=" + assignOrg.getId();
                    Map<String, Long> paramMap = new HashMap<String, Long>();
                    paramMap.put("roleId", 58L);
                    paramMap.put("orgId", assignOrg.getSurveyOrgId());
                    List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                    backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(), userInfo.getUserName(), toUsers, 4, "平台复审通过通知",
                            "你有案件平台复审通过，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，案件截止日期：" + simpleDateFormat.format(assignOrg.getOrgEndTime()), url);
                }

                surveyCaseWorkflowApi.addSurveyCaseWorkflow("平台复审", userInfo, surveyRiskCaseInfo.getLefanReportDate(), surveyRiskCaseInfo.getEntrustReportStartDate(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());
            } else if ("1301".equals(btnCode)) {
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String chkOrgIds = apiRequest.getString("chkOrgIds");
                if (!"".equals(chkOrgIds)) {
                    String[] orgIds = chkOrgIds.split(",");
                    for (String orgId : orgIds) {
                        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(Long.parseLong(orgId));
                        surveyAssignOrg.setOrgSurveyState(2);
                        surveyAssignOrg.setOrgSurveyStateName("初审中");
                        surveyAssignOrg.setReportDate(null);
                        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
                        double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
                        surveyAssignOrg.setAgingDay(Math.abs(AgingDayUtil.orgAgingDay(surveyAssignOrg, surveyConsignor.getEfficiencyAttr(), v)));
                        surveyAssignOrg.setOrgOpinion(apiRequest.getString("opinion" + orgId));
                        if (surveyAssignOrg.getReturnState() == null) {
                            surveyAssignOrg.setReturnState(0);
                        }
                        surveyAssignOrg.setReturnState(surveyAssignOrg.getReturnState() + 1);//累计退回次数
                        surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                        //机构案件的状态 改变成审核通过
                        List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyAssorgCaseId(surveyAssignOrg.getId());
                        for (SurveyInvestigatorCase aCase : cases) {
                            aCase.setSurveyState(4);
                            aCase.setSurveyStateName("已提交");
                            //此处风控审核退回 正常情况下 案件状态都为 4。 不需要赋 提交时间
                            surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
                        }

                        //发送机构消息通知
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=org-review-list&assignOrgId=" + surveyAssignOrg.getId();
                        Map<String, Long> paramMap = new HashMap<String, Long>();
                        paramMap.put("roleId", 58L);
                        paramMap.put("orgId", surveyAssignOrg.getSurveyOrgId());
                        List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                        String content = "你有案件平台复审退回，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime());
                        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(), userInfo.getUserName(), toUsers, 4, "平台复审退回通知",
                                content, url);

                        Map<String, Object> msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "复审退回");
                        msgMap.put("content", "你有案件复审退回，请尽快进行处理！");
                        msgMap.put("keyWords", "案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()) + "\n"
                                + "复审人员：" + userInfo.getUserName() + "\n" + "退回原因：" + surveyAssignOrg.getOrgOpinion());
                        backendWechatApi.send(toUsers, msgMap);

                        //进度
                        backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(), surveyRiskCaseInfo.getId(), userInfo.getUserId(), userInfo.getUserName(), "平台复审退回(" + surveyAssignOrg.getSurveyOrgName() + ")", surveyAssignOrg.getOrgOpinion());

                        SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = new SurveyOrgPrescriptionFlow();
                        surveyOrgPrescriptionFlow.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                        surveyOrgPrescriptionFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
                        surveyOrgPrescriptionFlow.setOperateType(3);
                        surveyOrgPrescriptionFlow.setStartTime(new Date());
                        surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
                        surveyOrgPrescriptionFlow.setOperateType(2);
                        surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);


                        //增加退回记录
                        SurveyAssignOrgBack surveyAssignOrgBack = new SurveyAssignOrgBack();
                        surveyAssignOrgBack.setSurveyId(surveyAssignOrg.getSurveyId());
                        surveyAssignOrgBack.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                        surveyAssignOrgBack.setSurveyCaseOrgId(surveyAssignOrg.getId());
                        surveyAssignOrgBack.setContent(surveyAssignOrg.getOrgOpinion());
                        surveyAssignOrgBack.setCreateBy(userInfo.getUserName());
                        surveyAssignOrgBack.setCreateTime(new Date());
                        surveyAssignOrgBack.setDeleteFlag(0);
                        surveyAssignOrgBack.setType("BACK");
                        surveyAssignOrgBackMapper.insert(surveyAssignOrgBack);
                    }
                }

                //终审退回
//                Boolean b = true;//如果是所有机构退回
//                if (b){
//                    Map<String,Object> map =  new HashMap<String,Object>();
//                    map.put("surveyInfoId",surveyRiskCaseInfo.getId());
//                    List<SurveyAssignOrgDto> surveyAssignOrgs = surveyAssignOrgMapper.list(map);
//                    for (SurveyAssignOrgDto surveyAssignOrg : surveyAssignOrgs) {
//                        if (surveyAssignOrg.getOrgSurveyState() == 4){
//                            surveyAssignOrg.setOrgSurveyState(2);
//                            surveyAssignOrg.setOrgSurveyStateName("终审退回");
//                            surveyAssignOrg.setOrgOpinion(opinion);
//                            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
//                        }
//                    }
//                }else{//选机构退回
//                    Long orgId = apiRequest.getLong("orgId");
//                    Map<String,Object> map =  new HashMap<String,Object>();
//                    map.put("surveyInfoId",surveyRiskCaseInfo.getId());
//                    map.put("surveyOrgId",orgId);
//                    SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
//                    surveyAssignOrg.setOrgSurveyState(2);
//                    surveyAssignOrg.setOrgSurveyStateName("终审退回");
//                    surveyAssignOrg.setOrgOpinion(opinion);
//                    surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
//                }
                surveyRiskCaseInfo.setLefanReportDate(null);
                surveyRiskCaseInfo.setSurveyState(12);//调查中  可以重新分派   机构重新复核
                surveyRiskCaseInfo.setSurveyStateName("调查中");
            } else if ("1302".equals(btnCode)) {
                surveyRiskCaseInfo.setIsSun(1);
            } else if ("1303".equals(btnCode)) {
                surveyRiskCaseInfo.setIsSun(0);
            } else if ("1400".equals(btnCode)) {
                //验证该案件关联的方向是否关联渠道费。渠道费未审核通过。不可审核通过。
                int count = surveyChannelCaseMapper.selectChannelNotOpr(surveyRiskCaseInfo.getId());
                if (count > 0) {
                    return new ApiResponse(ApiMsgEnum.SURVEY_CHANNEL_NOT_OPR);
                }

                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
                try {
                    Date oprTime = DateUtils.parseDate(apiRequest.getString("oprTime"), "yyyy-MM-dd HH:mm:ss");
                    surveyRiskCaseInfo.setEntrustReportEndDate(oprTime);
                    surveyRiskCaseInfo.setAgingDay(AgingDayUtil.riskCaseInfoAgingDay(surveyRiskCase.getEntrustTime(), oprTime, surveyConsignor.getEfficiencyAttr()));
                } catch (Exception e) {
                    e.printStackTrace();
                    surveyRiskCaseInfo.setEntrustReportEndDate(new Date());
                }

                String remark = apiRequest.getString("remark");
                surveyRiskCaseInfo.setEntrustReportRemark(remark);
                opinion = remark;
                surveyRiskCaseInfo.setOpinion(null);
                surveyRiskCaseInfo.setSurveyState(28);
                surveyRiskCaseInfo.setSurveyStateName("保司终审通过");
                surveyCaseWorkflowApi.addSurveyCaseWorkflow("保司审核", userInfo, surveyRiskCaseInfo.getEntrustReportStartDate(), surveyRiskCaseInfo.getEntrustReportEndDate(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());
                //案件时效
                surveyCaseWorkflowApi.addSurveyCaseWorkflow("案件时效", userInfo, surveyRiskCaseInfo.getCreateTime(), surveyRiskCaseInfo.getEntrustReportEndDate(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());
                //归档管理
                Map<String, Object> map = new HashMap<>();
                map.put("surveyInfoId", surveyRiskCaseInfo.getId());
                SurveyCaseArchives surveyCaseArchives = surveyCaseArchivesMapper.selectOne(map);
                if (surveyCaseArchives == null) {
                    surveyCaseArchives = new SurveyCaseArchives();
                    surveyCaseArchives.setSurveyCaseNo(surveyRiskCase.getSurveyCaseNo());
                    surveyCaseArchives.setSurveyNo(surveyRiskCaseInfo.getSurveyNo());
                    surveyCaseArchives.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    surveyCaseArchives.setSurveyId(surveyRiskCaseInfo.getSurveyId());
                    surveyCaseArchives.setCreateBy(userInfo.getUserName());
                    surveyCaseArchives.setCreateTime(new Date());
                    surveyCaseArchives.setDeleteFlag(0);
                    surveyCaseArchives.setArchivesState(0);
                    surveyCaseArchivesMapper.insert(surveyCaseArchives);
                }

            } else if ("bsCase".equals(btnCode)) {
                //根据ids查询列表
                String oprTimeStr = apiRequest.getString("oprTime");

                String idStr = apiRequest.getString("ids");
                List<SurveyRiskCaseInfoDto> surveyRiskCaseInfos = surveyRiskCaseInfoMapper.selectBsCaseList(idStr);//根据ids查询集合
                for (SurveyRiskCaseInfoDto item : surveyRiskCaseInfos) {
                    if (oprTimeStr != null) {
                        Date oprTime = DateUtils.parseDate(oprTimeStr, "yyyy-MM-dd HH:mm:ss");
                        item.setEntrustReportEndDate(oprTime);
                    } else {
                        item.setEntrustReportEndDate(item.getEntrustReportStartDate());
                    }

                    item.setAgingDay(AgingDayUtil.riskCaseInfoAgingDay(item.getRiskCaseEntrustTime(), item.getEntrustReportEndDate(), item.getEfficiencyAttr()));
                    item.setOpinion(null);
                    item.setSurveyState(28);
                    item.setSurveyStateName("保司终审通过");

//                    surveyRiskCaseInfoMapper.updateByPrimaryKey(item);
                    item.setStepName("保司审核");
                    item.setDealUserId(userInfo.getUserId());
                    item.setDealUserName(userInfo.getUserName());
                    Long hours = DateCaleUtil.getDiffHours(item.getEntrustReportStartDate(), item.getEntrustReportEndDate());
                    item.setHours(hours);
                }

                //update 根据列表更新数据(update SurveyRiskCaseInfo)
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("surveyRiskCaseInfos", surveyRiskCaseInfos);
                surveyRiskCaseInfoMapper.updateBsCaseList(paramMap);

                //insert 根据列表插入进度表  (insert SurveyProgress)
                surveyCaseWorkflowMapper.insertByBsCaseList(paramMap);

                //insert 根据列表插入归档管理表(insert SurveyCaseArchives)
                paramMap.put("surveyRiskCaseInfos", surveyRiskCaseInfos);
                surveyCaseArchivesMapper.insertByBsCaseList(paramMap);

                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("1401".equals(btnCode)) {
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
//                if (surveyRiskCaseInfo.getEntrustOrgId().intValue() == 105 || surveyRiskCaseInfo.getEntrustOrgId().intValue() == 82){//如果审核互助的保司审核退回 则清空机构提交时间
                if (surveyConsignor.getOrgAttr() == 2) {
                    Map<String, Object> map = new HashMap<>();
                    map = new HashMap();
                    map.put("surveyInfoId", surveyRiskCaseInfo.getId());
                    List<SurveyAssignOrgDto> orgs = surveyAssignOrgMapper.list(map);
                    for (SurveyAssignOrgDto org : orgs) {
                        org.setReviewTime(null);
                        surveyAssignOrgMapper.updateByPrimaryKey(org);

                        //添加复审时效记录  互助保司退回
                        SurveyCheckPreFlow surveyCheckPreFlow = new SurveyCheckPreFlow();
                        surveyCheckPreFlow.setSurveyInfoId(org.getSurveyInfoId());
                        surveyCheckPreFlow.setSurveyAssignOrgId(org.getId());
                        surveyCheckPreFlow.setStartTime(new Date());
                        surveyCheckPreFlow.setOperateType(5);
                        surveyCheckPreFlowMapper.insert(surveyCheckPreFlow);
                        surveyCheckPreFlow.setOperateType(null);
                        surveyCheckPreFlowMapper.insert(surveyCheckPreFlow);
                    }
                }


                surveyRiskCaseInfo.setEntrustReportStartDate(null);//保司审核不通过 到平台复审中  清空终审时间
                surveyRiskCaseInfo.setOpinion(opinion);
                surveyRiskCaseInfo.setSurveyState(22);
                surveyRiskCaseInfo.setSurveyStateName("平台复审中");

                //保司审核不通过  风控审核通知
                //发送风控审核人通知
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                List<UserInfo> toUsers = userInfoMapper.selectOprUserSurveyInfoId(surveyRiskCaseInfo.getId());
                String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=survey-list";
                backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(), userInfo.getUserName(), toUsers, 4, "保司审核退回",
                        "你有新的案件待审核，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyRiskCaseInfo.getEndTime()), url);

            } else if ("1333".equals(btnCode)) {// 当前已经复核通过的机构案件 其 审核人是当前登录人的案件  审核时间改掉
                Map map = new HashMap();
                map.put("surveyInfoId", surveyRiskCaseInfo.getId());
                map.put("reviewUserId", userInfo.getUserId());
                map.put("search", 33);
                List<SurveyAssignOrgDto> orgs = surveyAssignOrgMapper.list(map);
                for (SurveyAssignOrgDto org : orgs) {
                    org.setReviewTime(new Date());
                    surveyAssignOrgMapper.updateByPrimaryKey(org);
                }
                //如果所有机构都复核通过则到保司审核中
                Boolean isLast = true;
                map = new HashMap();
                map.put("surveyInfoId", surveyRiskCaseInfo.getId());
                orgs = surveyAssignOrgMapper.list(map);
                for (SurveyAssignOrgDto org : orgs) {
                    if (org.getReviewTime() == null) {// 只要有一个未提交  就不是最后一个。  最后一个机构审核通过的时候到保司审核
                        isLast = false;
                    }
                }

                if (isLast) {
                    surveyRiskCaseInfo.setOpinion(null);
                    surveyRiskCaseInfo.setEntrustReportStartDate(new Date());
                    surveyRiskCaseInfo.setSurveyState(24);
                    surveyRiskCaseInfo.setSurveyStateName("保司终审中");

                    //判断是否已经开票
                    map = new HashMap();
                    map.put("riskCaseInfoId", surveyRiskCaseInfo.getId());
                    List<SurveyBillingApply> surveyBillingApplies = surveyBillingApplyMapper.selectByInfo(map);
                    if (surveyBillingApplies != null && surveyBillingApplies.size() > 0) {//如果已开票就不管

                    } else {
                        surveyRiskCaseInfo.setEntrustOkPrice1(surveyRiskCaseInfo.getEntrustMoney());//默认 委托方确认的金额为 乐凡申请的金额
                        surveyRiskCaseInfo.setEntrustOkPrice2(surveyRiskCaseInfo.getEntrustReLosses());
                    }

                    Double entrustOkPrice1 = surveyRiskCaseInfo.getEntrustOkPrice1() == null ? 0D : surveyRiskCaseInfo.getEntrustOkPrice1();
                    Double entrustOkPrice2 = surveyRiskCaseInfo.getEntrustOkPrice2() == null ? 0D : surveyRiskCaseInfo.getEntrustOkPrice2();
                    surveyRiskCaseInfo.setBillingMoney(entrustOkPrice1 + entrustOkPrice2);
                    surveyRiskCaseInfo.setConfirmAccountMoney(entrustOkPrice1 + entrustOkPrice2);

                    //获取“案件类型”
                    surveyRiskCaseInfo.setCaseState(findCaseState(surveyRiskCaseInfo));

                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);


                    //发送机构消息通知
                    SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                    map = new HashMap<String, Object>();
                    map.put("surveyInfoId", surveyRiskCaseInfo.getId());
                    List<SurveyAssignOrgDto> assignOrgs = surveyAssignOrgMapper.list(map);
                    for (SurveyAssignOrgDto assignOrg : assignOrgs) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=org-review-list&assignOrgId=" + assignOrg.getId();
                        Map<String, Long> paramMap = new HashMap<String, Long>();
                        paramMap.put("roleId", 58L);
                        paramMap.put("orgId", assignOrg.getSurveyOrgId());
                        List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(), userInfo.getUserName(), toUsers, 4, "平台复审通过通知",
                                "你有案件平台复审通过，案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，案件截止日期：" + simpleDateFormat.format(assignOrg.getOrgEndTime()), url);
                    }

                    surveyCaseWorkflowApi.addSurveyCaseWorkflow("平台复审", userInfo, surveyRiskCaseInfo.getLefanReportDate(), surveyRiskCaseInfo.getEntrustReportStartDate(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());

                }
            } else if ("1334".equals(btnCode)) {


            } else if ("1500".equals(btnCode)) {
                surveyRiskCaseInfo.setCloseStartDate(new Date());
                surveyRiskCaseInfo.setSurveyState(32);
                surveyRiskCaseInfo.setSurveyStateName("结案审核中");
            } else if ("1502".equals(btnCode)) {
                surveyRiskCaseInfo.setIsSendReport(1);
                surveyRiskCaseInfo.setSendDate(new Date());
            } else if ("1505".equals(btnCode)) {//标记基本费结算
                surveyRiskCaseInfo.setPrice1IsCalc(1);
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
                //生成基本费的 待提现清单 乐凡币  成就点等
                for (SurveyInvestigatorCase aCase : cases) {
                    Long num = 1000L;
                    SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(aCase.getSurveyUserId());
                    //判断是否第一次完成任务  第一次额外增加 1000乐凡币和成就点
                    int count = surveyInvestigatorCaseMapper.getFirstCloseSurveyInvestigatorCase(investigator.getUserId());
                    if (count == 0) {
                        backendSurveyInvestigatorApi.addPrice(aCase.getSurveyUserId(), num, 3, "初次完成案件", aCase.getId(), 2);
                    }
                    //正常增加乐凡币和成就点
                    if (aCase.getSurveyTaskMoney() != null && aCase.getSurveyTaskMoney() != 0D) {
                        num = Math.round(aCase.getSurveyTaskMoney());
                        backendSurveyInvestigatorApi.addPrice(aCase.getSurveyUserId(), num, 3, "完成调查案件(基本费)", aCase.getId(), 2);
                    }

                    if (aCase.getSurveyTaskMoney() != null && aCase.getSurveyTaskMoney() != 0D) {
                        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(aCase.getSurveyId());
                        SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.selectByPrimaryKey(aCase.getSurveyInfoId());
                        SurveyCashInfoRecord record = new SurveyCashInfoRecord();
                        record.setSurveyId(aCase.getSurveyId());
                        record.setSurveyInfoId(aCase.getSurveyInfoId());
                        record.setSurveyInvestigatorCaseId(aCase.getId());
                        record.setSurveyCaseName(surveyRiskCase.getSurveyPerson().concat("的").concat(info.getSurveyBusName()));
                        record.setSurveyUserId(aCase.getSurveyUserId());
                        record.setSurveyUserName(aCase.getSurveyUserName());
                        record.setSurveyTaskMoney(aCase.getSurveyTaskMoney());
                        record.setFranchiseeId(aCase.getSurveyOrgId());
                        record.setFranchiseeName(aCase.getSurveyOrgName());
                        record.setCashState(1);
                        record.setCashType(1);
                        record.setCreateBy(userInfo.getUserName());
                        record.setCreateTime(new Date());
                        record.setUpdateBy(null);
                        record.setUpdateTime(null);
                        record.setDeleteFlag(0);
                        surveyCashInfoRecordMapper.insert(record);
                    }
                }
            } else if ("1506".equals(btnCode)) {//标记减损奖励结算
                surveyRiskCaseInfo.setPrice2IsCalc(1);
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
                //生成减损奖励的 待提现清单
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getSurveryReLosses() == null) {
                        aCase.setSurveryReLosses(0D);
                    }
                    SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(aCase.getSurveyUserId());
                    //正常增加乐凡币和成就点
                    if (aCase.getSurveryReLosses() != 0D) {
                        Long num = Math.round(aCase.getSurveryReLosses());
                        backendSurveyInvestigatorApi.addPrice(aCase.getSurveyUserId(), num, 3, "完成调查案件(减损奖励)", aCase.getId(), 2);
                    }

                    if (aCase.getSurveryReLosses() != 0D) {
                        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(aCase.getSurveyId());
                        SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.selectByPrimaryKey(aCase.getSurveyInfoId());
                        SurveyCashInfoRecord record = new SurveyCashInfoRecord();
                        record.setSurveyId(aCase.getSurveyId());
                        record.setSurveyInfoId(aCase.getSurveyInfoId());
                        record.setSurveyInvestigatorCaseId(aCase.getId());
                        record.setSurveyCaseName(surveyRiskCase.getSurveyPerson().concat("的").concat(info.getSurveyBusName()));
                        record.setSurveyUserId(aCase.getSurveyUserId());
                        record.setSurveyUserName(aCase.getSurveyUserName());
                        record.setSurveyTaskMoney(aCase.getSurveryReLosses());
                        record.setFranchiseeId(aCase.getSurveyOrgId());
                        record.setFranchiseeName(aCase.getSurveyOrgName());
                        record.setCashState(1);
                        record.setCashType(2);
                        record.setCreateBy(userInfo.getUserName());
                        record.setCreateTime(new Date());
                        record.setUpdateBy(null);
                        record.setUpdateTime(null);
                        record.setDeleteFlag(0);
                        surveyCashInfoRecordMapper.insert(record);
                    }
                }
            } else if ("1600".equals(btnCode)) {
                surveyRiskCaseInfo.setCloseEndDate(new Date());
                surveyRiskCaseInfo.setSurveyState(34);
                surveyRiskCaseInfo.setSurveyPhase(3);
                surveyRiskCaseInfo.setSurveyStateName("已结案");
            } else if ("1601".equals(btnCode)) {
                surveyRiskCaseInfo.setOpinion(opinion);
                surveyRiskCaseInfo.setSurveyState(36);
                surveyRiskCaseInfo.setSurveyStateName("结案审核不通过");
            } else if ("700".equals(btnCode)) {
                Double entrustMoney = apiRequest.getDouble("entrustMoney");
                String entrustMoneyRemark = apiRequest.getString("entrustMoneyRemark");
                Double entrustReLosses = apiRequest.getDouble("entrustReLosses");
                if (surveyRiskCaseInfo.getEntrustMoney() == null) {
                    surveyRiskCaseInfo.setEntrustMoney(0D);
                }
                if (surveyRiskCaseInfo.getSurveryReLosses() == null) {
                    surveyRiskCaseInfo.setEntrustReLosses(0D);
                }
                if (!entrustMoney.toString().equals(surveyRiskCaseInfo.getEntrustMoney().toString())
                        || !entrustReLosses.toString().equals(surveyRiskCaseInfo.getEntrustReLosses().toString())) {
                    //往价格调整记录表插入数据
                    SurveyAccountLog surveyAccountLog = new SurveyAccountLog();
                    surveyAccountLog.setCodeType(Long.parseLong("1"));
                    surveyAccountLog.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    if (surveyRiskCaseInfo.getEntrustMoney() != null && !surveyRiskCaseInfo.getEntrustMoney().equals("")) {
                        surveyAccountLog.setOldBasicPrice(surveyRiskCaseInfo.getEntrustMoney());
                    } else {
                        surveyAccountLog.setOldBasicPrice(0.00);
                    }
                    surveyAccountLog.setNewBasicPrice(entrustMoney);
                    if (surveyRiskCaseInfo.getEntrustReLosses() != null && !surveyRiskCaseInfo.getEntrustReLosses().equals("")) {
                        surveyAccountLog.setOldDePrice(surveyRiskCaseInfo.getEntrustReLosses());
                    } else {
                        surveyAccountLog.setOldDePrice(0.00);
                    }
                    surveyAccountLog.setNewDePrice(entrustReLosses);
                    surveyAccountLog.setUpdateTime(new Date());
                    surveyAccountLog.setUpdateBy(userInfo.getUserName());
                    surveyAccountLogMapper.insert(surveyAccountLog);
                }
                String beforeValue = surveyRiskCaseInfo.getEntrustMoney() == null ? "" : surveyRiskCaseInfo.getEntrustMoney().toString();

                surveyRiskCaseInfo.setEntrustMoney(entrustMoney);
                surveyRiskCaseInfo.setEntrustReLosses(entrustReLosses);
                surveyRiskCaseInfo.setEntrustOkPrice1(entrustMoney);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfo.getEntrustMoney() == null ? "" : surveyRiskCaseInfo.getEntrustMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_entrust_money");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(entrustMoneyRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
                // 2021年8月27日  直接调整委托方申请结算价格 则把结算方式改为一口价。同时所有方向的价格为0
                surveyRiskCaseInfo.setPayType(1);
                List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.selectAllDirectionByRiskInfoId(surveyRiskCaseInfo.getId());
                for (SurveyCaseDirection surveyCaseDirection : surveyCaseDirections) {
                    beforeValue = surveyCaseDirection.getEntrustMoney() == null ? "" : surveyCaseDirection.getEntrustMoney().toString();
                    surveyCaseDirection.setEntrustMoney(0D);
                    surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                    //保存更改记录，委托方记录
                    record = new SurveyAttrUpdRecord();
                    record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                    record.setUpdAfterValue(surveyCaseDirection.getEntrustMoney() == null ? "" : surveyCaseDirection.getEntrustMoney().toString());
                    if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                        record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                        record.setUpdAttr("survey_case_direction_entrust_money" + surveyCaseDirection.getId());
                        record.setUpdTime(new Date());
                        record.setUpdRemark("直接更改案件委托方结算价格关联变更");
                        record.setUpdUserName(userInfo.getUserName());
                        surveyAttrUpdRecordMapper.insert(record);
                    }
                }
            } else if ("701".equals(btnCode)) {
                Double surveyMoney = apiRequest.getDouble("surveyMoney");
                String surveyMoneyRemark = apiRequest.getString("surveyMoneyRemark");
                Double surveryReLosses = apiRequest.getDouble("surveryReLosses");
                String beforeValue = surveyRiskCaseInfo.getSurveyMoney() == null ? "" : surveyRiskCaseInfo.getSurveyMoney().toString();
                surveyRiskCaseInfo.setSurveyMoney(surveyMoney);
                surveyRiskCaseInfo.setSurveryReLosses(surveryReLosses);
                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfo.getSurveyMoney() == null ? "" : surveyRiskCaseInfo.getSurveyMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_survey_money");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(surveyMoneyRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

            } else if ("702".equals(btnCode)) {
                Double entrustCreditMoney = apiRequest.getDouble("entrustCreditMoney");
                surveyRiskCaseInfo.setEntrustCreditMoney(entrustCreditMoney);
            } else if ("800".equals(btnCode)) {
                Double entrustOkPrice1 = apiRequest.getDouble("entrustOkPrice1");
                String entrustOkPrice1Remark = apiRequest.getString("entrustOkPrice1Remark");
                Double entrustOkPrice2 = apiRequest.getDouble("entrustOkPrice2");
                String beforeValue = surveyRiskCaseInfo.getEntrustOkPrice1() == null ? "" : surveyRiskCaseInfo.getEntrustOkPrice1().toString();
                surveyRiskCaseInfo.setEntrustOkPrice1(entrustOkPrice1);
                surveyRiskCaseInfo.setEntrustOkPrice2(entrustOkPrice2);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfo.getEntrustOkPrice1() == null ? "" : surveyRiskCaseInfo.getEntrustOkPrice1().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_entrust_ok_price_1");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(entrustOkPrice1Remark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

            } else if ("updClientMoney".equals(btnCode)) {
                Long itemId = apiRequest.getLong("itemId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(itemId);
                String beforeValue = surveyAssignOrg.getInscompanySubmitMoney() == null ? "" : surveyAssignOrg.getInscompanySubmitMoney().toString();
                if (surveyAssignOrg.getInscompanySubmitMoney() != null) {
                    surveyAssignOrg.setOldInscompanySubmitMoney(surveyAssignOrg.getInscompanySubmitMoney());
                } else {
                    surveyAssignOrg.setOldInscompanySubmitMoney(0.00);
                }
                Double entrustOkPrice1 = apiRequest.getDouble("money");
                String moneyRemark = apiRequest.getString("moneyRemark");
                surveyAssignOrg.setInscompanySubmitMoney(entrustOkPrice1);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyAssignOrg.getInscompanySubmitMoney() == null ? "" : surveyAssignOrg.getInscompanySubmitMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                    record.setUpdAttr("survey_assign_org_inscompany_submit_money" + surveyAssignOrg.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(moneyRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }


                Double money = 0.00;
                Map map = new HashMap();
                map.put("surveyInfoId", id);
                List<SurveyAssignOrgDto> SurveyAssignOrgDtoList = surveyAssignOrgMapper.selectByMap(map);
                for (SurveyAssignOrgDto surveyAssignOrgDto : SurveyAssignOrgDtoList) {
                    if (surveyAssignOrgDto.getInscompanySubmitMoney() != null) {
                        money = money + surveyAssignOrgDto.getInscompanySubmitMoney();
                    }
                }
                //判断是否已经开票
                map = new HashMap();
                map.put("riskCaseInfoId", surveyRiskCaseInfo.getId());
                List<SurveyBillingApply> surveyBillingApplies = surveyBillingApplyMapper.selectByInfo(map);
                if (surveyBillingApplies != null && surveyBillingApplies.size() > 0) {//如果已开票就不管

                } else {
                    surveyRiskCaseInfo.setEntrustOkPrice1(money);
                }
            } else if ("dispatch".equals(btnCode)) {
                String taskIdsStr = apiRequest.getString("taskIds");
                surveyRiskCaseInfo.setOther1(taskIdsStr);//已选择的任务类型ID集合
                if (StringUtils.isNotEmpty(taskIdsStr)) {
                    String[] taskIds = taskIdsStr.split(",");
                    surveyRiskCaseInfo.setOther2("");
                    for (String taskId : taskIds) {
                        if (StringUtils.isNotEmpty(taskId)) {
                            SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(Long.parseLong(taskId));
                            String names = surveyRiskCaseInfo.getOther2() == null ? "" : surveyRiskCaseInfo.getOther2();
                            surveyRiskCaseInfo.setOther2(names.concat(surveyTaskInfo.getName()).concat(" "));
                        }
                    }
                }
                surveyRiskCaseInfo.setTaskDispatchState(2);//调度中
            } else if ("dispatchYes".equals(btnCode)) {
                surveyRiskCaseInfo.setTaskDispatchState(3);//调度通过
                //添加任务类型
                String chkTaskIds = surveyRiskCaseInfo.getOther1();
                if (StringUtils.isNotEmpty(chkTaskIds)) {
                    String[] taskIds = chkTaskIds.split(",");
                    for (String taskId : taskIds) {
                        if (StringUtils.isNotEmpty(taskId)) {
                            SurveyTaskType surveyTaskType = SurveyTaskType.class.newInstance();
                            surveyTaskType.setSurveyId(surveyRiskCaseInfo.getSurveyId());
                            surveyTaskType.setSurveyInfoId(surveyRiskCaseInfo.getId());
                            surveyTaskType.setTaskId(Long.parseLong(taskId));
                            SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyTaskType.getTaskId());
                            surveyTaskType.setTaskName(surveyTaskInfo.getName());
                            surveyTaskTypeMapper.insert(surveyTaskType);
                        }
                    }
                }
                surveyRiskCaseInfo.setOther1(null);//清空
                surveyRiskCaseInfo.setOther2(null);
            } else if ("dispatchNo".equals(btnCode)) {
                surveyRiskCaseInfo.setTaskDispatchRemark(opinion);
                surveyRiskCaseInfo.setTaskDispatchState(4);//调度不通过
            } else if ("classic".equals(btnCode)) {
                surveyRiskCaseInfo.setIsClassic(1);
            } else if ("reclassic".equals(btnCode)) {
                surveyRiskCaseInfo.setIsClassic(0);
            } else if ("900".equals(btnCode)) {
                Double billingMoney = apiRequest.getDouble("billingMoney");
                surveyRiskCaseInfo.setBillingMoney(billingMoney);
            } else if ("901".equals(btnCode)) {
                Double confirmAccountMoney = apiRequest.getDouble("confirmAccountMoney");
                surveyRiskCaseInfo.setConfirmAccountMoney(confirmAccountMoney);
            } else if ("902".equals(btnCode)) {
                Long sourceOrgId = apiRequest.getLong("sourceOrgId");
                SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(sourceOrgId);
                if (franchisee != null) {
                    surveyRiskCaseInfo.setSourceOrgId(franchisee.getId());
                    surveyRiskCaseInfo.setSourceOrgName(franchisee.getName());
                }
                surveyRiskCaseInfo.setSourceSupportType(apiRequest.getInt("sourceSupportType"));
            } else if ("120".equals(btnCode)) {
                Long backCaseId = apiRequest.getLong("backCaseId");
                SurveyBackCaseDto surveyBackCaseDto = surveyBackCaseMapper.selectByPrimaryKey(backCaseId);
                surveyBackCaseDto.setBackState(3);
                surveyBackCaseDto.setBackStateName("机构审核退回");
                surveyBackCaseDto.setOpinion(opinion);
                surveyBackCaseMapper.updateByPrimaryKey(surveyBackCaseDto);
            } else if ("121".equals(btnCode)) {
                Long backCaseId = apiRequest.getLong("backCaseId");
                SurveyBackCaseDto surveyBackCaseDto = surveyBackCaseMapper.selectByPrimaryKey(backCaseId);
                surveyBackCaseDto.setBackState(4);
                surveyBackCaseDto.setBackStateName("反馈平台审核中");
                surveyBackCaseDto.setOpinion(null);
                surveyBackCaseMapper.updateByPrimaryKey(surveyBackCaseDto);
            } else if ("122".equals(btnCode)) {
                Long backCaseId = apiRequest.getLong("backCaseId");
                SurveyBackCaseDto surveyBackCaseDto = surveyBackCaseMapper.selectByPrimaryKey(backCaseId);
                surveyBackCaseDto.setBackState(6);
                surveyBackCaseDto.setBackStateName("平台审核退回");
                surveyBackCaseDto.setOpinion(opinion);
                surveyBackCaseMapper.updateByPrimaryKey(surveyBackCaseDto);
            } else if ("sign1".equals(btnCode)) {
                surveyRiskCaseInfo.setIsSun(1);
            } else if ("resign1".equals(btnCode)) {
                surveyRiskCaseInfo.setIsSun(0);
            } else if ("delSurveyInfo".equals(btnCode)) {
                surveyRiskCaseInfo.setDeleteFlag(1);
                //删除案件info表数据 同时删除risk表
                SurveyRiskCase riskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                if (riskCase != null) {
                    riskCase.setDeleteFlag(1);
                    surveyRiskCaseMapper.updateByPrimaryKey(riskCase);
                }

                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
                for (SurveyInvestigatorCase aCase : cases) {
                    aCase.setDeleteFlag(1);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
                }

                //删除案件时--二调的案件,删除关联关系
                SurveyRiskCaseTransfer surveyRiskCaseTransfer = surveyRiskCaseTransferMapper.selectBySurveyId(id);
                if (surveyRiskCaseTransfer != null) {
                    surveyRiskCaseTransferMapper.deleteByPrimaryKey(surveyRiskCaseTransfer.getId());
                }

            } else if ("updEntrustOrg".equals(btnCode)) {
                Long entrustOrgId = apiRequest.getLong("entrustOrg");
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(entrustOrgId);
                if (surveyConsignor == null) {
                    return new ApiResponse(ApiMsgEnum.SURVEY_INVESTIGATER_NOT);
                }
                surveyRiskCaseInfo.setEntrustOrgId(surveyConsignor.getId());
                surveyRiskCaseInfo.setEntrustOrgName(surveyConsignor.getName());
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                surveyRiskCase.setEntrustOrgId(surveyConsignor.getId());
                surveyRiskCase.setEntrustOrgName(surveyConsignor.getName());
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);
            } else if ("sendReport".equals(btnCode)) {
                surveyRiskCaseInfo.setSendReportState(1);
                Long sendReportUserId = apiRequest.getLong("sendReportUserId");
                surveyRiskCaseInfo.setSendReportUserId(sendReportUserId);
                UserInfo sendUser = userInfoMapper.selectByPrimaryKey(sendReportUserId);
                surveyRiskCaseInfo.setSendReportUserName(sendUser.getUserName());
            } else if ("sendReportCommit".equals(btnCode)) {
                surveyRiskCaseInfo.setSendReportState(2);
            }
            //批量分派归属人
            else if ("belong".equals(btnCode)) {
                String idStr = apiRequest.getString("ids");
                String[] ids = idStr.split(",");
                for (String caseId : ids) {
                    SurveyRiskCaseInfo Info = surveyRiskCaseInfoMapper.selectByPrimaryKey(Long.valueOf(caseId));
                    Info.setBelongUserId(currentUserId);
                    Info.setBelongUserName(userInfo.getUserName());
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(Info);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            //改派归属人
            else if ("belongUser".equals(btnCode)) {
                Long belongUserId = apiRequest.getLong("belongUserId");
                String belongUserIdRemark = apiRequest.getString("belongUserIdRemark");
                UserInfo sendUser = userInfoMapper.selectByPrimaryKey(belongUserId);
                String beforeValue = surveyRiskCaseInfo.getBelongUserName();

                surveyRiskCaseInfo.setBelongUserId(belongUserId);
                surveyRiskCaseInfo.setBelongUserName(sendUser.getUserName());

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfo.getBelongUserName() == null ? "" : surveyRiskCaseInfo.getBelongUserName());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_belong_user_id");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(belongUserIdRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

            } else if ("generateReport".equals(btnCode)) {
                String generateReportPath = apiRequest.getString("generateReportPath");
                CommonFile commonFile = new CommonFile();
                commonFile.setFilePath(generateReportPath);
                String fileName = null;
                if (generateReportPath != null) {
                    fileName = generateReportPath.substring(generateReportPath.lastIndexOf("/") + 1);
                }
                commonFile.setFileName(fileName);
                commonFile.setCreateTime(new Date());
                commonFileMapper.insert(commonFile);
                surveyRiskCaseInfo.setReportId(commonFile.getId());
                surveyRiskCaseInfo.setReportName(fileName);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

                Map resultMap = new HashMap<>();
                resultMap.put("returnCode", btnCode);
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, resultMap);
            } else if ("downTransfer".equals(btnCode)) {
                String path = apiRequest.getString("path");
                Map resultMap = new HashMap<>();
                resultMap.put("path", path);
                resultMap.put("returnCode", btnCode);
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, resultMap);
            } else if ("character".equals(btnCode)) {
                //文字识别功能
                List<String> words = new ArrayList<>();
                String url = apiRequest.getString("url");
                CharacterRecognitionUtils characterRecognitionUtils = CharacterRecognitionUtils.getInstance();
                JSONObject json = characterRecognitionUtils.aipRecognitionUrl(url);
//                if(){
//                    怎么判断是否转义成功
//                }
                org.json.JSONArray jsonArray = json.getJSONArray("words_result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject partDaily = jsonArray.getJSONObject(i);
                    String word = partDaily.getString("words");
                    words.add(word);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, words);
            } else if ("updInfoStr".equals(btnCode)) {
                String updCode = apiRequest.getString("updCode");
                String updValue = apiRequest.getString("updValue");
                Long directionId = apiRequest.getLong("directionId");
                if (updaInfo(updCode, updValue, surveyRiskCaseInfo, directionId)) {
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                } else {
                    return new ApiResponse(ApiMsgEnum.FAIL);
                }
            } else if ("updInfoDate".equals(btnCode)) {
                String updCode = apiRequest.getString("updCode");
                Date updValue = DateUtils.parseDate(apiRequest.getString("updValue"), "yyyy-MM-dd HH:mm:ss");
                if (updaInfo(updCode, updValue, surveyRiskCaseInfo)) {
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                } else {
                    return new ApiResponse(ApiMsgEnum.FAIL);
                }
            } else if ("survey-base-info-upd".equals(btnCode)) {
                surveyRiskCaseInfo.setSurveyInfo(apiRequest.getString("surveyInfo"));
                surveyRiskCaseInfo.setSurveyItem(apiRequest.getString("surveyItem"));
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String modelId = apiRequest.getString("modelId");

                surveyRiskCase.setSurveyPerson(apiRequest.getString("surveyPerson"));
                surveyRiskCase.setSurveryPersonTel(apiRequest.getString("surveryPersonTel"));
                surveyRiskCase.setClaimsMoney(apiRequest.getDouble("claimsMoney"));
                surveyRiskCase.setSurveyCaseNo(apiRequest.getString("surveyCaseNo").replace(" ", "").replace("\t", "").trim());
                surveyRiskCase.setIdNumber(apiRequest.getString("idNumber"));
                surveyRiskCase.setAge(apiRequest.getInt("age"));
                surveyRiskCase.setSex(apiRequest.getInt("sex"));

                if (modelId != null) {
                    if ("4".equals(modelId)) { //中宏
                        surveyRiskCase.setPolicyNo(apiRequest.getString("policyNo"));
                    } else if ("3".equals(modelId)) { //中德
                        surveyRiskCase.setPolicyNo(apiRequest.getString("policyNo4"));
                        String insureTakeTime = apiRequest.getString("insureTakeTime");
                        Date insureTakeTimeD = DateUtils.parseDate(insureTakeTime, "yyyy-MM-dd");
                        surveyRiskCase.setInsureTakeTime(insureTakeTimeD);

                        String dangerTime4 = apiRequest.getString("dangerTime4");
                        Date dangerTime4D = DateUtils.parseDate(dangerTime4, "yyyy-MM-dd");
                        surveyRiskCase.setDangerTime(dangerTime4D);

                    } else if ("1".equals(modelId) || "2".equals(modelId)) { //乐凡、正言
                        surveyRiskCase.setInsureName(apiRequest.getString("insureName"));
                        surveyRiskCase.setClaimsNo(apiRequest.getString("claimsNo"));
                        surveyRiskCase.setDangerAddress(apiRequest.getString("dangerAddress"));

                        String insureTime = apiRequest.getString("insureTime");
                        Date insureTimeD = DateUtils.parseDate(insureTime, "yyyy-MM-dd");
                        surveyRiskCase.setInsureTime(insureTimeD);

                        String dangerTime = apiRequest.getString("dangerTime");
                        Date dangerTimeD = DateUtils.parseDate(dangerTime, "yyyy-MM-dd");
                        surveyRiskCase.setDangerTime(dangerTimeD);

                    }
                }
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("services".equals(btnCode)) {
                Long servicesId = apiRequest.getLong("servicesId");
                String servicesName = apiRequest.getString("servicesName");
                Long subServiceId = apiRequest.getLong("subServiceId");
                Integer payType = apiRequest.getInt("payType");
                Double entrustMoney = apiRequest.getDouble("entrustMoney");
                String entrustReLossesRemark = apiRequest.getString("entrustReLossesRemark");
                if (payType == 1 || payType == 2) {
                    surveyRiskCaseInfo.setEntrustMoney(entrustMoney);
                }
                if (payType == 2 || payType == 4) {
                    surveyRiskCaseInfo.setEntrustReLossesRemark(entrustReLossesRemark);
                }
                surveyRiskCaseInfo.setServicesId(servicesId);
                surveyRiskCaseInfo.setServicesName(servicesName);
                surveyRiskCaseInfo.setPayType(payType);
                if (subServiceId != null) {
                    SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                    Long oldSubServiceId = surveyRiskCaseInfo.getSubServiceId();
                    surveyRiskCaseInfo.setSubServiceId(subServiceId);
                    //如果变化了则更新
                    if (oldSubServiceId == null || (oldSubServiceId != null && oldSubServiceId != subServiceId)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        //更新案件的截止时间，根据委托时效模板
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        paramMap.put("entrustOrgId", surveyRiskCaseInfo.getEntrustOrgId());
                        paramMap.put("areaCategoriesId", surveyRiskCase.getInvestigationArea());
                        paramMap.put("serviceId", servicesId);
                        paramMap.put("subServiceId", subServiceId);//众安的时候存在subServiceId
                        Integer days = surveyConsignorEfficiencyModelInfoMapper.getAgingDay(paramMap);
                        //如果新业务类型，没有配置时效，保持原时间
                        if (days != null) {
                            String endTime = GetWorkDay.calLeaveEndDate(surveyRiskCase.getEntrustTime(), null, days, 1).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                            surveyRiskCaseInfo.setEndTime(sdf.parse(endTime + " 23:59:59"));
                        }
                    }
                }
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);


                //更改方向的委托方价格，如果为单点一一获取委托方价格，同时更新案件的委托方价格。如果为深度委托方价格为0
                Boolean singPoint = false;//是否为单点
                if (surveyRiskCaseInfo.getServicesId() == 11 || surveyRiskCaseInfo.getServicesId() == 12) {
                    singPoint = true;
                }
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
                List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.selectAllDirectionByRiskInfoId(surveyRiskCaseInfo.getId());
                for (SurveyCaseDirection surveyCaseDirection : surveyCaseDirections) {
                    String beforeValue = surveyCaseDirection.getEntrustMoney() == null ? "" : surveyCaseDirection.getEntrustMoney().toString();
                    if (singPoint) {//为单点
                        //获取委托方价格
                        Integer areaId = null;
                        Integer areaType = surveyCaseDirection.getAreaType();//城市类型（1、直辖市；2、省会；3、地级市；4、县级市）
                        if (areaType == 2 || areaType == 3) {// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
                            areaId = surveyCaseDirection.getCityId();
                        } else {
                            areaId = surveyCaseDirection.getDistrictId();
                        }
                        //根据具体的区域ID,及委托方   获取区域类别价格
                        Map<String, Object> map = new HashMap();
                        map.put("taskId", surveyCaseDirection.getTaskId());
                        map.put("taskInfoContentId", surveyCaseDirection.getNewId());
                        map.put("directionResultTypeId", surveyCaseDirection.getDirectionResultTypeId());
                        map.put("entrustOrgId", surveyRiskCaseInfo.getEntrustOrgId());
                        map.put("areaId", areaId);
                        map.put("orgAttr", surveyConsignor.getOrgAttr());
                        SurveyPrice surveyPrice = surveyPriceMapper.selectDirectionPrice(map);
                        if (surveyPrice != null) {
                            surveyCaseDirection.setEntrustMoney(surveyPrice.getTaskPrice() == null ? 0D : surveyPrice.getTaskPrice());
                        }
                        if (surveyCaseDirection.getEntrustMoney() == null) {
                            surveyCaseDirection.setEntrustMoney(0D);
                        }
                        surveyCaseDirection.setEntrustPriceSource(1);
                    } else {
                        surveyCaseDirection.setEntrustMoney(0D);
                    }
                    surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                    //保存更改记录，委托方记录
                    SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                    record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                    record.setUpdAfterValue(surveyCaseDirection.getEntrustMoney() == null ? "" : surveyCaseDirection.getEntrustMoney().toString());
                    if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                        record.setSurveyInfoId(surveyCaseDirection.getSurveyInfoId());
                        record.setUpdAttr("survey_case_direction_entrust_money" + surveyCaseDirection.getId());
                        record.setUpdTime(new Date());
                        record.setUpdRemark("任务类型变更");
                        record.setUpdUserName(userInfo.getUserName());
                        surveyAttrUpdRecordMapper.insert(record);
                    }
                }

                String beforeValue = surveyRiskCaseInfo.getEntrustMoney() == null ? "" : surveyRiskCaseInfo.getEntrustMoney().toString();
                Double caseEntrustMoney = surveyCaseDirections.stream().mapToDouble(SurveyCaseDirection::getEntrustMoney).sum();
                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(caseEntrustMoney == null ? "" : caseEntrustMoney.toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_entrust_money");
                    record.setUpdTime(new Date());
                    record.setUpdRemark("任务类型变更-关联变更");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("dircetionFindTasks".equals(btnCode)) {
                Map<String, Object> map = new HashMap<>();
                map.put("surveyInfoId", id);
                map.put("directionName", apiRequest.getString("directionName"));
                Long directionId = apiRequest.getLong("directionId");
                if (directionId != null && !"".equals(directionId)) {
                    map.put("directionId", directionId);
                }
                List<SurveyTaskInfo> tasks = surveyTaskInfoMapper.getSurveyTaskInfosBySurveyInfoIdAndDirectionName(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS, tasks.size(), tasks);
            } else if ("validateDirectionName".equals(btnCode)) {
                Map<String, Object> map = new HashMap<>();
                map.put("surveyInfoId", id);
                map.put("directionName", apiRequest.getString("directionName"));
                Long directionId = apiRequest.getLong("directionId");
                if (directionId != null && !"".equals(directionId)) {
                    map.put("directionId", directionId);
                }
                List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.selectValidateDirectionName(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS, directions.size(), directions);
            } else if ("org-aging-rate".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                Double agingRate = apiRequest.getDouble("agingRate" + surveyAssignOrg.getId()) == null ? 1D : apiRequest.getDouble("agingRate" + surveyAssignOrg.getId());
                String agingRateRemark = apiRequest.getString("agingRateRemark" + surveyAssignOrg.getId());
                String beforeValue = surveyAssignOrg.getOverdueAgingRate() == null ? "1" : surveyAssignOrg.getOverdueAgingRate().toString();

                surveyAssignOrg = surveyAssignOrgApi.agingRate(agingRate, surveyAssignOrg);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyAssignOrg.getOverdueAgingRate() == null ? "" : surveyAssignOrg.getOverdueAgingRate().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_assign_org_overdue_aging_rate" + surveyAssignOrg.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(agingRateRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("mark-error".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                String markType = apiRequest.getString("markType" + surveyAssignOrgId);
                String markRemark = apiRequest.getString("markRemark" + surveyAssignOrgId);
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                surveyAssignOrg.setMarkError(Integer.parseInt(markType));
                surveyAssignOrg.setMarkErrorRemark(markRemark);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("qx-mark-error".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                surveyAssignOrg.setMarkError(0);
                surveyAssignOrg.setMarkErrorRemark(null);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("updOrgSurveyMoney".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                Double surveyMoney = apiRequest.getDouble("surveyMoney".concat(surveyAssignOrgId.toString()));
                Double surveryReLosses = apiRequest.getDouble("surveryReLosses".concat(surveyAssignOrgId.toString()));
                String surveyMoneyRemark = apiRequest.getString("surveyMoneyRemark".concat(surveyAssignOrgId.toString()));
                String surveyMoneyRemarkRemark = apiRequest.getString("surveyMoneyRemarkRemark".concat(surveyAssignOrgId.toString()));
                String beforeValue = surveyAssignOrg.getAssessOrgMoney() == null ? "" : surveyAssignOrg.getAssessOrgMoney().toString();
                surveyAssignOrg.setAssessOrgMoney(surveyMoney);
                surveyAssignOrg.setAssessOrgLossesMoney(surveryReLosses);
                surveyAssignOrg.setSurveyMoneySubmit((surveyMoney == null ? 0 : surveyMoney) * (surveyAssignOrg.getOverdueAgingRate() == null ? 1D : surveyAssignOrg.getOverdueAgingRate()));
                surveyAssignOrg.setSurveryReLossesSubmit((surveryReLosses == null ? 0 : surveryReLosses) * (surveyAssignOrg.getOverdueAgingRate() == null ? 1D : surveyAssignOrg.getOverdueAgingRate()));
                surveyAssignOrg.setSurveryReLoossesRemark(surveyMoneyRemark);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);


                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyAssignOrg.getAssessOrgMoney() == null ? "" : surveyAssignOrg.getAssessOrgMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                    record.setUpdAttr("survey_assign_org_org_assess_org_money" + surveyAssignOrg.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(surveyMoneyRemarkRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("updOrgTaskRemark".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                String surveyOrgTaskRemark = apiRequest.getString("surveyOrgTaskRemark".concat(surveyAssignOrgId.toString()));
                String surveyOrgTaskRemarkRemark = apiRequest.getString("surveyOrgTaskRemarkRemark".concat(surveyAssignOrgId.toString()));

                String beforeValue = surveyAssignOrg.getOrgTaskRemark();
                surveyAssignOrg.setOrgTaskRemark(surveyOrgTaskRemark);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyAssignOrg.getOrgTaskRemark() == null ? "" : surveyAssignOrg.getOrgTaskRemark());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                    record.setUpdAttr("survey_assign_org_org_task_remark" + surveyAssignOrg.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(surveyOrgTaskRemarkRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("updOrgSummary".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                String orgSummary = apiRequest.getString("surveyOrgSummary".concat(surveyAssignOrgId.toString()));
                String orgSummaryRemark = apiRequest.getString("surveyOrgSummaryRemark".concat(surveyAssignOrgId.toString()));
                String beforeValue = surveyAssignOrg.getOrgSummary() == null ? "" : surveyAssignOrg.getOrgSummary();
                surveyAssignOrg.setOrgSummary(orgSummary);
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                List<SurveyInvestigatorCase> surveyInvestigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                if (surveyInvestigatorCases.size() > 0) {
                    if (surveyInvestigatorCases.get(0).getSurveyAssorgCaseId().intValue() == surveyAssignOrg.getId().intValue()) {
                        SurveyRiskCaseInfoDto riskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
                        riskCaseInfo.setReportCompletion(orgSummary);
                        surveyRiskCaseInfoMapper.updateByPrimaryKey(riskCaseInfo);
                    }
                }

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyAssignOrg.getOrgSummary() == null ? "" : surveyAssignOrg.getOrgSummary());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                    record.setUpdAttr("survey_assign_org_org_summary" + surveyAssignOrg.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(orgSummaryRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("updReportCompletion".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                String reportCompletion = apiRequest.getString("surveyReportCompletion".concat(surveyAssignOrgId.toString()));
                surveyRiskCaseInfo.setReportCompletion(reportCompletion);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("updClientPrice".equals(btnCode)) {
                Long caseId = apiRequest.getLong("caseId");
                SurveyRiskCaseInfo surveyRiskCaseInfoDto = surveyRiskCaseInfoMapper.selectByPrimaryKey(caseId);
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                Double inscompanyMoney = apiRequest.getDouble("inscompanyMoney".concat(surveyAssignOrgId.toString()));
                String inscompanyMoneyDesc = apiRequest.getString("inscompanyMoneyDesc".concat(surveyAssignOrgId.toString()));

                String beforeValue = surveyAssignOrg.getInscompanyMoney() == null ? "" : surveyAssignOrg.getInscompanyMoney().toString();
                surveyAssignOrg.setInscompanyMoney(inscompanyMoney);
                surveyAssignOrg.setInscompanySubmitMoney(inscompanyMoney);
                surveyAssignOrg.setInscompanyMoneyDesc(inscompanyMoneyDesc);
                if (surveyAssignOrg.getOldInscompanyMoney() == null || surveyAssignOrg.getOldInscompanyMoney().equals("")) {
                    surveyAssignOrg.setOldInscompanyMoney(0.00);
                }
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyAssignOrg.getInscompanyMoney() == null ? "" : surveyAssignOrg.getInscompanyMoney().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                    record.setUpdAttr("survey_assign_org_inscompany_money" + surveyAssignOrg.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(inscompanyMoneyDesc);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

                Map map = new HashMap();
                map.put("surveyInfoId", surveyRiskCaseInfoDto.getId());
                Double entrustMoney = 0.00;
                List<SurveyAssignOrgDto> list = surveyAssignOrgMapper.list(map);
                for (SurveyAssignOrgDto surveyAssignOrgDto : list) {
                    if (surveyAssignOrgDto.getInscompanyMoney() == null || surveyAssignOrgDto.getInscompanyMoney().equals("")) {
                        surveyAssignOrgDto.setInscompanyMoney(0.00);
                        surveyAssignOrgDto.setInscompanySubmitMoney(0.00);
                    }
                    entrustMoney = entrustMoney + surveyAssignOrgDto.getInscompanyMoney();
                }
                surveyRiskCaseInfoDto.setEntrustMoney(entrustMoney);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfoDto);

            } else if ("updOrgSurveyReview".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                Long reviewUserId = apiRequest.getLong("reviewUserId".concat(surveyAssignOrgId.toString()));
                String reviewUserIdRemark = apiRequest.getString("reviewUserIdRemark".concat(surveyAssignOrgId.toString()));
                String beforeValue = surveyAssignOrg.getReviewUserName();

                surveyAssignOrg.setReviewUserId(reviewUserId);
                UserInfo reviewUser = userInfoMapper.selectByPrimaryKey(reviewUserId);
                surveyAssignOrg.setReviewUserName(reviewUser.getUserName());
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyAssignOrg.getReviewUserName() == null ? "" : surveyAssignOrg.getReviewUserName());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                    record.setUpdAttr("survey_assign_org_review_user_id" + surveyAssignOrg.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(reviewUserIdRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("updDistributionAgencyTime".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                String beforeValue = surveyAssignOrg.getCreateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(surveyAssignOrg.getCreateTime());
                surveyAssignOrg.setUpdateBy(userInfo.getUserName());
                surveyAssignOrg.setUpdateTime(new Date());
                Date startTime = DateUtils.parseDate(apiRequest.getString("endTime"), "yyyy-MM-dd");
                String distributionAgencyTimeRemark = apiRequest.getString("distributionAgencyTimeRemark");
                Date minTime = DateUtils.parseDate(apiRequest.getString("minTime"), "yyyy-MM-dd");
                Date maxTime = DateUtils.parseDate(apiRequest.getString("maxTime"), "yyyy-MM-dd");
                boolean falg = startTime.before(minTime);
                if (falg) {
                    return new ApiResponse(ApiMsgEnum.ProfileUpdateFail);
                }
                falg = maxTime.before(startTime);
                if (falg) {
                    return new ApiResponse(ApiMsgEnum.ProfileUpdateFail);
                }
                surveyAssignOrg.setCreateTime(startTime);

                Map findMap = new HashMap();
                findMap.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
                findMap.put("surveyAssignOrgId", surveyAssignOrg.getId());
                List<SurveyOrgPrescriptionFlow> SurveyOrgPrescriptionFlowList = surveyOrgPrescriptionFlowMapper.selectByInfoIdAndSurOrgIdPrescription(findMap);
                SurveyRiskCaseInfo surveyRiskCaseInfoTwo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyInfoId());
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfoTwo.getEntrustOrgId());
                if (SurveyOrgPrescriptionFlowList.size() > 0) {
                    for (SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow : SurveyOrgPrescriptionFlowList) {
                        surveyOrgPrescriptionFlow.setStartTime(startTime);
                        if (surveyOrgPrescriptionFlow.getEndTime() != null && surveyOrgPrescriptionFlow.getOperateType() == 2) {
                            int days = GetWorkDay.calLeaveDays(surveyOrgPrescriptionFlow.getStartTime(), surveyOrgPrescriptionFlow.getEndTime(), surveyConsignor.getEfficiencyAttr());
                            days = Math.abs(days);
                            surveyOrgPrescriptionFlow.setDays(Double.parseDouble(days + ""));

                        }
                        surveyOrgPrescriptionFlowMapper.updateByPrimaryKey(surveyOrgPrescriptionFlow);
                    }
                    int checkDays = GetWorkDay.calLeaveDays(surveyAssignOrg.getCreateTime(), surveyAssignOrg.getOrgEndTime(), surveyConsignor.getEfficiencyAttr());
                    surveyAssignOrg.setAgingCheck(Double.parseDouble(checkDays + ""));
                    double v = surveyOrgPrescriptionFlowMapper.selectOrgAgingDayByInfoIdAndSurOrgId(surveyAssignOrg.getSurveyInfoId(), surveyAssignOrg.getId());
                    surveyAssignOrg.setAgingReal(Math.abs(v));
                    surveyAssignOrg.setAgingOver(surveyAssignOrg.getAgingReal() - surveyAssignOrg.getAgingCheck() > 0 ? surveyAssignOrg.getAgingReal() - surveyAssignOrg.getAgingCheck() : 0);
                }
                surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyAssignOrg.getCreateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(surveyAssignOrg.getCreateTime()));
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                    record.setUpdAttr("survey_assign_org_create_time" + surveyAssignOrg.getId());
                    record.setUpdTime(new Date());
                    record.setUpdRemark(distributionAgencyTimeRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }


//                return new ApiResponse(ApiMsgEnum.ProfileUpdateSuccess);
            } else if ("getTime".equals(btnCode)) {
                Long surveyAssignOrgId = apiRequest.getLong("surveyAssignOrgId");
                SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyAssignOrgId);
                Map findMap = new HashMap();
                findMap.put("surveyAssorgCaseId", surveyAssignOrgId);
                List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseList = surveyInvestigatorCaseMapper.selectByList(findMap);
                Map map = new HashMap();
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyId());
                map.put("minTime", surveyRiskCase.getEntrustTime());
                if (surveyInvestigatorCaseList.size() > 0) {
                    Date maxTime = surveyInvestigatorCaseList.get(0).getAssignDate();
                    for (int i = 0; i < surveyInvestigatorCaseList.size(); i++) {
                        if (i + 1 == surveyInvestigatorCaseList.size()) {
                            map.put("maxTime", maxTime);
                            break;
                        }
                        boolean falg = surveyInvestigatorCaseList.get(i).getAssignDate().before(surveyInvestigatorCaseList.get(i + 1).getAssignDate());
                        if (!falg) {
                            falg = surveyInvestigatorCaseList.get(i).getAssignDate().before(maxTime);
                            if (!falg) {
                                maxTime = surveyInvestigatorCaseList.get(i).getAssignDate();
                            }
                        } else {
                            falg = maxTime.before(surveyInvestigatorCaseList.get(i + 1).getAssignDate());
                            if (falg) {
                                maxTime = surveyInvestigatorCaseList.get(i + 1).getAssignDate();
                            }
                        }

                    }

                } else {
                    map.put("maxTime", surveyAssignOrg.getOrgEndTime());
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS, 0, map);
            } else if ("entrustUpdate".equals(btnCode)) {
                String updateType = apiRequest.getString("updateType");//修改类型 ：org：修改委托机构；user:修改委托人
                Long entrustUser = apiRequest.getLong("entrustUser");

                if ("org".equals(updateType)) {
                    Long entrustOrg = apiRequest.getLong("entrustOrg");
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(entrustOrg);
                    if (surveyConsignor == null) {
                        return new ApiResponse(ApiMsgEnum.SURVEY_INVESTIGATER_NOT);
                    }
                }

                SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByUserId(entrustUser);
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                if ("org".equals(updateType)) {
                    surveyRiskCase.setEntrustOrgId(surveyConsigner.getEntrustOrgId());
                    surveyRiskCase.setEntrustOrgName(surveyConsigner.getEntrustOrgName());

                    Long entrustDepartment = apiRequest.getLong("entrustDepartment");
                    surveyRiskCase.setDepartmentId(entrustDepartment);
                    SurveyConsignorDepartment surveyConsignorDepartment = surveyConsignorDepartmentMapper.selectByPrimaryKey(entrustDepartment);
                    if (surveyConsignorDepartment != null) {
                        surveyRiskCase.setDepartmentName(surveyConsignorDepartment.getName());
                    }
                    surveyRiskCaseInfo.setEntrustOrgId(surveyConsigner.getEntrustOrgId());
                    surveyRiskCaseInfo.setEntrustOrgName(surveyConsigner.getEntrustOrgName());

                    List<SurveyInvestigatorCase> surveyInvestigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(id);
                    for (SurveyInvestigatorCase surveyInvestigatorCase : surveyInvestigatorCases) {
                        surveyInvestigatorCase.setEntrustOrgId(surveyConsigner.getEntrustOrgId());
                        surveyInvestigatorCase.setEntrustOrgName(surveyConsigner.getEntrustOrgName());
                        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
                    }
                }
                surveyRiskCase.setEntrustUserId(surveyConsigner.getUserId());
                surveyRiskCase.setEntrustUserName(surveyConsigner.getUserName());
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                surveyRiskCaseInfo.setCreateUserId(surveyConsigner.getUserId());
                surveyRiskCaseInfo.setCreateUserName(surveyConsigner.getUserName());
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
            } else if ("generateReportHelp".equals(btnCode)) {
                String generateReportPath = apiRequest.getString("generateReportPath");
                if (!"".equals(generateReportPath) && generateReportPath != null) {
                    CommonFile commonFile = new CommonFile();
                    commonFile.setFilePath(generateReportPath);
                    String fileName = generateReportPath.substring(generateReportPath.lastIndexOf("/") + 1);
                    commonFile.setFileName(fileName);
                    commonFile.setCreateTime(new Date());
                    commonFileMapper.insert(commonFile);
                    surveyRiskCaseInfo.setReportId(commonFile.getId());
                    surveyRiskCaseInfo.setReportName(fileName);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("updateTaskTypeInfo".equals(btnCode)) { //修改任务类型

                String returnType = apiRequest.getString("returnType");
                if ("updateCaseTaskType".equals(returnType)) { //修改案件任务类型
                    String taskIdStr = apiRequest.getString("taskIds");
                    //修改的时候 将原有的任务类型删除
                    List<SurveyTaskType> taskTypes = surveyTaskTypeMapper.getSurveyTaskTypeBySurveyInfoId(surveyRiskCaseInfo.getId());
                    for (SurveyTaskType taskType : taskTypes) {
                        surveyTaskTypeMapper.deleteByPrimaryKey(taskType.getId());
                    }

                    if (StringUtils.isNotEmpty(taskIdStr)) {
                        String[] taskIds = taskIdStr.split(",");
                        for (String taskId : taskIds) {
                            if (StringUtils.isNotEmpty(taskId)) {
                                SurveyTaskType surveyTaskType = SurveyTaskType.class.newInstance();
                                surveyTaskType.setSurveyId(surveyRiskCaseInfo.getSurveyId());
                                surveyTaskType.setSurveyInfoId(surveyRiskCaseInfo.getId());
                                surveyTaskType.setTaskId(Long.parseLong(taskId));
                                SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyTaskType.getTaskId());
                                surveyTaskType.setTaskName(surveyTaskInfo.getName());
                                surveyTaskTypeMapper.insert(surveyTaskType);
                            }
                        }
                    }
                } else if ("updateOrgTaskType".equals(returnType)) { //修改机构案件任务类型
                    Long assignOrgId = apiRequest.getLong("assignOrgId");
                    String taskIdStr = apiRequest.getString("taskIds");
                    SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(assignOrgId);
                    //修改的时候 将原有的任务类型删除
                    Map map = new HashMap<>();
                    map.put("surveyAssignOrgId", assignOrgId);
                    List<SurveyAssignOrgType> taskTypes = surveyAssignOrgTypeMapper.list(map);
                    for (SurveyAssignOrgType taskType : taskTypes) {
                        surveyAssignOrgTypeMapper.deleteByPrimaryKey(taskType.getId());
                    }

                    if (StringUtils.isNotEmpty(taskIdStr)) {
                        String[] taskIds = taskIdStr.split(",");
                        for (String taskId : taskIds) {
                            if (StringUtils.isNotEmpty(taskId)) {
                                SurveyAssignOrgType assignOrgType = SurveyAssignOrgType.class.newInstance();
                                assignOrgType.setSurveyId(surveyRiskCaseInfo.getSurveyId());
                                assignOrgType.setSurveyInfoId(surveyRiskCaseInfo.getId());
                                assignOrgType.setSurveyAssignOrgId(assignOrgId);
                                assignOrgType.setTaskId(Long.parseLong(taskId));
                                SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(assignOrgType.getTaskId());
                                assignOrgType.setTaskName(surveyTaskInfo.getName());
                                assignOrgType.setSurveyOrgId(surveyAssignOrg.getSurveyOrgId());
                                assignOrgType.setSurveyOrgName(surveyAssignOrg.getSurveyOrgName());
                                surveyAssignOrgTypeMapper.insert(assignOrgType);
                            }
                        }
                    }
                }
            } else if ("1701".equals(btnCode)) {
                String surveyInfo = apiRequest.getString("surveyInfo");
                String surveyInfoRemark = apiRequest.getString("surveyInfoRemark");
                String beforeValue = surveyRiskCaseInfo.getSurveyInfo();
                surveyRiskCaseInfo.setSurveyInfo(surveyInfo);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfo.getSurveyInfo() == null ? "" : surveyRiskCaseInfo.getSurveyInfo());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_survey_info");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(surveyInfoRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

            } else if ("1702".equals(btnCode)) {
                String surveyItem = apiRequest.getString("surveyItem");
                String surveyItemRemark = apiRequest.getString("surveyItemRemark");
                String beforeValue = surveyRiskCaseInfo.getSurveyItem();
                surveyRiskCaseInfo.setSurveyItem(surveyItem);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfo.getSurveyItem() == null ? "" : surveyRiskCaseInfo.getSurveyItem());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_survey_item");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(surveyItemRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }

                //更新机构调查要求
                Map map = new HashMap();
                map.put("surveyInfoId", surveyRiskCaseInfo.getId());
                List<SurveyAssignOrgDto> assignOrgs = surveyAssignOrgMapper.list(map);
                for (SurveyAssignOrgDto item : assignOrgs) {
                    beforeValue = item.getOrgTaskRemark();
                    item.setOrgTaskRemark(surveyItem);
                    surveyAssignOrgMapper.updateByPrimaryKey(item);

                    record = new SurveyAttrUpdRecord();
                    record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                    record.setUpdAfterValue(item.getOrgTaskRemark() == null ? "" : item.getOrgTaskRemark());
                    if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                        record.setSurveyInfoId(item.getSurveyInfoId());
                        record.setUpdAttr("survey_assign_org_org_task_remark" + item.getId());
                        record.setUpdTime(new Date());
                        record.setUpdRemark("调整案件任务描述，关联变更");
                        record.setUpdUserName(userInfo.getUserName());
                        surveyAttrUpdRecordMapper.insert(record);
                    }
                }
                //更新调查员的调查要求
                ApiRequest apiRequest1 = new ApiRequest();
                apiRequest1.put("surveyInfoId", surveyRiskCaseInfo.getId());
                List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(apiRequest1);
                for (SurveyInvestigatorCaseDto aCase : cases) {
                    beforeValue = aCase.getSurveyTaskRemark();
                    aCase.setSurveyTaskRemark(surveyItem);
                    surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);
                    record = new SurveyAttrUpdRecord();
                    record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                    record.setUpdAfterValue(aCase.getSurveyTaskRemark() == null ? "" : aCase.getSurveyTaskRemark());
                    if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                        record.setSurveyInfoId(aCase.getSurveyInfoId());
                        record.setUpdAttr("survey_investigator_case_survey_remark" + aCase.getId());
                        record.setUpdTime(new Date());
                        record.setUpdRemark("调整案件任务描述，关联变更");
                        record.setUpdUserName(userInfo.getUserName());
                        surveyAttrUpdRecordMapper.insert(record);
                    }
                }
            } else if ("1703".equals(btnCode)) {
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                String beforeValue = surveyRiskCase.getSurveyCaseNo();
                String surveyCaseNo = apiRequest.getString("surveyCaseNo");
                String surveyCaseNoRemark = apiRequest.getString("surveyCaseNoRemark");
                surveyRiskCase.setSurveyCaseNo(surveyCaseNo.replace(" ", "").replace("\t", "").trim());
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCase.getSurveyCaseNo() == null ? "" : surveyRiskCase.getSurveyCaseNo());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_survey_case_no");
                    record.setUpdTime(new Date());
                    record.setUpdRemark(surveyCaseNoRemark);
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("guide".equals(btnCode) || "guided".equals(btnCode)) { //案件指导
                Long guideId = apiRequest.getLong("guideId");
                SurveyRiskCaseGuide surveyRiskCaseGuide = surveyRiskCaseGuideMapper.selectByPrimaryKey(guideId);
                if (surveyRiskCaseGuide != null) { //更新
                    surveyRiskCaseGuide = ConvertToBeanUtil.toBean(apiRequest, surveyRiskCaseGuide);
                    surveyRiskCaseGuide.setId(guideId);
                    surveyRiskCaseGuide.setUpdateBy(userInfo.getUserId());// 更新人id
                    surveyRiskCaseGuide.setUpdateTime(new Date());//更新时间
                    surveyRiskCaseGuide.setGuideBy(userInfo.getUserId());
                    surveyRiskCaseGuide.setGuideByName(userInfo.getUserName());
                    surveyRiskCaseGuide.setGuideTime(new Date());
                    surveyRiskCaseGuideMapper.updateByPrimaryKey(surveyRiskCaseGuide);
                } else {
                    surveyRiskCaseGuide = ConvertToBeanUtil.toBean(apiRequest, SurveyRiskCaseGuide.class);
                    surveyRiskCaseGuide.setSurveyInfoId(id);
                    surveyRiskCaseGuide.setCreateBy(userInfo.getUserId());// 创建人
                    surveyRiskCaseGuide.setCreateByName(userInfo.getUserName());//
                    surveyRiskCaseGuide.setCreateTime(new Date());//创建时间
                    surveyRiskCaseGuide.setDeleteFlag(0);
                    surveyRiskCaseGuide.setGuideBy(userInfo.getUserId()); // 指导人
                    surveyRiskCaseGuide.setGuideByName(userInfo.getUserName());
                    surveyRiskCaseGuide.setGuideTime(new Date());
                    surveyRiskCaseGuideMapper.insert(surveyRiskCaseGuide);
                }
                String guideType = apiRequest.getString("guideType");
                if (guideType == null) {
                    surveyRiskCaseInfo.setGuideState(1); //已指导
                } else {
                    surveyRiskCaseInfo.setGuideState(0); //未指导
                }
            } else if ("visit".equals(btnCode)) { //调查回访
                Long visitId = apiRequest.getLong("visitId");
                Long surveyInfoId = apiRequest.getLong("surveyInfoId");
//                Long surveyAssorgCaseId = apiRequest.getLong("surveyAssorgCaseId");

                Map<String, Object> map = new HashMap<>();
                map.put("surveyInfoId", surveyInfoId);
                SurveyRiskCaseVisit surveyRiskCaseVisit = surveyRiskCaseVisitMapper.selectOne(map);
                if (surveyRiskCaseVisit != null) { //更新
                    surveyRiskCaseVisit.setVisitNote(apiRequest.getString("visitNote"));
                    surveyRiskCaseVisit.setIsAbnormal(apiRequest.getInt("isAbnormal"));
                    surveyRiskCaseVisit.setVisitTime(new Date());
                    surveyRiskCaseVisit.setUpdateBy(userInfo.getUserId());// 更新人id
                    surveyRiskCaseVisit.setUpdateTime(new Date());//更新时间
                    surveyRiskCaseVisit.setVisitState(1);
                    surveyRiskCaseVisitMapper.updateByPrimaryKey(surveyRiskCaseVisit);
                } else {
                    surveyRiskCaseVisit = ConvertToBeanUtil.toBean(apiRequest, SurveyRiskCaseVisit.class);
                    surveyRiskCaseVisit.setSurveyInfoId(surveyInfoId);
//                    surveyRiskCaseVisit.setSurveyAssorgCaseId(surveyAssorgCaseId);
                    surveyRiskCaseVisit.setVisitTime(new Date());
                    surveyRiskCaseVisit.setVisitPerson(userInfo.getUserName());
                    surveyRiskCaseVisit.setCreateBy(userInfo.getUserId());// 创建人
                    surveyRiskCaseVisit.setCreateByName(userInfo.getUserName());//
                    surveyRiskCaseVisit.setCreateTime(new Date());//创建时间
                    surveyRiskCaseVisit.setDeleteFlag(0);
                    surveyRiskCaseVisit.setVisitState(1);
                    surveyRiskCaseVisitMapper.insert(surveyRiskCaseVisit);
                }
                //加入进度
                String abnormal = "否";
                if (surveyRiskCaseVisit.getIsAbnormal() != null && surveyRiskCaseVisit.getIsAbnormal() == 1) {
                    abnormal = "是";
                }
                String progressName = "回访记录：" + surveyRiskCaseVisit.getVisitNote() + "。回访异常：" + abnormal + "";
                backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(), surveyRiskCaseInfo.getId(), userInfo.getUserId(), userInfo.getUserName(), "调查回访", progressName);

            } else if ("updateDirectionSort".equals(btnCode)) {//更新方向排序
                String[] ids = apiRequest.getString("ids").split(",");
                for (int i = 0; i < ids.length; i++) {
                    SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(Long.parseLong(ids[i]));
                    surveyCaseDirection.setSort(ids.length - i);
                    surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
                }

                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("updIniureTime".equals(btnCode)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String insureTime = apiRequest.getString("insureTime");
                Long riskId = apiRequest.getLong("riskId");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(riskId);
                if (StringUtils.isNotBlank(insureTime) && surveyRiskCase != null) {
                    surveyRiskCase.setInsureTime(simpleDateFormat.parse(insureTime));
                    surveyRiskCaseMapper.updateByPrimaryKeySelective(surveyRiskCase);
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                } else {
                    return new ApiResponse(ApiMsgEnum.FAIL);
                }
            } else if ("updHandleId".equals(btnCode)) {
                String beforeValue = surveyRiskCaseInfo.getHandleId() == null ? "" : surveyRiskCaseInfo.getHandleId().toString();
                Long handleId = apiRequest.getLong("handleId");
                surveyRiskCaseInfo.setHandleId(handleId);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

                SurveyAttrUpdRecord record = new SurveyAttrUpdRecord();
                record.setUpdBeforeValue(beforeValue == null ? "" : beforeValue);
                record.setUpdAfterValue(surveyRiskCaseInfo.getHandleId() == null ? "" : surveyRiskCaseInfo.getHandleId().toString());
                if (!record.getUpdBeforeValue().equals(record.getUpdAfterValue())) {
                    record.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    record.setUpdAttr("survey_risk_case_info_handle_id");
                    record.setUpdTime(new Date());
                    record.setUpdRemark("");
                    record.setUpdUserName(userInfo.getUserName());
                    surveyAttrUpdRecordMapper.insert(record);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else if ("changeBelongUser".equals(btnCode)) {
                Long newBelongUserId = apiRequest.getLong("newBelongUserId");
                UserInfo uInfo = userInfoMapper.selectByPrimaryKey(newBelongUserId);
                String[] caseInfoIds = apiRequest.getString("caseInfoIds").split(",");
                for (int i = 0; i < caseInfoIds.length; i++) {
                    SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.selectByPrimaryKey(Long.parseLong(caseInfoIds[i]));
                    info.setBelongUserId(newBelongUserId);
                    info.setBelongUserName(uInfo.getUserName());
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(info);
                }
            } else if ("org-remind".equals(btnCode))//机构提醒
            {
                String selOrgIds = apiRequest.getString("selOrgIds");
                String remindRemark = apiRequest.getString("remindRemark");
                SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                if (!StringUtils.isEmpty(selOrgIds)) {
                    String[] ids = selOrgIds.split(",");
                    for (String s : ids) {
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        paramMap.put("roleId", 58L);
                        String parentOrgId = surveyFranchiseeMapper.selectParentOrgId(Long.parseLong(s));
                        paramMap.put("orgId", parentOrgId);
                        List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);

                        Map<String, Object> msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "机构提醒");
                        msgMap.put("content", remindRemark);
                        msgMap.put("keyWords", "案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson());
                        backendWechatApi.send(toUsers, msgMap);
                    }
                }
                backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(), surveyRiskCaseInfo.getId(), userInfo.getUserId(), userInfo.getUserName(), "机构提醒", remindRemark);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            } else {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            //风控审核之后的状态 调查员案件状态
//            if (surveyRiskCaseInfo.getSurveyState() >= 22) {
//                List<SurveyInvestigatorCase> investigatorCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(id);
//                for (SurveyInvestigatorCase investigatorCase : investigatorCases) {
//                    investigatorCase.setSurveyState(surveyRiskCaseInfo.getSurveyState());
//                    investigatorCase.setSurveyStateName(surveyRiskCaseInfo.getSurveyStateName());
//                    surveyInvestigatorCaseMapper.updateByPrimaryKey(investigatorCase);
//                }
//            }

            //添加进度
            setProgress(surveyRiskCaseInfo, userInfo, btnCode, opinion);
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    private Boolean updaInfo(String updCode, String updValue, SurveyRiskCaseInfo surveyRiskCaseInfo, Long directionId) {
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        if (directionId == null) {//修改除方向之外的信息
            switch (updCode) {
                case "upd-survey-person":
                    surveyRiskCase.setSurveyPerson(updValue);
                    break;
                case "upd-survey-person-tel":
                    surveyRiskCase.setSurveryPersonTel(updValue);
                    break;
                case "upd-age":
                    surveyRiskCase.setAge(Integer.parseInt(updValue));
                    break;
                case "upd-survey-case-no":
                    surveyRiskCase.setSurveyCaseNo(updValue.replace(" ", "").replace("\t", "").trim());
                    break;
                case "upd-insure-type":
                    surveyRiskCase.setInsureName(updValue);
                    break;
                case "upd-danger-address":
                    surveyRiskCase.setDangerAddress(updValue);
                    break;
                case "upd-survey-item":
                    surveyRiskCaseInfo.setSurveyItem(updValue);
                    break;
                case "upd-survey-info":
                    surveyRiskCaseInfo.setSurveyInfo(updValue);
                    break;
                case "upd-sex":
                    if ("男".equals(updValue)) surveyRiskCase.setSex(1);
                    if ("女".equals(updValue)) surveyRiskCase.setSex(2);
                    break;
                case "upd-sex-new":
                    surveyRiskCase.setSex(Integer.parseInt(updValue));
                    break;
                case "upd-nation":
                    surveyRiskCase.setAge(Integer.parseInt(updValue));
                    break;
                case "upd-id-number":
                    surveyRiskCase.setIdNumber(updValue);
                    break;
                case "upd-report-completion":
                    surveyRiskCaseInfo.setReportCompletion(updValue);
                    break;
                case "upd-policy-no":
                    surveyRiskCase.setPolicyNo(updValue);
                    break;
                case "upd-claims-no":
                    surveyRiskCase.setClaimsNo(updValue);
                    break;
                case "upd-org-opr-opinion":
                    surveyRiskCaseInfo.setOrgOprOpinion(updValue);
                    break;
                case "upd-survey-report-completion":
                    surveyRiskCaseInfo.setReportCompletion(updValue);
                    break;
                case "upd-claims-money":
                    surveyRiskCase.setClaimsMoney(Double.valueOf(updValue));
                    break;
                case "upd-hz-contact-name":
                    surveyRiskCase.setHzContactName(updValue);
                    break;
                case "upd-hz-contact-tel":
                    surveyRiskCase.setHzContactTel(updValue);
                    break;
                default:
                    break;
            }
            surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
        } else {//修改方向信息
            SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
            if ("upd-direction-name".equals(updCode)) {
                surveyCaseDirection.setDirectionName(updValue);
            } else if ("upd-direction-info".equals(updCode)) {
                surveyCaseDirection.setDirectionText(updValue);
            }
            surveyCaseDirection.setDirectionName(LFStringUtil.replacePrint(surveyCaseDirection.getDirectionName()));
            surveyCaseDirection.setDirectionText(LFStringUtil.replacePrint(surveyCaseDirection.getDirectionText()));
            surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
        }
        return true;
    }

    private Boolean updaInfo(String updCode, Date updValue, SurveyRiskCaseInfo surveyRiskCaseInfo) {
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        switch (updCode) {
            case "upd-entrust-time":
                surveyRiskCase.setEntrustTime(updValue);
                break;
            case "upd-insure-time":
                surveyRiskCase.setInsureTime(updValue);
                break;
            case "upd-danger-time":
                surveyRiskCase.setDangerTime(updValue);
                break;
            case "upd-lefan-report-date":
                surveyRiskCaseInfo.setLefanReportDate(updValue);
                break;
            case "upd-insure-take-time":
                surveyRiskCase.setInsureTakeTime(updValue);
                break;
            case "upd-entrust-report-start-date":
                surveyRiskCaseInfo.setEntrustReportStartDate(updValue);
                break;
            default:
                break;
        }
        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
        surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);
        return true;
    }

    private ApiResponse markBas(ApiRequest apiRequest, UserInfo userInfo, Integer type) {
        try {
            String idStr = apiRequest.getString("ids");
            String[] ids = idStr.split(",");

            if (type == 1) {
                surveyRiskCaseInfoMapper.updatePrice1IsCalc(Arrays.asList(ids));
            } else if (type == 2) {
                surveyRiskCaseInfoMapper.updatePrice2IsCalc(Arrays.asList(ids));
            }
//            for (String id : ids) {
//                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(Long.parseLong(id));
//                if (type == 1){
//                    surveyRiskCaseInfo.setPrice1IsCalc(1);
//                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
////                    List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
////                    //生成基本费的 待提现清单
////                    for (SurveyInvestigatorCase aCase : cases) {
////                        Long num = 1000L;
////                        SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(aCase.getSurveyUserId());
////                        if (investigator == null){
////                            continue;
////                        }
////                        //判断是否第一次完成任务  第一次额外增加 1000乐凡币和成就点
////                        int count = surveyInvestigatorCaseMapper.getFirstCloseSurveyInvestigatorCase(investigator.getUserId());
////                        if (count == 0){
////                            backendSurveyInvestigatorApi.addPrice(aCase.getSurveyUserId(),num,3,"初次完成案件",aCase.getId(),2);
////                        }
////                        //正常增加乐凡币和成就点
////                        if (aCase.getSurveyTaskMoney() == null){
////                            aCase.setSurveyTaskMoney(0D);
////                        }
////                        if (aCase.getSurveyTaskMoney() != 0D){
////                            num = Math.round(aCase.getSurveyTaskMoney());
////                            backendSurveyInvestigatorApi.addPrice(aCase.getSurveyUserId(),num,3,"完成调查案件(基本费)",aCase.getId(),2);
////                        }
////
////                        if (aCase.getSurveyTaskMoney() != 0D){
////                            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(aCase.getSurveyId());
////                            SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.selectByPrimaryKey(aCase.getSurveyInfoId());
////                            SurveyCashInfoRecord record = new SurveyCashInfoRecord();
////                            record.setSurveyId(aCase.getSurveyId());
////                            record.setSurveyInfoId(aCase.getSurveyInfoId());
////                            record.setSurveyInvestigatorCaseId(aCase.getId());
////                            record.setSurveyCaseName(surveyRiskCase.getSurveyPerson().concat("的").concat(info.getSurveyBusName()));
////                            record.setSurveyUserId(aCase.getSurveyUserId());
////                            record.setSurveyUserName(aCase.getSurveyUserName());
////                            record.setSurveyTaskMoney(aCase.getSurveyTaskMoney());
////                            record.setFranchiseeId(investigator.getOrgId());
////                            record.setFranchiseeName(investigator.getOrgName());
////                            record.setCashState(1);
////                            record.setCashType(1);
////                            record.setCreateBy(userInfo.getUserName());
////                            record.setCreateTime(new Date());
////                            record.setUpdateBy(null);
////                            record.setUpdateTime(null);
////                            record.setDeleteFlag(0);
////                            surveyCashInfoRecordMapper.insert(record);
////                        }
////                    }
////                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
//                }else if (type == 2){
//                    surveyRiskCaseInfo.setPrice2IsCalc(1);
//                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
////                    List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
////                    //生成减损奖励的 待提现清单
////                    for (SurveyInvestigatorCase aCase : cases) {
////                        //正常增加乐凡币和成就点
////                        if (aCase.getSurveryReLosses() == null){
////                            aCase.setSurveryReLosses(0D);
////                        }
////                        if (aCase.getSurveryReLosses() != 0D){
////                            Long num = Math.round(aCase.getSurveryReLosses());
////                            backendSurveyInvestigatorApi.addPrice(aCase.getSurveyUserId(),num,3,"完成调查案件(减损奖励)",aCase.getId(),2);
////                        }
////
////                        if (aCase.getSurveryReLosses() != 0D){
////                            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(aCase.getSurveyId());
////                            SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.selectByPrimaryKey(aCase.getSurveyInfoId());
////                            SurveyCashInfoRecord record = new SurveyCashInfoRecord();
////                            record.setSurveyId(aCase.getSurveyId());
////                            record.setSurveyInfoId(aCase.getSurveyInfoId());
////                            record.setSurveyInvestigatorCaseId(aCase.getId());
////                            record.setSurveyCaseName(surveyRiskCase.getSurveyPerson().concat("的").concat(info.getSurveyBusName()));
////                            record.setSurveyUserId(aCase.getSurveyUserId());
////                            record.setSurveyUserName(aCase.getSurveyUserName());
////                            record.setSurveyTaskMoney(aCase.getSurveryReLosses());
////                            SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(aCase.getSurveyUserId());
////                            record.setFranchiseeId(investigator.getOrgId());
////                            record.setFranchiseeName(investigator.getOrgName());
////                            record.setCashState(1);
////                            record.setCashType(2);
////                            record.setCreateBy(userInfo.getUserName());
////                            record.setCreateTime(new Date());
////                            record.setUpdateBy(null);
////                            record.setUpdateTime(null);
////                            record.setDeleteFlag(0);
////                            surveyCashInfoRecordMapper.insert(record);
////                        }
////                    }
////                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
//                }
//            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


    /**
     * 添加进度
     *
     * @param surveyRiskCaseInfo
     * @param userInfo
     * @param btnCode
     * @param desc
     */
    private void setProgress(SurveyRiskCaseInfo surveyRiskCaseInfo, UserInfo userInfo, String btnCode, String desc) {
        if ("1301".equals(btnCode)) {//2019年9月5日 进度循环添加
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String progressName = "";
        switch (btnCode) {
            case "1200":
                progressName = "受理通过，待分派";
                break;
            case "1201":
                progressName = "受理审核不通过";
                break;
            case "1300":
                progressName = "平台复审通过，保司待审核(" + sdf.format(surveyRiskCaseInfo.getEntrustReportStartDate()) + ")";
                break;
            case "13001":
                progressName = "平台复审通过，保司待审核(" + sdf.format(surveyRiskCaseInfo.getEntrustReportStartDate()) + ")";
                break;
            case "1301":
                progressName = "平台复审退回";
                break;
            case "1400":
                progressName = "保司终审通过(" + sdf.format(surveyRiskCaseInfo.getEntrustReportEndDate()) + ")";
                break;
            case "1401":
                progressName = "保司审核退回";
                break;
            case "1500":
                progressName = "已发起结案申请";
                break;
            case "1600":
                progressName = "结案审核通过，已结案";
                break;
            case "1601":
                progressName = "结案审核不通过";
                break;

            case "assignation":
                progressName = "已分派，调查中";
                break;
            default:
                progressName = "";
                break;
        }
        if (!"".equals(progressName)) {
            if ("".equals(desc) || desc == null) {
                desc = "同意";
            }
            backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(), surveyRiskCaseInfo.getId(), userInfo.getUserId(), userInfo.getUserName(), progressName, desc);
        }
    }

    @ApiMethod(needLogin = false, descript = "跟踪列表", value = "list-follow-survey-risk-case-info")
    @Override
    public ApiResponse follows(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        List<SurveyFollowDto> followDtos = surveyFollowMapper.getSurveyFollowsBySurveyInfoId(surveyInfoId);
        for (SurveyFollowDto followDto : followDtos) {
            List<SurveyFollowFileDto> followFileDtos = surveyFollowFileMapper.getSurveyFollowFilesByFollowId(followDto.getId());
            followDto.setSurveyFollowFiles(followFileDtos);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, followDtos.size(), followDtos);
    }

    @ApiMethod(needLogin = false, descript = "添加跟踪/反馈回复", value = "add-follow-survey-risk-case-info")
    @Override
    public ApiResponse addFollow(ApiRequest apiRequest) {
        try {
            Long currentUserId = getCurrentUserId(apiRequest);
            Long id = apiRequest.getLong("id");
            String btnCode = apiRequest.getString("btnCode");
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(id);
            if ("follow".equals(btnCode)) {
                SurveyFollow surveyFollow = new SurveyFollow();
                Date nextFollowTime = DateUtils.parseDate(apiRequest.getString("nextFollowTime"), "yyyy-MM-dd HH:mm:ss");
                apiRequest.put("nextFollowTime", nextFollowTime);
                surveyFollow = ConvertToBeanUtil.toBean(apiRequest, surveyFollow);
                surveyFollow.setSurveyId(surveyRiskCaseInfo.getSurveyId());
                surveyFollow.setSurveyInfoId(id);
                surveyFollow.setFollowUserId(currentUserId);
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
                surveyFollow.setFollowUserName(userInfo.getUserName());
                surveyFollow.setFollowTime(new Date());
                surveyFollowMapper.insert(surveyFollow);
                //跟踪附件
                String files = apiRequest.getString("files");
                if (StringUtils.isNotEmpty(files)) {
                    List<SurveyFileDto> fileDto = JSONArray.parseArray(files, SurveyFileDto.class);
                    for (SurveyFileDto surveyFileDto : fileDto) {
                        CommonFile commonFile = new CommonFile();
                        String filePath = surveyFileDto.getFilePath();
                        commonFile.setFilePath(filePath);
                        int firstName = filePath.lastIndexOf("\\") + 1;
                        int lastName = filePath.lastIndexOf(".");
                        String name = filePath.substring(firstName, lastName);
                        commonFile.setFileName(name);
                        commonFileMapper.insert(commonFile);
                        SurveyFollowFile surveyFollowFile = new SurveyFollowFile();
                        surveyFollowFile.setFollowId(surveyFollow.getId());
                        surveyFollowFile.setFileId(commonFile.getId());
                        surveyFollowFile.setFileName(surveyFileDto.getFileRealName());
                        surveyFollowFile.setCreateBy(userInfo.getUserName());
                        surveyFollowFile.setCreateTime(new Date());
                        surveyFollowFile.setUpdateBy(null);
                        surveyFollowFile.setUpdateTime(null);
                        surveyFollowFile.setDeleteFlag(0);
                        surveyFollowFileMapper.insert(surveyFollowFile);
                    }
                }
            } else if ("backreply".equals(btnCode)) {
                SurveyBackReply surveyBackReply = new SurveyBackReply();
                surveyBackReply = ConvertToBeanUtil.toBean(apiRequest, surveyBackReply);
                surveyBackReply.setSurveyId(surveyRiskCaseInfo.getSurveyId());
                surveyBackReply.setSurveyInfoId(id);
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
                surveyBackReply.setCreateBy(userInfo.getUserName());
                surveyBackReply.setCreateTime(new Date());
                surveyBackReplyMapper.insert(surveyBackReply);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false, descript = "案件分派", value = "assignation-survey-risk-case-info")
    @Override
    public ApiResponse assignation(ApiRequest apiRequest) {
        try {
            String btnCode = apiRequest.getString("btnCode");
            Long id = apiRequest.getLong("id");
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(id);
            surveyRiskCaseInfo.setSurveyState(12);
            surveyRiskCaseInfo.setSurveyStateName("调查中");
            surveyRiskCaseInfo.setLefanReportDate(null);
            surveyRiskCaseInfo.setEntrustReportStartDate(null);
            surveyRiskCaseInfo.setEntrustReportEndDate(null);
            surveyRiskCaseInfo.setLefanReportDate(null);
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
            Long currentUserId = getCurrentUserId(apiRequest);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            if ("112".equals(btnCode)) {// 如果分派人是NULl 则是分派机构  否则是分派人
                Long assignUserId = apiRequest.getLong("assignUserId");
                if (assignUserId == null) {
                    return assignOrg(apiRequest, surveyRiskCaseInfo, userInfo, false);
                } else {
                    return assignUser(apiRequest, surveyRiskCaseInfo, userInfo, false);
                }
            } else if ("111".equals(btnCode) || "113".equals(btnCode)) {//分派
                return assignUser(apiRequest, surveyRiskCaseInfo, userInfo, false);
            } else if ("115".equals(btnCode)) {//乐凡改派
                return updAssign(apiRequest);
            } else if ("116".equals(btnCode)) {//机构改派
                return updAssign(apiRequest);
            } else if ("117".equals(btnCode)) {
                return updAssignOrg(apiRequest, surveyRiskCaseInfo, userInfo);
            }

            //查询案件类型
            surveyRiskCaseInfo.setCaseState(findCaseState(surveyRiskCaseInfo));
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            return new ApiResponse(ApiMsgEnum.ERROR_PARAMETER);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 分派机构
     *
     * @param apiRequest
     * @param surveyRiskCaseInfo
     * @param userInfo
     * @return
     */
    private ApiResponse assignOrg(ApiRequest apiRequest, SurveyRiskCaseInfo surveyRiskCaseInfo, UserInfo userInfo, Boolean upd) {
        Long surveyOrgId = apiRequest.getLong("surveyOrgId");
        Date orgEndTime = DateUtils.parseDate(apiRequest.getString("endTime"), "yyyy-MM-dd HH:mm:ss");
        String orgTaskRemark = apiRequest.getString("taskRemark");
        Long backCaseId = apiRequest.getLong("backCaseId");
        Long oprUser = apiRequest.getLong("oprUser");//终审人员

        SurveyBackCase surveyBackCase = surveyBackCaseMapper.selectByPrimaryKey(backCaseId);
        SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyOrgId);
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("surveyOrgId", surveyOrgId);
        map.put("surveyInfoId", surveyRiskCaseInfo.getId());
        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
        if (surveyAssignOrg != null) {//已分配过的机构提示  如果不是协助调查的  提示不可重复分配
            if (surveyBackCase == null) {
                if (surveyAssignOrg.getOrgSurveyState() == 3 || surveyAssignOrg.getDeleteFlag() == 1) {//已分配过的机构如果是已拒绝状态 则修改为待接收
                    surveyAssignOrg.setOrgEndTime(orgEndTime);
                    surveyAssignOrg.setOldOrgEndTime(orgEndTime);
                    surveyAssignOrg.setOrgTaskRemark(orgTaskRemark);
                    //2019年9月27日10点43分  改为自动接收
                    surveyAssignOrg.setOrgSurveyState(1);
                    surveyAssignOrg.setOrgSurveyStateName("调查中");
                    surveyAssignOrg.setDeleteFlag(0);
                    surveyAssignOrg.setReviewTime(null);
                    surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                } else {
                    return new ApiResponse(ApiMsgEnum.SURVEY_ASSIGN_ORG);
                }
            }
        } else {
            surveyAssignOrg = new SurveyAssignOrg();
            surveyAssignOrg.setSurveyOrgId(surveyOrgId);
            surveyAssignOrg.setSurveyOrgName(surveyFranchisee.getName());
            surveyAssignOrg.setSurveyId(surveyRiskCaseInfo.getSurveyId());
            surveyAssignOrg.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyAssignOrg.setOrgEndTime(orgEndTime);
            surveyAssignOrg.setOldOrgEndTime(orgEndTime);
            surveyAssignOrg.setOrgTaskRemark(orgTaskRemark);
            surveyAssignOrg.setCreateBy(userInfo.getUserName());
            surveyAssignOrg.setCreateTime(new Date());
            surveyAssignOrg.setDeleteFlag(0);
//            surveyAssignOrg.setOrgSurveyState(0);
//            surveyAssignOrg.setOrgSurveyStateName("待接收");

            //2019年9月27日10点43分  改为自动接收
            surveyAssignOrg.setOrgSurveyState(1);
            surveyAssignOrg.setOrgSurveyStateName("调查中");
            surveyAssignOrg.setReportState(0);
            int havaPrimary = surveyAssignOrgMapper.selectIsHavePrimaryOrg(surveyRiskCaseInfo.getId());
            if (havaPrimary == 0) {
                surveyAssignOrg.setOrgPrimaryType(1);//设置为主调查机构
            } else {
                surveyAssignOrg.setOrgPrimaryType(2);
            }

            //2019-10-31 平台分派：业务类型、结算方式等数据关联到机构上
            surveyAssignOrg.setServicesId(apiRequest.getLong("servicesId")); // 业务类型
            surveyAssignOrg.setServicesName(apiRequest.getString("servicesName"));
            surveyAssignOrg.setPayType(apiRequest.getInt("payType")); //结算方式
            surveyAssignOrg.setSurveyMoney(apiRequest.getDouble("surveyMoney")); //调查费(分派)
            surveyAssignOrg.setSurveyMoneySubmit(apiRequest.getDouble("surveyMoney"));//'确认调查费（风控审核，默认为分派的价格）
            surveyAssignOrg.setAssessOrgMoney(apiRequest.getDouble("surveyMoney"));//考核前机构价格
            surveyAssignOrg.setSurveryReLoossesRemark(apiRequest.getString("surveryReLoossesRemark")); // 减损描述（分派）
            surveyAssignOrg.setSurveyPay(0);
            surveyAssignOrg.setReturnState(0);
            surveyAssignOrg.setSurveyReturn(0);
            surveyAssignOrg.setReviewOff(0);
            surveyAssignOrg.setOverdueAgingRate(1D);
            //互助案件 需要把终审人员信息保存到机构案件表 2。录入或更新SurveyRiskInfoFinalUser（下一个终审人员信息）
            Long entrustOrgId = surveyRiskCaseInfo.getEntrustOrgId();
            SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(entrustOrgId);
            if (surveyConsignorModel != null && surveyConsignorModel.getModelId() == 5) {
                surveyAssignOrg.setReviewUserId(oprUser);
                surveyAssignOrg.setReviewUserName(userInfoMapper.selectByPrimaryKey(oprUser).getUserName());

                //2。录入或更新SurveyRiskInfoFinalUser（下一个终审人员信息）
                updateSurveyRiskInfoFinalUser(entrustOrgId, surveyOrgId, oprUser);
            }
            int days = GetWorkDay.calLeaveDays(new Date(), orgEndTime, surveyConsignor.getEfficiencyAttr());
            surveyAssignOrg.setReviewTime(null);
            surveyAssignOrg.setAgingOver(0d);
            surveyAssignOrg.setAgingReal(0d);
            surveyAssignOrg.setAgingCheck((double) days);
            if (surveyConsignor.getOrgAttr() == 1) { // 判断是否是保司案子
                if (surveyAssignOrg.getServicesId() == 13) {
                    if (apiRequest.getDouble("surveyMoney") == null) {
                        Double deepCasesPrice = apiRequest.getDouble("deepCasesPrice") == null ? apiRequest.getDouble("surveyMoney") : apiRequest.getDouble("deepCasesPrice");
                        surveyAssignOrg.setSurveyMoney(deepCasesPrice); //调查费(分派)
                        surveyAssignOrg.setSurveyMoneySubmit(deepCasesPrice);//'确认调查费（风控审核，默认为分派的价格）
                        surveyAssignOrg.setAssessOrgMoney(deepCasesPrice);//考核前机构价格
                    }
                }
            }
            surveyAssignOrg.setNewCase(1);
            surveyAssignOrg.setMarkError(0);
            surveyAssignOrgMapper.insert(surveyAssignOrg);

            surveyRiskCaseInfo.setOrgAssign(1);//更改为“已分派”
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            //添加时效分派记录
            SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = new SurveyOrgPrescriptionFlow();
            surveyOrgPrescriptionFlow.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyOrgPrescriptionFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
            Date startDate = new Date();
            surveyOrgPrescriptionFlow.setStartTime(startDate);
            surveyOrgPrescriptionFlow.setOperateType(1);
            surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
            surveyOrgPrescriptionFlow.setStartTime(startDate);
            surveyOrgPrescriptionFlow.setOperateType(2);
            surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
        }

        if (oprUser != null) {
            surveyRiskCaseInfo.setBelongUserId(oprUser);
            surveyRiskCaseInfo.setBelongUserName(userInfoMapper.selectByPrimaryKey(oprUser).getUserName());
        }

        //机构主动退回(0：否，1:是)
        surveyRiskCaseInfo.setOrgReturn(0);
        surveyRiskCaseInfo.setSurveyState(12);
        surveyRiskCaseInfo.setSurveyStateName("调查中");
        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);


        //更新逆向案件状态
        if (surveyBackCase != null) {
            surveyBackCase.setAssOrgId(surveyFranchisee.getId());
            surveyBackCase.setAssOrgName(surveyFranchisee.getName());
            surveyBackCase.setBackState(5);
            surveyBackCase.setBackStateName("平台已分配");
            surveyBackCaseMapper.updateByPrimaryKey(surveyBackCase);
        }

        //保存机构对应任务类型
        String taskStr = apiRequest.getString("taskIds");
        String[] taskIds = taskStr.split(",");
        map = new HashMap<String, Long>();
        map.put("surveyAssignOrgId", surveyAssignOrg.getId());
        List<SurveyAssignOrgType> assignOrgTypes = surveyAssignOrgTypeMapper.list(map);
        for (SurveyAssignOrgType assignOrgType : assignOrgTypes) {
            surveyAssignOrgTypeMapper.deleteByPrimaryKey(assignOrgType.getId());
        }
        for (String taskId : taskIds) {
            if (StringUtils.isNotEmpty(taskId)) {
                SurveyAssignOrgType surveyAssignOrgType = new SurveyAssignOrgType();
                surveyAssignOrgType.setTaskId(Long.parseLong(taskId));
                SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyAssignOrgType.getTaskId());
                if (surveyTaskInfo != null) {
                    surveyAssignOrgType.setTaskName(surveyTaskInfo.getName());
                }
                surveyAssignOrgType.setSurveyId(surveyAssignOrg.getSurveyId());
                surveyAssignOrgType.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                surveyAssignOrgType.setSurveyAssignOrgId(surveyAssignOrg.getId());
                surveyAssignOrgType.setSurveyOrgId(surveyAssignOrg.getSurveyOrgId());
                surveyAssignOrgType.setSurveyOrgName(surveyAssignOrg.getSurveyOrgName());
                surveyAssignOrgTypeMapper.insert(surveyAssignOrgType);
            }
        }

        if (!upd) {
            backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(), surveyRiskCaseInfo.getId(), userInfo.getUserId(), userInfo.getUserName(), "已分派机构（" + surveyAssignOrg.getSurveyOrgName() + "）", "");
        }

        //发送消息通知
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=assign-org-list&assignOrgId=" + surveyAssignOrg.getId();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId", 58L);
        String parentOrgId = surveyFranchiseeMapper.selectParentOrgId(surveyAssignOrg.getSurveyOrgId());
        paramMap.put("orgId", parentOrgId);
        List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);

        String content = "案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime());
        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(), userInfo.getUserName(), toUsers, 4, "案件分派通知",
                content, url);

        Map<String, Object> msgMap = new HashMap<String, Object>();
        msgMap.put("title", "派单通知");
        msgMap.put("content", "机构有新的案件，请尽快查看并分派调查员！");
        msgMap.put("keyWords", "案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()));
        backendWechatApi.send(toUsers, msgMap);

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    private ApiResponse updAssignOrg(ApiRequest apiRequest, SurveyRiskCaseInfo surveyRiskCaseInfo, UserInfo userInfo) {
        //先删除之前的机构信息，分派的调查员信息，方向信息
        Long orgCaseId = apiRequest.getLong("orgCaseId");
        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(orgCaseId);
        surveyAssignOrg.setDeleteFlag(1);
        surveyAssignOrg.setUpdateBy(userInfo.getUserName());
        surveyAssignOrg.setUpdateTime(new Date());
        surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);

        Map paramMap = new HashMap<String, Object>();
        paramMap.put("roleId", 58L);
        paramMap.put("orgId", surveyAssignOrg.getSurveyOrgId());
        List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        Map<String, Object> msgMap = new HashMap<String, Object>();
        msgMap.put("title", "案件取消");
        msgMap.put("content", "机构有案件被取消，请注意！");
        msgMap.put("keyWords", "案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "操作人员：" + userInfo.getUserName());
        backendWechatApi.send(toUsers, msgMap);

        //删除机构  同时删除 调查员任务
        Map<String, Long> map = new HashMap<>();
        map.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
        map.put("surveyAssorgCaseId", surveyAssignOrg.getId());
        List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(map);

        for (SurveyInvestigatorCase aCase : cases) {
            aCase.setDeleteFlag(1);
            surveyInvestigatorCaseMapper.updateByPrimaryKey(aCase);

            msgMap = new HashMap<String, Object>();
            msgMap.put("title", "案件取消");
            msgMap.put("content", "你有任务被取消，请注意！");
            msgMap.put("keyWords", "案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "操作人员：" + userInfo.getUserName());
            backendWechatApi.send(aCase.getSurveyUserId(), msgMap);
        }

        // 同时删除方向
        map = new HashMap<>();
        map.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
        map.put("surveyOrgId", surveyAssignOrg.getSurveyOrgId());
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

        Long assignUserId = apiRequest.getLong("assignUserId");
        try {
            if (assignUserId == null) {
                assignOrg(apiRequest, surveyRiskCaseInfo, userInfo, true);
            } else {
                assignUser(apiRequest, surveyRiskCaseInfo, userInfo, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //发送消息通知
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=assign-org-list&assignOrgId=" + surveyAssignOrg.getId();
        paramMap = new HashMap<String, Object>();
        paramMap.put("roleId", 58L);
        String parentOrgId = surveyFranchiseeMapper.selectParentOrgId(surveyAssignOrg.getSurveyOrgId());
        paramMap.put("orgId", parentOrgId);
        toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
        String content = "案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime());
        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(), userInfo.getUserName(), toUsers, 4, "案件分派通知",
                content, url);

        msgMap = new HashMap<String, Object>();
        msgMap.put("title", "派单通知");
        msgMap.put("content", "机构有新的案件，请尽快查看并分派调查员！");
        msgMap.put("keyWords", "案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "案件截止日期：" + simpleDateFormat.format(surveyAssignOrg.getOrgEndTime()));
        backendWechatApi.send(toUsers, msgMap);

        //新改派的 机构编号
        Long surveyOrgId = apiRequest.getLong("surveyOrgId");
        SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyOrgId);

        //增加进度 改派机构
        String progressDesc = "原机构：" + surveyAssignOrg.getSurveyOrgName() + "\n" +
                "新机构：" + surveyFranchisee.getName() + "\n" +
                "改派原因：" + apiRequest.getString("orgOpinion");
        backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(), surveyAssignOrg.getSurveyInfoId(), userInfo.getUserId(), userInfo.getUserName(), "改派机构", progressDesc);


        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 分派调查员
     *
     * @param apiRequest
     * @param surveyRiskCaseInfo
     * @param userInfo
     * @return
     * @throws Exception
     */
    private ApiResponse assignUser(ApiRequest apiRequest, SurveyRiskCaseInfo surveyRiskCaseInfo, UserInfo userInfo, Boolean upd) throws Exception {
        Long assignUserId = apiRequest.getLong("assignUserId");
        Long backCaseId = apiRequest.getLong("backCaseId");
        String btnCode = apiRequest.getString("btnCode");
        Date surveyEndTime = DateUtils.parseDate(apiRequest.getString("endTime"), "yyyy-MM-dd HH:mm:ss");
        if (surveyEndTime.getTime() < new Date().getTime()) {
            //return new ApiResponse(ApiMsgEnum.SURVEY_END_TIME);
        }

        Long oprUser = apiRequest.getLong("oprUser");//终审人员
        if (oprUser != null) {
            surveyRiskCaseInfo.setBelongUserId(oprUser);
            surveyRiskCaseInfo.setBelongUserName(userInfoMapper.selectByPrimaryKey(oprUser).getUserName());
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
        }

        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(assignUserId);
        SurveyBackCase surveyBackCase = surveyBackCaseMapper.selectByPrimaryKey(backCaseId);
        String surveyTaskRemark = apiRequest.getString("taskRemark");
        //根据调查人id，查询调查员认证表信息，获取调查员的类型 自营or加盟?
        Integer userOrgType = surveyInvestigator.getType() == null ? 2 : surveyInvestigator.getType();
        if (!upd) {
            backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(), surveyRiskCaseInfo.getId(), userInfo.getUserId(), userInfo.getUserName(), "已分派调查员（" + surveyInvestigator.getRealName() + "），调查中", "");
        }
//        setProgress(surveyRiskCaseInfo,userInfo,"assignation",null);

        //分派时计算调查方的价格
        Integer payType = apiRequest.getInt("payType");
        if (payType == null) {
            payType = surveyRiskCaseInfo.getPayType();
            //机构分派调查员时，payType需取自 已经分派的省级或者平级
            String orgId = surveyFranchiseeMapper.selectParentOrgId(surveyInvestigator.getOrgId());
            Map<String, Object> map = new HashMap<>();
            map.put("surveyParentOrgId", Long.valueOf(orgId));
            map.put("surveyInfoId", surveyRiskCaseInfo.getId());
            map.put("search", 40);
            List<SurveyAssignOrgDto> orgs = surveyAssignOrgMapper.list(map);
            if (orgs.size() > 0) {
                payType = orgs.get(0).getPayType();
            }
        }
        Double surveyMoney = apiRequest.getDouble("surveyMoney");
        String surveryReLoossesRemark = apiRequest.getString("surveryReLoossesRemark");

        //分派子机构，父级 2020年2月29日12:11:52
        Long servicesId = apiRequest.getLong("servicesId"); // 业务类型
        String servicesName = apiRequest.getString("servicesName");
        SurveyServiceType surveyServiceType = surveyServiceTypeMapper.selectByPrimaryKey(servicesId);

        if (surveyServiceType != null) {
            servicesName = surveyServiceType.getName();
        }
        if (servicesId == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("surveyInfoId", surveyRiskCaseInfo.getId());
            List<SurveyAssignOrgDto> assignOrgList = surveyAssignOrgMapper.list(map);
            if (assignOrgList.size() > 0) {
                for (int i = 0; i < assignOrgList.size(); i++) {
                    if (assignOrgList.get(i).getServicesId() != null) {
                        servicesId = assignOrgList.get(i).getServicesId();
                        servicesName = assignOrgList.get(i).getServicesName();
                        break;
                    }
                }
            }
        }

        String taskStr = apiRequest.getString("taskIds");
        String[] taskIds = taskStr.split(",");
        //根据字表id以及调查员id查询是否已经分配过。 如果分配过则更改案件任务表
        Map<String, Object> map = new HashMap<>();
        map.put("surveyInfoId", surveyRiskCaseInfo.getId());
        map.put("surveyUserId", assignUserId);
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.getSurveyInvestigatorCaseByOne(map);
        if (surveyInvestigatorCase == null) {
            surveyInvestigatorCase = SurveyInvestigatorCase.class.newInstance();
            surveyInvestigatorCase.setSurveyId(surveyRiskCaseInfo.getSurveyId());
            surveyInvestigatorCase.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyInvestigatorCase.setSurveyTaskRemark(surveyTaskRemark);

            surveyInvestigatorCase.setEntrustTaskMoney(surveyRiskCaseInfo.getEntrustMoney());
            surveyInvestigatorCase.setEntrustReLosses(surveyRiskCaseInfo.getEntrustReLosses());
            surveyInvestigatorCase.setEntrustReLossesRemark(surveyRiskCaseInfo.getEntrustReLossesRemark());
            surveyInvestigatorCase.setSurveyUserId(surveyInvestigator.getUserId());
            surveyInvestigatorCase.setSurveyUserName(surveyInvestigator.getRealName());
            surveyInvestigatorCase.setSurveyUserType(2);
            surveyInvestigatorCase.setAssignDate(new Date());

            //2019年9月27日10点43分  改为自动接收
            surveyInvestigatorCase.setAcceptDate(new Date());
            surveyInvestigatorCase.setSurveyState(1);
            surveyInvestigatorCase.setSurveyStateName("调查中");

            surveyInvestigatorCase.setSurveyInfo(surveyRiskCaseInfo.getSurveyInfo());
            surveyInvestigatorCase.setSurveyItem(surveyRiskCaseInfo.getSurveyItem());
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
            surveyInvestigatorCase.setEntrustOrgId(surveyRiskCase.getEntrustOrgId());
            surveyInvestigatorCase.setEntrustOrgName(surveyRiskCase.getEntrustOrgName());
            surveyInvestigatorCase.setCreportId(null);
            surveyInvestigatorCase.setCreportName(null);
            surveyInvestigatorCase.setCreportDate(null);
            surveyInvestigatorCase.setCreportState(0);
            surveyInvestigatorCase.setIsSun(0);
            surveyInvestigatorCase.setSurveryUserOrgType(userOrgType);
            surveyInvestigatorCase.setScoreState(0);
            surveyInvestigatorCase.setUserCashState(0);
            surveyInvestigatorCase.setDeleteFlag(0);
            surveyInvestigatorCase.setSurveyEndTime(surveyEndTime);
            surveyInvestigatorCase.setSurveyOrgId(surveyInvestigator.getOrgId());
            surveyInvestigatorCase.setSurveyOrgName(surveyInvestigator.getOrgName());
            String surveyNo = surveyRiskCase.getSurveyNo() == null ? "" : surveyRiskCase.getSurveyNo();
            String surveyPerson = surveyRiskCase.getSurveyPerson() == null ? "" : surveyRiskCase.getSurveyPerson();
            String survery_person_tel = surveyRiskCase.getSurveryPersonTel() == null ? "" : surveyRiskCase.getSurveryPersonTel();
            String policyNo = surveyRiskCase.getPolicyNo() == null ? "" : surveyRiskCase.getPolicyNo();
            String claimsNo = surveyRiskCase.getClaimsNo() == null ? "" : surveyRiskCase.getClaimsNo();
            String entrustUserName = surveyRiskCase.getEntrustUserName() == null ? "" : surveyRiskCase.getEntrustUserName();
            String entrustOrgName = surveyRiskCase.getEntrustOrgName() == null ? "" : surveyRiskCase.getEntrustOrgName();
            String surveyCno = surveyRiskCaseInfo.getSurveyCno() == null ? "" : surveyRiskCaseInfo.getSurveyCno();
            String surveyBusName = surveyRiskCaseInfo.getSurveyBusName() == null ? "" : surveyRiskCaseInfo.getSurveyBusName();
            String surveyUserName = surveyRiskCaseInfo.getSurveyUserName() == null ? "" : surveyRiskCaseInfo.getSurveyUserName();
            String searchCondition = String.format("{surveyNo:%s}{surveyPerson:%s}{survery_person_tel:%s}{policyNo:%s}{claimsNo:%s}{entrustUserName:%s}{entrustOrgName:%s}{surveyCno:%s}" +
                    "{surveyBusName:%s}{surveyUserName:%s}", surveyNo, surveyPerson, survery_person_tel, policyNo, claimsNo, entrustUserName, entrustOrgName, surveyCno, surveyBusName, surveyUserName);
            surveyInvestigatorCase.setSearchCondition(searchCondition);
            surveyInvestigatorCase.setIsDirectionSuccess(0);
            surveyInvestigatorCase.setSunState(0);
            surveyInvestigatorCase.setSurveyPay(0);
            surveyInvestigatorCase.setReviewOff(0);
            surveyInvestigatorCase.setOverdueAgingRate(1D);
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCase.getEntrustOrgId());
            int days = GetWorkDay.calLeaveDays(new Date(), surveyEndTime, surveyConsignor.getEfficiencyAttr());
            surveyInvestigatorCase.setAgingOver(0D);
            surveyInvestigatorCase.setAgingReal(0D);
            surveyInvestigatorCase.setAgingCheck((double) days);
            surveyInvestigatorCase.setNewCase(1);
            surveyInvestigatorCaseMapper.insert(surveyInvestigatorCase);
            //保存扩展表
            SurveyInvestigatorCaseSub surveyInvestigatorCaseSub = new SurveyInvestigatorCaseSub();
            surveyInvestigatorCaseSub.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyInvestigatorCaseSubMapper.insert(surveyInvestigatorCaseSub);
            //增加调查员案件任务表
            for (String taskId : taskIds) {
                if (StringUtils.isNotEmpty(taskId)) {
                    SurveyInvestigatorCaseType surveyInvestigatorCaseType = new SurveyInvestigatorCaseType();
                    surveyInvestigatorCaseType.setTaskId(Long.parseLong(taskId));
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyInvestigatorCaseType.getTaskId());
                    if (surveyTaskInfo != null) {
                        surveyInvestigatorCaseType.setTaskName(surveyTaskInfo.getName());
                    }
                    surveyInvestigatorCaseType.setSurveyUserId(surveyInvestigator.getUserId());
                    surveyInvestigatorCaseType.setSurveyUserName(surveyInvestigator.getRealName());
                    surveyInvestigatorCaseType.setSurveyUserCaseId(surveyInvestigatorCase.getId());
                    surveyInvestigatorCaseTypeMapper.insert(surveyInvestigatorCaseType);
                }
            }
        } else {
            surveyInvestigatorCase.setSurveyId(surveyRiskCaseInfo.getSurveyId());
            surveyInvestigatorCase.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyInvestigatorCase.setSurveyTaskRemark(surveyTaskRemark);

            surveyInvestigatorCase.setSurveryReLosses(0D);
            surveyInvestigatorCase.setEntrustTaskMoney(surveyRiskCaseInfo.getEntrustMoney());
            surveyInvestigatorCase.setEntrustReLosses(surveyRiskCaseInfo.getEntrustReLosses());
            surveyInvestigatorCase.setEntrustReLossesRemark(surveyRiskCaseInfo.getEntrustReLossesRemark());
            surveyInvestigatorCase.setSurveyUserId(surveyInvestigator.getUserId());
            surveyInvestigatorCase.setSurveyUserName(surveyInvestigator.getRealName());
            surveyInvestigatorCase.setAssignDate(new Date());
            //2019年9月27日10点43分  改为自动接收
            surveyInvestigatorCase.setAcceptDate(new Date());
            surveyInvestigatorCase.setSurveyState(1);
            surveyInvestigatorCase.setSurveyStateName("调查中");
            surveyInvestigatorCase.setSurveyInfo(surveyRiskCaseInfo.getSurveyInfo());
            surveyInvestigatorCase.setSurveyItem(surveyRiskCaseInfo.getSurveyItem());
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
            surveyInvestigatorCase.setEntrustOrgId(surveyRiskCase.getEntrustOrgId());
            surveyInvestigatorCase.setEntrustOrgName(surveyRiskCase.getEntrustOrgName());
            surveyInvestigatorCase.setCreportId(null);
            surveyInvestigatorCase.setCreportName(null);
            surveyInvestigatorCase.setCreportDate(null);
            surveyInvestigatorCase.setCreportState(0);
            surveyInvestigatorCase.setSurveryUserOrgType(userOrgType);
            surveyInvestigatorCase.setSurveyEndTime(surveyEndTime);
            surveyInvestigatorCase.setIsDirectionSuccess(0);
            surveyInvestigatorCase.setSunState(0);
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCase.getEntrustOrgId());
            int days = GetWorkDay.calLeaveDays(new Date(), surveyEndTime, surveyConsignor.getEfficiencyAttr());
            surveyInvestigatorCase.setAgingOver(0D);
            surveyInvestigatorCase.setAgingReal(0D);
            surveyInvestigatorCase.setAgingCheck((double) days);

            //如果之前分派过调查员  就把之前调查员的时效记录清除掉 重新添加两条新的
            surveyUserPrescriptionFlowMapper.deleteBySurveyInvCaseId(surveyInvestigatorCase.getId());

            //添加时效分派记录
            SurveyUserPrescriptionFlow surveyUserPrescriptionFlow = new SurveyUserPrescriptionFlow();
            surveyUserPrescriptionFlow.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
            surveyUserPrescriptionFlow.setSurveyAssignOrgId(surveyInvestigatorCase.getSurveyAssorgCaseId());
            surveyUserPrescriptionFlow.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            Date startTime = new Date();
            surveyUserPrescriptionFlow.setStartTime(startTime);
            surveyUserPrescriptionFlow.setOperateType(1);
            surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);
            surveyUserPrescriptionFlow.setStartTime(startTime);
            surveyUserPrescriptionFlow.setOperateType(2);
            surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);

//            Map findMap=new HashMap();
//            findMap.put("surveyInvestigatorCaseId",surveyInvestigatorCase.getId());
//            List<SurveyUserPrescriptionFlow> surveyUserPrescriptionFlows = surveyUserPrescriptionFlowMapper.selectPrescription(findMap);
//            if(surveyUserPrescriptionFlows.size()>0){
//                for (SurveyUserPrescriptionFlow userPrescriptionFlow : surveyUserPrescriptionFlows) {
//                    userPrescriptionFlow.setStartTime(new Date());
//                    if(userPrescriptionFlow.getEndTime() != null && userPrescriptionFlow.getOperateType()==2){
//                        int efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
//                        days = GetWorkDay.calLeaveDays(userPrescriptionFlow.getStartTime(), userPrescriptionFlow.getEndTime(),efficiencyAttr);
//                        days = Math.abs(days);
//                        userPrescriptionFlow.setDays(Double.parseDouble(days+""));
//                    }
//                    surveyUserPrescriptionFlowMapper.updateByPrimaryKey(userPrescriptionFlow);
//                }
//            }
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
            //删除原有已分配的  暂时先不删 （2019-10-28放开：需求：一名调查员分配任务类型，只有一条任务详情数据）
            List<SurveyInvestigatorCaseType> caseTypes = surveyInvestigatorCaseTypeMapper.getSurveyInvestigatorCaseTypesByCaseId(surveyInvestigatorCase.getId());
            for (SurveyInvestigatorCaseType caseType : caseTypes) {
                surveyInvestigatorCaseTypeMapper.deleteByPrimaryKey(caseType.getId());
            }
            for (String taskId : taskIds) {
                if (StringUtils.isNotEmpty(taskId)) {
                    //根据调查员案件id（surveyInvestigatorCase.getId()）以及任务id(taskId) 以及当前分配人(assignUserId) 判断是否选择过，如果没有选择过则新增
//                    map =  new HashMap<>();
//                    map.put("surveyUserCaseId",surveyInvestigatorCase.getId());
//                    map.put("taskId",taskId);
//                    map.put("surveyUserId", surveyInvestigator.getUserId());
//                    //新增勾选的
//                    SurveyInvestigatorCaseType surveyInvestigatorCaseType = surveyInvestigatorCaseTypeMapper.getSurveyInvestigatorCaseTypeByOne(map);
//                    if (surveyInvestigatorCaseType == null){
//                        surveyInvestigatorCaseType = new SurveyInvestigatorCaseType();
//                        surveyInvestigatorCaseType.setTaskId(Long.parseLong(taskId));
//                        SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyInvestigatorCaseType.getTaskId());
//                        surveyInvestigatorCaseType.setTaskName(surveyTaskInfo.getName());
//                        surveyInvestigatorCaseType.setSurveyUserId(surveyInvestigator.getUserId());
//                        surveyInvestigatorCaseType.setSurveyUserName(surveyInvestigator.getRealName());
//                        surveyInvestigatorCaseType.setSurveyUserCaseId(surveyInvestigatorCase.getId());
//                        surveyInvestigatorCaseTypeMapper.insert(surveyInvestigatorCaseType);
//                    }
                    //2019-10-28需求：一名调查员分配任务类型，只有一条任务详情数据
                    SurveyInvestigatorCaseType surveyInvestigatorCaseType = new SurveyInvestigatorCaseType();
                    surveyInvestigatorCaseType.setTaskId(Long.parseLong(taskId));
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyInvestigatorCaseType.getTaskId());
                    if (surveyTaskInfo != null) {
                        surveyInvestigatorCaseType.setTaskName(surveyTaskInfo.getName());
                    }
                    surveyInvestigatorCaseType.setSurveyUserId(surveyInvestigator.getUserId());
                    surveyInvestigatorCaseType.setSurveyUserName(surveyInvestigator.getRealName());
                    surveyInvestigatorCaseType.setSurveyUserCaseId(surveyInvestigatorCase.getId());
                    surveyInvestigatorCaseTypeMapper.insert(surveyInvestigatorCaseType);
                }
            }
        }
        surveyRiskCaseInfo.setOrgAssign(1);//直接分派调查员 机构分派状态为已分派
        surveyRiskCaseInfo.setAssignState(2);
        surveyRiskCaseInfo.setAcceptState(2);

        surveyRiskCaseInfo.setSurveyState(12);
        surveyRiskCaseInfo.setSurveyStateName("调查中");
        surveyRiskCaseInfo.setOrgReturn(0); //机构主动退回(0：否，1:是)

        //需求：2020年2月27日 机构分派调查员。如果当前选中的那个机构案件的调查机构ID 不等于 调查员机构ID 则 update 机构案件的调查机构ID为调查员机构ID。
        if ("113".equals(btnCode)) {
            Long surveyOrgId = surveyInvestigator.getOrgId();
            Map<String, Long> map1 = new HashMap<String, Long>();
            map1.put("surveyOrgId", surveyOrgId);
            map1.put("surveyInfoId", surveyRiskCaseInfo.getId());
            //当前选中的 调查员所在机构，是否有 机构案件
            SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map1);
            if (surveyAssignOrg == null) {
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByParentId(surveyFranchiseeMapper.selectByPrimaryKey(surveyOrgId).getParentId());
                if (surveyFranchisee != null && surveyFranchisee.getLevel() == 1) {
                    //当前选中的 调查员所在机构的父级机构，是否有 机构案件
                    map1 = new HashMap<String, Long>();
                    map1.put("surveyOrgId", surveyFranchisee.getId());
                    map1.put("surveyInfoId", surveyRiskCaseInfo.getId());
                    surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map1);

                    //当前选中的 调查员所在机构的父级机构，是否有 调查员案件
                    apiRequest = new ApiRequest();
                    apiRequest.put("surveyOrgId", surveyFranchisee.getId());
                    apiRequest.put("surveyInfoId", surveyRiskCaseInfo.getId());
                    List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseList = surveyInvestigatorCaseMapper.list(apiRequest);
                    if (surveyAssignOrg != null && surveyInvestigatorCaseList.size() == 0) {
                        surveyAssignOrg.setSurveyOrgId(surveyInvestigator.getOrgId());
                        surveyAssignOrg.setSurveyOrgName(surveyInvestigator.getOrgName());
                        surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                    }
                }
            }
        }

        //保存分配机构表信息  2019年2月19日10点20分 增加
        Map<String, Long> map1 = new HashMap<String, Long>();
        map1.put("surveyOrgId", surveyInvestigator.getOrgId());
        map1.put("surveyInfoId", surveyRiskCaseInfo.getId());
        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map1);//分配机构表无数据 子案件ID and 机构ID
        if (surveyAssignOrg == null) {//乐凡直接分配调查员
            surveyAssignOrg = new SurveyAssignOrg();
            surveyAssignOrg.setSurveyOrgId(surveyInvestigator.getOrgId());
            surveyAssignOrg.setSurveyOrgName(surveyInvestigator.getOrgName());
            surveyAssignOrg.setSurveyId(surveyRiskCaseInfo.getSurveyId());
            surveyAssignOrg.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyAssignOrg.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyAssignOrg.setOrgTaskRemark(surveyRiskCaseInfo.getSurveyItem());
            surveyAssignOrg.setCreateBy(userInfo.getUserName());
            surveyAssignOrg.setCreateTime(new Date());
            surveyAssignOrg.setDeleteFlag(0);
            surveyAssignOrg.setOrgSurveyState(1);
            surveyAssignOrg.setOrgSurveyStateName("调查中");
            surveyAssignOrg.setReportState(0);
            surveyAssignOrg.setSurveyPay(0);
            int havaPrimary = surveyAssignOrgMapper.selectIsHavePrimaryOrg(surveyRiskCaseInfo.getId());
            if (havaPrimary == 0) {
                surveyAssignOrg.setOrgPrimaryType(1);//设置为主调查机构
            } else {
                surveyAssignOrg.setOrgPrimaryType(2);
            }

            surveyAssignOrg.setServicesId(servicesId); // 业务类型
            surveyAssignOrg.setServicesName(servicesName);
            surveyAssignOrg.setPayType(payType); //结算方式
            surveyAssignOrg.setSurveyMoney(surveyMoney); //调查费(分派)
            surveyAssignOrg.setSurveyMoneySubmit(surveyMoney);//'确认调查费（风控审核，默认为分派的价格）
            surveyAssignOrg.setAssessOrgMoney(surveyMoney);//考核前机构价格
            surveyAssignOrg.setSurveryReLoossesRemark(surveryReLoossesRemark); // 减损描述（分派）
            surveyAssignOrg.setReturnState(0);
            surveyAssignOrg.setSurveyReturn(0);
            surveyAssignOrg.setReviewOff(0);

            //互助案件 需要把终审人员信息保存到机构案件表
            Long entrustOrgId = surveyRiskCaseInfo.getEntrustOrgId();
            SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(entrustOrgId);
            if (surveyConsignorModel != null && surveyConsignorModel.getModelId() == 5) {
                if (oprUser == null) {
                    //获取子级或者父级机构，保存的终审人员
                    map1 = new HashMap<String, Long>();
                    map1.put("surveyInfoId", surveyRiskCaseInfo.getId());
                    List<SurveyAssignOrgDto> surveyAssignOrgList = surveyAssignOrgMapper.list(map1);
                    for (int i = 0; i < surveyAssignOrgList.size(); i++) {
                        if (surveyAssignOrgList.get(i).getReviewUserId() != null) {
                            oprUser = surveyAssignOrgList.get(i).getReviewUserId();
                            surveyAssignOrg.setServicesId(surveyAssignOrgList.get(i).getServicesId()); // 业务类型
                            surveyAssignOrg.setServicesName(surveyAssignOrgList.get(i).getServicesName());
                            break;
                        }
                    }
                }
                surveyAssignOrg.setReviewUserId(oprUser);
                surveyAssignOrg.setReviewUserName(userInfoMapper.selectByPrimaryKey(oprUser).getUserName());

                //2。录入或更新SurveyRiskInfoFinalUser（下一个终审人员信息）
                updateSurveyRiskInfoFinalUser(entrustOrgId, surveyInvestigator.getOrgId(), oprUser);
            }
            int days = 0;
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(entrustOrgId);
            if ("113".equals(btnCode)) {
                //判断机构是不是有 级联关系（省级，片区）
                int result = surveyFranchiseeMapper.relationships(surveyInvestigator.getOrgId());
                if (result > 0) {
                    Long surveyParentOrgId = surveyInvestigator.getOrgId();
                    SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigator.getOrgId());
                    if (surveyFranchisee != null && surveyFranchisee.getLevel() == 2) {
                        surveyParentOrgId = surveyFranchisee.getParentId();
                    }
                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("surveyParentOrgId", surveyParentOrgId);
                    map2.put("search", 40);
                    map2.put("surveyInfoId", surveyRiskCaseInfo.getId());
                    List<SurveyAssignOrgDto> assignOrgs = surveyAssignOrgMapper.list(map2);
                    surveyAssignOrg.setOrgEndTime(assignOrgs.get(0).getOrgEndTime());
                    days = GetWorkDay.calLeaveDays(new Date(), surveyAssignOrg.getOrgEndTime(), surveyConsignor.getEfficiencyAttr());
                }
            } else {
                surveyAssignOrg.setOrgEndTime(surveyInvestigatorCase.getSurveyEndTime());
                days = GetWorkDay.calLeaveDays(new Date(), surveyInvestigatorCase.getSurveyEndTime(), surveyConsignor.getEfficiencyAttr());
            }
            surveyAssignOrg.setOldOrgEndTime(surveyAssignOrg.getOrgEndTime());
            surveyAssignOrg.setOverdueAgingRate(1D);

            surveyAssignOrg.setReviewTime(null);
            surveyAssignOrg.setAgingOver(0d);
            surveyAssignOrg.setAgingReal(0d);
            surveyAssignOrg.setAgingCheck((double) days);
            if (surveyConsignor.getOrgAttr() == 1) { // 判断是否是保司案子
                if (surveyAssignOrg.getServicesId() == 13) {
                    if (surveyMoney == null) {
                        Double deepCasesPrice = apiRequest.getDouble("deepCasesPrice") == null ? surveyMoney : apiRequest.getDouble("deepCasesPrice");
                        surveyAssignOrg.setSurveyMoney(deepCasesPrice); //调查费(分派)
                        surveyAssignOrg.setSurveyMoneySubmit(deepCasesPrice);//'确认调查费（风控审核，默认为分派的价格）
                        surveyAssignOrg.setAssessOrgMoney(deepCasesPrice);//考核前机构价格
                    }
                }
            }
            surveyAssignOrg.setNewCase(1);
            surveyAssignOrg.setMarkError(0);
            surveyAssignOrgMapper.insert(surveyAssignOrg);

            if ("112".equals(btnCode)) {
                //添加时效分派记录
                SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = new SurveyOrgPrescriptionFlow();
                surveyOrgPrescriptionFlow.setSurveyInfoId(surveyRiskCaseInfo.getId());
                surveyOrgPrescriptionFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
                Date startDate = new Date();
                surveyOrgPrescriptionFlow.setStartTime(startDate);
                surveyOrgPrescriptionFlow.setOperateType(1);
                surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
                surveyOrgPrescriptionFlow.setStartTime(startDate);
                surveyOrgPrescriptionFlow.setOperateType(2);
                surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
            }
        } else {//乐凡先分配机构 再分配的的调查员  主调查员的时候同步 案件ID
            surveyAssignOrg.setOrgSurveyState(1);
            surveyAssignOrg.setOrgSurveyStateName("调查中");
            surveyAssignOrg.setOrgOpinion(null);
            surveyAssignOrg.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyAssignOrg.setReviewTime(null);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
        }
        //112 分派机构的时候直接分派调查员  112 分派机构后 单独分派调查员
        if ("112".equals(btnCode) || "113".equals(btnCode)) {
            SurveyUserPrescriptionFlow userFlow = surveyUserPrescriptionFlowMapper.selectByInvCaseIdLimitOne(surveyInvestigatorCase.getId());
            if (userFlow == null) {
                //添加时效分派记录
                SurveyUserPrescriptionFlow surveyUserPrescriptionFlow = new SurveyUserPrescriptionFlow();
                surveyUserPrescriptionFlow.setSurveyInfoId(surveyRiskCaseInfo.getId());
                Optional.ofNullable(surveyAssignOrg).ifPresent(e -> surveyUserPrescriptionFlow.setSurveyAssignOrgId(e.getId()));
                surveyUserPrescriptionFlow.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
                Date startTime = new Date();
                surveyUserPrescriptionFlow.setStartTime(startTime);
                surveyUserPrescriptionFlow.setOperateType(1);
                surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);
                surveyUserPrescriptionFlow.setStartTime(startTime);
                surveyUserPrescriptionFlow.setOperateType(2);
                surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);
            }
        }

        if ("113".equals(btnCode) && surveyBackCase != null) {
            surveyBackCase.setAssOrgId(surveyInvestigator.getOrgId());
            surveyBackCase.setAssOrgName(surveyInvestigator.getOrgName());
            surveyBackCase.setAssSurveyUserId(surveyInvestigator.getUserId());
            surveyBackCase.setAssSurveyUserName(surveyInvestigator.getNickName());
            surveyBackCase.setBackState(2);
            surveyBackCase.setBackStateName("机构已分派");
            surveyBackCaseMapper.updateByPrimaryKey(surveyBackCase);
        }

        //更新
        surveyInvestigatorCase.setSurveyAssorgCaseId(surveyAssignOrg.getId());
        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

        String addressJSON = apiRequest.getString("addressJSON");
        String addressDetailJSON = apiRequest.getString("addressDetailJSON");
        if (!StringUtils.isEmpty(addressJSON)) {
            String[] address = addressJSON.split(",");
            List<SurveyAddressDTO> detail = null;
            if (!StringUtils.isEmpty(addressDetailJSON)) {
                detail = JSONArray.parseArray(addressDetailJSON, SurveyAddressDTO.class);
            }
            for (String s : address) {
                SurveyAddressDTO temp = null;
                if (detail != null) {
                    List<SurveyAddressDTO> collect = detail.stream().filter(p -> p.getAddress() != null && p.getAddress().equals(s)).collect(Collectors.toList());
                    if (collect.size() > 0) {
                        temp = collect.get(0);
                    }
                }
                SurveyCaseDirection direction = SurveyCaseDirection.class.newInstance();
                direction.setSurveyId(surveyRiskCaseInfo.getSurveyId());
                direction.setSurveyInfoId(surveyRiskCaseInfo.getId());
                direction.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
                direction.setSurveyAssorgCaseId(surveyAssignOrg.getId());
                direction.setSurveyMoney(0d);
                direction.setEntrustMoney(0d);
                direction.setDeleteFlag(0);
                direction.setSurveyOrgId(surveyAssignOrg.getSurveyOrgId());
                direction.setSurveyOrgName(surveyAssignOrg.getSurveyOrgName());
                direction.setEntrustPriceSource(0);
                direction.setSurveyPriceSource(0);
                direction.setMedicalNumber(0);
                direction.setHaveReimbursement(0);
                direction.setChannelFeeCur(0d);
                direction.setChannelFeeSent(0d);
                direction.setInvalidState(0);
                direction.setChannelType(0);
                direction.setOrgPoint(1);
                direction.setDirectionName(s);
                direction.setCreateBy(userInfo.getUserName());
                direction.setCreateTime(new Date());
                direction.setUpdateBy(userInfo.getUserName());
                direction.setUpdateTime(new Date());
                if (temp != null) {//地址信息
                    direction.setProvince(temp.getProvince());
                    direction.setProvinceId(temp.getProvinceId());
                    direction.setAreaName(temp.getAreaName());
                    direction.setCity(temp.getCity());
                    direction.setCityId(temp.getCityId());
                    direction.setDistrict(temp.getDistrict());
                    direction.setDistrictId(temp.getDistrictId());
                    direction.setAreaType(temp.getAreaType());
                    direction.setRegionType(temp.getRegionType());
                }
                direction.setEvaluate(1);
                surveyCaseDirectionMapper.insert(direction);
            }
        }

        //保存机构对应任务类型
        map1 = new HashMap<String, Long>();
        map.put("surveyAssignOrgId", surveyAssignOrg.getId());
        List<SurveyAssignOrgType> assignOrgTypes = surveyAssignOrgTypeMapper.list(map);

        if (assignOrgTypes == null || assignOrgTypes.size() == 0) {
//            for (SurveyAssignOrgType assignOrgType : assignOrgTypes) {
//                surveyAssignOrgTypeMapper.deleteByPrimaryKey(assignOrgType.getId());
//            }
            for (String taskId : taskIds) {
                if (StringUtils.isNotEmpty(taskId)) {
                    SurveyAssignOrgType surveyAssignOrgType = new SurveyAssignOrgType();
                    surveyAssignOrgType.setTaskId(Long.parseLong(taskId));
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyAssignOrgType.getTaskId());
                    if (surveyTaskInfo != null) {
                        surveyAssignOrgType.setTaskName(surveyTaskInfo.getName());
                    }
                    surveyAssignOrgType.setSurveyId(surveyAssignOrg.getSurveyId());
                    surveyAssignOrgType.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                    surveyAssignOrgType.setSurveyAssignOrgId(surveyAssignOrg.getId());
                    surveyAssignOrgType.setSurveyOrgId(surveyAssignOrg.getSurveyOrgId());
                    surveyAssignOrgType.setSurveyOrgName(surveyAssignOrg.getSurveyOrgName());
                    surveyAssignOrgTypeMapper.insert(surveyAssignOrgType);
                }
            }
        }

        //发送短信
        Long days = (Long) (surveyEndTime.getTime() - new Date().getTime()) / (1000 * 3600 * 24);
        days += 1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String dateStr = simpleDateFormat.format(new Date());
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
//        SendMessageUntil.assignmentSurveyCase(surveyInvestigator.getTel(),surveyInvestigator.getRealName(),dateStr,surveyRiskCaseInfo.getSurveyCno(),surveyRiskCase.getSurveyPerson(), days.toString());
        //发送消息通知
        String content = "案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyEndTime);
        String url = "/survey/case/sic/info?id=" + surveyInvestigatorCase.getId() + "&menuCode=dcy-list";
        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(), userInfo.getUserName(), surveyInvestigator.getUserId(), surveyInvestigator.getRealName(), 4, "派单通知", content, url);

        Map<String, Object> msgMap = new HashMap<String, Object>();
        msgMap.put("title", "派单通知");
        msgMap.put("content", "你有新的任务，请尽快进行调查！");
        msgMap.put("keyWords", "案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "调查截止日期：" + simpleDateFormat.format(surveyInvestigatorCase.getSurveyEndTime()));
        backendWechatApi.send(surveyInvestigatorCase.getSurveyUserId(), msgMap);


        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 改派调查员
     *
     * @param apiRequest
     * @return
     */
    private ApiResponse updAssign(ApiRequest apiRequest) {
        Long surveyCaseId = apiRequest.getLong("surveyCaseId");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseId);
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyInfoId());
        String oldSurveyUserName = surveyInvestigatorCase.getSurveyUserName();
        Long assignUserId = apiRequest.getLong("assignUserId");
        Date surveyEndTime = DateUtils.parseDate(apiRequest.getString("endTime"), "yyyy-MM-dd HH:mm:ss");
        if (surveyEndTime.getTime() < new Date().getTime()) {
            //return new ApiResponse(ApiMsgEnum.SURVEY_END_TIME);
        }
        String surveyTaskRemark = apiRequest.getString("taskRemark");
        //根据调查人id，查询调查员认证表信息，获取调查员的类型 自营or加盟?
        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(assignUserId);
        Long surveyOrgId = surveyInvestigatorCase.getSurveyOrgId();

        //改派前的机构数据 机构id：surveyOrgId，判断该机构案件，是否还有调查员案件，如果没有则删除机构数据 2020年2月28日23:07:23
        Map<String, Long> map2 = new HashMap<String, Long>();
        map2.put("surveyOrgId", surveyOrgId);
        map2.put("surveyInfoId", surveyRiskCaseInfo.getId());
        SurveyAssignOrg oldAssignOrg = surveyAssignOrgMapper.selectByOne(map2);//分配机构表无数据 子案件ID and 机构ID

        //需求：省机构，子机构关联，所以可以支持跨机构改派  2020年2月28日23:08:15
//        if (surveyOrgId.intValue() != surveyInvestigator.getOrgId().intValue()){
//            return new ApiResponse(ApiMsgEnum.SURVEY_UPD_ASSIGN_ORG_2);//暂不支持跨机构改派
//        }

        Long oprUser = apiRequest.getLong("oprUser");//终审人员
        if (oprUser != null) {
            surveyRiskCaseInfo.setBelongUserId(oprUser);
            surveyRiskCaseInfo.setBelongUserName(userInfoMapper.selectByPrimaryKey(oprUser).getUserName());
        }
        surveyRiskCaseInfo.setSurveyState(12);
        surveyRiskCaseInfo.setSurveyStateName("调查中");
        surveyRiskCaseInfo.setOrgReturn(0);//机构主动退回(0：否，1:是)
        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

        Integer userOrgType = surveyInvestigator.getType() == null ? 2 : surveyInvestigator.getType();
//        Boolean isPrimaryUser = true;//是否是主调查员  true是  false否

        ApiRequest old = new ApiRequest();
        old.clear();
        old.put("surveyInfoId", surveyInvestigatorCase.getSurveyInfoId());
        old.put("org", surveyInvestigator.getOrgId());
        List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(old);
        /*2020年2月28日22:55:23 新需求：省机构，子机构之间可以相互改派调查员
        Boolean isHavePrimary = false;//没有主调查员
        for (SurveyInvestigatorCase aCase : cases) {
            if (aCase.getSurveyUserType() == 1){
                isHavePrimary = true;//有主调查员
            }
        }
        if (!isHavePrimary){
            surveyInvestigatorCase.setSurveyUserType(1);
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
            Map map =  new HashMap<String,Long>();
            map.put("surveyOrgId",surveyInvestigator.getOrgId());
            map.put("surveyInfoId",surveyInvestigatorCase.getSurveyInfoId());
            SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
            surveyAssignOrg.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
        }*/

        //查询根据子案件查询调查员案件表    有数据 且 当前分配的调查员机构 和 调查员案件所在机构一致 则不是主调查员
//        List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
//        for (SurveyInvestigatorCase aCase : cases) {
//            Long surveyUserId = aCase.getSurveyUserId();
//            SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(surveyUserId);
//            if (investigator.getOrgId().intValue() == surveyInvestigator.getOrgId().intValue()){
//                isPrimaryUser = false;
//                if (surveyInvestigatorCase.getSurveyUserType() == 1){//如果改派的案件是主调查员案件  则改案件还是主调查员
//                    isPrimaryUser = true;
//                }
//                break;
//            }
//        }

        //分派时计算调查方的价格
        Integer payType = surveyRiskCaseInfo.getPayType();
        Double surveyMoney = apiRequest.getDouble("surveyMoney");
        String surveryReLoossesRemark = apiRequest.getString("surveryReLoossesRemark");

        String taskStr = apiRequest.getString("taskIds");
        String[] taskIds = taskStr.split(",");

        /* 2019-10-31 机构分派时，“业务类型、付费方式、价格、描述等数据”关联机构
        if (userOrgType == 1){//自营
            surveyInvestigatorCase.setSurveyTaskMoney(0D);
        }else{//加盟
            if (payType == 1){
                surveyInvestigatorCase.setSurveyTaskMoney(surveyMoney);
            }else if (payType == 2){
                surveyInvestigatorCase.setSurveyTaskMoney(surveyMoney);
                surveyInvestigatorCase.setSurveryReLoossesRemark(surveryReLoossesRemark);
                surveyInvestigatorCase.setSurveryReLosses(0D);
            }else if (payType == 3){
                surveyInvestigatorCase.setSurveyTaskMoney(0D);
            }else if (payType == 4){
                surveyInvestigatorCase.setSurveyTaskMoney(0D);
                surveyInvestigatorCase.setSurveryReLosses(0D);
                surveyInvestigatorCase.setSurveryReLoossesRemark(surveryReLoossesRemark);
            }
        }
        */

        surveyInvestigatorCase.setEntrustTaskMoney(surveyRiskCaseInfo.getEntrustMoney());
        surveyInvestigatorCase.setEntrustReLosses(surveyRiskCaseInfo.getEntrustReLosses());
        surveyInvestigatorCase.setEntrustReLossesRemark(surveyRiskCaseInfo.getEntrustReLossesRemark());
        surveyInvestigatorCase.setSurveyUserId(surveyInvestigator.getUserId());
        surveyInvestigatorCase.setSurveyUserName(surveyInvestigator.getRealName());
        surveyInvestigatorCase.setSurveyOrgId(surveyInvestigator.getOrgId());
        surveyInvestigatorCase.setSurveyOrgName(surveyInvestigator.getOrgName());
        surveyInvestigatorCase.setAssignDate(new Date());
        surveyInvestigatorCase.setSurveyTaskRemark(surveyTaskRemark);
        surveyInvestigatorCase.setDeleteFlag(0);
        //自动接收
        surveyInvestigatorCase.setAcceptDate(new Date());
        surveyInvestigatorCase.setSurveyState(1);
        surveyInvestigatorCase.setSurveyStateName("调查中");
        surveyInvestigatorCase.setSurveyEndTime(surveyEndTime);
        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
        int day = GetWorkDay.calLeaveDays(new Date(), surveyEndTime, surveyConsignor.getEfficiencyAttr());
        surveyInvestigatorCase.setAgingOver(0d);
        surveyInvestigatorCase.setAgingReal(0d);
        surveyInvestigatorCase.setAgingCheck((double) day);
        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

        //改派调查员 删除原调查员之前的时效记录 添加新的
        surveyUserPrescriptionFlowMapper.deleteBySurveyInvCaseId(surveyInvestigatorCase.getId());
        //添加时效分派记录
        SurveyUserPrescriptionFlow surveyUserPrescriptionFlow = new SurveyUserPrescriptionFlow();
        surveyUserPrescriptionFlow.setSurveyInfoId(surveyInvestigatorCase.getSurveyInfoId());
        surveyUserPrescriptionFlow.setSurveyAssignOrgId(surveyInvestigatorCase.getSurveyAssorgCaseId());
        surveyUserPrescriptionFlow.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
        Date startTime = new Date();
        surveyUserPrescriptionFlow.setStartTime(startTime);
        surveyUserPrescriptionFlow.setOperateType(1);
        surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);
        surveyUserPrescriptionFlow.setStartTime(startTime);
        surveyUserPrescriptionFlow.setOperateType(2);
        surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);


        //2019年4月3日 10点42分  改派BUG  针对于改派的案件  将调查员的id更改，未存在于表中的任务类型增加。
        List<SurveyInvestigatorCaseType> caseTypes = surveyInvestigatorCaseTypeMapper.getSurveyInvestigatorCaseTypesByCaseId(surveyInvestigatorCase.getId());
        for (SurveyInvestigatorCaseType caseType : caseTypes) {
//                    caseType.setSurveyUserId(surveyInvestigator.getUserId());
//                    caseType.setSurveyUserName(surveyInvestigator.getRealName());
//                    surveyInvestigatorCaseTypeMapper.updateByPrimaryKey(caseType);
            surveyInvestigatorCaseTypeMapper.deleteByPrimaryKey(caseType.getId());
        }

        //增加调查员案件任务表
        for (String taskId : taskIds) {
            if (StringUtils.isNotEmpty(taskId)) {

                //根据调查员案件id（surveyInvestigatorCase.getId()）以及任务id(taskId) 以及当前分配人(assignUserId) 判断是否选择过，如果没有选择过则新增
                Map map = new HashMap<>();
                map.put("surveyUserCaseId", surveyInvestigatorCase.getId());
                map.put("taskId", taskId);
                map.put("surveyUserId", surveyInvestigator.getUserId());
                SurveyInvestigatorCaseType surveyInvestigatorCaseType = surveyInvestigatorCaseTypeMapper.getSurveyInvestigatorCaseTypeByOne(map);
                if (surveyInvestigatorCaseType == null) {
                    surveyInvestigatorCaseType = new SurveyInvestigatorCaseType();
                    surveyInvestigatorCaseType.setTaskId(Long.parseLong(taskId));
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyInvestigatorCaseType.getTaskId());
                    surveyInvestigatorCaseType.setTaskName(surveyTaskInfo.getName());
                    surveyInvestigatorCaseType.setSurveyUserId(surveyInvestigator.getUserId());
                    surveyInvestigatorCaseType.setSurveyUserName(surveyInvestigator.getRealName());
                    surveyInvestigatorCaseType.setSurveyUserCaseId(surveyInvestigatorCase.getId());
                    surveyInvestigatorCaseTypeMapper.insert(surveyInvestigatorCaseType);
                }
            }
        }

        //保存分配机构表信息  2019年2月19日10点20分 增加
        Map<String, Long> map1 = new HashMap<String, Long>();
        map1.put("surveyOrgId", surveyInvestigator.getOrgId());
        map1.put("surveyInfoId", surveyRiskCaseInfo.getId());
        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map1);//分配机构表无数据 子案件ID and 机构ID
        if (surveyAssignOrg == null) {//无机构案件信息
            surveyAssignOrg = new SurveyAssignOrg();
            surveyAssignOrg.setSurveyOrgId(surveyInvestigator.getOrgId());
            surveyAssignOrg.setSurveyOrgName(surveyInvestigator.getOrgName());
            surveyAssignOrg.setSurveyId(surveyRiskCaseInfo.getSurveyId());
            surveyAssignOrg.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyAssignOrg.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyAssignOrg.setOrgEndTime(surveyInvestigatorCase.getSurveyEndTime());
            surveyAssignOrg.setOldOrgEndTime(surveyInvestigatorCase.getSurveyEndTime());
            surveyAssignOrg.setCreateBy(userInfo.getUserName());
            surveyAssignOrg.setCreateTime(new Date());
            surveyAssignOrg.setDeleteFlag(0);
            surveyAssignOrg.setOrgSurveyState(1);
            surveyAssignOrg.setOrgSurveyStateName("调查中");
            surveyAssignOrg.setOrgOpinion(null);
            surveyAssignOrg.setReportState(0);
            int havaPrimary = surveyAssignOrgMapper.selectIsHavePrimaryOrg(surveyRiskCaseInfo.getId());
            if (havaPrimary == 0) {
                surveyAssignOrg.setOrgPrimaryType(1);//设置为主调查机构
            } else {
                surveyAssignOrg.setOrgPrimaryType(2);
            }
            surveyAssignOrg.setReturnState(0);
            surveyAssignOrg.setSurveyReturn(0);
            surveyAssignOrg.setReviewOff(0);
            surveyAssignOrg.setServicesId(oldAssignOrg.getServicesId()); // 业务类型
            surveyAssignOrg.setServicesName(oldAssignOrg.getServicesName());
            surveyAssignOrg.setReviewUserName(oldAssignOrg.getReviewUserName());
            surveyAssignOrg.setReviewUserId(oldAssignOrg.getReviewUserId());
            surveyAssignOrg.setPayType(oldAssignOrg.getPayType()); //结算方式
            surveyAssignOrg.setOverdueAgingRate(1D);

            int days = GetWorkDay.calLeaveDays(new Date(), surveyInvestigatorCase.getSurveyEndTime(), surveyConsignor.getEfficiencyAttr());
            surveyAssignOrg.setReviewTime(null);
            surveyAssignOrg.setAgingOver(0d);
            surveyAssignOrg.setAgingReal(0d);
            surveyAssignOrg.setAgingCheck((double) days);
            surveyAssignOrg.setNewCase(1);
            surveyAssignOrg.setMarkError(0);
            surveyAssignOrgMapper.insert(surveyAssignOrg);

            //添加时效分派记录
            SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = new SurveyOrgPrescriptionFlow();
            surveyOrgPrescriptionFlow.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyOrgPrescriptionFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
            Date startDate = new Date();
            surveyOrgPrescriptionFlow.setStartTime(startDate);
            surveyOrgPrescriptionFlow.setOperateType(1);
            surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
            surveyOrgPrescriptionFlow.setStartTime(startDate);
            surveyOrgPrescriptionFlow.setOperateType(2);
            surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
        } else {//乐凡先分配机构 再分配的的调查员
            surveyAssignOrg.setSurveyReturn(0);//调查员主动退回(0：否，1:是)
            surveyAssignOrg.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyAssignOrg.setOrgSurveyState(1);
            surveyAssignOrg.setOrgSurveyStateName("调查中");
            surveyAssignOrg.setOrgOpinion(null);
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
        }

        //更新机构案件id
        surveyInvestigatorCase.setSurveyAssorgCaseId(surveyAssignOrg.getId());
        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

        //判断该机构案件，是否还有调查员案件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("surveyInfoId", surveyRiskCaseInfo.getId());
        paramMap.put("surveyAssorgCaseId", surveyAssignOrg.getId());
        List<SurveyInvestigatorCase> oldCases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(paramMap);
        if (oldCases.size() == 0) {
            oldAssignOrg.setDeleteFlag(1);
            surveyAssignOrgMapper.updateByPrimaryKey(oldAssignOrg);
            int havaPrimary = surveyAssignOrgMapper.selectIsHavePrimaryOrg(surveyRiskCaseInfo.getId());
            if (havaPrimary == 0) {
                surveyAssignOrgMapper.updateOrgPrimayTypeBySurveyInfoId(surveyRiskCaseInfo.getId());
            }

            //删除原有机构的任务类型
            map1 = new HashMap<String, Long>();
            map1.put("surveyAssignOrgId", oldAssignOrg.getId());
            List<SurveyAssignOrgType> assignOrgTypes = surveyAssignOrgTypeMapper.list(map1);
            for (SurveyAssignOrgType assignOrgType : assignOrgTypes) {
                surveyAssignOrgTypeMapper.deleteByPrimaryKey(assignOrgType.getId());
            }
            //保存新的
            for (String taskId : taskIds) {
                if (StringUtils.isNotEmpty(taskId)) {
                    SurveyAssignOrgType surveyAssignOrgType = new SurveyAssignOrgType();
                    surveyAssignOrgType.setTaskId(Long.parseLong(taskId));
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyAssignOrgType.getTaskId());
                    if (surveyTaskInfo != null) {
                        surveyAssignOrgType.setTaskName(surveyTaskInfo.getName());
                    }
                    surveyAssignOrgType.setSurveyId(surveyAssignOrg.getSurveyId());
                    surveyAssignOrgType.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                    surveyAssignOrgType.setSurveyAssignOrgId(surveyAssignOrg.getId());
                    surveyAssignOrgType.setSurveyOrgId(surveyAssignOrg.getSurveyOrgId());
                    surveyAssignOrgType.setSurveyOrgName(surveyAssignOrg.getSurveyOrgName());
                    surveyAssignOrgTypeMapper.insert(surveyAssignOrgType);
                }
            }
        }

        //调查员发送消息通知
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        String url = "/survey/case/sic/info?id=" + surveyInvestigatorCase.getId() + "&menuCode=dcy-list";
        String content = "案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyEndTime);

        backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(), userInfo.getUserName(), surveyInvestigator.getUserId(), surveyInvestigator.getRealName(), 4, "派单通知",
                content, url);

        Map<String, Object> msgMap = new HashMap<String, Object>();
        msgMap.put("title", "派单通知");
        msgMap.put("content", "你有新的任务，请尽快进行调查！");
        msgMap.put("keyWords", "案件编号：" + surveyRiskCase.getSurveyCaseNo() + "\n" + "被调查人：" + surveyRiskCase.getSurveyPerson() + "\n" + "调查截止日期：" + simpleDateFormat.format(surveyInvestigatorCase.getSurveyEndTime()));
        backendWechatApi.send(surveyInvestigatorCase.getSurveyUserId(), msgMap);


        //发送短信
//        Long days = (Long)(surveyEndTime.getTime() - new Date().getTime()) / (1000*3600*24);
//        days += 1;
//        String dateStr = simpleDateFormat.format(new Date());
//        try{
//            SendMessageUntil.assignmentSurveyCase(surveyInvestigator.getTel(),surveyInvestigator.getRealName(),dateStr,surveyRiskCaseInfo.getSurveyCno(),surveyRiskCase.getSurveyPerson(), days.toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //增加进度 改派机构
        String progressDesc = "原调查员：" + oldSurveyUserName + "\n" +
                "新调查员：" + surveyInvestigator.getRealName() + "\n" +
                "改派原因：" + apiRequest.getString("orgOpinion");
        backendSurveyProgressApi.saveProgress(surveyAssignOrg.getSurveyId(), surveyAssignOrg.getSurveyInfoId(), userInfo.getUserId(), userInfo.getUserName(), "改派调查员", progressDesc);

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(needLogin = false, descript = "批量开票详情", value = "info-survey-risk-case-billing-list")
    @Override
    public ApiResponse billingList(ApiRequest apiRequest) {
        try {
            SurveyRiskCaseInfoDto dto = new SurveyRiskCaseInfoDto();

            String idList = apiRequest.getString("idList");
            if (idList != null) {
                String[] ids = idList.split(",");
                /*Double money = 0D;
                String orgName = null;
                for (String id : ids) {
                    SurveyRiskCaseInfoDto info = surveyRiskCaseInfoMapper.selectByPrimaryKey(Long.parseLong(id));
//                    money += info.getEntrustMoney();
                    money += info.getBillingMoney();
                    orgName=info.getEntrustOrgName();
                }*/
                Double money = surveyRiskCaseInfoMapper.selectBillingMoney(Arrays.asList(ids));

                dto.setEntrustOrgName(apiRequest.getString("companyName"));
                dto.setEntrustMoney(money);
                String code = SerialNumberUtil.getSurveyCode("CWTPL");
                dto.setSurveyNo(code);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false, descript = "调度案件（已分配列表 可分配列表）", value = "dispatcher-survey-risk-case-info")
    @Override
    public ApiResponse dispatcherView(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("id");
        Map map = new HashMap();
        List<SurveyTaskType> surveyTaskTypes = surveyTaskTypeMapper.getSurveyTaskTypeBySurveyInfoId(surveyInfoId);
        List<SurveyTaskInfo> surveyTaskInfos = surveyTaskInfoMapper.getSurveyTaskInfosBySurveyInfoId(surveyInfoId);
        map.put("surveyTaskTypes", surveyTaskTypes);//已分配列表
        map.put("surveyTaskInfos", surveyTaskInfos);//可分配列表
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, map);
    }


    @ApiMethod(needLogin = false, descript = "子案件列表（导出）", value = "survey-case-list-to-export")
    @Override
    public ApiResponse listToExport(ApiRequest apiRequest) {
        String menuCode = apiRequest.getString("menuCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        //对账清单list页面的导出
        if ("account-list".equals(menuCode)) {
            setBackendPageSize(apiRequest);


            apiRequest.put("condition", 5);
            //平台复审人员：查询自己名下机构信息
            String isFinalUserManage = apiRequest.getString("isFinalUserManage");
            if (!StringUtils.isEmpty(isFinalUserManage)) {
                apiRequest.put("isFinalUserManage", isFinalUserManage);
                apiRequest.put("userId", currentUserId);

                String consignorIds = surveyUserConsignorMapper.selectConsignorIds(currentUserId);
                apiRequest.put("consignorIds", consignorIds);
            }

            String reportType = apiRequest.getString("reportType");
            if ("entrust".equals(reportType)) {
                apiRequest.put("condition", 6);//保司报表 导出
            }
            String bookType = apiRequest.getString("bookType");
            if ("each".equals(bookType)) {//分表导出 :分两块数据：保司数据，互助数据
                apiRequest.put("consignorOrgAttr", 1);//仅查保司数据
            }
            apiRequest.put("acc-order", "1");

            if ("searchCount".equals(apiRequest.getString("searchCount"))) {
                int count = surveyRiskCaseInfoMapper.exportListSize(apiRequest);
                return new ApiResponse(ApiMsgEnum.SUCCESS, count, new ArrayList<SurveyRiskCaseInfoExportDto>());
            }
            List<SurveyRiskCaseInfoExportDto> list = surveyRiskCaseInfoMapper.exportList(apiRequest);
//            Map<String,Object> map = new HashMap<>();
            Date now = new Date();
            //待定


            //获取指定案件下的方向
            List<SurveyCaseDirection> surveyCaseDirections = new ArrayList<>();
            if (list.size() > 0) {
                surveyCaseDirections = surveyCaseDirectionMapper.selectByCases(list);
            }

            // 查询太平洋健康 委托时效模板数据
            Map paramMap = new HashMap();
            paramMap.put("efficiencyModelId",6);
            List<SurveyConsignorEfficiencyModelInfo> modelInfos = surveyConsignorEfficiencyModelInfoMapper.list(paramMap);


            for (SurveyRiskCaseInfoExportDto surveyRiskCaseInfoExportDto : list) {

                //获取此案件的 所有方向
                List<SurveyCaseDirection> directions = new ArrayList<>();
                String provinceStr = "";
                List<SurveyCaseDirection> collect = surveyCaseDirections.stream().filter(p -> p.getSurveyInfoId().toString().equals(surveyRiskCaseInfoExportDto.getCaseInfoId().toString())).collect(Collectors.toList());
                for (SurveyCaseDirection surveyCaseDirection : collect) {
                    directions.add(surveyCaseDirection);
                    if (surveyCaseDirection.getProvince() != null && !provinceStr.contains(surveyCaseDirection.getProvince())) {
                        provinceStr = provinceStr + surveyCaseDirection.getProvince() + ",";
                    }
                }
                surveyRiskCaseInfoExportDto.setProvinceStr(provinceStr);
                surveyRiskCaseInfoExportDto.setSurveyCaseDirections(directions);
                //获取时效
                int efficiencyAttr = surveyRiskCaseInfoExportDto.getConsignorEfficiencyAttr();//时效设置（1：工作日；2、自然日）
                int days = 0;
                //案件状态（未提交保司审核，提交保司审核）
                if (surveyRiskCaseInfoExportDto.getEntrustReportStartDate() == null) { //未提交保司审核
                    //“当前时间”与“案件截止时间”相比：
                    Date endTime = surveyRiskCaseInfoExportDto.getEndTime() == null ? now : surveyRiskCaseInfoExportDto.getEndTime();
                    if (now.before(endTime)) {
                        days = GetWorkDay.calLeaveDays(now, endTime, efficiencyAttr);
                        surveyRiskCaseInfoExportDto.setEfficiency(days);
                    } else {
                        days = GetWorkDay.calLeaveDays(endTime, now, efficiencyAttr);
                        surveyRiskCaseInfoExportDto.setEfficiency(days);
                    }
                }
                //提交保司审核
                else {
                    //“委托时间”
                    Date entrustTime = surveyRiskCaseInfoExportDto.getEntrustTime() == null ? now : surveyRiskCaseInfoExportDto.getEntrustTime();
                    //“案件截止时间”
//                    Date endTime = surveyRiskCaseInfoExportDto.getEndTime()==null ? now : surveyRiskCaseInfoExportDto.getEndTime();
                    //“提交保司审核时间”
                    Date entrustReportStartTime = surveyRiskCaseInfoExportDto.getEntrustReportStartDate() == null ? now : surveyRiskCaseInfoExportDto.getEntrustReportStartDate();
                    // 提交保司终审时间
                    Date getEntrustReportEndDate = surveyRiskCaseInfoExportDto.getEntrustReportEndDate() == null ? now : surveyRiskCaseInfoExportDto.getEntrustReportEndDate();

                    days = GetWorkDay.calLeaveDays(entrustTime, getEntrustReportEndDate, efficiencyAttr);
                    surveyRiskCaseInfoExportDto.setEfficiency(days);
                }

                surveyRiskCaseInfoExportDto.setAgingDay(7);
                if (surveyRiskCaseInfoExportDto.getServicesId() != null) {
                    if (surveyRiskCaseInfoExportDto.getServicesId() == 11 || surveyRiskCaseInfoExportDto.getServicesId() == 12) {
                        if (surveyRiskCaseInfoExportDto.getAreaType() != null) {
                            switch (surveyRiskCaseInfoExportDto.getAreaType()) {
                                case 0:
                                    surveyRiskCaseInfoExportDto.setAgingDay(3);
                                    break;
                                case 1:
                                    surveyRiskCaseInfoExportDto.setAgingDay(4);
                                    break;
                                case 2:
                                    surveyRiskCaseInfoExportDto.setAgingDay(4);
                                    break;
                                case 3:
                                    surveyRiskCaseInfoExportDto.setAgingDay(5);
                                    break;
                                case 4:
                                    surveyRiskCaseInfoExportDto.setAgingDay(7);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }


                //超期天数=案件时效-考核时效 小于0的取0
                int overTimeDay = days - surveyRiskCaseInfoExportDto.getAgingDay();
                surveyRiskCaseInfoExportDto.setOverTimeDay(overTimeDay);
                if (overTimeDay < 0) {
                    surveyRiskCaseInfoExportDto.setOverTimeDay(0);
                }
            }

            if ("each".equals(bookType)) {
                //分表导出 :分两块数据：保司数据，互助数据
                //单独查“互助数据”
                apiRequest.put("consignorOrgAttr", 2);//仅查互助数据
                List<SurveyRiskCaseInfoExportDto> huzhuList = surveyRiskCaseInfoMapper.exportHuZhuList(apiRequest);
                for (SurveyRiskCaseInfoExportDto surveyRiskCaseInfoExportDto : huzhuList) {
                    if (surveyRiskCaseInfoExportDto.getDirectionNameStrs() == null) {
                        continue;
                    }
                    String[] strs = surveyRiskCaseInfoExportDto.getDirectionNameStrs().split(",");
                    String nameStrs = "";
                    for (String str : strs) {
                        String name = "";
                        String[] item = str.split("_");
                        if (StringUtils.isEmpty(item[0])) {
                            continue;
                        }
                        String province = "";
                        if (!StringUtils.isEmpty(item[0])) {
                            province = item[0];
                        }
                        String serveiceName = "";
                        if (!StringUtils.isEmpty(item[1])) {
                            serveiceName = item[1];
                        }
                        int serveiceId = 11;
                        if (!StringUtils.isEmpty(item[2])) {
                            serveiceId = Integer.parseInt(item[2]);
                        }

                        if (serveiceId == 13) {//深度案件 不计算数量
                            name = province + "全案";
                        } else {
                            if (serveiceId == 12) { //“单点调查” -- “单点”
                                serveiceName = "单点";
                            }
                            int num = 1;
                            if (!StringUtils.isEmpty(item[3])) {
                                num = Integer.parseInt(item[3]);
                            }
                            name = province + serveiceName + "*" + num;
                        }

                        nameStrs = nameStrs + name + "+";
                    }
                    nameStrs = nameStrs.substring(0, nameStrs.length() - 1);
                    surveyRiskCaseInfoExportDto.setDirectionNameStrs(nameStrs);

                    surveyRiskCaseInfoExportDto.setAgingDay(7);
                    if (surveyRiskCaseInfoExportDto.getServicesId() != null) {
                        if (surveyRiskCaseInfoExportDto.getServicesId() == 11 || surveyRiskCaseInfoExportDto.getServicesId() == 12) {
                            if (surveyRiskCaseInfoExportDto.getAreaType() != null) {
                                switch (surveyRiskCaseInfoExportDto.getAreaType()) {
                                    case 0:
                                        surveyRiskCaseInfoExportDto.setAgingDay(3);
                                        break;
                                    case 1:
                                        surveyRiskCaseInfoExportDto.setAgingDay(4);
                                        break;
                                    case 2:
                                        surveyRiskCaseInfoExportDto.setAgingDay(4);
                                        break;
                                    case 3:
                                        surveyRiskCaseInfoExportDto.setAgingDay(5);
                                        break;
                                    case 4:
                                        surveyRiskCaseInfoExportDto.setAgingDay(7);
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }
                    }
                }
                list.addAll(huzhuList);
            }

            // 太平洋健康 取委托时效模板设置的时效
            try {
                list.stream().forEach(e -> {
                    if (e.getEntrustOrgId().intValue() == 52){
                        SurveyConsignorEfficiencyModelInfo stream = modelInfos.stream().filter(p -> p.getServiceId().intValue() == e.getServicesId().intValue() && p.getAreaCategoriesId().toString().equals(e.getInvestigationArea())).findFirst().orElse(null);
                        if (Objects.nonNull(stream)){
                            e.setAgingDay(stream.getDays());
                        }
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }


            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, list);
        } else if ("assign-list".equals(menuCode)) { //案件分派
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String entrustStateTime = apiRequest.getString("entrustStateTime");
            String entrustEndTime = apiRequest.getString("entrustEndTime");
            try {
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, -1);
                Date start = c.getTime();
                String yesterday = format.format(start);//前一天

                if (entrustStateTime == null) {
                    entrustStateTime = yesterday;
                    apiRequest.put("entrustStateTime", simpleDateFormat.parse(entrustStateTime));
                }
                if (entrustEndTime == null) {
                    entrustEndTime = yesterday;
                    apiRequest.put("entrustEndTime", simpleDateFormat.parse(entrustEndTime));
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            List<SurveyRiskCaseInfoExportDto> list = surveyRiskCaseInfoMapper.exportList(apiRequest);
            Map<String, Object> map = new HashMap<>();
            List<SurveyAssignOrgDto> surveyAssignOrgs = surveyAssignOrgMapper.list(map);
            for (SurveyRiskCaseInfoExportDto surveyRiskCaseInfoExportDto : list) {
                List items = new ArrayList();
                for (SurveyAssignOrgDto assignOrgList : surveyAssignOrgs) {
                    if (surveyRiskCaseInfoExportDto.getCaseInfoId().intValue() == assignOrgList.getSurveyInfoId().intValue()) {
                        items.add(assignOrgList);
                    }
                }
                surveyRiskCaseInfoExportDto.setSurveyAssignOrgs(items);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, list);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS, null, null);
    }


    /**
     * 生成报告
     *
     * @param surveyRiskCaseInfo
     */
    private Boolean generatorReport(SurveyRiskCaseInfo surveyRiskCaseInfo) {
        //根据委托机构查询模板ID
        Long modelId = 1L;
        return oneModel(surveyRiskCaseInfo);
    }

    private Boolean oneModel(SurveyRiskCaseInfo surveyRiskCaseInfo) {
        return true;
    }

    @ApiMethod(needLogin = false, descript = "分派调查员，查询对应的任务类型", value = "backend-survey-task-by-user-id")
    @Override
    public ApiResponse selectTaskByUserId(ApiRequest apiRequest) {
        Map<String, Object> returnMap = new HashMap<>(); // 返回数据
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        Long surveyUserId = apiRequest.getLong("userId");
        Long surveyOrgId = apiRequest.getLong("surveyOrgId");
        String returnType = apiRequest.getString("returnType");  //返回的“任务类型”：“surveyUser：调查员”，“surveyOrg：机构”
        Boolean isHaveAssign = false; //是否分派过机构，或者调查员（决定前端展示是全部任务类型，还是分派指定的任务类型）
        String taskRemark = ""; //任务类型描述

        String btnCode = apiRequest.getString("btnCode");
        if ("116".equals(btnCode)) {
            SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(surveyUserId);
            if (investigator != null) {
                surveyOrgId = investigator.getOrgId();
            }
        }

        //该案件的所有任务类型
        List<SurveyTaskType> surveyTaskTypeAll = surveyTaskTypeMapper.getSurveyTaskTypeBySurveyInfoId(surveyInfoId);

        List<SurveyTaskTypeDto> surveyTaskTypeDtoList = new ArrayList<>();
        if ("surveyUser".equals(returnType)) {
            Map<String, Object> map = new HashMap<>();
            //该案件在机构的所有任务类型
            map = new HashMap<>();
            map.put("surveyOrgId", surveyOrgId);
            map.put("surveyInfoId", surveyInfoId);
            SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
            List<SurveyAssignOrgType> assignOrgTypeAll = new ArrayList<>();
            if (surveyAssignOrg != null) {
                map = new HashMap<>();
                map.put("surveyAssignOrgId", surveyAssignOrg.getId());
                map.put("surveyInfoId", surveyInfoId);
                assignOrgTypeAll = surveyAssignOrgTypeMapper.list(map);
            }


            //查询案件，是否分派给某调查员
            map = new HashMap<>();
            map.put("surveyInfoId", surveyInfoId);
            map.put("surveyUserId", surveyUserId);
            SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.getSurveyInvestigatorCaseByOne(map);

            if (surveyInvestigatorCase != null) {
                isHaveAssign = true;
                taskRemark = surveyInvestigatorCase.getSurveyTaskRemark();
                //已分派的任务类型
                map = new HashMap<>();
                map.put("surveyUserCaseId", surveyInvestigatorCase.getId());
                map.put("surveyUserId", surveyUserId);
                List<SurveyInvestigatorCaseType> investigatorCaseTypes = surveyInvestigatorCaseTypeMapper.list(map);

                //已分派并做了方向的任务类型
                map = new HashMap<>();
                map.put("surveyInvestigatorCaseId", surveyInvestigatorCase.getId());
                map.put("surveyUserId", surveyUserId);
                List<SurveyCaseDirection> caseDirections = surveyCaseDirectionMapper.list(map);

                for (int i = 0; i < assignOrgTypeAll.size(); i++) {
                    SurveyTaskTypeDto dto = new SurveyTaskTypeDto();
                    dto.setSurveyAssignOrgType(assignOrgTypeAll.get(i));
                    dto.setSelectType(1);//默认未分派
                    for (int j = 0; j < investigatorCaseTypes.size(); j++) {
                        if (assignOrgTypeAll.get(i).getTaskId() == investigatorCaseTypes.get(j).getTaskId()) { //已分配
                            dto.setSelectType(2);//已分派，没有做方向 (已分配：默认2)
                            for (int z = 0; z < caseDirections.size(); z++) {
                                if (caseDirections.get(z).getTaskId() == investigatorCaseTypes.get(j).getTaskId()) { //做方向了
                                    dto.setSelectType(3);//已分派，做了方向
                                }
                            }
                            break;
                        }
                    }
                    surveyTaskTypeDtoList.add(dto);
                }
                returnMap.put("taskType", "orgCaseType"); //机构任务类型
            } else {
                //分人员时，会有两种情况：1、机构已存在任务类型；2、机构不存在任务类型
                if (assignOrgTypeAll.size() > 0) {
                    for (int i = 0; i < assignOrgTypeAll.size(); i++) {
                        SurveyTaskTypeDto dto = new SurveyTaskTypeDto();
                        dto.setSurveyAssignOrgType(assignOrgTypeAll.get(i));
                        dto.setSelectType(1);
                        surveyTaskTypeDtoList.add(dto);
                    }
                    returnMap.put("taskType", "orgCaseType"); //机构任务类型
                } else {
                    for (int i = 0; i < surveyTaskTypeAll.size(); i++) {
                        SurveyTaskTypeDto dto = new SurveyTaskTypeDto();
                        dto.setSurveyTaskType(surveyTaskTypeAll.get(i));
                        dto.setSelectType(1);
                        surveyTaskTypeDtoList.add(dto);
                    }
                    returnMap.put("taskType", "caseType");//案件任务类型
                }
            }
        }

        //机构
        else if ("surveyOrg".equals(returnType)) {
            //查询案件，是否分派给该机构
            Map<String, Object> orgMap = new HashMap<>();
            orgMap.put("surveyInfoId", surveyInfoId);
            orgMap.put("surveyOrgId", surveyOrgId);
            SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(orgMap);
            if (surveyAssignOrg != null) {
                isHaveAssign = true;
                taskRemark = surveyAssignOrg.getOrgTaskRemark();
                //已分派给 机构的任务类型
                Map<String, Object> map = new HashMap<>();
                map.put("surveyAssignOrgId", surveyAssignOrg.getId());
                List<SurveyAssignOrgType> assignOrgTypeList = surveyAssignOrgTypeMapper.list(map);

                //查询该机构，是否已分派给调查员，以及调查员是否做过方向
                ApiRequest apiRequestNew = new ApiRequest();
                apiRequestNew.put("surveyAssorgCaseId", surveyAssignOrg.getId());
                List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.list(apiRequestNew);
                List<SurveyCaseDirection> caseDirectionAll = new ArrayList<>();
                for (int i = 0; i < cases.size(); i++) {
                    List<SurveyCaseDirection> caseDirections = new ArrayList<>();
                    //查询每个调查员做过的方向
                    map = new HashMap<>();
                    map.put("surveyInvestigatorCaseId", cases.get(i).getId());
                    caseDirections = surveyCaseDirectionMapper.list(map);
                    caseDirectionAll.addAll(caseDirections);
                }

                for (int i = 0; i < surveyTaskTypeAll.size(); i++) {
                    SurveyTaskTypeDto dto = new SurveyTaskTypeDto();
                    dto.setSurveyTaskType(surveyTaskTypeAll.get(i));
                    dto.setSelectType(1);//默认未分派
                    for (int j = 0; j < assignOrgTypeList.size(); j++) {
                        if (surveyTaskTypeAll.get(i).getTaskId() == assignOrgTypeList.get(j).getTaskId()) { //已分配
                            dto.setSelectType(2);//已分派，没有做方向 (已分配：默认2)
                            for (int z = 0; z < caseDirectionAll.size(); z++) {
                                if (caseDirectionAll.get(z).getTaskId() == assignOrgTypeList.get(j).getTaskId()) { //做方向了
                                    dto.setSelectType(3);//已分派，做了方向
                                }
                            }
                            break;
                        }
                    }
                    surveyTaskTypeDtoList.add(dto);
                }
            } else {
                for (int i = 0; i < surveyTaskTypeAll.size(); i++) {
                    SurveyTaskTypeDto dto = new SurveyTaskTypeDto();
                    dto.setSurveyTaskType(surveyTaskTypeAll.get(i));
                    dto.setSelectType(1);
                    surveyTaskTypeDtoList.add(dto);
                }
            }
        }
        //1、updateCaseTaskType：修改案件任务类型/2、updateOrgTaskType：修改机构案件任务类型
        else if ("updateCaseTaskType".equals(returnType) || "updateOrgTaskType".equals(returnType)) {
            returnMap = selectTaskByCaseInfo(returnMap, apiRequest, returnType);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, returnMap);
        }

        returnMap.put("surveyTaskTypeDtoList", surveyTaskTypeDtoList);
        returnMap.put("isHaveAssign", isHaveAssign);
        returnMap.put("taskRemark", taskRemark);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, returnMap);
    }

    /**
     * 获取模板数据
     *
     * @param userInfo           签发人
     * @param surveyInfoId       调查案件ID
     * @param isGetdirectionSize 是否获取方向信息
     * @return
     */
    private TemplateData getTemplateData(UserInfo userInfo, Long surveyInfoId, Boolean isGetdirectionSize, String reportCompletion) {
        SurveyRiskCaseInfoDto surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInfoId);
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyInfoId(surveyRiskCaseInfo.getId());
        SurveyConsignorModel consignorModel = surveyConsignorModelMapper.selectByConsignorId(surveyRiskCase.getEntrustOrgId());
        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
        surveyRiskCaseInfo.setSurveyConsignor(surveyConsignor);
        //方向
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<SurveyCaseDirection> items = new ArrayList<>();
        if (surveyRiskCase.getTransferType() == null) {
            surveyRiskCase.setTransferType(1);
        }
        List<SurveyRiskCaseInfoDto> riskCaseInfos = new ArrayList<>();
        //如果是二调以上
        if (surveyRiskCase.getTransferType() > 1) {
            SurveyRiskCaseTransfer surveyRiskCaseTransfer = surveyRiskCaseTransferMapper.selectBySurveyId(surveyRiskCaseInfo.getId());
            if (surveyRiskCaseTransfer != null) {
                String[] ids = surveyRiskCaseTransfer.getIds().split(",");
                for (String id : ids) {
                    if (!"".equals(id)) {
                        paramMap = new HashMap<String, Object>();
                        paramMap.put("surveyInfoId", id);
                        paramMap.put("invalidState", 0);//无效方向
                        List<SurveyCaseDirection> temps = surveyCaseDirectionMapper.list(paramMap);
                        SurveyRiskCaseInfo obj = surveyRiskCaseInfoMapper.selectByPrimaryKey(Long.parseLong(id));
                        SurveyRiskCaseInfoDto riskCaseInfoDto = surveyRiskCaseInfoMapper.selectByPrimaryKey(Long.parseLong(id));
                        riskCaseInfos.add(riskCaseInfoDto);
                        if (obj != null) {
                            SurveyRiskCase riskCase = surveyRiskCaseMapper.selectByPrimaryKey(obj.getSurveyId());
                            for (SurveyCaseDirection temp : temps) {
                                temp.setSurveyRiskCase(riskCase);
                            }
                        }
                        items.addAll(temps);
                    }
                }
            }
        }
        //
        paramMap.put("surveyInfoId", surveyRiskCaseInfo.getId());
        paramMap.put("invalidState", 0);//无效方向
        List<SurveyCaseDirection> curDirection = surveyCaseDirectionMapper.list(paramMap);
        for (SurveyCaseDirection surveyCaseDirection : curDirection) {
            surveyCaseDirection.setSurveyRiskCase(surveyRiskCase);
        }
        items.addAll(curDirection);

        List<SurveyCaseDirectionDto> directions = new ArrayList<>();
        for (SurveyCaseDirection direction : items) {
            SurveyCaseDirectionDto surveyCaseDirectionDto = new SurveyCaseDirectionDto();
            try {
                BeanUtils.copyProperties(surveyCaseDirectionDto, direction);
                if (direction.getSurveyRiskCase() != null) {
                    SurveyRiskCase dirRiskCase = direction.getSurveyRiskCase();
                    if (dirRiskCase.getTransferType() == null) {
                        dirRiskCase.setTransferType(1);
                    }
                    if (dirRiskCase.getTransferType() > 1) {
                        surveyCaseDirectionDto.setDirectionName(direction.getDirectionName() + "(" + dirRiskCase.getTransferTypeName() + ")");
                    } else {
                        surveyCaseDirectionDto.setDirectionName(direction.getDirectionName());
                    }
                } else {
                    surveyCaseDirectionDto.setDirectionName(direction.getDirectionName());
                }
                surveyCaseDirectionDto.setRealDirectionName(direction.getDirectionName());
                surveyCaseDirectionDto.setDirectionInfo(direction.getDirectionText());
                surveyCaseDirectionDto.setItemDateStr(DateUtils.DateToStr(direction.getCreateTime(), "yyyy年MM月dd日"));
                surveyCaseDirectionDto.setItemCityStr(direction.getAreaName());
                surveyCaseDirectionDto.setItemContext(direction.getDirectionText());
                surveyCaseDirectionDto.setCityStr(direction.getCity());
                surveyCaseDirectionDto.setAddressStr(direction.getAreaName());
                surveyCaseDirectionDto.setMoney1(direction.getEntrustMoney() == null ? "0" : direction.getEntrustMoney().toString());
                surveyCaseDirectionDto.setMoney2(direction.getEntrustMoney() == null ? "0" : direction.getEntrustMoney().toString());
                surveyCaseDirectionDto.setMargeStr("0");
                if (isGetdirectionSize) {//获取方向附件的数量
                    Map map = new HashMap();
                    map.put("directionId", surveyCaseDirectionDto.getId());
                    List<SurveyCaseDirectionFileDto> directionFileDtos = surveyCaseDirectionFileMapper.list(map);
                    surveyCaseDirectionDto.setSurveyCaseDirectionFiles(directionFileDtos);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            directions.add(surveyCaseDirectionDto);
        }


        SurveyModelInfo model = null;
        if (consignorModel == null) {
            model = surveyModelInfoMapper.selectByPrimaryKey(3L);
        } else {
            model = surveyModelInfoMapper.selectByPrimaryKey(consignorModel.getModelId());
        }
        if (model == null) {
            return null;
        }
        TemplateData templateData = new TemplateData();
        //获取模板名称
        StringBuffer reportName = new StringBuffer();
        SurveyConsignorReportRule reportRule = surveyConsignorReportRuleMapper.selectByConsignorId(surveyRiskCaseInfo.getEntrustOrgId());
        if (reportRule == null) {
            reportRule = new SurveyConsignorReportRule();
            reportRule.setOneRule(2);
            reportRule.setTwoRule(5);
            reportRule.setThreeRule(5);
            reportRule.setFourRule(5);
        }
        if (reportRule.getOneRule() == 1) {
            reportName.append(surveyRiskCase.getPolicyNo() == null ? "" : surveyRiskCase.getPolicyNo());
        } else if (reportRule.getOneRule() == 2) {
            reportName.append(surveyRiskCase.getSurveyPerson() == null ? "" : surveyRiskCase.getSurveyPerson());
        } else if (reportRule.getOneRule() == 3) {
            reportName.append(surveyRiskCase.getClaimsNo() == null ? "" : surveyRiskCase.getClaimsNo());
        } else if (reportRule.getOneRule() == 4) {
            reportName.append(surveyRiskCase.getEntrustOrgName() == null ? "" : surveyRiskCase.getEntrustOrgName());
        } else if (reportRule.getOneRule() == 5) {
            reportName.append("");
        } else if (reportRule.getOneRule() == 6) {
            reportName.append(reportRule.getOneRuleStr() == null ? "" : reportRule.getOneRuleStr());
        }

        if (reportRule.getTwoRule() == 1) {
            reportName.append(surveyRiskCase.getPolicyNo() == null ? "" : surveyRiskCase.getPolicyNo());
        } else if (reportRule.getTwoRule() == 2) {
            reportName.append(surveyRiskCase.getSurveyPerson() == null ? "" : surveyRiskCase.getSurveyPerson());
        } else if (reportRule.getTwoRule() == 3) {
            reportName.append(surveyRiskCase.getClaimsNo() == null ? "" : surveyRiskCase.getClaimsNo());
        } else if (reportRule.getTwoRule() == 4) {
            reportName.append(surveyRiskCase.getEntrustOrgName() == null ? "" : surveyRiskCase.getEntrustOrgName());
        } else if (reportRule.getTwoRule() == 5) {
            reportName.append("");
        } else if (reportRule.getTwoRule() == 6) {
            reportName.append(reportRule.getTwoRuleStr() == null ? "" : reportRule.getTwoRuleStr());
        }

        if (reportRule.getThreeRule() == 1) {
            reportName.append(surveyRiskCase.getPolicyNo() == null ? "" : surveyRiskCase.getPolicyNo());
        } else if (reportRule.getThreeRule() == 2) {
            reportName.append(surveyRiskCase.getSurveyPerson() == null ? "" : surveyRiskCase.getSurveyPerson());
        } else if (reportRule.getThreeRule() == 3) {
            reportName.append(surveyRiskCase.getClaimsNo() == null ? "" : surveyRiskCase.getClaimsNo());
        } else if (reportRule.getThreeRule() == 4) {
            reportName.append(surveyRiskCase.getEntrustOrgName() == null ? "" : surveyRiskCase.getEntrustOrgName());
        } else if (reportRule.getThreeRule() == 5) {
            reportName.append("");
        } else if (reportRule.getThreeRule() == 6) {
            reportName.append(reportRule.getThreeRuleStr() == null ? "" : reportRule.getThreeRuleStr());
        }

        if (reportRule.getFourRule() == 1) {
            reportName.append(surveyRiskCase.getPolicyNo() == null ? "" : surveyRiskCase.getPolicyNo());
        } else if (reportRule.getFourRule() == 2) {
            reportName.append(surveyRiskCase.getSurveyPerson() == null ? "" : surveyRiskCase.getSurveyPerson());
        } else if (reportRule.getFourRule() == 3) {
            reportName.append(surveyRiskCase.getClaimsNo() == null ? "" : surveyRiskCase.getClaimsNo());
        } else if (reportRule.getFourRule() == 4) {
            reportName.append(surveyRiskCase.getEntrustOrgName() == null ? "" : surveyRiskCase.getEntrustOrgName());
        } else if (reportRule.getFourRule() == 5) {
            reportName.append("");
        } else if (reportRule.getFourRule() == 6) {
            reportName.append(reportRule.getFourRuleStr() == null ? "" : reportRule.getFourRuleStr());
        }
        //下载的报告名称以案件编号为命名
        if (surveyRiskCase.getSurveyCaseNo() == null) {
            templateData.setReportName(reportName.toString().concat("案"));
        } else {
            templateData.setReportName(surveyRiskCase.getSurveyCaseNo());
        }
//        templateData.setReportName(reportName.toString().concat("案"));
        templateData.setModel(model);
        surveyRiskCaseInfo.setSurveyRiskCase(surveyRiskCase);
        templateData.setSurveyRiskCaseInfo(surveyRiskCaseInfo);
        templateData.setDirections(directions);
        //返回二调的上级案件信息
        templateData.setRiskCaseInfos(riskCaseInfos);

        if (model.getId().intValue() == 1 || model.getId().intValue() == 6 || model.getId().intValue() == 7) {
            TemplateDataLF template = new TemplateDataLF();
            template.setEntrustOrgName(surveyRiskCase.getEntrustOrgName());
            template.setTrusteeOrgName("江苏乐凡保险公估有限公司");
            template.setEntrustUserName(surveyRiskCase.getSurveyPerson());
            template.setSurveyCno(surveyRiskCase.getClaimsNo() == null ? "" : surveyRiskCase.getClaimsNo());
//            template.setSurveyCno(surveyRiskCase.getSurveyCaseNo() == null ? "" : surveyRiskCase.getSurveyCaseNo());
            template.setEntrustDate(DateUtils.DateToStr(surveyRiskCase.getEntrustTime(), "yyyy年MM月dd日"));
            template.setSafeTypeStr(surveyRiskCase.getInsureName() == null ? "" : surveyRiskCase.getInsureName());
            template.setSafeDate(DateUtils.DateToStr(surveyRiskCase.getInsureTime(), "yyyy年MM月dd日"));

//
//            String names = "";
//            for (SurveyInvestigatorCase aCase : cases) {
//                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(aCase.getSurveyUserId());
//                if (surveyInvestigator != null && surveyInvestigator.getRegisterNo() != null) {
//                    names = names.concat(aCase.getSurveyUserName() + "(执业证:" + surveyInvestigator.getRegisterNo() + ")") + ",";
//                } else {
//                    names = names.concat(aCase.getSurveyUserName()) + ",";
//                }
//            }
//
//            // 如果只有一个调查员 则随机拼接一个有执证编号的调查员
//            if (cases.size() == 1) {
//                SurveyInvestigator appendUser = null;
//                paramMap = new HashMap<String, Object>();
//                paramMap.put("register", "yes");
//                List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.selectByMap(paramMap);
//                if (surveyInvestigators.size() > 0) {
//                    List<SurveyInvestigator> collect = surveyInvestigators.stream().filter(p -> cases.get(0).getSurveyOrgId().equals(p.getOrgId()) && !cases.get(0).getSurveyUserId().equals(p.getUserId())).collect(Collectors.toList());
//                    if (collect.size() > 0) {
//                        appendUser = collect.get(0);
//                    } else {
//                        long random = RandomIDUtil.getRandom(0, surveyInvestigators.size() - 1);
//                        appendUser = surveyInvestigators.get((int) random);
//                    }
//                }
//                if (appendUser != null) {
//                    names = names.concat(appendUser.getRealName() + "(执业证:" + appendUser.getRegisterNo() + ")") + ",";
//                }
//            }
//
//            if (names.length() > 0) {
//                template.setSurveyUserName(names.substring(0, names.length() - 1));
//            } else {
//                template.setSurveyUserName("");
//            }


            String sendReportUserName = surveyRiskCaseInfo.getSendReportUserName();
            //至少两个调查员  两个调查员必须都有执业证, 优先以原始调查员、              其次同机构调查员、最后随机调查员
            List<Long> qfrUsers = new ArrayList<>();
            // todo 优先取该案案件历史已保存的users。 如果没有数据再随机。随机之后保存;


            paramMap = new HashMap<String, Object>();
            paramMap.put("register", "yes");
            List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.selectByMap(paramMap);
            List<Long> filterUser = new ArrayList<>();
            filterUser.add(0L);
            filterUser.add(81L);
            filterUser.add(83L);
            filterUser.add(176L);
            filterUser.add(825L);
            filterUser.add(771L);
            List<SurveyInvestigator> collect = surveyInvestigators.stream().filter(f -> !f.getUserId().toString().equals(surveyRiskCaseInfo.getBelongUserId().toString()) && !filterUser.contains(f.getUserId())).collect(Collectors.toList());//过滤掉当前案件审核人


            cases.forEach(k -> {
                SurveyInvestigator surveyInvestigator = collect.stream().filter(e -> e.getUserId().toString().equals(k.getSurveyUserId().toString())).findFirst().orElse(null);
                if (Objects.nonNull(surveyInvestigator)) {
                    qfrUsers.add(surveyInvestigator.getUserId());//优先匹配原始调查员
                }
            });
            if (qfrUsers.size() < 2){//同机构匹配 + 随机  并且 随机调查员不能重复
                List<Long> surveyOrgIds = cases.stream().map(SurveyInvestigatorCase::getSurveyOrgId).collect(Collectors.toList());
                List<SurveyInvestigator> collect2 = collect.stream().filter(p -> surveyOrgIds.contains(p.getOrgId())).collect(Collectors.toList());//同机构的所有调查员
                Collections.shuffle(collect2);
                collect2.forEach(e -> {
                    if (!qfrUsers.contains(e.getUserId()) && qfrUsers.size() < 2){
                        qfrUsers.add(e.getUserId());
                    }
                });
            }
            if (qfrUsers.size() < 2){//随机匹配
                Collections.shuffle(collect);
                collect.forEach(e -> {
                    if (!qfrUsers.contains(e.getUserId()) && qfrUsers.size() < 2){
                        qfrUsers.add(e.getUserId());
                    }
                });
            }

            String names = "";
            for (Long qfrUser : qfrUsers) {
                SurveyInvestigator surveyInvestigator = surveyInvestigators.stream().filter(p -> p.getUserId().toString().equals(qfrUser.toString())).findFirst().orElse(null);
                names = names.concat(surveyInvestigator.getRealName() + "(执业证:" + surveyInvestigator.getRegisterNo() + ")") + ",";
            }
            if (names.length() > 0) {
                template.setSurveyUserName(names.substring(0, names.length() - 1));
            } else {
                template.setSurveyUserName("");
            }

            //随机出来的人 保存
            if (qfrUsers.size() > 0 && Objects.isNull(surveyRiskCaseInfo.getLefanReportDate())){
                SurveyRiskCaseInfo item = new SurveyRiskCaseInfo();
                item.setId(surveyRiskCaseInfo.getId());
                item.setSendReportUserName(JSONArray.toJSONString(qfrUsers));
                surveyRiskCaseInfoMapper.updateByPrimaryKeySelective(item);
            }

            template.setSurveyUserDate(DateUtils.DateToStr(surveyRiskCase.getDangerTime(), "yyyy年MM月dd日"));
            template.setSurveyUserAddress(surveyRiskCase.getDangerAddress() == null ? "" : surveyRiskCase.getDangerAddress());
            template.setSurveyItem(surveyRiskCaseInfo.getSurveyItem() == null ? "" : surveyRiskCaseInfo.getSurveyItem());
            template.setAccidentInfo(surveyRiskCaseInfo.getSurveyInfo() == null ? "" : surveyRiskCaseInfo.getSurveyInfo());
            if (surveyRiskCase.getSex() != null) {
                if (surveyRiskCase.getSex() == 1) {
                    template.setSexStr("男");
                } else if (surveyRiskCase.getSex() == 2) {
                    template.setSexStr("女");
                }
            } else {
                template.setSexStr("");
            }
            if (surveyRiskCase.getAge() != null) {
                template.setNation(surveyRiskCase.getAge().toString() + "周岁");
                template.setAge(surveyRiskCase.getAge().toString());
            } else {
                template.setNation("");
            }
            template.setIdNumber(surveyRiskCase.getIdNumber() == null ? "" : surveyRiskCase.getIdNumber());
            Integer idType = surveyRiskCase.getIdType() == null ? 1 : surveyRiskCase.getIdType();
            if (idType == 1) {
                template.setNumberTypeStr("身份证");
            } else if (idType == 2) {
                template.setNumberTypeStr("驾驶证");
            } else if (idType == 3) {
                template.setNumberTypeStr("护照号码");
            } else if (idType == 4) {
                template.setNumberTypeStr("其它证件号码");
            }
            template.setDirections(directions);
            if (reportCompletion != null) {
                surveyRiskCaseInfo.setReportCompletion(reportCompletion);
            }
            template.setDirectionResult(surveyRiskCaseInfo.getReportCompletion() == null ? "" : surveyRiskCaseInfo.getReportCompletion());
            StringBuffer midInfo = new StringBuffer();
            StringBuffer directionNames = new StringBuffer();
            int i = 0;
            for (SurveyCaseDirectionDto direction : directions) {
                i = i + 1;
                directionNames.append(i + "、" + direction.getDirectionName() + ";");
            }
            i = 0;
            for (SurveyCaseDirectionDto direction : directions) {
                if (direction.getSurveyReason() != null && !"".equals(direction.getSurveyReason())) {
                    i = i + 1;
                    midInfo.append(i + "、" + direction.getSurveyReason() + "\n");
                }
            }
            template.setDirectionNames(directionNames.toString());
            template.setFileMidInfo(midInfo.toString());


            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyRiskCaseInfo.getBelongUserId());
            if (surveyInvestigator != null && StringUtils.isNotEmpty(surveyInvestigator.getRegisterNo())) {
                template.setSurveyManagerName(surveyRiskCaseInfo.getBelongUserName() + "(执业证:" + surveyInvestigator.getRegisterNo() + ")");
            } else {
                template.setSurveyManagerName(surveyRiskCaseInfo.getBelongUserName());
            }
            template.setUserSignDate(DateUtils.DateToStr(new Date(), "yyyy年MM月dd日"));
//            if (userInfo != null){
//                template.setSurveyManagerName(userInfo.getUserName());
//                template.setUserSignDate(DateUtils.DateToStr(new Date(),"yyyy年MM月dd日"));
//            }else{
//                template.setSurveyManagerName("");
//                template.setUserSignDate("");
//            }
            String dateStr = DateUtils.DateToStr(surveyRiskCaseInfo.getLefanReportDate() == null ? new Date() : surveyRiskCaseInfo.getLefanReportDate(), "yyyy年MM月dd日");
            template.setUserSignDate(dateStr);
            templateData.setTemplateDataLF(template);
        } else if (model.getId().intValue() == 2) {
            TemplateDataZY template = new TemplateDataZY();
            template.setEntrustOrgName(surveyRiskCase.getEntrustOrgName());
            template.setTrusteeOrgName("上海正言金融信息服务有限公司");
            template.setEntrustUserName(surveyRiskCase.getSurveyPerson());
            template.setSurveyCno(surveyRiskCase.getClaimsNo() == null ? "" : surveyRiskCase.getClaimsNo());
//            template.setSurveyCno(surveyRiskCase.getSurveyCaseNo() == null ? "" : surveyRiskCase.getSurveyCaseNo());
            template.setEntrustDate(DateUtils.DateToStr(surveyRiskCase.getEntrustTime(), "yyyy年MM月dd日"));
            template.setSafeTypeStr(surveyRiskCase.getInsureName() == null ? "" : surveyRiskCase.getInsureName());
            template.setEntrustUserName(surveyRiskCase.getSurveyPerson());
            template.setSafeDate(DateUtils.DateToStr(surveyRiskCase.getInsureTime(), "yyyy年MM月dd日"));
            template.setSafeUserDate(DateUtils.DateToStr(surveyRiskCase.getDangerTime(), "yyyy年MM月dd日"));
            template.setSafeUserAddress(surveyRiskCase.getDangerAddress() == null ? "" : surveyRiskCase.getDangerAddress());
            template.setSurveyItem(surveyRiskCaseInfo.getSurveyItem() == null ? "" : surveyRiskCaseInfo.getSurveyItem());
            template.setAccidentInfo(surveyRiskCaseInfo.getSurveyInfo() == null ? "" : surveyRiskCaseInfo.getSurveyInfo());
            if (surveyRiskCase.getSex() != null) {
                if (surveyRiskCase.getSex() == 1) {
                    template.setSexStr("男");
                } else if (surveyRiskCase.getSex() == 2) {
                    template.setSexStr("女");
                }
            } else {
                template.setSexStr("");
            }
            if (surveyRiskCase.getAge() != null) {
                template.setNation(surveyRiskCase.getAge().toString() + "周岁");
                template.setAge(surveyRiskCase.getAge().toString());
            } else {
                template.setNation("");
            }
            template.setIdNumber(surveyRiskCase.getIdNumber() == null ? "" : surveyRiskCase.getIdNumber());
            Integer idType = surveyRiskCase.getIdType() == null ? 1 : surveyRiskCase.getIdType();
            if (idType == 1) {
                template.setNumberTypeStr("身份证");
            } else if (idType == 2) {
                template.setNumberTypeStr("驾驶证");
            } else if (idType == 3) {
                template.setNumberTypeStr("护照号码");
            } else if (idType == 4) {
                template.setNumberTypeStr("其它证件号码");
            }
            template.setDomicileAddress("");
            template.setDirections(directions);
            if (reportCompletion != null) {
                surveyRiskCaseInfo.setReportCompletion(reportCompletion);
            }
            template.setDirectionResult(surveyRiskCaseInfo.getReportCompletion() == null ? "" : surveyRiskCaseInfo.getReportCompletion());
            StringBuffer midInfo = new StringBuffer();
            StringBuffer directionNames = new StringBuffer();
            int i = 0;
            for (SurveyCaseDirectionDto direction : directions) {
                i = i + 1;
                directionNames.append(i + "、" + direction.getDirectionName() + ";");
            }
            i = 0;
            for (SurveyCaseDirectionDto direction : directions) {
                if (direction.getSurveyReason() != null && !"".equals(direction.getSurveyReason())) {
                    i = i + 1;
                    midInfo.append(i + "、" + direction.getSurveyReason() + "\n");
                }
            }
            template.setDirectionNames(directionNames.toString());
            template.setDirectionBasis(midInfo.toString());

//            String names = "";
//            for (SurveyInvestigatorCase aCase : cases) {
//                names = names.concat(aCase.getSurveyUserName()) + ",";
//            }
//            if (names.length() > 0) {
//                template.setNames(names.substring(0, names.length() - 1));
//            } else {
//                template.setNames("");
//            }

            //至少两个调查员  两个调查员必须都有执业证, 优先以原始调查员、              其次同机构调查员、最后随机调查员
            List<Long> qfrUsers = new ArrayList<>();
            paramMap = new HashMap<String, Object>();
            paramMap.put("register", "yes");
            List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.selectByMap(paramMap);
            List<SurveyInvestigator> collect = surveyInvestigators.stream().filter(f -> !f.getUserId().toString().equals(surveyRiskCaseInfo.getBelongUserId().toString())).collect(Collectors.toList());//过滤掉当前案件审核人
            cases.forEach(k -> {
                SurveyInvestigator surveyInvestigator = collect.stream().filter(e -> e.getUserId().toString().equals(k.getSurveyUserId().toString())).findFirst().orElse(null);
                if (Objects.nonNull(surveyInvestigator)) {
                    qfrUsers.add(surveyInvestigator.getUserId());//优先匹配原始调查员
                }
            });
            if (qfrUsers.size() < 2){//同机构匹配 + 随机  并且 随机调查员不能重复
                List<Long> surveyOrgIds = cases.stream().map(SurveyInvestigatorCase::getSurveyOrgId).collect(Collectors.toList());
                List<SurveyInvestigator> collect2 = collect.stream().filter(p -> surveyOrgIds.contains(p.getOrgId())).collect(Collectors.toList());//同机构的所有调查员
                Collections.shuffle(collect2);
                collect2.forEach(e -> {
                    if (!qfrUsers.contains(e.getUserId()) && qfrUsers.size() < 2){
                        qfrUsers.add(e.getUserId());
                    }
                });
            }
            if (qfrUsers.size() < 2){//随机匹配
                Collections.shuffle(collect);
                collect.forEach(e -> {
                    if (!qfrUsers.contains(e.getUserId()) && qfrUsers.size() < 2){
                        qfrUsers.add(e.getUserId());
                    }
                });
            }

            String names = "";
            for (Long qfrUser : qfrUsers) {
                SurveyInvestigator surveyInvestigator = surveyInvestigators.stream().filter(p -> p.getUserId().toString().equals(qfrUser.toString())).findFirst().orElse(null);
                names = names.concat(surveyInvestigator.getRealName() + "(执业证:" + surveyInvestigator.getRegisterNo() + ")") + ",";
            }
            if (names.length() > 0) {
                template.setNames(names.substring(0, names.length() - 1));
            } else {
                template.setNames("");
            }



            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyRiskCaseInfo.getBelongUserId());
            if (surveyInvestigator != null && StringUtils.isNotEmpty(surveyInvestigator.getRegisterNo())) {
                template.setQfUserName(surveyRiskCaseInfo.getBelongUserName() + "(执业证:" + surveyInvestigator.getRegisterNo() + ")");
            } else {
                template.setQfUserName(surveyRiskCaseInfo.getBelongUserName());
            }
            template.setDatestr(DateUtils.DateToStr(new Date(), "yyyy年MM月dd日"));
//            if (userInfo != null){
//                template.setQfUserName(userInfo.getUserName());
//                template.setDatestr(DateUtils.DateToStr(new Date(),"yyyy年MM月dd日"));
//            }else{
//                template.setQfUserName("");
//                template.setDatestr("");
//            }

            String dateStr = DateUtils.DateToStr(surveyRiskCaseInfo.getLefanReportDate() == null ? new Date() : surveyRiskCaseInfo.getLefanReportDate(), "yyyy年MM月dd日");
            template.setDatestr(dateStr);
            templateData.setTemplateDataZY(template);
        } else if (model.getId().intValue() == 3) {
            TemplateDataZD template = new TemplateDataZD();
            template.setTrusteeOrgName("江苏乐凡保险公估有限公司");
            template.setEntrustDate(DateUtils.DateToStr(surveyRiskCase.getEntrustTime(), "yyyy年MM月dd日"));
            if (surveyRiskCaseInfo.getLefanReportDate() == null) {
                surveyRiskCaseInfo.setLefanReportDate(new Date());
            }
            template.setEndDate(DateUtils.DateToStr(surveyRiskCaseInfo.getLefanReportDate(), "yyyy年MM月dd日"));
            template.setEntrustUserName(surveyRiskCase.getSurveyPerson());
            template.setIdNumber(surveyRiskCase.getIdNumber() == null ? "" : surveyRiskCase.getIdNumber());
            Integer idType = surveyRiskCase.getIdType() == null ? 1 : surveyRiskCase.getIdType();
            if (idType == 1) {
                template.setNumberTypeStr("身份证");
            } else if (idType == 2) {
                template.setNumberTypeStr("驾驶证");
            } else if (idType == 3) {
                template.setNumberTypeStr("护照号码");
            } else if (idType == 4) {
                template.setNumberTypeStr("其它证件号码");
            }
            template.setClaimsNo(surveyRiskCase.getPolicyNo() == null ? "" : surveyRiskCase.getPolicyNo());
            template.setSafeDate(DateUtils.DateToStr(surveyRiskCase.getInsureTakeTime(), "yyyy年MM月dd日"));
            template.setSurveyUserDate(DateUtils.DateToStr(surveyRiskCase.getDangerTime(), "yyyy年MM月dd日"));
            template.setSurveyItem(surveyRiskCaseInfo.getSurveyItem() == null ? "" : surveyRiskCaseInfo.getSurveyItem());
            template.setDirections(directions);
            StringBuffer directionInfos = new StringBuffer();
            StringBuffer midInfo = new StringBuffer();

            int i = 1;
            Double total = 0D;
            for (SurveyCaseDirectionDto direction : directions) {
                directionInfos.append(i + "、" + direction.getDirectionInfo() + "；");
                total += direction.getEntrustMoney() == null ? 0D : direction.getEntrustMoney();
                i = i + 1;

            }
            i = 0;
            for (SurveyCaseDirectionDto direction : directions) {
//                if (direction.getSurveyCaseDirectionFiles().size() == 0){
//                    continue;
//                }
//                midInfo.append(i + "、" + direction.getDirectionName() + direction.getSurveyCaseDirectionFiles().size() + "份" + "；");
//                i = i + 1;

                if (direction.getSurveyReason() != null && !"".equals(direction.getSurveyReason())) {
                    i = i + 1;
                    midInfo.append(i + "、" + direction.getSurveyReason() + "\n");
                }
            }
            template.setTotalMoney(total.toString());
            template.setDirectionInfo(directionInfos.toString());
            if (reportCompletion != null) {
                surveyRiskCaseInfo.setReportCompletion(reportCompletion);
            }
            template.setDirectionResult(surveyRiskCaseInfo.getReportCompletion() == null ? "" : surveyRiskCaseInfo.getReportCompletion());
            template.setFileMidInfo(midInfo.toString());


//            String names = "";
//            for (SurveyInvestigatorCase aCase : cases) {
//                names = names.concat(aCase.getSurveyUserName()) + ",";
//            }
//
//            // 如果只有一个调查员 则随机拼接一个有执证编号的调查员
//            if (cases.size() == 1) {
//                SurveyInvestigator appendUser = null;
//                paramMap = new HashMap<String, Object>();
//                paramMap.put("register", "yes");
//                List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.selectByMap(paramMap);
//                if (surveyInvestigators.size() > 0) {
//                    List<SurveyInvestigator> collect = surveyInvestigators.stream().filter(p -> cases.get(0).getSurveyOrgId().equals(p.getOrgId()) && !cases.get(0).getSurveyUserId().equals(p.getUserId())).collect(Collectors.toList());
//                    if (collect.size() > 0) {
//                        appendUser = collect.get(0);
//                    } else {
//                        long random = RandomIDUtil.getRandom(0, surveyInvestigators.size() - 1);
//                        appendUser = surveyInvestigators.get((int) random);
//                    }
//                }
//                if (appendUser != null) {
//                    names = names.concat(appendUser.getRealName() + "(执业证:" + appendUser.getRegisterNo() + ")") + ",";
//                }
//            }
//
//            if (names.length() > 0) {
//                template.setSurveyUserName(names.substring(0, names.length() - 1));
//            } else {
//                template.setSurveyUserName("");
//            }

            //至少两个调查员  两个调查员必须都有执业证, 优先以原始调查员、              其次同机构调查员、最后随机调查员
            List<Long> qfrUsers = new ArrayList<>();
            paramMap = new HashMap<String, Object>();
            paramMap.put("register", "yes");
            List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.selectByMap(paramMap);
            List<SurveyInvestigator> collect = surveyInvestigators.stream().filter(f -> !f.getUserId().toString().equals(surveyRiskCaseInfo.getBelongUserId().toString())).collect(Collectors.toList());//过滤掉当前案件审核人
            cases.forEach(k -> {
                SurveyInvestigator surveyInvestigator = collect.stream().filter(e -> e.getUserId().toString().equals(k.getSurveyUserId().toString())).findFirst().orElse(null);
                if (Objects.nonNull(surveyInvestigator)) {
                    qfrUsers.add(surveyInvestigator.getUserId());//优先匹配原始调查员
                }
            });
            if (qfrUsers.size() < 2){//同机构匹配 + 随机  并且 随机调查员不能重复
                List<Long> surveyOrgIds = cases.stream().map(SurveyInvestigatorCase::getSurveyOrgId).collect(Collectors.toList());
                List<SurveyInvestigator> collect2 = collect.stream().filter(p -> surveyOrgIds.contains(p.getOrgId())).collect(Collectors.toList());//同机构的所有调查员
                Collections.shuffle(collect2);
                collect2.forEach(e -> {
                    if (!qfrUsers.contains(e.getUserId()) && qfrUsers.size() < 2){
                        qfrUsers.add(e.getUserId());
                    }
                });
            }
            if (qfrUsers.size() < 2){//随机匹配
                Collections.shuffle(collect);
                collect.forEach(e -> {
                    if (!qfrUsers.contains(e.getUserId()) && qfrUsers.size() < 2){
                        qfrUsers.add(e.getUserId());
                    }
                });
            }

            String names = "";
            for (Long qfrUser : qfrUsers) {
                SurveyInvestigator surveyInvestigator = surveyInvestigators.stream().filter(p -> p.getUserId().toString().equals(qfrUser.toString())).findFirst().orElse(null);
                names = names.concat(surveyInvestigator.getRealName() + "(执业证:" + surveyInvestigator.getRegisterNo() + ")") + ",";
            }
            if (names.length() > 0) {
                template.setSurveyUserName(names.substring(0, names.length() - 1));
            } else {
                template.setSurveyUserName("");
            }



            if (surveyRiskCaseInfo.getEntrustReportStartDate() == null) {
                surveyRiskCaseInfo.setEntrustReportStartDate(new Date());
            }
            template.setUserSignDate(DateUtils.DateToStr(surveyRiskCaseInfo.getEntrustReportStartDate(), "yyyy年MM月dd日"));


            String dateStr = DateUtils.DateToStr(surveyRiskCaseInfo.getLefanReportDate() == null ? new Date() : surveyRiskCaseInfo.getLefanReportDate(), "yyyy年MM月dd日");
            template.setUserSignDate(dateStr);


            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyRiskCaseInfo.getBelongUserId());
            if (surveyInvestigator != null && StringUtils.isNotEmpty(surveyInvestigator.getRegisterNo())) {
                template.setSurveyManagerName(surveyRiskCaseInfo.getBelongUserName() + "(执业证:" + surveyInvestigator.getRegisterNo() + ")");
            } else {
                template.setSurveyManagerName(surveyRiskCaseInfo.getBelongUserName());
            }
            template.setManagerSignDate(DateUtils.DateToStr(new Date(), "yyyy年MM月dd日"));
//            if (userInfo != null){
//                template.setSurveyManagerName(userInfo.getUserName());
//                template.setManagerSignDate(DateUtils.DateToStr(new Date(),"yyyy年MM月dd日"));
//            }else{
//                template.setSurveyManagerName("");
//                template.setManagerSignDate("");
//            }
            templateData.setTemplateDataZD(template);
        } else if (model.getId().intValue() == 4) {
            TemplateDataZH template = new TemplateDataZH();
            template.setClaimsNo(surveyRiskCase.getClaimsNo() == null ? "" : surveyRiskCase.getClaimsNo());
            template.setEntrustOrgName(surveyRiskCase.getEntrustOrgName());
            template.setTrusteeOrgName("江苏乐凡保险公估有限公司");
            template.setEntrustUserName(surveyRiskCase.getEntrustUserName());
            template.setEntrustDate(DateUtils.DateToStr(surveyRiskCase.getEntrustTime(), "yyyy年MM月dd日"));
            if (surveyRiskCaseInfo.getEntrustReportStartDate() == null) {
                surveyRiskCaseInfo.setEntrustReportStartDate(new Date());
            }
            template.setEndDate(DateUtils.DateToStr(surveyRiskCaseInfo.getEntrustReportStartDate(), "yyyy年MM月dd日"));
            template.setPolicyNo(surveyRiskCase.getPolicyNo());
            template.setSafeDate(DateUtils.DateToStr(surveyRiskCase.getInsureTime(), "yyyy年MM月dd日"));
            template.setSurveyPerson(surveyRiskCase.getSurveyPerson());
            template.setSurveyReason(surveyRiskCaseInfo.getSurveyInfo() == null ? "" : surveyRiskCaseInfo.getSurveyInfo());
            template.setSurveyDemand(surveyRiskCaseInfo.getSurveyItem() == null ? "" : surveyRiskCaseInfo.getSurveyItem());
            template.setDirections(directions);
            String address = "";
            String infos = "";
            int i = 1;
            for (SurveyCaseDirectionDto direction : directions) {
                address = address.concat(i + "、").concat(direction.getDirectionName()).concat(";");
                if (direction.getDirectionInfo() != null) {
                    infos = infos.concat(direction.getDirectionInfo()) + ";";
                }
                direction.setDirectionName(i + "、" + direction.getDirectionName());
                i = i + 1;
            }
            if (address.length() > 0) {
                template.setDirectionName(address.substring(0, address.length() - 1));
            } else {
                template.setDirectionName("");
            }
            template.setDirectionInfo(infos);
            if (reportCompletion != null) {
                surveyRiskCaseInfo.setReportCompletion(reportCompletion);
            }
            template.setDirectionResult(surveyRiskCaseInfo.getReportCompletion() == null ? "" : surveyRiskCaseInfo.getReportCompletion());
            if (surveyRiskCaseInfo.getUseLetterInfo() == null) {
                surveyRiskCaseInfo.setUseLetterInfo(0);
            }
            if (surveyRiskCaseInfo.getUseLetterInfo() == 0) {
                template.setIsUseLetter("否");
            } else {
                template.setIsUseLetter("是");
            }
            StringBuffer midInfo = new StringBuffer();
            i = 0;
            for (SurveyCaseDirectionDto direction : directions) {
//                if (direction.getSurveyCaseDirectionFiles().size() == 0){
//                    continue;
//                }
//                midInfo.append(i + "、" + direction.getDirectionName() + direction.getSurveyCaseDirectionFiles().size() + "份" + "；");
//                i = i + 1;
                if (direction.getSurveyReason() != null && !"".equals(direction.getSurveyReason())) {
                    i = i + 1;
                    midInfo.append(i + "、" + direction.getSurveyReason() + "");
                }
            }
            template.setFileMidSize(midInfo.toString());
            template.setDirectionNameSize(directions.size() + "");


//            String names = "";
//            for (SurveyInvestigatorCase aCase : cases) {
//                names = names.concat(aCase.getSurveyUserName()) + ",";
//            }
//
//            // 如果只有一个调查员 则随机拼接一个有执证编号的调查员
//            if (cases.size() == 1) {
//                SurveyInvestigator appendUser = null;
//                paramMap = new HashMap<String, Object>();
//                paramMap.put("register", "yes");
//                List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.selectByMap(paramMap);
//                if (surveyInvestigators.size() > 0) {
//                    List<SurveyInvestigator> collect = surveyInvestigators.stream().filter(p -> cases.get(0).getSurveyOrgId().equals(p.getOrgId()) && !cases.get(0).getSurveyUserId().equals(p.getUserId())).collect(Collectors.toList());
//                    if (collect.size() > 0) {
//                        appendUser = collect.get(0);
//                    } else {
//                        long random = RandomIDUtil.getRandom(0, surveyInvestigators.size() - 1);
//                        appendUser = surveyInvestigators.get((int) random);
//                    }
//                }
//                if (appendUser != null) {
//                    names = names.concat(appendUser.getRealName() + "(执业证:" + appendUser.getRegisterNo() + ")") + ",";
//                }
//            }
//
//            if (names.length() > 0) {
//                template.setSurveyUserName(names.substring(0, names.length() - 1));
//            } else {
//                template.setSurveyUserName("");
//            }


            //至少两个调查员  两个调查员必须都有执业证, 优先以原始调查员、              其次同机构调查员、最后随机调查员
            List<Long> qfrUsers = new ArrayList<>();
            paramMap = new HashMap<String, Object>();
            paramMap.put("register", "yes");
            List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.selectByMap(paramMap);
            List<SurveyInvestigator> collect = surveyInvestigators.stream().filter(f -> !f.getUserId().toString().equals(surveyRiskCaseInfo.getBelongUserId().toString())).collect(Collectors.toList());//过滤掉当前案件审核人
            cases.forEach(k -> {
                SurveyInvestigator surveyInvestigator = collect.stream().filter(e -> e.getUserId().toString().equals(k.getSurveyUserId().toString())).findFirst().orElse(null);
                if (Objects.nonNull(surveyInvestigator)) {
                    qfrUsers.add(surveyInvestigator.getUserId());//优先匹配原始调查员
                }
            });
            if (qfrUsers.size() < 2){//同机构匹配 + 随机  并且 随机调查员不能重复
                List<Long> surveyOrgIds = cases.stream().map(SurveyInvestigatorCase::getSurveyOrgId).collect(Collectors.toList());
                List<SurveyInvestigator> collect2 = collect.stream().filter(p -> surveyOrgIds.contains(p.getOrgId())).collect(Collectors.toList());//同机构的所有调查员
                Collections.shuffle(collect2);
                collect2.forEach(e -> {
                    if (!qfrUsers.contains(e.getUserId()) && qfrUsers.size() < 2){
                        qfrUsers.add(e.getUserId());
                    }
                });
            }
            if (qfrUsers.size() < 2){//随机匹配
                Collections.shuffle(collect);
                collect.forEach(e -> {
                    if (!qfrUsers.contains(e.getUserId()) && qfrUsers.size() < 2){
                        qfrUsers.add(e.getUserId());
                    }
                });
            }

            String names = "";
            for (Long qfrUser : qfrUsers) {
                SurveyInvestigator surveyInvestigator = surveyInvestigators.stream().filter(p -> p.getUserId().toString().equals(qfrUser.toString())).findFirst().orElse(null);
                names = names.concat(surveyInvestigator.getRealName() + "(执业证:" + surveyInvestigator.getRegisterNo() + ")") + ",";
            }
            if (names.length() > 0) {
                template.setSurveyUserName(names.substring(0, names.length() - 1));
            } else {
                template.setSurveyUserName("");
            }


            if (surveyRiskCaseInfo.getEntrustReportStartDate() == null) {
                surveyRiskCaseInfo.setEntrustReportStartDate(new Date());
            }
            template.setUserSignDate(DateUtils.DateToStr(surveyRiskCaseInfo.getEntrustReportStartDate(), "yyyy年MM月dd日"));

            String dateStr = DateUtils.DateToStr(surveyRiskCaseInfo.getLefanReportDate() == null ? new Date() : surveyRiskCaseInfo.getLefanReportDate(), "yyyy年MM月dd日");
            template.setUserSignDate(dateStr);
            template.setAnnexCom(surveyRiskCase.getDepartmentName() == null ? "" : surveyRiskCase.getDepartmentName());
            template.setAnnexCity("");
            template.setAnnexType("");
            template.setAnnexPrice("");
            templateData.setTemplateDataZH(template);
        }
        //调查员签名URL
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectLastCommitInfo(surveyInfoId);
        SurveyUserSign surveyUserSign = surveyUserSignMapper.selectByUserId(surveyInvestigatorCase.getSurveyUserId());
        if (surveyUserSign != null) {
            templateData.setSurveyUserImg(surveyUserSign.getSignImg());
        }
        //风控人员签名
        surveyUserSign = surveyUserSignMapper.selectByUserId(surveyRiskCaseInfo.getBelongUserId());
        if (surveyUserSign != null) {
            templateData.setLfUserImg(surveyUserSign.getSignImg());
        }
        return templateData;
    }

    @ApiMethod(needLogin = false, descript = "获取模板数据", value = "get-survey-template-data")
    @Override
    public ApiResponse getTemplateData(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        String reportCompletion = apiRequest.getString("reportCompletion");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        TemplateData templateData = getTemplateData(userInfo, surveyInfoId, true, reportCompletion);
        if (templateData == null) {
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, templateData);
    }

    @ApiMethod(needLogin = false, descript = "获取审核报告的页面信息", value = "backend-survey-get-opr-info")
    @Override
    public ApiResponse getOprInfo(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        String btnCode = apiRequest.getString("btnCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
//        if ("survey-report-opr".equals(btnCode) || "org5".equals(btnCode)){//机构复核人员
        if ("org5".equals(btnCode)) {//机构复核人员
            userInfo = null;//签发人为空
        }
        SurveyOprInfoDTO info = new SurveyOprInfoDTO();
        TemplateData templateData = getTemplateData(userInfo, surveyInfoId, true, null);
        info.setTemplateData(templateData);
        List<SurveyFileInfoDTO> files = surveyCaseFileMapper.getSurveyFileInfo(surveyInfoId);
        info.setFiles(files);
        info.setDirections(templateData.getDirections());
        info.setSurveyRiskInfo(templateData.getSurveyRiskCaseInfo());

        SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(apiRequest.getLong("assignOrgCaseId"));
        info.setSurveyAssignOrg(surveyAssignOrg);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, info);
    }

    @Override
    public ApiResponse saveOprInfo(ApiRequest apiRequest) {


        return null;
    }

    //计算案件的案件时效
    /*private Map<String, Object> surveyCaseUserDays(int caseUserState, String assDate, String commitDate, String endDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (caseUserState == 4) { //说明是已提交 -- （提交时间-分派时间 计算时效）
            if (commitDate == null || "".equals(commitDate)){
                commitDate = endDate;
            }
            int days = GetWorkDay.calLeaveDays(assDate, commitDate);
            days = Math.abs(days);
            if(commitDate.compareTo(endDate) > 0){ //如果“提交时间” 超过“截止时间” 为红色
                map.put("efficiencyState","时效"+days+"天");
                map.put("efficiencyStateColor","#e51c23");
            }else{
                map.put("efficiencyState","时效"+days+"天");
                map.put("efficiencyStateColor","#3ba9ff");
            }
        }else{//未提交
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            if(endDate.compareTo(dateString) > 0){ //未超时
                int days = GetWorkDay.calLeaveDays(dateString,endDate);
                map.put("efficiencyState","剩余"+days+"天");
                map.put("efficiencyStateColor","#3ba9ff");//蓝色
                if(days <= 2 && days >0 ){ //
                    map.put("efficiencyStateColor","#ff9800");//黄色
                }
            }else{
                int days = GetWorkDay.calLeaveDays(endDate,dateString);
                map.put("efficiencyState","超时"+days+"天");
                map.put("efficiencyStateColor","#e51c23");
            }
        }
        return map;
    }*/


    /**
     * 修改任务类型（包含机构任务类型、案件任务类型）
     */
    private Map<String, Object> selectTaskByCaseInfo(Map<String, Object> returnMap, ApiRequest apiRequest, String returnType) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInfoId);
        List<SurveyTaskTypeDto> surveyTaskTypeDtoList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Boolean isHaveAssign = false; //是否分派过机构，或者调查员（决定前端展示是全部任务类型，还是分派指定的任务类型）

        //该案件的所有任务类型
        List<SurveyTaskType> surveyTaskTypeCase = surveyTaskTypeMapper.getSurveyTaskTypeBySurveyInfoId(surveyInfoId);

        if ("updateCaseTaskType".equals(returnType)) { //修改案件任务类型
            //案件的所有方向
            map = new HashMap<>();
            map.put("surveyInfoId", surveyInfoId);
            List<SurveyCaseDirection> caseDirections = surveyCaseDirectionMapper.list(map);
            //获取领域对应的所有任务类型
            map = new HashMap<>();
            map.put("businessTypeId", surveyRiskCaseInfo.getSurveyBusId());
            List<SurveyBusinessTaskType> surveyBusinessTaskTypeAll = surveyBusinessTaskTypeMapper.list(map);

            for (int i = 0; i < surveyBusinessTaskTypeAll.size(); i++) {
                SurveyTaskTypeDto dto = new SurveyTaskTypeDto();
                dto.setSurveyBusinessTaskType(surveyBusinessTaskTypeAll.get(i));
                dto.setSelectType(1);//默认未分派
                for (int j = 0; j < surveyTaskTypeCase.size(); j++) {
                    if (surveyBusinessTaskTypeAll.get(i).getTaskInfoId() == surveyTaskTypeCase.get(j).getTaskId()) { //已分配
                        dto.setSelectType(2);//已分派，没有做方向 (已分配：默认2)
                        for (int z = 0; z < caseDirections.size(); z++) {
                            if (caseDirections.get(z).getTaskId() == surveyTaskTypeCase.get(j).getTaskId()) { //做方向了
                                dto.setSelectType(3);//已分派，做了方向
                            }
                        }
                        break;
                    }
                }
                surveyTaskTypeDtoList.add(dto);
            }
            map = new HashMap<>();
            map.put("surveyInfoId", surveyInfoId);
            List<SurveyAssignOrgDto> surveyAssigns = surveyAssignOrgMapper.list(map);
            if (surveyAssigns.size() > 0) {
                isHaveAssign = true;
            }
        } else if ("updateOrgTaskType".equals(returnType)) {//机构任务类型
            isHaveAssign = true;
            Long assignOrgId = apiRequest.getLong("assignOrgId");
            //机构案件的所有方向
            map = new HashMap<>();
            map.put("surveyInfoId", surveyInfoId);
            map.put("surveyAssorgCaseId", assignOrgId);
            List<SurveyCaseDirection> caseDirections = surveyCaseDirectionMapper.list(map);
            //获取该机构的任务类型
            map = new HashMap<>();
            map.put("surveyInfoId", surveyInfoId);
            map.put("surveyAssignOrgId", assignOrgId);
            List<SurveyAssignOrgType> assignOrgTypes = surveyAssignOrgTypeMapper.list(map);
            for (int i = 0; i < surveyTaskTypeCase.size(); i++) {
                SurveyTaskTypeDto dto = new SurveyTaskTypeDto();
                dto.setSurveyTaskType(surveyTaskTypeCase.get(i));
                dto.setSelectType(1);//默认未分派
                for (int j = 0; j < assignOrgTypes.size(); j++) {
                    if (surveyTaskTypeCase.get(i).getTaskId() == assignOrgTypes.get(j).getTaskId()) { //已分配
                        dto.setSelectType(2);//已分派，没有做方向 (已分配：默认2)
                        for (int z = 0; z < caseDirections.size(); z++) {
                            if (caseDirections.get(z).getTaskId() == assignOrgTypes.get(j).getTaskId()) { //做方向了
                                dto.setSelectType(3);//已分派，做了方向
                            }
                        }
                        break;
                    }
                }
                surveyTaskTypeDtoList.add(dto);
            }

        }
        returnMap.put("surveyTaskTypeDtoList", surveyTaskTypeDtoList);
        returnMap.put("isHaveAssign", isHaveAssign);
        returnMap.put("taskRemark", null);
        return returnMap;
    }

    @ApiMethod(needLogin = false, descript = "获取案件指导信息", value = "backend-survey-risk-case-guide-info")
    @Override
    public ApiResponse getGuideInfo(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("id");
        SurveyRiskCaseGuide surveyRiskCaseGuide = surveyRiskCaseGuideMapper.selectBySurveyInfoId(surveyInfoId);
        if (surveyRiskCaseGuide == null) {
            surveyRiskCaseGuide = new SurveyRiskCaseGuide();
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInfoId).getSurveyId());
            surveyRiskCaseGuide.setDisease(surveyRiskCase.getHzConfirmDisease());
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, surveyRiskCaseGuide);
    }

    private void updateSurveyRiskInfoFinalUser(Long entrustOrgId, Long surveyOrgId, Long oprUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("consignorId", entrustOrgId);
        map.put("franchiseeId", surveyOrgId);
        SurveyRiskInfoFinalUser surveyRiskInfoFinalUser = surveyRiskInfoFinalUserMapper.selectByInfo(map);
        if (surveyRiskInfoFinalUser != null) {
            surveyRiskInfoFinalUser.setUserId(oprUser);
            surveyRiskInfoFinalUser.setUserName(userInfoMapper.selectByPrimaryKey(oprUser).getUserName());
            surveyRiskInfoFinalUserMapper.updateByPrimaryKey(surveyRiskInfoFinalUser);
        } else {
            surveyRiskInfoFinalUser = new SurveyRiskInfoFinalUser();
            surveyRiskInfoFinalUser.setFranchiseeId(surveyOrgId);
            surveyRiskInfoFinalUser.setFranchiseeName(surveyFranchiseeMapper.selectByPrimaryKey(surveyOrgId).getName());
            surveyRiskInfoFinalUser.setConsignorId(entrustOrgId);
            surveyRiskInfoFinalUser.setConsignorName(surveyConsignorMapper.selectByPrimaryKey(entrustOrgId).getName());
            surveyRiskInfoFinalUser.setUserId(oprUser);
            surveyRiskInfoFinalUser.setUserName(userInfoMapper.selectByPrimaryKey(oprUser).getUserName());
            surveyRiskInfoFinalUser.setDeleteFlag(0);
            surveyRiskInfoFinalUserMapper.insert(surveyRiskInfoFinalUser);
        }
    }


    public void score(Long surveyInfoId) {
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInfoId);


        //首先把调查员做的方向总和的分值同步到调查员案件表; 2、新增需求：2020年9月14日，加入“无效方向”（王伟）
        Map<String, Object> map = new HashMap<>();
        map.put("surveyInfoId", surveyInfoId);
        surveyInvestigatorCaseMapper.scores(map);

        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
//        if (surveyRiskCaseInfo.getEntrustOrgId().intValue() == 105 || surveyRiskCaseInfo.getEntrustOrgId().intValue() == 82) {
        if (surveyConsignor.getOrgAttr() == 2) {
            //阳性奖励分值（第一个发现阳性的调查员按照规则奖励）
            map = new HashMap<String, Object>();
            map.put("surveyInfoId", surveyInfoId);
            List<SurveyInvestigatorCaseDto> cases = surveyInvestigatorCaseMapper.getSuns(map);//所有发现阳性的调查员案件列表  按照发现阳性的时间升序
            if (cases.size() > 0) {
                SurveyInvestigatorCaseDto item = cases.get(0);// 第一个阳性发现阳性的调查员
                //奖励分值
                map = new HashMap<String, Object>();
                map.put("surveyInfoId", surveyInfoId);
//                map.put("surveyOrgId",item.getSurveyOrgId());
                map.put("surveyAssorgCaseId", item.getSurveyAssorgCaseId());
                map.put("invalidState", 0);//是否无效方向（0：有效；1、无效）
                List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.list(map);//该机构下所有调查员所做方向的总数量

                SurveyAssignOrgDto assignOrgDto = surveyAssignOrgMapper.selectByPrimaryKey(item.getSurveyAssorgCaseId());
//                Map<String,Object> paramMap =  new HashMap<String,Object>();
//                paramMap.put("surveyInfoId",surveyInfoId);
//                paramMap.put("orgId",assignOrgDto.getSurveyOrgId());
//                List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.selectDirections(paramMap);

                Double socre = 0D;
                if (assignOrgDto == null) {
                    map.put("surveyInfoId", surveyInfoId);
                    map.put("surveyOrgId", item.getSurveyOrgId());
                    assignOrgDto = surveyAssignOrgMapper.selectByOne(map);
                }
                if (assignOrgDto.getServicesId().intValue() == 13) {//全案
                    int num = directions.size();
                    if (num == 1) socre = 20D;
                    if (num == 2) socre = 18D;
                    if (num == 3) socre = 16D;
                    if (num == 4) socre = 14D;
                    if (num == 5) socre = 12D;
                    if (num == 6) socre = 10D;
                    if (num == 7) socre = 8D;
                    if (num == 8) socre = 5D;
                    if (num > 8) socre = 5D;
                } else {//单点
                    socre = 5D;
                }
                if (item.getScore() == null) {
                    item.setScore(0D);
                }
                Double rate = item.getOverdueAgingRate() == null ? 1D : item.getOverdueAgingRate();
                item.setAssessScore(item.getAssessScore() + socre);
                item.setAssessSunScore(socre);
                item.setScore(item.getAssessScore() * rate);
                item.setScoreSun(item.getAssessSunScore() * rate);//阳性分
                surveyInvestigatorCaseMapper.updateByPrimaryKey(item);


                //如果是复审人员确认之后 超过24小时提交的方向  则不计算分值。 减去方向的分值总和
                //是否存在复审人员手动标记
                map = new HashMap<String, Object>();
                map.put("surveyInfoId", surveyInfoId);
                List<SurveyInvestigatorCaseDto> reviewSuns = surveyInvestigatorCaseMapper.getReviewSuns(map);
                Boolean have = reviewSuns.size() > 0 ? true : false;
                //如果存在。则取复审人员最新标记的时间。
                //如果不存在。则取第一个有阳性调查员。平台复审通过的时间
                Date time = null;
                if (have) {
                    time = reviewSuns.get(0).getSunTime();
                } else {
                    map.put("surveyInfoId", surveyInfoId);
                    map.put("search", 99);
                    map.put("order", 99);
                    List<SurveyAssignOrgDto> orgs = surveyAssignOrgMapper.list(map);
                    if (orgs.size() > 0) {
                        time = orgs.get(0).getReviewTime();
                    }
                }
                if (time != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(time);
                    calendar.add(Calendar.HOUR_OF_DAY, 24);
                    Date afterTime = calendar.getTime();
                    //这个时间之后录入的方向 。 不计算调查员分值。
                    map.put("surveyInfoId", surveyInfoId);
                    map.put("afterTime", afterTime);
                    map.put("surveyInvestigatorCaseId", item.getId());
                    map.put("invalidState", 0);//是否无效方向（0：有效；1、无效）
                    List<SurveyCaseDirection> list = surveyCaseDirectionMapper.afterScores(map);
                    for (SurveyCaseDirection surveyCaseDirection : list) {
                        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInvestigatorCaseId());
                        Double score = surveyInvestigatorCase.getScore() - surveyCaseDirection.getScore();
                        surveyInvestigatorCase.setAssessScore(score);
                        surveyInvestigatorCase.setScore(score * rate);
                        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
                    }
                }

            }
        }
    }

    @ApiMethod(needLogin = false, descript = "获取调查回访信息", value = "backend-survey-risk-case-visit-info")
    @Override
    public ApiResponse getVisitInfo(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);

//        Long surveyAssorgCaseId = apiRequest.getLong("surveyAssorgCaseId");
        Long surveyInfoId = apiRequest.getLong("id");
        Map<String, Object> map = new HashMap<>();
//        map.put("surveyAssorgCaseId",surveyAssorgCaseId);
        map.put("surveyInfoId", surveyInfoId);
        SurveyRiskCaseVisit surveyRiskCaseVisit = surveyRiskCaseVisitMapper.selectOne(map);
        if (surveyRiskCaseVisit == null) {
            surveyRiskCaseVisit = new SurveyRiskCaseVisit();
            surveyRiskCaseVisit.setVisitTime(new Date());
            surveyRiskCaseVisit.setVisitPerson(userInfo.getUserName());
            surveyRiskCaseVisit.setSurveyInfoId(surveyInfoId);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, surveyRiskCaseVisit);
    }


    //查询案件类型：1、单点；2、单点+单点；3、全案；4、全案+单点；5、全案+全案
    public Integer findCaseState(SurveyRiskCaseInfo surveyRiskCaseInfo) {
        Integer caseState = 1;
        Long servicesId = surveyRiskCaseInfo.getServicesId();

        Map<String, Object> map = new HashMap<>();
        map.put("surveyInfoId", surveyRiskCaseInfo.getId());
        List<SurveyAssignOrgDto> orgs = surveyAssignOrgMapper.list(map);

        //上游是全案（深度案件） ：1、下游机构数量=1时，是“全案案件”；2、下游机构案件数量>1时；全案数量=1时，是“全案+单点”、全案数量>1时，是“全案+全案”
        if (servicesId == 13) {
            if (orgs.size() == 1) {
                caseState = 3;
            } else {
                int quananSize = 0;
                for (SurveyAssignOrgDto org : orgs) {
                    if (org.getServicesId() == 13) quananSize++;
                }
                if (quananSize == 0) caseState = 3;
                if (quananSize == 1) caseState = 4;
                if (quananSize > 1) caseState = 5;
            }
        }
        //上游是单点（单点案件、契约案件）
        if (servicesId == 11 || servicesId == 12) {
            if (orgs.size() == 1) caseState = 1;
            if (orgs.size() > 1) caseState = 2;
        }

        return caseState;
    }

    @ApiMethod(needLogin = false, descript = "案件分派按查询条件导出", value = "backend-survey-risk-case-info-export")
    public ApiResponse surveyRiskCaseInfoExport(ApiRequest apiRequest) {
        List<SurveyRiskCaseInfoDtoExport> list = surveyRiskCaseInfoMapper.exportByCondition(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, list);
    }

    @ApiMethod(needLogin = false, descript = "相互宝批量处理", value = "list-survey-xhbbatch-risk-case-info")
    @Override
    public ApiResponse xhbBatchList(ApiRequest apiRequest) {
        String exportType = apiRequest.getString("exportType");
        if ("ddDataSettlement".equals(exportType)) {
            List<SurveyCaseXHB> tempData = JSONArray.parseArray(apiRequest.getString("caseList"), SurveyCaseXHB.class);
            String claimsNos = tempData.stream().map(SurveyCaseXHB::getClaimsNo).collect(Collectors.joining(","));
            List<SurveyCaseXHB> data = surveyRiskCaseInfoMapper.selectCaseListByClaimsNos(claimsNos);
            for (SurveyCaseXHB surveyCaseXHB : data) {
                SurveyCaseXHB temp = tempData.stream().filter(p -> p.getClaimsNo().equals(surveyCaseXHB.getClaimsNo())).collect(Collectors.toList()).get(0);
                surveyCaseXHB.setSettlementMoney(temp.getSettlementMoney());
            }
            com.alibaba.fastjson.JSONObject resultJson = new com.alibaba.fastjson.JSONObject();
            resultJson.put("successNum", data.size());
            resultJson.put("successData", data);
            resultJson.put("failNum", tempData.size() - data.size());
            List<SurveyCaseXHB> failData = tempData.stream().filter(m -> !data.stream().map(d -> d.getClaimsNo()).collect(Collectors.toList()).contains(m.getClaimsNo())).collect(Collectors.toList());
            resultJson.put("failData", failData.stream().map(p -> p.getClaimsNo()).collect(Collectors.toList()));
            resultJson.put("successDataSurveyInfoIds", data.stream().map(p -> p.getSurveyInfoId().toString()).collect(Collectors.joining(",")));
            resultJson.put("money", data.stream().mapToDouble(SurveyCaseXHB::getSettlementMoney).sum());
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, resultJson);
        } else {
            List<String> caseList = getMergedStringUsing(apiRequest.getStringList("caseList"));
            List<SurveyRiskCaseInfoDto> xhbBatchList = Collections.emptyList();
            if (caseList.size() > 0) {
                xhbBatchList = surveyRiskCaseInfoMapper.xhbBatchList(apiRequest);
                xhbBatchList.removeIf(next -> !caseList.contains(next.getClaimsNo()));//迭代删除匹配不上的 剩下的是匹配上，列表展示的数据
                List<String> collect = xhbBatchList.parallelStream().map(SurveyRiskCaseInfoDto::getClaimsNo).collect(Collectors.toList()); //得到编号集合结果
                caseList.removeAll(collect);//上传的数据与最终显示的数据取差集  得到失败的
            }
            com.alibaba.fastjson.JSONObject resultJson = new com.alibaba.fastjson.JSONObject();
            resultJson.put("successNum", xhbBatchList.size());
            resultJson.put("successData", xhbBatchList);
            resultJson.put("failNum", caseList.size());
            resultJson.put("failData", caseList);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, resultJson);
        }
    }

    @ApiMethod(needLogin = false, descript = "相互宝批量处理", value = "list-survey-xhbbatch-risk-case-operator")
    @Override
    public ApiResponse xhbBatchListOperator(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        List<String> caseData = apiRequest.getStringList("caseData");
        String passTime = apiRequest.getString("passTime");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(105L);
        for (String id : caseData) {
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(Long.valueOf(id.trim()));
            if (surveyRiskCaseInfo != null) {
                surveyRiskCaseInfo.setSurveyState(28);
                surveyRiskCaseInfo.setSurveyStateName("保司终审通过");
                try {
                    Date passDate = simpleDateFormat.parse(passTime);
                    surveyRiskCaseInfo.setEntrustReportEndDate(passDate);
                    surveyRiskCaseInfo.setAgingDay(AgingDayUtil.riskCaseInfoAgingDay(surveyRiskCaseInfo.getEntrustStartDate(), passDate, surveyConsignor.getEfficiencyAttr()));
                    surveyRiskCaseInfoMapper.updateByPrimaryKeySelective(surveyRiskCaseInfo);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                surveyCaseWorkflowApi.addSurveyCaseWorkflow("保司审核", userInfo, surveyRiskCaseInfo.getEntrustReportStartDate(), surveyRiskCaseInfo.getEntrustReportEndDate(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());
                //案件时效
                surveyCaseWorkflowApi.addSurveyCaseWorkflow("案件时效", userInfo, surveyRiskCaseInfo.getCreateTime(), surveyRiskCaseInfo.getEntrustReportEndDate(), surveyRiskCaseInfo.getId(), surveyRiskCaseInfo.getSurveyId());

                //归档管理
                Map<String, Object> map = new HashMap<>();
                map.put("surveyInfoId", surveyRiskCaseInfo.getId());
                SurveyCaseArchives surveyCaseArchives = surveyCaseArchivesMapper.selectOne(map);
                if (surveyCaseArchives == null) {
                    surveyCaseArchives = new SurveyCaseArchives();
                    SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                    surveyCaseArchives.setSurveyCaseNo(surveyRiskCase.getSurveyCaseNo());
                    surveyCaseArchives.setSurveyNo(surveyRiskCaseInfo.getSurveyNo());
                    surveyCaseArchives.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    surveyCaseArchives.setSurveyId(surveyRiskCaseInfo.getSurveyId());
                    surveyCaseArchives.setCreateBy(userInfo.getUserName());
                    surveyCaseArchives.setCreateTime(new Date());
                    surveyCaseArchives.setDeleteFlag(0);
                    surveyCaseArchives.setArchivesState(0);
                    surveyCaseArchivesMapper.insert(surveyCaseArchives);
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, "success");
    }

    @ApiMethod(needLogin = false, descript = "获取众安时效", value = "info-survey-za-aging-day")
    @Override
    public ApiResponse getZaAgingDay(ApiRequest apiRequest) {
        Map<String, Integer> result = new HashMap<>();
        Integer subServiceId = apiRequest.getInt("subServiceId");
        Integer serviceId = apiRequest.getInt("serviceId");
        String areaCategoriesId = apiRequest.getString("areaCategoriesId");
        Integer entrustOrgId = apiRequest.getInt("entrustOrgId");
        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(entrustOrgId));
        Integer days = 0;
        if (StringUtils.isEmpty(areaCategoriesId)) {
            Integer cityType = apiRequest.getInt("cityType");
            if (cityType == 1) {
                days = 3;
            } else if (cityType == 2) {
                days = 4;
            } else if (cityType == 3) {
                days = 4;
            } else if (cityType == 4) {
                days = 5;
            } else if (cityType == 5) {
                days = 7;
            }
        } else {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("entrustOrgId", entrustOrgId);
            paramMap.put("areaCategoriesId", areaCategoriesId);
            paramMap.put("serviceId", serviceId);
            paramMap.put("subServiceId", subServiceId);//众安的时候存在subServiceId
            days = surveyConsignorEfficiencyModelInfoMapper.getAgingDay(paramMap);
            if (days == null) {
                days = 0;
            }
        }

        result.put("days", days);
        if (surveyConsignor != null) {
            result.put("efficiencyAttr", surveyConsignor.getEfficiencyAttr());
        } else {
            result.put("efficiencyAttr", 1);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, result);
    }

    private static List<String> getMergedStringUsing(List<String> strings) {
        List<String> resultList = new ArrayList();
        for (String string : strings) {
            if (!string.isEmpty()) {
                resultList.add(string.trim());
            }
        }
        return resultList;
    }

    @ApiMethod(needLogin = false, descript = "获取保司审终邮箱相关信息", value = "get-entrust-end-info")
    @Override
    public ApiResponse getEntrustEndInfo(ApiRequest apiRequest) {
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(apiRequest.getLong("id"));
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
        SurveyEmailInfo surveyEmailInfo = new SurveyEmailInfo();
        SurveyEmailInfoOrg surveyEmailInfoOrg = surveyEmailInfoOrgMapper.selectByEntrustOrgId(surveyRiskCaseInfo.getEntrustOrgId());
        if (surveyEmailInfoOrg != null) {
            surveyEmailInfo = surveyEmailInfoMapper.selectByPrimaryKey(surveyEmailInfoOrg.getEmailInfoId());//发件箱
        }
        if (surveyEmailInfo == null) {
            surveyEmailInfo = new SurveyEmailInfo();
        }
        SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByUserId(surveyRiskCase.getEntrustUserId());
        if (surveyConsigner != null) {
            surveyEmailInfo.setToEmailAddress(surveyConsigner.getEmail());//收件箱
            surveyEmailInfo.setMakeEmail(surveyConsigner.getMakeEmails());//抄送邮箱
        }
        //保司发邮件。
        surveyEmailInfo.setSend(false);
        if (surveyConsignor != null) {
            if (surveyConsignor.getOrgAttr() == 1) {
                if (!StringUtils.isEmpty(surveyEmailInfo.getEmailAddress())) {
                    surveyEmailInfo.setSend(true);
                }
            }
            surveyEmailInfo.setMaxSize(surveyConsignor.getAttrMaxSize());
        }
        surveyEmailInfo.setSurveyRiskCase(surveyRiskCase);
        surveyEmailInfo.setSurveyRiskCaseInfo(surveyRiskCaseInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, surveyEmailInfo);
    }

    @ApiMethod(needLogin = false, descript = "提交报告结论api", value = "entrust-survey-risk-commit-report")
    @Override
    public ApiResponse commitReport(ApiRequest apiRequest) {
        Long handleId = apiRequest.getLong("handleId");//众安案件id
        Long surveyInvId = apiRequest.getLong("surveyInvId");//调查员id

        String conclusion = apiRequest.getString("conclusion");//报告结论

        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByHandleId(handleId);
        if (surveyRiskCaseInfo == null) {
            return new ApiResponse(ApiMsgEnum.FAIL, 0, "乐凡案件未找到！");
        }
        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyInvId);
        if (surveyInvestigator == null) {
            return new ApiResponse(ApiMsgEnum.FAIL, 0, "调查员找不到！");
        }
        SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectBySurveyInfoIdAndUserId(surveyRiskCaseInfo.getId(), surveyInvId);
        if (surveyInvestigatorCase == null) {
            return new ApiResponse(ApiMsgEnum.FAIL, 0, "fail！");
        }

        String directions = apiRequest.getString("directions");
        JSONArray.parseArray(directions);

        surveyInvestigatorCase.setIsDirectionSuccess(1);
//        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);//已完成全部方向

        //什么时候是已提交  如果提交报告的时候是已提交 那么方向不能编辑了
        surveyInvestigatorCase.setSurveyState(4);
        surveyInvestigatorCase.setSurveyStateName("已提交");
        surveyInvestigatorCase.setCreportDate(new Date());

        //主调查员提交报告 生成报告结论
        if (StringUtils.isNotBlank(conclusion)) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(surveyInvId);
            TemplateData templateData = getTemplateData(userInfo, surveyRiskCaseInfo.getId(), true, conclusion);
            SurveyModelInfo model = templateData.getModel();
            String modelPath = null;
            if ("dev".equals(surveySettingSource)) {//如果是本低环境 则模板路径 不取数据库配置路径
                modelPath = "F:\\templete";
            } else {
                modelPath = model.getModelPath();
            }
            try {
                String generateReportPath = WordUtil.generateReport(templateData, model.getId().intValue(), model.getModelName(), generateFilePath.concat("/").concat(templateData.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/report"), templateData.getReportName(), modelPath);
                if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath) || "/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                    if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath)) {
                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/product/ddr/cno/", surveyFilePathSftp.concat("/product/ddr/cno/"));
                    } else if ("/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/test/ddr/cno/", surveyFilePathSftp.concat("/test/ddr/cno/"));
                    }
                }
                apiRequest.put("generateReportPath", generateReportPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            apiRequest.put("id", surveyInvestigatorCase.getId());
            apiRequest.put("reportCompletion", conclusion);
            apiRequest.put("operatorId", userInfo.getUserId());
            ApiResponse apiResponse = backendSurveyInvestigatorCaseApi.commitReport(apiRequest);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(needLogin = false, descript = "同步暖哇案件", value = "backend-survey-case-nw-async")
    @Override
    public ApiResponse asyncNwRisk(ApiRequest apiRequest) {
        Long riskInfoId = apiRequest.getLong("riskInfoId");
        SurveyRiskCaseInfoDto surveyRiskCaseInfoDto = surveyRiskCaseInfoMapper.selectByPrimaryKey(riskInfoId);
        if (surveyRiskCaseInfoDto == null || surveyRiskCaseInfoDto.getHandleId() == null) {
            return new ApiResponse(ApiMsgEnum.FAIL, 0, "狄大人系统中未找到对应的案件！");
        }

        TestGateway testGateway = new TestGateway();
        testGateway.setHandleId(surveyRiskCaseInfoDto.getHandleId().toString());
        try {
            String rsp = testGateway.operate(appkey, secretkey, cprivatekey, spublickey, url);
            if (StringUtils.isNotBlank(rsp)) {
                com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(rsp);
                if ("200".equals(jsonObject.getString("code"))) {
                    com.alibaba.fastjson.JSONObject result = jsonObject.getJSONObject("result");
                    syncRisk(result);
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                } else {
                    String message = jsonObject.getString("message");
                    return new ApiResponse(ApiMsgEnum.FAIL, 0, message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL, 0, "同步接口异常!");
        }

        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    //同步方法
    public void syncRisk(com.alibaba.fastjson.JSONObject jsonObject) {
        Long handleId = jsonObject.getLong("handleId");
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByHandleId(handleId);
        if (surveyRiskCaseInfo == null) return;
        String assignTaskTime = jsonObject.getString("assignTaskTime");//任务分配时间
        //任务列表
        List<InvestigateTask> investigateTaskList = JSONArray.parseArray(jsonObject.getString("investigateTaskList"), InvestigateTask.class);
        for (InvestigateTask investigateTask : investigateTaskList) {
            String investigatorAccount = investigateTask.getThirdPartyAccount();
            if (StringUtils.isNotBlank(investigatorAccount)) {
                investigateTask.setAssignTaskTime(assignTaskTime);
                assignSurveyInv(surveyRiskCaseInfo, investigateTask);
            }
        }
        //实施列表
        List<InvestigateExecute> investigateExecuteList = JSONArray.parseArray(jsonObject.getString("investigateExecuteList"), InvestigateExecute.class);
        if (investigateExecuteList != null && investigateExecuteList.size() > 0) {
            for (InvestigateExecute investigateExecute : investigateExecuteList) {
                List<InvestigateExecuteDetail> executeDetailList = investigateExecute.getExecuteDetailList();//实施详情列表
                for (InvestigateExecuteDetail investigateExecuteDetail : executeDetailList) {
                    String isInvalid = investigateExecuteDetail.getIsInvalid();
                    String investigatorAccount = investigateExecuteDetail.getThirdPartyAccount();
                    // 1.未删除的实施
                    if (StringUtils.isNotBlank(investigatorAccount)) {
                        Long userId;
                        try {
                            userId = Long.parseLong(investigatorAccount);
                        } catch (NumberFormatException e) { //绑定的不是我们的调查员 for example：： test001
                            continue;
                        }
                        boolean deleteFlag = !"N".equals(isInvalid);
                        SurveyInvestigatorCase investigatorCase = surveyInvestigatorCaseMapper.selectBySurveyInfoIdAndUserId(surveyRiskCaseInfo.getId(), userId);
                        if (investigatorCase != null) { // 调查员案件不存在  则同步一下机构与调查员案件 然后添加实施
                            if (investigatorCase.getCreportDate() == null) {
                                //主调查员提交报告 生成报告结论
                                String conclusion = jsonObject.getString("investigateConclusionDetail");
                                addDirections(surveyRiskCaseInfo, investigatorCase, userId, investigateExecute, investigateExecuteDetail, deleteFlag, conclusion);
                            }
                        }
                    }
                }
            }
        }
    }

    public void assignSurveyInv(SurveyRiskCaseInfo surveyRiskCaseInfo, InvestigateTask investigateTask) {
        Long userId;
        try {
            userId = Long.parseLong(investigateTask.getThirdPartyAccount());
        } catch (NumberFormatException e) { //绑定的不是我们的调查员 for example：： test001
            e.printStackTrace();
            return;
        }

        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userId);
        if (surveyInvestigator == null) {
            return;
        }

        SurveyInvestigatorCase invCase = surveyInvestigatorCaseMapper.selectBySurveyInfoIdAndUserId(surveyRiskCaseInfo.getId(), userId);
        if (invCase != null) {
            if ("Y".equals(investigateTask.getIsMainInvestigator())) {
                invCase.setSurveyUserType(1);
                surveyInvestigatorCaseMapper.updateByPrimaryKey(invCase);
            }
            surveyRiskCaseInfo.setOrgAssign(1);
            surveyRiskCaseInfo.setAssignState(2);
            surveyRiskCaseInfo.setSurveyState(12);
            surveyRiskCaseInfo.setSurveyStateName("调查中");
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
            SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(invCase.getSurveyAssorgCaseId());
            if (surveyAssignOrg == null) return;
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
            double agingRate = AgingDayUtil.surveyAgingRate(invCase, surveyAssignOrg, surveyFranchisee, surveyConsignor);//超期考核绩效
            invCase.setOverdueAgingRate(agingRate);
            //调查积分计算
            Double sumScore = surveyCaseDirectionMapper.selectScoreAllDirectionByInvCaseId(invCase.getId());
            sumScore = Optional.ofNullable(sumScore).orElse(0d);
            invCase.setAssessScore(sumScore);
            invCase.setScore(sumScore * agingRate);
            surveyInvestigatorCaseMapper.updateByPrimaryKey(invCase);

            //完成全部方向
            String taskStatus = investigateTask.getTaskStatus();

            if (investigateTask.getInvestigatorSubmitTime() != null) {
                /**        去掉自动提交  2021年12月27日。
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 String investigatorSubmitTime = investigateTask.getInvestigatorSubmitTime();//提交报告的时间
                 invCase.setIsDirectionSuccess(1);
                 invCase.setSurveyState(4);
                 invCase.setAgingDay(0);
                 invCase.setSurveyStateName("已提交");
                 try {
                 invCase.setCreportDate(sdf.parse(investigatorSubmitTime));
                 } catch (ParseException e) {
                 e.printStackTrace();
                 }
                 surveyInvestigatorCaseMapper.updateByPrimaryKey(invCase);
                 */
                //当前机构的所有调查员都提交。机构才变成初审中。
                List<SurveyInvestigatorCase> cases = surveyInvestigatorCaseMapper.getSurveyInvestigatorCasesBySurveyAssorgCaseId(surveyAssignOrg.getId());
                Boolean b = false;
                for (SurveyInvestigatorCase aCase : cases) {
                    if (aCase.getCreportDate() == null) {
                        b = false;
                        break;
                    }
                    b = true;
                }
                if (b) {//说明机构内的所有调查员都已经提交
                    if (surveyAssignOrg.getReportDate() == null) {
                        surveyAssignOrg.setOrgSurveyState(2);
                        surveyAssignOrg.setOrgSurveyStateName("初审中");
                        surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                    }
                    //获取机构是否都已提交。
                    Boolean f = true;
                    Map<String, Long> map = new HashMap<String, Long>();
                    map.put("surveyInfoId", surveyAssignOrg.getSurveyInfoId());
                    List<SurveyAssignOrgDto> orgs = surveyAssignOrgMapper.list(map);
                    for (SurveyAssignOrgDto org : orgs) {
                        if (org.getReportDate() == null) {
                            f = false;
                        }
                    }
                    if (f) {
                        surveyAssignOrgApi.zhuanLefan(surveyRiskCaseInfo, new ApiRequest(), userInfoMapper.selectByPrimaryKey(userId), "");
                    }
                } else {
                    surveyAssignOrg.setOrgSurveyState(1);
                    surveyAssignOrg.setOrgSurveyStateName("调查中");
                    surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                }

                /**
                 int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
                 if(surveyConsignor!=null){
                 efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
                 }
                 //修改时效
                 SurveyUserPrescriptionFlow surveyUserPrescriptionFlow = surveyUserPrescriptionFlowMapper.selectByInvCaseIdLimitOne(invCase.getId());
                 if (surveyUserPrescriptionFlow != null) {
                 try {
                 surveyUserPrescriptionFlow.setEndTime(sdf.parse(investigatorSubmitTime)); //调查员提交时间
                 } catch (ParseException e) {
                 e.printStackTrace();
                 }
                 surveyUserPrescriptionFlow.setDays(Math.abs((double)GetWorkDay.calLeaveDays(surveyUserPrescriptionFlow.getStartTime(),surveyUserPrescriptionFlow.getEndTime(),efficiencyAttr)));
                 surveyUserPrescriptionFlowMapper.updateByPrimaryKey(surveyUserPrescriptionFlow);
                 double s = surveyUserPrescriptionFlowMapper.selectInvAgingDayByInfoIdAndSurOrgIdInvId(invCase.getSurveyInfoId(), invCase.getSurveyAssorgCaseId(), invCase.getId());
                 invCase.setAgingDay((int) s);
                 invCase.setAgingReal(s);
                 invCase.setAgingOver(invCase.getAgingReal()-invCase.getAgingCheck()>0?invCase.getAgingReal()-invCase.getAgingCheck():0);
                 surveyInvestigatorCaseMapper.updateByPrimaryKey(invCase);
                 }
                 */
            }
            return;
        }

        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(94L);
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        int day = GetWorkDay.calLeaveDays(surveyRiskCase.getEntrustTime(), surveyRiskCaseInfo.getEndTime(), 1);
        Date maxDate = null; //调查截止时间
        if (day > 0) {
            maxDate = GetWorkDay.calLeaveEndDate(new Date(), null, (day - 1), 1);
        } else {
            maxDate = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            maxDate = sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(maxDate) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SurveyInvestigatorCase surveyInvestigatorCase = new SurveyInvestigatorCase();
        surveyInvestigatorCase.setSurveyId(surveyRiskCaseInfo.getSurveyId());
        surveyInvestigatorCase.setSurveyInfoId(surveyRiskCaseInfo.getId());
        surveyInvestigatorCase.setEntrustTaskMoney(surveyRiskCaseInfo.getEntrustMoney());
        surveyInvestigatorCase.setEntrustReLosses(0.0);
        surveyInvestigatorCase.setSurveyUserId(surveyInvestigator.getUserId());
        surveyInvestigatorCase.setSurveyUserName(surveyInvestigator.getRealName());
        if ("Y".equals(investigateTask.getIsMainInvestigator())) {
            surveyInvestigatorCase.setSurveyUserType(1);
        } else {
            surveyInvestigatorCase.setSurveyUserType(2);
        }
        surveyInvestigatorCase.setSurveyState(1);
        surveyInvestigatorCase.setSurveyStateName("调查中");
        String assignTaskTime = investigateTask.getAssignTaskTime();
        Date currentDate = new Date();
        if (StringUtils.isNotBlank(assignTaskTime)) {
            currentDate = DateUtils.parseDate(assignTaskTime, "yyyy-MM-dd HH:mm:ss");
        }
        surveyInvestigatorCase.setAssignDate(currentDate);
        surveyInvestigatorCase.setAcceptDate(currentDate);
        surveyInvestigatorCase.setSurveyInfo(surveyRiskCaseInfo.getSurveyInfo());
        surveyInvestigatorCase.setSurveyItem(surveyRiskCaseInfo.getSurveyItem());
        surveyInvestigatorCase.setEntrustOrgId(surveyConsignor.getId());
        surveyInvestigatorCase.setEntrustOrgName(surveyConsignor.getName());
        surveyInvestigatorCase.setCreportState(0);
        surveyInvestigatorCase.setSurveryUserOrgType(1);
        surveyInvestigatorCase.setIsSun(0);
        surveyInvestigatorCase.setScoreState(0);
        surveyInvestigatorCase.setUserCashState(0);
        surveyInvestigatorCase.setSearchCondition(surveyRiskCaseInfo.getSearchCondition());
        surveyInvestigatorCase.setDeleteFlag(0);
        surveyInvestigatorCase.setSurveyEndTime(maxDate);
        surveyInvestigatorCase.setSurveyOrgId(surveyInvestigator.getOrgId());
        surveyInvestigatorCase.setSurveyOrgName(surveyInvestigator.getOrgName());
        surveyInvestigatorCase.setIsDirectionSuccess(0);
        surveyInvestigatorCase.setSunState(0);
        surveyInvestigatorCase.setSurveyPay(0);
        surveyInvestigatorCase.setReviewOff(0);
        surveyInvestigatorCase.setOverdueAgingRate(1.0);
        double agingcheckDays = GetWorkDay.calLeaveDays(currentDate, maxDate, surveyConsignor.getEfficiencyAttr());
        surveyInvestigatorCase.setAgingCheck(agingcheckDays);
        surveyInvestigatorCase.setAgingReal(0d);
        surveyInvestigatorCase.setAgingOver(0d);
        surveyInvestigatorCase.setSurveyTaskRemark(surveyRiskCaseInfo.getSurveyItem());
        surveyInvestigatorCase.setNewCase(1);
        surveyInvestigatorCaseMapper.insert(surveyInvestigatorCase);

        surveyRiskCaseInfo.setOrgAssign(1);
        surveyRiskCaseInfo.setAssignState(2);
        surveyRiskCaseInfo.setSurveyState(12);
        surveyRiskCaseInfo.setSurveyStateName("调查中");
        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

        //看机构是否已经分派过
        Map paramMap = new HashMap();
        paramMap.put("surveyOrgId", surveyInvestigator.getOrgId());
        paramMap.put("surveyInfoId", surveyRiskCaseInfo.getId());
        SurveyAssignOrgDto surveyAssignOrgDto = surveyAssignOrgMapper.selectByOne(paramMap);
        if (surveyAssignOrgDto != null) {
            //修改调查员的机构案件id
            surveyInvestigatorCase.setSurveyAssorgCaseId(surveyAssignOrgDto.getId());
            surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);
            surveyAssignOrgDto.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrgDto);

            surveyRiskCaseInfo.setOrgAssign(1);
            surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

            //添加调查员时效记录
            SurveyUserPrescriptionFlow surveyUserPrescriptionFlow = new SurveyUserPrescriptionFlow();
            surveyUserPrescriptionFlow.setSurveyInfoId(surveyRiskCaseInfo.getId());
            surveyUserPrescriptionFlow.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
            surveyUserPrescriptionFlow.setSurveyAssignOrgId(surveyAssignOrgDto.getId());
            surveyUserPrescriptionFlow.setStartTime(currentDate);
            surveyUserPrescriptionFlow.setOperateType(1);
            surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);
            surveyUserPrescriptionFlow.setOperateType(2);
            surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);

            String[] taskIds = new String[]{"9", "10", "13"};//13医院排查 10医保排查 13走访调查
            //增加调查员案件任务表
            for (String taskId : taskIds) {
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(taskId)) {
                    SurveyInvestigatorCaseType surveyInvestigatorCaseType = new SurveyInvestigatorCaseType();
                    surveyInvestigatorCaseType.setTaskId(Long.parseLong(taskId));
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyInvestigatorCaseType.getTaskId());
                    if (surveyTaskInfo != null) {
                        surveyInvestigatorCaseType.setTaskName(surveyTaskInfo.getName());
                    }
                    surveyInvestigatorCaseType.setSurveyUserId(surveyInvestigator.getUserId());
                    surveyInvestigatorCaseType.setSurveyUserName(surveyInvestigator.getRealName());
                    surveyInvestigatorCaseType.setSurveyUserCaseId(surveyInvestigatorCase.getId());
                    surveyInvestigatorCaseTypeMapper.insert(surveyInvestigatorCaseType);
                }
            }
            return;
        }

        SurveyAssignOrg surveyAssignOrg = new SurveyAssignOrg();
        surveyAssignOrg.setSurveyOrgId(surveyInvestigator.getOrgId());
        surveyAssignOrg.setSurveyOrgName(surveyInvestigator.getOrgName());
        surveyAssignOrg.setSurveyId(surveyRiskCaseInfo.getSurveyId());
        surveyAssignOrg.setSurveyInfoId(surveyRiskCaseInfo.getId());
        surveyAssignOrg.setReportState(0);
        surveyAssignOrg.setOrgSurveyState(1);
        surveyAssignOrg.setOrgSurveyStateName("调查中");
        surveyAssignOrg.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
        surveyAssignOrg.setOrgEndTime(maxDate);
        surveyAssignOrg.setCreateBy("暖哇");
        surveyAssignOrg.setCreateTime(currentDate);
        surveyAssignOrg.setDeleteFlag(0);
        surveyAssignOrg.setOrgPrimaryType(1);
        surveyAssignOrg.setReturnState(0);
        if (surveyRiskCaseInfo.getSubServiceId() == 2) {
            surveyAssignOrg.setServicesId(12L);//单点
            surveyAssignOrg.setServicesName("单点调查");
        } else {
            surveyAssignOrg.setServicesId(13L);//深度
            surveyAssignOrg.setServicesName("深度调查");
        }
        surveyAssignOrg.setAssessOrgMoney(0d);
        surveyAssignOrg.setAssessOrgLossesMoney(0d);
        surveyAssignOrg.setSurveryReLosses(0d);
        surveyAssignOrg.setSurveryReLossesSubmit(0d);
        surveyAssignOrg.setPayType(surveyRiskCaseInfo.getPayType());
        surveyAssignOrg.setSurveyPay(0);
        surveyAssignOrg.setSurveyReturn(0);
        surveyAssignOrg.setReviewOff(0);
        surveyAssignOrg.setOldOrgEndTime(maxDate);
        surveyAssignOrg.setOverdueAgingRate(1.0);
        surveyAssignOrg.setAgingCheck(agingcheckDays);
        surveyAssignOrg.setAgingReal(0d);
        surveyAssignOrg.setAgingOver(0d);
        surveyAssignOrg.setNewCase(1);
        surveyAssignOrg.setMarkError(0);
        surveyAssignOrgMapper.insert(surveyAssignOrg);
        surveyInvestigatorCase.setSurveyAssorgCaseId(surveyAssignOrg.getId());
        surveyInvestigatorCaseMapper.updateByPrimaryKey(surveyInvestigatorCase);

        surveyRiskCaseInfo.setOrgAssign(1);
        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

        //添加机构时效记录
        SurveyOrgPrescriptionFlow surveyOrgPrescriptionFlow = new SurveyOrgPrescriptionFlow();
        surveyOrgPrescriptionFlow.setSurveyInfoId(surveyRiskCaseInfo.getId());
        surveyOrgPrescriptionFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
        surveyOrgPrescriptionFlow.setStartTime(currentDate);
        surveyOrgPrescriptionFlow.setOperateType(1);
        surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);
        surveyOrgPrescriptionFlow.setOperateType(2);
        surveyOrgPrescriptionFlowMapper.insert(surveyOrgPrescriptionFlow);

        //添加调查员时效记录
        SurveyUserPrescriptionFlow surveyUserPrescriptionFlow = new SurveyUserPrescriptionFlow();
        surveyUserPrescriptionFlow.setSurveyInfoId(surveyRiskCaseInfo.getId());
        surveyUserPrescriptionFlow.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());
        surveyUserPrescriptionFlow.setSurveyAssignOrgId(surveyAssignOrg.getId());
        surveyUserPrescriptionFlow.setStartTime(currentDate);
        surveyUserPrescriptionFlow.setOperateType(1);
        surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);
        surveyUserPrescriptionFlow.setOperateType(2);
        surveyUserPrescriptionFlowMapper.insert(surveyUserPrescriptionFlow);

        String[] taskIds = new String[]{"9", "10", "13"};//13医院排查 10医保排查 13走访调查
        //增加调查员案件任务表
        for (String taskId : taskIds) {
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(taskId)) {
                SurveyInvestigatorCaseType surveyInvestigatorCaseType = new SurveyInvestigatorCaseType();
                surveyInvestigatorCaseType.setTaskId(Long.parseLong(taskId));
                SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyInvestigatorCaseType.getTaskId());
                if (surveyTaskInfo != null) {
                    surveyInvestigatorCaseType.setTaskName(surveyTaskInfo.getName());
                }
                surveyInvestigatorCaseType.setSurveyUserId(surveyInvestigator.getUserId());
                surveyInvestigatorCaseType.setSurveyUserName(surveyInvestigator.getRealName());
                surveyInvestigatorCaseType.setSurveyUserCaseId(surveyInvestigatorCase.getId());
                surveyInvestigatorCaseTypeMapper.insert(surveyInvestigatorCaseType);
            }
        }

        //机构任务
        for (String taskId : taskIds) {
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(taskId)) {
                SurveyAssignOrgType surveyAssignOrgType = new SurveyAssignOrgType();
                surveyAssignOrgType.setTaskId(Long.parseLong(taskId));
                SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyAssignOrgType.getTaskId());
                if (surveyTaskInfo != null) {
                    surveyAssignOrgType.setTaskName(surveyTaskInfo.getName());
                }
                surveyAssignOrgType.setSurveyId(surveyAssignOrg.getSurveyId());
                surveyAssignOrgType.setSurveyInfoId(surveyAssignOrg.getSurveyInfoId());
                surveyAssignOrgType.setSurveyAssignOrgId(surveyAssignOrg.getId());
                surveyAssignOrgType.setSurveyOrgId(surveyAssignOrg.getSurveyOrgId());
                surveyAssignOrgType.setSurveyOrgName(surveyAssignOrg.getSurveyOrgName());
                surveyAssignOrgTypeMapper.insert(surveyAssignOrgType);
            }
        }
    }

    public void addDirections(SurveyRiskCaseInfo surveyRiskCaseInfo, SurveyInvestigatorCase surveyInvestigatorCase, Long surveyInvId, InvestigateExecute investigateExecute, InvestigateExecuteDetail investigateExecuteDetail, boolean deleteFlag, String conclusion) {

        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyInvId);
        if (surveyInvestigator == null) {
            return;
        }
        if (surveyInvestigatorCase == null || surveyInvestigatorCase.getId() == null) {
            return;
        }

        SurveyCaseDirection surveyCaseDirection = new SurveyCaseDirection();

        ApproachCodeEnum codeEnum = ApproachCodeEnum.getEnumNameByEnumId(investigateExecute.getApproachCode());
        if (codeEnum == null) return;
        if (codeEnum.getTaskId() == 13 && StringUtils.isNotBlank(investigateExecuteDetail.getHospitalName())) {//如果是医院排查的话  方向名称为医院名称+实施时间 否则为实施途径名称+实施时间
            surveyCaseDirection.setDirectionName(investigateExecuteDetail.getHospitalName() + investigateExecuteDetail.getExecuteTime());//方向名称
        } else {
            surveyCaseDirection.setDirectionName(codeEnum.getApproachCodeName() + investigateExecuteDetail.getExecuteTime());
        }

        Map<String, Object> validateMap = new HashMap<>();
        validateMap.put("surveyInfoId", surveyRiskCaseInfo.getId());
        validateMap.put("directionName", surveyCaseDirection.getDirectionName());
        SurveyCaseDirection direction = surveyCaseDirectionMapper.selectDirectionOneByName(validateMap);
        if (deleteFlag) {  //删除实施
            if (direction != null) {
                direction.setDeleteFlag(1);
                surveyCaseDirectionMapper.updateByPrimaryKey(direction);
            }
//            return;
        }
        if (direction != null) {  //如果有相同方向名称的实施 那就不再新增同样名称的实施了
            surveyCaseDirection = direction;
            if (surveyInvestigatorCase.getSurveyState() == 4) {//已提交
                //主调查员提交报告 生成报告结论
                if (StringUtils.isNotBlank(conclusion) && surveyInvestigatorCase.getSurveyUserType() == 1) { //主调查员
                    SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
//                    UserInfo userInfo = userInfoMapper.selectByPrimaryKey(surveyInvId);
                    try {
                        surveyAssignOrg.setOrgSummary(conclusion);
                        surveyAssignOrg.setReportState(1);
                        surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
                        surveyRiskCaseInfo.setReportCompletion(conclusion);
                        surveyRiskCaseInfo.setReportState(1);

//                        surveyRiskCaseInfo.setSurveyState(22);
//                        surveyRiskCaseInfo.setSurveyStateName("平台复审中");
//                        surveyRiskCaseInfo.setReportDate(surveyInvestigatorCase.getCreportDate());
//                        surveyRiskCaseInfo.setLefanReportDate(surveyInvestigatorCase.getCreportDate());
                        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

//                        surveyInvestigatorCaseApi.syncPrice(surveyRiskCaseInfo,userInfo,false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        //地址信息设置
        String provinceName = investigateExecuteDetail.getProvinceName();
        String cityName = investigateExecuteDetail.getCityName();
        String countyName = investigateExecuteDetail.getCountyName();
        Map<String, Object> map = commonAreaMapper.selectThreeId(provinceName, cityName, countyName);
        if (map != null) {
            surveyCaseDirection.setProvince(provinceName);
            surveyCaseDirection.setCity(cityName);
            surveyCaseDirection.setDistrict(countyName);
            if (map.get("pId") != null) {
                surveyCaseDirection.setProvinceId(Integer.valueOf(map.get("pId").toString()));
            } else {
                surveyCaseDirection.setProvinceId(0);
            }
            if (map.get("cId") != null) {
                surveyCaseDirection.setCityId(Integer.valueOf(map.get("cId").toString()));
            } else {
                surveyCaseDirection.setCityId(0);
            }
            if (map.get("aId") != null) {
                surveyCaseDirection.setDistrictId(Integer.valueOf(map.get("aId").toString()));
            } else {
                surveyCaseDirection.setDistrictId(0);
            }
            if (map.get("cType") != null) {
                int cType = Integer.parseInt(map.get("cType").toString());
                if (cType == 5 || cType == 6) {
                    surveyCaseDirection.setAreaType(1);
                    surveyCaseDirection.setRegionType(cType);
                } else {
                    surveyCaseDirection.setAreaType(cType);
                    surveyCaseDirection.setRegionType(0);
                }
            } else {
                Integer aCtype = (Integer) map.get("aCtype");
                surveyCaseDirection.setAreaType(aCtype);
                surveyCaseDirection.setDistrictId(0);
            }

            if (surveyCaseDirection.getRegionType() == null) {
                surveyCaseDirection.setRegionType(0);
            }
            surveyCaseDirection.setAreaName(provinceName + cityName + countyName);
            surveyCaseDirection.setAreaId(surveyCaseDirection.getProvinceId());
        }

        if (surveyCaseDirection.getProvinceId() == null) {
            surveyCaseDirection.setProvinceId(0);
        }
        if (surveyCaseDirection.getCityId() == null) {
            surveyCaseDirection.setCityId(0);
        }
        if (surveyCaseDirection.getDistrictId() == null) {
            surveyCaseDirection.setDistrictId(0);
        }

        surveyCaseDirection.setDirectionText(investigateExecuteDetail.getExecuteDetailDesc());//方向内容
        surveyCaseDirection.setSurveyInvestigatorCaseId(surveyInvestigatorCase.getId());//调查员案件id
        surveyCaseDirection.setSurveyAssorgCaseId(surveyInvestigatorCase.getSurveyAssorgCaseId());
        surveyCaseDirection.setSun(0);//0不是阳性 1阳性
        surveyCaseDirection.setTaskId(codeEnum.getTaskId());
        surveyCaseDirection.setTaskName(codeEnum.getTaskName());

        /**
         surveyCaseDirection.setNewId(codeEnum.getNewId());
         surveyCaseDirection.setNewName(codeEnum.getNewName());
         String executeResult = investigateExecuteDetail.getExecuteResult();
         List<String> result1 = new ArrayList<>(Arrays.asList("101","107","202","103","109","106","111","709","707"));
         Map paramMap = new HashMap();
         List<Double> scoreList = new ArrayList<>();//用来存放临时分值 最终取最大的一个
         List<String> executeResultList = new ArrayList<>();//实施结果可能有多个 如果有多个 取最大的一个
         if (StringUtils.isNotBlank(executeResult)){
         String[] split = investigateExecuteDetail.getExecuteResult().split(",");
         executeResultList.addAll(Arrays.asList(split));
         }
         if (surveyCaseDirection.getNewId() == 319)
         {
         paramMap.put("taskInfoContentId",surveyCaseDirection.getNewId());
         if (result1.removeAll(executeResultList)){
         surveyCaseDirection.setDirectionResultTypeId(12L);
         surveyCaseDirection.setDirectionResultTypeName("病例有且门诊无");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }
         if ( executeResultList.contains("105") || executeResultList.contains("102")){
         surveyCaseDirection.setDirectionResultTypeId(14L);
         surveyCaseDirection.setDirectionResultTypeName("病例无且门诊无");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }
         if (executeResultList.contains("110") || executeResultList.contains("104")){
         surveyCaseDirection.setDirectionResultTypeId(13L);
         surveyCaseDirection.setDirectionResultTypeName("病历无且门诊有");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }
         if (executeResultList.contains("704")){
         surveyCaseDirection.setDirectionResultTypeId(17L);
         surveyCaseDirection.setDirectionResultTypeName("病历不配合且门诊不配合");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }
         if (executeResultList.contains("708")){
         if ("2".equals(investigateExecute.getApproachCode())){
         surveyCaseDirection.setDirectionResultTypeId(12L);
         surveyCaseDirection.setDirectionResultTypeName("病例有且门诊无");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }else{
         surveyCaseDirection.setDirectionResultTypeId(14L);
         surveyCaseDirection.setDirectionResultTypeName("病例无且门诊无");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }
         }
         }else if (surveyCaseDirection.getNewId() == 306)
         {
         paramMap.put("taskInfoContentId",surveyCaseDirection.getNewId());
         if (executeResultList.contains("720")){
         surveyCaseDirection.setDirectionResultTypeId(2L);
         surveyCaseDirection.setDirectionResultTypeName("无结果");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }else {
         surveyCaseDirection.setDirectionResultTypeId(1L);
         surveyCaseDirection.setDirectionResultTypeName("无结果");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }
         }else if (surveyCaseDirection.getNewId() == 318)
         {
         paramMap.put("taskInfoContentId",surveyCaseDirection.getNewId());
         if (executeResultList.contains("102")){
         surveyCaseDirection.setDirectionResultTypeId(10L);
         surveyCaseDirection.setDirectionResultTypeName("无");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }else {
         surveyCaseDirection.setDirectionResultTypeId(9L);
         surveyCaseDirection.setDirectionResultTypeName("有");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }
         }else {
         paramMap.put("taskInfoContentId",surveyCaseDirection.getNewId());
         surveyCaseDirection.setDirectionResultTypeId(1L);
         surveyCaseDirection.setDirectionResultTypeName("有结果");
         paramMap.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         SurveyTaskDirectionResult surveyTaskDirectionResult = surveyTaskDirectionResultMapper.selectOneByInfo(paramMap);
         if (surveyTaskDirectionResult != null && surveyTaskDirectionResult.getScore() != null){
         scoreList.add(surveyTaskDirectionResult.getScore());
         }
         }

         if (scoreList.size() > 0){
         surveyCaseDirection.setScore(Collections.max(scoreList));
         surveyCaseDirection.setAccScore(Collections.max(scoreList));
         }
         if (surveyCaseDirection.getScore() == null){
         surveyCaseDirection.setScore(0.0);
         }

         Integer areaId = null;
         Integer areaType = surveyCaseDirection.getAreaType();
         if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
         areaId = surveyCaseDirection.getCityId();
         }else{
         areaId = surveyCaseDirection.getDistrictId();
         }
         SurveyAssignOrgDto surveyAssignOrg = surveyAssignOrgMapper.selectByPrimaryKey(surveyInvestigatorCase.getSurveyAssorgCaseId());
         Double scoreRate = 1D;
         SurveyScoreModelOrg surveyScoreModelOrg = surveyScoreModelOrgMapper.selectOne(surveyAssignOrg.getSurveyOrgId());
         if (surveyScoreModelOrg != null){
         paramMap =  new HashMap<String,Object>();
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
         surveyCaseDirection.setScore(surveyCaseDirection.getScore() * scoreRate);
         */

        surveyCaseDirection.setSurveyId(surveyRiskCaseInfo.getSurveyId());
        surveyCaseDirection.setSurveyInfoId(surveyRiskCaseInfo.getId());
        surveyCaseDirection.setSurveyMoney(0d);
        surveyCaseDirection.setEntrustMoney(0d);

        surveyCaseDirection.setDeleteFlag(0);
        surveyCaseDirection.setSurveyOrgId(surveyInvestigator.getOrgId());
        surveyCaseDirection.setSurveyOrgName(surveyInvestigator.getOrgName());
        surveyCaseDirection.setEntrustPriceSource(0);
        surveyCaseDirection.setSurveyPriceSource(0);
        surveyCaseDirection.setMedicalNumber(0);
        surveyCaseDirection.setHaveReimbursement(0);
        surveyCaseDirection.setChannelFeeCur(0d);
        surveyCaseDirection.setChannelFeeSent(0d);
        surveyCaseDirection.setInvalidState(0);
        surveyCaseDirection.setChannelType(0);
        surveyCaseDirection.setSurveyAssorgCaseId(surveyInvestigatorCase.getSurveyAssorgCaseId());
        if ("Y".equals(investigateExecuteDetail.getIsInvalid())) {
            surveyCaseDirection.setDeleteFlag(1);
        }
        if (surveyCaseDirection.getId() != null) {
            surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
        } else {
            surveyCaseDirection.setCreateBy(surveyInvestigator.getRealName());
            surveyCaseDirection.setCreateTime(new Date());
            surveyCaseDirection.setUpdateBy(surveyInvestigator.getRealName());
            surveyCaseDirection.setUpdateTime(new Date());

            surveyCaseDirection.setNewId(null);
            surveyCaseDirection.setNewName(null);
            surveyCaseDirection.setOrgPoint(1);//默认都是指定调查方向
            surveyCaseDirection.setScore(0D);
            surveyCaseDirection.setScoreRate(1D);
            surveyCaseDirection.setEvaluate(1);
            surveyCaseDirectionMapper.insert(surveyCaseDirection);
        }


        /**

         //2020年11月9日 若为渠道调查员。则新增的方向为渠道方向。分值为0
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
         Map<String,Object> paramMap2 =  new HashMap<String,Object>();
         paramMap2.put("modelId",surveyChannelModelOrg.getModelId());
         paramMap2.put("taskId",surveyCaseDirection.getTaskId());
         paramMap2.put("taskContentId",surveyCaseDirection.getNewId());
         paramMap2.put("taskContentResutlId",surveyCaseDirection.getDirectionResultTypeId());
         paramMap2.put("areaId",areaId);
         SurveyChannelModelInfo surveyChannelModelInfo = surveyChannelModelInfoMapper.selectByParam(paramMap2);
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

         SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(94L);
         SurveyFranchisee sf = surveyFranchiseeMapper.selectByPrimaryKey(surveyAssignOrg.getSurveyOrgId());
         Integer surveyFranchiseeType = sf.getType();//互助的调查方类别
         if (surveyConsignor.getOrgAttr() == 1){
         surveyFranchiseeType = sf.getInsuranceType();//保司的调查方类别
         }
         Integer surveyPayType = surveyAssignOrg.getPayType();
         areaType = surveyCaseDirection.getAreaType();
         if (surveyPayType == null || areaType == null) return;

         areaId = null;
         if (areaType == 2 || areaType == 3){// 如果没有到具体的区(省会或地级市)则,取市的ID；否则取具体的区的ID
         areaId = surveyCaseDirection.getCityId();
         }else{
         areaId = surveyCaseDirection.getDistrictId();
         }
         Long surveyOrgId = surveyInvestigatorCase.getSurveyOrgId();//调查方机构ID
         //根据具体的区域ID,及委托方   获取区域类别价格
         map = new HashMap();
         map.put("taskId",surveyCaseDirection.getTaskId());
         map.put("taskInfoContentId",surveyCaseDirection.getNewId());
         map.put("directionResultTypeId",surveyCaseDirection.getDirectionResultTypeId());
         map.put("entrustOrgId",surveyRiskCaseInfo.getEntrustOrgId());
         map.put("surveyOrgId",surveyOrgId);
         map.put("areaId",areaId);
         map.put("orgAttr",surveyConsignor.getOrgAttr());
         SurveyPrice surveyPrice = surveyPriceMapper.selectDirectionPriceNew(map);
         //获取核算价格
         if (surveyPrice != null){
         surveyCaseDirection.setAccMoney(surveyPrice.getTaskPrice());
         }else{
         surveyCaseDirection.setAccMoney(0D);
         }

         if (surveyFranchiseeType != 1 || surveyConsignor.getOrgAttr() == 1){//非自营    2020年8月24日 13点52分。汪鑫确认。  保司的案件不管是自营还是非自营都取价格
         if (surveyPayType == 3 || surveyPayType == 4){
         if (surveyPrice != null) {
         surveyCaseDirection.setSurveyMoney(surveyPrice.getTaskPrice() == null ? 0D : surveyPrice.getTaskPrice());
         surveyCaseDirection.setSurveyPriceSource(1);
         }
         if (false){
         //获取调查员价格
         map = new HashMap();
         map.put("franchiseeId",surveyOrgId);
         map.put("taskId", surveyCaseDirection.getTaskId());
         map.put("areaId",surveyCaseDirection.getAreaId());
         Long regionType =surveyCaseDirection.getRegionType() != null ?Long.valueOf(surveyCaseDirection.getRegionType()) : 5L ;//5市去 6郊区
         if (areaType == 1){
         map.put("cityType",regionType);
         surveyCaseDirection.setRegionType(Integer.parseInt(regionType.toString()));
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
         //                            Long regionType = apiRequest.getLong("regionType");//5市去 6郊区
         map.put("cityType",regionType);
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
         }
         Integer entrustPayType = surveyRiskCaseInfo.getPayType();//委托方结算方式
         if (entrustPayType == 3 || entrustPayType == 4)
         {
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
         Long regionType =surveyCaseDirection.getRegionType() != null ?Long.valueOf(surveyCaseDirection.getRegionType()) : 5l ;//5市去 6郊区
         // 根据委托方机构ID和任务ID获取价格
         map = new HashMap();
         map.put("enturyId",surveyConsignor.getId());
         map.put("taskId", surveyCaseDirection.getTaskId());
         map.put("areaId",surveyCaseDirection.getAreaId());
         if (areaType == 1){
         //                    Long regionType = apiRequest.getLong("regionType");//5市去 6郊区
         map.put("cityType",regionType);
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
         //                        Long regionType = apiRequest.getLong("regionType");//5市去 6郊区
         map.put("cityType",regionType);
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

         //如果调查方按比例计算。 2021年3月17日 需求
         if (surveyPayType == 3 || surveyPayType == 4){
         paramMap =  new HashMap<String,Object>();
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
         surveyCaseDirection.setSurveyMoney(surveyCaseDirection.getEntrustMoney() == null ? 0 : surveyCaseDirection.getEntrustMoney() * surveyPriceModel.getSurveyEntrustRate()  / 100);
         }
         surveyCaseDirection.setAccMoney(surveyCaseDirection.getEntrustMoney() == null ? 0 : surveyCaseDirection.getEntrustMoney() * surveyPriceModel.getSurveyEntrustRate() / 100);
         }
         }
         }
         surveyCaseDirectionMapper.updateByPrimaryKey(surveyCaseDirection);
         UserInfo userInfo = userInfoMapper.selectByPrimaryKey(surveyInvId);
         surveyInvestigatorCaseApi.syncPrice(surveyRiskCaseInfo,userInfo,false);
         */
    }


    @ApiMethod(needLogin = false, descript = "保存相互宝案件保司金额", value = "backend-survey-case-risk-info-xhb-case")
    @Override
    public ApiResponse xhbCase(ApiRequest apiRequest) {
        List<SurveyCaseXHB> successData = JSONArray.parseArray(apiRequest.getString("successData"), SurveyCaseXHB.class);
        surveyRiskCaseInfoMapper.updateEntrustMoney(successData);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }
}
