package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.FrontMenuDto;
import com.lefancrm.backend.dto.SurveyMessageDto;
import com.lefancrm.backend.dto.SurveyPayInfoDto;
import com.lefancrm.backend.dto.UserInfo;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BackendLoginController extends BackendBaseController {

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest req) {
		if (this.isLogon(req)) {
			return "redirect:/";
		}
		req.setAttribute("userTelphone", req.getParameter("userTelphone"));
		return "/login";
	}

	@RequestMapping(value = "/doLogin")
	public String doLogin(HttpServletRequest req, HttpServletResponse rsp, RedirectAttributes redirectAttributes) {
		if (this.isLogon(req)) {
			return "redirect:/";
		}
		Map<String, Object> appendMap = new HashMap<String, Object>();
		appendMap.put("login_ip", WebHelper.getRequestIp(req));
		String json = this.callApi(BackendApiMethodEnum.BACKEND_ADMIN_LOGIN, appendMap, req);
		Type type = new TypeToken<ApiFinalResponse<UserInfo>>() {
		}.getType();
		ApiFinalResponse<UserInfo> apiRsp = JsonUtil.jsonToObject(json, type);
		String retMsg = "登录失败";
		if (apiRsp != null) {
			if (apiRsp.getIsSuccess() == Boolean.TRUE) {
                UserInfo authUser = apiRsp.getResults();
				WebUtils.setSessionAttribute(req, SESSION_ADMIN_DTO, authUser);
				List<FrontMenuDto> menuList = null;
				// root用户拥有超级权限
				/*if ("root".equalsIgnoreCase(authUser.getUsername())) {
					menuList = _getUserMenuList(null);
				} else {*/
					menuList = _getUserMenuList(authUser.getUserId());
				/*}*/
				WebUtils.setSessionAttribute(req, SESSION_MENULIST_DTO, menuList);
				Map<String, Integer> menuCodeMap = new HashMap<String, Integer>();
				for (FrontMenuDto dto : menuList) {
					menuCodeMap.put(dto.getMenuCode(), dto.getId());
				}
				WebUtils.setSessionAttribute(req, SESSION_MENU_MAP, menuCodeMap);

				Map<String,Object> messageMap =  null;
				messageMap = selectMessage(authUser.getUserId());
                WebUtils.setSessionAttribute(req, SESSION_MESSAGE_MAP, messageMap);

				return "redirect:/";
			} else {
				retMsg = apiRsp.getMsg();
			}
		}
		redirectAttributes.addFlashAttribute("msg", retMsg);
		redirectAttributes.addFlashAttribute("userTelphone", req.getParameter("userTelphone"));
		return "redirect:/login";
	}

    public List<FrontMenuDto> _getUserMenuList(Long userId) {
		Map<String, Object> appendMap = new HashMap<String, Object>();
		appendMap.put("userId", userId);
		String json = this.callApi(BackendApiMethodEnum.BACKEND_SYSMENU_LISTBYUSERID, appendMap, null);
		Type type = new TypeToken<ApiFinalResponse<List<FrontMenuDto>>>() {
		}.getType();
		ApiFinalResponse<List<FrontMenuDto>> apiRsp = JsonUtil.jsonToObject(json, type);
		if (apiRsp != null) {
			return apiRsp.getResults();
		}
		return null;
	}

    private Map<String,Object> selectMessage(Long userId) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("messageType", 4);//消息类型(1:系统消息，2：客服消息，3：其他消息)
        appendMap.put("isRead", 0);//标识未读
        appendMap.put("surveyCode", "message");
        appendMap.put("userId", userId);
        appendMap.put("enter", "login");//代表入口
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyMessageDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("surveyMessagesResponse",apiFinalResponse);
		return map;
	}


    /**
     * 垫付案件-付款管理-发送短信给被保险人-短信中的图片路径
     */
    @RequestMapping(value = "/im")
    public ModelAndView im(HttpServletRequest req) {
        Map<String,Object> model =  new HashMap<String,Object>();
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("shortUrl",  req.getParameter("ag"));//测试
        appendMap.put("menuCode","imageInfo");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyPayInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_PAY_INFO_INFO, appendMap, req);
        SurveyPayInfoDto surveyPayInfo = (SurveyPayInfoDto) apiFinalResponse.getResults();
        if(surveyPayInfo!=null){
            model.put("payImgUrl",surveyPayInfo.getPayImgUrl());
        }else{
            model.put("payImgUrl",null);
        }
        return new ModelAndView("/image",model);
    }
}
