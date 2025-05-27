package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/05/17.
 */
@Controller
@RequestMapping(value = "/withdrawalsInfo")
public class BackendWithdrawalsInfoController extends BackendBaseController {

    /**
     * 提现审核列表
     * @param req
     * @return
     */
    @RequestMapping(value = "/withdrawalsInfoList")
    public ModelAndView withdrawalsInfoList(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        String orgId = req.getParameter("orgId");
        String widraCode = req.getParameter("widraCode");
        String state = req.getParameter("state");
        String mtype = req.getParameter("mtype");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<WithdrawalsInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_WITHDRAWALS_INFO_LIST, null, req);

        //parentId为1的机构list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("orgInfoDtos",orgInfoDtos);
        model.put("widraCode",widraCode==null?"":widraCode);
        model.put("state",state==null?"":state);
        model.put("orgId",orgId==null?"":orgId);

        model.put("mtype",mtype);
        model.put("page", page);
        model.put("pageSize",req.getParameter("pageSize"));
        return new ModelAndView("/withdrawalsInfo/withdrawalsInfoList",model);

    }

    /**
     * 提现用户收支明细
     * @param req
     * @return
     */
    @RequestMapping(value = "/withdrawalsDetails")
    public ModelAndView withdrawalsDetails(HttpServletRequest req , HttpServletResponse rsp, Long userId){
        String page = req.getParameter("page");
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        //该用户的收支记录明细
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PromotionOutlayDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PROMOTION_OUTLAY_DTO_LIST, null, req);

        //用户账户总金额；可提现金额；已经提现金额 三个数据
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<UserAccountDto>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_USER_ACCOUNT_DTO, null, req);
        UserAccountDto userAccountDto=(UserAccountDto)apiFinalResponse1.getResults();

        Map model = new HashMap();
        req.setAttribute("userAccountDto", userAccountDto);
        req.setAttribute("apiRsp", apiFinalResponse);
        req.setAttribute("page", page);
        req.setAttribute("userId", userId);

        return new ModelAndView("/withdrawalsInfo/withdrawalsInfoDetails",model);
    }

    /**
     * 审核提现申请
     * @param req
     * @return
     */
    @RequestMapping(value = "/editWithdrawalsState")
    public String editWithdrawalsState (HttpServletRequest req,HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_WITHDRAWALS_STATE_EDIT, null, req, rsp);
    }

    /**
     * 减掉此笔费用
     * @param req
     * @return
     */
    @RequestMapping(value = "/cutOffPromotionOutlayInfo")
    public String cutOffPromotionOutlayInfo (HttpServletRequest req,HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_PROMOTION_OUTLAY_CUT_OFF, null, req, rsp);
    }

    /**
     * 提现数据详情
     * @param req
     * @return
     */
    @RequestMapping(value = "/withdrawalsView")
    public ModelAndView withdrawalsView(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("withdrawalsId",req.getParameter("withdrawalsId"));

        TypeToken typeToken = new TypeToken<ApiFinalResponse<WithdrawalsInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_WITHDRAWALS_INFO_BY_ID, null, req);
        WithdrawalsInfoDto withdrawalsInfo = (WithdrawalsInfoDto) apiFinalResponse.getResults();
        model.put("withdrawalsInfo", withdrawalsInfo);
        return new ModelAndView("/withdrawalsInfo/withdrawalsView",model);

    }


    /**
     * 线下提现
     * @param req
     * @return
     */
    @RequestMapping(value = "/withdrawalsUnline")
    public ModelAndView withdrawalsUnline(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("withdrawalsId",req.getParameter("withdrawalsId"));
        return new ModelAndView("/withdrawalsInfo/withdrawalsUnline",model);
    }

    /**
     * 保存线下提现凭证
     * @param req
     * @return
     */
    @RequestMapping(value = "/withdrawalsUnlineSave")
    public String withdrawalsUnlineSave(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_WITHDRAWALS_INFO_UNLINE_SAVE, null, req, rsp);
    }

    /**
     * 在线提现
     * @param req
     * @return
     */
    @RequestMapping(value = "/withdrawalsOnline")
    public String withdrawalsOnline(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_WITHDRAWALS_INFO_ONLINE, null, req, rsp);
    }

}
