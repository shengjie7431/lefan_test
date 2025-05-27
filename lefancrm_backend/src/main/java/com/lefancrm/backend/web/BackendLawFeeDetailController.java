package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/10/22.
 */
@Controller
@RequestMapping(value = "/law")
public class BackendLawFeeDetailController extends BackendBaseController {

    /**
     * 查询列表
     *
     */
    @RequestMapping(value = "/feeDetail/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        String caseNo = req.getParameter("caseNo");
        String entrustUserName = req.getParameter("entrustUserName");
        String entrustUserTel = req.getParameter("entrustUserTel");
        String type = req.getParameter("type");

        //list
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<LawFeeDetailDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_LAW_FEE_DETAIL_INFO_LIST, null, req);

        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("entrustUserName",entrustUserName==null?"":entrustUserName);
        model.put("entrustUserTel",entrustUserTel==null?"":entrustUserTel);
        model.put("type",type==null?"":type);

        return new ModelAndView("/law/feeDetail/list",model);
    }

    /**
     * 查询开票详情页面
     *
     */
    @RequestMapping(value = "/feeDetail/view")
    public ModelAndView view(HttpServletRequest req , HttpServletResponse rsp) {
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<LawFeeDetailDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_LAW_FEE_DETAIL_BY_INFO, null, req);
        LawFeeDetailDto lawFeeDetailDto = (LawFeeDetailDto) apiFinalResponse.getResults();
        model.put("lawFeeDetailDto", lawFeeDetailDto);

        return new ModelAndView("/law/feeDetail/view",model);
    }

    /**
     * 退费修改
     * @param req
     * @return
     */
    @RequestMapping(value = "/feeDetail/upd")
    public String upd (HttpServletRequest req,HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LAW_FEE_DETAIL_UPD, null, req, rsp);
    }
}
