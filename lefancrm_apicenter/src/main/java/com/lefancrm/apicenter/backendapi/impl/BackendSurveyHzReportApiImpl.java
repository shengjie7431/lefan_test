package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyHzReportApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.dto.hzReport.*;
import com.lefancrm.apicenter.dto.report.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "狄大人互助案件报表API")
public class BackendSurveyHzReportApiImpl extends BaseServiceImpl implements BackendSurveyHzReportApi {

    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyInvestigatorCaseTypeMapper surveyInvestigatorCaseTypeMapper;
    @Autowired
    private SurveyTaskTypeMapper surveyTaskTypeMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyReimbursementInfoMapper reimbursementInfoMapper;
    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;
    @Autowired
    private SurveyTaskInfoMapper surveyTaskInfoMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyAssignOrgTypeMapper surveyAssignOrgTypeMapper;
    @Autowired
    private SurveyClockCaseMapper clockCaseMapper;
    @Autowired
    private SurveyCheckPreFlowMapper surveyCheckPreFlowMapper;
    @Autowired
    private SurveyAssignOrgExtendMapper surveyAssignOrgExtendMapper;

    @ApiMethod(needLogin = false,descript = "获取互助报表数据",value = "get-data-survey-hz-report")
    @Override
    public ApiResponse getData(ApiRequest apiRequest) {
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Long currentUserId = getCurrentUserId(apiRequest);
        //积分报表 score
        String menuCode = apiRequest.getString("menuCode");

        //upMonth 上月 yesterday 昨天 today 今天 curWeek 本周 curMonth 本月  all 全部  date 时间段  last30Days最近30天
        String searchType = apiRequest.getString("searchType");
        String startTime = apiRequest.getString("startTime");
        String endTime = apiRequest.getString("endTime");
        if ("date".equals(searchType)){
            //正常情况下startTime  endTime 都不为空  防止前台传NULL 故加 默认值
            if (startTime == null){
                startTime = "2019-02-27";
            }
            if (endTime == null){
                endTime = LocalDate.now().toString();
            }
        }else{//快捷查询  解析开始时间 与 结束时间（上月,昨天，今天，本周，本月，全部，N月度，N季度，...）
            StringBuilder builderStart = new StringBuilder("");
            StringBuilder builderEnd = new StringBuilder("");
            if ("month".equals(searchType) || "quarter".equals(searchType)){
                int year = apiRequest.getInt("year");
                int keyValue = apiRequest.getInt("keyValue");
                convertTimeBySearchType(builderStart,builderEnd,searchType,year,keyValue);
            }else{
                convertTimeBySearchType(builderStart,builderEnd,searchType);
            }
            startTime = builderStart.toString();
            endTime = builderEnd.toString();
            if ("all".equals(searchType)){
                startTime = "2019-02-27";
                endTime = LocalDate.now().toString();
            }
        }

        try {
            if (startTime != null){
                paramMap.put("startTime",simpleDateFormat.parse(startTime));
            }
            if (endTime !=  null){
                paramMap.put("endTime",simpleDateFormat.parse(endTime));
            }

            //环比时间
            String momTime = DateUtils.getChainTime(startTime,endTime);
            paramMap.put("momTime",simpleDateFormat.parse(momTime));
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String,Object> dataRoleMap = getDataRole(currentUserId,menuCode);
        if (dataRoleMap != null){
            paramMap.put("dataRoleCode",dataRoleMap.get("dataRoleCode"));//获取  平台数据manager 省级数据provincialManger  片区数据areaManger 的编码
            paramMap.put("dataRoleOrgId",dataRoleMap.get("dataRoleOrgId"));
        }else{
            return new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
        }
        paramMap.put("orgDataType",1);// 默认 1只查询互助机构数据 (保司类型 在下边赋值 2)
        paramMap.put("orgAttr",apiRequest.getString("orgAttr"));//
        Map map = new HashMap();
        switch (menuCode){
            case "score" : //互助积分报表
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("surveyState", apiRequest.getLong("surveyState"));//案件状态
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型
                map = getScoreList(paramMap, map, currentUserId);
                break;
            case "score-son"://互助积分（片区）
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("surveyState", apiRequest.getLong("surveyState"));//案件状态
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型

                paramMap.put("son", apiRequest.getLong("son"));
                Long orgId = apiRequest.getLong("orgId");
                map = getScoreList(paramMap, map, currentUserId,orgId);
                break;
            case "score-son-item"://互助积分（调查员）
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("surveyState", apiRequest.getLong("surveyState"));//案件状态
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型

                orgId = apiRequest.getLong("orgId");
                int scoreSon = apiRequest.getInt("son");
                map = getScoreList(paramMap, map, currentUserId,orgId,scoreSon,"org");
                break;
            case "scoreBs" : //保司积分报表
                paramMap.put("orgDataType",2);//
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("surveyState", apiRequest.getLong("surveyState"));//案件状态
                map = getScoreList(paramMap, map, currentUserId);
                break;
            case "scoreBs-son"://保司积分（片区）
                paramMap.put("orgDataType",2);//
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("surveyState", apiRequest.getLong("surveyState"));//案件状态

                paramMap.put("son", apiRequest.getLong("son"));
                map = getScoreList(paramMap, map, currentUserId, apiRequest.getLong("orgId"));
                break;
            case "scoreBs-son-item"://保司积分（调查员）
                paramMap.put("orgDataType",2);//
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("surveyState", apiRequest.getLong("surveyState"));//案件状态

                orgId = apiRequest.getLong("orgId");
                map = getScoreList(paramMap, map, currentUserId,orgId,apiRequest.getInt("son"),"org");
                break;
            case "assessmentIndex": //考核指标报表明细
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型
                paramMap.put("clickPq",apiRequest.getString("clickPq"));
                paramMap.put("clickPqSurv",apiRequest.getString("clickPqSurv"));
                paramMap.put("surveyAreaId",apiRequest.getLong("surveyAreaId"));
                map = getAssessmentIndexList(paramMap);
                break;
            case "incomeAndCost": //收入与成本报表明细
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("clickPq",apiRequest.getString("clickPq"));
                paramMap.put("orgAttr",2);
                map = getIncomeAndCostList(paramMap);
                break;
            case "incomeAndCostBs": //收入与成本报表明细-保司
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("clickPq",apiRequest.getString("clickPq"));
                paramMap.put("orgAttr",1);
                map = getIncomeAndCostList(paramMap);
                break;
            case "surveyManpower"://调查员人力报表(省级)
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                map = getSurveyManpower(paramMap,map,currentUserId);
                break;
            case "surveyManpower-son"://调查员人力报表(片区)
                orgId = apiRequest.getLong("orgId");
                map = getSurveyManpower(paramMap,map,currentUserId,orgId);
                break;
            case "surveyManpower-son-item" :
                orgId = apiRequest.getLong("orgId");
                int son = apiRequest.getInt("son");
                paramMap.put("colType",apiRequest.getLong("colType"));
                map = getSurveyManpower(paramMap,map,currentUserId,orgId,son);
                break;
            case "surveyManpowerBs"://调查员人力报表(省级)
                paramMap.put("orgDataType",2);// 2只查询保司机构数据
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                map = getSurveyManpowerBs(paramMap,map,currentUserId);
                break;
            case "surveyManpowerBs-son"://调查员人力报表(片区)
                paramMap.put("orgDataType",2);// 2只查询保司机构数据
                orgId = apiRequest.getLong("orgId");
                map = getSurveyManpowerBs(paramMap,map,currentUserId,orgId);
                break;
            case "surveyManpowerBs-son-item" :
                paramMap.put("orgDataType",2);// 2只查询保司机构数据
                orgId = apiRequest.getLong("orgId");
                son = apiRequest.getInt("son");
                paramMap.put("colType",apiRequest.getLong("colType"));
                map = getSurveyManpowerBs(paramMap,map,currentUserId,orgId,son);
                break;
            case "regionalDistribution"://案件区域分布报表
                map=selectReportList(apiRequest);
                break;
            case "directionAreaDistribution"://方向区域分布报表
                map=selectReportList(apiRequest);
                break;
            case "taskDistribution"://任务分布报表
                map=seletTaskDistribution(apiRequest);
                break;
            case "investigatorReport"://调查员报表
                map=selectInvestigatorReport(apiRequest.getLong("userId"),paramMap);
                break;
            case "progressTrack"://进度跟踪报表(省级) 互助
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型
                paramMap.put("orgAttr",2);
                map = getProgressTrackList(paramMap, map, currentUserId);
                break;
            case "progressTrackBs"://进度跟踪报表(省级) 保司
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型
                paramMap.put("orgAttr",1);
                map = getProgressTrackList(paramMap, map, currentUserId);
                break;
            case "progressTrack-son"://进度跟踪报表（片区） 互助
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型
                paramMap.put("son", apiRequest.getLong("son"));
                orgId = apiRequest.getLong("orgId");
                paramMap.put("orgAttr",2);
                map = getProgressTrackList(paramMap, map, currentUserId,orgId);
                break;
            case "progressTrack-son-bs"://进度跟踪报表（片区）保司
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型
                paramMap.put("son", apiRequest.getLong("son"));
                orgId = apiRequest.getLong("orgId");
                paramMap.put("orgAttr",1);
                map = getProgressTrackList(paramMap, map, currentUserId,orgId);
                break;
            case "progressTrack-son-item"://进度跟踪报表（调查员）互助
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型

                orgId = apiRequest.getLong("orgId");
//                int trackSon = apiRequest.getInt("son");
                paramMap.put("orgAttr",2);
                map = getProgressTrackList(paramMap, map, currentUserId,orgId,apiRequest.getInt("son"));
                break;
            case "progressTrack-son-item-bs"://进度跟踪报表（调查员）保司
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("caseState", apiRequest.getLong("caseState"));//案件类型
                paramMap.put("orgCaseState", apiRequest.getLong("orgCaseState"));//机构案件类型

                orgId = apiRequest.getLong("orgId");
//                int trackSon = apiRequest.getInt("son");
                paramMap.put("orgAttr",1);
                map = getProgressTrackList(paramMap, map, currentUserId,orgId,apiRequest.getInt("son"));
                break;
            case "caseDirection"://查得率报表(省级)
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("surveyState", apiRequest.getLong("surveyState"));//案件状态
                map = getCaseDirectionList(paramMap, map, currentUserId);
                break;
            case "caseDirection-son"://查得率报表（片区）
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("surveyState", apiRequest.getLong("surveyState"));//案件状态
                paramMap.put("son", apiRequest.getLong("son"));
                orgId = apiRequest.getLong("orgId");
                map = getCaseDirectionList(paramMap, map, currentUserId,orgId);
                break;
            case "caseDirection-son-item"://查得率报表（调查员）
                paramMap.put("surveyOrgIds", apiRequest.getString("surveyOrgIds"));//调查机构
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("surveyInvestigators", apiRequest.getString("surveyInvestigators"));//调查员
                paramMap.put("surveyState", apiRequest.getLong("surveyState"));//案件状态

                orgId = apiRequest.getLong("orgId");
                Long directionSon = apiRequest.getLong("son");
                map = getCaseDirectionList(paramMap, map, currentUserId,orgId,directionSon,"org");
                break;
            case "review-user" :
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));//互助平台
                paramMap.put("finalInfos", apiRequest.getString("finalInfos"));//复审人员
                map = getOprUserData(paramMap,map,currentUserId);
                break;
            case "review-user-item" :
                paramMap.put("entrustOrgIds", apiRequest.getString("entrustOrgIds"));
                paramMap.put("reviewUserId",apiRequest.getLong("reviewUserId"));
                paramMap.put("fieldType",apiRequest.getInt("fieldType"));
                map = getOprUserDataItem(paramMap,map,currentUserId);
                break;
        }

        map.put("searchType",searchType);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    private Map getOprUserData(Map<String,Object> paramMap,Map map ,Long currentUserId){
        List<UserInfoOprDTO> oprData = userInfoMapper.selectUserInfoOprData(paramMap);
        UserInfoOprDTO total = new UserInfoOprDTO();
        total.setOprNum(0);
        total.setOprOverNum(0);
        total.setBasScore(0D);
        total.setSunScore(0D);
        total.setTotalScore(0D);
        total.setSunCaseNum(0);
        total.setTnocaticitcp(0);
        total.setTnocaticitcpRate(0D);
        total.setNocpbrpd(0);
        total.setNocpbrpdRate(0D);
        total.setPrescription(0D);

        for (UserInfoOprDTO item : oprData) {
            Double rate = oprUserRate(item.getOprOverNum(),item.getOprNum());
            item.setOverRate(DecimalUtil.fourDecimalTOFourFromFive(rate));
            rate = oprUserRate(item.getSunCaseNum(),item.getOprNum());
            item.setSunRate(DecimalUtil.fourDecimalTOFourFromFive(rate));
            rate = oprUserRate(item.getTnocaticitcp(),item.getOprNum());
            item.setTnocaticitcpRate(DecimalUtil.fourDecimalTOFourFromFive(rate));
            rate = oprUserRate(item.getNocpbrpd(),item.getOprNum());
            item.setNocpbrpdRate(DecimalUtil.fourDecimalTOFourFromFive(rate));
            if(item.getTotalPrescription() != null && item.getOprNum() !=0){
                item.setPrescription(item.getTotalPrescription()/item.getOprNum());
                total.setPrescription(total.getPrescription()+item.getPrescription());
            }else if(item.getOprNum() == 0){
                item.setPrescription(0D);
            }

            total.setOprNum(total.getOprNum() + item.getOprNum());
            total.setOprOverNum(total.getOprOverNum() + item.getOprOverNum());
            total.setBasScore(total.getBasScore() + item.getBasScore());
            total.setSunScore(total.getSunScore() + item.getSunScore());
            total.setTotalScore(total.getTotalScore() + item.getTotalScore());
            total.setSunCaseNum(total.getSunCaseNum() + item.getSunCaseNum());
            total.setTnocaticitcp(total.getTnocaticitcp() + item.getTnocaticitcp());
            total.setNocpbrpd(total.getNocpbrpd() + item.getNocpbrpd());
        }
        total.setOverRate(DecimalUtil.fourDecimalTOFourFromFive(totalRate(total.getOprOverNum(),total.getOprNum())));
        total.setSunRate(DecimalUtil.fourDecimalTOFourFromFive(totalRate(total.getSunCaseNum(),total.getOprNum())));
        total.setTnocaticitcpNumRate(DecimalUtil.fourDecimalTOFourFromFive(totalRate(total.getTnocaticitcp(),total.getOprNum())));
        total.setNocpbrpdNumRate(DecimalUtil.fourDecimalTOFourFromFive(totalRate(total.getNocpbrpd(),total.getOprNum())));

        //总计平均时效计算
        double orgSumAging = oprData.stream().mapToDouble(e -> e.getPrescription() * e.getOprNum() * 60).sum();//机构总时效
        int caseSum = oprData.stream().mapToInt(UserInfoOprDTO::getOprNum).sum();//机构所有案子总数
        total.setPrescription(caseSum == 0 ? 0 : (orgSumAging/caseSum));
        map.put("list",oprData);
        map.put("total",total);
        return map;
    }

    private Double totalRate(Double a,Double b){
        if (a == 0 || b == 0){
            return 0D;
        }
        return new BigDecimal(a / b).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private Double totalRate(int a,int b){
        return totalRate(new Double(String.valueOf(a)),new Double(String.valueOf(b)));
    }

    private Map getOprUserDataItem(Map<String,Object> paramMap,Map map ,Long currentUserId){
        List<OprUserDataItemDTO> oprData = userInfoMapper.selectUserInfoOprDataItem(paramMap);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (OprUserDataItemDTO item : oprData) {
            //机构时效

            //复审时效
            Boolean oprOver = false;
            Date startDate =  item.getCommitDateStr() == null ? new Date() : item.getCommitDate(),endDate = null;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DATE, 1);
            Date oneDayAfter = calendar.getTime();//一天之后的时间
            if (item.getReviewTimeStr() == null){//未提交
                if (oneDayAfter.before(new Date())){//加上一天之后 在当前时间之后。 则超时
                    startDate = oneDayAfter;
                    endDate = new Date();
                    oprOver = true;
                }else{//未超时
                    startDate = oneDayAfter;
                    endDate = new Date();
                }
            }else{//已提交
                endDate = item.getReviewTime();
                if (oneDayAfter.before(endDate)){
                    oprOver = true;
                }
            }
//            Long startTime = startDate.getTime(), endTime = endDate.getTime();
//            int day = Math.abs((int) ((startTime-endTime)/86400000));
//            //计算小时
//            int h = Math.abs((int) (((startTime-endTime)%86400000)/3600000));
//            //计算分钟
//            int m = Math.abs((int)(((startTime-endTime)%86400000)%3600000)/60000);
//            //计算秒
//            int s = Math.abs((int)((((startTime-endTime)%86400000)%3600000)%60000)/1000);
//            StringBuffer oprOverStr = new StringBuffer();
//            /*if (item.getReviewTime() == null){
//                if (oprOver){
//                    oprOverStr.append("超时");
//                }else{
//                    oprOverStr.append("剩余");
//                }
//            }*/
//            oprOverStr.append(day + "天" + h + "小时" + m + "分钟");
//            item.setAgingReview(oprOverStr.toString());
//            item.setAgingReviewTwo((endTime-startTime)/1000);
            item.setEntrustReportEndDateStr(DateUtils.DateToStr(item.getEntrustReportEndDate(),"yyyy-MM-dd HH:mm:ss"));

            List<Map<String,Object>> surveyCheckMapList = new ArrayList<>();
            List<SurveyCheckPreFlow> surveyCheckPreFlows = surveyCheckPreFlowMapper.selectPreListBySurveyAssignOrgId(item.getSurveyAssorgCaseId());
            Map<String, Object> orgPreMap = new HashMap<>();
            for (int i = 0; i < surveyCheckPreFlows.size(); i++) {
                SurveyCheckPreFlow surveyCheckPreFlow = surveyCheckPreFlows.get(i);
                Integer operateType = surveyCheckPreFlow.getOperateType();
                String str = "";
                if (operateType == 1) {
                    str = "机构提交时间:";
                } else if (operateType == 2) {
                    str = "复审通过时间:";
                } else if (operateType == 3) {
                    str = "复审撤回时间:";
                } else if (operateType == 4) {
                    str = "复审退回时间:";
                } else if (operateType == 5) {
                    str = "终审退回时间:";
                }
                if ((i & 1) == 1) {//奇数
                    orgPreMap.put("k2", "第" + surveyCheckPreFlow.getRowNum() + "次" + str + simpleDateFormat.format(surveyCheckPreFlow.getEndTime()));
                    if (surveyCheckPreFlow.getDays() >= 60) {
                        orgPreMap.put("k3", surveyCheckPreFlow.getDays().intValue() > 60 ? ("用时" + surveyCheckPreFlow.getDays().intValue()/60 + "小时" + surveyCheckPreFlow.getDays().intValue() % 60  +"分钟") : "用时1小时");
                    } else {
                        orgPreMap.put("k3", "用时" + surveyCheckPreFlow.getDays().intValue() + "分钟");
                    }
                    surveyCheckMapList.add(orgPreMap);
                } else {
                    if (i != 0){
                        orgPreMap = new HashMap<>();
                    }
                    orgPreMap.put("k1", "第" + surveyCheckPreFlow.getRowNum() + "次" + str + simpleDateFormat.format(surveyCheckPreFlow.getStartTime()));
                }
            }
            item.setSurveyCheckMapList(surveyCheckMapList);
            SurveyAssignOrgExtend surveyAssignOrgExtend = surveyAssignOrgExtendMapper.selectByPrimaryKey(item.getSurveyAssorgCaseId());
            if (surveyAssignOrgExtend != null){
                item.setSurveyAssignOrgExtend(surveyAssignOrgExtend);
                item.setAgingReviewTwo((long) (surveyAssignOrgExtend.getAgingReal()*60));
            }
        }
        map.put("list",oprData);
        return map;
    }

    private Double oprUserRate(int a ,int b){
        if (b == 0){
            return 0D;
        }
        return new Double(String.valueOf(a)) / new Double(String.valueOf(b));
    }


    private List<ScoreDto> returnScoreList(Map<String, Object> paramMap, int level) {
        List<ScoreDto> list = new ArrayList<>();
        if(level ==1){
            list = surveyInvestigatorCaseMapper.getScoreListBySurveyOrg(paramMap);
            //获取片区数据
            for (ScoreDto scoreDto : list) {
                int orgLevel = scoreDto.getOrgLevel();//省级机构，片区机构
                if(orgLevel == 1){
                    String ids = surveyFranchiseeMapper.selectChildrens(scoreDto.getSurveyOrgId());
                    if(ids !=null){
                        scoreDto.setOrgType(1);
                        scoreDto.setOrgTypeName("A类");

                        paramMap.put("surveyOrgIds", ids);
                        paramMap.put("level", 2);//查询到的是片区机构数据
                        List<ScoreDto> childrenList = surveyInvestigatorCaseMapper.getScoreListBySurveyOrg(paramMap);
                        //调查员
                        for (ScoreDto dto : childrenList) {
                            paramMap.remove("surveyOrgIds");
                            paramMap.put("orgId", dto.getSurveyOrgId());
                            List<ScoreDto> userList = surveyInvestigatorCaseMapper.getScoreListByInvestigator(paramMap);
                            dto.setChildren(userList);
                        }
                        scoreDto.setChildren(childrenList);
                    }
                    else{
                        scoreDto.setOrgType(2);
                        scoreDto.setOrgTypeName("B类");

                        paramMap.remove("surveyOrgIds");
                        paramMap.put("orgId", scoreDto.getSurveyOrgId());
                        List<ScoreDto> userList = surveyInvestigatorCaseMapper.getScoreListByInvestigator(paramMap);
                        scoreDto.setChildren(userList);

                    }
                }
            }
        }
        else if(level ==2){
            list = surveyInvestigatorCaseMapper.getScoreListBySurveyOrg(paramMap);
            for (ScoreDto dto : list) {
                paramMap.remove("surveyOrgIds");
                paramMap.put("orgId", dto.getSurveyOrgId());
                List<ScoreDto> userList = surveyInvestigatorCaseMapper.getScoreListByInvestigator(paramMap);
                dto.setChildren(userList);
            }
        }
        return list;
    }

    public Map getIncomeAndCostList(Map<String, Object> paramMap) {
        //机构负责人，仅看到本机构的数据
        String dataRoleCode = paramMap.get("dataRoleCode") != null ? paramMap.get("dataRoleCode").toString() : "";
        String dataRoleOrgId = paramMap.get("dataRoleOrgId") != null ? paramMap.get("dataRoleOrgId").toString() : "";
        if ("manger".equals(dataRoleCode) || "provincialManger".equals(dataRoleCode)) {
            paramMap.put("level", 1);//查询到的是省级机构数据
            if ("provincialManger".equals(dataRoleCode)) {
                paramMap.put("surveyOrgIds", dataRoleOrgId);
            }
        } else if ("districtManger".equals(dataRoleCode)) {
            paramMap.put("level", 1);//查询到的是片区机构数据
            paramMap.put("surveyOrgIds", dataRoleOrgId);
        }
        boolean flag = paramMap.get("clickPq") == null;
        if (!flag) {
            paramMap.put("level", 2);//查询到的是片区机构数据
        }
        paramMap.putIfAbsent("level", 1); //没有上面角色但是有这些菜单的人会报错 默认1
//        int level = (int) paramMap.get("level");
        paramMap.put("dateType", 1);
        List<IncomeAndCostDto> incomeAndCostDtos = surveyInvestigatorCaseMapper.selectIncomeAndCostList(paramMap);
        paramMap.put("dateType", 2);
        List<IncomeAndCostDto> momIncomeAndCostDtos = surveyInvestigatorCaseMapper.selectIncomeAndCostList(paramMap);
        double momInvTotal = 0d;
        double momSurTotal = 0d;
        for (IncomeAndCostDto incomeAndCostDto : incomeAndCostDtos) {
            paramMap.put("orgId",incomeAndCostDto.getParentId() == 0 ? incomeAndCostDto.getSurveyOrgId() : incomeAndCostDto.getParentId());
            paramMap.put("groupId",incomeAndCostDto.getGroupId());
            paramMap.put("areaId",incomeAndCostDto.getSurveyOrgId());
            Map<String, Double> map = reimbursementInfoMapper.selectAllMoneyByInfoIds(paramMap);
            incomeAndCostDto.setMedicalHistoryMoney(map != null ? map.get("medicalHistoryMoney") : 0d);
            incomeAndCostDto.setTroubleshootingMoney(map != null ? map.get("troubleshootingMoney") : 0d);
            incomeAndCostDto.setOpcTroubleshootingMoney(map != null ? map.get("opcTroubleshootingMoney") : 0d);
            incomeAndCostDto.setPrintingMoney(map != null ? map.get("printingMoney") : 0d);
            incomeAndCostDto.setCrossCityTransportationFees(map != null ? map.get("crossCityTransportationFees") : 0d);
            incomeAndCostDto.setOtherFee(map != null ? map.get("otherFee") : 0d);
            incomeAndCostDto.setAccommodatioMoney(map != null ? map.get("accommodatioMoney") : 0d);
            incomeAndCostDto.setCityTransportationSubsidies(map != null ? map.get("cityTransportationSubsidies") : 0d);
            //片区机构集合与环比片区
            List<IncomeAndCostDto> pcollect = incomeAndCostDtos.stream().filter(e -> e.getSurveyOrgId().equals(incomeAndCostDto.getSurveyOrgId())).collect(Collectors.toList());
            List<IncomeAndCostDto> mpcollect = momIncomeAndCostDtos.stream().filter(e -> e.getSurveyOrgId().equals(incomeAndCostDto.getSurveyOrgId())).collect(Collectors.toList());
            //保司确认收入 环比
            Double inv = pcollect.stream().mapToDouble(IncomeAndCostDto::getInsuranceCompanyConfirmsRevenue).sum();
            Double mominv = mpcollect.stream().mapToDouble(IncomeAndCostDto::getInsuranceCompanyConfirmsRevenue).sum();
            incomeAndCostDto.setInsuranceCompanyConfirmsRevenue(inv);
            incomeAndCostDto.setInsuranceCompanyConfirmsRevenueRate(rate(inv, mominv));
            momInvTotal += mominv;
            //调查确认收入 环比
            Double inv2 = pcollect.stream().mapToDouble(IncomeAndCostDto::getSurveyCompanyConfirmsRevenue).sum();
            Double mominv2 = mpcollect.stream().mapToDouble(IncomeAndCostDto::getSurveyCompanyConfirmsRevenue).sum();
            incomeAndCostDto.setSurveyCompanyConfirmsRevenue(inv2);
            incomeAndCostDto.setSurveyCompanyConfirmsRevenueRate(rate(inv2, mominv2));
            momSurTotal += mominv2;

            Double totalMoney = 0d;
            if (map != null) {
                for (Double value : map.values()) {
                    totalMoney += value;
                }
            }
            incomeAndCostDto.setTotalMoney(totalMoney);
            if (StringUtils.isNotBlank(incomeAndCostDto.getGroupId())) {
                incomeAndCostDto.setOrgType(1);
                incomeAndCostDto.setOrgTypeName("A类");
            } else {
                incomeAndCostDto.setOrgType(2);
                incomeAndCostDto.setOrgTypeName("B类");
            }
        }
        paramMap.put("list", incomeAndCostDtos);
        double invTotal = incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getInsuranceCompanyConfirmsRevenue).sum();
        double surTotal = incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getSurveyCompanyConfirmsRevenue).sum();
        paramMap.put("invTotal",invTotal);
        paramMap.put("invTotalRate",rate(invTotal, momInvTotal));
        paramMap.put("surTotal",surTotal);
        paramMap.put("surTotalRate",rate(surTotal, momSurTotal));
        paramMap.put("cityInnerTotalMoney",incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getCityTransportationSubsidies).sum());
        paramMap.put("medicalHistoryTotalMoney",incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getMedicalHistoryMoney).sum());
        paramMap.put("troubleshootingTotalMoney",incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getTroubleshootingMoney).sum());
        paramMap.put("opcTroubleshootingTotalMoney",incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getOpcTroubleshootingMoney).sum());
        paramMap.put("printingTotalMoney",incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getPrintingMoney).sum());
        paramMap.put("accommodatioTotalMoney",incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getAccommodatioMoney).sum());
        paramMap.put("crossCityTransportationTotalMoney",incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getCrossCityTransportationFees).sum());
        paramMap.put("otherTotalMoney",incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getOtherFee).sum());
        paramMap.put("finalTotalMoney",incomeAndCostDtos.stream().mapToDouble(IncomeAndCostDto::getTotalMoney).sum());
        return paramMap;
    }

    private Map getAssessmentIndexList(Map<String, Object> paramMap) {
        //机构负责人，仅看到本机构的数据
        String dataRoleCode = paramMap.get("dataRoleCode") != null ? paramMap.get("dataRoleCode").toString() : "";
        String dataRoleOrgId = paramMap.get("dataRoleOrgId") != null ? paramMap.get("dataRoleOrgId").toString() : "";
        if ("manger".equals(dataRoleCode) || "provincialManger".equals(dataRoleCode)) {
            paramMap.put("level", 1);//查询到的是省级机构数据
            if ("provincialManger".equals(dataRoleCode)) {
                paramMap.put("surveyOrgIds", paramMap.get("surveyOrgIds") == null ? dataRoleOrgId : paramMap.get("surveyOrgIds"));
                paramMap.put("orgId", dataRoleOrgId);
            }
        } else if ("districtManger".equals(dataRoleCode)) {
            paramMap.put("level", 1);//查询到的是片区机构数据
            paramMap.put("surveyOrgIds", paramMap.get("surveyOrgIds") == null ? dataRoleOrgId : paramMap.get("surveyOrgIds"));
            paramMap.put("orgId", dataRoleOrgId);
        }
        paramMap.put("isChild", false);
        boolean flag = paramMap.get("clickPq") == null;
        List<AssessmentIndexDto> orgList = surveyInvestigatorCaseMapper.selectStateNameByStatue(paramMap);
        //如果有调查员的话只查调查员所在的机构列表数据
        String userIdStr = paramMap.get("surveyInvestigators") != null ? paramMap.get("surveyInvestigators").toString() : "";
        List<AssessmentIndexDto> resultList = new ArrayList<>();
        paramMap.put("dateType", 1);
        List<AssessmentIndexDto> assessmentIndexDtoList = surveyInvestigatorCaseMapper.selectAssessMentIndex(paramMap);
        paramMap.put("dateType", 2);
        List<AssessmentIndexDto> huanbiIndexDtoList = surveyInvestigatorCaseMapper.selectAssessMentIndex(paramMap);
        paramMap.put("overTotal",0L);//超期总数
        paramMap.put("overSubmitTotal",0L);//提交总数(超期)
        paramMap.put("momCaseTotal",0L);//环比超期案子总数

        paramMap.put("rejectedTotal",0L);//驳回总数 (案件数)
        paramMap.put("rejectedSubmitTotal",0L);//提交总数(驳回)
        paramMap.put("momRejectedTotal",0L);//环比驳回案子总数

        paramMap.put("rejectedTotalForRm",0L);//驳回总数（按次数）
        paramMap.put("momRejectedTotalForRm",0L);//环比驳回案子总数

        paramMap.put("positiveTotal",0L);//阳性总数
        paramMap.put("positiveSubmitTotal",0L);//提交总数(阳性)
        paramMap.put("momPositiveTotal",0L);//环比案子总数
        DecimalFormat df = new DecimalFormat("#.##%");
        if (StringUtils.isNotBlank(userIdStr)) {
            List<AssessmentIndexDto> list = surveyInvestigatorMapper.selectAssePersonData(paramMap);
            for (AssessmentIndexDto ac : list) {
                getData(assessmentIndexDtoList, huanbiIndexDtoList, ac, paramMap, new ArrayList<Long>() {{
                    add(ac.getSurveyOrgId());
                }}, ac.getSurveyUserId(),false);
            }
            paramMap.put("list", list);
            paramMap.put("overTotalRate", df.format((Double.parseDouble(paramMap.get("overTotal").toString()) / (Double.parseDouble(paramMap.get("overSubmitTotal").toString()) > 0 ? Double.parseDouble(paramMap.get("overSubmitTotal").toString()) : 1d))));
            paramMap.put("rejectedTotaRate", df.format((Double.parseDouble(paramMap.get("rejectedTotal").toString()) / (Double.parseDouble(paramMap.get("rejectedSubmitTotal").toString()) > 0 ? Double.parseDouble(paramMap.get("rejectedSubmitTotal").toString()) : 1d))));
            paramMap.put("rejectedTotaRateForRm", df.format((Double.parseDouble(paramMap.get("rejectedTotalForRm").toString()) / (Double.parseDouble(paramMap.get("rejectedSubmitTotal").toString()) > 0 ? Double.parseDouble(paramMap.get("rejectedSubmitTotal").toString()) : 1d))));
            paramMap.put("positiveTotalRate", df.format((Double.parseDouble(paramMap.get("positiveTotal").toString()) / (Double.parseDouble(paramMap.get("positiveSubmitTotal").toString()) > 0 ? Double.parseDouble(paramMap.get("positiveSubmitTotal").toString()) : 1d))));
            paramMap.put("momOverTotalRate", df.format(rate(Double.valueOf(paramMap.get("overTotal").toString()), Double.valueOf(paramMap.get("momCaseTotal").toString())).doubleValue()));
            paramMap.put("momRejectedTotaRate", df.format(rate(Double.valueOf(paramMap.get("rejectedTotal").toString()), Double.valueOf(paramMap.get("momRejectedTotal").toString())).doubleValue()));
            paramMap.put("momRejectedTotaRateForRm", df.format(rate(Double.valueOf(paramMap.get("rejectedTotalForRm").toString()), Double.valueOf(paramMap.get("momRejectedTotalForRm").toString())).doubleValue()));
            paramMap.put("momPositiveTotalRate", df.format(rate(Double.valueOf(paramMap.get("positiveTotal").toString()), Double.valueOf(paramMap.get("momPositiveTotal").toString())).doubleValue()));
            return paramMap;
        }
        if (paramMap.get("clickPqSurv") == null) {
            for (AssessmentIndexDto assessmentIndexDto : orgList) {
                if (flag) {
                    if (StringUtils.isNotBlank(assessmentIndexDto.getAreaIds())) {
                        assessmentIndexDto.setOrgType(1);
                        assessmentIndexDto.setOrgTypeName("A类");
                    } else {//m没有片区
                        assessmentIndexDto.setOrgType(2);
                        assessmentIndexDto.setOrgTypeName("B类");
                    }
                    getData(assessmentIndexDtoList, huanbiIndexDtoList, assessmentIndexDto, paramMap, new ArrayList<Long>() {{add(assessmentIndexDto.getSurveyOrgId());}}, null,false);
                } else {
                    getData(assessmentIndexDtoList, huanbiIndexDtoList, assessmentIndexDto, paramMap, new ArrayList<Long>() {{ if (assessmentIndexDto.getParentId() == 0){
                        add(assessmentIndexDto.getSurveyOrgId());
                    }else {
                        add(assessmentIndexDto.getSurveyAreaId());
                    }
                    }}, null,true);
                }
                resultList.add(assessmentIndexDto);
            }

            paramMap.put("overTotalRate", df.format((Double.parseDouble(paramMap.get("overTotal").toString()) / (Double.parseDouble(paramMap.get("overSubmitTotal").toString()) > 0 ? Double.parseDouble(paramMap.get("overSubmitTotal").toString()) : 1d))));
            paramMap.put("rejectedTotaRate", df.format((Double.parseDouble(paramMap.get("rejectedTotal").toString()) / (Double.parseDouble(paramMap.get("rejectedSubmitTotal").toString()) > 0 ? Double.parseDouble(paramMap.get("rejectedSubmitTotal").toString()) : 1d))));
            paramMap.put("rejectedTotaRateForRm", df.format((Double.parseDouble(paramMap.get("rejectedTotalForRm").toString()) / (Double.parseDouble(paramMap.get("rejectedSubmitTotal").toString()) > 0 ? Double.parseDouble(paramMap.get("rejectedSubmitTotal").toString()) : 1d))));
            paramMap.put("positiveTotalRate", df.format((Double.parseDouble(paramMap.get("positiveTotal").toString()) / (Double.parseDouble(paramMap.get("positiveSubmitTotal").toString()) > 0 ? Double.parseDouble(paramMap.get("positiveSubmitTotal").toString()) : 1d))));
            paramMap.put("momOverTotalRate", df.format(rate(Double.valueOf(paramMap.get("overTotal").toString()), Double.valueOf(paramMap.get("momCaseTotal").toString())).doubleValue()));
            paramMap.put("momRejectedTotaRate", df.format(rate(Double.valueOf(paramMap.get("rejectedTotal").toString()), Double.valueOf(paramMap.get("momRejectedTotal").toString())).doubleValue()));
            paramMap.put("momRejectedTotaRateForRm", df.format(rate(Double.valueOf(paramMap.get("rejectedTotalForRm").toString()), Double.valueOf(paramMap.get("momRejectedTotalForRm").toString())).doubleValue()));
            paramMap.put("momPositiveTotalRate", df.format(rate(Double.valueOf(paramMap.get("positiveTotal").toString()), Double.valueOf(paramMap.get("momPositiveTotal").toString())).doubleValue()));

        } else {
            List<AssessmentIndexDto> list = surveyInvestigatorMapper.selectAssePersonData(paramMap);
            if (list != null && list.size() > 0) {
                for (AssessmentIndexDto ac : list) {
                    getData(assessmentIndexDtoList, huanbiIndexDtoList, ac, paramMap, new ArrayList<Long>() {{
                        add(ac.getSurveyOrgId());
                    }}, ac.getSurveyUserId(),false);
                    resultList.add(ac);
                }
            }
        }
//        if (paramMap.get("clickPqSurv") == null && flag) {
//            resultList = resultList.stream().filter(e -> flag ? e.getParentId() == 0 : e.getParentId().toString().equals(paramMap.get("surveyOrgIds").toString())).collect(Collectors.toList());
//        }
        paramMap.put("list", resultList);
        return paramMap;
    }

    public Map selectReportList(ApiRequest apiRequest) {
        Map map=new HashMap();
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
        //获取区级/县级ID
        String districtId=apiRequest.getString("districtId");
        map.put("districtId",districtId);
        String menuCode=apiRequest.getString("menuCode");
        map.put("menuCode",menuCode);
        List<SurveyCaseDirectionDto> list=surveyCaseDirectionMapper.selectReportList(map);
        //计算总和
        Double count = list.stream().collect(Collectors.summingDouble(SurveyCaseDirectionDto::getCount));
        for (SurveyCaseDirectionDto surveyCaseDirectionDto:list) {
            Double ratio = Double.parseDouble(surveyCaseDirectionDto.getCount().toString()) * 100 / count;
            DecimalFormat df = new DecimalFormat("#.00");
            surveyCaseDirectionDto.setProportion(Double.parseDouble(df.format(ratio)));
        }
        map=new HashMap();
        map.put("list",list);
        return map;
    }

    public Map seletTaskDistribution(ApiRequest apiReq) {
        String ads=apiReq.getString("region");
        Map parameterMap=new HashMap();
        if(ads != null){
            List<Map> listMap=new ArrayList<>();
            String [] address=ads.split(",");
            for (int i = 0; i < address.length; i++) {
                String [] b=address[i].split("_");
                Map findmap=new HashMap();
                findmap.put("areaType",b[0]);
                findmap.put("regionType",b[1]);
                listMap.add(findmap);
            }
            parameterMap.put("list",listMap);
        }
        parameterMap.put("platform",apiReq.getString("platform"));
        parameterMap.put("caseStatus",apiReq.getString("caseStatus"));
        parameterMap.put("region",apiReq.getString("region"));
        parameterMap.put("startTime",apiReq.getString("startTime")+" 00:00:00");
        parameterMap.put("endTime",apiReq.getString("endTime")+" 23:59:59");
        List<TaskDistribution> list=surveyTaskInfoMapper.seletTaskDistribution(parameterMap);
        //和
        double doublesum = list.stream().mapToDouble(TaskDistribution::getTaskNumber).sum();
        for (TaskDistribution taskDistribution:list) {
            if(taskDistribution.getTaskNumber()==0){
                taskDistribution.setProportion(0D);
            }else {
                taskDistribution.setProportion(taskDistribution.getTaskNumber() * 100 / doublesum);
                DecimalFormat df = new DecimalFormat("#.00");
                taskDistribution.setProportion(Double.parseDouble(df.format(taskDistribution.getProportion())));
            }
        }
        Map map=new HashMap();
        map.put("list",list);
        return map;
    }

    public Map selectInvestigatorReport(Long currentUserId,Map paramMap){
        Map map=new HashMap();
        if(currentUserId != null){
            paramMap.put("currentUserId",currentUserId);
            String currentTime=DateUtils.getCurrentTime();
            //当前时间
            paramMap.put("currentTime",currentTime);
            //当前时间的前30天
            String firstDays=DateUtils.getFirstDays(currentTime,-30);
            paramMap.put("firstDays",firstDays);
            //当前选择时间的数据
            InvestigatorReportDto investigatorReportDto=surveyInvestigatorCaseMapper.selectInvestigatorReport(paramMap);
            if(investigatorReportDto.getScore()>=250){
                investigatorReportDto.setTitleGrade("合格调查员");
            }else if(investigatorReportDto.getScore()>=150){
                investigatorReportDto.setTitleGrade("有效调查员");
            }else if(investigatorReportDto.getScore()>=50){
                investigatorReportDto.setTitleGrade("调查新人");
            }else if(investigatorReportDto.getScore()<50){
                investigatorReportDto.setTitleGrade("调查学员");
            }
            Map findMap=new HashMap();

            findMap.put("currentUserId",currentUserId);
//            String startTime=paramMap.get("momTime").toString();
//            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
//            Date times =new Date(startTime);
//            startTime = sdfs.format(times);
//            findMap.put("startTime",startTime+" 00:00:00");
//            String endTime=paramMap.get("startTime").toString();
//            Date time =new Date(endTime);
//            String timeFormat = sdfs.format(time);
//            findMap.put("endTime", DateUtils.getSpecifiedDayBefore(timeFormat)+" 23:59:59");

            findMap.put("startTime",paramMap.get("momTime"));
            findMap.put("endTime",paramMap.get("startTime"));
            //选择了时间之后的数据
            InvestigatorReportDto investigatorReportDtoTwo=surveyInvestigatorCaseMapper.selectInvestigatorReport(findMap);
            DecimalFormat df = new DecimalFormat("#.00");
            //计算承接案件数环比
            Double acceptedNumberChainRatio=rate(investigatorReportDto.getAcceptedNumber(),investigatorReportDtoTwo.getAcceptedNumber())*100;
            investigatorReportDto.setAcceptedNumberChainRatio(Double.parseDouble(df.format(acceptedNumberChainRatio)));

            //平台复审通过案件数环比
            Double platformReviewNumberChainRatio=rate(investigatorReportDto.getPlatformReviewNumber(),investigatorReportDtoTwo.getPlatformReviewNumber())*100;
            investigatorReportDto.setPlatformReviewNumberChainRatio(Double.parseDouble(df.format(platformReviewNumberChainRatio)));

            //保司终审通过件数环比
            Double insuranceCompanyNumberChainRatio=rate(investigatorReportDto.getInsuranceCompanyNumber(),investigatorReportDtoTwo.getInsuranceCompanyNumber())*100;
            investigatorReportDto.setInsuranceCompanyNumberChainRatio(Double.parseDouble(df.format(insuranceCompanyNumberChainRatio)));

            //平台终审通过任务积分环比
            Double platformTaskPoints=rate(investigatorReportDto.getPlatformTaskPoints(),investigatorReportDtoTwo.getPlatformTaskPoints())*100;
            investigatorReportDto.setPlatformTaskPointsChainRatio(Double.parseDouble(df.format(platformTaskPoints)));

            //保司终审通过任务积分环比
            Double missionPoints=rate(investigatorReportDto.getMissionPoints(),investigatorReportDtoTwo.getMissionPoints())*100;
            investigatorReportDto.setMissionPointsChainRatio(Double.parseDouble(df.format(missionPoints)));

            //超期率环比
            Double overdueCasesNumber=rate(investigatorReportDto.getOverdueCasesNumber(),investigatorReportDtoTwo.getOverdueCasesNumber())*100;
            investigatorReportDto.setOverdueRateChainRatio(Double.parseDouble(df.format(overdueCasesNumber)));

            //驳回率环比
            Double rejectedCasesNumber=rate(investigatorReportDto.getRejectedCasesNumber(),investigatorReportDtoTwo.getRejectedCasesNumber())*100;
            investigatorReportDto.setRejectionRateChainRatio(Double.parseDouble(df.format(rejectedCasesNumber)));

            //阳性率环比
            Double positiveCasesNumber=rate(investigatorReportDto.getPositiveCasesNumber(),investigatorReportDtoTwo.getPositiveCasesNumber())*100;
            investigatorReportDto.setPositiveRateChainRatio(Double.parseDouble(df.format(positiveCasesNumber)));

            //查得率环比
            Double searchRateRatio=rate(investigatorReportDto.getSearchRate(),investigatorReportDtoTwo.getSearchRate())*100;
            investigatorReportDto.setSearchRateRatio(Double.parseDouble(df.format(searchRateRatio)));

            //件均时效环比
            Double averageAgingRatio=rate(investigatorReportDto.getAverageAging(),investigatorReportDtoTwo.getAverageAging())*100;
            investigatorReportDto.setAverageAgingRatio(Double.parseDouble(df.format(averageAgingRatio)));

            map.put("list",investigatorReportDto);
            map.put("currentUserId",currentUserId);
        }
        return map;
    }

    /**
     *
     * @param assessmentIndexDtoList  案件
     * @param huanbiIndexDtoList  环比案件
     * @param assessmentIndexDto  案件dto
     * @param paramMap
     * @param collect    机构id list
     */
    public void getData(List<AssessmentIndexDto> assessmentIndexDtoList, List<AssessmentIndexDto> huanbiIndexDtoList, AssessmentIndexDto assessmentIndexDto, Map paramMap, List<Long> collect, Long surveyInvId,boolean isPq) {
        Date startTime = (Date) paramMap.get("startTime");
        Date endTime = (Date) paramMap.get("endTime");
        Date momTime = (Date) paramMap.get("momTime");

        assessmentIndexDtoList = assessmentIndexDtoList.stream().filter(dto -> {
            if (!isPq || dto.getParentId().equals(assessmentIndexDto.getParentId())) { //非片区或者片区里面查省级机构的
                if (dto.getParentId().equals(assessmentIndexDto.getParentId())) {
                    return collect.contains(dto.getSurveyOrgId()) && dto.getSurveyAreaId() == null;
                } else {
                    return collect.contains(dto.getSurveyOrgId());
                }
            } else {
                return dto.getSurveyAreaId() != null && collect.contains(dto.getSurveyAreaId());
            }
        }).filter(dto -> surveyInvId == null || dto.getSurveyUserId().equals(surveyInvId)).collect(Collectors.toList());
        huanbiIndexDtoList = huanbiIndexDtoList.stream().filter(dto -> {
            if (!isPq || dto.getParentId().equals(assessmentIndexDto.getParentId())) { //非片区或者片区里面查省级机构的
                if (dto.getParentId().equals(assessmentIndexDto.getParentId())) {
                    return collect.contains(dto.getSurveyOrgId()) && dto.getSurveyAreaId() == null;
                } else {
                    return collect.contains(dto.getSurveyOrgId());
                }
            } else {
                return dto.getSurveyAreaId() != null && collect.contains(dto.getSurveyAreaId());
            }
        }).filter(dto -> surveyInvId == null || dto.getSurveyUserId().equals(surveyInvId)).collect(Collectors.toList());

        //超期数 //省级机构，片区机构根据机构调查截至时间计算，调查员根据分派调查员时设置的截止日期判断。
        long orvedueNum = assessmentIndexDtoList.stream().filter(e -> (surveyInvId == null && !isPq)  ? (e.getOrgCreateTime() != null && e.getOrgCreateTime().compareTo(startTime) >= 0 && e.getOrgCreateTime().compareTo(endTime) <= 0) : (e.getAssignDate() != null && e.getAssignDate().compareTo(startTime) >= 0 && e.getAssignDate().compareTo(endTime) <= 0)).filter(dto -> (surveyInvId != null || isPq) ? (dto.getSurveyAgingDay() > 0) : (dto.getOrgAgingDay() > 0)).map(AssessmentIndexDto::getRiskId).distinct().count();
        long momOrvedueNum = huanbiIndexDtoList.stream().filter(e -> (surveyInvId == null && !isPq) ? (e.getOrgCreateTime() != null && e.getOrgCreateTime().compareTo(momTime) >= 0 && e.getOrgCreateTime().compareTo(startTime) < 0) : (e.getAssignDate() != null && e.getAssignDate().compareTo(momTime) >= 0 && e.getAssignDate().compareTo(startTime) < 0)).filter(dto -> ((surveyInvId != null || isPq) ? (dto.getSurveyAgingDay() > 0) : (dto.getOrgAgingDay() > 0))).map(AssessmentIndexDto::getRiskId).distinct().count();
        assessmentIndexDto.setOverdueNum((int) orvedueNum);
        long oveTotal = assessmentIndexDtoList.stream().filter(e -> (surveyInvId == null && !isPq) ? (e.getOrgCreateTime() != null && e.getOrgCreateTime().compareTo(startTime) >= 0 && e.getOrgCreateTime().compareTo(endTime) <= 0) : (e.getAssignDate() != null && e.getAssignDate().compareTo(startTime) >= 0 && e.getAssignDate().compareTo(endTime) <= 0)).map(AssessmentIndexDto::getRiskId).distinct().count();
        assessmentIndexDto.setOverdueRate(String.valueOf((double) orvedueNum / (oveTotal > 0 ? (double) oveTotal : 1)));
        assessmentIndexDto.setOverdueChain(String.valueOf(rate(Double.valueOf(orvedueNum), Double.valueOf(momOrvedueNum))));

        //驳回(按件数)
        long numberRejected; //驳回件数 //returnState>0
        long momNumRejected;//环比驳回件数
        numberRejected = assessmentIndexDtoList.stream().filter(dto -> (surveyInvId == null && !isPq) ? (dto.getReviewTime() != null && dto.getReviewTime().compareTo(startTime) >= 0 && dto.getReviewTime().compareTo(endTime) <= 0) : (dto.getcReportTime() != null && dto.getcReportTime().compareTo(startTime) >= 0 && dto.getcReportTime().compareTo(endTime) <= 0)).filter(dto -> (surveyInvId == null && !isPq) ? dto.getReturnState() > 0 : dto.getSurveyReturnState() > 0).map(AssessmentIndexDto::getRiskId).distinct().count();
        momNumRejected = huanbiIndexDtoList.stream().filter(dto -> (surveyInvId == null && !isPq) ? (dto.getReviewTime() != null && dto.getReviewTime().compareTo(momTime) >= 0 && dto.getReviewTime().compareTo(startTime) < 0) : (dto.getcReportTime() != null && dto.getcReportTime().compareTo(momTime) >= 0 && dto.getcReportTime().compareTo(startTime) < 0)).filter(dto -> (surveyInvId == null && !isPq) ? dto.getReturnState() > 0 : dto.getSurveyReturnState() > 0).map(AssessmentIndexDto::getRiskId).distinct().count();
        assessmentIndexDto.setRejectedNum((int) numberRejected);
        long rejectTotal = assessmentIndexDtoList.stream().filter(dto -> (surveyInvId == null && !isPq) ? (dto.getReviewTime() != null && dto.getReviewTime().compareTo(startTime) >= 0 && dto.getReviewTime().compareTo(endTime) <= 0) : (dto.getcReportTime() != null && dto.getcReportTime().compareTo(startTime) >= 0 && dto.getcReportTime().compareTo(endTime) <= 0)).map(AssessmentIndexDto::getRiskId).distinct().count();
        assessmentIndexDto.setRejectionRate(String.valueOf((double) numberRejected / (rejectTotal > 0 ? (double) rejectTotal : 1)));
        assessmentIndexDto.setDismissedQoQ(String.valueOf(rate(Double.valueOf(numberRejected), Double.valueOf(momNumRejected))));

        //驳回(按次数)
        long numberRejectedForRm; //驳回次数
        long momNumRejectedForRm;//环比驳回件数
        numberRejectedForRm = assessmentIndexDtoList.stream().filter(dto -> (surveyInvId == null && !isPq) ? (dto.getReviewTime() != null && dto.getReviewTime().compareTo(startTime) >= 0 && dto.getReviewTime().compareTo(endTime) <= 0) : (dto.getcReportTime() != null && dto.getcReportTime().compareTo(startTime) >= 0 && dto.getcReportTime().compareTo(endTime) <= 0)).filter(dto -> (surveyInvId == null && !isPq) ? dto.getReturnState() > 0 : dto.getSurveyReturnState() > 0).filter(distinctByKey(AssessmentIndexDto::getRiskId)).mapToLong(e->(surveyInvId == null && !isPq)?e.getReturnState():e.getSurveyReturnState()).sum();
        momNumRejectedForRm = huanbiIndexDtoList.stream().filter(dto -> (surveyInvId == null && !isPq) ? (dto.getReviewTime() != null && dto.getReviewTime().compareTo(momTime) >= 0 && dto.getReviewTime().compareTo(startTime) < 0) : (dto.getcReportTime() != null && dto.getcReportTime().compareTo(momTime) >= 0 && dto.getcReportTime().compareTo(startTime) < 0)).filter(dto -> (surveyInvId == null && !isPq) ? dto.getReturnState() > 0 : dto.getSurveyReturnState() > 0).filter(distinctByKey(AssessmentIndexDto::getRiskId)).mapToLong(e->(surveyInvId == null && !isPq)?e.getReturnState():e.getSurveyReturnState()).sum();
        assessmentIndexDto.setRejectedNumForRm((int) numberRejectedForRm);
        assessmentIndexDto.setRejectionRateForRm(String.valueOf((double) numberRejectedForRm / (rejectTotal > 0 ? (double) rejectTotal : 1)));
        assessmentIndexDto.setDismissedQoQForRm(String.valueOf(rate(Double.valueOf(numberRejectedForRm), Double.valueOf(momNumRejectedForRm))));

        long positiveNumber;//阳性案件数  复审通过 第一个标记阳性的调查员有效（有阳性分）
        long momPositiveNumber;//环比阳性案件数
        positiveNumber = assessmentIndexDtoList.stream().filter(dto -> dto.getReviewTime() != null && (dto.getReviewTime() != null && dto.getReviewTime().compareTo(startTime) >= 0 && dto.getReviewTime().compareTo(endTime) <= 0)).filter(dto -> (dto.getIsSun() == 1 && StringUtils.isNotBlank(dto.getScoreSun()) && Double.parseDouble(dto.getScoreSun()) > 0)).map(AssessmentIndexDto::getRiskId).distinct().count();
        momPositiveNumber = huanbiIndexDtoList.stream().filter(dto -> dto.getReviewTime() != null && (dto.getReviewTime() != null && dto.getReviewTime().compareTo(momTime) >= 0 && dto.getReviewTime().compareTo(startTime) < 0)).filter(dto -> (dto.getIsSun() == 1 && StringUtils.isNotBlank(dto.getScoreSun()) && Double.parseDouble(dto.getScoreSun()) > 0)).map(AssessmentIndexDto::getRiskId).distinct().count();
        assessmentIndexDto.setPositivepNum((int) positiveNumber);
        long positiveTotal = assessmentIndexDtoList.stream().filter(dto -> dto.getReviewTime() != null && (dto.getReviewTime().compareTo(startTime) >= 0 && dto.getReviewTime().compareTo(endTime) <= 0)).map(AssessmentIndexDto::getRiskId).distinct().count();
        assessmentIndexDto.setPositiveRate(String.valueOf((double) positiveNumber / (positiveTotal > 0 ? (double) positiveTotal : 1)));
        assessmentIndexDto.setPositiveRatio(String.valueOf(rate(Double.valueOf(positiveNumber), Double.valueOf(momPositiveNumber))));

        //计算合计
        if (Objects.isNull(paramMap.get("clickPqSurv"))) {
            paramMap.put("overTotal", (long) paramMap.get("overTotal") + orvedueNum);//超期总数
            paramMap.put("overSubmitTotal", (long) paramMap.get("overSubmitTotal") + oveTotal);//提交总数(超期)
            paramMap.put("momCaseTotal", (long) paramMap.get("momCaseTotal") + momOrvedueNum);//环比超期案子总数

            paramMap.put("rejectedTotal", (long) paramMap.get("rejectedTotal") + numberRejected);//驳回总数 按件数
            paramMap.put("rejectedSubmitTotal", (long) paramMap.get("rejectedSubmitTotal") + rejectTotal);//提交总数(驳回)
            paramMap.put("momRejectedTotal", (long) paramMap.get("momRejectedTotal") + momNumRejected);//环比驳回案子总数

            paramMap.put("rejectedTotalForRm", (long) paramMap.get("rejectedTotalForRm") + numberRejectedForRm);//驳回总数  按次数
            paramMap.put("momRejectedTotalForRm", (long) paramMap.get("momRejectedTotalForRm") + momNumRejectedForRm);//环比驳回案子总数

            paramMap.put("positiveTotal", (long) paramMap.get("positiveTotal") + positiveNumber);//阳性总数
            paramMap.put("positiveSubmitTotal", (long) paramMap.get("positiveSubmitTotal") + positiveTotal);//提交总数(阳性)
            paramMap.put("momPositiveTotal", (long) paramMap.get("momPositiveTotal") + momPositiveNumber);//环比案子总数
        }
    }

    //根据某个字段去重
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 调查员人力报表数据获取(菜单获取数据)
     * @param paramMap
     * @param map
     * @param currentUserId
     * @return
     */
    private Map getSurveyManpower(Map<String,Object> paramMap, Map map,Long currentUserId){
        Long orgId = null;
        String dataRoleCode = paramMap.get("dataRoleCode").toString();
        if ("areaManger".equals(dataRoleCode)){
            orgId = Long.parseLong(paramMap.get("dataRoleOrgId").toString());
        }
        return getSurveyManpower(paramMap,map,currentUserId,orgId);
    }


    @Autowired
    private SurveyOrgAreaMapper surveyOrgAreaMapper;
    /**
     * 获取片区数据
     * @param paramMap
     * @param map
     * @param currentUserId
     * @param orgId
     * @return
     */
    private Map getSurveyManpower(Map<String,Object> paramMap, Map map,Long currentUserId,Long orgId){
        List<SurveyManpowerDTO> list = null;
        String dataRoleCode = paramMap.get("dataRoleCode").toString();
        if (orgId == null && !"areaManger".equals(dataRoleCode)){
            //获取平台、省级
            paramMap.put("son",0);
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
            }
            list = surveyFranchiseeMapper.selectManPowerList(paramMap);//获取机构
        }else{//通过点击详情获取数据
            paramMap.put("son",1);
            paramMap.put("orgId",orgId);
            if ("areaManger".equals(dataRoleCode)){
                paramMap.remove("orgId");
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
                paramMap.put("surveyAreaId",paramMap.get("surveyAreaId"));
            }
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.remove("dataRoleOrgId");
                paramMap.remove("surveyAreaId");
            }
//            list = surveyFranchiseeMapper.selectManPowerList(paramMap);//获取片区
            Map<String,Object> paramAreaMap =  new HashMap<String,Object>();
            paramAreaMap.put("surveyOrgId",orgId);
            paramAreaMap.put("surveyAreaId",paramMap.get("surveyAreaId"));
            List<SurveyOrgArea> surveyOrgAreas = surveyOrgAreaMapper.selectByList(paramAreaMap);//片区列表
            list = new ArrayList<>();
            for (SurveyOrgArea surveyOrgArea : surveyOrgAreas) {
                SurveyManpowerDTO surveyManpowerDTO = new SurveyManpowerDTO();
                surveyManpowerDTO.setSurveyOrgId(Long.parseLong(surveyOrgArea.getId().toString()));
                surveyManpowerDTO.setSurveyOrgName(surveyOrgArea.getSurveyAreaName());
                surveyManpowerDTO.setOrgLevel(3);
                list.add(surveyManpowerDTO);
            }
            //本身
            SurveyManpowerDTO surveyManpowerDTO = new SurveyManpowerDTO();
            surveyManpowerDTO.setSurveyOrgId(orgId);
            surveyManpowerDTO.setSurveyOrgName(surveyFranchiseeMapper.selectByPrimaryKey(orgId).getName());
            surveyManpowerDTO.setOrgLevel(2);
            list.add(surveyManpowerDTO);
        }
        List<SurveyManPowerItemDTO> data = surveyInvestigatorMapper.selectManPowerData(paramMap);//获取数据

        Double totalScore1 = 0D,totalScore2 = 0D,totalScore3 = 0D,totalScore4 = 0D,totalScore5 = 0D,totalScore6 = 0D,totalScore7 = 0D;
        Double totalUpScore1 = 0D,totalUpScore2 = 0D,totalUpScore3 = 0D,totalUpScore4 = 0D,totalUpScore5 = 0D,totalUpScore6 = 0D,totalUpScore7 = 0D;
        for (SurveyManpowerDTO line : list) {
            //人数
            Long num1 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).count();//机构下的总人数
            Long num2 = data.stream().filter(e -> (e.getDeleteFlag() > 0 && e.getScore() < 50D && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//调查学员人数
            Long num3 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() >= 50D && e.getScore() <150 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//调查新人
            Long num4 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() >= 150D && e.getScore() <250 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//有效调查员
            Long num5 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() >= 250 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//合格调查员
            Long num6 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//活动调查员
            Long num7 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//沉默调查员
            line.setUserNum1(num1.intValue());
            line.setUserNum2(num2.intValue());
            line.setUserNum3(num3.intValue());
            line.setUserNum4(num4.intValue());
            line.setUserNum5(num5.intValue());
            line.setUserNum6(num6.intValue());
            line.setUserNum7(num7.intValue());

            Date momTime = (Date) paramMap.get("momTime");
            Date startTime = (Date) paramMap.get("startTime");

            //环比人数
            //环比的在编=  已离职 + 在职         已离职 = ( 离职时间 > 环比开始日期  && 入职时间 < 环比结束日期 )[ 离职日期要在环比日期之后  入职日期要在环比结束日期之前]
            num1 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0)  && e.getEntryTime().before(startTime) && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).count();//机构下的总人数
            num2 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() > 0) && e.getEntryTime().before(startTime) && e.getMomScore() < 50D && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//调查学员人数
            num3 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() >= 50D && e.getMomScore() <150 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//调查新人
            num4 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() >= 150D && e.getMomScore() <250 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//有效调查员
            num5 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() >= 250 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//合格调查员
            num6 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//合格调查员
            num7 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//合格调查员
            line.setUpUserNum1(num1.intValue());line.setNum1Rate(rate(line.getUserNum1(),line.getUpUserNum1()));
            line.setUpUserNum2(num2.intValue());line.setNum2Rate(rate(line.getUserNum2(),line.getUpUserNum2()));
            line.setUpUserNum3(num3.intValue());line.setNum3Rate(rate(line.getUserNum3(),line.getUpUserNum3()));
            line.setUpUserNum4(num4.intValue());line.setNum4Rate(rate(line.getUserNum4(),line.getUpUserNum4()));
            line.setUpUserNum5(num5.intValue());line.setNum5Rate(rate(line.getUserNum5(),line.getUpUserNum5()));
            line.setUpUserNum6(num6.intValue());line.setNum6Rate(rate(line.getUserNum6(),line.getUpUserNum6()));
            line.setUpUserNum7(num7.intValue());line.setNum7Rate(rate(line.getUserNum7(),line.getUpUserNum7()));

            //积分
            Double score1 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score2 = data.stream().filter(e -> (e.getDeleteFlag() > 0 && e.getScore() < 50D && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score3 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() >= 50D && e.getScore() <150  && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score4 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() >= 150D && e.getScore() <250 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score5 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() >= 250 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score6 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score7 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            line.setUserScore1(score1 / (line.getUserNum1() == 0 ? 1D : line.getUserNum1()));
            line.setUserScore2(score2 / (line.getUserNum2() == 0 ? 1D : line.getUserNum2()));
            line.setUserScore3(score3 / (line.getUserNum3() == 0 ? 1D : line.getUserNum3()));
            line.setUserScore4(score4 / (line.getUserNum4() == 0 ? 1D : line.getUserNum4()));
            line.setUserScore5(score5 / (line.getUserNum5() == 0 ? 1D : line.getUserNum5()));
            line.setUserScore6(score6 / (line.getUserNum6() == 0 ? 1D : line.getUserNum6()));
            line.setUserScore7(score7 / (line.getUserNum7() == 0 ? 1D : line.getUserNum7()));
            //总积分
            totalScore1 = totalScore1 + score1;
            totalScore2 = totalScore2 + score2;
            totalScore3 = totalScore3 + score3;
            totalScore4 = totalScore4 + score4;
            totalScore5 = totalScore5 + score5;
            totalScore6 = totalScore6 + score6;
            totalScore7 = totalScore7 + score7;

            //积分
            score1 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score2 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() > 0) && e.getEntryTime().before(startTime) && e.getMomScore() < 50D && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score3 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() >= 50D && e.getMomScore() <150  && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score4 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() >= 150D && e.getMomScore() <250 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score5 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() >= 250 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score6 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score7 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            line.setUpUserScore1(score1 / (line.getUpUserNum1() == 0 ? 1D : line.getUpUserNum1()));line.setScore1Rate(rate(line.getUserScore1(),line.getUpUserScore1()));
            line.setUpUserScore2(score2 / (line.getUpUserNum2() == 0 ? 1D : line.getUpUserNum2()));line.setScore2Rate(rate(line.getUserScore2(),line.getUpUserScore2()));
            line.setUpUserScore3(score3 / (line.getUpUserNum3() == 0 ? 1D : line.getUpUserNum3()));line.setScore3Rate(rate(line.getUserScore3(),line.getUpUserScore3()));
            line.setUpUserScore4(score4 / (line.getUpUserNum4() == 0 ? 1D : line.getUpUserNum4()));line.setScore4Rate(rate(line.getUserScore4(),line.getUpUserScore4()));
            line.setUpUserScore5(score5 / (line.getUpUserNum5() == 0 ? 1D : line.getUpUserNum5()));line.setScore5Rate(rate(line.getUserScore5(),line.getUpUserScore5()));
            line.setUpUserScore6(score6 / (line.getUpUserNum6() == 0 ? 1D : line.getUpUserNum6()));line.setScore6Rate(rate(line.getUserScore6(),line.getUpUserScore6()));
            line.setUpUserScore7(score7 / (line.getUpUserNum7() == 0 ? 1D : line.getUpUserNum7()));line.setScore7Rate(rate(line.getUserScore7(),line.getUpUserScore7()));
            //环比总积分
            totalUpScore1 = totalUpScore1 + score1;
            totalUpScore2 = totalUpScore2 + score2;
            totalUpScore3 = totalUpScore3 + score3;
            totalUpScore4 = totalUpScore4 + score4;
            totalUpScore5 = totalUpScore5 + score5;
            totalUpScore6 = totalUpScore6 + score6;
            totalUpScore7 = totalUpScore7 + score7;
        }

        //所有的DOUBLE 保留两位小数  计算合计
        SurveyManpowerDTO total = new SurveyManpowerDTO();
        total.setUserNum1(0);total.setUpUserNum1(0);
        total.setUserNum2(0);total.setUpUserNum2(0);
        total.setUserNum3(0);total.setUpUserNum3(0);
        total.setUserNum4(0);total.setUpUserNum4(0);
        total.setUserNum5(0);total.setUpUserNum5(0);
        total.setUserNum6(0);total.setUpUserNum6(0);
        total.setUserNum7(0);total.setUpUserNum7(0);
        total.setUserScore1(0D);total.setUpUserScore1(0D);
        total.setUserScore2(0D);total.setUpUserScore2(0D);
        total.setUserScore3(0D);total.setUpUserScore3(0D);
        total.setUserScore4(0D);total.setUpUserScore4(0D);
        total.setUserScore5(0D);total.setUpUserScore5(0D);
        total.setUserScore6(0D);total.setUpUserScore6(0D);
        total.setUserScore7(0D);total.setUpUserScore7(0D);
        for (SurveyManpowerDTO item : list) {//总人数（当前 以及 环比）
            total.setUserNum1(total.getUserNum1() + item.getUserNum1());total.setUpUserNum1(total.getUpUserNum1() + item.getUpUserNum1());
            total.setUserNum2(total.getUserNum2() + item.getUserNum2());total.setUpUserNum2(total.getUpUserNum2() + item.getUpUserNum2());
            total.setUserNum3(total.getUserNum3() + item.getUserNum3());total.setUpUserNum3(total.getUpUserNum3() + item.getUpUserNum3());
            total.setUserNum4(total.getUserNum4() + item.getUserNum4());total.setUpUserNum4(total.getUpUserNum4() + item.getUpUserNum4());
            total.setUserNum5(total.getUserNum5() + item.getUserNum5());total.setUpUserNum5(total.getUpUserNum5() + item.getUpUserNum5());
            total.setUserNum6(total.getUserNum6() + item.getUserNum6());total.setUpUserNum6(total.getUpUserNum6() + item.getUpUserNum6());
            total.setUserNum7(total.getUserNum7() + item.getUserNum7());total.setUpUserNum7(total.getUpUserNum7() + item.getUpUserNum7());

            Field[] declaredFields = item.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                try {
                    Object value = declaredField.get(item);
                    if (declaredField.getType().equals(Double.class)) {
                        if (value != null){
                            String declareName = declaredField.getName();
                            if ("num1Rate".equals(declareName) || "score1Rate".equals(declareName) || "num2Rate".equals(declareName) || "score2Rate".equals(declareName) || "num3Rate".equals(declareName) || "score3Rate".equals(declareName)
                                    || "num4Rate".equals(declareName) || "score4Rate".equals(declareName)|| "num5Rate".equals(declareName) || "score5Rate".equals(declareName)){
                                declaredField.set(item,DecimalUtil.fourDecimalTOFourFromFive(new Double(value.toString())));
                            }else{
                                declaredField.set(item,DecimalUtil.twoDecimalTOFourFromFive(new Double(value.toString())));
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //总积分平均值
        total.setUserScore1(totalScore1 / (total.getUserNum1() == 0D ? 1D : total.getUserNum1()));
        total.setUserScore2(totalScore2 / (total.getUserNum2() == 0D ? 1D : total.getUserNum2()));
        total.setUserScore3(totalScore3 / (total.getUserNum3() == 0D ? 1D : total.getUserNum3()));
        total.setUserScore4(totalScore4 / (total.getUserNum4() == 0D ? 1D : total.getUserNum4()));
        total.setUserScore5(totalScore5 / (total.getUserNum5() == 0D ? 1D : total.getUserNum5()));
        total.setUserScore6(totalScore6 / (total.getUserNum6() == 0D ? 1D : total.getUserNum6()));
        total.setUserScore7(totalScore7 / (total.getUserNum7() == 0D ? 1D : total.getUserNum7()));

        //环比总积分平均值
        total.setUpUserScore1(totalUpScore1 / (total.getUpUserNum1() == 0D ? 1D : total.getUpUserNum1()));
        total.setUpUserScore2(totalUpScore2 / (total.getUpUserNum2() == 0D ? 1D : total.getUpUserNum2()));
        total.setUpUserScore3(totalUpScore3 / (total.getUpUserNum3() == 0D ? 1D : total.getUpUserNum3()));
        total.setUpUserScore4(totalUpScore4 / (total.getUpUserNum4() == 0D ? 1D : total.getUpUserNum4()));
        total.setUpUserScore5(totalUpScore5 / (total.getUpUserNum5() == 0D ? 1D : total.getUpUserNum5()));
        total.setUpUserScore6(totalUpScore6 / (total.getUpUserNum6() == 0D ? 1D : total.getUpUserNum6()));
        total.setUpUserScore7(totalUpScore7 / (total.getUpUserNum7() == 0D ? 1D : total.getUpUserNum7()));

        //人数环比
        total.setNum1Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum1(),total.getUpUserNum1())));
        total.setNum2Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum2(),total.getUpUserNum2())));
        total.setNum3Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum3(),total.getUpUserNum3())));
        total.setNum4Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum4(),total.getUpUserNum4())));
        total.setNum5Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum5(),total.getUpUserNum5())));
        total.setNum6Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum6(),total.getUpUserNum6())));
        total.setNum7Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum7(),total.getUpUserNum7())));

        //积分环比
        total.setScore1Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore1(),total.getUpUserScore1())));
        total.setScore2Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore2(),total.getUpUserScore2())));
        total.setScore3Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore3(),total.getUpUserScore3())));
        total.setScore4Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore4(),total.getUpUserScore4())));
        total.setScore5Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore5(),total.getUpUserScore5())));
        total.setScore6Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore6(),total.getUpUserScore6())));
        total.setScore7Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore7(),total.getUpUserScore7())));
        map.put("list",list);
        map.put("total",total);
        return map;
    }


    private Map getSurveyManpower(Map<String,Object> paramMap, Map map,Long currentUserId,Long orgId,int son){
        paramMap.put("orgId",orgId);
        paramMap.put("son",son);
        List<SurveyManPowerItemDTO> list = surveyInvestigatorMapper.selectManPowerData(paramMap);
        for (SurveyManPowerItemDTO item : list) {
            if (item.getScore() > 0 && item.getScore() < 50){
                item.setSurveyType(1);
                item.setSurveyTypeName("调查学员");
            }else if (item.getScore() >= 50 && item.getScore() < 150){
                item.setSurveyType(2);
                item.setSurveyTypeName("调查新人");
            }else if (item.getScore() >= 150 && item.getScore() < 250){
                item.setSurveyType(3);
                item.setSurveyTypeName("有效调查员");
            }else if (item.getScore() >= 250){
                item.setSurveyType(4);
                item.setSurveyTypeName("合格调查员");
            }else if (item.getScore() > 0){
                item.setSurveyType(5);
                item.setSurveyTypeName("活动调查员");
            }else if (item.getScore() == 0){
                item.setSurveyType(6);
                item.setSurveyTypeName("沉默调查员");
            }
            item.setScoreRate(DecimalUtil.twoDecimalTOFourFromFive(rate(item.getScore(),item.getMomScore())));
        }
        map.put("list",list);
        return map;
    }


    /**
     * 调查员人力报表数据获取(菜单获取数据)
     * @param paramMap
     * @param map
     * @param currentUserId
     * @return
     */
    private Map getSurveyManpowerBs(Map<String,Object> paramMap, Map map,Long currentUserId){
        Long orgId = null;
        String dataRoleCode = paramMap.get("dataRoleCode").toString();
        if ("areaManger".equals(dataRoleCode)){
            orgId = Long.parseLong(paramMap.get("dataRoleOrgId").toString());
        }
        return getSurveyManpowerBs(paramMap,map,currentUserId,orgId);
    }

    /**
     * 获取片区数据
     * @param paramMap
     * @param map
     * @param currentUserId
     * @param orgId
     * @return
     */
    private Map getSurveyManpowerBs(Map<String,Object> paramMap, Map map,Long currentUserId,Long orgId){
        List<SurveyManpowerDTO> list = null;
        String dataRoleCode = paramMap.get("dataRoleCode").toString();
        if (orgId == null && !"areaManger".equals(dataRoleCode)){
            //获取平台、省级
            paramMap.put("son",0);
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
            }
            list = surveyFranchiseeMapper.selectManPowerList(paramMap);
        }else{//通过点击详情获取数据
            paramMap.put("son",1);
            paramMap.put("orgId",orgId);
            if ("areaManger".equals(dataRoleCode)){
                paramMap.remove("orgId");
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
                paramMap.put("surveyAreaId",paramMap.get("surveyAreaId"));
            }
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.remove("dataRoleOrgId");
                paramMap.put("surveyAreaId",paramMap.get("surveyAreaId"));
            }
//            list = surveyFranchiseeMapper.selectManPowerList(paramMap);
            Map<String,Object> paramAreaMap =  new HashMap<String,Object>();
            paramAreaMap.put("surveyOrgId",orgId);
            paramAreaMap.put("surveyAreaId",paramMap.get("surveyAreaId"));
            List<SurveyOrgArea> surveyOrgAreas = surveyOrgAreaMapper.selectByList(paramAreaMap);//片区列表
            list = new ArrayList<>();
            for (SurveyOrgArea surveyOrgArea : surveyOrgAreas) {
                SurveyManpowerDTO surveyManpowerDTO = new SurveyManpowerDTO();
                surveyManpowerDTO.setSurveyOrgId(Long.parseLong(surveyOrgArea.getId().toString()));
                surveyManpowerDTO.setSurveyOrgName(surveyOrgArea.getSurveyAreaName());
                surveyManpowerDTO.setOrgLevel(3);
                list.add(surveyManpowerDTO);
            }
            //本身
            SurveyManpowerDTO surveyManpowerDTO = new SurveyManpowerDTO();
            surveyManpowerDTO.setSurveyOrgId(orgId);
            surveyManpowerDTO.setSurveyOrgName(surveyFranchiseeMapper.selectByPrimaryKey(orgId).getName());
            surveyManpowerDTO.setOrgLevel(2);
            list.add(surveyManpowerDTO);
        }
        List<SurveyManPowerItemDTO> data = surveyInvestigatorMapper.selectManPowerDataBs(paramMap);


        Double totalScore1 = 0D,totalScore2 = 0D,totalScore3 = 0D,totalScore4 = 0D,totalScore5 = 0D;
        Double totalUpScore1 = 0D,totalUpScore2 = 0D,totalUpScore3 = 0D,totalUpScore4 = 0D,totalUpScore5 = 0D;
        Double totalCaseNum1 = 0D,totalCaseNum2 = 0D,totalCaseNum3 = 0D,totalCaseNum4 = 0D,totalCaseNum5 = 0D;
        Double totalUpCaseNum1 = 0D,totalUpCaseNum2 = 0D,totalUpCaseNum3 = 0D,totalUpCaseNum4 = 0D,totalUpCaseNum5 = 0D;
        for (SurveyManpowerDTO line : list) {
            //人数
            Long num1 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).count();//机构下的总人数 在编
            Long num2 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//活动调查员
            Long num3 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() >= 70 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//正式调查员
            Long num4 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() > 0 && e.getScore() <70 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//试用期调查员
            Long num5 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//沉默调查员
            line.setUserNum1(num1.intValue());
            line.setUserNum2(num2.intValue());
            line.setUserNum3(num3.intValue());
            line.setUserNum4(num4.intValue());
            line.setUserNum5(num5.intValue());

            Date momTime = (Date) paramMap.get("momTime");
            Date startTime = (Date) paramMap.get("startTime");

            //环比人数
            //环比的在编=  已离职 + 在职         已离职 = ( 离职时间 > 环比开始日期  && 入职时间 < 环比结束日期 )[ 离职日期要在环比日期之后  入职日期要在环比结束日期之前]
            num1 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0)  && e.getEntryTime().before(startTime) && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).count();//机构下的总人数
            num2 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//合格调查员
            num3 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() >= 70 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//合格调查员
            num4 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() > 0 && e.getMomScore() <70 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//有效调查员
            num5 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).count();//合格调查员

            line.setUpUserNum1(num1.intValue());line.setNum1Rate(rate(line.getUserNum1(),line.getUpUserNum1()));
            line.setUpUserNum2(num2.intValue());line.setNum2Rate(rate(line.getUserNum2(),line.getUpUserNum2()));
            line.setUpUserNum3(num3.intValue());line.setNum3Rate(rate(line.getUserNum3(),line.getUpUserNum3()));
            line.setUpUserNum4(num4.intValue());line.setNum4Rate(rate(line.getUserNum4(),line.getUpUserNum4()));
            line.setUpUserNum5(num5.intValue());line.setNum5Rate(rate(line.getUserNum5(),line.getUpUserNum5()));

            //环比积分
            Double score1 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score2 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score3 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() >= 70 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score4 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() > 0 && e.getScore() < 70  && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));
            Double score5 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getScore));

            line.setUserScore1(score1 / (line.getUserNum1() == 0 ? 1D : line.getUserNum1()));
            line.setUserScore2(score2 / (line.getUserNum2() == 0 ? 1D : line.getUserNum2()));
            line.setUserScore3(score3 / (line.getUserNum3() == 0 ? 1D : line.getUserNum3()));
            line.setUserScore4(score4 / (line.getUserNum4() == 0 ? 1D : line.getUserNum4()));
            line.setUserScore5(score5 / (line.getUserNum5() == 0 ? 1D : line.getUserNum5()));
            //总积分
            totalScore1 = totalScore1 + score1;
            totalScore2 = totalScore2 + score2;
            totalScore3 = totalScore3 + score3;
            totalScore4 = totalScore4 + score4;
            totalScore5 = totalScore5 + score5;

            //环比积分
            score1 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score2 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score3 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() >= 70 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score4 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() > 0 && e.getMomScore() < 70  && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));
            score5 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomScore));

            line.setUpUserScore1(score1 / (line.getUpUserNum1() == 0 ? 1D : line.getUpUserNum1()));line.setScore1Rate(rate(line.getUserScore1(),line.getUpUserScore1()));
            line.setUpUserScore2(score2 / (line.getUpUserNum2() == 0 ? 1D : line.getUpUserNum2()));line.setScore2Rate(rate(line.getUserScore2(),line.getUpUserScore2()));
            line.setUpUserScore3(score3 / (line.getUpUserNum3() == 0 ? 1D : line.getUpUserNum3()));line.setScore3Rate(rate(line.getUserScore3(),line.getUpUserScore3()));
            line.setUpUserScore4(score4 / (line.getUpUserNum4() == 0 ? 1D : line.getUpUserNum4()));line.setScore4Rate(rate(line.getUserScore4(),line.getUpUserScore4()));
            line.setUpUserScore5(score5 / (line.getUpUserNum5() == 0 ? 1D : line.getUpUserNum5()));line.setScore5Rate(rate(line.getUserScore5(),line.getUpUserScore5()));
            //环比总积分
            totalUpScore1 = totalUpScore1 + score1;
            totalUpScore2 = totalUpScore2 + score2;
            totalUpScore3 = totalUpScore3 + score3;
            totalUpScore4 = totalUpScore4 + score4;
            totalUpScore5 = totalUpScore5 + score5;


            //人均案件数
            Double caseNum1 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getCaseNum));
            Double caseNum2 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getCaseNum));
            Double caseNum3 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() >= 70 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getCaseNum));
            Double caseNum4 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() > 0 && e.getScore() < 70  && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getCaseNum));
            Double caseNum5 = data.stream().filter(e -> (e.getDeleteFlag() == 0 && e.getScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getCaseNum));

            line.setUserCaseNum1(caseNum1 / (line.getUserNum1() == 0 ? 1D : line.getUserNum1()));
            line.setUserCaseNum2(caseNum2 / (line.getUserNum2() == 0 ? 1D : line.getUserNum2()));
            line.setUserCaseNum3(caseNum3 / (line.getUserNum3() == 0 ? 1D : line.getUserNum3()));
            line.setUserCaseNum4(caseNum4 / (line.getUserNum4() == 0 ? 1D : line.getUserNum4()));
            line.setUserCaseNum5(caseNum5 / (line.getUserNum5() == 0 ? 1D : line.getUserNum5()));
            totalCaseNum1 = totalCaseNum1 + caseNum1;
            totalCaseNum2 = totalCaseNum2 + caseNum2;
            totalCaseNum3 = totalCaseNum3 + caseNum3;
            totalCaseNum4 = totalCaseNum4 + caseNum4;
            totalCaseNum5 = totalCaseNum5 + caseNum5;


            //环比案件数
            caseNum1 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue()))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomCaseNum));
            caseNum2 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() > 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomCaseNum));
            caseNum3 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() >= 70 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomCaseNum));
            caseNum4 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() > 0 && e.getMomScore() < 70  && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomCaseNum));
            caseNum5 = data.stream().filter(e -> ((e.getQuitTime() != null ? e.getQuitTime().after(momTime) : e.getDeleteFlag() == 0) && e.getEntryTime().before(startTime) && e.getMomScore() == 0 && (line.getSurveyOrgId().intValue() == (orgId == null ? e.getOrgId().intValue() : e.getSonOrgId().intValue())))).collect(Collectors.summingDouble(SurveyManPowerItemDTO::getMomCaseNum));

            line.setUpUserCaseNum1(caseNum1 / (line.getUpUserNum1() == 0 ? 1D : line.getUpUserNum1()));line.setUserCaseNum1Rate(rate(line.getUserCaseNum1(),line.getUpUserCaseNum1()));
            line.setUpUserCaseNum2(caseNum2 / (line.getUpUserNum2() == 0 ? 1D : line.getUpUserNum2()));line.setUserCaseNum2Rate(rate(line.getUserCaseNum2(),line.getUpUserCaseNum2()));
            line.setUpUserCaseNum3(caseNum3 / (line.getUpUserNum3() == 0 ? 1D : line.getUpUserNum3()));line.setUserCaseNum3Rate(rate(line.getUserCaseNum3(),line.getUpUserCaseNum3()));
            line.setUpUserCaseNum4(caseNum4 / (line.getUpUserNum4() == 0 ? 1D : line.getUpUserNum4()));line.setUserCaseNum4Rate(rate(line.getUserCaseNum4(),line.getUpUserCaseNum4()));
            line.setUpUserCaseNum5(caseNum5 / (line.getUpUserNum5() == 0 ? 1D : line.getUpUserNum5()));line.setUserCaseNum5Rate(rate(line.getUserCaseNum5(),line.getUpUserCaseNum4()));
            totalUpCaseNum1 = totalUpCaseNum1 + caseNum1;
            totalUpCaseNum2 = totalUpCaseNum2 + caseNum2;
            totalUpCaseNum3 = totalUpCaseNum3 + caseNum3;
            totalUpCaseNum4 = totalUpCaseNum4 + caseNum4;
            totalUpCaseNum5 = totalUpCaseNum5 + caseNum5;

        }

        //所有的DOUBLE 保留两位小数  计算合计
        SurveyManpowerDTO total = new SurveyManpowerDTO();
        total.setUserNum1(0);total.setUpUserNum1(0);
        total.setUserNum2(0);total.setUpUserNum2(0);
        total.setUserNum3(0);total.setUpUserNum3(0);
        total.setUserNum4(0);total.setUpUserNum4(0);
        total.setUserNum5(0);total.setUpUserNum5(0);
        total.setUserNum6(0);total.setUpUserNum6(0);
        total.setUserNum7(0);total.setUpUserNum7(0);
        total.setUserScore1(0D);total.setUpUserScore1(0D);
        total.setUserScore2(0D);total.setUpUserScore2(0D);
        total.setUserScore3(0D);total.setUpUserScore3(0D);
        total.setUserScore4(0D);total.setUpUserScore4(0D);
        total.setUserScore5(0D);total.setUpUserScore5(0D);
        total.setUserScore6(0D);total.setUpUserScore6(0D);
        total.setUserScore7(0D);total.setUpUserScore7(0D);

        total.setUserCaseNum1(0D);total.setUpUserCaseNum1(0D);
        total.setUserCaseNum2(0D);total.setUpUserCaseNum2(0D);
        total.setUserCaseNum3(0D);total.setUpUserCaseNum3(0D);
        total.setUserCaseNum4(0D);total.setUpUserCaseNum4(0D);
        total.setUserCaseNum5(0D);total.setUpUserCaseNum5(0D);
        for (SurveyManpowerDTO item : list) {//总人数（当前 以及 环比）
            total.setUserNum1(total.getUserNum1() + item.getUserNum1());total.setUpUserNum1(total.getUpUserNum1() + item.getUpUserNum1());
            total.setUserNum2(total.getUserNum2() + item.getUserNum2());total.setUpUserNum2(total.getUpUserNum2() + item.getUpUserNum2());
            total.setUserNum3(total.getUserNum3() + item.getUserNum3());total.setUpUserNum3(total.getUpUserNum3() + item.getUpUserNum3());
            total.setUserNum4(total.getUserNum4() + item.getUserNum4());total.setUpUserNum4(total.getUpUserNum4() + item.getUpUserNum4());
            total.setUserNum5(total.getUserNum5() + item.getUserNum5());total.setUpUserNum5(total.getUpUserNum5() + item.getUpUserNum5());

            total.setUserCaseNum1(total.getUserCaseNum1() + item.getUserCaseNum1());total.setUpUserCaseNum1(total.getUpUserCaseNum1() + item.getUpUserCaseNum1());
            total.setUserCaseNum2(total.getUserCaseNum2() + item.getUserCaseNum2());total.setUpUserCaseNum2(total.getUpUserCaseNum2() + item.getUpUserCaseNum2());
            total.setUserCaseNum3(total.getUserCaseNum3() + item.getUserCaseNum3());total.setUpUserCaseNum3(total.getUpUserCaseNum3() + item.getUpUserCaseNum3());
            total.setUserCaseNum4(total.getUserCaseNum4() + item.getUserCaseNum4());total.setUpUserCaseNum4(total.getUpUserCaseNum4() + item.getUpUserCaseNum4());
            total.setUserCaseNum5(total.getUserCaseNum5() + item.getUserCaseNum4());total.setUpUserCaseNum5(total.getUpUserCaseNum5() + item.getUpUserCaseNum5());


            Field[] declaredFields = item.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                try {
                    Object value = declaredField.get(item);
                    if (declaredField.getType().equals(Double.class)) {
                        if (value != null){
                            String declareName = declaredField.getName();
                            //关于比例字段保留四位小数
                            if ("num1Rate".equals(declareName) || "score1Rate".equals(declareName) || "num2Rate".equals(declareName) || "score2Rate".equals(declareName) || "num3Rate".equals(declareName) || "score3Rate".equals(declareName)
                                    || "num4Rate".equals(declareName) || "score4Rate".equals(declareName)|| "num5Rate".equals(declareName)
                                    || "score5Rate".equals(declareName) || "userCaseNum1Rate".equals(declareName) || "userCaseNum2Rate".equals(declareName) || "userCaseNum3Rate".equals(declareName)
                                    || "userCaseNum4Rate".equals(declareName) || "userCaseNum5Rate".equals(declareName)){
                                declaredField.set(item,DecimalUtil.fourDecimalTOFourFromFive(new Double(value.toString())));
                            }else{
                                declaredField.set(item,DecimalUtil.twoDecimalTOFourFromFive(new Double(value.toString())));
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //总积分平均值
        total.setUserScore1(totalScore1 / (total.getUserNum1() == 0D ? 1D : total.getUserNum1()));
        total.setUserScore2(totalScore2 / (total.getUserNum2() == 0D ? 1D : total.getUserNum2()));
        total.setUserScore3(totalScore3 / (total.getUserNum3() == 0D ? 1D : total.getUserNum3()));
        total.setUserScore4(totalScore4 / (total.getUserNum4() == 0D ? 1D : total.getUserNum4()));
        total.setUserScore5(totalScore5 / (total.getUserNum5() == 0D ? 1D : total.getUserNum5()));

        total.setUserCaseNum1(totalCaseNum1 / (total.getUserCaseNum1() == 0D ? 1D : total.getUserCaseNum1()));//总案件数平均值
        total.setUserCaseNum2(totalCaseNum2 / (total.getUserCaseNum2() == 0D ? 1D : total.getUserCaseNum2()));
        total.setUserCaseNum3(totalCaseNum3 / (total.getUserCaseNum3() == 0D ? 1D : total.getUserCaseNum3()));
        total.setUserCaseNum4(totalCaseNum4 / (total.getUserCaseNum4() == 0D ? 1D : total.getUserCaseNum4()));
        total.setUserCaseNum5(totalCaseNum5 / (total.getUserCaseNum5() == 0D ? 1D : total.getUserCaseNum5()));
        //环比总积分平均值
        total.setUpUserScore1(totalUpScore1 / (total.getUpUserNum1() == 0D ? 1D : total.getUpUserNum1()));
        total.setUpUserScore2(totalUpScore2 / (total.getUpUserNum2() == 0D ? 1D : total.getUpUserNum2()));
        total.setUpUserScore3(totalUpScore3 / (total.getUpUserNum3() == 0D ? 1D : total.getUpUserNum3()));
        total.setUpUserScore4(totalUpScore4 / (total.getUpUserNum4() == 0D ? 1D : total.getUpUserNum4()));
        total.setUpUserScore5(totalUpScore5 / (total.getUpUserNum5() == 0D ? 1D : total.getUpUserNum5()));

        total.setUpUserCaseNum1(totalUpCaseNum1 / (total.getUpUserCaseNum1() == 0D ? 1D : total.getUpUserCaseNum1()));//总环比案件数平均值
        total.setUpUserCaseNum2(totalUpCaseNum2 / (total.getUpUserCaseNum2() == 0D ? 1D : total.getUpUserCaseNum2()));
        total.setUpUserCaseNum3(totalUpCaseNum3 / (total.getUpUserCaseNum3() == 0D ? 1D : total.getUpUserCaseNum3()));
        total.setUpUserCaseNum4(totalUpCaseNum4 / (total.getUpUserCaseNum4() == 0D ? 1D : total.getUpUserCaseNum4()));
        total.setUpUserCaseNum5(totalUpCaseNum5 / (total.getUpUserCaseNum5() == 0D ? 1D : total.getUpUserCaseNum5()));

        //人数环比
        total.setNum1Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum1(),total.getUpUserNum1())));
        total.setNum2Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum2(),total.getUpUserNum2())));
        total.setNum3Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum3(),total.getUpUserNum3())));
        total.setNum4Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum4(),total.getUpUserNum4())));
        total.setNum5Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserNum5(),total.getUpUserNum5())));

        //积分环比
        total.setScore1Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore1(),total.getUpUserScore1())));
        total.setScore2Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore2(),total.getUpUserScore2())));
        total.setScore3Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore3(),total.getUpUserScore3())));
        total.setScore4Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore4(),total.getUpUserScore4())));
        total.setScore5Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserScore5(),total.getUpUserScore5())));

        total.setUserCaseNum1Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserCaseNum1(),total.getUpUserCaseNum1()))); //案件数平均值环比
        total.setUserCaseNum2Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserCaseNum2(),total.getUpUserCaseNum2())));
        total.setUserCaseNum3Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserCaseNum3(),total.getUpUserCaseNum3())));
        total.setUserCaseNum4Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserCaseNum4(),total.getUpUserCaseNum4())));
        total.setUserCaseNum5Rate(DecimalUtil.twoDecimalTOFourFromFive(rate(total.getUserCaseNum5(),total.getUpUserCaseNum5())));

        map.put("list",list);
        map.put("total",total);
        return map;
    }


    private Map getSurveyManpowerBs(Map<String,Object> paramMap, Map map,Long currentUserId,Long orgId,int son){
        paramMap.put("orgId",orgId);
        paramMap.put("son",son);
        List<SurveyManPowerItemDTO> list = surveyInvestigatorMapper.selectManPowerDataBs(paramMap);
        for (SurveyManPowerItemDTO item : list) {
            if (item.getScore() > 0 && item.getScore() < 70){
                item.setSurveyType(1);
                item.setSurveyTypeName("试用期调查员");
            }else if (item.getScore() >= 70){
                item.setSurveyType(4);
                item.setSurveyTypeName("正式调查员");
            }else if (item.getScore() > 0){
                item.setSurveyType(5);
                item.setSurveyTypeName("活动调查员");
            }else if (item.getScore() == 0){
                item.setSurveyType(6);
                item.setSurveyTypeName("沉默调查员");
            }
            item.setScoreRate(DecimalUtil.twoDecimalTOFourFromFive(rate(item.getScore(),item.getMomScore())));
        }
        map.put("list",list);
        return map;
    }


    private Double rate(Double a,Double b){
        if (a == 0){
            return b == 0 ? 0 : -b;
        }
        if (b == 0){
            return a == 0 ? 0 : a;
        }
        return new BigDecimal((float)(a - b) / b).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private Double rate(int a,int b){
        return rate(new Double(String.valueOf(a)),new Double(String.valueOf(b)));
    }



    @ApiMethod(needLogin = false,descript = "获取互助 保司 报表的案件数据",value = "get-survey-hz-report-case")
    @Override
    public ApiResponse getHZCaseList(ApiRequest apiRequest) {

        String menuCode = apiRequest.getString("menuCode");
        Map map = new HashMap();
        List list = new ArrayList<>();
        if("score".equals(menuCode) || "scoreBs".equals(menuCode) ){
            map = getScoreCaseList(map,apiRequest);
            list = (List<ScoreDto>) map.get("list");
        }else if("progressTrack".equals(menuCode)){
            map = getProgressTrackCaseList(map,apiRequest);
            list = (List<ProgressTrackDto>) map.get("list");
        }else if("progressTrackBs".equals(menuCode)){
            map = getProgressTrackCaseList(map,apiRequest);
            list = (List<ProgressTrackDto>) map.get("list");
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,(Integer)map.get("count"),list);
    }



    public Map getScoreCaseList(Map map, ApiRequest apiRequest) {
        String caseCode = apiRequest.getString("caseCode");

        String entrustOrgIds =  apiRequest.getString("entrustOrgIds");
        String entrustOrgName = "";
        if(entrustOrgIds !=null){
            String[] entrustOrgId = entrustOrgIds.split(",");
            for (String id : entrustOrgId) {
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(id));
                if(surveyConsignor != null){
                    entrustOrgName = entrustOrgName + surveyConsignor.getName() + " ";
                }
            }
            apiRequest.put("entrustOrgName",entrustOrgName);
        }else{
            apiRequest.put("entrustOrgName","全部");
        }

        setBackendPageSize(apiRequest);
        List<ScoreDto> list = new ArrayList<>();
        int count = 0;
        apiRequest.put("caseType",apiRequest.get("caseType"));//案件类型：1: 渠道案件
        if("user".equals(caseCode)){
            apiRequest.put("mySurveyOrgId",apiRequest.get("surveyOrgId"));
            apiRequest.put("surveyOrgId",null);
            apiRequest.put("orgAttr",apiRequest.get("orgAttr"));
            list = surveyInvestigatorCaseMapper.getScoreCaseListByInvestigator(apiRequest);
            count = surveyInvestigatorCaseMapper.getScoreCaseListSizeByInvestigator(apiRequest);

            for (ScoreDto scoreDto : list) {
                //根据调查员案件Id关联调查员任务列表
                scoreDto.setTasks(surveyInvestigatorCaseTypeMapper.getSurveyInvestigatorCaseTypesByCaseId(scoreDto.getInvestigatorCaseId()));
            }

        }else if("org".equals(caseCode)){
            apiRequest.put("thisSurveyOrgId",apiRequest.get("surveyOrgId"));
            apiRequest.put("surveyOrgId",null);
            list = surveyInvestigatorCaseMapper.getScoreCaseListByOrg(apiRequest);
            count = surveyInvestigatorCaseMapper.getScoreCaseListSizeByOrg(apiRequest);

            for (ScoreDto scoreDto : list) {
                //案件的任务类型
                scoreDto.setSurveyTaskTypes(surveyTaskTypeMapper.getSurveyTaskTypeBySurveyInfoId(scoreDto.getSurveyInfoId()));
            }
        }

        map.put("list",list);
        map.put("count",count);
        return map;
    }

    private Map getProgressTrackList(Map<String, Object> paramMap, Map map, Long currentUserId) {
        Long orgId = null;
        String dataRoleCode = paramMap.get("dataRoleCode").toString();
        if ("areaManger".equals(dataRoleCode)){
            orgId = Long.parseLong(paramMap.get("dataRoleOrgId").toString());
        }
        String surveyInvestigators =  (String)paramMap.get("surveyInvestigators");
        if(surveyInvestigators != null){
            //调查员数据
            return getProgressTrackList(paramMap, map, currentUserId, null,0);
        }else {
            return getProgressTrackList(paramMap, map, currentUserId, orgId);
        }
    }

    private Map getProgressTrackList(Map<String, Object> paramMap, Map map, Long currentUserId, Long orgId) {

        String dataRoleCode =paramMap.get("dataRoleCode")!=null? paramMap.get("dataRoleCode").toString():"";
        List<ProgressTrackDto> list = new ArrayList<>();
        if (orgId == null && !"areaManger".equals(dataRoleCode)){
            //获取平台、省级
            paramMap.put("son",0);
            paramMap.put("level", 1);
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
            }
            list = surveyInvestigatorCaseMapper.getProgressTrackBySurveyOrg(paramMap);
        }else{//通过点击详情获取数据
            paramMap.put("level", 2);
            paramMap.put("son",1);
            paramMap.put("orgId",orgId);
            if ("areaManger".equals(dataRoleCode)){
                paramMap.put("level", 2);
                paramMap.remove("orgId");
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
            }
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.remove("dataRoleOrgId");
            }
//            list = surveyInvestigatorCaseMapper.getProgressTrackBySurveyOrg(paramMap);

            Map<String,Object> paramAreaMap =  new HashMap<String,Object>();
            paramAreaMap.put("surveyOrgId",orgId);
            paramAreaMap.put("surveyAreaId",paramMap.get("surveyAreaId"));
            List<SurveyOrgArea> surveyOrgAreas = surveyOrgAreaMapper.selectByList(paramAreaMap);//片区列表
            list = new ArrayList<>();
            for (SurveyOrgArea surveyOrgArea : surveyOrgAreas) {
                ProgressTrackDto progressTrackDto = new ProgressTrackDto();
                progressTrackDto.setSurveyOrgId(Long.parseLong(surveyOrgArea.getId().toString()));
                progressTrackDto.setSurveyOrgName(surveyOrgArea.getSurveyAreaName());
                progressTrackDto.setOrgLevel(3);
                progressTrackDto.setOrgType(1);
                list.add(progressTrackDto);
            }
            //本身
            ProgressTrackDto progressTrackDto = new ProgressTrackDto();
            progressTrackDto.setSurveyOrgId(orgId);
            progressTrackDto.setSurveyOrgName(surveyFranchiseeMapper.selectByPrimaryKey(orgId).getName());
            progressTrackDto.setOrgLevel(2);
            progressTrackDto.setOrgType(2);
            list.add(progressTrackDto);

            List<ProgressTrackDto> data = surveyInvestigatorCaseMapper.getProgressTrackBySurveyOrgArea(paramMap);
            for (ProgressTrackDto line : list) {
                for (ProgressTrackDto dto : data) {
                    if(line.getSurveyOrgId() == dto.getSurveyOrgId()){
                        line.setEntrustNum(dto.getEntrustNum());
                        line.setUpEntrustNum(dto.getUpEntrustNum());
                        line.setReviewNum(dto.getReviewNum());
                        line.setUpReviewNum(dto.getUpReviewNum());
                        line.setZtNum(dto.getZtNum());

                        line.setJjcqNum(dto.getJjcqNum());
                        line.setCqdhfNum(dto.getCqdhfNum());
                        line.setYzcqNum(dto.getYzcqNum());
                        line.setYxNum(dto.getYxNum());

                        line.setSurveyStateName(dto.getSurveyStateName());
                        line.setCaseStateName(dto.getCaseStateName());
                        line.setOrgCaseStateName(dto.getOrgCaseStateName());
                        line.setEntrustOrgName(dto.getEntrustOrgName());
                        line.setSurveyState(dto.getSurveyState());
                        line.setCaseState(dto.getCaseState());
                        line.setOrgCaseState(dto.getOrgCaseState());
                        line.setEntrustOrgIds(dto.getEntrustOrgIds());
                    }
                }
            }
        }

        //合计：方法1、传合计；2、传环比量
        ProgressTrackDto totle = new ProgressTrackDto();
        totle.setEntrustNum(0);     //委托量
        totle.setUpEntrustNum(0);   //环比委托量
        totle.setReviewNum(0);      //复审量
        totle.setUpReviewNum(0);    //环比复审量
        totle.setZtNum(0);          //在途案件数
        totle.setJjcqNum(0);        //即将超期案件数
        totle.setCqdhfNum(0);       //超期待回复
        totle.setYzcqNum(0);        //严重超期数
        totle.setYxNum(0);          //阳性案件数


        for (ProgressTrackDto progressTrackDto : list) {
            progressTrackDto.setEntrustRate(rate(progressTrackDto.getEntrustNum(), progressTrackDto.getUpEntrustNum()));
            progressTrackDto.setReviewRate(rate(progressTrackDto.getReviewNum(),progressTrackDto.getUpReviewNum()));

            totle.setEntrustNum(totle.getEntrustNum() + progressTrackDto.getEntrustNum());
            totle.setUpEntrustNum(totle.getUpEntrustNum() + progressTrackDto.getUpEntrustNum());
            totle.setReviewNum(totle.getReviewNum() + progressTrackDto.getReviewNum());
            totle.setUpReviewNum(totle.getUpReviewNum() + progressTrackDto.getUpReviewNum());
            totle.setZtNum(totle.getZtNum() + progressTrackDto.getZtNum());
            totle.setJjcqNum(totle.getJjcqNum() + progressTrackDto.getJjcqNum());
            totle.setCqdhfNum(totle.getCqdhfNum() + progressTrackDto.getCqdhfNum());
            totle.setYzcqNum(totle.getYzcqNum() + progressTrackDto.getYzcqNum());
            totle.setYxNum(totle.getYxNum() + progressTrackDto.getYxNum());
        }
        totle.setEntrustRate(rate(totle.getEntrustNum(), totle.getUpEntrustNum()));//委托量环比
        totle.setReviewRate(rate(totle.getReviewNum(), totle.getUpReviewNum()));//复审通过量环比

        map.put("list",list);
        map.put("totle",totle);
        return map;
    }

    private Map getProgressTrackList(Map<String, Object> paramMap, Map map, Long currentUserId, Long orgId, int trackSon) {

        paramMap.remove("surveyOrgIds");
        paramMap.put("orgId", orgId);
        paramMap.put("son", trackSon);//片区 1，省级2
        List<ProgressTrackDto> list = surveyInvestigatorCaseMapper.getProgressTrackListByInvestigator(paramMap);
        //合计：方法1、传合计；2、传环比量
        ProgressTrackDto totle = new ProgressTrackDto();
        totle.setEntrustNum(0);     //委托量
        totle.setUpEntrustNum(0);   //环比委托量
        totle.setReviewNum(0);      //复审量
        totle.setUpReviewNum(0);    //环比复审量
        totle.setZtNum(0);          //在途案件数
        totle.setJjcqNum(0);        //即将超期案件数
        totle.setCqdhfNum(0);       //超期待回复
        totle.setYzcqNum(0);        //严重超期数
        totle.setYxNum(0);          //阳性案件数
        for (ProgressTrackDto progressTrackDto : list) {
            progressTrackDto.setEntrustRate(DecimalUtil.twoDecimalTOFourFromFive(rate(progressTrackDto.getEntrustNum(), progressTrackDto.getUpEntrustNum())));
            progressTrackDto.setReviewRate(DecimalUtil.twoDecimalTOFourFromFive(rate(progressTrackDto.getReviewNum(),progressTrackDto.getUpReviewNum())));

            totle.setEntrustNum(totle.getEntrustNum() + progressTrackDto.getEntrustNum());
            totle.setUpEntrustNum(totle.getUpEntrustNum() + progressTrackDto.getUpEntrustNum());
            totle.setReviewNum(totle.getReviewNum() + progressTrackDto.getReviewNum());
            totle.setUpReviewNum(totle.getUpReviewNum() + progressTrackDto.getUpReviewNum());
            totle.setZtNum(totle.getZtNum() + progressTrackDto.getZtNum());
            totle.setJjcqNum(totle.getJjcqNum() + progressTrackDto.getJjcqNum());
            totle.setCqdhfNum(totle.getCqdhfNum() + progressTrackDto.getCqdhfNum());
            totle.setYzcqNum(totle.getYzcqNum() + progressTrackDto.getYzcqNum());
            totle.setYxNum(totle.getYxNum() + progressTrackDto.getYxNum());
        }
        totle.setEntrustRate(rate(totle.getEntrustNum(), totle.getUpEntrustNum()));//委托量环比
        totle.setReviewRate(rate(totle.getReviewNum(), totle.getUpReviewNum()));//复审通过量环比
        map.put("totle",totle);
        map.put("list",list);
        return map;
    }


    public Map getProgressTrackCaseList(Map map, ApiRequest apiRequest) {
        String caseCode = apiRequest.getString("caseCode");
        String menuCode = apiRequest.getString("menuCode");
        if (StringUtils.isNotBlank(menuCode)){
            apiRequest.put("orgAttr",2);
            if ("progressTrack".equals(menuCode)){
                apiRequest.put("orgAttr",2);
            }else if ("progressTrackBs".equals(menuCode)){
                apiRequest.put("orgAttr",1);
            }
        }

        setBackendPageSize(apiRequest);
        List<ProgressTrackDto> list = new ArrayList<>();
        int count = 0;
        if("user".equals(caseCode)){
            apiRequest.put("userss","true");
            list = surveyInvestigatorCaseMapper.getProgressTrackCaseListByInvestigator(apiRequest);
            count = surveyInvestigatorCaseMapper.getProgressTrackCaseListSizeByInvestigator(apiRequest);

            for (ProgressTrackDto progressTrackDto : list) {
                //根据调查员案件Id关联调查员任 务列表
                progressTrackDto.setInvestigatorCaseTypes(surveyInvestigatorCaseTypeMapper.getSurveyInvestigatorCaseTypesByCaseId(progressTrackDto.getInvestigatorCaseId()));
            }

        }else if("org".equals(caseCode)){
            apiRequest.put("thisSurveyOrgId",apiRequest.get("surveyOrgId"));
            apiRequest.put("surveyOrgId",null);
            list = surveyInvestigatorCaseMapper.getProgressTrackCaseListByOrg(apiRequest);
            count = surveyInvestigatorCaseMapper.getProgressTrackCaseSizeListByOrg(apiRequest);

            for (ProgressTrackDto progressTrackDto : list) {
                //案件的任务类型
                Map map1 = new HashMap<>();
                map1.put("surveyAssignOrgId", progressTrackDto.getAssignCaseId());
                List<SurveyAssignOrgType> orgTypes = surveyAssignOrgTypeMapper.list(map1);
                progressTrackDto.setSurveyAssignOrgTypes(orgTypes);
            }
        }

        map.put("list",list);
        map.put("count",count);
        return map;
    }

    @ApiMethod(needLogin = false,descript = "获取积分报表的案件数据",value = "get-data-survey-hz-report-role")
    @Override
    public ApiResponse getDataRole(ApiRequest apiRequest) {
        Map<String,Object> map = new HashMap<>();
        Long currentUserId = getCurrentUserId(apiRequest);
        map = getDataRole(currentUserId,apiRequest.getString("menuCode"));
        if (map != null){
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
        }
        return new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
    }

    private Map<String,Object> getDataRole(Long currentUserId,String menuCode){
        Map<String,Object> map =  new HashMap<String,Object>();
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgManger = isRoleUser(userRoles,104L),
                lfManger1 = isRoleUser(userRoles,95L),
                lfManger2 = isRoleUser(userRoles,75L),
                lfManger3 = isRoleUser(userRoles,96L),
                lfManger4 = isRoleUser(userRoles,91L),
                lfManger5 = isRoleUser(userRoles,121L),
                lfManger6 = isRoleUser(userRoles,53L),
                investigators = isRoleUser(userRoles,50L), areaManger = isRoleUser(userRoles,116L);
        SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
        if (lfManger1 || lfManger2 || lfManger3 || lfManger4 || lfManger5 || lfManger6){
            map.put("dataRoleCode","manger");
            return map;
        }
        if (orgManger){
            if (investigator != null){
                SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(investigator.getOrgId());
                if (franchisee != null){
                    if (franchisee.getLevel() == 1){
                        map.put("dataRoleCode","provincialManger");// 省级数据
                    }else if (franchisee.getLevel() == 2){
                        map.put("dataRoleCode","areaManger");//片区数据
                    }
                    map.put("dataRoleOrgId",investigator.getOrgId());
                    map.put("surveyAreaId",investigator.getSurveyAreaId());
                    return map;
                }
            }
        }
        if(areaManger){
            map.put("dataRoleCode","districtManger");//片区负责人
            map.put("dataRoleOrgId",investigator.getOrgId());
            map.put("surveyAreaId",investigator.getSurveyAreaId());
            return map;
        }
        if(investigators){
            if (investigator != null && "investigatorReport".equals(menuCode)) {
                map.put("dataRoleUserId", investigator.getUserId());
                map.put("dataRoleOrgId", investigator.getOrgId());
                map.put("surveyAreaId",investigator.getSurveyAreaId());
            }
            map.put("dataRoleCode","investigators");
            return map;
        }
        return map;
    }

    //角色判断
    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().intValue() == roleId.intValue()){
                return true;
            }
        }
        return false;
    }

    public void convertTimeBySearchType(StringBuilder startTime,StringBuilder endTime,String searchType,int year,int keyValue){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar= Calendar.getInstance();
        String start = "";
        String end = "";
        if ("upMonth".equals(searchType)) {
            calendar.setTime(new Date());
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            start = simpleDateFormat.format(calendar.getTime());

            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            end = simpleDateFormat.format(calendar.getTime());
        } else if ("yesterday".equals(searchType)){
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -1);
            start = simpleDateFormat.format(calendar.getTime());
            end = start;
        }else if ("today".equals(searchType)){
            calendar.setTime(new Date());
            start = simpleDateFormat.format(calendar.getTime());
            end = start;
        }else if ("curWeek".equals(searchType)){
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            start = simpleDateFormat.format(calendar.getTime());

            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            end = simpleDateFormat.format(calendar.getTime());
        }else if ("curMonth".equals(searchType)){
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            start = simpleDateFormat.format(calendar.getTime());

            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            end = simpleDateFormat.format(calendar.getTime());
        }else if ("month".equals(searchType)){//月度
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,keyValue - 1);
            int first = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH,first);
            start = simpleDateFormat.format(calendar.getTime());

            int last = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH,last);
            end = simpleDateFormat.format(calendar.getTime());

        }else if ("quarter".equals(searchType)){//季度
            if(keyValue == 1){
                calendar.set(year, 0, 1, 00, 00, 00);
                start = simpleDateFormat.format(calendar.getTime());
                calendar.set(year, 2, 31, 23, 59, 59);
                end = simpleDateFormat.format(calendar.getTime());
            }else if(keyValue == 2){
                calendar.set(year, 3, 1, 00, 00, 00);
                start = simpleDateFormat.format(calendar.getTime());
                calendar.set(year, 5, 30, 23, 59, 59);
                end = simpleDateFormat.format(calendar.getTime());
            }
            else if(keyValue == 3){
                calendar.set(year, 6, 1, 00, 00, 00);
                start = simpleDateFormat.format(calendar.getTime());
                calendar.set(year, 8, 30, 23, 59, 59);
                end = simpleDateFormat.format(calendar.getTime());
            }
            else if(keyValue == 4){
                calendar.set(year, 9, 1, 00, 00, 00);
                start = simpleDateFormat.format(calendar.getTime());
                calendar.set(year, 11, 31, 23, 59, 59);
                end = simpleDateFormat.format(calendar.getTime());
            }
        }else if ("last30Days".equals(searchType)){//最近30天
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -31);
            start = simpleDateFormat.format(calendar.getTime());

            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,-1);
            end = simpleDateFormat.format(calendar.getTime());
        }else if ("7days".equals(searchType)){
            start = LocalDate.now().plusDays(-7).toString();
            end = LocalDate.now().plusDays(-1).toString();
        }else if ("curQuarter".equals(searchType)){
            LocalDate now = LocalDate.now();
            int currentMonth = now.getMonth().getValue();
            int month = 1;
            if (currentMonth >= 1 && currentMonth <= 3) month = 1;
            else if (currentMonth >= 4 && currentMonth <= 6) month = 4;
            else if (currentMonth >= 7 && currentMonth <= 9) month = 7;
            else if (currentMonth >= 10 && currentMonth <= 12) month = 10;
            start = LocalDate.of(now.getYear(), month, 1).toString();
            end = now.toString();
        }else if ("curYear".equals(searchType)){
            start = LocalDate.now().getYear()+"-01-01";
            end = LocalDate.now().toString();
        }

        try {
            if (!"".equals(end)){
                if (simpleDateFormat.parse(end).after(new Date())) {
                    end = simpleDateFormat.format(new Date());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        startTime = startTime.delete(0,startTime.length()).append(start);
        endTime = endTime.delete(0,endTime.length()).append(end);
    }

    public void convertTimeBySearchType(StringBuilder startTime,StringBuilder endTime,String searchType){
        convertTimeBySearchType(startTime,endTime,searchType,-1,-1);
    }


    @ApiMethod(needLogin = false,descript = "获取考核指标报表的案件数据",value = "get-survey-hz-report-assessmentIndex-case")
    @Override
    public ApiResponse getAssessmentIndexCaseList(ApiRequest apiRequest) {
        setBackendPageSize(apiRequest);
//        String str = surveyFranchiseeMapper.selectChildrens2(apiRequest.getLong("surveyOrgId"),apiRequest.getLong("num"));
//        if (StringUtils.isNotBlank(str)){
//            apiRequest.put("surveyOrgId",str);
//        }
        if (StringUtils.isNotBlank(apiRequest.getString("surveyUserId"))){
            apiRequest.put("caseCode","user");
        }
        List<AssessmentIndexDto> list = surveyInvestigatorCaseMapper.selectAssessMentList(apiRequest);
        int count = surveyInvestigatorCaseMapper.selectAssessMentListSize(apiRequest);

        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }


    private Map getCaseDirectionList(Map<String, Object> paramMap, Map map, Long currentUserId) {
        Long orgId = null;
        String dataRoleCode = paramMap.get("dataRoleCode").toString();
        if ("areaManger".equals(dataRoleCode)){
            orgId = Long.parseLong(paramMap.get("dataRoleOrgId").toString());
        }
        String surveyInvestigators =  (String)paramMap.get("surveyInvestigators");
        if(surveyInvestigators != null){
            //调查员数据
            return getCaseDirectionList(paramMap, map, currentUserId, null,0L, "user");
        }else {
            return getCaseDirectionList(paramMap, map, currentUserId, orgId);
        }
    }

    private Map getCaseDirectionList(Map<String, Object> paramMap, Map map, Long currentUserId, Long orgId) {

        String entrustOrgIds =  (String)paramMap.get("entrustOrgIds");
        String entrustOrgName = "";
        if(entrustOrgIds !=null){
            String[] entrustOrgId = entrustOrgIds.split(",");
            for (String id : entrustOrgId) {
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(id));
                if(surveyConsignor != null){
                    entrustOrgName = entrustOrgName + surveyConsignor.getName() + " ";
                }
            }
            paramMap.put("entrustOrgName",entrustOrgName);
        }else{
            paramMap.put("entrustOrgName","全部");
        }

        String dataRoleCode =paramMap.get("dataRoleCode")!=null? paramMap.get("dataRoleCode").toString():"";
        List<CaseDirectionDto> list = new ArrayList<>();
        if (orgId == null && !"areaManger".equals(dataRoleCode)){
            //获取平台、省级
            paramMap.put("son",0);
            paramMap.put("level", 1);
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
            }
            list = surveyCaseDirectionMapper.getCaseDirectionBySurveyOrg(paramMap);
        }else{//通过点击详情获取数据
            paramMap.put("level", 2);
            paramMap.put("son",1);
            paramMap.put("orgId",orgId);
            if ("areaManger".equals(dataRoleCode)){
                paramMap.put("level", 2);
                paramMap.remove("orgId");
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
                paramMap.put("surveyAreaId",paramMap.get("surveyAreaId"));
            }
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.remove("dataRoleOrgId");
                paramMap.remove("surveyAreaId");
            }
//            list = surveyCaseDirectionMapper.getCaseDirectionBySurveyOrg(paramMap);

            //获取片区列表
            Map<String,Object> paramAreaMap =  new HashMap<String,Object>();
            paramAreaMap.put("surveyOrgId",orgId);
            paramAreaMap.put("surveyAreaId",paramMap.get("surveyAreaId"));
            List<SurveyOrgArea> surveyOrgAreas = surveyOrgAreaMapper.selectByList(paramAreaMap);//片区列表
            list = new ArrayList<>();
            for (SurveyOrgArea surveyOrgArea : surveyOrgAreas) {
                CaseDirectionDto caseDirectionDto = new CaseDirectionDto();
                caseDirectionDto.setSurveyOrgId(Long.parseLong(surveyOrgArea.getId().toString()));
                caseDirectionDto.setSurveyOrgName(surveyOrgArea.getSurveyAreaName());
                caseDirectionDto.setOrgLevel(3);
                list.add(caseDirectionDto);
            }
            //本身
            CaseDirectionDto caseDirectionDto = new CaseDirectionDto();
            caseDirectionDto.setSurveyOrgId(orgId);
            caseDirectionDto.setSurveyOrgName(surveyFranchiseeMapper.selectByPrimaryKey(orgId).getName());
            caseDirectionDto.setOrgLevel(2);
            list.add(caseDirectionDto);

            //片区数据
            List<CaseDirectionDto> caseDirectionBySurveyArea = surveyCaseDirectionMapper.getCaseDirectionBySurveyArea(paramMap);
            for (CaseDirectionDto directionDto : list) {
                for (CaseDirectionDto dto : caseDirectionBySurveyArea) {
                    directionDto.setEntrustOrgName(dto.getEntrustOrgName());
                    if (directionDto.getSurveyOrgId().intValue() == dto.getSurveyOrgId().intValue()) {
                        directionDto.setFileNum(dto.getFileNum());
                        directionDto.setHaveFileNum(dto.getHaveFileNum());
                        directionDto.setSoundNum(dto.getSoundNum());
                        directionDto.setHaveSoundNum(dto.getHaveSoundNum());
                    }
                }
            }
        }
        CaseDirectionDto totle = new CaseDirectionDto();
        totle.setFileNum(0);
        totle.setHaveFileNum(0);
        totle.setSoundNum(0);
        totle.setHaveSoundNum(0);

        for (CaseDirectionDto caseDirectionDto : list) {
            if (caseDirectionDto.getFileNum() == null){
                caseDirectionDto.setFileNum(0);
            }
            if (caseDirectionDto.getHaveFileNum() == null){
                caseDirectionDto.setHaveFileNum(0);
            }
            if (caseDirectionDto.getSoundNum() == null){
                caseDirectionDto.setSoundNum(0);
            }
            if (caseDirectionDto.getHaveSoundNum() == null){
                caseDirectionDto.setHaveSoundNum(0);
            }

            totle.setFileNum(totle.getFileNum() + caseDirectionDto.getFileNum());
            totle.setHaveFileNum(totle.getHaveFileNum() + caseDirectionDto.getHaveFileNum());
            totle.setSoundNum(totle.getSoundNum() + caseDirectionDto.getSoundNum());
            totle.setHaveSoundNum(totle.getHaveSoundNum() + caseDirectionDto.getHaveSoundNum());

            caseDirectionDto.setHaveFileRate(caseDirectionRate(caseDirectionDto.getHaveFileNum(),caseDirectionDto.getFileNum()));
            caseDirectionDto.setHaveSoundRate(caseDirectionRate(caseDirectionDto.getHaveSoundNum(),caseDirectionDto.getSoundNum()));
        }
        totle.setHaveFileRate(caseDirectionRate(totle.getHaveFileNum(),totle.getFileNum()));
        totle.setHaveSoundRate(caseDirectionRate(totle.getHaveSoundNum(),totle.getSoundNum()));

        map.put("totle",totle);
        map.put("list",list);
        return map;
    }

    private Map getCaseDirectionList(Map<String, Object> paramMap, Map map, Long currentUserId, Long orgId, Long trackSon, String searchType) {

        String entrustOrgIds =  (String)paramMap.get("entrustOrgIds");
        String entrustOrgName = "";
        if(entrustOrgIds !=null){
            String[] entrustOrgId = entrustOrgIds.split(",");
            for (String id : entrustOrgId) {
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(id));
                if(surveyConsignor != null){
                    entrustOrgName = entrustOrgName + surveyConsignor.getName() + " ";
                }
            }
            paramMap.put("entrustOrgName",entrustOrgName);
        }else{
            paramMap.put("entrustOrgName","全部");
        }

        paramMap.remove("surveyOrgIds");
        paramMap.put("orgId", orgId);
        paramMap.put("son", trackSon);
        List<CaseDirectionDto> list = new ArrayList<>();
        if("user".equals(searchType)){
            list = surveyCaseDirectionMapper.getCaseDirectionByInvestigator(paramMap);//条件查询
        }else if("org".equals(searchType)){
            list = surveyCaseDirectionMapper.getInvestigatorBySurveyOrg(paramMap);//机构下的人员数据
        }

        CaseDirectionDto totle = new CaseDirectionDto();
        totle.setFileNum(0);
        totle.setHaveFileNum(0);
        totle.setSoundNum(0);
        totle.setHaveSoundNum(0);

        for (CaseDirectionDto caseDirectionDto : list) {
            totle.setFileNum(totle.getFileNum() + caseDirectionDto.getFileNum());
            totle.setHaveFileNum(totle.getHaveFileNum() + caseDirectionDto.getHaveFileNum());
            totle.setSoundNum(totle.getSoundNum() + caseDirectionDto.getSoundNum());
            totle.setHaveSoundNum(totle.getHaveSoundNum() + caseDirectionDto.getHaveSoundNum());

            caseDirectionDto.setHaveFileRate(caseDirectionRate(caseDirectionDto.getHaveFileNum(),caseDirectionDto.getFileNum()));
            caseDirectionDto.setHaveSoundRate(caseDirectionRate(caseDirectionDto.getHaveSoundNum(),caseDirectionDto.getSoundNum()));
        }
        totle.setHaveFileRate(caseDirectionRate(totle.getHaveFileNum(),totle.getFileNum()));
        totle.setHaveSoundRate(caseDirectionRate(totle.getHaveSoundNum(),totle.getSoundNum()));

        map.put("totle",totle);


        map.put("list",list);
        return map;
    }

    private Double caseDirectionRate(Double a,Double b){
        if (b == 0){
            b = 1D;
        }
        return new BigDecimal(a / b).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private Double caseDirectionRate(int a,int b){
        return caseDirectionRate(new Double(String.valueOf(a)), new Double(String.valueOf(b)));
    }


    private Map getScoreList(Map<String, Object> paramMap, Map map, Long currentUserId) {
        Long orgId = null;
        String dataRoleCode = paramMap.get("dataRoleCode").toString();
        if ("areaManger".equals(dataRoleCode)){
            orgId = Long.parseLong(paramMap.get("dataRoleOrgId").toString());
        }
        String surveyInvestigators =  (String)paramMap.get("surveyInvestigators");
        if(surveyInvestigators != null){
            //调查员数据
            return getScoreList(paramMap, map, currentUserId, null,0, "user");
        }else {
            return getScoreList(paramMap, map, currentUserId, orgId);
        }
    }

    private Map getScoreList(Map<String, Object> paramMap, Map map, Long currentUserId, Long orgId) {

        String entrustOrgIds =  (String)paramMap.get("entrustOrgIds");
        String entrustOrgName = "";
        if(entrustOrgIds !=null){
            String[] entrustOrgId = entrustOrgIds.split(",");
            for (String id : entrustOrgId) {
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(id));
                if(surveyConsignor != null){
                    entrustOrgName = entrustOrgName + surveyConsignor.getName() + " ";
                }
            }
            paramMap.put("entrustOrgName",entrustOrgName);
        }else{
            paramMap.put("entrustOrgName","全部");
        }

        String dataRoleCode =paramMap.get("dataRoleCode")!=null? paramMap.get("dataRoleCode").toString():"";
        List<ScoreDto> list = new ArrayList<>();
        if (orgId == null && !"areaManger".equals(dataRoleCode)){
            //获取平台、省级
//            paramMap.put("son",0);
            paramMap.put("level", 1);
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
            }
            list = surveyInvestigatorCaseMapper.getScoreListBySurveyOrg(paramMap);
        }else{//通过点击详情获取数据
            paramMap.put("level", 2);
            paramMap.put("son",1);
            paramMap.put("orgId",orgId);
            if ("areaManger".equals(dataRoleCode)){
                paramMap.put("level", 2);
                paramMap.remove("orgId");
                paramMap.put("dataRoleOrgId",paramMap.get("dataRoleOrgId"));
            }
            if ("provincialManger".equals(dataRoleCode)){
                paramMap.remove("dataRoleOrgId");
            }

            Map<String,Object> paramAreaMap =  new HashMap<String,Object>();
            paramAreaMap.put("surveyOrgId",orgId);
            paramAreaMap.put("surveyAreaId",paramMap.get("surveyAreaId"));
            List<SurveyOrgArea> surveyOrgAreas = surveyOrgAreaMapper.selectByList(paramAreaMap);//片区列表
            list = new ArrayList<>();
            for (SurveyOrgArea surveyOrgArea : surveyOrgAreas) {
                ScoreDto scoreDto = new ScoreDto();
                scoreDto.setSurveyOrgId(Long.parseLong(surveyOrgArea.getId().toString()));
                scoreDto.setSurveyOrgName(surveyOrgArea.getSurveyAreaName());
                scoreDto.setOrgLevel(3);
                scoreDto.setOrgType(1);
                list.add(scoreDto);
            }
            //本身
            ScoreDto scoreDto = new ScoreDto();
            scoreDto.setSurveyOrgId(orgId);
            scoreDto.setSurveyOrgName(surveyFranchiseeMapper.selectByPrimaryKey(orgId).getName());
            scoreDto.setOrgLevel(2);
            scoreDto.setOrgType(2);
            list.add(scoreDto);

            List<ScoreDto> data = surveyInvestigatorCaseMapper.getScoreListBySurveyOrgArea(paramMap);

            for (ScoreDto line : list) {
                for (ScoreDto dto : data) {
                    if(line.getSurveyOrgId() == dto.getSurveyOrgId()){
                        line.setCaseNum(dto.getCaseNum());
                        line.setChannelCaseNum(dto.getChannelCaseNum());
                        line.setSurveyScore(dto.getSurveyScore());
                        line.setSunScore(dto.getSunScore());
                        line.setScoreSum(dto.getScoreSum());
                        line.setSurveyStateName(dto.getSurveyStateName());
                        line.setCaseStateName(dto.getCaseStateName());
                        line.setOrgCaseStateName(dto.getOrgCaseStateName());
                        line.setEntrustOrgName(dto.getEntrustOrgName());
                        line.setSurveyState(dto.getSurveyState());
                        line.setCaseState(dto.getCaseState());
                        line.setOrgCaseState(dto.getOrgCaseState());
                        line.setEntrustOrgIds(dto.getEntrustOrgIds());
                    }
                }
            }

        }

        map.put("list",list);
        return map;
    }
    private Map getScoreList(Map<String, Object> paramMap, Map map, Long currentUserId, Long orgId, int trackSon, String searchType) {

        String entrustOrgIds =  (String)paramMap.get("entrustOrgIds");
        String entrustOrgName = "";
        if(entrustOrgIds !=null){
            String[] entrustOrgId = entrustOrgIds.split(",");
            for (String id : entrustOrgId) {
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(id));
                if(surveyConsignor != null){
                    entrustOrgName = entrustOrgName + surveyConsignor.getName() + " ";
                }
            }
            paramMap.put("entrustOrgName",entrustOrgName);
        }else{
            paramMap.put("entrustOrgName","全部");
        }

        paramMap.remove("surveyOrgIds");
        paramMap.put("orgId", orgId);
        paramMap.put("son", trackSon);
        List<ScoreDto> list = new ArrayList<>();
        if("user".equals(searchType)){
            list = surveyInvestigatorCaseMapper.getScoreListByInvestigator(paramMap);
        }else if("org".equals(searchType)){
            list = surveyInvestigatorCaseMapper.getInvestigatorBySurveyOrg(paramMap);//机构名下的人员数据
        }

        map.put("list",list);
        return map;
    }

}
