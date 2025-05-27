package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.CommissionInfoDto;
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
 * “佣金指标”数据管理
 */
@Controller
@RequestMapping(value = "/commissionInfo")
public class BackendCommissionInfoController extends BackendBaseController{
    /*
    * 获取“佣金指标”数据列表
    */
    @RequestMapping(value = "/commissionInfoList")
    public ModelAndView commissionInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommissionInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_COMMISSION_INFO_LIST, null, req);

        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();

        //职位名称
        String levelCode = req.getParameter("levelCode");
        model.put("levelCode", levelCode);
        model.put("positionLevel", positionLevel);
        model.put("page", page);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/commissionInfo/list",model);
    }

    /*
    * 编辑数据
    */
    @RequestMapping(value = "/commissionInfoEdit")
    public ModelAndView commissionInfoEdit(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CommissionInfoDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_COMMISSION_INFO_EDIT, appendMap, null);
        Map model = new HashMap();
        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevel", positionLevel);

        model.put("commissionInfo",apiFinalResponse.getResults());
        return new ModelAndView("/commissionInfo/edit",model);
    }

    /*
    * add页面
    */
    @RequestMapping(value = "/commissionInfoAdd")
    public ModelAndView commissionInfoAdd(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevel", positionLevel);

        return new ModelAndView("/commissionInfo/add",model);
    }

    /*
    * 新增或修改数据
    */
    @RequestMapping(value = "/commissionInfoUpdate")
     public String commissionInfoUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_COMMISSION_INFO_SAVE, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_COMMISSION_INFO_UPDATE, null, req, rsp);
        }

    }

    @RequestMapping(value = "/commissionInfoDelete")
    public String commissionInfoDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_COMMISSION_INFO_DELETE, null, req, rsp);
    }
}
