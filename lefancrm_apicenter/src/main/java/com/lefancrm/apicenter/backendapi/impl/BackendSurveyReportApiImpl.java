package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyReportApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.dto.avg.SurveyHuzhuDTO;
import com.lefancrm.apicenter.dto.report.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.model.SurveyBusinessReport;
import com.lefancrm.apicenter.model.SurveyCrossRegionReport;
import com.lefancrm.apicenter.model.SurveyTaskReport;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ChineseUtil;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.GetWorkDay;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@ApiService(descript = "狄大人案件报表API")
public class BackendSurveyReportApiImpl  extends BaseServiceImpl implements BackendSurveyReportApi {

    @Autowired
    private SurveyBusinessReportMapper surveyBusinessReportMapper;
    @Autowired
    private SurveyCrossRegionReportMapper surveyCrossRegionReportMapper;
    @Autowired
    private SurveyTaskReportMapper surveyTaskReportMapper;
    @Autowired
    private SurveyConsignorReportMapper surveyConsignorReportMapper;
    @Autowired
    private SurveyServiceConsignorReportMapper surveyServiceConsignorReportMapper;
    @Autowired
    private SurveyCaseAttributesReportMapper surveyCaseAttributesReportMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyCaseDirectionMapper surveyCaseDirectionMapper;
    @Autowired
    private SurveyInvestigatorOrgReportMapper surveyInvestigatorOrgReportMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyInvestigatorUserReportMapper surveyInvestigatorUserReportMapper;
    @Autowired
    private SurveyServiceOrgReportMapper surveyServiceOrgReportMapper;
    @Autowired
    private SurveyServiceUserReportMapper surveyServiceUserReportMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyPayInfoMapper surveyPayInfoMapper;
    @Autowired
    private SurveyPayInfoDetailMapper surveyPayInfoDetailMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private SurveyInvestigatorReInfoMapper surveyInvestigatorReInfoMapper;
    @Autowired
    private SurveyUserClockMapper surveyUserClockMapper;
    @Autowired
    private BackendSurveyReportThinkApiImpl surveyReportThinkApi;
    @Autowired
    private SurveyProgressMapper surveyProgressMapper;
    @Autowired
    private SurveyUserConsignorMapper surveyUserConsignorMapper;

    @ApiMethod(needLogin = false,descript = "获取报表数据",value = "get-data-survey-report")
    @Override
    public ApiResponse getData(ApiRequest apiRequest) {
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //总部 all , 保险公司 entrust， 机构 org  ， 调查员 survey  ,
        String dataType = apiRequest.getString("dataType");
        String dataTable = apiRequest.getString("dataTable");//领域  业务类型  任务统计 跨区域 .....

        //upMonth 上月 yesterday 昨天 today 今天 curWeek 本周 curMonth 本月  all 全部  date 时间段   curYear本年   curQuarterly 本季
        String searchType = apiRequest.getString("searchType");
        String startTime = apiRequest.getString("startTime");
        String endTime = apiRequest.getString("endTime");
        if ("date".equals(searchType)){
            //正常情况下startTime  endTime 都不为空  防止前台传NULL 故加 默认值
            if (startTime == null){
                startTime = "2019-02-27";
            }
            if (endTime == null){
                endTime = simpleDateFormat.format(new Date());
            }
        }else{//快捷查询  解析开始时间 与 结束时间（上月,昨天，今天，本周，本月，全部，N月度，N季度，...  本年 本季）
            StringBuilder builderStart = new StringBuilder("");
            StringBuilder builderEnd = new StringBuilder("");
            if ("month".equals(searchType) || "quarter".equals(searchType)){
                int year = apiRequest.getInt("year");
                int keyValue = apiRequest.getInt("keyValue");
                DateUtils.convertTimeBySearchType(builderStart,builderEnd,searchType,year,keyValue);
            }else if ("soon".equals(searchType)){//最近一年

            }else if ("curQuarterly".equals(searchType)){
                int curQuarter = 1;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.getTime().getMonth() + 1;
                if (month >= 1 && month <=3){
                    curQuarter = 1;
                }else if (month >= 4 && month <=6){
                    curQuarter = 2;
                }else if (month >= 7 && month <=9){
                    curQuarter = 3;
                }else if (month >= 10 && month <=12){
                    curQuarter = 4;
                }
                DateUtils.convertTimeBySearchType(builderStart,builderEnd,searchType,year,curQuarter);
            }else{
                DateUtils.convertTimeBySearchType(builderStart,builderEnd,searchType);
            }
            startTime = builderStart.toString();
            endTime = builderEnd.toString();
            if ("all".equals(searchType)){
                startTime = "2019-02-27";
                endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            }
        }

        try {
            if (!StringUtils.isEmpty(startTime)){
                paramMap.put("startTime",simpleDateFormat.parse(startTime));
            }
            if (!StringUtils.isEmpty(endTime)){
                paramMap.put("endTime",simpleDateFormat.parse(endTime));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //环比时间
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)){
            String momTime = DateUtils.getChainTime(startTime,endTime);
            try {
                paramMap.put("momTime",simpleDateFormat.parse(momTime));//环比日期
                System.out.println("环比开始时间" + momTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String tbStartTime = null,tbEndTime = null;
        //同比时间
        if (!StringUtils.isEmpty(startTime)){
            try {
                String tbStartTimeStr = apiRequest.getString("tbStartTime");
                if (StringUtils.isEmpty(tbStartTimeStr)){
                    tbStartTime = simpleDateFormat.format(DateUtils.getTbStartTime(simpleDateFormat.parse(startTime)));
                }else{
                    tbStartTime = tbStartTimeStr;
                }
                String tbEndTimeStr = apiRequest.getString("tbEndTime");
                if (StringUtils.isEmpty(tbStartTimeStr)){
                    tbEndTime = simpleDateFormat.format(DateUtils.getTbEndTime(simpleDateFormat.parse(tbStartTime),simpleDateFormat.parse(startTime),simpleDateFormat.parse(endTime)));
                }else{
                    tbEndTime = tbEndTimeStr;
                }
                paramMap.put("tbStartTime",simpleDateFormat.parse(tbStartTime));
                paramMap.put("tbEndTime",simpleDateFormat.parse(tbEndTime));

                System.out.println("同比开始时间:" + tbStartTime);
                System.out.println("同比截至时间:" + tbEndTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        //委托方机构报表  存在委托方机构ID
        if("all".equals(dataType) || "project".equals(dataType)){
            paramMap.put("checkType",2);//默认都是保司审核通过的案件
            if ("searchEntrust".equals(dataTable)){
                paramMap.put("entrustOrgId",apiRequest.getLong("entrustOrgId") == -1 ? null : apiRequest.getLong("entrustOrgId"));
                paramMap.put("sortAttr",apiRequest.getLong("sortAttr") == null ? 1 : apiRequest.getLong("sortAttr"));
                paramMap.put("sortType",apiRequest.getLong("sortType") == null ? 2 : apiRequest.getLong("sortType"));
            }else if ("searchSurvey".equals(dataTable)){
                paramMap.put("surveyOrgId",apiRequest.getLong("surveyOrgId") == -1 ? null : apiRequest.getLong("surveyOrgId"));
                paramMap.put("sortAttr",apiRequest.getLong("sortAttr") == null ? 1 : apiRequest.getLong("sortAttr"));
                paramMap.put("sortType",apiRequest.getLong("sortType") == null ? 2 : apiRequest.getLong("sortType"));

                //此处特殊转换。选择的委托方筛选数据。 2021年8月3日  需求
                paramMap.put("entrustOrgId",apiRequest.getLong("entrustOrgId2") == -1 ? null : apiRequest.getLong("entrustOrgId2"));
            }else if ("area".equals(dataTable)){
                paramMap.put("sortType",apiRequest.getLong("sortType") == null ? 2 : apiRequest.getLong("sortType"));
            }
            if ("project".equals(dataType)){
                Long curUserId = getCurrentUserId(apiRequest);
                Map<String,Object> searchMap = new HashMap<>();
                searchMap.put("userId",curUserId);
                List<SurveyUserConsignor> list = surveyUserConsignorMapper.list(searchMap);
                String entrustOrgIds = list.stream().map(p -> p.getConsignorId().toString()).collect(Collectors.joining(","));
                paramMap.put("entrustOrgIds",entrustOrgIds);
            }
        }else if ("entrust".equals(dataType)){
            paramMap.put("checkType",2);//默认都是保司审核通过的案件
            paramMap.put("entrustOrgId",apiRequest.getLong("entrustOrgId"));
            paramMap.put("sortAttr",apiRequest.getLong("sortAttr") == null ? 1 : apiRequest.getLong("sortAttr"));
            paramMap.put("sortType",apiRequest.getLong("sortType") == null ? 2 : apiRequest.getLong("sortType"));
        }else if("org".equals(dataType)){
            paramMap.put("entrustOrgId",apiRequest.getLong("entrustOrgId"));//委托机构
            paramMap.put("entrustOrgIds",apiRequest.getString("entrustOrgIds"));//委托机构-多选
            paramMap.put("surveyOrgId",apiRequest.getLong("surveyOrgId"));//调查机构
            paramMap.put("checkType",apiRequest.getLong("checkType"));//案件状态
            paramMap.put("surveyUserId",apiRequest.getLong("surveyUserId"));//调查员
            if("userReport".equals(dataTable)){
                paramMap.put("sortAttr",apiRequest.getLong("sortAttr") == null ? 1 : apiRequest.getLong("sortAttr"));
                paramMap.put("sortType",apiRequest.getLong("sortType") == null ? 2 : apiRequest.getLong("sortType"));
            }
            if ("area-son".equals(dataTable)){
                paramMap.put("cityId",apiRequest.getLong("cityId"));//城市ID
            }
        }else if("survey".equals(dataType)){
            paramMap.put("entrustOrgId",apiRequest.getLong("entrustOrgId"));//委托机构
            paramMap.put("surveyUserId",apiRequest.getLong("surveyUserId"));//调查员
            paramMap.put("checkType",apiRequest.getLong("checkType"));//案件状态
        }else if ("clock".equals(dataType)){
            paramMap.put("surveyOrgIds",apiRequest.getString("surveyOrgIds"));//调查机构集合
            paramMap.put("surveyUserIds",apiRequest.getString("surveyUserIds"));//调查员集合
            paramMap.put("endQuitTime",apiRequest.getString("endQuitTime"));//离职截止日期
            //如果只有机构层级的角色 只查询机构的数据
            Long currentUserId = getCurrentUserId(apiRequest);
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean orgManger = isRoleUser(userRoles,104L), lfManger = isRoleUser(userRoles,95L),generalManger = isRoleUser(userRoles,75L),
                    rsRoleUser = isRoleUser(userRoles,100L),forgManger = isRoleUser(userRoles,114L),orgReviewManger = isRoleUser(userRoles,58L),
                    provincialManger = isRoleUser(userRoles,98L),personneManger = isRoleUser(userRoles,107L),areaManger = isRoleUser(userRoles,116L);
            if (orgManger || forgManger || provincialManger || orgReviewManger){
                SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (investigator != null){
                    paramMap.put("curSurveyOrgId",investigator.getOrgId());//当前机构
                }
            }
            if (lfManger || rsRoleUser || generalManger || personneManger){//平台角色  移除机构查询条件
                paramMap.remove("curSurveyOrgId");
            }
        }

        //2019年11月29日  所有报表加载任务类型统计 需要区分互助还是保司
        if ("task".equals(dataTable)){
            paramMap.put("orgAttr",apiRequest.getString("orgAttr"));
        }

        if ("trend".equals(dataTable) || "trend2".equals(dataTable)){
            String trendType = apiRequest.getString("trendType");
            paramMap.put("trendType", StringUtils.isEmpty(trendType) ? "1" : trendType);//1最近一年  2筛选时间
            String surveyOrgIdTrend = apiRequest.getString("surveyOrgIdTrend");
            if (StringUtils.isEmpty(surveyOrgIdTrend)){
                paramMap.put("surveyOrgIdTrend",null);
            }else{
                paramMap.put("surveyOrgIdTrend",Long.parseLong(surveyOrgIdTrend));
            }
        }

        paramMap.put("orgAttr",1);//所有报表都只查询保司数据
        Map map = new HashMap();
        switch (dataType){
            case "all" :
            case "entrust" :
            case "project" :
                map = getMapNew(dataType,dataTable,paramMap,map,apiRequest.getLong("entrustOrgId"));
                if ("index-survey-data-export".equals(apiRequest.getString("exportType"))){
                    List<SurveyInvestigatorOrgReport> surveyInvestigatorOrgReports = (List<SurveyInvestigatorOrgReport>)map.get("surveyOrgs");
                    return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyInvestigatorOrgReports);
                }
                break;
            case "org" :
                map = getOrgMapNew(dataType,dataTable,paramMap,map,apiRequest.getLong("surveyOrgId"),apiRequest);
                break;
            case "survey" :
                map = getSurveyMapNew(dataType,dataTable,paramMap,map,apiRequest.getLong("surveyUserId"),apiRequest);
                break;
            case "clock":
                map = getClockMap(dataType,dataTable,paramMap,map,apiRequest); break;
            default: break;
        }

        //获取案件详情
        String dataItem = apiRequest.getString("dataItem");
        if ("dataItem".equals(dataItem)){
            paramMap.put("dataType",dataType);
            Long currentUserId = getCurrentUserId(apiRequest);
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean generalRole = isRoleUser(userRoles,91L);//总部报表权限
            map.put("generalRole",generalRole);
            String itemType = apiRequest.getString("itemType");
            String itemValue = apiRequest.getString("itemValue");
            String itemId = apiRequest.getString("itemId");
            paramMap.put("itemType",itemType);
            paramMap.put("itemValue",itemValue);
            paramMap.put("itemId",itemId);
            switch (dataType){
                case "entrust": paramMap.put("entrustOrgId",apiRequest.getLong("entrustOrgId")); break;
                case "org": paramMap.put("surveyOrgId",apiRequest.getLong("surveyOrgId")); break;
                case "survey": paramMap.put("surveyUserId",apiRequest.getLong("surveyUserId")); break;
                default: break;
            }

            List<SurveyReportCaseDTO> list = surveyRiskCaseInfoMapper.selectReportCaseItem(paramMap);
            if (list.size() > 0){
                List<SurveyAssignOrg> caseOrgs = surveyAssignOrgMapper.selectByCases(list);
                for (SurveyReportCaseDTO item : list) {
                    List<SurveyAssignOrg> surveyOrgs = new ArrayList<>();
                    for (SurveyAssignOrg org : caseOrgs) {
                        if (item.getSurveyInfoId().intValue() == org.getSurveyInfoId().intValue()){
                            surveyOrgs.add(org);
                        }
                    }
                    item.setSurveyOrgs(surveyOrgs);
                }

                //获取退回率明细导出的退回原因
                String from = apiRequest.getString("from");
                if ("indexToOrgReturn".equals(from)){
                    List<Long> collect = list.stream().map(SurveyReportCaseDTO::getSurveyInfoId).collect(Collectors.toList());
                    List<SurveyProgress> surveyProgresses = surveyProgressMapper.selectBySurveyInfoIds(collect);
                    for (SurveyReportCaseDTO surveyReportCaseDTO : list) {
                        List<SurveyProgress> temp = surveyProgresses.stream().filter(p -> p.getProgressName() != null && surveyReportCaseDTO.getSurveyInfoId().intValue() == p.getSurveyInfoId() && p.getProgressName().indexOf(surveyReportCaseDTO.getSurveyOrgName()) > -1).collect(Collectors.toList());
                        String remarks = "";
                        for (int i = 0; i < temp.size(); i++) {
                            remarks += "第" + ChineseUtil.numberToChinese((i + 1) + "") + "退回：" + temp.get(i).getProgressDesc() + "；";
                        }
                        surveyReportCaseDTO.setReturnRemark(remarks);
                    }
                }
            }

            //获取部门集合
            List<SurveyDepartmentDTO> departments = new ArrayList<>();
            Map<String, List<SurveyReportCaseDTO>> collect = list.stream().filter(p -> p.getEntrustDepartmentName() != null).collect(Collectors.groupingBy(SurveyReportCaseDTO::getEntrustDepartmentName));
            collect.forEach((k1,k2) -> {
                SurveyDepartmentDTO department = new SurveyDepartmentDTO();
                department.setDepartmentName(k1);
                department.setNum(k2.size());
                departments.add(department);
            });
            departments.sort(Comparator.comparing(SurveyDepartmentDTO :: getNum).reversed());//降序
            map.put("departments",departments);
            map.put("cases",list);
        }
        map.put("searchType",searchType);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("tbStartTime",tbStartTime);
        map.put("tbEndTime",tbEndTime);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    /**
     * 打卡报表
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    private Map<String,Object> getClockMap(String dataType,String dataTable,Map paramMap,Map map,ApiRequest apiRequest){
        try {
            HashMap<String, Object> totalMap = new HashMap<>();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = paramMap.get("startTime") == null ? sf.parse("2020-01-01") : (Date)paramMap.get("startTime");
            Date endTime = paramMap.get("endTime") == null ? sf.parse(LocalDate.now().toString()) : (Date)paramMap.get("endTime");
            List list = GetWorkDay.calLeaveDaysList(startTime,endTime, 1);
            int daySize = (list == null || list.size() == 0) ? 1 :list.size();
            paramMap.put("dateList",list);
            List<SurveyClockDTO> clocks = surveyUserClockMapper.clocks(paramMap);
            for (SurveyClockDTO clock : clocks) {
                if (clock.getCaseNum() == 0){
                    clock.setAvgCaseMoney(0D);
                }else{
                    clock.setAvgCaseMoney(DecimalUtil.twoDecimalTOFourFromFive(clock.getTotalMoney()  / clock.getCaseNum()));
                }
                clock.setAvgEveryDayClockNum((double)clock.getClockNum()/daySize);
                clock.setAvgCaseClockNum(clock.getCaseNum() == 0 ? 0 : ((double)clock.getTotalNum()/clock.getCaseNum()));
                clock.setTempMoney(fourRate(Double.valueOf(clock.getClockNum()),Double.valueOf(clock.getDirectionNum())));
                clock.setTempDistance(fourRate(clock.getDistance(),Double.valueOf(clock.getDirectionNum())));
            }
            map.put("clocks",clocks);

            //底部合计
            Supplier<Stream<SurveyClockDTO>> streamSupplier = clocks::stream;
            int clockTotal = streamSupplier.get().mapToInt(SurveyClockDTO::getClockNum).sum();
            int totalNum = streamSupplier.get().mapToInt(SurveyClockDTO::getTotalNum).sum();
            int caseTotal = streamSupplier.get().mapToInt(SurveyClockDTO::getCaseNum).sum();
            int totalDirectionNum = streamSupplier.get().mapToInt(SurveyClockDTO::getDirectionNum).sum();
            long allDay = streamSupplier.get().mapToLong(SurveyClockDTO::getClockDay).sum();
            totalMap.put("total1",clockTotal);
            List<SurveyClockDTO> collect = clocks.stream().filter(e -> e.getAvgEveryDayClockNum() > 0).collect(Collectors.toList());
            double sum = clocks.stream().filter(e -> e.getAvgEveryDayClockNum() > 0).mapToDouble(SurveyClockDTO::getAvgEveryDayClockNum).sum();
            totalMap.put("total2",sum/(collect.size() == 0?1 : collect.size()));
            totalMap.put("total3", caseTotal == 0 ? 0 : (double) totalNum / caseTotal);//平均打卡次数
            if (clocks.size() == 1){
                totalMap.put("total4",clocks.get(0).getFirstClockTime());//合计首次打卡时间
            }else {
                long allSec = streamSupplier.get().mapToLong(SurveyClockDTO::getEveryDayFirstClockAllSec).sum();
                if (allSec == 0){
                    totalMap.put("total4",null);
                }else {
                    long h = allSec / allDay / 3600;
                    long m = (allSec - h * allDay * 3600) / allDay / 60;
                    long s = (allSec - h * allDay * 3600 - m * allDay * 60) / allDay;
                    totalMap.put("total4", (h < 10 ? ("0" + h) : h) + ":" + (m < 10 ? ("0" + m) : m) + ":" + (s < 10 ? ("0" + s) : s));//合计首次打卡时间
                }
            }
            totalMap.put("total5",streamSupplier.get().mapToInt(SurveyClockDTO::getLackClockNum).sum());
            totalMap.put("total6",streamSupplier.get().mapToDouble(SurveyClockDTO::getTotalMoney).sum());
            totalMap.put("total7",caseTotal);
            totalMap.put("total8", caseTotal == 0 ? 0 : ((double) totalMap.get("total6") / caseTotal));
            totalMap.put("total9",streamSupplier.get().mapToDouble(SurveyClockDTO::getMoney1).sum());
            totalMap.put("total10",streamSupplier.get().mapToDouble(SurveyClockDTO::getMoney2).sum());
            totalMap.put("total11",streamSupplier.get().mapToDouble(SurveyClockDTO::getMoney3).sum());
            totalMap.put("total12",streamSupplier.get().mapToDouble(SurveyClockDTO::getMoney4).sum());
            totalMap.put("total13",streamSupplier.get().mapToDouble(SurveyClockDTO::getMoney5).sum());
            totalMap.put("total14",streamSupplier.get().mapToDouble(SurveyClockDTO::getMoney6).sum());
            totalMap.put("total15",streamSupplier.get().mapToDouble(SurveyClockDTO::getMoney7).sum());
            totalMap.put("total16",streamSupplier.get().mapToDouble(SurveyClockDTO::getMoney8).sum());
            totalMap.put("total17",streamSupplier.get().mapToDouble(SurveyClockDTO::getMoney9).sum());
            totalMap.put("total18",fourRate(Double.valueOf(clockTotal),Double.valueOf(totalDirectionNum)));
            Double totalDistance = streamSupplier.get().mapToDouble(SurveyClockDTO::getDistance).sum();
            totalMap.put("total19",totalDistance);
            totalMap.put("total20",fourRate(totalDistance,Double.valueOf(totalDirectionNum)));
            map.put("totalData",totalMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().intValue() == roleId.intValue()){
                return true;
            }
        }
        return false;
    }


    /**
     * 计算比例 保留4位小数
     * @param a
     * @param b
     * @return
     */
    private Double fourRate(Double a, Double b){
        if (b == 0) return 0D;
        return new BigDecimal(a / b).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private Double rate(Double a,Double b){
        if (a == null) a = 0D;
        if (b == null) b = 0D;
        if (a == 0){
            return b == 0 ? 0 : -b;
        }
        if (b == 0){
            return a == 0 ? 0 : a;
        }
        return new BigDecimal((float)(a - b) / b).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private Double rate(Integer a,Integer b){
        if (a == null) a = 0;
        if (b == null) b = 0;
        return rate(new Double(String.valueOf(a)),new Double(String.valueOf(b)));
    }


    private Map getMapNew(String dataType,String dataTable,Map paramMap,Map map,Long entrustOrgId){
        if ("bas".equals(dataTable)){
            //基础信息，关键指标
            SurveyReportLefanDTO bas = surveyCrossRegionReportMapper.selectAllBas(paramMap);
            //环比委托案件数量
            int tarMomNewEntrust = surveyCrossRegionReportMapper.selectAllBasByMomTime(paramMap);
            bas.setTarMomNewEntrust(tarMomNewEntrust);
            bas.setTarNewEntrustRate(DecimalUtil.fourDecimalTOFourFromFive(rate(bas.getTarNewEntrust(),bas.getTarMomNewEntrust())));
            //同比案件数量
            int tarTbNewEntrust = surveyCrossRegionReportMapper.selectAllBasByTbTime(paramMap);
            bas.setTarTbNewEntrust(tarTbNewEntrust);
            bas.setTarTbNewEntrustRate(DecimalUtil.fourDecimalTOFourFromFive(rate(bas.getTarNewEntrust(),bas.getTarTbNewEntrust())));


            //开票未到账金额= 总开票金额-总到账金额
            bas.setBasBillNotAcc(bas.getBasTotalBillMoney() - bas.getBasTotalAccMoney());
            bas.setTarAvgMoney(bas.getTarNewEntrustOK() == 0 ? 0 : bas.getTarEntrustMoney() / bas.getTarNewEntrustOK());
            bas.setTarAvgDirectionMoney(bas.getTarDirectionNum() == 0 ? 0 : bas.getTarEntrustMoney() / bas.getTarDirectionNum());
            bas.setTarSunRate(bas.getTarNewEntrustOK() == 0 ? 0 : bas.getTarSunRate() / bas.getTarNewEntrustOK() * 100);
            bas.setTarEfficRate(bas.getTarNewEntrustOK() == 0 ? 0 : bas.getTarEfficRate() / bas.getTarNewEntrustOK() * 100);
            map.put("bas",bas);
            //委托机构排名
            List<SurveyConsignorReport> surveyConsignorReports = surveyConsignorReportMapper.selectAllNew(paramMap);
            for (SurveyConsignorReport surveyConsignorReport : surveyConsignorReports) {
                surveyConsignorReport.setNewSurveyCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyConsignorReport.getNewSurveyCaseNum(),surveyConsignorReport.getMomNewSurveyCaseNum())));//环比
                surveyConsignorReport.setHbNewSurveyCaseNumRate(surveyConsignorReport.getNewSurveyCaseNumRate());//环比
                surveyConsignorReport.setTbNewSurveyCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyConsignorReport.getNewSurveyCaseNum(),surveyConsignorReport.getTbNewSurveyCaseNum())));//同比
                if (surveyConsignorReport.getNewCheckNum() == 0){
                    surveyConsignorReport.setPositiveRate(0D);
                    surveyConsignorReport.setLossEfficiencyRate(0D);
                    continue;
                }
                surveyConsignorReport.setPositiveRate(new Double(surveyConsignorReport.getSunNum()) / new Double(surveyConsignorReport.getNewCheckNum()));
                surveyConsignorReport.setLossEfficiencyRate(new Double(surveyConsignorReport.getLossNum()) / new Double(surveyConsignorReport.getNewCheckNum()));
            }

            map.put("entrustOrgs",surveyConsignorReports);

            //调查机构排名
            List<SurveyInvestigatorOrgReport> surveyInvestigatorOrgReports = surveyInvestigatorOrgReportMapper.selectAllNew(paramMap);
            for (SurveyInvestigatorOrgReport surveyInvestigatorOrgReport : surveyInvestigatorOrgReports) {
                surveyInvestigatorOrgReport.setHbCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyInvestigatorOrgReport.getNewSend(),surveyInvestigatorOrgReport.getHbCaseNum())));//环比
                surveyInvestigatorOrgReport.setTbCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyInvestigatorOrgReport.getNewSend(),surveyInvestigatorOrgReport.getTbCaseNum())));//同比
                if (surveyInvestigatorOrgReport.getInsuranceCheckCaseNum() == 0) {
                    surveyInvestigatorOrgReport.setReturnRate(0D);
                    surveyInvestigatorOrgReport.setPositiveRate(0D);
                    surveyInvestigatorOrgReport.setMarkErrorRate(0D);
                    continue;
                }
                surveyInvestigatorOrgReport.setReturnRate(new Double(surveyInvestigatorOrgReport.getReturnNum()) / new Double(surveyInvestigatorOrgReport.getInsuranceCheckCaseNum()));
                surveyInvestigatorOrgReport.setPositiveRate(new Double(surveyInvestigatorOrgReport.getSunNum()) / new Double(surveyInvestigatorOrgReport.getInsuranceCheckCaseNum()));
                surveyInvestigatorOrgReport.setMarkErrorRate(new Double(surveyInvestigatorOrgReport.getMarkErrorNum()) / new Double(surveyInvestigatorOrgReport.getInsuranceCheckCaseNum()));
            }
            map.put("surveyOrgs",surveyInvestigatorOrgReports);
        }
        else if ("searchEntrust".equals(dataTable)){
            //委托机构排名
            List<SurveyConsignorReport> surveyConsignorReports = surveyConsignorReportMapper.selectAllNew(paramMap);
            for (SurveyConsignorReport surveyConsignorReport : surveyConsignorReports) {
                surveyConsignorReport.setNewSurveyCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyConsignorReport.getNewSurveyCaseNum(),surveyConsignorReport.getMomNewSurveyCaseNum())));//环比
                surveyConsignorReport.setHbNewSurveyCaseNumRate(surveyConsignorReport.getNewSurveyCaseNumRate());//环比
                surveyConsignorReport.setTbNewSurveyCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyConsignorReport.getNewSurveyCaseNum(),surveyConsignorReport.getTbNewSurveyCaseNum())));//同比
                if (surveyConsignorReport.getNewCheckNum() == 0){
                    surveyConsignorReport.setPositiveRate(0D);
                    surveyConsignorReport.setLossEfficiencyRate(0D);
                    continue;
                }
                surveyConsignorReport.setPositiveRate(new Double(surveyConsignorReport.getSunNum()) / new Double(surveyConsignorReport.getNewCheckNum()));
                surveyConsignorReport.setLossEfficiencyRate(new Double(surveyConsignorReport.getLossNum()) / new Double(surveyConsignorReport.getNewCheckNum()));
            }
            //java8 排序
            String sortAttr = paramMap.get("sortAttr") == null ? "" : paramMap.get("sortAttr").toString();
            String sortType = paramMap.get("sortType") == null ? "1" : paramMap.get("sortType").toString();
            if ("15".equals(sortAttr)){//环比排序
                if ("1".equals(sortType)){//升序
                    surveyConsignorReports = surveyConsignorReports.stream().sorted(Comparator.comparing(SurveyConsignorReport::getHbNewSurveyCaseNumRate)).collect(Collectors.toList());
                }else{
                    surveyConsignorReports = surveyConsignorReports.stream().sorted(Comparator.comparing(SurveyConsignorReport::getHbNewSurveyCaseNumRate).reversed()).collect(Collectors.toList());
                }
            }else if ("16".equals(sortAttr)){//同比排序
                if ("1".equals(sortType)){//升序
                    surveyConsignorReports = surveyConsignorReports.stream().sorted(Comparator.comparing(SurveyConsignorReport::getTbNewSurveyCaseNumRate)).collect(Collectors.toList());
                }else{
                    surveyConsignorReports = surveyConsignorReports.stream().sorted(Comparator.comparing(SurveyConsignorReport::getTbNewSurveyCaseNumRate).reversed()).collect(Collectors.toList());
                }
            }
            map.put("entrustOrgs",surveyConsignorReports);
        }
        else if ("searchSurvey".equals(dataTable)){
            //调查机构排名
            List<SurveyInvestigatorOrgReport> surveyInvestigatorOrgReports = surveyInvestigatorOrgReportMapper.selectAllNew(paramMap);
            for (SurveyInvestigatorOrgReport surveyInvestigatorOrgReport : surveyInvestigatorOrgReports) {
                surveyInvestigatorOrgReport.setHbCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyInvestigatorOrgReport.getNewSend(),surveyInvestigatorOrgReport.getHbCaseNum())));//环比
                surveyInvestigatorOrgReport.setTbCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyInvestigatorOrgReport.getNewSend(),surveyInvestigatorOrgReport.getTbCaseNum())));//同比

                if (surveyInvestigatorOrgReport.getInsuranceCheckCaseNum() == 0) {
                    surveyInvestigatorOrgReport.setReturnRate(0D);
                    surveyInvestigatorOrgReport.setPositiveRate(0D);
                    surveyInvestigatorOrgReport.setMarkErrorRate(0D);
                    continue;
                }
                surveyInvestigatorOrgReport.setReturnRate(new Double(surveyInvestigatorOrgReport.getReturnNum()) / new Double(surveyInvestigatorOrgReport.getInsuranceCheckCaseNum()));
                surveyInvestigatorOrgReport.setPositiveRate(new Double(surveyInvestigatorOrgReport.getSunNum()) / new Double(surveyInvestigatorOrgReport.getInsuranceCheckCaseNum()));
                surveyInvestigatorOrgReport.setMarkErrorRate(new Double(surveyInvestigatorOrgReport.getMarkErrorNum()) / new Double(surveyInvestigatorOrgReport.getInsuranceCheckCaseNum()));
            }
            //java8 排序
            String sortAttr = paramMap.get("sortAttr") == null ? "" : paramMap.get("sortAttr").toString();
            String sortType = paramMap.get("sortType") == null ? "1" : paramMap.get("sortType").toString();
            if ("15".equals(sortAttr)){//环比排序
                if ("1".equals(sortType)){//升序
                    surveyInvestigatorOrgReports = surveyInvestigatorOrgReports.stream().sorted(Comparator.comparing(SurveyInvestigatorOrgReport::getHbCaseNumRate)).collect(Collectors.toList());
                }else{
                    surveyInvestigatorOrgReports = surveyInvestigatorOrgReports.stream().sorted(Comparator.comparing(SurveyInvestigatorOrgReport::getHbCaseNumRate).reversed()).collect(Collectors.toList());
                }
            }else if ("16".equals(sortAttr)){//同比排序
                if ("1".equals(sortType)){//升序
                    surveyInvestigatorOrgReports = surveyInvestigatorOrgReports.stream().sorted(Comparator.comparing(SurveyInvestigatorOrgReport::getTbCaseNumRate)).collect(Collectors.toList());
                }else{
                    surveyInvestigatorOrgReports = surveyInvestigatorOrgReports.stream().sorted(Comparator.comparing(SurveyInvestigatorOrgReport::getTbCaseNumRate).reversed()).collect(Collectors.toList());
                }
            }
            map.put("surveyOrgs",surveyInvestigatorOrgReports);
        }
        else if ("entrustBas".equals(dataTable)){
            //基础数据 关键指标
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(entrustOrgId);
            SurveyReportEntrustDTO bas = surveyConsignorReportMapper.selectEntrustBas(paramMap);
            //开票未到账金额= 总开票金额-总到账金额
            bas.setBasBillNotAcc(bas.getBasTotalBillMoney() - bas.getBasTotalAccMoney());

            bas.setSurveyConsignor(surveyConsignor);
            List<SurveyConsignorReport> list = surveyConsignorReportMapper.selectAllNew(paramMap);
            for (SurveyConsignorReport surveyConsignorReport : list) {
                surveyConsignorReport.setNewSurveyCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyConsignorReport.getNewSurveyCaseNum(),surveyConsignorReport.getMomNewSurveyCaseNum())));//环比
                surveyConsignorReport.setHbNewSurveyCaseNumRate(surveyConsignorReport.getNewSurveyCaseNumRate());//环比
                surveyConsignorReport.setTbNewSurveyCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(surveyConsignorReport.getNewSurveyCaseNum(),surveyConsignorReport.getTbNewSurveyCaseNum())));//同比
                if (surveyConsignorReport.getNewCheckNum() == 0){
                    surveyConsignorReport.setPositiveRate(0D);
                    surveyConsignorReport.setLossEfficiencyRate(0D);
                    continue;
                }
                surveyConsignorReport.setPositiveRate(new Double(surveyConsignorReport.getSunNum()) / new Double(surveyConsignorReport.getNewCheckNum()));
                surveyConsignorReport.setLossEfficiencyRate(new Double(surveyConsignorReport.getLossNum()) / new Double(surveyConsignorReport.getNewCheckNum()));
            }
            SurveyConsignorReport surveyConsignorReport = list.size() > 0 ? list.get(0) : new SurveyConsignorReport();
            if (surveyConsignorReport.getDirectionNum() == 0){
                surveyConsignorReport.setDirectionAvgMoney(0D);
            }else{
                Double avgMoney = surveyConsignorReport.getSurveyMoney() / surveyConsignorReport.getDirectionNum();
                surveyConsignorReport.setDirectionAvgMoney(avgMoney);
            }
            bas.setSurveyConsignorReport(surveyConsignorReport);
            map.put("bas",bas);

        }
        else if ("trend".equals(dataTable)){
            try {
                Calendar cld = Calendar.getInstance();
                List<String> dates = new ArrayList<String>();
                Date time1 = null,time2 = null;
                String trendType = paramMap.get("trendType").toString();
                if ("1".equals(trendType)){//最近一年
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                    cld.setTime(new Date());
                    cld.add(Calendar.YEAR,-1);
                    Date tempTime = cld.getTime();
                    for (;true;){
                        cld.setTime(tempTime);
                        cld.add(Calendar.MONTH,1);
                        tempTime = cld.getTime();
                        if (tempTime.after(new Date())){
                            break;
                        }
                        dates.add(simpleDateFormat.format(tempTime));
                    }
                    List<SurveyConsignorReport> reports1 = surveyConsignorReportMapper.selectAllTrendNew1Year(paramMap);//新增委托数据
                    List<SurveyConsignorReport> reports2 = surveyConsignorReportMapper.selectAllTrendNew2Year(paramMap);//保司审核通过数据
                    List<SurveyConsignorReport> list = new ArrayList<>();
                    for (String date : dates) {
                        SurveyConsignorReport report = new SurveyConsignorReport();
                        report.setReportDate(simpleDateFormat.parse(date));
                        report.setReportDateStr(simpleDateFormat.format(report.getReportDate()));
                        for (SurveyConsignorReport item : reports1) {
                            if (date.equals(item.getReportDateStr())) {
                                report.setNewSurveyCaseNum(item.getNewSurveyCaseNum());
                            }
                        }
                        for (SurveyConsignorReport item : reports2) {
                            if (date.equals(item.getReportDateStr())) {
                                report.setNewCheckNum(item.getNewCheckNum());
                            }
                        }
                        list.add(report);
                    }
                    map.put("list",list);
                }else{//按时间筛选
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Object startTime = paramMap.get("startTime");
                    Object endTime = paramMap.get("endTime");
                    if (startTime != null && endTime != null){
                        time1 = simpleDateFormat.parse(simpleDateFormat.format(startTime));
                        time2 = simpleDateFormat.parse(simpleDateFormat.format(endTime));
                    }else{
                        //报表最小时间 报表最大时间
                        Date minDate = surveyConsignorReportMapper.selectMinReportDate(paramMap);
                        Date maxDate = new Date();
                        time1 = minDate == null ? new Date() : simpleDateFormat.parse(simpleDateFormat.format(minDate));
                        time2 = maxDate == null ? new Date() : simpleDateFormat.parse(simpleDateFormat.format(maxDate));
                    }
                    int days = (int) ((time2.getTime() - time1.getTime()) / (1000*3600*24));
                    for (int i = 0; i < days + 1; i++) {
                        cld.setTime(time1);
                        cld.add(Calendar.DATE, i);
                        dates.add(simpleDateFormat.format(cld.getTime()));
                    }
                    map.put("dates",dates);
                    List<SurveyConsignorReport> reports1 = surveyConsignorReportMapper.selectAllTrendNew1(paramMap);//新增委托数据
                    List<SurveyConsignorReport> reports2 = surveyConsignorReportMapper.selectAllTrendNew2(paramMap);//保司审核通过数据
                    List<SurveyConsignorReport> list = new ArrayList<>();
                    for (String date : dates) {
                        SurveyConsignorReport report = new SurveyConsignorReport();
                        report.setReportDate(simpleDateFormat.parse(date));
                        report.setReportDateStr(simpleDateFormat.format(report.getReportDate()));
                        for (SurveyConsignorReport item : reports1) {
                            if (date.equals(item.getReportDateStr())) {
                                report.setNewSurveyCaseNum(item.getNewSurveyCaseNum());
                            }
                        }
                        for (SurveyConsignorReport item : reports2) {
                            if (date.equals(item.getReportDateStr())) {
                                report.setNewCheckNum(item.getNewCheckNum());
                            }
                        }
                        list.add(report);
                    }
                    map.put("list",list);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if ("trend2".equals(dataTable)){
            try {
                Calendar cld = Calendar.getInstance();
                List<String> dates = new ArrayList<String>();
                Date time1 = null,time2 = null;
                String trendType = paramMap.get("trendType").toString();
                if ("1".equals(trendType)){//最近一年
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                    cld.setTime(new Date());
                    cld.add(Calendar.YEAR,-1);
                    Date tempTime = cld.getTime();
                    for (;true;){
                        cld.setTime(tempTime);
                        cld.add(Calendar.MONTH,1);
                        tempTime = cld.getTime();
                        if (tempTime.after(new Date())){
                            break;
                        }
                        dates.add(simpleDateFormat.format(tempTime));
                    }
                    paramMap.put("startTime",DateUtils.getUpYear(new Date()));
                    paramMap.put("endTime",new Date());
                    List<SurveyTrendDTO> tempCases = null;
                    if (paramMap.get("surveyOrgIdTrend") == null){
                        //所有案件集合
                        tempCases = surveyConsignorReportMapper.selectTrendYear(paramMap);
                    }else{
                        //机构案件集合(阳性标记 、超时标记)
                        tempCases = surveyConsignorReportMapper.selectTrendOrgYear(paramMap);
                    }
                    List<SurveyConsignorReport> list = new ArrayList<>();
                    for (String date : dates) {
                        SurveyConsignorReport report = new SurveyConsignorReport();
                        Long totalNum = tempCases.stream().filter(p -> date.equals(p.getReportDate())).count();//总数量
                        Long sunNum = tempCases.stream().filter(p -> date.equals(p.getReportDate()) && p.getIsSun() == 1).count();//阳性案件数量
                        Long overNum = tempCases.stream().filter(p -> date.equals(p.getReportDate()) && p.getIsOver() == 1).count();//超时案件数量
                        report.setReportDate(simpleDateFormat.parse(date));
                        report.setReportDateStr(simpleDateFormat.format(report.getReportDate()));
                        report.setPositiveRate(fourRate(sunNum.doubleValue(),totalNum.doubleValue()) * 100);
                        report.setLossEfficiencyRate(fourRate(overNum.doubleValue(),totalNum.doubleValue()) * 100);
                        list.add(report);
                    }
                    map.put("list",list);
                }else{//按时间筛选
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Object startTime = paramMap.get("startTime");
                    Object endTime = paramMap.get("endTime");
                    if (startTime != null && endTime != null){
                        time1 = simpleDateFormat.parse(simpleDateFormat.format(startTime));
                        time2 = simpleDateFormat.parse(simpleDateFormat.format(endTime));
                    }else{
                        //报表最小时间 报表最大时间
                        Date minDate = surveyConsignorReportMapper.selectMinReportDate(paramMap);
                        Date maxDate = new Date();
                        time1 = minDate == null ? new Date() : simpleDateFormat.parse(simpleDateFormat.format(minDate));
                        time2 = maxDate == null ? new Date() : simpleDateFormat.parse(simpleDateFormat.format(maxDate));
                    }
                    int days = (int) ((time2.getTime() - time1.getTime()) / (1000*3600*24));
                    for (int i = 0; i < days + 1; i++) {
                        cld.setTime(time1);
                        cld.add(Calendar.DATE, i);
                        dates.add(simpleDateFormat.format(cld.getTime()));
                    }
                    map.put("dates",dates);
                    List<SurveyConsignorReport> reports1 = surveyConsignorReportMapper.selectAllTrendNew1(paramMap);//新增委托数据
                    List<SurveyConsignorReport> reports2 = surveyConsignorReportMapper.selectAllTrendNew2(paramMap);//保司审核通过数据
                    List<SurveyConsignorReport> list = new ArrayList<>();
                    for (String date : dates) {
                        SurveyConsignorReport report = new SurveyConsignorReport();
                        report.setReportDate(simpleDateFormat.parse(date));
                        report.setReportDateStr(simpleDateFormat.format(report.getReportDate()));
                        for (SurveyConsignorReport item : reports1) {
                            if (date.equals(item.getReportDateStr())) {
                                report.setNewSurveyCaseNum(item.getNewSurveyCaseNum());
                            }
                        }
                        for (SurveyConsignorReport item : reports2) {
                            if (date.equals(item.getReportDateStr())) {
                                report.setNewCheckNum(item.getNewCheckNum());
                            }
                        }
                        list.add(report);
                    }
                    map.put("list",list);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if ("business".equals(dataTable)){
            List<SurveyBusinessReport> list = surveyBusinessReportMapper.selectAllNew(paramMap);
            int total = 0;
            for (SurveyBusinessReport surveyBusinessReport : list) {
                total += surveyBusinessReport.getEntrustCaseNum();
            }
            map.put("total",total);//总数量 用于计算比列
            map.put("list",list);
        }
        else if ("cross".equals(dataTable)){
            List<SurveyCrossRegionReport> list = surveyCrossRegionReportMapper.selectAllNew(paramMap);
            int total  = 0;
            for (SurveyCrossRegionReport surveyCrossRegionReport : list) {
                String areaLevel = surveyCrossRegionReport.getAreaLevel().toString();
                if ("1".equals(areaLevel)){
                    surveyCrossRegionReport.setAreaLevelName("单区域");
                }else if ("2".equals(areaLevel)){
                    surveyCrossRegionReport.setAreaLevelName("双区域");
                }else{
                    surveyCrossRegionReport.setAreaLevelName(ChineseUtil.numberToChinese(areaLevel) + "区域");
                }
                total += surveyCrossRegionReport.getEntrustCaseNum();
            }
            map.put("total",total);//总数量 用于计算比列
            map.put("list",list);
        }
        else if ("task".equals(dataTable)){
            List<SurveyTaskReport> list = surveyTaskReportMapper.selectAllNew(paramMap);
            for (SurveyTaskReport item : list) {
                paramMap.put("taskId",item.getParentId());
                List<SurveyTaskReport> surveyTaskReports = surveyTaskReportMapper.selectAllNewByParentId(paramMap);
                item.setSurveyTaskReports(surveyTaskReports);
            }
            map.put("list",list);
        }
        else if ("area".equals(dataTable)){
            List<SurveyReportAreaDTO> list = surveyCrossRegionReportMapper.selectAllArea(paramMap);
            int total  = 0;
            for (SurveyReportAreaDTO surveyReportAreaDTO : list) {
                total +=  surveyReportAreaDTO.getNum();
            }
            map.put("total",total);//总数量 用于计算比列
            map.put("list",list);
            int maxNum = 0;
            if (list.size() > 0){
                Object sortType = paramMap.get("sortType");
                if (sortType != null) {
                    if ("1".equals(sortType.toString())){//如果是升序 取最后一条
                        maxNum = list.get(list.size() - 1).getNum();
                    }else{
                        maxNum = list.get(0).getNum();
                    }
                }else{
                    maxNum = list.get(0).getNum();
                }
            }
            map.put("maxNum",maxNum);
        }
        else if ("service".equals(dataTable)){
            List<SurveyServiceConsignorReport> list = surveyServiceConsignorReportMapper.selectAllNew(paramMap);
            List<SurveyServiceConsignorReport> listSun = surveyServiceConsignorReportMapper.selectAllNewSun(paramMap);
            int total  = 0;
            for (SurveyServiceConsignorReport surveyServiceConsignorReport : list) {
                total +=  (surveyServiceConsignorReport.getEntrustCaseNum() == null ? 0 : surveyServiceConsignorReport.getEntrustCaseNum());
                for (SurveyServiceConsignorReport serviceConsignorReport : listSun) {
                    if (surveyServiceConsignorReport.getServiceTypeId().intValue() == serviceConsignorReport.getServiceTypeId().intValue()) {
                        surveyServiceConsignorReport.setPositiveNum(serviceConsignorReport.getPositiveNum());//阳性案件数量
                    }
                }
                if (surveyServiceConsignorReport.getPositiveNum() == null){
                    surveyServiceConsignorReport.setPositiveNum(0);
                }
            }
            map.put("total",total);//总数量 用于计算比列
            map.put("list",list);
        }
        else if ("attr".equals(dataTable)){
            List<SurveyCaseAttributesReport> list = surveyCaseAttributesReportMapper.selectAllNew(paramMap);
            int total  = 0;
            for (SurveyCaseAttributesReport surveyCaseAttributesReport : list) {
                total +=  (surveyCaseAttributesReport.getEntrustCaseNum() ==  null ? 0 : surveyCaseAttributesReport.getEntrustCaseNum());
            }
            map.put("total",total);//总数量 用于计算比列
            map.put("list",list);
        }
        else if ("keyTar".equals(dataTable)){//关键指标雷达图
            List<SurveyConsignorReport> list = surveyConsignorReportMapper.selectEntrustKeyTar(paramMap);
            map.put("list",list);
            int entrusts = surveyConsignorReportMapper.selectEntrustKeyTarEntrusts(paramMap);
            map.put("entrusts",entrusts);//机构数量
        }
        else if ("price".equals(dataTable)){
            List<SurveyReportEntrustPriceDTO> list = surveyCrossRegionReportMapper.selectEntrustPrice(paramMap);
            List<SurveyReportEntrustPriceXDTO> xObject = surveyCrossRegionReportMapper.selectEntrustXPrice(paramMap);
            List<SurveyReportEntrustPriceYDTO> yObject = surveyCrossRegionReportMapper.selectEntrustYPrice(paramMap);
            map.put("list",list);
            map.put("xObject",xObject);//x轴最大值 最小值
            map.put("yObject",yObject);//y轴最大值 最小值

//            List<SurveyReportEntrustTextDTO> ageList = surveyCrossRegionReportMapper.selectEntrustAge(paramMap);
//            List<SurveyReportEntrustTextDTO> sexList = surveyCrossRegionReportMapper.selectEntrustSex(paramMap);
//            List<SurveyReportEntrustTextDTO> safeTypeList = surveyCrossRegionReportMapper.selectEntrustSafeType(paramMap);
            map.put("ageList",null);
            map.put("sexList",null);
            map.put("safeTypeList",null);
        }
        else if ("source".equals(dataTable)){
            List<SurveyReportEntrustSourceDTO> list = surveyCaseAttributesReportMapper.selectAllSourceNew(paramMap);
            int total  = 0;
            for (SurveyReportEntrustSourceDTO source : list) {
                total +=  (source.getNum() ==  null ? 0 : source.getNum());
            }
            map.put("total",total);//总数量 用于计算比列
            map.put("list",list);
        }
        return map;
    }

    //机构"org"、调查员数据"survey"
    private Map getOrgMapNew(String dataType,String dataTable,Map paramMap,Map map,Long surveyOrgId,ApiRequest apiRequest){
        if("org".equals(dataType)) {
            if ("bas".equals(dataTable)) {
                //基础信息
                SurveyReportInvestigatorOrgDTO bas = surveyInvestigatorOrgReportMapper.selectBas(paramMap);

                Double basMoneyNotAcc = surveyInvestigatorOrgReportMapper.selectBasMoneyNotAcc(surveyOrgId);//未结算费用
                bas.setBasMoneyNotAcc(basMoneyNotAcc);
                //机构信息
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyOrgId);
                bas.setSurveyFranchisee(surveyFranchisee);

                //关键指标
                SurveyInvestigatorOrgReport report = null;
                List<SurveyInvestigatorOrgReport> surveyInvestigatorOrgReports = surveyInvestigatorOrgReportMapper.selectAllNew(paramMap);
                if (surveyInvestigatorOrgReports.size() > 0){
                    report = surveyInvestigatorOrgReports.get(0);
                    if (report.getInsuranceCheckCaseNum() == 0) {
                        report.setReturnRate(0D);
                        report.setPositiveRate(0D);
                        report.setMarkErrorRate(0D);
                    }else{
                        report.setReturnRate(new Double(report.getReturnNum()) / new Double(report.getInsuranceCheckCaseNum()));
                        report.setPositiveRate(new Double(report.getSunNum()) / new Double(report.getInsuranceCheckCaseNum()));
                        report.setMarkErrorRate(new Double(report.getMarkErrorNum()) / new Double(report.getInsuranceCheckCaseNum()));
                    }
                }else{
                    report = new SurveyInvestigatorOrgReport();
                }
                //关键指标 -- 新增委派字段
                int newSend = surveyRiskCaseInfoMapper.selectOrgNewSendCount(paramMap);
                report.setNewSend(newSend);
                report.setHbCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(report.getNewSend(),report.getHbCaseNum())));//环比
                report.setTbCaseNumRate(DecimalUtil.fourDecimalTOFourFromFive(rate(report.getNewSend(),report.getTbCaseNum())));//同比

                //委托方价格
                Double entrustAgreementMoney=surveyInvestigatorOrgReportMapper.selectEntrustAgreementMoney(paramMap);
                report.setEntrustAgreementMoney(entrustAgreementMoney);

                //总数
                int num = surveyInvestigatorOrgReportMapper.selectReportTarOverData(paramMap);
                //绝对超期数量
                paramMap.put("over",1);
                int num1 = surveyInvestigatorOrgReportMapper.selectReportTarOverData(paramMap);
                //相对超期数量
                paramMap.put("over",2);
                int num2 = surveyInvestigatorOrgReportMapper.selectReportTarOverData(paramMap);
                if (num == 0){
                    report.setUtterOverRate(0D);
                    report.setOpposeOverRate(0D);
                }else{
                    report.setUtterOverRate(DecimalUtil.twoDecimalTOFourFromFive((float) num1 /  num));
                    report.setOpposeOverRate(DecimalUtil.twoDecimalTOFourFromFive((float) num2 / num));
                }
                bas.setSurveyInvestigatorOrgReport(report);

                map.put("bas", bas);

                //调查费到账情况
                Long currentUserId = getCurrentUserId(apiRequest);
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (surveyInvestigator != null){
                    Map<String,Object> paramMap2 =  new HashMap<String,Object>();
                    paramMap2.put("orgId",surveyInvestigator.getOrgId());
                    SurveyPayInfoDTO surveyPayInfoDTO = surveyPayInfoMapper.selectByInfo(paramMap2);
                    if (surveyPayInfoDTO != null){
                        paramMap2.clear();
                        paramMap2.put("payId",surveyPayInfoDTO.getId());
                        num = surveyPayInfoDetailMapper.listSize(paramMap2);
                        surveyPayInfoDTO.setSurveyCaseNum(num);
                        surveyPayInfoDTO.setStartTimeStr(new SimpleDateFormat("yyyy年MM月dd日").format(surveyPayInfoDTO.getAppStartDate()));
                        surveyPayInfoDTO.setEndTimeStr(new SimpleDateFormat("yyyy年MM月dd日").format(surveyPayInfoDTO.getAppEndDate()));
                        UserInfo payUser = userInfoMapper.selectByPrimaryKey(surveyPayInfoDTO.getPayUserId());
                        if (payUser != null){
                            surveyPayInfoDTO.setPayUserTel(payUser.getUserTel());
                        }
                    }
                    map.put("surveyPayInfo",surveyPayInfoDTO);
                }

            }
            else if ("task".equals(dataTable)) { //任务类型
                //原因：页面上“案件状态的value值”，与数据库survey_task_report的search_type对应，但值错了
                Long checkType =apiRequest.getLong("checkType");
                if(checkType ==1){
                    paramMap.put("searchType",2);
                }else if(checkType ==2){
                    paramMap.put("searchType",1);
                }

                List<SurveyTaskReport> list = surveyTaskReportMapper.selectOrg(paramMap);
                for (SurveyTaskReport surveyTaskReport : list) {
                    paramMap.put("parentId", surveyTaskReport.getParentId());
                    List<SurveyTaskReport> surveyTaskReports = surveyTaskReportMapper.selectOrgParentId(paramMap);
                    surveyTaskReport.setSurveyTaskReports(surveyTaskReports);
                }
                map.put("list", list);
            }
            else if ("service".equals(dataTable)){//业务类型
                List<SurveyServiceOrgReport> list = surveyServiceOrgReportMapper.selectAllNew(paramMap);
                List<SurveyServiceOrgReport> listSun = surveyServiceOrgReportMapper.selectAllNewSun(paramMap);
                int total  = 0;
                for (SurveyServiceOrgReport surveyServiceOrgReport : list) {
                    total +=  (surveyServiceOrgReport.getCheckCaseNum() == null ? 0 : surveyServiceOrgReport.getCheckCaseNum());
                    for (SurveyServiceOrgReport serviceOrgReport : listSun) {
                        if (surveyServiceOrgReport.getServiceTypeId().intValue() == serviceOrgReport.getServiceTypeId().intValue()) {
                            surveyServiceOrgReport.setPositiveNum(serviceOrgReport.getPositiveNum());
                        }
                    }
                }
                map.put("total",total);//总数量 用于计算比列
                map.put("list",list);
            }
            else if ("area".equals(dataTable)){
                List<SurveyReportAreaDTO> list = surveyCrossRegionReportMapper.selectOrgArea(paramMap);
                int total  = 0;
                for (SurveyReportAreaDTO surveyReportAreaDTO : list) {
                    total +=  surveyReportAreaDTO.getNum();
                }
                map.put("total",total);//总数量 用于计算比列
                map.put("list",list);
                int maxNum = 0;
                Object sortType = paramMap.get("sortType");
                if (sortType != null) {
                    if ("1".equals(sortType.toString())){//如果是升序 取最后一条
                        maxNum = list.get(list.size() - 1).getNum();
                    }else{
                        if (list.size() > 0){
                            maxNum = list.get(0).getNum();
                        }
                    }
                }else{
                    if (list.size() > 0){
                        maxNum = list.get(0).getNum();
                    }
                }
                map.put("maxNum",maxNum);
            }else if ("area-son".equals(dataTable)){//区域子集
                List<SurveyReportAreaDTO> list = surveyCrossRegionReportMapper.selectOrgAreaCity(paramMap);
                int total  = 0;
                for (SurveyReportAreaDTO surveyReportAreaDTO : list) {
                    total +=  surveyReportAreaDTO.getNum();
                }
                map.put("total",total);//总数量 用于计算比列
                map.put("list",list);
                Long cityId = (Long) paramMap.get("cityId");
                map.put("province",-1);//省ID
                int maxNum = 0;
                Object sortType = paramMap.get("sortType");
                if (sortType != null) {
                    if ("1".equals(sortType.toString())){//如果是升序 取最后一条
                        maxNum = list.get(list.size() - 1).getNum();
                    }else{
                        if (list.size() > 0){
                            maxNum = list.get(0).getNum();
                        }
                    }
                }else{
                    if (list.size() > 0){
                        maxNum = list.get(0).getNum();
                    }
                }
                map.put("maxNum",maxNum);
            }else if ("trend".equals(dataTable)){ //分值曲线， 调查费曲线
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Calendar cld = Calendar.getInstance();
                    List<String> dates = new ArrayList<String>();
                    Date time1 = null,time2 = null;
                    Object startTime = paramMap.get("startTime");
                    Object endTime = paramMap.get("endTime");
                    if (startTime != null && endTime != null){
                        time1 = simpleDateFormat.parse(simpleDateFormat.format(startTime));
                        time2 = simpleDateFormat.parse(simpleDateFormat.format(endTime));
                    }else{
                        //报表最小时间 报表最大时间
                        Date minDate = surveyInvestigatorOrgReportMapper.selectMinReportDate(paramMap);
                        Date maxDate = new Date();
                        time1 = minDate == null ? new Date() : simpleDateFormat.parse(simpleDateFormat.format(minDate));
                        time2 = maxDate == null ? new Date() : simpleDateFormat.parse(simpleDateFormat.format(maxDate));
                    }
                    int days = (int) ((time2.getTime() - time1.getTime()) / (1000*3600*24));
                    for (int i = 0; i < days + 1; i++) {
                        cld.setTime(time1);
                        cld.add(Calendar.DATE, i);
                        dates.add(simpleDateFormat.format(cld.getTime()));
                    }
                    map.put("dates",dates);
                    List<SurveyInvestigatorOrgReport> reports = surveyInvestigatorOrgReportMapper.selectAllTrendNew(paramMap);
                    List<SurveyInvestigatorOrgReport> list = new ArrayList<>();
                    for (String date : dates) {
                        SurveyInvestigatorOrgReport report = new SurveyInvestigatorOrgReport();
                        report.setReportDate(simpleDateFormat.parse(date));
                        report.setReportDateStr(simpleDateFormat.format(report.getReportDate()));
                        for (SurveyInvestigatorOrgReport surveyInvestigatorOrgReport : reports) {
                            if (surveyInvestigatorOrgReport.getReportDate() != null) {
                                if (simpleDateFormat.format(surveyInvestigatorOrgReport.getReportDate()).equals(date)) {
                                    report.setScore(surveyInvestigatorOrgReport.getScore());
                                    report.setInvestigationMoney(surveyInvestigatorOrgReport.getInvestigationMoney());
                                }
                            }
                        }
                        list.add(report);
                    }
                    map.put("list",list);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if ("keyTar".equals(dataTable)){//关键指标雷达图
                List<SurveyInvestigatorOrgReport> list = surveyInvestigatorOrgReportMapper.selectOrgKeyTar(paramMap);
                map.put("list",list);
                int orgs = surveyInvestigatorOrgReportMapper.selectOrgKeyTarOrgs(paramMap);
                map.put("orgs",orgs);//机构数量
            }
            else if("userList".equals(dataTable)){//所有调查员
                map = new HashMap<>();
                map.put("surveyOrgId",surveyOrgId);
                List<SurveyInvestigator> surveyInvestigators = surveyInvestigatorMapper.list(map);
                map = new HashMap<>();
                map.put("roleId",50);
                List<BusUserRole> busUserRoles = busUserRoleMapper.selectBusInfo(map);
                List<SurveyInvestigator> surveyInvestigatorList =  new ArrayList<>();
                for (SurveyInvestigator surveyInvestigator : surveyInvestigators) {
                    for (BusUserRole busUserRole : busUserRoles) {
                        if (surveyInvestigator.getUserId().intValue() == busUserRole.getUserId().intValue()) {
                            surveyInvestigatorList.add(surveyInvestigator);
                        }
                    }
                }
                map.put("surveyInvestigators", surveyInvestigatorList);
            }
            else if("userReport".equals(dataTable)){//调查员排名
                List<SurveyInvestigatorUserReport> surveyInvestigatorUserReports = surveyInvestigatorUserReportMapper.selectAllNew(paramMap);
                for (SurveyInvestigatorUserReport surveyInvestigatorUserReport : surveyInvestigatorUserReports) {
                    if (surveyInvestigatorUserReport.getInsuranceCheckCaseNum() == 0) {
                        surveyInvestigatorUserReport.setPositiveRate(0D);
                        surveyInvestigatorUserReport.setLossEfficiencyRate(0D);
                    }else{
                        surveyInvestigatorUserReport.setPositiveRate(new Double(surveyInvestigatorUserReport.getSunNum() / new Double(surveyInvestigatorUserReport.getInsuranceCheckCaseNum())));
                        surveyInvestigatorUserReport.setLossEfficiencyRate(new Double(surveyInvestigatorUserReport.getLossNum() / new Double(surveyInvestigatorUserReport.getInsuranceCheckCaseNum())));
                    }
                    if (surveyInvestigatorUserReport.getDirectionNum() == 0){
                        surveyInvestigatorUserReport.setHegeRate(0D);
                        surveyInvestigatorUserReport.setYouRate(0D);
                        surveyInvestigatorUserReport.setChaRate(0D);
                    }else{
                        surveyInvestigatorUserReport.setHegeRate(fourRate(new Double(surveyInvestigatorUserReport.getHege()),new Double(surveyInvestigatorUserReport.getDirectionNum())));
                        surveyInvestigatorUserReport.setYouRate(fourRate(new Double(surveyInvestigatorUserReport.getYou()),new Double(surveyInvestigatorUserReport.getDirectionNum())));
                        surveyInvestigatorUserReport.setChaRate(fourRate(new Double(surveyInvestigatorUserReport.getCha()),new Double(surveyInvestigatorUserReport.getDirectionNum())));
                    }
                }
                map.put("investigatorUsers", surveyInvestigatorUserReports);
            }
            else if("roic".equals(dataTable)) {//保司排名
                String startTime=apiRequest.getString("startTime");
                String endTime=apiRequest.getString("endTime");
                String entrustOrgIds=apiRequest.getString("entrustOrgIds");
                Integer checkType=apiRequest.getInt("checkType");
                Integer sort=apiRequest.getInt("sort");
                String desc=apiRequest.getString("desc");
                paramMap=new HashMap();
                paramMap.put("startTime",startTime);
                paramMap.put("surveyOrgId",surveyOrgId);
                paramMap.put("endTime",endTime);
                paramMap.put("entrustOrgIds",entrustOrgIds);
                paramMap.put("checkType",checkType);
                paramMap.put("sort",sort);
                paramMap.put("desc",desc);
                List<AssignOrgDTO> assignOrgDTOList=surveyAssignOrgMapper.selectRoic(paramMap);
                Integer total=surveyRiskCaseInfoMapper.selectCount(paramMap);
                for (AssignOrgDTO assignOrgDTO:assignOrgDTOList) {
                    if(total==0){
                        assignOrgDTO.setPositiveRate(0D);
                        assignOrgDTO.setReturnRate(0D);
                        assignOrgDTO.setAbsolutelyRate(0D);
                        assignOrgDTO.setRelativeRate(0D);
                    }else {
                        if (assignOrgDTO.getPositiveNum() != null) {
                            Double positiveRate = assignOrgDTO.getPositiveNum() / total;
                            assignOrgDTO.setPositiveRate(positiveRate);
                        }
                        if (assignOrgDTO.getReturnNum() != null) {
                            Double returnRate = assignOrgDTO.getReturnNum() / total;
                            assignOrgDTO.setReturnRate(returnRate);
                        }
                        if(assignOrgDTO.getAbsolutelyNum() != null ){
                            Double absolutelyRate = assignOrgDTO.getAbsolutelyNum() / total;
                            assignOrgDTO.setAbsolutelyRate(absolutelyRate);
                        }
                        if(assignOrgDTO.getRelativeNum() != null ){
                            Double relativeRate = assignOrgDTO.getRelativeNum() / total;
                            assignOrgDTO.setRelativeRate(relativeRate);
                        }
                    }
                }
                SurveyFranchisee surveyFranchisee=surveyFranchiseeMapper.selectByPrimaryKey(surveyOrgId);

                map.put("insuranceType",surveyFranchisee.getInsuranceType());
                map.put("assignOrgDTOList",assignOrgDTOList);
            }
        }

        return map;
    }

    //机构"org"、调查员数据"survey"
    private Map getSurveyMapNew(String dataType,String dataTable,Map paramMap,Map map,Long surveyUserId,ApiRequest apiRequest){
        if ("bas".equals(dataTable)) {
            //基础信息
            SurveyReportInvestigatorUserDTO bas = surveyInvestigatorUserReportMapper.selectBas(paramMap);
            //调查人员信息
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyUserId);
            bas.setSurveyInvestigator(surveyInvestigator);

            //关键指标
            SurveyInvestigatorUserReport report = null;
            List<SurveyInvestigatorUserReport> surveyInvestigatorUserReports = surveyInvestigatorUserReportMapper.selectAllNew(paramMap);
            if (surveyInvestigatorUserReports.size() > 0) {
                report = surveyInvestigatorUserReports.get(0);
                if (report.getInsuranceCheckCaseNum() == 0) {
                    report.setPositiveRate(0D);
                    report.setLossEfficiencyRate(0D);
                    report.setReturnRate(0D);
                }else{
                    report.setPositiveRate(new Double(report.getSunNum() / new Double(report.getInsuranceCheckCaseNum())));
                    report.setLossEfficiencyRate(new Double(report.getLossNum() / new Double(report.getInsuranceCheckCaseNum())));
                    report.setReturnRate(new Double(report.getReturnNum() / new Double(report.getInsuranceCheckCaseNum())));
                }
            }else{
                report = new SurveyInvestigatorUserReport();
            }
            //关键指标 -- 新增委派字段
            int newSend = surveyRiskCaseInfoMapper.selectUserNewSendCount(paramMap);
            report.setNewSend(newSend);
            bas.setSurveyInvestigatorUserReport(report);

            map.put("bas", bas);
            //分值清单
            map.put("surveyRiskCaseInfoExportDto", null);

        }else if ("task".equals(dataTable)) { //任务类型
            List<SurveyTaskReport> list = surveyTaskReportMapper.selectUser(paramMap);
            for (SurveyTaskReport surveyTaskReport : list) {
                paramMap.put("parentId", surveyTaskReport.getParentId());
                List<SurveyTaskReport> surveyTaskReports = surveyTaskReportMapper.selectUserParentId(paramMap);
                surveyTaskReport.setSurveyTaskReports(surveyTaskReports);
            }
            map.put("list", list);
        }else if ("service".equals(dataTable)){//业务类型
            List<SurveyServiceUserReport> list = surveyServiceUserReportMapper.selectAllNew(paramMap);
            List<SurveyServiceUserReport> listSun = surveyServiceUserReportMapper.selectAllNewSun(paramMap);
            int total  = 0;
            for (SurveyServiceUserReport surveyServiceUserReport : list) {
                total +=  (surveyServiceUserReport.getCheckCaseNum() == null ? 0 : surveyServiceUserReport.getCheckCaseNum());
                for (SurveyServiceUserReport serviceUserReport : listSun) {
                    if (surveyServiceUserReport.getServiceTypeId().intValue() == serviceUserReport.getServiceTypeId().intValue()) {
                        surveyServiceUserReport.setPositiveNum(serviceUserReport.getPositiveNum());
                    }
                }
            }
            map.put("total",total);//总数量 用于计算比列
            map.put("list",list);
        }
        else if ("area".equals(dataTable)){
            List<SurveyReportAreaDTO> list = surveyCrossRegionReportMapper.selectSurveyArea(paramMap);
            int total  = 0;
            for (SurveyReportAreaDTO surveyReportAreaDTO : list) {
                total +=  surveyReportAreaDTO.getNum();
            }
            map.put("total",total);//总数量 用于计算比列
            map.put("list",list);
            int maxNum = 0;
            Object sortType = paramMap.get("sortType");
            if (sortType != null) {
                if ("1".equals(sortType.toString())){//如果是升序 取最后一条
                    maxNum = list.get(list.size() - 1).getNum();
                }else{
                    if (list.size() > 0){
                        maxNum = list.get(0).getNum();
                    }
                }
            }else{
                if (list.size() > 0){
                    maxNum = list.get(0).getNum();
                }
            }
            map.put("maxNum",maxNum);
        }
        else if ("trend".equals(dataTable)){ //分值曲线， 调查费曲线
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Calendar cld = Calendar.getInstance();
                List<String> dates = new ArrayList<String>();
                Date time1 = null,time2 = null;
                Object startTime = paramMap.get("startTime");
                Object endTime = paramMap.get("endTime");
                if (startTime != null && endTime != null){
                    time1 = simpleDateFormat.parse(simpleDateFormat.format(startTime));
                    time2 = simpleDateFormat.parse(simpleDateFormat.format(endTime));
                }else{
                    //报表最小时间 报表最大时间
                    Date minDate = surveyInvestigatorUserReportMapper.selectMinReportDate(paramMap);
                    Date maxDate = new Date();
                    time1 = minDate == null ? new Date() : simpleDateFormat.parse(simpleDateFormat.format(minDate));
                    time2 = maxDate == null ? new Date() : simpleDateFormat.parse(simpleDateFormat.format(maxDate));
                }
                int days = (int) ((time2.getTime() - time1.getTime()) / (1000*3600*24));
                for (int i = 0; i < days + 1; i++) {
                    cld.setTime(time1);
                    cld.add(Calendar.DATE, i);
                    dates.add(simpleDateFormat.format(cld.getTime()));
                }
                map.put("dates",dates);
                List<SurveyInvestigatorUserReport> reports = surveyInvestigatorUserReportMapper.selectAllTrendNew(paramMap);
                List<SurveyInvestigatorOrgReport> list = new ArrayList<>();
                for (String date : dates) {
                    SurveyInvestigatorOrgReport report = new SurveyInvestigatorOrgReport();
                    report.setReportDate(simpleDateFormat.parse(date));
                    report.setReportDateStr(simpleDateFormat.format(report.getReportDate()));
                    for (SurveyInvestigatorUserReport surveyInvestigatorUserReport : reports) {
                        if (surveyInvestigatorUserReport.getReportDate() != null) {
                            if (simpleDateFormat.format(surveyInvestigatorUserReport.getReportDate()).equals(date)) {
                                report.setScore(surveyInvestigatorUserReport.getScore());
                                report.setInvestigationMoney(surveyInvestigatorUserReport.getInvestigationMoney());
                            }
                        }
                    }
                    list.add(report);
                }
                map.put("list",list);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if ("keyTar".equals(dataTable)){//关键指标雷达图
            List<SurveyInvestigatorUserReport> list = surveyInvestigatorUserReportMapper.selectSurveyKeyTar(paramMap);
            map.put("list",list);
            int surveys = surveyInvestigatorUserReportMapper.selectSurveyKeyTarSurveys(paramMap);
            map.put("surveys",surveys);//机构数量
        }
        return map;
    }

    @ApiMethod(descript = "对账清单", value = "backend-survey-entrust-acc-list", apiParams = { })
    @Override
    public ApiResponse getAccList(ApiRequest apiRequest){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String menuCode = apiRequest.getString("menuCode");
        String reportBtn  = apiRequest.getString("reportBtn");//report 表示导出。 导出不分页
        if (!"report".equals(reportBtn)){
            setBackendPageSize(apiRequest);
        }
        if ("entrust".equals(menuCode)){
            String startTime = apiRequest.getString("startTime");
            String endTime = apiRequest.getString("endTime");
            try {
                if (startTime != null){
                    apiRequest.put("startTime",simpleDateFormat.parse(startTime));
                }
                if (endTime !=  null){
                    apiRequest.put("endTime",simpleDateFormat.parse(endTime));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            List<SurveyAccEntrustDTO> accs = surveyRiskCaseInfoMapper.selectAccEntrustList(apiRequest);
            int count = surveyRiskCaseInfoMapper.selectAccEntrustListSize(apiRequest);
            List<SurveyCaseDirection> directions = surveyCaseDirectionMapper.selectAccEntrustListDirections(apiRequest);
            for (SurveyAccEntrustDTO acc : accs) {
                List<SurveyCaseDirection> list = new ArrayList<SurveyCaseDirection>();
                for (SurveyCaseDirection direction : directions) {
                    if (acc.getId().intValue() == direction.getSurveyInfoId().intValue()) {
                        list.add(direction);
                    }
                }
                acc.setDirections(list);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,accs);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }




    /**
     * 狄大人报表 -- 分值订单导出（）
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "狄大人报表 -- 分值订单导出", value = "backend-survey-report-score-export", apiParams = { })
    @Override
    public ApiResponse reportScoreExport(ApiRequest apiReq) {

        String dataType = apiReq.getString("dataType");
        if("org".equals(dataType)){
            apiReq.put("orgAttr",1);
            List<SurveyRiskCaseInfoExportDto> caseInfoExportDtos = surveyRiskCaseInfoMapper.orgReportScoreList(apiReq);
//            for (SurveyRiskCaseInfoExportDto caseInfoExportDto : caseInfoExportDtos) {
//                Map<String,Object> map  =  new HashMap<>();
//                map.put("surveyInfoId",caseInfoExportDto.getCaseInfoId());
//                List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtos = surveyInvestigatorCaseMapper.list(map);
//                for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto : surveyInvestigatorCaseDtos) {
//                    map  =  new HashMap<>();
//                    map.put("surveyInfoId",surveyInvestigatorCaseDto.getId());
//                    List<SurveyCaseDirectionDto> surveyCaseDirections = surveyCaseDirectionMapper.list(map);
//                    surveyInvestigatorCaseDto.setSurveyCaseDirections(surveyCaseDirections);
//                }
//                caseInfoExportDto.setSurveyInvestigatorCases(surveyInvestigatorCaseDto);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Map<String,Object> map = new HashMap<>();
                map.put("surveyOrgId", apiReq.getString("surveyOrgId"));
                try {
                    map.put("startTime",simpleDateFormat.parse(apiReq.getString("startTime")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    map.put("endTime",simpleDateFormat.parse(apiReq.getString("endTime")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                map.put("checkType",apiReq.getString("checkType"));
                List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.selectAllBySurveyOrgId(map);
                for (SurveyRiskCaseInfoExportDto surveyRiskCaseInfoExportDto : caseInfoExportDtos) {
                    List<SurveyCaseDirection> items = new ArrayList<SurveyCaseDirection>();

                    Double scoreSum = 0D;//总分值
                    Double entrustMoneySum = 0D;//总金额
                    for (SurveyCaseDirection directionList : surveyCaseDirections) {
                        if(surveyRiskCaseInfoExportDto.getCaseInfoId().intValue() == directionList.getSurveyInfoId().intValue()){
                            items.add(directionList);
                            entrustMoneySum = entrustMoneySum + (directionList.getSurveyMoney() == null ? 0D : directionList.getSurveyMoney());
                        }
                    }

                    //深度案件的“总金额”，直接获取案件的金额，而不是方向金额的总和
                    if(surveyRiskCaseInfoExportDto.getServicesId() !=null &&surveyRiskCaseInfoExportDto.getServicesId()==13){
                        surveyRiskCaseInfoExportDto.setEntrustMoneySum(surveyRiskCaseInfoExportDto.getSurveyMoneySubmit() + surveyRiskCaseInfoExportDto.getSurveryReLossesSubmit());
                    }else{
                        surveyRiskCaseInfoExportDto.setEntrustMoneySum(entrustMoneySum);
                    }

                    surveyRiskCaseInfoExportDto.setSurveyCaseDirections(items);
                    //获取时效
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfoExportDto.getEntrustOrgId());
                    int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
                    if(surveyConsignor!=null){
                        efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
                    }

                    //案件状态（未提交保司审核，提交保司审核）
                    if(surveyRiskCaseInfoExportDto.getEntrustReportStartDate() == null){ //未提交保司审核
                        //“当前时间”与“案件截止时间”相比：
                        Date endTime = surveyRiskCaseInfoExportDto.getEndTime()==null ? new Date() : surveyRiskCaseInfoExportDto.getEndTime();
                        if(new Date().before(endTime)){
                            int days = GetWorkDay.calLeaveDays(new Date(), endTime,efficiencyAttr);
                            surveyRiskCaseInfoExportDto.setEfficiency(days);
                        }else{
                            int days = GetWorkDay.calLeaveDays(endTime, new Date(),efficiencyAttr);
                            surveyRiskCaseInfoExportDto.setEfficiency(days);
                        }
                    }
                    //提交保司审核
                    else{
                        //“委托时间”
                        Date entrustTime = surveyRiskCaseInfoExportDto.getEntrustTime() == null ? new Date() : surveyRiskCaseInfoExportDto.getEntrustTime();
                        //“案件截止时间”
                        Date endTime = surveyRiskCaseInfoExportDto.getEndTime()==null ? new Date() : surveyRiskCaseInfoExportDto.getEndTime();
                        //“提交保司审核时间”
                        Date entrustReportStartTime = surveyRiskCaseInfoExportDto.getEntrustReportStartDate() == null ? new Date() : surveyRiskCaseInfoExportDto.getEntrustReportStartDate();
                        int days = GetWorkDay.calLeaveDays(entrustTime, entrustReportStartTime,efficiencyAttr);
                        surveyRiskCaseInfoExportDto.setEfficiency(days);
                    }
                }
            return new ApiResponse(ApiMsgEnum.SUCCESS,null,caseInfoExportDtos);


        }else if("survey".equals(dataType)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            apiReq.put("orgAttr",1);
            List<SurveyRiskCaseInfoExportDto> caseInfoExportDtos = surveyRiskCaseInfoMapper.surveyReportScoreList(apiReq);
            Map<String,Object> map = new HashMap<>();
            map.put("surveyUserId",apiReq.getString("surveyUserId"));
            try {
                map.put("startTime",simpleDateFormat.parse(apiReq.getString("startTime")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                map.put("endTime",simpleDateFormat.parse(apiReq.getString("endTime")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            map.put("checkType",apiReq.getString("checkType"));
            List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.selectAllBySurveyUserId(map);
//            List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.list(map);
            for (SurveyRiskCaseInfoExportDto surveyRiskCaseInfoExportDto : caseInfoExportDtos) {
                List<SurveyCaseDirection> items = new ArrayList<SurveyCaseDirection>();
                for (SurveyCaseDirection directionList : surveyCaseDirections) {
                    if(surveyRiskCaseInfoExportDto.getInvestigatorCaseId().intValue() == directionList.getSurveyInvestigatorCaseId().intValue()){
                        items.add(directionList);
                    }
                }
                surveyRiskCaseInfoExportDto.setSurveyCaseDirections(items);

                //获取时效
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfoExportDto.getEntrustOrgId());
                int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
                if(surveyConsignor!=null){
                    efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
                }

                //案件状态（未提交保司审核，提交保司审核）
                if(surveyRiskCaseInfoExportDto.getEntrustReportStartDate() == null){ //未提交保司审核
                    //“当前时间”与“案件截止时间”相比：
                    Date endTime = surveyRiskCaseInfoExportDto.getEndTime()==null ? new Date() : surveyRiskCaseInfoExportDto.getEndTime();
                    if(new Date().before(endTime)){
                        int days = GetWorkDay.calLeaveDays(new Date(), endTime,efficiencyAttr);
                        surveyRiskCaseInfoExportDto.setEfficiency(days);
                    }else{
                        int days = GetWorkDay.calLeaveDays(endTime, new Date(),efficiencyAttr);
                        surveyRiskCaseInfoExportDto.setEfficiency(days);
                    }
                }
                //提交保司审核
                else{
                    //“委托时间”
                    Date entrustTime = surveyRiskCaseInfoExportDto.getEntrustTime() == null ? new Date() : surveyRiskCaseInfoExportDto.getEntrustTime();
                    //“案件截止时间”
                    Date endTime = surveyRiskCaseInfoExportDto.getEndTime()==null ? new Date() : surveyRiskCaseInfoExportDto.getEndTime();
                    //“提交保司审核时间”
                    Date entrustReportStartTime = surveyRiskCaseInfoExportDto.getEntrustReportStartDate() == null ? new Date() : surveyRiskCaseInfoExportDto.getEntrustReportStartDate();
                    int days = GetWorkDay.calLeaveDays(entrustTime, entrustReportStartTime,efficiencyAttr);
                    surveyRiskCaseInfoExportDto.setEfficiency(days);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,null,caseInfoExportDtos);
        }



        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);

    }

    @ApiMethod(descript = "狄大人报表 -- 分值订单", value = "backend-survey-report-score-list", apiParams = { })
    @Override
    public ApiResponse reportScore(ApiRequest apiRequest){
        setBackendPageSize(apiRequest);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String menuCode = apiRequest.getString("menuCode");
        String startTime = apiRequest.getString("startTime");
        String endTime = apiRequest.getString("endTime");
        try {
            if (startTime != null){
                apiRequest.put("startTime",simpleDateFormat.parse(startTime));
            }
            if (endTime !=  null){
                apiRequest.put("endTime",simpleDateFormat.parse(endTime));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<SurveyRiskCaseInfoExportDto> caseInfoExportDtos = new ArrayList<>();
        int count =0;
        if ("org".equals(menuCode)){
            apiRequest.put("orgAttr",1);
            caseInfoExportDtos = surveyRiskCaseInfoMapper.orgReportScoreList(apiRequest);
            count = surveyRiskCaseInfoMapper.orgReportScoreListSize(apiRequest);
        }else if ("survey".equals(menuCode)){
            apiRequest.put("orgAttr",1);
            caseInfoExportDtos = surveyRiskCaseInfoMapper.surveyReportScoreList(apiRequest);
            count = surveyRiskCaseInfoMapper.surveyReportScoreListSize(apiRequest);
        }

        if("org".equals(menuCode)) {
            //所有调查方向list
            Map<String, Object> map = new HashMap<>();
            map.put("surveyOrgId", apiRequest.getString("surveyOrgId"));
            try {
                map.put("startTime",simpleDateFormat.parse(startTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                map.put("endTime",simpleDateFormat.parse(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            map.put("checkType",apiRequest.getString("checkType"));
            List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.selectAllBySurveyOrgId(map);

            for (SurveyRiskCaseInfoExportDto surveyRiskCaseInfoExportDto : caseInfoExportDtos) {
                //调查时效
                int days = GetWorkDay.calLeaveDays(surveyRiskCaseInfoExportDto.getAssignDate() == null ? new Date() : surveyRiskCaseInfoExportDto.getAssignDate(), surveyRiskCaseInfoExportDto.getEntrustReportStartDate() == null ? new Date() : surveyRiskCaseInfoExportDto.getEntrustReportStartDate(),surveyRiskCaseInfoExportDto.getConsignorEfficiencyAttr());
                days = Math.abs(days);
                surveyRiskCaseInfoExportDto.setEfficiency(days);
                List<SurveyCaseDirection> items = new ArrayList<SurveyCaseDirection>();
                Double scoreSum = 0D;//总分值
                Double entrustMoneySum = 0D;//总金额
                for (SurveyCaseDirection directionList : surveyCaseDirections) {
                    if (surveyRiskCaseInfoExportDto.getCaseInfoId().intValue() == directionList.getSurveyInfoId().intValue()) {
                        items.add(directionList);
//                        scoreSum = scoreSum + (directionList.getScore() == null ? 0D : directionList.getScore());
//                        entrustMoneySum = entrustMoneySum + (directionList.getEntrustMoney() == null ? 0D : directionList.getEntrustMoney());
                        entrustMoneySum = entrustMoneySum + (directionList.getSurveyMoney() == null ? 0D : directionList.getSurveyMoney());
                    }
                }
                surveyRiskCaseInfoExportDto.setScoreSum(scoreSum);
                //深度案件的“总金额”，直接获取案件的金额，而不是方向金额的总和
                if(surveyRiskCaseInfoExportDto.getServicesId() !=null &&surveyRiskCaseInfoExportDto.getServicesId()==13){
                    surveyRiskCaseInfoExportDto.setEntrustMoneySum(surveyRiskCaseInfoExportDto.getSurveyMoneySubmit() + surveyRiskCaseInfoExportDto.getSurveryReLossesSubmit());
                }else{
                    surveyRiskCaseInfoExportDto.setEntrustMoneySum(entrustMoneySum);
                }

                surveyRiskCaseInfoExportDto.setSurveyCaseDirections(items);

                if(surveyRiskCaseInfoExportDto.getEntrustReportStartDate()!=null && surveyRiskCaseInfoExportDto.getEndTime()!=null){
                    int i = surveyRiskCaseInfoExportDto.getEntrustReportStartDate().compareTo(surveyRiskCaseInfoExportDto.getEndTime());
                    if(i > 0){ //超时
                        surveyRiskCaseInfoExportDto.setIsOverTime(true);
                    }
                }
            }
        }
        else if("survey".equals(menuCode)) {
            Map<String,Object> map = new HashMap<>();
            map.put("surveyUserId",apiRequest.getString("surveyUserId"));
            try {
                map.put("startTime",simpleDateFormat.parse(startTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                map.put("endTime",simpleDateFormat.parse(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            map.put("checkType",apiRequest.getString("checkType"));
            List<SurveyCaseDirection> surveyCaseDirections = surveyCaseDirectionMapper.selectAllBySurveyUserId(map);
            for (SurveyRiskCaseInfoExportDto surveyRiskCaseInfoExportDto : caseInfoExportDtos) {
                int days = GetWorkDay.calLeaveDays(surveyRiskCaseInfoExportDto.getAssignDate() == null ? new Date() : surveyRiskCaseInfoExportDto.getAssignDate(), surveyRiskCaseInfoExportDto.getEntrustReportStartDate() == null ? new Date() : surveyRiskCaseInfoExportDto.getEntrustReportStartDate(),surveyRiskCaseInfoExportDto.getConsignorEfficiencyAttr());
                days = Math.abs(days);
                surveyRiskCaseInfoExportDto.setEfficiency(days);
                List<SurveyCaseDirection> items = new ArrayList<SurveyCaseDirection>();
                Double scoreSum = 0D;//总分值
                Double entrustMoneySum = 0D;//总金额
                for (SurveyCaseDirection directionList : surveyCaseDirections) {
                    if (surveyRiskCaseInfoExportDto.getInvestigatorCaseId().intValue() == directionList.getSurveyInvestigatorCaseId().intValue()) {
                        items.add(directionList);
                        scoreSum = scoreSum + (directionList.getScore() == null ? 0D : directionList.getScore());
                        entrustMoneySum = entrustMoneySum + (directionList.getSurveyMoney() == null ? 0D : directionList.getSurveyMoney());
                    }
                }
                surveyRiskCaseInfoExportDto.setScoreSum(scoreSum);
                surveyRiskCaseInfoExportDto.setEntrustMoneySum(entrustMoneySum);
                surveyRiskCaseInfoExportDto.setSurveyCaseDirections(items);

                if(surveyRiskCaseInfoExportDto.getEntrustReportStartDate()!=null && surveyRiskCaseInfoExportDto.getEndTime()!=null){
                    int i = surveyRiskCaseInfoExportDto.getEntrustReportStartDate().compareTo(surveyRiskCaseInfoExportDto.getEndTime());
                    if(i > 0){ //超时
                        surveyRiskCaseInfoExportDto.setIsOverTime(true);
                    }
                }
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,count,caseInfoExportDtos);

    }


    @ApiMethod(descript = "狄大人互助报表", value = "backend-survey-org-help", apiParams = { })
    @Override
    public ApiResponse getSurveyOrgData(ApiRequest apiRequest) {
        String report = apiRequest.getString("report");
        Boolean isReport = "report".equals(report) ? true : false;
        if (!isReport){//非导出 则分页
            setBackendPageSize(apiRequest);
        }
        List<SurveyOrgDTO> list = new ArrayList<>();
        //list = init();
//        apiRequest.put("entrustOrgId",105);
//        apiRequest.put("entrustOrgName","相互宝");
//        apiRequest.put("beginDate",getDate(-37));
//        apiRequest.put("endDate",getDate(-7));
        list = surveyAssignOrgMapper.getSurveyOrgData(apiRequest);
        int count = 0;
        if (!isReport){
            count = surveyAssignOrgMapper.getSurveyOrgDataSize(apiRequest);;
        }else{
            count = list.size();
        }
        //总计 平均值
        SurveyHuzhuDTO surveyHuzhuDTO = surveyAssignOrgMapper.getSurveyOrgDataAvg(apiRequest);
        if (surveyHuzhuDTO != null) {
            //保留两位小数
            surveyHuzhuDTO.setCaseAvg(new BigDecimal(surveyHuzhuDTO.getCaseAvg()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            surveyHuzhuDTO.setLongTimeAvg(new BigDecimal(surveyHuzhuDTO.getLongTimeAvg()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            surveyHuzhuDTO.setVetoCaseAvg(new BigDecimal(surveyHuzhuDTO.getVetoCaseAvg()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            surveyHuzhuDTO.setVetoAvg(new BigDecimal(surveyHuzhuDTO.getVetoAvg()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            //计算 平均 百分比（率）
            float f = ((float) surveyHuzhuDTO.getLongTimeNum() / surveyHuzhuDTO.getCaseNum());//总超期数 除以 总案件数
            surveyHuzhuDTO.setLongTimeRate(new BigDecimal(f * 100 ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

            f = ((float) surveyHuzhuDTO.getVetoNum() / surveyHuzhuDTO.getCommitEdNum());//总驳回次数 除以 总提交案件数
            surveyHuzhuDTO.setVetoRate(new BigDecimal(f * 100 ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        }

        if (isReport){
            for (SurveyOrgDTO surveyOrgDTO : list) {
                apiRequest.put("surveyOrgId",surveyOrgDTO.getSurveyOrgId());
                apiRequest.put("type",1);//超时清单
                List<SurveyOrgDetailDTO> longCaseList = surveyAssignOrgMapper.getSurveyOrgDataDetail(apiRequest);
                surveyOrgDTO.setLongCaseList(convert(longCaseList));
                apiRequest.put("type",2);//驳回清单
                List<SurveyOrgDetailDTO> vetoCaseList = surveyAssignOrgMapper.getSurveyOrgDataDetail(apiRequest);
                surveyOrgDTO.setVetoCaseList(convert(vetoCaseList));
            }
        }
        for (SurveyOrgDTO surveyOrgDTO : list) {
            if (surveyOrgDTO.getCaseNum() != 0){
                float f = ((float) surveyOrgDTO.getLongNum() / surveyOrgDTO.getCaseNum());
                double rate = new BigDecimal(f * 100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                surveyOrgDTO.setLongRate(rate);
            }else{
                surveyOrgDTO.setLongRate(0D);
            }
            if (surveyOrgDTO.getCommitEdNum() != 0){
                float f = ((float)surveyOrgDTO.getVetoNum() / surveyOrgDTO.getCommitEdNum());
                double rate = new BigDecimal(f * 100 ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                surveyOrgDTO.setVetoRate(rate);
            }else{
                surveyOrgDTO.setVetoRate(0D);
            }
        }
        if (list.size() > 0){//把每一列的平均值放第一行
            list.get(0).setSurveyHuzhuDTO(surveyHuzhuDTO);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(descript = "狄大人互助报表详情", value = "backend-survey-org-help-detail", apiParams = { })
    @Override
    public ApiResponse getSurveyOrgDetailData(ApiRequest apiRequest) {
        String report = apiRequest.getString("report");
        Boolean isReport = "report".equals(report) ? true : false;
        if (!isReport){//非导出 则分页
            setBackendPageSize(apiRequest);
        }
//        Long entrustOrgId = apiRequest.getLong("entrustOrgId");
//        String beginDate = apiRequest.getString("beginDate");
//        String endDate = apiRequest.getString("endDate");
//        apiRequest.put("entrustOrgId",entrustOrgId);
//        apiRequest.put("beginDate",beginDate);
//        apiRequest.put("endDate",endDate);
        List<SurveyOrgDetailDTO> list = surveyAssignOrgMapper.getSurveyOrgDataDetail(apiRequest);
        int count = 0;
        if (!isReport){
            count = surveyAssignOrgMapper.getSurveyOrgDataDetailSize(apiRequest);;
        }else{
            count = list.size();
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,count,convert(list));
    }


    @ApiMethod(descript = "调查员费用报销提交发票提醒", value = "get-data-survey-expense-reimburse-info", apiParams = { })
    @Override
    public ApiResponse getExpenseReimbursementInfo(ApiRequest apiRequest){
        Long currentUserId = getCurrentUserId(apiRequest);
        //待提交发票提醒
        List<SurveyInvestigatorReInfo> surveyInvestigatorReInfos = surveyInvestigatorReInfoMapper.selectReStateBySurveyUserId(currentUserId);
        if (surveyInvestigatorReInfos.size() > 0) {
            SurveyInvestigatorReInfo investigatorReInfo = surveyInvestigatorReInfos.get(0);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,investigatorReInfo);
        }else {
            //待确认到账提醒
            SurveyPayInfoDTO surveyPayInfoDTO = surveyPayInfoMapper.selectByPaySurveyUserId(currentUserId);
            if (surveyPayInfoDTO!=null){
                SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectByPrimaryKey(surveyPayInfoDTO.getPayKeyId());
                if (surveyInvestigatorReInfo != null){
                    int count = surveyUserClockMapper.selectClockCaseCount(surveyInvestigatorReInfo.getId());
                    surveyPayInfoDTO.setInvestigatorCount(count);
                }
                //查询案件机构数量
//                int count = investigatorReDetailsMapper.selectInvestigatorCountByPayInfoId(surveyPayInfoDTO.getId());
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyPayInfoDTO);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
    }

    public List<SurveyOrgDTO> init(){
        List<SurveyOrgDTO> surveyOrgDTOS = new ArrayList<>();
        SurveyOrgDTO surveyOrgDTO = new SurveyOrgDTO();
        surveyOrgDTO.setSurveyOrgId(58L);
        surveyOrgDTO.setSurveyOrgName("狄仁杰公司");
        surveyOrgDTO.setBeginDate("2019-10-01");
        surveyOrgDTO.setEndDate("2019-10-30");
        surveyOrgDTO.setEntrustOrgId(105L);
        surveyOrgDTO.setEntrustOrgName("相互宝");
        surveyOrgDTO.setCaseNum(10);
        surveyOrgDTO.setLongNum(5);
        if (surveyOrgDTO.getCaseNum() != 0){
            surveyOrgDTO.setLongRate(new Double(surveyOrgDTO.getLongNum() / surveyOrgDTO.getCaseNum()));
        }else{
            surveyOrgDTO.setLongRate(0D);
        }
        surveyOrgDTO.setVetoNum(30);
        surveyOrgDTO.setCommitEdNum(1);
        if (surveyOrgDTO.getCommitEdNum() != 0){
            surveyOrgDTO.setVetoRate(new Double(surveyOrgDTO.getVetoNum() / surveyOrgDTO.getCommitEdNum()));
        }else{
            surveyOrgDTO.setVetoRate(0D);
        }
        surveyOrgDTOS.add(surveyOrgDTO);
        surveyOrgDTO = new SurveyOrgDTO();
        surveyOrgDTO.setSurveyOrgId(59L);
        surveyOrgDTO.setSurveyOrgName("狄仁杰公司2");
        surveyOrgDTO.setBeginDate("2019-10-01");
        surveyOrgDTO.setEndDate("2019-10-30");
        surveyOrgDTO.setEntrustOrgId(105L);
        surveyOrgDTO.setEntrustOrgName("相互宝");
        surveyOrgDTO.setCaseNum(11);
        surveyOrgDTO.setLongNum(6);
        if (surveyOrgDTO.getCaseNum() != 0){
            surveyOrgDTO.setLongRate(new Double(surveyOrgDTO.getLongNum() / surveyOrgDTO.getCaseNum()));
        }else{
            surveyOrgDTO.setLongRate(0D);
        }
        surveyOrgDTO.setVetoNum(15);
        surveyOrgDTO.setCommitEdNum(7);
        if (surveyOrgDTO.getCommitEdNum() != 0){
            surveyOrgDTO.setVetoRate(new Double(surveyOrgDTO.getVetoNum() / surveyOrgDTO.getCommitEdNum()));
        }else{
            surveyOrgDTO.setVetoRate(0D);
        }
        surveyOrgDTOS.add(surveyOrgDTO);
        surveyOrgDTO = new SurveyOrgDTO();
        surveyOrgDTO.setSurveyOrgId(60L);
        surveyOrgDTO.setSurveyOrgName("狄仁杰公司3");
        surveyOrgDTO.setBeginDate("2019-10-01");
        surveyOrgDTO.setEndDate("2019-10-30");
        surveyOrgDTO.setEntrustOrgId(105L);
        surveyOrgDTO.setEntrustOrgName("相互宝");
        surveyOrgDTO.setCaseNum(30);
        surveyOrgDTO.setLongNum(8);
        if (surveyOrgDTO.getCaseNum() != 0){
            surveyOrgDTO.setLongRate(new Double(surveyOrgDTO.getLongNum() / surveyOrgDTO.getCaseNum()));
        }else{
            surveyOrgDTO.setLongRate(0D);
        }
        surveyOrgDTO.setVetoNum(1);
        surveyOrgDTO.setCommitEdNum(30);
        if (surveyOrgDTO.getCommitEdNum() != 0){
            surveyOrgDTO.setVetoRate(new Double(surveyOrgDTO.getVetoNum() / surveyOrgDTO.getCommitEdNum()));
        }else{
            surveyOrgDTO.setVetoRate(0D);
        }
        surveyOrgDTOS.add(surveyOrgDTO);

        return surveyOrgDTOS;
    }

    private String getDate(int day){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH,day);
            calendar.getTime();
            return simpleDateFormat.format(calendar.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //转换 计算时效
    private List<SurveyOrgDetailDTO> convert(List<SurveyOrgDetailDTO> orgDetailDTOS){
        for (SurveyOrgDetailDTO orgDetailDTO : orgDetailDTOS) {
            SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(orgDetailDTO.getEntrustOrgId());
            int efficiencyAttr = 1 ;//时效设置（1：工作日；2、自然日）
            if(surveyConsignor!=null){
                efficiencyAttr =  surveyConsignor.getEfficiencyAttr();
            }

            Map<String,Object> effMap = surveyCaseDays(orgDetailDTO.getOrgSurveyState(),orgDetailDTO.getOrgAssignDate(),orgDetailDTO.getOrgReportDate(),orgDetailDTO.getOrgEndTime(), efficiencyAttr);
            orgDetailDTO.setOrgEfficiencyState(effMap.get("efficiencyState").toString());
            orgDetailDTO.setOrgEfficiencyStateColor(effMap.get("efficiencyStateColor").toString());
            //如果当前时间大于截止时间（按已提交计算时效 , 取 委托时间与当前时间相差的天数; 否则按未提交计算时效
            int state = new Date().compareTo(orgDetailDTO.getEndTime()) > 0 ? 4 : -1;
            effMap = surveyCaseDays(state,orgDetailDTO.getEntrustTime(),new Date(),orgDetailDTO.getEndTime(), efficiencyAttr);
            orgDetailDTO.setEfficiencyState(effMap.get("efficiencyState").toString());
            orgDetailDTO.setEfficiencyStateColor(effMap.get("efficiencyStateColor").toString());
        }
        return orgDetailDTOS;
    }


    //计算案件的案件时效
    private Map<String, Object> surveyCaseDays(int caseUserState, Date assDate, Date commitDate, Date endDate , int efficiencyAttr) {
        String assDateStr = null;
        if (assDate != null){
            assDateStr = new SimpleDateFormat("yyyy-MM-dd").format(assDate);
        }
        String commitDateStr = null;
        if (commitDate != null){
            commitDateStr = new SimpleDateFormat("yyyy-MM-dd").format(commitDate);
        }
        String endDateStr = null;
        if (endDate != null){
            endDateStr = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
        }
        return surveyCaseDays(caseUserState,assDateStr,commitDateStr,endDateStr,efficiencyAttr);
    }
    private Map<String, Object> surveyCaseDays(int caseUserState, String assDate, String commitDate, String endDate,int efficiencyAttr) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (caseUserState == 4) { //说明是已提交 -- （提交时间-分派时间 计算时效）
            if (commitDate == null || "".equals(commitDate)){
                commitDate = endDate;
            }
            int days = GetWorkDay.calLeaveDays(assDate, commitDate,efficiencyAttr);
            days = Math.abs(days);
            if(commitDate.compareTo(endDate) > 0){ //如果“提交时间” 超过“截止时间” 为红色
                map.put("efficiencyState","时效"+days+"天");
                map.put("efficiencyStateColor","#e51c23");
            }else{
                map.put("efficiencyState","时效"+days+"天");
                map.put("efficiencyStateColor","#3ba9ff");
            }
        }else{//未提交
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            if(endDate.compareTo(dateString) > 0){ //未超时
                int days = GetWorkDay.calLeaveDays(dateString,endDate,efficiencyAttr);
                map.put("efficiencyState","剩余"+days+"天");
                map.put("efficiencyStateColor","#3ba9ff");//蓝色
                if(days <= 2 && days >0 ){ //
                    map.put("efficiencyStateColor","#ff9800");//黄色
                }
            }else{
                int days = GetWorkDay.calLeaveDays(endDate,dateString,efficiencyAttr);
                map.put("efficiencyState","超时"+days+"天");
                map.put("efficiencyStateColor","#e51c23");
            }
        }
        return map;
    }
}
