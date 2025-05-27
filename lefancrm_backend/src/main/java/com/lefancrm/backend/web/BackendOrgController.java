package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.util.Util;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.DateTimeUtil;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.lang.Boolean;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by DELL on 2017/4/26.
 */

@Controller
@RequestMapping(value = "/org")
public class BackendOrgController extends BackendBaseController {

    /**树结构机构数据获取方法*/
    @RequestMapping(value = "/treeData")
    public String treeData(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_LIST, null, req);
        Type type = new TypeToken<ApiFinalResponse<List<OrgTreeData>>>() {
        }.getType();
        ApiFinalResponse<List<OrgTreeData>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
            List<OrgTreeData> tree = apiRsp.getResults();
            retJson = JsonUtil.objectToJson(tree);
        }
        return WebHelper.outputJson(retJson, rsp);
    }

    // 机构活动量表报默认显示方法 默认显示当前用户的机构
    @RequestMapping(value = "/treeDataOrg")
    public String treeDataOrg(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String id=req.getParameter("id");// orgId
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");
        String orderByVisitNum=req.getParameter("orderByVisitNum");
        String orderBySignNum=req.getParameter("orderBySignNum");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isEmpty(startDate)) {
            startDate = simpleDateFormat.format(new Date());
        }
        if (StringUtils.isEmpty(endDate)) {
            endDate = startDate;
        }
        if (StringUtils.isEmpty(orderByVisitNum) && StringUtils.isEmpty(orderBySignNum)) {
            orderBySignNum = "1";
        }
        Map map = new HashMap();
        map.put("id",id);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("orderByVisitNum",orderByVisitNum);
        map.put("orderBySignNum",orderBySignNum);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TREE_DATA, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<OrgActivityCountReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<OrgActivityCountReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        map.put("orgId",id);
        if (apiRsp != null) {
//            List<OrgActivityCountReportDto> tree = apiRsp.getResults();
            map.put("results",apiRsp.getResults());
        }
        retJson = JsonUtil.objectToJson(map);
        return WebHelper.outputJson(retJson, rsp);
    }

    @RequestMapping(value = "/queryOrgById")
    public String queryOrgById(HttpServletRequest req, HttpServletResponse rsp) {
        String id=req.getParameter("id");
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");
        String orderByVisitNum=req.getParameter("orderByVisitNum");
        String orderBySignNum=req.getParameter("orderBySignNum");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isEmpty(startDate)) {
            startDate = simpleDateFormat.format(new Date());
        }
        if (StringUtils.isEmpty(endDate)) {
            endDate = startDate;
        }
        if (StringUtils.isEmpty(orderByVisitNum) && StringUtils.isEmpty(orderBySignNum)) {
            orderBySignNum = "1";// 默认签约人数降序
        }
        Map map = new HashMap();
        map.put("id",id);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("orderByVisitNum",orderByVisitNum);
        map.put("orderBySignNum",orderBySignNum);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_INFO, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<OrgActivityCountReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<OrgActivityCountReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        map.put("orgId",id);
        if (apiRsp != null) {
//            List<OrgActivityCountReportDto> treeList = apiRsp.getResults();
            map.put("results",apiRsp.getResults());
        }
        retJson = JsonUtil.objectToJson(map);
        return WebHelper.outputJson(retJson, rsp);
    }

    @RequestMapping(value = "/list")
    public String list(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
//        Integer orgType = Integer.parseInt(req.getParameter("orgType"));
        Integer orgType = 1;
        String isRole = this.callApi(BackendApiMethodEnum.BACKEND_IS_ROLE, null, req);
        Type isRoleType = new TypeToken<ApiFinalResponse<Boolean>>() {
        }.getType();
        ApiFinalResponse<Boolean> isRoleApiRsp = JsonUtil.jsonToObject(isRole, isRoleType);
        if(!Boolean.parseBoolean(String.valueOf(isRoleApiRsp.getResults()))) {
            req.setAttribute("isRole",0);
        }else{
            req.setAttribute("isRole",1);
        }
        // return "/org/userRoleList";
        if(orgType == 1){
            return "/org/userRoleList";
        }
        return null;
    }
    @RequestMapping(value = "/toAddUser")
    public String toAddUser(HttpServletRequest req) throws  Exception{
        req.setAttribute("img",req.getParameter("img"));
        req.setAttribute("userName",req.getParameter("userName"));
        req.setAttribute("userType",req.getParameter("userType"));
        req.setAttribute("nickName",req.getParameter("nickName"));
        req.setAttribute("userTel",req.getParameter("userTel"));
        req.setAttribute("email",req.getParameter("email"));
        req.setAttribute("userState",req.getParameter("userState"));
        req.setAttribute("sex",req.getParameter("sex"));
        req.setAttribute("userAddress",req.getParameter("userAddress"));
        return "";
    }

    /**
     * 显示树形结构
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/treeList")
    public String treeList(HttpServletRequest req) throws  Exception{
        //用于操作类型判断
        Integer type = Integer.parseInt(req.getParameter("type"));
        req.setAttribute("type",req.getParameter("type"));
        switch (type){
            case 1 :
                UserInfoDto userInfoDto = new UserInfoDto();
                userInfoDto.setImg(req.getParameter("img"));
                userInfoDto.setUserName(req.getParameter("userName"));
                userInfoDto.setUserType(Integer.parseInt(req.getParameter("userType")));
                userInfoDto.setNickName(req.getParameter("nickName"));
                userInfoDto.setUserTel(req.getParameter("userTel"));
                userInfoDto.setEmail(req.getParameter("email"));
                userInfoDto.setUserState(Integer.parseInt(req.getParameter("userState")));
                userInfoDto.setSex(Integer.parseInt(req.getParameter("sex")));
                userInfoDto.setUserAddress(req.getParameter("userAddress"));
                req.getSession().setAttribute("newUser",userInfoDto);
                req.getSession().setAttribute("isShow",0);
                return "/org/userOrgList";
            case 2:
                req.setAttribute("userId", req.getParameter("userId"));
                return "/user/insUserAddressAdd";
            case 3:
                return "/area/area";
        }
        return "";
    }



    @RequestMapping(value = "/cclist")
    public String cclist(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
//        Integer orgType = Integer.parseInt(req.getParameter("orgType"));
        Integer orgType = 1;
        String isRole = this.callApi(BackendApiMethodEnum.BACKEND_IS_ROLE, null, req);
        Type isRoleType = new TypeToken<ApiFinalResponse<Boolean>>() {
        }.getType();
        ApiFinalResponse<Boolean> isRoleApiRsp = JsonUtil.jsonToObject(isRole, isRoleType);
        if(!Boolean.parseBoolean(String.valueOf(isRoleApiRsp.getResults()))) {
            req.setAttribute("isRole",0);
        }else{
            req.setAttribute("isRole",1);
        }
        // return "/org/userRoleList";
        if(orgType == 1){
            return "/org/userRoleCCList";
        }else if(orgType == 2){
            return "/org/userRoleListTo";
        }
        return null;
    }

    @RequestMapping(value = "/ccTreeData")
    public String ccTreeData(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_LIST, null, req);
        Type type = new TypeToken<ApiFinalResponse<List<OrgTreeData>>>() {
        }.getType();
        ApiFinalResponse<List<OrgTreeData>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
            List<OrgTreeData> tree = apiRsp.getResults();
            retJson = JsonUtil.objectToJson(tree);
        }
        return WebHelper.outputJson(retJson, rsp);
    }
    @RequestMapping(value = "/ccTreeDataOrg")
    public String ccTreeDataOrg(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String orgId=req.getParameter("orgId");
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");
        String orderByVisitNum=req.getParameter("orderByVisitNum");
        String orderBySignNum=req.getParameter("orderBySignNum");
        String userName=req.getParameter("userName");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isEmpty(startDate)) {// 设置默认查询时间为当天
            startDate = simpleDateFormat.format(new Date());
        }
        if (StringUtils.isEmpty(endDate)) {
            endDate = startDate;
        }
        if (StringUtils.isEmpty(orderByVisitNum) && StringUtils.isEmpty(orderBySignNum)) {
            orderBySignNum = "1";// 默认签约人数降序
        }
        Map map = new HashMap();
        map.put("orgId",orgId);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("orderByVisitNum",orderByVisitNum);
        map.put("orderBySignNum",orderBySignNum);
        map.put("userName",userName);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TREE_CC_DATA, map, req);
        System.out.println("测试使用: "+json);
        Type type = new TypeToken<ApiFinalResponse<List<ActivityDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<ActivityDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
//            List<ActivityDayReportDto> treeList = apiRsp.getResults();
            map.put("results",apiRsp.getResults());
        }
        retJson = JsonUtil.objectToJson(map);
        return WebHelper.outputJson(retJson, rsp);
    }
    @RequestMapping(value = "/queryCCListByOrgId")
    public String queryCCById(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId=req.getParameter("orgId");
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");
        String orderByVisitNum=req.getParameter("orderByVisitNum");
        String orderBySignNum=req.getParameter("orderBySignNum");
        String userName=req.getParameter("userName");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isEmpty(startDate)) {
            startDate = simpleDateFormat.format(new Date());
        }
        if (StringUtils.isEmpty(endDate)) {
            endDate = startDate;
        }
        if (StringUtils.isEmpty(orderByVisitNum) && StringUtils.isEmpty(orderBySignNum)) {
            orderBySignNum = "1";// 默认签约人数降序
        }
        Map map = new HashMap();
        map.put("orgId",orgId);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("orderByVisitNum",orderByVisitNum);
        map.put("orderBySignNum",orderBySignNum);
        map.put("userName",userName);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CC_LIST_DATA, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<ActivityDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<ActivityDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
//            List<ActivityDayReportDto> tree = apiRsp.getResults();
            map.put("results", apiRsp.getResults());
        }
        retJson = JsonUtil.objectToJson(map);
//        req.getSession().setAttribute("userOrgId",id);
        return WebHelper.outputJson(retJson, rsp);
    }

    @RequestMapping(value = "/queryUserListByOrgId")
    public String queryUserListByOrgId(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId=req.getParameter("orgId");
        Map map = new HashMap();
        map.put("orgId",orgId);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CC_USER_LIST, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {
        }.getType();
        ApiFinalResponse<List<UserInfo>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
            map.put("results", apiRsp.getResults());
        }
        retJson = JsonUtil.objectToJson(map);
        return WebHelper.outputJson(retJson, rsp);
    }

    // +++++++++++++++++++++++++++ 销售业绩方法 +++++++++++++++++++++++++++++++++++++++++++++++++++
    @RequestMapping(value = "/orgMoneyList")
    public String orgMoneyList(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        //        Integer orgType = Integer.parseInt(req.getParameter("orgType"));
        Integer orgType = 1;
        String isRole = this.callApi(BackendApiMethodEnum.BACKEND_IS_ROLE, null, req);
        Type isRoleType = new TypeToken<ApiFinalResponse<Boolean>>() {
        }.getType();
        ApiFinalResponse<Boolean> isRoleApiRsp = JsonUtil.jsonToObject(isRole, isRoleType);
        if(!Boolean.parseBoolean(String.valueOf(isRoleApiRsp.getResults()))) {
            req.setAttribute("isRole",0);
        }else{
            req.setAttribute("isRole",1);
        }
        // return "/org/userRoleList";
        if(orgType == 1){
            return "/org/orgMoneyList";
        }else if(orgType == 2){
            return "/org/userRoleListTo";
        }
        return null;
    }

    @RequestMapping(value = "/treeDataOrgMoney")
    public String treeDataOrgMoney(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String orgId=req.getParameter("orgId");
        String startMonth=req.getParameter("startMonth");
        String endMonth=req.getParameter("endMonth");
        String orderByCompletionRate=req.getParameter("orderByCompletionRate");
        String naturalMonth=req.getParameter("naturalMonth");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        // 设置默认自然月份
        if (StringUtils.isEmpty(naturalMonth)) {// 设置默认查询为当前月份
            naturalMonth = Calendar.getInstance().get(Calendar.MONTH) + 1 + "";
        }
        if(!StringUtils.isEmpty(startMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = null;
        }
        if(!StringUtils.isEmpty(endMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = null;
            if(StringUtils.isEmpty(startMonth)){// 如果存在最大时间没有最小时间使用默认当前时间为最小时间
                startMonth = simpleDateFormat.format(new Date());
            }
        }
        Map map = new HashMap();
        map.put("orgId",orgId);
        map.put("startMonth",startMonth);
        map.put("endMonth",endMonth);
        map.put("orderByResultPercent",orderByCompletionRate);
        map.put("naturalMonth",naturalMonth);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_MONEY_DATA, map, req);
        System.out.println("测试使用: "+json);
        Type type = new TypeToken<ApiFinalResponse<List<ActivityDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<ActivityDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
//            List<ActivityDayReportDto> treeList = apiRsp.getResults();
            map.put("results",apiRsp.getResults());
        }
        retJson = JsonUtil.objectToJson(map);
        return WebHelper.outputJson(retJson, rsp);
    }

    @RequestMapping(value = "/queryOrgMoneyListById")
    public String queryOrgMoneyListById(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId=req.getParameter("orgId");
        String startMonth=req.getParameter("startMonth");
        String endMonth=req.getParameter("endMonth");
        String orderByCompletionRate=req.getParameter("orderByCompletionRate");
        String naturalMonth=req.getParameter("naturalMonth");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        // 设置默认自然月份
        if (StringUtils.isEmpty(naturalMonth)) {// 设置默认查询为当前月份
            naturalMonth = Calendar.getInstance().get(Calendar.MONTH) + 1 + "";
        }
        if(!StringUtils.isEmpty(startMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = null;
        }
        if(!StringUtils.isEmpty(endMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = null;
            if(StringUtils.isEmpty(startMonth)){// 如果存在最大时间没有最小时间使用默认当前时间为最小时间
                startMonth = simpleDateFormat.format(new Date());
            }
        }

        if (StringUtils.isEmpty(orderByCompletionRate)) {
            orderByCompletionRate = "1";// 默认签约人数降序
        }
        Map map = new HashMap();
        map.put("orgId",orgId);
        map.put("startMonth",startMonth);
        map.put("endMonth",endMonth);
        map.put("orderByResultPercent",orderByCompletionRate);
        map.put("naturalMonth",naturalMonth);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_QUERY_ORG_MONEY, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<ActivityDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<ActivityDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
            map.put("results", apiRsp.getResults());
        }
        retJson = JsonUtil.objectToJson(map);
        return WebHelper.outputJson(retJson, rsp);
    }
// +++++++++++++++++++++++++++ 销售业绩方法 +++++++++++++++++++++++++++++++++++++++++++++++++++

    // +++++++++++++++++++++++++++ cc销售月业绩方法 +++++++++++++++++++++++++++++++++++++++++++++++++++
    @RequestMapping(value = "/orgCCMoneyList")
    public String orgCCMoneyList(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        //        Integer orgType = Integer.parseInt(req.getParameter("orgType"));
        Integer orgType = 1;
        String isRole = this.callApi(BackendApiMethodEnum.BACKEND_IS_ROLE, null, req);
        Type isRoleType = new TypeToken<ApiFinalResponse<Boolean>>() {
        }.getType();
        ApiFinalResponse<Boolean> isRoleApiRsp = JsonUtil.jsonToObject(isRole, isRoleType);
        if(!Boolean.parseBoolean(String.valueOf(isRoleApiRsp.getResults()))) {
            req.setAttribute("isRole",0);
        }else{
            req.setAttribute("isRole",1);
        }
        // return "/org/userRoleList";
        if(orgType == 1){
            return "/org/orgCCMoneyList";
        }else if(orgType == 2){
            return "/org/userRoleListTo";
        }
        return null;
    }

    @RequestMapping(value = "/treeDataOrgCCMoney")
    public String treeDataOrgCCMoney(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String orgId=req.getParameter("orgId");
        String startMonth=req.getParameter("startMonth");
        String endMonth=req.getParameter("endMonth");
        String orderByCompletionRate=req.getParameter("orderByCompletionRate");
        String naturalMonth=req.getParameter("naturalMonth");
        String userName=req.getParameter("userName");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        // 设置默认自然月份
        if (StringUtils.isEmpty(naturalMonth)) {// 设置默认查询为当前月份
            naturalMonth = Calendar.getInstance().get(Calendar.MONTH) + 1 + "";
        }
        if(!StringUtils.isEmpty(startMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = null;
        }
        if(!StringUtils.isEmpty(endMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = null;
            if(StringUtils.isEmpty(startMonth)){// 如果存在最大时间没有最小时间使用默认当前时间为最小时间
                startMonth = simpleDateFormat.format(new Date());
            }
        }
        Map map = new HashMap();
        map.put("orgId",orgId);
        map.put("startMonth",startMonth);
        map.put("endMonth",endMonth);
        map.put("orderByResultPercent",orderByCompletionRate);
        map.put("naturalMonth",naturalMonth);
        map.put("userName",userName);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_CC_MONEY_DATA, map, req);
        System.out.println("测试使用: "+json);
        Type type = new TypeToken<ApiFinalResponse<List<ActivityDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<ActivityDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
//            List<ActivityDayReportDto> treeList = apiRsp.getResults();
            map.put("results",apiRsp.getResults());
        }
        retJson = JsonUtil.objectToJson(map);
        return WebHelper.outputJson(retJson, rsp);
    }

    @RequestMapping(value = "/queryOrgCCMoneyListById")
    public String queryOrgCCMoneyListById(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId=req.getParameter("orgId");
        String startMonth=req.getParameter("startMonth");
        String endMonth=req.getParameter("endMonth");
        String orderByCompletionRate=req.getParameter("orderByCompletionRate");
        String naturalMonth=req.getParameter("naturalMonth");
        String userName=req.getParameter("userName");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        // 设置默认自然月份
        if (StringUtils.isEmpty(naturalMonth)) {// 设置默认查询为当前月份
            naturalMonth = Calendar.getInstance().get(Calendar.MONTH) + 1 + "";
        }
        if(!StringUtils.isEmpty(startMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = "";
        }
        if(!StringUtils.isEmpty(endMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = "";
            if(StringUtils.isEmpty(startMonth)){// 如果存在最大时间没有最小时间使用默认当前时间为最小时间
                startMonth = simpleDateFormat.format(new Date());
            }
        }

        if (StringUtils.isEmpty(orderByCompletionRate)) {
            orderByCompletionRate = "1";// 默认签约人数降序
        }
        Map map = new HashMap();
        map.put("orgId",orgId);
        map.put("startMonth",startMonth);
        map.put("endMonth",endMonth);
        map.put("orderByResultPercent",orderByCompletionRate);
        map.put("naturalMonth",naturalMonth);
        map.put("userName",userName);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_CC_MONEY_DATA, map, req);
        System.out.println(json);
        Type type = new TypeToken<ApiFinalResponse<List<ActivityDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<ActivityDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
            map.put("results", apiRsp.getResults());
        }
        retJson = JsonUtil.objectToJson(map);
        return WebHelper.outputJson(retJson, rsp);
    }
// +++++++++++++++++++++++++++ 销售业绩方法 +++++++++++++++++++++++++++++++++++++++++++++++++++

//++++++++++++++++++++导出excel表格+++++++++++++++++++++
@RequestMapping(value = "/out")
public void out(HttpServletRequest req, HttpServletResponse rsp) {
    String id=req.getParameter("id");
    String startDate=req.getParameter("startDate");
    String endDate=req.getParameter("endDate");
    String orderByVisitNum=req.getParameter("orderByVisitNum");
    String orderBySignNum=req.getParameter("orderBySignNum");

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    if (StringUtils.isEmpty(startDate)) {
        startDate = simpleDateFormat.format(new Date());
    }
    if (StringUtils.isEmpty(endDate)) {
        endDate = startDate;
    }
    if (StringUtils.isEmpty(orderByVisitNum) && StringUtils.isEmpty(orderBySignNum)) {
        orderBySignNum = "1";// 默认签约人数降序
    }
    Map map = new HashMap();
    map.put("id",id);
    map.put("startDate",startDate);
    map.put("endDate",endDate);
    map.put("orderByVisitNum",orderByVisitNum);
    map.put("orderBySignNum",orderBySignNum);
    String retJson = "";
    String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_INFO, map, req);
    Type type = new TypeToken<ApiFinalResponse<List<OrgActivityCountReportDto>>>() {
    }.getType();
    // 获取需要导出的数据
    ApiFinalResponse<List<OrgActivityCountReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
    if (apiRsp == null) {
       return;
    }

    // 创建excel
    List<OrgActivityCountReportDto> queryList = apiRsp.getResults();
    WritableWorkbook book = null; // 创建jxl工作簿
    String filename ="机构活动量统计表.xls";
    try {
        OutputStream os = rsp.getOutputStream();
        rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
        rsp.setContentType("application/msexcel");
        WritableWorkbook wwb=Workbook.createWorkbook(os);
        // 打开文件
        book = Workbook.createWorkbook(os);
        // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
        WritableSheet sheet = book.createSheet("机构活动量统计表", 0);

        // 设置表头
        sheet.addCell(new Label(0,0,"CC组名"));
        sheet.addCell(new Label(1,0,"拜访客户数"));
        sheet.addCell(new Label(2,0,"目标客户数"));
        sheet.addCell(new Label(3,0,"意向客户数"));
        sheet.addCell(new Label(4,0,"签约客户数"));
        sheet.addCell(new Label(5,0,"案件成交总额"));


        if(queryList!=null && !queryList.isEmpty()){
            for(int i=0; i<queryList.size(); i++){
                sheet.addCell(new Label(0,i+1,  queryList.get(i).getOrgName()));
                sheet.addCell(new Number(1,i+1, queryList.get(i).getVisitNum()));
                sheet.addCell(new Number(2,i+1,  queryList.get(i).getTargetNum()));
                sheet.addCell(new Number(3,i+1,  queryList.get(i).getIntentionNum()));
                sheet.addCell(new Number(4,i+1,  queryList.get(i).getSignNum()));
                sheet.addCell(new Number(5,i+1,  queryList.get(i).getSaleAmount()));
            }
        }

        // 写入数据并关闭文件
        book.write();
        book.close();
        os.close();
    } catch (Exception e) {
        System.out.println(e);
    }finally{
        if(book!=null){
            try {
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    return WebHelper.outputJson(retJson, rsp);
}

    @RequestMapping(value = "/outCC")
    public void outCC(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId=req.getParameter("orgId");
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");
        String orderByVisitNum=req.getParameter("orderByVisitNum");
        String orderBySignNum=req.getParameter("orderBySignNum");
        String userName=req.getParameter("userName");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isEmpty(startDate)) {
            startDate = simpleDateFormat.format(new Date());
        }
        if (StringUtils.isEmpty(endDate)) {
            endDate = startDate;
        }
        if (StringUtils.isEmpty(orderByVisitNum) && StringUtils.isEmpty(orderBySignNum)) {
            orderBySignNum = "1";// 默认签约人数降序
        }
        Map map = new HashMap();
        map.put("orgId",orgId);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("orderByVisitNum",orderByVisitNum);
        map.put("orderBySignNum",orderBySignNum);
        map.put("userName",userName);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CC_LIST_DATA, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<ActivityDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<ActivityDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }

        // 创建excel
        List<ActivityDayReportDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="机构CC活动量统计表.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            WritableWorkbook wwb=Workbook.createWorkbook(os);
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("机构CC活动量统计表", 0);

            // 设置表头
            sheet.addCell(new Label(0,0,"CC组名"));
            sheet.addCell(new Label(1,0,"CC姓名"));
            sheet.addCell(new Label(2,0,"拜访客户数"));
            sheet.addCell(new Label(3,0,"目标客户数"));
            sheet.addCell(new Label(4,0,"意向客户数"));
            sheet.addCell(new Label(5,0,"签约客户数"));
            sheet.addCell(new Label(6,0,"案件成交总额"));


            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0,i+1,  queryList.get(i).getOrgName()));
                    sheet.addCell(new Label(1,i+1, queryList.get(i).getUserName()));
                    sheet.addCell(new Number(2,i+1, queryList.get(i).getVisitNum()));
                    sheet.addCell(new Number(3,i+1,  queryList.get(i).getTargetNum()));
                    sheet.addCell(new Number(4,i+1,  queryList.get(i).getIntentionNum()));
                    sheet.addCell(new Number(5,i+1,  queryList.get(i).getSignNum()));
                    sheet.addCell(new Number(6,i+1,  queryList.get(i).getSaleAmount()));
                }
            }

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

//    return WebHelper.outputJson(retJson, rsp);
    }

    @RequestMapping(value = "/moneyOut")
    public void moneyOut(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId=req.getParameter("orgId");
        String startMonth=req.getParameter("startMonth");
        String endMonth=req.getParameter("endMonth");
        String orderByCompletionRate=req.getParameter("orderByCompletionRate");
        String naturalMonth=req.getParameter("naturalMonth");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        // 设置默认自然月份
        if (StringUtils.isEmpty(naturalMonth)) {// 设置默认查询为当前月份
            naturalMonth = Calendar.getInstance().get(Calendar.MONTH) + 1 + "";
        }
        if(!StringUtils.isEmpty(startMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = null;
        }
        if(!StringUtils.isEmpty(endMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = null;
            if(StringUtils.isEmpty(startMonth)){// 如果存在最大时间没有最小时间使用默认当前时间为最小时间
                startMonth = simpleDateFormat.format(new Date());
            }
        }

        if (StringUtils.isEmpty(orderByCompletionRate)) {
            orderByCompletionRate = "1";// 默认签约人数降序
        }
        Map map = new HashMap();
        map.put("orgId",orgId);
        map.put("startMonth",startMonth);
        map.put("endMonth",endMonth);
        map.put("orderByResultPercent",orderByCompletionRate);
        map.put("naturalMonth",naturalMonth);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_QUERY_ORG_MONEY, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<ActivityDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<ActivityDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }

        // 创建excel
        List<ActivityDayReportDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="机构业绩达成月报.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            WritableWorkbook wwb=Workbook.createWorkbook(os);
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("机构业绩达成月报", 0);

            // 设置表头
            sheet.addCell(new Label(0,0,"CC组名"));
            sheet.addCell(new Label(1,0,"案件成交数量"));
            sheet.addCell(new Label(2,0,"案件成交总额"));
            sheet.addCell(new Label(3,0,"案件目标总额"));
            sheet.addCell(new Label(4,0,"业绩完成率"));


            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0,i+1,  queryList.get(i).getOrgName()));
                    sheet.addCell(new Number(1,i+1, queryList.get(i).getSignNum()));
                    sheet.addCell(new Number(2,i+1, queryList.get(i).getSaleAmount()));
                    sheet.addCell(new Number(3,i+1,  queryList.get(i).getSaleGoal()));
                    sheet.addCell(new Number(4,i+1,  queryList.get(i).getCompletionRate()));

                }
            }

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

//    return WebHelper.outputJson(retJson, rsp);
    }
    @RequestMapping(value = "/moneyCCOut")
    public void moneyCCOut(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId=req.getParameter("orgId");
        String startMonth=req.getParameter("startMonth");
        String endMonth=req.getParameter("endMonth");
        String orderByCompletionRate=req.getParameter("orderByCompletionRate");
        String naturalMonth=req.getParameter("naturalMonth");
        String userName=req.getParameter("userName");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        // 设置默认自然月份
        if (StringUtils.isEmpty(naturalMonth)) {// 设置默认查询为当前月份
            naturalMonth = Calendar.getInstance().get(Calendar.MONTH) + 1 + "";
        }
        if(!StringUtils.isEmpty(startMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = "";
        }
        if(!StringUtils.isEmpty(endMonth)){
            // 如果晒选月份不为空, 置空自然月份
            naturalMonth = "";
            if(StringUtils.isEmpty(startMonth)){// 如果存在最大时间没有最小时间使用默认当前时间为最小时间
                startMonth = simpleDateFormat.format(new Date());
            }
        }

        if (StringUtils.isEmpty(orderByCompletionRate)) {
            orderByCompletionRate = "1";// 默认签约人数降序
        }
        Map map = new HashMap();
        map.put("orgId",orgId);
        map.put("startMonth",startMonth);
        map.put("endMonth",endMonth);
        map.put("orderByResultPercent",orderByCompletionRate);
        map.put("naturalMonth",naturalMonth);
        map.put("userName",userName);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_CC_MONEY_DATA, map, req);
        System.out.println(json);
        Type type = new TypeToken<ApiFinalResponse<List<ActivityDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<ActivityDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }

        // 创建excel
        List<ActivityDayReportDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="机构CC业绩达成月报.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("机构CC业绩达成月报", 0);

            // 设置表头
            sheet.addCell(new Label(0,0,"CC组名"));
            sheet.addCell(new Label(1,0,"CC姓名"));
            sheet.addCell(new Label(2,0,"案件成交数量"));
            sheet.addCell(new Label(3,0,"案件成交总额"));
            sheet.addCell(new Label(4,0,"案件目标总额"));
            sheet.addCell(new Label(5,0,"业绩完成率"));


            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0,i+1,  queryList.get(i).getOrgName()));
                    sheet.addCell(new Label(1,i+1,  queryList.get(i).getUserName()));
                    sheet.addCell(new Number(2,i+1, queryList.get(i).getSignNum()));
                    sheet.addCell(new Number(3,i+1, queryList.get(i).getSaleAmount()));
                    sheet.addCell(new Number(4,i+1,  queryList.get(i).getSaleGoal()));
                    sheet.addCell(new Number(5,i+1,  queryList.get(i).getCompletionRate()));

                }
            }

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

//    return WebHelper.outputJson(retJson, rsp);
    }

    @RequestMapping(value = "/selectArea")
    public String selectArea(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, null, req, rsp);
    }
}
