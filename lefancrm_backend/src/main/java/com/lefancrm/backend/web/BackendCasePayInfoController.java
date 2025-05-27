package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.CasePayInfoDto;
import com.lefancrm.backend.dto.CaseUserRoleDto;
import com.lefancrm.backend.dto.UserInfo;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/06/19.
 */
@Controller
@RequestMapping(value = "/casePayInfo")
public class BackendCasePayInfoController extends BackendBaseController {

    @RequestMapping(value = "/casePayInfoList")
    public ModelAndView casePayInfoList(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        String userName = req.getParameter("userName");
        String caseTitle = req.getParameter("caseTitle");
        String payState = req.getParameter("payState");
        String auditState = req.getParameter("auditState");
        String isTestcase = req.getParameter("isTestcase");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CasePayInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_PAY_INFO_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("userName",userName==null?"":userName);
        model.put("caseTitle",caseTitle==null?"":caseTitle);
        model.put("payState",payState==null?"":payState);
        model.put("auditState",auditState==null?"":auditState);

        model.put("pageSize",req.getParameter("pageSize"));
        //获取角色
        typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>(){};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
        CaseUserRoleDto caseUserRoleDto = (CaseUserRoleDto)apiFinalResponse.getResults();

        model.put("caseUserRole",caseUserRoleDto);

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
        return new ModelAndView("/casePayInfo/casePayInfoList",model);
    }

    /**
     * 单条待支付项目详情
     * @param req
     * @return
     */
    @RequestMapping(value = "/casePayInfoView")
    public ModelAndView casePayInfoView(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<CasePayInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_PAY_INFO_BY_ID, null, req);
        CasePayInfoDto casePayInfo = (CasePayInfoDto) apiFinalResponse.getResults();

        //获取角色
        typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>(){};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
        CaseUserRoleDto caseUserRoleDto = (CaseUserRoleDto)apiFinalResponse.getResults();

        model.put("caseUserRole",caseUserRoleDto);
        model.put("casePayInfo", casePayInfo);
        return new ModelAndView("/casePayInfo/casePayInfoView",model);
    }
    /**
     * 上传开票详情页面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/uploadCasePayInfoImg")
    public ModelAndView uploadCasePayInfoImg(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        String id = req.getParameter("id");
        model.put("id",id);
        String btnCode = req.getParameter("btnCode");
        model.put("btnCode",btnCode);
        return new ModelAndView("/casePayInfo/uploadCasePayInfoImg",model);
    }

    /**
     * 修改状态
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/casePayInfoUpd")
    public String casePayInfoUpd(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_PAY_INFO_UPD,null,req,rsp);
    }
}
