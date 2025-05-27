package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.PromotionOutlayDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/06/21.
 */
@Controller
@RequestMapping(value = "/promotionOutlay")
public class BackendPromotionOutlayController extends BackendBaseController {

    /**
     * 根据“caseNo”查询推广费用记录表
     * @param req
     * @return
     */
    @RequestMapping(value = "/promotionOutlayView")
    public ModelAndView promotionOutlayView(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PromotionOutlayDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PROMOTION_OUTLAY_BY_CASENO, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);

        model.put("page", page);
        return new ModelAndView("/promotionOutlay/promotionOutlayView",model);
    }
}
