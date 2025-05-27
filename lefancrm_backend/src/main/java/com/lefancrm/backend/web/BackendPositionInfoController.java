package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
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
 * Created by wangwei on 2018/3/21.
 * “职位”数据管理
 */
@Controller
@RequestMapping(value = "/positionInfo")
public class BackendPositionInfoController extends BackendBaseController{
    /*
    * 获取“职位”数据列表
    */
    @RequestMapping(value = "/positionInfoList")
    public ModelAndView positionInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PositionInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_POSITION_INFO_LIST, null, req);

        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();

        //职位名称
        String positionName = req.getParameter("positionName");
        model.put("positionName", positionName);
        model.put("positionLevel", positionLevel);
        model.put("page", page);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/positionInfo/list",model);
    }

    /*
    * 编辑数据
    */
    @RequestMapping(value = "/positionInfoEdit")
    public ModelAndView positionInfoEdit(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<PositionInfoDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_POSITION_INFO_EDIT, appendMap, null);
        Map model = new HashMap();
        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevel", positionLevel);

        model.put("positionInfo",apiFinalResponse.getResults());
        return new ModelAndView("/positionInfo/edit",model);
    }

    /*
    * add页面
    */
    @RequestMapping(value = "/positionInfoAdd")
    public ModelAndView positionInfoAdd(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevel", positionLevel);

        return new ModelAndView("/positionInfo/add",model);
    }

    /*
    * 新增或修改数据
    */
    @RequestMapping(value = "/positionInfoUpdate")
     public String positionInfoUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_POSITION_INFO_SAVE, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_POSITION_INFO_UPDATE, null, req, rsp);
        }

    }

    @RequestMapping(value = "/positionInfoDelete")
    public String positionInfoDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_POSITION_INFO_DELETE, null, req, rsp);
    }
}
