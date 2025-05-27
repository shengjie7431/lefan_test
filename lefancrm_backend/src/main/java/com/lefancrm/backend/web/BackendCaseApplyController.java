package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lefancrm.base.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangwei on 2018/05/14.
 */
@Controller
@RequestMapping(value = "/caseApply")
public class BackendCaseApplyController extends BackendBaseController {
    /**
     * 查询报案中心列表
     *
     */
    @RequestMapping(value = "/caseApplyList")
    public ModelAndView caseApplyList(HttpServletRequest req, HttpServletResponse rsp) {
        String userName = req.getParameter("userName");
        String phone = req.getParameter("phone");
        String state = req.getParameter("state");
        String caseNo = req.getParameter("caseNo");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseApplyDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_APPLY_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("userName",userName==null?"":userName);
        model.put("phone",phone==null?"":phone);
        model.put("state",state==null?"":state);
        model.put("caseNo",caseNo==null?"":caseNo);
        return new ModelAndView("/case/caseApply/caseApplyList",model);
    }
    /**
     * 查看报案中心列表
     *
     */
    @RequestMapping(value = "/caseApplyView")
    public ModelAndView caseApplyView(HttpServletRequest req , HttpServletResponse rsp) {
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseApplyDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_APPLY_INFO_BY_ID, null, req);
        CaseApplyDto caseApplyInfo = (CaseApplyDto) apiFinalResponse.getResults();
        model.put("caseApplyInfo", caseApplyInfo);
        return new ModelAndView("/case/caseApply/caseApplyView",model);
    }
    /**
     * 添加新案件
     *
     */
    @RequestMapping(value = "/caseApplyAdd")
    public String caseApplyAdd(HttpServletRequest req){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("parentId",0);
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
        Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
        }.getType();
        ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
        req.setAttribute("apiRsp", apiRsp);
        return "/case/caseApply/caseApplyAdd";
    }
    /**
     * 保存新案件
     *
     */
    @RequestMapping(value = "/caseApplySave")
    public String caseApplySave(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_APPLY_SAVE, null, req, rsp);
    }
    /**
     * 转办案件中心
     *
     */
    @RequestMapping(value = "/caseApplyForward")
    public ModelAndView caseApplyForward(HttpServletRequest req, HttpServletResponse rsp) {
        String id = req.getParameter("id");
        String userName = req.getParameter("userName");
        String phone = req.getParameter("phone");
        String caseProvince =req.getParameter("caseProvince");
        String caseProvinceId = req.getParameter("caseProvinceId");
        String caseCity = req.getParameter("caseCity");
        String caseCityId = req.getParameter("caseCityId");
        String caseDistrict = req.getParameter("caseDistrict");
        String caseDistrictId = req.getParameter("caseDistrictId");
        String caseAddress = req.getParameter("caseAddress");
//        String dangerTime = req.getParameter("dangerTime");
        String isDangerTime = req.getParameter("isDangerTime");
        String type = req.getParameter("type");
        String userId = req.getParameter("userId");
        Map model = new HashMap();
        model.put("id",id==null?"":id);
        model.put("userName",userName==null?"":userName);
        model.put("phone",phone==null?"":phone);
        model.put("caseProvince",caseProvince==null?"":caseProvince);
        model.put("caseProvinceId",caseProvinceId==null?"":caseProvinceId);
        model.put("caseCity",caseCity==null?"":caseCity);
        model.put("caseCityId",caseCityId==null?"":caseCityId);
        model.put("caseDistrict",caseDistrict==null?"":caseDistrict);
        model.put("caseDistrictId",caseDistrictId==null?"":caseDistrictId);
        model.put("caseAddress",caseAddress==null?"":caseAddress);
//        model.put("dangerTime",dangerTime==null?"":dangerTime);
        model.put("isDangerTime",isDangerTime==null?false:isDangerTime);
        model.put("type",type);
        model.put("userId",userId);

        //仅查询机构-父级为1的数据
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        Type type1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {
        }.getType();
        ApiFinalResponse<List<OrgInfoDto>> apiRsp = JsonUtil.jsonToObject(json, type1);
        model.put("apiRsp",apiRsp);

        return new ModelAndView("/case/caseApply/caseApplyForward",model);
    }
    /**
     * 保存转办案件中心
     *
     */
    @RequestMapping(value = "/forwardSubmit")
    public String caseApplyForwardSubmit(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASEAPPLY_FORWARDSUBMIT, null, req,rsp);
    }

    /**
     * 标记已处理
     *
     */
    @RequestMapping(value = "/caseApplyToAlready")
    public String caseApplyToAlready(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASEAPPLY_TO_ALREADY_STATE, null, req, rsp);
    }

    /**
     * 查询咨询案件
     *
     */
    @RequestMapping(value = "/caseApplyAlreadyList")
    public ModelAndView caseApplyAlreadyList(HttpServletRequest req, HttpServletResponse rsp) {

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseApplyDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_APPLY_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        return new ModelAndView("/case/caseApply/caseApplyAlreadyList",model);
    }

    @RequestMapping(value = "/selectSalesUserByOrgId")
    public String selectSalesUserByOrgId(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASEAPPLY_SELECT_SALES_USER_BY_ORGID, null, req,rsp);
    }
}
