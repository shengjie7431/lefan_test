package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyCaseDirectionApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.dto.hzReport.CaseAreaDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wangwei on 2019/04/04.
 */
@Service
@ApiService(descript = "调查案件方向表API")
public class BackendSurveyCaseDirectionApiImpl extends BaseServiceImpl implements BackendSurveyCaseDirectionApi {

    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;

    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;

    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;

    @Autowired
    private SurveyInvestigatorCaseTypeMapper surveyInvestigatorCaseTypeMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;

    @ApiMethod(needLogin = false,descript = "调查案件方向表-分值清单",value = "backend-survey-case-direction-list-by-score")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        try{
            setBackendPageSize(apiRequest);
            int count = surveyCaseDirectionMapper.listSizeByScore(apiRequest);
            List<SurveyCaseDirectionScoreDto> list = surveyCaseDirectionMapper.listByScore(apiRequest);
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
        }catch (Exception e){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(needLogin = false,descript = "获取方向信息",value = "backend-survey-direction-info-by-direction-id")
    @Override
    public ApiResponse directionInfo(ApiRequest apiRequest) {
        Long directionId = apiRequest.getLong("directionId");
        SurveyCaseDirection surveyCaseDirection = surveyCaseDirectionMapper.selectByPrimaryKey(directionId);
        SurveyRiskCaseInfoDto surveyRiskCaseInfoDto = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyCaseDirection.getSurveyInfoId());
        surveyCaseDirection.setSurveyCno(surveyRiskCaseInfoDto.getSurveyCno());
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyCaseDirection);
    }

    @ApiMethod(needLogin = false,descript = "调查案件方向表-分值详情",value = "backend-survey-case-direction-info-by-score")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {

        List<SurveyCaseDirectionScoreDto> list = surveyCaseDirectionMapper.directionListByUser(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,list==null?0:list.size(),list);
    }

    @ApiMethod(needLogin = false,descript = "分值清单（导出）",value = "backend-survey-case-direction-list-to-export")
    @Override
    public ApiResponse listToExport(ApiRequest apiRequest) {

        List<SurveyCaseDirectionScoreDto> list = surveyCaseDirectionMapper.listByScore(apiRequest);
        List<SurveyCaseDirectionScoreDto> directionList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            apiRequest.put("userId",list.get(i).getSurveyUserId());
            List<SurveyCaseDirectionScoreDto> directions = surveyCaseDirectionMapper.directionListByUser(apiRequest);
            directionList.addAll(directions);
        }
        Map map = new HashMap<>();
        map.put("list",list);
        map.put("directionList",directionList);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }


    @ApiMethod(needLogin = false,descript = "查询报表详情",value = "backend-survey-case-direction-list-to-detailsList")
    @Override
    public ApiResponse detailsList(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        String menuCode=apiRequest.getString("menuCode");
        Map map=new HashMap();
        map.put("menuCode",menuCode);
        if(!"regionalDistribution".equals(menuCode)){
            setBackendPageSize(apiRequest);
        }
        if("regionalDistribution".equals(menuCode)){//案件区域分布报表

            //获取所选互助平台
            String orgId=apiRequest.getString("platform");
            map.put("orgId",orgId);
            //获取案件状态
            String caseStatus=apiRequest.getString("caseStatus");
            map.put("caseStatus",caseStatus);
            //获取案件类型
            String caseType=apiRequest.getString("caseType");
            map.put("caseType",caseType);
            //获取时间
            String startTime=apiRequest.getString("startTime");
            map.put("startTime",startTime+" 00:00:00");
            String endTime=apiRequest.getString("endTime");
            map.put("endTime",endTime+" 23:59:59");
            //获取省级ID
            String provinceId=apiRequest.getString("provinceId");
            map.put("provinceId",provinceId);
            //获取市级ID
            String cityId=apiRequest.getString("cityId");
            map.put("cityId",cityId);
            List<SurveyCaseDirectionDto> list=surveyCaseDirectionMapper.selectPmlList(map);
            //计算总和
            Double count = list.stream().collect(Collectors.summingDouble(SurveyCaseDirectionDto::getCount));
            for (SurveyCaseDirectionDto surveyCaseDirectionDto:list) {
                Double ratio = Double.parseDouble(surveyCaseDirectionDto.getCount().toString()) * 100 / count;
                DecimalFormat df = new DecimalFormat("#.00");
                surveyCaseDirectionDto.setProportion(Double.parseDouble(df.format(ratio)));
            }
            map=new HashMap();
            map.put("list",list);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
        }else if("investigatorReport".equals(menuCode)){//调查员报表详情
            map.put("surveyUserId",apiRequest.getLong("userId"));
            List<InvestigatorDetailsDto> list=surveyInvestigatorCaseMapper.selectInvestigatorDetails(apiRequest);
            for (InvestigatorDetailsDto investigatorDetailsDto:list) {
                map.put("surveyUserCaseId",investigatorDetailsDto.getaId());
                List<SurveyInvestigatorCaseType> surveyInvestigatorCaseTypeList=surveyInvestigatorCaseTypeMapper.list(map);
                investigatorDetailsDto.setTaskTypeList(surveyInvestigatorCaseTypeList);
            }
            Integer count=surveyInvestigatorCaseMapper.selectInvestigatorDetailsCount(apiRequest);
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
        }else if("directionAreaDistribution".equals(menuCode)){//方向区域分布报表
            //获取所选互助平台
            String orgId=apiRequest.getString("platform");
            map.put("orgId",orgId);
            //获取案件状态
            String caseStatus=apiRequest.getString("caseStatus");
            map.put("caseStatus",caseStatus);
            //获取时间
            String startTime=apiRequest.getString("startTime");
            map.put("startTime",startTime+" 00:00:00");
            String endTime=apiRequest.getString("endTime");
            map.put("endTime",endTime+" 23:59:59");
            //获取省级ID
            String provinceId=apiRequest.getString("provinceId");
            map.put("provinceId",provinceId);
            //获取市级ID
            String cityId=apiRequest.getString("cityId");
            map.put("cityId",cityId);
            List<SurveyCaseDirectionDto> list=surveyCaseDirectionMapper.selectPmlList(map);
            //计算总和
            Double count = list.stream().collect(Collectors.summingDouble(SurveyCaseDirectionDto::getCount));
            for (SurveyCaseDirectionDto surveyCaseDirectionDto:list) {
                Double ratio = Double.parseDouble(surveyCaseDirectionDto.getCount().toString()) * 100 / count;
                DecimalFormat df = new DecimalFormat("#.00");
                surveyCaseDirectionDto.setProportion(Double.parseDouble(df.format(ratio)));
            }
            map=new HashMap();
            map.put("list",list);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
        }
        return null;
    }
}
