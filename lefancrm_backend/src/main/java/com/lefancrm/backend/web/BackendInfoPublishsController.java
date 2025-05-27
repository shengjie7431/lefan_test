package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.InfoPublishsDto;
import com.lefancrm.backend.dto.InfoPublishsForumFileDto;
import com.lefancrm.backend.dto.InfoSafeCompanyDto;
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
 * Created by lixianfeng on 2018/11/22.13点49分
 */
@Controller
@RequestMapping(value = "/info/publishs/")
public class BackendInfoPublishsController  extends BackendBaseController {

    @RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        return new ModelAndView("/info/publishs/add",model);
    }

    @RequestMapping(value = "/upd")
    public ModelAndView upd(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<InfoPublishsDto>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_PUBLISHS_GET, null, req);
        InfoPublishsDto dto = (InfoPublishsDto)apiFinalResponse.getResults();
        model.put("dto",dto);
        return new ModelAndView("/info/publishs/upd",model);
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_INFO_PUBLISHS_SAVE, null, req, rsp);
    }

    @RequestMapping(value = "/saveForum")
    public String saveForum(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_INFO_PUBLISHS_SAVE_FORUM, null, req, rsp);
    }


    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String userName = req.getParameter("userName");
        String userTel = req.getParameter("userTel");
        String userCardid = req.getParameter("userCardid");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<InfoPublishsDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_PUBLISHS_LIST, null, req);
        model.put("list",apiFinalResponse.getResults());
        model.put("userName",userName);
        model.put("userTel",userTel);
        model.put("userCardid",userCardid);
        model.put("menuType",req.getParameter("menuType"));
        return new ModelAndView("/info/publishs/list",model);
    }

    @RequestMapping(value = "/info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("menuType",req.getParameter("menuType"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<InfoPublishsDto>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_PUBLISHS_INFO, null, req);
        InfoPublishsDto dto = (InfoPublishsDto)apiFinalResponse.getResults();
        model.put("dto",dto);
        return new ModelAndView("/info/publishs/info",model);
    }

    /**
     *
     * 轮播查看图片
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("fileShow")
    public String fileShow(HttpServletRequest req,HttpServletResponse rsp){

        Map<String,Object> paramMap = new HashMap<>();

        TypeToken token = new TypeToken<ApiFinalResponse<List<InfoPublishsForumFileDto>>>(){};
        ApiFinalResponse response = this.callApi(token, BackendApiMethodEnum.BACKEND_INFO_PUBLISHS_FORUM_FILE_INFO,paramMap,req);
        List<InfoPublishsForumFileDto> dtos = (List<InfoPublishsForumFileDto>)(response == null ? null : response.getResults());
        req.setAttribute("file",dtos);

        String index = req.getParameter("index");
        req.setAttribute("id",index);
        return "/info/publishs/show";
    }

    @RequestMapping("/selectCompany")
    public ModelAndView selectCompany(HttpServletRequest req,HttpServletResponse rsp){
        String choose = req.getParameter("choose");
        Map model = new HashMap();
        String safeName = req.getParameter("safeName");
        String safeTel = req.getParameter("safeTel");
        String safeUser = req.getParameter("safeUser");
        model.put("safeName", safeName);
        model.put("safeTel", safeTel);
        model.put("safeUser", safeUser);
        model.put("publishsId",req.getParameter("id"));
        model.put("choose",choose);
        Map<String, Object> appendMap = new HashMap<String, Object>();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<InfoSafeCompanyDto>>>() {};
        if ("del".equals(choose)){
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_SAFE_COMPANY_LISTED, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
        }else{
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_SAFE_COMPANY_LISTING, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
        }
        return new ModelAndView("/info/publishs/selectCompany",model);
    }

    @RequestMapping(value = "/selectCompanyOK")
    public String selectCompanyOK(HttpServletRequest req, HttpServletResponse rsp, String safeCompanys) {
        Map param = new HashMap();
        param.put("publishsId",req.getParameter("publishsId"));
        param.put("safeCompanys",safeCompanys);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_INFO_PUBLISHS_SELECT_COMPANYOK, param, req, rsp);
    }

}
