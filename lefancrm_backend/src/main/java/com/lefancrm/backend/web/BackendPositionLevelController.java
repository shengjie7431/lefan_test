package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.ManagerComrateInfoDto;
import com.lefancrm.backend.dto.PositionInfoDto;
import com.lefancrm.backend.dto.PositionLevelDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.web.WebHelper;
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
 * “职位级别”数据管理
 */
@Controller
@RequestMapping(value = "/positionLevel")
public class BackendPositionLevelController extends BackendBaseController{
    /*
    * 获取“职位级别”数据列表
    */
    @RequestMapping(value = "/positionLevelList")
    public ModelAndView positionLevelList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        Map model = new HashMap();

        //职位代码
        String levelCode = req.getParameter("levelCode");
        model.put("levelCode", levelCode);

        model.put("page", page);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/positionLevel/list",model);
    }

    /*
    * 编辑数据
    */
    @RequestMapping(value = "/positionLevelEdit")
    public ModelAndView positionLevelEdit(HttpServletRequest req, HttpServletResponse rsp) {

        Map model = new HashMap();
        Object isShow = req.getSession().getAttribute("isShow");
        Object managerComrateInfoId = req.getSession().getAttribute("managerComrateId");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));

        if(StringUtils.isEmpty(isShow)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<PositionLevelDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_EDIT, appendMap, null);
            model.put("positionLevel",apiFinalResponse.getResults());
            req.getSession().removeAttribute("managerComrateId");
//            model.put("managerComrateId", positionLevel.);
        }else{
            PositionLevelDto positionLevelDto = (PositionLevelDto)(req.getSession().getAttribute("positionLevel"));
            positionLevelDto.setManagerComrateId(new Long(managerComrateInfoId.toString()));
            model.put("positionLevel",positionLevelDto);
//            model.put("managerComrateId", managerComrateInfoId);
        }
        req.getSession().removeAttribute("isShow");

        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();


        model.put("positionLevelList", positionLevel);

        //清理session中的managerComrateInfoId
//        Object isShow = req.getSession().getAttribute("isShow");
//        if(StringUtils.isEmpty(isShow)) {
//            req.getSession().removeAttribute("managerComrateId");
//        }


        return new ModelAndView("/positionLevel/edit",model);
    }


   /*
    * add页面
    */
    @RequestMapping(value = "/positionLevelAdd")
    public ModelAndView positionLevelAdd(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        Object isShow = req.getSession().getAttribute("isShow");
        if(StringUtils.isEmpty(isShow)){
            req.getSession().removeAttribute("isShow");
            req.getSession().removeAttribute("positionLevel");
            req.getSession().removeAttribute("managerComrateId");
        }else{
            Object managerComrateInfoId = req.getSession().getAttribute("managerComrateId");
            model.put("managerComrateId", managerComrateInfoId);
        }
        req.getSession().removeAttribute("isShow");
        Map<String, Object> paramMap = new HashMap<>();


        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevelList", positionLevel);

        return new ModelAndView("/positionLevel/add",model);
    }

    /*
    * 新增或修改数据
    */
    @RequestMapping(value = "/positionLevelUpdate")
     public String positionLevelUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_POSITION_LEVEL_SAVE, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_POSITION_LEVEL_UPDATE, null, req, rsp);
        }

    }

    @RequestMapping(value = "/positionLevelDelete")
    public String positionLevelDelete(HttpServletRequest req, HttpServletResponse rsp) {

        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_POSITION_LEVEL_DELETE, null, req, rsp);

    }

    @RequestMapping(value = "/choiceManagerComrateInfo")
    public ModelAndView choiceManagerComrateInfo(HttpServletRequest req, HttpServletResponse rsp) {

        PositionLevelDto positionLevel = new PositionLevelDto();
        String id = req.getParameter("id");
        String levelCode = req.getParameter("levelCode");
        String levelDesc = req.getParameter("levelDesc");
        String parentId = req.getParameter("parentId");
        String baseWages = req.getParameter("baseWages");
        String evaWages = req.getParameter("evaWages");

        if (id != null) {
            positionLevel.setId(Long.parseLong(id));
        }
        if (levelCode != null) {
            positionLevel.setLevelCode(levelCode);
        }
        if (levelDesc != null) {
            positionLevel.setLevelDesc(levelDesc);
        }
        if (!StringUtils.isEmpty(parentId)) {
            positionLevel.setParentId(Long.parseLong(parentId));
        }
        if(baseWages != null && baseWages!=""){
            positionLevel.setBaseWages(Double.parseDouble(baseWages));
        }
        if(evaWages != null&& evaWages!=""){
            positionLevel.setEvaWages(Double.parseDouble(evaWages));
        }
        req.getSession().setAttribute("positionLevel",positionLevel);
        req.getSession().setAttribute("isShow",0);

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
        return new ModelAndView("/positionLevel/managerComrateInfoList",model);
    }

    @RequestMapping(value = "/queryByManagerComrateInfoId")
    public String queryByManagerComrateInfoId(HttpServletRequest req, HttpServletResponse rsp) {
        String managerComrateInfoId=req.getParameter("managerComrateInfoId");

        req.getSession().setAttribute("managerComrateId",managerComrateInfoId);
        return WebHelper.outputJson(null, rsp);
    }
}
