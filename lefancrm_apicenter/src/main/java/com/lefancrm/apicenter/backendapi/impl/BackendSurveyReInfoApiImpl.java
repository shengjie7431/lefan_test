package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.backendapi.BackendSurveyReInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyReInfoDto;
import com.lefancrm.apicenter.dto.SurveyReimbursementFileDto;
import com.lefancrm.apicenter.dto.SurveyUserClockDto;
import com.lefancrm.apicenter.enums.ReInfoEnum;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.apicenter.util.pinganfu.StringUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@ApiService(descript = "报销相关API")
@SuppressWarnings({"rawtypes", "unchecked"})
public class BackendSurveyReInfoApiImpl extends BaseServiceImpl implements BackendSurveyReInfoApi {

    @Autowired
    private SurveyReInfoMapper surveyReInfoMapper;
    @Autowired
    private SurveyInvestigatorReInfoMapper surveyInvestigatorReInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyUserClockMapper surveyUserClockMapper;
    @Autowired
    private SurveyClockCaseMapper surveyClockCaseMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyPayInfoMapper surveyPayInfoMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyClockReInfoMapper surveyClockReInfoMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;
    @Autowired
    private SurveyPreReimbursementMapper surveyPreReimbursementMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private SurveyReimbursementFileMapper surveyReimbursementFileMapper;

    @ApiMethod(needLogin = false, descript = "调查员报销清单列表", value = "backend-fee-re-list-new")
    @Override
    public ApiResponse surveyUserSuedList(ApiRequest apiRequest) {
        Integer pageIndex = apiRequest.getInt("pageNum");
        Integer pageSize = apiRequest.getInt("pageSize");
        apiRequest.put("pageIndex",(pageIndex - 1) * pageSize);
        Long currentUserId = getCurrentUserId(apiRequest);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgRole = isRoleUser(userRoles, 104L), finance = isRoleUser(userRoles, 23L), reSupervisor = isRoleUser(userRoles, 110L),inv = isRoleUser(userRoles,50L);
        if (!orgRole && !finance && !reSupervisor &&!inv){
            return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
        }
        if (reSupervisor){// 如果有是报销查看角色 则查询所有数据
            orgRole = false;
        }
        apiRequest.put("currentUserId", currentUserId);
        apiRequest.put("orgRole", orgRole);
        apiRequest.put("finance", finance);
        apiRequest.put("reSupervisor", reSupervisor);
        String reStates = apiRequest.getString("reStates");
        if (reStates != null && !"".equals(reStates)){
            if(reStates.indexOf("1") > -1){
                reStates = reStates.replace("1","1,8,9");//包含多种状态
            }
            apiRequest.put("reStates",reStates);
        }


        List<SurveyInvestigatorReInfo> surveyInvestigatorReInfos = surveyInvestigatorReInfoMapper.selectByParam(apiRequest);
        for (SurveyInvestigatorReInfo surveyInvestigatorReInfo : surveyInvestigatorReInfos) {
            List<SurveyInvestigatorReInfo> reInfoList = surveyInvestigatorReInfoMapper.selectByReId(surveyInvestigatorReInfo.getReId());
            Supplier<Stream<SurveyInvestigatorReInfo>> streamSupplier = () -> reInfoList.stream().filter(e -> surveyInvestigatorReInfo.getSurveyOrgId().equals(e.getSurveyOrgId()));
            double orgTotalMoney = streamSupplier.get().mapToDouble(SurveyInvestigatorReInfo::getTotalMoney).sum();
            double orgTotalCaseNum = streamSupplier.get().mapToDouble(SurveyInvestigatorReInfo::getTotalCaseNum).sum();
            surveyInvestigatorReInfo.setOrgAvgMoney(orgTotalMoney / orgTotalCaseNum);
            surveyInvestigatorReInfo.setHuanbiMoney(rate(Optional.ofNullable(surveyInvestigatorReInfo.getAvgMoney()).orElse(0d), Optional.ofNullable(surveyInvestigatorReInfo.getAvgMoney2()).orElse(0d)));
        }
        int count = surveyInvestigatorReInfoMapper.selectByParamCount(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, surveyInvestigatorReInfos);
    }

    @ApiMethod(needLogin = false, descript = "报销处理操作", value = "backend-fee-re-operate-new")
    @Override
    public ApiResponse suedOperate(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        String btnCode = apiRequest.getString("btnCode");
        String reason = apiRequest.getString("reason");
        SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectByPrimaryKey(id);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        Date currentDate =  new Date();
        if (surveyInvestigatorReInfo != null) {
            if ("org_submit".equals(btnCode)) {//提交机构审核
                surveyInvestigatorReInfo.setReState(ReInfoEnum.WAIT_ORG_CHECK.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.WAIT_ORG_CHECK.getStateName());
                surveyReimbursementFileMapper.deleteByReimId(surveyInvestigatorReInfo.getId());
                List<String> trafficFileList = new ArrayList<>();
                List<String> consultFileList = new ArrayList<>();
                List<String> accnewFileList = new ArrayList<>();
                if (StringUtils.isNotBlank(apiRequest.getString("trafficFile"))){
                    trafficFileList = JSON.parseArray(apiRequest.getString("trafficFile"), String.class);
                }
                if (StringUtils.isNotBlank(apiRequest.getString("consultFile"))){
                    consultFileList = JSON.parseArray(apiRequest.getString("consultFile"), String.class);
                }
                if (StringUtils.isNotBlank(apiRequest.getString("accnewFile"))){
                    accnewFileList = JSON.parseArray(apiRequest.getString("accnewFile"), String.class);
                }
                for (String s : trafficFileList) {
                    SurveyReimbursementFile surveyReimbursementFile = new SurveyReimbursementFile();
                    surveyReimbursementFile.setReimbursementId(surveyInvestigatorReInfo.getId());
                    surveyReimbursementFile.setFileCode("traffic");
                    surveyReimbursementFile.setFileUrl(s);
                    surveyReimbursementFile.setCreateTime(currentDate);
                    surveyReimbursementFile.setCreateBy(userInfo.getUserName());
                    surveyReimbursementFileMapper.insert(surveyReimbursementFile);
                }
                for (String s : consultFileList) {
                    SurveyReimbursementFile surveyReimbursementFile = new SurveyReimbursementFile();
                    surveyReimbursementFile.setReimbursementId(surveyInvestigatorReInfo.getId());
                    surveyReimbursementFile.setFileCode("consult");
                    surveyReimbursementFile.setFileUrl(s);
                    surveyReimbursementFile.setCreateTime(currentDate);
                    surveyReimbursementFile.setCreateBy(userInfo.getUserName());
                    surveyReimbursementFileMapper.insert(surveyReimbursementFile);
                }
                for (String s : accnewFileList) {
                    SurveyReimbursementFile surveyReimbursementFile = new SurveyReimbursementFile();
                    surveyReimbursementFile.setReimbursementId(surveyInvestigatorReInfo.getId());
                    surveyReimbursementFile.setFileCode("accnew");
                    surveyReimbursementFile.setFileUrl(s);
                    surveyReimbursementFile.setCreateTime(currentDate);
                    surveyReimbursementFile.setCreateBy(userInfo.getUserName());
                    surveyReimbursementFileMapper.insert(surveyReimbursementFile);
                }
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("roleId", 58L);
                String parentOrgId = surveyFranchiseeMapper.selectParentOrgId(surveyInvestigatorReInfo.getSurveyOrgId());
                paramMap.put("orgId", parentOrgId);
                List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                Map<String, Object> msgMap = new HashMap<String, Object>();
                msgMap.put("title", "费用报销审核");
                msgMap.put("content", "你有调查员提交发票，请尽快进行审核！");
                msgMap.put("keyWords", "清单名称：" + surveyInvestigatorReInfo.getReName() + "\n" + "调查员：" + surveyInvestigatorReInfo.getSurveyUserName() + "案件数量：" + surveyInvestigatorReInfo.getTotalCaseNum() + "件" + "\n" + "报销费用：" + surveyInvestigatorReInfo.getTotalMoney() + "元");
                backendWechatApi.send(toUsers, msgMap);
            } else if ("finance_submit".equals(btnCode)) {//提交财务审核
                surveyInvestigatorReInfo.setReState(ReInfoEnum.WAIT_FINANCE_CHECK.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.WAIT_FINANCE_CHECK.getStateName());
                surveyInvestigatorReInfo.setOrgCheckTime(new Date());
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("roleId", 23L);
                List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                Map<String, Object> msgMap = new HashMap<String, Object>();
                msgMap.put("title", "费用报销审核");
                msgMap.put("content", "你有调查员提交发票，请尽快进行审核！");
                msgMap.put("keyWords", "清单名称：" + surveyInvestigatorReInfo.getReName() + "\n" + "调查员：" + surveyInvestigatorReInfo.getSurveyUserName() + "案件数量：" + surveyInvestigatorReInfo.getTotalCaseNum() + "件" + "\n" + "报销费用：" + surveyInvestigatorReInfo.getTotalMoney() + "元");
                backendWechatApi.send(toUsers, msgMap);

                //查寻是否有对应的预报销 如果有则同步状态
                if (surveyInvestigatorReInfo.getDownTime() != null) {
                    String preInfoDate = DateUtils.dateToLocalDate(surveyInvestigatorReInfo.getDownTime()).plusMonths(-1).toString().substring(0, 7);//一定是上一个月的预报销单
                    SurveyPreReimbursement surveyPreReimbursement = surveyPreReimbursementMapper.selectByUserId(surveyInvestigatorReInfo.getSurveyUserId(), preInfoDate,surveyInvestigatorReInfo.getSurveyOrgId());
                    if (surveyPreReimbursement != null) {
                        surveyPreReimbursement.setState(2);
                        surveyPreReimbursementMapper.updateByPrimaryKeySelective(surveyPreReimbursement);
                    }
                }

            } else if ("finance_pass".equals(btnCode)) {//财务审核通过
                surveyInvestigatorReInfo.setReState(ReInfoEnum.THE_PAYING.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.THE_PAYING.getStateName());
                surveyInvestigatorReInfo.setFinanceCheckTime(new Date());
                //生成一条公估付款记录
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyInvestigatorReInfo.getSurveyUserId());
                SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
                surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("RE"));
                surveyPayInfo.setOrgId(surveyInvestigator.getOrgId());
                surveyPayInfo.setOrgName(surveyInvestigator.getOrgName());
                surveyPayInfo.setSourceSupportType(null);
                surveyPayInfo.setAppPayMoney(surveyInvestigatorReInfo.getTotalMoney());
                surveyPayInfo.setRemark(surveyInvestigatorReInfo.getReName());
                surveyPayInfo.setAppStartDate(currentDate);
                surveyPayInfo.setAppEndDate(currentDate);
                surveyPayInfo.setAppType(1);
                surveyPayInfo.setCreateUserId(userInfo.getUserId());
                surveyPayInfo.setCreateBy(userInfo.getUserName());
                surveyPayInfo.setPayState(1);
                surveyPayInfo.setCreateTime(currentDate);
                surveyPayInfo.setDeleteFlag(0);
                surveyPayInfo.setUpdateBy(userInfo.getUserName());
                surveyPayInfo.setUpdateTime(currentDate);
                surveyPayInfo.setPayType(2);//报销费
                surveyPayInfo.setPaySurveyUserId(surveyInvestigator.getUserId());
                surveyPayInfo.setPaySurveyUserName(surveyInvestigator.getRealName());
                surveyPayInfo.setPayKeyId(surveyInvestigatorReInfo.getId());

                //同时查询是否是员工管理中的人员
                StaffPersonnelInfo info  = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(surveyInvestigator.getUserId());
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

                //查寻是否有对应的预报销 如果有则同步状态
                if (surveyInvestigatorReInfo.getDownTime() != null) {
                    String preInfoDate = DateUtils.dateToLocalDate(surveyInvestigatorReInfo.getDownTime()).plusMonths(-1).toString().substring(0, 7);//一定是上一个月的预报销单
                    SurveyPreReimbursement surveyPreReimbursement = surveyPreReimbursementMapper.selectByUserId(surveyInvestigatorReInfo.getSurveyUserId(), preInfoDate,surveyInvestigatorReInfo.getSurveyOrgId());
                    if (surveyPreReimbursement != null) {
                        surveyPreReimbursement.setState(3);
                        surveyPreReimbursementMapper.updateByPrimaryKeySelective(surveyPreReimbursement);
                    }
                }

            } else if ("finance_reject".equals(btnCode)) {//财务审核驳回
                surveyInvestigatorReInfo.setReState(ReInfoEnum.FINANCE_CHECK_REJECT.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.FINANCE_CHECK_REJECT.getStateName());
                surveyInvestigatorReInfo.setRejectDesc(reason);
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("roleId", 58L);
                String sendOrgId = surveyFranchiseeMapper.selectChildrens2(surveyInvestigatorReInfo.getSurveyOrgId(), 2L);
                paramMap.put("orgId", sendOrgId);
                List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                Map<String, Object> msgMap = new HashMap<String, Object>();
                msgMap.put("title", "报销审核驳回");
                msgMap.put("content", "你有一笔报销清单审核驳回，请尽快处理！");
                msgMap.put("keyWords", "清单名称：" + surveyInvestigatorReInfo.getReName() + "\n" + "案件数量：" + surveyInvestigatorReInfo.getTotalCaseNum() + "件\n" + "驳回原因：" + reason);
                backendWechatApi.send(toUsers, msgMap);

                //查寻是否有对应的预报销 如果有则同步状态
                if (surveyInvestigatorReInfo.getDownTime() != null) {
                    String preInfoDate = DateUtils.dateToLocalDate(surveyInvestigatorReInfo.getDownTime()).plusMonths(-1).toString().substring(0, 7);//一定是上一个月的预报销单
                    SurveyPreReimbursement surveyPreReimbursement = surveyPreReimbursementMapper.selectByUserId(surveyInvestigatorReInfo.getSurveyUserId(), preInfoDate,surveyInvestigatorReInfo.getSurveyOrgId());
                    if (surveyPreReimbursement != null) {
                        surveyPreReimbursement.setState(4);
                        surveyPreReimbursement.setReturnText(reason);
                        surveyPreReimbursementMapper.updateByPrimaryKeySelective(surveyPreReimbursement);
                    }
                }

            } else if ("no_re_money".equals(btnCode)) {//没有报销
                surveyInvestigatorReInfo.setReState(ReInfoEnum.NO_RE_MONEY.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.NO_RE_MONEY.getStateName());
            } else if ("pay_ok".equals(btnCode)) {//确认收款
                surveyInvestigatorReInfo.setReState(ReInfoEnum.RE_SUCCESS.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.RE_SUCCESS.getStateName());
                surveyInvestigatorReInfo.setFinshTime(new Date());
            }else if ("checkInvoice".equals(btnCode)){//查看发票
                List<SurveyReimbursementFileDto> surveyReimbursementFileDtos = surveyReimbursementFileMapper.selectBillNewByReimbursementId(surveyInvestigatorReInfo.getId());
                return new ApiResponse(ApiMsgEnum.SUCCESS,surveyReimbursementFileDtos.size(),surveyReimbursementFileDtos);
            }else if ("org_reject".equals(btnCode)){//机构驳回
                surveyInvestigatorReInfo.setReState(ReInfoEnum.ORG_CHECK_REJECT.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.ORG_CHECK_REJECT.getStateName());
                surveyInvestigatorReInfo.setRejectDesc(reason);
            }
            int result = surveyInvestigatorReInfoMapper.updateByPrimaryKeySelective(surveyInvestigatorReInfo);
            if ("pay_ok".equals(btnCode)) {
                //修改付款记录表状态survey_pay_info
                surveyPayInfoMapper.updateByKeyId(3L, surveyInvestigatorReInfo.getId());
                int count = surveyInvestigatorReInfoMapper.selectReAllOkByReId(surveyInvestigatorReInfo.getReId());
                if (count == 0) {
                    SurveyReInfo surveyReInfo = surveyReInfoMapper.selectByPrimaryKey(surveyInvestigatorReInfo.getReId());
                    surveyReInfo.setReState(1);//已完成
                    surveyReInfoMapper.updateByPrimaryKey(surveyReInfo);
                }
            }
            return new ApiResponse(result > 0 ? ApiMsgEnum.SUCCESS : ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false, descript = "报销打卡金额修改", value = "backend-fee-re-clock-operate-new")
    @Override
    public ApiResponse uptUserClockRe(ApiRequest apiRequest) {
        Long clockReId = apiRequest.getLong("clockReId");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        SurveyClockReInfo surveyClockReInfo = surveyClockReInfoMapper.selectByPrimaryKey(clockReId);
        if (surveyClockReInfo != null){
            SurveyUserClockDto surveyUserClock = surveyUserClockMapper.selectByPrimaryKey(surveyClockReInfo.getClockId());
            surveyClockReInfo.setId(clockReId);
            surveyClockReInfo.setCityinDrivingMoney(apiRequest.getDouble("cityinDrivingMoney"));
            surveyClockReInfo.setMedicalHistoryMoney(apiRequest.getDouble("medicalHistoryMoney"));
            surveyClockReInfo.setTroubleshootingMoney(apiRequest.getDouble("troubleshootingMoney"));
//            surveyClockReInfo.setPrintingMoney(apiRequest.getDouble("printingMoney"));
            surveyClockReInfo.setAccommodatioMoney(apiRequest.getDouble("accommodatioMoney"));
            surveyClockReInfo.setCrossDrivingMoney(apiRequest.getDouble("crossDrivingMoney"));
            surveyClockReInfo.setSelfDrivingMoney(apiRequest.getDouble("selfDrivingMoney"));
            surveyClockReInfo.setKilometresNum(apiRequest.getDouble("kilometresNum"));
            surveyClockReInfo.setOtherMoney(apiRequest.getDouble("otherMoney"));
            surveyClockReInfo.setClockDesc(apiRequest.getString("clockDesc"));
            surveyClockReInfo.setTollMoney(apiRequest.getDouble("tollMoney"));
            JSONObject medicalObj = JSON.parseObject(apiRequest.getString("medicalList"));//病史费
            JSONObject troubleObj = JSON.parseObject(apiRequest.getString("troubleList"));//排查费
            JSONObject opcTroubleObj = JSON.parseObject(apiRequest.getString("opcTroubleList"));//门诊排查费
            JSONObject printObj = JSON.parseObject(apiRequest.getString("printList"));//体检报告打印费
            surveyClockReInfo.setMedicalHistoryMoney(getJsonObjectValuesSum(medicalObj));
            surveyClockReInfo.setTroubleshootingMoney(getJsonObjectValuesSum(troubleObj));
            surveyClockReInfo.setOpcTroubleshootingMoney(getJsonObjectValuesSum(opcTroubleObj));
            surveyClockReInfo.setPrintingMoney(getJsonObjectValuesSum(printObj));
            //修改打卡关联的报销费用明细
            surveyClockReInfoMapper.updateByPrimaryKeySelective(surveyClockReInfo);
            double mediaMoney = 0;
            double troubleMoney = 0;
            double opcMoney = 0;
            double printMoney = 0;
            //修改打卡关联的案子费用总计
            List<SurveyClockCase> surveyClockCaseList = surveyClockCaseMapper.selectByClocId(surveyClockReInfo.getClockId());
            if (surveyClockCaseList != null && surveyClockCaseList.size() > 0) {
                SurveyClockReInfo surveyClockReInfoNew = surveyClockReInfoMapper.selectByPrimaryKey(clockReId);
                int listSize = surveyClockCaseList.size();
                for (int i = 0; i < surveyClockCaseList.size(); i++) {
                    SurveyClockCase surveyClockCase = surveyClockCaseList.get(i);
                    String str = surveyClockCase.getId().toString();
                    if (medicalObj != null) {
                        if (medicalObj.get(str) != null){
                            double v = Double.parseDouble(medicalObj.get(str).toString());
                            surveyClockCase.setMedicalHistoryMoney(v);
                        }
                    }
                    if (troubleObj != null) {
                        if (troubleObj.get(str) != null){
                            double v = Double.parseDouble(troubleObj.get(str).toString());
                            surveyClockCase.setTroubleshootingMoney(v);
                        }
                    }
                    if (opcTroubleObj != null) {
                        if (opcTroubleObj.get(str) != null){
                            double v = Double.parseDouble(opcTroubleObj.get(str).toString());
                            surveyClockCase.setOpcTroubleshootingMoney(v);
                        }
                    }
                    if (printObj != null) {
                        if (printObj.get(str) != null){
                            double v = Double.parseDouble(printObj.get(str).toString());
                            surveyClockCase.setPrintingMoney(v);
                        }
                    }
                    surveyClockCase.setCityinDrivingMoney(getAvgMoney(surveyClockReInfoNew.getCityinDrivingMoney(), listSize, i == listSize - 1));
//                    surveyClockCase.setPrintingMoney(getAvgMoney(surveyClockReInfoNew.getPrintingMoney(), listSize, i == listSize - 1));
                    surveyClockCase.setAccommodatioMoney(getAvgMoney(surveyClockReInfoNew.getAccommodatioMoney(), listSize, i == listSize - 1));
                    surveyClockCase.setCrossDrivingMoney(getAvgMoney(surveyClockReInfoNew.getCrossDrivingMoney(), listSize, i == listSize - 1));
                    surveyClockCase.setSelfDrivingMoney(getAvgMoney(surveyClockReInfoNew.getSelfDrivingMoney(), listSize, i == listSize - 1));
                    surveyClockCase.setTollMoney(getAvgMoney(surveyClockReInfoNew.getTollMoney(), listSize, i == listSize - 1));
                    surveyClockCase.setOtherMoney(getAvgMoney(surveyClockReInfoNew.getOtherMoney(), listSize, i == listSize - 1));
                    surveyClockCase.setReTotalMoney(surveyClockCase.getCityinDrivingMoney() + surveyClockCase.getMedicalHistoryMoney() + surveyClockCase.getTroubleshootingMoney() + surveyClockCase.getPrintingMoney()
                            + surveyClockCase.getAccommodatioMoney() + surveyClockCase.getCrossDrivingMoney() + surveyClockCase.getSelfDrivingMoney() + surveyClockCase.getTollMoney() + surveyClockCase.getOtherMoney() + Optional.ofNullable(surveyClockCase.getOpcTroubleshootingMoney()).orElse(0d));
                    surveyClockCaseMapper.updateByPrimaryKey(surveyClockCase);
                }
                mediaMoney = surveyClockCaseList.stream().mapToDouble(SurveyClockCase::getMedicalHistoryMoney).sum();
                troubleMoney = surveyClockCaseList.stream().mapToDouble(SurveyClockCase::getTroubleshootingMoney).sum();
                opcMoney = surveyClockCaseList.stream().mapToDouble(SurveyClockCase::getOpcTroubleshootingMoney).sum();
                printMoney = surveyClockCaseList.stream().mapToDouble(SurveyClockCase::getPrintingMoney).sum();
                surveyClockReInfo.setMedicalHistoryMoney(mediaMoney);
                surveyClockReInfo.setTroubleshootingMoney(troubleMoney);
                surveyClockReInfo.setOpcTroubleshootingMoney(opcMoney);
                surveyClockReInfo.setPrintingMoney(printMoney);
                //修改打卡关联的报销费用明细
                surveyClockReInfoMapper.updateByPrimaryKeySelective(surveyClockReInfo);
                //判断是否有预报销 有则修改预报销的金额
                SurveyPreReimbursement surveyPreReimbursement = surveyPreReimbursementMapper.selectByUserId(surveyUserClock.getSurveyUserId(),DateUtils.DateToStr(surveyUserClock.getClockTime(),"yyyy-MM"),surveyUserClock.getSurveyOrgId());
                if (surveyPreReimbursement != null){
                    surveyPreReimbursementMapper.updateBySelect(surveyPreReimbursement.getId(), surveyPreReimbursement.getSurveyUserId(),surveyPreReimbursement.getSurveyOrgId());
                }
                String reInfoDate = DateUtils.dateToLocalDate(surveyUserClock.getClockTime()).plusMonths(1).toString().substring(0,7);
                SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectReInfoSurveyUserId(surveyUserClock.getSurveyUserId(),reInfoDate,surveyUserClock.getSurveyOrgId());
                if (surveyInvestigatorReInfo != null){
                    Double totalMoney = surveyInvestigatorReInfoMapper.selectSurveyUserAllMoneyBySlective(surveyInvestigatorReInfo.getSurveyUserId(), sf.format(surveyUserClock.getClockTime()), surveyInvestigatorReInfo.getSurveyOrgId());
                    surveyInvestigatorReInfo.setTotalMoney(Optional.ofNullable(totalMoney).orElse(0d));
                    surveyInvestigatorReInfo.setAvgMoney(surveyInvestigatorReInfo.getTotalMoney() / surveyInvestigatorReInfo.getTotalCaseNum());
                    surveyInvestigatorReInfoMapper.updateByPrimaryKey(surveyInvestigatorReInfo);
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(needLogin = false, descript = "费用报销打卡列表", value = "backend-survey-re-clock-list")
    @Override
    public ApiResponse getSurveyReClockList(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgRole = isRoleUser(userRoles, 104L), lfManger = isRoleUser(userRoles, 95L), finance = isRoleUser(userRoles, 23L), reSupervisor = isRoleUser(userRoles, 110L);//机构角色查看机构下面的所有调查员打卡记录
        apiRequest.put("orgRole", orgRole);
        apiRequest.put("finance", finance);
        apiRequest.put("lfManger", lfManger);
        apiRequest.put("reSupervisor", reSupervisor);
        apiRequest.put("surveyUserId", currentUserId);
        if (orgRole) {
            SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (investigator != null) {
                SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(investigator.getOrgId());
                apiRequest.put("orgId", franchisee.getId());
            }
        }
        List<SurveyUserClockDto> surveyUserClocks;
        if (StringUtils.isNotBlank(apiRequest.getString("clockIds"))) {
            surveyUserClocks = surveyUserClockMapper.selectByParamFromPre(apiRequest);
        } else {
            surveyUserClocks = surveyUserClockMapper.selectByParam(apiRequest);
        }
        String type = apiRequest.getString("type");
        Iterator<SurveyUserClockDto> iterator = surveyUserClocks.iterator();
        while (iterator.hasNext()) {
            SurveyUserClockDto surveyUserClock = iterator.next();
            List<SurveyClockCase> clockCase = surveyClockCaseMapper.selectSurveyClockCase(surveyUserClock.getId(), type);
            if (clockCase != null && !clockCase.isEmpty()) {
                surveyUserClock.setClockCaseList(clockCase);
                SurveyClockReInfo surveyClockReInfo = surveyClockReInfoMapper.selectByClockId(surveyUserClock.getId());
                if (!"all".equals(type)) {//如果是保险或者互助点进来的 费用使用保险案子费用总和  或者互助费用总和
                    surveyClockReInfo.setCityinDrivingMoney(clockCase.stream().mapToDouble(e -> getDoubleValue(e.getCityinDrivingMoney())).sum());
                    surveyClockReInfo.setTollMoney(clockCase.stream().mapToDouble(e -> getDoubleValue(e.getTollMoney())).sum());
                    surveyClockReInfo.setOtherMoney(clockCase.stream().mapToDouble(e -> getDoubleValue(e.getOtherMoney())).sum());
                    surveyClockReInfo.setSelfDrivingMoney(clockCase.stream().mapToDouble(e -> getDoubleValue(e.getSelfDrivingMoney())).sum());
                    surveyClockReInfo.setCrossDrivingMoney(clockCase.stream().mapToDouble(e -> getDoubleValue(e.getCrossDrivingMoney())).sum());
                    surveyClockReInfo.setAccommodatioMoney(clockCase.stream().mapToDouble(e -> getDoubleValue(e.getAccommodatioMoney())).sum());
                    surveyClockReInfo.setPrintingMoney(clockCase.stream().mapToDouble(e -> getDoubleValue(e.getPrintingMoney())).sum());
//                    surveyClockReInfo.setTroubleshootingMoney(clockCase.stream().mapToDouble(e -> getDoubleValue(e.getTroubleshootingMoney())).sum());
//                    surveyClockReInfo.setMedicalHistoryMoney(clockCase.stream().mapToDouble(e -> getDoubleValue(e.getMedicalHistoryMoney())).sum());
                }
                surveyUserClock.setSurveyClockReInfo(surveyClockReInfo);
                surveyUserClock.setSurveyCostApplyDto(surveyClockReInfoMapper.selectCostApplyByClockId(surveyUserClock.getId()));
            } else {//如果是保险或者互助点进来的 去除没有关联到保险或者互助案子的打卡记录
                iterator.remove();
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS, surveyUserClocks.size(), surveyUserClocks);
    }

    @ApiMethod(needLogin = false, descript = "报销管理清单列表", value = "backend-fee-re-manager-new")
    @Override
    public ApiResponse suedManagerList(ApiRequest apiRequest) {
        setBackendPageSize(apiRequest);
        Long currentUserId = getCurrentUserId(apiRequest);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgRole = isRoleUser(userRoles, 104L);
        SurveyInvestigator surveyInvestigator = null;
        if (orgRole) {
            surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
        }
        List<SurveyReInfoDto> surveyReInfoDtoList = surveyReInfoMapper.selectAllListByParam(apiRequest);
        int count = surveyReInfoMapper.selectCountAllListByParam(apiRequest);
        for (SurveyReInfoDto dto : surveyReInfoDtoList) {
            SurveyReInfoDto surveyReInfoDto = surveyReInfoMapper.selectReInfo(dto.getId(), orgRole, orgRole ? surveyInvestigator.getOrgId() : null);
            dto.setCaseCount(surveyReInfoDto.getCaseCount());
            dto.setPaying(surveyReInfoDto.getPaying());
            dto.setReOk(surveyReInfoDto.getReOk());
            dto.setReSurveyInvCount(surveyReInfoDto.getReSurveyInvCount());
            dto.setWaitFanceCheck(surveyReInfoDto.getWaitFanceCheck());
            dto.setWaitOrgCheck(surveyReInfoDto.getWaitOrgCheck());
            dto.setWaitSubmitInvoice(surveyReInfoDto.getWaitSubmitInvoice());
            dto.setWaitOkAccount(surveyReInfoDto.getWaitOkAccount());
            dto.setReTotalMoney(surveyReInfoDto.getReTotalMoney());
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, surveyReInfoDtoList);
    }


    private Boolean isRoleUser(List<BusUserRole> busUserRoles, Long roleId) {
        for (BusUserRole busUserRole : busUserRoles) {
            if (busUserRole.getRoleId().equals(roleId)) {
                return true;
            }
        }
        return false;
    }

    public double getAvgMoney(Double totalMoney, int size, boolean last) {
        if (totalMoney == null) {
            return 0;
        }
        totalMoney = Double.parseDouble(String.format("%.2f", totalMoney));
        double avgMoney = Double.parseDouble(String.format("%.2f", totalMoney / size));
        return last ? Double.parseDouble(String.format("%.2f", totalMoney - avgMoney * (size - 1))) : avgMoney;
    }

    public double getDoubleValue(Double value) {
        return Optional.ofNullable(value).orElse(0d);
    }

    public Double getJsonObjectValuesSum(JSONObject jsonObject){
        if (jsonObject == null) return null;
        Collection<Object> values = jsonObject.values();
        return values.stream().mapToDouble(e->(e==null || "".equals(e.toString()))?0:Double.parseDouble(e.toString())).sum();
    }

    private Double rate(Double a,Double b){
        if (a == 0){
            return b == 0 ? 0 : -b;
        }
        if (b == 0){
            return a == 0 ? 0 : a;
        }
        return new BigDecimal((float)(a - b) / b * 100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
