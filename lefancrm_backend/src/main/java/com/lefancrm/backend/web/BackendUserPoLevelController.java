package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.PositionInfoDto;
import com.lefancrm.backend.dto.PositionLevelDto;
import com.lefancrm.backend.dto.UserInfoDto;
import com.lefancrm.backend.dto.UserPoLevelDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by wangwei on 2018/3/21.
 * “CC人员职级职位信息”数据管理
 */
@Controller
@RequestMapping(value = "/userPoLevel")
public class BackendUserPoLevelController extends BackendBaseController{
    /*
    * 获取“CC人员职级职位信息”数据列表
    */
    @RequestMapping(value = "/userPoLevelList")
    public ModelAndView userPoLevelList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserPoLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_PO_LEVEL_LIST, null, req);

        Map model = new HashMap();
        //查询条件
        String userName = req.getParameter("userName");
        model.put("userName", userName);

        model.put("page", page);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/userPoLevel/list",model);
    }

    /*
    * 编辑“CC人员职级职位信息”数据
    */
    @RequestMapping(value = "/userPoLevelEdit")
    public ModelAndView userPoLevelEdit(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<UserPoLevelDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_USER_PO_LEVEL_EDIT, appendMap, null);
        UserPoLevelDto userPoLevel = (UserPoLevelDto)apiFinalResponse.getResults();

        Map model = new HashMap();

        //获取职位数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_INFO_LIST, null, req);
        List<PositionInfoDto> positionInfo = (List<PositionInfoDto>) apiFinalResponse1.getResults();

        //获取职级数据list
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse2.getResults();

        //查询是CC人员职级职位的用户list
        TypeToken typeToken3 = new TypeToken<ApiFinalResponse<List<UserInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse3= this.callApi(typeToken3, BackendApiMethodEnum.BACKEND_CC_USER_INFO_LIST, null, req);
        List<UserInfoDto> userInfo = (List<UserInfoDto>) apiFinalResponse3.getResults();

        //将本条记录加入list中

        Map<String, Object> appendMap2 = new HashMap<String, Object>();
        appendMap2.put("userId", userPoLevel.getUserId());
        TypeToken typeToken5 = new TypeToken<ApiFinalResponse<UserInfoDto>>() {};
        ApiFinalResponse apiFinalResponse5 = this.callApi(typeToken5,BackendApiMethodEnum.BACKEND_USER_INFO_BY_USERID, appendMap2, null);
        UserInfoDto userIn = (UserInfoDto)apiFinalResponse5.getResults();
        userInfo.add(userIn);

        model.put("positionInfo",positionInfo);
        model.put("positionLevel",positionLevel);
        model.put("userInfo",userInfo);

        model.put("userPoLevel",apiFinalResponse.getResults());
        return new ModelAndView("/userPoLevel/edit",model);
    }

    /*
    * add页面
    */
    @RequestMapping(value = "/userPoLevelAdd")
    public ModelAndView userPoLevelAdd(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //获取职位数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_INFO_LIST, null, req);
        List<PositionInfoDto> positionInfo = (List<PositionInfoDto>) apiFinalResponse1.getResults();

        //获取职级数据list
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse2.getResults();

        //查询是CC人员职级职位的用户list
        TypeToken typeToken3 = new TypeToken<ApiFinalResponse<List<UserInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse3= this.callApi(typeToken3, BackendApiMethodEnum.BACKEND_CC_USER_INFO_LIST, null, req);
        List<UserInfoDto> userInfo = (List<UserInfoDto>) apiFinalResponse3.getResults();


        model.put("positionInfo",positionInfo);
        model.put("positionLevel",positionLevel);
        model.put("userInfo",userInfo);

        return new ModelAndView("/userPoLevel/add",model);
    }

    /*
    * 新增或修改数据
    */
    @RequestMapping(value = "/userPoLevelUpdate")
     public String userPoLevelUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_USER_PO_LEVEL_SAVE, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_USER_PO_LEVEL_UPDATE, null, req, rsp);
        }

    }

    @RequestMapping(value = "/userPoLevelDelete")
    public String userPoLevelDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_USER_PO_LEVEL_DELETE, null, req, rsp);
    }

    @RequestMapping(value = "/searchInfoByPositionId")
    public String searchInfoByPositionId(HttpServletRequest req, HttpServletResponse rsp) {
        String positionId=req.getParameter("positionId");
        Map param = new HashMap();
        param.put("id",positionId);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_SEARCH_INFO_BY_POSITIONID, param, req);

        Type type = new TypeToken<ApiFinalResponse<PositionInfoDto>>() {
        }.getType();
        ApiFinalResponse<PositionInfoDto> apiRsp = JsonUtil.jsonToObject(json, type);

        retJson = JsonUtil.objectToJson(apiRsp.getResults());

        return WebHelper.outputJson(retJson, rsp);
    }

    @RequestMapping(value = "/searchInfoByLevelId")
    public String searchInfoByLevelId(HttpServletRequest req, HttpServletResponse rsp) {
        String levelId=req.getParameter("levelId");
        Map param = new HashMap();
        param.put("positionLevelId",levelId);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_SEARCH_INFO_BY_LEVELID, param, req);

        Type type = new TypeToken<ApiFinalResponse<PositionInfoDto>>() {
        }.getType();
        ApiFinalResponse<PositionInfoDto> apiRsp = JsonUtil.jsonToObject(json, type);
        retJson = JsonUtil.objectToJson(apiRsp.getResults());

        return WebHelper.outputJson(retJson, rsp);
    }
}
