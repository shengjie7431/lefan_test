package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.util.Util;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.DateTimeUtil;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/4/26.
 */

@Controller
@RequestMapping(value = "/user/role")
public class BackendUserRoleController extends BackendBaseController {

    @RequestMapping(value = "/treeData")
    public String treeData(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_USER_ROLE_LIST, null, req);
        Type type = new TypeToken<ApiFinalResponse<List<OrgTreeData>>>() {
        }.getType();
        ApiFinalResponse<List<OrgTreeData>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
            List<OrgTreeData> tree = apiRsp.getResults();
            retJson = JsonUtil.objectToJson(tree);
        }
        return WebHelper.outputJson(retJson, rsp);
    }

    @RequestMapping(value = "/treeDataUserRole")
    public String treeDataOrg(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_USER_ROLE_TREE_DATA, null, req);
        Type type = new TypeToken<ApiFinalResponse<OrgInfo>>() {
        }.getType();
        ApiFinalResponse<OrgInfo> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
            OrgInfo tree = apiRsp.getResults();
            retJson = JsonUtil.objectToJson(tree);
        }
        return WebHelper.outputJson(retJson, rsp);
    }


    @RequestMapping(value = "/list")
    public String list(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        Integer orgType = Integer.parseInt(req.getParameter("orgType"));
        String isRole = this.callApi(BackendApiMethodEnum.BACKEND_IS_ROLE, null, req);
        Type isRoleType = new TypeToken<ApiFinalResponse<Boolean>>() {
        }.getType();
        ApiFinalResponse<Boolean> isRoleApiRsp = JsonUtil.jsonToObject(isRole, isRoleType);
        if(!Boolean.parseBoolean(String.valueOf(isRoleApiRsp.getResults()))) {
            req.setAttribute("isRole",0);
        }else{
            req.setAttribute("isRole",1);
        }
       // return "/org/userRoleList";
        if(orgType == 1){
            return "/user/role/userRoleList";
        }else if(orgType == 2){
            return "/user/role/userRoleListTo";
        }
        return null;
    }
    @RequestMapping(value = "/toAddUser")
    public String toAddUser(HttpServletRequest req) throws  Exception{
        req.setAttribute("img",req.getParameter("img"));
        req.setAttribute("userName",req.getParameter("userName"));
        req.setAttribute("userType",req.getParameter("userType"));
        req.setAttribute("nickName",req.getParameter("nickName"));
        req.setAttribute("userTel",req.getParameter("userTel"));
        req.setAttribute("email",req.getParameter("email"));
        req.setAttribute("userState",req.getParameter("userState"));
        req.setAttribute("sex",req.getParameter("sex"));
        req.setAttribute("userAddress",req.getParameter("userAddress"));
        return "";
    }

    /**
     * 显示树形结构
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/treeList")
    public String treeList(HttpServletRequest req) throws  Exception{
        //用于操作类型判断
        Integer type = Integer.parseInt(req.getParameter("type"));
        req.setAttribute("type",req.getParameter("type"));
        switch (type){
            case 1 :
                UserInfoDto userInfoDto = new UserInfoDto();
                userInfoDto.setImg(req.getParameter("img"));
                userInfoDto.setUserName(req.getParameter("userName"));
                userInfoDto.setUserType(Integer.parseInt(req.getParameter("userType")));
                userInfoDto.setNickName(req.getParameter("nickName"));
                userInfoDto.setUserTel(req.getParameter("userTel"));
                userInfoDto.setEmail(req.getParameter("email"));
                userInfoDto.setUserState(Integer.parseInt(req.getParameter("userState")));
                userInfoDto.setSex(Integer.parseInt(req.getParameter("sex")));
                userInfoDto.setUserAddress(req.getParameter("userAddress"));
                req.getSession().setAttribute("newUser",userInfoDto);
                req.getSession().setAttribute("isShow",0);
                return "/org/userOrgList";
            case 2:
                req.setAttribute("userId", req.getParameter("userId"));
                return "/user/insUserAddressAdd";
            case 3:
                return "/area/area";
        }
        return "";
    }

//    @RequestMapping(value = "/treeDataPub")
//    public String treeDataPub(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
//        //用于操作类型判断(因为当前树的数据结构都是一样，所以都使用OrgTreeData)
//        Integer type = Integer.parseInt(req.getParameter("type"));
//        String retJson = "";
//        String json = "";
//        switch (type){
//            case 1 :
//                Map<String,Object> paramMap = new HashMap<>();
//                paramMap.put("orgType",1);
//                json =  this.callApi(BackendApiMethodEnum.BACKEND_ORG_LIST, paramMap, req);
//                break;
//            case 2 :
//                json =  this.callApi(BackendApiMethodEnum.BACKEND_SELECT_AREA, null, req);
//            default:
//               break;
//        }
//        Type orgType = new TypeToken<ApiFinalResponse<List<OrgTreeData>>>() {
//        }.getType();
//        ApiFinalResponse<List<OrgTreeData>> apiRsp = JsonUtil.jsonToObject(json, orgType);
//        if (apiRsp != null) {
//            List<OrgTreeData> tree = apiRsp.getResults();
//            retJson = JsonUtil.objectToJson(tree);
//        }
//        return WebHelper.outputJson(retJson, rsp);
//    }
//
//    @RequestMapping(value = "/toAdd")
//    public String toAdd(HttpServletRequest req, HttpServletResponse rsp) {
//        Map<String, Object> appendMap = new HashMap<String, Object>();
//        appendMap.put("parentId",0);
//        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
//        Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
//        }.getType();
//
//        appendMap.put("all","yes");
//        ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
//        String jsonOrg = this.callApi(BackendApiMethodEnum.BACKEND_ORG_LIST_TO, appendMap, req);
//        Type typeOrg = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {
//        }.getType();
//        ApiFinalResponse<List<OrgInfoDto>> apiRspOrg = JsonUtil.jsonToObject(jsonOrg, typeOrg);
//        req.setAttribute("apiRsp", apiRsp);
//        req.setAttribute("apiRspOrg",apiRspOrg);
//        return "/org/orgAdd";
//    }
//
    @RequestMapping(value = "/selectArea")
    public String selectArea(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, null, req, rsp);
    }
//
//    @RequestMapping(value = "/add")
//    public String add(HttpServletRequest req, HttpServletResponse rsp, String img) {
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("img",img);
//       return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ORG_ADD, null, req, rsp);
//    }
//
//    @RequestMapping(value = "/toEdit")
//    public String toEdit(HttpServletRequest req, HttpServletResponse rsp) {
//        Map<String, Object> appendMap = new HashMap<String, Object>();
//        appendMap.put("parentId",0);
//        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
//        Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
//        }.getType();
//        ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
//        String id=req.getParameter("id");
//        Map param = new HashMap();
//        param.put("id",id);
//        TypeToken typeToken = new TypeToken<ApiFinalResponse<OrgInfo>>() {};
//        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_ORG_INFO, param, req);
//        req.setAttribute("apiRsp", apiRsp);
//        req.setAttribute("org", apiFinalResponse.getResults());
//        return "/org/orgEdit";
//    }
//
//    @RequestMapping(value = "/delFile")
//    public String delFile(HttpServletRequest req, HttpServletResponse rsp) {
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ORG_DEL_FILE, null, req, rsp);
//    }
//
    @RequestMapping(value = "/queryOrgById")
    public String queryOrgById(HttpServletRequest req, HttpServletResponse rsp) {
        String id=req.getParameter("id");
        Map param = new HashMap();
        param.put("id",id);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_USER_ROLE_INFO, param, req);
        Type type = new TypeToken<ApiFinalResponse<OrgInfo>>() {
        }.getType();
        ApiFinalResponse<OrgInfo> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp != null) {
            OrgInfo tree = apiRsp.getResults();
            retJson = JsonUtil.objectToJson(tree);
        }
        req.getSession().setAttribute("userOrgId",id);
        return WebHelper.outputJson(retJson, rsp);
    }
//
//    @RequestMapping(value = "/edit")
//    public String edit(HttpServletRequest req, HttpServletResponse rsp, String img) {
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("img",img);
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ORG_EDIT, null, req, rsp);
//    }
//
//    @RequestMapping(value = "/editState")
//    public String editState(HttpServletRequest req, HttpServletResponse rsp) {
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ORG_EDIT_STATE, null, req, rsp);
//    }
//
//    @RequestMapping(value = "/queryCase")
//    public String queryCase(HttpServletRequest req, HttpServletResponse rsp) {
//        String id=req.getParameter("id");
//        Map param = new HashMap();
//        param.put("orgId",id);
//        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_CASE, param, req);
//        Type type = new TypeToken<ApiFinalResponse<List<CaseCenterInfoDto>>>() {
//        }.getType();
//        ApiFinalResponse<List<CaseCenterInfoDto>> apiRsp = JsonUtil.jsonToObject(json, type);
//        req.setAttribute("caseCenterInfoList", apiRsp.getResults());
//        req.setAttribute("apiRsp", apiRsp);
//        req.setAttribute("id",id);
//        req.setAttribute("caseNo",req.getParameter("caseNo"));
//        req.setAttribute("caseName",req.getParameter("caseName"));
//        req.setAttribute("caseTel",req.getParameter("caseTel"));
//        req.setAttribute("caseTitle",req.getParameter("caseTitle"));
//        req.setAttribute("orgUserName",req.getParameter("orgUserName"));
//        req.setAttribute("caseState",req.getParameter("caseState"));
//        req.setAttribute("type",req.getParameter("type"));
//        return "/org/orgCaseCenterInfoList";
//    }
    @RequestMapping(value = "/queryUser")
    public String queryUser(HttpServletRequest req, HttpServletResponse rsp) {
        UserInfo ui = (UserInfo) req.getSession().getAttribute("adminDto");
        String id=req.getParameter("id");
        String userName = req.getParameter("userName");
        String nickName = req.getParameter("nickName");
        String userTel = req.getParameter("userTel");
        Map param = new HashMap();
        param.put("orgId",id);
        String isRole = this.callApi(BackendApiMethodEnum.BACKEND_IS_ROLE, null, req);
        Type isRoleType = new TypeToken<ApiFinalResponse<Boolean>>() {
        }.getType();
        ApiFinalResponse<Boolean> isRoleApiRsp = JsonUtil.jsonToObject(isRole, isRoleType);
//        if(!Boolean.parseBoolean(String.valueOf(isRoleApiRsp.getResults()))) {
//            String orgIsRole = this.callApi(BackendApiMethodEnum.BACKEND_IS_ORG_ROLE, param, req);
//            Type isOrgRoleType = new TypeToken<ApiFinalResponse<Integer>>() {
//            }.getType();
//            ApiFinalResponse<Integer> isOrgRoleApi = JsonUtil.jsonToObject(orgIsRole, isOrgRoleType);
//            Integer isOrgRole = isOrgRoleApi.getResults();
//            if(isOrgRole == 1){
//                req.setAttribute("isRole",1);
//            }else{
//                req.setAttribute("isRole",0);
//            }
//        }else{
//            req.setAttribute("isRole",1);
//        }
        String page = req.getParameter("page");
        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        String json = this.callApi(BackendApiMethodEnum.BACKEND_USER_QUERY_USER, param, req);
        Type type = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {
        }.getType();
        ApiFinalResponse<List<UserInfo>> apiRsp = JsonUtil.jsonToObject(json, type);

        param.put("all","yes");
        param.put("userId",ui.getUserId());
        String jsonBs = this.callApi(BackendApiMethodEnum.USER_BUSINESS_ROLE_LIST, param, req);
        Type typeBs = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {
        }.getType();
        ApiFinalResponse<List<BusinessRoleDto>> apiRspBs = JsonUtil.jsonToObject(jsonBs, typeBs);
        req.setAttribute("bsInfo", apiRspBs.getResults());
        req.setAttribute("users", apiRsp.getResults());
        req.setAttribute("apiRsp", apiRsp);
        req.setAttribute("id",id);
        req.setAttribute("page",page);
        req.setAttribute("userName",userName);
        req.setAttribute("nickName",nickName);
        req.setAttribute("userTel",userTel);
        req.setAttribute("orgType",req.getParameter("orgType"));
        return "/user/role/orgUserList";
    }
//    @RequestMapping(value = "/toDisUser")
//    public String toDisUser(HttpServletRequest req, HttpServletResponse rsp) {
//        String id=req.getParameter("id");
//        String page = req.getParameter("page");
//        if(StringUtils.isEmpty(page)){
//            page = "1";
//        }
//        String userName = req.getParameter("userName");
//        String nickName = req.getParameter("nickName");
//        String userTel = req.getParameter("userTel");
//        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_DIS_USER, null, req);
//        Type type = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {
//        }.getType();
//        ApiFinalResponse<List<UserInfo>> apiRsp = JsonUtil.jsonToObject(json, type);
//        String jsonBs = this.callApi(BackendApiMethodEnum.BACKEND_BUSINESS_ROLE_LIST, null, req);
//        Type typeBs = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {
//        }.getType();
//        ApiFinalResponse<List<BusinessRoleDto>> apiRspBs = JsonUtil.jsonToObject(jsonBs, typeBs);
//        req.setAttribute("bsInfo",apiRspBs.getResults());
//        req.setAttribute("apiRsp", apiRsp);
//        req.setAttribute("orgId", id);
//        req.setAttribute("page",page);
//        req.setAttribute("userName",userName);
//        req.setAttribute("nickName",nickName);
//        req.setAttribute("userTel",userTel);
//        return "/org/orgDisUser";
//    }
//
//    @RequestMapping(value = "/disUser")
//    public String disUser(HttpServletRequest req, HttpServletResponse rsp, String users) {
//        Map param = new HashMap();
//        param.put("orgId",req.getParameter("orgId"));
//        param.put("users",users);
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ORG_DIS_USER, param, req, rsp);
//    }
//
    @RequestMapping(value = "/disDsRole")
    public String disDsRole(HttpServletRequest req) {
        UserInfo ui = (UserInfo) req.getSession().getAttribute("adminDto");
        Long userId = ui.getUserId();
        Map<String,Object> map = new HashMap<>();
        map.put("all" ,1);
        map.put("userId",userId);
        String json = this.callApi(BackendApiMethodEnum.USER_BUSINESS_ROLE_LIST, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {
        }.getType();
        ApiFinalResponse<List<BusinessRoleDto>> apiRsp = JsonUtil.jsonToObject(json, type);

        String jsonRole = this.callApi(BackendApiMethodEnum.BACKEND_USER_ROLE, null, req);
        Type typeRole = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {
        }.getType();
        ApiFinalResponse<List<BusUserRoleDto>> apiRspRole = JsonUtil.jsonToObject(jsonRole, typeRole);
        req.setAttribute("userId",req.getParameter("userId"));
        req.setAttribute("bsInfo",apiRsp.getResults());
        req.setAttribute("roles",apiRspRole.getResults());
        return "/user/role/disDsRole";
    }
//
//
//    @RequestMapping(value = "/businessRoleList")
//    public String businessRoleList(HttpServletRequest req,Model model) throws Exception {
//        String page = req.getParameter("page");
//        String roleName = Util.decode(req.getParameter("roleName"));
//        if(req.getParameter("all") == null && StringUtils.isEmpty(page)){
//            page = "1";
//        }
//        String json = this.callApi(BackendApiMethodEnum.BACKEND_BUSINESS_ROLE_LIST, null, req);
//        Type type = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {
//        }.getType();
//        ApiFinalResponse<List<BusinessRoleDto>> apiRsp = JsonUtil.jsonToObject(json, type);
//        for(BusinessRoleDto b : apiRsp.getResults()){
//            b.setCreateTimes(DateTimeUtil.formatDateTime(b.getCreateTime(), "yyy-MM-dd"));
//        }
//        if(req.getParameter("all") == null){
//            model.addAttribute("roleName",roleName);
//            model.addAttribute("page",page);
//        }
//        model.addAttribute("apiRsp", apiRsp);
//        return "/org/businessRoleList";
//    }
//
//    @RequestMapping(value = "/businessRoleAddTo")
//    public String businessRoleAddTo(HttpServletRequest req) throws Exception {
//        return "/org/businessRoleAdd";
//    }
//
//    @RequestMapping(value = "/businessRoleAdd")
//    public String businessRoleAdd(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BUSINESS_ROLE_ADD, null, req, rsp);
//    }
//
//    @RequestMapping(value = "/businessRoleUpdateTo")
//    public String businessRoleUpdateTo(HttpServletRequest req) throws Exception {
//        TypeToken typeToken = new TypeToken<ApiFinalResponse<BusinessRoleDto>>() {};
//        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BUSINESS_ROLE_UPDATE_TO, null, req);
//        req.setAttribute("bsInfo", apiFinalResponse.getResults());
//        return "/org/businessRoleEdit";
//    }
//
//    @RequestMapping(value = "/businessRoleUpdate")
//    public String businessRoleUpdate(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BUSINESS_ROLE_UPDATE, null, req, rsp);
//    }
//
//    @RequestMapping(value = "/businessRoleDel")
//    public String businessRoleDel(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BUSINESS_ROLE_DEL, null, req, rsp);
//    }
//
//    @RequestMapping(value = "/delOrgUser")
//    public String delOrgUser(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_DEL_ORG_USER, null, req, rsp);
//    }

    /**
     *
     *
     */
    @RequestMapping(value = "/selectByAreaId")
    public String selectByAreaId(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_AREA_BY_ID, null, req, rsp);
    }
}
