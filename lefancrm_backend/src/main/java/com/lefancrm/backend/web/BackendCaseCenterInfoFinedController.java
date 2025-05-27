package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
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
 * Created by wangwei on 2018/05/17.
 */
@Controller
@RequestMapping(value = "/caseCenterInfoFined")
public class BackendCaseCenterInfoFinedController extends BackendBaseController {

    /**
     * 扣罚案件清单列表
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoFinedList")
    public ModelAndView caseCenterInfoFinedList(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        String caseName = req.getParameter("caseName");
        String caseNo = req.getParameter("caseNo");
        String caseTitle = req.getParameter("caseTitle");
        String finedType = req.getParameter("finedType");
        String caseType = req.getParameter("caseType");
        String isTestcase = req.getParameter("isTestcase");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoFinedDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FINED_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("caseName",caseName==null?"":caseName);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseTitle",caseTitle==null?"":caseTitle);

        model.put("finedType",finedType==null?"":finedType);
        model.put("caseType",caseType==null?"":caseType);

        typeToken = new TypeToken<ApiFinalResponse<List<CommonFineEnumDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_COMMON_FINE_ENUM_LIST, null, req);
        model.put("commonFineEnumDto", apiFinalResponse.getResults());

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
        return new ModelAndView("/caseCenterInfoFined/caseCenterInfoFinedList",model);
    }

    /**
     * 显示新增扣罚案件页面
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoFinedAdd")
    public String caseCenterInfoFinedAdd (HttpServletRequest req,Model model) {
        String caseId = req.getParameter("caseId");
        model.addAttribute("caseId",caseId);
        String caseType = req.getParameter("caseType");
        model.addAttribute("caseType",caseType);

        //获取CommonFineEnumDto
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonFineEnumDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_COMMON_FINE_ENUM_LIST, null, req);
        model.addAttribute("commonFineEnumDto", apiFinalResponse.getResults());

        return "/caseCenterInfoFined/caseCenterInfoFinedAdd";
    }

    /**
     * 扣罚类目匹配金额
     * @param req
     * @return
     */
    @RequestMapping(value = "/seachInfoByFinedType")
    public String seachInfoByFinedType(HttpServletRequest req, HttpServletResponse rsp) {
        String finedType=req.getParameter("finedType");
        Map param = new HashMap();
        param.put("id",finedType);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_COMMON_FINE_ENUM_INFO_BY_ID, param, req);
        Type type = new TypeToken<ApiFinalResponse<CommonFineEnumDto>>() {
        }.getType();
        ApiFinalResponse<CommonFineEnumDto> apiRsp = JsonUtil.jsonToObject(json, type);

        retJson = JsonUtil.objectToJson(apiRsp.getResults());

        return WebHelper.outputJson(retJson, rsp);
    }

    /**
     * 保存扣罚案件
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoFinedSave")
    public String caseCenterInfoFinedSave(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FINED_SAVE, null, req, rsp);
    }

    /**
     * 根据“caseNo”查询扣罚记录详情
     * @param req
     * @return
     */
    @RequestMapping(value = "/caseCenterInfoFinedView")
    public ModelAndView caseCenterInfoFinedView(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //案件信息
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoFinedDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FINED_BY_CASENO, null, req);
        CaseCenterInfoFinedDto caseCenterInfoFinedDto = (CaseCenterInfoFinedDto) apiFinalResponse.getResults();
        model.put("caseCenterInfoFined", caseCenterInfoFinedDto);

        return new ModelAndView("/caseCenterInfoFined/caseCenterInfoFinedView",model);
    }
}
