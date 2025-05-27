package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.PositionInfoDto;
import com.lefancrm.backend.dto.PositionLevelDto;
import com.lefancrm.backend.dto.QualifiedManpowerDto;
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
 * “合格人力奖金指标”数据管理
 */
@Controller
@RequestMapping(value = "/qualifiedManpower")
public class BackendQualifiedManpowerController extends BackendBaseController{
    /*
    * 获取“合格人力奖金指标”数据列表
    */
    @RequestMapping(value = "/qualifiedManpowerList")
    public ModelAndView qualifiedManpowerList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<QualifiedManpowerDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_QUALIFIED_MANPOWER_LIST, null, req);
        Map model = new HashMap();

        //查询条件
//        String positionName = req.getParameter("positionName");
//        model.put("positionName", positionName);

        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevel", positionLevel);
        model.put("page", page);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/qualifiedManpower/list",model);
    }

    /*
    * 编辑数据
    */
    @RequestMapping(value = "/qualifiedManpowerEdit")
    public ModelAndView qualifiedManpowerEdit(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<QualifiedManpowerDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_QUALIFIED_MANPOWER_EDIT, appendMap, null);
        Map model = new HashMap();
        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevel", positionLevel);

        model.put("qualifiedManpower",apiFinalResponse.getResults());
        return new ModelAndView("/qualifiedManpower/edit",model);
    }

    /*
    * add页面
    */
    @RequestMapping(value = "/qualifiedManpowerAdd")
    public ModelAndView qualifiedManpowerAdd(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevel", positionLevel);

        return new ModelAndView("/qualifiedManpower/add",model);
    }

    /*
    * 新增或修改数据
    */
    @RequestMapping(value = "/qualifiedManpowerUpdate")
     public String qualifiedManpowerUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_QUALIFIED_MANPOWER_SAVE, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_QUALIFIED_MANPOWER_UPDATE, null, req, rsp);
        }

    }

    @RequestMapping(value = "/qualifiedManpowerDelete")
    public String qualifiedManpowerDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_QUALIFIED_MANPOWER_DELETE, null, req, rsp);
    }
}
