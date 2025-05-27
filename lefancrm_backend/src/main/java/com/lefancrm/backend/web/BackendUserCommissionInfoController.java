package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/3/22.
 * “CC佣金”数据管理
 */
@Controller
@RequestMapping(value = "/userCommissionInfo")
public class BackendUserCommissionInfoController extends BackendBaseController{
    /*
    * 获取“CC佣金”数据列表
    */
    @RequestMapping(value = "/userCommissionInfoList")
    public ModelAndView userCommissionInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserCommissionInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_COMMISSION_INFO_LIST, null, req);
        Map model = new HashMap();

        //查询条件
        String userName = req.getParameter("userName");
        model.put("userName", userName);
        String month = req.getParameter("month");
        model.put("month", month);
        String year = req.getParameter("year");
        model.put("year", year);

        model.put("page", page);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/userCommissionInfo/list",model);
    }

    /*
   * 获取“CC佣金”的佣金记录
   */
    @RequestMapping(value = "/searchCommissionLogList")
    public ModelAndView searchCommissionLogList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        Integer month = Integer.parseInt(req.getParameter("month"));
        String year = req.getParameter("year");
        String monther = null;
        if( month < 10){
            monther = "0" + month.toString();
        }
        String date = year + monther;
        appendMap.put("createTime",date);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommissionLogDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_COMMISSION_LOG_LIST_BY_USER, appendMap, req);
        Map model = new HashMap();
        model.put("page", page);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/userCommissionInfo/commissionLogList",model);
    }

    /*
  * 获取“CC佣金”的月新签单记录
  */
    @RequestMapping(value = "/searchMonthNewSignList")
    public ModelAndView searchMonthNewSignList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        Integer month = Integer.parseInt(req.getParameter("month"));
        String year = req.getParameter("year");
        String monther = null;
        if( month < 10){
            monther = "0" + month.toString();
        }
        String date = year + monther;
        appendMap.put("agreeSignTime",date);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_LIST_FOR_MONTH_NEW_SIGN, appendMap, req);

        Map model = new HashMap();
        model.put("page", page);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/userCommissionInfo/monthNewSignList",model);
    }
}
