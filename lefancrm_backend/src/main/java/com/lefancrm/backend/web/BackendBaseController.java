package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.BusUserRoleDto;
import com.lefancrm.backend.dto.UserInfo;
import com.lefancrm.base.constants.Constant;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.ApiServerEnum;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.HttpClientUtil;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.SuperDispatcherServlet;
import com.lefancrm.base.web.WebHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackendBaseController {
	protected static final String SESSION_ADMIN_DTO = "adminDto";

	protected static final String SESSION_MENULIST_DTO = "menuListDto";

	protected static final String SESSION_MENU_MAP = "menuMap";

    protected static final String SESSION_MESSAGE_MAP = "messageMap";

	@Value("${api.server}")
	private String apiServer;

	@Value("${api.key}")
	private String apiKey;

	@Value("${api.secert}")
	private String apiSecert;

	protected void setOperator(Map<String, Object> appendMap, HttpServletRequest req) {
		/*SysUserDto adminSession = this.getSessionAdmin(req);*/
        UserInfo adminSession = this.getSessionAdmin(req);
		if (adminSession != null) {
			/*appendMap.put("operatorId", adminSession.getId());
			appendMap.put("operatorName", adminSession.getUsername());*/
            appendMap.put("operatorId", adminSession.getUserId());
            appendMap.put("operatorName", adminSession.getUserTel());
		}
	}

    /**
     * 2018年5月3日13:53:41  李贤丰 增加   当前用户id name 放到 map里边, 便于api 获取
     * @param appendMap
     * @param req
     */
    protected void setCurrentUser(Map<String, Object> appendMap, HttpServletRequest req){
        UserInfo adminSession = this.getSessionAdmin(req);
        if (adminSession != null){
            appendMap.put("currentUserId",adminSession.getUserId());
            appendMap.put("currentUserDisplayName",adminSession.getUserName());
        }
    }

	protected boolean isLogon(HttpServletRequest req) {
		if (this.getSessionAdmin(req) != null) {
			return true;
		}
		return false;
	}

	protected Long getSessionAdminId(HttpServletRequest req) {
		/*SysUserDto adminDto = this.getSessionAdmin(req);*/
        UserInfo adminDto = this.getSessionAdmin(req);
		if (adminDto != null) {
			/*return adminDto.getId();*/
            return adminDto.getUserId();
		}
		return null;
	}

	protected UserInfo getSessionAdmin(HttpServletRequest req) {
		/*SysUserDto adminDto = (SysUserDto) WebUtils.getSessionAttribute(req, SESSION_ADMIN_DTO);*/
        UserInfo adminDto = (UserInfo) WebUtils.getSessionAttribute(req, SESSION_ADMIN_DTO);
		return adminDto;
	}

	protected String callApi(BackendApiMethodEnum apiMethodEnum, Map<String, Object> appendMap, HttpServletRequest req) {
		return this._callApi(apiMethodEnum.getCode(), apiMethodEnum.getApiServer(), appendMap, req);
	}

	protected ApiFinalResponse callApi(TypeToken type,BackendApiMethodEnum apiMethodEnum, Map<String, Object> appendMap, HttpServletRequest req) {
		String json =  this._callApi(apiMethodEnum.getCode(), apiMethodEnum.getApiServer(), appendMap, req);
		ApiFinalResponse apiRsp = JsonUtil.jsonToObject(json, type.getType());
		return apiRsp;
	}

	private String _callApi(String apiCode, ApiServerEnum apiServer, Map<String, Object> appendMap, HttpServletRequest req) {
		Map<String, Object> reqParamMap = null;
		if (req != null) {
			reqParamMap = WebHelper.buildParamMapFromRequest(req);
			reqParamMap.put(Constant.REQUEST_IP, WebHelper.getRequestIp(req));
			this.setOperator(reqParamMap, req);
            this.setCurrentUser(reqParamMap,req);
		} else {
			reqParamMap = new HashMap<String, Object>();
		}
		if (appendMap != null && appendMap.size() > 0) {
			reqParamMap.putAll(appendMap);
		}
		reqParamMap.put(Constant.API_CODE, apiCode);
		reqParamMap.put(Constant.API_KEY, apiKey);
		reqParamMap.put(Constant.API_SIGN, Constant.INNER_API_SIGN);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String dataJson = JsonUtil.objectToJson(reqParamMap, Map.class);
		paramMap.put(Constant.DATA, dataJson);

		HttpClientUtil clientUtil = new HttpClientUtil();
		Map<String, String> head = new HashMap<String, String>();
		int port = SuperDispatcherServlet.getlocalPort();
		head.put("referer", "backend/" + port);
		clientUtil.setHttpSetting(head);
		clientUtil.setTimeOut(60000);
		String retJson = clientUtil.doHttpPost(MessageFormat.format(this.apiServer, apiCode, Constant.INNER_API_SIGN), paramMap);
		return retJson;
	}

	/**
	 * 调用API并输出json
	 * 
	 * @param apiMethodEnum
	 *            API FUNCTION CODE
	 * @param appendMap
	 *            附加参数
	 * @param req
	 *            http请求
	 * @param rsp
	 *            http响应
	 * @return
	 * @author Daniel
	 */
	protected String callApiAndOutput(BackendApiMethodEnum apiMethodEnum, Map<String, Object> appendMap, HttpServletRequest req, HttpServletResponse rsp) {
		String retJson = this.callApi(apiMethodEnum, appendMap, req);
		return WebHelper.outputJson(retJson, rsp);
	}

	protected String outputApiMsg(BackendApiMethodEnum apiMethodEnum, boolean isSuccess, String msg, HttpServletResponse rsp) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("isSuccess", isSuccess);
		msgMap.put("msg", msg);
		String retJson = JsonUtil.objectToJson(msgMap, new TypeToken<Map<String, Object>>() {
		}.getType());
		return WebHelper.outputJson(retJson, rsp);
	}

    protected String output(String str, HttpServletResponse response, String contentType) {
        response.reset();
        response.setContentType(contentType);
        try {
            PrintWriter out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 输出JSON
     *
     * @param json
     * @param response
     * @return
     * @author daniel
     */
    protected String outputJson(String json, HttpServletResponse response) {
        return this.output(json, response, "text/html;charset=UTF-8");
    }

	protected Boolean isRoleUser(List<BusUserRoleDto> busUserRoles, Long roleId){
		for (BusUserRoleDto busUserRole : busUserRoles){
			if (busUserRole.getRoleId().equals(roleId)){
				return true;
			}
		}
		return false;
	}
}
