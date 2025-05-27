package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseCenterInfoNewInfo;
import com.lefancrm.apicenter.backendapi.BackendSuningApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.model.CommonFile;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.*;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianfeng on 2018/5/10.
 */
@ApiService(descript = "案件中心信息API")
@Service
public class BackendCaseCenterInfoNewInfoImpl  extends BaseServiceImpl implements BackendCaseCenterInfoNewInfo{
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private CaseCenterExtendMapper caseCenterExtendMapper;
    @Autowired
    private SuningWithholdApplyMapper suningWithholdApplyMapper;//苏宁代扣申请mapper
    @Autowired
    private BackendSuningApi suningApi;//苏宁代扣Api
    @Autowired
    private CardInfoDtoMapper cardInfoDtoMapper;//卡信息mapper
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CaseMediationClaimMapper caseMediationClaimMapper;
    @Autowired
    private CaseMediationClaimLegalMapper caseMediationClaimLegalMapper;
    @Autowired
    private BackendMessageApiImpl messageApiImpl;
    @Autowired
    private CaseAssessmentReportMapper caseAssessmentReportMapper;
    @Autowired
    private CaseRiskControlMapper caseRiskControlMapper;
    @Autowired
    private CaseClosedReportMapper caseCloseReportMapper;
    @Autowired
    private PromotionOutlayMapper promotionOutlayMapper;
    @Autowired
    private DistributionBasicMapper distributionBasicMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;
    @Autowired
    private OrgAccountMapper orgAccountMapper;
    @Autowired
    private OrgAccountDetailMapper orgAccountDetailMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;
    @Autowired
    private CaseCenterInfoFollowMapper caseCenterInfoFollowMapper;
    @Autowired
    private CommonEnumMapper commonEnumMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private CaseFileMidMapper caseFileMidMapper;
    @Autowired
    private InvalidismEstimateMapper invalidismEstimateMapper;
    @Autowired
    private PaymentEstimateInquiryMapper paymentEstimateInquiryMapper;
    @Autowired
    private CasePayInfoMapper casePayInfoMapper;
    @Autowired
    private BillingApplyMapper billingApplyMapper;
    @Autowired
    private BillingApplyImgsMapper billingApplyImgsMapper;

    @Autowired
    private CaseEntrustInputMapper caseEntrustInputMapper;
    @Autowired
    private CaseCenterInfoExtend2Mapper caseCenterInfoExtend2Mapper;
    @Autowired
    private BackendCaseStepsApiImpl backendCaseStepsApi;
    @Autowired
    private CaseKaitingInfoMapper caseKaitingInfoMapper;
    @Autowired
    private PaymentEstimateApplyMapper paymentEstimateApplyMapper;
    @Autowired
    private InvalidismEstimateReportMapper invalidismEstimateReportMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;


    @ApiMethod(descript = "案件列表" ,value = "backend-case-center-new-list")
    @Override
    public ApiResponse list(ApiRequest request) {
        //综合查询  ( 评估综合查询 11 ,索赔综合查询 111 ,诉讼综合查询 1111)
        //2紧急代扣
        //案件接收  5评估员待接收案件  55索赔员待接收案件   555诉讼员待接收案件
        //评估中心:评估清单10,评估审核15,保证保险20,贷款管理25, 案件跟踪26 ,确认服务费 27
        //索赔中心:索赔清单30,索赔审核35, 案件跟踪 36
        //法务中心:诉讼清单45,诉讼审核50 案件跟踪 51
        //财务中心:保险费支出21  财务案件列表 54
        //客服中心  案件跟踪 52  案件列表 53  解约列表 56
        Integer menuType = request.getInt("menuType");
        Long currentUserId = request.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()

        if (11 == menuType){
            //综合查询 ((评估阶段，索赔阶段，诉讼阶段，结案的  ) and 贷款案件)
            request.put("menuType",11); //  (gradationState != 1 and type = 1)
        }else if (111 == menuType){
            //综合查询 (索赔阶段，诉讼阶段，结案)
            request.put("menuType",111);  //(gradationState not in (1,2))
        }else if (1111 == menuType){
            //综合查询  (诉讼阶段，结案  or 诉讼员不是NULL)
            request.put("menuType",1111); //(gradationState in (6,4) or legalUserId is not null)
        }else if(2 == menuType){
            //紧急代扣  所有案件
        }else if (5 == menuType){//评估员待接收案件
            request.put("gradationState",2);
            request.put("caseState",1);
            request.put("assessId",currentUserId);
        }else if (55 == menuType){//索赔员待接收
            request.put("gradationState",3);
            request.put("caseState",1);
            request.put("claimantId",currentUserId);
        }else if (555 == menuType){//诉讼员待接收
            request.put("gradationState",6);
            request.put("caseState",1);
            request.put("legalUserId",currentUserId);
        }else if (10 == menuType || 15 == menuType || 20 == menuType || 25 == menuType || 26 == menuType || 21 == menuType){
            if (menuType != 26){//案件跟踪  不是案件跟踪的时候，只查询评估阶段的
                request.put("gradationState",2);
            }
            String caseState = request.getString("caseState");
            if(caseState==null||"".equals(caseState)){
                request.put("caseState",-1); //非待接收  (caseState != 1)
            }else{
                request.put("caseState",caseState);//条件查询
            }
            if (15 != menuType && 21 != menuType){
                //评估员
                request.put("assessId",currentUserId);
            }

            switch (menuType)
            {
                case 10:
                    break;
                case 15:
                    request.put("menuType",15);//一次审核中, 一次审核通过的(二次审核中) (issuanceState in (1,5))
                    break;
                case 20:
                    request.put("handOutFlag",1);//已提交苏宁贷款(投保未发起)
                    break;
                case 21:
                    request.put("handOutFlag",3);//已提交苏宁贷款(投保中)
                    break;
                case  25:
                    request.put("handOutFlag",4);//投保已确认,待支付
                    break;
            }
        }else if(menuType == 27){
            request.put("menuType",27);//非洽谈阶段   gradationState != 1
        }else if (30 == menuType || 35 == menuType || 36 == menuType){
            //评估阶段,非待接
            if (menuType != 36){
                request.put("gradationState",3);
            }
            request.put("caseState",-1); //非待接收  (caseState != 1)
            if (35 != menuType){
                request.put("claimantId",currentUserId);//索赔员
            }
            switch (menuType)
            {
                case 30:
                    request.put("fakeState",0);
                    request.put("giveupState",0);
                    request.put("menuType",30);
                    break;
                case 35:
                    request.put("fakeState",0);
                    request.put("giveupState",0);
                    request.put("menuType",35);//审核中的  (claimState = 10)
                    break;
            }
        }else if (45 == menuType || 50 == menuType || 51 == menuType){
            if (menuType != 51){
                request.put("gradationState",6);//诉讼阶段
            }
            request.put("caseState",-1); //非待接收  (caseState != 1)
            if (50 != menuType){
                request.put("legalUserId",currentUserId);//诉讼员
            }
            switch (menuType)
            {
                case 45:
                    request.put("fakeState",0);
                    request.put("giveupState",0);
                    break;
                case 50:
                    request.put("fakeState",0);
                    request.put("giveupState",0);
                    request.put("menuType",50);//审核中的  (claimState = 2 or closeReportState =2 or closedState = 2)
            }
        }else if(1 == menuType || 52 == menuType || 53 == menuType || 54 == menuType){

        }else if(56 == menuType){
            //解约案件
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean marketManager = isRoleUser(userRoles,19L);
            Boolean customer = isRoleUser(userRoles,30L);
            if (marketManager && customer){
                request.put("menuType",56);
            }else{
                if (marketManager){
                    request.put("releaseState",4);
                }else if (customer){
                    request.put("releaseState",1);
                }else{
                    request.put("releaseState",-99);//-99代表 该条件下 无数据
                }
            }
        }
        else{
            //其他列表  无数据
            request.put("gradationState",-1);
        }
        //根据当前登录人是否是测试人员
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                request.put("isTest",0);
            }else{
                //测试人员:默认查询测试案件
                request.put("isTest",1);
            }
        }

        this.setBackendPageSize(request);
        int count = caseCenterInfoMapper.findListSize(request);
        List<CaseCenterInfoDto> list = caseCenterInfoMapper.findList(request);
        if (52 == menuType || 26 == menuType || 36 == menuType || 51 == menuType){
            for (CaseCenterInfoDto dto : list){
                CaseCenterInfoFollow  follow = new CaseCenterInfoFollow();
                follow.setCaseId(dto.getId());
                Integer state = -1;
                if (52 == menuType){
                    state = 1;
                }else if (26 == menuType){
                    state = 2;
                }else if (36 == menuType){
                    state = 3;
                }else if (51 == menuType){
                    state = 6;
                }
                follow.setGradationState(state);
                follow.setFollowType(1);
                CaseCenterInfoFollow caseCenterInfoFollow = caseCenterInfoFollowMapper.searchCaseCenterInfoFollowByInfo(follow);
                if (caseCenterInfoFollow != null){
                    if (caseCenterInfoFollow.getNextFollowTime() == null){
                        dto.setIsShowRed(false);
                    }else{
                        if (caseCenterInfoFollow.getNextFollowTime().getTime() - new Date().getTime() < 0){
                            dto.setIsShowRed(true);
                        }else {
                            dto.setIsShowRed(false);
                        }
                    }
                }
            }
        }else if (27 == menuType){
            for (CaseCenterInfoDto dto : list){
                if (dto.getDefineState() != null){
                    if (dto.getDefineState() == 1L){
                        dto.setDefineStateName("已确认");
                    }else {
                        dto.setDefineStateName("未确认");
                    }
                }
                else{
                    dto.setDefineStateName("未确认");
                }
            }
        }
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }


    @ApiMethod(descript = "评估/索赔/诉讼工作台取数" ,value = "backend-case-center-new-manager")
    @Override
    public ApiResponse manager(ApiRequest request) {
        Long currentUserId = request.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        Long choose = request.getLong("choose");//1评估工作台  2索赔工作台  3诉讼工作台

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String isTest = null;
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                isTest = "0";
            }else{
                //测试人员:默认查询测试案件
                isTest = "1";
            }
        }

        int count = 0;
        CaseCenterNumberDto caseCenterNumberDto = new CaseCenterNumberDto();
        request.clear();//案件总数
        request.put("isTest",isTest);
        count = caseCenterInfoMapper.findListSize(request);
        caseCenterNumberDto.setCaseNumber(count);
        request.clear();//乐赔宝
        request.put("type",1);
        request.put("isTest",isTest);
        count = caseCenterInfoMapper.findListSize(request);
        caseCenterNumberDto.setCaseLoan(count);
        request.clear();//索赔通
        request.put("type",2);
        request.put("isTest",isTest);
        count = caseCenterInfoMapper.findListSize(request);
        caseCenterNumberDto.setCaseAgent(count);
        request.clear();//洽谈阶段
        request.put("gradationState",1);
        request.put("isTest",isTest);
        count = caseCenterInfoMapper.findListSize(request);
        caseCenterNumberDto.setOprNumber(count);
        request.clear();//评估阶段
        request.put("gradationState",2);
        request.put("isTest",isTest);
        count = caseCenterInfoMapper.findListSize(request);
        caseCenterNumberDto.setAssNumber(count);
        request.clear();//索赔阶段
        request.put("gradationState",3);
        request.put("isTest",isTest);
        count = caseCenterInfoMapper.findListSize(request);
        caseCenterNumberDto.setClaimNumber(count);
        request.clear();//诉讼阶段
        request.put("gradationState",6);
        request.put("isTest",isTest);
        count = caseCenterInfoMapper.findListSize(request);
        caseCenterNumberDto.setLegalNumber(count);
        request.clear();//已结案
        request.put("gradationState",4);
        request.put("isTest",isTest);
        count = caseCenterInfoMapper.findListSize(request);
        caseCenterNumberDto.setCloseNumber(count);
        if (choose == 1){
            request.clear();//评估待接收
            request.put("menuType",5);
            request.put("gradationState",2);
            request.put("caseState",1);
            request.put("assessId",currentUserId);
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setPgdjs(count);
            request.clear();//待测算审核
            request.put("userId",currentUserId);
            request.put("negotiateState",0);
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.queryCaseNegotiateStateCount(request);
            caseCenterNumberDto.setPgdcs(count);
            request.clear();//待预估
            request.put("state",1);
            count = invalidismEstimateMapper.selectInvalidismEstimateListCount(request);
            caseCenterNumberDto.setPgdyg(count);
            request.clear();//待提交报告
            request.put("menuType",10);
            request.put("gradationState",2);
            request.put("caseState",-1);
            request.put("assessId",currentUserId);
            request.put("pgChoose",1);// 条件（dto.listState == 888 || dto.listState == 6 || dto.listState == 10 || dto.listState == 11）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setPgdtjbg(count);
            request.clear();//评估审核中
            request.put("menuType",15);
            request.put("gradationState",2);
            request.put("caseState",-1);
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean assessorManager = isRoleUser(userRoles,24L);
            Boolean riskSuper = isRoleUser(userRoles,22L);
            if (assessorManager){
                request.put("listState",2);
            }
            if (riskSuper){
                request.put("listState",4);
            }
            if (assessorManager && riskSuper){
                request.put("listState",null);//如果即是评估主管 又是 风控主管，将listState置空，表示  查 listState in (2,4)
            }
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setPgshz(count);
            request.clear();//待提交贷款
            request.put("menuType",10);
            request.put("gradationState",2);
            request.put("caseState",-1);
            request.put("assessId",currentUserId);
            request.put("pgChoose",2); //条件  （dto.listState = 8)
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setPgdtjdk(count);
            request.clear();//待投保
            request.put("menuType",20);
            request.put("gradationState",2);
            request.put("caseState",-1);
            request.put("assessId",currentUserId);
            request.put("handOutFlag",1);
            request.put("pgChoose",3); //条件  （dto.listState = 12)
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setPgdtb(count);
            request.clear();//待财务支付
            request.put("menuType",21);
            request.put("gradationState",2);
            request.put("caseState",-1);
            request.put("handOutFlag",3);
            request.put("pgChoose",4); //条件  （dto.listState = 14)
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setPgdcwzf(count);
            request.clear();//待放款确认
            request.put("menuType",25);
            request.put("gradationState",2);
            request.put("caseState",-1);
            request.put("assessId",currentUserId);
            request.put("handOutFlag",4);
            request.put("pgChoose",5); //条件  （dto.listState = 16)
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setPgdqr(count);
            request.clear();//待确认服务费
            request.put("menuType",27);
            request.put("pgChoose",6); //条件  （dto.defineState ！= 1)
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setPgqrfwf(count);
        }else if (choose == 2){
            request.clear();//索赔待接收
            request.put("menuType",55);
            request.put("gradationState",3);
            request.put("caseState",1);
            request.put("claimantId",currentUserId);
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSpdjs(count);
            request.clear();//材料收集中
            request.put("menuType",30);
            request.put("gradationState",3);
            request.put("claimantId",currentUserId);
            request.put("caseState",-1);
            request.put("spChoose",1); //条件 （dto.listState = 777）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSpclsjz(count);
            request.clear();//待鉴定
            request.put("menuType",30);
            request.put("gradationState",3);
            request.put("claimantId",currentUserId);
            request.put("caseState",-1);
            request.put("spChoose",2); //条件 （dto.listState = 77701 or dto.listState == 240202）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSpdjd(count);
            //
            request.clear();//待制作预案报告
            request.put("menuType",30);
            request.put("gradationState",3);
            request.put("claimantId",currentUserId);
            request.put("caseState",-1);
            request.put("spChoose",7); //条件 （dto.listState == 77702 || dto.listState == 26）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSpdzzyabg(count);

            request.clear();//索赔审核中
            request.put("menuType",35);
            request.put("gradationState",3);
            request.put("caseState",-1);
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSpshz(count);
            request.clear();//调解中
            request.put("menuType",30);
            request.put("gradationState",3);
            request.put("caseState",-1);
            request.put("claimantId",currentUserId);
            request.put("spChoose",3); //条件 （dto.listState = 24）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSptjz(count);
            request.clear();//待提交结案报告
            request.put("menuType",30);
            request.put("gradationState",3);
            request.put("caseState",-1);
            request.put("claimantId",currentUserId);
            request.put("spChoose",4); //条件 （dto.listState == 2401 || dto.listState == 32）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSpdtjja(count);
            request.clear();//待还款
            request.put("menuType",30);
            request.put("gradationState",3);
            request.put("caseState",-1);
            request.put("claimantId",currentUserId);
            request.put("spChoose",5); //条件 （dto.listState == 30）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSpdhk(count);
            request.clear();//待结案
            request.put("menuType",30);
            request.put("gradationState",3);
            request.put("caseState",-1);
            request.put("claimantId",currentUserId);
            request.put("spChoose",6); //条件 （dto.listState == 34 || dto.listState == 40）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSpdja(count);
        }else if (choose == 3){
            request.clear();//待接收
            request.put("menuType",555);
            request.put("gradationState",6);
            request.put("caseState",1);
            request.put("legalUserId",currentUserId);
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSsdjs(count);
            request.clear();//材料收集中
            request.put("menuType",45);
            request.put("gradationState",6);
            request.put("caseState",-1);
            request.put("legalUserId",currentUserId);
            request.put("ssChoose",1); //条件 （dto.listState == 666）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSsclsjz(count);
            request.clear();//待制作预案报告
            request.put("menuType",45);
            request.put("gradationState",6);
            request.put("caseState",-1);
            request.put("legalUserId",currentUserId);
            request.put("ssChoose",2); //条件 （dto.listState == 66601 or dto.listState == 6660202）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSsdzzyabg(count);
            request.clear();//审核中
            request.put("menuType",50);
            request.put("gradationState",6);
            request.put("caseState",-1);
            //request.put("legalUserId",currentUserId);
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSsshz(count);
            request.clear();//待立案
            request.put("menuType",45);
            request.put("gradationState",6);
            request.put("caseState",-1);
            request.put("legalUserId",currentUserId);
            request.put("ssChoose",3); //条件 （dto.listState == 6660201）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSsdla(count);
            request.clear();//待提交结案报告
            request.put("menuType",45);
            request.put("gradationState",6);
            request.put("caseState",-1);
            request.put("legalUserId",currentUserId);
            request.put("ssChoose",4); //条件 （dto.listState == 666020101 or dto.listState == 32）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSsdtjjabg(count);
            request.clear();//待还款
            request.put("menuType",45);
            request.put("gradationState",6);
            request.put("caseState",-1);
            request.put("legalUserId",currentUserId);
            request.put("ssChoose",5); //条件 （dto.listState == 30）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSsdhk(count);
            request.clear();//待结案
            request.put("menuType",45);
            request.put("gradationState",6);
            request.put("caseState",-1);
            request.put("legalUserId",currentUserId);
            request.put("ssChoose",6); //条件 （dto.listState == 34 || dto.listState == 40）
            request.put("isTest",isTest);
            count = caseCenterInfoMapper.findListSize(request);
            caseCenterNumberDto.setSsdja(count);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseCenterNumberDto);
    }






    @ApiMethod(descript = "案件操作" ,value = "backend-case-center-new-info")
    @Override
    public ApiResponse getInfo(ApiRequest request) {
        Long id = request.getLong("id");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
        CaseCenterInfoDto dto = ConvertToBeanUtil.buildInfo(CaseCenterInfoDto.class,caseCenterInfo);
//        if(caseCenterInfo.getType()!=null){
//            if (1 == caseCenterInfo.getType()){
//                LoanApplication loan = loanApplicationMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
//                dto.setAddress(loan.getAccidentAddress());
//                dto.setCaseTypeName("贷款");
//            }else if(2 == caseCenterInfo.getType()){
//                AgentApply agent = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
//                dto.setAddress(agent.getAccidentAddress());
//                dto.setCaseTypeName("代理");
//            }
//        }else{
//            dto.setAddress(null);
//            dto.setCaseTypeName(null);
//        }
//        if (1 == caseCenterInfo.getGradationState()){
//            dto.setGradationStateName("洽谈阶段");
//        }else if(2 == caseCenterInfo.getGradationState()){
//            dto.setGradationStateName("评估阶段");
//            //
//            CaseAssessmentReport assessmentReport = caseAssessmentReportMapper.selectByCaseId(caseCenterInfo.getId());
//            if (assessmentReport != null){
//                dto.setAssessmentReportId(assessmentReport.getId());
//            }
//            CaseRiskControl riskControl = caseRiskControlMapper.selectByCaseId(caseCenterInfo.getId());
//            if (riskControl != null){
//                dto.setRiskReportId(riskControl.getId());
//            }
//        }else if(3 == caseCenterInfo.getGradationState()){
//            dto.setGradationStateName("索赔阶段");
//            CaseMediationClaim mediationClaim = caseMediationClaimMapper.queryByCaseId(caseCenterInfo.getId());
//            if (mediationClaim != null){
//                dto.setMediationReportId(mediationClaim.getId());
//            }
//            CaseClosedReport closedReport = caseCloseReportMapper.selectByCaseId(caseCenterInfo.getId());
//            if (closedReport != null){
//                dto.setCloseReportId(closedReport.getId());
//            }
//        }else if(4 == caseCenterInfo.getGradationState()){
//            dto.setGradationStateName("已结案");
//        }else if(6 == caseCenterInfo.getGradationState()){
//            dto.setGradationStateName("诉讼阶段");
//            CaseMediationClaim mediationClaim = caseMediationClaimMapper.queryByCaseId(caseCenterInfo.getId());
//            if (mediationClaim != null){
//                dto.setMediationReportId(mediationClaim.getId());
//            }
//            CaseClosedReport closedReport = caseCloseReportMapper.selectByCaseId(caseCenterInfo.getId());
//            if (closedReport != null){
//                dto.setCloseReportId(closedReport.getId());
//            }
//        }
        //案件中心附表
        CaseCenterExtend caseCenterExtend = caseCenterExtendMapper.selectByPrimaryKey(id);
        dto.setClaimantDate(caseCenterExtend.getClaimantDate());
        dto.setLegalDate(caseCenterExtend.getLegalDate());
//        if (caseCenterExtend != null){
//            if (caseCenterExtend.getAssessFlowState() == 1L){
//                dto.setAssessFlowState(1L);
//                dto.setAssessFlowName("跟踪中");
//            }else if (caseCenterExtend.getAssessFlowState() == 2L){
//                dto.setAssessFlowState(2L);
//                dto.setAssessFlowName("已结束");
//            }else {
//                dto.setAssessFlowName("未跟踪");
//            }
//            if (caseCenterExtend.getClaimFlowState() == 1L){
//                dto.setClaimFlowState(1L);
//                dto.setClaimFlowName("跟踪中");
//            }else if (caseCenterExtend.getClaimFlowState() == 2L){
//                dto.setClaimFlowState(2L);
//                dto.setClaimFlowName("已结束");
//            }else {
//                dto.setClaimFlowName("未跟踪");
//            }
//            if (caseCenterExtend.getLegalFlowState() == 1L){
//                dto.setLegalFlowState(1L);
//                dto.setLegalFlowName("跟踪中");
//            }else if (caseCenterExtend.getLegalFlowState() == 2L){
//                dto.setLegalFlowState(2L);
//                dto.setLegalFlowName("已结束");
//            }else {
//                dto.setLegalFlowName("未跟踪");
//            }
//            if (caseCenterExtend.getCustomerFlowState() == 1L){
//                dto.setCustomerFlowState(1L);
//                dto.setCustomerFlowName("跟踪中");
//            }else if (caseCenterExtend.getCustomerFlowState() == 2L){
//                dto.setCustomerFlowState(2L);
//                dto.setCustomerFlowName("已结束");
//            }else {
//                dto.setCustomerFlowName("未跟踪");
//            }
//            if (caseCenterExtend.getOperatorFlowState() == 1L){
//                dto.setOperatorFlowState(1L);
//                dto.setOperatorFlowName("跟踪中");
//            }else if (caseCenterExtend.getOperatorFlowState() == 2L){
//                dto.setOperatorFlowState(2L);
//                dto.setOperatorFlowName("已结束");
//            }else {
//                dto.setOperatorFlowName("未跟踪");
//            }
//
//            if(caseCenterExtend.getDefineState()!=null){
//                if (caseCenterExtend.getDefineState() == 1L){
//                    dto.setDefineStateName("已确认");
//                    dto.setDefineAmount(caseCenterExtend.getDefineAmount());
//                }else{
//                    dto.setDefineStateName("未确认");
//                    Map<String,Object> map = new HashMap();
//                    map.put("caseId",caseCenterInfo.getCaseId());
//                    map.put("caseType",caseCenterInfo.getType());
//                    PaymentEstimateInquiryDto inquiryDto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
//                    if(inquiryDto != null){
//                        dto.setDefineAmount(inquiryDto.getAgentServiceFee());
//                    }
//                }
//            }
//            dto.setDefineDate(caseCenterExtend.getDefineDate());
//            dto.setDefineState(caseCenterExtend.getDefineState());
//            //还款状态
//            dto.setRepay(caseCenterExtend.getRepay());
//            //还款图片
//            dto.setRepayImg(caseCenterExtend.getRepayImg());
//            dto.setOperatorFlowState(caseCenterExtend.getOperatorFlowState());
//            dto.setAppraiseType(caseCenterExtend.getAppraiseType());
//            dto.setMediateType(caseCenterExtend.getMediateType());
//            dto.setCloseCaseType(caseCenterExtend.getCloseCaseType());
//        }
        //案件中心附表
        CaseCenterInfoExtend2 extend2 = caseCenterInfoExtend2Mapper.selectByPrimaryKey(id);
        dto.setExtend2(extend2);

        //案件进度信息
        List<CaseStepsDTO> caseStepsDTOs = backendCaseStepsApi.list(caseCenterInfo);
        dto.setCaseStepsDTOs(caseStepsDTOs);

        if(caseCenterInfo.getClaimState()!=null && caseCenterInfo.getClaimState() == 9){
            List<CaseKaitingInfo> caseKaitingInfos = caseKaitingInfoMapper.selectByCaseCenterId(caseCenterInfo.getId());
            dto.setCaseKaitingInfos(caseKaitingInfos);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }

    @ApiMethod(descript = "获取角色" ,value = "backend-case-user-role")
    @Override
    public ApiResponse getCaseUserRole(ApiRequest request) {
        Long currentUserId = request.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        CaseUserRoleDto caseUserRoleDto = new CaseUserRoleDto();
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        caseUserRoleDto.setAssessor(isRoleUser(userRoles,3L));
        caseUserRoleDto.setAssessorManager(isRoleUser(userRoles,24L));
        caseUserRoleDto.setRiskSuper(isRoleUser(userRoles,22L));
        caseUserRoleDto.setClaims(isRoleUser(userRoles,7L));
        caseUserRoleDto.setClaimsManager(isRoleUser(userRoles,25L));
        caseUserRoleDto.setLegal(isRoleUser(userRoles,29L));
        caseUserRoleDto.setLegalManager(isRoleUser(userRoles,25L));//和索赔主管一样的角色
        caseUserRoleDto.setCustomer(isRoleUser(userRoles,30L));//客服
        caseUserRoleDto.setMarketingManager(isRoleUser(userRoles,19L));//市场总监

        caseUserRoleDto.setIsAssess(isRoleUser(userRoles,34L));//评估师
        caseUserRoleDto.setIsComplex(isRoleUser(userRoles, 35L));//综合内勤
        caseUserRoleDto.setIsAssessSuper(isRoleUser(userRoles, 36L));//评估主管
        caseUserRoleDto.setIsAssessManager(isRoleUser(userRoles, 37L));//评估经理
        caseUserRoleDto.setIsFinancialAuditor(isRoleUser(userRoles, 39L));//财务审核
        caseUserRoleDto.setIsFinancial(isRoleUser(userRoles, 23L));//财务部
        caseUserRoleDto.setCustomerManager(isRoleUser(userRoles,40L));//客服

        caseUserRoleDto.setSurveyEntrust(isRoleUser(userRoles,51L));
        caseUserRoleDto.setSurveyAgentEntrust(isRoleUser(userRoles,56L));
        caseUserRoleDto.setIsBelong(isRoleUser(userRoles,68L));//案件归属改派
        caseUserRoleDto.setIsTest(isRoleUser(userRoles,28L));//测试角色

        caseUserRoleDto.setIsFinalUserManage(isRoleUser(userRoles,118L));//复审人员主管
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseUserRoleDto);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }


    @ApiMethod(descript = "案件业务处理" ,value = "backend-case-center-new-operate")
    @Override
    public ApiResponse operate(ApiRequest request) {
        Long id = request.getLong("id");
        String btnCode = request.getString("btnCode");//操作按钮code
        String reason = request.getString("reason");//退回原因
        String flowName = "";
//        Long currentUserId  = request.getCurrentUserId();
        Long currentUserId = request.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        Boolean isSuccess = true;
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
        CaseCenterExtend caseCenterExtend = caseCenterExtendMapper.selectByPrimaryKey(id);
        CaseEntrustInput caseEntrustInput = caseEntrustInputMapper.selectByPrimaryKey(id);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        if ("1101".equals(btnCode)){//评估员提交
            caseCenterInfo.setIssuanceState(5);
        }else if ("1102".equals(btnCode)){//提交一审
            caseCenterInfo.setIssuanceState(1);
        }else if ("1103".equals(btnCode)){//退回一审
            caseCenterInfo.setIssuanceState(2);
        }else if ("1104".equals(btnCode)){//提交二审
            caseCenterInfo.setIssuanceState(3);
            caseCenterInfo.setHandOutFlag("0");//待提交苏宁贷款
        }else if ("1105".equals(btnCode)){//退回二审
            caseCenterInfo.setIssuanceState(4);
        }else if ("11061".equals(btnCode)){
            caseCenterInfo.setIssuanceState(4);//再次审核
        }else if ("1106".equals(btnCode)){//提交贷款
            caseCenterInfo.setHandOutFlag("1");
        }else if ("1107".equals(btnCode)){//投保
            caseCenterInfo.setHandOutFlag("3");
            Double insuredAmount = request.getDouble("insuredAmount") == null ? 0D : request.getDouble("insuredAmount");
            caseCenterInfo.setInsuredAmount(insuredAmount);
        }else if("11081".equals(btnCode)){//保险费支付
            caseCenterInfo.setHandOutFlag("4");
        }else if ("1108".equals(btnCode) || "1100".equals(btnCode)){//贷款管理-放款确认  (待分配索赔员)  或紧急代扣
            Map map = new HashMap();
            map.put("caseId",caseCenterInfo.getCaseId());
            map.put("caseType",caseCenterInfo.getType());
            //PaymentEstimateInquiryDto dto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());//最新测算信息
            String withholdType = request.getString("withholdType");//代扣方式   1现金  2代扣  3转账
            Double amount = request.getDouble("amount");//金额
            Date handOutTime = DateUtils.parseDate(request.getString("handOutTime"), "yyyy-MM-dd");//放款时间
            if ("1".equals(withholdType) || "3".equals(withholdType)){
                //不对接苏宁  生成代扣成功的纪录
                SuningWithholdApply apply = new SuningWithholdApply();
                apply.setCaseId(caseCenterInfo.getId());
                apply.setCaseNo(caseCenterInfo.getCaseNo());
                apply.setCaseTitle(caseCenterInfo.getCaseTitle());
                apply.setWithholdMoney(amount);
                apply.setWithholdTime(handOutTime);
                apply.setWithholdState(2);//代扣成功
                apply.setWithholdType(Integer.parseInt(withholdType));
                apply.setAppUserId(currentUserId);
                if ("1108".equals(btnCode)){
                    apply.setApplyType(0);
                }else{
                    apply.setApplyType(2);
                }
                suningWithholdApplyMapper.insert(apply);
            }else if ("2".equals(withholdType) || "4".equals(withholdType)){
                //苏宁代扣
//                CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCaseId(caseCenterInfo.getId());
                CardInfoDto search = new CardInfoDto();
                search.setCaseId(caseCenterInfo.getId());
                if ("2".equals(withholdType)){
                    search.setCardSource(1);
                }else if("4".equals(withholdType)){
                    search.setCardSource(2);
                }
                CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByInfo(search);
                if (cardInfoDto == null){
                    if ("2".equals(withholdType)){
                        return new ApiResponse(ApiMsgEnum.CASE_SUNING_HANDOUT_CARD);
                    }else{
                        return new ApiResponse(ApiMsgEnum.CASE_PINGAN_HANDOUT_CARD);
                    }
                }
                String path = request.getRes().getSession().getServletContext().getRealPath("/");
                Integer applyType = -1;
                if ("1108".equals(btnCode)){
                    applyType = 0;
                }else{
                    applyType = 2;
                }
                isSuccess = suningApi.proxy(cardInfoDto,amount,caseCenterInfo,false,Integer.parseInt(withholdType),currentUserId,null,path,applyType,0D);
            }

            //放款确认-//不管成功还是失败 都把放款动作做掉
            if ("1108".equals(btnCode)){
                caseCenterInfo.setHandOutFlag("2");//苏宁已确认(代扣成功)
                caseCenterInfo.setHandOutTime(handOutTime);
                caseCenterInfo.setGradationState(3);//改为索赔阶段
                caseCenterInfo.setClaimState(3);//索赔方案状态审核  3未发起
                caseCenterInfo.setCaseState(1);//待接收
                caseCenterInfo.setCaseStateStr("待接收");
                caseCenterInfo.setOrgUserId(null);
                caseCenterInfo.setOrgUserName(null);
                caseCenterInfo.setClaimantId(null);
                caseCenterInfo.setClaimantName(null);
            }
        }else if("3401".equals(btnCode)){
            caseCenterInfo.setGradationState(3);//改为索赔阶段
            caseCenterInfo.setClaimState(3);//索赔方案状态审核  3未发起
            caseCenterInfo.setCaseState(1);//待接收
            caseCenterInfo.setCaseStateStr("待接收");
            caseCenterInfo.setOrgUserId(null);
            caseCenterInfo.setOrgUserName(null);
            caseCenterInfo.setClaimantId(null);
            caseCenterInfo.setClaimantName(null);
        }else if("11083".equals(btnCode)){ //再次代扣
            Long applyId = request.getLong("applyId");
            SuningWithholdApply apply = suningWithholdApplyMapper.selectByPrimaryKey(applyId);
            String withholdType = request.getString("withholdType");//代扣方式   1现金  2苏宁代扣  3转账  4平安
            Double amount = request.getDouble("amount");//金额
            Double hidAmount = request.getDouble("hidAmount");//金额
            Date handOutTime = DateUtils.parseDate(request.getString("handOutTime"), "yyyy-MM-dd");//放款时间
            apply.setWithholdMoney(amount);
            apply.setWithholdTime(handOutTime);
            apply.setWithholdType(Integer.parseInt(withholdType));
            apply.setWithholdState(2);
            apply.setRemark(null);
            suningWithholdApplyMapper.updateByPrimaryKey(apply);
            if (apply.getWithholdType() == 2 || apply.getWithholdType() == 4){
                CardInfoDto search = new CardInfoDto();
                search.setCaseId(caseCenterInfo.getId());
                if ("2".equals(withholdType)){
                    search.setCardSource(1);
                }else if("4".equals(withholdType)){
                    search.setCardSource(2);
                }
                CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByInfo(search);
//                CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCaseId(caseCenterInfo.getId());
                if (cardInfoDto == null){
                    if ("2".equals(withholdType)){
                        return new ApiResponse(ApiMsgEnum.CASE_SUNING_HANDOUT_CARD);
                    }else{
                        return new ApiResponse(ApiMsgEnum.CASE_PINGAN_HANDOUT_CARD);
                    }
                }
                String path = request.getRes().getSession().getServletContext().getRealPath("/");
                isSuccess = suningApi.proxy(cardInfoDto,amount,caseCenterInfo,false,apply.getWithholdType(),currentUserId,applyId,path,apply.getApplyType(),amount);
            }
            if (1 == apply.getApplyType() && isSuccess){
                caseCenterInfo.setHandInFlag("2");//已确认代扣
                caseCenterInfo.setHandInTime(apply.getWithholdTime());
                caseCenterInfo.setClosedState(0);//未申请结案
            }
            if (!isSuccess){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }

        }else if("11085".equals(btnCode)){
            //确认服务费
            Double amount = request.getDouble("amount");
            if (caseCenterExtend != null){
                caseCenterExtend.setDefineAmount(amount);
                caseCenterExtend.setDefineDate(new Date());
                caseCenterExtend.setDefineState(1);
                caseCenterExtendMapper.updateByPrimaryKeySelective(caseCenterExtend);
            }

            //生成开票记录 2018年8月16日15:26:22 新需求：在“确认服务费”的时候，生成预开票记录（之前是在同意委托的时候生成，已删除该功能）
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("caseId",caseCenterInfo.getCaseId());
            map.put("caseType",caseCenterInfo.getType());
            PaymentEstimateInquiry paymentEstimateInquiry = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
            BillingApply billingApply = new BillingApply();
            billingApply.setCaseId(caseCenterInfo.getId());
            billingApply.setCaseNo(caseCenterInfo.getCaseNo());
            billingApply.setCaseTitle(caseCenterInfo.getCaseTitle());
            if (paymentEstimateInquiry != null){
                billingApply.setServcieMoney(DecimalUtil.twoDecimalTOFourFromFive(amount));
                billingApply.setChannelMoney(paymentEstimateInquiry.getLoanFee() == null ? 0D : paymentEstimateInquiry.getLoanFee());
                billingApply.setInsuranceMoney(paymentEstimateInquiry.getInsuranceFee() == null ? 0D : paymentEstimateInquiry.getInsuranceFee());
                billingApply.setDeductionMoney(0D);
                billingApply.setBillingMoney(DecimalUtil.twoDecimalTOFourFromFive(billingApply.getServcieMoney() + billingApply.getChannelMoney()));
            }else{
                billingApply.setServcieMoney(DecimalUtil.twoDecimalTOFourFromFive(amount));
                billingApply.setChannelMoney(0D);
                billingApply.setInsuranceMoney(0D);
                billingApply.setDeductionMoney(0D);
                billingApply.setBillingMoney(DecimalUtil.twoDecimalTOFourFromFive(amount));
            }
            billingApply.setBillingState(2);
            billingApply.setIsPreSign(1);//预签约案件
            billingApply.setBillingEnum(7);//开票类目(7：个人业务)
            billingApply.setBillingItem(5);//开票项目(5：服务费)
            billingApply.setBillingType(2);//开票类型(2、普票)
            billingApply.setBusinessType(1);//业务类别(1、金融)
            billingApply.setBillingTime(new Date());
            billingApply.setCreateTime(new Date());
            billingApply.setBillingSource(1);

            billingApply.setCreateBy(userInfo.getUserName());
            billingApply.setCreateById(currentUserId);

            OrgInfo orgInfo = isTopOrg(caseCenterInfo.getOrgId());
            billingApply.setOrgId(orgInfo.getId());
            billingApply.setOrgName(orgInfo.getOrgName());

            billingApply.setMeritName(caseCenterInfo.getOperatorName());//绩效所属人员（业务员）
            suningApi.saveBillingApply(billingApply);

            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else if ("1109".equals(btnCode)){//案件接收
            //业务员接受案件（洽谈阶段接受案件）
            if (caseCenterInfo.getGradationState() == 1){
                Double money = promotionOutlayMapper.selectSumMoneyByCaseNo(caseCenterInfo.getCaseNo());   //首先查询案件的推广总费用
                if (money == null){
                    DistributionBasic dis = distributionBasicMapper.selectDistributionBasic();       //如果没有推广总费用，查询固定扣款费用
                    money = dis.getCaseMoney();
                }
                HashMap<String,Object> paramMap = new HashMap<>();
                paramMap.put("userId",currentUserId);
                Long orgId = userInfo.getOrgId();
                if (userInfo == null || orgId == null){
                    //未找到用户机构信息
                    return new ApiResponse(ApiMsgEnum.CASE_ACCECP_ORG);
                }
                while (true){
                    OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(orgId);
                    if(orgInfo.getOrgParentid() == 1){
                        break;
                    }
                    orgId = orgInfo.getOrgParentid();
                }
                OrgAccount orgAccount = orgAccountMapper.selectByPrimaryKey(orgId);   //根据机构ID查询机构信息
                if (orgAccount.getUserForegift() != null && orgAccount.getUserForegift() > 0){
                    if (orgAccount.getUserUsable() < money){
                        //余额不足
                        return new ApiResponse(ApiMsgEnum.CASE_ACCECP_AMONT);
                    }else {
                        if (caseCenterInfo.getGradationState() == 1){
                            Boolean b = updateOrgAccount(userInfo.getOrgId(),currentUserId,money,2,"案源消费支出("+caseCenterInfo.getCaseName()+")",2);  //开始修改机构账户余额
                            if (!b){
                                //机构账户金额更新失败
                                return new ApiResponse(ApiMsgEnum.CASE_ACCECP_UPD_AMOUNT);
                            }
                        }
                    }
                }
            }

            //索赔员接收案件(索赔阶段接收案件) 发送短信
            if (caseCenterInfo.getGradationState() == 3){
                try {
                    SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(), caseCenterInfo.getCaseName(), userInfo.getUserName(), userInfo.getUserTel(), "1366");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            caseCenterInfo.setCaseState(2);
            caseCenterInfo.setCaseStateStr("已接收");
            if (caseCenterExtend != null) {
                if (caseCenterInfo.getGradationState() == 2){//评估员接收
                    caseCenterExtend.setAssessDate(new Date());
                }else if (caseCenterInfo.getGradationState() ==3){//索赔员接收
                    caseCenterExtend.setClaimantDate(new Date());
                    flowName = "索赔员已接收";
                }else if (caseCenterInfo.getGradationState() == 6){//诉讼员接收
                    caseCenterExtend.setLegalDate(new Date());
                    flowName = "诉讼员已接收";
                }
                caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            }
        }else if ("1110".equals(btnCode)){//案件拒绝
            caseCenterInfo.setCaseState(30);
            caseCenterInfo.setCaseStateStr("已拒绝");
            flowName = "已拒绝";
        }else if ("1111".equals(btnCode) || "66602".equals(btnCode)){//提交审核(索赔预案) or 诉讼预案
            caseCenterInfo.setClaimState(2);
        }else if("11111".equals(btnCode) || "240201".equals(btnCode)){
            caseCenterInfo.setGradationState(6);//改为诉讼阶段
            caseCenterInfo.setClaimState(3);//诉讼方案状态审核  3未发起
            caseCenterInfo.setCaseState(1);//待接收
            caseCenterInfo.setCaseStateStr("待接收");
            caseCenterInfo.setOrgUserId(null);
            caseCenterInfo.setOrgUserName(null);
            caseCenterInfo.setLegalUserId(null);
            caseCenterInfo.setLegalUserName(null);
        }else if ("1112".equals(btnCode)){//通过审核(索赔预案)
            //更新索赔方案预案审核信息  审核意见 审核人 审核时间
            CaseMediationClaim caseMediationClaim = caseMediationClaimMapper.queryByCaseId(caseCenterInfo.getId());
            if (caseMediationClaim != null){
                caseMediationClaim.setPlanReviewPerson(userInfo.getUserName());
                caseMediationClaim.setPlanReviewTime(new Date());
                caseMediationClaimMapper.updateByPrimaryKey(caseMediationClaim);
            }
            caseCenterInfo.setClaimState(1);//索赔方案  审核通过
            caseCenterInfo.setCloseReportState(3);//结案报告  [未发起结案报告]
            //索赔预案审核通过                发送短信
            try {
                SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),"1368");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if ("1113".equals(btnCode)){//退回审核(索赔预案)
            caseCenterInfo.setClaimState(0);
        }else if ("6660201".equals(btnCode)){//通过审核(诉讼预案)
            //更新索赔方案预案审核信息  审核意见 审核人 审核时间
            CaseMediationClaimLegal caseMediationClaimLegal = caseMediationClaimLegalMapper.queryByCaseId(caseCenterInfo.getId());
            if (caseMediationClaimLegal != null){
                caseMediationClaimLegal.setPlanReviewPerson(userInfo.getUserName());
                caseMediationClaimLegal.setPlanReviewTime(new Date());
                caseMediationClaimLegalMapper.updateByPrimaryKey(caseMediationClaimLegal);
            }
            caseCenterInfo.setClaimState(1);//索赔方案  审核通过
            caseCenterInfo.setCloseReportState(3);//结案报告  [未发起结案报告]
            //诉讼预案审核通过                发送短信
            try {
                SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),"1368");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if ("6660202".equals(btnCode)){//退回审核(诉讼预案)
            caseCenterInfo.setClaimState(0);
        }else if ("1114".equals(btnCode)){//提交审核(结案报告)
            //验证是否存在银行卡信息
//            CardInfoDto search = new CardInfoDto();
//            search.setCaseId(caseCenterInfo.getId());
//            CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByInfo(search);
//            if (cardInfoDto == null){
//                return new ApiResponse(ApiMsgEnum.CASE_SUNING_HANDOUT_CARD);
//            }
            caseCenterInfo.setCloseReportState(2);
            if (caseCenterExtend != null){
                Integer closeCaseType = request.getInt("closeCaseType");
                caseCenterExtend.setCloseCaseType(closeCaseType);
                caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            }
        }else if ("1115".equals(btnCode)){//通过审核(结案报告)
            caseCenterInfo.setCloseReportState(1);
            caseCenterInfo.setHandInFlag("0");//未发起代扣
            //2018年12月24日 09点49分 结案报告的 应收服务费与开票金额比较，比开票金额少的话 补票 ，比金额多的话 生成红冲 金额为负数
            CaseClosedReport caseClosedReport = caseCloseReportMapper.selectByCaseId(caseCenterInfo.getId());
            if (caseClosedReport == null){
                caseClosedReport = new CaseClosedReport();
                caseClosedReport.setShouldTotalMoney(0D);
                caseClosedReport.setLoanChannelMoney(0D);
                caseClosedReport.setInsuranceMoney(0D);
                caseClosedReport.setDeductedLoanMoney(0D);
            }
//            Double billMoney = billingApplyMapper.selectBillMoneyByCaseId(caseCenterInfo.getId());
//            billMoney =  billMoney == null ? 0D : DecimalUtil.twoDecimalTOFourFromFive(billMoney);
//            Double closeMoney = caseClosedReport.getShouldTotalMoney();
//            closeMoney =  closeMoney == null ? 0D : DecimalUtil.twoDecimalTOFourFromFive(closeMoney);
//            Double amount = closeMoney - billMoney;//正数  (补票)  负数 (红冲)
//            if (amount != 0D){
//                BillingApply billingApply = new BillingApply();
//                billingApply.setCaseId(caseCenterInfo.getId());
//                billingApply.setCaseNo(caseCenterInfo.getCaseNo());
//                billingApply.setCaseTitle(caseCenterInfo.getCaseTitle());
//                billingApply.setServcieMoney(0D);
//                billingApply.setChannelMoney(0D);
//                billingApply.setInsuranceMoney(0D);
//                billingApply.setDeductionMoney(0D);
//                billingApply.setBillingMoney(DecimalUtil.twoDecimalTOFourFromFive(amount));
//                billingApply.setBillingState(2);
//                billingApply.setIsPreSign(1);//预签约案件
//                billingApply.setBillingEnum(7);//开票类目(7：个人业务)
//                if (amount > 0){
//                    billingApply.setBillingItem(5);//服务费
//                }else if (amount < 0){
//                    billingApply.setBillingItem(12);//服务费(红冲)
//                }
//                billingApply.setBillingType(2);//开票类型(2、普票)
//                billingApply.setBusinessType(1);//业务类别(1、金融)
//                billingApply.setBillingTime(new Date());
//                billingApply.setCreateTime(new Date());
//                billingApply.setCreateBy(userInfo.getUserName());
//                billingApply.setCreateById(currentUserId);
//                OrgInfo orgInfo = isTopOrg(caseCenterInfo.getOrgId());
//                billingApply.setOrgId(orgInfo.getId());
//                billingApply.setOrgName(orgInfo.getOrgName());
//                billingApply.setMeritName(caseCenterInfo.getOperatorName());//绩效所属人员（业务员）
//                billingApply.setConfirmAccountState(2);
//                billingApply.setConfirmAccountTime(new Date());
//                billingApply.setConfirmAccountMoney(amount);
//                suningApi.saveBillingApply(billingApply);
//            }

            //2019年3月4日14:30:59
            // 新需求：应收服务费与开票金额比较，1、前者大，补开‘待开票’的‘差额票’；2、后者大，按照‘开票金额’落地一条“待红冲”票，再落地一条‘待开票’的“应收服务费额”票
            Map<String,Object> map = new HashMap<>();
            map.put("caseId",caseCenterInfo.getId());
            map.put("isPreSign",1);
            BillingApply billingApplys = billingApplyMapper.selectByInfo(map);
            if(billingApplys == null){
                return new ApiResponse(ApiMsgEnum.CASE_SUNING_CONFIRM_SERVICE);
            }
            Double billMoney = billingApplys.getBillingMoney();//预开票的开票金额
//            Double billMoney = billingApplyImgsMapper.selectBillMoneyByBillId(billingApplys.getId());//预开票的开票金额
            billMoney =  billMoney == null ? 0D : DecimalUtil.twoDecimalTOFourFromFive(billMoney);
            Double closeMoney = caseClosedReport.getShouldTotalMoney();//应收服务费
            closeMoney =  closeMoney == null ? 0D : DecimalUtil.twoDecimalTOFourFromFive(closeMoney);
            Double amount = closeMoney - billMoney;//正数  (补票)  负数 (红冲)
            if(amount != 0D){

                if(amount>0){
                    //正数  (补票)  应收服务费,待开票
                    BillingApplyImgs applyImgs =  new BillingApplyImgs();
                    applyImgs.setBillId(billingApplys.getId());
                    applyImgs.setCreateTime(new Date());
                    applyImgs.setBillingMoney(amount);
                    applyImgs.setCreateById(userInfo.getUserId());
                    applyImgs.setCreateBy(userInfo.getUserName());
                    applyImgs.setState(5);//发票状态（1、正常，2、作废，3、红冲, 4、待红冲，5、待开票）
                    applyImgs.setStateUpdateTime(new Date());
                    applyImgs.setStateUpdateBy(userInfo.getUserName());
                    applyImgs.setStateUpdateById(userInfo.getUserId());
                    billingApplyImgsMapper.insertSelective(applyImgs);
                }else{
                    billingApplys.setBillingState(3);//3、已开票
                    billingApplyMapper.updateByPrimaryKeySelective(billingApplys);

                    //负数：1、原所有开票金额总数红冲，待红冲
                    BillingApplyImgs applyImgs =  new BillingApplyImgs();
                    applyImgs.setBillId(billingApplys.getId());
                    applyImgs.setCreateTime(new Date());
                    applyImgs.setBillingMoney(-billMoney);
                    applyImgs.setCreateById(userInfo.getUserId());
                    applyImgs.setCreateBy(userInfo.getUserName());
                    applyImgs.setState(4);//发票状态（1、正常，2、作废，3、红冲, 4、待红冲）
                    applyImgs.setStateUpdateTime(new Date());
                    applyImgs.setStateUpdateBy(userInfo.getUserName());
                    applyImgs.setStateUpdateById(userInfo.getUserId());
                    billingApplyImgsMapper.insertSelective(applyImgs);

                    //应收服务费,待开票
                    applyImgs =  new BillingApplyImgs();
                    applyImgs.setBillId(billingApplys.getId());
                    applyImgs.setCreateTime(new Date());
                    applyImgs.setBillingMoney(closeMoney);
                    applyImgs.setCreateById(userInfo.getUserId());
                    applyImgs.setCreateBy(userInfo.getUserName());
                    applyImgs.setState(5);//发票状态（1、正常，2、作废，3、红冲, 4、待红冲，5、待开票）
                    applyImgs.setStateUpdateTime(new Date());
                    applyImgs.setStateUpdateBy(userInfo.getUserName());
                    applyImgs.setStateUpdateById(userInfo.getUserId());
                    billingApplyImgsMapper.insertSelective(applyImgs);

                }
            }



        }else if ("1116".equals(btnCode)){//退回审核(结案报告)
            caseCenterInfo.setCloseReportState(0);
        }else if ("1117".equals(btnCode) || "claim_fuwufei".equals(btnCode) || "legal_fuwufei".equals(btnCode)){//发起代扣
            Integer withholdType = request.getInt("withholdType");
            Double amount = request.getDouble("amount");
            Double hidAmount = request.getDouble("hidAmount");
            Date handInTime = DateUtils.parseDate(request.getString("handInTime"), "yyyy-MM-dd");//还款时间
//            CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCaseId(id);//根据案件中心id查看卡信息
            CardInfoDto search = new CardInfoDto();
            search.setCaseId(caseCenterInfo.getId());
            if (2 == withholdType){
                search.setCardSource(1);
            }else if (4 == withholdType){
                search.setCardSource(2);
            }
            CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByInfo(search);
            if (withholdType == 2){
                if (cardInfoDto == null){
                    return new ApiResponse(ApiMsgEnum.CASE_SUNING_HANDOUT_CARD);
                }
            }else if (withholdType == 4){
                if (cardInfoDto == null){
                    return new ApiResponse(ApiMsgEnum.CASE_PINGAN_HANDOUT_CARD);
                }
            }
            //苏宁代扣发起          需要审核true
            String path = request.getRes().getSession().getServletContext().getRealPath("/");
            isSuccess = suningApi.proxy(cardInfoDto,amount,caseCenterInfo,false,withholdType,currentUserId,null,path,1,hidAmount);
            System.out.println(isSuccess);
            if (isSuccess){
//                Date handInTime = DateUtils.parseDate(request.getString("handInTime"), "yyyy-MM-dd");
//                caseCenterInfo.setHandInFlag("1");// 已发起代扣              // 已发起代扣之后,由财务确认代扣之后 将申请结案状态改为0(未申请结案),代扣状态改为2 代扣已确认
//                caseCenterInfo.setHandInTime(handInTime);
                caseCenterInfo.setHandInFlag("2");//已确认代扣
                caseCenterInfo.setHandInTime(handInTime);
                caseCenterInfo.setClosedState(0);//未申请结案

                if (caseCenterInfo.getGradationState() == 3){
                    caseCenterInfo.setClaimState(12);
                    caseCenterInfo.setListStateName("待发起结案");
                    flowName = "服务费已收取/待结案";
                }else if (caseCenterInfo.getGradationState() == 6){
                    caseCenterInfo.setClaimState(13);
                    caseCenterInfo.setListStateName("待发起结案");
                    flowName = "服务费已收取/待结案";
                }
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }else if ("1118".equals(btnCode)){
            caseCenterInfo.setClosedState(1);//结案审核中
        }else if ("1119".equals(btnCode)){
            Map map = new HashMap();
            map.put("caseId",caseCenterInfo.getId());
            map.put("mType",0);
            int count = suningWithholdApplyMapper.selectListSize(map);
            if(count > 0 ){
                //表明尚有“未确认”的“确认到账”数据
                return new ApiResponse(ApiMsgEnum.CASE_CLOSE_ACC_ALL);
            }else{
                caseCenterInfo.setClosedState(2);// 结案通过
                caseCenterInfo.setGradationState(4);
                //结案审核通过   发送短信
                try {
                    SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),"1370");
                }catch (Exception e){
                    e.printStackTrace();
                }
                //评估跟踪  索赔跟踪 诉讼跟踪状态改为已结案
                if (caseCenterExtend != null){
                    caseCenterExtend.setAssessFlowState(2L);
                    caseCenterExtend.setClaimFlowState(2L);
                    caseCenterExtend.setLegalFlowState(2L);
                    caseCenterExtend.setClosedDate(new Date());//结案时间
                }
                caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            }

        }else if ("1120".equals(btnCode)){
            caseCenterInfo.setClosedState(3);//结案不通过
        }else if ("2100".equals(btnCode)){//结束跟踪
            String choose = request.getString("choose");
            if (caseCenterExtend != null){
                switch (choose){
                    case "PG": caseCenterExtend.setAssessFlowState(2L); break;
                    case "SP": caseCenterExtend.setClaimFlowState(2L); break;
                    case "SS": caseCenterExtend.setLegalFlowState(2L); break;
                    case "KF": caseCenterExtend.setCustomerFlowState(2L); break;
                }
                caseCenterExtendMapper.updateByPrimaryKeySelective(caseCenterExtend);
            }
            CaseCenterInfoFollow caseCenterInfoFollow = new CaseCenterInfoFollow();
            caseCenterInfoFollow.setCaseId(id);
            caseCenterInfoFollow.setFollowBy(userInfo.getUserName());
            caseCenterInfoFollow.setFollowById(userInfo.getUserId());
            caseCenterInfoFollow.setFollowTime(new Date());
            //下一次跟踪时间
            caseCenterInfoFollow.setNextFollowTime(null);
            //保存案件信息
            caseCenterInfoFollow.setCaseNo(caseCenterInfo.getCaseNo());
            caseCenterInfoFollow.setCaseType(caseCenterInfo.getType());
            switch (choose){
                case "PG": caseCenterInfoFollow.setGradationState(2);break;
                case "SP": caseCenterInfoFollow.setGradationState(3);break;
                case "SS": caseCenterInfoFollow.setGradationState(6);break;
                case "KF": caseCenterInfoFollow.setGradationState(1);break;
            }
            caseCenterInfoFollow.setCaseState(caseCenterInfo.getCaseState());
            caseCenterInfoFollow.setCaseTitle(caseCenterInfo.getCaseTitle());
            caseCenterInfoFollow.setCaseName(caseCenterInfo.getCaseName());
            caseCenterInfoFollow.setOrgName(caseCenterInfo.getOrgName());
            caseCenterInfoFollow.setOrgId(caseCenterInfo.getOrgId());
            caseCenterInfoFollow.setFollowType(2);
            caseCenterInfoFollow.setFollowDesc("已结束");
            caseCenterInfoFollowMapper.insertSelective(caseCenterInfoFollow);
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else if ("5300".equals(btnCode)){
            Long orgId = request.getLong("orgId");
            String orgName = request.getString("orgName");
            caseCenterInfo.setOrgId(orgId);
            caseCenterInfo.setOrgName(orgName);
        }else if ("2601".equals(btnCode) || "3601".equals(btnCode) || "5101".equals(btnCode)){
            //提交案件状态
            Long state = request.getLong("state");//状态------枚举的表的ID
            String desc = request.getString("desc");//描述
            CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(state);
            messageApiImpl.addMessageOrFollowByUpdCaseState(userInfo.getUserId(),userInfo.getUserName(),caseCenterInfo,commonEnum.getEnumName(),desc);
            try {
                if ("10".equals(state)){//材料收集中(索赔员点击单证收集)
                    SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),"1367");
                }else if ("15".equals(state)){//诉讼人员标记已立案
                    SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),"1371");
                }else if ("23".equals(state)){//已调解
                    SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),"1369");
                }else if ("24".equals(state)){//已判决
                    SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),"1372");
                }
            }catch (Exception e){

            }
            caseCenterInfo.setCaseState(Integer.parseInt(commonEnum.getEnumCode()));
            caseCenterInfo.setCaseStateStr(commonEnum.getEnumName());
            caseCenterInfo.setUpdateTime(new Date());
            caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else if ("77702".equals(btnCode)){
            if (caseCenterExtend != null){
                Integer appraiseType = request.getInt("appraiseType");
                caseCenterExtend.setAppraiseType(appraiseType);
                caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            }
        }else if ("2401".equals(btnCode)){
            Integer mediateType = request.getInt("mediateType");
            if (caseCenterExtend != null){
                caseCenterExtend.setMediateType(mediateType);
                caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            }
            //调解成功 或 失败 走不通的流程,此处将btnCode 重新赋值,2402代表调解失败的按钮
            if (mediateType == 0){//调解成功
                btnCode = "2401";
            }else if (mediateType == 1){//调解失败
                btnCode = "2402";
            }
        }else if ("666020101".equals(btnCode)){
            Long catalogId = request.getLong("catalogId");
            String catalogName = request.getString("catalogName");
            String strUrl = request.getString("strImages");
            if (strUrl != null){
                String [] urls = strUrl.split(",");
                for (String url : urls){
                    CommonFile commonFile = new CommonFile();
                    commonFile.setFilePath(url);
                    int firstName = url.lastIndexOf("/") + 1 ;
                    int lastName = url.lastIndexOf(".");
                    String name = url.substring(firstName,lastName);
                    commonFile.setFileName(name);
                    commonFileMapper.insert(commonFile);
                    //保存“案件文件表”
                    CaseFileMid caseFileMid = new CaseFileMid();
                    caseFileMid.setCaseId(caseCenterInfo.getId());
                    caseFileMid.setCaseNo(caseCenterInfo.getCaseNo());
                    caseFileMid.setCatalogId(catalogId);
                    caseFileMid.setCatalogName(catalogName);
                    caseFileMid.setFileId(commonFile.getId());
                    caseFileMidMapper.insert(caseFileMid);
                }
            }
        }else if("3001".equals(btnCode)){
            //把原所属列表状态，赋值，以便后续审核不通过时，还原此状态
            if(caseCenterExtend != null){
                caseCenterExtend.setOldListState(caseCenterInfo.getListState());
                caseCenterExtend.setOldListStateName(caseCenterInfo.getListStateName());
                caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            }
            caseCenterInfo.setReleaseState(1);
        }else if("3004".equals(btnCode)){
            //一审审核通过
            caseCenterInfo.setReleaseState(4);
        }else if("3002".equals(btnCode)){
            caseCenterInfo.setReleaseState(2);
            //清空部分数据
            caseCenterInfo.setCaseState(28);
            caseCenterInfo.setCaseStateStr("客户解约");
            caseCenterInfo.setOrgUserId(caseCenterInfo.getOperatorId());
            caseCenterInfo.setOrgUserName(caseCenterInfo.getOperatorName());
            caseCenterInfo.setGradationState(1);
            caseCenterInfo.setAssessId(null);
            caseCenterInfo.setAssessName(null);
            caseCenterInfo.setClaimantId(null);
            caseCenterInfo.setClaimantName(null);
            caseCenterInfo.setReleaseState(0);
            caseCenterInfo.setReleaseReason(null);
            caseCenterInfo.setIssuanceState(0);
            caseCenterInfo.setClaimState(0);
            caseCenterInfo.setClosedState(null);
            caseCenterInfo.setDistributionTime(new Date());
            caseCenterInfo.setUpdateTime(new Date());
            caseCenterInfo.setNegotiateState(0);
            caseCenterInfo.setHandOutTime(null);
            caseCenterInfo.setHandOutFlag("0");
            caseCenterInfo.setHandInFlag("0");
            caseCenterInfo.setHandInTime(null);
            caseCenterInfo.setCloseReportState(0);
            caseCenterInfo.setAgreeSignTime(null);
            caseCenterInfo.setListState(-2);
            caseCenterInfo.setListStateName("待签约");
            caseCenterInfo.setLegalUserId(null);
            caseCenterInfo.setLegalUserName(null);

            caseCenterExtend.setCustomerFlowState(0l);
            caseCenterExtend.setClaimFlowState(0l);
            caseCenterExtend.setAssessFlowState(0l);
            caseCenterExtend.setLegalFlowState(0l);
            caseCenterExtend.setOperatorFlowState(0l);
            caseCenterExtend.setAppraiseType(0);
            caseCenterExtend.setOldListState(0);
            caseCenterExtend.setOldListStateName(null);
            caseCenterExtend.setReleaseDate(new Date());//解约时间
            caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);

        }else if("3003".equals(btnCode)){
            caseCenterInfo.setReleaseState(3);
            //还原状态
            caseCenterInfo.setListState(caseCenterExtend.getOldListState());
            caseCenterInfo.setListStateName(caseCenterExtend.getOldListStateName());
            caseCenterExtend.setReleaseDate(null);
            caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
        }else if ("6868".equals(btnCode)){
            //代理转贷款案件
            if (caseCenterInfo.getType() == 2){
                AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                caseCenterInfo = toLoan(caseCenterInfo,agentApply);
            }
        }else if ("6969".equals(btnCode)){
            //贷款转代理案件
            if (caseCenterInfo.getType() == 1){
                LoanApplication loanApplication = loanApplicationMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                caseCenterInfo = toAgent(caseCenterInfo,loanApplication);
            }
        }else if ("claim_ccjiaoliu".equals(btnCode)){
            caseCenterInfo.setClaimState(3);
            caseCenterInfo.setListStateName("见面伤者");
            flowName = "CC交流完成/见面伤者";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_jianmian", 1, 3);
        }else if ("claim_jianmian".equals(btnCode)){
            caseCenterInfo.setClaimState(4);
            caseCenterInfo.setListStateName("材料收集");
            flowName = "见面伤者完成/材料待收集";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_cailiao", 1, 4);
        }else if ("claim_cailiao".equals(btnCode)){
            caseCenterInfo.setClaimState(5);
            caseCenterInfo.setListStateName("鉴定情况");
            flowName = "材料收集完成/待鉴定";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_jianding", 1, 5);
        }else if ("claim_jianding".equals(btnCode)){
            if (caseCenterExtend != null){
                Integer appraiseType = request.getInt("appraiseType");
                caseCenterExtend.setAppraiseType(appraiseType);
                caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            }
            caseCenterInfo.setClaimState(6);
            caseCenterInfo.setListStateName("制作索赔预案");
            flowName = "鉴定完成/待制作索赔预案";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_yuan", 1, 6);
        }else if ("claim_yuan".equals(btnCode)){
            String nextCode = request.getString("nextCode");
            if ("four_commit".equals(nextCode)){
                caseCenterInfo.setClaimState(7);
                caseCenterInfo.setOperReason(null);
                caseCenterInfo.setListStateName("索赔预案审核中");
                flowName = "索赔预案已提交/待审核";
                backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_shenhe", 1, 7);
            }
        }else if ("claim_yuan_agree".equals(btnCode)){
            caseCenterInfo.setClaimState(8);
            caseCenterInfo.setListStateName("调解");
            flowName = "索赔预案审核通过/待调解";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_jieguo", 1, 8);
        }else if ("claim_yuan_veto".equals(btnCode)){
            caseCenterInfo.setClaimState(6);
            caseCenterInfo.setOperReason(reason);
            flowName = "索赔预案已驳回，重新制作";
        }else if ("claim_tiaojie".equals(btnCode)){
            Integer mediateType = request.getInt("mediateType");
            if (caseCenterExtend != null){
                caseCenterExtend.setMediateType(mediateType);
                caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            }
            //调解成功 或 失败 走不同的流程, 调解失败  则重新鉴定
            if (mediateType == 0){//调解成功
                caseCenterInfo.setClaimState(9);
                caseCenterInfo.setListStateName("制作结案报告");
                flowName = "调解成功/制作结案报告";
            }else if (mediateType == 1){//调解失败
                caseCenterInfo.setClaimState(5);//调解失败 重新鉴定
                caseCenterInfo.setOperReason("调解失败,重新鉴定");
                caseCenterInfo.setListStateName("调解失败/重新鉴定");
                flowName = "调解失败/重新鉴定";
                backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_jianding", 1, 5);
            }
        }else if ("claim_jiean_report".equals(btnCode)){
            String nextCode = request.getString("nextCode");
            if ("four_commit".equals(nextCode)){
                caseCenterInfo.setClaimState(10);
                caseCenterInfo.setOperReason(null);
                caseCenterInfo.setListStateName("审核结案报告");
                flowName = "结案报告已提交/待审核";
            }
        }else if ("claim_jiean_report_agree".equals(btnCode)){
            caseCenterInfo.setClaimState(11);
            caseCenterInfo.setOperReason(null);
            caseCenterInfo.setListStateName("收取服务费");
            flowName = "结案报告审核通过/待收取服务费";
        }else if ("claim_jiean_report_veto".equals(btnCode)){
            caseCenterInfo.setClaimState(9);
            caseCenterInfo.setOperReason(reason);
            caseCenterInfo.setListStateName("重新制作结案报告");
            flowName = "结案报告审核驳回/重新制作";
        }
//        else if ("claim_fuwufei".equals(btnCode)){
//
//        }
        else if ("claim_jiean_start".equals(btnCode)){
            caseCenterInfo.setClaimState(13);
            caseCenterInfo.setOperReason(null);
            caseCenterInfo.setListStateName("结案审核中");
            flowName = "结案已发起/待审核";
        }else if ("claim_jiean_agree".equals(btnCode)){
            caseCenterInfo.setClaimState(14);
            caseCenterInfo.setGradationState(4);
            caseCenterInfo.setListStateName("已结案");
            flowName = "结案审核通过/已结案";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, false, "claim_jieguo", 1, 8);

            UserLogin ccUser = userLoginMapper.selectByPrimaryKey(caseCenterInfo.getOperatorId());
            String remarkStr = "理赔状态：已结案" + "\r\n"
                    + "理赔专员：" + caseCenterInfo.getClaimantName() + "\r\n"
                    + "服务费：" + caseCenterExtend.getDefineAmount();
            caseCenterInfo.setOperReason(reason);
            try {
                WechatTempleMsgUtil.sendWechatMsg(ccUser.getLfpcOpenid(), "pages/case/newCase/caseDetail?pageCode=ccuser-end&id=" + caseCenterInfo.getId(), "你有案件已结案！\r\n", "#FF0000", "结案", "#080808", caseCenterInfo.getCaseTitle(), "#080808", remarkStr, "#080808");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if ("claim_jiean_veto".equals(btnCode)){
            caseCenterInfo.setClaimState(12);
            caseCenterInfo.setOperReason(reason);
            caseCenterInfo.setListStateName("结案审核不通过");
            flowName = "结案审核不通过/重新申请";
        }else if ("claim_to_legal".equals(btnCode)){
            caseCenterInfo.setCaseState(1);
            caseCenterInfo.setGradationState(6);
            caseCenterInfo.setClaimState(5);
            caseCenterInfo.setOrgUserId(null);
            caseCenterInfo.setOrgUserName(null);
            caseCenterInfo.setListStateName("鉴定情况");
            flowName = "索赔转诉讼";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_to_legal", 2, 4);
        }else if ("legal_jianding".equals(btnCode)){
            if (caseCenterExtend != null){
                Integer appraiseType = request.getInt("appraiseType");
                caseCenterExtend.setAppraiseType(appraiseType);
                caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            }
            caseCenterInfo.setClaimState(6);
            caseCenterInfo.setListStateName("制作诉讼预案");
            flowName = "鉴定完成/待制作诉讼预案";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "legal_yuan", 2, 6);
        }else if ("legal_yuan".equals(btnCode)){
            String nextCode = request.getString("nextCode");
            if ("four_commit".equals(nextCode)){
                caseCenterInfo.setClaimState(7);
                caseCenterInfo.setOperReason(null);
                caseCenterInfo.setListStateName("诉讼预案审核中");
                flowName = "诉讼预案已提交/待审核";
                backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "legal_shenhe", 2, 7);
            }
        }else if ("legal_yuan_agree".equals(btnCode)){
            caseCenterInfo.setClaimState(8);
            caseCenterInfo.setOperReason(null);
            caseCenterInfo.setListStateName("立案");
            flowName = "诉讼预案审核通过/待立案";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "legal_lian", 2, 8);
        }else if ("legal_yuan_veto".equals(btnCode)){
            caseCenterInfo.setClaimState(6);
            caseCenterInfo.setOperReason(reason);
            caseCenterInfo.setListStateName("重新制作诉讼预案");
            flowName = "诉讼预案驳回/重新制作";
        }else if ("legal_lian".equals(btnCode)){
            caseCenterInfo.setClaimState(9);
            caseCenterInfo.setListStateName("开庭");
            flowName = "已立案/待开庭";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "legal_kaiting", 2, 9);
        }else if ("legal_kaiting_start".equals(btnCode)){
            //新增开庭信息  开始时间  案件ID
            String kaitingTime  = request.getString("kaitingTime");
            if (!"".equals(kaitingTime)){
                Date time = DateUtils.parseDate(kaitingTime,"yyyy-MM-dd HH:mm");
                CaseKaitingInfo caseKaitingInfo = new CaseKaitingInfo();
                caseKaitingInfo.setKaitingTime(time);
                caseKaitingInfo.setCaseCenterId(caseCenterInfo.getId());
                caseKaitingInfo.setCreateBy(userInfo.getUserName());
                caseKaitingInfo.setDeleteFlag(0);
                caseKaitingInfo.setCreateTime(new Date());
                caseKaitingInfo.setKaitingPlace(request.getString("kaitingPlace"));
                caseKaitingInfo.setKaitingContent(request.getString("kaitingContent"));
                caseKaitingInfoMapper.insert(caseKaitingInfo);
                flowName = "开庭";
            }
        }else if ("legal_kaiting_end".equals(btnCode)){
            caseCenterInfo.setClaimState(10);
            caseCenterInfo.setListStateName("制作结案报告");
            flowName = "结束开庭/待制作结案报告";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "legal_jieguo", 2, 10);
        }else if ("legal_jiean_report".equals(btnCode)){
            String nextCode = request.getString("nextCode");
            if ("four_commit".equals(nextCode)){
                caseCenterInfo.setClaimState(11);
                caseCenterInfo.setOperReason(null);
                caseCenterInfo.setListStateName("结案报告审核中");
                flowName = "结案报告已提交/待审核";
            }
        }else if ("legal_jiean_report_agree".equals(btnCode)){
            caseCenterInfo.setClaimState(12);
            caseCenterInfo.setOperReason(null);
            caseCenterInfo.setListStateName("收取服务费");
            flowName = "结案报告审核通过/待收取服务费";
        }else if ("legal_jiean_report_veto".equals(btnCode)){
            caseCenterInfo.setClaimState(10);
            caseCenterInfo.setOperReason(reason);
            caseCenterInfo.setListStateName("重新制作结案报告");
            flowName = "结案报告已驳回/重新制作";
        }else if ("legal_jiean_start".equals(btnCode)){
            caseCenterInfo.setClaimState(14);
            caseCenterInfo.setOperReason(null);
            caseCenterInfo.setListStateName("结案审核中");
            flowName = "结案申请已发起/待审核";
        }else if ("legal_jiean_agree".equals(btnCode)){
            caseCenterInfo.setClaimState(15);
            caseCenterInfo.setGradationState(4);
            caseCenterInfo.setListStateName("已结案");
            flowName = "结案审核通过/已结案";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, false, "legal_jieguo", 2, 8);

            UserLogin ccUser = userLoginMapper.selectByPrimaryKey(caseCenterInfo.getOperatorId());
            String remarkStr = "理赔状态：已结案" + "\r\n"
                    + "理赔专员：" + caseCenterInfo.getClaimantName() + "\r\n"
                    + "服务费：" + caseCenterExtend.getDefineAmount();
            caseCenterInfo.setOperReason(reason);
            try {
                WechatTempleMsgUtil.sendWechatMsg(ccUser.getLfpcOpenid(), "pages/case/newCase/caseDetail?pageCode=ccuser-end&id=" + caseCenterInfo.getId(), "你有案件已结案！\r\n", "#FF0000", "结案", "#080808", caseCenterInfo.getCaseTitle(), "#080808", remarkStr, "#080808");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if ("legal_jiean_veto".equals(btnCode)){
            caseCenterInfo.setClaimState(13);
            caseCenterInfo.setOperReason(reason);
            caseCenterInfo.setListStateName("重新申请结案");
            flowName = "结案已驳回/重新申请";
        }else if ("legal_to_claim".equals(btnCode)){
            caseCenterInfo.setGradationState(3);
            caseCenterInfo.setClaimState(5);
            caseCenterInfo.setOrgUserId(null);
            caseCenterInfo.setOrgUserName(null);
            caseCenterInfo.setListStateName("诉讼转索赔");
            flowName = "鉴定情况";
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "legal_to_claim", 1, 4);
        }

        if ("1103".equals(btnCode) || "1105".equals(btnCode) || "1110".equals(btnCode) || "1113".equals(btnCode)
                || "1116".equals(btnCode) || "1120".equals(btnCode) || "3001".equals(btnCode) || "3003".equals(btnCode)
                || "6660202".equals(btnCode)){
            caseCenterInfo.setOperReason(reason);
        }else{
//            caseCenterInfo.setOperReason(null);
        }
        String caseListStateName = "";

        //listState  所属列表状态,便于展示名称
        if(!"1100".equals(btnCode) && !"3003".equals(btnCode)){//非紧急代扣
            Integer listState = returnListState(btnCode);
            caseCenterInfo.setListState(listState == null ? caseCenterInfo.getListState() : listState);
        }

        if ("1109".equals(btnCode)){
            if (caseCenterInfo.getGradationState() == 1){
                caseCenterInfo.setListState(999);
            }else if (caseCenterInfo.getGradationState() == 2){
                caseCenterInfo.setListState(888);
            }else if (caseCenterInfo.getGradationState() == 3){
                caseCenterInfo.setListState(777);
            }else if (caseCenterInfo.getGradationState() == 6){
                caseCenterInfo.setListState(666);
                caseCenterInfo.setListStateName("鉴定情况");
                caseListStateName = "诉讼员已接受/鉴定情况";
            }
        }
//        if(!"1100".equals(btnCode)){//非紧急代扣
//            caseListStateName = returnListStateName(caseCenterInfo.getListState());
//            caseCenterInfo.setListStateName(caseListStateName == null ? caseCenterInfo.getListStateName() : caseListStateName);
//            caseCenterInfo.setUpdateTime(new Date());
//        }else{
//            caseListStateName = "紧急代扣已完成";
//        }

        //乐凡金融匹配借贷产品：如选择了“人伤全无忧资金垫付”，则不走保证保险流程
        if("1106".equals(btnCode)) {
            CaseRiskControl caseRiskControl = caseRiskControlMapper.selectByCaseId(caseCenterInfo.getId());
            if (caseRiskControl != null) {
                if (caseRiskControl.getMatchingProduct() != null) {
                    if (caseRiskControl.getMatchingProduct() == 3) {
                        caseCenterInfo.setHandOutFlag("4");
                        caseCenterInfo.setListState(16);
                        caseCenterInfo.setListStateName("放款已发起，待确认");
                        //案件进度状态
                        caseListStateName = "放款已发起，待确认";
                    }
                }

                //生成待支付项目
                CasePayInfo casePayInfo = new CasePayInfo();
                if (caseRiskControl.getLefanProposalMoney() != null && caseRiskControl.getLefanProposalMoney() > 0D) {
                    casePayInfo.setPayName("实际放款");
                    casePayInfo.setCaseId(caseCenterInfo.getId());
                    casePayInfo.setCaseTitle(caseCenterInfo.getCaseTitle());
                    casePayInfo.setUserName(caseCenterInfo.getCaseName());
                    casePayInfo.setPayMoney(caseRiskControl.getLefanProposalMoney());
                    casePayInfo.setAccountName(caseRiskControl.getAdvanceName());
                    casePayInfo.setBankName(caseRiskControl.getAdvanceBank());
                    casePayInfo.setCardNo(caseRiskControl.getAdvanceAccount());
                    casePayInfo.setRemarks(caseRiskControl.getAdvanceRemarks());
                    casePayInfo.setPayState(0);
                    casePayInfo.setAuditState(1);
                    casePayInfo.setCreateTime(new Date());
                    casePayInfoMapper.insert(casePayInfo);
                }
            }
        }
        if("9999".equals(btnCode)){
            //客服主管删除案件
            caseCenterInfo.setDeleteFlag(1);
            //同时标记子表也删除
            if (1 == caseCenterInfo.getType()) {
                //查询贷款申请表案件信息
                LoanApplication loanApplication = loanApplicationMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                loanApplication.setDeleteFlag(1);
                loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
            } else {
                //查询代理申请表案件信息
                AgentApply agentApplyInfo = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                agentApplyInfo.setDeleteFlag(1);
                agentApplyMapper.updateByPrimaryKeySelective(agentApplyInfo);
            }

        }
        if("9998".equals(btnCode)){
            //客服主管：修改案件为测试案件
            caseCenterInfo.setIsTestcase(1);
        }
        if("9997".equals(btnCode)){
            String caseName = request.getString("caseName");
            //确认服务费：可修改用户姓名
            caseCenterInfo.setCaseName(caseName);
            caseCenterInfoMapper.updateByPrimaryKeySelective(caseCenterInfo);
            //根据案件类型（type）查询不同的申请表 1.贷款申请2.代理申请
            if (1 == caseCenterInfo.getType()) {
                //查询贷款申请表案件信息
                LoanApplication loanApplication = loanApplicationMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                loanApplication.setUserName(caseName);
                loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
            } else {
                //查询代理申请表案件信息
                AgentApply agentApplyInfo = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                agentApplyInfo.setUserName(caseName);
                agentApplyMapper.updateByPrimaryKeySelective(agentApplyInfo);
            }
        }

        if (!"".equals(flowName)){
            Boolean b = messageApiImpl.addMessageOrFollowByUpdCaseState(userInfo.getUserId(),userInfo.getUserName(),caseCenterInfo,flowName,reason);
        }
        int ret = caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
        if (ret < 0 || !isSuccess){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    private CaseCenterInfo toLoan(CaseCenterInfo caseCenterInfo,AgentApply agentApply){
        if (caseCenterInfo.getGradationState() == 3 || caseCenterInfo.getGradationState() == 6){
            //转到评估阶段
            caseCenterInfo.setGradationState(2);
            caseCenterInfo.setOrgUserId(null);
            caseCenterInfo.setOrgUserName(null);
            caseCenterInfo.setClaimantId(null);
            caseCenterInfo.setAssessId(null);
            caseCenterInfo.setAssessName(null);
            caseCenterInfo.setClaimantName(null);
            caseCenterInfo.setLegalUserId(null);
            caseCenterInfo.setLegalUserName(null);
            caseCenterInfo.setClaimState(null);
            caseCenterInfo.setReleaseState(null);
            caseCenterInfo.setIssuanceState(null);
            caseCenterInfo.setHandOutFlag(null);
            caseCenterInfo.setHandOutTime(null);
            caseCenterInfo.setHandInTime(null);
            caseCenterInfo.setHandInFlag(null);
            caseCenterInfo.setClosedState(null);
            caseCenterInfo.setCloseReportState(null);
        }
        agentApply.setDeleteFlag(1);//改为删除
        agentApplyMapper.updateByPrimaryKey(agentApply);
        //插入贷款申请表
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setUserId(agentApply.getUserId());
        loanApplication.setUserName(agentApply.getUserName());
        loanApplication.setUserPhone(agentApply.getUserPhone());
        loanApplication.setIsTrafficAccident(1);
        loanApplication.setLoanNo(agentApply.getAgentNo());
        loanApplication.setLoanMoney(0D);
        loanApplication.setLoanPurpose(1);
        loanApplication.setAccidentProvince(agentApply.getAccidentProvinceId() + "");
        loanApplication.setAccidentCity(agentApply.getAccidentCityId() + "");
        loanApplication.setState(agentApply.getState());
        loanApplication.setAccidentDistrict(agentApply.getAccidentDistrictId() + "");
        loanApplication.setAccidentAddress(agentApply.getAccidentAddress());
        loanApplication.setCreateTime(agentApply.getCreateTime());
        loanApplication.setDeleteFlag(0);
        loanApplication.setReson(agentApply.getReson());
        loanApplication.setAccidentTime(agentApply.getAccidentTime());
        loanApplication.setIsFined(agentApply.getIsFined());
        loanApplication.setUserPromotedName(agentApply.getUserPromotedName());
        loanApplication.setUserPromotedPhone(agentApply.getUserPromotedPhone());
        loanApplication.setIsTestcase(agentApply.getIsTestcase());
        loanApplicationMapper.insert(loanApplication);

//        Map<String,Object> caseCenterInfoMap = new HashMap<>();
//        caseCenterInfoMap.put("caseId",agentApply.getId());
//        caseCenterInfoMap.put("caseType",2);
//        PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfo(caseCenterInfoMap);

        //转类型 转状态（待接收）
        if (caseCenterInfo.getGradationState() != 4){
            caseCenterInfo.setCaseState(1);
            caseCenterInfo.setCaseStateStr("待接收");
            caseCenterInfo.setListState(-1);
            caseCenterInfo.setListStateName("系统转换为贷款案件，待接收");
        }
        caseCenterInfo.setType(1);
        caseCenterInfo.setCaseId(loanApplication.getId());
        return caseCenterInfo;
    }

    private CaseCenterInfo toAgent(CaseCenterInfo caseCenterInfo,LoanApplication loanApplication){
        if (caseCenterInfo.getGradationState() == 2 || caseCenterInfo.getGradationState() == 3 || caseCenterInfo.getGradationState() == 6){
            //转到索赔阶段
            caseCenterInfo.setGradationState(3);
            caseCenterInfo.setOrgUserId(null);
            caseCenterInfo.setOrgUserName(null);
            caseCenterInfo.setClaimantId(null);
            caseCenterInfo.setClaimantName(null);
            caseCenterInfo.setLegalUserId(null);
            caseCenterInfo.setLegalUserName(null);
            caseCenterInfo.setClaimState(null);
            caseCenterInfo.setReleaseState(null);
            caseCenterInfo.setIssuanceState(null);
            caseCenterInfo.setHandInTime(null);
            caseCenterInfo.setHandInFlag(null);
            caseCenterInfo.setClosedState(null);
            caseCenterInfo.setCloseReportState(null);
        }
        loanApplication.setDeleteFlag(1);
        loanApplicationMapper.updateByPrimaryKey(loanApplication);
        //插入代理申请表
        AgentApply agentApply = new AgentApply();
        agentApply.setUserId(loanApplication.getUserId());
        agentApply.setUserName(loanApplication.getUserName());
        agentApply.setUserPhone(loanApplication.getUserPhone());
        agentApply.setAccidentProvince(null);
        agentApply.setAccidentCity(null);
        agentApply.setAccidentDistrict(null);
        agentApply.setAccidentProvinceId(new Integer(loanApplication.getAccidentProvince() == null ? "-1" : loanApplication.getAccidentProvince()));
        agentApply.setAccidentCityId(new Integer(loanApplication.getAccidentCity() == null ? "-1" : loanApplication.getAccidentCity()));
        agentApply.setAccidentDistrictId(new Integer(loanApplication.getAccidentDistrict() == null ? "-1" : loanApplication.getAccidentDistrict()));
        agentApply.setAccidentAddress(loanApplication.getAccidentAddress());
        agentApply.setAgentType(1);
        agentApply.setState(loanApplication.getState());
        agentApply.setCreateTime(loanApplication.getCreateTime());
        agentApply.setCreateBy(null);
        agentApply.setUpdateTime(new Date());
        agentApply.setUpdateBy(null);
        agentApply.setDeleteFlag(0);
        agentApply.setReson(loanApplication.getReson());
        agentApply.setAgentNo(loanApplication.getLoanNo());
        agentApply.setIsRead(1);
        agentApply.setAccidentTime(loanApplication.getAccidentTime());
        agentApply.setIsFined(loanApplication.getIsFined());
        agentApply.setUserPromotedName(loanApplication.getUserPromotedName());
        agentApply.setUserPromotedPhone(loanApplication.getUserPromotedPhone());
        agentApply.setIsTestcase(loanApplication.getIsTestcase());
        agentApplyMapper.insert(agentApply);

        //转类型 转状态（待接收）
        if (caseCenterInfo.getGradationState() != 4){
            caseCenterInfo.setCaseState(1);
            caseCenterInfo.setCaseStateStr("待接收");
            caseCenterInfo.setListState(-1);
            caseCenterInfo.setListStateName("系统转换为代理案件，待接收");
        }
        caseCenterInfo.setType(2);
        caseCenterInfo.setCaseId(agentApply.getId());
        return caseCenterInfo;
    }


    /**
     * 获取顶级机构
     */
    private OrgInfo isTopOrg(Long orgId) {
        OrgInfo orgInfo =  orgInfoMapper.selectByPrimaryKey(orgId);
        if(orgInfo.getOrgParentid() == -1){
            //默认为总部
            orgInfo = orgInfoMapper.selectByPrimaryKey(33L);
            return orgInfo;
        }
        if(orgInfo.getOrgParentid() == 1){
            return orgInfo;
        }else{
            return isTopOrg(orgInfo.getOrgParentid());
        }
    }

    /**
     * 根据按钮CODE 返回列表案件状态
     * @param btnCode
     * @return
     */
    private Integer returnListState(String btnCode){
        Map<String,Integer> map = new HashMap<>();
        map.put("1109",-1);//key:案件接收 value:案件已接收,待提交评估报告 888/索赔预案 777/诉讼预案  666
        map.put("1110",20);//key:案件拒绝 value:案件已拒绝
        map.put("1100",0);//key:紧急代扣 value:紧急代扣已完成
        map.put("1101",2);//key:提交审核(评估报告/公估报告) value:一审待审核
        map.put("1102",4);//key:提交一审 value:二审待审核
        map.put("1103",6);//key:退回一审 value:一审退回,重新提交报告
        map.put("1104",8);//key:提交二审 value:待提交贷款
        map.put("1105",10);//key:退回二审 value:二审退回,重新提交报告
        map.put("11061",11);//key:再次审核 value:再次审核,重新提交报告
        map.put("1106",12);//key:提交贷款 value:已提交苏宁贷款,待发起投保
        map.put("1107",14);//key:投保 value:已投保,待支付放款
        map.put("11081",16);//key:保险费支付 value:放款已发起，待确认
        map.put("1108",17);//key:放款确认 value:放款已确认，索赔员待接收
        map.put("1111",22);//key:提交审核(索赔预案) value:预案已提交,风控待审核
        map.put("11111",23);//key:已转诉讼
        map.put("1112",24);//key:通过审核 value:预案审核通过,调解中
        map.put("1113",26);//key:退回审核 value:预案已退回,重新提交索赔预案
        map.put("1114",28);//key:提交审核(结案报告) value:结案报告已提交,风控待审核
        map.put("1115",30);//key:通过审核 value:结案报告审核通过,待还款(待发起代扣)
        map.put("1116",32);//key:退回审核 value:结案报告已退回,重新提交结案报告
        map.put("1117",34);//key:发起代扣 value:还款已确认,待结案
        map.put("1118",36);//key:发起结案 value:结案已发起,风控待审核
        map.put("1119",38);//key:通过审核 value:已结案
        map.put("1120",40);//key:退回审核 value:结案审核不通过,重新申请结案

        map.put("77701",77701);
        map.put("77702",77702);
        map.put("2401",2401);
        map.put("2402",2402);
        map.put("240201",240201);
        map.put("240202",240202);

        map.put("66601",66601);
        map.put("66602",66602);
        map.put("6660201",6660201);
        map.put("6660202",6660202);
        map.put("666020101",666020101);
        map.put("3401",3401);
        map.put("3001",3001);
        map.put("3002",3002);
        map.put("3003",3003);
        map.put("3004",3004);

        map.put("6868",-2);
        map.put("6969",-2);
        return map.get(btnCode);
    }
    private String returnListStateName(Integer listState){
        Map<Integer,String> map = new HashMap<>();
        map.put(-1,"待接收(洽谈/评估/索赔/诉讼)");//无实际意义
        map.put(-2,"系统转换为贷款案件，待接收");//无实际意义
        map.put(0,"紧急代扣已完成");
        map.put(20,"已拒绝");
        map.put(999,"待签约");
        map.put(888,"评估员已接收，待提交报告");
        map.put(777,"索赔员已接收,材料收集中");
        map.put(77701,"材料收集完毕,待鉴定");
        map.put(77702,"鉴定完毕,待制作预案报告");

        map.put(666,"诉讼员已接受,诉讼材料收集中");
        map.put(66601,"诉讼材料收集完毕,待制作预案报告");
        map.put(66602,"诉讼预案已提交,风控待审核");
        map.put(6660201,"诉讼预案审核通过,待立案");
        map.put(6660202,"诉讼预案审核不通过,重新提交预案报告");
        map.put(666020101,"已立案,待结案");
        map.put(3401,"阶段结案,已转索赔");

        map.put(3001,"解约审核中");
        map.put(3002,"解约二审通过,已解约");
        map.put(3003,"解约不通过");
        map.put(3004,"解约一审通过");

        map.put(2,"一审待审核");//key:提交审核(评估报告/公估报告) value:一审待审核
        map.put(4,"二审待审核");//key:提交一审 value:二审待审核
        map.put(6,"一审退回,重新提交报告");//key:退回一审 value:一审退回,重新提交评估审核
        map.put(8,"待提交贷款");//key:提交二审 value:待提交贷款
        map.put(10,"二审退回,重新提交报告");//key:退回二审 value:二审退回,重新提交评估审核
        map.put(11,"再次审核,重新提交报告");//key:再次审核 value:再次审核,重新提交报告
        map.put(12,"已提交苏宁贷款,待发起投保");//key:提交贷款 value:已提交苏宁贷款,待发起投保
        map.put(14,"已投保,待财务支付保费");//key:投保 value:已投保,待财务支付保费
        map.put(16,"放款已发起，待确认");//key:保险费支付 value:支付已发起，放款待确认"
        map.put(17,"放款已确认，索赔员待接收");//key:放款确认 value:放款已确认，索赔员待接收
        map.put(22,"预案已提交,风控待审核");//key:提交审核(索赔预案) value:预案已提交,风控待审核
        map.put(23,"已转诉讼，诉讼员待接收");//key:提交审核(索赔预案) value:预案已提交,风控待审核
        map.put(24,"预案审核通过,调解中");//key:通过审核 value:预案审核通过,调解中
        map.put(2401,"调解成功,待提交结案报告");
        map.put(2402,"调解失败,转诉讼/重新鉴定");
        map.put(240201,"调解失败,转诉讼");
        map.put(240202,"调解失败,重新鉴定");
        map.put(26,"预案已退回,重新提交预案");//key:退回审核 value:预案已退回,重新提交索赔预案
        map.put(28,"结案报告已提交,风控待审核");//key:提交审核(结案报告) value:结案报告已提交,风控待审核
        map.put(30,"结案报告审核通过,待还款");//key:通过审核 value:结案报告审核通过,待还款(待发起代扣)
        map.put(32,"结案报告已退回,重新提交结案报告");//key:退回审核 value:结案报告已退回,重新提交结案报告
        map.put(34,"还款已确认,结案/转索赔");//key:发起代扣 value:代扣已发起,财务待确认
        map.put(36,"结案已发起,风控待审核");//key:发起结案 value:结案已发起,风控待审核
        map.put(38,"已结案");//key:发起结案 value:通过审核 value:已结案
        map.put(40,"结案审核不通过,重新申请结案");//key:发起结案 value:结案审核不通过,重新申请结案
        return map.get(listState);
    }

    //机构账号金额扣除和增加
    private  boolean  updateOrgAccount(Long orgId,Long userId,Double money,Integer type,String title,Integer genre){
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("orgId",orgId);
        paramMap.put("money",money);
        boolean flag=false;
        try{
            //type =1收入，type =2支出
            if(type==1){
                orgAccountMapper.addMoneyOrgAccountByOrgId(paramMap);
                //添加收支明细
            }else if(type==2){
                orgAccountMapper.lessMoneyOrgAccountByOrgId(paramMap);
                //添加收支明细
            }
            OrgInfo orgInfo=orgInfoMapper.selectByPrimaryKey(orgId);
            Map<String, Object> paramMap1=new HashMap<String, Object>();
            paramMap1.put("userId",userId);
            UserInfo userInfo=userInfoMapper.selectUserInfoByUserId(paramMap1);
            OrgAccountDetail orgAccountDetail=new OrgAccountDetail();
            orgAccountDetail.setOrgName(orgInfo.getOrgName());
            orgAccountDetail.setUserName(userInfo.getUserName());
            orgAccountDetail.setOrgId(orgId);
            orgAccountDetail.setUserId(userId);
            orgAccountDetail.setType(type);
            orgAccountDetail.setTitle(title);
            orgAccountDetail.setMoney(money);
            orgAccountDetail.setCreateTime(new Date());
            orgAccountDetail.setGenre(genre);
            orgAccountDetailMapper.insertSelective(orgAccountDetail);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    @ApiMethod(descript = "获取案件枚举状态",value = "backend-case-enum-state")
    @Override
    public ApiResponse getCaseEnumState(ApiRequest request) {
        String btnCode = request.getString("btnCode");
        List<CommonEnum> enums = null;
        if ("2601".equals(btnCode)){
            enums = commonEnumMapper.selectListByParentEnumCode("assess");
        }else if ("3601".equals(btnCode)){
            enums = commonEnumMapper.selectListByParentEnumCode("claimant");
        }else if ("5101".equals(btnCode)){
            enums = commonEnumMapper.selectListByParentEnumCode("legal");
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,enums);
    }

    /**
     * 更新
     */
    @ApiMethod(descript = "更新案件基础信息", value = "backend-case-info-new-update")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse update(ApiRequest apiReq) {

        try {
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));

            //案件附属表数据update
            CaseCenterInfoExtend2 caseCenterInfoExtend2 = caseCenterInfoExtend2Mapper.selectByPrimaryKey(apiReq.getLong("id"));
            if(caseCenterInfoExtend2!=null){
                caseCenterInfoExtend2.setPoliceTeam(apiReq.getString("policeTeam"));
                caseCenterInfoExtend2.setIdCard(apiReq.getString("idCard"));
                caseCenterInfoExtend2.setSex(apiReq.getInt("sex"));
                caseCenterInfoExtend2.setAge(apiReq.getInt("age"));
                caseCenterInfoExtend2.setMaritalStatus(apiReq.getInt("maritalStatus"));
                caseCenterInfoExtend2.setWorkUnit(apiReq.getString("workUnit")); //工作单位

                Date entryTime = DateUtils.parseDate(apiReq.getString("entryTime"), "yyyy-MM-dd"); //入职时间
                caseCenterInfoExtend2.setEntryTime(entryTime);
                caseCenterInfoExtend2.setWages(apiReq.getDouble("wages"));
                caseCenterInfoExtend2.setTaxCertificate(apiReq.getInt("taxCertificate"));
                caseCenterInfoExtend2.setPaySocialSecurity(apiReq.getInt("paySocialSecurity")); //是否缴纳社保：0：否  1：是
                caseCenterInfoExtend2.setBankInfo(apiReq.getInt("bankInfo"));

                caseCenterInfoExtend2.setLiveProvince(apiReq.getString("liveProvince"));  //居住信息
                caseCenterInfoExtend2.setLiveProvinceId(apiReq.getLong("liveProvinceId"));
                caseCenterInfoExtend2.setLiveCity(apiReq.getString("liveCity"));
                caseCenterInfoExtend2.setLiveCityId(apiReq.getLong("liveCityId"));
                caseCenterInfoExtend2.setLiveDistrict(apiReq.getString("liveDistrict"));
                caseCenterInfoExtend2.setLiveDistrictId(apiReq.getLong("liveDistrictId"));
                caseCenterInfoExtend2.setLiveAddress(apiReq.getString("liveAddress"));
                caseCenterInfoExtend2.setLiveTime(apiReq.getString("liveTime"));
                caseCenterInfoExtend2.setLiveTimeType(apiReq.getInt("liveTimeType"));
                caseCenterInfoExtend2.setResponsiblePartyPayRatio(apiReq.getDouble("responsiblePartyPayRatio"));//肇事方赔偿比例

                caseCenterInfoExtend2.setDomicileProvince(apiReq.getString("domicileProvince"));
                caseCenterInfoExtend2.setDomicileProvinceId(apiReq.getLong("domicileProvinceId"));
                caseCenterInfoExtend2.setDomicileCity(apiReq.getString("domicileCity"));
                caseCenterInfoExtend2.setDomicileCityId(apiReq.getLong("domicileCityId"));
                caseCenterInfoExtend2.setDomicileDistrict(apiReq.getString("domicileDistrict"));
                caseCenterInfoExtend2.setDomicileDistrictId(apiReq.getLong("domicileDistrictId"));
                caseCenterInfoExtend2.setDomicileAddress(apiReq.getString("domicileAddress"));

                caseCenterInfoExtend2.setUnitProvince(apiReq.getString("unitProvince"));
                caseCenterInfoExtend2.setUnitProvinceId(apiReq.getLong("unitProvinceId"));
                caseCenterInfoExtend2.setUnitCity(apiReq.getString("unitCity"));
                caseCenterInfoExtend2.setUnitCityId(apiReq.getLong("unitCityId"));
                caseCenterInfoExtend2.setUnitDistrict(apiReq.getString("unitDistrict"));
                caseCenterInfoExtend2.setUnitDistrictId(apiReq.getLong("unitDistrictId"));
                caseCenterInfoExtend2.setUnitAddress(apiReq.getString("unitAddress"));

                caseCenterInfoExtend2.setLandExpropriation(apiReq.getInt("landExpropriation"));
                caseCenterInfoExtend2.setVisitingHospital(apiReq.getString("visitingHospital"));

                caseCenterInfoExtend2.setInsuranceCompany(apiReq.getString("insuranceCompany"));
                caseCenterInfoExtend2.setInsuranceCompanyId(apiReq.getLong("insuranceCompanyId"));
                caseCenterInfoExtend2.setInsuranceCompany2(apiReq.getString("insuranceCompany2"));
                caseCenterInfoExtend2.setInsuranceCompany2Id(apiReq.getLong("insuranceCompany2Id"));

                caseCenterInfoExtend2.setAccidentType(apiReq.getInt("accidentType"));
                caseCenterInfoExtend2.setOurResponsibilities(apiReq.getInt("ourResponsibilities"));
//                caseCenterInfoExtend2.setOurTransportation(apiReq.getInt("ourTransportation"));
                caseCenterInfoExtend2.setResponsiblePartyPayRatio(apiReq.getDouble("responsiblePartyPayRatio"));
                caseCenterInfoExtend2Mapper.updateByPrimaryKeySelective(caseCenterInfoExtend2);
            }

            //新增案件表数据更新
            CaseEntrustInput caseEntrustInput = caseEntrustInputMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
            if(caseEntrustInput!=null){
                caseEntrustInput.setAccidentProvince(apiReq.getString("accidentProvince"));
                caseEntrustInput.setAccidentProvinceId(apiReq.getLong("accidentProvinceId"));
                caseEntrustInput.setAccidentCity(apiReq.getString("accidentCity"));
                caseEntrustInput.setAccidentCityId(apiReq.getLong("accidentCityId"));
                caseEntrustInput.setAccidentDistrict(apiReq.getString("accidentDistrict"));
                caseEntrustInput.setAccidentDistrictId(apiReq.getLong("accidentDistrictId"));
                caseEntrustInput.setAccidentAddress(apiReq.getString("accidentAddress"));
                caseEntrustInput.setInjuredTel(apiReq.getString("injuredTel"));
                caseEntrustInput.setInjuredPerson(apiReq.getString("injuredPerson"));
                Date accidentTime = DateUtils.parseDate(apiReq.getString("accidentTime"), "yyyy-MM-dd");
                caseEntrustInput.setAccidentTime(accidentTime);
                caseEntrustInputMapper.updateByPrimaryKeySelective(caseEntrustInput);
            }

            //索赔方案
            CaseMediationClaim  caseMediationClaim = caseMediationClaimMapper.queryByCaseId(caseCenterInfo.getId());
            if(caseMediationClaim!=null){
                caseMediationClaim.setDetermineType(apiReq.getString("determineType"));
                caseMediationClaim.setPartyName(apiReq.getString("partyName"));
                caseMediationClaim.setPartyTel(apiReq.getString("partyTel"));
                caseMediationClaim.setCardNumber(apiReq.getString("cardNumber"));
                caseMediationClaim.setDisclaimerType(apiReq.getString("disclaimerType"));
                caseMediationClaimMapper.updateByPrimaryKeySelective(caseMediationClaim);
            }

            //测算
            Map<String,Object> map = new HashMap<>();
            map.put("caseNo",caseCenterInfo.getCaseNo());
            PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(map);
            if(paymentEstimateApply!=null){
                paymentEstimateApply.setInjuredPart(apiReq.getString("injuredPart"));
                paymentEstimateApply.setTreatmentMethod(apiReq.getInt("treatmentMethod"));
                paymentEstimateApply.setMyTarfficStatus(apiReq.getInt("myTarfficStatus"));
                paymentEstimateApply.setOtherTarfficStatus(apiReq.getInt("otherTarfficStatus"));
                paymentEstimateApplyMapper.updateByPrimaryKeySelective(paymentEstimateApply);
            }

            //伤残评估
            map = new HashMap<>();
            map.put("caseNo",caseCenterInfo.getCaseNo());
            map.put("caseId",caseCenterInfo.getId());
            InvalidismEstimate invalidismEstimate = invalidismEstimateMapper.selectInvalidismEstimateByInfo(map);
            if(invalidismEstimate!=null){
                InvalidismEstimateReport invalidismEstimateReport = invalidismEstimateReportMapper.queryApplyByEstimateId(invalidismEstimate.getId());
                if(invalidismEstimateReport!=null){
                    invalidismEstimateReport.setInjuryDiagnose(apiReq.getString("injuryDiagnose"));
                    invalidismEstimateReport.setInvalidismGrade(apiReq.getString("invalidismGrade"));
                    invalidismEstimateReportMapper.updateByPrimaryKeySelective(invalidismEstimateReport);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }
}
