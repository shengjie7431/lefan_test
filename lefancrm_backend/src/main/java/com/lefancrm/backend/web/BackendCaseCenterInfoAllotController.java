package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.CaseCenterInfo;
import com.lefancrm.backend.dto.CaseCenterInfoAllotDto;
import com.lefancrm.backend.dto.CaseCenterInfoFollowDto;
import com.lefancrm.backend.dto.UserInfo;
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
 * Created by wangwei on 2018/05/16.
 */
@Controller
@RequestMapping(value = "/caseCenterInfoAllot")
public class BackendCaseCenterInfoAllotController extends BackendBaseController {

    /**
     * 案件分配列表
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoAllotList")
    public ModelAndView caseCenterInfoAllotList(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        String orgId = req.getParameter("orgId");
        String gradationState = req.getParameter("gradationState");
        String caseName = req.getParameter("caseName");
        String caseNo = req.getParameter("caseNo");
        String type = req.getParameter("type");
        String isTestcase = req.getParameter("isTestcase");

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoAllotDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_ALLOT_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("orgId",orgId==null?"":orgId);
        model.put("gradationState",gradationState==null?"":gradationState);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseName",caseName==null?"":caseName);
        model.put("type",type==null?"":type);

        //判断登录人是不是测试人员
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<UserInfo>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_USER_INFO, null, req);
        UserInfo userInfoDto = (UserInfo)apiFinalResponse1.getResults();
        if(isTestcase == null){
            if(userInfoDto.getIsTester() ==1){
                //测试人员
                model.put("isTestcase",1);
            }else{
                model.put("isTestcase",0);
            }
        }else if(isTestcase == ""){
            model.put("isTestcase",-1);
        }else {
            model.put("isTestcase", isTestcase == null ? "" : isTestcase);
        }

        model.put("page", page);
        return new ModelAndView("/caseCenterInfoAllot/caseCenterInfoAllotList",model);

    }

    /**
     * 单条案件分配详情
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoAllotView")
    public ModelAndView caseCenterInfoAllotView(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //案件信息
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfo>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FOR_ALLOT_BY_ID, null, req);
        CaseCenterInfo caseCenterInfo = (CaseCenterInfo) apiFinalResponse.getResults();
        model.put("caseCenterInfo", caseCenterInfo);

        return new ModelAndView("/caseCenterInfoAllot/caseCenterInfoAllotView",model);
    }

    /**
     * 显示案件分配页面
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoAllotAdd")
    public ModelAndView caseCenterInfoAllotAdd (HttpServletRequest req,HttpServletResponse rsp) {
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        String gradationState = req.getParameter("gradationState");
        String type = req.getParameter("type");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_INFO_LIST_FOR_ALLOT, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("gradationState",gradationState);
        model.put("type",type);
        //gradationState : 1、洽谈阶段（业务员）；2、评估阶段（评估员）3、索赔阶段（索赔员）
        if("1".equals(gradationState)){
            model.put("bsRoleId",2);
        }else if("2".equals(gradationState)){
            model.put("bsRoleId",3);
        }else if("3".equals(gradationState)){
            model.put("bsRoleId",7);
        }else if("6".equals(gradationState)){
            model.put("bsRoleId",29);
        }
        String caseId = req.getParameter("caseId");
        model.put("caseId",caseId);
        return new ModelAndView("/caseCenterInfoAllot/caseCenterInfoAllotAdd",model);
    }

    /**
     * 保存案件分配
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoAllotSave")
    public String caseCenterInfoAllotSave (HttpServletRequest req,HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_ALLOT_SAVE, null, req, rsp);
    }


    /**
     * 修改业务员（查询案件所在机构下的所有业务员信息）
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "/selectOperator")
    public ModelAndView selectOperator(HttpServletRequest req, HttpServletResponse rsp) {
        String caseId = req.getParameter("caseId");
        String operatorName = req.getParameter("operatorName");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_LIST_BY_ORGID, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("caseId",caseId==null?null:caseId);
        model.put("operatorName",operatorName);
        return new ModelAndView("/caseCenterInfoAllot/caseOperatorList",model);
    }

    /**
     * 修改业务员
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "/updateOperator")
    public String updateOperator(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_OPERATOR, null, req,rsp);
    }

}
