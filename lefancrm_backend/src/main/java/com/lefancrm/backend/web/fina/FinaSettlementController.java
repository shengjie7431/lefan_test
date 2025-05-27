package com.lefancrm.backend.web.fina;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.fina.FinaSettlementInfo;
import com.lefancrm.backend.web.BackendBaseController;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/fina/settlement/")
public class FinaSettlementController extends BackendBaseController {
    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("menuCode",req.getParameter("menuCode"));
        return new ModelAndView("/fina/settlement/list",model);
    }
    @RequestMapping(value = "operateView")
    public ModelAndView operateView(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String btnCode = req.getParameter("btnCode");
        model.put("btnCode",btnCode);
        model.put("settlementId",req.getParameter("settlementId"));
        if ("repay-app".equals(btnCode)){
            return new ModelAndView("/fina/settlement/repayApp",model);
        }else if ("files".equals(btnCode) || "repay-files".equals(btnCode)){
            model.put("opr",req.getParameter("opr"));
            if ("repay-files".equals(btnCode)){
                model.put("keyId",req.getParameter("keyId"));
                model.put("keyCode",req.getParameter("keyCode"));
            }
            return new ModelAndView("/fina/settlement/files",model);
        }else if ("get-settlement-track".equals(btnCode) || "operate-bad".equals(btnCode)){
            model.put("dataType",req.getParameter("dataType"));
            if ("operate-bad".equals(btnCode)){
                model.put("dataUrge",req.getParameter("dataUrge"));
            }
            return new ModelAndView("/fina/settlement/infoList",model);
        }
        return null;
    }

    /**
     * ajax获取list 列表
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "getDetail")
    public String getDetail(HttpServletRequest req, HttpServletResponse rsp){
        Map<String,Object> appendMap = new HashMap<>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LIST_FINA_SETTLEMENT_INFO, appendMap, req, rsp);
    }

    @RequestMapping(value = "info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("menuCode",req.getParameter("menuCode"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaSettlementInfo>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_FINA_SETTLEMENT_INFO, null, req);
        FinaSettlementInfo finaSettlementInfo = (FinaSettlementInfo)apiFinalResponse.getResults();
        model.put("dto",finaSettlementInfo);
        return new ModelAndView("/fina/settlement/info",model);
    }

    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_OPERATE_FINA_SETTLEMENT_INFO, null, req, rsp);
    }

    @RequestMapping(value = "ajaxData")
    public String ajaxData(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_AJAX_DATA_FINA_SETTLEMENT_INFO, null, req, rsp);
    }
}
