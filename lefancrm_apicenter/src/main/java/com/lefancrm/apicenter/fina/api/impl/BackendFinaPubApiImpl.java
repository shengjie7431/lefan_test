package com.lefancrm.apicenter.fina.api.impl;

import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.fina.api.BackendFinaPubApi;
import com.lefancrm.apicenter.fina.dao.*;
import com.lefancrm.apicenter.fina.dto.FinaPubFilesDTO;
import com.lefancrm.apicenter.fina.dto.StatesDTO;
import com.lefancrm.apicenter.fina.enums.AppcationInfoEnum;
import com.lefancrm.apicenter.fina.enums.FileTableEnum;
import com.lefancrm.apicenter.fina.enums.SettlementEnum;
import com.lefancrm.apicenter.fina.model.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@ApiService(descript = "金融垫付处理公共数据相关API")
public class BackendFinaPubApiImpl extends BaseServiceImpl implements BackendFinaPubApi {
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private FinaHospitalInfoMapper finaHospitalInfoMapper;
    @Autowired
    private FinaDiagnosisInfoMapper finaDiagnosisInfoMapper;
    @Autowired
    private BackendFinaFileApiImpl backendFinaFileApi;
    @Autowired
    private FinaTreatmentInfoMapper finaTreatmentInfoMapper;


    @ApiMethod(needLogin = false,descript = "金融垫付获取ajax数据",value = "ajax-data-fina-pub")
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        String dataType = apiRequest.getString("dataType");
        if ("entrust-org-list".equals(dataType)){//获取委托方机构列表
            Map<String,Object> paramMap =  new HashMap<>();
            paramMap.put("serviceType",1); //1代表（1,3）   业务类型：1垫付 2调查 3垫付+调查
            List<SurveyConsignor> surveyConsignors = surveyConsignorMapper.list(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,surveyConsignors.size(),surveyConsignors);
        }else if ("settlement-state-list".equals(dataType)){//获取结算单状态枚举列表
            String menuCode = apiRequest.getString("menuCode");
            List<StatesDTO> states = new ArrayList<StatesDTO>();
            SettlementEnum[] values = SettlementEnum.values();
            for (SettlementEnum value : values) {
                if ("safe-list".equals(menuCode)){
                    if (value.getState() < 4) {
                        continue;
                    }else{
                        states.add(new StatesDTO(value.getState(),value.getStateName(),value.getState() == 4 ? true : false));
                    }
                    continue;
                }else if ("acc-list".equals(menuCode)){
                    if (value.getState() < 5){
                        continue;
                    }else{
                        states.add(new StatesDTO(value.getState(),value.getStateName(),value.getState() == 5 ? true : false));
                    }
                    continue;
                }
                states.add(new StatesDTO(value.getState(),value.getStateName(),value.getChecked()));
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,states.size(),states);
        }
        else if ("applicant-state-list".equals(dataType)) //获取垫付信息枚举列表
        {
            String menuCode = apiRequest.getString("menuCode");
            List<StatesDTO> states = new ArrayList<StatesDTO>();
            AppcationInfoEnum[] values = AppcationInfoEnum.values();
            for (AppcationInfoEnum value : values) {
                if ("first-trial-list".equals(menuCode)){//垫付初审
                    if (value.getState() < 2) {
                        continue;
                    }else{
                        states.add(new StatesDTO(value.getState(),value.getStateName(),value.getState() == 2 ? true : false));
                    }
                    continue;
                }else if ("review-trial-list".equals(menuCode)){//垫付复审
                    if (value.getState() < 4){
                        continue;
                    }else{
                        states.add(new StatesDTO(value.getState(),value.getStateName(),value.getState() == 4 ? true : false));
                    }
                    continue;
                }else if ("insurance-trial-list".equals(menuCode)){//垫付保司终审
                    if (value.getState() < 5){
                        continue;
                    }else{
                        states.add(new StatesDTO(value.getState(),value.getStateName(),value.getState() == 5 ? true : false));
                    }
                    continue;
                }else if("confirm-account-list".equals(menuCode)){//垫付确认到账
                    if (value.getState() < 7){
                        continue;
                    }else{
                        states.add(new StatesDTO(value.getState(),value.getStateName(),value.getState() == 7 ? true : false));
                    }
                    continue;
                }
                states.add(new StatesDTO(value.getState(),value.getStateName(),value.getChecked()));
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,states.size(),states);
        }
        else if ("franchisee-list".equals(dataType))//获取调查方机构列表
        {
            Map<String,Object> paramMap =  new HashMap<>();
            paramMap.put("serviceType",1); //1代表（1,3）   业务类型：1垫付 2调查 3垫付+调查
            List<SurveyFranchisee> surveyFranchisees = surveyFranchiseeMapper.list(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,surveyFranchisees.size(),surveyFranchisees);
        }
        else if ("investigator-list".equals(dataType))//获取某机构下调查人员列表
        {
            Map<String,Object> paramMap =  new HashMap<>();
            Long surveyOrgId = apiRequest.getLong("surveyOrgId");
            paramMap.put("surveyOrgId",surveyOrgId);
            List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.list(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,surveyInvestigators.size(),surveyInvestigators);
        }
        else if ("hospital-info-list".equals(dataType))//就诊医院
        {
            Map<String,Object> paramMap =  new HashMap<>();
            String hospitalName = apiRequest.getString("hospitalName");
            paramMap.put("hospitalName",hospitalName);
            List<FinaHospitalInfo> finaHospitalInfos = finaHospitalInfoMapper.list(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,finaHospitalInfos.size(),finaHospitalInfos);
        }
        else if ("diagnosis-info-list".equals(dataType))//诊断
        {
            Map<String,Object> paramMap =  new HashMap<>();
            List<FinaDiagnosisInfo> finaDiagnosisInfos = finaDiagnosisInfoMapper.list(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,finaDiagnosisInfos.size(),finaDiagnosisInfos);
        }
        else if ("get-files".equals(dataType))
        {
            Long keyId = apiRequest.getLong("keyId");
            String keyCode = apiRequest.getString("keyCode");
            List<FinaFile> files = backendFinaFileApi.getFiles(keyId, FileTableEnum.getFileTableEnumByCode(keyCode));
            List<FinaPubFilesDTO> pubFiles = new ArrayList<FinaPubFilesDTO>();
            pubFiles.add(new FinaPubFilesDTO(1L,"附件",files));
            return new ApiResponse(ApiMsgEnum.SUCCESS,pubFiles.size(),pubFiles);
        }
        else if ("treatment-info-list".equals(dataType))//诊断对应的治疗方案
        {
            Long diagnosisId = apiRequest.getLong("diagnosisId");
            Map<String,Object> paramMap =  new HashMap<>();
            paramMap.put("diagnosisId",diagnosisId);
            List<FinaTreatmentInfo> finaTreatmentInfoList = finaTreatmentInfoMapper.selectByDiagnosis(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,finaTreatmentInfoList.size(),finaTreatmentInfoList);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
