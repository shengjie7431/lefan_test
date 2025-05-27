package com.lefancrm.apicenter.fina.api.impl;

import cn.jpush.api.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendWechatApi;
import com.lefancrm.apicenter.backendapi.impl.BackendSurveyProgressApiImpl;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyRiskCaseInfoDto;
import com.lefancrm.apicenter.fina.api.BackendFinaApplicantInfoApi;
import com.lefancrm.apicenter.fina.dao.*;
import com.lefancrm.apicenter.fina.enums.*;
import com.lefancrm.apicenter.fina.model.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.*;
import com.lefancrm.apicenter.util.bdaiPUtils.CharacterRecognitionUtils;
import com.lefancrm.apicenter.util.fina.IdNumberUtil;
import com.lefancrm.apicenter.util.fina.ShowAgingStr;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "垫付信息表相关API")
public class BackendFinaApplicantInfoApiImpl extends BaseServiceImpl implements BackendFinaApplicantInfoApi {
    @Autowired
    private FinaApplicantInfoMapper finaApplicantInfoMapper;
    @Autowired
    private FinaApplicantMapper finaApplicantMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private FinaApplicantOrgMapper finaApplicantOrgMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private FinaApplicantInvestigatorMapper finaApplicantInvestigatorMapper;
    @Autowired
    private FinaApplicantMoneyMapper finaApplicantMoneyMapper;
    @Autowired
    private FinaHospitalInfoMapper finaHospitalInfoMapper;
    @Autowired
    private FinaDiagnosisInfoMapper finaDiagnosisInfoMapper;
    @Autowired
    private FinaDiagnosisTreatmentMapper finaDiagnosisTreatmentMapper;
    @Autowired
    private FinaTreatmentInfoMapper finaTreatmentInfoMapper;
    @Autowired
    private FinaDiagnosisTreatmentMidMapper finaDiagnosisTreatmentMidMapper;
    @Autowired
    private FinaMedicalInsuranceMapper finaMedicalInsuranceMapper;
    @Autowired
    private FinaApplicantTrackMapper finaApplicantTrackMapper;
    @Autowired
    private BackendFinaFileApiImpl backendFinaFileApi;
    @Autowired
    private FinaHospitalAccountMapper finaHospitalAccountMapper;
    @Autowired
    private SurveyPayInfoMapper surveyPayInfoMapper;
    @Autowired
    private FinaConfirmAccountMapper finaConfirmAccountMapper;
    @Autowired
    private FinaTaskInfoMapper finaTaskInfoMapper;
    @Autowired
    private FinaSurveyConsignorEfficiencyModelInfoMapper finaSurveyConsignorEfficiencyModelInfoMapper;
    @Autowired
    private FinaSurveyPriceMapper finaSurveyPriceMapper;
    @Autowired
    private FinaSettlementApplicantMapper finaSettlementApplicantMapper;
    @Autowired
    private FinaSettlementInfoMapper finaSettlementInfoMapper;
    @Autowired
    private FinaSurveyCoefficientModelInfoMapper finaSurveyCoefficientModelInfoMapper;
    @Autowired
    private FinaSurveyCoefficientModelMapper finaSurveyCoefficientModelMapper;
    @Autowired
    private FinaSurveyCoefficientAreaCityMapper finaSurveyCoefficientAreaCityMapper;
    @Autowired
    private BackendWechatApi backendWechatApi;
    @Autowired
    private FinaApplicantCollectFileMapper finaApplicantCollectFileMapper;
    @Autowired
    private FinaApplicantFileMapper finaApplicantFileMapper;
    @Autowired
    private FinaFileMapper finaFileMapper;
    @Autowired
    private FinaAgreementInfoMapper finaAgreementInfoMapper;
    @Autowired
    private FinaApplicantFileEnumMapper finaApplicantFileEnumMapper;
    @Autowired
    private FinaProgressMapper finaProgressMapper;
    @Autowired
    private BackendFinaProgressApiImpl backendFinaProgressApiImpl;


    @ApiMethod(needLogin = false,descript = "垫付信息表列表",value = "list-fina-applicant-info")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {

        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        //分页
        Boolean pageFlag = !"report".equals(apiRequest.getString("report"));//是否分页
        if (pageFlag){
            setBackendPageSize(apiRequest);
        }
        String menuCode = apiRequest.getString("menuCode");

        apiRequest.put("searchData","settlement");//发起结算单 选择的案件列表。已确认到账 并且  确认到账时间不是NULL 。出院完成时间是NULL

        if("register-list".equals(menuCode)){//接案立案

        }
        else if("assign-list".equals(menuCode))//分派机构
        {
            apiRequest.put("searchData",menuCode);
            apiRequest.put("finaOrgState",apiRequest.getString("applicantStates"));
            apiRequest.put("applicantStates",null);
        }
        else if("assign-org-list".equals(menuCode))//分派垫付员
        {
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
            if (surveyInvestigator != null){
                apiRequest.put("surveyOrgId",surveyInvestigator.getOrgId());//当前登录人所在机构
            }
            apiRequest.put("searchData",menuCode);
            apiRequest.put("finaUserState",apiRequest.getString("applicantStates"));
            apiRequest.put("applicantStates",null);
        }
        else if("first-trial-list".equals(menuCode))//垫付初审
        {
            apiRequest.put("searchData",menuCode);
        }
        else if("review-trial-list".equals(menuCode))//垫付复审
        {
            apiRequest.put("searchData",menuCode);
        }
        else if("insurance-trial-list".equals(menuCode))//保司审核
        {
            apiRequest.put("searchData",menuCode);
        }
        else if("applicant-track-list".equals(menuCode))//案件跟踪
        {

        }
        else if("danger-list".equals(menuCode))//风险案件
        {
            apiRequest.put("caseType",2); //案件类型：1、正常案件，2、风险案件，3、坏账案件
        }
        else
        {
            apiRequest.put("searchData","settlement");//发起结算单 选择的案件列表。已确认到账 并且  确认到账时间不是NULL 。出院完成时间是NULL
        }

        List<FinaApplicantInfo> list = finaApplicantInfoMapper.list(apiRequest);
        int count= 1 ;
        if (pageFlag){
            count = finaApplicantInfoMapper.listSize(apiRequest);
        }
        for (FinaApplicantInfo finaApplicantInfo : list) {
            finaApplicantInfo.setApplicantStateStr(AppcationInfoEnum.getStateNameByState(finaApplicantInfo.getApplicantState()));//垫付状态
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(needLogin = false,descript = "垫付信息表详情",value = "info-fina-applicant-info")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long finaInfoId = apiRequest.getLong("finaInfoId");
        FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);

        // btnType: 新增：add, 修改：update, 二垫：transfer
        String urgeType = apiRequest.getString("urgeType");
        if("add".equals(urgeType) || "update".equals(urgeType) || "transfer".equals(urgeType)){
            if("add".equals(urgeType)){
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
            }else{
                //诊断信息
                Map map = new HashMap<>();
                map.put("finaInfoId",finaApplicantInfo.getId());
                map.put("type",1);//类别（1：主要诊断，2：其他诊断）
                FinaDiagnosisTreatment finaDiagnosisTreatment = finaDiagnosisTreatmentMapper.selectByOne(map);
                finaApplicantInfo.setFinaDiagnosisTreatment(finaDiagnosisTreatment);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        if(finaApplicantInfo.getApplicantState()!=null){
            finaApplicantInfo.setApplicantStateStr(AppcationInfoEnum.getStateNameByState(finaApplicantInfo.getApplicantState()));//垫付状态
        }

        //机构信息
        Map map = new HashMap<>();
        map.put("finaInfoId", finaInfoId);
        List<FinaApplicantOrg> finaApplicantOrgs = finaApplicantOrgMapper.list(map);
        for (FinaApplicantOrg finaApplicantOrg : finaApplicantOrgs) {
            finaApplicantOrg.setAgingHtml(ShowAgingStr.getAgingHtml(finaApplicantOrg.getOrgAssignTime(), finaApplicantOrg.getOrgSubmitTime(), finaApplicantOrg.getOrgEndTime()));
            finaApplicantOrg.setOrgTaskTypeStr(AppcationTaskEnum.getStateNameByState(finaApplicantOrg.getOrgTaskType()));
        }
        finaApplicantInfo.setFinaApplicantOrgList(finaApplicantOrgs);
        if(finaApplicantOrgs!=null && finaApplicantOrgs.size() > 0){
            finaApplicantInfo.setAssignOrg(true);//已分派垫付机构
        }
        //第一条机构数据 “材料收集数据”
        map = new HashMap<>();
        map.put("finaInfoId", finaInfoId);
        map.put("orgTaskType",1);
        FinaApplicantOrg finaApplicantOrg = finaApplicantOrgMapper.selectByOne(map);
        finaApplicantInfo.setFinaApplicantOrg(finaApplicantOrg);

        //调查员案件信息
        map = new HashMap<>();
        map.put("finaInfoId", finaInfoId);
        List<FinaApplicantInvestigator> finaApplicantInvestigators = finaApplicantInvestigatorMapper.list(map);
        for (FinaApplicantInvestigator finaApplicantInvestigator : finaApplicantInvestigators) {
            finaApplicantInvestigator.setAgingHtml(ShowAgingStr.getAgingHtml(finaApplicantInvestigator.getUserAssignTime(), finaApplicantInvestigator.getUserSubmitTime(), finaApplicantInvestigator.getOrgEndTime()));
            finaApplicantInvestigator.setUserTaskTypeStr(AppcationTaskEnum.getStateNameByState(finaApplicantInvestigator.getUserTaskType()));
        }
        finaApplicantInfo.setFinaApplicantInvestigatorList(finaApplicantInvestigators);
        if(finaApplicantInvestigators!=null && finaApplicantInvestigators.size() > 0){
            finaApplicantInfo.setAssignUser(true);//已分派垫付机构
        }
        //垫付员"材料收集"数据
        map = new HashMap<>();
        map.put("finaInfoId", finaInfoId);
        map.put("orgTaskType",1);
        FinaApplicantInvestigator finaApplicantInvestigator = finaApplicantInvestigatorMapper.selectByOne(map);
        finaApplicantInfo.setFinaApplicantInvestigator(finaApplicantInvestigator);

        //诊断信息表
        map = new HashMap<>();
        map.put("finaInfoId",finaApplicantInfo.getId());
        map.put("type",1);//类别（1：主要诊断，2：其他诊断）
        FinaDiagnosisTreatment finaDiagnosisTreatment = finaDiagnosisTreatmentMapper.selectByOne(map);
        finaApplicantInfo.setFinaDiagnosisTreatment(finaDiagnosisTreatment);
        map = new HashMap<>();
        map.put("finaInfoId",finaApplicantInfo.getId());
        map.put("type",2);//类别（1：主要诊断，2：其他诊断）
        List<FinaDiagnosisTreatment> finaDiagnosisTreatmentList = finaDiagnosisTreatmentMapper.list(map);
        finaApplicantInfo.setFinaDiagnosisTreatmentList(finaDiagnosisTreatmentList);

        //垫付费用信息表
        FinaApplicantMoney finaApplicantMoney = finaApplicantMoneyMapper.selectByPrimaryKey(finaInfoId);
        finaApplicantInfo.setFinaApplicantMoney(finaApplicantMoney);

        //医院账号信息
        map = new HashMap<>();
        map.put("finaInfoId",finaApplicantInfo.getId());
        FinaHospitalAccount finaHospitalAccount = finaHospitalAccountMapper.selectByOne(map);
        finaApplicantInfo.setFinaHospitalAccount(finaHospitalAccount);

        //医院信息
        finaApplicantInfo.setFinaHospitalInfo(finaHospitalInfoMapper.selectByPrimaryKey(finaApplicantInfo.getHospitalId()));

        //跟踪信息
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("finaInfoId",finaInfoId);
        List<FinaApplicantTrack> applicantTracks = finaApplicantTrackMapper.list(paramMap);
        finaApplicantInfo.setFinaApplicantTrackList(applicantTracks);

        //附件数量
        paramMap =  new HashMap<String,Object>();
        paramMap.put("finaInfoId",finaInfoId);
        //立案附件数量
        int applicantFileNum = finaApplicantFileMapper.listSize(paramMap);
        //材料收集附件数量
        int applicantCollectFileNum = finaApplicantCollectFileMapper.listSize(paramMap);
        //签约
        /*map = new HashMap<>();
        map.put("finaInfoId",finaApplicantInfo.getId());
        map.put("signState",2);//签署状态（1：待签署，2：已签约）
        map.put("signAgreementPathLocal","have");//返回路径不为空
        int agreementInfoFileNum = finaAgreementInfoMapper.listSize(map);*/

        map = new HashMap<>();
        map.put("finaInfoId",finaApplicantInfo.getId());
        map.put("signState",2);//签署状态（1：待签署，2：已签约）
        List<FinaAgreementInfo> agreementFiles = finaAgreementInfoMapper.list(map);
        List<FinaApplicantFile> finaApplicantFiles = new ArrayList<>();
        int agreementInfoFileNum = 0;
        for (FinaAgreementInfo agreementFile : agreementFiles) {
            if(agreementFile.getFileType() ==1 || agreementFile.getFileType() ==2){
                agreementInfoFileNum = agreementInfoFileNum + 1;
            }else if(agreementFile.getFileType() ==3){
                List<Long> ids = new ArrayList<Long>();
                ids.add(agreementFile.getId());
                List<FinaFile> finaFiles = backendFinaFileApi.getFilesByIds(ids, FileTableEnum.FINA_AGREEMENT_INFO_ATTR);
                agreementInfoFileNum = agreementInfoFileNum + finaFiles.size();
            }
        }

        //确认到账附件数量
        List<Long> ids = new ArrayList<Long>();
        ids.add(finaInfoId);
        List<FinaFile> confirmAccountFiles = backendFinaFileApi.getFilesByIds(ids, FileTableEnum.FINA_CONFIRM_ACCOUNT_ATTR);
        int accountFilesNum = confirmAccountFiles.size();
        finaApplicantInfo.setApplicantFileNum(applicantFileNum+applicantCollectFileNum+agreementInfoFileNum+accountFilesNum);

        //被保险人年龄
        finaApplicantInfo.setInsuredAge(IdNumberUtil.IdNOToAge(finaApplicantInfo.getInsuredIdcard()));

        //案件关联的结算单信息
        Long settlementId = finaSettlementApplicantMapper.selectSettlementIdByApplicantId(finaApplicantInfo.getId());
        if (settlementId != null){
            finaApplicantInfo.setFinaSettlementInfo(finaSettlementInfoMapper.selectByPrimaryKey(settlementId));
        }
        //案件是否已确认到账
        map = new HashMap<>();
        map.put("finaInfoId",finaApplicantInfo.getId());
        map.put("accountTime",true);
        FinaConfirmAccount finaConfirmAccount = finaConfirmAccountMapper.selectOne(map);
        if(finaConfirmAccount != null){
            finaApplicantInfo.setHaveAccount(true);
        }
        //建议垫付金额调整的数量
        map = new HashMap<>();
        map.put("finaInfoId",apiRequest.getLong("finaInfoId"));
        map.put("keyCode","PROPOSAL-MONEY");
        int progressNum = finaProgressMapper.listSize(map);
        finaApplicantInfo.setProgressNum(progressNum);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
    }

    @ApiMethod(needLogin = false,descript = "垫付信息表操作",value = "operate-fina-applicant-info")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        Long userId = apiRequest.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        String userName = null;
        if(userInfo != null){
            userName = userInfo.getUserName();
        }

        Long finaInfoId = apiRequest.getLong("finaInfoId");
        FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);
        FinaApplicantMoney finaApplicantMoney = finaApplicantMoneyMapper.selectByPrimaryKey(finaInfoId);//垫付费用信息表
        String btnCode = apiRequest.getString("btnCode");
        Map map = new HashMap<>();
        if("add-info".equals(btnCode)) //新增、修改
        {
            FinaApplicant finaApplicant = new FinaApplicant();
            try {
                String firstInsureTime = apiRequest.getString("firstInsureTime");//首次投保时间
                if (StringUtils.isNotEmpty(firstInsureTime)) {
                    Date firstInsureTimeD = DateUtils.parseDate(firstInsureTime, "yyyy-MM-dd");
                    apiRequest.put("firstInsureTime", firstInsureTimeD);
                }

                String insureStartTime = apiRequest.getString("insureStartTime");//保险起始时间
                if (StringUtils.isNotEmpty(insureStartTime)) {
                    Date insureStartTimeD = DateUtils.parseDate(insureStartTime, "yyyy-MM-dd");
                    apiRequest.put("insureStartTime", insureStartTimeD);
                }

                String insureEndTime = apiRequest.getString("insureEndTime");//保险终止时间
                if (StringUtils.isNotEmpty(insureEndTime)) {
                    Date insureEndTimeD = DateUtils.parseDate(insureEndTime, "yyyy-MM-dd");
                    apiRequest.put("insureEndTime", insureEndTimeD);
                }

                String inHospitalTime = apiRequest.getString("inHospitalTime");//入院时间
                if (StringUtils.isNotEmpty(inHospitalTime)) {
                    Date inHospitalTimeD = DateUtils.parseDate(inHospitalTime, "yyyy-MM-dd");
                    apiRequest.put("inHospitalTime", inHospitalTimeD);
                }

                //诊断信息
                FinaDiagnosisInfo finaDiagnosisInfo = finaDiagnosisInfoMapper.selectByPrimaryKey(apiRequest.getLong("diagnosisId"));
                //医院信息
                FinaHospitalInfo finaHospitalInfo = finaHospitalInfoMapper.selectByPrimaryKey(apiRequest.getLong("hospitalId"));
                //保险公司信息
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(apiRequest.getLong("entrustOrgId"));

                //二垫  btnType: 新增：add, 修改：update, 二垫：transfer
                String urgeType = apiRequest.getString("urgeType");
                if("add".equals(urgeType) || "transfer".equals(urgeType))//新增：add, 二垫：transfer
                {
                    String finaUserTel = apiRequest.getString("finaUserTel");
                    if(finaUserTel != null){
                        map = new HashMap<>();
                        map.put("userTelphone",finaUserTel);
                        UserInfo finaUserInfo = userInfoMapper.selectUserInfoByPhone(map);
                        if(finaUserInfo !=null){
                            finaApplicant.setFinaUserId(finaUserInfo.getUserId());
                        }
                    }
                    finaApplicant.setFinaUserName(apiRequest.getString("finaUserName"));
                    finaApplicant.setFinaUserIdcard(apiRequest.getString("finaUserIdcard"));
                    finaApplicant.setFinaUserTel(finaUserTel);
                    finaApplicant.setCreateBy(userName);// 创建人id
                    finaApplicant.setCreateTime(new Date());//创建时间
                    finaApplicant.setDeleteFlag(0);
                    finaApplicantMapper.insert(finaApplicant);

                    finaApplicantInfo = ConvertToBeanUtil.toBean(apiRequest, FinaApplicantInfo.class);

                    if(finaHospitalInfo != null){
                        finaApplicantInfo.setHospitalId(finaHospitalInfo.getId());
                        finaApplicantInfo.setHospitalName(finaHospitalInfo.getHospitalName());
                    }
                    if(surveyConsignor!=null){
                        finaApplicantInfo.setEntrustOrgId(surveyConsignor.getId());
                        finaApplicantInfo.setEntrustOrgName(surveyConsignor.getCompany());
                    }

                    finaApplicantInfo.setFinaId(finaApplicant.getId());
                    finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_CLSJZ.getState());//垫付状态 1:材料收集中
                    finaApplicantInfo.setFinaNum(1);//第几次垫付
                    finaApplicantInfo.setCaseType(1);
                    finaApplicantInfo.setCreateBy(userName);// 创建人id
                    finaApplicantInfo.setCreateTime(new Date());//创建时间
                    finaApplicantInfo.setDeleteFlag(0);

                    if("transfer".equals(urgeType)){
                        map = new HashMap<>();
                        map.put("insurancePolicyNo",apiRequest.getString("insurancePolicyNo"));
                        map.put("entrustOrgId",apiRequest.getString("entrustOrgId"));
                        int count = finaApplicantInfoMapper.listSize(map);
                        List<FinaApplicantInfo> infos = finaApplicantInfoMapper.list(map);
                        finaApplicantInfo.setFinaNum(count + 1);//第几次垫付
                        if(finaApplicantInfo.getFinaParentId()==null){
                            finaApplicantInfo.setFinaParentId(infos.get(0).getId());//第一个案件id
                        }else{
                            finaApplicantInfo.setFinaParentId(finaApplicantInfo.getFinaParentId());//父级id永远是第一个案件的id
                        }
                    }

                    finaApplicantInfoMapper.insert(finaApplicantInfo);

                    //垫付费用信息表
                    finaApplicantMoney = new FinaApplicantMoney();
                    finaApplicantMoney.setFinaInfoId(finaApplicantInfo.getId());
                    finaApplicantMoney.setRiskLevel(0);//风险提示(0:无，1：有)
                    finaApplicantMoneyMapper.insert(finaApplicantMoney);
                    //计算预估医疗费用、建议垫付金额
                    syncMoney(finaApplicantInfo, userInfo);

                    //诊断信息 fina_diagnosis_info 落地：fina_diagnosis_treatment

                    FinaDiagnosisTreatment finaDiagnosisTreatment = new FinaDiagnosisTreatment();
                    finaDiagnosisTreatment.setFinaId(finaApplicantInfo.getFinaId());
                    finaDiagnosisTreatment.setFinaInfoId(finaApplicantInfo.getId());
                    finaDiagnosisTreatment.setDiagnosisMoneyRate(1D);
                    if(finaDiagnosisInfo != null){
                        finaDiagnosisTreatment.setDiagnosisId(finaDiagnosisInfo.getId());
                        finaDiagnosisTreatment.setDiagnosisName(finaDiagnosisInfo.getDiagnosisName());
                    }

                    finaDiagnosisTreatment.setCreateBy(userName);// 创建人id
                    finaDiagnosisTreatment.setCreateTime(new Date());//创建时间
                    finaDiagnosisTreatment.setDeleteFlag(0);
                    finaDiagnosisTreatment.setType(1);//类别（1：主要诊断，2：其他诊断）
                    finaDiagnosisTreatmentMapper.insert(finaDiagnosisTreatment);

                }
                else if("update".equals(urgeType))//修改
                {
                    finaApplicant = finaApplicantMapper.selectByPrimaryKey(finaApplicantInfo.getFinaId());
                    if(finaApplicant != null){
                        String finaUserTel = apiRequest.getString("finaUserTel");
                        if(finaUserTel != null){
                            map = new HashMap<>();
                            map.put("userTelphone",finaUserTel);
                            UserInfo finaUserInfo = userInfoMapper.selectUserInfoByPhone(map);
                            if(finaUserInfo != null){
                                finaApplicant.setFinaUserId(finaUserInfo.getUserId());
                            }
                        }
                        finaApplicant.setFinaUserName(apiRequest.getString("finaUserName"));
                        finaApplicant.setFinaUserIdcard(apiRequest.getString("finaUserIdcard"));
                        finaApplicant.setFinaUserTel(finaUserTel);
                        finaApplicant.setUpdateBy(userName);// 创建人id
                        finaApplicant.setUpdateTime(new Date());//创建时间
                        finaApplicantMapper.updateByPrimaryKey(finaApplicant);
                    }

                    finaApplicantInfo = ConvertToBeanUtil.toBean(apiRequest,finaApplicantInfo);
                    //医院信息 fina_hospital_info
                    if(finaHospitalInfo != null){
                        finaApplicantInfo.setHospitalId(finaHospitalInfo.getId());
                        finaApplicantInfo.setHospitalName(finaHospitalInfo.getHospitalName());
                    }

                    //保险公司信息
                    if(surveyConsignor!=null){
                        finaApplicantInfo.setEntrustOrgId(surveyConsignor.getId());
                        finaApplicantInfo.setEntrustOrgName(surveyConsignor.getCompany());
                    }

                    //诊断信息 fina_diagnosis_info 落地：fina_diagnosis_treatment
                    map = new HashMap<>();
                    map.put("finaInfoId",finaApplicantInfo.getId());
                    map.put("type",1); //类别（1：主要诊断，2：其他诊断）
                    FinaDiagnosisTreatment finaDiagnosisTreatment = finaDiagnosisTreatmentMapper.selectByOne(map);
                    if(finaDiagnosisInfo != null){
                        finaDiagnosisTreatment.setDiagnosisId(finaDiagnosisInfo.getId());
                        finaDiagnosisTreatment.setDiagnosisName(finaDiagnosisInfo.getDiagnosisName());
                    }
                    finaDiagnosisTreatment.setUpdateBy(userName);
                    finaDiagnosisTreatment.setUpdateTime(new Date());
                    finaDiagnosisTreatmentMapper.updateByPrimaryKey(finaDiagnosisTreatment);

                    finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
                }
            } catch (Exception e) {
                    e.printStackTrace();
            }

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("submit-applicant-file".equals(btnCode)) //保存立案材料
        {

            //先删除原数据
            /*map = new HashMap<>();
            map.put("finaInfoId",finaInfoId);
            List<FinaApplicantFile> oldFinaApplicantFiles = finaApplicantFileMapper.list(map);
            for (FinaApplicantFile oldFinaApplicantFile : oldFinaApplicantFiles) {
                finaApplicantFileMapper.deleteByPrimaryKey(oldFinaApplicantFile.getId());
            }*/

            String files = apiRequest.getString("files");
            List<FinaApplicantFile> filesDtos = JSONArray.parseArray(files, FinaApplicantFile.class);
            for (FinaApplicantFile filesDto : filesDtos) {
                filesDto.setFinaId(finaApplicantInfo.getFinaId());
                filesDto.setFinaInfoId(finaInfoId);
                Long fileEnumId = filesDto.getFileEnumId();
                String fileEnumName = ApplicantFilesEnum.getEnumNameByEnumId(fileEnumId.intValue());
                filesDto.setFileEnumName(fileEnumName);

                /*Long fileEnumId = filesDto.getFileEnumId();
                String fileEnumName = filesDto.getFileEnumName();
                String filePath = filesDto.getFilePath();
                String fileName = filesDto.getFileName();*/
                filesDto.setUploadBy(userName);
                filesDto.setUploadTime(new Date());
                finaApplicantFileMapper.insert(filesDto);
            }

            //根据上传的“病历资料图片”，分析是否为风险案件
            map = new HashMap<>();
            map.put("finaInfoId",finaInfoId);
            map.put("fileEnumId",1);
            List<FinaApplicantFile> finaApplicantFiles = finaApplicantFileMapper.list(map);
            int otherNum = 0;//其他字段
            int timeNum = 0;//其他字段
            String stringList ="";
            for (FinaApplicantFile finaApplicantFile : finaApplicantFiles) {
                String path = finaApplicantFile.getFilePath();
                int firstName = path.lastIndexOf(".") + 1;
                String fileType = path.substring(firstName,path.length());
                if("gif".equals(fileType) || "jpg".equals(fileType) || "jpeg".equals(fileType) || "bmp".equals(fileType) || "png".equals(fileType)){
                    Map numMap = calculationNum(finaApplicantFile.getFilePath(), "num", stringList);
                    otherNum = otherNum + (Integer)numMap.get("otherNum");
                    timeNum = timeNum + (Integer)numMap.get("timeNum");
                    stringList = stringList + (String)numMap.get("stringList");
                }
            }

            if((timeNum > 0 && otherNum > 2) || (timeNum == 0 && otherNum > 7)){
                finaApplicantMoney.setRiskLevel(1);
            }else{
                finaApplicantMoney.setRiskLevel(0);
            }
            finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("delete-applicant-file".equals(btnCode)) //保存立案材料
        {
            Long finaFileId = apiRequest.getLong("finaFileId");
            FinaApplicantFile finaApplicantFile = finaApplicantFileMapper.selectByPrimaryKey(finaFileId);
            if(finaApplicantFile!=null){
                finaApplicantFileMapper.deleteByPrimaryKey(finaFileId);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("user-submit".equals(btnCode)) //垫付员提交
        {

            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(finaApplicantInfo.getEntrustOrgId());
            map = new HashMap<>();
            map.put("finaInfoId", finaInfoId);
            FinaApplicantInvestigator finaApplicantInvestigator = finaApplicantInvestigatorMapper.selectByOne(map);
            if(finaApplicantInvestigator !=null){
                finaApplicantInvestigator.setState(2);//垫付员案件状态（1：作业中，2：已提交）
                finaApplicantInvestigator.setUserSubmitTime(new Date());
                finaApplicantInvestigator.setUpdateTime(new Date());
                finaApplicantInvestigator.setUpdateBy(userName);

                //计算机构时效、垫付员时效
                Date userAssignTime = finaApplicantInvestigator.getUserAssignTime();//分派日期
                Date userSubmitTime = finaApplicantInvestigator.getUserSubmitTime();//提交日期
                Date userEndTime = finaApplicantInvestigator.getOrgEndTime();//截止日期

                int days = GetWorkDay.calLeaveDays(userAssignTime, userSubmitTime, surveyConsignor.getEfficiencyAttr());
                finaApplicantInvestigator.setFinshAging((double)days);//完成时效

                days = GetWorkDay.calLeaveDays(userSubmitTime, userEndTime, surveyConsignor.getEfficiencyAttr());
                if(days < 0 ){
                    finaApplicantInvestigator.setOverAging(Math.abs((double)days));//超期天数
                }else{
                    finaApplicantInvestigator.setOverAging(0D);//超期天数
                }

                finaApplicantInvestigatorMapper.updateByPrimaryKey(finaApplicantInvestigator);

                //机构案件
                FinaApplicantOrg finaApplicantOrg = finaApplicantOrgMapper.selectByPrimaryKey(finaApplicantInvestigator.getApplicantOrgId());
                finaApplicantOrg.setOrgSubmitTime(new Date());
                finaApplicantOrg.setUpdateTime(new Date());
                finaApplicantOrg.setUpdateBy(userName);
                finaApplicantOrg.setFinaOrgState(2);//机构案件状态（1：作业中，2：已提交）

                Date orgAssignTime = finaApplicantOrg.getOrgAssignTime();//分派日期
                Date orgSubmitTime = finaApplicantOrg.getOrgSubmitTime();//提交日期
                Date orgEndTime = finaApplicantOrg.getOrgEndTime();//截止日期
                days = GetWorkDay.calLeaveDays(orgAssignTime, orgSubmitTime, surveyConsignor.getEfficiencyAttr());
                finaApplicantInvestigator.setFinshAging((double)days);//完成时效

                days = GetWorkDay.calLeaveDays(orgSubmitTime, orgEndTime, surveyConsignor.getEfficiencyAttr());
                if(days < 0 ){
                    finaApplicantOrg.setOverAging(Math.abs((double)days));//超期天数
                }else{
                    finaApplicantOrg.setOverAging(0D);//超期天数
                }
                finaApplicantOrgMapper.updateByPrimaryKey(finaApplicantOrg);
            }

            finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_DFCSZ.getState());//2.垫付初审中
            finaApplicantInfo.setFinshCollectTime(new Date());//材料收集完成时间

            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("first-trial".equals(btnCode)) //初审
        {
            String urgeType = apiRequest.getString("urgeType");
            switch (urgeType)
            {
                case "yes": //通过
                    finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_DFFSZ.getState());//4、垫付复审中
                    finaApplicantInfo.setFirstPassedTime(new Date());//初审通过时间

                    //垫付费用信息表
                    finaApplicantMoney.setProposalMoney(apiRequest.getDouble("proposalMoney"));//建议垫付金额
                    finaApplicantMoney.setCheckManId(userInfo.getUserId());
                    finaApplicantMoney.setCheckManName(userInfo.getUserName());
                    finaApplicantMoney.setCheckTime(new Date());
                    finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);

                    //保存进度
                    String progressName = "垫付初审调整至"+ apiRequest.getDouble("proposalMoney") +"元";
                    setProgress(finaApplicantInfo,userInfo,progressName, "", "PROPOSAL-MONEY");
                    break;
                case "no": //退回-- 至材料收集
                    finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_CLSJZ.getState());//1.材料收集中
                    finaApplicantInfo.setFirstReturnReason(apiRequest.getString("firstReturnReason"));//初审退回原因
                    finaApplicantInfo.setFirstReturnTime(new Date());//初审退回原因
                    finaApplicantInfo.setFinshCollectTime(null);//材料收集完成时间

                    map = new HashMap<>();
                    map.put("finaInfoId", finaInfoId);
                    map.put("userTaskType", 1);
                    FinaApplicantInvestigator finaApplicantInvestigator = finaApplicantInvestigatorMapper.selectByOne(map);

                    //发送微信通知
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Map<String,Object> msgMap =  new HashMap<String,Object>();
                    msgMap.put("title", "垫付初审退回");
                    msgMap.put("content", "你有垫付任务退回至收集材料中，请尽快进行处理！");
                    msgMap.put("keyWords", "案件编号：" + finaApplicantInfo.getCaseApplicantNo() + "\n" + "被保人：" + finaApplicantInfo.getInsuredName() + "\n" + "任务截止日期：" + simpleDateFormat.format(finaApplicantInvestigator.getOrgEndTime()));
                    backendWechatApi.send(finaApplicantInvestigator.getSurveyUserId(),msgMap);
                    break;
            }
            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }

        else if("review-trial".equals(btnCode)) //平台复审
        {
            String urgeType = apiRequest.getString("urgeType");
            switch (urgeType)
            {
                case "yes": //通过
                    finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_DFBSZSZ.getState());//5、垫付保司审核中
                    finaApplicantInfo.setFinshReviewTime(new Date());//垫付复审完成时间
                    //乐凡兜底 应该是垫付保司审核 自动跳过
                    SurveyConsignor consignor = surveyConsignorMapper.selectByPrimaryKey(finaApplicantInfo.getEntrustOrgId());
                    if(consignor!=null && consignor.getAdvanceParty()!=null && consignor.getAdvanceParty() == 1){
                        finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_SHTTDFK.getState());//6、审核通过待放款
                        finaApplicantInfo.setFinshInsuranceTime(new Date());//垫付保司审核完成时间
                        //生成付款管理数据
                        SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
                        surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("DF"));
                        surveyPayInfo.setSourceSupportType(null);
                        surveyPayInfo.setAppPayMoney(apiRequest.getDouble("proposalMoney"));
                        surveyPayInfo.setAppStartDate(new Date());
                        surveyPayInfo.setAppEndDate(new Date());
                        surveyPayInfo.setAppType(1);
                        surveyPayInfo.setPayKeyId(finaApplicantInfo.getId());
                        surveyPayInfo.setCreateUserId(userInfo.getUserId());
                        surveyPayInfo.setCreateBy(userInfo.getUserName());
                        surveyPayInfo.setPayState(1);
                        surveyPayInfo.setCreateTime(new Date());
                        surveyPayInfo.setDeleteFlag(0);
                        surveyPayInfo.setPayType(8);//垫付业务
                        surveyPayInfoMapper.insert(surveyPayInfo);
                    }

                    //垫付费用信息表
                    finaApplicantMoney.setProposalMoney(apiRequest.getDouble("proposalMoney"));//建议垫付金额
                    finaApplicantMoney.setDoubleCheckManId(userInfo.getUserId());
                    finaApplicantMoney.setDoubleCheckManName(userInfo.getUserName());
                    finaApplicantMoney.setDoubleCheckTime(new Date());
                    finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);

                    //保存进度
                    String progressName = "垫付复审调整至"+ apiRequest.getDouble("proposalMoney") +"元";
                    setProgress(finaApplicantInfo,userInfo,progressName, "", "PROPOSAL-MONEY");
                    break;
                case "no": //退回
                    finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_DFCSZ.getState());//2、垫付初审中
                    finaApplicantInfo.setFirstPassedTime(null);//初审通过时间
                    break;
            }
            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }

        else if("insurance-trial".equals(btnCode))//保司审核
        {
            String urgeType = apiRequest.getString("urgeType");
            switch (urgeType)
            {
                case "yes": //通过
                    finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_SHTTDFK.getState());//6、审核通过待放款
                    finaApplicantInfo.setFinshInsuranceTime(new Date());//垫付保司审核完成时间

                    //垫付费用信息表
                    finaApplicantMoney.setProposalMoney(apiRequest.getDouble("proposalMoney"));//建议垫付金额
                    finaApplicantMoney.setDoubleCheckManId(userInfo.getUserId());
                    finaApplicantMoney.setDoubleCheckManName(userInfo.getUserName());
                    finaApplicantMoney.setDoubleCheckTime(new Date());
                    finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);

                    //保存进度
                    String progressName = "保司终审调整至"+ apiRequest.getDouble("proposalMoney") +"元";
                    setProgress(finaApplicantInfo,userInfo,progressName, "", "PROPOSAL-MONEY");

                    //生成付款管理数据
                    SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
                    surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("DF"));
                    surveyPayInfo.setSourceSupportType(null);
                    surveyPayInfo.setAppPayMoney(apiRequest.getDouble("proposalMoney"));
                    surveyPayInfo.setAppStartDate(new Date());
                    surveyPayInfo.setAppEndDate(new Date());
                    surveyPayInfo.setAppType(1);
                    surveyPayInfo.setPayKeyId(finaApplicantInfo.getId());
                    surveyPayInfo.setCreateUserId(userInfo.getUserId());
                    surveyPayInfo.setCreateBy(userInfo.getUserName());
                    surveyPayInfo.setPayState(1);
                    surveyPayInfo.setCreateTime(new Date());
                    surveyPayInfo.setDeleteFlag(0);
                    surveyPayInfo.setPayType(8);//垫付业务
                    surveyPayInfoMapper.insert(surveyPayInfo);
                    break;

            }
            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("confirm-account".equals(btnCode)) //确认到账
        {

            int confirmType = apiRequest.getInt("confirmType");
            //确认到账记录
            FinaConfirmAccount finaConfirmAccount = new FinaConfirmAccount();
            finaConfirmAccount.setFinaInfoId(finaApplicantInfo.getId());
            finaConfirmAccount.setFinaId(finaApplicantInfo.getFinaId());
            finaConfirmAccount.setConfirmType(confirmType);//确认方式(1:电话确认，2：现场确认)
            finaConfirmAccount.setSignFilePath(null);

            if(confirmType == 1)//电话确认
            {
                finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_YQRDZ.getState());//8.已确认到账
                finaApplicantInfo.setLoanAccountTime(new Date());//放款到账确认时间

                finaApplicantInfo.setUpdateBy(userName);// 更新人id
                finaApplicantInfo.setUpdateTime(new Date());//更新时间
                finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);

                finaConfirmAccount.setAccountMoney(apiRequest.getDouble("accountMoney"));//金额
                finaConfirmAccount.setSurveyUserId(userInfo.getUserId());//确认到账人
                finaConfirmAccount.setSurveyUserName(userName);
                String accountTime = apiRequest.getString("accountTime");//时间
                if (StringUtils.isNotEmpty(accountTime)) {
                    Date accountTimeD = DateUtils.parseDate(accountTime, "yyyy-MM-dd");
                    finaConfirmAccount.setAccountTime(accountTimeD);
                }
                String json = apiRequest.getString("files");//附件
                backendFinaFileApi.saveFile(finaApplicantInfo.getId(),FileTableEnum.FINA_CONFIRM_ACCOUNT_ATTR,userInfo,json);
            }
            else if(confirmType == 2)
            {
                apiRequest.put("orgTaskType", 3);//机构任务类型(1:垫付材料收集，2：现场跟踪，3：确认到账)
                assignOrgUser(apiRequest, finaApplicantInfo, userInfo);

            }
            finaConfirmAccountMapper.insert(finaConfirmAccount);

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("refuse-to-visit".equals(btnCode)) //拒绝垫付待回访
        {
            finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_JJDFDHF.getState());//16.拒绝垫付待回访
            finaApplicantInfo.setRefuseApplicant(apiRequest.getString("refuseApplicant"));//拒绝原因

            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("refuse-endCase".equals(btnCode)) //拒绝垫付结案
        {
            finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_JJDFJA.getState());//17、拒绝垫付结案
            finaApplicantInfo.setCaseCloseTime(new Date());//结案时间

            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("give-up-endCase".equals(btnCode)) //放弃垫付结案
        {
            finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_FQDFJA.getState());//18、放弃垫付结案
            finaApplicantInfo.setCaseCloseTime(new Date());//结案时间

            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("submit-visit-endCase".equals(btnCode)) //确认已回访并结案
        {
            finaApplicantInfo.setApplicantState(AppcationInfoEnum.APPLICATION_JJDFJA.getState());//17、拒绝垫付结案
            finaApplicantInfo.setCaseCloseTime(new Date());//结案时间
            finaApplicantInfo.setRevisitTime(new Date());//回访完成时间

            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间

            finaApplicantMoney.setCustomerId(userId);
            finaApplicantMoney.setCustomerName(userName);
            finaApplicantMoney.setCustomerCheckTime(new Date());
            finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);

            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("convert-danger".equals(btnCode)) //转风险案件
        {
            finaApplicantInfo.setCaseType(2);//案件类型：1、正常案件，2、风险案件，3、坏账案件
            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("convert-normal".equals(btnCode)) //转正常案件
        {
            finaApplicantInfo.setCaseType(1);//案件类型：1、正常案件，2、风险案件，3、坏账案件
            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);

            Long settlementInfoId = finaSettlementApplicantMapper.selectSettlementIdByApplicantId(finaApplicantInfo.getId());
            if (settlementInfoId != null){
                FinaSettlementInfo finaSettlementInfo = finaSettlementInfoMapper.selectByPrimaryKey(settlementInfoId);
                if (finaSettlementInfo != null){
                    finaSettlementInfo.setIsBad(0);
                    finaSettlementInfo.setUrgeState(null);
                    finaSettlementInfo.setUpdateTime(new Date());
                    finaSettlementInfo.setUpdateBy(userName);
                    finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("convert-bad".equals(btnCode)) //转坏账案件
        {
            finaApplicantInfo.setCaseType(3);//案件类型：1、正常案件，2、风险案件，3、坏账案件
            finaApplicantInfo.setUpdateBy(userName);// 更新人id
            finaApplicantInfo.setUpdateTime(new Date());//更新时间
            finaApplicantInfoMapper.updateByPrimaryKey(finaApplicantInfo);

            Long settlementInfoId = finaSettlementApplicantMapper.selectSettlementIdByApplicantId(finaApplicantInfo.getId());
            if (settlementInfoId != null){
                FinaSettlementInfo finaSettlementInfo = finaSettlementInfoMapper.selectByPrimaryKey(settlementInfoId);
                if (finaSettlementInfo != null){
                    finaSettlementInfo.setIsBad(1);
                    finaSettlementInfo.setUrgeState(1);//转坏帐之后变为催收中
                    finaSettlementInfo.setUpdateTime(new Date());
                    finaSettlementInfo.setUpdateBy(userName);
                    finaSettlementInfoMapper.updateByPrimaryKey(finaSettlementInfo);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("diagnosis-treatment-info".equals(btnCode))//主要诊断、其他诊断 新增
        {
            String urgeType = apiRequest.getString("urgeType");
            Long finaDiagnosisTreatmentId = apiRequest.getLong("diagnosisTreatmentId");
            FinaDiagnosisTreatment finaDiagnosisTreatment = finaDiagnosisTreatmentMapper.selectByPrimaryKey(finaDiagnosisTreatmentId);
            if(finaDiagnosisTreatment ==null){
                finaDiagnosisTreatment = new FinaDiagnosisTreatment();
            }
            switch (urgeType)
            {
                case "add":
                    finaDiagnosisTreatment.setFinaId(finaApplicantInfo.getFinaId());
                    finaDiagnosisTreatment.setFinaInfoId(finaApplicantInfo.getId());
                    finaDiagnosisTreatment.setType(2);//类别（1：主要诊断，2：其他诊断）
                    finaDiagnosisTreatment.setDiagnosisMoneyRate(0.2D);
                    finaDiagnosisTreatment.setCreateBy(userName);
                    finaDiagnosisTreatment.setCreateTime(new Date());
                    finaDiagnosisTreatment.setDeleteFlag(0);
                    finaDiagnosisTreatmentMapper.insert(finaDiagnosisTreatment);
                    return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaDiagnosisTreatment);
//                    break;
                case "update":
                    Long diagnosisId = apiRequest.getLong("diagnosisId");
                    FinaDiagnosisInfo finaDiagnosisInfo = finaDiagnosisInfoMapper.selectByPrimaryKey(diagnosisId);
                    if(finaDiagnosisInfo!=null){
                        finaDiagnosisTreatment.setDiagnosisId(finaDiagnosisInfo.getId());
                        finaDiagnosisTreatment.setDiagnosisName(finaDiagnosisInfo.getDiagnosisName());
                    }
                    Long treatmentId = apiRequest.getLong("treatmentId");
                    FinaTreatmentInfo finaTreatmentInfo = finaTreatmentInfoMapper.selectByPrimaryKey(treatmentId);
                    if(finaTreatmentInfo != null){
                        finaDiagnosisTreatment.setTreatmentId(finaTreatmentInfo.getId());
                        finaDiagnosisTreatment.setTreatmentName(finaTreatmentInfo.getTreatmentName());
                    }
                    if(finaDiagnosisTreatment.getType() !=1){ //主要诊断，比例默认不可修改为1
                        Double diagnosisMoneyRate = apiRequest.getDouble("diagnosisMoneyRate");
                        finaDiagnosisTreatment.setDiagnosisMoneyRate(diagnosisMoneyRate);
                    }
                    finaDiagnosisTreatmentMapper.updateByPrimaryKey(finaDiagnosisTreatment);
                    //计算预估医疗费用、建议垫付金额
                    syncMoney(finaApplicantInfo, userInfo);
                    break;
                case "delete":
                    finaDiagnosisTreatment.setDeleteFlag(1);
                    finaDiagnosisTreatmentMapper.updateByPrimaryKey(finaDiagnosisTreatment);
                    syncMoney(finaApplicantInfo, userInfo);
                    break;
            }

            finaDiagnosisTreatment.setUpdateBy(userName);
            finaDiagnosisTreatment.setUpdateTime(new Date());
            finaDiagnosisTreatmentMapper.updateByPrimaryKey(finaDiagnosisTreatment);

            //诊断信息表
            map = new HashMap<>();
            map.put("finaInfoId",finaApplicantInfo.getId());
            map.put("type",1);//类别（1：主要诊断，2：其他诊断）
            finaDiagnosisTreatment = finaDiagnosisTreatmentMapper.selectByOne(map);
            finaApplicantInfo.setFinaDiagnosisTreatment(finaDiagnosisTreatment);
            map = new HashMap<>();
            map.put("finaInfoId",finaApplicantInfo.getId());
            map.put("type",2);//类别（1：主要诊断，2：其他诊断）
            List<FinaDiagnosisTreatment> finaDiagnosisTreatmentList = finaDiagnosisTreatmentMapper.list(map);
            finaApplicantInfo.setFinaDiagnosisTreatmentList(finaDiagnosisTreatmentList);

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("estimate-money".equals(btnCode))//预估医疗费用
        {
            finaApplicantMoney.setEstimateMoney(apiRequest.getDouble("estimateMoney"));
            finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantMoney);
        }
        else if("risk-level".equals(btnCode))//风险提示
        {
            finaApplicantMoney.setRiskLevel(apiRequest.getInt("riskLevel"));
            finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantMoney);
        }
        else if("proposal-money".equals(btnCode))//建议垫付金额
        {
            finaApplicantMoney.setProposalMoney(apiRequest.getDouble("proposalMoney"));
            finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantMoney);
        }
        else if ("applicant-track-save".equals(btnCode))//跟踪信息的保存
        {
            try {
                FinaApplicantTrack finaApplicantTrack = FinaApplicantTrack.class.newInstance();
                finaApplicantTrack.setFinaInfoId(finaInfoId);
                finaApplicantTrack.setTrackDesc(apiRequest.getString("trackDesc"));
                finaApplicantTrack.setTrackFtime(new Date());
                finaApplicantTrack.setTrackUserId(userInfo.getUserId());
                finaApplicantTrack.setTrackUserName(userInfo.getUserName());
                finaApplicantTrack.setNextTrackTime(DateUtils.parseDate(apiRequest.getString("nextTrackTime"),"yyyy-MM-dd HH:mm:ss"));
                finaApplicantTrack.setCreateBy(userInfo.getUserName());
                finaApplicantTrack.setCreateTime(new Date());

                finaApplicantTrack.setDeleteFlag(0);
                finaApplicantTrackMapper.insert(finaApplicantTrack);
                //添加跟踪附件
                String json = apiRequest.getString("files");
                backendFinaFileApi.saveFile(finaApplicantTrack.getId(), FileTableEnum.FINA_APPLICANT_TRACK_ATTR,userInfo,json);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("assignation".equals(btnCode))//分派
        {
            assignation(apiRequest);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("delete-assign-org-case".equals(btnCode))//删除机构
        {

            //删除机构案件
            Long applicantOrgId = apiRequest.getLong("applicantOrgId");
            FinaApplicantOrg finaApplicantOrg = finaApplicantOrgMapper.selectByPrimaryKey(applicantOrgId);
            if(finaApplicantOrg != null){
                finaApplicantOrg.setDeleteFlag(1);
                finaApplicantOrg.setUpdateTime(new Date());
                finaApplicantOrg.setUpdateBy(userInfo.getUserName());
                finaApplicantOrgMapper.updateByPrimaryKey(finaApplicantOrg);
            }

            //删除所有调查员案件
            map = new HashMap<>();
            map.put("applicantOrgId", applicantOrgId);
            List<FinaApplicantInvestigator> finaApplicantInvestigators = finaApplicantInvestigatorMapper.list(map);
            for (FinaApplicantInvestigator finaApplicantInvestigator : finaApplicantInvestigators) {
                finaApplicantInvestigator.setDeleteFlag(1);
                finaApplicantInvestigator.setUpdateTime(new Date());
                finaApplicantInvestigator.setUpdateBy(userInfo.getUserName());
                finaApplicantInvestigatorMapper.updateByPrimaryKey(finaApplicantInvestigator);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        else if("delete-assign-user-case".equals(btnCode))//删除调查员
        {
            Long applicantInvestigatorId = apiRequest.getLong("applicantInvestigatorId");
            FinaApplicantInvestigator finaApplicantInvestigator = finaApplicantInvestigatorMapper.selectByPrimaryKey(applicantInvestigatorId);

            if(finaApplicantInvestigator != null) {
                //删除原数据
                finaApplicantInvestigator.setDeleteFlag(1);
                finaApplicantInvestigator.setUpdateBy(userInfo.getUserName());
                finaApplicantInvestigator.setUpdateTime(new Date());
                finaApplicantInvestigatorMapper.updateByPrimaryKey(finaApplicantInvestigator);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "垫付信息表ajax",value = "ajax-data-fina-applicant-info")
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        Long userId = apiRequest.getLong("operatorId");
        String dataType = apiRequest.getString("dataType");
        switch (dataType)
        {
            case  "same-case"://根据保险公司及保单号判断是否为相同案件，且历史案件的状态必须在出院结算中 之前 则弹出此框
            {
                String insurancePolicyNo = apiRequest.getString("insurancePolicyNo");
                Long entrustOrgId = apiRequest.getLong("entrustOrgId");

                Map map = new HashMap<>();
                map.put("insurancePolicyNo",insurancePolicyNo);
                map.put("entrustOrgId",entrustOrgId);
                map.put("searchData","same-case");
                List<FinaApplicantInfo> samefinaApplicantInfos = finaApplicantInfoMapper.list(map);

                if(samefinaApplicantInfos != null && samefinaApplicantInfos.size() > 0){ // 代表有相同案件
                    return new ApiResponse(ApiMsgEnum.SUCCESS,1,samefinaApplicantInfos);
                }else{
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
            case  "same-case-info"://相同案件的具体信息
            {
                Long finaInfoId = apiRequest.getLong("finaInfoId");
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);
                Map map = new HashMap<>();
                map.put("finaInfoId",finaApplicantInfo.getId());
                map.put("type",1);//类别（1：主要诊断，2：其他诊断）
                FinaDiagnosisTreatment finaDiagnosisTreatment = finaDiagnosisTreatmentMapper.selectByOne(map);
                finaApplicantInfo.setFinaDiagnosisTreatment(finaDiagnosisTreatment);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
            }
            case "get-applicant-track" : //添加跟踪
            {
                Long finaInfoId = apiRequest.getLong("finaInfoId");
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("finaInfoId",finaInfoId);
                List<FinaApplicantTrack> applicantTracks = finaApplicantTrackMapper.list(paramMap);
                List<Long> ids = applicantTracks.stream().map(p -> p.getId()).collect(Collectors.toList());

                List<FinaFile> finaFiles = backendFinaFileApi.getFilesByIds(ids, FileTableEnum.FINA_APPLICANT_TRACK_ATTR);
                for (FinaApplicantTrack finaApplicantTrack : applicantTracks) {
                    List<FinaFile> item = new ArrayList<FinaFile>();
                    for (FinaFile finaFile : finaFiles) {
                        if (finaFile.getKeyId().intValue() == finaApplicantTrack.getId().intValue())
                            item.add(finaFile);
                    }
                    finaApplicantTrack.setFinaFiles(item);
                }
                finaApplicantInfo.setFinaApplicantTrackList(applicantTracks);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
            }
            case "fina-diagnosis-treatment-mid"://根据诊断，查询治疗方案
            {
                Long diagnosisId = apiRequest.getLong("diagnosisId");
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("diagnosisId",diagnosisId);
                List<FinaDiagnosisTreatmentMid> finaDiagnosisTreatmentMid = finaDiagnosisTreatmentMidMapper.list(paramMap);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaDiagnosisTreatmentMid);
            }
            case "fina_diagnosis_treatment"://垫付案件的单条诊断信息
            {
                Long finaDiagnosisTreatmentId = apiRequest.getLong("finaDiagnosisTreatmentId");
                FinaDiagnosisTreatment finaDiagnosisTreatment = finaDiagnosisTreatmentMapper.selectByPrimaryKey(finaDiagnosisTreatmentId);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaDiagnosisTreatment);
            }

            case "to-lefan-survey"://初审 -- 发起乐凡调查
            {
                Long finaInfoId = apiRequest.getLong("finaInfoId");
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);

                SurveyRiskCase surveyRiskCase = new SurveyRiskCase();
                surveyRiskCase.setEntrustOrgId(finaApplicantInfo.getEntrustOrgId());
                surveyRiskCase.setEntrustOrgName(finaApplicantInfo.getEntrustOrgName());
                surveyRiskCase.setSurveyPerson(finaApplicantInfo.getInsuredName());
                surveyRiskCase.setSurveryPersonTel(finaApplicantInfo.getInsuredTel());
                surveyRiskCase.setIdType(1);
                surveyRiskCase.setIdNumber(finaApplicantInfo.getInsuredIdcard());

                SurveyRiskCaseInfoDto surveyRiskCaseInfo = new SurveyRiskCaseInfoDto();
                surveyRiskCaseInfo.setSurveyRiskCase(surveyRiskCase);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyRiskCaseInfo);
            }

            case "confirm-account"://确认到账页面
            {
                Long finaInfoId = apiRequest.getLong("finaInfoId");
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);

                Map map = new HashMap<>();
                map.put("finaInfoId",finaInfoId);
                FinaApplicantInvestigator investigator = finaApplicantInvestigatorMapper.selectByOne(map);
                finaApplicantInfo.setSurveyUserId(investigator.getSurveyUserId());
                finaApplicantInfo.setSurveyUserName(investigator.getSurveyUserName());

                FinaApplicantMoney finaApplicantMoney = finaApplicantMoneyMapper.selectByPrimaryKey(finaApplicantInfo.getId());
                finaApplicantInfo.setFinaApplicantMoney(finaApplicantMoney);

                map = new HashMap<>();
                map.put("finaInfoId",finaApplicantInfo.getId());
                FinaConfirmAccount finaConfirmAccount = finaConfirmAccountMapper.selectOne(map);

                //附件
                List<Long> ids = new ArrayList<Long>();
                ids.add(finaInfoId);
                List<FinaFile> finaFiles = backendFinaFileApi.getFilesByIds(ids, FileTableEnum.FINA_CONFIRM_ACCOUNT_ATTR);
                if(finaConfirmAccount !=null){
                    finaConfirmAccount.setFinaFiles(finaFiles);
                }
                finaApplicantInfo.setFinaConfirmAccount(finaConfirmAccount);

                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
            }
            case "add-update-transfer-info"://新增，编辑，二垫 数据获取
            {
                Long finaInfoId = apiRequest.getLong("finaInfoId");
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);
                // btnType: 新增：add, 修改：update, 二垫：transfer
                String urgeType = apiRequest.getString("urgeType");
                if("add".equals(urgeType) || "update".equals(urgeType) || "transfer".equals(urgeType)){
                    if("add".equals(urgeType)){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantInfo);
                }
            }
            case "get-org-case-end-time"://获取机构截止日期
            {
                Map map = new HashMap<>();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Long finaInfoId = apiRequest.getLong("finaInfoId");
                FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);

                String urgeType = apiRequest.getString("urgeType");
                if(urgeType != null){
                    if("updAssignOrg".equals(urgeType) || "assignOrg".equals(urgeType)){ //改派机构，分派机构
                        Long entrustOrgId = finaApplicantInfo.getEntrustOrgId();
                        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(entrustOrgId);

                        FinaHospitalInfo hospitalInfo = finaHospitalInfoMapper.selectByPrimaryKey(finaApplicantInfo.getHospitalId());
                        Long districtId = hospitalInfo.getDistrictId();
                        if(districtId == null){
                            districtId = hospitalInfo.getCityId();
                        }

                        Integer days = finaSurveyConsignorEfficiencyModelInfoMapper.getDays(entrustOrgId,1L,districtId);
                        if (days == null){
                            days = 3;
                        }
                        Date endTime = GetWorkDay.calLeaveEndDate(new Date(), null, days-1, surveyConsignor.getEfficiencyAttr());

                        String endTimeD = simpleDateFormat.format(endTime);//截止日期
                        map.put("endTimeD",endTimeD);
                        map.put("myEndTime",endTimeD);
                        if("updAssignOrg".equals(urgeType)){//机构案件改派
                            FinaApplicantOrg finaApplicantOrg = finaApplicantOrgMapper.selectByPrimaryKey(apiRequest.getLong("applicantOrgId"));
                            if(finaApplicantOrg !=null){
                                map.put("surveyOrgId", finaApplicantOrg.getSurveyOrgId());
                                map.put("surveyOrgName", finaApplicantOrg.getSurveyOrgName());
                                map.put("taskDesc", finaApplicantOrg.getTaskDesc());
                                String myEndTime = simpleDateFormat.format(finaApplicantOrg.getOrgEndTime());//截止日期
                                map.put("myEndTime", myEndTime);
                            }
                        }
                    }
                    else if("updAssignUser".equals(urgeType) || "assignUser".equals(urgeType)){//分派调查员，改派垫付员
                        Map parMap = new HashMap<>();
                        parMap.put("finaInfoId",finaInfoId);
                        FinaApplicantOrg finaApplicantOrg = finaApplicantOrgMapper.selectByOne(parMap);
                        String endTimeD = simpleDateFormat.format(finaApplicantOrg.getOrgEndTime());//截止日期
                        map.put("endTimeD",endTimeD);
                        map.put("myEndTime",endTimeD);
                        map.put("orgTaskDesc",finaApplicantOrg.getTaskDesc());
                        FinaApplicantInvestigator finaApplicantInvestigator = finaApplicantInvestigatorMapper.selectByPrimaryKey(apiRequest.getLong("applicantInvestigatorId"));
                        if(finaApplicantInvestigator!=null){
                            if("updAssignUser".equals(urgeType)){//调查员案件改派
                                map.put("surveyUserId", finaApplicantInvestigator.getSurveyUserId());
                                map.put("surveyUserName", finaApplicantInvestigator.getSurveyUserName());
                                map.put("taskDesc", finaApplicantInvestigator.getTaskDesc());
                                String myEndTime = simpleDateFormat.format(finaApplicantInvestigator.getOrgEndTime());//截止日期
                                map.put("myEndTime", myEndTime);
                            }
                        }
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
            }
            case "select-hospitals"://模糊查询医院信息
            {
                String hospitalName = apiRequest.getString("hospitalName");
                Map map  = new HashMap<>();
                map.put("hospitalName",hospitalName);
                List<FinaHospitalInfo> finaHospitalInfoList = finaHospitalInfoMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaHospitalInfoList);
            }
            case "get-applicant-file" ://获取附件的目录数据
            {
                Long finaInfoId = apiRequest.getLong("finaInfoId");
                List<FinaApplicantFileEnumParDto> parent = addFinaApplicantFileEnum(finaInfoId);
                return new ApiResponse(ApiMsgEnum.SUCCESS, 0, parent);
            }
            case "risk-level-info" ://风险识别详情
            {
                //根据上传的“病历资料图片”，分析是否为风险案件
                Map map = new HashMap<>();
                map.put("finaInfoId",apiRequest.getLong("finaInfoId"));
                map.put("fileEnumId",1);
                List<FinaApplicantFile> finaApplicantFiles = finaApplicantFileMapper.list(map);
                /*String words = "";
                for (FinaApplicantFile finaApplicantFile : finaApplicantFiles) {
                    Map numMap = calculationNum(finaApplicantFile.getFilePath(), "words");
                    words = (String)numMap.get("wordStr");
                    finaApplicantFile.setImageWords(words);
                }*/
                Map returnMap = new HashMap<>();
                returnMap.put("finaApplicantFiles",finaApplicantFiles);

                //字段
                map = new HashMap<>();
                map.put("enumCode","medicalRecords");
                List<FinaApplicantFileEnum> allEnum = finaApplicantFileEnumMapper.selectListNoSomeCode(map);
                returnMap.put("allEnum",allEnum);

                return new ApiResponse(ApiMsgEnum.SUCCESS, 0, returnMap);
            }
            case "risk-level-image-word" ://风险识别详情--图片文字识别
            {
                Long finaApplicantFileId = apiRequest.getLong("finaApplicantFileId");
                String words = "";
                String stringList ="";
                FinaApplicantFile finaApplicantFile =finaApplicantFileMapper.selectByPrimaryKey(finaApplicantFileId);
                Map numMap = calculationNum(finaApplicantFile.getFilePath(), "words", stringList);
                words = (String)numMap.get("wordStr");
                finaApplicantFile.setImageWords(words);

                return new ApiResponse(ApiMsgEnum.SUCCESS,1,finaApplicantFile);
            }
            case "risk-level-view-left-info" ://风险识别图片详情
            {
                //根据上传的“病历资料图片”，分析是否为风险案件
                Map map = new HashMap<>();
                map.put("finaInfoId",apiRequest.getLong("finaInfoId"));
                map.put("fileEnumId",1);
                List<FinaApplicantFile> finaApplicantFiles = finaApplicantFileMapper.list(map);
                Map returnMap = new HashMap<>();
                returnMap.put("finaApplicantFiles",finaApplicantFiles);

                //字段
                map = new HashMap<>();
                map.put("enumCode","medicalRecords");
                List<FinaApplicantFileEnum> allEnum = finaApplicantFileEnumMapper.selectListNoSomeCode(map);
                returnMap.put("allEnum",allEnum);

                return new ApiResponse(ApiMsgEnum.SUCCESS, 0, returnMap);
            }
            case "get-fina-progress" ://获取进度
            {
                String keyCode = apiRequest.getString("keyCode");
                Map map = new HashMap<>();
                map.put("finaInfoId",apiRequest.getLong("finaInfoId"));
                map.put("keyCode",keyCode);
                List<FinaProgress> progresses = finaProgressMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS, 0, progresses);
            }

        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    public ApiResponse assignation(ApiRequest apiRequest) {
        try {
            String urgeType = apiRequest.getString("urgeType");
            Long finaInfoId = apiRequest.getLong("finaInfoId");
            FinaApplicantInfo finaApplicantInfo = finaApplicantInfoMapper.selectByPrimaryKey(finaInfoId);

            Long currentUserId = getCurrentUserId(apiRequest);
            if(currentUserId == null){
                currentUserId = 5665L;
            }
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            if ("assignOrg".equals(urgeType)){// 分派机构
                return assignOrg(apiRequest,finaApplicantInfo,userInfo,false);
            }else if ("assignUser".equals(urgeType)){//分派调查员
                return assignUser(apiRequest,finaApplicantInfo,userInfo,false);
            }else if ("updAssignOrg".equals(urgeType)){//改派机构
                return updAssignOrg(apiRequest, finaApplicantInfo, userInfo);
            }else if ("updAssignUser".equals(urgeType)){//改派调查员
                return updAssignUser(apiRequest, finaApplicantInfo, userInfo);
            }else if ("assignOrgUser".equals(urgeType)){//现场跟踪、确认到账分派
                return assignOrgUser(apiRequest, finaApplicantInfo, userInfo);
            }
            return new ApiResponse(ApiMsgEnum.ERROR_PARAMETER);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }


    /**
     * 分派机构
     * @param apiRequest
     * @param
     * @param userInfo
     * @return
     */
    private ApiResponse assignOrg(ApiRequest apiRequest, FinaApplicantInfo finaApplicantInfo, UserInfo userInfo, Boolean upd){
        Long surveyOrgId = apiRequest.getLong("surveyOrgId");
        Date orgEndTime = DateUtils.parseDate(apiRequest.getString("endTime")+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
        String taskDesc = apiRequest.getString("taskDesc");


        SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyOrgId);
        Map map =  new HashMap<>();
        map.put("surveyOrgId",surveyOrgId);
        map.put("finaInfoId",finaApplicantInfo.getId());
        FinaApplicantOrg finaApplicantOrg = finaApplicantOrgMapper.selectByOne(map);

        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(finaApplicantInfo.getEntrustOrgId());
        if (finaApplicantOrg != null){//已分配过的机构提示:提示不可重复分配
            return new ApiResponse(ApiMsgEnum.SURVEY_ASSIGN_ORG);
        }else{
            finaApplicantOrg = new FinaApplicantOrg();
            finaApplicantOrg.setSurveyOrgId(surveyOrgId);
            finaApplicantOrg.setSurveyOrgName(surveyFranchisee.getName());
            finaApplicantOrg.setFinaId(finaApplicantInfo.getFinaId());
            finaApplicantOrg.setFinaInfoId(finaApplicantInfo.getId());
            finaApplicantOrg.setFinaOrgState(1);//垫付机构案件状态（1：作业中，2：已提交）

            Integer orgTaskType = 1;
            if(apiRequest.getInt("orgTaskType") !=null){
                orgTaskType = apiRequest.getInt("orgTaskType");
            }
            finaApplicantOrg.setOrgTaskType(orgTaskType);//机构任务类型(1:垫付材料收集，2：现场跟踪，3：确认到账)
//            finaApplicantOrg.setOrgTaskType(AppcationTaskEnum.DFCLSJ.getState());//机构任务类型(1:垫付材料收集，2：现场跟踪，3：确认到账)

            finaApplicantOrg.setOrgEndTime(orgEndTime);
            finaApplicantOrg.setOrgAssignTime(new Date());
            finaApplicantOrg.setTaskDesc(taskDesc);
            finaApplicantOrg.setCreateBy(userInfo.getUserName());
            finaApplicantOrg.setCreateTime(new Date());
            finaApplicantOrg.setDeleteFlag(0);

            //服务费
            FinaHospitalInfo hospitalInfo = finaHospitalInfoMapper.selectByPrimaryKey(finaApplicantInfo.getHospitalId());
            Long districtId = hospitalInfo.getDistrictId();
            if(districtId == null){
                districtId = hospitalInfo.getCityId();
            }
            Double serviceMoney = finaSurveyPriceMapper.getPrice(finaApplicantOrg.getSurveyOrgId(), AppcationTaskEnum.getDataIdByState(finaApplicantOrg.getOrgTaskType()), districtId);
            finaApplicantOrg.setServiceMoney(serviceMoney ==null ? 0D : serviceMoney);

            int days = GetWorkDay.calLeaveDays(new Date(), orgEndTime, surveyConsignor.getEfficiencyAttr());
            finaApplicantOrg.setOrgAging((double)days);//考核时效
            finaApplicantOrgMapper.insert(finaApplicantOrg);
        }

        /*//进度，暂定
        if (!upd){
            backendSurveyProgressApi.saveProgress(finaApplicantOrg.getFinaId(),finaApplicantOrg.getId(),userInfo.getUserId(),userInfo.getUserName(),"已分派机构（" + finaApplicantOrg.getSurveyOrgName() + "）","");
        }
        */
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }


    /**
     * 分派调查员
     * @param apiRequest
     * @param
     * @param userInfo
     * @return
     * @throws Exception
     */
    private ApiResponse assignUser(ApiRequest apiRequest, FinaApplicantInfo finaApplicantInfo, UserInfo userInfo,Boolean upd) throws Exception{
        Long assignUserId = apiRequest.getLong("assignUserId");
        Date surveyEndTime = DateUtils.parseDate(apiRequest.getString("endTime")+" 23:59:59", "yyyy-MM-dd HH:mm:ss");

        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(assignUserId);
        String taskDesc = apiRequest.getString("taskDesc");

        //进度，暂定
        /*if (!upd){
            backendSurveyProgressApi.saveProgress(finaApplicantInfo.getFinaId(),finaApplicantInfo.getId(),userInfo.getUserId(),userInfo.getUserName(),"已分派调查员（" + surveyInvestigator.getRealName() + "），调查中","");
        }*/

        //机构案件信息
        Map map =  new HashMap<>();
        map.put("surveyOrgId",surveyInvestigator.getOrgId());
        map.put("finaInfoId",finaApplicantInfo.getId());
        FinaApplicantOrg finaApplicantOrg = finaApplicantOrgMapper.selectByOne(map);

        if(upd){//是改派
            finaApplicantOrg = finaApplicantOrgMapper.selectByPrimaryKey(apiRequest.getLong("applicantOrgId"));
        }

        //根据字表id以及调查员id查询是否已经分配过。 如果分配过则更改案件任务表
        map =  new HashMap<>();
        map.put("finaInfoId",finaApplicantInfo.getId());
        map.put("surveyUserId",assignUserId);
        FinaApplicantInvestigator finaApplicantInvestigator = finaApplicantInvestigatorMapper.selectByOne(map);
        //时效
        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(finaApplicantInfo.getEntrustOrgId());
        int days = GetWorkDay.calLeaveDays(new Date(), surveyEndTime, surveyConsignor.getEfficiencyAttr());

        if (finaApplicantInvestigator != null){ //无需再次分派
            return new ApiResponse(ApiMsgEnum.SURVEY_ASSIGN_ORG);
        }else{//新建
            finaApplicantInvestigator = new FinaApplicantInvestigator();
            finaApplicantInvestigator.setFinaId(finaApplicantInfo.getFinaId());
            finaApplicantInvestigator.setFinaInfoId(finaApplicantInfo.getId());
            finaApplicantInvestigator.setApplicantOrgId(finaApplicantOrg.getId());//机构案件id
            finaApplicantInvestigator.setSurveyOrgId(finaApplicantOrg.getSurveyOrgId());
            finaApplicantInvestigator.setSurveyOrgName(finaApplicantOrg.getSurveyOrgName());
            finaApplicantInvestigator.setSurveyUserId(surveyInvestigator.getUserId());
            finaApplicantInvestigator.setSurveyUserName(surveyInvestigator.getRealName());
            finaApplicantInvestigator.setState(1);//垫付员案件状态（1：作业中，2：已提交）
            finaApplicantInvestigator.setUserAssignTime(new Date());
            finaApplicantInvestigator.setOrgEndTime(surveyEndTime);
            finaApplicantInvestigator.setUserAging((double) days);

            Integer userTaskType = 1;
            if(apiRequest.getInt("userTaskType") !=null){
                userTaskType = apiRequest.getInt("userTaskType");
            }
            finaApplicantInvestigator.setUserTaskType(userTaskType);//机构任务类型(1:垫付材料收集，2：现场跟踪，3：确认到账
//            finaApplicantInvestigator.setUserTaskType(AppcationTaskEnum.DFCLSJ.getState());//机构任务类型(1:垫付材料收集，2：现场跟踪，3：确认到账)

            finaApplicantInvestigator.setTaskDesc(taskDesc);
            finaApplicantInvestigator.setCreateBy(userInfo.getUserName());
            finaApplicantInvestigator.setCreateTime(new Date());
            finaApplicantInvestigator.setDeleteFlag(0);

            //分值
            FinaTaskInfo finaTaskInfo = finaTaskInfoMapper.selectByPrimaryKey(finaApplicantInvestigator.getUserTaskType().longValue());
            if(finaTaskInfo!=null){
                finaApplicantInvestigator.setScore(finaTaskInfo.getScore());
            }

            //服务费
            FinaHospitalInfo hospitalInfo = finaHospitalInfoMapper.selectByPrimaryKey(finaApplicantInfo.getHospitalId());
            Long districtId = hospitalInfo.getDistrictId();
            if(districtId == null){
                districtId = hospitalInfo.getCityId();
            }
            Double serviceMoney = finaSurveyPriceMapper.getPrice(finaApplicantOrg.getSurveyOrgId(), AppcationTaskEnum.getDataIdByState(finaApplicantInvestigator.getUserTaskType()), districtId);

            finaApplicantInvestigator.setServiceMoney(serviceMoney ==null ? 0D : serviceMoney);

            finaApplicantInvestigatorMapper.insert(finaApplicantInvestigator);

            //发送微信通知
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Map<String,Object> msgMap =  new HashMap<String,Object>();
            msgMap.put("title","垫付派单");
            msgMap.put("content","你有新的垫付任务，请尽快进行处理！");
            msgMap.put("keyWords","案件编号：" + finaApplicantInfo.getCaseApplicantNo() + "\n" + "被保人：" + finaApplicantInfo.getInsuredName() + "\n" + "任务截止日期：" + simpleDateFormat.format(finaApplicantInvestigator.getOrgEndTime()));
            backendWechatApi.send(surveyInvestigator.getUserId(),msgMap);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 改派调查员
     * @param apiRequest
     * @return
     */
    private ApiResponse updAssignUser(ApiRequest apiRequest,FinaApplicantInfo finaApplicantInfo,UserInfo userInfo){

        Long applicantInvestigatorId = apiRequest.getLong("applicantInvestigatorId");
        FinaApplicantInvestigator finaApplicantInvestigator = finaApplicantInvestigatorMapper.selectByPrimaryKey(applicantInvestigatorId);

        if(finaApplicantInvestigator != null){
            //删除原数据
            finaApplicantInvestigator.setDeleteFlag(1);
            finaApplicantInvestigator.setUpdateBy(userInfo.getUserName());
            finaApplicantInvestigator.setUpdateTime(new Date());
            finaApplicantInvestigatorMapper.updateByPrimaryKey(finaApplicantInvestigator);

            try {
                //录入新数据
                assignUser(apiRequest,finaApplicantInfo,userInfo,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
        if(finaApplicantInvestigator != null){
            Long assignUserId = apiRequest.getLong("assignUserId");
            if(finaApplicantInvestigator.getSurveyUserId() == assignUserId){ //改派为本人
                Date surveyEndTime = DateUtils.parseDate(apiRequest.getString("endTime")+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
                String taskDesc = apiRequest.getString("taskDesc");
                //时效
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(finaApplicantInfo.getEntrustOrgId());
                int days = GetWorkDay.calLeaveDays(new Date(), surveyEndTime, surveyConsignor.getEfficiencyAttr());
                finaApplicantInvestigator.setUserAssignTime(new Date());
                finaApplicantInvestigator.setOrgEndTime(surveyEndTime);
                finaApplicantInvestigator.setUserAging((double) days);
                finaApplicantInvestigator.setTaskDesc(taskDesc);
                finaApplicantInvestigator.setUpdateTime(new Date());
                finaApplicantInvestigator.setUpdateBy(userInfo.getUserName());
                finaApplicantInvestigatorMapper.updateByPrimaryKey(finaApplicantInvestigator);
            }else{
                //删除原数据
                finaApplicantInvestigator.setDeleteFlag(1);
                finaApplicantInvestigator.setUpdateBy(userInfo.getUserName());
                finaApplicantInvestigator.setUpdateTime(new Date());
                finaApplicantInvestigatorMapper.updateByPrimaryKey(finaApplicantInvestigator);

                try {
                    //录入新数据
                    assignUser(apiRequest,finaApplicantInfo,userInfo,false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 改派机构
     * @param apiRequest
     * @return
     */
    private ApiResponse updAssignOrg(ApiRequest apiRequest,FinaApplicantInfo finaApplicantInfo,UserInfo userInfo){

        //删除机构案件
        Long applicantOrgId = apiRequest.getLong("applicantOrgId");
        FinaApplicantOrg finaApplicantOrg = finaApplicantOrgMapper.selectByPrimaryKey(applicantOrgId);
        finaApplicantOrg.setDeleteFlag(1);
        finaApplicantOrg.setUpdateTime(new Date());
        finaApplicantOrg.setUpdateBy(userInfo.getUserName());
        finaApplicantOrgMapper.updateByPrimaryKey(finaApplicantOrg);

        //删除所有调查员案件
        Map map = new HashMap<>();
        map.put("applicantOrgId", applicantOrgId);
        List<FinaApplicantInvestigator> finaApplicantInvestigators = finaApplicantInvestigatorMapper.list(map);
        for (FinaApplicantInvestigator finaApplicantInvestigator : finaApplicantInvestigators) {
            finaApplicantInvestigator.setDeleteFlag(1);
            finaApplicantInvestigator.setUpdateTime(new Date());
            finaApplicantInvestigator.setUpdateBy(userInfo.getUserName());
            finaApplicantInvestigatorMapper.updateByPrimaryKey(finaApplicantInvestigator);
        }

        //录入新的机构案件
        assignOrg(apiRequest,finaApplicantInfo,userInfo,false);
        /*
        if(finaApplicantOrg != null){
            Long surveyOrgId = apiRequest.getLong("surveyOrgId");
            if(finaApplicantOrg.getSurveyOrgId() == surveyOrgId){
                Date orgEndTime = DateUtils.parseDate(apiRequest.getString("endTime")+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
                String taskDesc = apiRequest.getString("taskDesc");
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(finaApplicantInfo.getEntrustOrgId());
                int days = GetWorkDay.calLeaveDays(new Date(), orgEndTime, surveyConsignor.getEfficiencyAttr());
                finaApplicantOrg.setOrgAging((double)days);//考核时效
                finaApplicantOrg.setOrgEndTime(orgEndTime);
                finaApplicantOrg.setOrgAssignTime(new Date());
                finaApplicantOrg.setTaskDesc(taskDesc);
                finaApplicantOrg.setUpdateTime(new Date());
                finaApplicantOrg.setUpdateBy(userInfo.getUserName());
                finaApplicantOrgMapper.updateByPrimaryKey(finaApplicantOrg);
            }else{
                finaApplicantOrg.setDeleteFlag(1);
                finaApplicantOrg.setUpdateTime(new Date());
                finaApplicantOrg.setUpdateBy(userInfo.getUserName());
                finaApplicantOrgMapper.updateByPrimaryKey(finaApplicantOrg);

                //删除所有调查员案件
                Map map = new HashMap<>();
                map.put("applicantOrgId", applicantOrgId);
                List<FinaApplicantInvestigator> finaApplicantInvestigators = finaApplicantInvestigatorMapper.list(map);
                for (FinaApplicantInvestigator finaApplicantInvestigator : finaApplicantInvestigators) {
                    finaApplicantInvestigator.setDeleteFlag(1);
                    finaApplicantInvestigator.setUpdateTime(new Date());
                    finaApplicantInvestigator.setUpdateBy(userInfo.getUserName());
                    finaApplicantInvestigatorMapper.updateByPrimaryKey(finaApplicantInvestigator);
                }

                //录入新的机构案件
                assignOrg(apiRequest,finaApplicantInfo,userInfo,false);
            }
        }*/

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 现场跟踪、确认到账分派
     * @param apiRequest
     * @param
     * @param userInfo
     * @return
     * @throws Exception
     */
    private ApiResponse assignOrgUser(ApiRequest apiRequest, FinaApplicantInfo finaApplicantInfo, UserInfo userInfo){

        Integer orgTaskType = apiRequest.getInt("orgTaskType"); //机构任务类型(1:垫付材料收集，2：现场跟踪，3：确认到账)
        //查询时效天数，当前日期+时效天数-1 ==机构截止日期
        Long entrustOrgId = finaApplicantInfo.getEntrustOrgId();
        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(entrustOrgId);

        FinaHospitalInfo hospitalInfo = finaHospitalInfoMapper.selectByPrimaryKey(finaApplicantInfo.getHospitalId());
        Long districtId = hospitalInfo.getDistrictId();
        if(districtId == null){
            districtId = hospitalInfo.getCityId();
        }
        Integer days = finaSurveyConsignorEfficiencyModelInfoMapper.getDays(finaApplicantInfo.getEntrustOrgId(),1L,districtId);
        if (days == null){
            days = 1;
        }
        Date endTime = GetWorkDay.calLeaveEndDate(new Date(), null, days-1, surveyConsignor.getEfficiencyAttr());

        Map qmap = new HashMap<>();
        qmap.put("userTaskType",1);//获取“材料收集”任务的垫付员案件
        qmap.put("finaInfoId",finaApplicantInfo.getId());
        FinaApplicantInvestigator firstInvestigator = finaApplicantInvestigatorMapper.selectByOne(qmap);

        FinaApplicantOrg firstOrg = finaApplicantOrgMapper.selectByPrimaryKey(firstInvestigator.getApplicantOrgId());
        Long orgId = firstOrg.getSurveyOrgId();
        String orgName = firstInvestigator.getSurveyOrgName();

        Map map =  new HashMap<>();
        map.put("surveyOrgId",orgId);
        map.put("finaInfoId",finaApplicantInfo.getId());
        map.put("orgTaskType",orgTaskType);
        FinaApplicantOrg finaApplicantOrg = finaApplicantOrgMapper.selectByOne(map);

        if (finaApplicantOrg == null || orgTaskType ==2){//已分配过的机构提示:提示不可重复分配  （现场跟踪，可以多次分派）
            finaApplicantOrg = new FinaApplicantOrg();
            finaApplicantOrg.setSurveyOrgId(orgId);
            finaApplicantOrg.setSurveyOrgName(orgName);
            finaApplicantOrg.setFinaId(finaApplicantInfo.getFinaId());
            finaApplicantOrg.setFinaInfoId(finaApplicantInfo.getId());
            finaApplicantOrg.setFinaOrgState(1);//垫付机构案件状态（1：作业中，2：已提交）
            finaApplicantOrg.setOrgTaskType(orgTaskType.intValue());//机构任务类型(1:垫付材料收集，2：现场跟踪，3：确认到账)
            finaApplicantOrg.setOrgEndTime(endTime);
            finaApplicantOrg.setOrgAssignTime(new Date());
            finaApplicantOrg.setCreateBy(userInfo.getUserName());
            finaApplicantOrg.setCreateTime(new Date());
            finaApplicantOrg.setDeleteFlag(0);
            //服务费
            Double serviceMoney = finaSurveyPriceMapper.getPrice(finaApplicantOrg.getSurveyOrgId(), AppcationTaskEnum.getDataIdByState(finaApplicantOrg.getOrgTaskType()), districtId);
            finaApplicantOrg.setServiceMoney(serviceMoney ==null ? 0D : serviceMoney);

            finaApplicantOrg.setOrgAging((double) days-1);//考核时效
            finaApplicantOrgMapper.insert(finaApplicantOrg);
        }else {
            return new ApiResponse(ApiMsgEnum.SURVEY_ASSIGN_ORG);
        }

        //根据字表id以及调查员id查询是否已经分配过。
        map =  new HashMap<>();
        map.put("finaInfoId",finaApplicantInfo.getId());
        map.put("surveyUserId",firstInvestigator.getSurveyUserId());
        map.put("userTaskType",orgTaskType);
        FinaApplicantInvestigator finaApplicantInvestigator = finaApplicantInvestigatorMapper.selectByOne(map);

        if (finaApplicantInvestigator == null || orgTaskType ==2){ //无需再次分派（现场跟踪，可以多次分派）
            finaApplicantInvestigator = new FinaApplicantInvestigator();
            finaApplicantInvestigator.setFinaId(finaApplicantInfo.getFinaId());
            finaApplicantInvestigator.setFinaInfoId(finaApplicantInfo.getId());
            finaApplicantInvestigator.setApplicantOrgId(finaApplicantOrg.getId());//机构案件id
            finaApplicantInvestigator.setSurveyOrgId(finaApplicantOrg.getSurveyOrgId());
            finaApplicantInvestigator.setSurveyOrgName(finaApplicantOrg.getSurveyOrgName());
            finaApplicantInvestigator.setSurveyUserId(firstInvestigator.getSurveyUserId());
            finaApplicantInvestigator.setSurveyUserName(firstInvestigator.getSurveyUserName());
            finaApplicantInvestigator.setState(1);//垫付员案件状态（1：作业中，2：已提交）
            finaApplicantInvestigator.setUserAssignTime(new Date());
            finaApplicantInvestigator.setOrgEndTime(endTime);
            finaApplicantInvestigator.setUserAging((double) (days-1)); //考核时效
            finaApplicantInvestigator.setUserTaskType(orgTaskType.intValue());//机构任务类型(1:垫付材料收集，2：现场跟踪，3：确认到账
            finaApplicantInvestigator.setCreateBy(userInfo.getUserName());
            finaApplicantInvestigator.setCreateTime(new Date());
            finaApplicantInvestigator.setDeleteFlag(0);
            //分值
            FinaTaskInfo finaTaskInfo = finaTaskInfoMapper.selectByPrimaryKey(finaApplicantInvestigator.getUserTaskType().longValue());
            if(finaTaskInfo!=null){
                finaApplicantInvestigator.setScore(finaTaskInfo.getScore());
            }

            //服务费
            Double serviceMoney = finaSurveyPriceMapper.getPrice(finaApplicantOrg.getSurveyOrgId(), AppcationTaskEnum.getDataIdByState(finaApplicantInvestigator.getUserTaskType()), districtId);
            finaApplicantOrg.setServiceMoney(serviceMoney ==null ? 0D : serviceMoney);
            finaApplicantInvestigator.setServiceMoney(serviceMoney ==null ? 0D : serviceMoney);
            finaApplicantInvestigatorMapper.insert(finaApplicantInvestigator);
            finaApplicantOrgMapper.updateByPrimaryKey(finaApplicantOrg);

            String title = "现场跟踪";
            String content = "你有垫付任务待跟踪，请尽快进行处理！";
            if(orgTaskType == 3){
                title = "垫付待确认到账";
                content = "你有垫付任务待确认到账，请尽快进行处理！";
            }
            //发送微信通知
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Map<String,Object> msgMap =  new HashMap<String,Object>();
            msgMap.put("title",title);
            msgMap.put("content",content);
            msgMap.put("keyWords","案件编号：" + finaApplicantInfo.getCaseApplicantNo() + "\n" + "被保人：" + finaApplicantInfo.getInsuredName() + "\n" + "任务截止日期：" + simpleDateFormat.format(finaApplicantInvestigator.getOrgEndTime()));
            backendWechatApi.send(finaApplicantInvestigator.getSurveyUserId(),msgMap);
        }else{//新建
            return new ApiResponse(ApiMsgEnum.SURVEY_ASSIGN_ORG);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }


    //计算预估医疗费用、建议垫付金额 暂定：1、预估医疗费用 根据诊断对应的分值、医院等级、城市计算出来 2、建议垫付金额=预估医疗费用*医保情况  新农合 50% 非新农合 100%
    public void syncMoney(FinaApplicantInfo finaApplicantInfo,UserInfo userInfo){
        //垫付费用信息表
        FinaApplicantMoney finaApplicantMoney = finaApplicantMoneyMapper.selectByPrimaryKey(finaApplicantInfo.getId());

        //医院信息
        Double ratio = 1D; //系数 ：根据医院的省、市，获取系数
        FinaHospitalInfo finaHospitalInfo = finaHospitalInfoMapper.selectByPrimaryKey(finaApplicantInfo.getHospitalId());
        int hospitalGrade = 1;
        if(finaHospitalInfo !=null){
            if(finaHospitalInfo.getHospitalGrade() !=null){
                hospitalGrade = finaHospitalInfo.getHospitalGrade();//医院等级
            }else{
                hospitalGrade = 7;//医院等级
            }

            Long districtId = finaHospitalInfo.getDistrictId();
            if(districtId == null){
                districtId = finaHospitalInfo.getCityId();
            }

            //根据医院的省、市，获取系数  暂定
            Map map = new HashMap<>();
            FinaSurveyCoefficientModel finaSurveyCoefficientModel = finaSurveyCoefficientModelMapper.selectByOne();
            Long modelId = finaSurveyCoefficientModel.getId();

            map = new HashMap<>();
            map.put("modelId",modelId);
            map.put("areaId",districtId);
            FinaSurveyCoefficientAreaCity finaSurveyCoefficientAreaCity = finaSurveyCoefficientAreaCityMapper.selectByOne(map);
            if(finaSurveyCoefficientAreaCity != null){
                Long areaCategoriesId = finaSurveyCoefficientAreaCity.getAreaCategoriesId();

                FinaSurveyCoefficientModelInfo finaSurveyCoefficientModelInfo = finaSurveyCoefficientModelInfoMapper.selectByModelIdAndAreaId(modelId, areaCategoriesId);
                if(finaSurveyCoefficientModelInfo !=null){
                    ratio = finaSurveyCoefficientModelInfo.getCoeff();
                }
            }
        }

        //医保标识信息
        Map map = new HashMap<>();
        int medicalInsuranceType = 1;
        map.put("finaInfoId",finaApplicantInfo.getId());
        FinaMedicalInsurance finaMedicalInsurance = finaMedicalInsuranceMapper.selectOne(map);
        if(finaMedicalInsurance !=null){
            medicalInsuranceType = finaMedicalInsurance.getMedicalInsuranceType(); //医保类型（1：城保，2：新农合，3：其他，4：无）
        }

        //垫付诊断治疗方案信息表
        Double moneyAll = 0D;
        map = new HashMap<>();
        map.put("finaInfoId",finaApplicantInfo.getId());
        List<FinaDiagnosisTreatment> list = finaDiagnosisTreatmentMapper.list(map);
        for (FinaDiagnosisTreatment finaDiagnoisTreatment : list) {

            if(finaDiagnoisTreatment.getDiagnosisId()==null || finaDiagnoisTreatment.getTreatmentId()==null){
                continue;
            }

            Double diagnosisMoneyRate = 1D;//诊断费用比例
            if(finaDiagnoisTreatment.getDiagnosisMoneyRate() != null){
                diagnosisMoneyRate = finaDiagnoisTreatment.getDiagnosisMoneyRate();
            }

            Double money = 0D;
            //根据诊断、治疗方案，医院级别，获取“”病种分值，“金额”
            map = new HashMap<>();
            map.put("diagnosisId",finaDiagnoisTreatment.getDiagnosisId());
            map.put("treatmentId",finaDiagnoisTreatment.getTreatmentId());
            FinaDiagnosisTreatmentMid mid = finaDiagnosisTreatmentMidMapper.selectOne(map);

            if(mid != null){
                //1、一级医院；2、三甲综合；3、三甲专科；4、三甲其他；5、二甲综合；6、二甲其他，对应不同的finaDiagnosisTreatmentMid金额字段
                switch (hospitalGrade){
                    case 1: money = mid.getFirstHosptal() == null ? 0D : mid.getFirstHosptal();break;
                    case 2: money = mid.getTripleAChs() == null ? 0D : mid.getTripleAChs();break;
                    case 3: money = mid.getTripleASpe() == null ? 0D : mid.getTripleASpe();break;
                    case 4: money = mid.getTripleOther() == null ? 0D : mid.getTripleOther();break;
                    case 5: money = mid.getDoubleAChs() == null ? 0D : mid.getDoubleAChs();break;
                    case 6: money = mid.getDoubleOther() == null ? 0D : mid.getDoubleOther();break;
                    case 7: money = 0D;break;
                }

                //（诊断、治疗方案、医院等级 对应的金额）  *  （医院所在城市的系数） *  诊断费用比例
                money = money * ratio * diagnosisMoneyRate;
            }

            moneyAll = moneyAll + money;
        }

        finaApplicantMoney.setEstimateMoney(DecimalUtil.twoDecimalTOFourFromFive(moneyAll));//预估医疗费用
        finaApplicantMoney.setProposalMoney(DecimalUtil.twoDecimalTOFourFromFive(moneyAll));
        //2、建议垫付金额=预估医疗费用*医保情况  新农合 50% 非新农合 100%
        if(medicalInsuranceType == 2){
            finaApplicantMoney.setProposalMoney(DecimalUtil.twoDecimalTOFourFromFive(moneyAll*0.5));
        }
        finaApplicantMoneyMapper.updateByPrimaryKey(finaApplicantMoney);
    }


    private List addFinaApplicantFileEnum(Long finaInfoId) {

        List<FinaApplicantFileEnumParDto> parent = new ArrayList();

        //委托附件
        FinaApplicantFileEnumParDto sonOne = new FinaApplicantFileEnumParDto();
        sonOne.setEnumId(1L);
        sonOne.setEnumName("委托附件");
        sonOne.setEnumNameCode("register");

        //所有的立案资料
        Map map = new HashMap<>();
        map.put("finaInfoId",finaInfoId);
        List<FinaApplicantFile> finaApplicantFiles = finaApplicantFileMapper.list(map);
        List<FinaApplicantFileEnumDto> fileGrandsons = new ArrayList<>();
        for (ApplicantFilesEnum value : ApplicantFilesEnum.values()) {//立案材料 的枚举
            List<FinaApplicantFile> temp = new ArrayList<>();
            for (FinaApplicantFile finaApplicantFile : finaApplicantFiles) {
                if (finaApplicantFile.getFileEnumId().intValue() == value.getEnumId().intValue()) {
                    temp.add(finaApplicantFile);
                }
            }
            fileGrandsons.add(new FinaApplicantFileEnumDto(value.getEnumId(),value.getEnumName(),temp));
        }
        sonOne.setSons(fileGrandsons);
        sonOne.setFileNum(finaApplicantFiles.size());
        parent.add(sonOne);


        //第二个 ：材料收集
        sonOne = new FinaApplicantFileEnumParDto();
        sonOne.setEnumId(2L);
        sonOne.setEnumName("材料收集");
        sonOne.setEnumNameCode("collect");

        map = new HashMap<>();
        map.put("finaInfoId",finaInfoId);
        List<FinaApplicantCollectFile> collectFiles = finaApplicantCollectFileMapper.list(map);
        finaApplicantFiles = new ArrayList<>();
        for (FinaApplicantCollectFile collectFile : collectFiles) {
            FinaApplicantFile finaApplicantFile = new FinaApplicantFile();
            finaApplicantFile.setFileEnumId(collectFile.getFileEnumId());
            finaApplicantFile.setFileEnumName(collectFile.getFileEnumName());
            finaApplicantFile.setFileName(collectFile.getFileName());
            finaApplicantFile.setFilePath(collectFile.getFilePath());
            finaApplicantFiles.add(finaApplicantFile);
        }

        List<FinaApplicantFileEnumDto> collectGrandsons = new ArrayList<>();
        for (ApplicantCollectFilesEnum value : ApplicantCollectFilesEnum.values()) {//材料收集 的枚举
            List<FinaApplicantFile> temp = new ArrayList<>();
            for (FinaApplicantFile finaApplicantFile : finaApplicantFiles) {
                if (finaApplicantFile.getFileEnumId().intValue() == value.getEnumId().intValue()) {
                    temp.add(finaApplicantFile);
                }
            }
            collectGrandsons.add(new FinaApplicantFileEnumDto(value.getEnumId(),value.getEnumName(),temp));
        }
        sonOne.setSons(collectGrandsons);
        sonOne.setFileNum(collectFiles.size());
        parent.add(sonOne);


        //第三个 ：签约资料
        sonOne = new FinaApplicantFileEnumParDto();
        sonOne.setEnumId(3L);
        sonOne.setEnumName("签约资料");
        sonOne.setEnumNameCode("collect");

        map = new HashMap<>();
        map.put("finaInfoId",finaInfoId);
        map.put("signState",2);//签署状态（1：待签署，2：已签约）
        List<FinaAgreementInfo> agreementFiles = finaAgreementInfoMapper.list(map);
        finaApplicantFiles = new ArrayList<>();
        for (FinaAgreementInfo agreementFile : agreementFiles) {
            if(agreementFile.getFileType() ==1 || agreementFile.getFileType() ==2){
                FinaApplicantFile finaApplicantFile = new FinaApplicantFile();
                finaApplicantFile.setFileName(agreementFile.getAgreementName());
                finaApplicantFile.setFilePath(agreementFile.getSignAgreementPath());
                finaApplicantFiles.add(finaApplicantFile);
            }else if(agreementFile.getFileType() ==3){
                List<Long> ids = new ArrayList<Long>();
                ids.add(agreementFile.getId());
                List<FinaFile> finaFiles = backendFinaFileApi.getFilesByIds(ids, FileTableEnum.FINA_AGREEMENT_INFO_ATTR);
                for (FinaFile finaFile : finaFiles) {
                    FinaApplicantFile finaApplicantFile = new FinaApplicantFile();
                    finaApplicantFile.setFileName(finaFile.getKeyName()+finaFile.getFileName());
                    finaApplicantFile.setFilePath(finaFile.getFilePath());
                    finaApplicantFiles.add(finaApplicantFile);
                }
            }
        }
        List<FinaApplicantFileEnumDto> agreementGrandsons = new ArrayList<>();
        agreementGrandsons.add(new FinaApplicantFileEnumDto(1L,"签约材料",finaApplicantFiles));
        sonOne.setSons(agreementGrandsons);
        sonOne.setFileNum(finaApplicantFiles.size());
        parent.add(sonOne);


        //确认到账
        sonOne = new FinaApplicantFileEnumParDto();
        sonOne.setEnumId(4L);
        sonOne.setEnumName("确认到账材料");
        sonOne.setEnumNameCode("confirmAccount");

        List<Long> ids = new ArrayList<Long>();
        ids.add(finaInfoId);
        List<FinaFile> confirmAccountFiles = backendFinaFileApi.getFilesByIds(ids, FileTableEnum.FINA_CONFIRM_ACCOUNT_ATTR);

        finaApplicantFiles = new ArrayList<>();
        for (FinaFile file : confirmAccountFiles) {
            FinaApplicantFile finaApplicantFile = new FinaApplicantFile();
            finaApplicantFile.setFileName(file.getFileName());
            finaApplicantFile.setFilePath(file.getFilePath());
            finaApplicantFiles.add(finaApplicantFile);
        }

        List<FinaApplicantFileEnumDto> confirmAccountsons = new ArrayList<>();
        confirmAccountsons.add(new FinaApplicantFileEnumDto(1L,"确认到账材料",finaApplicantFiles));
        sonOne.setSons(confirmAccountsons);
        sonOne.setFileNum(confirmAccountFiles.size());
        parent.add(sonOne);

        return parent;
    }


    //根据病历资料上传的图片，解析是否“风险案件”  type:num(仅获取数量)，words(获取内容)
    private Map calculationNum(String path,String type, String stringList) {
        Map numMap = new HashMap<>();
        try {
            List<String> words = new ArrayList<>();
            String wordStr = "";
            String url = path;
            CharacterRecognitionUtils characterRecognitionUtils = CharacterRecognitionUtils.getInstance();
            JSONObject json = characterRecognitionUtils.aipRecognitionUrl(url);

            org.json.JSONArray jsonArray = null;

            jsonArray = json.getJSONArray("words_result");

            for (int i=0;i<jsonArray.length();i++){
                JSONObject partDaily = jsonArray.getJSONObject(i);
                String word = partDaily.getString("words");
//                words.add(word);
                wordStr = wordStr + word;
            }

            if("num".equals(type)){
                //其他字段
                Map map = new HashMap<>();
                map.put("enumCode","medicalRecords");
                map.put("noHaveEnumCode","time");
                List<FinaApplicantFileEnum> otherEnum = finaApplicantFileEnumMapper.selectListNoSomeCode(map);

                //时间字段
                map = new HashMap<>();
                map.put("enumCode","time");
                List<FinaApplicantFileEnum> timeEnum = finaApplicantFileEnumMapper.selectListByParentEnumCodePage(map);

                int otherNum = 0;//其他字典
                int timeNum = 0;//时间字典

                //!stringList.contains(applicantFileEnum.getEnumName())  此为两张图片中，相同的值，不重复计算
                for (FinaApplicantFileEnum applicantFileEnum : otherEnum) {
                    if(wordStr.contains(applicantFileEnum.getEnumName()) && !stringList.contains(applicantFileEnum.getEnumName())){
                        otherNum++;
                        stringList = stringList + applicantFileEnum.getEnumName();
                    }
                }

                for (FinaApplicantFileEnum applicantFileEnum : timeEnum) {
                    if(wordStr.contains(applicantFileEnum.getEnumName()) && !stringList.contains(applicantFileEnum.getEnumName())){
                        timeNum++;
                        stringList = stringList + applicantFileEnum.getEnumName();
                    }
                }

                numMap.put("otherNum",otherNum);
                numMap.put("timeNum",timeNum);
                numMap.put("stringList",stringList);
            }
            else if("words".equals(type)){
                numMap.put("wordStr",wordStr);
//                numMap.put("wordList",words);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return numMap;
    }

    //进度保存
    private void setProgress(FinaApplicantInfo finaApplicantInfo,UserInfo userInfo,String progressName, String progressDesc, String keyCode ){
        String keyName ="";
        if("PROPOSAL-MONEY".equals(keyCode)){
            keyName = "建议垫付金额";
        }
        if (!"".equals(progressName)){
            if ("".equals(progressDesc) || progressDesc == null){
                progressDesc = "通过";
            }
        }
        if("PROPOSAL-MONEY".equals(keyCode)){
            progressDesc = "";
        }
        backendFinaProgressApiImpl.saveProgress(finaApplicantInfo.getId(),userInfo.getUserId(),userInfo.getUserName(),progressName,progressDesc,keyCode, keyName);
    }
}
