package com.lefancrm.backend.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.hzReport.ScoreDto;
import com.lefancrm.backend.dto.staff.*;
import com.lefancrm.backend.dto.staff.StaffOrganDepartmentDto;
import com.lefancrm.backend.util.ExcelReport;
import com.lefancrm.backend.util.FileZipUtil;
import com.lefancrm.backend.util.StaffExcel;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.HttpClientUtil;
import com.lefancrm.base.utils.JsonUtil;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.Boolean;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by wangwei on 2020年3月17日11:21:50
 * 人事管理 - 基础数据维护
 */
@Controller
@RequestMapping(value = "/staff")
public class BackendStaffController extends BackendBaseController{
    @Value("${survey.setting.source}")
    private String settingSource;
    @Value("${survey.file.path.sftp}")
    private String fileHttp;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Value("${survey.account.path}")
    private String accountExcelPath;

    /**
    *   list
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {

        String surveyCode = req.getParameter("surveyCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识
        model.put("pageSize", req.getParameter("pageSize"));//code标识
        model.put("state",req.getParameter("state") == null ? 0 : req.getParameter("state"));//默认启用
        if("personnelInfo".equals(surveyCode) || "personnelCostAnalysis".equals(surveyCode)){
            Map<String, Object> appendMap = new HashMap<String, Object>();

            String order = req.getParameter("order");
            if (!StringUtils.isEmpty(order)){
                appendMap.put("order",order);
            }
            String colSortType = req.getParameter("colSortType");
            if (!StringUtils.isEmpty(colSortType)){
                appendMap.put("colSortType",colSortType);
            }
            model.put("order",order);
            model.put("colSortType",colSortType);

            Map params=new HashMap();
            //公司信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","company");
            appendMap.put("havePage","no");//不分页
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffCompanyDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffCompanyDto> companys = (List<StaffCompanyDto>)apiFinalResponse.getResults();
            model.put("companys", companys);
            params.put("companysJson", JsonUtil.objectToJson(companys));
            //机构信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","organ");
            appendMap.put("havePage","no");//不分页
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffOrganDto> organs = (List<StaffOrganDto>)apiFinalResponse.getResults();
            model.put("organs", organs);
            params.put("organsJson", JsonUtil.objectToJson(organs));
            //部门信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","department");
            appendMap.put("havePage","no");//不分页
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffDepartmentDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffDepartmentDto> departments = (List<StaffDepartmentDto>)apiFinalResponse.getResults();
            model.put("departments", departments);
            params.put("departmentsJson", JsonUtil.objectToJson(departments));
            //岗位信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","jobPost");
            appendMap.put("havePage","no");//不分页
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffJobPostDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffJobPostDto> jobPosts = (List<StaffJobPostDto>)apiFinalResponse.getResults();
            model.put("jobPosts", jobPosts);
            params.put("jobPostsJson", JsonUtil.objectToJson(jobPosts));

            //岗位信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","team");
            appendMap.put("havePage","no");//不分页
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffTeamDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffTeamDto> teams = (List<StaffTeamDto>)apiFinalResponse.getResults();
            model.put("teams", teams);
            params.put("teamsJson", JsonUtil.objectToJson(teams));

            //调查员等级
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","team");
            appendMap.put("havePage","no");//不分页
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyLevelDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyLevelDto> levels = (List<SurveyLevelDto>)apiFinalResponse.getResults();
            model.put("levels", levels);
            params.put("levelsJson", JsonUtil.objectToJson(levels));

            model.put("params", params);
        }
        // 每月应上班天数
        if("workingDaysInfo".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffWorkingDaysInfoDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("workTime", req.getParameter("workTime"));
            return new ModelAndView("/staff/staffWorkingDaysInfo/list",model);
        }
        //员工管理
        else if("personnelInfo".equals(surveyCode)){
            Map<String,Object> appendMap = new HashMap<String, Object>();
            if (req.getParameter("staffStates") == null){
                appendMap.put("staffStates","1,2,3,4,5");
            }
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffPersonnelInfoDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("realName", req.getParameter("realName"));
            model.put("userTel", req.getParameter("userTel"));
            model.put("jobNo", req.getParameter("jobNo"));
            model.put("idCard", req.getParameter("idCard"));
//            model.put("businessUnitIds", req.getParameter("businessUnitIds"));
            model.put("companyIds", req.getParameter("companyIds"));
            model.put("staffState", req.getParameter("staffState"));
            model.put("staffStates", req.getParameter("staffStates"));

            model.put("searchStr",req.getParameter("searchStr"));//快捷查询

            model.put("organIds", req.getParameter("organIds"));
            model.put("departmentIds", req.getParameter("departmentIds"));
            model.put("jobPostIds", req.getParameter("jobPostIds"));
            model.put("teamIds", req.getParameter("teamIds"));
            model.put("relation", req.getParameter("relation"));
            model.put("entryTimeStart", req.getParameter("entryTimeStart"));
            model.put("entryTimeEnd", req.getParameter("entryTimeEnd"));
            model.put("quitTimeStart", req.getParameter("quitTimeStart"));
            model.put("quitTimeEnd", req.getParameter("quitTimeEnd"));

            return new ModelAndView("/staff/staffPersonnelInfo/list",model);
        }
        //事业部
        else if("businessUnit".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffBusinessUnitDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("name", req.getParameter("name"));
            return new ModelAndView("/staff/base/staffBusinessUnit/list",model);
        }
        //公司
        else if("company".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffCompanyDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("name", req.getParameter("name"));
            return new ModelAndView("/staff/base/staffCompany/list",model);
        }
        //预算归属公司
        else if("budgetCompany".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("name", req.getParameter("name"));
            return new ModelAndView("/staff/base/staffBudgetCompany/list",model);
        }
        //机构
        else if("organ".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("name", req.getParameter("name"));
            return new ModelAndView("/staff/base/staffOrgan/list",model);
        }
        //部门
        else if("department".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffDepartmentDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("name", req.getParameter("name"));
            return new ModelAndView("/staff/base/staffDepartment/list",model);
        }
        //小组
        else if("team".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffTeamDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("name", req.getParameter("name"));
            return new ModelAndView("/staff/base/staffTeam/list",model);
        }
        //岗位_
        else if("jobPost".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffJobPostDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("name", req.getParameter("name"));
            return new ModelAndView("/staff/base/staffJobPost/list",model);
        }
        //工资条
        else if("paySlip".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffPaySlipDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("workTime", req.getParameter("workTime"));
            model.put("type", req.getParameter("type"));//机构查看工资条标记

            //判断是不是人事角色，控制添加 按钮
            typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
            List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
            Boolean isHr = false;
            Boolean organManager = false;
            Boolean hrManager = false;
            Boolean superManager = false;//分管总
            for (BusUserRoleDto busUserRoleDto : userRoles) {
                if(busUserRoleDto.getRoleId() == 100){
                    isHr = true;
                }
                if(busUserRoleDto.getRoleId() == 108){
                    organManager = true;
                }
                if(busUserRoleDto.getRoleId() == 107){
                    hrManager = true;
                }
                if(busUserRoleDto.getRoleId().intValue() == 109){
                    superManager = true;
                }
            }
            model.put("isHr", isHr);
            model.put("organManager", organManager);
            model.put("hrManager", hrManager);
            model.put("superManager",superManager);
            Boolean showPassWord = false;
            if (userRoles.get(0).getUserId() == 2411) { //周军的账号，特殊显示密码
                model.put("showPassWord", true);
            }

            return new ModelAndView("/staff/staffPaySlip/list",model);
        }
        //绩效
        else if("performance".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffPerformanceDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("workTime", req.getParameter("workTime"));
            model.put("type", req.getParameter("type"));//机构查看工资条标记
            //判断是不是人事角色，控制添加 按钮
            typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
            List<BusUserRoleDto> list = (List<BusUserRoleDto>)apiFinalResponse.getResults();
            Boolean isHr = false;//人事专员
            Boolean organManager = false; //机构经理
            Boolean hrManager = false;
            Boolean superManager = false;//分管总
            for (BusUserRoleDto busUserRoleDto : list) {
                if(busUserRoleDto.getRoleId() == 100){
                    isHr = true;
                }
                if(busUserRoleDto.getRoleId() == 108){
                    organManager = true;
                }
                if(busUserRoleDto.getRoleId() == 107){
                    hrManager = true;
                }
                if(busUserRoleDto.getRoleId().intValue() == 109){
                    superManager = true;
                }
            }
            model.put("isHr", isHr);
            model.put("organManager", organManager);
            model.put("hrManager", hrManager);
            model.put("superManager",superManager);
            Boolean showPassWord = false;
            if (list.get(0).getUserId() == 2411) { //周军的账号，特殊显示密码
                model.put("showPassWord", true);
            }
            Boolean surveyUser = true; //调查员（场景：仅仅是调查员，不包含其余几个角色）
            for (BusUserRoleDto busUserRoleDto : list) {
                if(busUserRoleDto.getRoleId() == 100 || busUserRoleDto.getRoleId() == 103 || busUserRoleDto.getRoleId() == 104
                        || busUserRoleDto.getRoleId() == 107 || busUserRoleDto.getRoleId() == 108 || busUserRoleDto.getRoleId() == 109 ){
                    surveyUser = false; break;
                }
            }
            model.put("surveyUser", surveyUser);

            return new ModelAndView("/staff/staffPerformance/list",model);
        }
        //人员变更历史记录
        else if("personnelInfoLog".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffPersonnelInfoLogDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("staffPersonnelInfoId", req.getParameter("staffPersonnelInfoId"));
            return new ModelAndView("/staff/staffPersonnelInfo/logList",model);
        }
        //人力成本分析
        else if("personnelCostAnalysis".equals(surveyCode)){
            return new ModelAndView("/staff/staffPersonnelCostAnalysis/list",model);
        }
        return null;
    }

    /**
     *   edit
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit(HttpServletRequest req, HttpServletResponse rsp) {

        String surveyCode = req.getParameter("surveyCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识
        model.put("id", req.getParameter("id"));
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode",surveyCode);
        //每月应上班天数
        if("workingDaysInfo".equals(surveyCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffWorkingDaysInfoDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffWorkingDaysInfo", apiFinalResponse.getResults());
            //获取上一个最新月份
            typeToken = new TypeToken<ApiFinalResponse<List<StaffWorkingDaysInfoDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            List<StaffWorkingDaysInfoDto> staffWorkingDaysInfoDtos = (List<StaffWorkingDaysInfoDto>) apiFinalResponse.getResults();
            if(staffWorkingDaysInfoDtos!=null && staffWorkingDaysInfoDtos.size()> 0){
                StaffWorkingDaysInfoDto lastStaffWorkingDaysInfoDto = staffWorkingDaysInfoDtos.get(0);
                if(lastStaffWorkingDaysInfoDto !=null){
                    model.put("lastWorkTime", getLastWorkTime(lastStaffWorkingDaysInfoDto.getWorkTime()));
                }
            }
            return new ModelAndView("/staff/staffWorkingDaysInfo/edit", model);
        }
        //员工管理
        else if("personnelInfo".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffPersonnelInfoDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffPersonnelInfo", apiFinalResponse.getResults());
            StaffPersonnelInfoDto info = (StaffPersonnelInfoDto)apiFinalResponse.getResults();
            Map params=new HashMap();
            //公司信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","company");
            appendMap.put("havePage","no");
            typeToken = new TypeToken<ApiFinalResponse<List<StaffCompanyDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffCompanyDto> companys = (List<StaffCompanyDto>)apiFinalResponse.getResults();
            model.put("companys", companys);

            //预算归属公司信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","budgetCompany");
            appendMap.put("havePage","no");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffBudgetCompanyDto> staffBudgetCompanyDtoList = (List<StaffBudgetCompanyDto>)apiFinalResponse.getResults();
            model.put("staffBudgetCompanyDtoList", staffBudgetCompanyDtoList);
            //部门信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("havePage","no");
            appendMap.put("surveyCode","department");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffDepartmentDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffDepartmentDto> departments = (List<StaffDepartmentDto>)apiFinalResponse.getResults();
            model.put("departments", departments);
            params.put("departmentsJson", JsonUtil.objectToJson(departments));
            //小组信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("havePage","no");
            appendMap.put("surveyCode","team");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffTeamDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffTeamDto> teams = (List<StaffTeamDto>)apiFinalResponse.getResults();
            model.put("teams", teams);
            params.put("teamsJson", JsonUtil.objectToJson(teams));
            //岗位信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("havePage","no");
            appendMap.put("surveyCode","jobPost");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffJobPostDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffJobPostDto> jobPosts = (List<StaffJobPostDto>)apiFinalResponse.getResults();
            model.put("jobPosts", jobPosts);
            params.put("jobPostsJson", JsonUtil.objectToJson(jobPosts));

            //调查员等级
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","level");
            appendMap.put("havePage","no");//不分页
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyLevelDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyLevelDto> levels = (List<SurveyLevelDto>)apiFinalResponse.getResults();
            model.put("levels", levels);

            //岗位职级下拉列表数据
            typeToken = new TypeToken<ApiFinalResponse<List<StaffPostRankDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_QUERY_STAFF_POST_RANK_LIST_ALL, appendMap, req);
            List<StaffPostRankDto> staffPostRankDtoList = (List<StaffPostRankDto>)apiFinalResponse.getResults();
            model.put("staffPostRankDtoList", staffPostRankDtoList);
            //职务称谓
            typeToken = new TypeToken<ApiFinalResponse<List<StaffPostAppellationDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_QUERY_STAFF_POST_APPLLATION_LIST_ALL, appendMap, req);
            List<StaffPostAppellationDto> staffPostAppellationDtoList = (List<StaffPostAppellationDto>)apiFinalResponse.getResults();
            model.put("staffPostAppellationDtoList", staffPostAppellationDtoList);

            //职务称谓下拉列表数据


            //机构信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("havePage","no");
//                appendMap.put("companyId", info.getBudgetCompanyId());//对应的是社保缴纳公司id
            appendMap.put("surveyCode","companyOrganY");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffCompanyOrganDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffCompanyOrganDto> organs = (List<StaffCompanyOrganDto>)apiFinalResponse.getResults();
            model.put("organs", organs);
            params.put("organsJson",JsonUtil.objectToJson(organs));

            if(info != null){
                //公司信息
//                appendMap = new HashMap<String, Object>();
//                appendMap.put("havePage","no");
//                appendMap.put("businessUnitId",info.getBusinessUnitId());
//                appendMap.put("surveyCode","businessUnitCompany");
//                typeToken = new TypeToken<ApiFinalResponse<List<StaffBusinessUnitCompanyDto>>>() {};
//                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
//                List<StaffBusinessUnitCompanyDto> companys = (List<StaffBusinessUnitCompanyDto>)apiFinalResponse.getResults();
//                model.put("companys", companys);



            }
            model.put("params",params);
            model.put("type", req.getParameter("type"));
            return new ModelAndView("/staff/staffPersonnelInfo/edit", model);
        }
        //事业部
        else if("businessUnit".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffBusinessUnitDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffBusinessUnit", apiFinalResponse.getResults());
            return new ModelAndView("/staff/base/staffBusinessUnit/edit", model);
        }
        //公司
        else if("company".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffCompanyDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffCompany", apiFinalResponse.getResults());
            return new ModelAndView("/staff/base/staffCompany/edit", model);
        }
        //预算归属公司
        else if("budgetCompany".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffBudgetCompanyDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffCompany", apiFinalResponse.getResults());
            return new ModelAndView("/staff/base/staffBudgetCompany/edit", model);
        }
        //机构
        else if("organ".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffOrganDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffOrgan", apiFinalResponse.getResults());
            StaffOrganDto staffOrganDto = (StaffOrganDto)apiFinalResponse.getResults();
            if (staffOrganDto != null){
                List<StaffOrganProduct> staffOrganProductList=staffOrganDto.getStaffOrganProductList();
                model.put("staffOrganProductsJson", JsonUtil.objectToJson(staffOrganProductList));
            }
            Map params = new HashMap();
            //获取所有“员工管理”中所有非 已离职与离职（待结算）状态的员工
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","personnelInfo");
            appendMap.put("havePage","no");
            appendMap.put("search",2);//非 已离职与离职（待结算）状态的员工
            typeToken = new TypeToken<ApiFinalResponse<List<StaffPersonnelInfoDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffPersonnelInfoDto> personnelInfos = (List<StaffPersonnelInfoDto>)apiFinalResponse.getResults();
            model.put("personnelInfos", personnelInfos);
            params.put("personnelInfosJson", JsonUtil.objectToJson(personnelInfos));
            model.put("params",params);

            appendMap.put("enumCode","billingEnum");
            typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
            List<CommonEnumDto> results = (List<CommonEnumDto>) apiFinalResponse.getResults();
//            CommonEnumDto commonEnumDto = new CommonEnumDto();
//            commonEnumDto.setId(-100l);
//            commonEnumDto.setEnumName("互助+保司");
//            results.add(commonEnumDto);
            model.put("billListJson",JSON.toJSONString(results));
            return new ModelAndView("/staff/base/staffOrgan/edit", model);
        }
        //部门
        else if("department".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffDepartmentDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffDepartment", apiFinalResponse.getResults());
            return new ModelAndView("/staff/base/staffDepartment/edit", model);
        }
        //岗位
        else if("jobPost".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffJobPostDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffJobPost", apiFinalResponse.getResults());
            return new ModelAndView("/staff/base/staffJobPost/edit", model);
        }
        //小组
        else if("team".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffTeamDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffTeam", apiFinalResponse.getResults());
            return new ModelAndView("/staff/base/staffTeam/edit", model);
        }
        //工资条
        else if("paySlip".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffPaySlipDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffPaySlip", apiFinalResponse.getResults());
            //获取上一个最新月份
            typeToken = new TypeToken<ApiFinalResponse<List<StaffPaySlipDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            List<StaffPaySlipDto> staffPaySlipDtos = (List<StaffPaySlipDto>) apiFinalResponse.getResults();
            if(staffPaySlipDtos != null && staffPaySlipDtos.size()> 0){
                StaffPaySlipDto lastStaffPaySlipDto = staffPaySlipDtos.get(0);
                if(lastStaffPaySlipDto !=null){
                    model.put("lastWorkTime", getLastWorkTime(lastStaffPaySlipDto.getWorkTime()));
                }
            }
            return new ModelAndView("/staff/staffPaySlip/edit", model);
        }
        //绩效
        else if("performance".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffPerformanceDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
            model.put("staffPerformance", apiFinalResponse.getResults());

            //获取上一个最新月份
            typeToken = new TypeToken<ApiFinalResponse<List<StaffPerformanceDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req);
            List<StaffPerformanceDto> staffPerformanceDtos = (List<StaffPerformanceDto>) apiFinalResponse.getResults();
            if(staffPerformanceDtos!=null && staffPerformanceDtos.size()> 0){
                StaffPerformanceDto lastStaffPerformanceDto = staffPerformanceDtos.get(0);
                if(lastStaffPerformanceDto !=null){
                    model.put("lastWorkTime", getLastWorkTime(lastStaffPerformanceDto.getWorkTime()));
                }
            }
            return new ModelAndView("/staff/staffPerformance/edit", model);
        }
        return null;
    }

    /**
     * info页面
     */
    @RequestMapping(value = "/info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp) {

        String surveyCode = req.getParameter("surveyCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识
        model.put("id", req.getParameter("id"));
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode",surveyCode);
        //每月应上班天数
        if("workingDaysInfo".equals(surveyCode)) {
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffWorkingDaysInfoDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO, appendMap, req);
            model.put("surveyBusinessType", apiFinalResponse.getResults());
            return new ModelAndView("/staff/staffWorkingDaysInfo/info", model);
        }
        //员工管理
        else if("personnelInfo".equals(surveyCode)){

        }
        //事业部
        else if("businessUnit".equals(surveyCode)){

        }
        //公司
        else if("company".equals(surveyCode)){

        }
        //机构
        else if("organ".equals(surveyCode)){

        }
        //部门
        else if("department".equals(surveyCode)){

        }
        //岗位
        else if("jobPost".equals(surveyCode)){

        }
        //工资条
        else if("paySlip".equals(surveyCode) || "performance".equals(surveyCode)){//工资paySlip  绩效performance
            //社保缴纳公司信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","company");
            appendMap.put("havePage","no");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffCompanyDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffCompanyDto> companys = (List<StaffCompanyDto>)apiFinalResponse.getResults();
            model.put("companys", companys);

            //成本归属公司
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","budgetCompany");
            appendMap.put("havePage","no");
            typeToken = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffBudgetCompanyDto> budgetCompanys = (List<StaffBudgetCompanyDto>)apiFinalResponse.getResults();
            model.put("budgetCompanys", budgetCompanys);

            //机构信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "organ");
            appendMap.put("havePage","no");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffOrganDto> organs = (List<StaffOrganDto>)apiFinalResponse.getResults();
            model.put("organs", organs);
            //部门信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","department");
            appendMap.put("havePage","no");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffDepartmentDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffDepartmentDto> departments = (List<StaffDepartmentDto>)apiFinalResponse.getResults();
            model.put("departments", departments);
            //岗位信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","jobPost");
            appendMap.put("havePage","no");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffJobPostDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffJobPostDto> jobPosts = (List<StaffJobPostDto>)apiFinalResponse.getResults();
            model.put("jobPosts", jobPosts);

            //小组信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","team");
            appendMap.put("havePage","no");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffTeamDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffTeamDto> teams = (List<StaffTeamDto>)apiFinalResponse.getResults();
            model.put("teams", teams);

            Map params=new HashMap();
            params.put("companysJson", JsonUtil.objectToJson(companys));
            params.put("budgetCompanysJson", JsonUtil.objectToJson(budgetCompanys));
            params.put("organsJson", JsonUtil.objectToJson(organs));
            params.put("departmentsJson", JsonUtil.objectToJson(departments));
            params.put("jobPostsJson", JsonUtil.objectToJson(jobPosts));
            params.put("teamsJson", JsonUtil.objectToJson(teams));
            model.put("params", params);

            if ("paySlip".equals(surveyCode)){
                appendMap.put("id", req.getParameter("id"));
                typeToken = new TypeToken<ApiFinalResponse<StaffPaySlipDto>>() {};
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_PAY_SLIP_INFO_KEY, appendMap, req);
                StaffPaySlipDto staffPaySlip = (StaffPaySlipDto)apiFinalResponse.getResults();
                model.put("staffPaySlip", staffPaySlip);//工资单数据

                //查看页面
                String type = req.getParameter("type");
                if("view".equals(type)){
                    return new ModelAndView("/staff/staffPaySlip/view",model);
                }
                return new ModelAndView("/staff/staffPaySlip/info",model);
            }else if ("performance".equals(surveyCode)){
                appendMap.put("id", req.getParameter("id"));
                typeToken = new TypeToken<ApiFinalResponse<StaffPerformanceDto>>() {};
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_PERFORMANCE_INFO_KEY, appendMap, req);
                StaffPerformanceDto staffPerformance = (StaffPerformanceDto)apiFinalResponse.getResults();
                model.put("staffPerformance",staffPerformance);

                //查看页面
                String type = req.getParameter("type");
                if("view".equals(type)){
                    return new ModelAndView("/staff/staffPerformance/view",model);
                }
                return new ModelAndView("/staff/staffPerformance/info",model);
            }
        }
        return null;
    }

    /**
     * 新增或修改数据
     */
    @RequestMapping(value = "/update")
    public String update(HttpServletRequest req, HttpServletResponse rsp) {
        String surveyCode = req.getParameter("surveyCode");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode", surveyCode);//code标识
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_UPDATE, null, req, rsp);
    }

    /**
     * 根据id，查询关联的子表数据
     */
    @RequestMapping(value = "/selectStaffInfoByRelationId")
    public String selectStaffInfoByRelationId(HttpServletRequest req, HttpServletResponse rsp) {
        String surveyCode = req.getParameter("surveyCode");
        String btnCode = req.getParameter("btnCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识

        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode",surveyCode);
        appendMap.put("btnCode",btnCode);
        //根据“事业部id”，查询“公司”数据
        if("businessUnit".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                Long businessUnitId = Long.parseLong(req.getParameter("businessUnitId"));
                appendMap.put("businessUnitId", businessUnitId);
                appendMap.put("havePage","no");//不分页
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据“公司id”，查询“机构”数据
        else if("staffBudgetCompany".equals(surveyCode)){
            if("1000".equals(btnCode)) {
                Long budgetCompanyId = Long.parseLong(req.getParameter("budgetCompanyId"));
                appendMap.put("budgetCompanyId", budgetCompanyId);
                appendMap.put("havePage","no");//不分页
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据“机构id”，查询“部门”数据
        else if("organ".equals(surveyCode)){
            if("1000".equals(btnCode)) {
                Long organId = Long.parseLong(req.getParameter("organId"));
                appendMap.put("organId", organId);
                appendMap.put("havePage","no");//不分页
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据“部门id”，查询“岗位”数据
        else if("department".equals(surveyCode)){
            if("1000".equals(btnCode)) {
                Long departmentId = Long.parseLong(req.getParameter("departmentId"));
                appendMap.put("departmentId", departmentId);
                appendMap.put("havePage","no");//不分页
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据“小组id”，查询“岗位”数据
        else if("team".equals(surveyCode)){
            if("1000".equals(btnCode)) {
                Long teamId = Long.parseLong(req.getParameter("teamId"));
                appendMap.put("teamId", teamId);
                appendMap.put("havePage","no");//不分页
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据“岗位id”，查询“人员”数据
        else if("jobPost".equals(surveyCode)){
            if("1000".equals(btnCode)) {
                Long jobPostId = Long.parseLong(req.getParameter("jobPostId"));
                appendMap.put("jobPostId", jobPostId);
                appendMap.put("havePage","no");//不分页
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据“手机号”、“工号”、“身份证”，查询“人员”数据
        else if("personnelInfo".equals(surveyCode)){
            if("1001".equals(btnCode) || "1002".equals(btnCode) || "1003".equals(btnCode)) {
                String info = req.getParameter("info");
                appendMap.put("info", info);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //工资条管理
        else if("paySlip".equals(surveyCode)){
            //根据月份 查询
            if("2000".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //绩效管理
        else if("performance".equals(surveyCode)){
            //根据月份 查询
            if("2000".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SELECT_STAFF_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        return null;
    }

    /**
     * 操作-弹窗页面
     */
    @RequestMapping(value = "/popup")
    public ModelAndView popup(HttpServletRequest req, HttpServletResponse rsp) {

        String surveyCode = req.getParameter("surveyCode");
        String btnCode = req.getParameter("btnCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识
        model.put("btnCode", btnCode);//code标识

        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode",surveyCode);
        //事业部
        if("businessUnit".equals(surveyCode)) {
            String businessUnitId = req.getParameter("businessUnitId");
            model.put("businessUnitId",businessUnitId);
            //设置公司
            if("1000".equals(btnCode)){
                //所有的公司
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("surveyCode","company");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffCompanyDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
                List<StaffCompanyDto> companys = (List<StaffCompanyDto>) apiFinalResponse.getResults();
                model.put("companys",companys);

                //名下公司
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("businessUnitId",businessUnitId);
                appendMap.put("surveyCode","businessUnitCompany");
                typeToken = new TypeToken<ApiFinalResponse<List<StaffBusinessUnitCompanyDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffBusinessUnitCompanyDto> businessUnitCompanys = (List<StaffBusinessUnitCompanyDto>) apiFinalResponse.getResults();
                model.put("businessUnitCompanys",businessUnitCompanys);
                return new ModelAndView("/staff/base/staffBusinessUnit/companyList",model);
            }else if("2000".equals(btnCode)){
                //名下公司
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("businessUnitId",businessUnitId);
                appendMap.put("surveyCode","businessUnitCompany");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffBusinessUnitCompanyDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffBusinessUnitCompanyDto> businessUnitCompanys = (List<StaffBusinessUnitCompanyDto>) apiFinalResponse.getResults();
                model.put("businessUnitCompanys",businessUnitCompanys);
                return new ModelAndView("/staff/base/staffBusinessUnit/companyList",model);
            }
        }
        //公司
        else if("company".equals(surveyCode)) {
            String companyId = req.getParameter("companyId");
            model.put("companyId",companyId);
            //设置机构
            if("1000".equals(btnCode)){
                //所有的机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("surveyCode","organ");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
                List<StaffOrganDto> organs = (List<StaffOrganDto>) apiFinalResponse.getResults();
                model.put("organs",organs);

                //名下机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("surveyCode","companyOrgan");
                typeToken = new TypeToken<ApiFinalResponse<List<StaffCompanyOrganDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffCompanyOrganDto> companyOrgans = (List<StaffCompanyOrganDto>) apiFinalResponse.getResults();
                model.put("companyOrgans",companyOrgans);
                return new ModelAndView("/staff/base/staffCompany/organList",model);
            }else if("2000".equals(btnCode)){
                //名下机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("surveyCode","companyOrgan");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffCompanyOrganDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffCompanyOrganDto> companyOrgans = (List<StaffCompanyOrganDto>) apiFinalResponse.getResults();
                model.put("companyOrgans",companyOrgans);
                return new ModelAndView("/staff/base/staffCompany/organList",model);
            }
        }
        //预算归属公司
        else if("budgetCompany".equals(surveyCode)) {
            String companyId = req.getParameter("companyId");
            model.put("companyId",companyId);
            //设置机构
            if("1000".equals(btnCode)){
                //所有的机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("surveyCode","organ");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
                List<StaffOrganDto> organs = (List<StaffOrganDto>) apiFinalResponse.getResults();
                model.put("organs",organs);

                //名下机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("surveyCode","budgetCompanyOrgan");
                typeToken = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyOrganDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffBudgetCompanyOrganDto> companyOrgans = (List<StaffBudgetCompanyOrganDto>) apiFinalResponse.getResults();
                model.put("companyOrgans",companyOrgans);
                return new ModelAndView("/staff/base/staffBudgetCompany/organList",model);
            }else if("2000".equals(btnCode)){
                //名下机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("surveyCode","budgetCompanyOrgan");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyOrganDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffBudgetCompanyOrganDto> companyOrgans = (List<StaffBudgetCompanyOrganDto>) apiFinalResponse.getResults();
                model.put("companyOrgans",companyOrgans);
                return new ModelAndView("/staff/base/staffBudgetCompany/organList",model);
            }
        }
        //机构
        else if("organ".equals(surveyCode)) {
            String companyId = req.getParameter("companyId");
            model.put("companyId",companyId);
            String organId = req.getParameter("organId");
            model.put("organId",organId);
            //设置部门
            if("1000".equals(btnCode)){
                //所有的部门
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("surveyCode","department");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffDepartmentDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
                List<StaffDepartmentDto> departments = (List<StaffDepartmentDto>) apiFinalResponse.getResults();
                model.put("departments",departments);

                //名下机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("organId",organId);
                appendMap.put("surveyCode","organDepartment");
                typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDepartmentDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffOrganDepartmentDto> organDepartments = (List<StaffOrganDepartmentDto>) apiFinalResponse.getResults();
                model.put("organDepartments",organDepartments);
                return new ModelAndView("/staff/base/staffOrgan/departmentList",model);
            }else if("2000".equals(btnCode)){
                //名下部门
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("organId",organId);
                appendMap.put("surveyCode","organDepartment");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDepartmentDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffOrganDepartmentDto> organDepartments = (List<StaffOrganDepartmentDto>) apiFinalResponse.getResults();
                model.put("organDepartments",organDepartments);
                return new ModelAndView("/staff/base/staffOrgan/departmentList",model);
            }
        }
        //部门
        else if("department".equals(surveyCode)){
            String companyId = req.getParameter("companyId");
            model.put("companyId",companyId);
            String organId = req.getParameter("organId");
            model.put("organId",organId);
            String departmentId = req.getParameter("departmentId");
            model.put("departmentId",departmentId);

            //设置小组
            if("1000".equals(btnCode)){
                //所有的小组
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("surveyCode","team");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffTeamDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
                List<StaffTeamDto> teams = (List<StaffTeamDto>) apiFinalResponse.getResults();
                model.put("teams",teams);

                //名下小组
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("organId",organId);
                appendMap.put("departmentId",departmentId);
                appendMap.put("surveyCode","departmentTeam");
                typeToken = new TypeToken<ApiFinalResponse<List<StaffDepartmentTeamDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffDepartmentTeamDto> departmentTeams = (List<StaffDepartmentTeamDto>) apiFinalResponse.getResults();
                model.put("departmentTeams",departmentTeams);
//                return new ModelAndView("/staff/base/staffDepartment/jobPostList",model);
                return new ModelAndView("/staff/base/staffDepartment/teamList",model);
            }else if("2000".equals(btnCode)){
                //名下岗位
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("organId",organId);
                appendMap.put("departmentId",departmentId);
                appendMap.put("surveyCode","departmentTeam");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffDepartmentTeamDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffDepartmentTeamDto> departmentTeams = (List<StaffDepartmentTeamDto>) apiFinalResponse.getResults();
                model.put("departmentTeams",departmentTeams);
//                return new ModelAndView("/staff/base/staffDepartment/jobPostList",model);
                return new ModelAndView("/staff/base/staffDepartment/teamList",model);
            }
        }
        //小组
        else if("team".equals(surveyCode)){
            String companyId = req.getParameter("companyId");
            model.put("companyId",companyId);
            String organId = req.getParameter("organId");
            model.put("organId",organId);
            String departmentId = req.getParameter("departmentId");
            model.put("departmentId",departmentId);
            String teamId = req.getParameter("teamId");
            model.put("teamId",teamId);
            //设置岗位
            if("1000".equals(btnCode)){
                //所有的岗位
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("surveyCode","jobPost");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffJobPostDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
                List<StaffJobPostDto> jobPosts = (List<StaffJobPostDto>) apiFinalResponse.getResults();
                model.put("jobPosts",jobPosts);

                //名下岗位
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("organId",organId);
                appendMap.put("departmentId",departmentId);
                appendMap.put("teamId",teamId);
                appendMap.put("surveyCode","teamJobPost");
                typeToken = new TypeToken<ApiFinalResponse<List<StaffTeamJobPostDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffTeamJobPostDto> teamJobPosts = (List<StaffTeamJobPostDto>) apiFinalResponse.getResults();
                model.put("teamJobPosts",teamJobPosts);
                return new ModelAndView("/staff/base/staffTeam/jobPostList",model);
            }else if("2000".equals(btnCode)){
                //名下岗位
                appendMap = new HashMap<String, Object>();
                appendMap.put("havePage","no"); //不分页
                appendMap.put("companyId",companyId);
                appendMap.put("organId",organId);
                appendMap.put("departmentId",departmentId);
                appendMap.put("teamId",teamId);
                appendMap.put("surveyCode","teamJobPost");
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffTeamJobPostDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
                List<StaffTeamJobPostDto> teamJobPosts = (List<StaffTeamJobPostDto>) apiFinalResponse.getResults();
                model.put("teamJobPosts",teamJobPosts);
                return new ModelAndView("/staff/base/staffTeam/jobPostList",model);
            }
        }
        //绩效
        else if("performance".equals(surveyCode)){
            if("case".equals(btnCode) || "caseNum".equals(btnCode) || "examineCaseNum".equals(btnCode)){
                model.put("pageSize",req.getParameter("pageSize"));

                //积分案件list
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ScoreDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PERFORMANCE_SCORE_CASE_LIST, null, req);
                req.setAttribute("apiRsp", apiFinalResponse);

                //单条绩效明细
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode","performancePersonnel");
                typeToken = new TypeToken<ApiFinalResponse<StaffPerformancePersonnelDto>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_INFO, appendMap, req);
                StaffPerformancePersonnelDto staffPerformancePersonnel = (StaffPerformancePersonnelDto) apiFinalResponse.getResults();
                model.put("staffPerformancePersonnel",staffPerformancePersonnel);

                //当前绩效的阶段
                model.put("roleCode",req.getParameter("roleCode"));
                model.put("orgAttr",req.getParameter("orgAttr"));
                model.put("staffPerformanceId",req.getParameter("staffPerformanceId"));
                model.put("userId",req.getParameter("userId"));
                model.put("id",req.getParameter("id"));
                model.put("searchStr",req.getParameter("searchStr"));

                //角色（）
                typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
                List<BusUserRoleDto> list = (List<BusUserRoleDto>)apiFinalResponse.getResults();
                Boolean surveyUser = false; //调查员
                Boolean organManager = false; //机构经理
                Boolean isManager = false;//是否管理层（分管总、人事主管、总经理）

                for (BusUserRoleDto busUserRoleDto : list) {
                    if(busUserRoleDto.getRoleId() == 50){
                        surveyUser = true;
                    }
                    if(busUserRoleDto.getRoleId() == 108){
                        organManager = true;
                    }
                    if(busUserRoleDto.getRoleId() == 100 ||busUserRoleDto.getRoleId() == 103 || busUserRoleDto.getRoleId() == 107 || busUserRoleDto.getRoleId() == 109){
                        isManager = true;
                    }
                }
                model.put("surveyUser", surveyUser);
                model.put("isManager", isManager);
                model.put("organManager", organManager);
            }
            model.put("btnCode",btnCode);
            return new ModelAndView("/staff/staffPerformance/scoreCaseList", model);
        }
        return null;
    }

    public static final Logger logger = LoggerFactory.getLogger(BackendStaffController.class);

    /**
     * 导入钉钉数据 及 纳税 数据
     * @param request
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(MultipartHttpServletRequest request, HttpServletResponse response){
        Map<String,Object> param =  new HashMap<>();
        MultipartFile multipartFile = request.getFile("file");
        String exportType = request.getParameter("exportType");
        try {
            InputStream inputStream = multipartFile.getInputStream();
            //将文件上传至服务器
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            File file = new File("/mnt/sftp/files/staff/" + simpleDateFormat.format(new Date()) + "/" + System.currentTimeMillis() + "/" + multipartFile.getOriginalFilename());
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length  = 0 ;
            while((length = inputStream.read(buffer))>0){
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();

            String url = file.getPath();
            if ("test".equals(settingSource) || "product".equals(settingSource)){//测试 或 线上
                url = url.replace("/mnt/sftp/files/staff/",fileHttp.concat("/staff/"));
            }
            Map returnMap = new HashMap<>();
            if ("ddData".equals(exportType)){
                StaffPaySlipDto staffPaySlip = new StaffPaySlipDto();
                List<DdDataDTO> data = StaffExcel.generate(new FileInputStream(file));
                param.put("url",url);
                returnMap.put("successCount",0);
                returnMap.put("errorCount",0);
                returnMap.put("errorMessage","");
                returnMap = export(data,exportType,0,param,request,response,returnMap);
                Map map = new HashMap<>();
                map.put("message",returnMap.get("message"));
                map.put("ddUrl",returnMap.get("ddUrl"));
                map.put("ddUrlName",returnMap.get("ddUrlName"));
                map.put("isSuccess", "true");
                String json = sh.zj100.common.util.JsonUtil.objectToJson(map);
                this.outputJson(json, response);
                return;
            }else if ("jsData".equals(exportType)){
                List<JsDataDTO> data = StaffExcel.generateJsData(new FileInputStream(file));

                param.put("url",url);
                returnMap.put("successCount",0);
                returnMap.put("errorCount",0);
                returnMap.put("errorMessage","");
                returnMap = export(data,exportType,0,param,request,response,returnMap);
                Map map = new HashMap<>();
                map.put("message",returnMap.get("message"));
                map.put("isSuccess", "true");
                String json = sh.zj100.common.util.JsonUtil.objectToJson(map);
                this.outputJson(json, response);
                return;

            }else if("personnelInfo".equals(exportType)){
                List<StaffPersonnelInfoDto> data = StaffExcel.generatePersonnelInfo(new FileInputStream(file));
                param.put("url",url);
                returnMap.put("successCount",0);
                returnMap.put("errorCount",0);
                returnMap.put("errorMessage","");
                returnMap = export(data,exportType,0,param,request,response,returnMap);
                Map map = new HashMap<>();
                map.put("message",returnMap.get("message"));
                map.put("isSuccess", "true");
                String json = sh.zj100.common.util.JsonUtil.objectToJson(map);
                this.outputJson(json, response);
                return;
            }else if ("otherData".equals(exportType)){
                List<OtherDataDTO> data = StaffExcel.generateOtherData(new FileInputStream(file));
                param.put("otherData", JSONArray.toJSON(data));
                param.put("url",url);
                this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_PAY_SLIP_EXPORT, param, request, response);
            }else if ("performancePersonnelData".equals(exportType)){//绩效明细内容的导入
                List<StaffPerformancePersonnelDto> data = StaffExcel.generatePerformancePersonnelData(new FileInputStream(file));
                param.put("url",url);
                returnMap.put("successCount",0);
                returnMap.put("errorCount",0);
                returnMap.put("errorMessage","");
                returnMap = export(data,exportType,0,param,request,response,returnMap);
                Map map = new HashMap<>();
                map.put("message",returnMap.get("message"));
                map.put("isSuccess", "true");
                String json = sh.zj100.common.util.JsonUtil.objectToJson(map);
                this.outputJson(json, response);
                return;
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    private Map export(Object tempData,String exportType,int i,Map param,MultipartHttpServletRequest request, HttpServletResponse response,Map returnMap){
        if ("personnelInfo".equals(exportType)){
            List<StaffPersonnelInfoDto> data = (List<StaffPersonnelInfoDto>)tempData;
            int totle = data.size() / 50;// 3
            if (i <= totle){
                List<StaffPersonnelInfoDto> info = data.subList(50 * i, (50 * (i + 1))>data.size()?data.size():(50 * (i + 1)));
                param.put("ddData", JSONArray.toJSON(info));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<Map>>() {};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_PERSONNEL_INFO_EXPORT, param, request);
                Map map = (Map) apiFinalResponse.getResults();
                returnMap.put("successCount", (int)returnMap.get("successCount") + ((Double)map.get("successCount")).intValue());
                returnMap.put("errorCount", (int)returnMap.get("errorCount")+ ((Double)map.get("errorCount")).intValue());
                returnMap.put("errorMessage", ((String)returnMap.get("errorMessage")+ ((String)map.get("errorMessage"))));
                return export(tempData,exportType, i+1 ,param,request,response,returnMap);
            }
        }
        else if("ddData".equals(exportType)){
            List<DdDataDTO> data = (List<DdDataDTO>)tempData;
            int totle = data.size() / 50;
            if (i <= totle){
                List<DdDataDTO> info = data.subList(50 * i, (50 * (i + 1))>data.size()?data.size():(50 * (i + 1)));
                param.put("ddData", JSONArray.toJSON(info));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<Map>>() {};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_PAY_SLIP_EXPORT, param, request);
                Map map = (Map) apiFinalResponse.getResults();

                returnMap.put("successCount", (int)returnMap.get("successCount")+ ((Double)map.get("successCount")).intValue());
                returnMap.put("errorCount", (int)returnMap.get("errorCount")+ ((Double)map.get("errorCount")).intValue());
                returnMap.put("errorMessage", ((String)returnMap.get("errorMessage")+ ((String)map.get("errorMessage"))));
                returnMap.put("ddUrl", (String)map.get("ddUrl"));
                returnMap.put("ddUrlName", (String)map.get("ddUrlName"));
                return export(tempData,exportType, i+1 ,param,request,response,returnMap);
            }
        }
        else if("performancePersonnelData".equals(exportType)){
            List<StaffPerformancePersonnelDto> data = (List<StaffPerformancePersonnelDto>)tempData;
            int totle = data.size() / 50;
            if (i <= totle){
                List<StaffPerformancePersonnelDto> info = data.subList(50 * i, (50 * (i + 1))>data.size()?data.size():(50 * (i + 1)));
                param.put("performancePersonnelData", JSONArray.toJSON(info));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<Map>>() {};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_PERSONNEL_INFO_EXPORT, param, request);
                Map map = (Map) apiFinalResponse.getResults();
                returnMap.put("successCount", (int)returnMap.get("successCount")+ ((Double)map.get("successCount")).intValue());
                returnMap.put("errorCount", (int)returnMap.get("errorCount")+ ((Double)map.get("errorCount")).intValue());
                returnMap.put("errorMessage", ((String)returnMap.get("errorMessage")+ ((String)map.get("errorMessage"))));
                return export(tempData,exportType, i+1 ,param,request,response,returnMap);
            }
        }
        else if("jsData".equals(exportType)){
            List<JsDataDTO> data = (List<JsDataDTO>)tempData;

            int totle = data.size() / 50;
            if (i <= totle){
                List<JsDataDTO> info = data.subList(50 * i, (50 * (i + 1))>data.size()?data.size():(50 * (i + 1)));
                param.put("jsData", JSONArray.toJSON(info));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<Map>>() {};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_PAY_SLIP_EXPORT, param, request);
                Map map = (Map) apiFinalResponse.getResults();
                returnMap.put("successCount", (int)returnMap.get("successCount")+ ((Double)map.get("successCount")).intValue());
                returnMap.put("errorCount", (int)returnMap.get("errorCount")+ ((Double)map.get("errorCount")).intValue());
                returnMap.put("errorMessage", ((String)returnMap.get("errorMessage")+ ((String)map.get("errorMessage"))));
                return export(tempData,exportType, i+1 ,param,request,response,returnMap);
            }
        }
        returnMap.put("message","成功导入"+ (int)returnMap.get("successCount")+"条数据。");
        if(!"".equals((String)returnMap.get("errorMessage"))){
            returnMap.put("message", (String)returnMap.get("message") + "导入失败"+(int)returnMap.get("errorCount")+"条数据，失败原因："+(String)returnMap.get("errorMessage"));
        }
        return returnMap;
    }



    @RequestMapping(value = "/operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp) {
        String operateCode = req.getParameter("operateCode");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        if ("slip".equals(operateCode)){//工资
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_PAY_SLIP_OPERATE, null, req, rsp);
        }else if ("performance".equals(operateCode)){//绩效操作
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_PERFORMANCE_OPERATE, null, req, rsp);
        }else if ("staff-organ-money".equals(operateCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_ORGAN_AJAX_DATA, null, req, rsp);
        }
        else{
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_OPERATE, appendMap, req, rsp);
        }
    }

    /**
     * 获取详情列表信息
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "getDetail")
    public String getDetail(HttpServletRequest req, HttpServletResponse rsp){
        Map param =  new HashMap<>();
        String dataCode = req.getParameter("dataCode");
        if ("slip".equals(dataCode)){//工资明细数据获取
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_PAY_SLIP_INFO, null, req, rsp);
        }else if ("performance".equals(dataCode)){ //绩效明细
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_PERFORMANCE_INFO, null, req, rsp);
        }else if("personnelInfo".equals(dataCode)){
            Map<String,Object> appendMap = new HashMap<String, Object>();
            if (req.getParameter("staffStates") == null){
                appendMap.put("staffStates","1,2,3,4,5");
            }
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req, rsp);
        }
        //人力成本分析
        else if("personnelCostAnalysis".equals(dataCode) || "personnelCostAnalysisUser".equals(dataCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_LIST, null, req, rsp);
        }
        return null;
    }

    //返回最新月份
    private String getLastWorkTime(String workTime) {
        DateFormat df=new SimpleDateFormat("yyyy-MM");
        try {
            Calendar ct=Calendar.getInstance();
            ct.setTime(df.parse(workTime));
            ct.add(Calendar.MONTH, +1);
            return df.format(ct.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return workTime;
    }


    //导出
    @RequestMapping(value = "/downLoad")
    public void downLoad(HttpServletRequest req, HttpServletResponse rsp) {
        String surveyCode = req.getParameter("surveyCode");
        //员工管理导出
        if("personnelInfo".equals(surveyCode)) {
            downLoadPersonnelInfo(req, rsp, surveyCode);
        }
        //工资条导出
        else if("paySlip".equals(surveyCode)){
            downLoadPaySlip(req, rsp, surveyCode);
        }
        //绩效部分导出
        else if("performance".equals(surveyCode)){
            downLoadPerformance(req, rsp, surveyCode);
        }
        //绩效全部导出
        else if("performanceWhole".equals(surveyCode)){
            downLoadPerformanceWhole(req, rsp, surveyCode);
        }
        //工资条模板导出
        else if("ddDataModel".equals(surveyCode)){
            ExcelReport.reportDdDataModel(null, new HashMap<>(), rsp);
        }
    }

    //员工导出
    private void downLoadPersonnelInfo(HttpServletRequest req, HttpServletResponse rsp,String surveyCode) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("havePage","no");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffPersonnelInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffPersonnelInfoDto> infos = (List<StaffPersonnelInfoDto>)apiFinalResponse.getResults();
        ExcelReport.reportPersonnelInfo(infos, new HashMap<>(), rsp);
    }

    //创建表头
    private void addSheetHeader(WritableSheet sheet,WritableCellFormat wcf,int i,String surveyCode,String bookType) {
        try {
            if("paySlip".equals(surveyCode)){
                int index = -1;
                sheet.addCell(new Label(++index, i,"姓名",wcf));
//                sheet.addCell(new Label(1,i,"事业部",wcf));
                sheet.addCell(new Label(++index,i,"社保缴纳公司",wcf));
                sheet.addCell(new Label(++index,i,"预算归属公司",wcf));
                sheet.addCell(new Label(++index,i,"机构/部门",wcf));
                sheet.addCell(new Label(++index,i,"科室",wcf));
                sheet.addCell(new Label(++index,i,"小组",wcf));
                sheet.addCell(new Label(++index,i,"工号",wcf));
                sheet.addCell(new Label(++index,i,"手机号",wcf));
                sheet.addCell(new Label(++index,i,"身份证",wcf));
                sheet.addCell(new Label(++index,i,"岗位",wcf));
                sheet.addCell(new Label(++index,i,"入职时间",wcf));
                sheet.addCell(new Label(++index,i,"社保公积金缴纳地",wcf));

                sheet.addCell(new Label(++index,i,"基本工资",wcf));
                sheet.addCell(new Label(++index,i,"实际出勤天数",wcf));
                sheet.addCell(new Label(++index,i,"当月应上班天数",wcf));
                sheet.addCell(new Label(++index,i,"百分比",wcf));
                sheet.addCell(new Label(++index,i,"迟到/早退扣款",wcf));
                sheet.addCell(new Label(++index,i,"旷工扣款",wcf));
                sheet.addCell(new Label(++index,i,"事假扣款",wcf));
                sheet.addCell(new Label(++index,i,"病假时长",wcf));
                sheet.addCell(new Label(++index,i,"病假扣款",wcf));
                sheet.addCell(new Label(++index,i,"补贴",wcf));
                sheet.addCell(new Label(++index,i,"加班工资",wcf));
                sheet.addCell(new Label(++index,i,"其他补发",wcf));
                sheet.addCell(new Label(++index,i,"其他补发备注",wcf));
                sheet.addCell(new Label(++index,i,"其他扣款",wcf));
                sheet.addCell(new Label(++index,i,"其他扣款备注",wcf));
                sheet.addCell(new Label(++index,i,"浮动绩效",wcf));
                sheet.addCell(new Label(++index,i,"浮动绩效备注",wcf));

                sheet.addCell(new Label(++index,i,"应发工资小计",wcf));

                if("1".equals(bookType) || "2".equals(bookType) || "4".equals(bookType)|| "5".equals(bookType)){
                    sheet.addCell(new Label(++index,i,"公积金公司部分",wcf));
                    sheet.addCell(new Label(++index,i,"公积金个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"养老保险公司部分",wcf));
                    sheet.addCell(new Label(++index,i,"养老保险个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"医疗保险公司部分",wcf));
                    sheet.addCell(new Label(++index,i,"医疗保险个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"个人失业保险公司部分",wcf));
                    sheet.addCell(new Label(++index,i,"个人失业保险个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"生育险公司部分",wcf));
                    sheet.addCell(new Label(++index,i,"工伤保险公司部分",wcf));
                    sheet.addCell(new Label(++index,i,"大病补助公司部分",wcf));
                    sheet.addCell(new Label(++index,i,"大病补助个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"残保金",wcf));
                    sheet.addCell(new Label(++index,i,"服务费",wcf));
                    sheet.addCell(new Label(++index,i,"社保备注",wcf));
                    sheet.addCell(new Label(++index,i,"社保公积金公司部分小计",wcf));
                    sheet.addCell(new Label(++index,i,"社保公积金个人部分小计",wcf));

                    sheet.addCell(new Label(++index,i,"税前工资",wcf));
                    sheet.addCell(new Label(++index,i,"个人所得税",wcf));
                    sheet.addCell(new Label(++index,i,"个税调整",wcf));
                    sheet.addCell(new Label(++index,i,"实发工资",wcf));
                }
                else if("3".equals(bookType)){
                    sheet.addCell(new Label(++index,i,"公积金个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"养老保险个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"医疗保险个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"个人失业保险个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"大病补助个人部分",wcf));
                    sheet.addCell(new Label(++index,i,"社保备注",wcf));
                    sheet.addCell(new Label(++index,i,"社保公积金个人部分小计",wcf));
                    sheet.addCell(new Label(++index,i,"税前工资",wcf));
                    sheet.addCell(new Label(++index,i,"个人所得税",wcf));
                    sheet.addCell(new Label(++index,i,"个税调整",wcf));
                    sheet.addCell(new Label(++index,i,"实发工资",wcf));
                }
            }else if("performance".equals(surveyCode)){
                sheet.addCell(new Label(0,i,"姓名",wcf));
                sheet.addCell(new Label(1,i,"工号",wcf));
                sheet.addCell(new Label(2,i,"考核绩效基数",wcf));
                sheet.addCell(new Label(3,i,"绩效考核系数",wcf));
                sheet.addCell(new Label(4,i,"小程序打卡率绩效",wcf));
                sheet.addCell(new Label(5,i,"视频面访率绩效",wcf));
                sheet.addCell(new Label(6,i,"其他补发",wcf));
                sheet.addCell(new Label(7,i,"其他补发备注",wcf));
                sheet.addCell(new Label(8,i,"其他扣款",wcf));
                sheet.addCell(new Label(9,i,"其他扣款备注",wcf));
            }
            else if("ddDataModel".equals(surveyCode)){
                sheet.addCell(new Label(0,i,"姓名",wcf));
                sheet.addCell(new Label(1,i,"工号",wcf));
                sheet.addCell(new Label(2,i,"迟到次数",wcf));
                sheet.addCell(new Label(3,i,"早退次数",wcf));
                sheet.addCell(new Label(4,i,"旷工次数",wcf));
                sheet.addCell(new Label(5,i,"事假",wcf));
                sheet.addCell(new Label(6,i,"病假",wcf));
                sheet.addCell(new Label(7,i,"加班工资",wcf));
                sheet.addCell(new Label(8,i,"其他补扣款 ",wcf));
//                sheet.addCell(new Label(9,i,"实际出勤天数",wcf));
                sheet.addCell(new Label(9,i,"个税调整",wcf));

                sheet.addCell(new Label(10,i,"公积金公司部分",wcf));
                sheet.addCell(new Label(11,i,"公积金个人部分",wcf));
                sheet.addCell(new Label(12,i,"养老保险公司部分",wcf));
                sheet.addCell(new Label(13,i,"养老保险个人部分",wcf));
                sheet.addCell(new Label(14,i,"医疗保险公司部分",wcf));
                sheet.addCell(new Label(15,i,"医疗保险个人部分",wcf));
                sheet.addCell(new Label(16,i,"个人失业保险公司部分",wcf));
                sheet.addCell(new Label(17,i,"个人失业保险个人部分",wcf));
                sheet.addCell(new Label(18,i,"生育险公司部分",wcf));
                sheet.addCell(new Label(19,i,"工伤保险公司部分",wcf));

                sheet.addCell(new Label(20,i,"大病补助公司部分",wcf));
                sheet.addCell(new Label(21,i,"大病补助个人部分",wcf));
                sheet.addCell(new Label(22,i,"残保金",wcf));
                sheet.addCell(new Label(23,i,"服务费",wcf));
                sheet.addCell(new Label(24,i,"社保备注",wcf));
                sheet.addCell(new Label(25,i,"备注",wcf));
            }
            else if("downLoadPerformanceWhole".equals(surveyCode)){
                sheet.addCell(new Label(0,i,"姓名",wcf));
                sheet.addCell(new Label(1,i,"社保缴纳公司",wcf));
                sheet.addCell(new Label(2,i,"预算归属公司",wcf));
                sheet.addCell(new Label(3,i,"机构/部门",wcf));
                sheet.addCell(new Label(4,i,"科室",wcf));
                sheet.addCell(new Label(5,i,"小组",wcf));
                sheet.addCell(new Label(6,i,"工号",wcf));
                sheet.addCell(new Label(7,i,"手机号",wcf));
                sheet.addCell(new Label(8,i,"岗位",wcf));
                sheet.addCell(new Label(9,i,"入职时间",wcf));
                sheet.addCell(new Label(10,i,"固定绩效基数",wcf));
                sheet.addCell(new Label(11,i,"驻外补贴基数",wcf));
                sheet.addCell(new Label(12,i,"管理绩效基数",wcf));
                sheet.addCell(new Label(13,i,"基础积分",wcf));
                sheet.addCell(new Label(14,i,"实际出勤天数",wcf));
                sheet.addCell(new Label(15,i,"当月应上班天数",wcf));
                sheet.addCell(new Label(16,i,"百分比",wcf));
                sheet.addCell(new Label(17,i,"实发固定绩效",wcf));
                sheet.addCell(new Label(18,i,"实发驻外补贴",wcf));
                sheet.addCell(new Label(19,i,"实发管理绩效",wcf));
                sheet.addCell(new Label(20,i,"考核绩效基数",wcf));
                sheet.addCell(new Label(21,i,"绩效考核系数",wcf));
                sheet.addCell(new Label(22,i,"实际考核绩效",wcf));
                sheet.addCell(new Label(23,i,"互助调查积分",wcf));
                sheet.addCell(new Label(24,i,"互助阳性积分",wcf));
                sheet.addCell(new Label(25,i,"保司调查积分",wcf));
                sheet.addCell(new Label(26,i,"保司阳性奖励",wcf));
                sheet.addCell(new Label(26+1,i,"个案减损奖励",wcf));
                sheet.addCell(new Label(27+1,i,"实际基础积分",wcf));
                sheet.addCell(new Label(28+1,i,"积分绩效",wcf));
                sheet.addCell(new Label(29+1,i,"迟到/早退扣款 ",wcf));
                sheet.addCell(new Label(30+1,i,"旷工扣款",wcf));
                sheet.addCell(new Label(31+1,i,"事假扣款",wcf));
                sheet.addCell(new Label(32+1,i,"病假时长",wcf));
                sheet.addCell(new Label(33+1,i,"病假扣款",wcf));
                sheet.addCell(new Label(34+1,i,"其它补发",wcf));

                sheet.addCell(new Label(35+1,i,"保司案件数量(按量)",wcf));
                sheet.addCell(new Label(36+1,i,"保司案件数量(机构)",wcf));
                sheet.addCell(new Label(37+1,i,"互助案件数量(按量)",wcf));
                sheet.addCell(new Label(38+1,i,"互助案件数量(机构)",wcf));

                sheet.addCell(new Label(39+1,i,"其他补发备注",wcf));
                sheet.addCell(new Label(40+1,i,"其它扣款",wcf));
                sheet.addCell(new Label(41+1,i,"其它扣款备注",wcf));

                sheet.addCell(new Label(42+1,i,"审核总积分",wcf));
                sheet.addCell(new Label(43+1,i,"审核绩效系数",wcf));
                sheet.addCell(new Label(44+1,i,"审核绩效",wcf));

                sheet.addCell(new Label(45+1,i,"实发绩效",wcf));
            }
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    //工资条导出
    private void downLoadPaySlip(HttpServletRequest req, HttpServletResponse rsp, String surveyCode) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String bookType = req.getParameter("bookType");//1：按预算归属公司导出；2、按事业部导出；3、总表导出（不显示社保公积金公司部分）；4、总表导出（显示社保公积金公司部分）;5、社保缴纳公司
        appendMap.put("havePage","no");
        appendMap.put("id",req.getParameter("staffPaySlipId"));
        if("1".equals(bookType)){
            appendMap.put("orderType","1");
        }else if("2".equals(bookType)){
            appendMap.put("orderType","2");
        }else if("5".equals(bookType)){
            appendMap.put("orderType","5");
        }
        appendMap.put("surveyCode","payPersonnelSlip");//工资条明细
        appendMap.put("downLoad", 1);//代表“导出”
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffPayPersonnelSlipDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_PAY_SLIP_INFO, appendMap, req);
        List<StaffPayPersonnelSlipDto> infos = (List<StaffPayPersonnelSlipDto>)apiFinalResponse.getResults();

        if("3".equals(bookType)||"4".equals(bookType)){
            createPaySlipBook(infos,rsp,surveyCode,bookType);
        }else if("1".equals(bookType) || "2".equals(bookType) || "5".equals(bookType)) {
            Long timestamp = new Date().getTime();//当前时间戳
            String excelPath = accountExcelPath + timestamp;
            File file = new File(excelPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            createPaySlipEachBook(infos, rsp, surveyCode, bookType, 0, excelPath, false);
        }
    }

    //工资条导出-总表导出
    private void createPaySlipBook(List<StaffPayPersonnelSlipDto> infos, HttpServletResponse rsp, String surveyCode, String bookType) {
        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="工资条明细.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页

            WritableSheet sheet = book.createSheet("工资条明细", 0);
            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);

            sheet.setColumnView(0,15);// 将第一列的宽度设为30
            sheet.setColumnView(1,35);
            sheet.setColumnView(2,35);
            sheet.setColumnView(3,35);
            sheet.setColumnView(4,35);
            sheet.setColumnView(5,15);
            sheet.setColumnView(6,15);
            sheet.setColumnView(7,15);
            sheet.setColumnView(8,15);
            sheet.setColumnView(9,15);
            sheet.setColumnView(10,15);
            sheet.setColumnView(11,15);
            sheet.setColumnView(12,15);
            sheet.setColumnView(13,15);
            sheet.setColumnView(14,15);
            sheet.setColumnView(15,15);
            sheet.setColumnView(16,15);
            sheet.setColumnView(17,15);
            sheet.setColumnView(18,15);
            sheet.setColumnView(19,15);
            sheet.setColumnView(20,15);
            sheet.setColumnView(21,15);
            sheet.setColumnView(22,15);
            sheet.setColumnView(23,15);
            sheet.setColumnView(24,15);
            sheet.setColumnView(25,15);
            sheet.setColumnView(26,15);
            sheet.setColumnView(27,15);
            sheet.setColumnView(28,15);
            sheet.setColumnView(29,15);
            sheet.setColumnView(30,15);
            sheet.setColumnView(31,15);
            sheet.setColumnView(32,15);
            sheet.setColumnView(33,15);
            sheet.setColumnView(34,15);
            sheet.setColumnView(35,15);
            //添加表头
            addSheetHeader(sheet,wcf,0,surveyCode,bookType);
            //添加内容
            for (int i = 0; i < infos.size(); i++) {
                addSheetPaySlipContent(infos.get(i), sheet, wcf, bookType, i);
            };

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //工资条导出-总表导出 -- 具体内容
    private void addSheetPaySlipContent(StaffPayPersonnelSlipDto info, WritableSheet sheet, WritableCellFormat wcf, String bookType, int i) {
        try {
            int index = -1;
            sheet.addCell(new Label(++index, i+1,  info.getRealName()==null?"":info.getRealName(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getSocialSecurityCompany()==null?"":info.getSocialSecurityCompany(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getCompany()==null?"":info.getCompany(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getOrgan()==null?"":info.getOrgan(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getDepartment()==null?"":info.getDepartment(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getTeam()==null?"":info.getTeam(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getJobNo()==null?"":info.getJobNo(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getUserTel()==null?"":info.getUserTel(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getIdCard()==null?"":info.getIdCard(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getJobPost()==null?"":info.getJobPost(),wcf));
            if (info.getEntryTime() != null) {
                sheet.addCell(new Label(++index, i+1, format.format(info.getEntryTime()),wcf));
            } else {
                sheet.addCell(new Label(++index, i+1, "",wcf));
            }
            sheet.addCell(new Label(++index, i+1,  info.getPayAddress()==null?"":info.getPayAddress(),wcf));

            sheet.addCell(new Number(++index, i+1,  info.getBasePay()==null?0D:info.getBasePay(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getRealWorkingDays()==null?0D:info.getRealWorkingDays(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getWorkingDays()==null?0D:info.getWorkingDays(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getRate()==null?0D:info.getRate(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getLateEarlyMoney()==null?0D:info.getLateEarlyMoney(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getAbsenteeismMoney()==null?0D:info.getAbsenteeismMoney(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getLeaveMoney()==null?0D:info.getLeaveMoney(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getSickLeaveTime()==null?0D:info.getSickLeaveTime(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getSickLeaveMoney()==null?0D:info.getSickLeaveMoney(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getOfficeSubsidies()==null?0D:info.getOfficeSubsidies(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getOvertimePay()==null?0D:info.getOvertimePay(),wcf));
            sheet.addCell(new Number(++index, i+1,  info.getOtherPay()==null?0D:info.getOtherPay(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getRemarks()==null?"":info.getRemarks(),wcf));

            sheet.addCell(new Number(++index, i+1,  info.getOtherCutPay()==null?0D:info.getOtherCutPay(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getOtherCutRemarks()==null?"":info.getOtherCutRemarks(),wcf));

            sheet.addCell(new Number(++index, i+1,  info.getWelfarePay()==null?0D:info.getWelfarePay(),wcf));
            sheet.addCell(new Label(++index, i+1,  info.getWelfareRemark()==null?"":info.getWelfareRemark(),wcf));

            sheet.addCell(new Number(++index, i+1,  info.getWagesPaySub()==null?0D:info.getWagesPaySub(),wcf));

            if("4".equals(bookType) || "1".equals(bookType) || "2".equals(bookType) || "5".equals(bookType)){
                sheet.addCell(new Number(++index, i+1,  info.getConpanyFundMoney()==null?0D:info.getConpanyFundMoney(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalFundMoney()==null?0D:info.getPersonalFundMoney(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getCompanyPensionBenefits()==null?0D:info.getCompanyPensionBenefits(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalPensionBenefits()==null?0D:info.getPersonalPensionBenefits(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getCompanyMedicalInsurance()==null?0D:info.getCompanyMedicalInsurance(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalMedicalInsurance()==null?0D:info.getPersonalMedicalInsurance(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getCompanyUnemploymentInsurance()==null?0D:info.getCompanyUnemploymentInsurance(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalUnemploymentInsurance()==null?0D:info.getPersonalUnemploymentInsurance(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getCompanyBirthInsurance()==null?0D:info.getCompanyBirthInsurance(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getCompanyInjuryInsurance()==null?0D:info.getCompanyInjuryInsurance(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getCompanySickSubsidy()==null?0D:info.getCompanySickSubsidy(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalSickSubsidy()==null?0D:info.getPersonalSickSubsidy(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getDisabilityInsurance()==null?0D:info.getDisabilityInsurance(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getServiceFee()==null?0D:info.getServiceFee(),wcf));
                sheet.addCell(new Label(++index, i+1,  info.getSocialRemark()==null?"":info.getSocialRemark(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getCompanyMoneySub()==null?0D:info.getCompanyMoneySub(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalMoneySub()==null?0D:info.getPersonalMoneySub(),wcf));

                sheet.addCell(new Number(++index, i+1,  info.getGrossPay()==null?0D:info.getGrossPay(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getIndividualTax()==null?0D:info.getIndividualTax(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getIndividualTaxChange()==null?0D:info.getIndividualTaxChange(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getRealWages()==null?0D:info.getRealWages(),wcf));

            }else if("3".equals(bookType)){
                sheet.addCell(new Number(++index, i+1,  info.getPersonalFundMoney()==null?0D:info.getPersonalFundMoney(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalPensionBenefits()==null?0D:info.getPersonalPensionBenefits(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalMedicalInsurance()==null?0D:info.getPersonalMedicalInsurance(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalUnemploymentInsurance()==null?0D:info.getPersonalUnemploymentInsurance(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalSickSubsidy()==null?0D:info.getPersonalSickSubsidy(),wcf));
                sheet.addCell(new Label(++index, i+1,  info.getSocialRemark()==null?"":info.getSocialRemark(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getPersonalMoneySub()==null?0D:info.getPersonalMoneySub(),wcf));

                sheet.addCell(new Number(++index, i+1,  info.getGrossPay()==null?0D:info.getGrossPay(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getIndividualTax()==null?0D:info.getIndividualTax(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getIndividualTaxChange()==null?0D:info.getIndividualTaxChange(),wcf));
                sheet.addCell(new Number(++index, i+1,  info.getRealWages()==null?0D:info.getRealWages(),wcf));

            }

        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    //工资条导出-分表导出
    private void createPaySlipEachBook(List<StaffPayPersonnelSlipDto> infos, HttpServletResponse rsp, String surveyCode, String bookType, int index, String excelPath,boolean isNewBook) {
        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        StaffPayPersonnelSlipDto info = infos.get(index);
        String name = "";
        if("1".equals(bookType)){
            name = info.getCompany();
        }else if("2".equals(bookType)){
            name = info.getBusinessUnit();
        }else if("5".equals(bookType)){
            name = info.getSocialSecurityCompany();
        }

        String filename = name +"-工资条明细.xls";
        try {
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            String path = excelPath + File.separator + filename;
            File file = new File(path);
            book = Workbook.createWorkbook(file);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页

            WritableSheet sheet = book.createSheet(name +"表", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);

            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(12);
            //具体内容
            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf3 = new WritableCellFormat(wf);
            wcf3.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf3.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            //表头
            String title = name +"工资条";
            sheet.mergeCells(0, 0, 44, 0);//
            sheet.addCell(new Label(0, 0, title,wcf3));
            addSheetHeader(sheet,wcf,1,surveyCode,bookType);
            int z = 1;
            for (int i = index; i < infos.size(); i++) {
                StaffPayPersonnelSlipDto thisInfo = infos.get(i);
                if(i ==0){
                    addSheetPaySlipContent(thisInfo,sheet,wcf,bookType,z);//具体内容
                    z = z+1;
                }else{
                    if(isNewBook){
                        //添加内容
                        addSheetPaySlipContent(thisInfo,sheet,wcf,bookType,z);//具体内容
                        z = z+1;
                        isNewBook = false;
                    }else{
                        StaffPayPersonnelSlipDto lastInfo = infos.get(i-1);
                        Boolean judge = true;
                        if("1".equals(bookType)){
                            judge = thisInfo.getCompanyId().equals(lastInfo.getCompanyId());
                        }else if("2".equals(bookType)){
                            judge = thisInfo.getBusinessUnit().equals(lastInfo.getBusinessUnit());
                        }else if("5".equals(bookType)){
                            judge = thisInfo.getSocialSecurityCompanyId().equals(lastInfo.getSocialSecurityCompanyId());
                        }
                        if(judge){
                            //添加内容
                            addSheetPaySlipContent(thisInfo,sheet,wcf,bookType,z);
                            z = z+1;
                        }else{
                            // 写入数据并关闭文件
                            book.write();
                            book.close();
                            //创建新的excel
                            if(i < infos.size()){
                                createPaySlipEachBook(infos, rsp, surveyCode, bookType, i, excelPath,true);
                            }
                        }
                    }
                }
            }
            // 写入数据并关闭文件
            book.write();
            book.close();

            //下载zip
            FileZipUtil.createZip(excelPath, excelPath + ".zip");

            String url = excelPath+ ".zip";
            File downLoadFile = new File(url);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(downLoadFile));

            rsp.reset();
            BufferedOutputStream out = new BufferedOutputStream(rsp.getOutputStream());

            byte[] buff = new byte[2048];
            int length = 0;
            while ((length = in.read(buff)) > 0) {
                out.write(buff, 0, length);
                out.flush();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally{

        }
    }

    private void downLoadPerformance(HttpServletRequest req, HttpServletResponse rsp, String surveyCode) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("havePage","no");
        appendMap.put("staffPerformanceId",req.getParameter("staffPerformanceId"));
        appendMap.put("surveyCode","performancePersonnel");//绩效明细
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffPerformancePersonnelDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffPerformancePersonnelDto> infos = (List<StaffPerformancePersonnelDto>)apiFinalResponse.getResults();

        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="绩效管理.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("绩效管理", 0);
            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);
            sheet.setColumnView(0,15);// 将第一列的宽度设为30
            sheet.setColumnView(1,15);
            sheet.setColumnView(2,15);
            sheet.setColumnView(3,15);
            sheet.setColumnView(4,35);
            sheet.setColumnView(5,15);
            sheet.setColumnView(6,15);
            //添加表头
            addSheetHeader(sheet,wcf,0,surveyCode,null);
            //添加内容
            addSheetPerformanceContent(infos, sheet, wcf);

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private void addSheetPerformanceContent(List<StaffPerformancePersonnelDto> infos, WritableSheet sheet, WritableCellFormat wcf) {
        try{
            for (int i = 0; i < infos.size(); i++) {
                StaffPerformancePersonnelDto info = infos.get(i);
                sheet.addCell(new Label(0, i + 1, info.getRealName() == null ? "" : info.getRealName(),wcf));
                sheet.addCell(new Label(1, i + 1, info.getJobNo() == null ? "" : info.getJobNo(),wcf));
                sheet.addCell(new Number(2, i + 1, info.getAssesPerfPay() == null ? 0D : info.getAssesPerfPay(),wcf));
                sheet.addCell(new Number(3, i + 1, info.getAssessKpi() == null ? 0D : info.getAssessKpi(),wcf));
                sheet.addCell(new Label(4, i + 1, null,wcf));
                sheet.addCell(new Label(5, i + 1, null,wcf));
                sheet.addCell(new Number(6, i + 1, info.getOtherPay() == null ? 0D : info.getOtherPay(),wcf));
                sheet.addCell(new Label(7, i + 1, info.getRemarks() == null ? "" : info.getRemarks(),wcf));
                sheet.addCell(new Number(8, i + 1, info.getOtherCutPay() == null ? 0D : info.getOtherCutPay(),wcf));
                sheet.addCell(new Label(9, i + 1, info.getOtherCutRemarks() == null ? "" : info.getOtherCutRemarks(),wcf));
            }
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    private void downLoadPerformanceWhole(HttpServletRequest req, HttpServletResponse rsp, String surveyCode) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("havePage","no");
        appendMap.put("staffPerformanceId",req.getParameter("staffPerformanceId"));
        appendMap.put("surveyCode","performancePersonnel");//绩效明细
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffPerformancePersonnelDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffPerformancePersonnelDto> infos = (List<StaffPerformancePersonnelDto>)apiFinalResponse.getResults();

        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="绩效管理.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("绩效管理", 0);
            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);
            sheet.setColumnView(0,15);// 将第一列的宽度设为30
            sheet.setColumnView(1,15);
            sheet.setColumnView(2,15);
            sheet.setColumnView(3,15);
            sheet.setColumnView(4,15);
            sheet.setColumnView(5,35);
            sheet.setColumnView(6,15);
            sheet.setColumnView(7,15);
            sheet.setColumnView(8,15);
            sheet.setColumnView(9,15);
            sheet.setColumnView(10,15);
            sheet.setColumnView(11,15);
            sheet.setColumnView(12,15);
            sheet.setColumnView(13,15);
            sheet.setColumnView(14,15);
            sheet.setColumnView(15,15);
            sheet.setColumnView(16,15);
            sheet.setColumnView(17,15);
            sheet.setColumnView(18,15);
            sheet.setColumnView(19,15);
            sheet.setColumnView(20,15);
            sheet.setColumnView(21,15);
            sheet.setColumnView(22,15);
            sheet.setColumnView(23,15);
            sheet.setColumnView(24,15);
            sheet.setColumnView(25,15);
            sheet.setColumnView(26,15);
            sheet.setColumnView(27,15);
            sheet.setColumnView(28,15);
            sheet.setColumnView(29,15);
            sheet.setColumnView(30,15);
            sheet.setColumnView(31,15);
            sheet.setColumnView(32,15);
            sheet.setColumnView(33,15);
            sheet.setColumnView(34,15);
            sheet.setColumnView(35,35);
            sheet.setColumnView(36,15);
            sheet.setColumnView(37,15);
            sheet.setColumnView(38,15);
            sheet.setColumnView(39,35);
            sheet.setColumnView(40,15);
            sheet.setColumnView(41,15);
            //添加表头
            addSheetHeader(sheet,wcf,0,"downLoadPerformanceWhole",null);
            //添加内容
            addSheetPerformanceContentWhole(infos, sheet, wcf);

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addSheetPerformanceContentWhole(List<StaffPerformancePersonnelDto> infos, WritableSheet sheet, WritableCellFormat wcf) {
        try{
            for (int i = 0; i < infos.size(); i++) {
                StaffPerformancePersonnelDto info = infos.get(i);
                sheet.addCell(new Label(0, i + 1, info.getRealName() == null ? "" : info.getRealName(),wcf));
                sheet.addCell(new Label(1, i + 1, info.getSocialSecurityCompany() == null ? "" : info.getSocialSecurityCompany(),wcf));
                sheet.addCell(new Label(2, i + 1, info.getCompany() == null ? "" : info.getCompany(),wcf));
                sheet.addCell(new Label(3, i + 1, info.getOrgan() == null ? "" : info.getOrgan(),wcf));
                sheet.addCell(new Label(4, i + 1, info.getDepartment() == null ? "" : info.getDepartment(),wcf));
                sheet.addCell(new Label(5, i + 1, info.getTeam() == null ? "" : info.getTeam(),wcf));
                sheet.addCell(new Label(6, i + 1, info.getJobNo() == null ? "" : info.getJobNo(),wcf));
                sheet.addCell(new Label(7, i + 1, info.getUserTel() == null ? "" : info.getUserTel(),wcf));
                sheet.addCell(new Label(8, i + 1, info.getJobPost() == null ? "" : info.getJobPost(),wcf));
                if (info.getEntryTime() != null) {
                    sheet.addCell(new Label(9, i+1, format.format(info.getEntryTime()),wcf));
                } else {
                    sheet.addCell(new Label(9, i+1, "",wcf));
                }
                sheet.addCell(new Number(10, i + 1, info.getFixedPerfPayBase() == null ?  0D : info.getFixedPerfPayBase(),wcf));
                sheet.addCell(new Number(11, i + 1, info.getTravelAllowancePayBase() == null ?  0D : info.getTravelAllowancePayBase(),wcf));
                sheet.addCell(new Number(12, i + 1, info.getManagePerfPayBase() == null ?  0D : info.getManagePerfPayBase(),wcf));
                sheet.addCell(new Number(13, i + 1, info.getBasicIntegral() == null ?  0D : info.getBasicIntegral(),wcf));
                sheet.addCell(new Number(14, i + 1, info.getRealWorkingDays() == null ?  0D : info.getRealWorkingDays(),wcf));
                sheet.addCell(new Number(15, i + 1, info.getWorkingDays() == null ?  0D : info.getWorkingDays(),wcf));
                sheet.addCell(new Number(16, i + 1, info.getRate() == null ?  0D : info.getRate(),wcf));
                sheet.addCell(new Number(17, i + 1, info.getFixedPerfPay() == null ?  0D : info.getFixedPerfPay(),wcf));
                sheet.addCell(new Number(18, i + 1, info.getTravelAllowancePay() == null ?  0D : info.getTravelAllowancePay(),wcf));
                sheet.addCell(new Number(19, i + 1, info.getManagePerfPay() == null ?  0D : info.getManagePerfPay(),wcf));
                sheet.addCell(new Number(20, i + 1, info.getAssesPerfPay() == null ?  0D : info.getAssesPerfPay(),wcf));
                sheet.addCell(new Number(21, i + 1, info.getAssessKpi() == null ?  0D : info.getAssessKpi(),wcf));
                sheet.addCell(new Number(22, i + 1, info.getRealAssessKpi() == null ?  0D : info.getRealAssessKpi(),wcf));
                sheet.addCell(new Number(23, i + 1, info.getScoreHz() == null ?  0D : info.getScoreHz(),wcf));
                sheet.addCell(new Number(24, i + 1, info.getSunScoreHz() == null ?  0D : info.getSunScoreHz(),wcf));
                sheet.addCell(new Number(25, i + 1, info.getScoreBs() == null ?  0D : info.getScoreBs(),wcf));
                sheet.addCell(new Number(26, i + 1, info.getSunMoneyBs() == null ?  0D : info.getSunMoneyBs(),wcf));
                sheet.addCell(new Number(26+1, i + 1, info.getCaseSubMoney() == null ?  0D : info.getCaseSubMoney(),wcf));
                sheet.addCell(new Number(27+1, i + 1, info.getMonthBasicIntegral() == null ?  0D : info.getMonthBasicIntegral(),wcf));
                sheet.addCell(new Number(28+1, i + 1, info.getIntegralPay() == null ?  0D : info.getIntegralPay(),wcf));
                sheet.addCell(new Number(29+1, i + 1, info.getLateEarlyMoney() == null ?  0D : info.getLateEarlyMoney(),wcf));
                sheet.addCell(new Number(30+1, i + 1, info.getAbsenteeismMoney() == null ?  0D : info.getAbsenteeismMoney(),wcf));
                sheet.addCell(new Number(31+1, i + 1, info.getLeaveMoney() == null ?  0D : info.getLeaveMoney(),wcf));
                sheet.addCell(new Number(32+1, i + 1, info.getSickLeaveTime() == null ?  0D : info.getSickLeaveTime(),wcf));
                sheet.addCell(new Number(33+1, i + 1, info.getSickLeaveMoney() == null ?  0D : info.getSickLeaveMoney(),wcf));
                sheet.addCell(new Number(34+1, i + 1, info.getOtherPay() == null ?  0D : info.getOtherPay(),wcf));
                sheet.addCell(new Number(35+1, i + 1, info.getManagePerfPaySize() == null ?  0D : info.getManagePerfPaySize(),wcf));
                sheet.addCell(new Number(36+1, i + 1, info.getManageCaseNum() == null ?  0D : info.getManageCaseNum(),wcf));
                sheet.addCell(new Number(37+1, i + 1, info.getManagePerfPaySizeHz() == null ?  0D : info.getManagePerfPaySizeHz(),wcf));
                sheet.addCell(new Number(38+1, i + 1, info.getManageCaseNumHz() == null ?  0D : info.getManageCaseNumHz(),wcf));
                sheet.addCell(new Label(39+1, i + 1, info.getRemarks() == null ?  "" : info.getRemarks(),wcf));
                sheet.addCell(new Number(40+1, i + 1, info.getOtherCutPay() == null ?  0D : info.getOtherCutPay(),wcf));
                sheet.addCell(new Label(41+1, i + 1, info.getOtherCutRemarks() == null ?  "" : info.getOtherCutRemarks(),wcf));
                sheet.addCell(new Number(42+1, i + 1, info.getExamineScore() == null ?  0D : info.getExamineScore(),wcf));
                sheet.addCell(new Number(43+1, i + 1, info.getExamineRate() == null ?  0D : info.getExamineRate(),wcf));
                sheet.addCell(new Number(44+1, i + 1, info.getExaminePay() == null ?  0D : info.getExaminePay(),wcf));
                sheet.addCell(new Number(45+1, i + 1, info.getRealPay() == null ?  0D : info.getRealPay(),wcf));
            }
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

}
