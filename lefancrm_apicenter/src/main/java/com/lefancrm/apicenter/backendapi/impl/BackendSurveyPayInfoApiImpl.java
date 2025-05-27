package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendSurveyPayInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyInvestigatorDto;
import com.lefancrm.apicenter.dto.SurveyPayInfoAjaxFinancialData;
import com.lefancrm.apicenter.dto.SurveyPayInfoDTO;
import com.lefancrm.apicenter.dto.SurveyPays;
import com.lefancrm.apicenter.dto.finacial.FinancialFileTableEnumDto;
import com.lefancrm.apicenter.dto.finacial.FinancialReApplyStateEnumDto;
import com.lefancrm.apicenter.enums.ReInfoEnum;
import com.lefancrm.apicenter.fina.dao.FinaApplicantInfoMapper;
import com.lefancrm.apicenter.fina.dao.FinaApplicantMoneyMapper;
import com.lefancrm.apicenter.fina.dao.FinaHospitalAccountMapper;
import com.lefancrm.apicenter.fina.enums.AppcationInfoEnum;
import com.lefancrm.apicenter.fina.model.FinaApplicantInfo;
import com.lefancrm.apicenter.fina.model.FinaApplicantMoney;
import com.lefancrm.apicenter.fina.model.FinaHospitalAccount;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.SendMessageUntil;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "调查放付款API")
public class BackendSurveyPayInfoApiImpl extends BaseServiceImpl implements BackendSurveyPayInfoApi {
    @Autowired
    private SurveyPayInfoMapper surveyPayInfoMapper;
//    @Autowired
//    private SurveyPayInfoDetailMapper surveyPayInfoDetailMapper;
    @Autowired
    private SurveyPayInfoDetailNewMapper surveyPayInfoDetailNewMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private InvestigatorReInfoMapper investigatorReInfoMapper;
    @Autowired
    private ReInfoMapper reInfoMapper;
    @Autowired
    private SurveyInvestigatorReInfoMapper surveyInvestigatorReInfoMapper;
    @Autowired
    private SurveyReInfoMapper surveyReInfoMapper;

    @Autowired
    private StaffPayPersonnelSlipMapper staffPayPersonnelSlipMapper;
    @Autowired
    private SurveyPreReimbursementMapper surveyPreReimbursementMapper;
    @Autowired
    private SurveyChannelCostNewMapper surveyChannelCostNewMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private BillingRefundInfoMapper billingRefundInfoMapper;
    @Autowired
    private FinaHospitalAccountMapper finaHospitalAccountMapper;
    @Autowired
    private FinaApplicantInfoMapper finaApplicantInfoMapper;
    @Autowired
    private FinaApplicantMoneyMapper finaApplicantMoneyMapper;
    @Autowired
    private FinancialReApplyMapper financialReApplyMapper;
    @Autowired
    private BackendFinancialReProgresApiImpl backendFinancialReProgresApiImpl;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private FinancialCostBearMapper financialCostBearMapper;
    @Autowired
    private BackendFinancialFileApiImpl backendFinancialFileApiImpl;
    @Autowired
    private FinancialReProgresMapper financialReProgresMapper;
    @Autowired
    private FinancialCostDetailsMapper financialCostDetailsMapper;

    @Value("${fina.messAge.url}")
    private String messAgeUrl; //垫付案件-付款管理-发送短信给被保险人-短信中的图片路径

    @ApiMethod(descript = "调查费用清单操作", value = "backend-survey-pay-info-operate", apiParams = { })
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String btnCode = apiRequest.getString("btnCode");
        if ("apps".equals(btnCode)){
            List<SurveyPays> pays = JSONArray.parseArray(apiRequest.getString("pays"),SurveyPays.class);
            String startDate = apiRequest.getString("startDate");
            String endDate = apiRequest.getString("endDate");
            String dateType = apiRequest.getString("dateType");
            String sourceSupportTypes = apiRequest.getString("sourceSupportTypes");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            try {
                for (SurveyPays pay : pays) {
                    SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(pay.getOrgId());
                    Double appMoney = pay.getAppMoney() == null ? 0D : pay.getAppMoney();
                    if (appMoney <= 0){// 如果实际付款金额小于等于0  则将申请付款金额刷新至历史欠费。 且不进入付款流程
                        if (surveyFranchisee.getHisOweMoney() == null){
                            surveyFranchisee.setHisOweMoney(0D);
                        }
                        surveyFranchisee.setHisOweMoney(Math.abs(pay.getAppMoney()));
                        surveyFranchiseeMapper.updateByPrimaryKey(surveyFranchisee);
                    }
                    //更新机构案件的付款申请状态
                    Map<String,Object> map =  new HashMap<String,Object>();
                    map.put("orgId",pay.getOrgId());
                    map.put("dateType",dateType);
                    map.put("startDate",format.parse(startDate));
                    map.put("endDate",format.parse(endDate));
                    map.put("sourceSupportTypes",sourceSupportTypes);
                    surveyInvestigatorCaseMapper.appsUpdate(map);


                    //更新相关月份工资成本是否结算
                    map =  new HashMap<>();
                    map.put("organId",surveyFranchisee.getDepartmentId());
                    map.put("startStr",startDate);
                    map.put("endStr",endDate);
                    staffPayPersonnelSlipMapper.updCostSettel(map);


                    if (appMoney > 0){
                        SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
                        surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("TB"));
                        surveyPayInfo.setOrgId(new Long(pay.getOrgId()));
                        surveyPayInfo.setOrgName(surveyFranchisee.getName());
//                        surveyPayInfo.setSourceSupportType(sourceSupportTypes);
                        surveyPayInfo.setAppPayMoney(pay.getAppMoney() == null ? 0D : pay.getAppMoney());
                        surveyPayInfo.setRemark(pay.getRemark());
                        surveyPayInfo.setAppStartDate(format.parse(startDate));
                        surveyPayInfo.setAppEndDate(format.parse(endDate));
                        surveyPayInfo.setAppType(Integer.parseInt(dateType));
                        surveyPayInfo.setCreateUserId(userInfo.getUserId());
                        surveyPayInfo.setCreateBy(userInfo.getUserName());
                        surveyPayInfo.setPayState(1);
                        surveyPayInfo.setCreateTime(new Date());
                        surveyPayInfo.setDeleteFlag(0);
                        surveyPayInfo.setUpdateBy(userInfo.getUserName());
                        surveyPayInfo.setUpdateTime(new Date());
                        surveyPayInfo.setPayType(1);//调查费

                        //同时查询是否是员工管理中的人员
                        map = new HashMap<>();
                        map.put("roleId",58);
                        map.put("orgId",surveyFranchisee.getId());
                        List<SurveyInvestigator> surveyInvestigator = surveyInvestigatorMapper.selectInfoByRole(map);
                        Long userId = 0l;
                        if(surveyInvestigator!=null && surveyInvestigator.size() > 0){
                            userId = surveyInvestigator.get(0).getUserId();
                        }
                        StaffPersonnelInfo info  = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(userId);
                        if(info != null){
                            surveyPayInfo.setUserId(info.getUserId());
                            surveyPayInfo.setRealName(info.getRealName());
                            surveyPayInfo.setSocialSecurityCompanyId(info.getSocialSecurityCompanyId());
                            surveyPayInfo.setSocialSecurityCompany(info.getSocialSecurityCompany());
                            surveyPayInfo.setOrganId(info.getOrganId());
                            surveyPayInfo.setOrgan(info.getOrgan());
                            surveyPayInfo.setDepartmentId(info.getDepartmentId());
                            surveyPayInfo.setDepartment(info.getDepartment());
                            surveyPayInfo.setTeam(info.getTeam());
                            surveyPayInfo.setTeamId(info.getTeamId());
                            surveyPayInfo.setJobPost(info.getJobPost());
                            surveyPayInfo.setJobPostId(info.getJobPostId());
                        }
                        surveyPayInfoMapper.insert(surveyPayInfo);

                        //保存付款明细表 insert select
                        map =  new HashMap<String,Object>();
                        map.put("orgId",pay.getOrgId());
                        map.put("sourceSupportType",sourceSupportTypes);
                        map.put("dateType",dateType);
                        map.put("startDate",startDate);
                        map.put("endDate",endDate);
                        map.put("payId",surveyPayInfo.getId());
                        surveyInvestigatorCaseMapper.appsInsert(map);

                        surveyFranchisee.setHisOweMoney(0D);
                        surveyFranchisee.setPayStateOk(1);//是 存在未到账
                        surveyFranchiseeMapper.updateByPrimaryKey(surveyFranchisee);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if ("ok-pay".equals(btnCode)){//确认付款
            String payTime = apiRequest.getString("actualPaymentTime");
            Double payRate = 0D;
            if(apiRequest.getDouble("payRate") != null){
                payRate = apiRequest.getDouble("payRate") / 100;
            }
            Double payTaxRate = 0D;
            if(apiRequest.getDouble("payTaxRate") != null){
                payTaxRate = apiRequest.getDouble("payTaxRate") / 100;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (StringUtils.isNotBlank(apiRequest.getString("batchOkPay"))){//批量付款
                String ids = apiRequest.getString("ids");
                if (StringUtils.isBlank(ids)){
                    return new ApiResponse(ApiMsgEnum.FAIL);
                }
                String[] idsList = ids.split(",");
                for (String id : idsList) {
                    SurveyPayInfo surveyPayInfo = surveyPayInfoMapper.selectByPrimaryKey(Long.valueOf(id));
                    surveyPayInfo.setRealPayMoney(surveyPayInfo.getAppPayMoney() * payRate);//实际支付金额
                    //税费
                    surveyPayInfo.setPayTax(surveyPayInfo.getAppPayMoney() * payTaxRate);
                    surveyPayInfo.setPayTaxRate(payTaxRate);
                    //实际所得金额
                    surveyPayInfo.setRealIncomeMoney(surveyPayInfo.getAppPayMoney() - surveyPayInfo.getAppPayMoney() * payTaxRate);

                    surveyPayInfo.setPayState(3);//已付款，待确认到账
                    //0909版：工资、绩效自动确认到账  或者退费
                    if(surveyPayInfo.getPayType() ==3 || surveyPayInfo.getPayType() ==4 || surveyPayInfo.getPayType()==5 || surveyPayInfo.getPayType() == 7){
                        surveyPayInfo.setPayState(3);//已付款，已确认到账
                        surveyPayInfo.setPayStateOkTime(new Date());
                    }
                    try {
                        surveyPayInfo.setPayTime(sdf.parse(payTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    surveyPayInfo.setPayUserId(userInfo.getUserId());
                    surveyPayInfo.setPayUserName(userInfo.getUserName());
                    surveyPayInfo.setUpdateBy(userInfo.getUserName());
                    surveyPayInfo.setUpdateTime(new Date());

                   if (surveyPayInfo.getPayType() == 2){
                       SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectByPrimaryKey(surveyPayInfo.getPayKeyId());
                       if (surveyInvestigatorReInfo != null){
                           //查寻是否有对应的预报销 如果有则同步状态
                           String preInfoDate = surveyInvestigatorReInfo.getDownTime()!=null?DateUtils.dateToLocalDate(surveyInvestigatorReInfo.getDownTime()).plusMonths(-1).toString().substring(0,7):"";
                           SurveyPreReimbursement surveyPreReimbursement = surveyPreReimbursementMapper.selectByUserId(surveyInvestigatorReInfo.getSurveyUserId(),preInfoDate,surveyInvestigatorReInfo.getSurveyOrgId());
                           if (surveyPreReimbursement != null){//如果有预报销存在  付款后直接修改报销单状态为报销完成
                               surveyPreReimbursement.setState(3);
                               surveyPayInfo.setPayState(3);//已确认到账
                               surveyPreReimbursementMapper.updateByPrimaryKeySelective(surveyPreReimbursement);
                               surveyInvestigatorReInfo.setReState(ReInfoEnum.RE_SUCCESS.getState());
                               surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.RE_SUCCESS.getStateName());
                               surveyInvestigatorReInfo.setFinshTime(new Date());
                           }else {
                               surveyPayInfo.setPayState(3);//已付款，待确认到账
                               surveyInvestigatorReInfo.setReState(ReInfoEnum.RE_SUCCESS.getState());
                               surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.RE_SUCCESS.getStateName());
                           }
                           surveyInvestigatorReInfo.setPayTime(new Date());
                           surveyInvestigatorReInfoMapper.updateByPrimaryKey(surveyInvestigatorReInfo);
                       }
                   }else if(surveyPayInfo.getPayType() == 6){//离职预报销付款
                       SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectByPrimaryKey(surveyPayInfo.getPayKeyId());
                       if (surveyInvestigatorReInfo != null){
                           surveyPayInfo.setPayState(3);//已确认到账
                           surveyInvestigatorReInfo.setReState(ReInfoEnum.RE_SUCCESS.getState());
                           surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.RE_SUCCESS.getStateName());
                           surveyInvestigatorReInfo.setFinshTime(new Date());
                           surveyInvestigatorReInfo.setPayTime(new Date());
                           surveyInvestigatorReInfoMapper.updateByPrimaryKey(surveyInvestigatorReInfo);
                       }
                   }
                   else if(surveyPayInfo.getPayType() == 9 || surveyPayInfo.getPayType() == 10 || surveyPayInfo.getPayType() == 11){//每刻报销
                       FinancialReApply financialReApply = financialReApplyMapper.selectByPrimaryKey(surveyPayInfo.getPayKeyId());
                       if (financialReApply != null){
                           financialReApply.setPayTime(new Date());
                           if(financialReApply.getReType() ==1 || financialReApply.getReType() ==2){//日常费用报销、对公支付
                               financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_YFK.getState());//状态 8:已付款
                           }else if(financialReApply.getReType() ==3){
                               financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DJKHX.getState());//状态 14:待借款核销
                           }
                           financialReApplyMapper.updateByPrimaryKey(financialReApply);

                           //进度
                           String progressName = "出纳支付"+surveyPayInfo.getAppPayMoney()+"元";
                           String progressDesc = "已付款";
                           String stateName = FinancialReApplyStateEnumDto.getStateNameByState(financialReApply.getState());
                           backendFinancialReProgresApiImpl.saveProgress(financialReApply.getId(),userInfo.getUserId(),userInfo.getUserName(),progressName,progressDesc,stateName,"",0);

                           String weProgressDesc = "日常费用报销";
                           if(financialReApply.getReType() ==2){
                               weProgressDesc = "对公支付";
                           }else if(financialReApply.getReType() ==3){
                               weProgressDesc = "借款单";
                           }

                           Map msgMap = new HashMap<String, Object>();
                           msgMap.put("title", "报销审核");
                           msgMap.put("content", "你有报销已付款，请注意查收！");
                           msgMap.put("keyWords", "事由：【" + weProgressDesc + "】"+financialReApply.getReReasons() + "\n" + "金额：" + financialReApply.getReMoney() + "元");
                           backendWechatApi.send(financialReApply.getApplyUserId(), msgMap);
                       }
                   }
                   surveyPayInfoMapper.updateByPrimaryKey(surveyPayInfo);
                }


                //如果是渠道费用，则修改渠道单到账状态
                surveyPayInfoDetailNewMapper.updateChannelItems(ids);

                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }

            Long id = apiRequest.getLong("id");
            Double payMoney = apiRequest.getDouble("payMoney");
            String urls = apiRequest.getString("urls");
            SurveyPayInfo surveyPayInfo = surveyPayInfoMapper.selectByPrimaryKey(id);

            //税费
            surveyPayInfo.setPayTax(payMoney * payTaxRate);
            surveyPayInfo.setPayTaxRate(payTaxRate);
            surveyPayInfo.setRealPayMoney(payMoney);
            surveyPayInfo.setRealIncomeMoney(apiRequest.getDouble("realIncomeMoney"));
            surveyPayInfo.setPayState(3);//已付款，待确认到账.-----2021年5月20日   需求改为直接确认到账
            if (surveyPayInfo.getPayType() == 3 || surveyPayInfo.getPayType() ==4 || surveyPayInfo.getPayType()==5 || surveyPayInfo.getPayType() == 7){//如果是渠道费用报销。确认付款。 或者退费类型 则直接变成确认到账。（0909版：工资、绩效自动确认到账）
                surveyPayInfo.setPayState(3);
                surveyPayInfo.setPayStateOkTime(new Date());
            }
            try {
                surveyPayInfo.setPayTime(sdf.parse(payTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            surveyPayInfo.setPayUserId(userInfo.getUserId());
            surveyPayInfo.setPayUserName(userInfo.getUserName());
            surveyPayInfo.setPayImgUrl(urls);
            surveyPayInfo.setUpdateBy(userInfo.getUserName());
            surveyPayInfo.setUpdateTime(new Date());
            if (surveyPayInfo.getPayType() == 2){
                SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectByPrimaryKey(surveyPayInfo.getPayKeyId());
                if (surveyInvestigatorReInfo != null){
                    //查寻是否有对应的预报销 如果有则同步状态
                    String preInfoDate = surveyInvestigatorReInfo.getDownTime()!=null?DateUtils.dateToLocalDate(surveyInvestigatorReInfo.getDownTime()).plusMonths(-1).toString().substring(0,7):"";
                    SurveyPreReimbursement surveyPreReimbursement = surveyPreReimbursementMapper.selectByUserId(surveyInvestigatorReInfo.getSurveyUserId(),preInfoDate,surveyInvestigatorReInfo.getSurveyOrgId());
                    if (surveyPreReimbursement != null){//如果有预报销存在  付款后直接修改报销单状态为报销完成
                        surveyPayInfo.setPayState(3);//已确认到账
                        surveyPreReimbursement.setState(3);
                        surveyPreReimbursementMapper.updateByPrimaryKeySelective(surveyPreReimbursement);
                        surveyInvestigatorReInfo.setReState(ReInfoEnum.RE_SUCCESS.getState());
                        surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.RE_SUCCESS.getStateName());
                        surveyInvestigatorReInfo.setFinshTime(new Date());
                    }else {
                        surveyPayInfo.setPayState(3);//已付款，待确认到账
                        surveyInvestigatorReInfo.setReState(ReInfoEnum.RE_SUCCESS.getState());
                        surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.RE_SUCCESS.getStateName());
                    }
                    surveyInvestigatorReInfo.setPayTime(new Date());
                    surveyInvestigatorReInfoMapper.updateByPrimaryKey(surveyInvestigatorReInfo);
                }
            }else if(surveyPayInfo.getPayType() == 6){//离职预报销付款
                SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectByPrimaryKey(surveyPayInfo.getPayKeyId());
                if (surveyInvestigatorReInfo != null){
                    surveyPayInfo.setPayState(3);//已确认到账
                    surveyInvestigatorReInfo.setReState(ReInfoEnum.RE_SUCCESS.getState());
                    surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.RE_SUCCESS.getStateName());
                    surveyInvestigatorReInfo.setFinshTime(new Date());
                    surveyInvestigatorReInfo.setPayTime(new Date());
                    surveyInvestigatorReInfoMapper.updateByPrimaryKey(surveyInvestigatorReInfo);
                }
            }else if(surveyPayInfo.getPayType() == 8){ //垫付业务

                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(surveyPayInfo.getPayKeyId());
                finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_DQRDZ.getState());//垫付状态 7:待确认到账
                finaApplicantInfo.setFinshLoanTime(new Date());
                finaApplicantInfo.setUpdateBy(userInfo.getUserName());
                finaApplicantInfo.setUpdateTime(new Date());
                finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);

                FinaApplicantMoney finaApplicantMoney = finaApplicantMoneyMapper.selectByPrimaryKey(finaApplicantInfo.getId());
                finaApplicantMoney.setActualMoney(payMoney);
                finaApplicantMoney.setRealLoanTime(new Date());
                finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);
                //发短信尊敬的xxx，您在xxxx申请的垫付服务已付款，本次付款金额xxxx元，点击asdfagafe.cn查看付款凭证
                //依次为 被保险人 保险公司 放款金额 付款凭证 凭证图片为财务上传图片
                try {
                    String insuredName = finaApplicantInfo.getInsuredName() == null ?"" : finaApplicantInfo.getInsuredName();
                    Double actualMoney = finaApplicantMoney.getActualMoney() == null ?0D : finaApplicantMoney.getActualMoney();

                    //委托机构
                    String entrustOrgName = finaApplicantInfo.getEntrustOrgName() == null ?"" : finaApplicantInfo.getEntrustOrgName();
                    List orgNameList = getListFromContent(entrustOrgName, 20);
                    String entrustOrgNameFirst = orgNameList.get(0).toString();
                    String entrustOrgNameSecond = "所";
                    if(orgNameList.size() > 1){
                        entrustOrgNameSecond = orgNameList.get(1).toString()==null?" ":orgNameList.get(1).toString() ;
                    }

                    String payImgUrlFirst = "联系垫";
                    String payImgUrlSecond = "付";
                    String payImgUrlThree = "员";

                    if(surveyPayInfo.getPayImgUrl() != null){
                        //短连接的获取
                        String aResult = null;
                        if(surveyPayInfo.getShortUrl() == null){
                            Date date = new Date();
                            String timestamp = String.valueOf(date.getTime()/1000);//精确到秒
                            String eid = timestamp.toString() + surveyPayInfo.getId().toString();

                            String sLongUrl = eid; // 原始链接
                            aResult = shortUrl(sLongUrl);//将产生1组6位字符串

                            surveyPayInfo.setShortUrl(aResult);
                            surveyPayInfoMapper.updateByPrimaryKey(surveyPayInfo);
                        }else{
                            aResult =surveyPayInfo.getShortUrl();
                        }

                        String payImgUrl = messAgeUrl+ "im?ag="+aResult;//测试
                        List urlList = getListFromContent(payImgUrl, 20);
                        payImgUrlFirst = urlList.get(0).toString();
                        payImgUrlSecond = urlList.get(1).toString() ==null?" ":urlList.get(1).toString() ;
                        payImgUrlThree = urlList.get(2).toString() ==null?" ":urlList.get(2).toString()+" ";
                    }

                    SendMessageUntil.sendFinaApplicantInfo2(finaApplicantInfo.getInsuredTel(), insuredName,entrustOrgNameFirst,entrustOrgNameSecond,actualMoney,payImgUrlFirst,payImgUrlSecond, payImgUrlThree, "1914");

//                    SendMessageUntil.sendFinaApplicantInfo(finaApplicantInfo.getInsuredTel(), insuredName,entrustOrgName,actualMoney,surveyPayInfo.getPayImgUrl(), "1912");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(surveyPayInfo.getPayType() == 9 || surveyPayInfo.getPayType() == 10 || surveyPayInfo.getPayType() == 11){//每刻报销
                FinancialReApply financialReApply = financialReApplyMapper.selectByPrimaryKey(surveyPayInfo.getPayKeyId());
                if (financialReApply != null){
                    financialReApply.setPayTime(new Date());
                    if(financialReApply.getReType() ==1 || financialReApply.getReType() ==2){//日常费用报销、对公支付
                        financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_YFK.getState());//状态 8:已付款
                    }else if(financialReApply.getReType() ==3){
                        financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DJKHX.getState());//状态 14:待借款核销
                    }
                    financialReApplyMapper.updateByPrimaryKey(financialReApply);

                    //进度
                    String progressName = "出纳支付"+surveyPayInfo.getAppPayMoney()+"元";
                    String progressDesc = "已付款";
                    String stateName = FinancialReApplyStateEnumDto.getStateNameByState(financialReApply.getState());
                    backendFinancialReProgresApiImpl.saveProgress(financialReApply.getId(),userInfo.getUserId(),userInfo.getUserName(),progressName,progressDesc,stateName,"",0);

                    String weProgressDesc = "日常费用报销";
                    if(financialReApply.getReType() ==2){
                        weProgressDesc = "对公支付";
                    }else if(financialReApply.getReType() ==3){
                        weProgressDesc = "借款单";
                    }
                    Map msgMap = new HashMap<String, Object>();
                    msgMap.put("title", "报销审核");
                    msgMap.put("content", "你有报销已付款，请注意查收！");
                    msgMap.put("keyWords", "事由：【" + weProgressDesc + "】"+financialReApply.getReReasons() + "\n" + "金额：" + financialReApply.getReMoney() + "元");
                    backendWechatApi.send(financialReApply.getApplyUserId(), msgMap);
                }
            }

            surveyPayInfoMapper.updateByPrimaryKey(surveyPayInfo);

            //如果是渠道费用，则修改渠道单到账状态
            if (surveyPayInfo.getPayType() == 3){
                surveyPayInfoDetailNewMapper.updateChannelItems(id.toString());
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else if ("veto-pay".equals(btnCode)){//驳回
            Long id = apiRequest.getLong("id");
            String reason = apiRequest.getString("reason");
            SurveyPayInfo surveyPayInfo = surveyPayInfoMapper.selectByPrimaryKey(id);
            surveyPayInfo.setPayState(4);//驳回
            surveyPayInfo.setOpinion(reason);
            surveyPayInfo.setUpdateBy(userInfo.getUserName());
            surveyPayInfo.setUpdateTime(new Date());
            //还原案件状态
            Map<String,Object> map =  new HashMap<String,Object>();
            map.put("payId",surveyPayInfo.getId());
            map.put("surveyPay",0);
            surveyInvestigatorCaseMapper.payUpdate(map);
            surveyPayInfoMapper.updateByPrimaryKey(surveyPayInfo);
            //机构是否存在未到账 改为否
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyPayInfo.getOrgId());
            surveyFranchisee.setPayStateOk(0);//否 存在未到账
            surveyFranchiseeMapper.updateByPrimaryKey(surveyFranchisee);
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else if ("ok-acc-pay".equals(btnCode)){//确认到账
            //报销费的确认到账有多个入口。  若增加其他逻辑。  BackendReInfoApiImpl  (step-four)的确认到账也需要增加
            Long id = apiRequest.getLong("id");
            SurveyPayInfo surveyPayInfo = surveyPayInfoMapper.selectByPrimaryKey(id);
            surveyPayInfo.setPayState(3);
            surveyPayInfo.setPayStateOkTime(new Date());
            surveyPayInfo.setUpdateBy(userInfo.getUserName());
            surveyPayInfo.setUpdateTime(new Date());
            surveyPayInfoMapper.updateByPrimaryKey(surveyPayInfo);
            //  更改机构到账状态。  为 否
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyPayInfo.getOrgId());
            if (surveyFranchisee != null){
                surveyFranchisee.setPayStateOk(0);//否  不存在未到账
                surveyFranchiseeMapper.updateByPrimaryKey(surveyFranchisee);
            }

            //更新案件状态未已到账
            if (surveyPayInfo.getPayType() != 2){
                Map<String,Object> map =  new HashMap<String,Object>();
                map.put("payId",surveyPayInfo.getId());
                map.put("surveyPay",3);
                surveyInvestigatorCaseMapper.payUpdate(map);
            }

            if (surveyPayInfo.getPayType() == 2){
                SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectByPrimaryKey(surveyPayInfo.getPayKeyId());
                if (surveyInvestigatorReInfo != null){
                    surveyInvestigatorReInfo.setReState(ReInfoEnum.RE_SUCCESS.getState());
                    surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.RE_SUCCESS.getStateName());
                    surveyInvestigatorReInfo.setFinshTime(new Date());
                    //判断该报销清单的案件 所有调查员是否都已经报销完成。 若报销完成 则更改案件状态 已报销
                    surveyInvestigatorReInfoMapper.updateByPrimaryKey(surveyInvestigatorReInfo);
                    //判断该报销单下的所有报销清单是否都已完成。 都已完成 则更改报销单状态 已完成
                    int count = surveyInvestigatorReInfoMapper.selectReAllOkByReId(surveyInvestigatorReInfo.getReId());
                    //SELECT COUNT(1) FROM survey_investigator_re_info WHERE re_id = #{reId} AND re_state != 7; 只要count(1) 等于0  则说明。 报销单已完成
                    if (count == 0){
                        SurveyReInfo surveyReInfo = surveyReInfoMapper.selectByPrimaryKey(surveyInvestigatorReInfo.getReId());
                        surveyReInfo.setReState(1);//已完成
                        surveyReInfoMapper.updateByPrimaryKey(surveyReInfo);
                    }
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);

        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


    @ApiMethod(descript = "已生成付款清单", value = "backend-survey-pay-info-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        String menuCode = apiRequest.getString("menuCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        apiRequest.put("pageSize",100);
        //导出时，会传值pageIndex，否则会第一页数据一直重复
        String pageIndex = apiRequest.getString("pageIndex");
        if(pageIndex == null){
            setBackendPageSize(apiRequest);
        }
        List<SurveyPayInfoDTO> list = null;
        int count = 0;
        if ("payInfo".equals(menuCode)){//公估业务付款

        }else if ("appList".equals(menuCode)){//已申请付款清单
            apiRequest.put("payType",1);
            apiRequest.put("createUserId",userInfo.getUserId());
        }
        Boolean boss = isRoleUser(userRoles,134L);//盐城财务
        if(boss){
            apiRequest.put("companyIds",47);//
        }
        Boolean boss1 = isRoleUser(userRoles,135L);//常熟财务
        if(boss1){
            apiRequest.put("companyIds",46);//
        }

        Boolean boss2 = isRoleUser(userRoles,145L);//无锡财务
        if(boss2){
            apiRequest.put("companyIds",49);//
        }

        Boolean boss3 = isRoleUser(userRoles,146L);//南通财务
        if(boss3){
            apiRequest.put("companyIds",48);//
        }


        count = surveyPayInfoMapper.listSize(apiRequest);
        list = surveyPayInfoMapper.list(apiRequest);
        DecimalFormat df = new DecimalFormat("#.00");
        for (SurveyPayInfoDTO surveyPayInfoDTO : list) {
            surveyPayInfoDTO.setAppPayMoney(Double.valueOf(df.format(surveyPayInfoDTO.getAppPayMoney())));
            //调查费取机构银行卡信息
            if (surveyPayInfoDTO.getPayType() == null) {
                surveyPayInfoDTO.setPayType(1);
            }
            if (surveyPayInfoDTO.getPayType() == 1){
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyPayInfoDTO.getOrgId());
                surveyPayInfoDTO.setSurveyFranchisee(surveyFranchisee);
            }else if (surveyPayInfoDTO.getPayType() == 2){
                //费用报销取调查员银行信息
                Long investigatorUid = surveyPayInfoDTO.getPaySurveyUserId();
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(investigatorUid);
                if (surveyInvestigator != null){
                    SurveyInvestigatorDto surveyInvestigatorDto = new SurveyInvestigatorDto();
                    BeanUtils.copyProperties(surveyInvestigator,surveyInvestigatorDto);
                    surveyPayInfoDTO.setSurveyInvestigatorDto(surveyInvestigatorDto);
                }
            }else if (surveyPayInfoDTO.getPayType() == 3){//渠道费用
                SurveyChannelCostNew surveyChannelCostNew = surveyChannelCostNewMapper.selectByPrimaryKey(surveyPayInfoDTO.getPayKeyId());
                if (surveyChannelCostNew != null) {
                    SurveyInvestigatorDto surveyInvestigatorDto = new SurveyInvestigatorDto();
                    surveyInvestigatorDto.setRealName(surveyChannelCostNew.getPayeeUserName());
                    surveyInvestigatorDto.setBankName(surveyChannelCostNew.getBankDeposit()+"("+surveyChannelCostNew.getBankBranch()+")");
                    surveyInvestigatorDto.setBankNo(surveyChannelCostNew.getBankNo());
                    surveyPayInfoDTO.setSurveyInvestigatorDto(surveyInvestigatorDto);
                }
            }else if (surveyPayInfoDTO.getPayType() == 7) {//退费
                BillingRefundInfo billingRefundInfo = billingRefundInfoMapper.selectByPrimaryKey(surveyPayInfoDTO.getPayKeyId());
                if (billingRefundInfo != null) {
                    SurveyInvestigatorDto surveyInvestigatorDto = new SurveyInvestigatorDto();
                    surveyInvestigatorDto.setRealName(billingRefundInfo.getPayee());
                    surveyInvestigatorDto.setBankName(billingRefundInfo.getBankName()+"("+billingRefundInfo.getBankBranch()+")");
                    surveyInvestigatorDto.setBankNo(billingRefundInfo.getBankCarNo());
                    surveyPayInfoDTO.setSurveyInvestigatorDto(surveyInvestigatorDto);
                }
            }
            else if (surveyPayInfoDTO.getPayType() == 8) {//垫付业务
                Map map = new HashMap<>();
                map.put("finaInfoId",surveyPayInfoDTO.getPayKeyId());
                FinaHospitalAccount finaHospitalAccount = finaHospitalAccountMapper.selectByOne(map);
                if (finaHospitalAccount != null) {
                    surveyPayInfoDTO.setFinaHospitalAccount(finaHospitalAccount);
                }
            }
            else if(surveyPayInfoDTO.getPayType() == 9 || surveyPayInfoDTO.getPayType() == 10 || surveyPayInfoDTO.getPayType() == 11){//

                FinancialReApply financialReApply = financialReApplyMapper.selectByPrimaryKey(surveyPayInfoDTO.getPayKeyId());
                if (financialReApply != null) {
                    surveyPayInfoDTO.setFinancialReApply(financialReApply);
                }
            }

            if (surveyPayInfoDTO.getPayImgUrl()!=null){
                String[] images = surveyPayInfoDTO.getPayImgUrl().split(",");
                surveyPayInfoDTO.setImages(new ArrayList<>(Arrays.asList(images)));
            }
        }

        if ("yes".equals(apiRequest.getString("exportNew"))){
            String reIds = list.stream().filter(p -> p.getPayType() == 2 && p.getPayKeyId() != null).map(e -> e.getPayKeyId().toString()).collect(Collectors.joining(","));
            ApiRequest paramMap = new ApiRequest();
            paramMap.put("reIds",StringUtils.isEmpty(reIds) ? "-1" : reIds);
            List<SurveyInvestigatorReInfo> reInfos = surveyInvestigatorReInfoMapper.selectByParam(paramMap);
            String finanReIds = list.stream().filter(p -> (p.getPayType() == 9 || p.getPayType() == 10) && p.getPayKeyId() != null).map(e -> e.getPayKeyId().toString()).collect(Collectors.joining(","));
            paramMap = new ApiRequest();
            paramMap.put("finanReIds",StringUtils.isEmpty(finanReIds) ? "-1" : finanReIds);
            List<FinancialCostDetails> costDetails = financialCostDetailsMapper.list(paramMap);//每刻报销费用明细
            List<FinancialCostBear> costBears = financialCostBearMapper.list(paramMap);//每刻报销部门承担明细
            for (SurveyPayInfoDTO item : list) {
                //如果是费用报销  查明细
                if (item.getPayType() == 2){
                    List<SurveyInvestigatorReInfo> collect = reInfos.stream().filter(p -> p.getId().intValue() == item.getPayKeyId().intValue()).collect(Collectors.toList());
                    if (collect.size() > 0){
                        SurveyInvestigatorReInfo tempReinfos = collect.get(0);
                        //室内交通费、病史费（含复印费）、住院排查费用、门诊排查费用、体检报告打印费、住宿费、跨地市交通费（汽车、火车、飞机）、跨地市交通费（自驾）、其他
                        item.setReInfo(tempReinfos);
                    }
                }
                //如果是日常费用报销 对公支付 查明细
                if (item.getPayType() == 9 || item.getPayType() == 10){
                    List<FinancialCostDetails> tempCostDetails = costDetails.stream().filter(p -> p.getFinancialReApplyId().intValue() == item.getPayKeyId().intValue()).collect(Collectors.toList());
                    item.setCostDetails(tempCostDetails);//费用明细
                    List<FinancialCostBear> tempCostBears = costBears.stream().filter(p -> p.getFinancialReApplyId().intValue() == item.getPayKeyId().intValue()).collect(Collectors.toList());
                    item.setCostBears(tempCostBears);//部门明细
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(descript = "已生成付款清单", value = "backend-survey-pay-info-info", apiParams = { })
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        SurveyPayInfo surveyPayInfo = surveyPayInfoMapper.selectByPrimaryKey(id);

        String menuCode = apiRequest.getString("menuCode");
        if(menuCode!=null && "imageInfo".equals(menuCode)){ //垫付案件-付款管理-发送短信给被保险人-短信中的图片路径
            String shortUrl = apiRequest.getString("shortUrl");
            surveyPayInfo = surveyPayInfoMapper.selectByShortUrl(shortUrl);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyPayInfo);
    }



    @ApiMethod(descript = "获取报销数据，生成pdf", value = "backend-survey-pay-info-ajax-financial-data", apiParams = { })
    @Override
    public ApiResponse ajaxFinancialData(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        SurveyPayInfo surveyPayInfo = surveyPayInfoMapper.selectByPrimaryKey(id);
        if (surveyPayInfo != null){
            FinancialReApply financialReApply = financialReApplyMapper.selectByPrimaryKey(surveyPayInfo.getPayKeyId());
            if (financialReApply != null){
                SurveyPayInfoAjaxFinancialData data = new SurveyPayInfoAjaxFinancialData();
                if (surveyPayInfo.getPayType() == 10){
                    data.setTitle("对公支付" + financialReApply.getReNo());
                }else if (surveyPayInfo.getPayType() == 11){
                    data.setTitle("借款单" + financialReApply.getReNo());
                }else if (surveyPayInfo.getPayType() == 9){
                    data.setTitle("日常费用报销" + financialReApply.getReNo());
                }
                data.setLittleTitle(financialReApply.getCompanyTitle());
                data.setApplyTime(new SimpleDateFormat("yyyy-MM-dd").format(financialReApply.getCreateTime()));
                data.setSno(surveyPayInfo.getPayNo());
                data.setApplyUserName(financialReApply.getApplyUserName());
                if (surveyPayInfo.getPayType() != 11){//非借款单的时候有承担部门
                    Map paramMap = new HashMap<>();
                    paramMap.put("financialReApplyId",financialReApply.getId());
                    List<FinancialCostBear> bears = financialCostBearMapper.list(paramMap);
                    String departments = bears.stream().map(FinancialCostBear :: getDepartmentName).collect(Collectors.joining(" "));
                    data.setDepartments(departments);
                }else{
                    data.setDepartments("");
                }
                data.setReason(financialReApply.getReReasons());
                data.setMoney(surveyPayInfo.getAppPayMoney());
                if (surveyPayInfo.getPayTime() != null){
                    data.setPayTime(new SimpleDateFormat("yyyy-MM-dd").format(surveyPayInfo.getPayTime()));
                }else{
                    data.setPayTime("");
                }
                data.setReceiveUserName(financialReApply.getPayeeName());
                data.setReceiveUserAcc(financialReApply.getBankName() + " " + financialReApply.getPayeeNo() + " " + financialReApply.getBranchBank());
                data.setRemark(financialReApply.getApplyDesc());
                List<Long> ids = new ArrayList<Long>();
                ids.add(financialReApply.getId());
                FinancialFileTableEnumDto fileTableEnum = FinancialFileTableEnumDto.FINACIAL_RE_APPLE_ATTR;//每刻报销申请单凭证
                if(financialReApply.getReType() == 3) {//借款单
                    fileTableEnum = FinancialFileTableEnumDto.FINACIAL_RE_LOAN_ATTR;
                }
                List<FinancialFile> files = backendFinancialFileApiImpl.getFilesByIds(ids, fileTableEnum);
                data.setFiles(files);
                Map map = new HashMap<>();
                map.put("financialReApplyId", financialReApply.getId());
                List<FinancialReProgres> progress = financialReProgresMapper.list(map);
                data.setProgress(progress);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,data);
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }






    public static List getListFromContent(String content, int count) {
        List list = new ArrayList();
        // 获取String的总长度
        int contentLength = content.length();
        if (contentLength < count) {
            list.add(content);
        } else {
            int begin = 0;
            // 获取需要切割多少段
            int cutCount = contentLength / count;
            int cutCounts = contentLength % count;
            // 获取切割段的长度
            if (cutCounts != 0) {
                cutCount++;
            }
            for (int i = 1; i <= cutCount; i++) {
                String temp;
                // 不是最后一段
                if (i != cutCount) {
                    temp = content.substring(begin, count * i);
                } else {
                    temp = content.substring(begin, contentLength);
                }
                begin = count * i;
                list.add(temp);
            }
        }
        return list;
    }



    public static void main(String[] args) {
        String sLongUrl = "ag=161061880211291"; // 原始链接
        System.out.println("长链接:"+sLongUrl);
        String aResult = shortUrl(sLongUrl);//将产生4组6位字符串
        // 打印出结果
//        for (int i = 0; i < aResult.length; i++) {
//            System.out.println("[" + i + "]:" + aResult[i]);
//        }
        Random random=new Random();
        int j=random.nextInt(4);//产成4以内随机数
        System.out.println("短链接:"+aResult);//随机取一个作为短链
    }

    public static String shortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "test";
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"

        };
        // 对传入网址进行 MD5 加密
        String hex = md5ByHex(key + url);

        String resUrl = null;
//        for (int i = 0; i < 4; i++) {

            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring( 8,  8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl = outChars;
//        }
        return resUrl;
    }
    /**
     * MD5加密(32位大写)
     * @param src
     * @return
     */
    public static String md5ByHex(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = src.getBytes();
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            String hs = "";
            String stmp = "";
            for (int i = 0; i < hash.length; i++) {
                stmp = Integer.toHexString(hash[i] & 0xFF);
                if (stmp.length() == 1)
                    hs = hs + "0" + stmp;
                else {
                    hs = hs + stmp;
                }
            }
            return hs.toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }
    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().equals(roleId)){
                return true;
            }
        }
        return false;
    }
}
