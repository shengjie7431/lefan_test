package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.PositionInfoDto;
import com.lefancrm.backend.dto.PositionPromotionDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/3/24.
 * “职位晋升指标”数据管理
 */
@Controller
@RequestMapping(value = "/positionPromotion")
public class BackendPositionPromotionController extends BackendBaseController{
    /*
    * 获取“职位晋升指标”数据列表
    */
    @RequestMapping(value = "/positionPromotionList")
    public ModelAndView positionPromotionList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PositionPromotionDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_POSITION_PROMOTION_LIST, null, req);
        Map model = new HashMap();

        //职位名称
        String positionName = req.getParameter("positionName");
        model.put("positionName", positionName);
        model.put("page", page);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/positionPromotion/list",model);
    }

    /*
    * 编辑数据
    */
    @RequestMapping(value = "/positionPromotionEdit")
    public ModelAndView positionPromotionEdit(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<PositionPromotionDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_POSITION_PROMOTION_EDIT, appendMap, null);
        Map model = new HashMap();
        //获取职位数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_INFO_LIST, null, req);
        List<PositionInfoDto> positionInfo = (List<PositionInfoDto>) apiFinalResponse1.getResults();
        model.put("positionInfo", positionInfo);

        model.put("positionPromotion",apiFinalResponse.getResults());
        return new ModelAndView("/positionPromotion/edit",model);
    }

    /*
    * add页面
    */
    @RequestMapping(value = "/positionPromotionAdd")
    public ModelAndView positionPromotionAdd(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //获取职位数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_INFO_LIST, null, req);
        List<PositionInfoDto> positionInfo = (List<PositionInfoDto>) apiFinalResponse1.getResults();
        model.put("positionInfo", positionInfo);

        return new ModelAndView("/positionPromotion/add",model);
    }

    /*
    * 新增或修改数据
    */
    @RequestMapping(value = "/positionPromotionUpdate")
     public String positionPromotionUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_POSITION_PROMOTION_SAVE, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_POSITION_PROMOTION_UPDATE, null, req, rsp);
        }

    }

    @RequestMapping(value = "/positionPromotionDelete")
    public String positionPromotionDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_POSITION_PROMOTION_DELETE, null, req, rsp);
    }
}
