package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/05/15.
 */
@Controller
@RequestMapping(value = "/caseCenterInfoFollow")
public class BackendCaseCenterInfoFollowController extends BackendBaseController {

    /**
     * 案件跟踪列表
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoFollowList")
    public ModelAndView caseCenterInfoFollowList(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        String gradationState = req.getParameter("gradationState");
        String caseName = req.getParameter("caseName");
        String caseNo = req.getParameter("caseNo");
        String caseState = req.getParameter("caseState");
        String followType = req.getParameter("followType");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoFollowDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FOLLOW_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("gradationState",gradationState==null?"":gradationState);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseName",caseName==null?"":caseName);
        model.put("caseState",caseState==null?"":caseState);
        model.put("followType",followType==null?"":followType);

        model.put("page", page);
        return new ModelAndView("/caseCenterInfoFollow/caseCenterInfoFollowList",model);
    }

    /**
     * 单条案件跟踪详情
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoFollowView")
    public ModelAndView caseCenterInfoFollowView(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoFollowDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FOLLOW_BY_CASEID, null, req);
        model.put("apiRsp",apiFinalResponse);

        //案件信息
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<CaseCenterInfo>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_BY_ID, null, req);
        CaseCenterInfo caseCenterInfo = (CaseCenterInfo) apiFinalResponse1.getResults();
        model.put("caseCenterInfo", caseCenterInfo);

        return new ModelAndView("/caseCenterInfoFollow/caseCenterInfoFollowView",model);
    }

    /**
     * 显示新增案件跟踪页面
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoFollowAdd")
    public String caseCenterInfoFollowAdd (HttpServletRequest req,Model model) {
        String caseId = req.getParameter("caseId");
        model.addAttribute("caseId",caseId);
        String choose = req.getParameter("choose");
        model.addAttribute("choose",choose);
        String id = req.getParameter("id");
        model.addAttribute("id",id);
        String type = req.getParameter("type");
        model.addAttribute("type",type);
        return "/caseCenterInfoFollow/caseCenterInfoFollowAdd";
    }

    /**
     * 保存案件跟踪信息(添加 或  结束)
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoFollowSave")
    public String caseCenterInfoFollowSave (HttpServletRequest req,HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FOLLOW_SAVE, null, req, rsp);
    }
}
