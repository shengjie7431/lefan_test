package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyQaDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangwei on 2019-03-14.
 * 提现问题
 */
@Controller
@RequestMapping(value = "/surveyCashInfoRecord")
public class BackendSurveyCashInfoRecordController extends BackendBaseController{


    /**
    *   提现状态
     */
    @RequestMapping(value = "/cashInfoState")
    public String cashInfoState(HttpServletRequest req, HttpServletResponse rsp) {
        Map param = new HashMap();
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_CASH_INFO_RECORD_STATE, param, req);
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }
}
