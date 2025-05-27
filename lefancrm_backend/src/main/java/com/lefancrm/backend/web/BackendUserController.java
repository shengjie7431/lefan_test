package com.lefancrm.backend.web;

import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 * 
 * @author Daniel
 */
@Controller
@RequestMapping("/user")
public class BackendUserController extends BackendBaseController {


    @RequestMapping(value = "/setOrgAdmin")
    public String setOrgAdmin(HttpServletRequest req, HttpServletResponse rsp, String roles){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bsType",roles);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SET_ORG_ADMIN, paramMap, req, rsp);
    }
}
