package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendWorkBenchApi;
import com.lefancrm.apicenter.dao.ActivityDayReportMapper;
import com.lefancrm.apicenter.dao.OrgInfoMapper;
import com.lefancrm.apicenter.dao.SaleGoalMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.dto.CaseSumDataDto;
import com.lefancrm.apicenter.dto.CaseSumReportDto;
import com.lefancrm.apicenter.dto.WorkBenchDto;
import com.lefancrm.apicenter.model.ActivityDayReport;
import com.lefancrm.apicenter.model.OrgInfo;
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

import java.util.*;

/**
 * Created by DELL on 2017/12/28.
 */
@Service
@ApiService(descript = "工作台")
public class BackendWorkBenchApiImpl extends BaseServiceImpl implements BackendWorkBenchApi {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ActivityDayReportMapper activityDayReportMapper;
    @Autowired
    private SaleGoalMapper saleGoalMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;




    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "工作台", value = "backend-query-work-bench",
            apiParams = { @ApiParam(name = "roleId",descript = "角色ID"),
                    @ApiParam(name = "userId",descript = "用户ID")})
    @Override
    public ApiResponse queryWorkBench(ApiRequest apiReq) {

        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(apiReq);
        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        Long roleId = apiReq.getLong("roleId");
        Long userId = apiReq.getLong("userId");
        Integer objectType = 0;//1是组织，2是个人
        Long objectId = 0L;

        Calendar now = Calendar.getInstance();
        apiReq.put("salesDate",now.getTime());
        apiReq.put("year",now.get(Calendar.YEAR));
        apiReq.put("month",now.get(Calendar.MONTH ) + 1);
        //管理员机构 //cc主管    //市场总监  //业务小组长
        if(roleId == 1 || roleId == 17 || roleId == 19 || roleId == 20){
            objectType = 1;
            objectId = userInfo.getOrgId();
        }
        //业务员
        else if(roleId == 2){
            objectType = 2;
            objectId = userId;
        }

        apiReq.put("objectId",objectId);
        apiReq.put("objectType",objectType);
        apiReq.put("date",1);

        //案件汇总（销售简报）
        List<CaseSumDataDto> caseSumDataDtos = null;
        //案件汇总报告（销售简报）
        CaseSumReportDto caseSumReportDto = null;


        //目标金额(业绩目标)
        int saleGold = saleGoalMapper.querySaleGoalGold(apiReq);
        //实际金额(业绩目标)
        int saleAmount = 0;

        //业绩排行
        this.setBackendPageSize(apiReq);
        List<ActivityDayReport> activityDayReports = null;

        //销售漏斗
        ActivityDayReport activityDayReport = null;
        if(objectType == 1){
            apiReq.put("orgParentid",objectId);
            activityDayReports = activityDayReportMapper.queryAchievementRankDate(apiReq);
            caseSumDataDtos = activityDayReportMapper.queryCaseDataNum(apiReq);
            caseSumReportDto = activityDayReportMapper.queryCaseReportNum(apiReq);
            saleAmount = activityDayReportMapper.querySaleAmountGold(apiReq);
            activityDayReport = activityDayReportMapper.querySaleFunnel(apiReq);

        }else if(objectType == 2){
            apiReq.put("userId",objectId);
            activityDayReports = activityDayReportMapper.queryMeAchievementRankDate(apiReq);
            caseSumDataDtos = activityDayReportMapper.queryMeCaseDataNum(apiReq);
            caseSumReportDto  = activityDayReportMapper.queryMeCaseReportNum(apiReq);
            saleAmount = activityDayReportMapper.queryMeSaleAmountGold(apiReq);
            activityDayReport = activityDayReportMapper.queryMeSaleFunnel(apiReq);
        }



        WorkBenchDto workBenchDto = new WorkBenchDto();
        workBenchDto.setActivityDayReport(activityDayReport);
        workBenchDto.setActivityDayReports(activityDayReports);
        workBenchDto.setCaseSumDatas(caseSumDataDtos);
        workBenchDto.setCaseSumReport(caseSumReportDto);
        workBenchDto.setSaleAmount(saleAmount);
        workBenchDto.setSaleGold(saleGold);

        //如果是总监，查询所有机构列表
        if(roleId == 19L){
            List<OrgInfo> orgInfos = orgInfoMapper.queryOrgList(null);
            workBenchDto.setOrgInfos(orgInfos);

        }

        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, workBenchDto);
    }


    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "工作台", value = "backend-query-work-bench-date",
            apiParams = { @ApiParam(name = "roleId",descript = "角色ID"),
                    @ApiParam(name = "userId",descript = "用户ID")})
    @Override
    public ApiResponse queryWorkBenchByDate(ApiRequest apiReq) {
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(apiReq);
        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        //类型：1业绩目标 2业绩排行 3销售漏斗
        Integer type = apiReq.getInt("type");
        //查询周期：1本月，2上月，3本季度，4上季度，5本年
        Integer date = apiReq.getInt("date");

        Long roleId = apiReq.getLong("roleId");
        Long userId = apiReq.getLong("userId");
        Integer objectType = 0;//1是组织，2是个人
        Long objectId = 0L;
        //管理员机构 //cc主管    //市场总监  //业务小组长
        if(roleId == 1 || roleId == 17 || roleId == 19 || roleId == 20){
            objectType = 1;
            //如果是销售总监，验证是否选了机构
            if(roleId == 19L){
                objectId = apiReq.getLong("orgId");
            }else{
                objectId = userInfo.getOrgId();
            }
        }
        //业务员
        else if(roleId == 2){
            objectType = 2;
            objectId = userId;
        }
        apiReq.put("objectId",objectId);
        apiReq.put("objectType",objectType);
        if(objectType == 1){
            apiReq.put("orgParentid",objectId);
        }else if(objectType == 2){
            apiReq.put("userId",objectId);
        }

        if(type == 1){//业绩目标
            //目标金额(业绩目标)
            int saleGold = saleGoalMapper.querySaleGoalGoldDate(apiReq);
            //实际金额(业绩目标)
            int saleAmount = 0;
            if(objectType == 1){
                saleAmount = activityDayReportMapper.querySaleAmountGoldDate(apiReq);
            }else if(objectType == 2){
                saleAmount = activityDayReportMapper.queryMeSaleAmountGoldDate(apiReq);
            }
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("saleGold",saleGold);
            resultMap.put("saleAmount",saleAmount);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultMap);
        }else if(type == 2){//业绩排行
            List<ActivityDayReport> activityDayReports = null;
            if(objectType == 1){
                activityDayReports = activityDayReportMapper.queryAchievementRankDate(apiReq);
            }else if(objectType == 2){
                activityDayReports = activityDayReportMapper.queryMeAchievementRankDate(apiReq);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,activityDayReports);
        }else if(type == 3){//销售漏斗
            //销售漏斗
            ActivityDayReport activityDayReport = null;
            if(objectType == 1){
                activityDayReport = activityDayReportMapper.querySaleFunnelDate(apiReq);
            }else if(objectType == 2){
                activityDayReport = activityDayReportMapper.queryMeSaleFunnelDate(apiReq);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,activityDayReport);
        }
        return null;
    }
}
