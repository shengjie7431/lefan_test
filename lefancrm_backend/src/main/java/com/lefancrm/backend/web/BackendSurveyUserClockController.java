package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyFranchiseeDto;
import com.lefancrm.backend.dto.SurveyTaskInfoDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lixianfeng
 * @date 2018/12/24
 */
@Controller
@RequestMapping(value = "/survey/userClock/")
public class BackendSurveyUserClockController extends BackendBaseController{

    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        Map params = new HashMap();
        String menuCode=req.getParameter("menuCode");
        if("userClock".equals(menuCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.GET_DATA_SURVEY_USER_ORG, null, req);
            List<SurveyFranchiseeDto> surveyFranchiseeDtoList=(List<SurveyFranchiseeDto>)apiFinalResponse.getResults();
            model.put("surveyFranchiseeDtoList",surveyFranchiseeDtoList);
            params.put("surveyFranchiseeDtoJson", JsonUtil.objectToJson(surveyFranchiseeDtoList));
            model.put("params",params);
            return new ModelAndView("/survey/clock/list",model);
        }
        return null;
    }

    @RequestMapping(value = "details")
    public String details(HttpServletRequest req, HttpServletResponse rsp){
        String menuType=req.getParameter("menuType");
        if("dropDownDetails".equals(menuType)){
            return this.callApiAndOutput(BackendApiMethodEnum.GET_DATA_SURVEY_USER_GETDETAIL, null, req, rsp);
        }else if("clockDetails".equals(menuType)){
            return this.callApiAndOutput(BackendApiMethodEnum.GET_DATA_SURVEY_USER_ORG_GETDETAIL, null, req, rsp);
        }
        return null;
    }
}
