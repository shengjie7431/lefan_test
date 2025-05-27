package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.BusUserRoleDto;
import com.lefancrm.backend.dto.ThinkData;
import com.lefancrm.backend.dto.UserInfo;
import com.lefancrm.backend.util.DateUtil;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/think/data/")
public class BackendThinkDataController extends BackendBaseController{
    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ThinkData>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.THINK_DATA_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("thinkTime",req.getParameter("thinkTime"));
        UserInfo curUser = this.getSessionAdmin(req);
        if (curUser != null){
            if (curUser.getUserId().intValue() == 2189){
                model.put("csManager",true);
            }
        }
        return new ModelAndView("/survey/report/think/data/list",model);
    }
    @RequestMapping(value = "info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
        List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
        model.put("roleOne",isRoleUser(userRoles,108L));//机构经理
        model.put("roleTwo",isRoleUser(userRoles,109L));//分管总
        model.put("roleThree",isRoleUser(userRoles,23L));//财务
        model.put("isCurMonth",new SimpleDateFormat("yyyy-MM").format(DateUtil.getUpMonth()).equals(req.getParameter("itemMonth")));
        model.put("thinkDataId",req.getParameter("thinkDataId"));
        return new ModelAndView("/survey/report/think/data/info",model);
    }
    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.THINK_DATA_OPERATE, null, req, rsp);
    }
    @RequestMapping(value = "ajaxData")
    public String ajaxData(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.THINK_DATA_AJAX_DATA, null, req, rsp);
    }
}
