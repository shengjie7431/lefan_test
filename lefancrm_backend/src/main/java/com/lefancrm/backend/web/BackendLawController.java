package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianfeng on 2018/10/18.
 */
@Controller
@RequestMapping(value = "/law")
public class BackendLawController extends BackendBaseController {

    /**
     * 跳转添加界面
     *
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest req, HttpServletResponse rsp) {
        //地区信息
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("parentId",0);
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
        Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {}.getType();
        ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);

        Map model = new HashMap();
        model.put("apiRsp", apiRsp);
        return new ModelAndView("/law/add",model);
    }


    /**
     * 保存（新增）
     *
     */
    @RequestMapping(value = "/save")
    public String save(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LAW_CASE_INFO_ADD, null, req, rsp);
    }

    @RequestMapping(value = "/manager")
    public ModelAndView manager(HttpServletRequest req, HttpServletResponse rsp){
        TypeToken typeToken = new TypeToken<ApiFinalResponse<LawNumberDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_LAW_CASE_INFO_NUMBER, null, req);
        Map model = new HashMap();
        model.put("numberDto",apiFinalResponse.getResults());

        typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>(){};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
        CaseUserRoleDto lawUserRole = (CaseUserRoleDto)apiFinalResponse.getResults();
        model.put("lawUserRole",lawUserRole);
        return new ModelAndView("/law/manager",model);
    }

    /**
     * 查询列表
     *
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        String caseNo = req.getParameter("caseNo");
        String entrustUserName = req.getParameter("entrustUserName");
        String caseType = req.getParameter("caseType");
        String linkName = req.getParameter("linkName");
        String linkTel = req.getParameter("linkTel");
        String flowState = req.getParameter("flowState");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String menuType = req.getParameter("menuType");
        String entrustUserTel = req.getParameter("entrustUserTel");
        String isUploadAssessPlan = req.getParameter("isUploadAssessPlan");
        String isUploadReqmoneyLetter = req.getParameter("isUploadReqmoneyLetter");
        String isExpress = req.getParameter("isExpress");
        String stageState = req.getParameter("stageState");

        //list
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<LawCaseInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_LAW_CASE_INFO_LIST, null, req);

        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("entrustUserName",entrustUserName==null?"":entrustUserName);
        model.put("caseType",caseType==null?"":caseType);
        model.put("linkName",linkName==null?"":linkName);
        model.put("linkTel",linkTel==null?"":linkTel);
        model.put("flowState",flowState==null?"":flowState);
        model.put("startDate",startDate==null?"":startDate);
        model.put("endDate",endDate==null?"":endDate);
        model.put("menuType",menuType==null?"":menuType);
        model.put("entrustUserTel",entrustUserTel==null?"":entrustUserTel);
        model.put("isUploadAssessPlan",isUploadAssessPlan==null?"":isUploadAssessPlan);
        model.put("isUploadReqmoneyLetter",isUploadReqmoneyLetter==null?"":isUploadReqmoneyLetter);
        model.put("isExpress",isExpress==null?"":isExpress);
        model.put("stageState",stageState==null?"":stageState);

        return new ModelAndView("/law/list",model);
    }

    @RequestMapping(value = "/info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){

        //地区信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId",0);
        String jsonArea = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
        Type typeArea = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
        }.getType();
        ApiFinalResponse<List<CommonArea>> apiRspArea = JsonUtil.jsonToObject(jsonArea, typeArea);

        Map model = new HashMap();
        model.put("apiRspArea", apiRspArea);
        String menuType = req.getParameter("menuType");
        model.put("menuType",menuType);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<LawCaseInfoDto>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_LAW_CASE_INFO_INFO, null, req);
        LawCaseInfoDto dto = (LawCaseInfoDto)apiFinalResponse.getResults();
        model.put("dto",dto);

        //获取跟踪记录
        Map paramMap = new HashMap();
        paramMap.put("caseId",req.getParameter("id"));
        typeToken = new TypeToken<ApiFinalResponse<List<LawCaseInfoFollowDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_LAW_CASE_INFO_FOLLOW_BY_CASEID, paramMap, req);
        model.put("caseFollows",apiFinalResponse.getResults());

        //退案费用
        typeToken = new TypeToken<ApiFinalResponse<LawFeeDetailDto>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_LAW_FEE_DETAIL_BY_INFO, paramMap, req);
        model.put("lawFeeDetail",apiFinalResponse.getResults());

        //如果是客服列表，同时补充材料中  则跳转编辑界面补充信息   或者 是委托清单列表同时是未申请
        if (("1".equals(menuType) && dto.getIsCursupplement() == 1L) || ("35".equals(menuType) && dto.getFlowState() == 1)){
            //地区信息
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {}.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            model.put("apiRsp", apiRsp);
            if ("1".equals(menuType)){
                model.put("btnCode","1100");
            }else if ("35".equals(menuType)){
                model.put("btnCode","1000");
            }
            return new ModelAndView("/law/upd",model);
        }
        return new ModelAndView("/law/info",model);
    }

    @RequestMapping(value = "/operate")
    public String operate(HttpServletRequest req,HttpServletResponse rsp){
        Map<String,Object> param =  new HashMap<>();
        String btnCode = req.getParameter("btnCode");
        if ("1515".equals(btnCode)){
//            param.put("checkAssessFee",req.getParameter("checkAssessFee"));
//            param.put("finalAssessFee",req.getParameter("finalAssessFee"));
//            param.put("finalAssessAmount",req.getParameter("finalAssessAmount"));
//            param.put("travelFee",req.getParameter("travelFee"));
//            param.put("assessRemark",req.getParameter("assessRemark"));
//            param.put("toName",req.getParameter("toName"));
//            param.put("toTel",req.getParameter("toTel"));
//            param.put("toOrg",req.getParameter("toOrg"));
//            param.put("toAddress",req.getParameter("toAddress"));
        }else if ("1900".equals(btnCode)){
            param.put("orgId",req.getParameter("orgId"));
        }
//        else if ("1500".equals(btnCode)){
//            param.put("uploadPaths",req.getParameter("uploadPaths"));
//        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LAW_CASE_INFO_OPR, param, req, rsp);
    }

    /**
     * 分派评估师- 评估师列表
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "/assessUsers")
    public ModelAndView assessUsers(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.parseLong(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        Long roleId = Long.parseLong(req.getParameter("roleId"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_INFO_LIST_BY_ROLEID, null, req);
        List<UserInfo> userInfos =(List<UserInfo>)apiFinalResponse.getResults();
        model.put("id",id);
        model.put("btnCode",btnCode);
        model.put("roleId",roleId);
        model.put("userInfos",userInfos);
        return new ModelAndView("/law/assessUsers",model);
    }

    @RequestMapping(value = "/uploadLawFile")
    public ModelAndView uploadLawFile(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.parseLong(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        model.put("id",id);
        model.put("btnCode",btnCode);
        return new ModelAndView("/law/uploadLawFile",model);
    }

    @RequestMapping(value = "/back")
    public ModelAndView back(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.parseLong(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        model.put("id",id);
        model.put("btnCode",btnCode);
        return new ModelAndView("/law/back",model);
    }

    @RequestMapping(value = "/okLawCaseInfo")
    public ModelAndView okLawCaseInfo(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.parseLong(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        model.put("id",id);
        model.put("btnCode",btnCode);
        return new ModelAndView("/law/okLawCaseInfo",model);
    }

    /**
     * 案件跟踪页面
     * @param req
     * @return
     */
    @RequestMapping(value = "/followAdd")
    public String followAdd (HttpServletRequest req,Model model) {
        String caseId = req.getParameter("caseId");
        model.addAttribute("caseId",caseId);
        return "/law/followAdd";
    }

    /**
     * 保存案件跟踪信息
     * @param req
     * @return
     */
    @RequestMapping(value = "/followSave")
    public String followSave (HttpServletRequest req,HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LAW_CASE_INFO_FOLLOW_SAVE, null, req, rsp);
    }

    @RequestMapping(value = "selectOrgInfo")
    public ModelAndView selectOrgInfo(HttpServletRequest req, HttpServletResponse rsp) {
        String orgName = req.getParameter("orgName");
        String orgTel = req.getParameter("orgTel");
        String id = req.getParameter("id");
        String btnCode = req.getParameter("btnCode");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("orgName",orgName==null?"":orgName);
        model.put("orgTel",orgTel==null?"":orgTel);
        model.put("id",id==null?null:id);
        model.put("btnCode",btnCode);
        return new ModelAndView("/law/selectOrgList",model);
    }

    /**
     *
     * 查看资料
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectLawFile")
    public String selectLawFile(HttpServletRequest req,HttpServletResponse rsp){
        String viewType = req.getParameter("viewType");
        String id = req.getParameter("id");
        //点击查询
        if ("treeClick".equals(viewType)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LAW_FILE_ADDRESS, null, req,rsp);
        }

        //查看进度
        if ("progress".equals(viewType)){
            TypeToken token = new TypeToken<ApiFinalResponse<List<CaseFollowInfo>>>(){};
            Map<String,Object> paramMap = new HashMap<>();
            ApiFinalResponse response = this.callApi(token, BackendApiMethodEnum.BACKEND_LAW_PROGRESS,paramMap,req);
            List<CaseFollowInfo> progress = (List<CaseFollowInfo>)(response == null ? null : response.getResults());
            req.setAttribute("list",progress);
            return "/law/progress";
        }

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        TypeToken token = new TypeToken<ApiFinalResponse<List<LawFileDto>>>(){};
        ApiFinalResponse response = this.callApi(token, BackendApiMethodEnum.BACKEND_LAW_FILE,paramMap,req);
        List<LawFileDto> lawFileDtos = (List<LawFileDto>)(response == null ? null : response.getResults());
        req.setAttribute("lawFileDtos",lawFileDtos);
        req.setAttribute("id",id);
        return "/law/lawFile";
    }


    /**
     *
     * 放大查看图片
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/lawFileShow")
    public String lawFileShow(HttpServletRequest req,HttpServletResponse rsp){
        String id = req.getParameter("id");
        String fileType = req.getParameter("fileType");

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        paramMap.put("fileType",fileType);
        TypeToken token = new TypeToken<ApiFinalResponse<List<LawFileDto>>>(){};
        ApiFinalResponse response = this.callApi(token, BackendApiMethodEnum.BACKEND_LAW_FILE,paramMap,req);
        List<LawFileDto> lawFileDtos = (List<LawFileDto>)(response == null ? null : response.getResults());
        req.setAttribute("caseFile",lawFileDtos);

        String index = req.getParameter("index");
        req.setAttribute("id",index);
        return "/law/lawFileShow";
    }
}
