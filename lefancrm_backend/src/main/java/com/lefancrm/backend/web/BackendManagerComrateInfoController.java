package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.ManagerComrateInfoDto;
import com.lefancrm.backend.dto.PositionInfoDto;
import com.lefancrm.backend.dto.PositionLevelDto;
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
 * Created by wangwei on 2018/3/26.
 * “管理佣金指标和提成比例”数据管理
 */
@Controller
@RequestMapping(value = "/managerComrateInfo")
public class BackendManagerComrateInfoController extends BackendBaseController{
    /*
    * 获取“管理佣金指标和提成比例”数据列表
    */
    @RequestMapping(value = "/managerComrateInfoList")
    public ModelAndView managerComrateInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ManagerComrateInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_MANAGER_COMRATE_INFO_LIST, null, req);
        Map model = new HashMap();

        model.put("page", page);
        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/managerComrateInfo/list",model);
    }

    /*
    * 编辑数据
    */
    @RequestMapping(value = "/managerComrateInfoEdit")
    public ModelAndView managerComrateInfoEdit(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<ManagerComrateInfoDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_MANAGER_COMRATE_INFO_EDIT, appendMap, null);
        Map model = new HashMap();

        model.put("managerComrateInfo",apiFinalResponse.getResults());
        return new ModelAndView("/managerComrateInfo/edit",model);
    }

    /*
    * add页面
    */
    @RequestMapping(value = "/managerComrateInfoAdd")
    public ModelAndView managerComrateInfoAdd(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        return new ModelAndView("/managerComrateInfo/add",model);
    }

    /*
    * 新增或修改数据
    */
    @RequestMapping(value = "/managerComrateInfoUpdate")
     public String managerComrateInfoUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_MANAGER_COMRATE_INFO_SAVE, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_MANAGER_COMRATE_INFO_UPDATE, null, req, rsp);
        }

    }

    @RequestMapping(value = "/managerComrateInfoDelete")
    public String managerComrateInfoDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_MANAGER_COMRATE_INFO_DELETE, null, req, rsp);
    }
}
