
package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCommissionApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.ArrivalInfoDto;
import com.lefancrm.apicenter.dto.BackendCaseDetailsDto;
import com.lefancrm.apicenter.dto.PaymentEstimateInquiryDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
// import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by lixianfeng on 2018/2/26.
 *
 */

@Service
@ApiService(descript = "佣金计算")
public class BackendCommissionApiImpl extends BaseServiceImpl implements BackendCommissionApi {

    @Autowired
    private PaymentEstimateInquiryMapper paymentEstimateInquiryMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private StudioCommissionInfoMapper studioCommissionInfoMapper;
    @Autowired
    private UserPoLevelMapper poLevelMapper;
    @Autowired
    private UserCommissionInfoMapper userCommissionInfoMapper;
    @Autowired
    private CommissionLogMapper commissionLogMapper;
    @Autowired
    private PositionLevelMapper positionLevelMapper;
    @Autowired
    private PositionInfoMapper positionInfoMapper;
    @Autowired
    private CommissionInfoMapper commissionInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;
    @Autowired
    private UserPoLevelMapper userPoLevelMapper;
    @Autowired
    private ManmanagerComrateInfoMapper manmanagerComrateInfoMapper;
    @Autowired
    private ArrivalInfoMapper arrivalInfoMapper;
    @Autowired
    private PromotionOutlayMapper promotionOutlayMapper;
    @Autowired
    private BillingApplyMapper billingApplyMapper;
    @Autowired
    private CaseClosedReportMapper caseClosedReportMapper;
    @Autowired
    private SuningWithholdApplyMapper suningWithholdApplyMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;
    @Autowired
    private LawCaseInfoMapper lawCaseInfoMapper;
    @Autowired
    private CaseFollowInfoMapper caseFollowInfoMapper;
    /**
     * 确定到账        是否到账 不受佣金影响   2018年5月29日17:33:24   修改
     * @param request
     * @param caseCenterInfo
     * @return
     */
    public int commissionCalculation(ApiRequest request,CaseCenterInfo caseCenterInfo,Integer applyType,SuningWithholdApply suningWithholdApply,UserInfo userInfo){
        Double amount =request.getDouble("commissionMoney");
        Date arrivalTime = DateUtils.parseDate(request.getString("confirmAccountTime"), "yyyy-MM-dd");
        int withholdState = -1;
        if (suningWithholdApply != null){
            withholdState = suningWithholdApply.getWithholdState();
        }
        if (applyType == 5 || applyType == 6){
            LawCaseInfo lawCaseInfo = lawCaseInfoMapper.selectByCaseNo(suningWithholdApply.getCaseNo());
            ArrivalInfo arrivalInfo = new ArrivalInfo();
            arrivalInfo.setCaseId(null);//保存空 为了避免司法评估案件ID与案件中心id重复
            arrivalInfo.setCaseNo(suningWithholdApply.getCaseNo());
            String orgName = lawCaseInfo.getOrgName() == null ? "" : lawCaseInfo.getOrgName();
            arrivalInfo.setCaseTitle(orgName.concat(lawCaseInfo.getEntrustUserName()).concat("司法评估"));
            arrivalInfo.setArrivalMoney(amount);

            arrivalInfo.setArrivalTime(arrivalTime);
            arrivalInfo.setUserCommissionId(lawCaseInfo.getAssessId());
            arrivalInfoMapper.insert(arrivalInfo);
            if (applyType == 5){
                lawCaseInfo.setIsArrAssFee(1);
                lawCaseInfo.setAssessRecTime(new Date());
                lawCaseInfo.setIsUploadReqmoneyLetter(4);
            }else if (applyType ==6){
                lawCaseInfo.setIsArrRefund(1);
                lawCaseInfo.setArrRefundTime(new Date());
            }
            CaseFollowInfo caseFollowInfo = new CaseFollowInfo();
            caseFollowInfo.setType(5);
            caseFollowInfo.setCaseId(lawCaseInfo.getId());
            caseFollowInfo.setCaseState(null);
            caseFollowInfo.setCaseStateStr(applyType == 5 ? "请款函已支付" : "补退费用已到账");
            caseFollowInfo.setFollowDesc(null);
            caseFollowInfo.setFollowBy(userInfo.getUserName());
            caseFollowInfo.setFollowById(userInfo.getUserId());
            caseFollowInfo.setFollowTime(new Date());
            caseFollowInfo.setCaseNo(lawCaseInfo.getCaseNo());
            caseFollowInfoMapper.insertSelective(caseFollowInfo);

//            lawCaseInfo.setIsBill(0);
            lawCaseInfoMapper.updateByPrimaryKey(lawCaseInfo);
            return 0;
        }
        Map<String, Object> caseCenterInfoMap = new HashMap<>();
        caseCenterInfoMap.put("caseId", caseCenterInfo.getCaseId());
        caseCenterInfoMap.put("caseType", caseCenterInfo.getType());
        PaymentEstimateInquiryDto dto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
        if (caseCenterInfo.getType() == 2){
            AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
            if (agentApply.getAgentType() == 2){
                dto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
            }
        }
        //保存到账信息
        ArrivalInfo arrivalInfo = new ArrivalInfo();
        arrivalInfo.setCaseId(caseCenterInfo.getId());
        arrivalInfo.setCaseNo(caseCenterInfo.getCaseNo());
        arrivalInfo.setCaseTitle(caseCenterInfo.getCaseTitle());
        arrivalInfo.setArrivalMoney(DecimalUtil.twoDecimalTOFourFromFive(amount));
        arrivalInfo.setArrivalTime(arrivalTime);
        arrivalInfo.setUserCommissionId(caseCenterInfo.getOperatorId());
        CaseClosedReport caseClosedReport = caseClosedReportMapper.selectByCaseId(caseCenterInfo.getId());
        if (caseClosedReport != null){
            //如果该案件有历史到账信息,则新的到账信息 本金 ,利息 ,通道费 ,保险费 为0  便于计算报表
            Map<String,Object> map =  new HashMap<String,Object>();
            map.put("caseNo",caseCenterInfo.getCaseNo());
            map.put("closeFlag",99); //特殊标志, 结案的到账 如果有到账数据则本金,利息....则为0
            List<ArrivalInfo> arrivalInfos = arrivalInfoMapper.selectByInfo(map);
            if (arrivalInfos != null && arrivalInfos.size() > 0){
                arrivalInfo.setLoanAmount(0D);
                arrivalInfo.setInterestAmount(0D);
                arrivalInfo.setStillAmount(0D);
                arrivalInfo.setInsuranceAmount(0D);
            }else{
                Double e = caseClosedReport.getLoanMoney() == null ? 0D : caseClosedReport.getLoanMoney();//本金   (贷款金额)
                Date outTime = caseCenterInfo.getHandOutTime() == null ? new Date() : caseCenterInfo.getHandOutTime();
                Date currentDate = new Date();
                int days = (int)(currentDate.getTime() - outTime.getTime()) / (1000 * 3600 * 24) + 3;
                arrivalInfo.setLoanAmount(e);
                arrivalInfo.setInterestAmount(DecimalUtil.twoDecimalTOFourFromFive(e * 0.0002778 * days));
                if (dto != null){
                    arrivalInfo.setStillAmount(dto.getStillNeedFee());
                    arrivalInfo.setInsuranceAmount(dto.getInsuranceFee());
                }
            }
        }
        arrivalInfoMapper.insert(arrivalInfo);

        //保存开票信息
//        Map<String, Object> caseCenterInfoMap = new HashMap<>();
//        caseCenterInfoMap.put("caseId", caseCenterInfo.getCaseId());
//        caseCenterInfoMap.put("caseType", caseCenterInfo.getType());
//        PaymentEstimateInquiryDto dto = caseCenterInfoMapper.selectNewestPayInquiry(caseCenterInfoMap);
//        BillingApply billingApply = new BillingApply();
//        billingApply.setCaseId(caseCenterInfo.getId());
//        billingApply.setCaseNo(caseCenterInfo.getCaseNo());
//        billingApply.setCaseTitle(caseCenterInfo.getCaseTitle());
//        if (dto != null) {
//            billingApply.setServcieMoney(dto.getAgentServiceFee());
//            billingApply.setChannelMoney(dto.getLoanFee());
//            billingApply.setInsuranceMoney(dto.getInsuranceFee());
//            billingApply.setDeductionMoney(DecimalUtil.twoDecimalTOFourFromFive(amount == null ? 0D : amount));
//            billingApply.setBillingMoney(amount);
//            if (applyType == 1) {
//                //还款代扣--少收的服务费（结案报告中提取）
//                CaseClosedReport caseClosedReport = caseClosedReportMapper.selectByCaseId(caseCenterInfo.getId());
//                if (caseClosedReport != null) {
//                    billingApply.setBillingMoney(caseClosedReport.getSurplusTotalMoney() == null ? 0D : caseClosedReport.getSurplusTotalMoney());
//                }
//            }
//            //--下面为原需求
//            //billingApply.setBillingMoney((dto.getAgentServiceFee() == null ? 0D : dto.getAgentServiceFee()) + (dto.getLoanFee() == null ? 0D : dto.getLoanFee()));
//        }
//        billingApply.setBillingMoney(billingApply.getBillingMoney());
//        billingApply.setBillingState(2);//开票中
//        billingApply.setBillingEnum(7);//开票类目(7：个人业务)
//        billingApply.setBillingItem(5);//开票项目(5：服务费)
//        billingApply.setCreateTime(new Date());
//        //2018年7月23日16:49:10  当开票金额为“0”或者为“null”时，不生成开票记录
//        if (billingApply.getBillingMoney() != 0D && billingApply.getBillingMoney() != null) {
//            billingApplyMapper.insertSelective(billingApply);
//        }


        // 需求：2019年3月19日18:08:20  因在案件结案通过的时候，会根据金额与预开票的金额比较，会生成发票记录，与下列方法重复，故注释
        //还款代扣、代扣成功的案件
//        if(applyType == 1 && withholdState ==2){
//            // 新需求：2018年8月1日14:20:12
//            // 重新开放“签约预开票”，结案到账后生成的开票记录金额要和预开票的金额进行对比，大于生成正数的开票金额，小于生成负数的开票记录
//            BillingApply billingApplyNew = new BillingApply();
//            billingApplyNew.setCaseId(caseCenterInfo.getId());
//            billingApplyNew.setCaseNo(caseCenterInfo.getCaseNo());
//            billingApplyNew.setCaseTitle(caseCenterInfo.getCaseTitle());
//
//            OrgInfo orgInfo = isTopOrg(caseCenterInfo.getOrgId());
//            billingApplyNew.setOrgId(orgInfo.getId());
//            billingApplyNew.setOrgName(orgInfo.getOrgName());
//            if (dto != null) {
//                billingApplyNew.setServcieMoney(dto.getAgentServiceFee());
//                billingApplyNew.setChannelMoney(dto.getLoanFee());
//                billingApplyNew.setInsuranceMoney(dto.getInsuranceFee());
//                billingApplyNew.setDeductionMoney(DecimalUtil.twoDecimalTOFourFromFive(amount == null ? 0D : amount));
//                billingApplyNew.setBillingMoney(amount);
//            }
//
//            //查询“同意委托时生成的预签约数据”
//            Map<String, Object> map = new HashMap<>();
//            map.put("caseId",caseCenterInfo.getId());
//            map.put("isPreSign",1);
//            BillingApply billingApplyIsPreSign = billingApplyMapper.selectByInfo(map);
//
//            //确认到账记录表
//            Map<String, Object> suningWithholdApplyMap = new HashMap<>();
//            suningWithholdApplyMap.put("caseId",caseCenterInfo.getId());
//            suningWithholdApplyMap.put("caseNo",caseCenterInfo.getCaseNo());
//            List<SuningWithholdApply> suningWithholdApplys =suningWithholdApplyMapper.selectByCaseInfo(suningWithholdApplyMap);
//            Double money = 0D;
//            //贷款案件
//            if(caseCenterInfo.getType() ==1){
//                for (SuningWithholdApply item : suningWithholdApplys){
//                    //放款代扣
//                    if(item.getApplyType() ==0){
//                        money += item.getWithholdMoney();
//                    }
//                    //1、还款代扣(本息)  4、还款代扣(服务费)
//                    if(item.getApplyType() ==4){
//                        money += item.getWithholdMoney();
//                    }
//                }
//            }
//            //代理案件
//            if(caseCenterInfo.getType() ==2) {
//                for (SuningWithholdApply item : suningWithholdApplys) {
//                    money += item.getWithholdMoney();
//                }
//            }
//
//            //金额比较
//            Double billingMoney =0D;
//            if(billingApplyIsPreSign !=null){
//                if(billingApplyIsPreSign.getBillingMoney() != null){
//                    //money :  SuningWithholdApply表中的案件代扣金额总和
//                    //billingApplyIsPreSign.getBillingMoney:  同意委托时生成的预签约金额
//                    billingMoney =  money - billingApplyIsPreSign.getBillingMoney();
//                }
//            }else{
//                //如没有“签约预开票记录”，默认签约预开票为0；实际为：billingMoney = money - 0；
//                billingMoney = money;
//            }
//            billingApplyNew.setBillingMoney(DecimalUtil.twoDecimalTOFourFromFive(billingMoney));
//
//            billingApplyNew.setBillingState(2);//开票中
//            billingApplyNew.setBillingEnum(7);//开票类目(7：个人业务)
//            billingApplyNew.setBillingItem(5);//开票项目(5：服务费)
//            billingApplyNew.setBillingType(2);//开票类型(2：普票)
//            billingApplyNew.setBusinessType(1);//业务类别:(1、金融)
//            billingApplyNew.setCreateTime(new Date());
//            billingApplyNew.setBillingTime(new Date());
//            billingApplyNew.setMeritName(caseCenterInfo.getOperatorName());//绩效所属人员
//            billingApplyNew.setIsPreSign(10);//生成历史到账票据
//            //2018年7月23日16:49:10  当开票金额为“0”或者为“null”时，不生成开票记录
//            if (billingApplyNew.getBillingMoney() != 0D && billingApplyNew.getBillingMoney() != null) {
//                //查询“历史到账票据”
//                Map<String ,Object> mapInfo = new HashMap();
//                mapInfo.put("isPreSign",10);
//                mapInfo.put("caseId",caseCenterInfo.getId());
//                BillingApply billingApplyInfo = billingApplyMapper.selectByInfo(mapInfo);
//                //原需求：billingMoney为负值时，直接落地一条负值数据。
//                //新需求：为负值时，落地“签约预开票记录”数据为退票状态，“案件代扣金额总和”数据为开票中状态。2018年8月13日14:35:27
//                if(billingApplyNew.getBillingMoney()<0){
//                    if(billingApplyInfo == null){
//                        //落地“签约预开票记录”数据为退票状态
//                        BillingApply billingApplyPreSign = new BillingApply();
//                        BeanUtils.copyProperties(billingApplyNew,billingApplyPreSign);
//                        billingApplyPreSign.setBillingMoney(-DecimalUtil.twoDecimalTOFourFromFive(billingApplyIsPreSign.getBillingMoney()));
//                        billingApplyPreSign.setBillingState(9);//退票中
//                        billingApplyMapper.insertSelective(billingApplyPreSign);
//                        //落地“案件代扣金额总和”数据为开票中状态
//                        BillingApply billingApplySuning = new BillingApply();
//                        BeanUtils.copyProperties(billingApplyNew,billingApplySuning);
//                        billingApplySuning.setBillingMoney(DecimalUtil.twoDecimalTOFourFromFive(money));
//                        billingApplySuning.setBillingState(2);//开票中
//                        billingApplyMapper.insertSelective(billingApplySuning);
//                    }
//                }else{
//                    //2018年8月13日15:01:52 因为可以多次确认到账，2、为负值时，落地的两条数据，会有后续流程，所以不赋值10，就不会有删除动作
//                    //删除该票据的“历史到账票据”
//                    if(billingApplyInfo!=null){
//                        billingApplyMapper.deleteByPrimaryKey(billingApplyInfo.getId());
//                    }
//
//                    billingApplyMapper.insertSelective(billingApplyNew);
//                }
//            }
//        }

        //确定金额
        Double commissionMoney = amount;
        //纯佣金
        Double newCommissionMoney = pureProfit(commissionMoney,caseCenterInfo);
        if(newCommissionMoney > 0){
            //获得处理案件的CC职级信息
            UserPoLevel userPoLevel = poLevelMapper.queryByUserId(caseCenterInfo.getOrgUserId());
            if (userPoLevel != null){
                //验证是工作室还是CC
                if(caseCenterInfo.getcOrgUserId() != null && caseCenterInfo.getcOrgUserId() != 0){//工作室
                    //计算方式
                    studioCommissionMoney(commissionMoney, newCommissionMoney,userPoLevel.getLevelId(),caseCenterInfo);
                }else{
                    //CC
                    //根据案件的时间算佣金（如果案件是一月的，则以一月的到账计算）
                    Date caseDate = caseCenterInfo.getCreateTime();
                    //根据案件时间获得当前是几月份的绩效
                    Integer year = getYear(caseDate);
                    Integer month = getMonth(caseDate);
                    UserCommissionInfo userCommissionInfo = getUserCommissionInfo(caseCenterInfo.getOrgUserId(),year,month,caseCenterInfo.getOrgUserName());
                    //先得出之前的到账金额
                    Double arrivalMoney = userCommissionInfo.getArrivalMoney();
                    //根据历史总金额得出历史最低比例
                    CommissionInfo commissionInfo = commissionInfoMapper.selectByLevelId(userPoLevel.getLevelId());
                    Double comrate = 0D;//历史到账比例
                    Double oldQuota = 0D;//历史到账金额
                    int quotaIndex= 0;//记录下标
                    if(commissionInfo.getOneQuota() > arrivalMoney){
                        comrate = commissionInfo.getOneComrate();
                        quotaIndex = 1;
                        oldQuota = commissionInfo.getOneQuota();
                    }else if(commissionInfo.getOneQuota() < arrivalMoney && commissionInfo.getTwoQuota() >= arrivalMoney){
                        comrate = commissionInfo.getTwoComrate();
                        quotaIndex = 2;
                        oldQuota = commissionInfo.getTwoQuota();
                    }else if(commissionInfo.getTwoQuota() < arrivalMoney && commissionInfo.getThreeQuota() >= arrivalMoney){
                        comrate = commissionInfo.getThreeComrate();
                        quotaIndex = 3;
                        oldQuota = commissionInfo.getThreeQuota();
                    }else if(commissionInfo.getThreeQuota() < arrivalMoney && commissionInfo.getFourQuota() >= arrivalMoney){
                        comrate = commissionInfo.getFourComrate();
                        quotaIndex = 4;
                        oldQuota = commissionInfo.getFourQuota();
                    }else if(commissionInfo.getFourQuota() < arrivalMoney && commissionInfo.getFiveQuota() >= arrivalMoney){
                        comrate = commissionInfo.getFiveComrate();
                        quotaIndex = 5;
                        oldQuota = commissionInfo.getFiveQuota();
                    }else{
                        comrate = commissionInfo.getFiveComrate();
                        oldQuota = commissionInfo.getFiveQuota();
                        quotaIndex = 6;
                    }
                    //开始计算，向上级一级一级计算(CC佣金)
                    Double newCommissionMoneyNum = getCommissionMoney(arrivalMoney,quotaIndex,newCommissionMoney,commissionInfo,oldQuota,comrate);
                    //CC
                    newCommissionMoneyNum = DecimalUtil.twoDecimalTOFourFromFive(newCommissionMoneyNum);
                    if(newCommissionMoneyNum != 0){
                        List<PromotionOutlay> promotionOutlays = promotionOutlayMapper.selectByCaseNo(caseCenterInfo.getCaseNo());
                        if(promotionOutlays != null){
                            for(PromotionOutlay promotionOutlay : promotionOutlays){
                                //未扣签约费等
                                if(promotionOutlay.getIsDeduction() == null || promotionOutlay.getIsDeduction() == 1){

                                    //2018-05-03，接到的需求--小程序根据业务部门的推广文件进行提现调整：1、财务佣金结算的时候不扣除推广费用，
                                    //所以：不扣去promotionOutlay.getMoney()，并且生成日志数据中，此数据也生成为0。
                                    //以下两行是原需求代码
                                    //newCommissionMoneyNum = newCommissionMoneyNum - promotionOutlay.getMoney();
                                    //addLog(caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),caseCenterInfo,4,-promotionOutlay.getMoney(),commissionMoney);
                                    //新需求实现：
                                    addLog(caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),caseCenterInfo,4,0D,commissionMoney);

                                    promotionOutlay.setIsDeduction(2);
                                    promotionOutlayMapper.updateByPrimaryKeySelective(promotionOutlay);
                                }
                            }
                        }
                        Double loadFee = getLoanFee(caseCenterInfo);
                        if(loadFee > 0){
                            newCommissionMoneyNum = newCommissionMoneyNum + loadFee;
                            addLog(caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),caseCenterInfo,5,loadFee,commissionMoney);
                        }
                        //加入到本月的
                        userCommissionInfo = getUserCommissionInfo(caseCenterInfo.getOrgUserId(),getYear(),getMonth(),caseCenterInfo.getOrgUserName());
                        userCommissionInfo.setArrivalMoney(userCommissionInfo.getArrivalMoney() + newCommissionMoney);
                        Double money = DecimalUtil.twoDecimalTOFourFromFive(userCommissionInfo.getCommissionMoney() + newCommissionMoneyNum);
                        userCommissionInfo.setCommissionMoney(money);
                        userCommissionInfoMapper.updateByPrimaryKey(userCommissionInfo);
                        //加日志
                        addLog(caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),caseCenterInfo,1,newCommissionMoneyNum,commissionMoney);
                    }

                    //管理佣金
                    managerComrateMoney(newCommissionMoney,year,month,commissionMoney,caseCenterInfo);
                }
            }
        }
        return 0;
    }

    //查询顶级机构
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
     * old  暂时不用
     * @param amount
     * @param caseCenterInfo
     * @return
     */
    public int commissionCalculationOld(Double amount,CaseCenterInfo caseCenterInfo){
    //确定金额
        Double commissionMoney = amount;
        //案件ID id
        //获取案件
//        CaseCenterInfo caseCenterInfo = this.caseCenterInfoMapper.selectByPrimaryKey(id);

        //纯佣金
        Double newCommissionMoney = pureProfit(commissionMoney,caseCenterInfo);
//        Double newCommissionMoney =commissionMoney;
        if(newCommissionMoney <= 0){
            return 1;//纯佣金计算小于或等于0
        }
        //获得处理案件的CC职级信息
        UserPoLevel userPoLevel = poLevelMapper.queryByUserId(caseCenterInfo.getOrgUserId());
        if (userPoLevel == null){
            return 2;//未配置用户职位职级信息
        }
        //验证是工作室还是CC
        if(caseCenterInfo.getcOrgUserId() != null && caseCenterInfo.getcOrgUserId() != 0){//工作室
            //计算方式
            studioCommissionMoney(commissionMoney, newCommissionMoney,userPoLevel.getLevelId(),caseCenterInfo);
        }else{
            //CC
            //根据案件的时间算佣金（如果案件是一月的，则以一月的到账计算）
            Date caseDate = caseCenterInfo.getCreateTime();
            //根据案件时间获得当前是几月份的绩效
            Integer year = getYear(caseDate);
            Integer month = getMonth(caseDate);
            UserCommissionInfo userCommissionInfo = getUserCommissionInfo(caseCenterInfo.getOrgUserId(),year,month,caseCenterInfo.getOrgUserName());
            //先得出之前的到账金额
            Double arrivalMoney = userCommissionInfo.getArrivalMoney();
            //根据历史总金额得出历史最低比例
            CommissionInfo commissionInfo = commissionInfoMapper.selectByLevelId(userPoLevel.getLevelId());
            Double comrate = 0D;//历史到账比例
            Double oldQuota = 0D;//历史到账金额
            int quotaIndex= 0;//记录下标
            if(commissionInfo.getOneQuota() > arrivalMoney){
                comrate = commissionInfo.getOneComrate();
                quotaIndex = 1;
                oldQuota = commissionInfo.getOneQuota();
            }else if(commissionInfo.getOneQuota() < arrivalMoney && commissionInfo.getTwoQuota() >= arrivalMoney){
                comrate = commissionInfo.getTwoComrate();
                quotaIndex = 2;
                oldQuota = commissionInfo.getTwoQuota();
            }else if(commissionInfo.getTwoQuota() < arrivalMoney && commissionInfo.getThreeQuota() >= arrivalMoney){
                comrate = commissionInfo.getThreeComrate();
                quotaIndex = 3;
                oldQuota = commissionInfo.getThreeQuota();
            }else if(commissionInfo.getThreeQuota() < arrivalMoney && commissionInfo.getFourQuota() >= arrivalMoney){
                comrate = commissionInfo.getFourComrate();
                quotaIndex = 4;
                oldQuota = commissionInfo.getFourQuota();
            }else if(commissionInfo.getFourQuota() < arrivalMoney && commissionInfo.getFiveQuota() >= arrivalMoney){
                comrate = commissionInfo.getFiveComrate();
                quotaIndex = 5;
                oldQuota = commissionInfo.getFiveQuota();
            }else{
                comrate = commissionInfo.getFiveComrate();
                oldQuota = commissionInfo.getFiveQuota();
                quotaIndex = 6;
            }
            //开始计算，向上级一级一级计算(CC佣金)
            Double newCommissionMoneyNum = getCommissionMoney(arrivalMoney,quotaIndex,newCommissionMoney,commissionInfo,oldQuota,comrate);
            //CC
            newCommissionMoneyNum = DecimalUtil.twoDecimalTOFourFromFive(newCommissionMoneyNum);
            if(newCommissionMoneyNum != 0){
                List<PromotionOutlay> promotionOutlays = promotionOutlayMapper.selectByCaseNo(caseCenterInfo.getCaseNo());
                if(promotionOutlays != null){
                    for(PromotionOutlay promotionOutlay : promotionOutlays){
                        //未扣签约费等
                        if(promotionOutlay.getIsDeduction() == null || promotionOutlay.getIsDeduction() == 1){

                            //2018-05-03，接到的需求--小程序根据业务部门的推广文件进行提现调整：1、财务佣金结算的时候不扣除推广费用，
                                                   //所以：不扣去promotionOutlay.getMoney()，并且生成日志数据中，此数据也生成为0。
                            //以下两行是原需求代码
                            //newCommissionMoneyNum = newCommissionMoneyNum - promotionOutlay.getMoney();
                            //addLog(caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),caseCenterInfo,4,-promotionOutlay.getMoney(),commissionMoney);
                            //新需求实现：
                            addLog(caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),caseCenterInfo,4,0D,commissionMoney);

                            promotionOutlay.setIsDeduction(2);
                            promotionOutlayMapper.updateByPrimaryKeySelective(promotionOutlay);
                        }
                    }
                }
                Double loadFee = getLoanFee(caseCenterInfo);
                if(loadFee > 0){
                    newCommissionMoneyNum = newCommissionMoneyNum + loadFee;
                    addLog(caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),caseCenterInfo,5,loadFee,commissionMoney);
                }
                //加入到本月的
                userCommissionInfo = getUserCommissionInfo(caseCenterInfo.getOrgUserId(),getYear(),getMonth(),caseCenterInfo.getOrgUserName());
                userCommissionInfo.setArrivalMoney(userCommissionInfo.getArrivalMoney() + newCommissionMoney);
                Double money = DecimalUtil.twoDecimalTOFourFromFive(userCommissionInfo.getCommissionMoney() + newCommissionMoneyNum);
                userCommissionInfo.setCommissionMoney(money);
                userCommissionInfoMapper.updateByPrimaryKey(userCommissionInfo);
                //加日志
                addLog(caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),caseCenterInfo,1,newCommissionMoneyNum,commissionMoney);
            }

            //管理佣金
            managerComrateMoney(newCommissionMoney,year,month,commissionMoney,caseCenterInfo);
        }

        //保存到账信息
        ArrivalInfo arrivalInfo = new ArrivalInfo();
        arrivalInfo.setCaseId(caseCenterInfo.getId());
        arrivalInfo.setCaseNo(caseCenterInfo.getCaseNo());
        arrivalInfo.setCaseTitle(caseCenterInfo.getCaseTitle());
        arrivalInfo.setArrivalMoney(amount);
        arrivalInfo.setArrivalTime(new Date());
        arrivalInfo.setUserCommissionId(caseCenterInfo.getOrgUserId());
        arrivalInfoMapper.insert(arrivalInfo);

        //保存开票信息
        Map<String,Object> caseCenterInfoMap = new HashMap<>();
        caseCenterInfoMap.put("caseId",caseCenterInfo.getCaseId());
        caseCenterInfoMap.put("caseType",caseCenterInfo.getType());
        PaymentEstimateInquiryDto dto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
        BillingApply billingApply = new BillingApply();
        billingApply.setCaseId(caseCenterInfo.getId());
        billingApply.setCaseNo(caseCenterInfo.getCaseNo());
        billingApply.setCaseTitle(caseCenterInfo.getCaseTitle());
        if (dto != null){
            billingApply.setServcieMoney(dto.getAgentServiceFee());
            billingApply.setChannelMoney(dto.getLoanFee());
            billingApply.setInsuranceMoney(dto.getInsuranceFee());
            billingApply.setDeductionMoney(amount);
            billingApply.setBillingMoney((dto.getAgentServiceFee() == null ? 0D : dto.getAgentServiceFee()) + (dto.getLoanFee() == null ? 0D : dto.getLoanFee()));
        }
        billingApply.setBillingState(0);
        billingApply.setBillingTime(new Date());
        billingApplyMapper.insertSelective(billingApply);

        return 0;
    }
    /**
     * 佣金计算
     * @param apiReq
     * @return
     */
    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = false, descript = "佣金计算", value = "backend-commission-calculation")
    public ApiResponse commissionCalculation(ApiRequest apiReq) {
        //确定金额
        Double commissionMoney = apiReq.getDouble("commissionMoney");
        //案件ID
        Long id = apiReq.getLong("id");
        //获取案件
        CaseCenterInfo caseCenterInfo = this.caseCenterInfoMapper.selectByPrimaryKey(id);
        int ret = commissionCalculation(apiReq,caseCenterInfo,null,null,null);
        if (ret == 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else if (ret == 1){
            return new ApiResponse(ApiMsgEnum.CASE_COMMISSION_MONEY);
        }else if (ret == 2){
            return new ApiResponse(ApiMsgEnum.CASE_COMMISSION_USER_LEVEL);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 计算管理佣金
     * @param newCommissionMoney 纯佣金
     * @param year 案子年份
     * @param month 案子月份
     * @param commissionMoney
     * @param caseCenterInfo
     */
    private void managerComrateMoney(Double newCommissionMoney,Integer year,Integer month,Double commissionMoney,CaseCenterInfo caseCenterInfo) {
        //先获取当前CC是那个机构
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(caseCenterInfo.getOrgUserId());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("year", year);
        paramMap.put("month", month);
        Long orgId = userInfo.getOrgId();
        while (true){
            OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(orgId);
            List<UserPoLevel> userPoLevels = userPoLevelMapper.queryManagerComrateLevel(orgId);
            paramMap.put("orgId", orgId);
            for (UserPoLevel userPoLevel : userPoLevels){
                paramMap.put("userId", userPoLevel.getUserId());
                PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(userPoLevel.getLevelId());
                managerComrateMoney(paramMap,newCommissionMoney,positionLevel,caseCenterInfo,commissionMoney);
            }
            if(orgInfo.getOrgParentid() == 1 || orgInfo.getOrgParentid() < 1){
                break;
            }
            orgId = orgInfo.getOrgParentid();
        }
    }

    public void managerComrateMoney(Map<String,Object> paramMap, Double newCommissionMoney, PositionLevel positionLevel,CaseCenterInfo caseCenterInfo,Double commissionMoney){
        //获取除去自己和F0的cc人数
        int count = userPoLevelMapper.queryCommissionMoneyCount(Long.parseLong(String.valueOf(paramMap.get("orgId")))) - 1;
        if(count <= 0){
            return;
        }
        Double oldCommissionMoneyNum = userPoLevelMapper.queryCommissionMoneyNum(paramMap);
        if(oldCommissionMoneyNum == null){
            oldCommissionMoneyNum = 0D;
        }
        //得到最新的所有到帐金额
        Double commissionMoneyNum = DecimalUtil.twoDecimalTOFourFromFive(oldCommissionMoneyNum / count);
        //得到历史到帐金额
        Double arrivalMoney = oldCommissionMoneyNum - newCommissionMoney;
        //获得历史到帐总额对应的指标，和比例
        Double perComrate = 0D;//比例
        Double newCommissionMoneyNum = 0D;//记录总佣金
        ManmanagerComrateInfo manmanagerComrateInfo = manmanagerComrateInfoMapper.selectByPrimaryKey(positionLevel.getManagerComrateId());
        if (manmanagerComrateInfo.getOnePerQuota() > commissionMoneyNum) {
            perComrate = manmanagerComrateInfo.getOnePerComrate();
        } else if (manmanagerComrateInfo.getOnePerQuota() < commissionMoneyNum && manmanagerComrateInfo.getTwoPerQuota() >= commissionMoneyNum) {
            perComrate = manmanagerComrateInfo.getTwoPerComrate();
        } else if (manmanagerComrateInfo.getTwoPerQuota() < commissionMoneyNum && manmanagerComrateInfo.getThreePerQuota() >= commissionMoneyNum) {
            perComrate = manmanagerComrateInfo.getThreePerComrate();
        } else if (manmanagerComrateInfo.getThreePerQuota() < commissionMoneyNum && manmanagerComrateInfo.getFourPerQuota() >= commissionMoneyNum) {
            perComrate = manmanagerComrateInfo.getFourPerComrate();
        } else if (manmanagerComrateInfo.getFourPerQuota() < commissionMoneyNum && manmanagerComrateInfo.getFivePerQuota() >= commissionMoneyNum) {
            perComrate = manmanagerComrateInfo.getFivePerComrate();
        } else {
            perComrate = manmanagerComrateInfo.getFivePerComrate();
        }
        //获得历史指标
        Double oldArrivalMoney = arrivalMoney/count;
        Double oldPerComrate =0D;
        if (manmanagerComrateInfo.getOnePerQuota() > oldArrivalMoney) {
            oldPerComrate = manmanagerComrateInfo.getOnePerComrate();
        } else if (manmanagerComrateInfo.getOnePerQuota() < oldArrivalMoney && manmanagerComrateInfo.getTwoPerQuota() >= oldArrivalMoney) {
            oldPerComrate = manmanagerComrateInfo.getTwoPerComrate();
        } else if (manmanagerComrateInfo.getTwoPerQuota() < oldArrivalMoney && manmanagerComrateInfo.getThreePerQuota() >= oldArrivalMoney) {
            oldPerComrate = manmanagerComrateInfo.getThreePerComrate();
        } else if (manmanagerComrateInfo.getThreePerQuota() < oldArrivalMoney && manmanagerComrateInfo.getFourPerQuota() >= oldArrivalMoney) {
            oldPerComrate = manmanagerComrateInfo.getFourPerComrate();
        } else if (manmanagerComrateInfo.getFourPerQuota() < oldArrivalMoney && manmanagerComrateInfo.getFivePerQuota() >= oldArrivalMoney) {
            oldPerComrate = manmanagerComrateInfo.getFivePerComrate();
        } else {
            oldPerComrate = manmanagerComrateInfo.getFivePerComrate();
        }

        newCommissionMoneyNum = DecimalUtil.twoDecimalTOFourFromFive(oldCommissionMoneyNum * perComrate -
                arrivalMoney * oldPerComrate);
        if(newCommissionMoneyNum != 0){
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(Long.parseLong(paramMap.get("userId").toString()));
            //加入到本月的
            UserCommissionInfo userCommissionInfo = getUserCommissionInfo(userInfo.getUserId(),getYear(),getMonth(),userInfo.getUserName());
            userCommissionInfo.setCommissionMoney(userCommissionInfo.getCommissionMoney() + newCommissionMoneyNum);
            userCommissionInfoMapper.updateByPrimaryKey(userCommissionInfo);
            //加日志
            addLog(userInfo.getUserId(),userInfo.getUserName(),caseCenterInfo,2,newCommissionMoneyNum,commissionMoney);
        }
    }

    /**
     * 除去成本的纯佣金
     * @param commissionMoney
     * @param caseCenterInfo
     * @return
     */
    public Double pureProfit(Double commissionMoney,CaseCenterInfo caseCenterInfo){
        Double pureProfitMoney = 0D;
        //如果没有报价不给予确定到账
        PaymentEstimateInquiryDto paymentEstimateInquiry = getPaymentEstimateInquiry(caseCenterInfo.getId());
        if(paymentEstimateInquiry == null){
            return -1D;
        }
        //先判断案件类型
        if(caseCenterInfo.getType() == 1){//贷款案件
            //申请的贴息额度
            Double applyDiscountLoanFee = paymentEstimateInquiry.getApplyDiscountLoanFee();
            //判断是否贴息贷款
            if(applyDiscountLoanFee != null && applyDiscountLoanFee > 0){//贴息贷款（（到账金额-10%*贴息贷款额）*100%）
                pureProfitMoney = commissionMoney - 0.1 * applyDiscountLoanFee;
            }else if(applyDiscountLoanFee == null || applyDiscountLoanFee == 0){//非贴息贷款
                if(applyDiscountLoanFee == null){
                    applyDiscountLoanFee = 0D;
                }
                pureProfitMoney = commissionMoney - 0.1 * applyDiscountLoanFee;
            }
        }else if(caseCenterInfo.getType() == 2){// //代办理赔案件
            //计算方式（到账金额*80%）
            pureProfitMoney = commissionMoney * 0.8;
        }
        pureProfitMoney =  DecimalUtil.twoDecimalTOFourFromFive(pureProfitMoney);
        return pureProfitMoney;
    }

    /***
     * 验证是否是贷款案件 加上百分之30的通道费（贷款费用）
     * @param caseCenterInfo
     * @return
     */
    private Double getLoanFee(CaseCenterInfo caseCenterInfo){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("caseId",caseCenterInfo.getId());
        paramMap.put("CommissionType",5);
        CommissionLog commissionLog = commissionLogMapper.selectByCaseIdAndCommissionType(paramMap);
        if(commissionLog != null){
            return 0D;
        }
        //如果没有报价不给予确定到账
        PaymentEstimateInquiryDto paymentEstimateInquiry = getPaymentEstimateInquiry(caseCenterInfo.getId());
        if(paymentEstimateInquiry == null){
            return 0D;
        }
        if(caseCenterInfo.getType() == 1){//贷款案件
            //申请的贴息额度
            Double applyDiscountLoanFee = paymentEstimateInquiry.getApplyDiscountLoanFee();
            //判断是否贴息贷款
            if(applyDiscountLoanFee == null || applyDiscountLoanFee == 0){//非贴息贷款
                if(paymentEstimateInquiry.getLoanFee() != null){
                    return DecimalUtil.twoDecimalTOFourFromFive(paymentEstimateInquiry.getLoanFee() * 0.3);
                }
                return 0D;
            }
        }
        return 0D;
    }

    /**
     * 获得最新精准报价
     * @param caseId
     * @param
     * @return
     */
    private PaymentEstimateInquiryDto getPaymentEstimateInquiry(Long caseId){
        PaymentEstimateInquiryDto dto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseId);
        return dto;
    }

    /**
     * 计算工作室获得的佣金
     * @param commissionMoney
     * @param newCommissionMoney
     * @param levelId
     * @param caseCenterInfo
     * @return
     */
    private void studioCommissionMoney(Double commissionMoney,Double newCommissionMoney,Long levelId,CaseCenterInfo caseCenterInfo){
        StudioCommissionInfo studioCommissionInfo = studioCommissionInfoMapper.queryByLevelId(levelId);
        if(studioCommissionInfo == null){
            studioCommissionInfo = studioCommissionInfoMapper.queryByLevelId(0L);
        }
        Integer year = getYear();
        Integer month = getMonth();
        //工作室签约案件提成比例	CC 5%	工作室 25%
        //工作室
        Double studioCommissionMoney =  newCommissionMoney * studioCommissionInfo.getStudioComrate();
        studioCommissionMoney = DecimalUtil.twoDecimalTOFourFromFive(studioCommissionMoney);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(caseCenterInfo.getcOrgUserId());
        UserCommissionInfo userCommissionInfo =  getUserCommissionInfo(caseCenterInfo.getcOrgUserId(),year,month,userInfo.getUserName());
        userCommissionInfo.setArrivalMoney(userCommissionInfo.getArrivalMoney() + commissionMoney);
        userCommissionInfo.setCommissionMoney(userCommissionInfo.getCommissionMoney() + studioCommissionMoney);
        userCommissionInfoMapper.updateByPrimaryKey(userCommissionInfo);
        //加日志
        addLog(caseCenterInfo.getcOrgUserId(),userInfo.getUserName(),caseCenterInfo,3,studioCommissionMoney,commissionMoney );

        //CC
        Double ccMoney = newCommissionMoney * studioCommissionInfo.getStudioComrate();
        ccMoney = DecimalUtil.twoDecimalTOFourFromFive(ccMoney);
        if(ccMoney != 0){
            userCommissionInfo =  getUserCommissionInfo(caseCenterInfo.getOrgUserId(),year,month,caseCenterInfo.getOrgUserName());
            userCommissionInfo.setArrivalMoney(userCommissionInfo.getArrivalMoney() + commissionMoney);
            userCommissionInfo.setCommissionMoney(userCommissionInfo.getCommissionMoney() + ccMoney);
            userCommissionInfoMapper.updateByPrimaryKey(userCommissionInfo);
            //加日志
            addLog(caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),caseCenterInfo,3,ccMoney,commissionMoney );
        }
    }

    private Integer getYear(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }
    private Integer getYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    private Integer getMonth(){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH )+1;
        return month;
    }
    private Integer getMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH )+1;
        return month;
    }

    private void addLog(Long userId,String userName,CaseCenterInfo caseCenterInfo,Integer commissionType,Double comMoney,Double arrivalMoney){
        //获得处理案件的CC职级信息
        UserPoLevel userPoLevel = poLevelMapper.queryByUserId(userId);
        PositionLevel positionLevel =  positionLevelMapper.selectByPrimaryKey(userPoLevel.getLevelId());
        PositionInfo positionInfo = positionInfoMapper.selectByPrimaryKey(userPoLevel.getPositionId());
        CommissionLog commissionLog = new CommissionLog();
        commissionLog.setUserId(userId);
        commissionLog.setUserName(userName);
        commissionLog.setPositionId(userPoLevel.getPositionId());
        commissionLog.setPositionName(positionInfo.getPositionName());
        commissionLog.setLevelId(userPoLevel.getLevelId());
        commissionLog.setLevelName(positionLevel.getLevelCode());
        commissionLog.setCaseId(caseCenterInfo.getId());
        commissionLog.setCaseTitle(caseCenterInfo.getCaseTitle());
        commissionLog.setCommissionType(commissionType);
        commissionLog.setComMoney(comMoney);
        commissionLog.setArrivalMoney(arrivalMoney);
        commissionLog.setCreateTime(new Date());
        commissionLogMapper.insertSelective(commissionLog);
    }

    private UserCommissionInfo getUserCommissionInfo(Long userId,Integer year, Integer month,String userName){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("year",year);
        paramMap.put("month",month);
        paramMap.put("userId",userId);
        UserCommissionInfo userCommissionInfo = userCommissionInfoMapper.queryByUserIdAndTime(paramMap);
        if(userCommissionInfo == null){
            userCommissionInfo = new UserCommissionInfo();
            userCommissionInfo.setUserId(userId);
            userCommissionInfo.setUserName(userName);
            userCommissionInfo.setYear(year);
            userCommissionInfo.setMonth(month);
            userCommissionInfo.setMonthNewSign(0);
            userCommissionInfo.setMonthServiceMoney(0d);
            userCommissionInfo.setArrivalMoney(0D);
            userCommissionInfo.setCommissionMoney(0D);
            userCommissionInfoMapper.insert(userCommissionInfo);
        }
        return userCommissionInfo;
    }

    /**
     * 计算本次到帐金额所获得的佣金
     * @param arrivalMoney 历史到账
     * @param quotaIndex 指标索引
     * @param newCommissionMoney 新到账
     * @param commissionInfo 职级对应的指标
     * @return
     */
    private Double getCommissionMoney(Double arrivalMoney,Integer quotaIndex,Double newCommissionMoney,CommissionInfo commissionInfo,Double oldQuota,Double cormate){
//        Double a = 40000D;
        Double newCommissionMoneyNum = 0D;//记录总佣金
        //获得总到账
        Double commissionMoneyNum = newCommissionMoney + arrivalMoney;
        if(commissionMoneyNum > oldQuota){
            //得到当前指标剩余金额的佣金（历史指标对应的金额-历史到账）*当前指标
            newCommissionMoneyNum = newCommissionMoneyNum + (oldQuota - arrivalMoney) * cormate;
            //得出剩余金额
            commissionMoneyNum = commissionMoneyNum - oldQuota;
        }else{
            newCommissionMoneyNum = newCommissionMoneyNum + newCommissionMoney * cormate;
            commissionMoneyNum = 0D;
        }
        Double b = 0D;
        while (commissionMoneyNum > 0){
            quotaIndex =  quotaIndex + 1;
            switch (quotaIndex){
                case 2:
                    b = commissionMoneyNum - (commissionInfo.getTwoQuota() - oldQuota);
                    if(b >= 0){
                        newCommissionMoneyNum = newCommissionMoneyNum + (commissionInfo.getTwoQuota() - oldQuota) * commissionInfo.getTwoComrate();
                        commissionMoneyNum = b;
                    }else{
                        newCommissionMoneyNum = newCommissionMoneyNum + commissionMoneyNum * commissionInfo.getTwoComrate();
                        commissionMoneyNum = 0D;
                    }
                    oldQuota = commissionInfo.getTwoQuota();
                    break;
                case 3:
                    b = commissionMoneyNum - (commissionInfo.getThreeQuota() - oldQuota);
                    if(b >= 0){
                        newCommissionMoneyNum = newCommissionMoneyNum + (commissionInfo.getThreeQuota() - oldQuota) * commissionInfo.getThreeComrate();
                        commissionMoneyNum = b;
                    }else{
                        newCommissionMoneyNum = newCommissionMoneyNum + commissionMoneyNum *  commissionInfo.getThreeComrate();
                        commissionMoneyNum = 0D;
                    }
                    oldQuota = commissionInfo.getThreeQuota();
                    break;
                case 4:
                    b = commissionMoneyNum - (commissionInfo.getFourQuota() - oldQuota);
                    if(b >= 0){
                        newCommissionMoneyNum = newCommissionMoneyNum + (commissionInfo.getFourQuota() - oldQuota) * commissionInfo.getFourComrate();
                        commissionMoneyNum = b;
                    }else{
                        newCommissionMoneyNum = newCommissionMoneyNum + commissionMoneyNum * commissionInfo.getFourComrate();
                        commissionMoneyNum = 0D;
                    }
                    oldQuota = commissionInfo.getFourQuota();
                    break;
                case 5:
                    b = commissionMoneyNum - (commissionInfo.getFiveQuota() - oldQuota);
                    if(b >= 0){
                        newCommissionMoneyNum = newCommissionMoneyNum + (commissionInfo.getFiveQuota() - oldQuota) * commissionInfo.getFiveComrate();
                        commissionMoneyNum = b;
                    }else{
                        newCommissionMoneyNum = newCommissionMoneyNum + commissionMoneyNum * commissionInfo.getFiveComrate();
                        commissionMoneyNum = 0D;
                    }
                    oldQuota = commissionInfo.getFiveQuota();
                    break;
                default:
                    newCommissionMoneyNum = newCommissionMoneyNum + commissionMoneyNum * commissionInfo.getFiveComrate();
                    commissionMoneyNum = 0D;
                    break;
            }
        }
        return newCommissionMoneyNum;
        }
}

