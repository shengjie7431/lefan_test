package com.lefancrm.apicenter.backendapi.impl;

import cn.jpush.api.utils.StringUtils;
import com.lefancrm.apicenter.backendapi.SurveyRiskCaseApi;
import com.lefancrm.apicenter.dto.SurveyNumberDto;
import com.lefancrm.apicenter.fina.dao.FinaApplicantInfoMapper;
import com.lefancrm.apicenter.fina.enums.AppcationInfoEnum;
import com.lefancrm.apicenter.fina.model.FinaApplicantInfo;
import com.lefancrm.apicenter.util.*;
import com.lefancrm.apicenter.util.pinganfu.StringUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianfeng on 2018/12/18.
 */
@Service
@ApiService(descript = "狄大人案件主表API")
public class BackendSurveyRiskCaseApiImpl extends BaseServiceImpl implements SurveyRiskCaseApi{
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private SurveyTaskTypeMapper surveyTaskTypeMapper;

    @Autowired
    private SurveyBusinessTypeMapper surveyBusinessTypeMapper;
    @Autowired
    private SurveyTaskInfoMapper surveyTaskInfoMapper;
    @Autowired
    private SurveyServiceTypeMapper surveyServiceTypeMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyCaseWorkflowMapper surveyCaseWorkflowMapper;
    @Autowired
    private SurveyConsignerMapper surveyConsignerMapper;
    @Autowired
    private BackendSurveyProgressApiImpl backendSurveyProgressApi;
    @Autowired
    private SurveyBackCaseMapper surveyBackCaseMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private BackendSurveyMessageApiImpl backendSurveyMessageApi;
    @Autowired
    private SurveyRiskCaseTransferMapper surveyRiskCaseTransferMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private SurveyBusinessTaskTypeMapper surveyBusinessTaskTypeMapper;
    @Autowired
    private SurveyConsignorModelMapper surveyConsignorModelMapper;
    @Autowired
    private FinaApplicantInfoMapper finaApplicantInfoMapper;
    @Autowired
    private SurveyConsignorDepartmentMapper surveyConsignorDepartmentMapper;
    @Autowired
    private SurveyConsignerDepartmentMapper surveyConsignerDepartmentMapper;


    @ApiMethod(needLogin = false,descript = "委托案件",value = "entrust-survey-risk-case")
    @Override
    public ApiResponse entrustSurveyRiskCase(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        String obj = apiRequest.getString("obj");
        String btnCode = apiRequest.getString("btnCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        SurveyRiskCase surveyRiskCase = null;
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(id);
        try {
            String modelId = apiRequest.getString("modelId");
            //调查处理--编辑
            if("survey-base-info-upd".equals(btnCode)) {
                updateDcyListInfo(surveyRiskCaseInfo, apiRequest);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            if(modelId != null ){
                //中德安联模板（该模板与其他模板字段相同，页面上id修改了）
                if("3".equals(modelId)){
                    String policyNo = apiRequest.getString("policyNo4");//保险合同编号
                    if (StringUtils.isNotEmpty(policyNo)) {
                        apiRequest.put("policyNo", policyNo);
                    }
                }
                //互助
                else if("5".equals(modelId)){
                    String claimsNo = apiRequest.getString("claimsNoHz");//互助案件编号
                    if (StringUtils.isNotEmpty(claimsNo)) {
                        apiRequest.put("claimsNo", claimsNo);
                    }
                }
            }

            //互助：等待期截止日期
            String hzWaitEndTime = apiRequest.getString("hzWaitEndTime");//等待期截止日期
            if (StringUtils.isNotEmpty(hzWaitEndTime)) {
                Date hzWaitEndTimeD = DateUtils.parseDate(hzWaitEndTime, "yyyy-MM-dd");
                apiRequest.put("hzWaitEndTime", hzWaitEndTimeD);
            }

            //出险时间
            String dangerTime = apiRequest.getString("dangerTime");//出险日期
            if (StringUtils.isNotEmpty(dangerTime)) {
                Date dangerTimeD = DateUtils.parseDate(dangerTime, "yyyy-MM-dd");
                apiRequest.put("dangerTime", dangerTimeD);
            }

            //保单生效日
            String insureTakeTime = apiRequest.getString("insureTakeTime");
            if (StringUtils.isNotEmpty(insureTakeTime)) {
                Date insureTakeTimeD = DateUtils.parseDate(insureTakeTime, "yyyy-MM-dd");
                apiRequest.put("insureTakeTime", insureTakeTimeD);
            }

            //投保日期
            String insureTime = apiRequest.getString("insureTime");
            if (StringUtils.isNotEmpty(insureTime)) {
                Date insureTimeD = DateUtils.parseDate(insureTime, "yyyy-MM-dd");
                apiRequest.put("insureTime", insureTimeD);
            }

            Integer investigationArea = apiRequest.getInt("surveyArea");
            //委托时间
            String entrustTime = apiRequest.getString("entrustTime");
            if (StringUtils.isNotEmpty(entrustTime)) {
                Date entrustTimeD = DateUtils.parseDate(entrustTime, "yyyy-MM-dd");
                apiRequest.put("entrustTime", entrustTimeD);
            }
            if (surveyRiskCaseInfo != null){
                surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
                //截止时间
                String endTime = apiRequest.getString("endTime");
                if (StringUtils.isNotEmpty(endTime)) {
                    Date endTimeD = DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss");
                    apiRequest.put("endTime",endTimeD);
                }
                //surveyRiskCase surveyRiskCaseInfo  原本的ID ，通过tobean之后 id也变 所以把id清空
                if (apiRequest.containsKey("id")){
                    apiRequest.remove("id");
                }
                surveyRiskCase = ConvertToBeanUtil.toBean(apiRequest,surveyRiskCase);
                surveyRiskCaseInfo = ConvertToBeanUtil.toBean(apiRequest,surveyRiskCaseInfo);
                String oprType = apiRequest.getString("oprType");
                if ("1".equals(oprType)){
                    surveyRiskCaseInfo.setSupplementState(2);
                }else if ("2".equals(oprType)){
                    surveyRiskCaseInfo.setSurveyState(2);
                    surveyRiskCaseInfo.setSurveyStateName("待受理");
                }
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);
            }else {
                Long payType = apiRequest.getLong("payType");
                Double entrustMoney = apiRequest.getDouble("entrustMoney");
                SurveyConsigner surveyConsigner = surveyConsignerMapper.selectByUserId(currentUserId);
                Long entrustDepartment = null;
                if ("agent".equals(obj)){//如果是代理申请 则委托人是页面上选择的。
                    Long entrustUser = apiRequest.getLong("entrustUser");
                    surveyConsigner = surveyConsignerMapper.selectByUserId(entrustUser);
                    entrustDepartment = apiRequest.getLong("entrustDepartment");
                }
                if(btnCode != null){
                    if("transfer".equals(btnCode)){
                        surveyConsigner = surveyConsignerMapper.selectByUserId(apiRequest.getLong("entrustUser"));
                        entrustDepartment = apiRequest.getLong("entrustDepartment");
                    }
                }
                if (surveyConsigner == null){
                    return new ApiResponse(ApiMsgEnum.SURVEY_SURVEYCONSIGNER);
                }
                //***********保存主表信息***********
                surveyRiskCase = ConvertToBeanUtil.toBean(apiRequest,SurveyRiskCase.class);
                String code = SerialNumberUtil.getSurveyCode(surveyConsigner.getCode());
                surveyRiskCase.setSurveyNo(code);
                surveyRiskCase.setSurveyTotalMoney(0D);
                if (payType == null){
                    payType = 3L;
                }
                if (payType == 1 || payType == 2){
                    surveyRiskCase.setEntrustTotalMoney(entrustMoney);
                }else{
                    surveyRiskCase.setEntrustTotalMoney(0D);
                }
                surveyRiskCase.setEntrustUserId(surveyConsigner.getUserId());
                surveyRiskCase.setEntrustUserName(surveyConsigner.getUserName());

                //获取委托人认证信息 得到委托人所在机构
                surveyRiskCase.setEntrustOrgId(surveyConsigner.getEntrustOrgId());
                surveyRiskCase.setEntrustOrgName(surveyConsigner.getEntrustOrgName());
                //委托人部门信息
                if (entrustDepartment == null){
                    //如果不是代理委托，委托人部门ID默认一个。
                    Map<String,Object> paramMap = new HashMap<>();
                    paramMap.put("consignorOrgId",surveyConsigner.getEntrustOrgId());
                    paramMap.put("consignerUserId",surveyConsigner.getId());
                    List<SurveyConsignerDepartment> consignerDepartments = surveyConsignerDepartmentMapper.list(paramMap);
                    if (consignerDepartments.size() > 0){
                        entrustDepartment = consignerDepartments.get(0).getConsignorDepartmentId();
                    }
                }
                surveyRiskCase.setDepartmentId(entrustDepartment);
                SurveyConsignorDepartment surveyConsignorDepartment = surveyConsignorDepartmentMapper.selectByPrimaryKey(entrustDepartment);
                if (surveyConsignorDepartment != null){
                    surveyRiskCase.setDepartmentName(surveyConsignorDepartment.getName());
                }
//                surveyRiskCase.setDepartmentId(surveyConsigner.getDepartmentId());
//                surveyRiskCase.setDepartmentName(surveyConsigner.getDepartmentName());

                surveyRiskCase.setCreateBy(userInfo.getUserName());
                surveyRiskCase.setCreateTime(new Date());
                surveyRiskCase.setDeleteFlag(0);
                surveyRiskCase.setInvestigationArea(investigationArea);
                //去除“案件编号”空格：影响报告导出名称
//                surveyRiskCase.setSurveyCaseNo((surveyRiskCase.getSurveyCaseNo()).trim());
                surveyRiskCase.setSurveyCaseNo(surveyRiskCase.getSurveyCaseNo().replace(" ","").replace("\t","").trim());
                String idType=apiRequest.getString("idType");
                if(idType != null && !idType.equals("")){
                    surveyRiskCase.setIdType(Integer.parseInt(idType));
                }
                surveyRiskCaseMapper.insert(surveyRiskCase);

                //***********保存子表信息**********
                String endTime = apiRequest.getString("endTime");
                if (endTime != null && !"".equals(endTime)){
                    Date endTimeD = DateUtils.parseDate(apiRequest.getString("endTime"), "yyyy-MM-dd HH:mm:ss");
                    apiRequest.put("endTime",endTimeD);
                }
                surveyRiskCaseInfo = ConvertToBeanUtil.toBean(apiRequest,SurveyRiskCaseInfo.class);
                code = SerialNumberUtil.getSurveyCode(surveyConsigner.getCode(),"C");
                if (surveyRiskCaseInfo.getPayType() == null){
                    surveyRiskCaseInfo.setPayType(3);
                }
                surveyRiskCaseInfo.setSurveyCno(code);
                surveyRiskCaseInfo.setSurveyNo(surveyRiskCase.getSurveyNo());
                surveyRiskCaseInfo.setSurveyId(surveyRiskCase.getId());
                surveyRiskCaseInfo.setSurveyPhase(1);//委托阶段
                surveyRiskCaseInfo.setSurveyState(2);
                surveyRiskCaseInfo.setSurveyStateName("待受理");
                surveyRiskCaseInfo.setCreateUserId(surveyConsigner.getUserId());
                surveyRiskCaseInfo.setCreateUserName(surveyConsigner.getUserName());

                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyConsigner.getEntrustOrgId());
                if (surveyConsignor == null){
                    return new ApiResponse(ApiMsgEnum.SURVEY_INVESTIGATER_NOT);
                }
                //案源机构
                surveyRiskCaseInfo.setSourceSupportType(surveyConsignor.getBusinessAttr());
                surveyRiskCaseInfo.setEntrustCredit(1);
                surveyRiskCaseInfo.setEntrustCreditMoney(0D);
                surveyRiskCaseInfo.setEntrustCreditIsPay(null);
                surveyRiskCaseInfo.setEntrustOrgId(surveyConsigner.getEntrustOrgId());
                surveyRiskCaseInfo.setEntrustOrgName(surveyConsigner.getEntrustOrgName());
                surveyRiskCaseInfo.setIsSun(0);
                surveyRiskCaseInfo.setReportState(0);
                surveyRiskCaseInfo.setSupplementState(0);
                surveyRiskCaseInfo.setEntrustStartDate(surveyRiskCase.getEntrustTime());
                surveyRiskCaseInfo.setCreateBy(userInfo.getUserName());
                surveyRiskCaseInfo.setAgentUserId(userInfo.getUserId());//委托代理人（创建人）
                surveyRiskCaseInfo.setCreateTime(new Date());
                surveyRiskCaseInfo.setUpdateTime(new Date());
                surveyRiskCaseInfo.setUpdateBy(userInfo.getUserName());
                surveyRiskCaseInfo.setAssignState(0);
                surveyRiskCaseInfo.setAcceptState(0);
                surveyRiskCaseInfo.setDeleteFlag(0);
                surveyRiskCaseInfo.setOrgAssign(0);
                surveyRiskCaseInfo.setIsSendReport(0);
                surveyRiskCaseInfo.setIsPayEntrustFee(0);
                surveyRiskCaseInfo.setIsClassic(0);//是否经典案例
                surveyRiskCaseInfo.setPrice1IsCalc(0);//基本费是否结算
                surveyRiskCaseInfo.setPrice2IsCalc(0);//减损奖励是否结算
                surveyRiskCaseInfo.setTaskDispatchState(0);//0默认状态 1可调度 2调度中 3调度通过 4调度不通过
                surveyRiskCaseInfo.setOrgReturn(0);//机构主动退回(0：否，1:是)
                surveyRiskCaseInfo.setGuide(0);
                surveyRiskCaseInfo.setGuideState(0);
                surveyRiskCaseInfo.setReimState(0);//案件费用报销状态(0：未发起，1：未完成，2：报销完成)

                //保险类案件，不需要展示“案件指导”
                SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(surveyRiskCase.getEntrustOrgId());
                if(surveyConsignorModel != null && surveyConsignorModel.getModelId() ==5) {
                    surveyRiskCaseInfo.setGuide(1);
                }

                String surveyNo = surveyRiskCase.getSurveyNo() == null ? "" : surveyRiskCase.getSurveyNo();
                String surveyCno = surveyRiskCaseInfo.getSurveyCno() == null ? "" : surveyRiskCaseInfo.getSurveyCno();
                String surveyUserName = surveyRiskCaseInfo.getSurveyUserName() == null ? "" : surveyRiskCaseInfo.getSurveyUserName();
                String entrustOrgName = surveyRiskCaseInfo.getEntrustOrgName() == null ? "" : surveyRiskCaseInfo.getEntrustOrgName();
                String surveyPerson = surveyRiskCase.getSurveyPerson() == null ? "" : surveyRiskCase.getSurveyPerson();
                String surveyBusName = surveyRiskCaseInfo.getSurveyBusName() == null ? "" : surveyRiskCaseInfo.getSurveyBusName();
                String survery_person_tel = surveyRiskCase.getSurveryPersonTel() == null ? "" : surveyRiskCase.getSurveryPersonTel();
                String policyNo = surveyRiskCase.getPolicyNo() == null ? "" : surveyRiskCase.getPolicyNo();
                String claimsNo = surveyRiskCase.getClaimsNo() == null ? "" : surveyRiskCase.getClaimsNo();
                String entrustUserName = surveyRiskCase.getEntrustUserName() == null ? "" : surveyRiskCase.getEntrustUserName();
                String searchCondition = String.format("{surveyNo:%s}{surveyPerson:%s}{survery_person_tel:%s}{policyNo:%s}{claimsNo:%s}{entrustUserName:%s}{entrustOrgName:%s}{surveyCno:%s}" +
                        "{surveyBusName:%s}{surveyUserName:%s}",surveyNo,surveyPerson,survery_person_tel,policyNo,claimsNo,entrustUserName,entrustOrgName,surveyCno,surveyBusName,surveyUserName);
                surveyRiskCaseInfo.setSearchCondition(searchCondition);

            }

            //领域
            Long surveyBusId = apiRequest.getLong("surveyBusId");
            SurveyBusinessType surveyBusinessType = surveyBusinessTypeMapper.selectByPrimaryKey(surveyBusId);
            if (surveyBusinessType == null){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
            surveyRiskCaseInfo.setSurveyBusId(surveyBusId);
            surveyRiskCaseInfo.setSurveyBusName(surveyBusinessType.getName());
            //业务类型
            Long servicesId = apiRequest.getLong("servicesId");
            SurveyServiceType surveyServiceType = surveyServiceTypeMapper.selectByPrimaryKey(servicesId);
            if (surveyServiceType == null){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
            surveyRiskCaseInfo.setServicesId(servicesId);
            surveyRiskCaseInfo.setServicesName(surveyServiceType.getName());
            //计算委托方价格
            Integer payType = surveyRiskCaseInfo.getPayType();
            if (payType == 1){
                Double entrustMoney = apiRequest.getDouble("entrustMoney");
                surveyRiskCaseInfo.setEntrustMoney(entrustMoney);
                surveyRiskCaseInfo.setEntrustReLosses(0D);
            }else if (payType == 2){
                Double entrustMoney = apiRequest.getDouble("entrustMoney");
                Double entrustReLosses = apiRequest.getDouble("entrustReLosses");
                surveyRiskCaseInfo.setEntrustMoney(entrustMoney);
                surveyRiskCaseInfo.setEntrustReLosses(entrustReLosses == null ? 0D : entrustReLosses);
            }else if (payType == 3){
                surveyRiskCaseInfo.setEntrustMoney(0D);
                surveyRiskCaseInfo.setEntrustReLosses(0D);
            }else if (payType == 4){
                surveyRiskCaseInfo.setEntrustMoney(0D);
                surveyRiskCaseInfo.setEntrustReLosses(0D);
            }
            //调查方价格
            surveyRiskCaseInfo.setSurveyMoney(0D);
            surveyRiskCaseInfo.setSurveryReLosses(0D);
            surveyRiskCaseInfo.setSubServiceId(apiRequest.getLong("subServiceId"));
            surveyRiskCaseInfo.setPerformanceState(0);// 结算绩效的状态 （0、未结算；1、结算中；2、已结算）
            if (id != null){
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(),surveyRiskCaseInfo.getId(),surveyRiskCaseInfo.getCreateUserId(),surveyRiskCaseInfo.getCreateUserName(),"委托申请信息更改提交","");
            }else{

                surveyRiskCaseInfo.setSurveyItem(LFStringUtil.replacePrint(surveyRiskCaseInfo.getSurveyItem()));
                surveyRiskCaseInfo.setSurveyInfo(LFStringUtil.replacePrint(surveyRiskCaseInfo.getSurveyInfo()));
                surveyRiskCaseInfoMapper.insert(surveyRiskCaseInfo);

                //垫付案件 -- 发起乐凡调查
                Long finaInfoId = apiRequest.getLong("finaInfoId");
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);
                if(finaApplicantInfo != null){
                    finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_DFBSZSZ.getState());//5、垫付保司审核中
                    finaApplicantInfo.setFinshCollectTime(new Date());//材料收集完成时间
                    finaApplicantInfo.setFirstPassedTime(new Date());//初审通过时间
                    finaApplicantInfo.setFinshReviewTime(new Date());//垫付复审完成时间
                    //是否需要填写“建议垫付金额”  暂定

                    finaApplicantInfo.setUpdateBy(userInfo.getUserName());// 更新人id
                    finaApplicantInfo.setUpdateTime(new Date());//更新时间
                    finaApplicantInfo.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
                }

                //添加进度
                backendSurveyProgressApi.saveProgress(surveyRiskCaseInfo.getSurveyId(),surveyRiskCaseInfo.getId(),surveyRiskCaseInfo.getCreateUserId(),surveyRiskCaseInfo.getCreateUserName(),"委托申请发起","");
            }

            //二调
            if("transfer".equals(btnCode)){
                String nextTransferType = apiRequest.getString("nextTransferType");//下一个“二调”的类型
                //如果是真二调，把一调数据保存
                if("2".equals(nextTransferType)){
                    SurveyRiskCaseTransfer transfer = new SurveyRiskCaseTransfer();
                    String topSurveyId = apiRequest.getString("topSurveyId");//"发起二调”的顶级 调查id
                    transfer.setSurveyParentId(Long.valueOf(topSurveyId));
                    transfer.setSurveyId(Long.valueOf(topSurveyId));
                    transfer.setTransferType(1);
                    transfer.setTransferTypeName("一调");
                    transfer.setIds(topSurveyId);
                    surveyRiskCaseTransferMapper.insert(transfer);
                }

                surveyRiskCase.setTransferType(Integer.valueOf(nextTransferType));
                surveyRiskCase.setTransferTypeName(ConvertToBeanUtil.toChinese(nextTransferType)+"调");
                surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);

                SurveyRiskCaseTransfer transfer = new SurveyRiskCaseTransfer();
                String topSurveyId = apiRequest.getString("topSurveyId");//"发起二调”的顶级 调查id

                transfer.setSurveyParentId(Long.valueOf(topSurveyId));
                transfer.setSurveyId(surveyRiskCaseInfo.getId());
                transfer.setTransferType(Integer.valueOf(nextTransferType));
                transfer.setTransferTypeName(ConvertToBeanUtil.toChinese(nextTransferType)+"调");

                Map<String,Object> map = new HashMap<>();
                map.put("surveyParentId",topSurveyId);
                List<SurveyRiskCaseTransfer> transferList = surveyRiskCaseTransferMapper.list(map);
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = transferList.size()-1; i >= 0; i--) {
                    stringBuffer.append(transferList.get(i).getSurveyId()+",");
                }
                transfer.setIds(stringBuffer.toString());
                surveyRiskCaseTransferMapper.insert(transfer);
            }

            //***********保存调查委托任务类型表***********
            //领域：互助调查17 根据页面选择任务类型提交，2、其余的领域，按照其对应的业务类型 全部提交
            String taskIdStr = apiRequest.getString("chkTaskIds");
            //修改的时候 将原有的任务类型删除
            if (id != null){
                List<SurveyTaskType> taskTypes = surveyTaskTypeMapper.getSurveyTaskTypeBySurveyInfoId(surveyRiskCaseInfo.getId());
                for (SurveyTaskType taskType : taskTypes) {
                    surveyTaskTypeMapper.deleteByPrimaryKey(taskType.getId());
                }
            }
            if(surveyBusinessType.getId() == 17){
                if (StringUtils.isNotEmpty(taskIdStr)){
                    String [] taskIds = taskIdStr.split(",");
                    for (String taskId : taskIds) {
                        if (StringUtils.isNotEmpty(taskId)){
                            SurveyTaskType surveyTaskType = SurveyTaskType.class.newInstance();
                            surveyTaskType.setSurveyId(surveyRiskCase.getId());
                            surveyTaskType.setSurveyInfoId(surveyRiskCaseInfo.getId());
                            surveyTaskType.setTaskId(Long.parseLong(taskId));
                            SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyTaskType.getTaskId());
                            surveyTaskType.setTaskName(surveyTaskInfo.getName());
                            surveyTaskTypeMapper.insert(surveyTaskType);
                        }
                    }
                }
            }else{
                Map<String,Object> map  = new HashMap<>();
                map.put("businessTypeId",surveyBusinessType.getId());
                List<SurveyBusinessTaskType> businessTaskTypes = surveyBusinessTaskTypeMapper.list(map);
                for (SurveyBusinessTaskType businessTaskType : businessTaskTypes) {
                    SurveyTaskType surveyTaskType = SurveyTaskType.class.newInstance();
                    surveyTaskType.setSurveyId(surveyRiskCase.getId());
                    surveyTaskType.setSurveyInfoId(surveyRiskCaseInfo.getId());
                    surveyTaskType.setTaskId(businessTaskType.getTaskInfoId());
                    SurveyTaskInfo surveyTaskInfo = surveyTaskInfoMapper.selectByPrimaryKey(surveyTaskType.getTaskId());
                    surveyTaskType.setTaskName(surveyTaskInfo.getName());
                    surveyTaskTypeMapper.insert(surveyTaskType);
                }
            }

            //发送平台受理人
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
//            Map<String,Long> paramMap =  new HashMap<String,Long>();
//            paramMap.put("roleId",52L);
//            List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
//            String url = "/survey/case/info?id=" + surveyRiskCaseInfo.getId() + "&menuCode=check-list";
//            String content = "案件编号：" + surveyRiskCase.getSurveyCaseNo() + ",被调查人：" + surveyRiskCase.getSurveyPerson() + "，调查截止日期：" + simpleDateFormat.format(surveyRiskCaseInfo.getEndTime());
//            backendSurveyMessageApi.sendSurveyMessage(userInfo.getUserId(),userInfo.getUserName(),toUsers,4,"案件受理通知",content,url);
//
//            for (UserInfo toUser : toUsers) {
//                UserLogin userLogin = userLoginMapper.selectByPrimaryKey(toUser.getUserId());
//                if (userLogin != null) {
//                    String openid2 = userLogin.getLfpcOpenid();
//                    if (openid2 == null || "".equals(openid2)){
//                        openid2 = userLogin.getLfpc2Openid();
//                    }
//                    if (openid2 != null && !"".equals(openid2)){
//                        WechatTempleMsgUtil.sendWechatMsgDdr(openid2,"","","","案件受理通知","#FF0000",content,
//                                "#080808","","");
//                    }
//                }
//            }
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyRiskCaseInfo);
    }


    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }
    @ApiMethod(needLogin = false,descript = "调查工作台取数",value = "manager-survey")
    @Override
    public ApiResponse manager(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean entrustRole = true,lfRole = true,surveyRole = true;//a 委托人权限  b乐凡权限   c调查员权限
        int count = 0;
        SurveyNumberDto numberDto = new SurveyNumberDto();
        numberDto.setEntrustRole(isRoleUser(userRoles,51L));//委托人
        numberDto.setEntrustAgentRole(isRoleUser(userRoles,56L));//代理委托人

        numberDto.setLfZhuguan(isRoleUser(userRoles,52L));//平台主管
        numberDto.setLfNeiqin(isRoleUser(userRoles,54L));//平台内勤
        numberDto.setLfZhongshen(isRoleUser(userRoles,53L));//平台终审
        numberDto.setLfUpload(isRoleUser(userRoles,60L));//终审上传报告

        numberDto.setSurveyRole(isRoleUser(userRoles,50L));//调查员
        numberDto.setOrgChushen(isRoleUser(userRoles,57L));//机构初审
        numberDto.setOrgZhongshen(isRoleUser(userRoles,58L));//机构复核

        apiRequest.clear();
        apiRequest.put("surveyPhase",1);
        count = surveyRiskCaseInfoMapper.listSize(apiRequest);
        numberDto.setEntrustNum5(count);
        apiRequest.clear();
        apiRequest.put("surveyPhase",2);
        count = surveyRiskCaseInfoMapper.listSize(apiRequest);
        numberDto.setSurveyNum5(count);
        apiRequest.clear();
        apiRequest.put("surveyPhase",3);
        count = surveyRiskCaseInfoMapper.listSize(apiRequest);
        numberDto.setLfNum5(count);
        if (numberDto.getEntrustRole() || numberDto.getEntrustAgentRole()){
            //退回案件
            apiRequest.clear();
            apiRequest.put("menuCode","my-list");
            apiRequest.put("surveyState",6);
            if (numberDto.getEntrustAgentRole()){
                apiRequest.put("userId",currentUserId);
                apiRequest.put("condition",3); //  agent_user_id = userInfo.getUserId  or create_user_id = userInfo.getUserId
            }else{
                apiRequest.put("createUserId",currentUserId);//委托人是当前登录人
            }
            count = surveyRiskCaseInfoMapper.listSize(apiRequest);
            numberDto.setEntrustNum1(count);

            //保司待审核
            apiRequest.clear();
            apiRequest.put("menuCode","entrust-list");
            apiRequest.put("surveyPhase",2);
            apiRequest.put("surveyState",24);
            if (numberDto.getEntrustAgentRole()){//如果有代理委托的权限  则 查询所有保司待审核的列表
                //apiRequest.put("userId",currentUserId);
                //apiRequest.put("condition",3); //  agent_user_id = userInfo.getUserId  or create_user_id = userInfo.getUserId
            }else{
                apiRequest.put("createUserId",currentUserId);//委托人是当前登录人
            }
            count = surveyRiskCaseInfoMapper.listSize(apiRequest);
            numberDto.setEntrustNum2(count);
        }
        if (numberDto.getLfZhuguan() || numberDto.getLfZhongshen() || numberDto.getLfNeiqin() || numberDto.getLfUpload()){
            //委托待审核
            if (numberDto.getLfZhuguan() || numberDto.getLfNeiqin()){
                apiRequest.clear();
                apiRequest.put("menuCode","check-list");
                apiRequest.put("surveyState",2);
                count = surveyRiskCaseInfoMapper.listSize(apiRequest);
                numberDto.setLfNum1(count);

                //待分派
                apiRequest.clear();
                apiRequest.put("menuCode","assign-list");
                apiRequest.put("condition","1");
                count = surveyRiskCaseInfoMapper.listSize(apiRequest);
                numberDto.setLfNum2(count);
            }

            //调查待审核-终审
            if (numberDto.getLfZhongshen()){
                apiRequest.clear();
                apiRequest.put("menuCode","survey-list");
                apiRequest.put("condition",2);
                count = surveyRiskCaseInfoMapper.listSize(apiRequest);
                numberDto.setLfNum3(count);
            }

            //报告待上传 - 终审
            if (numberDto.getLfUpload()){
                apiRequest.clear();
                apiRequest.put("menuCode","survey-list");
                apiRequest.put("condition",2);
                count = surveyRiskCaseInfoMapper.listSize(apiRequest);
                numberDto.setAdd2(count);
            }
        }
        if (numberDto.getSurveyRole()){
            //待接收
            apiRequest.clear();
            apiRequest.put("menuCode","dcy-list");
            apiRequest.put("surveyUserId",currentUserId);
            apiRequest.put("condition",1);
            apiRequest.put("surveyState",0);
            count = surveyInvestigatorCaseMapper.listSize(apiRequest);
            numberDto.setSurveyNum1(count);

            //调查中
            apiRequest.clear();
            apiRequest.put("menuCode","dcy-list");
            apiRequest.put("surveyUserId",currentUserId);
            apiRequest.put("condition",1);
            count = surveyInvestigatorCaseMapper.listSize(apiRequest);
            numberDto.setSurveyNum2(count);
        }

        //待协助处理
        if (numberDto.getOrgZhongshen() || numberDto.getLfZhongshen()){
            apiRequest.clear();
            Boolean a = numberDto.getLfZhongshen();//平台终审
            Boolean b = numberDto.getOrgZhongshen();//狄大人机构复核
            if (a){//乐凡平台审核
                apiRequest.put("search",1);//back_state = 4
            }
            if (b){//机构复核审核
                //back_state in (1,6) 且 申请机构是当前机构
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (surveyInvestigator != null) {
                    apiRequest.put("currentOrgId",surveyInvestigator.getOrgId());
                    apiRequest.put("search",2);//back_state = 1,6 and org_id = currentOrgId or back_state = 5 and ass_org_id = currentOrgId
                }
            }
            if (a && b){//机构复核 乐凡平台都有
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (surveyInvestigator != null) {
                    apiRequest.put("currentOrgId",surveyInvestigator.getOrgId());
                    apiRequest.put("search",3);//back_state in (1,6,4)
                }
            }
            count = surveyBackCaseMapper.listSize(apiRequest);
            numberDto.setAdd1(count);
        }

        if (numberDto.getOrgChushen()){
            //机构待分派
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (surveyInvestigator != null){
                apiRequest.clear();
                apiRequest.put("surveyOrgId",surveyInvestigator.getOrgId());
                apiRequest.put("search",1);
                count = surveyAssignOrgMapper.listSize(apiRequest);
                numberDto.setAdd3(count);

                //机构初审（报告初审）
                apiRequest.clear();
                apiRequest.put("surveyState",3);
                apiRequest.put("org",surveyInvestigator.getOrgId());
                count = surveyInvestigatorCaseMapper.listSize(apiRequest);
                numberDto.setAdd4(count);
            }
        }
        if (numberDto.getOrgZhongshen()){
            //初审中
            apiRequest.clear();
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (surveyInvestigator != null){
                apiRequest.put("surveyOrgId",surveyInvestigator.getOrgId());
                apiRequest.put("search",2);
                count = surveyAssignOrgMapper.listSize(apiRequest);
                numberDto.setAdd5(count);
            }
        }

        if (numberDto.getLfZhuguan()){
            //拒接审核中
            apiRequest.clear();
            apiRequest.put("surveyState",5);
            count = surveyInvestigatorCaseMapper.listSize(apiRequest);
            numberDto.setAdd6(count);
        }

        if (numberDto.getLfZhongshen()){
            //调度审核中
            apiRequest.clear();
            apiRequest.put("taskDispatchState",2);
            count = surveyRiskCaseInfoMapper.listSize(apiRequest);
            numberDto.setAdd7(count);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,numberDto);
    }

    private void updateDcyListInfo(SurveyRiskCaseInfo surveyRiskCaseInfo, ApiRequest apiRequest) {
        //调查处理--“编辑”
        String modelId = apiRequest.getString("modelId");
        surveyRiskCaseInfo.setSurveyInfo(apiRequest.getString("surveyInfo"));
        surveyRiskCaseInfo.setSurveyItem(apiRequest.getString("surveyItem"));
        surveyRiskCaseInfo.setSurveyItem(LFStringUtil.replacePrint(surveyRiskCaseInfo.getSurveyItem()));
        surveyRiskCaseInfo.setSurveyInfo(LFStringUtil.replacePrint(surveyRiskCaseInfo.getSurveyInfo()));
        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);

        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        surveyRiskCase.setSurveyPerson(apiRequest.getString("surveyPerson"));
        surveyRiskCase.setSurveryPersonTel(apiRequest.getString("surveryPersonTel"));
        surveyRiskCase.setSurveyCaseNo(apiRequest.getString("surveyCaseNo").replace(" ","").replace("\t","").trim());
        surveyRiskCase.setIdNumber(apiRequest.getString("idNumber"));
        surveyRiskCase.setAge(apiRequest.getInt("age"));
        surveyRiskCase.setSex(apiRequest.getInt("sex"));
        surveyRiskCase.setClaimsMoney(apiRequest.getDouble("claimsMoney"));

        if(modelId !=null){
            if("4".equals(modelId)){ //中宏
                surveyRiskCase.setPolicyNo(apiRequest.getString("policyNo"));
            }else if("3".equals(modelId)){ //中德
                surveyRiskCase.setPolicyNo(apiRequest.getString("policyNo4"));

                String insureTakeTime = apiRequest.getString("insureTakeTime");
                if(StringUtils.isNotEmpty(insureTakeTime)){
                    Date insureTakeTimeD = DateUtils.parseDate(insureTakeTime, "yyyy-MM-dd");
                    surveyRiskCase.setInsureTakeTime(insureTakeTimeD);
                }
            }else if("1".equals(modelId) || "2".equals(modelId)){ //乐凡、正言
                surveyRiskCase.setInsureName(apiRequest.getString("insureName"));
                surveyRiskCase.setClaimsNo(apiRequest.getString("claimsNo"));
                surveyRiskCase.setDangerAddress(apiRequest.getString("dangerAddress"));
                String dangerTime = apiRequest.getString("dangerTime");
                if(StringUtils.isNotEmpty(dangerTime)){
                    Date dangerTimeD = DateUtils.parseDate(dangerTime, "yyyy-MM-dd");
                    surveyRiskCase.setDangerTime(dangerTimeD);
                }
            }else if("5".equals(modelId)){//互助
                surveyRiskCase.setHzContactName(apiRequest.getString("hzContactName"));
                surveyRiskCase.setHzContactTel(apiRequest.getString("hzContactTel"));
                surveyRiskCase.setHzProduct(apiRequest.getInt("hzProduct"));
                String claimsNo = apiRequest.getString("claimsNoHz");//互助案件编号
                if (StringUtils.isNotEmpty(claimsNo)) {
                    surveyRiskCase.setClaimsNo(claimsNo);
                }

                String insureTime = apiRequest.getString("insureTime");//加入日期
                if (StringUtils.isNotEmpty(insureTime)) {
                    Date insureTimeD = DateUtils.parseDate(insureTime, "yyyy-MM-dd");
                    surveyRiskCase.setInsureTime(insureTimeD);
                }
                String hzWaitEndTime = apiRequest.getString("hzWaitEndTime");//等待期截止日期
                if (StringUtils.isNotEmpty(hzWaitEndTime)) {
                    Date hzWaitEndTimeD = DateUtils.parseDate(hzWaitEndTime, "yyyy-MM-dd");
                    surveyRiskCase.setHzWaitEndTime(hzWaitEndTimeD);
                }
                String dangerTime = apiRequest.getString("dangerTime");//出险日期
                if (StringUtils.isNotEmpty(dangerTime)) {
                    Date dangerTimeD = DateUtils.parseDate(dangerTime, "yyyy-MM-dd");
                    surveyRiskCase.setDangerTime(dangerTimeD);
                }
                surveyRiskCase.setClaimsMoney(apiRequest.getDouble("claimsMoney"));
                surveyRiskCase.setHzLiveAddress(apiRequest.getString("hzLiveAddress"));
                surveyRiskCase.setDangerAddress(apiRequest.getString("dangerAddress"));
                surveyRiskCase.setHzConfirmDisease(apiRequest.getString("hzConfirmDisease"));
                surveyRiskCase.setHzFollowInfo(apiRequest.getString("hzFollowInfo"));
            }
        }
        surveyRiskCaseMapper.updateByPrimaryKey(surveyRiskCase);
    }
}
