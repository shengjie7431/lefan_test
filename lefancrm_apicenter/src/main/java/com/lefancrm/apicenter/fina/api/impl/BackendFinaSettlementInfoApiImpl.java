package com.lefancrm.apicenter.fina.api.impl;

import com.alibaba.fastjson.JSON;
import com.lefancrm.apicenter.backendapi.impl.BackendWechatApiImpl;
import com.lefancrm.apicenter.dao.SurveyConsignorMapper;
import com.lefancrm.apicenter.dao.SurveyInvestigatorMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.fina.api.BackendFinaSettlementInfoApi;
import com.lefancrm.apicenter.fina.dao.*;
import com.lefancrm.apicenter.fina.dto.SettlementFilesDTO;
import com.lefancrm.apicenter.fina.dto.SettlementUserDTO;
import com.lefancrm.apicenter.fina.enums.*;
import com.lefancrm.apicenter.fina.model.*;
import com.lefancrm.apicenter.model.SurveyConsignor;
import com.lefancrm.apicenter.model.SurveyInvestigator;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ChineseUtil;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.GetWorkDay;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.apicenter.util.fina.IdNumberUtil;
import com.lefancrm.apicenter.util.fina.ShowAgingStr;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "结算单相关API")
public class BackendFinaSettlementInfoApiImpl extends BaseServiceImpl implements BackendFinaSettlementInfoApi {
    @Autowired
    private FinaSettlementInfoMapper finaSettlementInfoMapper;
    @Autowired
    private FinaRepaymentInfoMapper finaRepaymentInfoMapper;
    @Autowired
    private FinaUrgeInfoMapper finaUrgeInfoMapper;
    @Autowired
    private FinaApplicantInfoMapper finaApplicantInfoMapper;
    @Autowired
    private FinaApplicantMapper finaApplicantMapper;
    @Autowired
    private FinaSettlementOrgMapper finaSettlementOrgMapper;
    @Autowired
    private FinaSettlementInvestigatorMapper finaSettlementInvestigatorMapper;
    @Autowired
    private FinaSettlementApplicantMapper finaSettlementApplicantMapper;
    @Autowired
    private FinaFileSettlementMapper finaFileSettlementMapper;
    @Autowired
    private FinaSettlementTrackMapper finaSettlementTrackMapper;

    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private FinaApplicantInvestigatorMapper finaApplicantInvestigatorMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Autowired
    private BackendFinaFileApiImpl backendFinaFileApi;

    @Autowired
    private BackendWechatApiImpl backendWechatApi;

    @Autowired
    private FinaSurveyConsignorEfficiencyModelInfoMapper surveyConsignorEfficiencyModelInfoMapper;
    @Autowired
    private FinaHospitalInfoMapper finaHospitalInfoMapper;
    @Autowired
    private FinaSurveyPriceMapper finaSurveyPriceMapper;
    @Autowired
    private FinaTaskInfoMapper finaTaskInfoMapper;
    @Autowired
    private FinaApplicantOrgMapper finaApplicantOrgMapper;
    @Autowired
    private FinaApplicantMoneyMapper finaApplicantMoneyMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;


    @ApiMethod(needLogin = false,descript = "结算单列表",value = "list-fina-settlement-info")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Boolean pageFlag = !"report".equals(apiRequest.getString("report"));//是否分页
        if (pageFlag){
            setBackendPageSize(apiRequest);
        }
        List<FinaSettlementInfo> list = new ArrayList<>();
        //
        String menuCode = apiRequest.getString("menuCode");
        if ("app-claims-list".equals(menuCode)){
            String claimsStates = apiRequest.getString("claimsStates");
            if (!StringUtils.isEmpty(claimsStates)){
                String[] status = claimsStates.split(",");
                List<String> paramStatus = Arrays.asList(status);
                if (paramStatus.contains("1")) {//待申请理赔
                    apiRequest.put("claimsStatesWait","yes");
                }
                if (paramStatus.contains("2")) {//已申请理赔
                    apiRequest.put("claimsStatesEd","yes");
                }
            }
        }

        list = finaSettlementInfoMapper.list(apiRequest);//查询列表
        int size = list.size();
        if (pageFlag){
            size = finaSettlementInfoMapper.listSize(apiRequest);//查询总条数
        }
        for (FinaSettlementInfo finaSettlementInfo : list) {
            finaSettlementInfo.setSettlementStateStr(SettlementEnum.getStateNameByState(finaSettlementInfo.getSettlementState()));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,size,list);
    }

    @ApiMethod(needLogin = false,descript = "结算单列表",value = "info-fina-settlement-info")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long settlementId = apiRequest.getLong("settlementId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        //垫付信息
        FinaSettlementInfo finaSettlementInfo = finaSettlementInfoMapper.selectByPrimaryKey(settlementId);
        finaSettlementInfo.setSettlementStateStr(SettlementEnum.getStateNameByState(finaSettlementInfo.getSettlementState()));
        finaSettlementInfo.setCurUser(userInfo);
        //基础信息
        FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaSettlementInfo.getFinaInfoParentId());
        finaApplicantInfo.setApplyAdvanceMoney(finaSettlementInfo.getLefanPaymentMoney());//申请垫付总金额。取结算单的垫付总金额
        finaApplicantInfo.setDeductibleMoney(finaSettlementInfo.getDeductibleMoney());//免赔额。 取结算单的免赔总额
        finaApplicantInfo.setInsuredAge(IdNumberUtil.IdNOToAge(finaApplicantInfo.getInsuredIdcard()));
        finaApplicantInfo.setFinaHospitalInfo(finaHospitalInfoMapper.selectByPrimaryKey(finaApplicantInfo.getHospitalId()));

        finaSettlementInfo.setFinaApplicantInfo(finaApplicantInfo);
        finaSettlementInfo.setFinaApplicantMoney(finaApplicantMoneyMapper.selectByPrimaryKey(finaApplicantInfo.getId()));
        FinaApplicant finaApplicant = finaApplicantMapper.selectByPrimaryKey(finaApplicantInfo.getFinaId());
        finaSettlementInfo.setFinaApplicant(finaApplicant);

        Map<String,Object> paramMap =  null;
        //还款信息
        paramMap =  new HashMap<String,Object>();
        paramMap.put("settlementInfoId",settlementId);
        List<FinaRepaymentInfo> repaymentInfos = finaRepaymentInfoMapper.list(paramMap);
        finaSettlementInfo.setRepaymentInfos(repaymentInfos);

        Double realMoney = finaSettlementInfo.getRealMoney() == null ? 0D : finaSettlementInfo.getRealMoney();//实际放款总金额
        Double lefanReceivesMoney = finaSettlementInfo.getLefanReceivesMoney() == null ? 0D : finaSettlementInfo.getLefanReceivesMoney();//我司收到的退款总额
        Double yinghuanMoney = realMoney - lefanReceivesMoney;//应还金额(实际放款金额-已收到的退款总额)
        finaSettlementInfo.setNotRepayMoney(yinghuanMoney);


        paramMap =  new HashMap<String,Object>();
        paramMap.put("searchData","opr-ing-or-yes");
        paramMap.put("settlementInfoId",finaSettlementInfo.getId());
        List<FinaRepaymentInfo> temp = finaRepaymentInfoMapper.list(paramMap);
        Double appRepayMoney = temp.stream().mapToDouble(e->Optional.ofNullable(e.getRepaymentMoney()).orElse(0d)).sum();//已申请还款金额
        finaSettlementInfo.setShowRepayAppBtn(false);
        if (yinghuanMoney > 0 && yinghuanMoney.doubleValue() != appRepayMoney.doubleValue()){
            finaSettlementInfo.setShowRepayAppBtn(true);
        }

        //机构信息
        paramMap =  new HashMap<String,Object>();
        paramMap.put("settlementInfoId",settlementId);
        List<FinaSettlementOrg> orgInfos = finaSettlementOrgMapper.list(paramMap);
        for (FinaSettlementOrg orgInfo : orgInfos) {
            orgInfo.setSettlementTaskTypeStr(SettlementTaskEnum.getStateNameByState(orgInfo.getSettlementTaskType()));
            orgInfo.setAgingHtml(ShowAgingStr.getAgingHtml(orgInfo.getOrgAssignTime(),orgInfo.getOrgSubmitTime(),orgInfo.getOrgEndTime()));
        }
        finaSettlementInfo.setOrgInfos(orgInfos);
        //垫付员信息
        paramMap =  new HashMap<String,Object>();
        paramMap.put("settlementInfoId",settlementId);
        List<FinaSettlementInvestigator> surveyUserInfos = finaSettlementInvestigatorMapper.list(paramMap);
        for (FinaSettlementInvestigator surveyUserInfo : surveyUserInfos) {
            surveyUserInfo.setUserTaskTypeStr(SettlementTaskEnum.getStateNameByState(surveyUserInfo.getUserTaskType()));
            surveyUserInfo.setAgingHtml(ShowAgingStr.getAgingHtml(surveyUserInfo.getUserAssignTime(),surveyUserInfo.getUserSubmitTime(),surveyUserInfo.getUserEndTime()));
        }
        finaSettlementInfo.setSurveyUserInfos(surveyUserInfos);

        //垫付明细、出院材料、跟踪信息
        paramMap =  new HashMap<String,Object>();
        paramMap.put("settlementInfoId",settlementId);
        List<FinaSettlementApplicant> settlementApplicants = finaSettlementApplicantMapper.list(paramMap);
        for (FinaSettlementApplicant settlementApplicant : settlementApplicants) {
            settlementApplicant.setFinaNumStr(ChineseUtil.numberToChinese(settlementApplicant.getFinaNum() + ""));
        }
        finaSettlementInfo.setSettlementApplicants(settlementApplicants);
        finaSettlementInfo.setSettlementApplicantsJSON(JSON.toJSONString(settlementApplicants));
        paramMap =  new HashMap<String,Object>();
        paramMap.put("settlementInfoId",settlementId);
        List<FinaFileSettlement> fileSettlements = finaFileSettlementMapper.list(paramMap);
        finaSettlementInfo.setFileSettlements(fileSettlements);
        paramMap =  new HashMap<String,Object>();
        paramMap.put("settlementInfoId",settlementId);
        List<FinaSettlementTrack> settlementTracks = finaSettlementTrackMapper.list(paramMap);
        finaSettlementInfo.setSettlementTracks(settlementTracks);


        //服务费
        if (finaSettlementInfo.getFinshRepaymentTime() == null){//保司终审之前 实时计算服务费
            Double serviceMoney = 0D;
            //案件层服务费
            Double applicantMoney = finaApplicantOrgMapper.serviceMoneyBySettlementId(finaSettlementInfo.getId());
            applicantMoney = applicantMoney == null ? 0D : applicantMoney;
            //结算单层服务费
            Double settlementServiceMoney = orgInfos.stream().mapToDouble(p -> p.getServiceMoney() == null ? 0D : p.getServiceMoney()).sum();
            serviceMoney = applicantMoney + settlementServiceMoney;
            finaSettlementInfo.setServicesMoney(serviceMoney);
            finaSettlementInfo.setSubmitServicesMoney(finaSettlementInfo.getServicesMoney());
        }else{
            finaSettlementInfo.setSubmitServicesMoney(finaSettlementInfo.getSubmitServicesMoney());
        }


        return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
    }

    @ApiMethod(needLogin = false,descript = "结算单列表",value = "operate-fina-settlement-info")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        Long settlementId = apiRequest.getLong("settlementId");
        String btnCode = apiRequest.getString("btnCode");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        FinaSettlementInfo finaSettlementInfo = finaSettlementInfoMapper.selectByPrimaryKey(settlementId);
        if ("generate-settlement-order".equals(btnCode))//生成结算单
        {
            try
            {
                Long finaParentId = apiRequest.getLong("finaParentId");
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaParentId);
                FinaApplicant finaApplicant = finaApplicantMapper.selectByPrimaryKey(finaApplicantInfo.getFinaId());
                finaSettlementInfo = FinaSettlementInfo.class.newInstance();
                finaSettlementInfo.setSettlementNo(SerialNumberUtil.toBuildCaseNo("J") + "" + finaApplicant.getFinaUserName() +"");
                finaSettlementInfo.setSettlementState(SettlementEnum.SETTLEMENT_CYJSZ.getState());
                finaSettlementInfo.setCreateBy(userInfo.getUserName());
                finaSettlementInfo.setCreateTime(new Date());
                finaSettlementInfo.setDeleteFlag(0);
                finaSettlementInfo.setUpdateTime(new Date());
                finaSettlementInfo.setUpdateBy(userInfo.getUserName());
                finaSettlementInfo.setFinaInfoParentId(finaParentId);
                finaSettlementInfo.setIsBad(0);
                finaSettlementInfoMapper.insert(finaSettlementInfo);
                String ids = apiRequest.getString("ids");
                //根据ids查询案件集合,统计金额相关数据
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("searchData","settlement-ids");
                paramMap.put("ids",ids);
                List<FinaApplicantInfo> data = finaApplicantInfoMapper.list(paramMap);
                Double deductibleMoney = data.stream().mapToDouble(e->Optional.ofNullable(e.getDeductibleMoney()).orElse(0d)).sum();//免赔额
                finaSettlementInfo.setDeductibleMoney(deductibleMoney);
                Double lefanPaymentMoney = data.stream().mapToDouble(e->Optional.ofNullable(e.getApplyAdvanceMoney()).orElse(0d)).sum();//申请垫付总金额
                finaSettlementInfo.setLefanPaymentMoney(lefanPaymentMoney);
                Double realMoney = data.stream().mapToDouble(e->Optional.ofNullable(e.getActualMoney()).orElse(0d)).sum(); //实际放款总金额
                finaSettlementInfo.setRealMoney(realMoney);

                //获取最新的一条垫付员信息
                paramMap =  new HashMap<String,Object>();
                paramMap.put("finaInfoId",finaApplicantInfo.getId());
                FinaApplicantInvestigator finaApplicantInvestigator = finaApplicantInvestigatorMapper.selectByOne(paramMap);
                if (finaApplicantInvestigator == null){
                    return new ApiResponse(ApiMsgEnum.FAIL);
                }
                Long surveyUserId = finaApplicantInvestigator.getSurveyUserId();//查询案件相关的最新一条调查员的信息
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyUserId);

                //插入机构案件表
                FinaSettlementOrg finaSettlementOrg = FinaSettlementOrg.class.newInstance();
                finaSettlementOrg.setSettlementInfoId(finaSettlementInfo.getId());
                finaSettlementOrg.setSurveyOrgId(surveyInvestigator.getOrgId());
                finaSettlementOrg.setSurveyOrgName(surveyInvestigator.getOrgName());
                finaSettlementOrg.setSettlementOrgState(1);
                finaSettlementOrg.setOrgAssignTime(new Date());
                finaSettlementOrg.setSettlementTaskType(SettlementTaskEnum.CYJS.getState());
                finaSettlementOrg.setOverAging(0D);
                finaSettlementOrg.setFinshAging(0D);
                finaSettlementOrg.setCreateTime(new Date());
                finaSettlementOrg.setCreateBy(userInfo.getUserName());
                finaSettlementOrg.setUpdateTime(new Date());
                finaSettlementOrg.setUpdateBy(userInfo.getUserName());
                finaSettlementOrg.setDeleteFlag(0);
                //根据保险公司医院区域获取材料收集时效
                Long entrustOrgId = finaApplicantInfo.getEntrustOrgId();
                Long taskId = SettlementTaskEnum.CYJS.getCurToDataId();
                Long hospitalId = finaApplicantInfo.getHospitalId();
                FinaHospitalInfo finaHospitalInfo = finaHospitalInfoMapper.selectByPrimaryKey(hospitalId);
                Long districtId = finaHospitalInfo.getDistrictId();
                Integer days = surveyConsignorEfficiencyModelInfoMapper.getDays(entrustOrgId, taskId, districtId);//委托方时效
                days = (days == null) ? 0 : days;
                days = (days - 1) < 0 ? 0 : days - 1;
                Date endTime = GetWorkDay.calLeaveEndDate(finaSettlementOrg.getOrgAssignTime(),null,days,2);
                finaSettlementOrg.setOrgEndTime(endTime);
                finaSettlementOrg.setOrgAging(Double.valueOf(days));
                finaSettlementOrgMapper.insert(finaSettlementOrg);

                //插入调查员案件表
                FinaSettlementInvestigator finaSettlementInvestigator = FinaSettlementInvestigator.class.newInstance();
                finaSettlementInvestigator.setSettlementInfoId(finaSettlementInfo.getId());
                finaSettlementInvestigator.setSettlementOrgId(finaSettlementOrg.getId());
                finaSettlementInvestigator.setSurveyOrgId(surveyInvestigator.getOrgId());
                finaSettlementInvestigator.setSurveyOrgName(surveyInvestigator.getOrgName());
                finaSettlementInvestigator.setSurveyUserId(surveyInvestigator.getUserId());
                finaSettlementInvestigator.setSurveyUserName(surveyInvestigator.getRealName());
                finaSettlementInvestigator.setState(1);
                finaSettlementInvestigator.setUserAssignTime(new Date());
                finaSettlementInvestigator.setUserTaskType(SettlementTaskEnum.CYJS.getState());
                finaSettlementInvestigator.setOverAging(0D);
                finaSettlementInvestigator.setFinshAging(0D);
                finaSettlementInvestigator.setCreateTime(new Date());
                finaSettlementInvestigator.setCreateBy(userInfo.getUserName());
                finaSettlementInvestigator.setUpdateTime(new Date());
                finaSettlementInvestigator.setUpdateBy(userInfo.getUserName());
                finaSettlementInvestigator.setDeleteFlag(0);
                finaSettlementInvestigator.setUserEndTime(endTime);
                finaSettlementInvestigator.setUserAging(Double.valueOf(days));
                finaSettlementInvestigatorMapper.insert(finaSettlementInvestigator);

                //插入结算单案件关联表
                paramMap =  new HashMap<String,Object>();
                paramMap.put("ids",ids);
                paramMap.put("settlementId",finaSettlementInfo.getId());
                finaSettlementApplicantMapper.generate(paramMap);

                //同步案件状态
                synCaseData(SettlementEnum.SETTLEMENT_CYJSZ,finaSettlementInfo);

                finaSettlementInfo.setUpdateBy(userInfo.getUserName());
                finaSettlementInfo.setUpdateTime(new Date());
                finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);

                //发送微信通知
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","出院结算");
                msgMap.put("content","你有出院结算任务，请尽快进行处理！");
                msgMap.put("keyWords","结算单编号：" + finaSettlementInfo.getSettlementNo() + "\n" + "被保险人：" + finaApplicantInfo.getInsuredName() + "\n" + "任务截止日期：" + simpleDateFormat.format(finaSettlementInvestigator.getUserEndTime()));
                backendWechatApi.send(surveyInvestigator.getUserId(),msgMap);

                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else if ("commit-cyjs".equals(btnCode))//小程序-提交结算单
        {
            //保存出院结算相关信息
            finaSettlementInfo.setMedicalMoney(apiRequest.getDouble("medicalMoney"));
            finaSettlementInfo.setCustomerPaymentMoney(apiRequest.getDouble("customerPaymentMoney"));
            finaSettlementInfo.setMedicalInsuranceMoney(apiRequest.getDouble("medicalInsuranceMoney"));
            finaSettlementInfo.setRefundMoney(apiRequest.getDouble("refundMoney"));
            finaSettlementInfo.setCustomerReceivesMoney(apiRequest.getDouble("customerReceivesMoney"));
            finaSettlementInfo.setLefanReceivesMoney(apiRequest.getDouble("lefanReceivesMoney"));
            finaSettlementInfo.setRefundChannel(apiRequest.getInt("refundChannel"));
            finaSettlementInfo.setLeaveHospitalFile(apiRequest.getInt("leaveHospitalFile"));
            finaSettlementInfo.setRefundMode(apiRequest.getInt("refundMode"));
            if (finaSettlementInfo.getRealMoney() == null) {
                finaSettlementInfo.setRealMoney(0D);
            }
            if (finaSettlementInfo.getLefanReceivesMoney() == null){
                finaSettlementInfo.setLefanReceivesMoney(0D);
            }
            finaSettlementInfo.setNoRepaymentMoney(finaSettlementInfo.getRealMoney() - finaSettlementInfo.getLefanReceivesMoney());
            //如果材料齐全 且 退款情况已完成 则进入下一步。
            if (finaSettlementInfo.getLeaveHospitalFile() == 2 && finaSettlementInfo.getRefundMode() == 2)
            {
                Long settlementInvestigatorId = apiRequest.getLong("settlementInvestigatorId");
                FinaSettlementInvestigator finaSettlementInvestigator = finaSettlementInvestigatorMapper.selectByPrimaryKey(settlementInvestigatorId);
                FinaSettlementOrg finaSettlementOrg = finaSettlementOrgMapper.selectByPrimaryKey(finaSettlementInvestigator.getSettlementOrgId());

                finaSettlementInvestigator.setState(2);
                finaSettlementInvestigator.setUserSubmitTime(new Date());
                int userAging = GetWorkDay.calLeaveDays(finaSettlementInvestigator.getUserAssignTime(), finaSettlementInvestigator.getUserEndTime(), 2);
                int finshAging = GetWorkDay.calLeaveDays(finaSettlementInvestigator.getUserAssignTime(), finaSettlementInvestigator.getUserSubmitTime(), 2);
                finaSettlementInvestigator.setUserAging(new Double(userAging));//考核时效
                finaSettlementInvestigator.setFinshAging(new Double(finshAging));//完成时效
                finaSettlementInvestigator.setOverAging(finshAging > userAging ? Math.abs(userAging - finshAging) : 0D);//超期时效
                finaSettlementInvestigator.setUpdateBy(userInfo.getUserName());
                finaSettlementInvestigator.setUpdateTime(new Date());
                //计算服务费、分值
                //根据调查方机构ID、任务类型、医院获取价格
                Long surveyOrgId = finaSettlementOrg.getSurveyOrgId();
                Long taskId = SettlementTaskEnum.getDataIdByState(finaSettlementInvestigator.getUserTaskType());
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaSettlementInfo.getFinaInfoParentId());
                FinaHospitalInfo finaHospitalInfo = finaHospitalInfoMapper.selectByPrimaryKey(finaApplicantInfo.getHospitalId());
                Long districtId = finaHospitalInfo.getDistrictId();
                Double price = finaSurveyPriceMapper.getPrice(surveyOrgId, taskId, districtId);
                finaSettlementInvestigator.setServiceMoney(price);
                FinaTaskInfo finaTaskInfo = finaTaskInfoMapper.selectByPrimaryKey(taskId);
                finaSettlementInvestigator.setScore(finaTaskInfo.getScore());
                finaSettlementInvestigatorMapper.updateByPrimaryKey(finaSettlementInvestigator);

                finaSettlementOrg.setSettlementOrgState(2);
                finaSettlementOrg.setOrgSubmitTime(new Date());
                int orgAging = GetWorkDay.calLeaveDays(finaSettlementOrg.getOrgAssignTime(), finaSettlementOrg.getOrgEndTime(), 2);
                finshAging = GetWorkDay.calLeaveDays(finaSettlementOrg.getOrgAssignTime(), finaSettlementOrg.getOrgSubmitTime(), 2);
                finaSettlementOrg.setOrgAging(new Double(orgAging));//考核时效
                finaSettlementOrg.setFinshAging(new Double(finshAging));//完成时效
                finaSettlementOrg.setOverAging(finshAging > orgAging ? Math.abs(orgAging - finshAging) : 0D);//超期时效
                finaSettlementOrg.setUpdateBy(userInfo.getUserName());
                finaSettlementOrg.setUpdateTime(new Date());
                //计算机构服务费
                finaSettlementOrg.setServiceMoney(finaSettlementInvestigator.getServiceMoney());

                finaSettlementOrgMapper.updateByPrimaryKey(finaSettlementOrg);

                finaSettlementInfo.setSettlementState(SettlementEnum.SETTLEMENT_DSQLP.getState());
                finaSettlementInfo.setFinshSettlementTime(new Date());
                finaSettlementInfo.setUpdateBy(userInfo.getUserName());
                finaSettlementInfo.setUpdateTime(new Date());
                finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);

                //同步案件状态
                synCaseData(SettlementEnum.SETTLEMENT_DSQLP,finaSettlementInfo);

                //生成结算单.保存结算单路径
                //??????????????????????
            }
            finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        else if ("app-claims".equals(btnCode))//申请理赔
        {
            finaSettlementInfo.setRepaymentState(1);
            finaSettlementInfo.setSettlementState(SettlementEnum.SETTLEMENT_DHK.getState());
            finaSettlementInfo.setFinshClaimsTime(new Date());
            finaSettlementInfo.setUpdateBy(userInfo.getUserName());
            finaSettlementInfo.setUpdateTime(new Date());
            if (finaSettlementInfo.getRefundState() == null) {
                finaSettlementInfo.setRefundState(0);
            }
            finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
            //同步案件状态
            synCaseData(SettlementEnum.SETTLEMENT_DHK,finaSettlementInfo);

            //如果应还金额是0 且 我司收到退款金额为0或者已确认。则到保司终审
            Double realMoney = finaSettlementInfo.getRealMoney() == null ? 0D : finaSettlementInfo.getRealMoney();//实际放款总金额
            Double lefanReceivesMoney = finaSettlementInfo.getLefanReceivesMoney() == null ? 0D : finaSettlementInfo.getLefanReceivesMoney();//我司收到的退款总额
            Double yinghuanMoney = realMoney - lefanReceivesMoney;//应还金额(实际放款金额-已收到的退款总额)

            if (yinghuanMoney == 0 && (lefanReceivesMoney == 0 || finaSettlementInfo.getRefundState() == 1)){
                finaSettlementInfo.setRepaymentMoney(yinghuanMoney);
                finaSettlementInfo.setRefundState(1);
                finaSettlementInfo.setNoRepaymentMoney(0D);
                finaSettlementInfo.setFinshRepaymentTime(new Date());
                finaSettlementInfo.setSettlementState(SettlementEnum.SETTLEMENT_DBSSH.getState());
                finaSettlementInfo.setFinshRepaymentTime(new Date());
                finaSettlementInfo.setUpdateBy(userInfo.getUserName());
                finaSettlementInfo.setUpdateTime(new Date());
                finaSettlementInfo.setRepaymentState(3);
                //同步案件状态
                synCaseData(SettlementEnum.SETTLEMENT_DBSSH,finaSettlementInfo);

                //机构信息
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("settlementInfoId",finaSettlementInfo.getId());
                List<FinaSettlementOrg> orgInfos = finaSettlementOrgMapper.list(paramMap);
                Double serviceMoney = 0D;
                //案件层服务费
                Double applicantMoney = finaApplicantOrgMapper.serviceMoneyBySettlementId(finaSettlementInfo.getId());
                applicantMoney = applicantMoney == null ? 0D : applicantMoney;
                //结算单层服务费
                Double settlementServiceMoney = orgInfos.stream().mapToDouble(p -> p.getServiceMoney() == null ? 0D : p.getServiceMoney()).sum();
                serviceMoney = applicantMoney + settlementServiceMoney;
                finaSettlementInfo.setServicesMoney(serviceMoney);
                finaSettlementInfo.setSubmitServicesMoney(finaSettlementInfo.getServicesMoney());
                finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaSettlementInfo.getFinaInfoParentId());
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(finaApplicantInfo.getEntrustOrgId());
                if (surveyConsignor.getAdvanceParty() == 1){//如果是乐凡兜底 则直接跳过保司审核到服务费待开票
                    finaSettlementInfo.setSubmitServicesMoney(finaSettlementInfo.getServicesMoney());
                    finaSettlementInfo.setSettlementState(SettlementEnum.SETTLEMENT_FWFDKP.getState());
                    finaSettlementInfo.setFinshPassedTime(new Date());
                    finaSettlementInfo.setUpdateBy(userInfo.getUserName());
                    finaSettlementInfo.setUpdateTime(new Date());
                    finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
                    //同步案件状态
                    synCaseData(SettlementEnum.SETTLEMENT_FWFDKP,finaSettlementInfo);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        else if ("repay-app".equals(btnCode))//还款申请
        {
            try
            {
                //增加还款记录表
                String codeType = apiRequest.getString("codeType");
                FinaRepaymentInfo finaRepaymentInfo = FinaRepaymentInfo.class.newInstance();
                finaRepaymentInfo.setSettlementInfoId(finaSettlementInfo.getId());
                finaRepaymentInfo.setRepaymentNo(SerialNumberUtil.toBuilNo("SE"));
                finaRepaymentInfo.setRepaymentMoney(apiRequest.getDouble("repaymentMoney"));
                finaRepaymentInfo.setRepaymentSource(apiRequest.getInt("repaymentSource"));
                finaRepaymentInfo.setRepaymentType(1);//还款方式(1.正常还款，2.电话催收还款，3.现成催收还款，4.其他)
                finaRepaymentInfo.setRepaymentTime(DateUtils.parseDate(apiRequest.getString("repayTime"),"yyyy-MM-dd"));
                finaRepaymentInfo.setApplyUserId(userInfo.getUserId());
                finaRepaymentInfo.setApplyUserName(userInfo.getUserName());
                finaRepaymentInfo.setRepaymentState(1);//1.待财务审核,2:财务审核通过,3.财务审核驳回
                finaRepaymentInfo.setCreateTime(new Date());
                finaRepaymentInfo.setCreateBy(userInfo.getUserName());
                finaRepaymentInfo.setUpdateTime(new Date());
                finaRepaymentInfo.setUpdateBy(userInfo.getUserName());
                finaRepaymentInfo.setDeleteFlag(0);
                finaRepaymentInfoMapper.insert(finaRepaymentInfo);
                switch (codeType){
                    case "all-commit" ://足额还款
                        break;
                    case "cur-commit"://仅提交本次还款
                        break;
                    case "not-all-commit-to-risk"://不足额还款，转风险案件
                        //如果转风险案件。则将结算单关联的案件都打上风险案件的标致
                        Map<String,Object> paramMap =  new HashMap<String,Object>();
                        paramMap.put("settlementId",settlementId);
                        paramMap.put("caseType",2);//案件类型：1、正常案件，2、风险案件，3、坏账案件
                        finaSettlementApplicantMapper.updCaseType(paramMap);
                        break;
                }
                String json = apiRequest.getString("files");
                backendFinaFileApi.saveFile(finaRepaymentInfo.getId(), FileTableEnum.FINA_REPAYMENT_INFO_ATTR,userInfo,json);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        else if ("cw-yes".equals(btnCode))//财务审核通过
        {
            Long repaymentId = apiRequest.getLong("repaymentId");//还款单ID
            FinaRepaymentInfo finaRepaymentInfo = finaRepaymentInfoMapper.selectByPrimaryKey(repaymentId);
            finaRepaymentInfo.setRepaymentState(2);//1.待财务审核,2:财务审核通过,3.财务审核驳回
            finaRepaymentInfo.setFinanceUserId(userInfo.getUserId());
            finaRepaymentInfo.setFinanceUserName(userInfo.getUserName());
            finaRepaymentInfo.setRepaymentPassedTime(new Date());
            finaRepaymentInfo.setUpdateTime(new Date());
            finaRepaymentInfo.setUpdateBy(userInfo.getUserName());
            finaRepaymentInfoMapper.updateByPrimaryKey(finaRepaymentInfo);

            Double realMoney = finaSettlementInfo.getRealMoney() == null ? 0D : finaSettlementInfo.getRealMoney();//实际放款总金额
            Double lefanReceivesMoney = finaSettlementInfo.getLefanReceivesMoney() == null ? 0D : finaSettlementInfo.getLefanReceivesMoney();//我司收到的退款总额
            Double yinghuanMoney = realMoney - lefanReceivesMoney;//应还金额(实际放款金额-已收到的退款总额)
            if (finaSettlementInfo.getRepaymentMoney() == null) {
                finaSettlementInfo.setRepaymentMoney(0D);
            }
            finaSettlementInfo.setRepaymentMoney(finaSettlementInfo.getRepaymentMoney() + finaRepaymentInfo.getRepaymentMoney());//已还款金额 (已还+本次)
            finaSettlementInfo.setNoRepaymentMoney(yinghuanMoney - finaSettlementInfo.getRepaymentMoney());//未还款金额(应还-已还)
            finaSettlementInfo.setRepaymentState(2);


            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("settlementInfoId",finaSettlementInfo.getId());
            List<FinaSettlementOrg> orgInfos = finaSettlementOrgMapper.list(paramMap);
            Double serviceMoney = 0D;
            //案件层服务费
            Double applicantMoney = finaApplicantOrgMapper.serviceMoneyBySettlementId(finaSettlementInfo.getId());
            applicantMoney = applicantMoney == null ? 0D : applicantMoney;
            //结算单层服务费
            Double settlementServiceMoney = orgInfos.stream().mapToDouble(p -> p.getServiceMoney() == null ? 0D : p.getServiceMoney()).sum();
            serviceMoney = applicantMoney + settlementServiceMoney;
            finaSettlementInfo.setServicesMoney(serviceMoney);
            finaSettlementInfo.setSubmitServicesMoney(finaSettlementInfo.getServicesMoney());

            //如果已还款金额等于应还款金额。则案件状态到保司终审
            if (finaSettlementInfo.getRepaymentMoney().doubleValue() == yinghuanMoney.doubleValue()){
                finaSettlementInfo.setUrgeState(2);
                finaSettlementInfo.setRepaymentMoney(yinghuanMoney);
                finaSettlementInfo.setNoRepaymentMoney(0D);
                finaSettlementInfo.setFinshRepaymentTime(new Date());
                finaSettlementInfo.setSettlementState(SettlementEnum.SETTLEMENT_DBSSH.getState());
                finaSettlementInfo.setFinshRepaymentTime(new Date());
                finaSettlementInfo.setUpdateBy(userInfo.getUserName());
                finaSettlementInfo.setUpdateTime(new Date());
                finaSettlementInfo.setRepaymentState(3);
                //同步案件状态
                synCaseData(SettlementEnum.SETTLEMENT_DBSSH,finaSettlementInfo);

                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaSettlementInfo.getFinaInfoParentId());
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(finaApplicantInfo.getEntrustOrgId());
                if (surveyConsignor.getAdvanceParty() == 1){//如果是乐凡兜底 则直接跳过保司审核到服务费待开票
                    finaSettlementInfo.setSubmitServicesMoney(finaSettlementInfo.getServicesMoney());
                    finaSettlementInfo.setSettlementState(SettlementEnum.SETTLEMENT_FWFDKP.getState());
                    finaSettlementInfo.setFinshPassedTime(new Date());
                    finaSettlementInfo.setUpdateBy(userInfo.getUserName());
                    finaSettlementInfo.setUpdateTime(new Date());
                    finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
                    //同步案件状态
                    synCaseData(SettlementEnum.SETTLEMENT_FWFDKP,finaSettlementInfo);
                }


            }
            finaSettlementInfo.setUpdateBy(userInfo.getUserName());
            finaSettlementInfo.setUpdateTime(new Date());
            finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        else if ("cw-no".equals(btnCode))//财务审核退回
        {
            Long repaymentId = apiRequest.getLong("repaymentId");//还款单ID
            String oprRemark = apiRequest.getString("oprRemark");//审核意见
            FinaRepaymentInfo finaRepaymentInfo = finaRepaymentInfoMapper.selectByPrimaryKey(repaymentId);
            finaRepaymentInfo.setRepaymentState(3);//1.待财务审核,2:财务审核通过,3.财务审核驳回
            finaRepaymentInfo.setFinanceUserId(userInfo.getUserId());
            finaRepaymentInfo.setFinanceUserName(userInfo.getUserName());
            finaRepaymentInfo.setUpdateTime(new Date());
            finaRepaymentInfo.setUpdateBy(userInfo.getUserName());
            finaRepaymentInfo.setOprRemark(oprRemark);
            finaRepaymentInfoMapper.updateByPrimaryKey(finaRepaymentInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        else if ("cw-refund-ok".equals(btnCode))//财务确认退款金额
        {
            Double lefanRefundMoney = apiRequest.getDouble("lefanRefundMoney");
            Date refundOkTime = DateUtils.parseDate(apiRequest.getString("refundOkTime"), "yyyy-MM-dd HH:mm:ss");
            finaSettlementInfo.setLefanReceivesMoney(lefanRefundMoney);
            finaSettlementInfo.setLefanReceivesTime(refundOkTime);
            finaSettlementInfo.setLefanReceivesUserName(userInfo.getUserName());
            finaSettlementInfo.setUpdateTime(new Date());
            finaSettlementInfo.setUpdateBy(userInfo.getUserName());
            finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        else if ("safe-opr-yes".equals(btnCode))//保司审核通过
        {
            finaSettlementInfo.setSubmitServicesMoney(apiRequest.getDouble("servicesMoney"));
            finaSettlementInfo.setSettlementState(SettlementEnum.SETTLEMENT_FWFDKP.getState());
            finaSettlementInfo.setFinshPassedTime(new Date());
            finaSettlementInfo.setUpdateBy(userInfo.getUserName());
            finaSettlementInfo.setUpdateTime(new Date());
            finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);

            //同步案件状态
            synCaseData(SettlementEnum.SETTLEMENT_FWFDKP,finaSettlementInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        else if ("convert-bad".equals(btnCode))//转坏帐
        {
            finaSettlementInfo.setIsBad(1);
            finaSettlementInfo.setUpdateBy(userInfo.getUserName());
            finaSettlementInfo.setUpdateTime(new Date());
            finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        else if ("recall-bad".equals(btnCode))//取消坏账。转正常结算单
        {
            finaSettlementInfo.setIsBad(0);
            finaSettlementInfo.setUpdateBy(userInfo.getUserName());
            finaSettlementInfo.setUpdateTime(new Date());
            finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        else if ("operate-bad".equals(btnCode))//坏账相关处理
        {
            try {
                FinaUrgeInfo finaUrgeInfo = FinaUrgeInfo.class.newInstance();
                finaUrgeInfo.setSettlementInfoId(finaSettlementInfo.getId());
                finaUrgeInfo.setCreateTime(new Date());
                finaUrgeInfo.setCreateBy(userInfo.getUserName());
                finaUrgeInfo.setUpdateTime(new Date());
                finaUrgeInfo.setUpdateBy(userInfo.getUserName());
                finaUrgeInfo.setDeleteFlag(0);
                String urgeType = apiRequest.getString("urgeType");
                switch (urgeType)
                {
                    case "tel-urge" ://电话催收
                        finaUrgeInfo.setUrgeType(1);
                        finaUrgeInfo.setLinkUser(apiRequest.getString("linkUser"));
                        finaUrgeInfo.setLinkTel(apiRequest.getString("linkTel"));
                        finaUrgeInfo.setLinkTime(DateUtils.parseDate(apiRequest.getString("linkTime"),"yyyy-MM-dd"));
                        finaUrgeInfo.setUrgeDesc(apiRequest.getString("urgeDesc"));
                        finaUrgeInfo.setUrgeMoney(apiRequest.getDouble("urgeMoney"));
                        finaUrgeInfo.setRepaymentTime(DateUtils.parseDate(apiRequest.getString("repaymentTime"),"yyyy-MM-dd"));
                        finaUrgeInfoMapper.insert(finaUrgeInfo);
                        //添加凭证
                        String json = apiRequest.getString("files");
                        backendFinaFileApi.saveFile(finaUrgeInfo.getId(),FileTableEnum.FINA_URGE_INFO_ATTR_TEL,userInfo,json);

                        //添加还款记录
                        FinaRepaymentInfo finaRepaymentInfo = FinaRepaymentInfo.class.newInstance();
                        finaRepaymentInfo.setSettlementInfoId(finaSettlementInfo.getId());
                        finaRepaymentInfo.setRepaymentNo(SerialNumberUtil.toBuilNo("SE"));
                        finaRepaymentInfo.setRepaymentMoney(finaUrgeInfo.getUrgeMoney());
                        finaRepaymentInfo.setRepaymentSource(2);//1.保司还款,2.客户还款
                        finaRepaymentInfo.setRepaymentType(2);//还款方式(1.正常还款，2.电话催收还款，3.现成催收还款，4.其他)
                        finaRepaymentInfo.setRepaymentTime(finaUrgeInfo.getRepaymentTime());
                        finaRepaymentInfo.setApplyUserId(userInfo.getUserId());
                        finaRepaymentInfo.setApplyUserName(userInfo.getUserName());
                        finaRepaymentInfo.setRepaymentState(1);//1.待财务审核,2:财务审核通过,3.财务审核驳回
                        finaRepaymentInfo.setCreateTime(new Date());
                        finaRepaymentInfo.setCreateBy(userInfo.getUserName());
                        finaRepaymentInfo.setUpdateTime(new Date());
                        finaRepaymentInfo.setUpdateBy(userInfo.getUserName());
                        finaRepaymentInfo.setDeleteFlag(0);
                        finaRepaymentInfo.setUrgeId(finaUrgeInfo.getId());
                        finaRepaymentInfoMapper.insert(finaRepaymentInfo);
                        break;
                    case "address-urge"://上门催收
                        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(apiRequest.getLong("urgeUserId"));
                        finaUrgeInfo.setUrgeType(2);
                        finaUrgeInfo.setUrgeUserId(surveyInvestigator.getUserId());
                        finaUrgeInfo.setUrgeUserName(surveyInvestigator.getRealName());
                        finaUrgeInfoMapper.insert(finaUrgeInfo);

                        //添加还款几率
//                        finaRepaymentInfo = FinaRepaymentInfo.class.newInstance();
//                        finaRepaymentInfo.setSettlementInfoId(finaSettlementInfo.getId());
//                        finaRepaymentInfo.setRepaymentNo(SerialNumberUtil.toBuilNo("SE"));
//                        finaRepaymentInfo.setRepaymentMoney(finaUrgeInfo.getUrgeMoney());
//                        finaRepaymentInfo.setRepaymentSource(2);//1.保司还款,2.客户还款
//                        finaRepaymentInfo.setRepaymentType(3);//还款方式(1.正常还款，2.电话催收还款，3.现成催收还款，4.其他)
//                        finaRepaymentInfo.setRepaymentTime(finaUrgeInfo.getRepaymentTime());
//                        finaRepaymentInfo.setApplyUserId(userInfo.getUserId());
//                        finaRepaymentInfo.setApplyUserName(userInfo.getUserName());
//                        finaRepaymentInfo.setRepaymentState(1);//1.待财务审核,2:财务审核通过,3.财务审核驳回
//                        finaRepaymentInfo.setCreateTime(new Date());
//                        finaRepaymentInfo.setCreateBy(userInfo.getUserName());
//                        finaRepaymentInfo.setUpdateTime(new Date());
//                        finaRepaymentInfo.setUpdateBy(userInfo.getUserName());
//                        finaRepaymentInfo.setDeleteFlag(0);
//                        finaRepaymentInfoMapper.insert(finaRepaymentInfo);

                        //分派机构、调查员催收任务
                        //插入机构案件表
                        FinaSettlementOrg finaSettlementOrg = FinaSettlementOrg.class.newInstance();
                        finaSettlementOrg.setSettlementInfoId(finaSettlementInfo.getId());
                        finaSettlementOrg.setSurveyOrgId(surveyInvestigator.getOrgId());
                        finaSettlementOrg.setSurveyOrgName(surveyInvestigator.getOrgName());
                        finaSettlementOrg.setSettlementOrgState(1);
                        finaSettlementOrg.setOrgAssignTime(new Date());
                        finaSettlementOrg.setSettlementTaskType(SettlementTaskEnum.XCCS.getState());
                        finaSettlementOrg.setOverAging(0D);
                        finaSettlementOrg.setFinshAging(0D);
                        finaSettlementOrg.setCreateTime(new Date());
                        finaSettlementOrg.setCreateBy(userInfo.getUserName());
                        finaSettlementOrg.setUpdateTime(new Date());
                        finaSettlementOrg.setUpdateBy(userInfo.getUserName());
                        finaSettlementOrg.setDeleteFlag(0);
                        //根据保险公司医院区域获取现场催收时效
                        FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaSettlementInfo.getFinaInfoParentId());
                        Long entrustOrgId = finaApplicantInfo.getEntrustOrgId();
                        Long taskId = SettlementTaskEnum.getDataIdByState(finaSettlementOrg.getSettlementTaskType());
                        Long hospitalId = finaApplicantInfo.getHospitalId();
                        FinaHospitalInfo finaHospitalInfo = finaHospitalInfoMapper.selectByPrimaryKey(hospitalId);
                        Long districtId = finaHospitalInfo.getDistrictId();
                        Integer days = surveyConsignorEfficiencyModelInfoMapper.getDays(entrustOrgId, taskId, districtId);//委托方时效
                        days = (days == null) ? 0 : days;
                        days = (days - 1) < 0 ? 0 : days - 1;
                        Date endTime = GetWorkDay.calLeaveEndDate(finaSettlementOrg.getOrgAssignTime(),null,days,2);
                        finaSettlementOrg.setOrgEndTime(endTime);
                        finaSettlementOrg.setOrgAging(Double.valueOf(days));
                        finaSettlementOrgMapper.insert(finaSettlementOrg);

                        //插入调查员案件表
                        FinaSettlementInvestigator finaSettlementInvestigator = FinaSettlementInvestigator.class.newInstance();
                        finaSettlementInvestigator.setSettlementInfoId(finaSettlementInfo.getId());
                        finaSettlementInvestigator.setSettlementOrgId(finaSettlementOrg.getId());
                        finaSettlementInvestigator.setSurveyOrgId(surveyInvestigator.getOrgId());
                        finaSettlementInvestigator.setSurveyOrgName(surveyInvestigator.getOrgName());
                        finaSettlementInvestigator.setSurveyUserId(surveyInvestigator.getUserId());
                        finaSettlementInvestigator.setSurveyUserName(surveyInvestigator.getRealName());
                        finaSettlementInvestigator.setState(1);
                        finaSettlementInvestigator.setUserAssignTime(new Date());
                        finaSettlementInvestigator.setUserTaskType(SettlementTaskEnum.XCCS.getState());
                        finaSettlementInvestigator.setOverAging(0D);
                        finaSettlementInvestigator.setFinshAging(0D);
                        finaSettlementInvestigator.setCreateTime(new Date());
                        finaSettlementInvestigator.setCreateBy(userInfo.getUserName());
                        finaSettlementInvestigator.setUpdateTime(new Date());
                        finaSettlementInvestigator.setUpdateBy(userInfo.getUserName());
                        finaSettlementInvestigator.setDeleteFlag(0);
                        finaSettlementInvestigator.setUserEndTime(endTime);
                        finaSettlementInvestigator.setUserAging(Double.valueOf(days));
                        finaSettlementInvestigator.setUrgeId(finaUrgeInfo.getId());
                        finaSettlementInvestigatorMapper.insert(finaSettlementInvestigator);

                        break;
                    case "ls-urge": //律师函
                        finaUrgeInfo.setUrgeType(3);
                        finaUrgeInfo.setUrgeMoney(apiRequest.getDouble("urgeMoney"));
                        finaUrgeInfo.setRepaymentTime(DateUtils.parseDate(apiRequest.getString("repaymentTime"),"yyyy-MM-dd"));
                        finaUrgeInfoMapper.insert(finaUrgeInfo);
                        String lsFilesOne = apiRequest.getString("lsFilesOne");//律师函材料
                        backendFinaFileApi.saveFile(finaUrgeInfo.getId(),FileTableEnum.FINA_URGE_INFO_ATTR_LS_ONE,userInfo,lsFilesOne);
                        String lsFilesTwo = apiRequest.getString("lsFilesTwo");//律师函凭证
                        backendFinaFileApi.saveFile(finaUrgeInfo.getId(),FileTableEnum.FINA_URGE_INFO_ATTR_LS_TWO,userInfo,lsFilesTwo);

                        finaRepaymentInfo = FinaRepaymentInfo.class.newInstance();
                        finaRepaymentInfo.setSettlementInfoId(finaSettlementInfo.getId());
                        finaRepaymentInfo.setRepaymentNo(SerialNumberUtil.toBuilNo("SE"));
                        finaRepaymentInfo.setRepaymentMoney(finaUrgeInfo.getUrgeMoney());
                        finaRepaymentInfo.setRepaymentSource(2);//1.保司还款,2.客户还款
                        finaRepaymentInfo.setRepaymentType(4);//还款方式(1.正常还款，2.电话催收还款，3.现成催收还款，4.其他)
                        finaRepaymentInfo.setRepaymentTime(finaUrgeInfo.getRepaymentTime());
                        finaRepaymentInfo.setApplyUserId(userInfo.getUserId());
                        finaRepaymentInfo.setApplyUserName(userInfo.getUserName());
                        finaRepaymentInfo.setRepaymentState(1);//1.待财务审核,2:财务审核通过,3.财务审核驳回
                        finaRepaymentInfo.setCreateTime(new Date());
                        finaRepaymentInfo.setCreateBy(userInfo.getUserName());
                        finaRepaymentInfo.setUpdateTime(new Date());
                        finaRepaymentInfo.setUpdateBy(userInfo.getUserName());
                        finaRepaymentInfo.setDeleteFlag(0);
                        finaRepaymentInfo.setUrgeId(finaUrgeInfo.getId());
                        finaRepaymentInfoMapper.insert(finaRepaymentInfo);
                        break;
                    case "ss-urge": //诉讼
                        finaUrgeInfo.setUrgeType(4);
                        finaUrgeInfo.setUrgeMoney(apiRequest.getDouble("urgeMoney"));
                        finaUrgeInfo.setRepaymentTime(DateUtils.parseDate(apiRequest.getString("repaymentTime"),"yyyy-MM-dd"));
                        finaUrgeInfoMapper.insert(finaUrgeInfo);
                        String ssFilesOne = apiRequest.getString("ssFilesOne");//诉讼材料
                        backendFinaFileApi.saveFile(finaUrgeInfo.getId(),FileTableEnum.FINA_URGE_INFO_ATTR_SS_ONE,userInfo,ssFilesOne);
                        String ssFilesTwo = apiRequest.getString("ssFilesTwo");//诉讼凭证
                        backendFinaFileApi.saveFile(finaUrgeInfo.getId(),FileTableEnum.FINA_URGE_INFO_ATTR_SS_TWO,userInfo,ssFilesTwo);

                        finaRepaymentInfo = FinaRepaymentInfo.class.newInstance();
                        finaRepaymentInfo.setSettlementInfoId(finaSettlementInfo.getId());
                        finaRepaymentInfo.setRepaymentNo(SerialNumberUtil.toBuilNo("SE"));
                        finaRepaymentInfo.setRepaymentMoney(finaUrgeInfo.getUrgeMoney());
                        finaRepaymentInfo.setRepaymentSource(2);//1.保司还款,2.客户还款
                        finaRepaymentInfo.setRepaymentType(5);//还款方式(1.正常还款，2.电话催收还款，3.现成催收还款，4.其他)
                        finaRepaymentInfo.setRepaymentTime(finaUrgeInfo.getRepaymentTime());
                        finaRepaymentInfo.setApplyUserId(userInfo.getUserId());
                        finaRepaymentInfo.setApplyUserName(userInfo.getUserName());
                        finaRepaymentInfo.setRepaymentState(1);//1.待财务审核,2:财务审核通过,3.财务审核驳回
                        finaRepaymentInfo.setCreateTime(new Date());
                        finaRepaymentInfo.setCreateBy(userInfo.getUserName());
                        finaRepaymentInfo.setUpdateTime(new Date());
                        finaRepaymentInfo.setUpdateBy(userInfo.getUserName());
                        finaRepaymentInfo.setDeleteFlag(0);
                        finaRepaymentInfo.setUrgeId(finaUrgeInfo.getId());
                        finaRepaymentInfoMapper.insert(finaRepaymentInfo);
                        break;
                    case "stop-urge" : // 终止催收
                        finaUrgeInfo.setUrgeType(5);
                        finaUrgeInfo.setUrgeDesc(apiRequest.getString("urgeDesc"));
                        finaUrgeInfoMapper.insert(finaUrgeInfo);
                        finaSettlementInfo.setUrgeState(3);
                        finaSettlementInfo.setSettlementState(SettlementEnum.SETTLEMENT_CSZZJA.getState());
                        finaSettlementInfo.setFinshCloseTime(new Date());
                        finaSettlementInfo.setUpdateTime(new Date());
                        finaSettlementInfo.setUpdateBy(userInfo.getUserName());
                        finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
                        synCaseData(SettlementEnum.SETTLEMENT_CSZZJA,finaSettlementInfo);
                        break;
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else if ("settlement-track-save".equals(btnCode))//跟踪信息的保存
        {
            try {
                FinaSettlementTrack finaSettlementTrack = FinaSettlementTrack.class.newInstance();
                finaSettlementTrack.setSettlementInfoId(settlementId);
                finaSettlementTrack.setTrackDesc(apiRequest.getString("trackDesc"));
                finaSettlementTrack.setTrackFtime(new Date());
                finaSettlementTrack.setTrackUserId(userInfo.getUserId());
                finaSettlementTrack.setTrackUserName(userInfo.getUserName());
                finaSettlementTrack.setNextTrackTime(DateUtils.parseDate(apiRequest.getString("nextTrackTime"),"yyyy-MM-dd HH:mm:ss"));
                finaSettlementTrack.setCreateBy(userInfo.getUserName());
                finaSettlementTrack.setCreateTime(new Date());
                finaSettlementTrack.setUpdateBy(userInfo.getUserName());
                finaSettlementTrack.setUpdateTime(new Date());
                finaSettlementTrack.setDeleteFlag(0);
                finaSettlementTrackMapper.insert(finaSettlementTrack);
                //添加跟踪附件
                String json = apiRequest.getString("files");
                backendFinaFileApi.saveFile(finaSettlementTrack.getId(),FileTableEnum.FINA_SETTLEMENT_TRACK_ATTR,userInfo,json);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        else if ("submitServicesMoney".equals(btnCode))
        {
            Double submitServicesMoney = apiRequest.getDouble("submitServicesMoney");
            finaSettlementInfo.setSubmitServicesMoney(submitServicesMoney);
            finaSettlementInfo.setUpdateBy(userInfo.getUserName());
            finaSettlementInfo.setUpdateTime(new Date());
            finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    public void synCaseData(SettlementEnum settlementEnum,Long settlementInfoId){
        FinaSettlementInfo finaSettlementInfo = new FinaSettlementInfo();
        finaSettlementInfo.setId(settlementInfoId);
        synCaseData(settlementEnum,finaSettlementInfo);
    }
    /**
     * 根据结算单及其状态。同步案件状态及时间等数据
     * @param settlementEnum
     * @param finaSettlementInfo
     */
    public void synCaseData(SettlementEnum settlementEnum,FinaSettlementInfo finaSettlementInfo){
        Map<String,Object> paramMap =  null;
        if (settlementEnum == SettlementEnum.SETTLEMENT_CYJSZ)//同步案件状态 为出院结算中
        {
            paramMap =  new HashMap<String,Object>();
            paramMap.put("settlementInfoId",finaSettlementInfo.getId());
            paramMap.put("applicantState", AppcationInfoEnum.APPLICATION_CYJSZ.getState());
        }
        else if (settlementEnum == SettlementEnum.SETTLEMENT_DSQLP)
        {
            //同步案件状态及结算完成时间
            paramMap =  new HashMap<String,Object>();
            paramMap.put("settlementInfoId",finaSettlementInfo.getId());
            paramMap.put("applicantState", AppcationInfoEnum.APPLICATION_DSQLP.getState());
            paramMap.put("finshStatementTime",new Date());
            finaSettlementApplicantMapper.updCaseData(paramMap);
        }
        else if (settlementEnum == SettlementEnum.SETTLEMENT_DHK)
        {
            paramMap =  new HashMap<String,Object>();
            paramMap.put("settlementInfoId",finaSettlementInfo.getId());
            paramMap.put("applicantState", AppcationInfoEnum.APPLICATION_DHK.getState());
            paramMap.put("finshApplyclaimsTime",new Date());
        }
        else if (settlementEnum == SettlementEnum.SETTLEMENT_DBSSH)
        {
            paramMap =  new HashMap<String,Object>();
            paramMap.put("settlementInfoId",finaSettlementInfo.getId());
            paramMap.put("applicantState", AppcationInfoEnum.APPLICATION_DBSZS.getState());
            paramMap.put("finshRepaymentTime",new Date());
        }
        else if (settlementEnum == SettlementEnum.SETTLEMENT_FWFDKP)
        {
            paramMap =  new HashMap<String,Object>();
            paramMap.put("settlementInfoId",finaSettlementInfo.getId());
            paramMap.put("applicantState", AppcationInfoEnum.APPLICATION_FWFDKP.getState());
            paramMap.put("finalInsurancePassedTime",new Date());
        }
        else if (settlementEnum == SettlementEnum.SETTLEMENT_FWFDQRDZ)
        {
            paramMap =  new HashMap<String,Object>();
            paramMap.put("settlementInfoId",finaSettlementInfo.getId());
            paramMap.put("applicantState", AppcationInfoEnum.APPLICATION_FWFDQRDZ.getState());
        }
        else if (settlementEnum == SettlementEnum.SETTLEMENT_ZCJA || settlementEnum == SettlementEnum.SETTLEMENT_CSZZJA)
        {
            paramMap =  new HashMap<String,Object>();
            paramMap.put("settlementInfoId",finaSettlementInfo.getId());
            paramMap.put("applicantState", AppcationInfoEnum.APPLICATION_ZCJA.getState());
        }
        finaSettlementApplicantMapper.updCaseData(paramMap);
    }

    @ApiMethod(needLogin = false,descript = "结算单列表",value = "ajax-data-fina-settlement-info")
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        String dataType = apiRequest.getString("dataType");
        switch (dataType)
        {
            case "get-settlement-case-list"://发起出院结算。选择案件列表
            {
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("searchData","settlement-sel");
                paramMap.put("searchStr",apiRequest.getString("searchStr"));
                List<FinaApplicantInfo> finaApplicantInfos = finaApplicantInfoMapper.list(paramMap);
                for (FinaApplicantInfo finaApplicantInfo : finaApplicantInfos) {
                    finaApplicantInfo.setApplicantStateStr(AppcationInfoEnum.getStateNameByState(finaApplicantInfo.getApplicantState()));
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,finaApplicantInfos.size(),finaApplicantInfos);
            }
            case "get-repay-app-data" :  //还款申请弹出框数据
            {
                Long settlementId = apiRequest.getLong("settlementId");
                FinaSettlementInfo finaSettlementInfo = finaSettlementInfoMapper.selectByPrimaryKey(settlementId);
                //获取未还款金额。//查询还款记录表(审核中的。和审核通过的)
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("searchData","opr-ing-or-yes");
                paramMap.put("settlementInfoId",finaSettlementInfo.getId());
                List<FinaRepaymentInfo> repaymentInfos = finaRepaymentInfoMapper.list(paramMap);

                Double repayMoney = repaymentInfos.stream().mapToDouble(e->Optional.ofNullable(e.getRepaymentMoney()).orElse(0d)).sum();//已申请还款金额
                Double realMoney = finaSettlementInfo.getRealMoney() == null ? 0D : finaSettlementInfo.getRealMoney();//实际放款总金额
                Double lefanReceivesMoney = finaSettlementInfo.getLefanReceivesMoney() == null ? 0D : finaSettlementInfo.getLefanReceivesMoney();//我司收到的退款总额
                Double yinghuanMoney = realMoney - lefanReceivesMoney;//应还金额(实际放款金额-已收到的退款总额)

                //未还款金额= 应还总金额 - 已申请还款金额 (审核中+审核通过的还款记录金额)
                Double notRepayMoney = yinghuanMoney - repayMoney;
                finaSettlementInfo.setNotRepayMoney(notRepayMoney);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
            }
            case "get-settlement-track" : //添加跟踪。跟踪信息列表
            {
                Long settlementId = apiRequest.getLong("settlementId");
                FinaSettlementInfo finaSettlementInfo = finaSettlementInfoMapper.selectByPrimaryKey(settlementId);
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("settlementInfoId",finaSettlementInfo.getId());
                List<FinaSettlementTrack> settlementTracks = finaSettlementTrackMapper.list(paramMap);
                List<Long> ids = settlementTracks.stream().map(p -> p.getId()).collect(Collectors.toList());

                List<FinaFile> finaFiles = backendFinaFileApi.getFilesByIds(ids, FileTableEnum.FINA_SETTLEMENT_TRACK_ATTR);
                for (FinaSettlementTrack settlementTrack : settlementTracks) {
                    List<FinaFile> item = new ArrayList<FinaFile>();
                    for (FinaFile finaFile : finaFiles) {
                        if (finaFile.getKeyId().intValue() == settlementTrack.getId().intValue())
                            item.add(finaFile);
                    }
                    settlementTrack.setFinaFiles(item);
                }
                finaSettlementInfo.setSettlementTracks(settlementTracks);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
            }
            case "get-urge-info-list-by-type" : //电话催收、上门催收、律师函、诉讼凭证等列表
            {
                Long settlementId = apiRequest.getLong("settlementId");
                FinaSettlementInfo finaSettlementInfo = finaSettlementInfoMapper.selectByPrimaryKey(settlementId);
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("settlementInfoId",finaSettlementInfo.getId());
                Integer urgeType = apiRequest.getInt("urgeType");
                paramMap.put("urgeType",urgeType);
                List<FinaUrgeInfo> urgeInfos = finaUrgeInfoMapper.list(paramMap);
                List<Long> ids = urgeInfos.stream().map(p -> p.getId()).collect(Collectors.toList());
                FileTableEnum fileTableEnum = null;
                switch (urgeType){
                    case 1 : fileTableEnum = FileTableEnum.FINA_URGE_INFO_ATTR_TEL; break;//电话催收
                    case 2 : fileTableEnum = FileTableEnum.FINA_URGE_INFO_ATTR_USER; break;//上门催收
                    case 3 : fileTableEnum = FileTableEnum.FINA_URGE_INFO_ATTR_LS_TWO; break;//律师函凭证
                    case 4 : fileTableEnum = FileTableEnum.FINA_URGE_INFO_ATTR_SS_TWO; break;//诉讼凭证
                }
                List<FinaFile> finaFiles = backendFinaFileApi.getFilesByIds(ids, fileTableEnum);

                //如果是律师函 和 诉讼还需要律师函资料、诉讼资料
                List<FinaFile> oneFiles = null;
                if (urgeType == 3){
                    oneFiles = backendFinaFileApi.getFilesByIds(ids, FileTableEnum.FINA_URGE_INFO_ATTR_LS_ONE);
                }else if (urgeType == 4){
                    oneFiles = backendFinaFileApi.getFilesByIds(ids, FileTableEnum.FINA_URGE_INFO_ATTR_SS_ONE);
                }

                for (FinaUrgeInfo urgeInfo : urgeInfos) {
                    //材料凭证
                    List<FinaFile> item = new ArrayList<FinaFile>();
                    for (FinaFile finaFile : finaFiles) {
                        if (urgeInfo.getId().intValue() == finaFile.getKeyId().intValue()) {
                            item.add(finaFile);
                        }
                    }
                    urgeInfo.setFinaFiles(item);

                    //律师函及诉讼 特殊处理
                    if (oneFiles != null){
                        item = new ArrayList<FinaFile>();
                        for (FinaFile finaFile : oneFiles) {
                            if (urgeInfo.getId().intValue() == finaFile.getKeyId().intValue()) {
                                item.add(finaFile);
                            }
                        }
                        urgeInfo.setOneFiles(item);
                    }
                }
                finaSettlementInfo.setUrgeInfos(urgeInfos);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaSettlementInfo);
            }
            case "get-urge-users"://获取催收员列表
            {
                List<SettlementUserDTO> users = new LinkedList<>();
                Long settlementId = apiRequest.getLong("settlementId");

                Long tempUserId = null;
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("settlementInfoId",settlementId);
                List<FinaSettlementInvestigator> list = finaSettlementInvestigatorMapper.list(paramMap);
                if (list.size() > 0){
                    FinaSettlementInvestigator lastUser = list.get(0);
                    tempUserId = lastUser.getSurveyUserId();
                    users.add(new SettlementUserDTO(lastUser.getSurveyUserId(),lastUser.getSurveyUserName()));
                }
                paramMap =  new HashMap<String,Object>();
                List<SurveyInvestigator> investigators = surveyInvestigatorMapper.list(paramMap);
                if (investigators.size() > 0) {
                    for (SurveyInvestigator investigator : investigators) {
                        if (tempUserId != null){
                            if (investigator.getUserId().intValue() == tempUserId.intValue()){
                                continue;
                            }else{
                                users.add(new SettlementUserDTO(investigator.getUserId(),investigator.getRealName()));
                            }
                        }else{
                            users.add(new SettlementUserDTO(investigator.getUserId(),investigator.getRealName()));
                        }
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,users.size(),users);
            }
            case "get-settlement-files"://获取出院材料
            {
                Long settlementId = apiRequest.getLong("settlementId");
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("settlementInfoId",settlementId);
                List<FinaFileSettlement> fileSettlements = finaFileSettlementMapper.list(paramMap);
                List<SettlementFilesDTO> files = new ArrayList<SettlementFilesDTO>();
                for (SettlementFilesEnum value : SettlementFilesEnum.values()) {
                    List<FinaFileSettlement> temp = new ArrayList<>();
                    for (FinaFileSettlement fileSettlement : fileSettlements) {
                        if (fileSettlement.getFileEnumId().intValue() == value.getEnumId().intValue()) {
                            temp.add(fileSettlement);
                        }
                    }
                    files.add(new SettlementFilesDTO(value.getEnumId(),value.getEnumName(),temp));
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,files.size(),files);
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
