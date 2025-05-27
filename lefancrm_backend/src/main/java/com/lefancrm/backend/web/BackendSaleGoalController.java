package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.CommonArea;
import com.lefancrm.backend.dto.OrgInfo;
import com.lefancrm.backend.dto.SaleGoal;
import com.lefancrm.backend.dto.SaleGoalDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.DateTimeUtil;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by jun on 2018/1/4.
 */
@Controller
@RequestMapping("/sale/goal")
public class BackendSaleGoalController extends BackendBaseController{





    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp)throws  Exception{
        int year = DateTimeUtil.getYearOfDate(new Date());
        boolean isAdd = false;
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SaleGoal>>>() {};
        ApiFinalResponse<List<SaleGoal>> apiFinalResponse= this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SALEGOALLIST, null, req);
        //判断是否可以新增年度业绩报告。year是当前年。
        for(int i=0;i<apiFinalResponse.getResults().size();i++){
            if(year==Integer.parseInt(apiFinalResponse.getResults().get(i).getYear())){
                isAdd = true;
            }
        }
        model.put("apiRsp",apiFinalResponse);
        model.put("isAdd",isAdd);
        return new ModelAndView("/saleGoal/list",model);
    }


    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse rsp)throws  Exception{
        String year = req.getParameter("year");
        Map model = new HashMap();
        if(year==null){
            int yearDate = DateTimeUtil.getYearOfDate(new Date());
            year = String.valueOf(yearDate);
        }
        model.put("year",year);
        return new ModelAndView("/saleGoal/index",model);
    }


    @RequestMapping(value = "/userInfoList")
    public String userInfoList(HttpServletRequest req, HttpServletResponse rsp)throws  Exception{
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_SALE_GOAL_LIST, null, req);
        Type type = new TypeToken<ApiFinalResponse<List<SaleGoalDto>>>() {
        }.getType();
        ApiFinalResponse<List<SaleGoalDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
            List<SaleGoalDto> tree = apiRsp.getResults();
            retJson = JsonUtil.objectToJson(tree);
        }
        return WebHelper.outputJson(retJson, rsp);
    }


    @RequestMapping(value = "/update/user")
    public String updateUserSaleGoal(HttpServletRequest req, HttpServletResponse rsp)throws  Exception{
        String json = this.callApi(BackendApiMethodEnum.BACKEND_UPDATESALEGOAL, null, req);
        return WebHelper.outputJson(json, rsp);
    }





}
