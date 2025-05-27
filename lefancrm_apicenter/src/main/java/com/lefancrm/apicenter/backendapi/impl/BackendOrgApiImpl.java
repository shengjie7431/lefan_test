package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSON;
import com.lefancrm.apicenter.backendapi.BackendOrgApi;

import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.OrgActivityCountReportDto;
import com.lefancrm.apicenter.dto.TreeData;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.tree.impl.AreaTreeImpl;
import com.lefancrm.apicenter.tree.impl.UserOrgTreeImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
@ApiService(descript = "机构公司")
public class BackendOrgApiImpl extends BaseServiceImpl implements BackendOrgApi {
    private static final Logger loger = Logger.getLogger(BackendOrgApiImpl.class);

    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @Autowired
    private ActivityDayReportMapper activityDayReportMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BusinessRoleMapper businessRoleMapper;

    @Autowired
    private BusUserRoleMapper busUserRoleMapper;

    @Autowired
    private BackendBusUserRoleMenuApiImpl backendBusUserRoleMenuApiImpl;

    @Autowired
    private UserOrgTreeImpl userOrgTree;

    @Autowired
    private AreaTreeImpl areaTree;

    @Autowired
    private FrontRoleMenuMapper frontRoleMenuMapper;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private CommonAreaMapper commonAreaMapper;
    @Autowired
    private CommonOrgInfoMapper commonOrgInfoMapper;

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "机构公司列表", value = "backend-org-list")
    @Override
    public ApiResponse<List<OrgInfo>> queryOrgList(ApiRequest apiReq){
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        ApiResponse apiResponse = backendBusUserRoleMenuApiImpl.isRole(apiReq);
        boolean b = Boolean.parseBoolean(String.valueOf(apiResponse.getResults()));
        List<OrgInfo> orgInfos = orgInfoMapper.queryOrgList(apiReq);
//         List<OrgInfo> menuList = this._createTree(orgInfos);
        List<TreeData> _results = userOrgTree._resultTreeDate(orgInfos,userInfo,b);
        return new ApiResponse(ApiMsgEnum.SUCCESS,_results==null?0:_results.size(),_results);
    }

    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "查询树形结构时返回的第一加载机构信息", value = "org-tree-data")
    public ApiResponse orgTreeData(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        String startDate = apiReq.getString("startDate");
        String endDate = apiReq.getString("endDate");
        String orderByVisitNum = apiReq.getString("orderByVisitNum");// 1 降序 2 升序
        String orderBySignNum = apiReq.getString("orderBySignNum");
        Map<String, Object> paramMap = new HashMap();
        Date sDate = DateUtils.parseDate(startDate, "yyyy-MM-dd");;
        Date eDate = DateUtils.parseDate(endDate,"yyyy-MM-dd");

        ApiResponse apiResponse = backendBusUserRoleMenuApiImpl.isRole(apiReq);
        boolean b = Boolean.parseBoolean(String.valueOf(apiResponse.getResults()));
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);

        Long orgId = userInfo.getOrgId();
        if (b) {// 判断权限 首次显示权限为总监显示所有机构
            orgId = null;
        }
        paramMap.put("sDate",sDate);
        paramMap.put("eDate",eDate);
        paramMap.put("orderByVisitNum",orderByVisitNum);
        paramMap.put("orderBySignNum",orderBySignNum);
        paramMap.put("orgId",orgId);

        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        List<OrgActivityCountReportDto> orgActivityCountReportDto = activityDayReportMapper.queryCountReportByOrgId(paramMap);

        return new ApiResponse(ApiMsgEnum.SUCCESS,orgActivityCountReportDto==null?0:1,orgActivityCountReportDto);
    }

    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "机构活动量统计", value = "backend-org-info")
    public ApiResponse getByOrgId(ApiRequest apiReq) {
        Long orgId = apiReq.getLong("id");
        String startDate = apiReq.getString("startDate");
        String endDate = apiReq.getString("endDate");
        String orderByVisitNum = apiReq.getString("orderByVisitNum");// 1 降序 2 升序
        String orderBySignNum = apiReq.getString("orderBySignNum");// 1 降序 2 升序
        Map<String, Object> paramMap = new HashMap();
        Date sDate = DateUtils.parseDate(startDate, "yyyy-MM-dd");;
        Date eDate = DateUtils.parseDate(endDate,"yyyy-MM-dd");
        if (StringUtils.isEmpty(orgId)) {
            ApiResponse apiResponse = backendBusUserRoleMenuApiImpl.isRole(apiReq);
            boolean b = Boolean.parseBoolean(String.valueOf(apiResponse.getResults()));
            if(b){// 是否具有总监权限
                orgId = null;
            }else{
                Long userId = apiReq.getLong("operatorId");
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
                orgId = userInfo.getOrgId();
            }
        }
        paramMap.put("sDate",sDate);
        paramMap.put("eDate",eDate);
        paramMap.put("orderByVisitNum",orderByVisitNum);
        paramMap.put("orderBySignNum",orderBySignNum);
        paramMap.put("orgId",orgId);
        List<OrgActivityCountReportDto> orgActivityCountReportDto = activityDayReportMapper.queryCountReportByOrgId(paramMap);

        return new ApiResponse<List<OrgActivityCountReportDto>>(ApiMsgEnum.SUCCESS, (orgActivityCountReportDto == null ? 0 : 1), orgActivityCountReportDto);
    }

    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "查询cc活动量返回的第一个机构信息", value = "org-tree-cc-data")
    public ApiResponse orgCCTreeData(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        // 判断是否传入机构id
        Long orgId=apiReq.getLong("orgId");
        if(StringUtils.isEmpty(orgId)){
            orgId = userInfo.getOrgId();
            apiReq.put("orgId",orgId);
        }
        Date sDate = DateUtils.parseDate(apiReq.getString("startDate"), "yyyy-MM-dd");
        Date eDate = DateUtils.parseDate(apiReq.getString("endDate"),"yyyy-MM-dd");
        apiReq.put("sDate",sDate);
        apiReq.put("eDate",eDate);
        // 查询报表信息根据orgId
        List<ActivityDayReport> activityDayReports = activityDayReportMapper.queryReportListByOrgId(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,activityDayReports==null?0:activityDayReports.size(),activityDayReports);
    }

    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "查询cc活动量列表根据机构id", value = "backend-cc-list-data")
    public ApiResponse orgCCTreeDataByOrgId(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        Long orgId=apiReq.getLong("orgId");
        if(StringUtils.isEmpty(orgId)){
            orgId = userInfo.getOrgId();
            apiReq.put("orgId",orgId);
        }
        Date sDate = DateUtils.parseDate(apiReq.getString("startDate"), "yyyy-MM-dd");;
        Date eDate = DateUtils.parseDate(apiReq.getString("endDate"),"yyyy-MM-dd");
        apiReq.put("sDate",sDate);
        apiReq.put("eDate",eDate);
        List<ActivityDayReport> activityDayReports = activityDayReportMapper.queryReportListByOrgId(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,activityDayReports==null?0:activityDayReports.size(),activityDayReports);
    }

    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "查询cc用户列表根据机构id", value = "backend-cc-user-list")
    public ApiResponse queryUserListByOrgId(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        Long orgId= null;
        try{
            orgId=apiReq.getLong("orgId");
        }catch (Exception e){
            orgId = null;
        }
        if(StringUtils.isEmpty(orgId)){
            orgId = userInfo.getOrgId();
            apiReq.put("orgId",orgId);
        }
        List<UserInfo> userInfoList = userInfoMapper.selectAllUserByOrgId(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,userInfoList==null?0:userInfoList.size(),userInfoList);
    }

    //+++++++++++++++ 销售业绩 +++++++++++++++++++++
    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "查询机构业绩默认显示数据", value = "backend-org-money-data")
    public ApiResponse treeDataOrgMoney(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        String startMonth = apiReq.getString("startMonth");
        String endMonth = apiReq.getString("endMonth");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        // 判断是否传入机构id
        Long orgId=apiReq.getLong("orgId");
        if(StringUtils.isEmpty(orgId)){
            ApiResponse apiResponse = backendBusUserRoleMenuApiImpl.isRole(apiReq);
            boolean b = Boolean.parseBoolean(String.valueOf(apiResponse.getResults()));
            if(b){// 是否具有总监权限
                orgId = null;
            }else{
                orgId = userInfo.getOrgId();
                apiReq.put("orgId",orgId);
            }
        }
        Date sMonth = null;
        Date eMonth = null;
        if (!StringUtils.isEmpty(startMonth)){
            sMonth = DateUtils.parseDate(apiReq.getString("startMonth"), "yyyy-MM");
        }
        if (!StringUtils.isEmpty(endMonth)){
            eMonth = DateUtils.parseDate(apiReq.getString("endMonth"),"yyyy-MM");
        }
        apiReq.put("sMonth",sMonth);
        apiReq.put("eMonth",eMonth);

        // 设置自然月份时间
        Date nMonth = null;
        if (!StringUtils.isEmpty(apiReq.getString("naturalMonth"))){
            nMonth = DateUtils.parseDate(Calendar.getInstance().get(Calendar.YEAR) + "-" + apiReq.getString("naturalMonth"),"yyyy-MM");
        }
        apiReq.put("nMonth",nMonth);
        // 查询报表信息根据orgId
        List<ActivityDayReport> activityDayReports = activityDayReportMapper.queryOrgMoneyListById(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,activityDayReports==null?0:activityDayReports.size(),activityDayReports);
    }

    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "条件查询机构销售业绩", value = "backend-query-org-money")
    public ApiResponse queryOrgMoneyListById(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        String startMonth = apiReq.getString("startMonth");
        String endMonth = apiReq.getString("endMonth");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        Long orgId=apiReq.getLong("orgId");
        if(StringUtils.isEmpty(orgId)){
            ApiResponse apiResponse = backendBusUserRoleMenuApiImpl.isRole(apiReq);
            boolean b = Boolean.parseBoolean(String.valueOf(apiResponse.getResults()));
            if(b){// 是否具有总监权限
                orgId = null;
            }else{
                orgId = userInfo.getOrgId();
                apiReq.put("orgId",orgId);
            }
        }
        Date sMonth = null;
        Date eMonth = null;
        if (!StringUtils.isEmpty(startMonth)){
            sMonth = DateUtils.parseDate(apiReq.getString("startMonth"), "yyyy-MM");
        }
        if (!StringUtils.isEmpty(endMonth)){
            eMonth = DateUtils.parseDate(apiReq.getString("endMonth"),"yyyy-MM");
        }
        apiReq.put("sMonth",sMonth);
        apiReq.put("eMonth",eMonth);

        // 设置自然月份时间
        Date nMonth = null;
        if (!StringUtils.isEmpty(apiReq.getString("naturalMonth"))){
            nMonth = DateUtils.parseDate(Calendar.getInstance().get(Calendar.YEAR) + "-" + apiReq.getString("naturalMonth"),"yyyy-MM");
        }
        apiReq.put("nMonth",nMonth);
        List<ActivityDayReport> activityDayReports = activityDayReportMapper.queryOrgMoneyListById(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,activityDayReports==null?0:activityDayReports.size(),activityDayReports);
    }

    //********************** 机构CC月销售业绩 ***************************
    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "条件查询机构销售业绩", value = "backend-org-cc-money-data")
    public ApiResponse queryOrgCCMoneyListById(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        String startMonth = apiReq.getString("startMonth");
        String endMonth = apiReq.getString("endMonth");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(userInfo == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        Long orgId=apiReq.getLong("orgId");
        if(StringUtils.isEmpty(orgId)){
//            ApiResponse apiResponse = backendBusUserRoleMenuApiImpl.isRole(apiReq);
//            boolean b = Boolean.parseBoolean(String.valueOf(apiResponse.getResults()));
//            if(b){// 是否具有总监权限
//                orgId = null;
//            }else{
            orgId = userInfo.getOrgId();
            apiReq.put("orgId",orgId);
//            }
        }
        Date sMonth = null;
        Date eMonth = null;
        if (!StringUtils.isEmpty(startMonth)){
            sMonth = DateUtils.parseDate(apiReq.getString("startMonth"), "yyyy-MM");
        }
        if (!StringUtils.isEmpty(endMonth)){
            eMonth = DateUtils.parseDate(apiReq.getString("endMonth"),"yyyy-MM");
        }
        apiReq.put("sMonth",sMonth);
        apiReq.put("eMonth",eMonth);

        // 设置自然月份时间
        Date nMonth = null;
        if (!StringUtils.isEmpty(apiReq.getString("naturalMonth"))){
            nMonth = DateUtils.parseDate(Calendar.getInstance().get(Calendar.YEAR) + "-" + apiReq.getString("naturalMonth"),"yyyy-MM");
        }
        apiReq.put("nMonth",nMonth);
        List<ActivityDayReport> activityDayReports = activityDayReportMapper.queryOrgCCMoneyListById(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,activityDayReports==null?0:activityDayReports.size(),activityDayReports);
    }

    @ApiMethod(descript = "风控人员下的机构list（不包含小组）", value = "backend-org-info-list-by-riskuser")
    @Override
    public ApiResponse selectOrgInfoByRiskOrg(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        apiReq.put("userId",apiReq.get("operatorId"));
        List<OrgInfo> list = orgInfoMapper.selectOrgInfoByRiskOrg(apiReq);
        return new ApiResponse<List<OrgInfo>>(ApiMsgEnum.SUCCESS, null, list);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询机构-父级为1的数据", value = "backend-org-list-by-orgParentId")
    @Override
    public ApiResponse<List<OrgInfo>> searchOrgListByOrgParentId(ApiRequest apiReq){
        List<OrgInfo> orgInfos = orgInfoMapper.searchOrgListByOrgParentId(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,orgInfos==null?0:orgInfos.size(),orgInfos);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查看机构下面的（业务员）用户", value = "backend-caseApply-select-sales-user-by-orgId")
    @Override
    public ApiResponse selectSalesUserByOrgId(ApiRequest apiReq) {
        List<UserInfo> userInfos = userInfoMapper.selectSalesUserByOrgId(apiReq.getLong("orgId"));
        return new ApiResponse<List<UserInfo>>(ApiMsgEnum.SUCCESS,userInfos==null?0:userInfos.size(),userInfos);
    }


    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "省市区查询", value = "backend-select-area")
    @Override
    public ApiResponse<List<TreeData>> selectAreaAll(ApiRequest apiReq) {
        List<CommonArea> commonAreas = commonAreaMapper.selectAll();
        List<TreeData> _result = areaTree._resultTreeDate(commonAreas);
        return new ApiResponse<List<TreeData>>(ApiMsgEnum.SUCCESS,_result==null?0:_result.size(),_result);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "区域是否显示状态", value = "backend-area_show_list")
    @Override
    public ApiResponse getShowArea(ApiRequest apiReq) {
        List<CommonArea> areas = commonAreaMapper.selectAreaShowList(1);
        return new ApiResponse(ApiMsgEnum.SUCCESS, areas!=null?areas.size() : 0,areas);
    }

    /**
     * 查询机构类型为2的保险公司
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod( descript = "查询机构类型为2的保险公司", value = "backend-common-org-info")
    @Override
    public ApiResponse selectInsuranceCompany(ApiRequest apiReq) {
        apiReq.put("cOrgType",5);
        String noPage = apiReq.getString("noPage");
        if(noPage !=null){
            apiReq.put("pageIndex",null);//不分页
        }
        List<CommonOrgInfo> list = commonOrgInfoMapper.queryCOrgListByParam(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,list==null?0:list.size(),list);
    }
}

