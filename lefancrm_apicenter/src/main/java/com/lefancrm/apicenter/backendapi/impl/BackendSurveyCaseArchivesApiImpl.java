package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyCaseArchivesApi;
import com.lefancrm.apicenter.dao.SurveyCaseArchivesMapper;
import com.lefancrm.apicenter.dao.SurveyRiskCaseInfoMapper;
import com.lefancrm.apicenter.dao.SurveyRiskCaseMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyCaseArchives;
import com.lefancrm.apicenter.model.SurveyRiskCase;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by wangwei on 2020年10月28日14:07:00
 * 归档管理
 */
@Service
@ApiService(descript = "归档管理")
public class BackendSurveyCaseArchivesApiImpl extends BaseServiceImpl implements BackendSurveyCaseArchivesApi {

    @Autowired
    private SurveyCaseArchivesMapper surveyCaseArchivesMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;

    @ApiMethod(needLogin = false,descript = "归档管理list",value = "backend-survey-case-archives-list")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        String noPage = apiRequest.getString("noPage");
        if(StringUtils.isEmpty(noPage)){
            setBackendPageSize(apiRequest);
        }
        int count = surveyCaseArchivesMapper.selectArchivesSize(apiRequest);
        List<SurveyCaseArchives> list = surveyCaseArchivesMapper.selectArchives(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    /**
     * operate
     */
    @ApiMethod(descript = "归档管理操作", value = "backend-survey-case-archives-operate")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse operate(ApiRequest apiReq) {

        String btnCode = apiReq.getString("btnCode");
        Long currentUserId = getCurrentUserId(apiReq);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        SurveyCaseArchives surveyCaseArchives = surveyCaseArchivesMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (surveyCaseArchives == null){
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(apiReq.getLong("surveyInfoId"));
            SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
            surveyCaseArchives= new SurveyCaseArchives();
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
        if("finish".equals(btnCode)){ //标记归档完成
            surveyCaseArchives.setArchivesState(1);
        }else if("revoke".equals(btnCode)){//撤销归档完成
            surveyCaseArchives.setArchivesState(0);
        }else if("itemUpdate".equals(btnCode)){//更新数据
            String colCode = apiReq.getString("colCode");
            String value = apiReq.getString("value");
            switch (colCode){
                case "interviewRecord" : surveyCaseArchives.setInterviewRecord(convert(value));break;
                case "medicalRecord" : surveyCaseArchives.setMedicalRecord(convert(value));break;
                case "socialInsurance" : surveyCaseArchives.setSocialInsurance(convert(value));break;
                case "isDeath" : surveyCaseArchives.setIsDeath(convert(value));break;
                case "legalHeir" : surveyCaseArchives.setLegalHeir(convert(value));break;
                case "legalHeirRelationship" : surveyCaseArchives.setLegalHeirRelationship(convert(value));break;
                case "deathCertificate" : surveyCaseArchives.setDeathCertificate(convert(value));break;
                case "isGiveUp" : surveyCaseArchives.setIsGiveUp(convert(value));break;
                case "claimsGiveUp" : surveyCaseArchives.setClaimsGiveUp(convert(value));break;
                case "otherStatement" : surveyCaseArchives.setOtherStatement(convert(value));break;
                case "isAccident" : surveyCaseArchives.setIsAccident(convert(value));break;
                case "anAccident" : surveyCaseArchives.setAnAccident(convert(value));break;
                case "noAccident" : surveyCaseArchives.setNoAccident(convert(value));break;
                case "publicInspection" : surveyCaseArchives.setPublicInspection(convert(value));break;
                case "appraisalReport" : surveyCaseArchives.setAppraisalReport(convert(value));break;
                case "legalInstrument" : surveyCaseArchives.setLegalInstrument(convert(value));break;
                case "otherItems" : surveyCaseArchives.setOtherItems(convert(value));break;
                case "medicalReport" : surveyCaseArchives.setMedicalReport(convert(value));break;
                case "remark" : surveyCaseArchives.setRemark(value);break;
            }
            surveyCaseArchives.setArchivesTime(new Date());
            surveyCaseArchives.setArchivesBy(userInfo.getUserName());
            surveyCaseArchivesMapper.updateByPrimaryKey(surveyCaseArchives);

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyCaseArchives); //返回前端
        }else if("finishAll".equals(btnCode)){//批量标记归档
            String ids = apiReq.getString("ids");
            if(ids!=null) {
                String[] role = ids.split(",");
                for (String ro : role) {
                    SurveyCaseArchives info  = surveyCaseArchivesMapper.selectByPrimaryKey(Long.valueOf(ro));
                    info.setArchivesState(1);
                    info.setArchivesTime(new Date());
                    info.setArchivesBy(userInfo.getUserName());
                    surveyCaseArchivesMapper.updateByPrimaryKey(info);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }

        surveyCaseArchives.setArchivesTime(new Date());
        surveyCaseArchives.setArchivesBy(userInfo.getUserName());
        surveyCaseArchivesMapper.updateByPrimaryKey(surveyCaseArchives);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    private Integer convert(String value){
        if (!StringUtils.isEmpty(value)){
            return Integer.parseInt(value);
        }
        return null;
    }
}
