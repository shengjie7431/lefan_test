package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.LevelPromotionDto;
import com.lefancrm.backend.dto.PositionInfoDto;
import com.lefancrm.backend.dto.PositionLevelDto;
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
 * “职级晋升指标”数据管理
 */
@Controller
@RequestMapping(value = "/levelPromotion")
public class BackendLevelPromotionController extends BackendBaseController{
    /*
    * 获取“职级晋升指标”数据列表
    */
    @RequestMapping(value = "/levelPromotionList")
    public ModelAndView levelPromotionList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<LevelPromotionDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_LEVEL_PROMOTION_LIST, null, req);
        Map model = new HashMap();
        model.put("page", page);
        //职级名称
        String levelCode = req.getParameter("levelCode");
        model.put("levelCode", levelCode);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/levelPromotion/list",model);
    }

    /*
    * 编辑数据
    */
    @RequestMapping(value = "/levelPromotionEdit")
    public ModelAndView levelPromotionEdit(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<LevelPromotionDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_LEVEL_PROMOTION_EDIT, appendMap, null);
        Map model = new HashMap();
        //获取职位级别数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevel", positionLevel);

        model.put("levelPromotion",apiFinalResponse.getResults());
        return new ModelAndView("/levelPromotion/edit",model);
    }

    /*
    * add页面
    */
    @RequestMapping(value = "/levelPromotionAdd")
    public ModelAndView levelPromotionAdd(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //获取职位级别数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<PositionLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_POSITION_LEVEL_LIST, null, req);
        List<PositionLevelDto> positionLevel = (List<PositionLevelDto>) apiFinalResponse1.getResults();
        model.put("positionLevel", positionLevel);

        return new ModelAndView("/levelPromotion/add",model);
    }

    /*
    * 新增或修改数据
    */
    @RequestMapping(value = "/levelPromotionUpdate")
     public String levelPromotionUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LEVEL_PROMOTION_SAVE, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LEVEL_PROMOTION_UPDATE, null, req, rsp);
        }

    }

    @RequestMapping(value = "/levelPromotionDelete")
    public String levelPromotionDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LEVEL_PROMOTION_DELETE, null, req, rsp);
    }
}
