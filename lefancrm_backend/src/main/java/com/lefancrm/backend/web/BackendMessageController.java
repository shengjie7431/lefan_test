package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.CrmMessageInfoDto;
import com.lefancrm.backend.dto.OrgInfo;
import com.lefancrm.backend.dto.UserInfo;
import com.lefancrm.backend.dto.UserInfoDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping(value = "/message")
public class BackendMessageController extends BackendBaseController {
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String sendTime = req.getParameter("sendTime");
        /*req.getInt(*/
        String isRead = req.getParameter("isRead");
        String deleteFlag = req.getParameter("deleteFlag");
        String userId= req.getParameter("id");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CrmMessageInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CRM_MESSAGE_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("sendTime",sendTime);
        model.put("isRead",isRead);
        model.put("deleteFlag",deleteFlag);

//        List<CrmMessageInfoDto> results = (List<CrmMessageInfoDto>)apiFinalResponse.getResults();
//        for (CrmMessageInfoDto c : results){
//            System.out.println("*********************"+c);
//        }
        return new ModelAndView("/message/list",model);
    }

    @RequestMapping(value = "/toAddMessage")
    public ModelAndView toAddMessage(HttpServletRequest req, HttpServletResponse rsp){
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CRM_RECEIVE, null, req);
        Type type = new TypeToken<ApiFinalResponse<List<UserInfoDto>>>() {
        }.getType();
        ApiFinalResponse<List<UserInfoDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        Map model = new HashMap();
        model.put("results",apiRsp.getResults());
        return new ModelAndView("/message/addMessage",model);
    }

    @RequestMapping(value = "/addMessage")
    public String addMessage(HttpServletRequest req, HttpServletResponse rsp){
        UserInfo userInfo = (UserInfo)req.getSession().getAttribute(SESSION_ADMIN_DTO);
//        String sendState = req.getParameter("sendState");
//        String content = req.getParameter("content");
//        String sendTime = req.getParameter("sendTime");
        String[] userIds = req.getParameterValues("userIds");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId",userInfo.getUserId());
        paramMap.put("userIds",userIds);

        this.callApiAndOutput(BackendApiMethodEnum.BACKEND_MESSAGE_INSERT, paramMap, req, rsp);

        return "redirect:/message/list";
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

}
