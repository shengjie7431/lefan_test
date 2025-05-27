package com.lefancrm.apicenter.appapi.impl;

import com.lefancrm.apicenter.appapi.SaletArgetApi;
import com.lefancrm.apicenter.dao.ActivityDayReportMapper;
import com.lefancrm.apicenter.dao.BusUserRoleMapper;
import com.lefancrm.apicenter.dao.SaleGoalMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.dto.CaseSumDataDto;
import com.lefancrm.apicenter.model.ActivityDayReport;
import com.lefancrm.apicenter.model.BusUserRole;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/12/15.
 */

@Service
@ApiService(descript = "销售目标")
public class SaletArgetApiImpl extends BaseServiceImpl implements SaletArgetApi {


    @Autowired
    private SaleGoalMapper saleGoalMapper;
    @Autowired
    private ActivityDayReportMapper activityDayReportMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;

    @ApiMethod(needLogin = false,descript = "销售简报查询", value = "query-sale-goal-simple",
            apiParams = { @ApiParam(name = "objectId",descript = "对象ID"),
                    @ApiParam(name = "objectType",descript = "类别(1.组织，2：用户)"),
                    @ApiParam(name = "month",descript = "月份"),
                    @ApiParam(name = "year",descript = "年份")})
    @Override
    public ApiResponse querySaleGoalSimple(ApiRequest apiReq) {
        Integer objectType = apiReq.getInt("objectType");
        Integer objectId = apiReq.getInt("objectId");
        StringBuffer sb = new StringBuffer();
        sb.append(apiReq.get("year"));
        sb.append("-");
        sb.append(apiReq.get("month"));

        Map<String,Object> resultMap = new HashMap<>();
        //目标金额
        int saleGold = saleGoalMapper.querySaleGoalGold(apiReq);
        //实际金额
        int saleAmount = 0;
        //销售漏斗
        ActivityDayReport activityDayReport = null;
        //案件汇总
        List<CaseSumDataDto> caseSumDataDtos = null;

        Date salesDate = DateUtils.parseDate(sb.toString(), "yyyy-MM");
        apiReq.put("salesDate",salesDate);
        if(objectType == 1){
            apiReq.put("orgParentid",objectId);
            saleAmount = activityDayReportMapper.querySaleAmountGold(apiReq);
            activityDayReport = activityDayReportMapper.querySaleFunnel(apiReq);
            caseSumDataDtos = activityDayReportMapper.queryCaseDataNum(apiReq);

        }else if(objectType == 2){
            apiReq.put("userId",objectId);
            saleAmount = activityDayReportMapper.queryMeSaleAmountGold(apiReq);
            activityDayReport = activityDayReportMapper.queryMeSaleFunnel(apiReq);
            caseSumDataDtos = activityDayReportMapper.queryMeCaseDataNum(apiReq);
        }
        resultMap.put("saleGold",saleGold);
        resultMap.put("saleAmount",saleAmount);
        resultMap.put("saleFunnel", activityDayReport);
        resultMap.put("caseDataNum", caseSumDataDtos);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultMap);
    }

    @ApiMethod(needLogin = false,descript = "业绩排行查询", value = "query-achievement-rank",
            apiParams = { @ApiParam(name = "salesDate",descript = "查询时间:(按年月查)"),
                    @ApiParam(name = "page",descript = "当前页"),
                   })
    @Override
    public ApiResponse queryAchievementRank(ApiRequest apiReq) {
//        Date salesDate = DateUtils.parseDate(apiReq.getString("salesDate"), "yyyy-MM");
//        apiReq.put("salesDate",salesDate);
        this.setPageIndex(apiReq);
        int count = activityDayReportMapper.queryAchievementRankCount(apiReq);
        List<ActivityDayReport> activityDayReports = activityDayReportMapper.queryAchievementRank(apiReq);
//        String roles = apiReq.getString("roles");
//        //市场总监(查所有机构)
//        if(roles.contains("19")){
//
//        }
//        //CC主管
//        else if(roles.contains("17")){
//
//        }
//        //业务组长
//        else if(roles.contains("20")){
//
//        }
//        //业务员
//        else if(roles.contains("2")){
//
//        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, activityDayReports);
    }


    @ApiMethod(needLogin = false,descript = "成交案件排行", value = "query-sign-case-rank",
            apiParams = {
                    @ApiParam(name = "page",descript = "当前页"),
                    @ApiParam(name = "dayType",descript = "1、本日，2、本周，3上周，4本月，5、上月")
            })
    @Override
    public ApiResponse querySignCaseRank(ApiRequest apiReq) {
        this.setPageIndex(apiReq);
        int count = 0;
        List<ActivityDayReport> activityDayReports = null;
        Integer dayType = apiReq.getInt("dayType");
        if(dayType == 1){
            count = activityDayReportMapper.querySignCaseDayRankCount();
            activityDayReports = activityDayReportMapper.querySignCaseDayRank(apiReq);
        }
        else if(dayType == 2){
            count = activityDayReportMapper.querySignCaseWeekRankCount();
            activityDayReports = activityDayReportMapper.querySignCaseWeekRank(apiReq);
        }
        else if(dayType == 3){
            count = activityDayReportMapper.querySignCaseLastWeekRankCount();
            activityDayReports = activityDayReportMapper.querySignCaseLastWeekRank(apiReq);
        }
        else if(dayType == 4){
            count = activityDayReportMapper.querySignCaseMonthRankCount();
            activityDayReports = activityDayReportMapper.querySignCaseMonthRank(apiReq);
        }
        else if(dayType == 5){
            count = activityDayReportMapper.querySignCaseLastMonthRankCount();
            activityDayReports = activityDayReportMapper.querySignCaseLastMonthRank(apiReq);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS, count, activityDayReports);
    }

    @ApiMethod(needLogin = false,descript = "拜访客户排行", value = "query-visit-customer-rank",
            apiParams = {
                    @ApiParam(name = "page",descript = "当前页"),
                    @ApiParam(name = "dayType",descript = "1、本日，2、本周，3上周，4本月，5、上月")
            })
    @Override
    public ApiResponse queryVisitCustomerRank(ApiRequest apiReq) {
        this.setPageIndex(apiReq);
        int count = 0;
        List<ActivityDayReport> activityDayReports = null;
        Integer dayType = apiReq.getInt("dayType");
        if(dayType == 1){
            count = activityDayReportMapper.queryVisitCustomerDayRankCount();
            activityDayReports = activityDayReportMapper.queryVisitCustomerDayRank(apiReq);
        }
        else if(dayType == 2){
            count = activityDayReportMapper.queryVisitCustomerWeekRankCount();
            activityDayReports = activityDayReportMapper.queryVisitCustomerWeekRank(apiReq);
        }
        else if(dayType == 3){
            count = activityDayReportMapper.queryVisitCustomerLastWeekRankCount();
            activityDayReports = activityDayReportMapper.queryVisitCustomerLastWeekRank(apiReq);
        }
        else if(dayType == 4){
            count = activityDayReportMapper.queryVisitCustomerMonthRankCount();
            activityDayReports = activityDayReportMapper.queryVisitCustomerMonthRank(apiReq);
        }
        else if(dayType == 5){
            count = activityDayReportMapper.queryVisitCustomerLastMonthRankCount();
            activityDayReports = activityDayReportMapper.queryVisitCustomerLastMonthRank(apiReq);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS, count, activityDayReports);
    }

//    @ApiMethod(needLogin = false,descript = "销售漏斗（本周）", value = "query-sale-funnel")
//    @Override
//    public ApiResponse querySaleFunnel(ApiRequest apiReq) {
//        ActivityDayReport activityDayReport = activityDayReportMapper.querySaleFunnel(14L);
//        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, activityDayReport);
//    }
}
